package com.kyleruss.jsockchat.server.gui;

import com.kyleruss.jsockchat.server.core.ServerConfig;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

public class ServerPanel extends JPanel
{
    private final LoggingList loggingList;
    private final ServerStatusPanel statusPanel;
    private final JScrollPane loggingScrollPane;
    private final JPanel leftTreePanel, rightServerPanel;
    private final JSplitPane treeServerSplit;
    
    public ServerPanel()
    {
        setPreferredSize(new Dimension(ServerConfig.WINDOW_WIDTH, ServerConfig.WINDOW_HEIGHT));
        setLayout(new BorderLayout());
        
        loggingList         =   LoggingList.getInstance();
        statusPanel         =   ServerStatusPanel.getInstance();
        loggingScrollPane   =   new JScrollPane(loggingList);
        leftTreePanel       =   new JPanel();
        rightServerPanel    =   new JPanel(new BorderLayout());
        treeServerSplit     =   new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        
        leftTreePanel.setPreferredSize(new Dimension(350, 0));
        rightServerPanel.add(statusPanel, BorderLayout.NORTH);
        rightServerPanel.add(loggingScrollPane, BorderLayout.CENTER);
        treeServerSplit.setLeftComponent(leftTreePanel);
        treeServerSplit.setRightComponent(rightServerPanel);
        
        add(treeServerSplit);
    }
}
