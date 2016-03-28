package com.kyleruss.jsockchat.server.io;

public class RoomListBroadcaster extends AbstractBroadcaster
{
    public RoomListBroadcaster(ListBroadcastServer broadcaster, int updateTime) 
    {
        super(broadcaster, updateTime);
    }

    @Override
    protected void runBroadcastOperations() {}

}
