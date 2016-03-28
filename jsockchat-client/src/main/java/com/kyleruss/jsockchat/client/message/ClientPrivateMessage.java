package com.kyleruss.jsockchat.client.message;

import com.kyleruss.jsockchat.commons.message.PrivateMessage;
import com.kyleruss.jsockchat.commons.message.ResponseMessage;

public class ClientPrivateMessage extends ClientMessage implements PrivateMessage
{
    private String destinationUser;
    private String content;
    private String room;
    
    public ClientPrivateMessage(String source) 
    {
        super(source);
    }
    
    public ClientPrivateMessage(String source, String destinationUser, String content, String room)
    {
        super(source);
        this.destinationUser    =   destinationUser;
        this.content            =   content;
        this.room               =   room;
    }

    @Override
    public void clientAction(ResponseMessage response) {
    }

    @Override
    public void witnessAction(ResponseMessage response) {
    }

    @Override
    public String getDestinationUser() 
    {
        return destinationUser;
    }

    @Override
    public String getRoom()
    {
        return room;
    }

    @Override
    public String getContent()
    {
        return content;
    }
    
    public void setDestinationUser(String destinationUser)
    {
        this.destinationUser    =   destinationUser;
    }
    
    public void setRoom(String room)
    {
        this.room   =   room;
    }
    
    public void setContent(String content)
    {
        this.content    =   content;
    }
}
