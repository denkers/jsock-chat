
package com.kyleruss.jsockchat.server.io;

import com.kyleruss.jsockchat.server.core.ServerConfig;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public final class MessageServer extends SyncedServer
{
    private static MessageServer instance;
    private ServerSocket serverSocket;
    
    private MessageServer()
    {
        initSocket();
    }
    
    protected void initSocket()
    {
        try
        {
            if(serverSocket != null) return;
            
            serverSocket    =   new ServerSocket(ServerConfig.MESSAGE_SERVER_PORT);
            serverSocket.setSoTimeout(ServerConfig.MESSAGE_SERVER_TIMEOUT);
        }
        
        catch(IOException e)
        {
            System.out.println("[MessageServer@startServer]: " + e.getMessage());
        }
    }
    
    @Override
    public synchronized boolean stopServer()
    {
        if(serverSocket == null || serverSocket.isClosed())
            return true;
        
        try
        {
            serverSocket.close();
            return true;
        }
        
        catch(IOException e)
        {
            System.out.println("[MessageServer@stopServer]: " + e.getMessage());
            return false;
        }
    }
    
    private void handleClientSocket(Socket socket)
    {
        MessageSocketHandler messageHandler =   new MessageSocketHandler(socket);
        messageHandler.start();
    }

    @Override
    public boolean isStopped() 
    {
        return serverSocket == null || serverSocket.isClosed();
    }

    @Override
    protected synchronized void runServerOperations() 
    {
        try
        {
            if(!isStopped() && isServing())
            {
                Socket clientSocket =   serverSocket.accept();
                handleClientSocket(clientSocket);
            }
        }
            
        catch(IOException e)
        {
            System.out.println("[MessageServer@runServerOperations]: " + e.getMessage());
        }
    }
    
    public static MessageServer getInstance()
    {
        if(instance == null) instance = new MessageServer();
        return instance;
    }
}
