
package com.kyleruss.jsockchat.server.core;

import com.kyleruss.jsockchat.commons.room.Room;

public final class RoomManager extends AbstractManager<String, Room>
{
    private static RoomManager instance;
    
    private RoomManager() {}
    
    public static RoomManager getInstance()
    {
        if(instance == null) instance = new RoomManager();
        return instance;
    }
}
