
package com.kyleruss.jsockchat.client.core;

import com.kyleruss.jsockchat.commons.updatebean.FriendsUpdateBean;
import com.kyleruss.jsockchat.commons.user.User;

public class UserManager 
{
    private static UserManager instance;
    private User activeUser;
    private FriendsUpdateBean friendsBean;
    
    public User getActiveUser()
    {
        return activeUser;
    }
    
    public void setFriendsBean(FriendsUpdateBean friendsBean)
    {
        this.friendsBean    =   friendsBean;
    }
    
    public FriendsUpdateBean getFriendsBean()
    {
        return friendsBean;
    }
    
    public static UserManager getInstance()
    {
        if(instance == null) instance = new UserManager();
        return instance;
    }
}
