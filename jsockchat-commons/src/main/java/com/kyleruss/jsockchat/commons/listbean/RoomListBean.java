package com.kyleruss.jsockchat.commons.listbean;

import com.kyleruss.jsockchat.commons.room.Room;

public class RoomListBean extends AbstractListBean<Room>
{
    private String channelNotice;
    
    public String getChannelNotice()
    {
        return channelNotice;
    }
    
    public void setChannelNotice(String channelNotice)
    {
        this.channelNotice  =   channelNotice;
    }
}
