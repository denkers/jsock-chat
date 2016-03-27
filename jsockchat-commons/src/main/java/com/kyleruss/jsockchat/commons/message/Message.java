
package com.kyleruss.jsockchat.commons.message;

import java.io.Serializable;
import java.util.Date;

public interface Message extends Serializable
{
    public Date getTimeSent();
    
    public void setTimeSent(Date timeSent);
    
    public String getDescription();
    
    public void setDescription(String description);
}
