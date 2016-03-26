package com.kyleruss.jsockchat.server.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager 
{
    private static DBManager instance;
    
    private DBManager()
    {
        initDriver();
    }
    
    private void initDriver()
    {
        try { Class.forName(ServerConfig.JDBC_CLASS); }
        catch(ClassNotFoundException e)
        {
            System.out.println("[DBManager@initDriver]: " + e.getMessage());
        }
    }
    
    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(ServerConfig.CONN_URL);
    }
    
    public void sendUpdates(String... updateStrs) throws SQLException
    {
        try(Connection conn = getConnection())
        {
            Statement statement =   conn.createStatement();
            for(String updateStr : updateStrs)
                statement.addBatch(updateStr);

            statement.executeBatch();
        }   
    }
    
    public static DBManager getInstance()
    {
        if(instance == null) instance = new DBManager();
        return instance;
    }
}
