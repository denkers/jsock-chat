
package com.kyleruss.jsockchat.server.io;

public abstract class SyncedServer extends Thread
{
    public abstract boolean isServing();
    
    public abstract boolean isStopped();
    
    public abstract void setServing(boolean serving);
    
    public abstract void runServerOperations();
    
    protected synchronized void setServingSync(boolean serving)
    {
        setServing(serving);
        notify();
    }
    
    protected synchronized void getServingLock() throws InterruptedException
    {
        if(!isServing())
            wait();
    }
    
    @Override
    public void run()
    {
        while(!isStopped())
        {
            try
            {   
                getServingLock();
                runServerOperations();
            }
            
            catch(InterruptedException e)
            {
                System.out.println("[SyncedServer@run]: " + e.getMessage());
            }
        }
    }
}
