package com.kingdee.eas.port.pm.invest.investplan.app;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.fdc.basedata.FDCBillInfo;
import com.kingdee.eas.fdc.basedata.FDCBillStateEnum;
import com.kingdee.eas.fdc.basedata.FDCSQLBuilder;
import com.kingdee.eas.port.pm.contract.ContractException;
import com.kingdee.eas.port.pm.invest.investplan.ContractProgrammingFactory;
import com.kingdee.eas.port.pm.invest.investplan.ContractProgrammingInfo;

public class ContractProgrammingControllerBean extends AbstractContractProgrammingControllerBean
{
    private static Logger logger =
        Logger.getLogger("com.kingdee.eas.port.pm.invest.investplan.app.ContractProgrammingControllerBean");
    protected void trimName(FDCBillInfo billInfo) {
    	//重写 该方法 放开名称可以保存 40个字符以上
    	if(billInfo.getName() != null) {
    		billInfo.setName(billInfo.getName().trim());
		}
    }
    //审批时 自动更新为最新版本并更改上一版本为非最新版本
    protected void _audit(Context ctx, BOSUuid billId) throws BOSException,
    		EASBizException {
    	ContractProgrammingInfo info=ContractProgrammingFactory.getLocalInstance(ctx).getContractProgrammingInfo(new ObjectUuidPK(billId));
    	FDCSQLBuilder builder=new FDCSQLBuilder(ctx);
    	builder.appendSql("update CT_INV_ContractProgramming set fisfinal=0 where fnumber=? and fid <> ?");
    	builder.addParam(info.getNumber());
    	builder.addParam(info.getId().toString());
    	builder.execute();
    	builder.clear();
    	info.setIsFinal(true);
    	_save(ctx,info);
    	super._audit(ctx, billId);
    }
    
    protected void _audit(Context ctx, List idList) throws BOSException,
    		EASBizException {
    	super._audit(ctx, idList);
    }
    //反审批时 应该 更新  审批状态的 版本号最大的 合约规划为最新版本
    protected void _unAudit(Context ctx, BOSUuid billId) throws BOSException,
    		EASBizException {
    	super._unAudit(ctx, billId);
    	ContractProgrammingInfo info=ContractProgrammingFactory.getLocalInstance(ctx).getContractProgrammingInfo(new ObjectUuidPK(billId));
    	info.setState(FDCBillStateEnum.SUBMITTED);
    	info.setAuditor(null);
    	info.setAuditTime(null);
    	info.setIsFinal(false);
		SelectorItemCollection selector = new SelectorItemCollection();
		selector.add("state");
		selector.add("auditor");
		selector.add("auditTime");
		selector.add("isFinal");
    	this._updatePartial(ctx, info, selector);
    	FDCSQLBuilder builder=new FDCSQLBuilder(ctx);
    	builder.appendSql("update CT_INV_ContractProgramming set fisfinal=1 where fnumber=? and fstate=? and fid in");
    	builder.appendSql(" (select fid from CT_INV_ContractProgramming where ");
    	builder.appendSql(" fedition=(select max(fedition) from CT_INV_ContractProgramming where fnumber=? and fid<>? and fstate=?)) ");
    	builder.addParam(info.getNumber());
    	builder.addParam(FDCBillStateEnum.AUDITTED_VALUE);
    	builder.addParam(info.getNumber());
    	builder.addParam(info.getId().toString());
    	builder.addParam(FDCBillStateEnum.AUDITTED_VALUE);
    	builder.execute();
    	builder.clear();
    }
    
    public void unAudit(Context ctx, List idList) throws BOSException,
    		EASBizException {
    	super.unAudit(ctx, idList);
    }
    
