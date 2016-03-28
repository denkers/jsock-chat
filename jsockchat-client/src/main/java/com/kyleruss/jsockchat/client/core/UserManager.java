
package com.kyleruss.jsockchat.client.core;

import com.kyleruss.jsockchat.commons.user.User;

public class UserManager 
{
    private static UserManager instance;
    private User activeUser;
    
    public User getActiveUser()
    {
        return activeUser;
    }
    
    public static UserManager getInstance()
    {
        if(instance == null) instance = new UserManager();
        return instance;
    }
}
