package com.kingdee.eas.port.pm.invest.app;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.framework.app.CoreBillEntryBaseController;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.metadata.entity.SorterItemCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.eas.port.pm.invest.YearInvestPlanE2Info;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.port.pm.invest.YearInvestPlanE2Collection;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface YearInvestPlanE2Controller extends CoreBillEntryBaseController
{
    public boolean exists(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public boolean exists(Context ctx, FilterInfo filter) throws BOSException, EASBizException, RemoteException;
    public boolean exists(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public YearInvestPlanE2Info getYearInvestPlanE2Info(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public YearInvestPlanE2Info getYearInvestPlanE2Info(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public YearInvestPlanE2Info getYearInvestPlanE2Info(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public IObjectPK addnew(Context ctx, YearInvestPlanE2Info model) throws BOSException, EASBizException, RemoteException;
    public void addnew(Context ctx, IObjectPK pk, YearInvestPlanE2Info model) throws BOSException, EASBizException, RemoteException;
    public void update(Context ctx, IObjectPK pk, YearInvestPlanE2Info model) throws BOSException, EASBizException, RemoteException;
    public void updatePartial(Context ctx, YearInvestPlanE2Info model, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public void updateBigObject(Context ctx, IObjectPK pk, YearInvestPlanE2Info model) throws BOSException, RemoteException;
    public void delete(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public IObjectPK[] getPKList(Context ctx) throws BOSException, EASBizException, RemoteException;
    public IObjectPK[] getPKList(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public IObjectPK[] getPKList(Context ctx, FilterInfo filter, SorterItemCollection sorter) throws BOSException, EASBizException, RemoteException;
    public YearInvestPlanE2Collection getYearInvestPlanE2Collection(Context ctx) throws BOSException, RemoteException;
    public YearInvestPlanE2Collection getYearInvestPlanE2Collection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public YearInvestPlanE2Collection getYearInvestPlanE2Collection(Context ctx, String oql) throws BOSException, RemoteException;
    public IObjectPK[] delete(Context ctx, FilterInfo filter) throws BOSException, EASBizException, RemoteException;
    public IObjectPK[] delete(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public void delete(Context ctx, IObjectPK[] arrayPK) throws BOSException, EASBizException, RemoteException;
}