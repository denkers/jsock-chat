package com.kyleruss.jsockchat.server.io;

import com.kyleruss.jsockchat.commons.user.User;
import com.kyleruss.jsockchat.server.core.DBManager;
import com.kyleruss.jsockchat.server.core.UserManager;
import java.util.Collection;


public class FriendListBroadcaster extends AbstractBroadcaster
{
    public FriendListBroadcaster(ListBroadcastServer broadcaster) 
    {
        super(broadcaster);
    }

    @Override
    protected void runBroadcastOperations() 
    {
        DBManager dbManager     =   DBManager.getInstance();
        UserManager userManager =   UserManager.getInstance();
        Collection<User> users  =   userManager.getDataValues();
    }
}
