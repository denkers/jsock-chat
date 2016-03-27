
package com.kyleruss.jsockchat.client.io;

import com.kyleruss.jsockchat.commons.message.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class MessageListener extends Thread
{
    private final Socket socket;
    private static MessageListener instance;
    private ObjectInputStream inputStream;
    
    private MessageListener(Socket socket)
    {
        this.socket =   socket;
        initInputStream();
    }
    
    private void initInputStream()
    {
        try { inputStream   =   new ObjectInputStream(socket.getInputStream()); }
        catch(IOException e)
        {
            System.out.println("[MessageListener@initInputStream]: " + e.getMessage());
        }
    }
    
    private Message getMessage()
    {
        try
        {
            Message msgObj =   (Message) inputStream.readObject();
            return msgObj;
        }
        
        catch(IOException | ClassNotFoundException | ClassCastException e)
        {
            System.out.println("[MessageListener@getMessage]: " + e.getMessage());
            return null;
        }
    }
    
    @Override
    public void run()
    {
        Message message;
        while(socket != null && !socket.isClosed())
        {
            if((message = getMessage()) != null)
                
        }   
    }
    
    public static MessageListener getInstance(Socket socket)
    {
        if(instance == null) instance = new MessageListener(socket);
        return instance;
    }
}
