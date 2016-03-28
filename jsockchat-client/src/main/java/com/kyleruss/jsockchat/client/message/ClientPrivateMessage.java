package com.kyleruss.jsockchat.client.message;

import com.kyleruss.jsockchat.commons.message.PrivateMessage;
import com.kyleruss.jsockchat.commons.message.ResponseMessage;

public class ClientPrivateMessage extends ClientMessage implements PrivateMessage
{

    public ClientPrivateMessage(String source) {
        super(source);
    }

    @Override
    public void clientAction(ResponseMessage response) {
    }

    @Override
    public void witnessAction(ResponseMessage response) {
    }

    @Override
    public String getDestinationUser() {
    }

    @Override
    public String getRoom() {
    }

    @Override
    public String getContent() {
    }
    
}
