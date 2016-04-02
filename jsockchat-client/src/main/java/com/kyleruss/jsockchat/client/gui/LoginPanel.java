package com.kyleruss.jsockchat.client.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.prompt.PromptSupport;

public class LoginPanel extends JPanel implements ActionListener
{
    protected JTextField usernameField;
    protected JPasswordField passwordField;
    protected JButton loginButton;
    protected JPanel fieldPanel;
    protected JLabel titleLabel;
    protected Border fieldBorder;
    
    public LoginPanel()
    {
        setBackground(Color.WHITE);
        
        fieldPanel      =   new JPanel(new MigLayout());
        passwordField   =   new JPasswordField();
        usernameField   =   new JTextField();
        loginButton     =   new JButton(new ImageIcon(AppResources.getInstance().getLoginImage()));
        
        fieldBorder     =   BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY);
        fieldPanel.setBorder(fieldBorder);
        usernameField.setBorder(fieldBorder);
        passwordField.setBorder(fieldBorder);
        fieldPanel.setPreferredSize(new Dimension(300, 180));
        PromptSupport.setPrompt(" Enter existing username", usernameField);
        PromptSupport.setPrompt(" Enter account password", passwordField);
        
        ClientPanel.removeBorder(loginButton);
        titleLabel   =   new JLabel("Sign in");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setIcon(new ImageIcon(AppResources.getInstance().getLockImage()));
        
        usernameField.setPreferredSize(new Dimension(200, 28));
        passwordField.setPreferredSize(new Dimension(200, 28));
        fieldPanel.setBackground(Color.WHITE);
        fieldPanel.add(titleLabel, "wrap");
        fieldPanel.add(new JLabel("Username"));
        fieldPanel.add(usernameField, "wrap");
        fieldPanel.add(new JLabel("Password"));
        fieldPanel.add(passwordField, "wrap");
        fieldPanel.add(loginButton, "span 2, al center, gapy 15");
        
        
        JPanel fieldWrapperPanel    =   new JPanel();
        fieldWrapperPanel.add(fieldPanel);
        fieldWrapperPanel.setBackground(Color.WHITE);
        fieldWrapperPanel.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 0));
        
        add(fieldWrapperPanel);
        
    }
    
    public JTextField getUsernameField() 
    {
        return usernameField;
    }

    public JPasswordField getPasswordField() 
    {
        return passwordField;
    }
    
    private void login()
    {
        
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object src  =   e.getSource();
       
        if(src == loginButton)
            login();
    }
}
