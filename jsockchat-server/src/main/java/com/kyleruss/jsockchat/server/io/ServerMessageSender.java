
package com.kyleruss.jsockchat.server.io;

import com.kyleruss.jsockchat.commons.io.MessageSender;
import com.kyleruss.jsockchat.server.core.ServerConfig;
import com.kyleruss.jsockchat.server.gui.ServerStatusPanel;

public class ServerMessageSender extends MessageSender
{
    private static ServerMessageSender instance;
    private boolean isSending;
    
    private ServerMessageSender()
    {
        isSending = true;
    }
    
    @Override
    public synchronized void getLock() 
    {
        super.getLock();
        
        if(!isSending)
        {
            try { wait(); }

            catch(InterruptedException e)
            {
                System.out.println("[ServerMessageSender@getLock]: " + e.getMessage());
            }
        }
    }
    
    public synchronized void setSending(boolean sending)
    {
        this.isSending  =   sending;
        ServerStatusPanel.getInstance().setServerStatus(sending, ServerConfig.MESSAGE_SEND_SERVER_CODE);
        notify();
    }
    
    @Override
    protected boolean isStopped() 
    {
        return false;
    }
    
    public static ServerMessageSender getInstance()
    {
        if(instance == null) instance   =   new ServerMessageSender();
        return instance;
    }
}
