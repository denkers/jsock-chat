
package com.kyleruss.jsockchat.server.message;

import com.kyleruss.jsockchat.commons.message.Message;
import com.kyleruss.jsockchat.server.user.ServerUser;

public class MessageQueueItem
{
    private final ServerUser destination;
    private Message message;
    
    public MessageQueueItem(ServerUser destination, Message message)
    {
        this.destination        =   destination;
        this.message            =   message;    
    }
    
    public ServerUser getDestination()
    {
        return destination;
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
