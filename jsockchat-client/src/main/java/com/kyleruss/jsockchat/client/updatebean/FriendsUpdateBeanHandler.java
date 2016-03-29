
package com.kyleruss.jsockchat.client.updatebean;

import com.kyleruss.jsockchat.client.core.UserManager;
import com.kyleruss.jsockchat.commons.updatebean.FriendsUpdateBean;


public class FriendsUpdateBeanHandler extends UpdateBeanHandler<FriendsUpdateBean>
{
    public FriendsUpdateBeanHandler(FriendsUpdateBean bean) 
    {
        super(bean);
    }
    
    @Override
    public void beanAction() 
    {
        UserManager userManager =   UserManager.getInstance();
        userManager.setFriendsBean(bean);
    }
}
