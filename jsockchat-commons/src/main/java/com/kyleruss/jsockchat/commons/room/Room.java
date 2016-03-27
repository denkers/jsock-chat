
package com.kyleruss.jsockchat.commons.room;

import com.kyleruss.jsockchat.commons.user.IUser;
import java.io.Serializable;
import java.util.List;

public interface Room extends Serializable
{
    public List<IUser> getUserList();
    
    public int getNumUsersInRoom();
    
    public String getOwner();
    
    public boolean isPrivate();
    
    public void setPrivate(boolean isPrivate);
    
    public boolean isPassProtected();
    
    public String getRoomPassword();
    
    public String getRoomName();
    
    public void setUserList(List<IUser> userList);
    
    public void leaveRoom(IUser username);
    
    public boolean joinRoom(IUser username);
}
