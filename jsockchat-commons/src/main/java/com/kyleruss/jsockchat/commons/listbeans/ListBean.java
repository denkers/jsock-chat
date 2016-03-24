
package com.kyleruss.jsockchat.commons.listbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface ListBean<T extends Serializable> extends Serializable
{
    public List<T> getListData();
    
    public void setListData(List<T> listData);
    
    public Date getUpdateTime();
    
    public void setUpdateTime(Date updateTime);
}
