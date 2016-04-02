
package com.kyleruss.jsockchat.client.gui;

import com.kyleruss.jsockchat.client.core.ClientConfig;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ClientPanel extends JPanel
{
    private static ClientPanel instance;
    private TransitionView transitionView;
    
    private ClientPanel()
    {
        setPreferredSize(new Dimension(ClientConfig.WINDOW_WIDTH, ClientConfig.WINDOW_HEIGHT));
        setLayout(new CardLayout());
        
        transitionView  =   new TransitionView();
        add(transitionView, ClientConfig.TRANSITION_VIEW_CARD);
        changeView(ClientConfig.TRANSITION_VIEW_CARD);
    }
    
    public void changeView(String viewName)
    {
        CardLayout cLayout  =   (CardLayout) getLayout();
        cLayout.show(this, viewName);
    }
    
    public void setMenuListener()
    {
        ClientMenuBar.getInstance().setListener(new ClientMenuListener());
    }
    
    public static ClientPanel getInstance()
    {
        if(instance == null) instance = new ClientPanel();
        return instance;
    }
    
    private class ClientMenuListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            Object src          =   e.getSource();
            ClientMenuBar menu  =   ClientMenuBar.getInstance();
            
            if(src == menu.getItem("authorItem"))
                JOptionPane.showMessageDialog(null, "Kyle Russell\nAUT University\nwww.github.com/denkers");
        }
    }
}
