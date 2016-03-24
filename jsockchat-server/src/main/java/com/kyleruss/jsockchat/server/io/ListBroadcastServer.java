package com.kyleruss.jsockchat.server.io;

public class ListBroadcastServer extends SyncedServer
{
    private static ListBroadcastServer instance;
    private boolean isBroadcasting;
    
    private ListBroadcastServer()
    {
        isBroadcasting  =   true;
    }

    public static ListBroadcastServer getInstance()
    {
        if(instance == null) instance   =   new ListBroadcastServer();
        return instance;
    }

    @Override
    public boolean isServing() 
    {
        return isBroadcasting;
    }

    @Override
    public boolean isStopped() 
    {
        return false;
    }

    @Override
    public void setServing(boolean serving) 
    {
        isBroadcasting  =   serving;
    }

    @Override
    public synchronized void runServerOperations() 
    {
        
    }

    @Override
    public boolean stopServer() 
    {
        return false;
    }
}
