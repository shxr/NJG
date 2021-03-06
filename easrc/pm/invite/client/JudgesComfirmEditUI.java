/**
 * output package name
 */
package com.kingdee.eas.port.pm.invite.client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Set;

import org.apache.log4j.Logger;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.ctrl.extendcontrols.BizDataFormat;
import com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox;
import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.KDTDefaultCellEditor;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.kdf.util.render.ObjectValueRender;
import com.kingdee.bos.ctrl.swing.KDContainer;
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
import com.kingdee.eas.base.uiframe.client.UIFactoryHelper;
import com.kingdee.eas.basedata.assistant.ProjectInfo;
import com.kingdee.eas.basedata.org.AdminOrgUnitFactory;
import com.kingdee.eas.basedata.org.AdminOrgUnitInfo;
import com.kingdee.eas.basedata.org.IAdminOrgUnit;
import com.kingdee.eas.basedata.org.client.f7.AdminF7;
import com.kingdee.eas.basedata.person.IPerson;
import com.kingdee.eas.basedata.person.PersonFactory;
import com.kingdee.eas.basedata.person.PersonInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.common.client.OprtState;
import com.kingdee.eas.common.client.SysContext;
import com.kingdee.eas.common.client.UIContext;
import com.kingdee.eas.port.pm.base.IJudgesTree;
import com.kingdee.eas.port.pm.base.JudgesTreeFactory;
import com.kingdee.eas.port.pm.base.JudgesTreeInfo;
import com.kingdee.eas.port.pm.invite.IInviteReportEntry5;
import com.kingdee.eas.port.pm.invite.InviteReportCollection;
import com.kingdee.eas.port.pm.invite.InviteReportEntry3Collection;
import com.kingdee.eas.port.pm.invite.InviteReportEntry3Info;
import com.kingdee.eas.port.pm.invite.InviteReportEntry5Collection;
import com.kingdee.eas.port.pm.invite.InviteReportEntry5Factory;
import com.kingdee.eas.port.pm.invite.InviteReportEntry5Info;
import com.kingdee.eas.port.pm.invite.InviteReportFactory;
import com.kingdee.eas.port.pm.invite.InviteReportInfo;
import com.kingdee.eas.port.pm.invite.JudgesComfirmFactory;
import com.kingdee.eas.rptclient.newrpt.util.MsgBox;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.eas.util.client.EASResource;

/**
 * output class name
 */
