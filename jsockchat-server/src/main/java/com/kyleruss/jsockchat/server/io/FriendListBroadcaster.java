package com.kyleruss.jsockchat.server.io;

import com.kyleruss.jsockchat.commons.listbean.FriendListBean;
import com.kyleruss.jsockchat.server.core.UserManager;
import java.util.Collection;
import com.kyleruss.jsockchat.server.user.ServerUser;
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
        UserManager userManager                 =   UserManager.getInstance();
        Collection<ServerUser> onlineUsers      =   userManager.getDataValues();
        
        for(ServerUser user : onlineUsers)
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
