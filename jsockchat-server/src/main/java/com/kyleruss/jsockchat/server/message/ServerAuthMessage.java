package com.kyleruss.jsockchat.server.message;

import com.kyleruss.jsockchat.commons.message.AuthMessage;

public class ServerAuthMessage extends ServerMessage<AuthMessage>
{

    public ServerAuthMessage(AuthMessage message) 
    {
        super(message);
    }

    @Override
    public void serverAction() {}
    
}
