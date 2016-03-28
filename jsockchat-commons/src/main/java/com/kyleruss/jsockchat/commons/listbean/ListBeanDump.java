package com.kyleruss.jsockchat.commons.listbean;

import java.io.Serializable;

public class ListBeanDump implements Serializable
{
    private FriendListBean friendList;
    
    private RoomListBean roomList;
    
    private UserRoomListBean userRoomList;
    
    public ListBeanDump()
    {
        this(new FriendListBean(), new RoomListBean(), new UserRoomListBean());
    }
    
    public ListBeanDump(FriendListBean friendList, RoomListBean roomList, UserRoomListBean userRoomList)
    {
        this.friendList     =   friendList;
        this.roomList       =   roomList;
        this.userRoomList   =   userRoomList;
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

    public UserRoomListBean getUserRoomList()
    {
        return userRoomList;
    }

    public void setUserRoomList(UserRoomListBean userRoomList) 
    {
        this.userRoomList = userRoomList;
    }
}
