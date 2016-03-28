
package com.kyleruss.jsockchat.commons.message;

public class PrivateMessage extends BroadcastMessage 
{
    private String destinationUser;
    
    public PrivateMessage(String source, String destinationUser, String content, String room)
    {
        super(room, content);
        this.destinationUser    =   destinationUser;
    }

    public String getDestinationUser() 
    {
        return destinationUser;
    }

    public void setDestinationUser(String destinationUser)
    {
        this.destinationUser    =   destinationUser;
    }
}
