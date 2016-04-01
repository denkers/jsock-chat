package com.kyleruss.jsockchat.commons.io;

import com.kyleruss.jsockchat.commons.message.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public abstract class MessageListener<T extends Message> extends Thread
{
    protected final Socket socket;
    
    public MessageListener(Socket socket)
    {
        this.socket =   socket;
    }
    
    protected abstract void handleReceivedMessage(T message);
    
    protected abstract T getMessage(ObjectInputStream inputStream);
    
    protected abstract void handleCleanup(ObjectInputStream inputStream);
    
    public Socket getSocket()
    {
        return socket;
    }
    
    @Override
    public void run()
    {
        if(socket == null) return;
            
        try(ObjectInputStream inputStream   =   new ObjectInputStream(socket.getInputStream()))
        {
            T message;
            while((message = getMessage(inputStream)) != null)
            {
                MessageHandler handler  =   new MessageHandler(message);
                handler.start();
            }
            
            handleCleanup(inputStream);
        }
        
        catch(IOException e)
        {
            System.out.println("[MessageListener@run]: " + e.getMessage());
            handleCleanup(null);
        }
    }
    
    protected class MessageHandler extends Thread
    {
        private final T message;
        
        public MessageHandler(T message)
        {
            this.message    =   message;
        }
        
        @Override
        public void run()
        {
            handleReceivedMessage(message);
        }
    }
}
