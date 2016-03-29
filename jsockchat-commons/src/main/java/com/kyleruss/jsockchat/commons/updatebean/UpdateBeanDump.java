package com.kyleruss.jsockchat.commons.updatebean;

import java.io.Serializable;
import java.util.Date;

public class UpdateBeanDump implements Serializable
{
    private FriendsUpdateBean friendList;
    
    private RoomsUpdateBean roomList;
    
    private Date updateTime;
    
    
    public UpdateBeanDump()
    {
        this(new FriendsUpdateBean(), new RoomsUpdateBean());
    }
    
    public UpdateBeanDump(FriendsUpdateBean friendList, RoomsUpdateBean roomList)
    {
        updateTime          =   new Date();
        this.friendList     =   friendList;
        this.roomList       =   roomList;
    }
    
    public FriendsUpdateBean getFriendList() 
    {
        return friendList;
    }

    public void setFriendList(FriendsUpdateBean friendList)
    {
        this.friendList = friendList;
    }

    public RoomsUpdateBean getRoomList()
    {
        return roomList;
    }

    public void setRoomList(RoomsUpdateBean roomList)
    {
        this.roomList = roomList;
    }
    
    public Date getUpdateTime()
    {
        return updateTime;
    }
}
