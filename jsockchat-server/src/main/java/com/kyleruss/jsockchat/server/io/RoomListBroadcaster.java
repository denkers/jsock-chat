package com.kyleruss.jsockchat.server.io;

import com.kyleruss.jsockchat.commons.listbean.RoomListBean;
import com.kyleruss.jsockchat.server.core.RoomManager;
import com.kyleruss.jsockchat.server.core.UserManager;
import com.kyleruss.jsockchat.server.user.ServerUser;
import java.io.IOException;
import java.util.Collection;

public class RoomListBroadcaster extends AbstractBroadcaster
{
    public RoomListBroadcaster(ListBroadcastServer broadcaster, int updateTime) 
    {
        super(broadcaster, updateTime);
    }

    @Override
    protected void runBroadcastOperations() 
    {
        RoomManager roomManager             =   RoomManager.getInstance();
        RoomListBean bean                   =   roomManager.createRoomListBean();
        
        UserManager userManager             =   UserManager.getInstance();
        Collection<ServerUser> onlineUsers  =   userManager.getDataValues();
        
        for(ServerUser user : onlineUsers)
        {
            try { sendListBean(bean, user); }
            catch(IOException e)
            {
                System.out.println("[RoomListBroadcaster@runBroadcastOperations]: " + e.getMessage());
            }
        }
    }
}
