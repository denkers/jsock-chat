package com.kyleruss.jsockchat.client.message;

import com.kyleruss.jsockchat.commons.message.ResponseMessage;

public class ClientJoinRoomMessage extends ClientMessage
{

    public ClientJoinRoomMessage(String source) {
        super(source);
    }

    @Override
    public void clientAction(ResponseMessage response) {
    }

    @Override
    public void witnessAction(ResponseMessage response) {
    }
    
}
