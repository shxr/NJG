/**
 * output package name
 */
package com.kingdee.eas.fdc.basedata.client;

import org.apache.log4j.Logger;

import com.kingdee.bos.ctrl.extendcontrols.KDBizMultiLangBox;
import com.kingdee.bos.ctrl.swing.KDTextField;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.entity.SelectorItemInfo;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.eas.fdc.basedata.FDCDataBaseInfo;
import com.kingdee.eas.fdc.basedata.ProjectStatusFactory;
import com.kingdee.eas.fdc.basedata.ProjectStatusInfo;
import com.kingdee.eas.framework.ICoreBase;
import com.kingdee.eas.util.client.EASResource;

/**
 * 描述:项目状态编辑
 * 
 * @author jackwang date:2007-1-30
 *         <p>
 * @version EAS5.2
 */
public class ProjectStatusEditUI extends AbstractProjectStatusEditUI {
	private static final Logger logger = CoreUIObject.getLogger(ProjectStatusEditUI.class);

	/**
	 * output class constructor
	 */
	public ProjectStatusEditUI() throws Exception {
		super();
	}

	public void onLoad() throws Exception {
		super.onLoad();
		setTitle();
//		setBtnStatus();
	}

	private void setTitle() {
		FDCBaseDataClientUtils.setupUITitle(this, EASResource.getString(FDCBaseDataClientUtils.FDCBASEDATA_RESOURCE, FDCBaseDataClientUtils.PROJECTSTATUS));
	}

//	private void setBtnStatus() {
//		if (STATUS_ADDNEW.equals(getOprtState())) {// 新增状态
//			this.btnCancelCancel.setVisible(false);// 启用按钮不可见
//			this.btnCancel.setVisible(false);// 禁用按钮不可见
//		} else if (STATUS_EDIT.equals(getOprtState())) {// 修改状态
//			if (this.editData.isIsEnabled()) {// 如果当前为启用状态
//				this.btnCancel.setVisible(true);// 禁用按钮可用
//				this.btnCancel.setEnabled(true);// 禁用按钮可用
//				this.btnCancelCancel.setVisible(false);// 启用按钮不可见
//			} else {// 如果当前为禁用状态
//				this.btnCancelCancel.setVisible(true);// 启用按钮可见
//				this.btnCancelCancel.setEnabled(true);// 启用按钮可用
//				this.btnCancel.setVisible(false);// 禁用按钮不可见
//			}
//		} else if (STATUS_VIEW.equals(getOprtState())) {// 查看状态
//			if (OrgConstants.DEF_CU_ID.equals(SysContext.getSysContext().getCurrentCtrlUnit().getId().toString())) {
//				if (this.editData.isIsEnabled()) {// 如果当前为启用状态
//					this.btnCancel.setVisible(true);// 禁用按钮可用
//					this.btnCancel.setEnabled(true);// 禁用按钮可用
//					this.btnCancelCancel.setVisible(false);// 启用按钮不可见
//				} else {// 如果当前为禁用状态
//					this.btnCancelCancel.setVisible(true);// 启用按钮可见
//					this.btnCancelCancel.setEnabled(true);// 启用按钮可用
//					this.btnCancel.setVisible(false);// 禁用按钮不可见
//				}
//				this.btnAddNew.setEnabled(true);
//				this.btnEdit.setEnabled(true);
//				this.menuItemAddNew.setEnabled(true);
//				this.menuItemEdit.setEnabled(true);
//				// this.menuItemRemove.setEnabled(true);
//			} else {
//				this.btnAddNew.setEnabled(false);
//				this.btnEdit.setEnabled(false);
//				this.btnRemove.setEnabled(false);
//				this.btnCancel.setVisible(false);
//				this.btnCancelCancel.setVisible(false);
//				this.menuItemAddNew.setEnabled(false);
//				this.menuItemEdit.setEnabled(false);
//				this.menuItemRemove.setEnabled(false);
//			}
//			if (OrgConstants.SYS_CU_ID.equals(this.editData.getCU().getId().toString())) {
//				this.btnAddNew.setEnabled(false);
//				this.btnEdit.setEnabled(false);
//				this.btnRemove.setEnabled(false);
//				this.btnCancel.setVisible(false);
//				this.btnCancelCancel.setVisible(false);
//				this.menuItemAddNew.setEnabled(false);
//				this.menuItemEdit.setEnabled(false);
//				this.menuItemRemove.setEnabled(false);
//			}
//		}
//	}

