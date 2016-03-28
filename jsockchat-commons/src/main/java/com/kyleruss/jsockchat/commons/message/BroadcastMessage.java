
package com.kyleruss.jsockchat.commons.message;

public interface BroadcastMessage extends RequestMessage
{
    public String getRoom();
    
    public String getContent();
}
