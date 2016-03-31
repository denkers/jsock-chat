
package com.kyleruss.jsockchat.server.core;


public class ServerConfig 
{
    //---------------------------------------------------------
    //  SERVER CONSTS
    //---------------------------------------------------------
    public static final int MESSAGE_SERVER_PORT         =   8890;
    public static final int MESSAGE_SERVER_TIMEOUT      =   0;
    public static final int BROADCAST_PORT              =   8891;
    public static final int BROADCAST_DELAY             =   8000;
    public static final String FIXED_ROOMS_PATH         =   "data/rooms.xml";
    //---------------------------------------------------------
    
    
    //---------------------------------------------------------
    //  GUI CONSTS
    //---------------------------------------------------------
    public static final String WINDOW_TITLE =   "JSockChat Server";
    public static final int WINDOW_WIDTH    =   500;
    public static final int WINDOW_HEIGHT   =   500;
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
