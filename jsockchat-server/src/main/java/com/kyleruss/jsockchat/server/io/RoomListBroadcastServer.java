package com.kyleruss.jsockchat.server.io;

public class RoomListBroadcastServer extends AbstractBroadcastServer
{
    public RoomListBroadcastServer(ListBroadcaster broadcaster) 
    {
        super(broadcaster);
    }

    @Override
    protected void runBroadcastOperations() {}

}
