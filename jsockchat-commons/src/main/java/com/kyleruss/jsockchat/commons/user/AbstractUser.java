
package com.kyleruss.jsockchat.commons.user;

import java.io.Serializable;
import java.net.Socket;
import java.util.List;


public abstract class AbstractUser implements User, Serializable
{
    private String username;
    private String displayName;
    private List<String> currentRooms;
    
    @Override
    public String getUsername() 
    {
        return username;
    }

    @Override
    public List<String> getActiveRooms() 
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
