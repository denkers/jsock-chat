
package com.kyleruss.jsockchat.commons.message;

public abstract class AbstractMessage implements Message
{
    private String source;
    private String content;
    
    @Override
    public String getSource()
    {
        return source;
    }
    
    @Override
    public String getContent()
    {
        return content;
    }
    
    @Override
    public abstract void action();
}
