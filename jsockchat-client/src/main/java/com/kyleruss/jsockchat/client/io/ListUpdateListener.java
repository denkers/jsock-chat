
package com.kyleruss.jsockchat.client.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
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
        while(socket != null && !socket.isClosed())
        {
            try
            {
                byte[] buffer               =   new byte[2048];
                DatagramPacket packet       =   new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                ByteArrayInputStream bais   =   new ByteArrayInputStream(buffer);   
                ObjectInputStream ois       =   new ObjectInputStream(bais);
                Object obj                  =   ois.readObject();
            }
            
            catch(IOException | ClassNotFoundException e)
            {
                System.out.println("[ListUpdateListener@run]: " + e.getMessage());
            }
        }   
    }
    
    public static ListUpdateListener getInstance(DatagramSocket socket)
    {
        if(instance == null) instance = new ListUpdateListener(socket);
        return instance;
    }
}
