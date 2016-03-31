package com.kyleruss.jsockchat.server.core;

import com.kyleruss.jsockchat.server.gui.ServerPanel;
import javax.swing.JFrame;

public class GUIManager 
{
    private static GUIManager instance;
    private JFrame frame;
    private ServerPanel panel;
    
    private GUIManager()
    {
        initFrame();
    }
    
    private void initFrame()
    {
        frame   =   new JFrame(ServerConfig.WINDOW_TITLE);
        panel   =   new ServerPanel();
        
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public void display()
    {
        frame.setVisible(true);
    }
    
    public JFrame getFrame()
    {
        return frame;
    }
    
    public ServerPanel getPanel()
    {
        return panel;
    }
    
    public static GUIManager getInstance()
    {
        if(instance == null) instance = new GUIManager();
        return instance;
    }
}
