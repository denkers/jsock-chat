package com.kyleruss.jsockchat.server.message;

import com.kyleruss.jsockchat.commons.message.RegisterMessage;

public class ServerRegisterMessage extends ServerMessage<RegisterMessage>
{
    public ServerRegisterMessage(RegisterMessage message) 
    {
        super(message);
    }

    @Override
    public void serverAction() {}
    
}
