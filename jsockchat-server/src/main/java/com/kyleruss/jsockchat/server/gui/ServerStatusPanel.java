package com.kyleruss.jsockchat.server.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class ServerStatusPanel extends JPanel
{
    private static ServerStatusPanel instance;
    private final JPanel msgServerPanel, updateServerPanel;
    private final JLabel msgServerLabel, updateServerLabel;
    
    private ServerStatusPanel()
    {
        setLayout(new GridLayout(1, 2));
        setPreferredSize(new Dimension(200, 65));
        
        msgServerPanel      =   new JPanel(new MigLayout("fillx"));
        updateServerPanel   =   new JPanel(new MigLayout("fillx"));
        msgServerLabel      =   new JLabel();
        updateServerLabel   =   new JLabel();
        
        JLabel msgServerTitleLabel      =   new JLabel("MESSAGE SERVER");
        JLabel updateServerTitleLabel   =   new JLabel("UPDATE SERVER");
        Font titleFont                  =   new Font("SansSerif", Font.BOLD, 14);
        Font statusFont                 =   new Font("SansSerif", Font.PLAIN, 12);
        msgServerTitleLabel.setFont(titleFont);
        updateServerTitleLabel.setFont(titleFont);
        msgServerLabel.setFont(statusFont);
        updateServerLabel.setFont(statusFont);
        updateServerLabel.setForeground(Color.WHITE);
        msgServerLabel.setForeground(Color.WHITE);
        msgServerTitleLabel.setForeground(Color.WHITE);
        updateServerTitleLabel.setForeground(Color.WHITE);
        msgServerTitleLabel.setIcon(new ImageIcon(AppResources.getInstance().getMsgServerImage()));
        updateServerTitleLabel.setIcon(new ImageIcon(AppResources.getInstance().getUpdateServerImage()));
        
        msgServerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.LIGHT_GRAY));
        updateServerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        
        msgServerPanel.add(msgServerTitleLabel, "al center, wrap");
        msgServerPanel.add(msgServerLabel, "al center");
        updateServerPanel.add(updateServerTitleLabel, "al center, wrap");
        updateServerPanel.add(updateServerLabel, "al center");
        
        add(msgServerPanel);
        add(updateServerPanel);
        
        setMessageServerStatus(false);
        setUpdateServerStatus(false);
    }
    
    public void setMessageServerStatus(boolean status)
    {
        msgServerPanel.setBackground(status? Color.GREEN : Color.RED);
        msgServerLabel.setText(status? "ONLINE" : "OFFLINE");
    }
    
    public void setUpdateServerStatus(boolean status)
    {
        updateServerPanel.setBackground(status? Color.GREEN : Color.RED);
        updateServerLabel.setText(status? "ONLINE" : "OFFLINE");
    }
    
    public static ServerStatusPanel getInstance()
    {
        if(instance == null) instance = new ServerStatusPanel();
        return instance;
    }
}
