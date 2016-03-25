
package com.kyleruss.jsockchat.server.core;


public class ServerConfig 
{
    //---------------------------------------------------------
    //  SERVER CONSTS
    //---------------------------------------------------------
    public static final int MESSAGE_SERVER_PORT         =   8890;
    public static final int MESSAGE_SERVER_TIMEOUT      =   0;
    public static final int BROADCAST_PORT              =   8891;
    public static final int FRIEND_LIST_UPDATE_MS       =   2500;
    public static final int ROOM_USER_LIST_UPDATE_MS    =   3500;
    public static final int ROOM_LIST_UPDATE_MS         =   10000;
    //---------------------------------------------------------
    
    
    
    //---------------------------------------------------------
    //  DATABASE CONSTS
    //---------------------------------------------------------
    public static final String JDBC_CLASS   =   "org.sqlite.JDBC";
    public static final String JDBC_DRIVER  =   "jdbc:sqlite:";
    public static final String DB_FILE      =   "data/db/jsockchat.db";
    public static final String CONN_URL     =   JDBC_DRIVER + DB_FILE;
    //---------------------------------------------------------
}
