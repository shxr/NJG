package com.kingdee.eas.port.equipment.special.app;

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

import com.kingdee.eas.base.codingrule.CodingRuleManagerFactory;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.common.client.SysContext;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.entity.SorterItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.util.app.ContextUtil;
import com.kingdee.eas.xr.XRBillBaseCollection;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.eas.xr.app.XRBillBaseControllerBean;
import com.kingdee.eas.framework.CoreBillBaseCollection;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.ObjectBaseCollection;
import com.kingdee.eas.port.equipment.maintenance.RepairOrderInfo;
import com.kingdee.eas.port.equipment.special.AnnualYearDetailInfo;
import com.kingdee.eas.port.equipment.special.AnnualYearDetailCollection;
import com.kingdee.util.UuidException;

public class AnnualYearDetailControllerBean extends AbstractAnnualYearDetailControllerBean
{
    private static Logger logger =
        Logger.getLogger("com.kingdee.eas.port.equipment.special.app.AnnualYearDetailControllerBean");
    protected IObjectPK _save(Context ctx, IObjectValue model)throws BOSException, EASBizException {
    	AnnualYearDetailInfo aXRBillBaseInfo = (AnnualYearDetailInfo)model;
    	 aXRBillBaseInfo.setNumber(getAutoCode(ctx, aXRBillBaseInfo));
    	return super._save(ctx, aXRBillBaseInfo);
    }
    
    protected void _submit(Context ctx, IObjectPK pk, IObjectValue model)throws BOSException, EASBizException {
    	AnnualYearDetailInfo aXRBillBaseInfo = (AnnualYearDetailInfo)model;
   	   aXRBillBaseInfo.setNumber(getAutoCode(ctx, aXRBillBaseInfo));
    	super._submit(ctx, pk, model);
    }
    
    
	/** 获取编码规则生成的编码 
     * @throws UuidException 
     * @throws BOSException 
     * @throws EASBizException */
	public static String getAutoCode(Context ctx ,IObjectValue objValue) throws EASBizException, BOSException, UuidException {
		    String NumberCode = "";
		    String companyId;
			com.kingdee.eas.base.codingrule.ICodingRuleManager codeRuleMgr = null;
			if(ctx==null){
				companyId = SysContext.getSysContext().getCurrentFIUnit().getId().toString();
				codeRuleMgr = CodingRuleManagerFactory.getRemoteInstance();
			}else{
				companyId = ContextUtil.getCurrentFIUnit(ctx).getId().toString();
				codeRuleMgr = CodingRuleManagerFactory.getLocalInstance(ctx);
			}

			if (codeRuleMgr.isExist(objValue, companyId)) {
				if (codeRuleMgr.isUseIntermitNumber(objValue, companyId)) {
					NumberCode = codeRuleMgr.readNumber(objValue, companyId);
				} else {
					NumberCode = codeRuleMgr.getNumber(objValue, companyId);
				}
			}
		return NumberCode;
	}
}