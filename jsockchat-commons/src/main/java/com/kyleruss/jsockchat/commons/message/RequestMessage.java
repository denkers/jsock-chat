
package com.kyleruss.jsockchat.commons.message;

public interface RequestMessage extends Message
{
    public String getUserSource();
    
    public boolean hasSource();
    
    public boolean isWitness(String username);
}
