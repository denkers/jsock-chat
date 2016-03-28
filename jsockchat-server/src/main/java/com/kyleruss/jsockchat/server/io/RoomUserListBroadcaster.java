package com.kyleruss.jsockchat.server.io;

public class RoomUserListBroadcaster extends AbstractBroadcaster
{

    public RoomUserListBroadcaster(ListBroadcastServer broadcaster, int updateTime)
    {
        super(broadcaster, updateTime);
    }

    @Override
    protected void runBroadcastOperations() {}
}
