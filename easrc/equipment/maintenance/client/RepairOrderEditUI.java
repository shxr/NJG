/**
 * output package name
 */
package com.kingdee.eas.port.equipment.maintenance.client;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.ctrl.extendcontrols.BizDataFormat;
import com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox;
import com.kingdee.bos.ctrl.kdf.table.ICell;
import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.KDTDefaultCellEditor;
import com.kingdee.bos.ctrl.kdf.table.KDTSelectBlock;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.kdf.table.event.KDTMouseEvent;
import com.kingdee.bos.ctrl.kdf.util.render.ObjectValueRender;
import com.kingdee.bos.ctrl.swing.KDPanel;
import com.kingdee.bos.ctrl.swing.KDTextField;
import com.kingdee.bos.ctrl.swing.KDWorkButton;
import com.kingdee.bos.ctrl.swing.event.DataChangeEvent;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.ui.face.IUIWindow;
import com.kingdee.bos.ui.face.UIFactory;
import com.kingdee.bos.ui.face.UIRuleUtil;
import com.kingdee.eas.base.message.client.PersonSelectUI;
import com.kingdee.eas.basedata.master.material.client.F7MaterialSimpleSelector;
import com.kingdee.eas.basedata.org.CtrlUnitInfo;
import com.kingdee.eas.basedata.person.IPerson;
import com.kingdee.eas.basedata.person.PersonFactory;
import com.kingdee.eas.basedata.person.PersonInfo;
import com.kingdee.eas.basedata.person.client.FIPersonPromptBox;
import com.kingdee.eas.basedata.person.client.PersonF7UI;
import com.kingdee.eas.basedata.person.client.PersonPromptBox;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.common.client.OprtState;
import com.kingdee.eas.common.client.SysContext;
import com.kingdee.eas.common.client.UIContext;
import com.kingdee.eas.common.client.UIFactoryName;
import com.kingdee.eas.cp.bc.BizCollUtil;
import com.kingdee.eas.fm.gnt.common.PersonF7;
import com.kingdee.eas.hr.emp.client.EmployeeMultiF7PromptBox;
import com.kingdee.eas.hr.emp.client.PersonMultiPromptBox;
import com.kingdee.eas.port.equipment.record.EquIdInfo;
import com.kingdee.eas.port.equipment.record.client.EquIdEditUI;
import com.kingdee.eas.port.equipment.uitl.ToolHelp;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.eas.util.client.EASResource;
import com.kingdee.eas.util.client.MsgBox;
import com.kingdee.eas.xr.app.XRBillStatusEnum;
import com.kingdee.eas.xr.helper.PersonXRHelper;
import com.kingdee.eas.xr.helper.Tool;

/**
 * output class name
 */
