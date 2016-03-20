
package com.kyleruss.jsockchat.server.message;

import com.kyleruss.jsockchat.commons.message.DisconnectMessage;

public class ServerDisconnectMessage extends ServerMessage<DisconnectMessage>
{
    public ServerDisconnectMessage(DisconnectMessage message)
    {
        super(message);
    }

    @Override
    public void action() 
    {
        
    }
}
