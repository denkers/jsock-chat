package com.kyleruss.jsockchat.server.io;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.Semaphore;

public class ListBroadcastServer
{
    private static ListBroadcastServer instance;
    private DatagramSocket socket;
    private Semaphore mutex;
    
    private ListBroadcastServer()
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

    public static ListBroadcastServer getInstance()
    {
        if(instance == null) instance   =   new ListBroadcastServer();
        return instance;
    }
}
