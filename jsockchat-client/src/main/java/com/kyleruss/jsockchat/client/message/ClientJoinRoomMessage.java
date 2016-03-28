package com.kyleruss.jsockchat.client.message;

import com.kyleruss.jsockchat.commons.message.JoinRoomMessage;
import com.kyleruss.jsockchat.commons.message.ResponseMessage;

public class ClientJoinRoomMessage extends ClientMessage implements JoinRoomMessage
{
    private String room;
    private String attemptedPassword;

    public ClientJoinRoomMessage(String source) 
    {
        super(source);
    }
    
    public ClientJoinRoomMessage(String source, String room, String password)
    {
        super(source);
        this.room                   =   room;
        this.attemptedPassword      =   password;
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
    public String getAttemptedPassword() 
    {
        return attemptedPassword;
    }
    
    public void setRoom(String room)
    {
        this.room = room;
    }

    public void setAttemptedPassword(String attemptedPassword) 
    {
        this.attemptedPassword = attemptedPassword;
    }
    
}
