package com.kyleruss.jsockchat.client.message;

import com.kyleruss.jsockchat.commons.message.DisconnectMessage;
import com.kyleruss.jsockchat.commons.message.ResponseMessage;

public class ClientDisconnectMessage extends ClientMessage implements DisconnectMessage
{
    private String room;
    private boolean isClientDisconnect;

    public ClientDisconnectMessage(String source) 
    {
        super(source);
    }
    
    public ClientDisconnectMessage(String source, String room, boolean isClientDisconnect)
    {
        super(source);
        this.room               =   room;
        this.isClientDisconnect =   isClientDisconnect;
    }

    @Override
    public void clientAction(ResponseMessage response) {
    }

    @Override
    public void witnessAction(ResponseMessage response) {
    }

    @Override
    public String getRoom() 
    {
        return room;
    }

    @Override
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
