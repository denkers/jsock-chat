package com.kyleruss.jsockchat.client.gui;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.jdesktop.swingx.prompt.PromptSupport;

public class RegisterPanel extends LoginPanel
{
    private JTextField displaynameField;
    
    public RegisterPanel()
    {
        fieldPanel.setPreferredSize(new Dimension(350, 200));
        fieldPanel.remove(submitButton);
        titleLabel.setText("REGISTER");
        titleLabel.setIcon(new ImageIcon(AppResources.getInstance().getAddPersonImage()));
        
        displaynameField    =   new JTextField();
        
        submitButton.setIcon(new ImageIcon(AppResources.getInstance().getRegisterImage()));
        PromptSupport.setPrompt(" Your name in chat", displaynameField);
        PromptSupport.setPrompt(" A unique account name", usernameField);
        
        displaynameField.setBorder(fieldBorder);
        displaynameField.setPreferredSize(usernameField.getPreferredSize());
        fieldPanel.add(new JLabel("Name"));
        fieldPanel.add(displaynameField, "wrap");
        fieldPanel.add(processingPanel, "span 2, al center, gapy 20, gapx 50");
    }
}
