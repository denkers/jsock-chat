
package com.kyleruss.jsockchat.client.io;

import java.net.DatagramSocket;

public class ListUpdateListener extends Thread
{
    private final DatagramSocket socket;
    private static ListUpdateListener instance;
    
    public ListUpdateListener(DatagramSocket socket)
    {
        this.socket =   socket;
    }
    
    @Override
    public void run()
    {
        
    }
    
    public static ListUpdateListener getInstance(DatagramSocket socket)
    {
        if(instance == null) instance = new ListUpdateListener(socket);
        return instance;
    }
}
