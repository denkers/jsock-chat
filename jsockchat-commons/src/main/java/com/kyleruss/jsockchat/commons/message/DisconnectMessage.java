
package com.kyleruss.jsockchat.commons.message;

enum DisconnectContext
{
    ROOM,
    APPLICATION
}

public abstract class DisconnectMessage extends AbstractMessage
{
    private DisconnectContext context;
    private String room;
    
    public DisconnectContext getContext()
    {
        return context;
    }
    
    public String getRoom()
    {
        if(context == DisconnectContext.APPLICATION)
            return null;
        
        else return room;
    }
}
