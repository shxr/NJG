/**
 * output package name
 */
package com.kingdee.eas.fdc.contract.client;

import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;

import org.apache.log4j.Logger;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox;
import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.KDTSelectManager;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.kdf.table.event.KDTMouseEvent;
import com.kingdee.bos.ctrl.swing.KDLayout;
import com.kingdee.bos.ctrl.swing.KDTextField;
import com.kingdee.bos.ctrl.swing.StringUtils;
import com.kingdee.bos.ctrl.swing.event.DataChangeEvent;
import com.kingdee.bos.ctrl.swing.event.DataChangeListener;
import com.kingdee.bos.ctrl.swing.event.SelectorEvent;
import com.kingdee.bos.ctrl.swing.event.SelectorListener;
import com.kingdee.bos.ctrl.swing.util.CtrlCommonConstant;
import com.kingdee.bos.dao.AbstractObjectValue;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.metadata.IMetaDataPK;
import com.kingdee.bos.metadata.MetaDataPK;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemCollection;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.entity.SelectorItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.ui.face.IUIWindow;
import com.kingdee.bos.ui.face.UIFactory;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.base.attachment.AttachmentInfo;
import com.kingdee.eas.base.attachment.common.AttachmentClientManager;
import com.kingdee.eas.base.attachment.common.AttachmentManagerFactory;
import com.kingdee.eas.base.codingrule.CodingRuleException;
import com.kingdee.eas.base.param.util.ParamManager;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.common.client.OprtState;
import com.kingdee.eas.common.client.SysContext;
import com.kingdee.eas.common.client.UIContext;
import com.kingdee.eas.common.client.UIFactoryName;
import com.kingdee.eas.fdc.basedata.ChangeReasonInfo;
import com.kingdee.eas.fdc.basedata.ChangeTypeFactory;
import com.kingdee.eas.fdc.basedata.ChangeTypeInfo;
import com.kingdee.eas.fdc.basedata.FDCBillStateEnum;
import com.kingdee.eas.fdc.basedata.FDCConstants;
import com.kingdee.eas.fdc.basedata.FDCDateHelper;
import com.kingdee.eas.fdc.basedata.FDCHelper;
import com.kingdee.eas.fdc.basedata.FDCSQLBuilder;
import com.kingdee.eas.fdc.basedata.SourceTypeEnum;
import com.kingdee.eas.fdc.basedata.VisaTypeFactory;
import com.kingdee.eas.fdc.basedata.VisaTypeInfo;
import com.kingdee.eas.fdc.basedata.client.AttachmentUtils;
import com.kingdee.eas.fdc.basedata.client.FDCClientHelper;
import com.kingdee.eas.fdc.basedata.client.FDCClientUtils;
import com.kingdee.eas.fdc.basedata.client.FDCClientVerifyHelper;
import com.kingdee.eas.fdc.basedata.client.FDCMsgBox;
import com.kingdee.eas.fdc.basedata.client.FDCSplitClientHelper;
import com.kingdee.eas.fdc.basedata.util.KDDetailedAreaUtil;
import com.kingdee.eas.fdc.contract.ChangeAuditBillInfo;
import com.kingdee.eas.fdc.contract.ChangeAuditUtil;
import com.kingdee.eas.fdc.contract.ChangeBillStateEnum;
import com.kingdee.eas.fdc.contract.ChangeUrgentDegreeEnum;
import com.kingdee.eas.fdc.contract.ConChangeNoCostSplitFactory;
import com.kingdee.eas.fdc.contract.ConChangeSplitFactory;
import com.kingdee.eas.fdc.contract.ContractBillFactory;
import com.kingdee.eas.fdc.contract.ContractBillInfo;
import com.kingdee.eas.fdc.contract.ContractChangeBillFactory;
import com.kingdee.eas.fdc.contract.ContractChangeBillInfo;
import com.kingdee.eas.fdc.contract.ContractChangeEntryCollection;
import com.kingdee.eas.fdc.contract.ContractChangeEntryInfo;
import com.kingdee.eas.fdc.contract.FDCUtils;
import com.kingdee.eas.fdc.contract.GraphCountEnum;
import com.kingdee.eas.fdc.contract.IContractChangeBill;
import com.kingdee.eas.fdc.contract.OfferEnum;
import com.kingdee.eas.fdc.contract.programming.client.ContractBillLinkProgContEditUI;
import com.kingdee.eas.framework.CoreBillBaseCollection;
import com.kingdee.eas.framework.client.FrameWorkClientUtils;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.eas.util.client.AdvMsgBox;
import com.kingdee.eas.util.client.EASResource;
import com.kingdee.eas.util.client.MsgBox;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.NumericExceptionSubItem;

/**
 * 
 * 描述:变更签证单编辑界面
 * @author liupd  date:2006-10-10 <p>
 * @version EAS5.1.3
 */
public class ContractChangeBillEditUI extends AbstractContractChangeBillEditUI
{
	/**
	 * 变更内容
	 */
	private static final String CHANGE_CONTENT = "changeContent";

	private static final Logger logger = CoreUIObject.getLogger(ContractChangeBillEditUI.class);
    
    //是否从暂估价合同变更序时簿上新增单据
    Boolean isFromTempEvalConChangeListUI=Boolean.FALSE;
    //设计变更单“提出方”及“施工单位”是否必填
	boolean isOfferAndConstrReq = false;
    //是否启用变更下发，默认为启用
    boolean isDispatch = true;
    //变更签证确认是否自动改为工程签证单
    boolean isTransformVisa = false;
    //责任人是否可以选择其他部门的人员
	boolean canSelectOtherOrgPerson = false;

	public ContractChangeBillEditUI() throws Exception
    {
        super();
        jbInit() ;
	}

	private void jbInit() {	    
		titleMain = getUITitle();
	}
    
