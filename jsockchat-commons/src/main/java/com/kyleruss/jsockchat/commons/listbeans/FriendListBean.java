
package com.kyleruss.jsockchat.commons.listbeans;

import com.kyleruss.jsockchat.commons.user.User;
import java.util.List;

public class FriendListBean extends AbstractListBean<User>
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
