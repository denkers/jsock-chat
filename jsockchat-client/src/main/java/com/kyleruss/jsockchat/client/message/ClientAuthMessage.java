
package com.kyleruss.jsockchat.client.message;

import com.kyleruss.jsockchat.commons.message.ResponseMessage;

public class ClientAuthMessage extends ClientMessage
{

    public ClientAuthMessage(String source)
    {
        super(source);
    }
    
    @Override
    public void clientAction(ResponseMessage response) {
    }

    @Override
    public void witnessAction(ResponseMessage response) {
    }
    
}
