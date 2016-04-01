package com.kyleruss.jsockchat.commons.room;

import com.kyleruss.jsockchat.commons.user.IUser;
import java.util.List;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class RoomTree extends JTree
{
    protected List<Room> rooms;
    protected DefaultMutableTreeNode rootNode;
    protected DefaultTreeModel treeModel;
    
    public RoomTree(List<Room> rooms, String serverTitle)
    {
        this.rooms  =   rooms;
        sortRooms();
        
        rootNode    =   new DefaultMutableTreeNode(serverTitle);
        treeModel   =   new DefaultTreeModel(rootNode);
        setModel(treeModel);
        initRooms();
    }
    
    private void initRooms()
    {
        for(Room room : rooms)
            addRoom(room);
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
}
