
package com.kyleruss.jsockchat.server.io;

import com.kyleruss.jsockchat.commons.io.MessageSender;
import java.net.Socket;

public class ServerMessageSender extends MessageSender
{
    private static ServerMessageSender instance;
    
    public ServerMessageSender(Socket socket) 
    {
        super(socket);
    }
    
    public ServerMessageSender getInstance(Socket socket)
    {
        if(instance == null) instance   =   new ServerMessageSender(socket);
        return instance;
    }
}
