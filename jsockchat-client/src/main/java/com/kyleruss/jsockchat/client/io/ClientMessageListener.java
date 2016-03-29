
package com.kyleruss.jsockchat.client.io;

import com.kyleruss.jsockchat.client.core.UserManager;
import com.kyleruss.jsockchat.client.message.AcceptFriendMessageHandler;
import com.kyleruss.jsockchat.client.message.AuthMessageHandler;
import com.kyleruss.jsockchat.client.message.BroadcastMessageHandler;
import com.kyleruss.jsockchat.client.message.ClientMessageHandler;
import com.kyleruss.jsockchat.client.message.DisconnectMessageHandler;
import com.kyleruss.jsockchat.client.message.JoinRoomMessageHandler;
import com.kyleruss.jsockchat.client.message.PrivateMessageHandler;
import com.kyleruss.jsockchat.client.message.RegisterMessageHandler;
import com.kyleruss.jsockchat.client.message.RequestFriendMessageHandler;
import com.kyleruss.jsockchat.commons.io.MessageListener;
import com.kyleruss.jsockchat.commons.message.AcceptFriendMsgBean;
import com.kyleruss.jsockchat.commons.message.AuthMsgBean;
import com.kyleruss.jsockchat.commons.message.BroadcastMsgBean;
import com.kyleruss.jsockchat.commons.message.DisconnectMsgBean;
import com.kyleruss.jsockchat.commons.message.JoinRoomMsgBean;
import com.kyleruss.jsockchat.commons.message.MessageBean;
import com.kyleruss.jsockchat.commons.message.PrivateMsgBean;
import com.kyleruss.jsockchat.commons.message.RegisterMsgBean;
import com.kyleruss.jsockchat.commons.message.RequestFriendMsgBean;
import com.kyleruss.jsockchat.commons.message.RequestMessage;
import com.kyleruss.jsockchat.commons.message.ResponseMessage;
import com.kyleruss.jsockchat.commons.user.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientMessageListener extends MessageListener<ResponseMessage>
{
    private static ClientMessageListener instance;
    
    public ClientMessageListener(Socket socket) 
    {
        super(socket);
    }

    private ClientMessageHandler getHandler(MessageBean bean)
    {
        ClientMessageHandler handler    =   null;   
        
        if(bean instanceof AuthMsgBean)
            handler     =   new AuthMessageHandler();
        
        else if(bean instanceof RegisterMsgBean)
            handler     =   new RegisterMessageHandler();
        
        else if(bean instanceof DisconnectMsgBean)
            handler     =   new DisconnectMessageHandler();
        
        else if(bean instanceof JoinRoomMsgBean)
            handler     =   new JoinRoomMessageHandler();
        
        else if(bean instanceof PrivateMsgBean)
            handler     =   new PrivateMessageHandler();
        
        else if(bean instanceof BroadcastMsgBean)
            handler     =   new BroadcastMessageHandler();
        
        else if(bean instanceof AcceptFriendMsgBean)
            handler     =   new AcceptFriendMessageHandler();
        
        else if(bean instanceof RequestFriendMsgBean)
            handler     =   new RequestFriendMessageHandler();
        
        return handler;
    }
    
    @Override
    protected void handleReceivedMessage(ResponseMessage response)
    {
        RequestMessage request          =   response.getRequestMessage();
        MessageBean bean                =   request.getMessageBean();
        User user                       =   UserManager.getInstance().getActiveUser();
        ClientMessageHandler handler    =   getHandler(bean);
        
        if(handler != null)
        {
            if(user == null || !request.isWitness(user.getUsername()))
                handler.clientAction(response);

            else
                handler.witnessAction(response); 
        }
    }
    
    @Override
    protected void handleCleanup(ObjectInputStream inputStream)
    {
        try
        {
            inputStream.close();
            socket.close();
        }
        
        catch(IOException e)
        {
            System.out.println("[ServerMessageListener@handleCleanup]: " + e.getMessage());
        }
    }

    @Override
    protected ResponseMessage getMessage(ObjectInputStream inputStream) 
    {
        try
        {
            ResponseMessage response    =   (ResponseMessage) inputStream.readObject();
            return response;
        }
        
        catch(IOException | ClassNotFoundException | ClassCastException e)
        {
            System.out.println("[ClientMessageListener@getMessage]: " + e.getMessage());
            return null;
        }
    }
    
    public static ClientMessageListener getInstance(Socket socket)
    {
        if(instance == null) instance = new ClientMessageListener(socket);
        return instance;
    }
    
}