package com.kyleruss.jsockchat.server.gui;

import com.kyleruss.jsockchat.commons.room.Room;
import com.kyleruss.jsockchat.commons.room.RoomTree;
import com.kyleruss.jsockchat.commons.user.User;
import com.kyleruss.jsockchat.server.core.RoomManager;
import com.kyleruss.jsockchat.server.core.ServerConfig;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

public class ServerPanel extends JPanel
{
    private final LoggingList loggingList;
    private final ServerStatusPanel statusPanel;
    private final JScrollPane loggingScrollPane;
    private final JPanel leftPanel, rightPanel;
    private final JSplitPane treeServerSplit;
    private final JPanel userPanel;
    private final RoomTree roomTree;
    
    public ServerPanel()
    {
        setPreferredSize(new Dimension(ServerConfig.WINDOW_WIDTH, ServerConfig.WINDOW_HEIGHT));
        setLayout(new BorderLayout());
        
        loggingList         =   LoggingList.getInstance();
        statusPanel         =   ServerStatusPanel.getInstance();
        loggingScrollPane   =   new JScrollPane(loggingList);
        leftPanel           =   new JPanel(new GridLayout(2, 1));
        rightPanel          =   new JPanel(new BorderLayout());
        treeServerSplit     =   new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        userPanel           =   new JPanel();
        roomTree            =   new RoomTree(new ArrayList<>(RoomManager.getInstance().getDataValues()), "JSockchat Server");
        
        AppResources resources  =   AppResources.getInstance();
        roomTree.setServerIcon(new ImageIcon(resources.getServerImage()));
        roomTree.setRoomIcon(new ImageIcon(resources.getChatImage()));
        roomTree.setLockedRoomIcon(new ImageIcon(resources.getLockedChatImage()));
        roomTree.setUserIcon(new ImageIcon(resources.getUserImage()));
        
        JPanel treeWrapper          =   new JPanel(new BorderLayout());
        JPanel leftContentWrapper   =   new JPanel(new BorderLayout());
        
        treeWrapper.setBorder(BorderFactory.createTitledBorder("Rooms"));
        userPanel.setBorder(BorderFactory.createTitledBorder("Online users"));
        leftContentWrapper.setBorder(BorderFactory.createEmptyBorder(8, 3, 3, 3));
        leftContentWrapper.setPreferredSize(new Dimension(350, 0));
        
        treeWrapper.add(roomTree);
        leftPanel.add(treeWrapper);
        leftPanel.add(userPanel);
        leftContentWrapper.add(leftPanel);
        
        rightPanel.add(statusPanel, BorderLayout.NORTH);
        rightPanel.add(loggingScrollPane, BorderLayout.CENTER);
        treeServerSplit.setLeftComponent(leftContentWrapper);
        treeServerSplit.setRightComponent(rightPanel);
        
        add(treeServerSplit);
    }
}
