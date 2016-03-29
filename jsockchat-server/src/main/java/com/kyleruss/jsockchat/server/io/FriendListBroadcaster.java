package com.kyleruss.jsockchat.server.io;

import com.kyleruss.jsockchat.commons.listbean.FriendListBean;
import com.kyleruss.jsockchat.commons.user.User;
import com.kyleruss.jsockchat.server.core.UserManager;
import java.util.Collection;
import java.io.IOException;


public class FriendListBroadcaster extends AbstractBroadcaster
{
    public FriendListBroadcaster(ListBroadcastServer broadcaster, int updateTime) 
    {
        super(broadcaster, updateTime);
    }

    @Override
    protected void runBroadcastOperations() 
    {   
        UserManager userManager           =   UserManager.getInstance();
        Collection<User> onlineUsers      =   userManager.getDataValues();
        
        for(User user : onlineUsers)
        {
            String username     =   user.getUsername();
            FriendListBean bean =   userManager.createFriendListBean(username);
            
            try { sendListBean(bean, user); }
            catch(IOException e)
            {
                System.out.println("[FriendListBroadcaster@runBroadcastOperations]: " + e.getMessage());
            }
        }
    }
}
