package com.kingdee.eas.port.equipment.record;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.entity.SorterItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.xr.IXRBillBase;

public interface IEquId extends IXRBillBase
{
    public void addnew(IObjectPK pk, EquIdInfo model) throws BOSException, EASBizException;
    public IObjectPK addnew(EquIdInfo model) throws BOSException, EASBizException;
    public void audit(IObjectPK pk) throws BOSException, EASBizException;
    public void delete(IObjectPK pk) throws BOSException, EASBizException;
    public void delete(IObjectPK[] arrayPK) throws BOSException, EASBizException;
    public IObjectPK[] delete(FilterInfo filter) throws BOSException, EASBizException;
    public IObjectPK[] delete(String oql) throws BOSException, EASBizException;
    public boolean exists(IObjectPK pk) throws BOSException, EASBizException;
    public boolean exists(FilterInfo filter) throws BOSException, EASBizException;
    public boolean exists(String oql) throws BOSException, EASBizException;
    public EquIdCollection getEquIdCollection() throws BOSException;
    public EquIdCollection getEquIdCollection(EntityViewInfo view) throws BOSException;
    public EquIdCollection getEquIdCollection(String oql) throws BOSException;
    public IObjectPK[] getPKList() throws BOSException, EASBizException;
    public IObjectPK[] getPKList(FilterInfo filter, SorterItemCollection sorter) throws BOSException, EASBizException;
    public IObjectPK[] getPKList(String oql) throws BOSException, EASBizException;
    public EquIdInfo getEquIdInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public EquIdInfo getEquIdInfo(IObjectPK pk) throws BOSException, EASBizException;
    public EquIdInfo getEquIdInfo(String oql) throws BOSException, EASBizException;
    public void unAudit(IObjectPK pk) throws BOSException, EASBizException;
    public void unAudit(IObjectPK[] pks) throws BOSException, EASBizException;
    public void update(IObjectPK pk, EquIdInfo model) throws BOSException, EASBizException;
    public void updateBigObject(IObjectPK pk, EquIdInfo model) throws BOSException;
    public void updatePartial(EquIdInfo model, SelectorItemCollection selector) throws BOSException, EASBizException;
    public String getBindingProperty() throws BOSException;
    public void inUse(EquIdInfo model) throws BOSException;
    public void outUse(EquIdInfo model) throws BOSException;
    public void actionRegistChange(EquIdInfo model) throws BOSException;
    public void excel(EquIdInfo model) throws BOSException;
    public void excelFoced(EquIdInfo model) throws BOSException;
    public void excelEqu(EquIdInfo model) throws BOSException;
    public void zhuyao(EquIdInfo model) throws BOSException;
    public void beijian(EquIdInfo model) throws BOSException;
    public void xiangxi(EquIdInfo model) throws BOSException;
}