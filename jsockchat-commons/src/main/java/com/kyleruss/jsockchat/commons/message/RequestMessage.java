
package com.kyleruss.jsockchat.commons.message;

public interface RequestMessage extends Message
{
    public int getRequestID();
    
    public String getUserSource();
    
    public boolean isIDSet();
    
    public boolean hasSource();
}
