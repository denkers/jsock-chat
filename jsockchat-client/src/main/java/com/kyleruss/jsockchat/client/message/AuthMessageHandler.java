
package com.kyleruss.jsockchat.client.message;

import com.kyleruss.jsockchat.commons.message.ResponseMessage;

public class AuthMessageHandler implements ClientMessageHandler
{
    @Override
    public void clientAction(ResponseMessage response) 
    {
        System.out.println("Received message: " + response.getDescription());
    }

    @Override
    public void witnessAction(ResponseMessage response) 
    {
    }

    
}
