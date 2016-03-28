
package com.kyleruss.jsockchat.commons.message;

public class DisconnectMessage extends AbstractMessage
{
    private String room;
    private boolean isClientDisconnect;

    public DisconnectMessage(String source, String room, boolean isClientDisconnect)
    {
        this.room               =   room;
        this.isClientDisconnect =   isClientDisconnect;
    }


    public String getRoom() 
    {
        return room;
    }

    public boolean isClientDisconnect() 
    {
        return isClientDisconnect;
    }
    
    public void setRoom(String room) 
    {    
        this.room   =   room;
    }
    
    public void setIsClientDisconnect(boolean isClientDisconnect)
    {
        this.isClientDisconnect =   isClientDisconnect;
    }
}
