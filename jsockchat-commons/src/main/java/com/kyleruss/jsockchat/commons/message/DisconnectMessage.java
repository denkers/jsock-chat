
package com.kyleruss.jsockchat.commons.message;

public interface DisconnectMessage extends RequestMessage
{
    public String getRoom();
    
    public void setRoom(String room);
    
    public boolean isClientDisconnect();
}
