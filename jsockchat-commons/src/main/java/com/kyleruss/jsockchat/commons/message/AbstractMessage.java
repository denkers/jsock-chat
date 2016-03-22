
package com.kyleruss.jsockchat.commons.message;

public abstract class AbstractMessage implements Message
{
    private MessageType messageType;
    
    @Override
    public abstract void action();
    
    public MessageType getMessageType()
    {
        return messageType;
    }
    
    public void setMessageType(MessageType messageType)
    {
        this.messageType    =   messageType;
    }
}
