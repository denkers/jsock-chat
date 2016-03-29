
package com.kyleruss.jsockchat.commons.updatebean;

import com.kyleruss.jsockchat.commons.message.RequestFriendMsgBean;
import java.util.List;
import com.kyleruss.jsockchat.commons.user.IUser;
import java.util.ArrayList;
import java.util.Map;

public class FriendsUpdateBean extends AbstractUpdateBean<IUser>
{
    private Map<String, IUser> onlineFriends;
    private List<RequestFriendMsgBean> pendingRequests;
    
    public Map<String, IUser> getOnlineFriends()
    {
        return onlineFriends;
    }
    
    public List<RequestFriendMsgBean> getPendingRequests()
    {
        return pendingRequests;
    }
    
    public void setPendingRequests(List<RequestFriendMsgBean> pendingRequests)
    {
        this.pendingRequests    =   pendingRequests;
    }
    
    public void setOnlineFriends(Map<String, IUser> onlineFriends)
    {
        this.onlineFriends    =   onlineFriends;
    }
    
    public List<IUser> getOnlineFriendsList()
    {
        return new ArrayList<>(onlineFriends.values());
    }
}
