
package com.kyleruss.jsockchat.commons.listbean;

import java.util.List;
import com.kyleruss.jsockchat.commons.user.IUser;

public class FriendListBean extends AbstractListBean<IUser>
{
    private List<IUser> onlineFriends;
    
    public List<IUser> getOnlineFriends()
    {
        return onlineFriends;
    }
    
    public void setOnlineFriends(List<IUser> onlineFriends)
    {
        this.onlineFriends    =   onlineFriends;
    }
}
