
package com.kyleruss.jsockchat.server.db;

public class DBUsers extends DBModel
{
    private static DBUsers instance;
    
    private DBUsers()
    {
        tableName   =   "Users";
        primaryKey  =   "username";
    }
    
    public static DBUsers getInstance()
    {
        if(instance == null) instance = new DBUsers();
        return instance;
    }
}
