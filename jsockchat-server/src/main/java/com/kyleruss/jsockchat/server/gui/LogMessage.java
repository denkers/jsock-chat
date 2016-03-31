package com.kyleruss.jsockchat.server.gui;

import javax.swing.Icon;

public class LogMessage
{
    private final Icon messageIcon;
    private final String message;
    
    public LogMessage(String message, Icon messageIcon)
    {
        this.message        =   message;
        this.messageIcon    =   messageIcon;
    }
    
    public Icon getMessageIcon()
    {
        return messageIcon;
    }
    
    public String getMessage()
    {
        return message;
    }
    
    @Override
    public String toString()
    {
        return message;
    }
}
