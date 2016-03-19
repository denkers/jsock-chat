
package com.kyleruss.jsockchat.commons.message;

public abstract class AbstractMessage implements Message
{
    private String source;
    private String content;
    private MessageType messageType;
    
    @Override
    public abstract void action();
    
    @Override
    public String getSource()
    {
        return source;
    }
    
    @Override
    public String getContent()
    {
        return content;
    }
    
    public MessageType getMessageType()
    {
        return messageType;
    }
    
    public void setMessageType(MessageType messageType)
    {
        this.messageType    =   messageType;
    }
}
