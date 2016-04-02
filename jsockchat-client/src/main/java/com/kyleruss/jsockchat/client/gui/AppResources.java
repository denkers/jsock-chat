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
    
    private AppResources()
    {
        initResources();
    }
    
    private void initResources()
    {
    }
    
    public BufferedImage getImageResource(String name) throws IOException 
    {
        return ImageIO.read(new File(ClientConfig.IMAGES_DIR + name));
    }
    
    public static AppResources getInstance()
    {
        if(instance == null) instance = new AppResources();
        return instance;
    }
}
