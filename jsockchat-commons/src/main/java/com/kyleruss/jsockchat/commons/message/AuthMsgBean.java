
package com.kyleruss.jsockchat.commons.message;

public class AuthMsgBean implements MessageBean
{
    private String username;
    private String password;
    private boolean rememberPassword;
   
    public AuthMsgBean(String username, String password, boolean rememberPassword)
    {
        this.username           =   username;
        this.password           =   password;
        this.rememberPassword   =   rememberPassword;
    }
    
    public String getUsername() 
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public boolean rememberPassword() 
    {
        return rememberPassword;
    }
    
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public void setRememberPassword(boolean rememberPassword) 
    {
        this.rememberPassword = rememberPassword;
    }
}
