package com.kyleruss.jsockchat.server.io;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.Semaphore;

public class ListBroadcastServer
{
    private static ListBroadcastServer instance;
    private DatagramSocket socket;
    private Semaphore mutex;
    private final FriendListBroadcaster friendBroadcaster;
    private final RoomListBroadcaster roomBroadcaster;
    private final RoomUserListBroadcaster roomUserBroadcaster;
    
    private ListBroadcastServer()
    {
        mutex   =   new Semaphore(1);
        initSocket();
        
        friendBroadcaster   =   new FriendListBroadcaster(this);
        roomBroadcaster     =   new RoomListBroadcaster(this);
        roomUserBroadcaster =   new RoomUserListBroadcaster(this);
        startBroadcasters();
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
    
    private void startBroadcasters()
    {
        friendBroadcaster.start();
        roomBroadcaster.start();
        roomUserBroadcaster.start();
    }
    
    protected Semaphore getMutex()
    {
        return mutex;
    }
    
    protected DatagramSocket getSocket()
    {
        return socket;
    }
    
    public FriendListBroadcaster getFriendBroadcastServer() 
    {
        return friendBroadcaster;
    }

    public RoomListBroadcaster getRoomBroadcastServer() 
    {
        return roomBroadcaster;
    }

    public RoomUserListBroadcaster getRoomUserBroadcastServer() 
    {
        return roomUserBroadcaster;
    }
    
    public static ListBroadcastServer getInstance()
    {
        if(instance == null) instance   =   new ListBroadcastServer();
        return instance;
    }
}
