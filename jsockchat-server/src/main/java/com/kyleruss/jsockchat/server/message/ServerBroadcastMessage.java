package com.kyleruss.jsockchat.server.message;

import com.kyleruss.jsockchat.commons.message.BroadcastMessage;

public class ServerBroadcastMessage extends ServerMessage<BroadcastMessage>
{
    public ServerBroadcastMessage(BroadcastMessage message) 
    {
        super(message);
    }

    @Override
    public void serverAction() {}
}
