
package com.kyleruss.jsockchat.commons.message;

public interface JoinRoomMessage extends RequestMessage
{
    public String getRoom();
    
    public String getAttemptedPassword();
}
