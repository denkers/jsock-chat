
package com.kyleruss.jsockchat.server.io;

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
    
    public ServerMessage createServerMessage(ObjectInputStream inputStream)
    {
        try
        {
            ServerMessage serverMessage;
            Object clientMessage  =   inputStream.readObject();
            
            if(clientMessage instanceof )
        }
        
        catch(IOException | ClassNotFoundException | ClassCastException e)
        {
            System.out.println("[MessageSocketHandler@getSvMessage: " + e.getMessage());
            return null;
        }
    }
    
    @Override
    public void run()
    {
        
    }
}
