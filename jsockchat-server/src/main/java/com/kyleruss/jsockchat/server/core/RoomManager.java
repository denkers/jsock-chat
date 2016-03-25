
package com.kyleruss.jsockchat.server.core;

import com.kyleruss.jsockchat.commons.room.Room;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class RoomManager
{
    private static RoomManager instance;
    private final Map<String, Room> rooms;
    
    private RoomManager()
    {
        rooms   =   new HashMap<>();
    }
    
    public synchronized boolean addRoom(String roomName, Room room)
    {
        return rooms.put(roomName, room) != null;
    }
    
    public synchronized Room removeRoom(String roomName)
    {
        return rooms.remove(roomName);
    }
    
    public synchronized Room getRoom(String roomName)
    {
        return rooms.get(roomName);
    }
    
    public synchronized Collection<Room> getRooms()
    {
        return rooms.values();
    }
    
    public static RoomManager getInstance()
    {
        if(instance == null) instance = new RoomManager();
        return instance;
    }
}
