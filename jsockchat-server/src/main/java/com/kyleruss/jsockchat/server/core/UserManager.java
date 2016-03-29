
package com.kyleruss.jsockchat.server.core;

import com.kyleruss.jsockchat.commons.updatebean.FriendsUpdateBean;
import com.kyleruss.jsockchat.commons.updatebean.RoomsUpdateBean;
import com.kyleruss.jsockchat.commons.updatebean.UpdateBeanDump;
import com.kyleruss.jsockchat.commons.updatebean.UsersUpdateBean;
import com.kyleruss.jsockchat.commons.user.AuthPackage;
import com.kyleruss.jsockchat.commons.user.IUser;
import com.kyleruss.jsockchat.commons.user.User;
import com.kyleruss.jsockchat.server.db.DBFriends;
import java.util.Map;

public final class UserManager extends AbstractManager<String, IUser>
{
    private static UserManager instance;
    
    private UserManager() {}
    
    public FriendsUpdateBean createFriendsBean(String username)
    {
        FriendsUpdateBean bean              =   new FriendsUpdateBean();
        DBFriends friendModel               =   DBFriends.getInstance();
        Map<String, IUser> friends          =   friendModel.getUsersFriends(username);
        Map<String, IUser> onlineFriends    =   friendModel.getUsersOnlineFriends(username, friends);
        
        bean.setData(friends);
        bean.setOnlineFriends(onlineFriends);
        return bean;
    }
    
    public UsersUpdateBean createUsersBean()
    {
        UsersUpdateBean bean    =   new UsersUpdateBean();
        bean.setData(data);
        return bean;
    }
    
    public AuthPackage prepareAuthPackage(User user)
    {
        UpdateBeanDump beanDump     =   ServerManager.getInstance().prepareUpdates(user);
        return new AuthPackage(user, beanDump);
    }
    
    public static UserManager getInstance()
    {
        if(instance == null) instance = new UserManager();
        return instance;
    }
}
