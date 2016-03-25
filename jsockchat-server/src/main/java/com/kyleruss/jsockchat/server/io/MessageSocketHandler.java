
package com.kyleruss.jsockchat.server.io;

import com.kyleruss.jsockchat.commons.message.DisconnectMessage;
import com.kyleruss.jsockchat.server.message.ServerDisconnectMessage;
import com.kyleruss.jsockchat.server.message.ServerMessage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class MessageSocketHandler extends Thread
{
    private final Socket socket;
    
    public MessageSocketHandler(Socket socket)
    {
        this.socket =   socket;
    }
    
    public ServerMessage getServerMessage(ObjectInputStream inputStream)
    {
        try
        {
            Object clientMessage  =   inputStream.readObject();
            
            if(clientMessage instanceof DisconnectMessage)
                return new ServerDisconnectMessage((DisconnectMessage) clientMessage);
            
            else 
            {
                System.out.println("Message isnt found");
                return null;
            }
        }
        
        catch(IOException | ClassNotFoundException | ClassCastException e)
        {
            e.printStackTrace();
            System.out.println("[MessageSocketHandler@getSvMessage: " + e.getMessage());
            return null;
        }
    }
    
    @Override
    public void run()
    {
        if(socket != null && !socket.isClosed())
        {
            try(ObjectInputStream inputStream   =   new ObjectInputStream(socket.getInputStream()))
            {
                ServerMessage message;
                while((message = getServerMessage(inputStream)) != null)
                {
                    message.action();
                }
            }
            
            catch(IOException e)
            {
                System.out.println("[MessageSocketHandler@run: " + e.getMessage());
            }
        }
    }
}
