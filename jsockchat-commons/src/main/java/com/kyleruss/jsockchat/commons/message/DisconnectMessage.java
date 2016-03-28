
package com.kyleruss.jsockchat.commons.message;

public interface DisconnectMessage extends RequestMessage
{
    public String getRoom();
    
    public boolean isClientDisconnect();
}
