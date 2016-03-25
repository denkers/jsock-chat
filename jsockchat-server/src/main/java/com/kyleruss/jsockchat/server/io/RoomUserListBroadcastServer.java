package com.kyleruss.jsockchat.server.io;

public class RoomUserListBroadcastServer extends AbstractBroadcastServer
{

    public RoomUserListBroadcastServer(ListBroadcaster broadcaster)
    {
        super(broadcaster);
    }

    @Override
    protected void runBroadcastOperations() {}
}
