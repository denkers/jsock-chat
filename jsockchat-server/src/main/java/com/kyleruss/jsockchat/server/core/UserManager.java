
package com.kyleruss.jsockchat.server.core;

public final class UserManager
{
    private static UserManager instance;
    
    private UserManager()
    {
        
    }
    
    public static UserManager getInstance()
    {
        if(instance == null) instance = new UserManager();
        return instance;
    }
}
