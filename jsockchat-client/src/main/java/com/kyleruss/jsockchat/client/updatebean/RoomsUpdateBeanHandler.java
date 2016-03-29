
package com.kyleruss.jsockchat.client.updatebean;

import com.kyleruss.jsockchat.client.core.RoomManager;
import com.kyleruss.jsockchat.commons.updatebean.RoomsUpdateBean;

public class RoomsUpdateBeanHandler extends UpdateBeanHandler<RoomsUpdateBean>
{
    public RoomsUpdateBeanHandler(RoomsUpdateBean bean) 
    {
        super(bean);
    }

    @Override
    public void beanAction() 
    {
        RoomManager roomManager =   RoomManager.getInstance();
        roomManager.setRoomsBean(bean);
    }
}
