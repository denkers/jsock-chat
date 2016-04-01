package com.kyleruss.jsockchat.client.gui;

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
    
    public static AppResources getInstance()
    {
        if(instance == null) instance = new AppResources();
        return instance;
    }
}
