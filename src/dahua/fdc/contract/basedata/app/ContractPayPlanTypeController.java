package com.kingdee.eas.fdc.basedata.app;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.fdc.basedata.ContractPayPlanTypeInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.fdc.basedata.ContractPayPlanTypeCollection;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface ContractPayPlanTypeController extends FDCDataBaseController
{
    public ContractPayPlanTypeInfo getContractPayPlanTypeInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public ContractPayPlanTypeInfo getContractPayPlanTypeInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public ContractPayPlanTypeInfo getContractPayPlanTypeInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public ContractPayPlanTypeCollection getContractPayPlanTypeCollection(Context ctx) throws BOSException, RemoteException;
    public ContractPayPlanTypeCollection getContractPayPlanTypeCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public ContractPayPlanTypeCollection getContractPayPlanTypeCollection(Context ctx, String oql) throws BOSException, RemoteException;
    public boolean disEnabled(Context ctx, IObjectPK pk, IObjectValue model) throws BOSException, EASBizException, RemoteException;
    public boolean enabled(Context ctx, IObjectPK pk, IObjectValue model) throws BOSException, EASBizException, RemoteException;
}