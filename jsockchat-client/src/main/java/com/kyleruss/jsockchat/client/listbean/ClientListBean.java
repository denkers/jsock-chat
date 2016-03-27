
package com.kyleruss.jsockchat.client.listbean;

import com.kyleruss.jsockchat.commons.listbean.ListBean;

public abstract class ClientListBean<T extends ListBean>
{
    private final T bean;
    
    public ClientListBean(T bean)
    {
        this.bean   =   bean;
    }
    
    public T getBean()
    {
        return bean;
    }
    
    public abstract void beanAction();
}
