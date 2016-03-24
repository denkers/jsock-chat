package com.kyleruss.jsockchat.commons.listbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractListBean<T extends Serializable> implements Serializable, Collection<T>
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
    
    public Date getUpdateTime()
    {
        return updateTime;
    }
    
    public void setUpdateTime(Date updateTime)
    {
        this.updateTime =   updateTime;
    }

    @Override
    public int size()
    {
        return listData.size();
    }

    @Override
    public boolean isEmpty() 
    {
        return listData.isEmpty();
    }

    @Override
    public boolean contains(Object o) 
    {
        return listData.contains(o);
    }

    @Override
    public Iterator<T> iterator() 
    {
        return listData.iterator();
    }

    @Override
    public Object[] toArray() 
    {
        return listData.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) 
    {
        return listData.toArray(a);
    }

    @Override
    public boolean add(T e)
    {
        return listData.add(e);
    }

    @Override
    public boolean remove(Object o)
    {
        return listData.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) 
    {
        return listData.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) 
    {
        return listData.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) 
    {
        return listData.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) 
    {
        return listData.retainAll(c);
    }

    @Override
    public void clear() 
    {
        listData.clear();
    }
}
