
package com.kyleruss.jsockchat.server.db;

import com.kyleruss.jsockchat.commons.user.User;

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
        String query    =   "SELECT * WHERE username = ? AND password = ?";
    }
    
    public static DBUsers getInstance()
    {
        if(instance == null) instance = new DBUsers();
        return instance;
    }
}
