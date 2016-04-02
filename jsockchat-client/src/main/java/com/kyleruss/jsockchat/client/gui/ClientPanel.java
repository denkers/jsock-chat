
package com.kyleruss.jsockchat.client.gui;

import com.kyleruss.jsockchat.client.core.ClientConfig;
import com.kyleruss.jsockchat.client.core.ClientManager;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ClientPanel extends JPanel
{
    private static ClientPanel instance;
    private TransitionPanel transitionView;
    private ChatHomePanel homeView;
    private LoginPanel loginView;
    private ConnectPanel connectView;
    private RegisterPanel registerView;
    
    private ClientPanel()
    {
        setPreferredSize(new Dimension(ClientConfig.WINDOW_WIDTH, ClientConfig.WINDOW_HEIGHT));
        setLayout(new CardLayout());
        
        initViews();
        changeView(ClientConfig.CONNECT_VIEW_CARD);
    }
    
    private void initViews()
    {
        transitionView  =   new TransitionPanel();
        loginView       =   new LoginPanel();
        registerView    =   new RegisterPanel();
        homeView        =   new ChatHomePanel();
        connectView     =   new ConnectPanel();
        
        add(transitionView, ClientConfig.TRANSITION_VIEW_CARD);
        add(loginView, ClientConfig.LOGIN_VIEW_CARD);
        add(registerView, ClientConfig.REGISTER_VIEW_CARD);
        add(homeView, ClientConfig.HOME_VIEW_CARD);
        add(connectView, ClientConfig.CONNECT_VIEW_CARD);
    }
    
    public void changeView(String viewName)
    {
        CardLayout cLayout  =   (CardLayout) getLayout();
        cLayout.show(this, ClientConfig.TRANSITION_VIEW_CARD);
        
        Timer transitionTimer   =   new Timer(1500, (ActionEvent e) ->
        {
            cLayout.show(this, viewName);
        });
        
        transitionTimer.setRepeats(false);
        transitionTimer.start();
    }
    
    public void setMenuListener()
    {
        ClientMenuBar.getInstance().setListener(new ClientMenuListener());
    }

    public TransitionPanel getTransitionView() 
    {
        return transitionView;
    }

    public ChatHomePanel getHomeView() 
    {
        return homeView;
    }

    public LoginPanel getLoginView() 
    {
        return loginView;
    }

    public ConnectPanel getConnectView() 
    {
        return connectView;
    }

    public RegisterPanel getRegisterView()
    {
        return registerView;
    }
    
    
    
    public static void removeBorder(JButton button)
    {
        button.setBorder(null);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
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
            
            else if(src == menu.getItem("loginItem"))
                changeView(ClientConfig.LOGIN_VIEW_CARD);
            
            else if(src == menu.getItem("registerItem"))
                changeView(ClientConfig.REGISTER_VIEW_CARD);
            
            else if(src == menu.getItem("logoutItem"))
                ClientManager.getInstance().logoutUser();
        }
    }
}
