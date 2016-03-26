
package com.kyleruss.jsockchat.server.db;

import com.kyleruss.jsockchat.server.core.DBManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.kyleruss.jsockchat.commons.user.IUser;
import com.kyleruss.jsockchat.commons.user.User;
import com.kyleruss.jsockchat.server.core.UserManager;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DBFriends extends DBModel
{
    private static DBFriends instance;
    
    private DBFriends()
    {
        tableName   =   "Friends";
        primaryKey  =   "id";
    }
    
    public List<IUser> getUsersFriends(String username)
    {
        List<IUser> friends  =   new ArrayList<>();
        String query        =   
        "SELECT friend.* \n" +
        "FROM Friends, User friend\n" +
        "WHERE Friends.friend_b = friend.username\n" +
        "AND Friends.friend_a = ?\n" +
        "UNION\n" +
        "SELECT friend.*\n" +
        "FROM Friends, User friend\n" +
        "WHERE Friends.friend_a = friend.username\n" +
        "AND Friends.friend_b = ?";
        
        try(Connection conn     =   DBManager.getConnection())
        {
            PreparedStatement statement =   conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, username);
            
            ResultSet results           =   statement.executeQuery();
            
            while(results.next())
            {
                String rsUsername       =   results.getString("username");
                String rsDisplayname    =   results.getString("display_name");
                
                User user               =   new User(rsUsername, rsDisplayname);
                friends.add(user);
            }
        }
        
        catch(SQLException e)
        {
            System.out.println("[DBFriends@getUsersFriends]: " + e.getMessage());
        }
        
        return friends;
    }
    
    public List<IUser> getUsersOnlineFriends(String username)
    {
        List<IUser> friends         =   getUsersFriends(username);
        List<IUser> onlineFriends   =   new ArrayList<>();
        UserManager userMgr         =   UserManager.getInstance();
        
        for(IUser friend : friends)
        {
            if(userMgr.find(friend.getUsername()))
                onlineFriends.add(friend);
        }
        
        return onlineFriends;
    }
    
    public static DBFriends getInstance()
    {
        if(instance == null) instance = new DBFriends();
        return instance;
    }
}
