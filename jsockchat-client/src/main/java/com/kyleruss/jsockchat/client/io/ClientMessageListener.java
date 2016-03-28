
package com.kyleruss.jsockchat.client.io;

import com.kyleruss.jsockchat.client.core.UserManager;
import com.kyleruss.jsockchat.client.user.ClientUser;
import com.kyleruss.jsockchat.commons.io.MessageListener;
import com.kyleruss.jsockchat.commons.message.MessageBean;
import com.kyleruss.jsockchat.commons.message.ResponseMessage;
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

    @Override
    protected void handleReceivedMessage(ResponseMessage response)
    {
        MessageBean bean            =   response.getRequestMessage().getMessageBean();
        ClientUser user             =   UserManager.getInstance().getActiveUser();

        /*if(user == null || !msg.isWitness(user.getUsername()))
            msg.clientAction(response);

        else
            msg.witnessAction(response); */
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