public class RepairOrderEditUI extends AbstractRepairOrderEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(RepairOrderEditUI.class);
    
    /**
     * output class constructor
     */
    public RepairOrderEditUI() throws Exception
    {
        super();
    }
    /**
     * output loadFields method
     */
    public void loadFields()
    {
        super.loadFields();
        
        try {
			loadFieldsRepairPersonValue();
		} catch (EASBizException e) {
			e.printStackTrace();
		} catch (BOSException e) {
			e.printStackTrace();
		}
    }

    /**
     * output storeFields method
     */
    public void storeFields()
    {
    	storeRepairPersonValue();
        super.storeFields();
    }
    
    
    private void storeRepairPersonValue()
    {
    	for (int i = 0; i < kdtE1.getRowCount(); i++)
    	{
    		IRow row = this.kdtE1.getRow(i);
    		
    		if(UIRuleUtil.isNull(row.getCell("repairPerson").getValue()))
    			continue;
    		
    		Object obj = row.getCell("repairPerson").getValue();
    		String sourPersonId = null;
    		if(obj instanceof Object[])
    		{
    			Object[] newObj = (Object[]) obj;
    			for (int j = 0; j < newObj.length; j++)
    			{
    				PersonInfo personInfo = (PersonInfo)newObj[j];
    				if(sourPersonId==null)
    					sourPersonId =personInfo.getId().toString();
    				else
    					sourPersonId =sourPersonId+";"+personInfo.getId().toString();
    			}
    		}
    		row.getCell("repPersonID").setValue(sourPersonId);
    	}
    }
    
    private void loadFieldsRepairPersonValue() throws BOSException, EASBizException
    {
    	IPerson IPerson = PersonFactory.getRemoteInstance();
    	for (int i = 0; i < kdtE1.getRowCount(); i++)
    	{
    		IRow row = this.kdtE1.getRow(i);
    		
    		if(UIRuleUtil.isNull(row.getCell("repPersonID").getValue()))
    			continue;
    		String repPersonId = UIRuleUtil.getString(row.getCell("repPersonID").getValue());
    		
    		String personId[] = repPersonId.split(";");
    		Object obj[] = new Object[personId.length];
    		for (int j = 0; j < personId.length; j++) 
    		{
    			String id = personId[j];
    			if(UIRuleUtil.isNull(id))
    				continue;
    			obj[j] = IPerson.getPersonInfo(new ObjectUuidPK(id));
			}
    		row.getCell("repairPerson").setValue(obj);
    	}
    }
    

    /**
     * output btnAddLine_actionPerformed method
     */
    protected void btnAddLine_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
        super.btnAddLine_actionPerformed(e);
    }

    /**
     * output menuItemEnterToNextRow_itemStateChanged method
     */
    protected void menuItemEnterToNextRow_itemStateChanged(java.awt.event.ItemEvent e) throws Exception
    {
        super.menuItemEnterToNextRow_itemStateChanged(e);
    }

    /**
     * output actionPageSetup_actionPerformed
     */
    public void actionPageSetup_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionPageSetup_actionPerformed(e);
    }

    /**
     * output actionExitCurrent_actionPerformed
     */
    public void actionExitCurrent_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionExitCurrent_actionPerformed(e);
    }

    /**
     * output actionHelp_actionPerformed
     */
    public void actionHelp_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionHelp_actionPerformed(e);
    }

    /**
     * output actionAbout_actionPerformed
     */
    public void actionAbout_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionAbout_actionPerformed(e);
    }

    /**
     * output actionOnLoad_actionPerformed
     */
    public void actionOnLoad_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionOnLoad_actionPerformed(e);
    }

    /**
     * output actionSendMessage_actionPerformed
     */
    public void actionSendMessage_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionSendMessage_actionPerformed(e);
    }

    /**
     * output actionCalculator_actionPerformed
     */
    public void actionCalculator_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionCalculator_actionPerformed(e);
    }

    /**
     * output actionExport_actionPerformed
     */
    public void actionExport_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionExport_actionPerformed(e);
    }

    /**
     * output actionExportSelected_actionPerformed
     */
    public void actionExportSelected_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionExportSelected_actionPerformed(e);
    }

    /**
     * output actionRegProduct_actionPerformed
     */
    public void actionRegProduct_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionRegProduct_actionPerformed(e);
    }

    /**
     * output actionPersonalSite_actionPerformed
     */
    public void actionPersonalSite_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionPersonalSite_actionPerformed(e);
    }

    /**
     * output actionProcductVal_actionPerformed
     */
    public void actionProcductVal_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionProcductVal_actionPerformed(e);
    }

    /**
     * output actionExportSave_actionPerformed
     */
    public void actionExportSave_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionExportSave_actionPerformed(e);
    }

    /**
     * output actionExportSelectedSave_actionPerformed
     */
    public void actionExportSelectedSave_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionExportSelectedSave_actionPerformed(e);
    }

    /**
     * output actionKnowStore_actionPerformed
     */
    public void actionKnowStore_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionKnowStore_actionPerformed(e);
    }

    /**
     * output actionAnswer_actionPerformed
     */
    public void actionAnswer_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionAnswer_actionPerformed(e);
    }

    /**
     * output actionRemoteAssist_actionPerformed
     */
    public void actionRemoteAssist_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionRemoteAssist_actionPerformed(e);
    }

    /**
     * output actionPopupCopy_actionPerformed
     */
    public void actionPopupCopy_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionPopupCopy_actionPerformed(e);
    }

    /**
     * output actionHTMLForMail_actionPerformed
     */
    public void actionHTMLForMail_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionHTMLForMail_actionPerformed(e);
    }

    /**
     * output actionExcelForMail_actionPerformed
     */
    public void actionExcelForMail_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionExcelForMail_actionPerformed(e);
    }

    /**
     * output actionHTMLForRpt_actionPerformed
     */
    public void actionHTMLForRpt_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionHTMLForRpt_actionPerformed(e);
    }

    /**
     * output actionExcelForRpt_actionPerformed
     */
    public void actionExcelForRpt_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionExcelForRpt_actionPerformed(e);
    }

    /**
     * output actionLinkForRpt_actionPerformed
     */
    public void actionLinkForRpt_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionLinkForRpt_actionPerformed(e);
    }

    /**
     * output actionPopupPaste_actionPerformed
     */
    public void actionPopupPaste_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionPopupPaste_actionPerformed(e);
    }

    /**
     * output actionToolBarCustom_actionPerformed
     */
    public void actionToolBarCustom_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionToolBarCustom_actionPerformed(e);
    }

    /**
     * output actionCloudFeed_actionPerformed
     */
    public void actionCloudFeed_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionCloudFeed_actionPerformed(e);
    }

    /**
     * output actionCloudShare_actionPerformed
     */
    public void actionCloudShare_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionCloudShare_actionPerformed(e);
    }

    /**
     * output actionCloudScreen_actionPerformed
     */
    public void actionCloudScreen_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionCloudScreen_actionPerformed(e);
    }

    /**
     * output actionSave_actionPerformed
     */
    public void actionSave_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionSave_actionPerformed(e);
    }

    /**
     * output actionSubmit_actionPerformed
     */
    public void actionSubmit_actionPerformed(ActionEvent e) throws Exception
    {
    	if(UIRuleUtil.isNull(txtacceptSituation.getText())){
    		MsgBox.showInfo("验收情况不能为空!");
  			SysUtil.abort();
    	}
    	if(UIRuleUtil.isNull(pkactualEndTime.getValue())){
    		MsgBox.showInfo("实际完工日期不能为空!");
    	}
        super.actionSubmit_actionPerformed(e);
    }

    /**
     * output actionCancel_actionPerformed
     */
    public void actionCancel_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionCancel_actionPerformed(e);
    }

    /**
     * output actionCancelCancel_actionPerformed
     */
    public void actionCancelCancel_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionCancelCancel_actionPerformed(e);
    }

    /**
     * output actionFirst_actionPerformed
     */
    public void actionFirst_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionFirst_actionPerformed(e);
    }

    /**
     * output actionPre_actionPerformed
     */
    public void actionPre_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionPre_actionPerformed(e);
    }

    /**
     * output actionNext_actionPerformed
     */
    public void actionNext_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionNext_actionPerformed(e);
    }

    /**
     * output actionLast_actionPerformed
     */
    public void actionLast_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionLast_actionPerformed(e);
    }

    /**
     * output actionPrint_actionPerformed
     */
    public void actionPrint_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionPrint_actionPerformed(e);
    }

    /**
     * output actionPrintPreview_actionPerformed
     */
    public void actionPrintPreview_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionPrintPreview_actionPerformed(e);
    }

    /**
     * output actionCopy_actionPerformed
     */
    public void actionCopy_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionCopy_actionPerformed(e);
    }

    /**
     * output actionAddNew_actionPerformed
     */
    public void actionAddNew_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionAddNew_actionPerformed(e);
    }

    /**
     * output actionEdit_actionPerformed
     */
    public void actionEdit_actionPerformed(ActionEvent e) throws Exception
    {
    	if(editData.getStatus().equals(XRBillStatusEnum.DELETED)){
    		MsgBox.showInfo("单据已作废，不允许修改!");
  			SysUtil.abort();
    	}
        super.actionEdit_actionPerformed(e);
    }

    /**
     * output actionRemove_actionPerformed
     */
    public void actionRemove_actionPerformed(ActionEvent e) throws Exception
    {
    	if(editData.getStatus().equals(XRBillStatusEnum.DELETED)){
    		MsgBox.showInfo("不允许删除作废单据!");
  			SysUtil.abort();
    	}
        super.actionRemove_actionPerformed(e);
    }

    /**
     * output actionAttachment_actionPerformed
     */
    public void actionAttachment_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionAttachment_actionPerformed(e);
    }

    /**
     * output actionSubmitOption_actionPerformed
     */
    public void actionSubmitOption_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionSubmitOption_actionPerformed(e);
    }

    /**
     * output actionReset_actionPerformed
     */
    public void actionReset_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionReset_actionPerformed(e);
    }

    /**
     * output actionMsgFormat_actionPerformed
     */
    public void actionMsgFormat_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionMsgFormat_actionPerformed(e);
    }

    /**
     * output actionAddLine_actionPerformed
     */
    public void actionAddLine_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionAddLine_actionPerformed(e);
    }

    /**
     * output actionCopyLine_actionPerformed
     */
    public void actionCopyLine_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionCopyLine_actionPerformed(e);
    }

    /**
     * output actionInsertLine_actionPerformed
     */
    public void actionInsertLine_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionInsertLine_actionPerformed(e);
    }

    /**
     * output actionRemoveLine_actionPerformed
     */
    public void actionRemoveLine_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionRemoveLine_actionPerformed(e);
    }

    /**
     * output actionCreateFrom_actionPerformed
     */
    public void actionCreateFrom_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionCreateFrom_actionPerformed(e);
    }

    /**
     * output actionCopyFrom_actionPerformed
     */
    public void actionCopyFrom_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionCopyFrom_actionPerformed(e);
    }

    /**
     * output actionAuditResult_actionPerformed
     */
    public void actionAuditResult_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionAuditResult_actionPerformed(e);
    }

    /**
     * output actionTraceUp_actionPerformed
     */
    public void actionTraceUp_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionTraceUp_actionPerformed(e);
    }

    /**
     * output actionTraceDown_actionPerformed
     */
    public void actionTraceDown_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionTraceDown_actionPerformed(e);
    }

    /**
     * output actionViewSubmitProccess_actionPerformed
     */
    public void actionViewSubmitProccess_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionViewSubmitProccess_actionPerformed(e);
    }

    /**
     * output actionViewDoProccess_actionPerformed
     */
    public void actionViewDoProccess_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionViewDoProccess_actionPerformed(e);
    }

    /**
     * output actionMultiapprove_actionPerformed
     */
    public void actionMultiapprove_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionMultiapprove_actionPerformed(e);
    }

    /**
     * output actionNextPerson_actionPerformed
     */
    public void actionNextPerson_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionNextPerson_actionPerformed(e);
    }

    /**
     * output actionStartWorkFlow_actionPerformed
     */
    public void actionStartWorkFlow_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionStartWorkFlow_actionPerformed(e);
    }

    /**
     * output actionVoucher_actionPerformed
     */
    public void actionVoucher_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionVoucher_actionPerformed(e);
    }

    /**
     * output actionDelVoucher_actionPerformed
     */
    public void actionDelVoucher_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionDelVoucher_actionPerformed(e);
    }

    /**
     * output actionWorkFlowG_actionPerformed
     */
    public void actionWorkFlowG_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionWorkFlowG_actionPerformed(e);
    }

    /**
     * output actionCreateTo_actionPerformed
     */
    public void actionCreateTo_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionCreateTo_actionPerformed(e);
    }

    /**
     * output actionSendingMessage_actionPerformed
     */
    public void actionSendingMessage_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionSendingMessage_actionPerformed(e);
    }

    /**
     * output actionSignature_actionPerformed
     */
    public void actionSignature_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionSignature_actionPerformed(e);
    }

    /**
     * output actionWorkflowList_actionPerformed
     */
    public void actionWorkflowList_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionWorkflowList_actionPerformed(e);
    }

    /**
     * output actionViewSignature_actionPerformed
     */
    public void actionViewSignature_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionViewSignature_actionPerformed(e);
    }

    /**
     * output actionSendMail_actionPerformed
     */
    public void actionSendMail_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionSendMail_actionPerformed(e);
    }

    /**
     * output actionLocate_actionPerformed
     */
    public void actionLocate_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionLocate_actionPerformed(e);
    }

    /**
     * output actionNumberSign_actionPerformed
     */
    public void actionNumberSign_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionNumberSign_actionPerformed(e);
    }

    /**
     * output actionAudit_actionPerformed
     */
    public void actionAudit_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionAudit_actionPerformed(e);
    }

    /**
     * output actionUnAudit_actionPerformed
     */
    public void actionUnAudit_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionUnAudit_actionPerformed(e);
    }

    /**
     * output getBizInterface method
     */
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return com.kingdee.eas.port.equipment.maintenance.RepairOrderFactory.getRemoteInstance();
    }

    /**
     * output createNewDetailData method
     */
    protected IObjectValue createNewDetailData(KDTable table)
    {
		
        return null;
    }

    /**
     * output createNewData method
     */
    protected com.kingdee.bos.dao.IObjectValue createNewData()
    {
        com.kingdee.eas.port.equipment.maintenance.RepairOrderInfo objectValue = new com.kingdee.eas.port.equipment.maintenance.RepairOrderInfo();
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));
      
    	Tool.checkGroupAddNew();
    	
    	try {
			objectValue.setBizDate(SysUtil.getAppServerTime(null));
		} catch (EASBizException e) {
			e.printStackTrace();
		}
		objectValue.setCU(SysContext.getSysContext().getCurrentCtrlUnit());
		
		PersonInfo personInfo = com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUserInfo().getPerson();
		if(personInfo==null)
		{
			MsgBox.showWarning("当前用户不是职员用户，不能执行此操作！");SysUtil.abort();
		}
		objectValue.setRepairPerson(personInfo);
