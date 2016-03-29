
package com.kyleruss.jsockchat.client.core;

import com.kyleruss.jsockchat.commons.listbean.FriendListBean;
import com.kyleruss.jsockchat.commons.user.User;

public class UserManager 
{
    private static UserManager instance;
    private User activeUser;
    private FriendListBean friendsBean;
    
    public User getActiveUser()
    {
        return activeUser;
    }
    
    public void setFriendsBean(FriendListBean friendsBean)
    {
        this.friendsBean    =   friendsBean;
    }
    
    public FriendListBean getFriendsBean()
    {
        return friendsBean;
    }
    
    public static UserManager getInstance()
    {
        if(instance == null) instance = new UserManager();
        return instance;
    }
}
