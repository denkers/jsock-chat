package com.kyleruss.jsockchat.server.io;

public class RoomListBroadcaster extends AbstractBroadcaster
{
    public RoomListBroadcaster(ListBroadcastServer broadcaster) 
    {
        super(broadcaster);
    }

    @Override
    protected void runBroadcastOperations() {}

}
