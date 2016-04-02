
package com.kyleruss.jsockchat.client.core;

import com.kyleruss.jsockchat.commons.updatebean.FriendsUpdateBean;
import com.kyleruss.jsockchat.commons.updatebean.UsersUpdateBean;
import com.kyleruss.jsockchat.commons.user.IUser;

public class UserManager 
{
    private static UserManager instance;
    private IUser activeUser;
    private FriendsUpdateBean friendsBean;
    private UsersUpdateBean usersBean;
    
    private UserManager() {}
    
    public IUser getActiveUser()
    {
        return activeUser;
    }
    
    public void setActiveUser(IUser activeUser)
    {
        this.activeUser =   activeUser;
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
    
    public String getSource()
    {
        if(activeUser == null) return null;
        else return activeUser.getUsername();
    }
    
    public static UserManager getInstance()
    {
        if(instance == null) instance = new UserManager();
        return instance;
    }
}
