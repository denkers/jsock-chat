package com.kyleruss.jsockchat.server.message;

import com.kyleruss.jsockchat.commons.message.AuthMsgBean;
import com.kyleruss.jsockchat.commons.message.RequestMessage;
import com.kyleruss.jsockchat.commons.user.User;
import com.kyleruss.jsockchat.server.core.SocketManager;
import com.kyleruss.jsockchat.server.core.UserManager;
import com.kyleruss.jsockchat.server.db.DBUsers;
import com.kyleruss.jsockchat.server.io.UserSocket;
import java.net.Socket;

public class AuthMessageHandler implements ServerMessageHandler
{
    private final Socket activeSocket;
    
    public AuthMessageHandler(Socket activeSocket)
    {
        this.activeSocket   =   activeSocket;
    }
    
    @Override
    public void serverAction(RequestMessage request) 
    {
        AuthMsgBean bean    =   (AuthMsgBean) request.getMessageBean();
        User authUser       =   DBUsers.getInstance().fetchVerifiedUser(bean.getUsername(), bean.getPassword());
        
        if(authUser != null)
        {
            String clientIP             =   activeSocket.getRemoteSocketAddress().toString();
            SocketManager socketManager =   SocketManager.getInstance();
            UserSocket userSocket       =   socketManager.remove(clientIP);
            userSocket                  =   userSocket == null? new UserSocket(activeSocket) : userSocket;
            socketManager.add(authUser.getUsername(), userSocket);
            
            UserManager userManager     =   UserManager.getInstance();
            userManager.add(authUser.getUsername(), authUser);
        }
    }
}
