
package com.kyleruss.jsockchat.client.core;

import com.kyleruss.jsockchat.client.user.ClientUser;

public class UserManager 
{
    private static UserManager instance;
    private ClientUser activeUser;
    
    public ClientUser getActiveUser()
    {
        return activeUser;
    }
    
    public static UserManager getInstance()
    {
        if(instance == null) instance = new UserManager();
        return instance;
    }
}
