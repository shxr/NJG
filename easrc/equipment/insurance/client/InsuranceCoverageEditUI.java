/**
 * output package name
 */
package com.kingdee.eas.port.equipment.insurance.client;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import com.kingdee.bos.ctrl.common.LanguageManager;
import com.kingdee.bos.ctrl.excel.io.kds.KDSBookToBook;
import com.kingdee.bos.ctrl.excel.model.struct.Sheet;
import com.kingdee.bos.ctrl.kdf.export.ExportManager;
import com.kingdee.bos.ctrl.kdf.export.KDTables2KDSBook;
import com.kingdee.bos.ctrl.kdf.export.KDTables2KDSBookVO;
import com.kingdee.bos.ctrl.kdf.kds.KDSBook;
import com.kingdee.bos.ctrl.kdf.read.POIXlsReader;
import com.kingdee.bos.ctrl.kdf.table.ICell;
import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.KDTMenuManager;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.swing.KDFileChooser;
import com.kingdee.bos.ctrl.swing.KDTextField;
import com.kingdee.bos.ctrl.swing.KDWorkButton;
import com.kingdee.bos.ctrl.swing.util.SimpleFileFilter;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.base.permission.client.longtime.ILongTimeTask;
import com.kingdee.eas.base.permission.client.longtime.LongTimeDialog;
import com.kingdee.eas.basedata.org.AdminOrgUnitCollection;
import com.kingdee.eas.basedata.org.AdminOrgUnitFactory;
import com.kingdee.eas.basedata.org.AdminOrgUnitInfo;
import com.kingdee.eas.basedata.org.IAdminOrgUnit;
import com.kingdee.eas.common.client.OprtState;
import com.kingdee.eas.fi.newrpt.client.designer.io.WizzardIO;
import com.kingdee.eas.port.equipment.insurance.InsuranceCoverageE1Info;
import com.kingdee.eas.port.equipment.record.EquIdCollection;
import com.kingdee.eas.port.equipment.record.EquIdFactory;
import com.kingdee.eas.port.equipment.record.EquIdInfo;
import com.kingdee.eas.port.equipment.record.IEquId;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.eas.util.client.EASResource;
import com.kingdee.eas.util.client.MsgBox;

/**
 * output class name
 */
