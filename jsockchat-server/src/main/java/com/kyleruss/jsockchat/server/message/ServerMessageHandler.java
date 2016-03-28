
package com.kyleruss.jsockchat.server.message;

import com.kyleruss.jsockchat.commons.message.RequestMessage;

public interface ServerMessageHandler
{
    public abstract void serverAction(RequestMessage request);
}
