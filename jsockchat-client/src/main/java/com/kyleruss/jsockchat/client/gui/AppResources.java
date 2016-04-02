package com.kyleruss.jsockchat.client.gui;

import com.kyleruss.jsockchat.client.core.ClientConfig;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class AppResources
{
    private static AppResources instance;
    private BufferedImage registerImage, loginImage, addPersonImage, lockImage;
    private BufferedImage connectImage, serverImage;

    private AppResources()
    {
        initResources();
    }
    
    private void initResources()
    {
        try
        {
            registerImage   =   getImageResource("registerbutton.png");
            loginImage      =   getImageResource("loginbutton.png");
            addPersonImage  =   getImageResource("add_friend.png");
            lockImage       =   getImageResource("lock.png");
            connectImage    =   getImageResource("connectbutton.png");
            serverImage     =   getImageResource("internet_icon.png");
        }
        
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "[Error] Failed to load resources");
        }
    }
    
    public BufferedImage getImageResource(String name) throws IOException 
    {
        return ImageIO.read(new File(ClientConfig.IMAGES_DIR + name));
    }
    
    public BufferedImage getRegisterImage() 
    {
        return registerImage;
    }

    public BufferedImage getLoginImage() 
    {
        return loginImage;
    }

    public BufferedImage getAddPersonImage() 
    {
        return addPersonImage;
    }

    public BufferedImage getLockImage() 
    {
        return lockImage;
    }

    public BufferedImage getConnectImage() 
    {
        return connectImage;
    }

    public BufferedImage getServerImage() 
    {
        return serverImage;
    }
    
    
    public static AppResources getInstance()
    {
        if(instance == null) instance = new AppResources();
        return instance;
    }
}
