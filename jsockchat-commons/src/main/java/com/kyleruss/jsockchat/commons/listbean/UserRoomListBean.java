
package com.kyleruss.jsockchat.commons.listbean;

import com.kyleruss.jsockchat.commons.user.User;

public class UserRoomListBean extends AbstractListBean<User>
{
    private String roomName;
    
    public String getRoomName()
    {
        return roomName;
    }
    
    public void setRoomName(String roomName)
    {
        this.roomName   =   roomName;
    }
}
