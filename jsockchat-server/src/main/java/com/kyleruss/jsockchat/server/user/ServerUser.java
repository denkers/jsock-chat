
package com.kyleruss.jsockchat.server.user;

import com.kyleruss.jsockchat.commons.user.User;
import java.net.Socket;
import com.kyleruss.jsockchat.commons.user.IUser;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ServerUser extends User
{
    private transient Socket socket;
    private transient ObjectOutputStream outputStream;
    
    public ServerUser(IUser user, Socket socket)
    {
        this(user.getUsername(), user.getDisplayName(), socket);
    }
    
    public ServerUser(String username, String displayname, Socket socket)
    {
        super(username, displayname);
        this.socket =   socket;
    }
    
    public ObjectOutputStream getSocketOuputStream() throws IOException
    {
        if(socket != null && outputStream == null) 
            outputStream = new ObjectOutputStream(socket.getOutputStream());
        
        return outputStream;
    }
    
    public Socket getSocket()
    {
        return socket;
    }
    
    public void setSocket(Socket socket)
    {
        this.socket =   socket;
    }
}
