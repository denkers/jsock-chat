
package com.kyleruss.jsockchat.commons.message;

public class RegisterMessage extends AbstractMessage
{
    private String username;
    private String password;
    private String displayName;
    
    public RegisterMessage(String username, String password, String displayName)
    {
        this.username       =   username;
        this.password       =   password;
        this.displayName    =   displayName;
    }

    public String getUsername() 
    {
        return username;
    }

    public String getPassword() 
    {
        return password;
    }

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
