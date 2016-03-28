
package com.kyleruss.jsockchat.server.message;

import com.kyleruss.jsockchat.commons.message.JoinRoomMessage;

public class ServerJoinRoomMessage extends ServerMessage<JoinRoomMessage>
{
    public ServerJoinRoomMessage(JoinRoomMessage message) 
    {
        super(message);
    }

    @Override
    public void serverAction() {}
    
}
