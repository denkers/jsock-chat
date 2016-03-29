package com.kyleruss.jsockchat.server.message;

import com.kyleruss.jsockchat.commons.message.RequestFriendMsgBean;
import com.kyleruss.jsockchat.commons.message.RequestMessage;
import com.kyleruss.jsockchat.commons.message.ResponseMessage;
import com.kyleruss.jsockchat.server.core.UserManager;
import com.kyleruss.jsockchat.server.db.DBFriends;
import java.io.IOException;

public class RequestFriendMessageHandler implements ServerMessageHandler
{
    @Override
    public void serverAction(RequestMessage request) 
    {
        RequestFriendMsgBean bean   =   (RequestFriendMsgBean) request.getMessageBean();
        ResponseMessage response    =   new ResponseMessage(request);
        String source               =   request.getUserSource();
        
        if(DBFriends.getInstance().friendRequestExists(bean.getFriendA(), bean.getFriendB()))
        {
            response.setStatus(false);
            response.setDescription("There already exists a pending friend request");
        }
        
        else
        {
            boolean result      =   DBFriends.getInstance().addFriendRequest(bean);
            String responseMsg  =   result? "Successfully sent " + bean.getFriendB() + " a friend request" : "Failed to send friend request";
            response.setStatus(result);
            response.setDescription(responseMsg);
        }
        
        try { UserManager.getInstance().sendMessageToUser(source, response); }
        catch(IOException e)
        {
            System.out.println("[RequestFriendMessageHandler@serverAction]: " + e.getMessage());
        }
    }
}
