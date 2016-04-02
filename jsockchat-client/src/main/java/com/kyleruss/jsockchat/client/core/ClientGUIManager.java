package com.kyleruss.jsockchat.client.core;

import com.kyleruss.jsockchat.client.gui.ClientMenuBar;
import com.kyleruss.jsockchat.client.gui.ClientPanel;
import com.kyleruss.jsockchat.commons.gui.GUIManager;

public class ClientGUIManager extends GUIManager
{
    private static ClientGUIManager instance;
    
    private ClientGUIManager()
    {
        initLookAndFeel(ClientConfig.LOOKNFEEL_PACKAGE);
        panel   =   ClientPanel.getInstance();
        initFrame(ClientConfig.WINDOW_TITLE);
        attachMenubar(ClientMenuBar.getInstance());
        ClientPanel.getInstance().setMenuListener();
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

