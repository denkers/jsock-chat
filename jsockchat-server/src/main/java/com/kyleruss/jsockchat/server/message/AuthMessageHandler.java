package com.kyleruss.jsockchat.server.message;

import com.kyleruss.jsockchat.commons.message.AuthMsgBean;
import com.kyleruss.jsockchat.commons.message.MessageQueueItem;
import com.kyleruss.jsockchat.commons.message.RequestMessage;
import com.kyleruss.jsockchat.commons.message.ResponseMessage;
import com.kyleruss.jsockchat.commons.user.User;
import com.kyleruss.jsockchat.server.core.SocketManager;
import com.kyleruss.jsockchat.server.core.UserManager;
import com.kyleruss.jsockchat.server.db.DBUsers;
import com.kyleruss.jsockchat.server.io.ServerMessageSender;
import com.kyleruss.jsockchat.server.io.UserSocket;
import java.io.IOException;
import java.net.Socket;

public class AuthMessageHandler implements ServerMessageHandler
{
    private final Socket activeSocket;
    
    public AuthMessageHandler(Socket activeSocket)
    {
        this.activeSocket   =   activeSocket;
    }
    
    @Override
    public void serverAction(RequestMessage request) 
    {
        AuthMsgBean bean            =   (AuthMsgBean) request.getMessageBean();
        User authUser               =   DBUsers.getInstance().fetchVerifiedUser(bean.getUsername(), bean.getPassword());
        ResponseMessage response    =   new ResponseMessage(request);
        String clientIP             =   activeSocket.getRemoteSocketAddress().toString();
        SocketManager socketManager =   SocketManager.getInstance();
        UserSocket userSocket       =   socketManager.get(clientIP);
        
        
        if(authUser != null)
        {
            System.out.println("User is auth");
            socketManager.remove(clientIP);
            socketManager.add(authUser.getUsername(), userSocket);
            UserManager userManager     =   UserManager.getInstance();
            userManager.add(authUser.getUsername(), authUser);
            
            response.setStatus(true);
            response.setDescription("Client successfully authenticated");
            response.setResponseData(response);
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
