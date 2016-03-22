
package com.kyleruss.jsockchat.commons.message;

import java.io.Serializable;
import java.util.Date;

enum MessageType
{
    REQUEST,
    RESPONSE
}

public interface Message extends Serializable
{
    public void action();

    public Date getTimeSent();
    
    public MessageType getMessageType();
    
    public void setMessageType(MessageType messageType);
}
