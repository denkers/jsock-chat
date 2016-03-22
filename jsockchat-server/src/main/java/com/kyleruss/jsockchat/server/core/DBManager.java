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
    
    public void sendUpdates(String... updateStrs) throws SQLException
    {
        try(Connection connection   =   DriverManager.getConnection(ServerConfig.CONN_URL))
        {
            Statement statement =   connection.createStatement();
            for(String updateStr : updateStrs)
                statement.addBatch(updateStr);

            statement.executeBatch();
        }   
    }
    
    public ResultSet sendQuery(String query) throws SQLException
    {
        try(Connection connection   =   DriverManager.getConnection(ServerConfig.CONN_URL))
        {
            Statement statement     =   connection.createStatement();
            return statement.executeQuery(query);
        }
    }
    
    public ResultSet sendQuery(String query, Object... params) throws SQLException
    {
        try(Connection connection   =   DriverManager.getConnection(ServerConfig.CONN_URL))
        {
            PreparedStatement statement =  connection.prepareStatement(query);

            int i   =   1;
            for(Object param : params)
                statement.setObject(i++, param);
            
            return statement.executeQuery();
        }
    }
    
    public static DBManager getInstance()
    {
        if(instance == null) instance = new DBManager();
        return instance;
    }
}
