
package com.kyleruss.jsockchat.server.core;

import com.kyleruss.jsockchat.commons.message.Message;
import com.kyleruss.jsockchat.commons.message.MessageQueueItem;
import com.kyleruss.jsockchat.commons.room.Room;
import com.kyleruss.jsockchat.commons.updatebean.RoomsUpdateBean;
import com.kyleruss.jsockchat.commons.user.IUser;
import com.kyleruss.jsockchat.server.io.ServerMessageSender;
import com.kyleruss.jsockchat.server.io.UserSocket;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class RoomManager extends AbstractManager<String, Room>
{
    private static RoomManager instance;
    private String channelNotice;
    
    private RoomManager() {}
    
    public List<IUser> getUsersInRoom(String roomName)
    {
        Room room           =   get(roomName);
        
        if(room != null) return room.getUserList();
        else return new ArrayList<>();
    }
    
    public void sendMessageToRoom(String roomName, Message message)
    {
        List<IUser> roomUsers   =   getUsersInRoom(roomName);
        
        for(IUser user : roomUsers)
        {
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
    
    public void leaveAllRooms(IUser user)
    {
        List<String> currentRooms   =   user.getCurrentRooms();
        for(String roomName : currentRooms)
        {
            Room room   =   data.get(roomName);
            room.leaveRoom(user);
            
            if(room.isEmpty() && !room.isRooted())
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
    
    public static RoomManager getInstance()
    {
        if(instance == null) instance = new RoomManager();
        return instance;
    }
}
