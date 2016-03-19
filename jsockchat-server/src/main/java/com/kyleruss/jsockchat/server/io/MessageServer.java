
package com.kyleruss.jsockchat.server.io;

public final class MessageServer extends Thread
{
    private static final MessageServer INSTANCE =   new MessageServer();
    private boolean isListening;
    
    private MessageServer()
    {
        isListening =   true;
    }
    
    public synchronized void setListening(boolean isRunning)
    {
        this.isListening  =   isRunning;
        notify();
    }
    
    public boolean isListening()
    {
        return isListening;
    }
    
    private synchronized void getListeningLock()
    {
        try
        {
            if(!isListening)
                wait();
        }
        
        catch(InterruptedException e)
        {
            System.out.println("[MessageServer@getListeningLock]: " + e.getMessage());
        }
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            getListeningLock();
        }
    }
    
    public static MessageServer getInstance()
    {
        return INSTANCE;
    }
}
