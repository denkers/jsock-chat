
package com.kyleruss.jsockchat.server.db;

import com.kyleruss.jsockchat.server.core.DBManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.kyleruss.jsockchat.commons.user.IUser;
import com.kyleruss.jsockchat.commons.user.User;
import com.kyleruss.jsockchat.server.core.UserManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

public class DBFriends extends DBModel
{
    private static DBFriends instance;
    
    private DBFriends()
    {
        tableName   =   "Friends";
        primaryKey  =   "id";
    }
    
    public Map<String, IUser> getUsersFriends(String username)
    {
        Map<String, IUser> friends  =   new HashMap<>();
        String query        =   
        "SELECT friend.* \n" +
        "FROM Friends, Users friend\n" +
        "WHERE Friends.friend_b = friend.username\n" +
        "AND Friends.friend_a = ?\n" +
        "UNION\n" +
        "SELECT friend.*\n" +
        "FROM Friends, Users friend\n" +
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
                friends.put(user.getUsername(), user);
            }
        }
        
        catch(SQLException e)
        {
            System.out.println("[DBFriends@getUsersFriends]: " + e.getMessage());
        }
        
        return friends;
    }
    
    public Map<String, IUser> getUsersOnlineFriends(String username)
    {
        Map<String, IUser> friends         =   getUsersFriends(username);
        return getUsersOnlineFriends(username, friends);
    }
    
    public Map<String, IUser> getUsersOnlineFriends(String username, Map<String, IUser>  friends)
    {
        Map<String, IUser> onlineFriends    =   new HashMap<>();
        UserManager userMgr                 =   UserManager.getInstance();
        
        for(String friendUsername : friends.keySet())
        {
            if(userMgr.find(friendUsername))
                onlineFriends.put(friendUsername, friends.get(friendUsername));
        }
        
        return onlineFriends;
    }
    
    public static DBFriends getInstance()
    {
        if(instance == null) instance = new DBFriends();
        return instance;
    }
}
