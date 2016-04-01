
package com.kyleruss.jsockchat.server.core;

import com.kyleruss.jsockchat.commons.updatebean.FriendsUpdateBean;
import com.kyleruss.jsockchat.commons.updatebean.RoomsUpdateBean;
import com.kyleruss.jsockchat.commons.updatebean.UpdateBeanDump;
import com.kyleruss.jsockchat.commons.updatebean.UsersUpdateBean;
import com.kyleruss.jsockchat.commons.user.IUser;
import com.kyleruss.jsockchat.server.gui.AppResources;
import com.kyleruss.jsockchat.server.gui.LogMessage;
import com.kyleruss.jsockchat.server.gui.LoggingList;
import com.kyleruss.jsockchat.server.io.MessageServer;
import com.kyleruss.jsockchat.server.io.ServerMessageSender;
import com.kyleruss.jsockchat.server.io.UpdateBroadcastServer;

public class ServerManager 
{
    private static ServerManager instance;
    
    private ServerManager() {} 
    
    private void startServers()
    {
        LoggingList.sendLogMessage(new LogMessage("[Server manager] Starting Message server...", AppResources.getInstance().getServerOkImage()));
        MessageServer messageServer     =   MessageServer.getInstance();
        messageServer.start();
        
        LoggingList.sendLogMessage(new LogMessage("[Server manager] Starting Message broadcast server...", AppResources.getInstance().getServerOkImage()));
        ServerMessageSender sendServer  =   ServerMessageSender.getInstance();
        sendServer.start();
        
        LoggingList.sendLogMessage(new LogMessage("[Server manager] Starting Update broadcast server...", AppResources.getInstance().getServerOkImage()));
        UpdateBroadcastServer broadcastServer =   UpdateBroadcastServer.getInstance();
        broadcastServer.start();
    }
    
    public UpdateBeanDump prepareUpdates(IUser user)
    {
        RoomsUpdateBean roomsBean       =   RoomManager.getInstance().createRoomsBean();
        FriendsUpdateBean freindsBean   =   UserManager.getInstance().createFriendsBean(user.getUsername());
        UsersUpdateBean usersBean       =   UserManager.getInstance().createUsersBean();
        UpdateBeanDump beanDump         =   new UpdateBeanDump(freindsBean, roomsBean, usersBean);
        
        return beanDump;
    }
    
    public static ServerManager getInstance()
    {
        if(instance == null) instance = new ServerManager();
        return instance;
    }
    
    public static void main(String[] args)
    {
        GUIManager guiManager   =   GUIManager.getInstance();
        guiManager.display();
        ServerManager manager   =   getInstance();
        manager.startServers();
    }
}
