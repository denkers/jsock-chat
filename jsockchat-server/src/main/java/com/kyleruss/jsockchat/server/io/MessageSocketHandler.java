
package com.kyleruss.jsockchat.server.io;

import java.net.Socket;

public class MessageSocketHandler extends Thread
{
    private final Socket socket;
    
    public MessageSocketHandler(Socket socket)
    {
        this.socket =   socket;
    }
    
    @Override
    public void run()
    {
        
    }
}
