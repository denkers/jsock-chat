
package com.kyleruss.jsockchat.commons.room;

import java.util.List;

enum RoomType
{
    PRIVATE,
    OPEN
}

public interface Room 
{
    public List<String> getUserList();
    
    public int getNumUsersInRoom();
    
    public RoomType getRoomType();
    
    public boolean isPassProtected();
    
    public String getRoomPassword();
    
    public String getRoomName();
    
    public void setRoomPassword(String password);
    
    public void setUserList(List<String> userList);
    
    public void leaveRoom(String username);
    
    public boolean joinRoom(String username);
}
