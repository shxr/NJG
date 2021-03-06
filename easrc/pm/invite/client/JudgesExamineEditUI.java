/**
 * output package name
 */
package com.kingdee.eas.port.pm.invite.client;

import java.awt.event.ActionEvent;
import java.util.HashSet;

import org.apache.log4j.Logger;

import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.swing.KDTextField;
import com.kingdee.bos.ctrl.swing.event.DataChangeEvent;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.ui.face.UIRuleUtil;
import com.kingdee.eas.basedata.assistant.ProjectInfo;
import com.kingdee.eas.basedata.person.IPerson;
import com.kingdee.eas.basedata.person.PersonFactory;
import com.kingdee.eas.basedata.person.PersonInfo;
import com.kingdee.eas.common.client.OprtState;
import com.kingdee.eas.port.pm.base.ExamineIndicatorsCollection;
import com.kingdee.eas.port.pm.base.ExamineIndicatorsFactory;
import com.kingdee.eas.port.pm.base.ExamineIndicatorsInfo;
import com.kingdee.eas.port.pm.base.ExamineIndicatorsTreeCollection;
import com.kingdee.eas.port.pm.base.ExamineIndicatorsTreeFactory;
import com.kingdee.eas.port.pm.base.ExamineIndicatorsTreeInfo;
import com.kingdee.eas.port.pm.base.IExamineIndicators;
import com.kingdee.eas.port.pm.base.IJudges;
import com.kingdee.eas.port.pm.base.JudgesFactory;
import com.kingdee.eas.port.pm.base.JudgesInfo;
import com.kingdee.eas.port.pm.invite.EvaluationCollection;
import com.kingdee.eas.port.pm.invite.EvaluationFactory;
import com.kingdee.eas.port.pm.invite.EvaluationInfo;
import com.kingdee.eas.port.pm.invite.InviteReportFactory;
import com.kingdee.eas.port.pm.invite.InviteReportInfo;
import com.kingdee.eas.port.pm.invite.JudgesComfirmCollection;
import com.kingdee.eas.port.pm.invite.JudgesComfirmEntryCollection;
import com.kingdee.eas.port.pm.invite.JudgesComfirmEntryInfo;
import com.kingdee.eas.port.pm.invite.JudgesComfirmFactory;
import com.kingdee.eas.port.pm.invite.JudgesComfirmInfo;
import com.kingdee.eas.port.pm.invite.examRecord;

/**
 * output class name
 */
