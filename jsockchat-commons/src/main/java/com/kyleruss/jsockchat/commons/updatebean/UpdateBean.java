
package com.kyleruss.jsockchat.commons.updatebean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface UpdateBean<T extends Serializable> extends Serializable
{
    public List<T> getDataList();
    
    public Map<String, T> getData();
    
    public void setData(Map<String, T> data);
}
