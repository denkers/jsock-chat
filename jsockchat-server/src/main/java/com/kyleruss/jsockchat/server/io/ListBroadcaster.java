package com.kyleruss.jsockchat.server.io;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.Semaphore;

public class ListBroadcaster
{
    private static ListBroadcaster instance;
    private DatagramSocket socket;
    private Semaphore mutex;
    
    private ListBroadcaster()
    {
        mutex   =   new Semaphore(1);
        initSocket();
    }
    
    private void initSocket()
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
    
    protected Semaphore getMutex()
    {
        return mutex;
    }

    public static ListBroadcaster getInstance()
    {
        if(instance == null) instance   =   new ListBroadcaster();
        return instance;
    }
}
