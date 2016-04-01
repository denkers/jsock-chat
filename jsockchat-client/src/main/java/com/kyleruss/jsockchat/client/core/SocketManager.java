
package com.kyleruss.jsockchat.client.core;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramSocket;
import java.net.Socket;


public class SocketManager 
{
    private static SocketManager instance;
    private Socket tcpSocket;
    private DatagramSocket udpSocket;
    private ObjectOutputStream tcpOutputStream;

    private SocketManager()
    {
        initSockets();
    }
    
    private void initSockets()
    {
        try
        {
            tcpSocket   =   new Socket(ClientConfig.MSG_SERVER_HOST, ClientConfig.MSG_SERVER_PORT);
            udpSocket   =   new DatagramSocket();
        }
        
        catch(IOException e)
        {
            System.out.println("[SocketManager@initSocket]: " + e.getMessage());
        }
    }
    
    public int getUdpPort()
    {
        if(udpSocket == null) return -1;
        return udpSocket.getLocalPort();
    }
    
    public ObjectOutputStream getTCPOutputStream() throws IOException
    {
        if(tcpSocket == null) return null;
        
        if(tcpOutputStream == null) tcpOutputStream = new ObjectOutputStream(tcpSocket.getOutputStream());
        return tcpOutputStream;
    }
    
    public void cleanUp()
    {
        try
        {
            if(tcpOutputStream != null)
            {
                tcpOutputStream.flush();
                tcpOutputStream.close();
            }

            if(tcpSocket != null) tcpSocket.close();
            if(udpSocket != null) udpSocket.close();
        }
        
        catch(IOException e)
        {
            System.out.println("[SocketManager@cleanUp]: " + e.getMessage());
        }
    }
    
    public Socket getTcpSocket()
    {
        return tcpSocket;
    }
    
    public DatagramSocket getUdpSocket()
    {
        return udpSocket;
    }
    
    public static SocketManager getInstance()
    {
        if(instance == null) instance = new SocketManager();
        return instance;
    }
    
}
