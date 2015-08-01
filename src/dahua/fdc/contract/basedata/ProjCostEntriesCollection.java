package com.kingdee.eas.fdc.basedata;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class ProjCostEntriesCollection extends AbstractObjectCollection 
{
    public ProjCostEntriesCollection()
    {
        super(ProjCostEntriesInfo.class);
    }
    public boolean add(ProjCostEntriesInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(ProjCostEntriesCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(ProjCostEntriesInfo item)
    {
        return removeObject(item);
    }
    public ProjCostEntriesInfo get(int index)
    {
        return(ProjCostEntriesInfo)getObject(index);
    }
    public ProjCostEntriesInfo get(Object key)
    {
        return(ProjCostEntriesInfo)getObject(key);
    }
    public void set(int index, ProjCostEntriesInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(ProjCostEntriesInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(ProjCostEntriesInfo item)
    {
        return super.indexOf(item);
    }
}