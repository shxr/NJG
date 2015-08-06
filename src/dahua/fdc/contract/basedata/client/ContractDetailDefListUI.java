/**
 * output package name
 */
package com.kingdee.eas.fdc.basedata.client;

import org.apache.log4j.Logger;

import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.eas.fdc.basedata.ContractDetailDefFactory;
import com.kingdee.eas.fdc.basedata.ContractDetailDefInfo;
import com.kingdee.eas.fdc.basedata.FDCDataBaseInfo;
import com.kingdee.eas.framework.ICoreBase;

/**
 * output class name
 */
public class ContractDetailDefListUI extends AbstractContractDetailDefListUI
{
    private static final Logger logger = CoreUIObject.getLogger(ContractDetailDefListUI.class);
    
    /**
     * output class constructor
     */
    public ContractDetailDefListUI() throws Exception
    {
        super();
    }
	/**
	 * 
	 */
//	public void onLoad() throws Exception {
//		super.onLoad();
//		this.btnEnabled.setIcon(EASResource.getIcon("imgTbtn_staruse"));
//		this.btnDisEnabled.setIcon(EASResource.getIcon("imgTbtn_forbid"));
//		if(OrgConstants.DEF_CU_ID.equals(SysContext.getSysContext().getCurrentCtrlUnit().getId().toString())){
//			this.btnAddNew.setEnabled(true);
//			this.btnEdit.setEnabled(true);
//			this.btnRemove.setEnabled(true);
//			this.btnEnabled.setVisible(true);
//			this.btnDisEnabled.setVisible(true);
//			this.menuItemAddNew.setEnabled(true);
//			this.menuItemEdit.setEnabled(true);
//			this.menuItemRemove.setEnabled(true);
////			this.menuItemCancel.setv(true)
//		}else{
//			this.btnAddNew.setEnabled(false);
//			this.btnEdit.setEnabled(false);
//			this.btnRemove.setEnabled(false);
//			this.btnEnabled.setVisible(false);
//			this.btnDisEnabled.setVisible(false);
//			this.menuItemAddNew.setEnabled(false);
//			this.menuItemEdit.setEnabled(false);
//			this.menuItemRemove.setEnabled(false);
//		}
//		this.tblMain.getSelectManager().setSelectMode(KDTSelectManager.ROW_SELECT);
//	}
   
	protected String getEditUIName() {
		return ContractDetailDefEditUI.class.getName();
	}

	protected ICoreBase getBizInterface() throws Exception {
		return ContractDetailDefFactory.getRemoteInstance();
	}
	/**
	 * output tblMain_tableSelectChanged method
	 */
//	protected void tblMain_tableSelectChanged(com.kingdee.bos.ctrl.kdf.table.event.KDTSelectEvent e) throws Exception {
//		if (this.tblMain.getSelectManager().getActiveRowIndex() != -1) {
//			// boolean status = false;
//			if (this.tblMain.getRow(this.tblMain.getSelectManager().getActiveRowIndex()).getCell("isEnabled") != null) {
//				boolean status = ((Boolean) this.tblMain.getRow(this.tblMain.getSelectManager().getActiveRowIndex()).getCell("isEnabled").getValue()).booleanValue();
//				// ����ÿһ�й����isEnabled��ֵ�ı䣬����WBT��״̬Ҳ�ı�
//				changeWBTEnabeld(status);
//			}
//			// if
//			// (this.tblMain.getRow(this.tblMain.getSelectManager().getActiveRowIndex()).getCell("cu.id")
//			// != null) {
//			// boolean isEnabled =
//			// this.tblMain.getRow(this.tblMain.getSelectManager().getActiveRowIndex()).getCell("cu.id").getValue().toString().equals(
//			// SysContext.getSysContext().getCurrentCtrlUnit().getId().toString());
//			// changeEditEnabeld(isEnabled);
//			// } else {
//			// disabledEdit();
//			// }
//		} else {
//			disabledWBT();
//
//		}
//	}
	/**
	 * ����ÿһ�й����isEnabled��ֵ�ı䣬����btn��״̬Ҳ�ı�
	 * 
	 * @param isEnabled
	 *            boolean
	 */
//	private void changeWBTEnabeld(boolean isEnabled) {
//		this.btnEnabled.setEnabled(!isEnabled);
//		this.btnDisEnabled.setEnabled(isEnabled);
//
//	}

	/**
	 * ������/��ֹ��ťdisabled
	 */
//	private void disabledWBT() {
//		this.btnEnabled.setEnabled(false);
//		this.btnDisEnabled.setEnabled(false);
//
//	}
	
//	public void actionEnabled_actionPerformed(ActionEvent e) throws Exception {
//		IRow row = checkSelected(e);
//		if (row == null)
//			return;
//		String id = row.getCell("id").getValue().toString().trim();
//		IContractDetailDef iContractDetailDef = ContractDetailDefFactory.getRemoteInstance();
//		if(iContractDetailDef.enabled(new ObjectUuidPK(id))){
//			setMessageText(EASResource.getString(FDCBaseDataClientUtils.FDCBASEDATA_RESOURCE, "Enabled_OK"));
//			showMessage();			
//			tblMain.removeRows();// �����²�ѯ
//			this.btnEnabled.setEnabled(false);
//			this.btnDisEnabled.setEnabled(true);
//		}			
//	}

	/**
	 * output actionDisEnabled_actionPerformed
	 */
//	public void actionDisEnabled_actionPerformed(ActionEvent e) throws Exception {
//		IRow row = checkSelected(e);
//		if (row == null)
//			return;
//		String id = row.getCell("id").getValue().toString().trim();
//		IContractDetailDef iContractDetailDef = ContractDetailDefFactory.getRemoteInstance();
//		if(iContractDetailDef.disEnabled(new ObjectUuidPK(id))){
//			setMessageText(EASResource.getString(FDCBaseDataClientUtils.FDCBASEDATA_RESOURCE, "DisEnabled_OK"));
//			showMessage();
//			tblMain.removeRows();// �����²�ѯ
//			this.btnDisEnabled.setEnabled(false);
//			this.btnEnabled.setEnabled(true);
//		}	
//		
//	}
//	private IRow checkSelected(ActionEvent e) {
//		IRow row = null;
//		if (this.tblMain.getSelectManager().getActiveRowIndex() == -1) {
//			// ����ָ��һ����¼
//			MsgBox.showWarning("����ָ��һ����¼!");
//		} else {
//			row = this.tblMain.getRow(this.tblMain.getSelectManager().getActiveRowIndex());
//		}
//		return row;
//	}
	/**
	 * 
	 * ����Ƿ�ɾ������ϵͳԤ����Ϣ
	 */

//	private void verifyNotAccepted(IRow row) {
//		if (row.getCell("CU.id").getValue() != null&&(OrgConstants.SYS_CU_ID.equals(row.getCell("CU.id").getValue().toString()))) {
//			MsgBox.showWarning(com.kingdee.eas.util.client.EASResource.getString("com.kingdee.eas.fdc.basedata.FDCBaseDataResource", "Delete_SysData"));
//			SysUtil.abort();
//		}
//	}
	protected FDCDataBaseInfo getBaseDataInfo() {
		return new ContractDetailDefInfo();
	}
}