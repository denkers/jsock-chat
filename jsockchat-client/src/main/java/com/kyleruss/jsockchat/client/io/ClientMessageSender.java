
package com.kyleruss.jsockchat.client.io;

import com.kyleruss.jsockchat.commons.io.MessageSender;
import java.net.Socket;

public class ClientMessageSender extends MessageSender
{
    private static ClientMessageSender instance;
    
    public ClientMessageSender(Socket socket) 
    {
        super(socket);
    }
    
    
    public static ClientMessageSender getInstance(Socket socket)
    {
        if(instance == null) instance   =   new ClientMessageSender(socket);
        return instance;
    }
}
