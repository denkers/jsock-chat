
package com.kyleruss.jsockchat.server.core;

import com.kyleruss.jsockchat.commons.message.Message;
import com.kyleruss.jsockchat.commons.message.MessageQueueItem;
import com.kyleruss.jsockchat.commons.room.Room;
import com.kyleruss.jsockchat.commons.updatebean.RoomsUpdateBean;
import com.kyleruss.jsockchat.commons.user.IUser;
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
    
    public List<IUser> getUsersInRoom(String roomName)
    {
        Room room           =   get(roomName);
        
        if(room != null) return room.getUserList();
        else return new ArrayList<>();
    }
    
    public void sendMessageToRoom(String roomName, Message message, List<IUser> exclusions)
    {
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
    
    public void leaveAllRooms(IUser user)
    {
        List<String> currentRooms   =   user.getCurrentRooms();
        for(String roomName : currentRooms)
        {
            Room room   =   data.get(roomName);
            room.leaveRoom(user);
            
            if(room.isEmpty() && !room.isFixed())
                data.remove(roomName);
        }
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
        }
        
        catch(IOException | ParserConfigurationException | SAXException e)
        {
            System.out.println("[RoomManager@initFixedRooms]: " + e.getMessage());
        }
    }
    
    public static RoomManager getInstance()
    {
        if(instance == null) instance = new RoomManager();
        return instance;
    }
}
