
package com.kyleruss.jsockchat.client.gui;

import com.kyleruss.jsockchat.client.core.ClientConfig;
import java.awt.Dimension;
import javax.swing.JPanel;

public class ClientPanel extends JPanel
{
    private static ClientPanel instance;
    
    private ClientPanel()
    {
        setPreferredSize(new Dimension(ClientConfig.WINDOW_WIDTH, ClientConfig.WINDOW_HEIGHT));
    }
    
    public static ClientPanel getInstance()
    {
        if(instance == null) instance = new ClientPanel();
        return instance;
    }
}
