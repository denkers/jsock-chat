
package com.kyleruss.jsockchat.server.io;

import com.kyleruss.jsockchat.commons.listbean.ListBean;
import com.kyleruss.jsockchat.commons.user.User;
import com.kyleruss.jsockchat.server.core.ServerConfig;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

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
    
    protected synchronized void sendListBean(ListBean bean, User user)
    {
        Socket userSocket   =   user.getClientSocket();
        
        if(userSocket != null && !userSocket.isClosed())
        {
            InetAddress host    =   userSocket.getInetAddress();
            int port            =   ServerConfig.BROADCAST_PORT;
        }
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
