package com.kyleruss.jsockchat.commons.message;

public class AcceptFriendMsgBean 
{
    private final boolean acceptRequest;
    private final int friendshipID;
    private final String fromUser;
    
    public AcceptFriendMsgBean(boolean acceptRequest, int friendshipID, String fromUser)
    {
        this.acceptRequest  =   acceptRequest;
        this.friendshipID   =   friendshipID;
        this.fromUser       =   fromUser;
    }
    
    public boolean isAcceptRequest()
    {
        return acceptRequest;
    }
    
    public int getFriendshipID()
    {
        return friendshipID;
    }
    
    public String getFromUser()
    {
        return fromUser;
    }
}
