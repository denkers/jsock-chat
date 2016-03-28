
package com.kyleruss.jsockchat.commons.message;

public interface DisconnectMessage extends Message
{
    public String getRoom();
    
    public void setRoom(String room);
    
    public boolean isClientDisconnect();
}
