
package com.kyleruss.jsockchat.client.io;

import com.kyleruss.jsockchat.client.core.SocketManager;
import com.kyleruss.jsockchat.commons.io.MessageSender;

public class ClientMessageSender extends MessageSender
{
    private static ClientMessageSender instance;
    
    @Override
    protected void cleanUp(String source)
    {
        SocketManager.getInstance().cleanUp();
    }
    
    @Override
    protected boolean isStopped() 
    {
        return false;
    }
    
    public static ClientMessageSender getInstance()
    {
        if(instance == null) instance   =   new ClientMessageSender();
        return instance;
    }
}
