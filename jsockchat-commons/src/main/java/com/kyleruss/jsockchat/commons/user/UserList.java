package com.kyleruss.jsockchat.commons.user;

import java.awt.Component;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingUtilities;

public class UserList extends JList
{
    private ImageIcon userIcon;
    
    public UserList()
    {
        setCellRenderer(new UserCellRenderer());
    }
    
    public void setUserIcon(ImageIcon userIcon)
    {
        this.userIcon   =   userIcon;
    }
    
    public void initUsers(List<IUser> users)
    {
        SwingUtilities.invokeLater(()->
        {
            setListData(users.toArray());
        });
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
