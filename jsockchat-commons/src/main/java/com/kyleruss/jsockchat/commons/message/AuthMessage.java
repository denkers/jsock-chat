
package com.kyleruss.jsockchat.commons.message;

public interface AuthMessage extends RequestMessage
{
    public String getUsername();
    
    public String getPassword();
    
    public boolean rememberPassword();
}
