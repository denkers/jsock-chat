
package com.kyleruss.jsockchat.server.db;

import com.kyleruss.jsockchat.commons.user.User;
import com.kyleruss.jsockchat.server.core.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUsers extends DBModel<User>
{
    private static DBUsers instance;
    
    private DBUsers()
    {
        tableName   =   "Users";
        primaryKey  =   "username";
    }
    
    public boolean verifyUser(String username, String password)
    {
        String query    =   "SELECT * FROM " + tableName + " WHERE " + primaryKey + " = ? AND password = ?;";
        
        try(Connection conn =   DBManager.getConnection())
        {
            PreparedStatement statement =   conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            
            ResultSet rs    =   statement.executeQuery();
            return rs.next();
        }
        
        catch(SQLException e)
        {
            System.out.println("[DBUsers@verifyUser]: " + e.getMessage());
            return false;
        }
    }
    
    public boolean createUser(User user, String password)
    {
        String username     =   user.getUsername();
        String displayname  =   user.getDisplayName();
        String update       =   "INSERT INTO " + tableName + " VALUES(?, ?, ?);";
        
        try(Connection conn =   DBManager.getConnection())
        {   
            PreparedStatement statement  =   conn.prepareStatement(update);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(2, displayname);
            
            int rowCount    =   statement.executeUpdate();
            return rowCount > 0;
        }
        
        catch(SQLException e)
        {
            System.out.println("[DBUsers@verifyUser]: " + e.getMessage());
            return false;
        }
    }
    
    public static DBUsers getInstance()
    {
        if(instance == null) instance = new DBUsers();
        return instance;
    }
}
