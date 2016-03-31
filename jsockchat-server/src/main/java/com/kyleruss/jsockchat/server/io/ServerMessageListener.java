
package com.kyleruss.jsockchat.server.io;

import com.kyleruss.jsockchat.server.message.AcceptFriendMessageHandler;
import com.kyleruss.jsockchat.commons.io.MessageListener;
import com.kyleruss.jsockchat.commons.message.AcceptFriendMsgBean;
import com.kyleruss.jsockchat.commons.message.AuthMsgBean;
import com.kyleruss.jsockchat.commons.message.BroadcastMsgBean;
import com.kyleruss.jsockchat.commons.message.CreateRoomMsgBean;
import com.kyleruss.jsockchat.commons.message.DisconnectMsgBean;
import com.kyleruss.jsockchat.commons.message.JoinRoomMsgBean;
import com.kyleruss.jsockchat.commons.message.MessageBean;
import com.kyleruss.jsockchat.commons.message.PrivateMsgBean;
import com.kyleruss.jsockchat.commons.message.RegisterMsgBean;
import com.kyleruss.jsockchat.commons.message.RequestFriendMsgBean;
import com.kyleruss.jsockchat.commons.message.RequestMessage;
import com.kyleruss.jsockchat.server.core.SocketManager;
import com.kyleruss.jsockchat.server.message.AuthMessageHandler;
import com.kyleruss.jsockchat.server.message.BroadcastMessageHandler;
import com.kyleruss.jsockchat.server.message.CreateRoomMessageHandler;
import com.kyleruss.jsockchat.server.message.DisconnectMessageHandler;
import com.kyleruss.jsockchat.server.message.JoinRoomMessageHandler;
import com.kyleruss.jsockchat.server.message.PrivateMessageHandler;
import com.kyleruss.jsockchat.server.message.RegisterMessageHandler;
import com.kyleruss.jsockchat.server.message.RequestFriendMessageHandler;
import com.kyleruss.jsockchat.server.message.ServerMessageHandler;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerMessageListener extends MessageListener<RequestMessage>
{
    private static ServerMessageListener instance;
    protected String servingUser;
    
    public ServerMessageListener(Socket socket) 
    {
        super(socket);
        initUserSocket();
    }
    
    private void initUserSocket()
    {
        if(socket != null)
        {
            String clientIP             =   socket.getRemoteSocketAddress().toString();
            System.out.println(clientIP);
            SocketManager sockManager   =   SocketManager.getInstance();
            
            if(!sockManager.find(clientIP))
            {
                UserSocket userSocket       =   new UserSocket(socket);
                sockManager.add(clientIP, userSocket);
                servingUser =   clientIP;
            }
            
        }
    }
    
    private ServerMessageHandler getHandler(MessageBean bean)
    {
        ServerMessageHandler handler    =   null;
            
        if(bean instanceof AuthMsgBean)
            handler     =   new AuthMessageHandler(servingUser);
        
        else if(bean instanceof RegisterMsgBean)
            handler     =   new RegisterMessageHandler(servingUser);
        
        else if(bean instanceof DisconnectMsgBean)
            handler     =   new DisconnectMessageHandler();
        
        else if(bean instanceof JoinRoomMsgBean)
            handler     =   new JoinRoomMessageHandler();
        
        else if(bean instanceof PrivateMsgBean)
            handler     =   new PrivateMessageHandler();
        
        else if(bean instanceof BroadcastMsgBean)
            handler     =   new BroadcastMessageHandler();
        
        else if(bean instanceof RequestFriendMsgBean)
            handler     =   new RequestFriendMessageHandler();
        
        else if(bean instanceof AcceptFriendMsgBean)
            handler     =   new AcceptFriendMessageHandler();
        
        else if(bean instanceof CreateRoomMsgBean)
            handler     =   new CreateRoomMessageHandler();
        
        return handler;
    }
    
    @Override
    protected void handleReceivedMessage(RequestMessage request) 
    {
        if(request != null)
        {
            if(request.getUserSource() != null)
                servingUser = request.getUserSource();
            
            System.out.println("Request description: " + request.getDescription());
            
            MessageBean bean                =   request.getMessageBean();
            ServerMessageHandler handler    =   getHandler(bean);
            
            
            if(handler != null) handler.serverAction(request);
        }
    }

    @Override
    protected RequestMessage getMessage(ObjectInputStream inputStream)
    {
        try
        {
            System.out.println("[ServerMessageListener] GetMessage");
            RequestMessage request   =   (RequestMessage) inputStream.readObject();
            return request;
        }
        
        catch(IOException | ClassNotFoundException e)
        {
            System.out.println("[ServerMessageListener@getMessage]: " + e.getMessage());
            return null;
        }
    }
    
    @Override
    protected void handleCleanup(ObjectInputStream inputStream)
    {
        try
        {
            inputStream.close();
            
            if(servingUser != null)
                SocketManager.getInstance().cleanUp(servingUser);
            
            else socket.close();
        }
        
        catch(IOException e)
        {
            System.out.println("[ServerMessageListener@handleCleanup]: " + e.getMessage());
        }
    }
    
    public static ServerMessageListener getInstance(Socket socket)
    {
        if(instance == null) instance   =   new ServerMessageListener(socket);
        return instance;
    }
}
