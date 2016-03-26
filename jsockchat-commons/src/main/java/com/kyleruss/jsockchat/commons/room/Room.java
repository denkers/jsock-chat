
package com.kyleruss.jsockchat.commons.room;

import java.io.Serializable;
import java.util.List;

public interface Room extends Serializable
{
    public List<String> getUserList();
    
    public int getNumUsersInRoom();
    
    public String getOwner();
    
    public boolean isPrivate();
    
    public void setPrivate(boolean isPrivate);
    
    public boolean isPassProtected();
    
    public String getRoomPassword();
    
    public String getRoomName();
    
    public void setUserList(List<String> userList);
    
    public void leaveRoom(String username);
    
    public boolean joinRoom(String username);
}
