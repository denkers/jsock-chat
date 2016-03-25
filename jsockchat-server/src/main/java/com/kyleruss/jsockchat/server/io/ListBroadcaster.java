package com.kyleruss.jsockchat.server.io;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.Semaphore;

public class ListBroadcaster
{
    private static ListBroadcaster instance;
    private DatagramSocket socket;
    private Semaphore mutex;
    private final FriendListBroadcastServer friendBroadcastServer;
    private final RoomListBroadcastServer roomBroadcastServer;
    private final RoomUserListBroadcastServer roomUserBroadcastServer;
    
    private ListBroadcaster()
    {
        mutex   =   new Semaphore(1);
        initSocket();
        
        friendBroadcastServer   =   new FriendListBroadcastServer(this);
        roomBroadcastServer     =   new RoomListBroadcastServer(this);
        roomUserBroadcastServer =   new RoomUserListBroadcastServer(this);
        
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
    
    protected DatagramSocket getSocket()
    {
        return socket;
    }
    
    public FriendListBroadcastServer getFriendBroadcastServer() 
    {
        return friendBroadcastServer;
    }

    public RoomListBroadcastServer getRoomBroadcastServer() 
    {
        return roomBroadcastServer;
    }

    public RoomUserListBroadcastServer getRoomUserBroadcastServer() 
    {
        return roomUserBroadcastServer;
    }
    
    public static ListBroadcaster getInstance()
    {
        if(instance == null) instance   =   new ListBroadcaster();
        return instance;
    }
}
