package com.kyleruss.jsockchat.client.gui;

import com.kyleruss.jsockchat.commons.gui.MappedMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class ClientMenuBar extends MappedMenuBar
{
    private static ClientMenuBar instance;
    private final JMenu aboutMenu, fileMenu, accountMenu, roomMenu;

    private ClientMenuBar()
    {
        aboutMenu   =   new JMenu("About");
        fileMenu    =   new JMenu("File");
        accountMenu =   new JMenu("Account");
        roomMenu    =   new JMenu("Room");
        
        addItem("miniItem", new JMenuItem("Minimize"), fileMenu);
        addItem("exitItem", new JMenuItem("Exit"), fileMenu);
        addItem("loginItem", new JMenuItem("Login"), accountMenu);
        addItem("registerItem", new JMenuItem("Register"), accountMenu);
        addItem("logoutItem", new JMenuItem("Logout"), accountMenu);
        addItem("joinRoomItem", new JMenuItem("Join room"), roomMenu);
        addItem("leaveRoomItem", new JMenuItem("Leave room"), roomMenu);
        addItem("createRoomItem", new JMenuItem("Create room"), roomMenu);
        
        add(fileMenu);
        add(accountMenu);
        add(roomMenu);
        add(aboutMenu);
    }
    
    public JMenu getAboutMenu() 
    {
        return aboutMenu;
    }

    public JMenu getFileMenu() 
    {
        return fileMenu;
    }

    public JMenu getAccountMenu()
    {
        return accountMenu;
    }

    public JMenu getRoomMenu()
    {
        return roomMenu;
    }
    
    public static ClientMenuBar getInstance()
    {
        if(instance == null) instance = new ClientMenuBar();
        return instance;
    }
}
