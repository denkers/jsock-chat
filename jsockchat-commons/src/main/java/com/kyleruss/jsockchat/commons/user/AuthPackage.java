package com.kyleruss.jsockchat.commons.user;

import com.kyleruss.jsockchat.commons.updatebean.UpdateBeanDump;
import java.io.Serializable;

public class AuthPackage implements Serializable
{
    private final User authenticatedUser;
    private final UpdateBeanDump listDump;
    
    public AuthPackage(User authenticatedUser, UpdateBeanDump listDump)
    {
        this.authenticatedUser  =   authenticatedUser;
        this.listDump           =   listDump;
    }
    
    public User getAuthenticatedUser() 
    {
        return authenticatedUser;
    }
   
    public UpdateBeanDump getListDump() 
    {
        return listDump;
    }
}
