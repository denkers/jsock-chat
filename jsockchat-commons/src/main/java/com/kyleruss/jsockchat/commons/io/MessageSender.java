
package com.kyleruss.jsockchat.commons.io;

import com.kyleruss.jsockchat.commons.message.Message;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public abstract class MessageSender extends Thread
{
    private final Socket socket;
    private final Queue<Message> messageQueue;
    
    public MessageSender(Socket socket)
    {
        this.socket     =   socket;
        messageQueue    =   new LinkedList<>();   
    }
    
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
    
    public Socket getSocket()
    {
        return socket;
    }
    
    public synchronized void addMessage(Message message)
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
        if(socket == null) return;
        
        try(ObjectOutputStream inputStream   =   new ObjectOutputStream(socket.getOutputStream()))
        {
            while(!socket.isClosed())
            {
                getLock();
                Message message  =   messageQueue.poll();
                sendMessage(message, inputStream);
            }
        }
        
        catch(IOException e)
        {
            System.out.println("[MessageSender@run]: " + e.getMessage());
        }
    }
}
