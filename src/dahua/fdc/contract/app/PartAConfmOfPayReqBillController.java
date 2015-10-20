package com.kingdee.eas.fdc.contract.app;

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
import com.kingdee.eas.fdc.contract.PartAConfmOfPayReqBillInfo;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.fdc.contract.PartAConfmOfPayReqBillCollection;
import com.kingdee.eas.framework.app.CoreBaseController;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface PartAConfmOfPayReqBillController extends CoreBaseController
{
    public PartAConfmOfPayReqBillInfo getPartAConfmOfPayReqBillInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public PartAConfmOfPayReqBillInfo getPartAConfmOfPayReqBillInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public PartAConfmOfPayReqBillInfo getPartAConfmOfPayReqBillInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public PartAConfmOfPayReqBillCollection getPartAConfmOfPayReqBillCollection(Context ctx) throws BOSException, RemoteException;
    public PartAConfmOfPayReqBillCollection getPartAConfmOfPayReqBillCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public PartAConfmOfPayReqBillCollection getPartAConfmOfPayReqBillCollection(Context ctx, String oql) throws BOSException, RemoteException;
}