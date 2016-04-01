package com.kyleruss.jsockchat.server.core;

import com.kyleruss.jsockchat.server.gui.AppResources;
import com.kyleruss.jsockchat.server.gui.LogMessage;
import com.kyleruss.jsockchat.server.gui.LoggingList;
import com.kyleruss.jsockchat.server.io.UserSocket;

public class SocketManager extends AbstractManager<String, UserSocket>
{
    private static SocketManager instance;
    
    public void cleanUp(String username)
    {
        if(find(username))
        {
            LoggingList.sendLogMessage(new LogMessage("[Socket manager] Cleaning up resources for client '" + username + "'", AppResources.getInstance().getServerOkImage()));
            
            UserSocket userSocket   =   get(username);
            userSocket.cleanUp();
            remove(username);
            UserManager.getInstance().remove(username);
        }
    }
    
    public static SocketManager getInstance()
    {
        if(instance == null) instance = new SocketManager();
        return instance;
    }
}
