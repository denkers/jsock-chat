
package com.kyleruss.jsockchat.commons.message;

public interface JoinRoomMessage extends Message
{
    public String getRoom();
    
    public String getAttemptedPassword();
}
