package com.kyleruss.jsockchat.server.message;

import com.kyleruss.jsockchat.commons.message.PrivateMessage;

public class ServerPrivateMessage extends ServerMessage<PrivateMessage>
{
    public ServerPrivateMessage(PrivateMessage message) 
    {
        super(message);
    }

    @Override
    public void serverAction() 
    {
    }
}
