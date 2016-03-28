
package com.kyleruss.jsockchat.commons.message;

public interface AuthMessage extends Message
{
    public String getUsername();
    
    public String getPassword();
    
    public boolean rememberPassword();
}