    protected void _delete(Context ctx, IObjectPK pk) throws BOSException,
    		EASBizException {
//    	ContractProgrammingInfo info=ContractProgrammingFactory.getLocalInstance(ctx).getContractProgrammingInfo(pk);
    	super._delete(ctx, pk);
//    	FDCSQLBuilder builder=new FDCSQLBuilder(ctx);
//		builder.appendSql("update CT_INV_ContractProgramming set fisversion=1 where fedition= ");
//		builder.appendSql(" (select max(fedition) from CT_INV_ContractProgramming where fnumber=? ) and fnumber=? ");
//		builder.addParam(info.getNumber());
//		builder.addParam(info.getNumber());
//		builder.execute();
//		builder.clear();
    }
    
    protected void _delete(Context ctx, IObjectPK[] arrayPK)
    		throws BOSException, EASBizException {
    	for (int i = 0; i < arrayPK.length; i++) {
			recycleNumber(ctx, arrayPK[i]);
			ContractProgrammingInfo info=ContractProgrammingFactory.getLocalInstance(ctx).getContractProgrammingInfo(arrayPK[i]);
	    	_delete(ctx, arrayPK[i]);
	    	updateIsLastVersion(ctx,info.getNumber());
		}
    	
    }
    //保存提交时 首先 把所有同一编码的 合约规划 设置为 非 最大版本  保存提交完成后 调用更新最大版本方法 更新
    protected IObjectPK _save(Context ctx, IObjectValue model) throws BOSException, EASBizException {
    	ContractProgrammingInfo info=(ContractProgrammingInfo)model;
    	FDCSQLBuilder builder=new FDCSQLBuilder(ctx);
		builder.appendSql("update CT_INV_ContractProgramming set fislastversion=0 where fnumber=? ");
		builder.addParam(info.getNumber());
		builder.execute();
		builder.clear();
    	IObjectPK obj=super._save(ctx, model);
    	updateIsLastVersion(ctx,info.getNumber());
    	return obj;
    }
    protected IObjectPK _submit(Context ctx, IObjectValue model) throws BOSException, EASBizException {
    	ContractProgrammingInfo info=(ContractProgrammingInfo)model;
    	FDCSQLBuilder builder=new FDCSQLBuilder(ctx);
		builder.appendSql("update CT_INV_ContractProgramming set fislastversion=0 where fnumber=? ");
		builder.addParam(info.getNumber());
		builder.execute();
		builder.clear();
    	IObjectPK obj=super._submit(ctx, model);
    	updateIsLastVersion(ctx,info.getNumber());
    	return obj;
    }
    //更新同一合约规划 版本号最大的 的 记录的  是否最大版本 为true
    private void updateIsLastVersion(Context ctx,String number) throws BOSException, EASBizException {
    	FDCSQLBuilder builder=new FDCSQLBuilder(ctx);
    	builder.appendSql("update CT_INV_ContractProgramming set fislastversion=1 where fedition= ");
		builder.appendSql(" (select max(fedition) from CT_INV_ContractProgramming where fnumber=? ) and fnumber=? ");
		builder.addParam(number);
		builder.addParam(number);
		builder.execute();
		builder.clear();
    }

    protected void checkBill(Context ctx, IObjectValue model)
		throws BOSException, EASBizException {
    	//重写检查单据方法  修订时能够保存提交    不报名称和编码可以重复 的错误 
		FDCBillInfo FDCBillInfo = ((FDCBillInfo) model);
		ContractProgrammingInfo info=(ContractProgrammingInfo)model;
		if(info.getEdition().compareTo(new BigDecimal("1.00"))==0){
			checkNumberDup(ctx, FDCBillInfo);
			
			checkNameDup(ctx, FDCBillInfo);
		}
	
	}
	/**
	 * 
	 * 描述：检查名称重复
	 * 
	 * @param ctx
	 * @param billInfo
	 * @throws BOSException
	 * @throws EASBizException
	 * @author:liupd 创建时间：2006-8-24
	 *               <p>
	 */
	private void checkNumberDup(Context ctx, FDCBillInfo billInfo)
			throws BOSException, EASBizException {
	}    
}