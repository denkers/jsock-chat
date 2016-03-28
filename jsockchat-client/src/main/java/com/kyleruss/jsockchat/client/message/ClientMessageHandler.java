package com.kyleruss.jsockchat.client.message;

import com.kyleruss.jsockchat.commons.message.ResponseMessage;

public interface ClientMessageHandler
{
    public void clientAction(ResponseMessage response);
    
    public void witnessAction(ResponseMessage response);
}