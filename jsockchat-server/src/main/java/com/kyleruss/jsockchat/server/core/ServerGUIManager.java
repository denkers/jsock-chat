package com.kyleruss.jsockchat.server.core;

import com.kyleruss.jsockchat.commons.gui.GUIManager;
import com.kyleruss.jsockchat.server.gui.ServerMenuBar;
import com.kyleruss.jsockchat.server.gui.ServerPanel;

public class ServerGUIManager extends GUIManager
{
    private static ServerGUIManager instance;
    
    private ServerGUIManager()
    {
        panel   =   ServerPanel.getInstance();
        initLookAndFeel(ServerConfig.LOOKNFEEL_PACKAGE);
        initFrame(ServerConfig.WINDOW_TITLE);
        attachMenubar(ServerMenuBar.getInstance());
    }
    
    public static ServerGUIManager getInstance()
    {
        if(instance == null) instance = new ServerGUIManager();
        return instance;
    }
    
    public static void main(String[] args)
    {
        ServerGUIManager gui  =   getInstance();
        gui.display();
    }
}
