
package com.kyleruss.jsockchat.client.updatebean;

import com.kyleruss.jsockchat.commons.updatebean.UpdateBean;


public abstract class UpdateBeanHandler<T extends UpdateBean>
{
    protected final T bean;
    
    public UpdateBeanHandler(T bean)
    {
        this.bean   =   bean;
    }
    
    public T getBean()
    {
        return bean;
    }
    
    public abstract void beanAction();
}
