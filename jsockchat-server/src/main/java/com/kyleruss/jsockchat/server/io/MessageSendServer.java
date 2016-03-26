
package com.kyleruss.jsockchat.server.io;

import com.kyleruss.jsockchat.commons.message.Message;
import java.util.LinkedList;
import java.util.Queue;

public class MessageSendServer extends SyncedServer
{
    private final Queue<Message> messageQueue;
    
    public MessageSendServer()
    {
        messageQueue    =   new LinkedList<>();
    }

    @Override
    protected void runServerOperations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
