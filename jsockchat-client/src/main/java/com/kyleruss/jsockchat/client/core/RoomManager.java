package com.kyleruss.jsockchat.client.core;

import com.kyleruss.jsockchat.commons.listbean.RoomListBean;
import com.kyleruss.jsockchat.commons.room.Room;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomManager 
{
    private static RoomManager instance;
    private final Map<String, Room> rooms;
    private String headChannelNotice;
    
    private RoomManager()
    {
        rooms   =   new HashMap<>();
    }
    
    public void initListBean(RoomListBean bean)
    {
        setHeadChannelNotice(bean.getChannelNotice());
        List<Room> roomList =   bean.getListData();
        
        for(Room room : roomList)
            rooms.put(room.getRoomName(), room);
        
    }
    
    public String getHeadChannelNotice()
    {
        return headChannelNotice;
    }
    
    public void setHeadChannelNotice(String headChannelNotice)
    {
        this.headChannelNotice  =   headChannelNotice;
    }
    
    public static RoomManager getInstance()
    {
        if(instance == null) instance = new RoomManager();
        return instance;
    }
}
