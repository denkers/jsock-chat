
package com.kyleruss.jsockchat.client.core;

import com.kyleruss.jsockchat.client.io.ClientMessageListener;
import com.kyleruss.jsockchat.client.io.ClientMessageSender;
import com.kyleruss.jsockchat.client.message.ClientAuthMessage;
import com.kyleruss.jsockchat.commons.message.MessageQueueItem;
import com.kyleruss.jsockchat.commons.message.RequestMessage;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ClientManager 
{
    private static ClientManager instance;
    private static ClientMessageSender sender;
    
    private ClientManager() {}
    
    private void startServers()
    {
        SocketManager sockMgr   =   SocketManager.getInstance();
        ClientMessageListener listener  =   ClientMessageListener.getInstance(sockMgr.getTcpSocket());
        listener.start();
        
        sender  =   ClientMessageSender.getInstance();
        sender.start();
        
    }
    
    public static ClientManager getInstance()
    {
        if(instance == null) instance = new ClientManager();
        return instance;
    }
    
    public static void main(String[] args)
    {
        ClientManager manager   =   getInstance();
        manager.startServers();

        try
        {
            RequestMessage authMessage   =   (RequestMessage) new ClientAuthMessage();
            ObjectOutputStream oos  =   SocketManager.getInstance().getTCPOutputStream();
            MessageQueueItem item   =   new MessageQueueItem(oos, authMessage);
            sender.addMessage(item);
        }
        
        catch(IOException e)
        {
            System.out.println("[ClientManager@main]: " + e.getMessage());
        }
    }
}
