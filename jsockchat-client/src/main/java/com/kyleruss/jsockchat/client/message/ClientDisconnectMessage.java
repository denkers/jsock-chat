package com.kyleruss.jsockchat.client.message;

import com.kyleruss.jsockchat.commons.message.DisconnectMessage;
import com.kyleruss.jsockchat.commons.message.ResponseMessage;

public class ClientDisconnectMessage extends ClientMessage implements DisconnectMessage
{

    public ClientDisconnectMessage(String source) {
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
    public void setRoom(String room) {
    }

    @Override
    public boolean isClientDisconnect() {
    }
    
}
