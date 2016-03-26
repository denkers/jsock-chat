
package com.kyleruss.jsockchat.server.message;

import com.kyleruss.jsockchat.commons.message.Message;
import java.io.ObjectOutputStream;

public class MessageQueueItem
{
    private ObjectOutputStream outputStream;
    private Message message;
    
    public MessageQueueItem(ObjectOutputStream outputStream, Message message)
    {
        this.outputStream     =   outputStream;
        this.message          =   message;    
    }
    
    public ObjectOutputStream getOutputStream()
    {
        return outputStream;
    }
    
    public Message getMessage()
    {
        return message;
    }
    
    public void setOutputStream(ObjectOutputStream outputStream)
    {
        this.outputStream =   outputStream;
    }
    
    public void setMessage(Message message)
    {
        this.message    =   message;
    }
}
