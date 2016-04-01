package com.kyleruss.jsockchat.server.core;

import com.kyleruss.jsockchat.server.gui.AppResources;
import com.kyleruss.jsockchat.server.gui.LogMessage;
import com.kyleruss.jsockchat.server.gui.LoggingList;
import java.sql.Connection;
import java.sql.DriverManager;
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
        LoggingList.sendLogMessage(new LogMessage("[DB manager] Registering database driver...", AppResources.getInstance().getServerOkImage()));
        try { Class.forName(ServerConfig.JDBC_CLASS); }
        catch(ClassNotFoundException e)
        {
            LoggingList.sendLogMessage(new LogMessage("[DB manager] Failed to register database driver", AppResources.getInstance().getServerBadImage()));
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
