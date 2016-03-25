
package com.kyleruss.jsockchat.commons.user;

import java.io.Serializable;
import java.util.List;

public interface User extends Serializable
{
    public String getUsername();
    
    public String getDisplayName();
    
    public List<String> getCurrentRooms();
}
