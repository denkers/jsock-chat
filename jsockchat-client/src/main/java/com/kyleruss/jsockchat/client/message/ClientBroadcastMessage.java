package com.kyleruss.jsockchat.client.message;

import com.kyleruss.jsockchat.commons.message.BroadcastMessage;
import com.kyleruss.jsockchat.commons.message.ResponseMessage;

public class ClientBroadcastMessage extends ClientMessage implements BroadcastMessage
{

    public ClientBroadcastMessage(String source) {
        super(source);
    }

    @Override
    public void clientAction(ResponseMessage response) {
    }

    @Override
    public void witnessAction(ResponseMessage response) {
    }

    @Override
    public String getRoom() {
    }

    @Override
    public String getContent() {
    }
    
}
