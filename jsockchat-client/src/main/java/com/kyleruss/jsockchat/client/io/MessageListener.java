
package com.kyleruss.jsockchat.client.io;

import java.net.Socket;

public class MessageListener extends Thread
{
    private Socket socket;
    private static MessageListener instance;
    
    private MessageListener(Socket socket)
    {
        this.socket =   socket;
    }
    
    
    @Override
    public void run()
    {
        
    }
    
    public static MessageListener getInstance(Socket socket)
    {
        if(instance == null) instance = new MessageListener(socket);
        return instance;
    }
}
