
package com.kyleruss.jsockchat.server.core;

import com.kyleruss.jsockchat.commons.room.Room;
import java.util.ArrayList;
import java.util.List;

public final class RoomManager extends AbstractManager<String, Room>
{
    private static RoomManager instance;
    
    private RoomManager() {}
    
    public List<String> getUsersInRoom(String roomName)
    {
        Room room           =   get(roomName);
        
        if(room != null) return room.getUserList();
        else return new ArrayList<>();
    }
    
    public static RoomManager getInstance()
    {
        if(instance == null) instance = new RoomManager();
        return instance;
    }
}
