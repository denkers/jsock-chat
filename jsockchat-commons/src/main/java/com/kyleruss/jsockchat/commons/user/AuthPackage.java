package com.kyleruss.jsockchat.commons.user;

import com.kyleruss.jsockchat.commons.listbean.ListBeanDump;
import java.io.Serializable;

public class AuthPackage implements Serializable
{
    private final User authenticatedUser;
    private final ListBeanDump listDump;
    
    public AuthPackage(User authenticatedUser, ListBeanDump listDump)
    {
        this.authenticatedUser  =   authenticatedUser;
        this.listDump           =   listDump;
    }
    
    public User getAuthenticatedUser() 
    {
        return authenticatedUser;
    }
   
    public ListBeanDump getListDump() 
    {
        return listDump;
    }
}
