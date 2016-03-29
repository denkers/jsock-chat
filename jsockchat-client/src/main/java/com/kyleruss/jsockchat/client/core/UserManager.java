
package com.kyleruss.jsockchat.client.core;

import com.kyleruss.jsockchat.commons.updatebean.FriendsUpdateBean;
import com.kyleruss.jsockchat.commons.updatebean.UsersUpdateBean;
import com.kyleruss.jsockchat.commons.user.User;

public class UserManager 
{
    private static UserManager instance;
    private User activeUser;
    private FriendsUpdateBean friendsBean;
    private UsersUpdateBean usersBean;
    
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
    
    public UsersUpdateBean getUsersBean()
    {
        return usersBean;
    }
    
    public void setUsersBean(UsersUpdateBean usersBean)
    {
        this.usersBean  =   usersBean;
    }
    
    public static UserManager getInstance()
    {
        if(instance == null) instance = new UserManager();
        return instance;
    }
}
