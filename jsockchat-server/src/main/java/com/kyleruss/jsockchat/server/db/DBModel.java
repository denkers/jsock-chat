
package com.kyleruss.jsockchat.server.db;

public abstract class DBModel 
{
    protected String primaryKey;
    
    protected String tableName;
    
    public String getPrimaryKey()
    {
        return primaryKey;
    }
    
    public String getTableName()
    {
        return tableName;
    }
}
