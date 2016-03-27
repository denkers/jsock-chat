
package com.kyleruss.jsockchat.server.io;

import com.kyleruss.jsockchat.commons.io.MessageListener;
import com.kyleruss.jsockchat.commons.message.Message;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerMessageListener extends MessageListener
{
    private static ServerMessageListener instance;
    
    public ServerMessageListener(Socket socket) {
        super(socket);
    }

    @Override
    protected void handleReceivedMessage(Message message) {}

    @Override
    protected Message getMessage(ObjectInputStream inputStream) {}
    
    public static ServerMessageListener getInstance(Socket socket)
    {
        if(instance == null) instance   =   new ServerMessageListener(socket);
        return instance;
    }
}
