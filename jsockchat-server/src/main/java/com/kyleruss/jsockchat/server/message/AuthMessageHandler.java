package com.kyleruss.jsockchat.server.message;

import com.kyleruss.jsockchat.commons.message.AuthMsgBean;
import com.kyleruss.jsockchat.commons.message.MessageQueueItem;
import com.kyleruss.jsockchat.commons.message.RequestMessage;
import com.kyleruss.jsockchat.commons.message.ResponseMessage;
import com.kyleruss.jsockchat.commons.user.AuthPackage;
import com.kyleruss.jsockchat.commons.user.User;
import com.kyleruss.jsockchat.server.core.SocketManager;
import com.kyleruss.jsockchat.server.core.UserManager;
import com.kyleruss.jsockchat.server.db.DBUsers;
import com.kyleruss.jsockchat.server.gui.AppResources;
import com.kyleruss.jsockchat.server.gui.LogMessage;
import com.kyleruss.jsockchat.server.gui.LoggingList;
import com.kyleruss.jsockchat.server.io.ServerMessageSender;
import com.kyleruss.jsockchat.server.io.UserSocket;
import java.io.IOException;

public class AuthMessageHandler implements ServerMessageHandler
{
    private final String servingUser;
    
    public AuthMessageHandler(String servingUser)
    {
        this.servingUser   =   servingUser;
    }
    
    @Override
    public void serverAction(RequestMessage request) 
    {
        AuthMsgBean bean            =   (AuthMsgBean) request.getMessageBean();
        User authUser               =   DBUsers.getInstance().fetchVerifiedUser(bean.getUsername(), bean.getPassword());
        ResponseMessage response    =   new ResponseMessage(request);
        
        SocketManager socketManager =   SocketManager.getInstance();
        UserSocket userSocket       =   socketManager.get(servingUser);
        System.out.println("Serving user: " + servingUser + " socket: " + userSocket);
        
        if(authUser != null)
        {
            socketManager.remove(servingUser);
            userSocket.setUdpPort(bean.getUdpPort());
            socketManager.add(authUser.getUsername(), userSocket);
            UserManager userManager     =   UserManager.getInstance();
            userManager.add(authUser.getUsername(), authUser);
            
            response.setStatus(true);
            response.setDescription("Client successfully authenticated");
            
            AuthPackage authPackage =   userManager.prepareAuthPackage(authUser);
            response.setResponseData(authPackage);
            
            LoggingList.sendLogMessage(new LogMessage("[Log in] User '" + authUser.getUsername() + "' has successfully logged in", 
            AppResources.getInstance().getAuthSuccessImage()));
        }
        
        else
        {
            response.setStatus(false);
            response.setDescription("Failed to authenticate client: Invalid username/password");
        }
        
        try
        {
            MessageQueueItem messageItem   =   new MessageQueueItem(userSocket.getSocketOutputStream(), response);
            ServerMessageSender.getInstance().addMessage(messageItem);
        }
        
        catch(IOException e)
        {
            System.out.println("[AuthMessageHandler@serverAction]: " + e.getMessage());
        }
    }
}