//		objectValue.setRepairDepart(PersonXRHelper.getPosiMemByDeptUser(personInfo));
        return objectValue;
    }
	@Override
	protected void attachListeners() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void detachListeners() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected KDTextField getNumberCtrl() {
		// TODO Auto-generated method stub
		return null;
	}

	protected void initWorkButton() {
		super.initWorkButton();
		btnequInfomation.setIcon(EASResource.getIcon("imgTbtn_list"));
	}
	
	public void onLoad() throws Exception {
		prmtslDepart.setEnabled(false);
		this.kdtE1.getColumn("seq").getStyleAttributes().setHided(true);
		this.kdtE1.getColumn("FaLocation").getStyleAttributes().setHided(true);
		this.kdtE1.getColumn("yujingDate").getStyleAttributes().setHided(true);
//		this.kdtE1.getColumn("xiulirenyuan").getStyleAttributes().setHided(true);
		this.kdtE1.getColumn("repPersonID").getStyleAttributes().setHided(true);
		
		txtmaintenanceProgram.setVisible(false);
		chkselfStudy.setVisible(false);
		chkoutsourcing.setVisible(false);
		txtselfAmount.setVisible(false);
		contselfAmount.setVisible(false);
		txtoutAmount.setVisible(false);
		contoutAmount.setVisible(false);
		contmaintenanceProgram.setVisible(false);
		prmtequName.setVisible(false);
		contequName.setVisible(false);
		txtequModel.setVisible(false);
		contequModel.setVisible(false);
		txtequAddress.setVisible(false);
		contequAddress.setVisible(false);
		pkrepairDate.setVisible(false);
		contrepairDate.setVisible(false);
		pktransferTime.setVisible(false);
		conttransferTime.setVisible(false);
		btnequInfomation.setVisible(false);
		contslDepart.setVisible(false);
		prmtslDepart.setVisible(false);
		if(getOprtState().equals(OprtState.ADDNEW)){
		    txtselfAmount.setEnabled(false);
		    txtoutAmount.setEnabled(false);
		}
		super.onLoad();
		this.setUITitle("维保任务单");
		 EntityViewInfo evi = new EntityViewInfo();
		 FilterInfo filter = new FilterInfo();
		 String id = SysContext.getSysContext().getCurrentCtrlUnit().getId().toString();
		 DateFormat FORMAT_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   Date date = null;
		    try {
		      date = SysUtil.getAppServerTime(null);
		    } catch (EASBizException e1) {
		      e1.printStackTrace();
		    }
		    StringBuffer sb = new StringBuffer();
		    sb.append(" select CFEqmNumberID from cT_OPE_EqmIO  ");
		    sb.append(" where CFInOrgUnitID='").append(id).append("'");
		    sb.append(" and CFRentStart<={ts '" + FORMAT_TIME.format(date) + "'}");
		    sb.append(" and CFRentEnd>={ts '" + FORMAT_TIME.format(date) + "'}");
		    sb.append(" and fstatus = '4'");
		 filter.getFilterItems().add(new FilterItemInfo("ssOrgUnit.id",id ,CompareType.EQUALS));
//		 filter.getFilterItems().add(new FilterItemInfo("sbStatus","1",CompareType.EQUALS));
		 filter.getFilterItems().add(new FilterItemInfo("id", sb.toString(), CompareType.INNER));
	 	 filter.setMaskString("#0 or #1");
		 evi.setFilter(filter);
		 prmtequName.setEntityViewInfo(evi);
//		 prmtequName.setSelector(ToolHelp.initPrmtEquIdByF7Color(evi, false)); 
		 
		 KDBizPromptBox kdtE1_equNameOne_PromptBox = new KDBizPromptBox();
	        kdtE1_equNameOne_PromptBox.setQueryInfo("com.kingdee.eas.port.equipment.record.app.EquIdQuery");
	        kdtE1_equNameOne_PromptBox.setVisible(true);
	        kdtE1_equNameOne_PromptBox.setEditable(true);
	        kdtE1_equNameOne_PromptBox.setDisplayFormat("$number$");
	        kdtE1_equNameOne_PromptBox.setEditFormat("$number$");
	        kdtE1_equNameOne_PromptBox.setCommitFormat("$number$");
	        kdtE1_equNameOne_PromptBox.setSelector(ToolHelp.initPrmtEquIdByF7Color(evi, false)); 
	        KDTDefaultCellEditor kdtE1_equNameOne_CellEditor = new KDTDefaultCellEditor(kdtE1_equNameOne_PromptBox);
	        this.kdtE1.getColumn("equNameOne").setEditor(kdtE1_equNameOne_CellEditor);
	        ObjectValueRender kdtE1_equNameOne_OVR = new ObjectValueRender();
	        kdtE1_equNameOne_OVR.setFormat(new BizDataFormat("$name$"));
	        this.kdtE1.getColumn("equNameOne").setRenderer(kdtE1_equNameOne_OVR);
		 
	        for (int i = 0; i < kdtE1.getRowCount(); i++) {
	        	 if ("equNameOne".equalsIgnoreCase(kdtE1.getColumn(i).getKey())) {
	 	        	kdtE1.getCell(i,"equUseDepart").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getString(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtE1.getCell(i,"equNameOne").getValue(),"usingDept.name")));

	 	        	}
			}
	        
	        EmployeeMultiF7PromptBox pmt = new EmployeeMultiF7PromptBox();
		    pmt.setIsSingleSelect(false);
		    pmt.showNoPositionPerson(false);
		    pmt.setEnabledMultiSelection(true); 
		    pmt.setIsDefaultFilterFieldsEnabled(true);
		    pmt.setIsShowAllAdmin(true);
		    
		   KDBizPromptBox kdtE1_repairPerson_PromptBox = new KDBizPromptBox();
	        kdtE1_repairPerson_PromptBox.setQueryInfo("com.kingdee.eas.basedata.person.app.PersonQuery");
	        kdtE1_repairPerson_PromptBox.setVisible(true);
	        kdtE1_repairPerson_PromptBox.setEditable(true);
	        kdtE1_repairPerson_PromptBox.setDisplayFormat("$name$");
	        kdtE1_repairPerson_PromptBox.setEditFormat("$number$");
	        kdtE1_repairPerson_PromptBox.setCommitFormat("$name$");
	        kdtE1_repairPerson_PromptBox.setEnabledMultiSelection(true);
	        kdtE1_repairPerson_PromptBox.setSelector(pmt);
	        KDTDefaultCellEditor kdtE1_repairPerson_CellEditor = new KDTDefaultCellEditor(kdtE1_repairPerson_PromptBox);
	        kdtE1.getColumn("repairPerson").setEditor(kdtE1_repairPerson_CellEditor);
	        ObjectValueRender kdtE1_replaceSparePart_OVR = new ObjectValueRender();
	        kdtE1_replaceSparePart_OVR.setFormat(new BizDataFormat("$name$"));
	        this.kdtE1.getColumn("repairPerson").setRenderer(kdtE1_replaceSparePart_OVR);
	        
	        String id1 = SysContext.getSysContext().getCurrentStorageUnit().getId().toString();
	        KDBizPromptBox kdtE1_replaceSparePart_PromptBox = new KDBizPromptBox();
	        kdtE1_replaceSparePart_PromptBox.setQueryInfo("com.kingdee.eas.port.equipment.rpt.F7MaterialQuery");
