package com.kingdee.eas.custom.richbase.app;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.custom.richbase.SaleTypeInfo;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.framework.app.DataBaseController;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.custom.richbase.SaleTypeCollection;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface SaleTypeController extends DataBaseController
{
    public SaleTypeInfo getSaleTypeInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public SaleTypeInfo getSaleTypeInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public SaleTypeInfo getSaleTypeInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public SaleTypeCollection getSaleTypeCollection(Context ctx) throws BOSException, RemoteException;
    public SaleTypeCollection getSaleTypeCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public SaleTypeCollection getSaleTypeCollection(Context ctx, String oql) throws BOSException, RemoteException;
}