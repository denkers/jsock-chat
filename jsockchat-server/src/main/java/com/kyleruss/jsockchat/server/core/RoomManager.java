
package com.kyleruss.jsockchat.server.core;

import com.kyleruss.jsockchat.commons.room.Room;
import com.kyleruss.jsockchat.commons.updatebean.RoomsUpdateBean;
import com.kyleruss.jsockchat.commons.user.IUser;
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
