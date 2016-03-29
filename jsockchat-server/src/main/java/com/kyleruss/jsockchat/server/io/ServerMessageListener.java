
package com.kyleruss.jsockchat.server.io;

import com.kyleruss.jsockchat.commons.io.MessageListener;
import com.kyleruss.jsockchat.commons.message.RequestMessage;
import com.kyleruss.jsockchat.server.core.SocketManager;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerMessageListener extends MessageListener<RequestMessage>
{
    private static ServerMessageListener instance;
    protected String servingUser;
    
    public ServerMessageListener(Socket socket) 
    {
        super(socket);
        initUserSocket();
    }
    
    private void initUserSocket()
    {
        if(socket != null)
        {
            String clientIP             =   socket.getInetAddress().getHostName();
            System.out.println(clientIP);
            SocketManager sockManager   =   SocketManager.getInstance();
            
            if(!sockManager.find(clientIP))
            {
                UserSocket userSocket       =   new UserSocket(socket);
                sockManager.add(clientIP, userSocket);
                servingUser =   clientIP;
            }
            
        }
    }

    @Override
    protected void handleReceivedMessage(RequestMessage request) 
    {
        if(request != null)
        {
            if(request.getUserSource() != null)
                servingUser = request.getUserSource();
            
            System.out.println("[ServerMessageListener] handleReceivedMessage");
            System.out.println("Request description: " + request.getDescription());
        }
    }

    @Override
    protected RequestMessage getMessage(ObjectInputStream inputStream)
    {
        try
        {
            System.out.println("[ServerMessageListener] GetMessage");
            RequestMessage request   =   (RequestMessage) inputStream.readObject();
            return request;
        }
        
        catch(IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
            System.out.println("[ServerMessageListener@getMessage]: " + e.getMessage());
            return null;
        }
    }
    
    @Override
    protected void handleCleanup(ObjectInputStream inputStream)
    {
        try
        {
            inputStream.close();
            
            if(servingUser != null)
            {
                SocketManager sockManager   =   SocketManager.getInstance();
                sockManager.cleanUp(servingUser);
            }
            
            else socket.close();
        }
        
        catch(IOException e)
        {
            System.out.println("[ServerMessageListener@handleCleanup]: " + e.getMessage());
        }
    }
    
    public static ServerMessageListener getInstance(Socket socket)
    {
        if(instance == null) instance   =   new ServerMessageListener(socket);
        return instance;
    }
}
