package com.kyleruss.jsockchat.server.message;

import com.kyleruss.jsockchat.commons.message.PrivateMsgBean;
import com.kyleruss.jsockchat.commons.message.RequestMessage;
import com.kyleruss.jsockchat.commons.message.ResponseMessage;
import com.kyleruss.jsockchat.commons.user.IUser;
import com.kyleruss.jsockchat.server.core.UserManager;
import java.io.IOException;


public class PrivateMessageHandler implements ServerMessageHandler
{
    @Override
    public void serverAction(RequestMessage request) 
    {
        PrivateMsgBean bean         =   (PrivateMsgBean) request.getMessageBean();
        UserManager userManager     =   UserManager.getInstance();
        IUser destinationUser       =   userManager.get(bean.getDestinationUser());
        String source               =   request.getUserSource();
        ResponseMessage response    =   new ResponseMessage(request);
        String destination;
        
        if(destinationUser == null)
        {
            response.setStatus(false);
            response.setDescription("User not found");
            destination =   source;
        }
        
        else
        {
            response.setStatus(true);
            destination =   bean.getDestinationUser();
        }
        
        try { userManager.sendMessageToUser(destination, response); }
        catch(IOException e)
        {
            System.out.println("[PrivateMessageHandler@serverAction]: " + e.getMessage());
        }
    }
}
