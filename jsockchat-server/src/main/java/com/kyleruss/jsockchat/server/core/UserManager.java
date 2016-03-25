
package com.kyleruss.jsockchat.server.core;

import com.kyleruss.jsockchat.commons.user.User;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class UserManager
{
    private static UserManager instance;
    private final Map<String, User> users;
    
    private UserManager()
    {
        users   =   new HashMap<>();
    }
    
    public synchronized boolean addUser(String username, User user)
    {
        return users.put(username, user) != null;
    }
    
    public synchronized User removeUser(String username)
    {
        return users.remove(username);
    }
    
    public synchronized User getUser(String username)
    {
        return users.get(username);
    }
    
    public synchronized Collection<User> getUsers()
    {
        return users.values();
    }
    
    public static UserManager getInstance()
    {
        if(instance == null) instance = new UserManager();
        return instance;
    }
}
