package com.kyleruss.jsockchat.commons.room;

import java.util.Set;

public class AbstractRoom implements Room
{
    private Set<String> userList;
    private String roomName;
    private String roomPassword;
    private String roomOwner;
    private RoomType roomType;
    
    @Override
    public Set<String> getUserList()
    {
        return userList;
    }

    @Override
    public String getRoomPassword() 
    {
        return roomPassword;
    }

    @Override
    public void setRoomPassword(String roomPassword) 
    {
        this.roomPassword   =   roomPassword;
    }

    @Override
    public void setUserList(Set<String> userList) 
    {
        this.userList   =   userList;
    }

    @Override
    public void leaveRoom(String username) 
    {
        userList.remove(username);
    }

    @Override
    public boolean joinRoom(String username)
    {
        return userList.add(username);
    }

    @Override
    public String getRoomName()
    {
        return roomName;
    }

    @Override
    public int getNumUsersInRoom() 
    {
        return userList != null? userList.size() : 0;
    }

    @Override
    public RoomType getRoomType() 
    {
        return roomType;
    }

    @Override
    public boolean isPassProtected() 
    {
        return roomPassword != null;
    }

    @Override
    public String getOwner() 
    {
        return roomOwner;
    }
    
    @Override
    public int hashCode()
    {
        return roomName.hashCode();
    }
    
    @Override
    public boolean equals(Object other)
    {
        return other instanceof AbstractRoom && ((AbstractRoom) other).getRoomName().equals(roomName);
    }
}
