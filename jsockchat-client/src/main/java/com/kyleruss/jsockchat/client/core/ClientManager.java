
package com.kyleruss.jsockchat.client.core;

import com.kyleruss.jsockchat.client.io.ClientMessageListener;
import com.kyleruss.jsockchat.client.io.ClientMessageSender;
import com.kyleruss.jsockchat.client.io.ListUpdateListener;
import com.kyleruss.jsockchat.commons.message.AuthMsgBean;
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
        
        ListUpdateListener listUpdateListener = new ListUpdateListener(sockMgr.getUdpSocket());
        listUpdateListener.start();
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
            AuthMsgBean bean        =   new AuthMsgBean("kyleruss2", "fgsmg2", false);
            RequestMessage request  =   new RequestMessage(null, bean);
            request.setDescription("HELLO FROM CLIENT");
            ObjectOutputStream oos  =   SocketManager.getInstance().getTCPOutputStream();
            MessageQueueItem item   =   new MessageQueueItem(oos, request);
            sender.addMessage(item);
        }
        
        catch(IOException e)
        {
            System.out.println("[ClientManager@main]: " + e.getMessage());
        }
    }
}
