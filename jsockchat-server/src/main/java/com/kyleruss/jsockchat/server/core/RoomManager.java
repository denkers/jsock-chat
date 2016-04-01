
package com.kyleruss.jsockchat.server.core;

import com.kyleruss.jsockchat.commons.message.DisconnectMsgBean;
import com.kyleruss.jsockchat.commons.message.Message;
import com.kyleruss.jsockchat.commons.message.MessageQueueItem;
import com.kyleruss.jsockchat.commons.message.RequestMessage;
import com.kyleruss.jsockchat.commons.message.ResponseMessage;
import com.kyleruss.jsockchat.commons.room.Room;
import com.kyleruss.jsockchat.commons.updatebean.RoomsUpdateBean;
import com.kyleruss.jsockchat.commons.user.IUser;
import com.kyleruss.jsockchat.server.gui.AppResources;
import com.kyleruss.jsockchat.server.gui.LogMessage;
import com.kyleruss.jsockchat.server.gui.LoggingList;
import com.kyleruss.jsockchat.server.io.ServerMessageSender;
import com.kyleruss.jsockchat.server.io.UserSocket;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public final class RoomManager extends AbstractManager<String, Room>
{
    private static RoomManager instance;
    private String channelNotice;
    
    private RoomManager() 
    {
        initFixedRooms();
    }
    
    public synchronized List<IUser> getUsersInRoom(String roomName)
    {
        Room room           =   get(roomName);
        
        if(room != null) return room.getUserList();
        else return new ArrayList<>();
    }
    
    public synchronized void sendMessageToRoom(String roomName, Message message, List<IUser> exclusions)
    {
        if(!find(roomName))
            return;
        
        List<IUser> roomUsers   =   getUsersInRoom(roomName);
        
        for(IUser user : roomUsers)
        {
            if(exclusions != null && exclusions.contains(user))
                continue;
            
            try
            {
                String username                 =   user.getUsername();
                UserSocket userSocket           =   SocketManager.getInstance().get(username);
                MessageQueueItem messageItem    =   new MessageQueueItem(userSocket.getSocketOutputStream(), message);
                ServerMessageSender.getInstance().addMessage(messageItem);
            }
            
            catch(IOException e)
            {
                System.out.println("[RoomManager@sendMessageToRoom]: " + e.getMessage());
            }
        }
    }
    
    public static List<IUser> createExclusions(IUser... excludedUsers)
    {
        List<IUser> exclusions  =   new ArrayList<>();
        for(IUser user : excludedUsers)
            exclusions.add(user);
        
        return exclusions;
    }
    
    public synchronized void leaveRoom(IUser user, String roomName)
    {
        if(find(roomName))
        {
            LoggingList.sendLogMessage(new LogMessage("[Room manager] User '" + user.getUsername() + "' has left room '" + roomName + "'", 
            AppResources.getInstance().getDcImage()));
            
            user.getCurrentRooms().remove(roomName);
            Room room   =   get(roomName);
            room.leaveRoom(user);
            
            if(room.isEmpty() && !room.isFixed())
            {
                data.remove(roomName);
                LoggingList.sendLogMessage(new LogMessage("[Room manager] Room '" + roomName + "' is empty, destroying...", AppResources.getInstance().getServerOkImage()));
            }
            
            else
            {
                DisconnectMsgBean bean      =   new DisconnectMsgBean(user.getUsername(), room.getRoomName(), false);
                RequestMessage request      =   new RequestMessage(user.getUsername(), bean);
                ResponseMessage response    =   new ResponseMessage(request);
                sendMessageToRoom(room.getRoomName(), response, createExclusions(user));
                
                LoggingList.sendLogMessage(new LogMessage("[Room manager] Notified witnesses of room '" + roomName + "' that user '" + user.getUsername() + "' has left", 
                AppResources.getInstance().getBroadcastImage()));
            }
        }
    }
    
    public void leaveAllRooms(IUser user)
    {
        List<String> currentRooms   =   user.getCurrentRooms();
        for(String roomName : currentRooms)
            leaveRoom(user, roomName);
    }
    
    public void setChannelNotice(String channelNotice)
    {
        this.channelNotice  =   channelNotice;
    }
    
    public RoomsUpdateBean createRoomsBean()
    {
        RoomsUpdateBean bean    =   new RoomsUpdateBean();
        bean.setChannelNotice(channelNotice);
        bean.setData(data);
        
        return bean;
    }
    
    private void initFixedRooms()
    {
        try
        {
            DocumentBuilderFactory builderFactory   =   DocumentBuilderFactory.newInstance();
            DocumentBuilder builder                 =   builderFactory.newDocumentBuilder();
            Document doc                            =   builder.parse(new File(ServerConfig.FIXED_ROOMS_PATH));
            NodeList roomTags                       =   doc.getElementsByTagName("room");
            
            for(int i = 0; i < roomTags.getLength(); i++)
            {
                Element roomTag     =   (Element) roomTags.item(i);
                String roomName     =   roomTag.getElementsByTagName("name").item(0).getTextContent();
                String password     =   roomTag.getElementsByTagName("password").item(0).getTextContent();
                Room room           =   new Room(roomName, false, password, true);
                add(roomName, room);
            }
            
            LoggingList.sendLogMessage(new LogMessage("[Room manager] rooms.xml has been initialized", AppResources.getInstance().getServerOkImage()));
        }
        
        catch(IOException | ParserConfigurationException | SAXException e)
        {
            System.out.println("[RoomManager@initFixedRooms]: " + e.getMessage());
            LoggingList.sendLogMessage(new LogMessage("[Room manager] Failed to initialize rooms.xml", AppResources.getInstance().getServerBadImage()));
        }
    }
    
    public static RoomManager getInstance()
    {
        if(instance == null) instance = new RoomManager();
        return instance;
    }
}
