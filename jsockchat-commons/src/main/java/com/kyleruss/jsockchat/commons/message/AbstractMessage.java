
package com.kyleruss.jsockchat.commons.message;

import java.util.Date;

public abstract class AbstractMessage implements Message
{
    private MessageType messageType;
    private Date timeSent;
    
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
    
    public Date getTimeSent()
    {
        return timeSent;
    }
}
