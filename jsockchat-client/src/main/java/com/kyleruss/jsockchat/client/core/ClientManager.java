
package com.kyleruss.jsockchat.client.core;

import com.kyleruss.jsockchat.client.gui.ClientPanel;
import com.kyleruss.jsockchat.client.io.ClientMessageListener;
import com.kyleruss.jsockchat.client.io.ClientMessageSender;
import com.kyleruss.jsockchat.client.io.ListUpdateListener;
import com.kyleruss.jsockchat.client.updatebean.FriendsUpdateBeanHandler;
import com.kyleruss.jsockchat.client.updatebean.RoomsUpdateBeanHandler;
import com.kyleruss.jsockchat.client.updatebean.UsersUpdateBeanHandler;
import com.kyleruss.jsockchat.commons.message.AuthMsgBean;
import com.kyleruss.jsockchat.commons.message.DisconnectMsgBean;
import com.kyleruss.jsockchat.commons.message.MessageQueueItem;
import com.kyleruss.jsockchat.commons.message.RequestMessage;
import com.kyleruss.jsockchat.commons.updatebean.UpdateBeanDump;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

public class ClientManager 
{
    private static ClientManager instance;
    private ClientMessageSender sender;
    private ClientMessageListener listener;
    private ListUpdateListener listUpdateListener;
    
    private ClientManager() {}
    
    public void startServers()
    {
        SocketManager sockMgr           =   SocketManager.getInstance();
        listener    =   new ClientMessageListener(sockMgr.getTcpSocket());
        listener.start();
        
        sender      =   new ClientMessageSender();
        sender.start();
        
        listUpdateListener = new ListUpdateListener(sockMgr.getUdpSocket());
        listUpdateListener.start();
    }
    
    public static ClientManager getInstance()
    {
        if(instance == null) instance = new ClientManager();
        return instance;
    }
    
    public void handleUpdates(UpdateBeanDump update)
    {
        Thread updateThread =   new Thread(()->
        {
            FriendsUpdateBeanHandler friendsHandler =   new FriendsUpdateBeanHandler(update.getFriendsBean());
            RoomsUpdateBeanHandler roomsHandler     =   new RoomsUpdateBeanHandler(update.getRoomsBean());
            UsersUpdateBeanHandler usersHandler     =   new UsersUpdateBeanHandler(update.getUsersBean());

            usersHandler.beanAction();
            roomsHandler.beanAction();
            friendsHandler.beanAction();
        });
        
        updateThread.start();
    }
    
    public void clearUpdates()
    {
        UserManager.getInstance().setFriendsBean(null);
        UserManager.getInstance().setUsersBean(null);
        RoomManager.getInstance().setRoomsBean(null);
    }
    
    public void logoutUser()
    {
        if(UserManager.getInstance().getActiveUser() == null) return;
        
        Thread thread   = new Thread(()->
        {
            UserManager userManager =   UserManager.getInstance();
            
            try
            {
                DisconnectMsgBean bean  =   new DisconnectMsgBean(DisconnectMsgBean.CLIENT_LOGOUT);
                RequestMessage request  =   new RequestMessage(userManager.getActiveUser().getUsername(), bean);
                ClientManager.getInstance().sendRequest(request);

                userManager.setActiveUser(null);
                ClientPanel.getInstance().changeView(ClientConfig.LOGIN_VIEW_CARD);
                clearUpdates();
            }
            
            catch(IOException e)
            {
                JOptionPane.showMessageDialog(null, "Failed to send logout request", "Logout request message", JOptionPane.ERROR_MESSAGE);
            }
            
        });
        
        thread.start();
    }
    
    public void disconnectUser()
    {
        clearUpdates();
        UserManager.getInstance().setActiveUser(null);
        JOptionPane.showMessageDialog(null, "You have disconnected from the server", "Connection failed", JOptionPane.ERROR_MESSAGE);
        ClientPanel.getInstance().changeView(ClientConfig.CONNECT_VIEW_CARD);
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
            AuthMsgBean bean        =   new AuthMsgBean("testaccount1", "mypass", SocketManager.getInstance().getUdpPort());
            RequestMessage request  =   new RequestMessage(null, bean);
            request.setDescription("HELLO FROM CLIENT");
            manager.sendRequest(request);
            
        }
        
        catch(IOException e)
        {
            System.out.println("[ClientManager@main]: " + e.getMessage());
        }
    }
}
