
package com.kyleruss.jsockchat.server.core;

import com.kyleruss.jsockchat.commons.user.User;

public final class UserManager extends AbstractManager<String, User>
{
    private static UserManager instance;
    
    private UserManager() {}
    
    
    public static UserManager getInstance()
    {
        if(instance == null) instance = new UserManager();
        return instance;
    }
}
