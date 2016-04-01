
package com.kyleruss.jsockchat.client.message;

import com.kyleruss.jsockchat.client.core.ClientManager;
import com.kyleruss.jsockchat.commons.message.CreateRoomMsgBean;
import com.kyleruss.jsockchat.commons.message.RequestMessage;
import com.kyleruss.jsockchat.commons.message.ResponseMessage;
import com.kyleruss.jsockchat.commons.user.AuthPackage;
import com.kyleruss.jsockchat.commons.user.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthMessageHandler implements ClientMessageHandler
{
    @Override
    public void clientAction(ResponseMessage response) 
    {
        System.out.println("Received message: " + response.getDescription());
        AuthPackage authPackage =   (AuthPackage) response.getResponseData();
        User authUser = authPackage.getAuthenticatedUser();
        System.out.println("auth username: " + authUser.getUsername() + " auth displayname: " + authUser.getDisplayName());
        
        if(response.getStatus())
        {
            try {
                CreateRoomMsgBean bean2   =   new CreateRoomMsgBean("noroom", "qweqwe", false);
                RequestMessage request2 =   new RequestMessage("testaccount1", bean2);
                ClientManager.getInstance().sendRequest(request2);
            } catch (IOException ex) {
                Logger.getLogger(AuthMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void witnessAction(ResponseMessage response) 
    {
    }

    
}