public class InsuranceCoverageEditUI extends AbstractInsuranceCoverageEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(InsuranceCoverageEditUI.class);
    
    /**
     * output class constructor
     */
    public InsuranceCoverageEditUI() throws Exception
    {
        super();
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
        return com.kingdee.eas.port.equipment.insurance.InsuranceCoverageFactory.getRemoteInstance();
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
        com.kingdee.eas.port.equipment.insurance.InsuranceCoverageInfo objectValue = new com.kingdee.eas.port.equipment.insurance.InsuranceCoverageInfo();
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


	public void onLoad() throws Exception {
		 this.kdtE1.getColumn("seq").getStyleAttributes().setHided(true);
		super.onLoad();
		
		this.kDContainer1.setTitle("����Ͷ����ϸ");
		this.kDContainer1.getContentPane().add(kdtE1, BorderLayout.CENTER);
		KDWorkButton  addnewButton =kdtE1_detailPanel.getAddNewLineButton();
		addnewButton.setText("������");
		KDWorkButton  InsertButton =kdtE1_detailPanel.getInsertLineButton();
		InsertButton.setText("������");
		KDWorkButton RemoveButton =kdtE1_detailPanel.getRemoveLinesButton();
		RemoveButton.setText("ɾ����");
		this.kDContainer1.addButton(addnewButton);
		this.kDContainer1.addButton(InsertButton);
		this.kDContainer1.addButton(RemoveButton);
		this.kDContainer1.addButton(this.btnImportExcel);
		this.kDContainer1.addButton(this.btnExcel);
		
		btnImportExcel	.setIcon(EASResource.getIcon("imgTbtn_input"));
		btnExcel.setIcon(EASResource.getIcon("imgTbtn_output"));
		
		this.btnExportExcel.setVisible(false);
		this.kDInsuranceDetail.setVisible(false);
		
		
	}
	
	protected void btnExcel_actionPerformed(ActionEvent e) throws Exception {
		super.btnExcel_actionPerformed(e);
		btnExportExcel();
	}
	
	protected void btnImportExcel_actionPerformed(ActionEvent e)throws Exception {
		super.btnImportExcel_actionPerformed(e);
		actionImportExcel();
	}
	
	private String lockCell[] = {"equNumber","equType","specModel","factoryUseDate","makeUnit","tonnage"};
	String path ="";
	
	    public void actionImportExcel()  {
			path = showExcelSelectDlg(this);
			if (path == null) {
				return;
			}
			Window win = SwingUtilities.getWindowAncestor(this);
	        LongTimeDialog dialog = null;
	        if(win instanceof Frame){
	        	dialog = new LongTimeDialog((Frame)win);
	        }else if(win instanceof Dialog){
	        	dialog = new LongTimeDialog((Dialog)win);
	        }
	        if(dialog==null){
	        	dialog = new LongTimeDialog(new Frame());
	        }
	        dialog.setLongTimeTask(new ILongTimeTask() {
				public void afterExec(Object arg0) throws Exception {
					Boolean bol=(Boolean)arg0;
					if(bol){
						MsgBox.showInfo("����ɹ���");
					}
				}
				public Object exec() throws Exception {
					boolean bol=importExcelToTable(path,kdtE1);
					return bol;
				}
	    	}
		    );
		    dialog.show();
		}
		private boolean importExcelToTable(String fileName, KDTable table) throws Exception {
			KDSBook kdsbook = null;
			try {
				kdsbook = POIXlsReader.parse2(fileName);
			} catch (Exception e) {
				e.printStackTrace();
				MsgBox.showWarning(this,"��EXCEL����,EXCEl��ʽ��ƥ�䣡");
				return false;
			}
			if (kdsbook == null) {
				return false;
			}
			if(KDSBookToBook.traslate(kdsbook).getSheetCount()>1){
				MsgBox.showWarning(this,"��EXCEL����,EXCEl Sheet������ƥ�䣡");
				return false;
			}
			Sheet excelSheet = KDSBookToBook.traslate(kdsbook).getSheet(0);
	    	Map e_colNameMap = new HashMap();
			int e_maxRow = excelSheet.getMaxRowIndex();
			int e_maxColumn = excelSheet.getMaxColIndex();
			for (int col = 0; col <= e_maxColumn; col++) {
				String excelColName = excelSheet.getCell(0, col, true).getText();
				e_colNameMap.put(excelColName, new Integer(col));
			}
			Map kdtableHidedCell = new HashMap();
			for (int i = 0; i < lockCell.length; i++) 
			{
				kdtableHidedCell.put(lockCell[i], lockCell[i]);
			}
			for (int col = 0; col< table.getColumnCount(); col++) {
				if (table.getColumn(col).getStyleAttributes().isHided()||kdtableHidedCell.get(table.getColumnKey(col))!=null) {
					continue;
				}
				String colName = (String) table.getHeadRow(0).getCell(col).getValue();
				Integer colInt = (Integer) e_colNameMap.get(colName);
				if (colInt == null) {
					MsgBox.showWarning(this,"��ͷ�ṹ��һ�£������ϵĹؼ���:" + colName + "��EXCEL��û�г��֣�");
					return false;
				}
			}
			table.removeRows();
			IAdminOrgUnit IAdminorgUnit = AdminOrgUnitFactory.getRemoteInstance();
			IEquId IEquId = EquIdFactory.getRemoteInstance();
			for (int rowIndex = 1; rowIndex <= e_maxRow; rowIndex++) {
				IRow row = table.addRow();
				int newrowIndex = row.getRowIndex();
				InsuranceCoverageE1Info entry = new InsuranceCoverageE1Info();
				entry.setId(BOSUuid.create(entry.getBOSType()));
				row.setUserObject(entry);
				for (int col = 0; col < table.getColumnCount(); col++) {
					if (table.getColumn(col).getStyleAttributes().isHided()||kdtableHidedCell.get(table.getColumnKey(col))!=null) {
	    				continue;
	    			}
					ICell tblCell = row.getCell(col);
					String colName = (String) table.getHeadRow(0).getCell(col).getValue();
					Integer colInt = (Integer) e_colNameMap.get(colName);

					if (colInt == null) {
						continue;
					}
					com.kingdee.bos.ctrl.common.variant.Variant cellRawVal = excelSheet.getCell(rowIndex, colInt.intValue(), true).getValue();
					if (com.kingdee.bos.ctrl.common.variant.Variant.isNull(cellRawVal)) {
						continue;
					}
					String colValue = cellRawVal.toString();
					if(colName.equals("ʹ�õ�λ"))
					{
						AdminOrgUnitCollection admCollection = IAdminorgUnit.getAdminOrgUnitCollection("select id,number,name where name='"+colValue+"'");
						AdminOrgUnitInfo admiOrgInfo = admCollection.size()>0?admCollection.get(0):null;
						tblCell.setValue(admiOrgInfo);
					}
					else if(colName.equals("�豸����"))
					{
						EquIdCollection eqCollection = IEquId.getEquIdCollection("where name='"+colValue+"'");
						EquIdInfo eqInfo = eqCollection.size()>0?eqCollection.get(0):null;
						if(eqInfo==null){continue;}
						tblCell.setValue(eqInfo.getName());
						table.getCell(newrowIndex, "equNumber").setValue(eqInfo);
						kdtE1.getCell(newrowIndex,"equType").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getString(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtE1.getCell(newrowIndex,"equNumber").getValue(),"eqmType.name")));
						kdtE1.getCell(newrowIndex,"equName").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getString(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtE1.getCell(newrowIndex,"equNumber").getValue(),"name")));
						kdtE1.getCell(newrowIndex,"specModel").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getString(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtE1.getCell(newrowIndex,"equNumber").getValue(),"model")));
						kdtE1.getCell(newrowIndex,"factoryUseDate").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getDateValue(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtE1.getCell(newrowIndex,"equNumber").getValue(),"qyDate")));
						kdtE1.getCell(newrowIndex,"makeUnit").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getString(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtE1.getCell(newrowIndex,"equNumber").getValue(),"mader")));
						kdtE1.getCell(newrowIndex,"tonnage").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getString(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtE1.getCell(newrowIndex,"equNumber").getValue(),"weight")));
					}
					else
					{
						tblCell.setValue(colValue);
					}
				}
			}
			return true;
		}
		
		 public static String showExcelSelectDlg(CoreUIObject ui)
         {
			 KDFileChooser chsFile = new KDFileChooser();
			 String XLS = "xls";
			 String Key_File = "Key_File";
			 SimpleFileFilter Filter_Excel = new SimpleFileFilter(XLS, (new StringBuilder("MS Excel")).append(LanguageManager.getLangMessage(Key_File, WizzardIO.class.getName(), "\u64CD\u4F5C\u5931\u8D25")).toString());
			 chsFile.addChoosableFileFilter(Filter_Excel);
			 int ret = chsFile.showOpenDialog(ui);
			 if(ret != 0)
				 SysUtil.abort();

			 File file = chsFile.getSelectedFile();
			 String fileName = file.getAbsolutePath();
			 return fileName;
         }
		public void setOprtState(String oprtType) {
			super.setOprtState(oprtType);
			if (oprtType.equals(OprtState.VIEW)) {
				this.lockUIForViewStatus();
				this.btnImportExcel.setEnabled(false);
				this.btnExcel.setEnabled(false);
			} else {
				this.unLockUI();
				this.btnExcel.setEnabled(true);
				this.btnImportExcel.setEnabled(true);
			}
		}
		private File js;
		protected void btnExportExcel() throws Exception {
			ExportManager exportM = new ExportManager();
	        String path = null;
	        File tempFile = File.createTempFile("eastemp",".xls");
	        path = tempFile.getCanonicalPath();

	        for (int i = 0; i < lockCell.length; i++) 
	        {
	        	this.kdtE1.getColumn(lockCell[i]).getStyleAttributes().setHided(true);
			}
	        
	        KDTables2KDSBookVO[] tablesVO = new KDTables2KDSBookVO[1];
	        tablesVO[0]=new KDTables2KDSBookVO(this.kdtE1);
			tablesVO[0].setTableName("����Ͷ����ϸ");
	        KDSBook book = null;
	        book = KDTables2KDSBook.getInstance().exportKDTablesToKDSBook(tablesVO,true,true);
	        exportM.exportToExcel(book, path);
	        for (int i = 0; i < lockCell.length; i++) 
	        {
	        	this.kdtE1.getColumn(lockCell[i]).getStyleAttributes().setHided(false);
			}
			KDFileChooser fileChooser = new KDFileChooser();
			fileChooser.setFileSelectionMode(0);
			fileChooser.setMultiSelectionEnabled(false);
			fileChooser.setSelectedFile(new File("����Ͷ����ϸ.xls"));
			int result = fileChooser.showSaveDialog(this);
			if (result == KDFileChooser.APPROVE_OPTION){
				File dest = fileChooser.getSelectedFile();
				try{
					File src = new File(path);
					if (dest.exists())
						dest.delete();
					src.renameTo(dest);
					MsgBox.showInfo("�����ɹ���");
					KDTMenuManager.openFileInExcel(dest.getAbsolutePath());
				}
				catch (Exception e3)
				{
					handUIException(e3);
				}
			}
			tempFile.delete();
		}
	
}