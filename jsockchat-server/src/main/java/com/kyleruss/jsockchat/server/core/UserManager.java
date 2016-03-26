
package com.kyleruss.jsockchat.server.core;

import com.kyleruss.jsockchat.server.user.ServerUser;

public final class UserManager extends AbstractManager<String, ServerUser>
{
    private static UserManager instance;
    
    private UserManager() {}
    
    
    public static UserManager getInstance()
    {
        if(instance == null) instance = new UserManager();
        return instance;
    }
}