//	        kdtE1_replaceSparePart_PromptBox.setQueryInfo("com.kingdee.eas.fi.rpt.app.F7MaterialQuery");
	        kdtE1_replaceSparePart_PromptBox.setVisible(true);
	        kdtE1_replaceSparePart_PromptBox.setEditable(true);
	        kdtE1_replaceSparePart_PromptBox.setDisplayFormat("$number$");
	        kdtE1_replaceSparePart_PromptBox.setEditFormat("$number$");
	        kdtE1_replaceSparePart_PromptBox.setCommitFormat("$number$");
//	        kdtE1_replaceSparePart_PromptBox.setSelector(new F7MaterialSimpleSelector(this,kdtE1_replaceSparePart_PromptBox));
	   	     EntityViewInfo evi1 = new EntityViewInfo();
			 FilterInfo filter1 = new FilterInfo();
	 		 filter1.getFilterItems().add(new FilterItemInfo("Storage.id",id1 ,CompareType.EQUALS));
			 evi1.setFilter(filter1);
			 kdtE1_replaceSparePart_PromptBox.setEntityViewInfo(evi1);
			 KDTDefaultCellEditor kdtEntry_feeType_CellEditor = new KDTDefaultCellEditor(kdtE1_replaceSparePart_PromptBox);
			 kdtE1.getColumn("replaceSparePart").setEditor(kdtEntry_feeType_CellEditor);
			 
		Tool.setPersonF7(this.prmtassignee, this, SysContext.getSysContext().getCurrentCtrlUnit().getId().toString());
		Tool.setPersonF7(this.prmtrepairPerson, this, SysContext.getSysContext().getCurrentCtrlUnit().getId().toString());
		Tool.setPersonF7(this.prmtdeliveryPerson, this, SysContext.getSysContext().getCurrentCtrlUnit().getId().toString());
		Tool.setPersonF7(this.prmtbaoxiuren, this, SysContext.getSysContext().getCurrentCtrlUnit().getId().toString());
		Tool.setPersonF7(this.prmtrecipient, this, SysContext.getSysContext().getCurrentCtrlUnit().getId().toString());
