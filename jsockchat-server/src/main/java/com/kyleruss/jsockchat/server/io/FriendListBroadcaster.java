package com.kyleruss.jsockchat.server.io;


public class FriendListBroadcaster extends AbstractBroadcaster
{
    public FriendListBroadcaster(ListBroadcastServer broadcaster) 
    {
        super(broadcaster);
    }

    @Override
    protected void runBroadcastOperations() {}

}
