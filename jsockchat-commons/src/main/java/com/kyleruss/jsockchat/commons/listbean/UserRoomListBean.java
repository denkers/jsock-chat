
package com.kyleruss.jsockchat.commons.listbean;

import com.kyleruss.jsockchat.commons.user.IUser;

public class UserRoomListBean extends AbstractListBean<IUser>
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
