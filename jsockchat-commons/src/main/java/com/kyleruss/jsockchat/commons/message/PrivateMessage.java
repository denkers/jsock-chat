
package com.kyleruss.jsockchat.commons.message;

public interface PrivateMessage extends BroadcastMessage
{
    public String getDestinationUser();
}
