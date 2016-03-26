
package com.kyleruss.jsockchat.client.io;

import com.kyleruss.jsockchat.commons.message.RequestMessage;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class RequestSender extends Thread
{
    private static RequestSender instance;
    private final Socket socket;
    private final Queue<RequestMessage> requestQueue;
            
    private RequestSender(Socket socket)
    {
        this.socket     =   socket;
        requestQueue    =   new LinkedList<>();   
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
    
    public synchronized void addRequest(RequestMessage request)
    {
        requestQueue.add(request);
        notify();
    }
    
    @Override
    public void run()
    {
        while(socket != null && !socket.isClosed())
        {
            getLock();
            RequestMessage nextRequest  =   requestQueue.poll();
        }
    }
    
    public static RequestSender getInstance(Socket socket)
    {
        if(instance == null) instance   =   new RequestSender(socket);
        return instance;
    }
}