public class JudgesComfirmEditUI extends AbstractJudgesComfirmEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(JudgesComfirmEditUI.class);
    
    /**
     * output class constructor
     */
    public JudgesComfirmEditUI() throws Exception
    {
        super();
    }
    
    
    public void onLoad() throws Exception {
    	this.txtplanNumber.setEnabled(false);
    	this.txtNumber.setRequired(true);
    	this.prmtplanName.setRequired(true);
    	this.txtinviteType.setEnabled(false);
    	this.txtorgUnit.setEnabled(false);
    	this.txtprjName.setEnabled(false);
    	this.contplanName.setBoundLabelText("招标方案");
    	InitInvitePerson(kDContainer2);
    	this.kDContainer2.getContentPane().add(this.kdtEntry,BorderLayout.CENTER);
    	this.kdtEntry_detailPanel.getAddNewLineButton().setText("新增分录");
    	this.kdtEntry_detailPanel.getInsertLineButton().setText("插入分录");
    	this.kdtEntry_detailPanel.getRemoveLinesButton().setText("删除分录");
    	this.kDContainer2.addButton(this.kdtEntry_detailPanel.getAddNewLineButton());
    	this.kDContainer2.addButton(this.kdtEntry_detailPanel.getInsertLineButton());
    	this.kDContainer2.addButton(this.kdtEntry_detailPanel.getRemoveLinesButton());
    	super.onLoad();
    	this.kdtEntry.getColumn("telephone").getStyleAttributes().setLocked(false);
    	
    	//部门F7字段树状显示
    	AdminF7 f7 = new AdminF7(this);
 		f7.showCheckBoxOfShowingAllOUs();
 		f7.setIsCUFilter(false);
 		f7.setRootUnitID(SysContext.getSysContext().getCurrentAdminUnit().getId().toString());
 		this.prmtdepartment.setSelector(f7);
 		
//    	this.setPreferredSize(new Dimension(645, 560));
    	
    	//过滤项目下对应的招标方案
    	ProjectInfo info = (ProjectInfo) getUIContext().get("treeInfo");
    	if(info != null) {
    		EntityViewInfo evi = new EntityViewInfo();
    		FilterInfo filter = new FilterInfo();
    		evi.setFilter(filter);
    		filter.getFilterItems().add(new FilterItemInfo("proName.longnumber", info.getLongNumber()+"%", CompareType.LIKE));
    		filter.getFilterItems().add(new FilterItemInfo("status","4", CompareType.EQUALS));
			prmtplanName.setEntityViewInfo(evi);
			
			InviteReportCollection coll = InviteReportFactory.getRemoteInstance().getInviteReportCollection(evi);
    		if(coll.size()==1)
    			prmtplanName.setValue(coll.get(0));
		}
//    	InitWorkButton(this.kDContainer2);
    	
    	 final KDBizPromptBox kdtEntry_judgeNumber_PromptBox = new KDBizPromptBox();
         kdtEntry_judgeNumber_PromptBox.setQueryInfo("com.kingdee.eas.port.pm.base.app.JudgesQuery");
         kdtEntry_judgeNumber_PromptBox.setVisible(true);
         kdtEntry_judgeNumber_PromptBox.setEditable(true);
         kdtEntry_judgeNumber_PromptBox.setDisplayFormat("$personName$");
         kdtEntry_judgeNumber_PromptBox.setEditFormat("$personName$");
         kdtEntry_judgeNumber_PromptBox.setCommitFormat("$number$");
         KDTDefaultCellEditor kdtEntry_judgeNumber_CellEditor = new KDTDefaultCellEditor(kdtEntry_judgeNumber_PromptBox);
         this.kdtEntry.getColumn("judgeNumber").setEditor(kdtEntry_judgeNumber_CellEditor);
         ObjectValueRender kdtEntry_judgeNumber_OVR = new ObjectValueRender();
         kdtEntry_judgeNumber_OVR.setFormat(new BizDataFormat("$personName$"));
         this.kdtEntry.getColumn("judgeNumber").setRenderer(kdtEntry_judgeNumber_OVR);
    }
    
    protected void prmtplanName_dataChanged(DataChangeEvent e) throws Exception {
    	super.prmtplanName_dataChanged(e);
    	if(prmtplanName.getValue()==null)
    		return;
    	InviteReportInfo Info = (InviteReportInfo)prmtplanName.getValue();
    	
		UIContext uiContext = new UIContext(this);
		uiContext.put("ID", Info.getId());
		InviteReportEditUI ui = (InviteReportEditUI)UIFactoryHelper.initUIObject(InviteReportEditUI.class.getName(), uiContext, null,OprtState.VIEW);
		
		kDContainer3.getContentPane().add(ui.getkdtEntry5(), BorderLayout.CENTER);
    }
    
    @Override
    protected void verifyInput(ActionEvent e) throws Exception {
    	// TODO Auto-generated method stub
    	com.kingdee.eas.xr.helper.ClientVerifyXRHelper.verifyNull(this, prmtplanName, "招标方案");
    	InviteReportInfo reportInfo = (InviteReportInfo) this.prmtplanName.getValue();
    	
		String oql = "select id where planName.id='"+reportInfo.getId()+"'";
		if(editData.getId()!=null)
			oql = oql+"and id <>'"+editData.getId()+"'";
		if(JudgesComfirmFactory.getRemoteInstance().exists(oql))
		{
			MsgBox.showWarning("招标方案<"+reportInfo.getReportName()+">已有对应的评标专家确认单，不允许重复录入！");SysUtil.abort();
		}
		
    	
    	InviteReportEntry5Collection judgeColl = reportInfo.getEntry5();
    	
    	//校验专家信息是否与招标方案申报中的相符合
    	if(this.kdtEntry.getRowCount() > 0 && judgeColl.size() > 0) {
    		IInviteReportEntry5 ientry5 = InviteReportEntry5Factory.getRemoteInstance();
    		IJudgesTree ijudge = JudgesTreeFactory.getRemoteInstance();
    		HashMap<String, Integer> numberMap = new HashMap<String, Integer>();
    		StringBuilder sb = new StringBuilder();
        	for(int i = 0; i < judgeColl.size(); i++) {
        		InviteReportEntry5Info entryInfo = judgeColl.get(i);
        		entryInfo = ientry5.getInviteReportEntry5Info(new ObjectUuidPK(entryInfo.getId()));
        		JudgesTreeInfo type = entryInfo.getJudgeType();
        		type = ijudge.getJudgesTreeInfo(new ObjectUuidPK(type.getId()));
        		numberMap.put(type.getName(), Integer.valueOf(entryInfo.getAmount()));
        		sb.append(type.getName() + ": " + entryInfo.getAmount() + "人\n");
        	}
    		Set<String> set = numberMap.keySet();
    		HashMap<String, Integer> perMap = new HashMap<String, Integer>();
    		for(String s : set) {
    			int count = 0;
    			for(int i = 0; i < this.kdtEntry.getRowCount(); i++) {
    				IRow row = this.kdtEntry.getRow(i);
    				if(s.compareTo(row.getCell("juType").getValue().toString()) == 0)
    					count++;
    			}
    			perMap.put(s, count);
    		}
    		
    		for(String s : set) {
    			if(perMap.get(s).compareTo(numberMap.get(s)) != 0) {
    				MsgBox.showWarning("专家构成与招标方案申报不符合!!请检查!!\n" + sb.toString());
//    				SysUtil.abort();
    			}
    		}
    	}
    	super.verifyInput(e);
    }
    //专家信息分录添加引入招标成员组按钮
    private void InitInvitePerson(KDContainer kDContainer) {
    	KDWorkButton invitePerson = new KDWorkButton();
//    	invitePerson.setText("引入招标成员信息");
//    	invitePerson.setIcon(EASResource.getIcon("imgTbtn_synchronization"));
//    	kDContainer.addButton(invitePerson);
//    	invitePerson.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				InviteReportInfo info = (InviteReportInfo) prmtplanName.getValue();
//				if(info != null) {
//			    	InviteReportEntry3Collection coll = info.getEntry3();
//			    	kdtEntry.removeRows();
//					try {
//						IPerson iPerson = PersonFactory.getRemoteInstance();
//						IJudgesTree ijudgesTree = JudgesTreeFactory.getRemoteInstance();
//						IAdminOrgUnit iadmin = AdminOrgUnitFactory.getRemoteInstance();
//						
//						for(int i = 0; i < coll.size(); i++) {
//				    		InviteReportEntry3Info entryInfo = coll.get(i);
//				    		IRow row = kdtEntry.addRow();
//				    		if(entryInfo.getInvitePerson()==null)
//				    			continue;
//				    		PersonInfo person = iPerson.getPersonInfo(new ObjectUuidPK(entryInfo.getInvitePerson().getId()));
//							row.getCell("judgeNumber").setValue(person);
//							row.getCell("judgesName").setValue(person.getName());
//							
//							JudgesTreeInfo jtree = ijudgesTree.getJudgesTreeInfo(new ObjectUuidPK(person.getJudgeType().getId()));
//							row.getCell("juType").setValue(jtree.getName());
//							if(person.getCurDep() != null) {
//								AdminOrgUnitInfo orginfo = iadmin.getAdminOrgUnitInfo(new ObjectUuidPK(person.getCurDep().getId()));
//								row.getCell("orgUnit").setValue(orginfo.getName());
//								row.getCell("telephone").setValue(person.getTelephone()); 
//							} else {
//								row.getCell("orgUnit").setValue(null);
//								row.getCell("telephone").setValue(null); 
//							}
//				    	}
//					} catch (BOSException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					} catch (EASBizException e2) {
//						// TODO Auto-generated catch block
//						e2.printStackTrace();
//					}
//				}
//			}
//    	});
    }
    
    //重构分录增删按钮
    private void InitWorkButton(KDContainer kDContainer){
    	KDWorkButton addRow = new KDWorkButton();
    	KDWorkButton InsertRow = new KDWorkButton();
    	KDWorkButton RemmoveRow = new KDWorkButton();
    	kDContainer.addButton(addRow);
    	kDContainer.addButton(InsertRow);
    	kDContainer.addButton(RemmoveRow);
    	addRow.setEnabled((getOprtState().equals("ADDNEW")||getOprtState().equals("EDIT"))?true:false);
    	InsertRow.setEnabled((getOprtState().equals("ADDNEW")||getOprtState().equals("EDIT"))?true:false);
    	RemmoveRow.setEnabled((getOprtState().equals("ADDNEW")||getOprtState().equals("EDIT"))?true:false);
    	addRow.setText("新增分录");
    	InsertRow.setText("插入分录");
    	RemmoveRow.setText("删除分录");
    	addRow.setIcon(EASResource.getIcon("imgTbtn_addline"));
    	InsertRow.setIcon(EASResource.getIcon("imgTbtn_insert"));
    	RemmoveRow.setIcon(EASResource.getIcon("imgTbtn_deleteline"));
    	addRow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					kdtEntry_detailPanel.actionAddnewLine_actionPerformed(e);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
    	});
    	InsertRow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					kdtEntry_detailPanel.actionInsertLine_actionPerformed(e);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
    	});
    	RemmoveRow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					kdtEntry_detailPanel.actionRemoveLine_actionPerformed(e);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
    	});
    }
    
    
    public void kdtEntry_Changed(int rowIndex, int colIndex) throws Exception {
    	  if ("judgeNumber".equalsIgnoreCase(kdtEntry.getColumn(colIndex).getKey())) {
    		  kdtEntry.getCell(rowIndex,"juName").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getString(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtEntry.getCell(rowIndex,"judgeNumber").getValue(),"juName.name")));

    		  }

    		      if ("judgeNumber".equalsIgnoreCase(kdtEntry.getColumn(colIndex).getKey())) {
    		  kdtEntry.getCell(rowIndex,"juType").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getString(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtEntry.getCell(rowIndex,"judgeNumber").getValue(),"judgeType.name")));

    		  }

    		      if ("judgeNumber".equalsIgnoreCase(kdtEntry.getColumn(colIndex).getKey())) {
    		  kdtEntry.getCell(rowIndex,"orgUnit").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getString(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtEntry.getCell(rowIndex,"judgeNumber").getValue(),"ssAddmin.name")));

    		  }

    		      if ("judgeNumber".equalsIgnoreCase(kdtEntry.getColumn(colIndex).getKey())) {
    		  kdtEntry.getCell(rowIndex,"telephone").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getString(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtEntry.getCell(rowIndex,"judgeNumber").getValue(),"mobile")));

    		  }

    		      if ("judgeNumber".equalsIgnoreCase(kdtEntry.getColumn(colIndex).getKey())) {
    		  kdtEntry.getCell(rowIndex,"judgesName").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getString(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtEntry.getCell(rowIndex,"judgeNumber").getValue(),"personName")));
    		 }
    }
    
    /**
     * output loadFields method
     */
    public void loadFields()
    {
        super.loadFields();
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
        this.kDContainer2.removeAllButton();
        InitWorkButton(this.kDContainer2);
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
        return com.kingdee.eas.port.pm.invite.JudgesComfirmFactory.getRemoteInstance();
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
        com.kingdee.eas.port.pm.invite.JudgesComfirmInfo objectValue = new com.kingdee.eas.port.pm.invite.JudgesComfirmInfo();
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));
		objectValue.setDepartment(SysContext.getSysContext().getCurrentAdminUnit());
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