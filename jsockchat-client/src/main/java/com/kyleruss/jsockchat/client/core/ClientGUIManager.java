package com.kyleruss.jsockchat.client.core;

import com.kyleruss.jsockchat.client.gui.ClientMenuBar;
import com.kyleruss.jsockchat.client.gui.ClientPanel;
import com.kyleruss.jsockchat.commons.gui.GUIManager;

public class ClientGUIManager extends GUIManager
{
    private static ClientGUIManager instance;
    
    private ClientGUIManager()
    {
        panel   =   ClientPanel.getInstance();
        initLookAndFeel(ClientConfig.LOOKNFEEL_PACKAGE);
        initFrame(ClientConfig.WINDOW_TITLE);
        attachMenubar(ClientMenuBar.getInstance());
    }
    
    public static ClientGUIManager getInstance()
    {
        if(instance == null) instance = new ClientGUIManager();
        return instance;
    }
    
    public static void main(String[] args)
    {
        ClientGUIManager gui  =  getInstance();
        gui.display();
    }
}

