package com.kyleruss.jsockchat.commons.listbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class AbstractListBean<T extends Serializable> 
{
    protected List<T> listData;
    protected Date updateTime;
    
    public AbstractListBean()
    {
        listData    =   new ArrayList<>();
        updateTime  =   new Date();
    }
    
    public List<T> getListData()
    {
        return listData;
    }
    
    public void setListData(List<T> listData)
    {
        this.listData   =   listData;
    }
}
