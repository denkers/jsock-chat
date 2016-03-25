
package com.kyleruss.jsockchat.server.core;

import com.kyleruss.jsockchat.commons.user.IUser;

public final class UserManager extends AbstractManager<String, IUser>
{
    private static UserManager instance;
    
    private UserManager() {}
    
    
    public static UserManager getInstance()
    {
        if(instance == null) instance = new UserManager();
        return instance;
    }
}
