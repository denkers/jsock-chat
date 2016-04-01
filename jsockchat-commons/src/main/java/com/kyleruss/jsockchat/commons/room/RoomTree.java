package com.kyleruss.jsockchat.commons.room;

import com.kyleruss.jsockchat.commons.user.IUser;
import java.awt.Component;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

public class RoomTree extends JTree
{
    protected List<Room> rooms;
    protected DefaultMutableTreeNode rootNode;
    protected DefaultTreeModel treeModel;
    protected ImageIcon serverIcon, userIcon, roomIcon, lockedRoomIcon; 

    
    public RoomTree(List<Room> rooms, String serverTitle)
    {
        this.rooms  =   rooms;
        sortRooms();
        
        rootNode    =   new DefaultMutableTreeNode(serverTitle);
        treeModel   =   new DefaultTreeModel(rootNode);
        
        setCellRenderer(new RoomTreeCellRenderer());
        setModel(treeModel);
        initRooms();
    }
    
    private void initRooms()
    {
        for(Room room : rooms)
            addRoom(room);
        
        expandRooms();
    }
    
    public void expandRooms()
    {
        for(int i = 0; i < getRowCount(); i++)
            expandRow(i);
    }
    
    public void setServerTitle(String title)
    {
        rootNode.setUserObject(title);
    }
    
    public void addRoom(Room room)
    {
        DefaultMutableTreeNode roomNode =   new DefaultMutableTreeNode(room);
        List<IUser> roomUsers           =   room.getUserList();
        for(IUser user : roomUsers)
            roomNode.add(new DefaultMutableTreeNode(user));
        
        rootNode.add(roomNode);
    }
    
    public void setRooms(List<Room> rooms)
    {
        this.rooms  =   rooms;
    }
    
    private void sortRooms()
    {
        if(rooms != null && rooms.size() > 1)
            rooms.sort((Room a, Room b) -> a.getRoomName().compareToIgnoreCase(b.getRoomName()));
    }
    
    public void setRootNode(DefaultMutableTreeNode rootNode) 
    {
        this.rootNode = rootNode;
    }

    public void setTreeModel(DefaultTreeModel treeModel) 
    {
        this.treeModel = treeModel;
    }

    public void setServerIcon(ImageIcon serverIcon)
    {
        this.serverIcon = serverIcon;
    }

    public void setUserIcon(ImageIcon userIcon)
    {
        this.userIcon = userIcon;
    }

    public void setRoomIcon(ImageIcon roomIcon) 
    {
        this.roomIcon = roomIcon;
    }

    public void setLockedRoomIcon(ImageIcon lockedRoomIcon)
    {
        this.lockedRoomIcon = lockedRoomIcon;
    }

    private class RoomTreeCellRenderer extends DefaultTreeCellRenderer
    {

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus)
        {
            super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
            DefaultMutableTreeNode node =   (DefaultMutableTreeNode) value;
            
            if(node.isRoot() && serverIcon != null)
                setIcon(serverIcon);
            
            else
            {
                Object nodeObj  =   node.getUserObject();
                
                if(nodeObj instanceof Room)
                {
                    Room roomObj    =   (Room) nodeObj;
                    if(roomObj.isPassProtected() && lockedRoomIcon != null)
                        setIcon(lockedRoomIcon);
                    
                    else if(!roomObj.isPassProtected() && roomIcon != null)
                        setIcon(roomIcon);
                }
                
                else if(nodeObj instanceof IUser && userIcon != null)
                    setIcon(userIcon);
            }
            
            return this;
        }
    }
}
