package com.kingdee.eas.port.equipment.insurance.app;

import org.apache.log4j.Logger;
import javax.ejb.*;
import java.rmi.RemoteException;
import com.kingdee.bos.*;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.metadata.IMetaDataPK;
import com.kingdee.bos.metadata.rule.RuleExecutor;
import com.kingdee.bos.metadata.MetaDataPK;
//import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.framework.ejb.AbstractEntityControllerBean;
import com.kingdee.bos.framework.ejb.AbstractBizControllerBean;
//import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.IObjectCollection;
import com.kingdee.bos.service.ServiceContext;
import com.kingdee.bos.service.IServiceContext;

import java.lang.String;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.port.equipment.insurance.EquInsuranceAccidentCollection;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.entity.SorterItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.xr.XRBillBaseCollection;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.eas.xr.app.XRBillBaseControllerBean;
import com.kingdee.eas.framework.CoreBillBaseCollection;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.port.equipment.insurance.EquInsuranceAccidentInfo;
import com.kingdee.eas.framework.ObjectBaseCollection;

public class EquInsuranceAccidentControllerBeanEx extends com.kingdee.eas.port.equipment.insurance.app.EquInsuranceAccidentControllerBean
{
    private static Logger logger =
        Logger.getLogger("com.kingdee.eas.port.equipment.insurance.app.EquInsuranceAccidentControllerBeanEx");
    protected void _audit(Context ctx, IObjectPK pk)throws BOSException, EASBizException
    {
	     super._audit(ctx, pk);
    }
    protected void _unAudit(Context ctx, IObjectPK pk)throws BOSException, EASBizException
    {
	     super._unAudit(ctx, pk);
    }
    protected void _unAudit(Context ctx, IObjectPK[] pks)throws BOSException, EASBizException
    {
	     super._unAudit(ctx, pks);
    }
    protected String _getBindingProperty(Context ctx)throws BOSException
    {
	    return  super._getBindingProperty(ctx);
    }
    protected void _equInfomation(Context ctx, IObjectValue model)throws BOSException
    {
	     super._equInfomation(ctx, model);
    }
}				
