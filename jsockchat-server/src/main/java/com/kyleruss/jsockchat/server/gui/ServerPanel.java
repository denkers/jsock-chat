package com.kyleruss.jsockchat.server.gui;

import com.kyleruss.jsockchat.server.core.ServerConfig;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

public class ServerPanel extends JPanel
{
    private final LoggingList loggingList;
    private final ServerStatusPanel statusPanel;
    
    public ServerPanel()
    {
        setPreferredSize(new Dimension(ServerConfig.WINDOW_WIDTH, ServerConfig.WINDOW_HEIGHT));
        setLayout(new BorderLayout());
        
        loggingList =   LoggingList.getInstance();
        statusPanel =   ServerStatusPanel.getInstance();
        
        add(statusPanel, BorderLayout.NORTH);
        add(loggingList, BorderLayout.CENTER);
    }
}
