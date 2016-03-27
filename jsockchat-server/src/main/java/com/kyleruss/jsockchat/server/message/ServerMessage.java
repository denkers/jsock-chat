
package com.kyleruss.jsockchat.server.message;

import com.kyleruss.jsockchat.commons.message.AbstractMessage;
import com.kyleruss.jsockchat.commons.message.RequestMessage;
import com.kyleruss.jsockchat.commons.message.ResponseMessage;

public abstract class ServerMessage<T extends RequestMessage> extends AbstractMessage implements ResponseMessage
{
    private final T request;
    private String description;
    private Object data;
    
    public ServerMessage(T message)
    {
        this.request    =   message;
    }
    
    @Override
    public abstract void action();

    @Override
    public T getRequestMessage() 
    {
        return request;
    }

    @Override
    public String getResponseDescription() 
    {
        return description;
    }

    @Override
    public Object getResponseData() 
    {
        return data;
    }
}
