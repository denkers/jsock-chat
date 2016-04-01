package com.kyleruss.jsockchat.server.core;

import com.kyleruss.jsockchat.server.gui.ServerMenuBar;
import com.kyleruss.jsockchat.server.gui.ServerPanel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GUIManager 
{
    private static GUIManager instance;
    private JFrame frame;
    private ServerPanel panel;
    
    private GUIManager()
    {
        initLookAndFeel();
        initFrame();
        attachMenubar();
    }
    
    private void initFrame()
    {
        frame   =   new JFrame(ServerConfig.WINDOW_TITLE);
        panel   =   ServerPanel.getInstance();
        
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }
    
    private void initLookAndFeel()
    {
        try
        {
            UIManager.setLookAndFeel(ServerConfig.LOOKNFEEL_PACKAGE);
        } 
        
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
        {
            JOptionPane.showMessageDialog(null, "[Error] Failed to initialize application look and feel ");
        }
    }

    public void display()
    {
        frame.setVisible(true);
    }
    
    private void attachMenubar()
    {
        frame.setJMenuBar(ServerMenuBar.getInstance());
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
    
    public static void main(String[] args)
    {
        GUIManager gui  =   getInstance();
        gui.display();
    }
}
