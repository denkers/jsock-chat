package com.kyleruss.jsockchat.client.message;

import com.kyleruss.jsockchat.commons.message.AbstractMessage;
import com.kyleruss.jsockchat.commons.message.RequestMessage;
import com.kyleruss.jsockchat.commons.message.ResponseMessage;

public abstract class ClientMessage extends AbstractMessage implements RequestMessage
{
    private String source;
    
    public abstract void clientAction(ResponseMessage response);
    
    public abstract void witnessAction(ResponseMessage response);
    
    @Override
    public String getUserSource() 
    {
        return source;
    }

    @Override
    public boolean hasSource() 
    {
        return source != null;
    }
    
    @Override
    public boolean isWitness(String username)
    {
        return source == null || source.equals(username);
    }
}