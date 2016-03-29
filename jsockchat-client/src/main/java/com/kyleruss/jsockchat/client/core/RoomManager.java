package com.kyleruss.jsockchat.client.core;

import com.kyleruss.jsockchat.commons.updatebean.RoomsUpdateBean;

public class RoomManager 
{
    private static RoomManager instance;
    private RoomsUpdateBean roomsBean;
    
    private RoomManager() {}
   
    public void setRoomsBean(RoomsUpdateBean roomsBean)
    {
        this.roomsBean  =   roomsBean;
    }
    
    public RoomsUpdateBean getRoomsBean()
    {
        return roomsBean;
    }
    
    public static RoomManager getInstance()
    {
        if(instance == null) instance = new RoomManager();
        return instance;
    }
}
