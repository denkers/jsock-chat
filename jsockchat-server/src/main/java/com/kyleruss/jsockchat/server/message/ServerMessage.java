
package com.kyleruss.jsockchat.server.message;

import com.kyleruss.jsockchat.commons.message.AbstractMessage;
import com.kyleruss.jsockchat.commons.message.Message;

public abstract class ServerMessage<T extends Message> extends AbstractMessage 
{
    private T message;
    
    public ServerMessage(T message)
    {
        this.message    =   message;
    }
    
    public T getWrappedMessage()
    {
        return message;
    }
    
    public void setWrappedMessage(T message)
    {
        this.message    =   message;
    }
}