public class JudgesExamineEditUI extends AbstractJudgesExamineEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(JudgesExamineEditUI.class);
    
    /**
     * output class constructor
     */
    public JudgesExamineEditUI() throws Exception
    {
        super();
    }
    
    @Override
    public void onLoad() throws Exception {
    	// TODO Auto-generated method stub
    	btnAddNew.setVisible(false);
    	txtprjName.setEnabled(false);
    	txtprjOrg.setEnabled(false);
    	txtppr.setEditable(true);
    	
    	prmtreportName.setRequired(true);
    	prmtinvitePerson.setRequired(true);
    	prmtjudgeName.setRequired(true);
    	pkevaDate.setRequired(true);
    	super.onLoad();
    	if(getOprtState().equals(OprtState.ADDNEW)) {
    		String oqls = "select * desc order by number asc";
    		ExamineIndicatorsTreeCollection eitColl = ExamineIndicatorsTreeFactory.getRemoteInstance().getExamineIndicatorsTreeCollection(oqls);//类别集合
        	IExamineIndicators iexamine = ExamineIndicatorsFactory.getRemoteInstance();
//        	EntityViewInfo evi = new EntityViewInfo();
        	int start = 0;
        	int end = 0;
        	for(int i = 0; i < eitColl.size(); i++) {
        		ExamineIndicatorsTreeInfo eitTreeInfo = eitColl.get(i);
//        		FilterInfo filterInfo = new FilterInfo();
//        		filterInfo.getFilterItems().add(new FilterItemInfo("treeid.id", eitTreeInfo.getId(), CompareType.EQUALS));
//        		evi.setFilter(filterInfo);
        		String oql = "where treeid.id='"+eitTreeInfo.getId().toString()+"' order by number asc";
        		ExamineIndicatorsCollection exiColl = iexamine.getExamineIndicatorsCollection(oql);
        		if(kdtEntryIndicators.getRowCount() == 0) {
        			start = 0;
        			end = exiColl.size() - 1;
        		} else {
        			start = end + 1;
        			end = end + exiColl.size();
        		}
        		for(int j = 0; j < exiColl.size(); j++) {
        			ExamineIndicatorsInfo info = exiColl.get(j);
        			IRow rowadd = this.kdtEntryIndicators.addRow();
        			rowadd.getCell("examCategory").setValue(info.getExamCategory());
        			rowadd.getCell("examIndicators").setValue(info.getExamineIndicator());
        		}
        		
        		this.kdtEntryIndicators.getMergeManager().mergeBlock(start, 1, end, 1);
        	}
        	
        //设置分录考核记录默认值为无
        	for (int i = 0; i < kdtEntryIndicators.getRowCount(); i++) {
        		kdtEntryIndicators.getCell(i, "record").setValue(examRecord.none);
			}
    	}
    	ProjectInfo info = (ProjectInfo) getUIContext().get("treeInfo");
    	if(info != null) {
    		EntityViewInfo evi = new EntityViewInfo();
    		FilterInfo filter = new FilterInfo();
    		evi.setFilter(filter);
    		filter.getFilterItems().add(new FilterItemInfo("proName.longnumber", info.getLongNumber()+"%", CompareType.LIKE));
    		filter.getFilterItems().add(new FilterItemInfo("status", "4", CompareType.EQUALS));
			prmtreportName.setEntityViewInfo(evi);
		}
    	com.kingdee.eas.port.pm.invite.client.InviteReportEditUI.initContainerButton(this.kDContainer1, this.kdtEntryIndicators_detailPanel);
    }
    @Override
    protected void prmtreportName_dataChanged(DataChangeEvent e)
    		throws Exception {
    	super.prmtreportName_dataChanged(e);
    	
    	if(this.prmtreportName.getValue()==null)
    		return;
    	
    	InviteReportInfo reportInfo = (InviteReportInfo) this.prmtreportName.getValue(); 
    	reportInfo = InviteReportFactory.getRemoteInstance().getInviteReportInfo(new ObjectUuidPK(reportInfo.getId()));
    	
    	String totalPerson = "";
    	
    	IPerson IJudges = PersonFactory.getRemoteInstance();
    	
    	for (int i = 0; i < reportInfo.getEntry3().size(); i++) 
    	{
    		if(reportInfo.getEntry3().get(i).getInvitePerson()==null)
    			continue;
    		PersonInfo judInfo = reportInfo.getEntry3().get(i).getInvitePerson();
    		totalPerson = (totalPerson.length()>0?totalPerson+";":"")+(judInfo!=null?IJudges.getPersonInfo(new ObjectUuidPK(judInfo.getId())).getName():"");
		}
    	String pbPerosn = "";
    	
		String oql = "where planName.id='"+reportInfo.getId()+"' and status='4'";
		JudgesComfirmCollection judCollection = JudgesComfirmFactory.getRemoteInstance().getJudgesComfirmCollection(oql);
		if(judCollection.size()>0)
		{
			for (int i = 0; i < judCollection.get(0).getEntry().size(); i++) 
			{
				JudgesComfirmEntryInfo entryInfo = judCollection.get(0).getEntry().get(i);
				pbPerosn = (pbPerosn.length()>0?pbPerosn+";":"")+(UIRuleUtil.isNotNull(entryInfo.getJudgesName())?entryInfo.getJudgesName():"");
				
			}
		}
    	this.txtppr.setText("招标项目组成员："+totalPerson+"； 评标专家："+pbPerosn);
    	
    	oql = "where planName.id = '" + reportInfo.getId() + "'";
    	JudgesComfirmCollection juColl = JudgesComfirmFactory.getRemoteInstance().getJudgesComfirmCollection(oql);
    	JudgesComfirmInfo juinfo = juColl.get(0);
    	JudgesComfirmEntryCollection juEntryColl = juinfo.getEntry();
    	HashSet<String> idSet = new HashSet<String>();
    	for(int i = 0; i < juEntryColl.size(); i++) {
    		JudgesComfirmEntryInfo entryInfo = juEntryColl.get(i);
    		idSet.add(entryInfo.getJudgeNumber().getId().toString());
    	}
    	EntityViewInfo evi = new EntityViewInfo();
    	FilterInfo filter = new FilterInfo();
    	filter.getFilterItems().add(new FilterItemInfo("id", idSet, CompareType.INCLUDE));
    	evi.setFilter(filter);
    	this.prmtjudgeName.setEntityViewInfo(evi);
    	
    	oql = "where inviteReport.id='" + reportInfo.getId() + "'";
    	EvaluationCollection evaColl = EvaluationFactory.getRemoteInstance().getEvaluationCollection(oql);
    	if(evaColl.size() > 0) {
    		EvaluationInfo evaInfo = evaColl.get(0);
    		this.pkevaDate.setValue(evaInfo.getEvaDate());
    	}
    }
    
    //设置表格融合
    private void showUI() throws Exception{
    	String oqls = "order by number asc";
		ExamineIndicatorsTreeCollection eitColl = ExamineIndicatorsTreeFactory.getRemoteInstance().getExamineIndicatorsTreeCollection(oqls);//类别集合
    	IExamineIndicators iexamine = ExamineIndicatorsFactory.getRemoteInstance();
    	int start = 0;
    	int end = 0;
    	for(int i = 0; i < eitColl.size(); i++) {
    		ExamineIndicatorsTreeInfo eitTreeInfo = eitColl.get(i);
    		String oql = "where treeid.id='"+eitTreeInfo.getId().toString()+"' order by number asc";
    		ExamineIndicatorsCollection exiColl = iexamine.getExamineIndicatorsCollection(oql);
    		end = start + exiColl.size() - 1;
    		this.kdtEntryIndicators.getMergeManager().mergeBlock(start, 1, end, 1);
    		start = end + 1;
    	}
	}
    
    @Override
    protected void verifyInput(ActionEvent actionevent) throws Exception {
    	// TODO Auto-generated method stub
    	com.kingdee.eas.xr.helper.ClientVerifyXRHelper.verifyNull(this, prmtreportName, "招标方案");
//    	com.kingdee.eas.xr.helper.ClientVerifyXRHelper.verifyNull(this, prmtjudgeName, "被考核专家");
    	com.kingdee.eas.xr.helper.ClientVerifyXRHelper.verifyNull(this, prmtinvitePerson, "招标办监督人员");
    	com.kingdee.eas.xr.helper.ClientVerifyXRHelper.verifyNull(this, pkevaDate, "评标日期");
    	super.verifyInput(actionevent);
    }
    /**
     * output loadFields method
     */
    public void loadFields()
    {
        super.loadFields();
        try {
			showUI();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * output storeFields method
     */
    public void storeFields()
    {
        super.storeFields();
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
        super.actionEdit_actionPerformed(e);
    }

    /**
     * output actionRemove_actionPerformed
     */
    public void actionRemove_actionPerformed(ActionEvent e) throws Exception
    {
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
        return com.kingdee.eas.port.pm.invite.JudgesExamineFactory.getRemoteInstance();
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
        com.kingdee.eas.port.pm.invite.JudgesExamineInfo objectValue = new com.kingdee.eas.port.pm.invite.JudgesExamineInfo();
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));
		
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

}