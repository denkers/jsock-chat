
package com.kyleruss.jsockchat.commons.message;

import java.io.Serializable;
import java.util.Date;

public interface Message extends Serializable
{
    public Date getTimeSent();
    
    public String getDescription();
}
