package com.kyleruss.jsockchat.client.message;

import com.kyleruss.jsockchat.commons.message.BroadcastMessage;
import com.kyleruss.jsockchat.commons.message.ResponseMessage;

public class ClientBroadcastMessage extends ClientMessage implements BroadcastMessage
{

    private String room;
    private String content;
    
    public ClientBroadcastMessage(String source)
    {
        super(source);
    }
    
    public ClientBroadcastMessage(String source, String room, String content)
    {
        super(source);
        this.room       =   room;
        this.content    =   content;
    }

    @Override
    public void clientAction(ResponseMessage response) {
    }

    @Override
    public void witnessAction(ResponseMessage response) {
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
    
    public void setRoom(String room)
    {
        this.room = room;
    }

    public void setContent(String content) 
    {
        this.content = content;
    }
}
