package com.kyleruss.jsockchat.commons.listbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class AbstractListBean<T extends Serializable> implements ListBean<T>
{
    protected List<T> listData;
    protected Date updateTime;
    
    public AbstractListBean()
    {
        listData    =   new ArrayList<>();
        updateTime  =   new Date();
    }
    
    @Override
    public List<T> getListData()
    {
        return listData;
    }
    
    @Override
    public void setListData(List<T> listData)
    {
        this.listData   =   listData;
    }
    
    @Override
    public Date getUpdateTime()
    {
        return updateTime;
    }
    
    @Override
    public void setUpdateTime(Date updateTime)
    {
        this.updateTime =   updateTime;
    }

}
