
package com.kyleruss.jsockchat.commons.room;

import java.io.Serializable;
import java.util.Set;

enum RoomType
{
    PRIVATE,
    OPEN
}

public interface Room extends Serializable
{
    public Set<String> getUserList();
    
    public int getNumUsersInRoom();
    
    public String getOwner();
    
    public RoomType getRoomType();
    
    public boolean isPassProtected();
    
    public String getRoomPassword();
    
    public String getRoomName();
    
    public void setRoomPassword(String password);
    
    public void setUserList(Set<String> userList);
    
    public void leaveRoom(String username);
    
    public boolean joinRoom(String username);
}
