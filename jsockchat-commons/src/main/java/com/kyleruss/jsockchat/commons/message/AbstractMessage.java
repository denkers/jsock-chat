
package com.kyleruss.jsockchat.commons.message;

import java.util.Date;

public abstract class AbstractMessage implements Message
{
    private Date timeSent;
    private String description;
    
    public AbstractMessage()
    {
        timeSent    =   new Date();
        description =   "";
    }
    
    @Override
    public Date getTimeSent()
    {
        return timeSent;
    }
    
    public void setTimeSent(Date timeSent)
    {
        this.timeSent   =   timeSent;
    }
    
    @Override
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description    =   description;
    }
}
