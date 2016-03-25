
package com.kyleruss.jsockchat.commons.user;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


public abstract class AbstractUser implements User, Serializable
{
    private String username;
    private String displayName;
    private List<String> currentRooms;
    
    public AbstractUser(String username, String displayName)
    {
        this.username       =   username;
        this.displayName    =   displayName;
        currentRooms        =   new LinkedList<>();
    }
    
    @Override
    public String getUsername() 
    {
        return username;
    }

    @Override
    public List<String> getCurrentRooms() 
    {
        return currentRooms;
    }

    @Override
    public String getDisplayName() 
    {
        return displayName;
    }

    @Override
    public int hashCode()
    {
        return username.hashCode();
    }
    
    @Override
    public boolean equals(Object other)
    {
        return other instanceof AbstractUser && ((AbstractUser) other).getUsername().equals(username);
    }
}
