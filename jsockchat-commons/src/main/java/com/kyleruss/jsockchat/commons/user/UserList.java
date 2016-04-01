package com.kyleruss.jsockchat.commons.user;

import java.awt.Component;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class UserList extends JList
{
    private ImageIcon userIcon;
    
    public UserList()
    {
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setCellRenderer(new UserCellRenderer());
    }
    
    public void setUserIcon(ImageIcon userIcon)
    {
        this.userIcon   =   userIcon;
    }
    
    public void initUsers(List<IUser> users)
    {
        setListData(users.toArray());
    }
    
    protected class UserCellRenderer extends DefaultListCellRenderer
    {
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
        {
            JLabel component =   (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            component.setIcon(userIcon);
            
            return component;
        }
        
    }
}