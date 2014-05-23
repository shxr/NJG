package com.kingdee.eas.port.pm.fi.app;

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
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.port.pm.fi.PayRequestBillEntryInfo;
import com.kingdee.eas.port.pm.fi.PayRequestBillEntryCollection;
import com.kingdee.eas.framework.app.BillEntryBaseController;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface PayRequestBillEntryController extends BillEntryBaseController
{
    public PayRequestBillEntryInfo getPayRequestBillEntryInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public PayRequestBillEntryInfo getPayRequestBillEntryInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public PayRequestBillEntryInfo getPayRequestBillEntryInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public PayRequestBillEntryCollection getPayRequestBillEntryCollection(Context ctx) throws BOSException, RemoteException;
    public PayRequestBillEntryCollection getPayRequestBillEntryCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public PayRequestBillEntryCollection getPayRequestBillEntryCollection(Context ctx, String oql) throws BOSException, RemoteException;
}