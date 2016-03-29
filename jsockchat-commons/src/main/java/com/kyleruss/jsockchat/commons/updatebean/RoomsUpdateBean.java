package com.kyleruss.jsockchat.commons.updatebean;

import com.kyleruss.jsockchat.commons.room.Room;

public class RoomsUpdateBean extends AbstractUpdateBean<Room>
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
