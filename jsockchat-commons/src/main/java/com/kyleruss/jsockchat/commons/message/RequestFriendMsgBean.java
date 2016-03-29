package com.kyleruss.jsockchat.commons.message;

public class RequestFriendMsgBean implements MessageBean
{
    private String friendA;
    private String friendB;
    
    public RequestFriendMsgBean(String friendA, String friendB)
    {
        this.friendA    =   friendA;
        this.friendB    =   friendB;
    }
    
    public String getFriendA()
    {
        return friendA;
    }
    
    public String getFriendB()
    {
        return friendB;
    }
}
