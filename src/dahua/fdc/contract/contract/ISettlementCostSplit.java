package com.kingdee.eas.fdc.contract;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.fdc.basedata.IFDCSplitBill;
import com.kingdee.bos.metadata.entity.SorterItemCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.BOSUuid;
import java.util.List;

public interface ISettlementCostSplit extends IFDCSplitBill
{
    public boolean exists(IObjectPK pk) throws BOSException, EASBizException;
    public boolean exists(FilterInfo filter) throws BOSException, EASBizException;
    public boolean exists(String oql) throws BOSException, EASBizException;
    public SettlementCostSplitInfo getSettlementCostSplitInfo(IObjectPK pk) throws BOSException, EASBizException;
    public SettlementCostSplitInfo getSettlementCostSplitInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public SettlementCostSplitInfo getSettlementCostSplitInfo(String oql) throws BOSException, EASBizException;
    public IObjectPK addnew(SettlementCostSplitInfo model) throws BOSException, EASBizException;
    public void addnew(IObjectPK pk, SettlementCostSplitInfo model) throws BOSException, EASBizException;
    public void update(IObjectPK pk, SettlementCostSplitInfo model) throws BOSException, EASBizException;
    public void updatePartial(SettlementCostSplitInfo model, SelectorItemCollection selector) throws BOSException, EASBizException;
    public void updateBigObject(IObjectPK pk, SettlementCostSplitInfo model) throws BOSException;
    public void delete(IObjectPK pk) throws BOSException, EASBizException;
    public IObjectPK[] getPKList() throws BOSException, EASBizException;
    public IObjectPK[] getPKList(String oql) throws BOSException, EASBizException;
    public IObjectPK[] getPKList(FilterInfo filter, SorterItemCollection sorter) throws BOSException, EASBizException;
    public SettlementCostSplitCollection getSettlementCostSplitCollection() throws BOSException;
    public SettlementCostSplitCollection getSettlementCostSplitCollection(EntityViewInfo view) throws BOSException;
    public SettlementCostSplitCollection getSettlementCostSplitCollection(String oql) throws BOSException;
    public IObjectPK[] delete(FilterInfo filter) throws BOSException, EASBizException;
    public IObjectPK[] delete(String oql) throws BOSException, EASBizException;
    public void delete(IObjectPK[] arrayPK) throws BOSException, EASBizException;
    public void traceData(List idList) throws BOSException, EASBizException;
    public void afterVoucher(SettlementCostSplitCollection sourceBillCollection) throws BOSException, EASBizException;
    public void autoSplit4(BOSUuid billID) throws BOSException, EASBizException;
}