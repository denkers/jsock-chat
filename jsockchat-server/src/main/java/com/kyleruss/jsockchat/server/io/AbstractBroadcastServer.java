
package com.kyleruss.jsockchat.server.io;

import java.net.DatagramSocket;

public abstract class AbstractBroadcastServer extends SyncedServer
{
    private final ListBroadcaster broadcaster;
    private final DatagramSocket socket;
    private boolean isStopped;
    private int updateTime;
    
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
    
    public int getUpdateTime()
    {
        return updateTime;
    }
    
    public void setUpdateTime(int updateTime)
    {
        this.updateTime =   updateTime;
    }
    
    protected abstract void runBroadcastOperations();

    @Override
    protected synchronized void runServerOperations()
    {
        try
        {
            wait(updateTime);
            broadcaster.getMutex().acquire();
            runBroadcastOperations();
            broadcaster.getMutex().release();
        }
        
        catch(InterruptedException e)
        {
            System.out.println("[AbstractBroadcastServer@runServerOperations]: " + e.getMessage());
        }
    }
    
}
