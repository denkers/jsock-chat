
package com.kyleruss.jsockchat.server.db;

public class DBFriends extends DBModel
{
    private static DBFriends instance;
    
    private DBFriends()
    {
        tableName   =   "Friends";
        primaryKey  =   "id";
    }
    
    public static DBFriends getInstance()
    {
        if(instance == null) instance = new DBFriends();
        return instance;
    }
}
