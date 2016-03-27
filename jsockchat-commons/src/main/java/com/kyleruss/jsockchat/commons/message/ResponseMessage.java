
package com.kyleruss.jsockchat.commons.message;

public interface ResponseMessage extends Message
{
    public RequestMessage getRequestMessage();
    
    public String getResponseDescription();
    
    public Object getResponseData();
}
