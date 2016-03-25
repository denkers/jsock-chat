
package com.kyleruss.jsockchat.server.db;

import com.kyleruss.jsockchat.server.core.DBManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.kyleruss.jsockchat.commons.user.IUser;

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
        
        try
        {
            DBManager dbManager =   DBManager.getInstance();
            ResultSet results   =   dbManager.sendQuery(query, username, username);
            
            while(results.next())
            {
                String rsUsername       =   results.getString("username");
                String rsDisplayname    =   results.getString("display_name");
                ServerUser user         =   new ServerUser(rsUsername)
            }
        }
        
        catch(SQLException e)
        {
            System.out.println("[DBFriends@getUsersFriends]: " + e.getMessage());
        }
        
        return friends;
    }
    
    public static DBFriends getInstance()
    {
        if(instance == null) instance = new DBFriends();
        return instance;
    }
}
