
package com.kyleruss.jsockchat.server.core;


public final class RoomManager
{
    private static RoomManger instance;
    
    private RoomManager()
    {
        
    }
    
    public static RoomManager getInstance()
    {
        if(instance == null) instance = new RoomManager();
        return instance;
    }
}
