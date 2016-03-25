
package com.kyleruss.jsockchat.server.user;

import com.kyleruss.jsockchat.commons.user.AbstractUser;
import java.net.Socket;

public class ServerUser extends AbstractUser
{
    private transient Socket socket;
    
    public Socket getSocket()
    {
        return socket;
    }
    
    public void setSocket(Socket socket)
    {
        this.socket =   socket;
    }
}
