package com.kyleruss.jsockchat.server.core;

import com.kyleruss.jsockchat.server.io.UserSocket;

public class SocketManager extends AbstractManager<String, UserSocket>
{
    private static SocketManager instance;
    
    public void cleanUp(String username)
    {
        if(find(username))
        {
            UserSocket userSocket   =   get(username);
            userSocket.cleanUp();
            remove(username);
            
            UserManager userManager =   UserManager.getInstance();
            userManager.remove(username);
        }
    }
    
    public static SocketManager getInstance()
    {
        if(instance == null) instance = new SocketManager();
        return instance;
    }
}
