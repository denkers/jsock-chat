
package com.kyleruss.jsockchat.commons.message;

import java.io.Serializable;

enum MessageType
{
    REQUEST,
    RESPONSE
}

public interface Message extends Serializable
{
    public String getSource();
    
    public String getContent();
    
    public void action();
    
    public MessageType getMessageType();
    
    public void setMessageType(MessageType messageType);
}
