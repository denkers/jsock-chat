
package com.kyleruss.jsockchat.commons.user;

import java.io.Serializable;
import java.net.Socket;
import java.util.List;

enum AccountLevel
{
    STANDARD,
    MODERATOR,
    BANNED
}

public interface User extends Serializable
{
    public String getUsername();
    
    public String getDisplayName();
    
    public Socket getClientSocket();
    
    public AccountLevel getAccountLevel();
    
    public void authenticate();
    
    public void executeCommand(String command);
    
    public List<String> getActiveRooms();
}
