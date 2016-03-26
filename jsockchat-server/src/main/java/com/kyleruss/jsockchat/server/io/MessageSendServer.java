
package com.kyleruss.jsockchat.server.io;

import com.kyleruss.jsockchat.commons.message.Message;
import com.kyleruss.jsockchat.server.message.MessageQueueItem;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class MessageSendServer extends SyncedServer
{
    private final Queue<MessageQueueItem> messageQueue;
    
    public MessageSendServer()
    {
        messageQueue    =   new LinkedList<>();
    }
    
    public synchronized void addToQueue(MessageQueueItem item)
    {
        messageQueue.add(item);
        notify();
    }
    
    public synchronized boolean hasMessages()
    {
        return !messageQueue.isEmpty();
    }
    
    private synchronized void sendMessage(MessageQueueItem message)
    {
        ObjectOutputStream outputStream =   message.getOutputStream();
            
        if(outputStream != null)
        {
            try
            {
                Message nextMessage =   message.getMessage();
                outputStream.writeObject(nextMessage);
            }

            catch(IOException e)
            {
                System.out.println("[MessageSendServer@sendMessage]: " + e.getMessage());
            }
        }
    }
    
    @Override
    protected synchronized void runServerOperations() 
    {
        try
        {
            if(!hasMessages())
                wait();
            
            MessageQueueItem next  =   messageQueue.poll();
            sendMessage(next);
        }
        
        catch(InterruptedException e)
        {
            System.out.println("[MessageSendServer@runServerOperations]: " + e.getMessage());
        }
    }
}
