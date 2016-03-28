
package com.kyleruss.jsockchat.server.core;

public class ServerManager 
{
    private static ServerManager instance;
    
    private ServerManager() {} 
    
    public static ServerManager getInstance()
    {
        if(instance == null) instance = new ServerManager();
        return instance;
    }
    
    public static void main(String[] args)
    {
        
    }
}
