package com.kyleruss.jsockchat.commons.listbeans;

import com.kyleruss.jsockchat.commons.room.Room;

public class RoomListBean extends AbstractListBean<Room>
{
    public int getNumRoomUsers()
    {
        return listData.size();
    }
}
