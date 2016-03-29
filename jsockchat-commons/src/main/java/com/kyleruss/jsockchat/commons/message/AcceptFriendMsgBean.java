package com.kyleruss.jsockchat.commons.message;

public class AcceptFriendMsgBean 
{
    private final boolean acceptRequest;
    private final int friendshipID;
    
    public AcceptFriendMsgBean(boolean acceptRequest, int friendshipID)
    {
        this.acceptRequest  =   acceptRequest;
        this.friendshipID   =   friendshipID;
    }
    
    public boolean isAcceptRequest()
    {
        return acceptRequest;
    }
    
    public int getFriendshipID()
    {
        return friendshipID;
    }
}
