
package com.kyleruss.jsockchat.client.core;

public class ClientManager 
{
    private static ClientManager instance;
    
    private ClientManager() {}
    
    public static ClientManager getInstance()
    {
        if(instance == null) instance = new ClientManager();
        return instance;
    }
}
