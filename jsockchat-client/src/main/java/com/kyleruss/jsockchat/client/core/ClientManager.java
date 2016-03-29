
package com.kyleruss.jsockchat.client.core;

import com.kyleruss.jsockchat.client.io.ClientMessageListener;
import com.kyleruss.jsockchat.client.io.ClientMessageSender;
import com.kyleruss.jsockchat.client.io.ListUpdateListener;
import com.kyleruss.jsockchat.client.updatebean.FriendsUpdateBeanHandler;
import com.kyleruss.jsockchat.client.updatebean.RoomsUpdateBeanHandler;
import com.kyleruss.jsockchat.client.updatebean.UsersUpdateBeanHandler;
import com.kyleruss.jsockchat.commons.message.AuthMsgBean;
import com.kyleruss.jsockchat.commons.message.CreateRoomMsgBean;
import com.kyleruss.jsockchat.commons.message.JoinRoomMsgBean;
import com.kyleruss.jsockchat.commons.message.MessageQueueItem;
import com.kyleruss.jsockchat.commons.message.RegisterMsgBean;
import com.kyleruss.jsockchat.commons.message.RequestMessage;
import com.kyleruss.jsockchat.commons.updatebean.UpdateBeanDump;
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
    
    public void handleUpdates(UpdateBeanDump update)
    {
        FriendsUpdateBeanHandler friendsHandler =   new FriendsUpdateBeanHandler(update.getFriendsBean());
        RoomsUpdateBeanHandler roomsHandler     =   new RoomsUpdateBeanHandler(update.getRoomsBean());
        UsersUpdateBeanHandler usersHandler     =   new UsersUpdateBeanHandler(update.getUsersBean());
        
        usersHandler.beanAction();
        roomsHandler.beanAction();
        friendsHandler.beanAction();
    }
    
    public void sendRequest(RequestMessage request) throws IOException
    {
        ObjectOutputStream oos  =   SocketManager.getInstance().getTCPOutputStream();
        MessageQueueItem item   =   new MessageQueueItem(oos, request);
        sender.addMessage(item);
    }
    
    public static void main(String[] args)
    {
        ClientManager manager   =   getInstance();
        manager.startServers();

        try
        {
            AuthMsgBean bean        =   new AuthMsgBean("testaccount1", "mypass");
            RequestMessage request  =   new RequestMessage(null, bean);
            request.setDescription("HELLO FROM CLIENT");
            manager.sendRequest(request);
            
            
            CreateRoomMsgBean bean2   =   new CreateRoomMsgBean("noroom", "qweqwe", false);
            RequestMessage request2 =   new RequestMessage("testaccount1", bean2);
            manager.sendRequest(request2);
        }
        
        catch(IOException e)
        {
            System.out.println("[ClientManager@main]: " + e.getMessage());
        }
    }
}
