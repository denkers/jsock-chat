
package com.kyleruss.jsockchat.commons.listbean;

import java.util.List;
import com.kyleruss.jsockchat.commons.user.IUser;

public class FriendListBean extends AbstractListBean<IUser>
{
    private List<Integer> onlineFriendIndexes;
    
    public List<Integer> getOnlineFriendIndexes()
    {
        return onlineFriendIndexes;
    }
    
    public void setOnlineFriendIndexes(List<Integer> onlineFriendIndexes)
    {
        this.onlineFriendIndexes    =   onlineFriendIndexes;
    }
}
