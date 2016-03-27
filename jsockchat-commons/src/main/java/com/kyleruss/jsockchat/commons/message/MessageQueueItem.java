
package com.kyleruss.jsockchat.commons.message;

import java.io.ObjectOutputStream;

public class MessageQueueItem
{
    private final ObjectOutputStream destOutputStream;
    private Message message;
    
    public MessageQueueItem(ObjectOutputStream destination, Message message)
    {
        this.destOutputStream       =   destination;
        this.message                =   message;    
    }
    
    public ObjectOutputStream getDestinationOutputStream()
    {
        return destOutputStream;
    }
    
    public Message getMessage()
    {
        return message;
    }
    
    public void setMessage(Message message)
    {
        this.message    =   message;
    }
}
