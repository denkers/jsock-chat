
package com.kyleruss.jsockchat.server.io;

import com.kyleruss.jsockchat.commons.updatebean.UpdateBeanDump;
import com.kyleruss.jsockchat.commons.user.User;
import com.kyleruss.jsockchat.server.core.ServerConfig;
import com.kyleruss.jsockchat.server.core.SocketManager;
import com.kyleruss.jsockchat.server.core.UserManager;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Collection;

public class UpdateBroadcastServer extends SyncedServer
{
    private static UpdateBroadcastServer instance;
    private DatagramSocket socket;
    
    private UpdateBroadcastServer()
    {
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
    
    @Override
    public boolean isStopped() 
    {
        return isStopped || socket == null || socket.isClosed();
    }

    protected DatagramSocket getSocket()
    {
        return socket;
    }
    
    protected synchronized void sendUpdates(UpdateBeanDump updates, User user) throws IOException
    {
        UserSocket sockContainer    =       SocketManager.getInstance().get(user.getUsername());
        Socket userSocket           =       sockContainer.getSocket();
        
        if(userSocket != null && !userSocket.isClosed())
        {
            InetAddress host    =   userSocket.getInetAddress();
            int port            =   ServerConfig.BROADCAST_PORT;
            
            ByteArrayOutputStream baos      =   new ByteArrayOutputStream();
            try(ObjectOutputStream oos      =   new ObjectOutputStream(baos))
            {
                oos.writeObject(updates);

                byte[] bData                =   baos.toByteArray();
                DatagramPacket packet       =   new DatagramPacket(bData, bData.length, host, port);
                socket.send(packet);
            }
        }
    }
    
    protected synchronized void updateUsers()
    {
        UserManager userManager =   UserManager.getInstance();
        Collection<User> users  =   userManager.getDataValues();
        
        for(User user : users)
        {
            UpdateBeanDump updates   =   userManager.prepareUpdates(user);
            
            try { sendUpdates(updates, user); } 
            catch(IOException e)
            {
                System.out.println("[AbstractBroadcaster@updateUsers]: " + e.getMessage());
            }
        }
    }

    @Override
    protected synchronized void runServerOperations()
    {
        try
        {
            wait(ServerConfig.BROADCAST_DELAY);
            updateUsers();
        }
        
        catch(InterruptedException e)
        {
            System.out.println("[AbstractBroadcastServer@runServerOperations]: " + e.getMessage());
        }
    }
    
    public static UpdateBroadcastServer getInstance()
    {
        if(instance == null) instance = new UpdateBroadcastServer();
        return instance;
    }
}
