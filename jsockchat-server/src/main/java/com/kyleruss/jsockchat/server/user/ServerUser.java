
package com.kyleruss.jsockchat.server.user;

import com.kyleruss.jsockchat.commons.user.User;
import java.net.Socket;
import com.kyleruss.jsockchat.commons.user.IUser;

public class ServerUser extends User
{
    private transient Socket socket;
    
    public ServerUser(IUser user, Socket socket)
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
