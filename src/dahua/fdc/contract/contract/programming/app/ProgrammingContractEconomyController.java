package com.kingdee.eas.fdc.contract.programming.app;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import com.kingdee.eas.fdc.contract.programming.ProgrammingContractEconomyInfo;
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
import com.kingdee.eas.fdc.contract.programming.ProgrammingContractEconomyCollection;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface ProgrammingContractEconomyController extends CoreBillEntryBaseController
{
    public ProgrammingContractEconomyInfo getProgrammingContractEconomyInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public ProgrammingContractEconomyInfo getProgrammingContractEconomyInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public ProgrammingContractEconomyInfo getProgrammingContractEconomyInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public ProgrammingContractEconomyCollection getProgrammingContractEconomyCollection(Context ctx) throws BOSException, RemoteException;
    public ProgrammingContractEconomyCollection getProgrammingContractEconomyCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public ProgrammingContractEconomyCollection getProgrammingContractEconomyCollection(Context ctx, String oql) throws BOSException, RemoteException;
}