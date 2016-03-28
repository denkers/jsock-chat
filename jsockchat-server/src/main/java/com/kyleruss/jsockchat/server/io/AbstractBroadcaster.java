
package com.kyleruss.jsockchat.server.io;

import com.kyleruss.jsockchat.commons.listbean.ListBean;
import com.kyleruss.jsockchat.server.core.ServerConfig;
import com.kyleruss.jsockchat.server.user.ServerUser;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public abstract class AbstractBroadcaster extends SyncedServer
{
    private final ListBroadcastServer broadcastServer;
    private final DatagramSocket socket;
    private int updateTime;
    
    public AbstractBroadcaster(ListBroadcastServer broadcaster, int updateTime)
    {
        this.broadcastServer    =   broadcaster;
        this.socket             =   broadcaster.getSocket();
        this.updateTime         =   updateTime;
    }
    
    @Override
    public boolean isStopped() 
    {
        return isStopped || socket == null || socket.isClosed();
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
    
    protected synchronized void sendListBean(ListBean bean, ServerUser user) throws IOException
    {
        Socket userSocket   =   user.getSocket();
        
        if(userSocket != null && !userSocket.isClosed())
        {
            InetAddress host    =   userSocket.getInetAddress();
            int port            =   ServerConfig.BROADCAST_PORT;
            
            ByteArrayOutputStream baos      =   new ByteArrayOutputStream();
            try(ObjectOutputStream oos      =   new ObjectOutputStream(baos))
            {
                oos.writeObject(bean);

                byte[] bData                =   baos.toByteArray();
                DatagramPacket packet       =   new DatagramPacket(bData, bData.length, host, port);
                socket.send(packet);
            }
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
