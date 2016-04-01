
package com.kyleruss.jsockchat.server.io;

import com.kyleruss.jsockchat.commons.io.MessageSender;
import com.kyleruss.jsockchat.commons.user.IUser;
import com.kyleruss.jsockchat.server.core.ServerConfig;
import com.kyleruss.jsockchat.server.core.UserManager;
import com.kyleruss.jsockchat.server.gui.AppResources;
import com.kyleruss.jsockchat.server.gui.LogMessage;
import com.kyleruss.jsockchat.server.gui.LoggingList;
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
        if(isSending == sending) return;
        
        isSending  =   sending;
        ServerStatusPanel.getInstance().setServerStatus(sending, ServerConfig.MESSAGE_SEND_SERVER_CODE);
        LoggingList.sendLogMessage(new LogMessage("[Message Send Server] Server has " + (sending? "resumed" : "paused"), 
            AppResources.getInstance().getServerOkImage()));
        notify();
    }
    
    @Override
    protected void cleanUp(String source)
    {
        IUser user  =   UserManager.getInstance().get(source);
        UserManager.getInstance().clientExit(user);
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
