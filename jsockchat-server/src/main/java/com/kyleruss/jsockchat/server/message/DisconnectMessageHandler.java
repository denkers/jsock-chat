
package com.kyleruss.jsockchat.server.message;

import com.kyleruss.jsockchat.commons.message.DisconnectMsgBean;
import com.kyleruss.jsockchat.commons.message.RequestMessage;
import com.kyleruss.jsockchat.commons.message.ResponseMessage;
import com.kyleruss.jsockchat.commons.user.IUser;
import com.kyleruss.jsockchat.server.core.RoomManager;
import com.kyleruss.jsockchat.server.core.UserManager;
import java.util.List;

public class DisconnectMessageHandler implements ServerMessageHandler
{
    @Override
    public void serverAction(RequestMessage request) 
    {
        String source               =   request.getUserSource();
        DisconnectMsgBean bean      =   (DisconnectMsgBean) request.getMessageBean();
        ResponseMessage response    =   new ResponseMessage(request);
        IUser user                  =   UserManager.getInstance().get(source);
        RoomManager roomManager     =   RoomManager.getInstance();
        
        response.setDescription(source + " (" + user.getDisplayName() + ") has left the room");
        response.setStatus(true);
        
        if(!bean.isClientDisconnect())
        {
            String roomName   =   bean.getRoom();
            user.getCurrentRooms().remove(roomName);
            roomManager.get(roomName).leaveRoom(user);
            roomManager.sendMessageToRoom(roomName, response);
        }
        
        else
        {
            roomManager.leaveAllRooms(user);
            
            List<String> rooms  =   user.getCurrentRooms();
            for(String roomName : rooms)
                roomManager.sendMessageToRoom(roomName, response);

            UserManager.getInstance().clientExit(user);
        }
    }
}

