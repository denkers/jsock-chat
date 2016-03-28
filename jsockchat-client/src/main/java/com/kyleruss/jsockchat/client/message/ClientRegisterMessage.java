package com.kyleruss.jsockchat.client.message;

import com.kyleruss.jsockchat.commons.message.RegisterMessage;
import com.kyleruss.jsockchat.commons.message.ResponseMessage;

public class ClientRegisterMessage extends ClientMessage implements RegisterMessage
{

    public ClientRegisterMessage(String source) {
        super(source);
    }

    @Override
    public void clientAction(ResponseMessage response) {
    }

    @Override
    public void witnessAction(ResponseMessage response) {
    }

    @Override
    public String getUsername() {
    }

    @Override
    public String getPassword() {
    }

    @Override
    public String getDisplayName() {
    }
    
}
