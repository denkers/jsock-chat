
package com.kyleruss.jsockchat.server.io;

import java.net.DatagramSocket;

public abstract class AbstractBroadcaster extends SyncedServer
{
    private final ListBroadcastServer broadcastServer;
    private final DatagramSocket socket;
    private boolean isStopped;
    private int updateTime;
    
    public AbstractBroadcaster(ListBroadcastServer broadcaster)
    {
        this.broadcastServer    =   broadcaster;
        this.socket             =   broadcaster.getSocket();
        isStopped               =   true;
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
    
    protected ListBroadcastServer getBroadcastServer()
    {
        return broadcastServer;
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
            broadcastServer.getMutex().acquire();
            runBroadcastOperations();
            broadcastServer.getMutex().release();
        }
        
        catch(InterruptedException e)
        {
            System.out.println("[AbstractBroadcastServer@runServerOperations]: " + e.getMessage());
        }
    }
    
}
