
package com.kyleruss.jsockchat.server.message;

import com.kyleruss.jsockchat.commons.message.Message;

public interface ServerMessage 
{
    public Message getWrappedMessage();
    
    public void setWrappedMessage(Message message);
}