//		if(chkselfStudy.getSelected() == 32){
//			txtselfAmount.setEnabled(true);
//		}else{
//			 txtselfAmount.setEnabled(false);
//			 txtselfAmount.setValue(null);
//		}
//		if(chkoutsourcing.getSelected() == 32){
//			 txtoutAmount.setEnabled(true);
//		}else{
//			txtoutAmount.setEnabled(false);
//			txtoutAmount.setValue(null);
//		}
//		
//		//自修
//		chkselfStudy.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e) {
//				if(chkselfStudy.getSelected() == 32){
//					 txtselfAmount.setEnabled(true);
//				}else{
//					 txtselfAmount.setEnabled(false);
//					 txtselfAmount.setValue(null);
//				}
//			}
//		});
//		//委外修理
//		chkoutsourcing.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e) {
//				if(chkoutsourcing.getSelected() == 32){
//					 txtoutAmount.setEnabled(true);
//				}else{
//					txtoutAmount.setEnabled(false);
//					txtoutAmount.setValue(null);
//				}
//			}
//		});
		
		KDWorkButton copyEntryLine = new KDWorkButton("复制分录");
		copyEntryLine.setIcon(EASResource.getIcon("imgTbtn_copyline"));
		
		copyEntryLine.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				try {
            		int rowIndex = kdtE1.getSelectManager().getActiveRowIndex();
            		if(rowIndex==-1){MsgBox.showWarning("请选择分录行复制！");SysUtil.abort();}else{
            			KDTSelectBlock sb;
                    	int size = kdtE1.getSelectManager().size(); 
                    	for (int i = 0; i < size; i++){
                    		sb = kdtE1.getSelectManager().get(i);
                    		int top = sb.getTop(); // 选择块最上边行索引
                    		int bottom = sb.getBottom(); // 选择块最下边行索引
                    		for (int j = top; j <= bottom; j++) {
                    			IRow row = kdtE1.getRow(j);
                    			IRow Copyrow = kdtE1.addRow();
                    			for(int cellIndex = 0; cellIndex < kdtE1.getColumnCount(); cellIndex++){
                    				String cellName = kdtE1.getColumnKey(cellIndex);
//        							if(!cellName.equals("CostType") &&!cellName.equals("CostCategories") && !cellName.equals("dep") )
//        								continue;
        							ICell copyFromCell = row.getCell(cellName);
        							if(copyFromCell != null){
        								Object orgValue = copyFromCell.getValue();
        								Copyrow.getCell(cellName).setValue(orgValue);
        				            }
        				        }
            				}
                    	}
            		}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		Component com[] = kdtE1_detailPanel.getComponents();
		
		for (int i = 0; i < com.length; i++) 
		{
			if(com[i] instanceof KDPanel)
			{
				KDPanel panle = (KDPanel)com[i];
				if(panle.getName().equals("controlPanel"))
					panle.add(copyEntryLine, new com.kingdee.bos.ctrl.swing.KDLayout.Constraints(kdtE1.getBounds().width - 174, 5, 82, 19, 9));
			}
		}
		
		setIsHideCell();
	}
	
	private void setIsHideCell()
	{
		CtrlUnitInfo Info = (CtrlUnitInfo)this.prmtCU.getValue();
		if(Info==null)
			return;
		
		if(Info.getName().contains("轮驳公司"))
		{
			this.kdtE1.getColumn("replaceSparePart").getStyleAttributes().setHided(true);
			this.kdtE1.getColumn("beijiangenghuan").getStyleAttributes().setHided(false);
			
			this.kdtE1.getColumn("model").getStyleAttributes().setLocked(false);
			this.kdtE1.getColumn("jlUnit").getStyleAttributes().setLocked(false);
			
		}
		else
		{
			this.kdtE1.getColumn("replaceSparePart").getStyleAttributes().setHided(false);
			this.kdtE1.getColumn("beijiangenghuan").getStyleAttributes().setHided(true);
			
			this.kdtE1.getColumn("model").getStyleAttributes().setLocked(true);
			this.kdtE1.getColumn("jlUnit").getStyleAttributes().setLocked(true);
		}
	}
	

	protected void verifyInput(ActionEvent e) throws Exception {
		for (int i = 0; i < kdtE1.getRowCount(); i++) {
			if(kdtE1.getCell(i, "equNameOne").getValue() == null){
				MsgBox.showWarning("第{"+(i+1)+"}行的分录的设备名称不能为空！");
				SysUtil.abort();
			}
		}
		super.verifyInput(e);
		Calendar ca = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (pkBizDate.getValue() != null) {
			for (int i = 0; i < kdtE1.getRowCount(); i++) {
				if(kdtE1.getCell(i, "yujingzhouqi").getValue() != null ){
					String date = sdf.format(pkBizDate.getValue());
					BigDecimal yujingzhouqi = UIRuleUtil.getBigDecimal(kdtE1.getCell(i, "yujingzhouqi").getValue());
					ca.setTime(sdf.parse(date));
					ca.add(Calendar.DATE,yujingzhouqi.intValue());
					Date date1 = ca.getTime();
					kdtE1.getCell(i, "yujingDate").setValue(date1);
				}
			}
		}
	}
	
	protected void prmtassignee_dataChanged(DataChangeEvent e) throws Exception {
		super.prmtassignee_dataChanged(e);
		if(BizCollUtil.isF7ValueChanged(e)&&e.getNewValue()!=null)
			this.prmtslDepart.setValue(PersonXRHelper.getPosiMemByDeptUser((PersonInfo)e.getNewValue()));
		else
			this.prmtslDepart.setValue(null);
	}
	
	protected void prmtrepairPerson_dataChanged(DataChangeEvent e)
			throws Exception {
		super.prmtrepairPerson_dataChanged(e);
		if(BizCollUtil.isF7ValueChanged(e)&&e.getNewValue()!=null)
			this.prmtrepairDepart.setValue(PersonXRHelper.getPosiMemByDeptUser((PersonInfo)e.getNewValue()));
	}
	
	public void actionEquInfomation_actionPerformed(ActionEvent e)
			throws Exception {
		super.actionEquInfomation_actionPerformed(e);
		 if(editData.getId() ==null){
			  MsgBox.showInfo("请先保存单据！");
				SysUtil.abort();
		  }
			  if(prmtequName.getValue() !=null){
			    String id = ((EquIdInfo)prmtequName.getData()).getId().toString();
				IUIWindow uiWindow = null;
				UIContext context = new UIContext(this);
				context.put("ID", id);
				context.put("anid", editData.getId().toString());
				uiWindow = UIFactory.createUIFactory(UIFactoryName.MODEL).create(EquIdEditUI.class.getName(), context, null, OprtState.VIEW);
				uiWindow.show(); 
			  }
	}
	
	public void kdtE1_Changed(int rowIndex, int colIndex) throws Exception {
		super.kdtE1_Changed(rowIndex, colIndex);
	}
	
	protected void kdtE1_tableClicked(KDTMouseEvent e) throws Exception {
		super.kdtE1_tableClicked(e);
		  if ((e.getButton() == 1) && (e.getClickCount() == 2))
	        {
			  if(!kdtE1.getColumnKey(e.getColIndex()).equals("equNameOne"))
				  return;
			  if(editData.getId() ==null){
				  MsgBox.showInfo("请先保存单据！");
					SysUtil.abort();
			  }else{
			  if(e.getRowIndex() != -1){
				  if(kdtE1.getCell(e.getRowIndex(), "equNameOne").getValue() !=null){
				    String id = ((EquIdInfo)kdtE1.getCell(e.getRowIndex(), "equNameOne").getValue()).getId().toString();
					IUIWindow uiWindow = null;
					UIContext context = new UIContext(this);
					context.put("ID", id);
					context.put("anid", editData.getId().toString());
					uiWindow = UIFactory.createUIFactory(UIFactoryName.MODEL).create(EquIdEditUI.class.getName(), context, null, OprtState.VIEW);
					uiWindow.show(); 
				  }
			    }
			  }
	        }
	}
}