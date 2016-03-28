
package com.kyleruss.jsockchat.commons.message;

public interface RegisterMessage extends RequestMessage
{
    public String getUsername();
    
    public String getPassword();
    
    public String getDisplayName();
}
