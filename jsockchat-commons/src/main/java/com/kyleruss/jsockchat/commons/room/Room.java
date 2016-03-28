package com.kyleruss.jsockchat.commons.room;

import com.kyleruss.jsockchat.commons.user.IUser;
import java.util.ArrayList;
import java.util.List;

public class Room implements IRoom
{
    private List<IUser> userList;
    private final String name;
    private final String password;
    private final String owner;
    private boolean isPrivate;
    
    public Room(String roomName, String roomOwner, boolean isPrivate, String roomPassword)
    {
        userList            =   new ArrayList<>();
        this.isPrivate      =   isPrivate;
        this.owner          =   roomOwner;
        this.password       =   roomPassword;
        this.name           =   roomName;
    }
    
    @Override
    public List<IUser> getUserList()
    {
        return userList;
    }

    @Override
    public String getRoomPassword() 
    {
        return password;
    }

    @Override
    public void setUserList(List<IUser> userList) 
    {
        this.userList   =   userList;
    }

    @Override
    public void leaveRoom(IUser username) 
    {
        userList.remove(username);
    }

    @Override
    public boolean joinRoom(IUser username)
    {
        if(userList.contains(username)) return false;
        else return userList.add(username);
    }

    @Override
    public String getRoomName()
    {
        return name;
    }

    @Override
    public int getNumUsersInRoom() 
    {
        return userList != null? userList.size() : 0;
    }

    @Override
    public boolean isPrivate()
    {
        return isPrivate;
    }
    
    @Override
    public void setPrivate(boolean isPrivate)
    {
        this.isPrivate  =   isPrivate;
    }

    @Override
    public boolean isPassProtected() 
    {
        return password != null;
    }

    @Override
    public String getOwner() 
    {
        return owner;
    }
    
    @Override
    public int hashCode()
    {
        return name.hashCode();
    }
    
    @Override
    public boolean equals(Object other)
    {
        return other instanceof Room && ((Room) other).getRoomName().equals(name);
    }
}
