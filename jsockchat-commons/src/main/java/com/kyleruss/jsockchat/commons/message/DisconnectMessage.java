
package com.kyleruss.jsockchat.commons.message;

enum DisconnectContext
{
    ROOM,
    APPLICATION
}

public interface DisconnectMessage
{
    public DisconnectContext getContext();
}
