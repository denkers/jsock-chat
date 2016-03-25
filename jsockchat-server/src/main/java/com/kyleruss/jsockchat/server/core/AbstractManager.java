
package com.kyleruss.jsockchat.server.core;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class AbstractManager<K, V> 
{
    protected Map<K, V> data;
    
    protected AbstractManager()
    {
        data   =   new HashMap<>();
    }
    
    public synchronized boolean add(K name, V value)
    {
        return data.put(name, value) != null;
    }
    
    public synchronized V remove(K name)
    {
        return data.remove(name);
    }
    
    public synchronized V get(K name)
    {
        return data.get(name);
    }
    
    public synchronized Collection<V> getDataValues()
    {
        return data.values();   
    }
    
    public synchronized boolean find(K name)
    {
        return data.containsKey(name);
    }
    
}
