
package com.kyleruss.jsockchat.client.io;

import com.kyleruss.jsockchat.client.message.ClientMessage;
import com.kyleruss.jsockchat.commons.io.MessageListener;
import com.kyleruss.jsockchat.commons.message.Message;
import com.kyleruss.jsockchat.commons.message.ResponseMessage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientMessageListener extends MessageListener
{
    private static ClientMessageListener instance;
    
    public ClientMessageListener(Socket socket) 
    {
        super(socket);
    }

    @Override
    protected void handleReceivedMessage(Message message) {}

    @Override
    protected Message getMessage(ObjectInputStream inputStream) 
    {
        try
        {
            ResponseMessage msgObj      =   (ResponseMessage) inputStream.readObject();
            ClientMessage msg           =   (ClientMessage) msgObj.getRequestMessage();
            String user
        }
        
        catch(IOException | ClassNotFoundException | ClassCastException e)
        {
            
        }
    }
    
    public static ClientMessageListener getInstance(Socket socket)
    {
        if(instance == null) instance = new ClientMessageListener(socket);
        return instance;
    }
    
}