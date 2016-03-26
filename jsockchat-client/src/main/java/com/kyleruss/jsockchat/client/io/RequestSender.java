
package com.kyleruss.jsockchat.client.io;

import java.net.Socket;

public class RequestSender extends Thread
{
    private static RequestSender instance;
    private final Socket socket;
    
    private RequestSender(Socket socket)
    {
        this.socket =   socket;
    }
    
    @Override
    public void run()
    {
        
    }
    
    public static RequestSender getInstance(Socket socket)
    {
        if(instance == null) instance   =   new RequestSender(socket);
        return instance;
    }
}
