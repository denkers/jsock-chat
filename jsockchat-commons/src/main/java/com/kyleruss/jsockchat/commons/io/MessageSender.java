
package com.kyleruss.jsockchat.commons.io;

import com.kyleruss.jsockchat.commons.message.Message;
import com.kyleruss.jsockchat.commons.message.MessageQueueItem;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Queue;

public abstract class MessageSender extends Thread
{
    private final Queue<MessageQueueItem> messageQueue;
    
    public MessageSender()
    {
        messageQueue    =   new LinkedList<>();   
    }
    
    protected abstract boolean isStopped();
    
    protected synchronized void getLock()
    {
        try
        {
            if(messageQueue.isEmpty())
                wait();
        }

        catch(InterruptedException e)
        {
            System.out.println("[RequestSender@getLock]: " + e.getMessage());
        }
    }
    
    public synchronized void addMessage(MessageQueueItem message)
    {
        messageQueue.add(message);
        notify();
    }
    
    protected void sendMessage(Message message, ObjectOutputStream outputStream) 
    { 
        try { outputStream.writeObject(message); }
        catch(IOException e)
        {
            System.out.println("[MessageSender@sendMessage]: " + e.getMessage());
        }
    }
    
    
    @Override
    public void run()
    {
        while(!isStopped())
        {
            getLock();
            MessageQueueItem item   =   messageQueue.poll();
            Message message         =   item.getMessage();
            ObjectOutputStream oos  =   item.getDestinationOutputStream();
            sendMessage(message, oos);
        }
    }
}
