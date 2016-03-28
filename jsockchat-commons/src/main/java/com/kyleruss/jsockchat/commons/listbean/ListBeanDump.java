package com.kyleruss.jsockchat.commons.listbean;

import java.io.Serializable;

public class ListBeanDump implements Serializable
{
    private FriendListBean friendList;
    
    private RoomListBean roomList;
    
    
    public ListBeanDump()
    {
        this(new FriendListBean(), new RoomListBean());
    }
    
    public ListBeanDump(FriendListBean friendList, RoomListBean roomList)
    {
        this.friendList     =   friendList;
        this.roomList       =   roomList;
    }
    
    public FriendListBean getFriendList() 
    {
        return friendList;
    }

    public void setFriendList(FriendListBean friendList)
    {
        this.friendList = friendList;
    }

    public RoomListBean getRoomList()
    {
        return roomList;
    }

    public void setRoomList(RoomListBean roomList)
    {
        this.roomList = roomList;
    }
}
