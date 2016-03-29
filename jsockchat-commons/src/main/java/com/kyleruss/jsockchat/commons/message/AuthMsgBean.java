
package com.kyleruss.jsockchat.commons.message;

public class AuthMsgBean implements MessageBean
{
    private String username;
    private String password;
   
    public AuthMsgBean(String username, String password)
    {
        this.username           =   username;
        this.password           =   password;
    }
    
    public String getUsername() 
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setUsername(String username) 
    {
        this.username = username;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }
}
