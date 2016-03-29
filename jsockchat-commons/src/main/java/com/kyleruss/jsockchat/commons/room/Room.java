package com.kyleruss.jsockchat.commons.room;

import com.kyleruss.jsockchat.commons.user.IUser;
import java.util.ArrayList;
import java.util.List;

public class Room implements IRoom
{
    private List<IUser> userList;
    private final String name;
    private final String password;
    private final boolean isRooted;
    private boolean isPrivate;
    
    public Room(String roomName, boolean isPrivate, String roomPassword, boolean isRooted)
    {
        userList            =   new ArrayList<>();
        this.isPrivate      =   isPrivate;
        this.password       =   roomPassword;
        this.name           =   roomName;
        this.isRooted       =   isRooted;
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
    public boolean isEmpty()
    {
        return userList.isEmpty();
    }
    
    @Override
    public boolean hasUser(IUser user)
    {
        return userList.contains(user);
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
    public boolean joinRoom(IUser user)
    {
        if(hasUser(user)) return false;
        else return userList.add(user);
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
    public int hashCode()
    {
        return name.hashCode();
    }
    
    @Override
    public boolean equals(Object other)
    {
        return other instanceof Room && ((Room) other).getRoomName().equals(name);
    }

    @Override
    public boolean isRooted() 
    {
        return isRooted;
    }
}
