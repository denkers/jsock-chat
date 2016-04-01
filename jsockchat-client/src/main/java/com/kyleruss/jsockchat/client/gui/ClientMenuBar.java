package com.kyleruss.jsockchat.client.gui;

import javax.swing.JMenuBar;

public class ClientMenuBar extends JMenuBar
{
    private static ClientMenuBar instance;
    
    private ClientMenuBar()
    {
        
    }
    
    public static ClientMenuBar getInstance()
    {
        if(instance == null) instance = new ClientMenuBar();
        return instance;
    }
}
