
package com.kyleruss.jsockchat.client.gui;

import javax.swing.JPanel;

public class ClientPanel extends JPanel
{
    private static ClientPanel instance;
    
    private ClientPanel()
    {
        
    }
    
    public static ClientPanel getInstance()
    {
        if(instance == null) instance = new ClientPanel();
        return instance;
    }
}