	/**
	 * output getSelectors method
	 */
	public SelectorItemCollection getSelectors() {
		SelectorItemCollection sic = new SelectorItemCollection();
		sic.add(new SelectorItemInfo("number"));
		sic.add(new SelectorItemInfo("isEnabled"));
		sic.add(new SelectorItemInfo("description"));
		sic.add(new SelectorItemInfo("name"));
		sic.add(new SelectorItemInfo("CU.id"));
		return sic;
	}

	/**
	 * 校验值对象的合法性
	 */
//	protected void verifyInput(ActionEvent e) throws Exception {
//		// 编码是否为空
//		if (this.txtNumber.getText() == null || this.txtNumber.getText().trim().equals("")) {
//			txtNumber.requestFocus(true);
//			throw new FDCBasedataException(FDCBasedataException.NUMBER_IS_EMPTY);
//		}
//		// 名称是否为空
//		boolean flag = FDCBaseDataClientUtils.isMultiLangBoxInputNameEmpty(bizName, this.editData, "name");
//		if (flag) {
//			bizName.requestFocus(true);
//			throw new FDCBasedataException(FDCBasedataException.NAME_IS_EMPTY);
//		}
//		if (getOprtState().equals(OprtState.ADDNEW))
//		FDCBaseTypeValidator.validate(((ProjectStatusListUI) getUIContext().get(UIContext.OWNER)).getMainTable(), txtNumber, bizName);
//	}

//	public void onShow() throws Exception {
//		super.onShow();
//		this.txtNumber.requestFocus();
//		FDCClientHelper.setActionEnable(actionEdit, false);
//		FDCClientHelper.setActionEnable(actionRemove, false);
//	}

	/**
	 * output actionCancel_actionPerformed
	 */
//	public void actionCancel_actionPerformed(ActionEvent e) throws Exception {
//		IProjectStatus iProjectStatus = ProjectStatusFactory.getRemoteInstance();
//		if (iProjectStatus.disEnabled(new ObjectUuidPK(editData.getId()))) {
//			this.showResultMessage(EASResource.getString(FDCBaseDataClientUtils.FDCBASEDATA_RESOURCE, "DisEnabled_OK"));
//			setDataObject(getValue(new ObjectUuidPK(editData.getId())));
//			loadFields();
//			setSave(true);
//			setSaved(true);
//			this.btnCancelCancel.setVisible(true);
//			this.btnCancelCancel.setEnabled(true);
//			this.btnCancel.setVisible(false);
//			this.chkIsEnabled.setSelected(false);
//		}
//	}

	/**
	 * output actionCancelCancel_actionPerformed
	 */
//	public void actionCancelCancel_actionPerformed(ActionEvent e) throws Exception {
//		IProjectStatus iProjectStatus = ProjectStatusFactory.getRemoteInstance();
//		if (iProjectStatus.enabled(new ObjectUuidPK(editData.getId()))) {
//			this.showResultMessage(EASResource.getString(FDCBaseDataClientUtils.FDCBASEDATA_RESOURCE, "Enabled_OK"));
//			setDataObject(getValue(new ObjectUuidPK(editData.getId())));
//			loadFields();
//			setSave(true);
//			setSaved(true);
//			this.btnCancel.setVisible(true);
//			this.btnCancel.setEnabled(true);
//			this.btnCancelCancel.setVisible(false);
//			this.chkIsEnabled.setSelected(true);
//		}
//	}
//
//	protected void showResultMessage(String message) {
//		// setMessageText(EASResource.getString(message));
//		setMessageText(message);
//		// setMessageIcon(SHOW_MESSAGE_ICON_ERROR);
//		// setMessageBgcolor(SHOW_MESSAGE_BG_ERROR);
//		showMessage();
//	}

	protected IObjectValue createNewData() {
		ProjectStatusInfo projectStatusInfo = new ProjectStatusInfo();
		projectStatusInfo.setIsEnabled(isEnabled);
		return projectStatusInfo;
	}

	protected ICoreBase getBizInterface() throws Exception {
		return ProjectStatusFactory.getRemoteInstance();
	}

	protected FDCDataBaseInfo getEditData() {
		return editData;
	}

	protected KDBizMultiLangBox getNameCtrl() {
		return bizName;
	}

	protected KDTextField getNumberCtrl() {
		return txtNumber;
	}

}