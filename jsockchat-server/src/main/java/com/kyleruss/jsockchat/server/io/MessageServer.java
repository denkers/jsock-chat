
package com.kyleruss.jsockchat.server.io;

import com.kyleruss.jsockchat.server.core.ServerConfig;
import com.kyleruss.jsockchat.server.gui.ServerStatusPanel;
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
    public synchronized void stopServer()
    {
        if(isStopped || serverSocket == null || serverSocket.isClosed())
            return;
        
        try
        {
            serverSocket.close();
            isStopped = true;
        }
        
        catch(IOException e)
        {
            System.out.println("[MessageServer@stopServer]: " + e.getMessage());
        }
    }
    
    private void handleClientSocket(Socket socket)
    {
        System.out.println("[MessageServer] New connection: " + socket);
        ServerMessageListener messageHandler =   new ServerMessageListener(socket);
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
            Socket clientSocket =   serverSocket.accept();
            handleClientSocket(clientSocket);
        }
            
        catch(IOException e)
        {
            System.out.println("[MessageServer@runServerOperations]: " + e.getMessage());
        }
    }
    
    @Override
    public synchronized void setServingSync(boolean serving)
    {
        super.setServingSync(serving);
        ServerStatusPanel.getInstance().setServerStatus(serving, ServerConfig.MESSAGE_LISTEN_SERVER_CODE);
    }
    
    public static MessageServer getInstance()
    {
        if(instance == null) instance = new MessageServer();
        return instance;
    }
}
