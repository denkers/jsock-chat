
package com.kyleruss.jsockchat.server.io;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UserSocket
{
    private Socket socket;
    private ObjectOutputStream sockOutputStream;
    
    public UserSocket(Socket socket)
    {
        this.socket =   socket;
    }
    
    public void cleanUp()
    {
        try
        {
            if(sockOutputStream != null)
            {
                sockOutputStream.flush();
                sockOutputStream.close();
            }
            
            if(socket != null && !socket.isClosed())
                socket.close();
        }
        
        catch(IOException e)
        {
            System.out.println("[UserSocket@cleanUp]: " + e.getMessage());
        }
    }
    
    public Socket getSocket()
    {
        return socket;
    }
    
    public void setSocket(Socket socket)
    {
        this.socket  =   socket;
    }
    
    public ObjectOutputStream getSocketOutputStream() throws IOException
    {
        if(socket == null) return null;
        
        if(sockOutputStream == null) sockOutputStream = new ObjectOutputStream(socket.getOutputStream());
        return sockOutputStream;
    }
}
