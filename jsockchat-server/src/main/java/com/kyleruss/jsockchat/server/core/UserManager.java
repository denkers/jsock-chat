
package com.kyleruss.jsockchat.server.core;

import com.kyleruss.jsockchat.commons.updatebean.FriendsUpdateBean;
import com.kyleruss.jsockchat.commons.updatebean.RoomsUpdateBean;
import com.kyleruss.jsockchat.commons.updatebean.UpdateBeanDump;
import com.kyleruss.jsockchat.commons.user.AuthPackage;
import com.kyleruss.jsockchat.commons.user.IUser;
import com.kyleruss.jsockchat.commons.user.User;
import com.kyleruss.jsockchat.server.db.DBFriends;
import java.util.Map;

public final class UserManager extends AbstractManager<String, User>
{
    private static UserManager instance;
    
    private UserManager() {}
    
    public FriendsUpdateBean createFriendListBean(String username)
    {
        FriendsUpdateBean bean              =   new FriendsUpdateBean();
        DBFriends friendModel               =   DBFriends.getInstance();
        Map<String, IUser> friends          =   friendModel.getUsersFriends(username);
        Map<String, IUser> onlineFriends    =   friendModel.getUsersOnlineFriends(username, friends);
        
        bean.setData(friends);
        bean.setOnlineFriends(onlineFriends);
        return bean;
    }
    
    public UpdateBeanDump prepareUpdates(User user)
    {
        RoomsUpdateBean roomsBean       =   RoomManager.getInstance().createRoomListBean();
        FriendsUpdateBean freindsBean   =   UserManager.getInstance().createFriendListBean(user.getUsername());
        UpdateBeanDump beanDump         =   new UpdateBeanDump(freindsBean, roomsBean);
        
        return beanDump;
    }
    
    public AuthPackage prepareAuthPackage(User user)
    {
        UpdateBeanDump beanDump =   prepareUpdates(user);
        return new AuthPackage(user, beanDump);
    }
    
    public static UserManager getInstance()
    {
        if(instance == null) instance = new UserManager();
        return instance;
    }
}
