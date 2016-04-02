package com.kyleruss.jsockchat.client.gui;

import com.kyleruss.jsockchat.client.core.ClientConfig;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TransitionView extends JPanel
{
    private final JLabel spinnerLabel;
    
    public TransitionView()
    {
        super(new BorderLayout());
        setBackground(Color.WHITE);
        spinnerLabel    =   new JLabel(new ImageIcon(ClientConfig.IMAGES_DIR + "loadspinner.gif"));
        add(spinnerLabel);
    }
}
