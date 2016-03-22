
package com.kyleruss.jsockchat.server.core;


public class ServerConfig 
{
    //---------------------------------------------------------
    //  SERVER CONSTS
    //---------------------------------------------------------
    public static final int MESSAGE_SERVER_PORT     =   8890;
    public static final int MESSAGE_SERVER_TIMEOUT  =   0;
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