    protected String getEditUIName()
    {
        return ContractChangeBillEditUI.class.getName();
    }

    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return ContractChangeBillFactory.getRemoteInstance();
    }

    protected KDTable getDetailTable()
    {        
        return kdtEntrys;
	}    
    
    public void actionEdit_actionPerformed(ActionEvent e) throws Exception {
		super.actionEdit_actionPerformed(e);
		this.prmtChangeReason.setEnabled(false);
	}

	protected IObjectValue createNewDetailData(KDTable table)
    {
    	ContractChangeEntryInfo info = new ContractChangeEntryInfo();
		char c = (char) ('A'+table.getRowCount());
		info.setNumber(c+"");
		return info;
    }

	/**
	 * 
	 * 描述：返回编码控件（子类必须实现）
	 * @return
	 * @author:liupd
	 * 创建时间：2006-10-13 <p>
	 */
	protected KDTextField getNumberCtrl() {
		return txtNumber;
	}
	
	/**
	 * @see com.kingdee.eas.fdc.basedata.client.FDCBillEditUI#handleCodingRule()
	 */
	protected void handleCodingRule() throws BOSException, CodingRuleException, EASBizException {
		//只有新增的时候调用获取编码规则编码的方法
		if (OprtState.ADDNEW.equals(this.oprtState)) {
			super.handleCodingRule();
		}

	}

	protected void prepareNumber(IObjectValue caller, String number) {
		super.prepareNumber(caller, number);
		this.editData.setNumber(number);
	}
	
    protected com.kingdee.bos.dao.IObjectValue createNewData()
    {
    	ContractChangeBillInfo objectValue = new ContractChangeBillInfo();
        objectValue.setCreator(SysContext.getSysContext().getCurrentUserInfo());
//        objectValue.setCreateTime(new Timestamp(new Date().getTime()));
		try {
			objectValue.setCreateTime(FDCDateHelper.getServerTimeStamp());
//			objectValue.setSignDate(new Date(FDCDateHelper.getServerTimeStamp().getTime()));
		} catch (BOSException e1) {
			handUIExceptionAndAbort(e1);
		}
        objectValue.setSourceType(SourceTypeEnum.ADDNEW);
        

        ContractBillInfo contractBillInfo = this.contractBill;
		objectValue.setContractBill(contractBillInfo);
	    String contractBillNumber = (String)getUIContext().get("contractBillNumber");
	    
	    	//不知道编码规则里要不要设置这个
		contractBillInfo.setNumber(contractBillNumber);
	   	objectValue.setContractBillNumber(contractBillNumber);
	    if(contractBillInfo.getName()!=null)
	    	txtContractName.setText(contractBillInfo.getName());
	    if(contractBillInfo.getCurProject()!=null){
	    	objectValue.setCurProject(contractBillInfo.getCurProject());
	    	if(contractBillInfo.getCurProject().getDisplayName()!=null){
	    		objectValue.setCurProjectName(contractBillInfo.getCurProject().getDisplayName());
	    	}
	    }
	    if(contractBillInfo.getPartB()!=null){
	    	objectValue.setMainSupp(contractBillInfo.getPartB());
	    }		
		
    	objectValue.setUrgentDegree(ChangeUrgentDegreeEnum.Normal);
    	objectValue.setGraphCount(GraphCountEnum.NoFile);
    	
    	objectValue.setConductTime(FDCHelper.getCurrentDate());
    	objectValue.setSettleTime(FDCHelper.getCurrentDate());
    	
    	objectValue.setCU(SysContext.getSysContext().getCurrentCtrlUnit());
    	
    	objectValue.setBookedDate(this.bookedDate);
    	objectValue.setPeriod(this.curPeriod);
    	
    	//如果是从变更签证申请序时簿上新增的造价性质为暂估价的合同的变更签证确认在暂估价合同变更序时簿上是不应该显示出来的
    	//但是必须有东西来标识,故根据从不同序时簿上传递的上下文来进行区分要给字段isFromTempEvalConChangeListUI设置的是啥值
    	//以便于在暂估价合同变更序时簿加载数据的时候根据此字段来过滤数据    by Cassiel_peng 2009-9-24
    	isFromTempEvalConChangeListUI=getUIContext().get("isFromTempEvalConChangeListUI")==null?Boolean.FALSE:(Boolean)getUIContext().get("isFromTempEvalConChangeListUI");
    	if(isFromTempEvalConChangeListUI.booleanValue()){
    		objectValue.setIsFromTempEvalChange(true);
    		//去掉页签
    		tbpContractChange.remove(pnlExecute);
    		pnlExecute.setVisible(false);
    		ctnExecute.setVisible(false);
    	}else{
    		objectValue.setIsFromTempEvalChange(false);
    	}
    	//推荐客户使用中渝模式
    	objectValue.setCurrency(contractBill.getCurrency());
    	objectValue.setExRate(contractBill.getExRate());
    	objectValue.setOffer(OfferEnum.SELFCOM);
        return objectValue;
    }
    
    public void onLoad() throws Exception {
    	kdtSpecialtyType.checkParsed();
    	this.txtOriBalanceAmount.setSupportedEmpty(true);
    	super.onLoad();
    	//new update renliang at 2010-5-14
    	FDCClientUtils.initSupplierF7(this, prmtConstrUnit);
		FDCClientUtils.initSupplierF7(this, prmtConductUnit);
		
		
    	if(this.editData.isIsFromTempEvalChange()){
    		//隐藏执行情况
    		this.lableVisa.setVisible(false);
    		this.kDSeparator7.setVisible(false);
    		this.ctnExecute.setVisible(false);
    		this.contThisTime.setVisible(false);
    		this.txtThisTime.setVisible(false);
    		this.contCondition.setVisible(false);
    		this.txtCondition.setVisible(false);
    		this.prmtContractBill.setQueryInfo("com.kingdee.eas.fdc.contract.app.F7TempEvalConQueryForContractChangeBill");
    	}
    	txtNumber.setMaxLength(80);
    	txtName.setMaxLength(80);
    	txtChangeSubject.setMaxLength(80);
    	txtConstrSite.setMaxLength(200);
    	
		EntityViewInfo v = new EntityViewInfo();
    	FilterInfo filter = new FilterInfo();
    	filter.getFilterItems().add(new FilterItemInfo("isEnabled", Boolean.TRUE));
    	v.setFilter(filter);
    	prmtChangeType.setEntityViewInfo(v);
    
    	prmtChangeReason.setEntityViewInfo(v);
    	
    	handleOldData();
    	disableAutoAddLine(getDetailTable());
		disableAutoAddLineDownArrow(getDetailTable());
		
		//业务日志判断为空时取期间中断
		if(pkbookedDate!=null&&pkbookedDate.isSupportedEmpty()){
			pkbookedDate.setSupportedEmpty(false);
		}
		this.actionPrint.setEnabled(true);
		this.actionPrintPreview.setEnabled(true);
		
		if (getOprtState().equals(STATUS_ADDNEW) || getOprtState().equals(STATUS_EDIT)) {
			// 未签证前置空,因为有0签证 by hpw
			this.txtOriBalanceAmount.setValue(null);
			this.txtBalanceAmount.setValue(null);
		}
		
		if(editData!=null&&editData.getId()!=null&&FDCUtils.isRunningWorkflow(this.editData.getId().toString())){
			ContractChangeBillInfo tempInfo=ContractChangeBillFactory.getRemoteInstance().getContractChangeBillInfo(new ObjectUuidPK(BOSUuid.read(this.editData.getId().toString())));
			this.txtOriBalanceAmount.setValue(tempInfo.getSettAuditAmt());
			this.txtBalanceAmount.setValue(FDCHelper.multiply(tempInfo.getSettAuditAmt(),tempInfo.getSettAuditExRate()));
		}
//		btnSubmit.setEnabled(true);
		actionSubmit.setVisible(true);
		initOprtBtn();
		if (editData != null && OfferEnum.SELFCOM.equals(editData.getOffer())) {
			prmtConductDept.setRequired(true);
			prmtConductUnit.setEnabled(false);
			prmtConductUnit.setRequired(false);
		} else {
			prmtConductUnit.setRequired(true);
			prmtConductDept.setRequired(false);
			prmtConductDept.setEnabled(false);
		}
		String cuId = editData.getCU().getId().toString();
		FDCClientUtils.setRespDeptF7(prmtConductDept, this, canSelectOtherOrgPerson?null:cuId);
		if(isOfferAndConstrReq){
			//中渝模式
		}else{
			prmtChangeReason.addDataChangeListener(new DataChangeListener(){

				public void dataChanged(DataChangeEvent eventObj) {
					if(prmtChangeType.getData() == null && prmtChangeReason != null){
						ChangeTypeInfo  info =null;
						try {
							info = ChangeTypeFactory.getRemoteInstance().getChangeTypeInfo(new ObjectUuidPK(((ChangeReasonInfo)prmtChangeReason.getData()).getChangeType().getId().toString()));
						} catch (EASBizException e) {
							handUIExceptionAndAbort(e);
						} catch (BOSException e) {
							handUIExceptionAndAbort(e);
						}
						prmtChangeType.setDataNoNotify(info);
					}
					
				}});
			
			prmtChangeReason.addSelectorListener(new SelectorListener(){

				public void willShow(SelectorEvent e) {
					prmtChangeReason.getQueryAgent().resetRuntimeEntityView();
					EntityViewInfo evi = null;
					if(prmtChangeType.getData() != null){
						if(prmtChangeReason.getEntityViewInfo()!=null){
							   evi = prmtChangeReason.getEntityViewInfo();
							   FilterItemCollection collection = evi.getFilter().getFilterItems();
							   if(collection != null && collection.size() > 0){
								   for(int i=0;i<collection.size();i++){
									  if("changeType.id".equalsIgnoreCase(collection.get(i).getPropertyName()))
									  {
									   collection.remove(collection.get(i));
									   }
								   }
							   }
							   evi.getFilter().appendFilterItem("changeType.id",((ChangeTypeInfo)prmtChangeType.getData()).getId().toString());
							   prmtChangeReason.getQueryAgent().setEntityViewInfo(evi);
						}else{
							 evi  = new EntityViewInfo();
							FilterInfo filter = new FilterInfo();
							filter.appendFilterItem("changeType.id", ((ChangeTypeInfo)prmtChangeType.getData()).getId().toString());
							evi.setFilter(filter);
							prmtChangeReason.setEntityViewInfo(evi);
						}
					}else{
						if(prmtChangeReason.getEntityViewInfo() != null){
						    evi = prmtChangeReason.getEntityViewInfo();
						   FilterItemCollection collection = evi.getFilter().getFilterItems();
						   if(collection != null && collection.size() > 0){
							   for(int i=0;i<collection.size();i++){
								   collection.remove(collection.get(i));
							   }
						   }
						}
					prmtChangeReason.setEntityViewInfo(evi);	
					}
				}});

		}
		fillAttachmentList();
		actionTraceUp.setEnabled(editData != null && editData.getChangeAudit() != null);
		actionTraceUp.setVisible(true);
		
		KDDetailedAreaUtil.setDetailCellEditor(kdtEntrys, CHANGE_CONTENT, false, 320);
    }

	/**
	 * @see com.kingdee.eas.framework.client.CoreBillEditUI#initDataStatus()
	 */
	protected void initDataStatus() {
		super.initDataStatus();
		ContractChangeBillInfo info = (ContractChangeBillInfo) getDataObject();
		actionTraceUp.setEnabled(info != null && info.getChangeAudit() != null);
		actionTraceUp.setVisible(info != null && info.getChangeAudit() != null);
    }
    
    public SelectorItemCollection getSelectors() {
    	
        SelectorItemCollection sic = new SelectorItemCollection();
        
        sic.add(new SelectorItemInfo("number"));
        sic.add(new SelectorItemInfo("name"));
        sic.add(new SelectorItemInfo("state"));
        sic.add(new SelectorItemInfo("auditTime"));
        sic.add(new SelectorItemInfo("hasSettled"));
        
        sic.add(new SelectorItemInfo("changeSubject"));
        sic.add(new SelectorItemInfo("urgentDegree"));        

        sic.add(new SelectorItemInfo("balanceType"));
        sic.add(new SelectorItemInfo("isDeduct"));
        sic.add(new SelectorItemInfo("deductAmount"));
        sic.add(new SelectorItemInfo("oriDeductAmount"));
        sic.add(new SelectorItemInfo("amount"));
        sic.add(new SelectorItemInfo("exRate"));
        sic.add(new SelectorItemInfo("originalAmount"));
        sic.add(new SelectorItemInfo("balanceAmount"));
        sic.add(new SelectorItemInfo("oriBalanceAmount"));
        //增加原始联系单号 eric_wang 2010.05.31
        sic.add(new SelectorItemInfo("originalContactNum"));
        sic.add(new SelectorItemInfo("isSureChangeAmt"));
        
        sic.add(new SelectorItemInfo("isImportChange"));
        sic.add(new SelectorItemInfo("constructPrice"));
        
        sic.add(new SelectorItemInfo("deductReason"));
        sic.add(new SelectorItemInfo("graphCount"));

        sic.add(new SelectorItemInfo("createTime"));
        sic.add(new SelectorItemInfo("disThisTime"));
        sic.add(new SelectorItemInfo("impleCondition"));
        
        sic.add(new SelectorItemInfo("changeAuditNumber"));
        sic.add(new SelectorItemInfo("costNouse"));
        sic.add(new SelectorItemInfo("bookedDate"));
        sic.add(new SelectorItemInfo("isFromTempEvalChange"));
        sic.add(new SelectorItemInfo("specialtyName"));
        sic.add(new SelectorItemInfo("changeAudit.specialtyTypeEntry.specialtyType.*"));

        sic.add("implement");
    
        sic.add(new SelectorItemInfo("currency.number"));
        sic.add(new SelectorItemInfo("currency.name"));
        
        sic.add(new SelectorItemInfo("changeType.number"));
        sic.add(new SelectorItemInfo("changeType.name"));
        
        sic.add(new SelectorItemInfo("conductDept.number"));
        sic.add(new SelectorItemInfo("conductDept.name"));
        
        sic.add(new SelectorItemInfo("changeReason.number"));
        sic.add(new SelectorItemInfo("changeReason.name"));
        
		sic.add("curProject.id");
		sic.add("curProject.number");
		sic.add("curProject.name");
		sic.add("curProject.displayName");
		sic.add("curProject.CU.number");
		sic.add("curProject.costCenter.id");	// modified by zhaoqin for R130711-0070 on 2013/12/3
		sic.add(new SelectorItemInfo("curProject.fullOrgUnit.name"));
        
        sic.add(new SelectorItemInfo("jobType.number"));
        sic.add(new SelectorItemInfo("jobType.name"));
        sic.add(new SelectorItemInfo("specialtyType.number"));
        sic.add(new SelectorItemInfo("specialtyType.name"));
        
        sic.add(new SelectorItemInfo("mainSupp.number"));
        sic.add(new SelectorItemInfo("mainSupp.name"));
        
        sic.add(new SelectorItemInfo("entrys.*"));
        sic.add(new SelectorItemInfo("entrys.changeContent"));
        sic.add(new SelectorItemInfo("entrys.isBack"));
        sic.add(new SelectorItemInfo("entrys.number"));
        sic.add(new SelectorItemInfo("entrys.isAllExe"));
        sic.add(new SelectorItemInfo("entrys.isPartExe"));
        sic.add(new SelectorItemInfo("entrys.isNoExe"));
        
        
        sic.add(new SelectorItemInfo("invalidCostReason.number"));
        sic.add(new SelectorItemInfo("invalidCostReason.name"));
        
        sic.add("orgUnit.id");
        sic.add("contractBill.id");
        sic.add("contractBill.number");
        sic.add("contractBill.name");
        sic.add("contractBill.hasSettled");
        sic.add("contractBill.isCoseSplit");
        sic.add("contractBillNumber");
        sic.add("changeAudit.id");
        sic.add("changeAudit.changeState");
    	
        sic.add(new SelectorItemInfo("creator.number"));
        sic.add(new SelectorItemInfo("creator.name"));
        sic.add(new SelectorItemInfo("auditor.number"));
        sic.add(new SelectorItemInfo("auditor.name"));
        
        sic.add(new SelectorItemInfo("period.number"));
        sic.add(new SelectorItemInfo("period.periodNumber"));
        sic.add(new SelectorItemInfo("period.periodYear"));
        sic.add(new SelectorItemInfo("period.beginDate"));
    	
        sic.add(new SelectorItemInfo("creator.number"));
        sic.add(new SelectorItemInfo("creator.name"));
        sic.add(new SelectorItemInfo("auditor.number"));
        sic.add(new SelectorItemInfo("auditor.name"));
        
        sic.add(new SelectorItemInfo("conductUnit.number"));
        sic.add(new SelectorItemInfo("conductUnit.name"));
        sic.add(new SelectorItemInfo("constrUnit.number"));
        sic.add(new SelectorItemInfo("constrUnit.name"));
        sic.add(new SelectorItemInfo("constrSite"));
        sic.add(new SelectorItemInfo("offer"));
        sic.add(new SelectorItemInfo("visaType.number"));
        sic.add(new SelectorItemInfo("visaType.name"));
        sic.add(new SelectorItemInfo("CU"));
        sic.add(new SelectorItemInfo("specialtyTypeEntry.specialtyType.*"));
    	return sic;
    }
    
    public void setOprtState(String oprtType) {
    	super.setOprtState(oprtType);    	
    	tblVisa.setEnabled(false);
    	txtThisTime.setEnabled(false);   
    	txtCondition.setEnabled(false);
    	if(STATUS_ADDNEW.equals(oprtType)||STATUS_EDIT.equals(oprtType)){
    		if(editData==null||editData.getChangeAudit()==null){
    			txtNumber.setEnabled(true);
    			//可修改
    			btnSubmit.setEnabled(true);
    			txtName.setEnabled(true);
    			pkbookedDate.setEnabled(true);
    			chkIsDeduct.setEnabled(true);
    			txtDeductAmount.setEnabled(true);
    			txtDeductReason.setEnabled(true);
    			txtBudgetOriAmount.setEnabled(true);
    			prmtValidReason.setEnabled(true);
    			txtNouse.setEnabled(true);
    			//set 原始单据单号可以编辑 eric_wang 2010.5.30
    			this.txtOriginalContactNum.setEnabled(true);
    			for(int i=0;i<ctnEntrys.getButtons().length;i++){
    				JButton btn = (JButton)ctnEntrys.getButtons()[i];
    				btn.setEnabled(true);
    			}
    		}
    	}
    	if(STATUS_VIEW.equals(this.oprtState)){
    		if(editData!=null&&editData.getState()!=null)
			if(editData.getState().equals(FDCBillStateEnum.ANNOUNCE)||editData.getState().equals(FDCBillStateEnum.VISA)){
				actionEdit.setEnabled(false);
			}
    		getDetailTable().getStyleAttributes().setLocked(true);
    		chkIsDeduct.setEnabled(false);
		}else{
			getDetailTable().getStyleAttributes().setLocked(false);
			if(getDetailTable().getColumn("number")!=null)
				getDetailTable().getColumn("number").getStyleAttributes().setLocked(true);
		}
    	
    	if(STATUS_FINDVIEW.equals(oprtType)) {
    		actionSplit.setEnabled(true);
    		actionSplit.setVisible(true);   		
    	}
    	else {
    		actionSplit.setEnabled(false);
    		actionSplit.setVisible(false);
    	}
    	Boolean isFromWorkflow = (Boolean) getUIContext().get("isFromWorkflow");
    	if(isFromWorkflow!=null&&isFromWorkflow.booleanValue()){
    		actionSubmit.setEnabled(false);
    		actionAddLine.setEnabled(false);
    		actionRemoveLine.setEnabled(false);
    		actionInsertLine.setEnabled(false);
    		actionRemove.setEnabled(false);
    		lockUIForViewStatus();
	    	actionSave.setEnabled(true);
	    	txtBudgetAmount.setEnabled(true);
			txtBudgetAmount.setEditable(true);
			txtBudgetAmount.setAccessAuthority(CtrlCommonConstant.AUTHORITY_COMMON);
    	}
    }
    
    /**
     * 铜陵:变更签证申请中的附件要传递到变更签证确认里   by Cassiel_peng  2009-9-20
     */
     public void actionViewChangeAudtiAttachment_actionPerformed(ActionEvent e)
    		throws Exception {
    	AttachmentClientManager acm = AttachmentManagerFactory.getClientManager();
    	ChangeAuditBillInfo changeAudit=this.editData.getChangeAudit();
    	if(changeAudit!=null){
    		String boId= changeAudit.getId().toString();
    		acm.showAttachmentListUIByBoID(boId,this,false);
    	}else{
    		MsgBox.showWarning("该单据不存在关联的变更签证申请，不能查看附件！");
    		return;
    	}
    } 
    /**
     * 子类重载此方法，负责清空一些不能重复的域的值
     */
    protected void setFieldsNull(AbstractObjectValue newData) {
    	super.setFieldsNull(newData);
    	ContractChangeBillInfo info = (ContractChangeBillInfo)newData;
    	info.setContractBill(editData.getContractBill());
    	info.setContractBillNumber(editData.getContractBillNumber());
    	info.setDisThisTime(null);
    	info.setImpleCondition(null);
    	ContractChangeEntryCollection coll = info.getEntrys();
    	if(coll.size()>0){
    		for(int i=0; i<coll.size();i++){
    			ContractChangeEntryInfo entry = coll.get(i);
    			entry.setIsAllExe(false);
    			entry.setIsPartExe(false);
    			entry.setIsNoExe(false);
    			entry.setDiscription(null);
    		}
    	}
    }
    public void actionCopy_actionPerformed(ActionEvent e) throws Exception {
    	super.actionCopy_actionPerformed(e);
    	final Timestamp createTime = new Timestamp(System.currentTimeMillis());
		editData.setCreateTime(createTime);
		pkCreateDate.setValue(createTime);
		editData.setCreator(SysContext.getSysContext().getCurrentUserInfo());
		prmtCreator.setValue(SysContext.getSysContext().getCurrentUserInfo());
    	for(int i=0;i<getSecondTable().getRowCount();i++){
    		getSecondTable().getCell(i, "isAllExe").setValue(Boolean.FALSE);
    		getSecondTable().getCell(i, "isPartExe").setValue(Boolean.FALSE);
    		getSecondTable().getCell(i, "isNoExe").setValue(Boolean.FALSE);
    		getSecondTable().getCell(i, "discription").setValue(null);
    	}
    	
    	editData.setSourceType(SourceTypeEnum.ADDNEW);
    }
    
    public void actionAddNew_actionPerformed(ActionEvent e) throws Exception {
    	super.actionAddNew_actionPerformed(e);
    	prmtChangeType.setEnabled(true);
    }    
    public void actionAttachment_actionPerformed(ActionEvent e)
    		throws Exception {
    	super.actionAttachment_actionPerformed(e);
    	/**
    	 * 奥园要求：审批状态的变更签证确认可以上传附件,但是由于单据编辑界面的标准"附件管理"是否可维护还受到界面编辑状态的控制,
    	 * 故在变更签证确认编辑界面再如此处理奥园的需求变得毫无意义，故仅处理变更签证确认序事簿界面.   by Cassiel_peng
    	 */
    	/*boolean isEdit=false;
    	AttachmentClientManager acm = AttachmentManagerFactory.getClientManager();
    	String boID = this.editData.getId().toString();
    	if (boID == null)
    	{
    		return;
    	}
    	
    	if(this.editData.getState()!=null){
    		Object conChangeBill=this.editData.getState();
    		isEdit=ContractClientUtils.canUploadAttaForAudited(conChangeBill);
    	}
    	
    	//判断界面状态
    	 if(STATUS_ADDNEW.equals(getOprtState())||STATUS_EDIT.equals(getOprtState()))
         {
             isEdit = true;
         }else{
        	 isEdit=false;
         }
    	acm.showAttachmentListUIByBoID(boID,this,isEdit);*/
    	fillAttachmentList();
    }
    //业务日期变化事件
    protected void bookedDate_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    	if(contractBill==null){
    		return ;
    	}
    	String projectId = this.contractBill.getCurProject().getId().toString();    	
    	fetchPeriod(e,pkbookedDate,cbPeriod, projectId,  true);
    }
    
    protected void controlDate_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e,ControlDateChangeListener listener) throws Exception
    {
    	if("bookedDate".equals(listener.getShortCut())){
    		bookedDate_dataChanged(e);
    	}
    }
    
    //业务日期变化事件
    ControlDateChangeListener bookedDateChangeListener = new ControlDateChangeListener("bookedDate");
    
	//监听器
	Map listenersMap = new HashMap();
    //加载完数据后加上监听器
    protected void attachListeners() {

		pkbookedDate.addDataChangeListener(bookedDateChangeListener);
    	//变更类型
		addDataChangeListener(prmtChangeType); 
		addDataChangeListener(prmtContractBill);
		addDataChangeListener(prmtSpecialtyType);
		
		addDataChangeListener(txtExRate);
		addDataChangeListener(txtBudgetOriAmount);
		
    }
    
    //注销监听器
    protected void detachListeners() {		
		pkbookedDate.removeDataChangeListener(bookedDateChangeListener);
		
    	removeDataChangeListener(prmtChangeType); 
    	removeDataChangeListener(prmtContractBill);
    	removeDataChangeListener(prmtSpecialtyType);    	
    	removeDataChangeListener(txtExRate);
    	removeDataChangeListener(txtBudgetOriAmount);
    }
    
    public void loadFields() {
    	
		//loadFields 最好注销监听器,以免loadFields触发事件
		detachListeners();
		//修改窗体的Title放在onLoad里边会混乱   by Cassiel_peng  2009-9-28
		if(this.editData.isIsFromTempEvalChange()){
    		if(STATUS_ADDNEW.equals(this.getOprtState())){
    			this.setUITitle("暂估价合同变更录入-新增");
    		}else if(STATUS_EDIT.equals(this.getOprtState())){
    			this.setUITitle("暂估价合同变更录入-编辑");
    		}else if(STATUS_VIEW.equals(this.getOprtState())){
    			this.setUITitle("暂估价合同变更录入-查看");
    		}
    	}
    	super.loadFields(); 
    	
    	
    	if (txtNumber.isEnabled()) {
			txtNumber.requestFocusInWindow();
		}else{
			prmtChangeReason.requestFocusInWindow();
		}
    	if(editData.getContractBill()!=null&&editData.getContractBill().getName()!=null)
    		txtContractName.setText(editData.getContractBill().getName());
    	setSaveActionStatus();
    	actionTraceDown.setVisible(false);
    	actionTraceUp.setVisible(false);
    	actionCreateFrom.setVisible(false);
    	getDetailTable().getColumn("number").getStyleAttributes().setLocked(true);
    	if (editData.getState() == FDCBillStateEnum.SUBMITTED) {
			actionSave.setEnabled(false);
		}
    	prmtAuditor.setDisplayFormat("$name$");   	
    	tblVisa.setEnabled(false);
    	txtThisTime.setEnabled(false);   
    	txtCondition.setEnabled(false);

    	if (editData.getChangeAudit() != null) {
			this.pkbookedDate.setEnabled(false);
			VisaTypeInfo visaType = editData.getVisaType();
			if(visaType==null){
				SelectorItemCollection selector = new SelectorItemCollection();
				selector.add("id");
				selector.add("name");
				selector.add("number");
				try {
					visaType = VisaTypeFactory.getRemoteInstance().getVisaTypeInfo(new ObjectUuidPK(BOSUuid.read(VisaTypeInfo.DESIGN)),selector);
					editData.setVisaType(visaType);
					prmtVisaType.setValue(visaType);
				} catch (EASBizException e) {
					handUIExceptionAndAbort(e);
				} catch (BOSException e) {
					handUIExceptionAndAbort(e);
				}
			}
			if(isTransformVisa){
				prmtChangeReason.setEnabled(false);
				prmtVisaType.setEnabled(false);
			}
		} else {
			// 新增工程签证单，控件状态设置
			// 必录
			txtBudgetOriAmount.setRequired(true);
			prmtVisaType.setRequired(true);
			// 非必录
			prmtJobType.setRequired(false);
			comboUrgentDegree.setRequired(false);
			
			// 不可用
			if (isTransformVisa) {
				prmtChangeReason.setEnabled(false);
			}
			//对于暂估价合同变更来说不管此次为了实现中渝要求的功能添加了多少个参数,都需要将施工单位置为必录项
			if(this.editData.isIsFromTempEvalChange()){
				prmtConstrUnit.setRequired(true);
			}
		}
		
    	if(baseCurrency.getId().toString().equals(this.editData.getCurrency().getId().toString())){
    		this.txtExRate.setEnabled(false);
    		this.txtBudgetAmount.setEnabled(false);
    	}
    	
    	//专业类型多选
		if (editData.getId() != null && editData.getSpecialtyTypeEntry() != null && editData.getSpecialtyTypeEntry().size() > 0) {
			Object[] objs = new Object[editData.getSpecialtyTypeEntry().size()];
			for (int i = 0; i < editData.getSpecialtyTypeEntry().size(); i++) {
				objs[i] = editData.getSpecialtyTypeEntry().get(i).getSpecialtyType();
			}
			prmtSpecialtyType.setData(objs);
		}
		if (editData.getChangeAudit() != null && editData.getChangeAudit().getSpecialtyTypeEntry() != null) {
			kdtSpecialtyType.removeRows();
			txtSpecialtyType.setText("");
			StringBuffer specialtyType = new StringBuffer();
			Object[] objs = new Object[editData.getChangeAudit().getSpecialtyTypeEntry().size()];
			for (int i = 0; i < editData.getChangeAudit().getSpecialtyTypeEntry().size(); i++) {
				objs[i] = editData.getChangeAudit().getSpecialtyTypeEntry().get(i).getSpecialtyType();
				kdtSpecialtyType.addRow().getCell("specialTypeID").setValue(objs[i]);
				specialtyType.append(objs[i] + ";");
			}
			prmtSpecialtyType.setData(objs);
			txtSpecialtyType.setText(specialtyType.toString());
		}
		attachListeners();

		KDDetailedAreaUtil.setDetailCellEditor(kdtEntrys, CHANGE_CONTENT, false, 320);
    }
    
    protected void verifyInputForSave() throws Exception {
    	super.verifyInputForSave();
    	FDCClientVerifyHelper.verifyEmpty(this, txtName);
    }
    protected void verifyInputForSubmint() throws Exception {
    	super.verifyInputForSubmint();
    	
    	String org = SysContext.getSysContext().getCurrentOrgUnit().getId().toString();
		 String paramValue = ParamManager.getParamValue(null, new ObjectUuidPK(org), "FDC228_ISSTRICTCONTROL");
		//严格控制时校验
    	if(editData.getContractBill()!=null && "0".equals(paramValue)){
    		
    		BOSUuid id = editData.getContractBill().getId();
    		SelectorItemCollection selector = new SelectorItemCollection();
    		selector.add("programmingContract.balance");
    		
    		ContractBillInfo contractBillInfo = ContractBillFactory.getRemoteInstance().getContractBillInfo(new ObjectUuidPK(id), selector);
    		
			if(contractBillInfo.getProgrammingContract()!=null && FDCHelper.compareTo(editData.getAmount(), contractBillInfo.getProgrammingContract().getBalance())>0){
				FDCMsgBox.showWarning("合同变更金额不能大于合约规划的控制余额");
				abort();
			}
    	}
    }

	protected void kdtEntrys_tableClicked(KDTMouseEvent e) throws Exception {
		if (e.getType() == 0) // 如果点击表头不要处理
			return;
		if (e.getColIndex() == getDetailTable().getColumnIndex(CHANGE_CONTENT)) {
			if (STATUS_VIEW.equals(oprtState) || STATUS_FINDVIEW.equals(oprtState)) {
				KDDetailedAreaUtil.showDialog(this, getDetailTable(), 250, 200, 500);
			}
		}
	}
	
    public void actionRemove_actionPerformed(ActionEvent e) throws Exception
    {
    	checkBeforeRemove();
    	super.actionRemove_actionPerformed(e);
    }
    
    protected void checkBeforeRemove() throws Exception{
    	if(editData.getChangeAudit()!=null){
    		MsgBox.showWarning(this, ChangeAuditUtil.getRes("changeAudit"));
			SysUtil.abort();
    	}
    	EntityViewInfo view = new EntityViewInfo();
		FilterInfo filter = new FilterInfo();
		filter.getFilterItems().add(
				new FilterItemInfo("contractChange", editData.getId()));
		view.setFilter(filter);
		view.getSelector().add("id");
		CoreBillBaseCollection coll = ConChangeSplitFactory.getRemoteInstance().getCoreBillBaseCollection(view);
		Iterator iter = coll.iterator(); 
		if(iter.hasNext()) {	
			MsgBox.showWarning(this, ContractClientUtils.getRes("noRemove"));
			SysUtil.abort();
		}
    }
    
    public void actionInsertLine_actionPerformed(ActionEvent e) throws Exception {
	}
    
	protected void prmtContractBill_dataChanged(DataChangeEvent e) throws Exception {
		super.prmtContractBill_dataChanged(e);
		Object newValue = e.getNewValue();
		if(newValue instanceof ContractBillInfo){
			ContractBillInfo info = (ContractBillInfo)newValue;
			SelectorItemCollection selector = new SelectorItemCollection();
			selector.add("id");
			selector.add("name");
			selector.add("partB.*");
			ContractBillInfo contractBillInfo = ContractBillFactory.getRemoteInstance()
			.getContractBillInfo(new ObjectUuidPK(info.getId()),selector);
			if(contractBillInfo.getName()!=null){
				txtContractName.setText(contractBillInfo.getName());
			}
			if(contractBillInfo.getPartB()!=null){
				editData.setMainSupp(contractBillInfo.getPartB());
				prmtMainSupp.setValue(contractBillInfo.getPartB());
			}else{
				editData.setMainSupp(null);
				prmtMainSupp.setValue(null);
			}
		}
	}

	public void onShow() throws Exception{
		super.onShow();
		if (txtNumber.isEnabled()) {
			txtNumber.requestFocusInWindow();
		}else{
			prmtChangeReason.requestFocusInWindow();
		}
		actionDisPatch.putValue(Action.SMALL_ICON, EASResource.getIcon("imgTbtn_emend"));
		actionVisa.putValue(Action.SMALL_ICON, EASResource.getIcon("imgTbtn_move"));
		actionDisPatch.setEnabled(true);
		actionAudit.setEnabled(false);
		actionAudit.setVisible(false);
		actionUnAudit.setEnabled(false);
		actionUnAudit.setVisible(false);
		actionVisa.setEnabled(true);

		if(editData!=null&&editData.getEntrys().size()>0){
			loadSecondTable();
		}
		
		Boolean disableSplit = (Boolean) getUIContext().get("disableSplit");
		if (disableSplit != null && disableSplit.booleanValue()) {
			actionSplit.setVisible(false);
			actionSplit.setEnabled(false);
			actionSave.setEnabled(false);
		}
		btnPrint.setEnabled(true);
		btnPrintPreview.setEnabled(true);
		actionPrint.setVisible(true);
		actionPrintPreview.setVisible(true);
		
		//by Cassiel_peng  2009-9-20
		btnWorkFlowG.setVisible(true);
		btnWorkFlowG.setEnabled(true);
		menuItemMultiapprove.setVisible(true);
		menuItemMultiapprove.setEnabled(true);
		MenuItemWFG.setVisible(true);
		MenuItemWFG.setEnabled(true);
		menuItemNextPerson.setVisible(true);
		menuItemNextPerson.setEnabled(true);
		menuItemAuditResult.setVisible(true);
		menuItemAuditResult.setEnabled(true);
		kDMenuItemSendMessage.setVisible(true);
		kDMenuItemSendMessage.setEnabled(true);
		menuWorkflow.setVisible(true);
		menuWorkflow.setEnabled(true);
		
		actionViewChangeAudtiAttachment.setVisible(true);
		actionViewChangeAudtiAttachment.setEnabled(true);
		btnViewChangeAuditAttachment.setEnabled(true);
		//对于暂估价合同变更来说,无论界面操作状态如何都不应该有下发按钮显示,并且相应地签证也应该显示为"执行情况确认"
		if(this.editData.isIsFromTempEvalChange()==true){
			this.menuItemDisPatch.setVisible(false);
			this.menuItemDisPatch.setEnabled(false);
			this.btnDisPatch.setVisible(false);
			this.btnDisPatch.setEnabled(false);
			this.btnVisa.setText("执行情况确认");
			this.btnVisa.setToolTipText("执行情况确认");
			this.menuItemVisa.setText("执行情况确认");
			this.menuItemVisa.setToolTipText("执行情况确认");
		}
	}
	
	public void actionDisPatch_actionPerformed(ActionEvent e) throws Exception {
		if(FDCUtils.isRunningWorkflow(this.editData.getId().toString())){
			  MsgBox.showWarning("已在工作流处理中，当前任务或执行人不匹配");
			  SysUtil.abort();
		 }
		checkBeforeDisPatch();
		IContractChangeBill bill = (IContractChangeBill) getBizInterface();		
		bill.disPatch(new IObjectPK[]{new ObjectUuidPK(editData.getId().toString())});
		syncDataFromDB();
		FDCClientUtils.showOprtOK(this);
		setSaveActionStatus();
		super.actionDisPatch_actionPerformed(e);
	}
	
	protected void syncDataFromDB() throws Exception {
		//由传递过来的ID获取值对象
        if(editData.getId() == null)
        {
            String s = EASResource.getString(FrameWorkClientUtils.strResource + "Msg_IDIsNull");
            MsgBox.showError(s);
            SysUtil.abort();
        }
        IObjectPK pk = new ObjectUuidPK(BOSUuid.read(editData.getId().toString()));
        setDataObject(getValue(pk));
        loadFields();
	}
	
	protected void setSaveActionStatus() {
		super.setSaveActionStatus();
		if(editData!=null&&editData.getState()!=null){
			if(STATUS_FINDVIEW.equals(this.getOprtState())) {
	    		actionSplit.setEnabled(true);
	    		actionSplit.setVisible(true);
	    		actionSave.setEnabled(true);
	    		txtBudgetAmount.setEditable(true);
	    		txtBudgetAmount.setAccessAuthority(CtrlCommonConstant.AUTHORITY_COMMON);
			}
			else if (editData.getState().equals(FDCBillStateEnum.ANNOUNCE)||editData.getState().equals(FDCBillStateEnum.VISA)){
				actionEdit.setEnabled(false);
				actionSave.setEnabled(false);
				actionSubmit.setEnabled(false);
			}
		}
	}
    
	private void checkBeforeDisPatch(){
		FDCClientVerifyHelper.verifyRequire(this);
		if(editData.getMainSupp()==null){
			MsgBox.showWarning(this,ChangeAuditUtil.getRes("mainSuppNoNull"));
			SysUtil.abort();
		}
		if(editData.getChangeAudit()==null){
			if(!(editData.getState().equals(FDCBillStateEnum.AUDITTED))){
				MsgBox.showWarning(this,ChangeAuditUtil.getRes("noDispatch"));
				SysUtil.abort();
			}
		}
		else{
			if((editData.getChangeAudit().getChangeState()!=null)
					&&(!((editData.getChangeAudit().getChangeState().equals(ChangeBillStateEnum.Audit))
							||(editData.getChangeAudit().getChangeState().equals(ChangeBillStateEnum.Announce))
							||(editData.getChangeAudit().getChangeState().equals(ChangeBillStateEnum.Visa))))){
				MsgBox.showWarning(this,ChangeAuditUtil.getRes("noDispatch"));
				SysUtil.abort();
			}else if(editData.getState().equals(FDCBillStateEnum.ANNOUNCE)||editData.getState().equals(FDCBillStateEnum.VISA)||editData.getState().equals(FDCBillStateEnum.SAVED)||editData.getState().equals(FDCBillStateEnum.SUBMITTED)){
				MsgBox.showWarning(this,ChangeAuditUtil.getRes("noDispatch"));
				SysUtil.abort();
			}
		}
	}


	protected void prmtChangeType_dataChanged(DataChangeEvent e) throws Exception {
		if (OprtState.VIEW.equals(getOprtState())) {
    		return;
    	}
		if(this.editData.getChangeType()!=null){
			if(this.editData.getChangeType().equals(this.prmtChangeType.getValue())){
				return;
			}
			else if((e.getOldValue()!=null)&&(e.getOldValue().equals(e.getNewValue()))){
				return;
			}
		}
		else if((e.getOldValue()!=null)&&(e.getOldValue().equals(e.getNewValue()))){
			return;
		}
		prmtSpecialtyType.setValue(null);
		prmtChangeReason.setDataNoNotify(null);
		super.prmtChangeType_dataChanged(e);
	}
	
	/**
	 * 专业类型
	 * @see com.kingdee.eas.fdc.contract.client.AbstractContractChangeBillEditUI#prmtSpecialtyType_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent)
	 */
	protected void prmtSpecialtyType_dataChanged(DataChangeEvent e) throws Exception {
		kdtSpecialtyType.removeRows();
		txtSpecialtyType.setText("");
		if (e.getNewValue() != null) {
			Object[] object = (Object[]) prmtSpecialtyType.getData();
			StringBuffer specialtyType = new StringBuffer();
			for (int i = 0; i < object.length; i++) {
				kdtSpecialtyType.addRow().getCell("specialTypeID").setValue(object[i]);
				specialtyType.append(object[i] + ";");
			}
			txtSpecialtyType.setText(specialtyType.toString());
		}
	}


	protected void prmtSpecialtyType_willShow(SelectorEvent e) throws Exception {
		if(prmtChangeType.getValue()==null){
			e.setCanceled(true);
			MsgBox.showWarning(this,ChangeAuditUtil.getRes("changeTypeFirst"));
			return;
		}
		setSpecialtyTypeEvi();
		super.prmtSpecialtyType_willShow(e);
	}
	
	private void setSpecialtyTypeEvi(){
		prmtSpecialtyType.getQueryAgent().resetRuntimeEntityView();
		EntityViewInfo evi = new EntityViewInfo();
		FilterInfo filter = new FilterInfo();
		ChangeTypeInfo info = (ChangeTypeInfo) prmtChangeType.getValue();
		filter.getFilterItems().add(new FilterItemInfo("changeType.id",info.getId()));
		evi.setFilter(filter);
		prmtSpecialtyType.setEntityViewInfo(evi);
	}
	
	protected void verifyInput(ActionEvent e) throws Exception {
		super.verifyInput(e);
		FDCClientVerifyHelper.verifyRequire(this);
    	if (getDetailTable().getExpandedRowCount()<=0){
    		if(isTransformVisa){
    			MsgBox.showWarning(this,ChangeAuditUtil.getRes("visaContentMust"));
    		}else{
    			MsgBox.showWarning(this,ChangeAuditUtil.getRes("contentMust"));
    		}
			SysUtil.abort();
    	}else{
    		IRow row;
    		KDTSelectManager manager = getDetailTable().getSelectManager();
    		int rowCount = getDetailTable().getRowCount();
    		int nIndex =0;		
    		for (int i = 0; i < rowCount; i++) {
    			row = getDetailTable().getRow(i);
    			
    			// 变更内容		
    			nIndex = row.getCell(CHANGE_CONTENT).getColumnIndex();
    			if (row.getCell(CHANGE_CONTENT).getValue() == null) {
    				manager.select(i, nIndex);
    				MsgBox.showWarning(this,ChangeAuditUtil.getRes("content"));
    				SysUtil.abort();
    			}
    		}
    	}	
	}
	
	public void checkBeforeAuditOrUnAudit(FDCBillStateEnum state, String warning) throws Exception{

		//检查单据是否在工作流中
		FDCClientUtils.checkBillInWorkflow(this, getSelectBOID());
	
		if (editData.getChangeAudit()!=null) {
			MsgBox.showWarning(this, ChangeAuditUtil.getRes("noUnAudit"));
			abort();
		}
		
		super.checkBeforeAuditOrUnAudit(state, warning);
	}


	protected void prmtContractBill_willShow(SelectorEvent e) throws Exception {
		if(editData.getCurProject()==null){
			//MsgBox.showWarning(EASResource.getString(resourcePath, "SelectCurProj"));
			e.setCanceled(true);
			return;
		}
		KDBizPromptBox f7=(KDBizPromptBox)e.getSource();
		f7.getQueryAgent().resetRuntimeEntityView();
		EntityViewInfo view=new EntityViewInfo();
		FilterInfo filter=new FilterInfo();
		filter.getFilterItems().add(new FilterItemInfo("curProject.id",editData.getCurProject().getId().toString(),CompareType.EQUALS));
		filter.getFilterItems().add(new FilterItemInfo("state", FDCBillStateEnum.AUDITTED_VALUE, CompareType.EQUALS));
		view.setFilter(filter);
		f7.setEntityViewInfo(view);	
		super.prmtContractBill_willShow(e);
	}
	
	public void actionSplit_actionPerformed(ActionEvent e) throws Exception {
		super.actionSplit_actionPerformed(e);
		
		String id = getSelectBOID();
		
		if(id == null) return;
		
		AbstractSplitInvokeStrategy invokeStra = new ConChangeSplitInvokeStrategy(id, this);
        invokeStra.invokeSplit();
	}
	
	protected void initWorkButton() {
		super.initWorkButton();
		btnSplit.setIcon(FDCClientHelper.ICON_SPLIT);
		menuItemSplit.setIcon(FDCClientHelper.ICON_SPLIT);
		//隐藏修改,审批,反审批,删除功能
		//by Cassiel_peng  2009-9-20
		actionViewChangeAudtiAttachment.putValue(Action.SMALL_ICON, EASResource.getIcon("imgTbtn_affixmanage"));
	}

	public void actionVisa_actionPerformed(ActionEvent e) throws Exception {
		checkBeforeVisa();
		//NP
		if(FDCUtils.isRunningWorkflow(this.editData.getId().toString())){
			MsgBox.showWarning("已在工作流处理中，当前任务或执行人不匹配");
			SysUtil.abort();
		}
		UIContext uiContext = new UIContext(this); 
		uiContext.put(UIContext.ID, editData.getId());
		IUIWindow uiWin = UIFactory.createUIFactory(UIFactoryName.MODEL).	create(
				ContractChangeVisaUI.class.getName(),	uiContext, null , OprtState.EDIT);       
		uiWin.show();
		super.actionVisa_actionPerformed(e);
		syncDataFromDB();
		getSecondTable().removeRows();
		loadSecondTable();	
	}
	
	private void checkBeforeVisa() throws Exception{
		if(editData.getState()==null){
			MsgBox.showWarning(this,ChangeAuditUtil.getRes("noVisa"));
			SysUtil.abort();
		}else if(!editData.getState().equals(FDCBillStateEnum.ANNOUNCE)){
			if(isDispatch){
				MsgBox.showWarning(this,ChangeAuditUtil.getRes("noVisa"));
				SysUtil.abort();
			}else if(!editData.getState().equals(FDCBillStateEnum.AUDITTED)){
				MsgBox.showWarning(this,ChangeAuditUtil.getRes("noVisa"));
				SysUtil.abort();
			}
		}
	}
	
	protected KDTable getSecondTable(){
		return tblVisa;
	}
	
	public void afterActionPerformed(ActionEvent e)
	{
		super.afterActionPerformed(e);
		Object source = e.getSource();
		if(source==btnNext||source==btnPre||source==btnFirst||source==btnLast||source==menuItemNext
				||source==menuItemPre||source==menuItemLast||source==menuItemFirst){
			try
			{
				getSecondTable().removeRows();
				loadSecondTable();	
				setOprtState(getOprtState());
			} catch (Exception e1)
			{
				handUIExceptionAndAbort(e1);
			}
		}
		else if(source==btnRemove||source==menuItemRemove){
			try
			{
					getSecondTable().removeRows();
					loadSecondTable();			
			} catch (Exception e1)
			{
				handUIExceptionAndAbort(e1);
			}
		}
		else if(source==btnAddNew||source==menuItemAddNew){
			getSecondTable().removeRows();
		}else if(source==btnSave||source==menuItemSave){
			Boolean isFromWorkflow = (Boolean) getUIContext().get("isFromWorkflow");
	    	if(isFromWorkflow!=null&&isFromWorkflow.booleanValue()){
	    		actionCopy.setEnabled(false);
	    		actionSubmit.setEnabled(false);
	    		actionAddLine.setEnabled(false);
	    		actionRemoveLine.setEnabled(false);
	    		actionInsertLine.setEnabled(false);
	    		actionRemove.setEnabled(false);
	    		lockUIForViewStatus();
	    		actionSplit.setVisible(true);
	    		actionSplit.setEnabled(true);
		    	actionSave.setEnabled(true);
		    	txtBudgetAmount.setEnabled(true);
				txtBudgetAmount.setEditable(true);
				txtBudgetAmount.setAccessAuthority(CtrlCommonConstant.AUTHORITY_COMMON);
	    	}
		}else if(source==btnEdit||source==menuItemEdit){
			prmtCurProject.setEditable(false);
			prmtCreator.setEditable(false);
			prmtAuditor.setEditable(false);
//			prmtMainSupp.setEditable(false);
		}
		if (getOprtState().equals(STATUS_ADDNEW) || getOprtState().equals(STATUS_EDIT)) {
			// 未签证前置空,因为有0签证 by hpw
			this.txtOriBalanceAmount.setValue(null);
			this.txtBalanceAmount.setValue(null);
		}
	}
	
	public void actionSave_actionPerformed(ActionEvent e) throws Exception {
		// modified by zhaoqin for R130924-0329 on 2013/11/14
    	if(StringUtils.isEmpty(txtSpecialtyType.getText()))
    		setSpecialtyName();
		
		//保存前调用一下编码规则生成编码
		this.handleCodingRule();
		super.actionSave_actionPerformed(e);
		Boolean isFromWorkflow = (Boolean) getUIContext().get("isFromWorkflow");
    	if(isFromWorkflow!=null&&isFromWorkflow.booleanValue()){
    		actionCopy.setEnabled(false);
    		actionSubmit.setEnabled(false);
    		actionAddLine.setEnabled(false);
    		actionRemoveLine.setEnabled(false);
    		actionInsertLine.setEnabled(false);
    		actionRemove.setEnabled(false);
    		lockUIForViewStatus();
    		actionSplit.setVisible(true);
    		actionSplit.setEnabled(true);
	    	actionSave.setEnabled(true);
	    	txtBudgetAmount.setEnabled(true);
			txtBudgetAmount.setEditable(true);
			txtBudgetAmount.setAccessAuthority(CtrlCommonConstant.AUTHORITY_COMMON);
    	}
	}
	
	/**
	 * @see com.kingdee.eas.fdc.contract.client.AbstractContractChangeBillEditUI#actionSubmit_actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionSubmit_actionPerformed(ActionEvent e) throws Exception {
		// modified by zhaoqin for R130924-0329 on 2013/11/14
    	if(StringUtils.isEmpty(txtSpecialtyType.getText()))
    		setSpecialtyName();
    	
    	/* modified by zhaoqin for R130711-0070 on 2013/12/3 */
    	checkBeforeSubmit();
		
		//提交前调用一下编码规则生成编码
		this.handleCodingRule();
		super.actionSubmit_actionPerformed(e);
	}
	
	private void loadSecondTable() throws BOSException{
		int unitNum = editData.getEntrys().size();
		for(int i=0;i<unitNum;i++){
			ContractChangeEntryInfo info = editData.getEntrys().get(i);
			loadValues(info);
		}
	}
	
	public void loadValues(ContractChangeEntryInfo info) throws BOSException {
		KDTable table = getSecondTable();
		int i=table.getRowCount();
		table.addRow();
		table.getCell(i, "id").setValue(info.getId());
		table.getCell(i, "number").setValue(info.getNumber());
		table.getCell(i, "isAllExe").setValue(Boolean.valueOf(info.isIsAllExe()));
		table.getCell(i, "isPartExe").setValue(Boolean.valueOf(info.isIsPartExe()));
		table.getCell(i, "isNoExe").setValue(Boolean.valueOf(info.isIsNoExe()));
		table.getCell(i, "discription").setValue(info.getDiscription());
	}
	
	/**
	 * 如单据已经拆分,在修改单据金额的时候给予提示重新拆分
	 */
	boolean amtWarned = false;
	protected void txtBudgetAmount_focusGained(FocusEvent e) throws Exception {
		if(STATUS_EDIT.equals(getOprtState()) && !amtWarned) {
			FDCSplitClientHelper.checkSplitedForAmtChange(this, getSelectBOID(), "T_CON_ConChangeSplit", "FContractChangeID");
			amtWarned = true;
		}
	}
	
	/**
     * 覆盖抽象类处理编码规则的行为,统一在FDCBillEditUI.handCodingRule方法中处理
     */
    protected void setAutoNumberByOrg(String orgType) {    	
    }
    
    protected void handleActionStatusByCurOrg() {		
	}
    
    public void actionAudit_actionPerformed(ActionEvent e) throws Exception {
    	/* modified by zhaoqin for R130711-0070 on 2013/12/3 */
    	checkBeforeSubmit();
    	
    	super.actionAudit_actionPerformed(e);
    	actionUnAudit.setVisible(true);
    	actionUnAudit.setEnabled(true);
		actionAudit.setVisible(false);
    }
    
    public void actionUnAudit_actionPerformed(ActionEvent e) throws Exception {
    	super.actionUnAudit_actionPerformed(e);
    	actionAudit.setVisible(true);
    	actionAudit.setEnabled(true);
    	actionUnAudit.setVisible(false);
    }
    
    protected void updateButtonStatus() {
    	super.updateButtonStatus();
    	
//		 如果是虚体成本中心，则不能拆分
		if (!SysContext.getSysContext().getCurrentCostUnit().isIsBizUnit()) {
			actionSplit.setEnabled(false);
			actionSplit.setVisible(false);
		}
		if(!isDispatch){
			actionDisPatch.setVisible(false);
			actionDisPatch.setEnabled(false);
		}
		if(isTransformVisa){
			contChangeReason.setBoundLabelText(getRes("visaReason"));
			contChangeSubject.setBoundLabelText(getRes("visaSubject"));
			contCbPeriod.setBoundLabelText(getRes("visaPeriod"));
			contAuditNumber.setBoundLabelText(getRes("changeAuditNo"));
			btnVisa.setText(getRes("visaConfirm"));
			menuItemVisa.setText(getRes("visaConfirm"));
			kdtEntrys.getHeadRow(0).getCell(0).setValue(getRes("visaContentNo"));
			kdtEntrys.getHeadRow(0).getCell(1).setValue(getRes("visaContent"));
		}
    }
    
    protected boolean isUseMainMenuAsTitle(){
    	return false;
    }
    
    protected  void fetchInitData() throws Exception{
    	//合同Id
		String contractBillId = (String) getUIContext().get("contractBillId");
	if(contractBillId==null){
//		String changeId = (String) getUIContext().get("ID"); 将BOSUuid强制类型转换为String会ClassCastException
		Object tempId = getUIContext().get("ID");
		if(tempId!=null){
			String changeId=tempId.toString();
			if(changeId!=null){
				FDCSQLBuilder builder=new FDCSQLBuilder();
				builder.appendSql("select fcontractBillId from T_CON_ContractchangeBill where fid=?");
				builder.addParam(changeId);
				IRowSet rowSet=builder.executeQuery();
				if(rowSet.size()==1){
					rowSet.next();
					getUIContext().put("contractBillId", rowSet.getString("fcontractBillId"));
				}
			}
		}
	}
	super.fetchInitData();}
    
	/**
    *
    * 描述：检查是否有关联对象
    * @author:liupd
    * 创建时间：2006-8-26 <p>
    */
   protected void checkRef(String id) throws Exception {
		FilterInfo filterSett = new FilterInfo();
		filterSett.getFilterItems().add(
				new FilterItemInfo("contractChange.id", id));
		filterSett.getFilterItems().add(
				new FilterItemInfo("state", FDCBillStateEnum.INVALID_VALUE,
						CompareType.NOTEQUALS));
		boolean hasSettleSplit = false;
		if (ConChangeSplitFactory.getRemoteInstance().exists(filterSett)
				|| ConChangeNoCostSplitFactory.getRemoteInstance()
				.exists(filterSett)) {
			hasSettleSplit = true;
		}
		if (hasSettleSplit) {
			MsgBox.showWarning("变更单已经拆分,不能反审批!");
			SysUtil.abort();
			return;
		}
   }
   
   /**
    * output txtBudgetOriAmount_dataChanged method
    */
   protected void txtBudgetOriAmount_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
   {
	   calLocalAmt();
   }

   /**
    * output txtExRate_dataChanged method
    */
   protected void txtExRate_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
   {
	   calLocalAmt();
   }
   
   
   private void calLocalAmt() {
      	if(txtBudgetOriAmount.getBigDecimalValue() != null && txtExRate.getBigDecimalValue() != null) {
	   		BigDecimal lAmount = txtBudgetOriAmount.getBigDecimalValue().multiply(txtExRate.getBigDecimalValue());
	   		txtBudgetAmount.setNumberValue(lAmount);
	   	}
	   	else {
	   		txtBudgetAmount.setNumberValue(null);
	   	}
   }
   
   public void actionPrint_actionPerformed(ActionEvent e) throws Exception {
		ArrayList idList = new ArrayList();
		if (editData != null && !StringUtils.isEmpty(editData.getString("id"))) {
			idList.add(editData.getString("id"));
		}
		if (idList == null || idList.size() == 0 || getTDQueryPK() == null
				|| getTDFileName() == null) {
			MsgBox.showWarning(this, FDCClientUtils.getRes("cantPrint"));
			return;
		}
		/*ContractChangeBillDataProvider data = new ContractChangeBillDataProvider(
				editData.getString("id"), getTDQueryPK());*/
		
		ContractChangeBillProvider data = new ContractChangeBillProvider(
				editData.getString("id"), getTDQueryPK(),getATTCHQueryPK());
		com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper appHlp = new com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper();
		appHlp.print(getTDFileName(), data, javax.swing.SwingUtilities
				.getWindowAncestor(this));
	}

	public void actionPrintPreview_actionPerformed(ActionEvent e)
			throws Exception {
		ArrayList idList = new ArrayList();
		if (editData != null && !StringUtils.isEmpty(editData.getString("id"))) {
			idList.add(editData.getString("id"));
		}
		if (idList == null || idList.size() == 0 || getTDQueryPK() == null
				|| getTDFileName() == null) {
			MsgBox.showWarning(this, FDCClientUtils.getRes("cantPrint"));
			return;
		}
		ContractChangeBillDataProvider data = new ContractChangeBillDataProvider(
				editData.getString("id"), getTDQueryPK());
//		ContractChangeBillProvider data = new ContractChangeBillProvider(
//				editData.getString("id"), getTDQueryPK(),getATTCHQueryPK());
		com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper appHlp = new com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper();
		appHlp.printPreview(getTDFileName(), data, javax.swing.SwingUtilities
				.getWindowAncestor(this));
	}

	/**
	 * 套打对应的目录
	 */
	protected String getTDFileName() {
		return "/bim/fdc/contract/contractChangebill";
	}

	/**
	 *  套打对应的Query
	 */
	protected IMetaDataPK getTDQueryPK() {
//		return new MetaDataPK(
//				"com.kingdee.eas.fdc.contract.app.ContractChangeForPrintQuery");
		return new MetaDataPK(
		"com.kingdee.eas.fdc.contract.app.ContractChangeBillPrintQuery");
	}
	
	// 对应的套打Query
	protected IMetaDataPK getATTCHQueryPK() {
		return new MetaDataPK(
				"com.kingdee.eas.fdc.contract.app.AttchmentForPrintQuery");
	}
	
	private void initOprtBtn() throws Exception {
		// 分录增、删按钮调整至分录上方
		JButton btnAddRuleNew = ctnEntrys.add(actionAddLine);
		JButton btnDelRuleNew = ctnEntrys.add(actionRemoveLine);
		btnAddRuleNew.setIcon(EASResource.getIcon("imgTbtn_addline"));
		btnAddRuleNew.setToolTipText(getRes("addVisaRule"));
		btnAddRuleNew.setText(getRes("addVisaRule"));
		btnAddRuleNew.setSize(10, 10);
		btnDelRuleNew.setIcon(EASResource.getIcon("imgTbtn_deleteline"));
		btnDelRuleNew.setToolTipText(getRes("delVisaRule"));
		btnDelRuleNew.setText(getRes("delVisaRule"));
		btnDelRuleNew.setSize(10, 10);
		if(editData.getChangeAudit()==null&&(STATUS_EDIT.equals(getOprtState())||STATUS_ADDNEW.equals(getOprtState()))){
			btnAddRuleNew.setEnabled(true);
			btnDelRuleNew.setEnabled(true);
		}else{
			btnAddRuleNew.setEnabled(false);
			btnDelRuleNew.setEnabled(false);
		}
		
/*		ctnExecute.setLayout(new BorderLayout());
		this.ctnExecute.add(this.tblVisa, BorderLayout.CENTER);
		JPanel panel=new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(this.contThisTime);
		panel.add(this.contCondition);
		this.ctnExecute.add(panel, BorderLayout.SOUTH);*/
		
        //ctnExecute
        ctnExecute.getContentPane().setLayout(new KDLayout());
        //TODO 由于该容器采用KDLayout布局，请在下面一条语句中修正该容器的初始大小：
        ctnExecute.getContentPane().putClientProperty("OriginalBounds", new Rectangle(0, 0, 963, 220));
        tblVisa.setBounds(new Rectangle(0, 0, 963, 183));
        ctnExecute.getContentPane().add(tblVisa, new KDLayout.Constraints(0, 0, 963, 183, KDLayout.Constraints.ANCHOR_TOP|KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        contThisTime.setBounds(new Rectangle(0, 190, 446, 19));
        ctnExecute.getContentPane().add(contThisTime, new KDLayout.Constraints(0, 190, 446, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contCondition.setBounds(new Rectangle(516, 190, 446, 19));
        ctnExecute.getContentPane().add(contCondition, new KDLayout.Constraints(516, 190, 446, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        //contThisTime
/*        contThisTime.setBoundEditor(txtThisTime);
        //contCondition
        contCondition.setBoundEditor(txtCondition);*/
	}
	
	protected void fetchInitParam() throws Exception {
		super.fetchInitParam();
		HashMap param = FDCUtils.getDefaultFDCParam(null,orgUnitInfo.getId().toString());		
		if(param.get(FDCConstants.FDC_PARAM_SELECTPERSON)!=null){
			canSelectOtherOrgPerson = Boolean.valueOf(param.get(FDCConstants.FDC_PARAM_SELECTPERSON).toString()).booleanValue();
		}
		if(param.get(FDCConstants.FDC_PARAM_ALLOWDISPATCH)!=null){
			isDispatch = Boolean.valueOf(param.get(FDCConstants.FDC_PARAM_ALLOWDISPATCH).toString()).booleanValue();
		}
		if(param.get(FDCConstants.FDC_PARAM_ALLOWDISPATCH)!=null){
			isTransformVisa = Boolean.valueOf(param.get(FDCConstants.FDC_PARAM_AUTOCHANGETOPROJECTVISA).toString()).booleanValue();
		}
		if(param.get(FDCConstants.FDC_PARAM_ISREQUIREDFORASKANDCONSTRCTION)!=null){
			isOfferAndConstrReq = Boolean.valueOf(param.get(FDCConstants.FDC_PARAM_ISREQUIREDFORASKANDCONSTRCTION).toString()).booleanValue();
		}
	}
	private String getRes(String resName){
		return ChangeAuditUtil.getRes(resName);
	}
	protected void comboOffer_itemStateChanged(ItemEvent e) throws Exception {
		if (e!=null && e.getStateChange() == ItemEvent.SELECTED) {
			OfferEnum offer = (OfferEnum)e.getItem();
			if(OfferEnum.SELFCOM.equals(offer)){
				prmtConductDept.setEnabled(true);
				prmtConductDept.setRequired(true);
				
				prmtConductUnit.setEnabled(false);
				prmtConductUnit.setRequired(false);
				prmtConductUnit.setValue(null);
			}else{
				prmtConductUnit.setEnabled(true);
				prmtConductUnit.setRequired(true);
				
				prmtConductDept.setRequired(false);
				prmtConductDept.setValue(null);
				prmtConductDept.setEnabled(false);
			}
		}
	}
	
	public void actionViewAttachment_actionPerformed(ActionEvent e)
			throws Exception {
		String attachId = null;
    	if(this.cmbAttachment.getSelectedItem() != null && this.cmbAttachment.getSelectedItem() instanceof AttachmentInfo){
    		attachId = ((AttachmentInfo)this.cmbAttachment.getSelectedItem()).getId().toString();
    		AttachmentClientManager acm = AttachmentManagerFactory.getClientManager();
    		acm.viewAttachment(attachId);
    	}
	}
	
	public void fillAttachmentList(){
		this.cmbAttachment.removeAllItems();
		String boId = null;
		if(this.editData.getId() == null){
			return;
		}else{
			boId = this.editData.getId().toString();
		}
		
		try {
			this.cmbAttachment.addItems(AttachmentUtils.getAttachmentListByBillID(boId).toArray());
			this.btnViewAttachment.setEnabled(this.cmbAttachment.getItemCount() > 0);
		} catch (BOSException e) {
			handUIExceptionAndAbort(e);
		}
	}
	
	protected void lockContainer(Container container) {
		if (lblAttachmentContainer.getName().equals(container.getName())) {
			return;
		} else {
			super.lockContainer(container);
		}
	}
	
	/**
	 * 上查
	 * @see com.kingdee.eas.framework.client.CoreBillEditUI#actionTraceUp_actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionTraceUp_actionPerformed(ActionEvent e) throws Exception {
		//super.actionTraceUp_actionPerformed(e);
		if (editData != null && editData.getId() != null && editData.getChangeAudit() != null) {
			String sourceBillID = editData.getChangeAudit().getId().toString();
			UIContext uiContext = new UIContext(this);
			uiContext.put(UIContext.ID, sourceBillID);
			IUIWindow uiWindow = null;
			uiWindow = UIFactory.createUIFactory(UIFactoryName.MODEL).create(ChangeAuditEditUI.class.getName(), uiContext, null,
					OprtState.VIEW);
			uiWindow.show();
		} else {
			MsgBox.showWarning(this, "源单据不存在!");
		}
	}
	
	/**
	 * 查看框架合约
	 */
	public void actionViewContract_actionPerformed(ActionEvent e) throws Exception {
		IUIWindow uiWindow = null;
		UIContext uiContext = new UIContext(this);
		if (editData.getContractBill() != null) {
			SelectorItemCollection sic = new SelectorItemCollection();
			sic.add("*");
			sic.add("programmingContract.*");
			ContractBillInfo contractBillInfo = ContractBillFactory.getRemoteInstance().getContractBillInfo(
					new ObjectUuidPK(editData.getContractBill().getId()), sic);
			uiContext.put("contractBillInfo", contractBillInfo);
			uiWindow = UIFactory.createUIFactory(UIFactoryName.MODEL).create(ContractBillLinkProgContEditUI.class.getName(), uiContext,
					null, OprtState.VIEW);
			uiWindow.show();
		}
	}
	
	public boolean useScrollPane() {
		return true;
	}

	public int getVerticalScrollPolicy() {

		return ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
	}

	public int getHorizontalScrollPolicy() {
		return ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
	}
	
	/**
	 * 设置专业类型名称 - for R130924-0329
	 * @author zhaoqin
	 * @date 2013/11/14
	 */
	private void setSpecialtyName() {
		kdtSpecialtyType.removeRows();
		txtSpecialtyType.setText("");
		Object[] object = (Object[]) prmtSpecialtyType.getData();
		if(null == object)
			return;
		
		StringBuffer specialtyType = new StringBuffer();
		for (int i = 0; i < object.length; i++) {
			kdtSpecialtyType.addRow().getCell("specialtyTypeID").setValue(object[i]);
			specialtyType.append(object[i] + ";");
		}
		txtSpecialtyType.setText(specialtyType.toString());
	}
	
	/**
	 * R130711-0070:合约规划的规划金额控制
	 * @throws EASBizException
	 * @throws BOSException
	 * @author zhaoqin
	 * @date 2013/12/3
	 */
	private void checkBeforeSubmit() throws EASBizException, BOSException {
		boolean isDispatch = FDCUtils.getBooleanValue4FDCParamByKey(null, null,
				FDCConstants.FDC_PARAM_ALLOWDISPATCH);
		// 不启用变更下发  
		if(!isDispatch && !isCheckCtrlAmountPass()) {
			SysUtil.abort();
		}
	}
	
	/**
	 * 对于测算金额 + 签约金额 + 累计变更 > 框架合约 的 规划余额 的参数控制策略
	 * @throws Exception
	 * @author zhaoqin
	 * @date 2013/12/3
	 */
	private boolean isCheckCtrlAmountPass() throws EASBizException, BOSException {
		if (null == this.editData.getId()) {
			// 未保存直接提交
			throw new EASBizException(new NumericExceptionSubItem("1", "请先保存再提交"));
		}

		String orgunitid = null;
		// 工程项目对应的成本中心id
		if(null != editData.getCurProject().getCostCenter())
			orgunitid = editData.getCurProject().getCostCenter().getId().toString();
		String paramValue = FDCUtils.getFDCParamByKey(null, orgunitid, FDCConstants.FDC_PARAM_CONTRACT_PROGRAM_AMOUNT);

		if ("2".equals(paramValue))	// 不控制或无参数
			return true;
		
		//预算原币金额
		BigDecimal amount = this.editData.getAmount();

		String contractBillId = this.editData.getContractBill().getId().toString();
		SelectorItemCollection selectors = new SelectorItemCollection();
		selectors.add("id");
		selectors.add("number");
		selectors.add("programmingContract.balance");
		selectors.add("programmingContract.id");
		ContractBillInfo contractBill = ContractBillFactory.getRemoteInstance().
				getContractBillInfo(new ObjectUuidPK(BOSUuid.read(contractBillId)),selectors);
		//规划余额
		BigDecimal balance = contractBill.getProgrammingContract() == null ? new BigDecimal(0) :contractBill.getProgrammingContract().getBalance();
		if(FDCHelper.compareTo(amount, balance) <= 0) 
			return true;
		
		String result = "合同[" + contractBill.getNumber() + "]\n变更测算金额(" + amount
				+ ")大于框架合约的规划余额(" + balance + ")，是否提交?\n\n";
		if ("0".equals(paramValue)) {	// 严格控制
			AdvMsgBox.createAdvMsgBox(this, "", "不允许提交，请查看详细信息", result, AdvMsgBox.INFORMATION_MESSAGE, 
					AdvMsgBox.DETAIL_OK_OPTION).show();
			return false;
		} else if ("1".equals(paramValue)) {	// 提示控制
			return MsgBox.YES == MsgBox.showConfirm3a(this, "校验不通过，请查看详细信息。\n是否提交？", result);
		}
		return true;
	}
}