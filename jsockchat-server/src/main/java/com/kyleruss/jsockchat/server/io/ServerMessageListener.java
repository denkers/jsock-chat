
package com.kyleruss.jsockchat.server.io;

import com.kyleruss.jsockchat.commons.io.MessageListener;
import com.kyleruss.jsockchat.commons.message.RequestMessage;
import com.kyleruss.jsockchat.server.message.ServerMessage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerMessageListener extends MessageListener<RequestMessage>
{
    private static ServerMessageListener instance;
    
    public ServerMessageListener(Socket socket) {
        super(socket);
    }

    @Override
    protected void handleReceivedMessage(RequestMessage request) 
    {
        if(request != null)
        {
            ServerMessage response;
            System.out.println("[ServerMessageListener] handleReceivedMessage");
            System.out.println("Request description: " + request.getDescription());
        }
    }

    @Override
    protected RequestMessage getMessage(ObjectInputStream inputStream)
    {
        try
        {
            System.out.println("[ServerMessageListener] GetMessage");
            RequestMessage msgObj   =   (RequestMessage) inputStream.readObject();
            return msgObj;
        }
        
        catch(IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
            System.out.println("[ServerMessageListener@getMessage]: " + e.getMessage());
            return null;
        }
    }
    
    public static ServerMessageListener getInstance(Socket socket)
    {
        if(instance == null) instance   =   new ServerMessageListener(socket);
        return instance;
    }
}
