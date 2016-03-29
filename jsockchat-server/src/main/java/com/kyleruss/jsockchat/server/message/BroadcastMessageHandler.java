package com.kyleruss.jsockchat.server.message;

import com.kyleruss.jsockchat.commons.message.BroadcastMsgBean;
import com.kyleruss.jsockchat.commons.message.RequestMessage;
import com.kyleruss.jsockchat.commons.message.ResponseMessage;
import com.kyleruss.jsockchat.server.core.RoomManager;

public class BroadcastMessageHandler implements ServerMessageHandler
{

    @Override
    public void serverAction(RequestMessage request)
    {
        BroadcastMsgBean bean       =   (BroadcastMsgBean) request.getMessageBean();
        String source               =   request.getUserSource();
        String room                 =   bean.getRoom();
        ResponseMessage response    =   new ResponseMessage(request);
        response.setStatus(true);
        RoomManager.getInstance().sendMessageToRoom(room, response);
    }
}
