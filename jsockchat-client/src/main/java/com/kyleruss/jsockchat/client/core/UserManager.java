
package com.kyleruss.jsockchat.client.core;

public class UserManager 
{
    private static UserManager instance;
    
    
    
    public static UserManager getInstance()
    {
        if(instance == null) instance = new UserManager();
        return instance;
    }
}
