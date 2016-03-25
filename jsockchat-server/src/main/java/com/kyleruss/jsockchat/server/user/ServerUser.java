
package com.kyleruss.jsockchat.server.user;

import com.kyleruss.jsockchat.commons.user.AbstractUser;
import com.kyleruss.jsockchat.commons.user.User;
import java.net.Socket;

public class ServerUser extends AbstractUser
{
    private transient Socket socket;
    
    public ServerUser(User user, Socket socket)
    {
        this(user.getUsername(), user.getDisplayName(), socket);
    }
    
    public ServerUser(String username, String displayname, Socket socket)
    {
        super(username, displayname);
        this.socket =   socket;
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
