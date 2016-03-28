
package com.kyleruss.jsockchat.client.io;

import com.kyleruss.jsockchat.client.listbean.ClientFriendListBean;
import com.kyleruss.jsockchat.client.listbean.ClientListBean;
import com.kyleruss.jsockchat.client.listbean.ClientRoomListBean;
import com.kyleruss.jsockchat.client.listbean.ClientUserRoomListBean;
import com.kyleruss.jsockchat.commons.listbean.FriendListBean;
import com.kyleruss.jsockchat.commons.listbean.RoomListBean;
import com.kyleruss.jsockchat.commons.listbean.UserRoomListBean;
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
    
    private ClientListBean getClientBean(Object beanObj)
    {
        if(beanObj instanceof FriendListBean)
            return new ClientFriendListBean((FriendListBean) beanObj);
        
        else if(beanObj instanceof RoomListBean)
            return new ClientRoomListBean((RoomListBean) beanObj);
        
        else if(beanObj instanceof UserRoomListBean)
            return new ClientUserRoomListBean((UserRoomListBean) beanObj);
        
        else return null;
    }
    
    @Override
    public void run()
    {
        try
        {
            while(socket != null && !socket.isClosed())
            {
                byte[] buffer               =   new byte[4096];
                DatagramPacket packet       =   new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                ByteArrayInputStream bais   =   new ByteArrayInputStream(buffer);   
                ObjectInputStream ois       =   new ObjectInputStream(bais);
                Object beanObj              =   ois.readObject();
                ClientListBean bean         =   getClientBean(beanObj);

                if(bean != null) bean.beanAction();

            } 
        }
        
        catch(IOException | ClassNotFoundException e)
        {
            System.out.println("[ListUpdateListener@run STOP]: " + e.getMessage());
        }
    }
    
    public static ListUpdateListener getInstance(DatagramSocket socket)
    {
        if(instance == null) instance = new ListUpdateListener(socket);
        return instance;
    }
}
