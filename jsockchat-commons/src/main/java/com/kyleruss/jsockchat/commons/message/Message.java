
package com.kyleruss.jsockchat.commons.message;

enum MessageType
{
    REQUEST,
    RESPONSE
}

public interface Message 
{
    public String getSource();
    
    public String getContent();
    
    public void action();
    
    public MessageType getMessageType();
    
    public void setMessageType(MessageType messageType);
}
