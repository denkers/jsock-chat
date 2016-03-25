package com.kyleruss.jsockchat.server.io;

public class RoomUserListBroadcaster extends AbstractBroadcaster
{

    public RoomUserListBroadcaster(ListBroadcastServer broadcaster)
    {
        super(broadcaster);
    }

    @Override
    protected void runBroadcastOperations() {}
}
