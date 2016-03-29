
package com.kyleruss.jsockchat.server.io;

import com.kyleruss.jsockchat.commons.io.MessageSender;

public class ServerMessageSender extends MessageSender
{
    private static ServerMessageSender instance;
    private boolean isStopped;
    
    public synchronized void setStopped(boolean isStopped)
    {
        this.isStopped   =  isStopped;
        notify();
    }
    
    @Override
    protected boolean isStopped() 
    {
        return isStopped;
    }
    
    public static ServerMessageSender getInstance()
    {
        if(instance == null) instance   =   new ServerMessageSender();
        return instance;
    }
}
