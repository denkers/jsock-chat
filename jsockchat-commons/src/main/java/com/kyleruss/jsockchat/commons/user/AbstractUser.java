
package com.kyleruss.jsockchat.commons.user;

import java.io.Serializable;
import java.net.Socket;
import java.util.List;


public abstract class AbstractUser implements User, Serializable
{
    private String username;
    private String displayName;
    private List<String> currentRooms;
    private AccountLevel accLevel;
    private Socket clientSocket;
    
    @Override
    public String getUsername() 
    {
        return username;
    }

    @Override
    public abstract void executeCommand(String command);

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
    public Socket getClientSocket() 
    {
        return clientSocket;
    }

    @Override
    public abstract void authenticate();

    @Override
    public AccountLevel getAccountLevel() 
    {
        return accLevel;
    }
}
