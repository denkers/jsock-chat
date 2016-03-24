package com.kyleruss.jsockchat.server.io;

public class ListBroadcastServer extends SyncedServer
{
    private static ListBroadcastServer instance;
    private boolean isBroadcasting;
    
    private ListBroadcastServer()
    {
        isBroadcasting  =   true;
    }
    
    public synchronized void setBroadcasting(boolean isBroadcasting)
    {
        this.isBroadcasting  =   isBroadcasting;
        notify();
    }
  
    public boolean isBroadcasting()
    {
        return isBroadcasting;
    }
    
    @Override
    public void run() 
    {
    }
    
    public static ListBroadcastServer getInstance()
    {
        if(instance == null) instance   =   new ListBroadcastServer();
        return instance;
    }
}
