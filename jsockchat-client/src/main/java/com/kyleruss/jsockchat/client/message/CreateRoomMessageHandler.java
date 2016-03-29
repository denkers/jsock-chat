package com.kyleruss.jsockchat.client.message;

import com.kyleruss.jsockchat.commons.message.ResponseMessage;

public class CreateRoomMessageHandler implements ClientMessageHandler
{
    @Override
    public void clientAction(ResponseMessage response)
    {
        System.out.println(response.getDescription());
    }

    @Override
    public void witnessAction(ResponseMessage response) 
    {
    }
    
}
