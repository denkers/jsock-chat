package com.kyleruss.jsockchat.server.io;

public class ListBroadcastServer extends Thread
{
    private static ListBroadcastServer instance;
    
    private ListBroadcastServer()
    {
        
    }
    
    public static ListBroadcastServer getInstance()
    {
        if(instance == null) instance   =   new ListBroadcastServer();
        return instance;
    }
}
