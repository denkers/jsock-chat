
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
    }

    @Override
    public void witnessAction(ResponseMessage response) 
    {
    }

    
}
