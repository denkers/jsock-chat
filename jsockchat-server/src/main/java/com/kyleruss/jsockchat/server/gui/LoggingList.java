package com.kyleruss.jsockchat.server.gui;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class LoggingList extends JList
{
    private static LoggingList instance;
    private DefaultListModel model;
    
    private LoggingList()
    {
        model   =   new DefaultListModel();
        setModel(model);
        setSelectionModel(new NonSelectionModel());
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    private class NonSelectionModel extends DefaultListSelectionModel
    {
        @Override
        public void setSelectionInterval(int indexA, int indexB)
        {
            super.setSelectionInterval(-1, -1);
        }
    }
    
    public static LoggingList getInstance()
    {
        if(instance == null) instance = new LoggingList();
        return instance;
    }
}
