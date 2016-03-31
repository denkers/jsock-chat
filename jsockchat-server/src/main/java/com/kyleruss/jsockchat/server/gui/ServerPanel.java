package com.kyleruss.jsockchat.server.gui;

import com.kyleruss.jsockchat.server.core.ServerConfig;
import java.awt.Dimension;
import javax.swing.JPanel;

public class ServerPanel extends JPanel
{
    public ServerPanel()
    {
        setPreferredSize(new Dimension(ServerConfig.WINDOW_WIDTH, ServerConfig.WINDOW_HEIGHT));
    }
}
