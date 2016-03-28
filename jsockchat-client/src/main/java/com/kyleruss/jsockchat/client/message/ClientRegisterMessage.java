package com.kyleruss.jsockchat.client.message;

import com.kyleruss.jsockchat.commons.message.RegisterMessage;
import com.kyleruss.jsockchat.commons.message.ResponseMessage;

public class ClientRegisterMessage extends ClientMessage implements RegisterMessage
{
    private String username;
    private String password;
    private String displayName;
    
    public ClientRegisterMessage()
    {
        super(null);
    }
    
    public ClientRegisterMessage(String username, String password, String displayName)
    {
        super(null);
        this.username       =   username;
        this.password       =   password;
        this.displayName    =   displayName;
    }

    @Override
    public void clientAction(ResponseMessage response) {
    }

    @Override
    public void witnessAction(ResponseMessage response) {
    }

    @Override
    public String getUsername() 
    {
        return username;
    }

    @Override
    public String getPassword() 
    {
        return password;
    }

    @Override
    public String getDisplayName()
    {
        return displayName;
    }
    
    public void setUsername(String username)
    {
        this.username   =   username;
    }
    
    public void setPassword(String password)
    {
        this.password   =   password;
    }
    
    public void setDisplayName(String displayName)
    {
        this.displayName    =   displayName;
    }
}
