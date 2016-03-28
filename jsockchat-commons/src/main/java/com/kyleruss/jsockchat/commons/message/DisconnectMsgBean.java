
package com.kyleruss.jsockchat.commons.message;

public class DisconnectMsgBean implements MessageBean
{
    private String room;
    private boolean isClientDisconnect;

    public DisconnectMsgBean(String source, String room, boolean isClientDisconnect)
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
