package com.kyleruss.jsockchat.server.io;

import java.net.DatagramSocket;
import java.net.SocketException;

public class ListBroadcastServer extends SyncedServer
{
    private static ListBroadcastServer instance;
    private boolean isBroadcasting;
    private DatagramSocket socket;
    
    private ListBroadcastServer()
    {
        isBroadcasting  =   true;
    }
    
    @Override
    protected void initSocket()
    {
        try
        {
            socket  =   new DatagramSocket();
        }
        
        catch(SocketException e)
        {
            System.out.println("[ListBroadcastServer@initSocket]: " + e.getMessage());
        }
    }

    @Override
    public boolean isServing() 
    {
        return isBroadcasting;
    }

    @Override
    public boolean isStopped() 
    {
        return socket != null || socket.isClosed();
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
    
    public static ListBroadcastServer getInstance()
    {
        if(instance == null) instance   =   new ListBroadcastServer();
        return instance;
    }
}
