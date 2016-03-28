package com.kyleruss.jsockchat.server.io;

import com.kyleruss.jsockchat.server.core.DBManager;
import com.kyleruss.jsockchat.server.core.UserManager;
import java.util.Collection;
import com.kyleruss.jsockchat.server.user.ServerUser;


public class FriendListBroadcaster extends AbstractBroadcaster
{
    public FriendListBroadcaster(ListBroadcastServer broadcaster, int updateTime) 
    {
        super(broadcaster, updateTime);
    }

    @Override
    protected void runBroadcastOperations() 
    {
        DBManager dbManager             =   DBManager.getInstance();
        UserManager userManager         =   UserManager.getInstance();
        Collection<ServerUser> users    =   userManager.getDataValues();
    }
}
