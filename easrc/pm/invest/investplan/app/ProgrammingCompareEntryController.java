package com.kingdee.eas.port.pm.invest.investplan.app;

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
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.port.pm.invest.investplan.ProgrammingCompareEntryCollection;
import com.kingdee.eas.port.pm.invest.investplan.ProgrammingCompareEntryInfo;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface ProgrammingCompareEntryController extends CoreBillEntryBaseController
{
    public ProgrammingCompareEntryInfo getProgrammingCompareEntryInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public ProgrammingCompareEntryInfo getProgrammingCompareEntryInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public ProgrammingCompareEntryInfo getProgrammingCompareEntryInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public ProgrammingCompareEntryCollection getProgrammingCompareEntryCollection(Context ctx) throws BOSException, RemoteException;
    public ProgrammingCompareEntryCollection getProgrammingCompareEntryCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public ProgrammingCompareEntryCollection getProgrammingCompareEntryCollection(Context ctx, String oql) throws BOSException, RemoteException;
}