
package com.kyleruss.jsockchat.server.core;

import com.kyleruss.jsockchat.commons.listbean.FriendListBean;
import com.kyleruss.jsockchat.commons.user.IUser;
import com.kyleruss.jsockchat.server.db.DBFriends;
import com.kyleruss.jsockchat.server.user.ServerUser;
import java.util.List;

public final class UserManager extends AbstractManager<String, ServerUser>
{
    private static UserManager instance;
    
    private UserManager() {}
    
    public FriendListBean createFriendListBean(String username)
    {
        FriendListBean bean         =   new FriendListBean();
        DBFriends friendModel       =   DBFriends.getInstance();
        List<IUser> friends         =   friendModel.getUsersFriends(username);
        List<IUser> onlineFriends   =   friendModel.getUsersOnlineFriends(username, friends);
        
        bean.setListData(friends);
        bean.setOnlineFriends(onlineFriends);
        return bean;
    }
    
    public static UserManager getInstance()
    {
        if(instance == null) instance = new UserManager();
        return instance;
    }
}
