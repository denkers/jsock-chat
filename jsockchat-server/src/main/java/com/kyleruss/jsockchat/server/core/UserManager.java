
package com.kyleruss.jsockchat.server.core;

import com.kyleruss.jsockchat.commons.message.Message;
import com.kyleruss.jsockchat.commons.message.MessageQueueItem;
import com.kyleruss.jsockchat.commons.message.RequestFriendMsgBean;
import com.kyleruss.jsockchat.commons.updatebean.FriendsUpdateBean;
import com.kyleruss.jsockchat.commons.updatebean.UpdateBeanDump;
import com.kyleruss.jsockchat.commons.updatebean.UsersUpdateBean;
import com.kyleruss.jsockchat.commons.user.AuthPackage;
import com.kyleruss.jsockchat.commons.user.IUser;
import com.kyleruss.jsockchat.commons.user.User;
import com.kyleruss.jsockchat.server.db.DBFriends;
import com.kyleruss.jsockchat.server.io.ServerMessageSender;
import com.kyleruss.jsockchat.server.io.UserSocket;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public final class UserManager extends AbstractManager<String, IUser>
{
    private static UserManager instance;
    
    private UserManager() {}
    
    public FriendsUpdateBean createFriendsBean(String username)
    {
        FriendsUpdateBean bean                      =   new FriendsUpdateBean();
        DBFriends friendModel                       =   DBFriends.getInstance();
        Map<String, IUser> friends                  =   friendModel.getUsersFriends(username);
        Map<String, IUser> onlineFriends            =   friendModel.getUsersOnlineFriends(username, friends);
        List<RequestFriendMsgBean> friendRequests   =   friendModel.getPendingFriendRequests(username);
        
        bean.setData(friends);
        bean.setOnlineFriends(onlineFriends);
        bean.setPendingRequests(friendRequests);
        return bean;
    }
    
    
    public UsersUpdateBean createUsersBean()
    {
        UsersUpdateBean bean    =   new UsersUpdateBean();
        bean.setData(data);
        return bean;
    }
    
    public synchronized void clientExit(IUser client)
    {
        data.remove(client.getUsername());
        SocketManager.getInstance().cleanUp(client.getUsername());
    }
    
    public synchronized void sendMessageToUser(String username, Message message) throws IOException
    {
        UserSocket userSocket     =   SocketManager.getInstance().get(username);

        if(userSocket != null)
        {
            MessageQueueItem messageItem    =   new MessageQueueItem(userSocket.getSocketOutputStream(), message);
            ServerMessageSender.getInstance().addMessage(messageItem);
        }
    }
    
    public AuthPackage prepareAuthPackage(User user)
    {
        UpdateBeanDump beanDump     =   ServerManager.getInstance().prepareUpdates(user);
        return new AuthPackage(user, beanDump);
    }
    
    public static UserManager getInstance()
    {
        if(instance == null) instance = new UserManager();
        return instance;
    }
}
