package com.kyleruss.jsockchat.client.core;

import com.kyleruss.jsockchat.client.gui.ClientPanel;
import javax.swing.JFrame;

public class GUIManager
{
    private static GUIManager instance;
    private JFrame frame;
    private ClientPanel panel;
    
    private GUIManager()
    {
        initFrame();
    }
    
    private void initFrame()
    {
        frame   =   new JFrame(ClientConfig.WINDOW_TITLE);
        panel   =   ClientPanel.getInstance();
        
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
    
    public void display()
    {
        frame.setVisible(true);
    }   
    
    public static GUIManager getInstance()
    {
        if(instance == null) instance = new GUIManager();
        return instance;
    }
    
    public static void main(String[] args)
    {
        GUIManager gui  =  getInstance();
        gui.display();
    }
}

