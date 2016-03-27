package com.kyleruss.jsockchat.client.message;

import com.kyleruss.jsockchat.commons.message.AbstractMessage;
import com.kyleruss.jsockchat.commons.message.RequestMessage;

public abstract class ClientMessage extends AbstractMessage implements RequestMessage
{
    private String source;
    
    public abstract void clientAction();
    
    public abstract void witnessAction();
    
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