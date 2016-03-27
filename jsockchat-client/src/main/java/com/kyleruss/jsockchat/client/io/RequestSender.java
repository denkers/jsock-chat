
package com.kyleruss.jsockchat.client.io;

import com.kyleruss.jsockchat.commons.message.RequestMessage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class RequestSender extends Thread
{
    private static RequestSender instance;
    private final Socket socket;
    private final Queue<RequestMessage> requestQueue;
    private ObjectOutputStream outputStream;
            
    private RequestSender(Socket socket)
    {
        requestQueue    =   new LinkedList<>();   
        this.socket     =   socket;
        initOutputStream();
    }
    
    private synchronized void getLock()
    {
        try
        {
            if(requestQueue.isEmpty())
                wait();
        }

        catch(InterruptedException e)
        {
            System.out.println("[RequestSender@getLock]: " + e.getMessage());
        }
    }
    
    private void initOutputStream()
    {
        try { outputStream   =   new ObjectOutputStream(socket.getOutputStream()); }
        catch(IOException e)
        {
            System.out.println("[RequestSender@initOuputStream]: " + e.getMessage());
        }
    }
    
    public synchronized void addRequest(RequestMessage request)
    {
        requestQueue.add(request);
        notify();
    }
    
    private void sendRequest(RequestMessage request) 
    { 
        try { outputStream.writeObject(request); }
        catch(IOException e)
        {
            System.out.println("[RequestSender@sendRequest]: " + e.getMessage());
        }
    }
    
    @Override
    public void run()
    {
        while(socket != null && !socket.isClosed())
        {
            getLock();
            RequestMessage nextRequest  =   requestQueue.poll();
            sendRequest(nextRequest);
        }
    }
    
    public static RequestSender getInstance(Socket socket)
    {
        if(instance == null) instance   =   new RequestSender(socket);
        return instance;
    }
}
