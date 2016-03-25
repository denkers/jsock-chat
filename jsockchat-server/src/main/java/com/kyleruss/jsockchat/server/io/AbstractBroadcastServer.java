
package com.kyleruss.jsockchat.server.io;

import java.net.DatagramSocket;

public abstract class AbstractBroadcastServer extends SyncedServer
{
    private final ListBroadcaster broadcaster;
    private final DatagramSocket socket;
    private boolean isStopped;
    
    public AbstractBroadcastServer(ListBroadcaster broadcaster)
    {
        this.broadcaster    =   broadcaster;
        this.socket         =   broadcaster.getSocket();
        isStopped           =   true;
    }
    
    @Override
    public boolean isStopped() 
    {
        return isStopped || socket == null || socket.isClosed();
    }

    @Override
    public boolean stopServer() 
    {
        return isStopped = true;
    }
    
    protected ListBroadcaster getBroadcaster()
    {
        return broadcaster;
    }
    
    protected DatagramSocket getSocket()
    {
        return socket;
    }

    @Override
    protected abstract void runServerOperations();
    
}
