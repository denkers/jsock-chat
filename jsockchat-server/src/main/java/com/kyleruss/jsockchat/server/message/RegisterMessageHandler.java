package com.kyleruss.jsockchat.server.message;

import com.kyleruss.jsockchat.commons.message.MessageQueueItem;
import com.kyleruss.jsockchat.commons.message.RegisterMsgBean;
import com.kyleruss.jsockchat.commons.message.RequestMessage;
import com.kyleruss.jsockchat.commons.message.ResponseMessage;
import com.kyleruss.jsockchat.commons.user.User;
import com.kyleruss.jsockchat.server.core.SocketManager;
import com.kyleruss.jsockchat.server.db.DBUsers;
import com.kyleruss.jsockchat.server.io.ServerMessageSender;
import com.kyleruss.jsockchat.server.io.UserSocket;
import java.io.IOException;

public class RegisterMessageHandler implements ServerMessageHandler
{
    private final String servingUser;
    
    public RegisterMessageHandler(String servingUser)
    {
        this.servingUser =   servingUser;
    }
    
    @Override
    public void serverAction(RequestMessage request) 
    {
        ResponseMessage response        =   new ResponseMessage(request);
        RegisterMsgBean registerBean    =   (RegisterMsgBean) request.getMessageBean();
        String userame                  =   registerBean.getUsername();
        String password                 =   registerBean.getPassword();
        String displayname              =   registerBean.getDisplayName();
        
        DBUsers userModel               =   DBUsers.getInstance();
        User existingUser               =   userModel.getuser(userame);
        
        if(existingUser != null)
        {
            response.setStatus(false);
            response.setDescription("Failed to register: Username already exists");
        }
        
        else
        {
            boolean result      =   userModel.createUser(userame, password, displayname);
            String responseMsg  =   result? "Your account has been successfully created" : "Failed to create account";   
            response.setStatus(result);
            response.setDescription(responseMsg);
        }
        
        UserSocket userSocket   =   SocketManager.getInstance().get(servingUser);
        
        if(userSocket == null)
            System.out.println("User socket not found");
        else
        {
            try
            {
                MessageQueueItem messageItem   =   new MessageQueueItem(userSocket.getSocketOutputStream(), response);
                ServerMessageSender.getInstance().addMessage(messageItem);
            }
            
            catch(IOException e)
            {
                System.out.println("[RegisterMessageHandler@serverAction]: " + e.getMessage());
            }
        }
    }
}
