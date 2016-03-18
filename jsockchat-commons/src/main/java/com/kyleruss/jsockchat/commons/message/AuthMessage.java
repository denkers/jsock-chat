
package com.kyleruss.jsockchat.commons.message;

public class AuthMessage 
{
    private String username;
    private String password;
    private boolean rememberPass;
    
    public String getUsername()
    {
        return username; 
    }
    
    public boolean getRememberPass()
    {
        return rememberPass;
    }
    
    public String getPassword()
    {
        return password;
    }
}
