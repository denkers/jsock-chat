
package com.kyleruss.jsockchat.server.core;

import com.kyleruss.jsockchat.server.io.MessageServer;
import com.kyleruss.jsockchat.server.io.ServerMessageSender;
import com.kyleruss.jsockchat.server.io.UpdateBroadcastServer;

public class ServerManager 
{
    private static ServerManager instance;
    
    private ServerManager() {} 
    
    private void startServers()
    {
        MessageServer messageServer     =   MessageServer.getInstance();
        messageServer.start();
        
        ServerMessageSender sendServer  =   ServerMessageSender.getInstance();
        sendServer.start();
        
        UpdateBroadcastServer broadcastServer =   UpdateBroadcastServer.getInstance();
        broadcastServer.start();
    }
    
    public static ServerManager getInstance()
    {
        if(instance == null) instance = new ServerManager();
        return instance;
    }
    
    public static void main(String[] args)
    {
        ServerManager manager   =   getInstance();
        manager.startServers();
    }
}
