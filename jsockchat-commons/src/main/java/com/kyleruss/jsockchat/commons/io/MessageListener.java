package com.kyleruss.jsockchat.commons.io;

import com.kyleruss.jsockchat.commons.message.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public abstract class MessageListener extends Thread
{
    protected final Socket socket;
    
    public MessageListener(Socket socket)
    {
        this.socket =   socket;
    }
    
    protected abstract void handleReceivedMessage(Message message);
    
    protected abstract Message getMessage(ObjectInputStream inputStream);
    
    public Socket getSocket()
    {
        return socket;
    }
    
    @Override
    public void run()
    {
        if(socket != null) return;
            
        try(ObjectInputStream inputStream   =   new ObjectInputStream(socket.getInputStream()))
        {
            while(!socket.isClosed())
            {
                Message message =   getMessage(inputStream);
                handleReceivedMessage(message);
            }
        }
        
        catch(IOException e)
        {
            System.out.println("[MessageListener@run]: " + e.getMessage());
        }
    }
}
