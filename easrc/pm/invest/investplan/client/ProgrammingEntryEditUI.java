/**
 * output package name
 */
package com.kingdee.eas.port.pm.invest.investplan.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

import org.apache.log4j.Logger;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.ctrl.extendcontrols.BizDataFormat;
import com.kingdee.bos.ctrl.extendcontrols.IDataFormat;
import com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox;
import com.kingdee.bos.ctrl.kdf.table.ICell;
import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.KDTDefaultCellEditor;
import com.kingdee.bos.ctrl.kdf.table.KDTStyleConstants;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent;
import com.kingdee.bos.ctrl.kdf.table.event.KDTMouseEvent;
import com.kingdee.bos.ctrl.kdf.util.render.ObjectValueRender;
import com.kingdee.bos.ctrl.kdf.util.style.Styles.HorizontalAlignment;
import com.kingdee.bos.ctrl.swing.KDContainer;
import com.kingdee.bos.ctrl.swing.KDFormattedTextField;
import com.kingdee.bos.ctrl.swing.KDTextField;
import com.kingdee.bos.ctrl.swing.KDWorkButton;
import com.kingdee.bos.ctrl.swing.LimitedLengthDocument;
import com.kingdee.bos.ctrl.swing.event.DataChangeEvent;
import com.kingdee.bos.ctrl.swing.event.DataChangeListener;
import com.kingdee.bos.dao.IObjectCollection;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.ui.face.IUIFactory;
import com.kingdee.bos.ui.face.IUIWindow;
import com.kingdee.bos.ui.face.UIFactory;
import com.kingdee.bos.ui.face.WinStyle;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.base.attachment.client.AttachmentUIContextInfo;
import com.kingdee.eas.base.attachment.common.AttachmentClientManager;
import com.kingdee.eas.base.attachment.common.AttachmentManagerFactory;
import com.kingdee.eas.basedata.assistant.ProjectInfo;
import com.kingdee.eas.basedata.org.CostCenterOrgUnitFactory;
import com.kingdee.eas.basedata.org.CostCenterOrgUnitInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.common.client.OprtState;
import com.kingdee.eas.common.client.UIFactoryName;
import com.kingdee.eas.fdc.basedata.CostAccountInfo;
import com.kingdee.eas.fdc.basedata.FDCHelper;
import com.kingdee.eas.fdc.basedata.FDCSQLBuilder;
import com.kingdee.eas.fdc.basedata.PaymentTypeFactory;
import com.kingdee.eas.fdc.basedata.PaymentTypeInfo;
import com.kingdee.eas.fdc.basedata.client.FDCMsgBox;
import com.kingdee.eas.fdc.contract.client.CostAccountF7UI;
import com.kingdee.eas.fdc.invite.InviteFormEnum;
import com.kingdee.eas.port.pm.base.InvestYearInfo;
import com.kingdee.eas.port.pm.invest.YearInvestPlanInfo;
import com.kingdee.eas.port.pm.invest.investplan.ProgrammingEntryCollection;
import com.kingdee.eas.port.pm.invest.investplan.ProgrammingEntryCostEntryCollection;
import com.kingdee.eas.port.pm.invest.investplan.ProgrammingEntryCostEntryInfo;
import com.kingdee.eas.port.pm.invest.investplan.ProgrammingEntryEconomyEntryCollection;
import com.kingdee.eas.port.pm.invest.investplan.ProgrammingEntryEconomyEntryFactory;
import com.kingdee.eas.port.pm.invest.investplan.ProgrammingEntryEconomyEntryInfo;
import com.kingdee.eas.port.pm.invest.investplan.ProgrammingEntryInfo;
import com.kingdee.eas.port.pm.utils.FDCUtils;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.eas.util.client.EASResource;
import com.kingdee.eas.util.client.ExceptionHandler;
import com.kingdee.eas.util.client.MsgBox;
import com.kingdee.eas.xr.helper.XRSQLBuilder;
import com.kingdee.jdbc.rowset.IRowSet;

/**
 * output class name
 */
public class ProgrammingEntryEditUI extends AbstractProgrammingEntryEditUI
{
	private static final Logger logger = CoreUIObject.getLogger(ProgrammingEntryEditUI.class);

	protected KDWorkButton btnAddnewLine_cost;
	protected KDWorkButton btnRemoveLines_cost;

	private ProgrammingEntryInfo pcInfo;
	private ProgrammingEntryInfo oldPcInfo;
	private ProgrammingEntryCollection pcCollection;

	private BigDecimal oldAmount;
	private BigDecimal oldControlAmount;
	private BigDecimal oldbalance;
	private BigDecimal oldcontrolBalance;

	// 成本构成分录表格列名
	private static final String COST_ID = "id";// ID
	private static final String PROJECT = "project";// 工程项目(F7)
	private static final String COSTACCOUNT_NUMBER = "number";// 成本科目编码
	private static final String INVESTYEAR = "investYear";//投资年度
	private static final String COSTACCOUNT = "name";// 成本科目名称(F7)
	private static final String GOALCOST = "goalCost";// 目标成本
	private static final String ASSIGNED = "assigned";// 已分配
	private static final String ASSIGNING = "assigning";// 待分配
	private static final String CONTRACTASSIGN = "contractAssign";// 本合约分配
	private static final String COST_DES = "description";// 备注


	public ProgrammingEntryEditUI() throws Exception {
		super();
	}

	public void onLoad() throws Exception {
		super.onLoad();
		toolBar.setVisible(true);
		initFormat();
		setSmallButton();
		kDTabbedPane1.remove(kDContainerEconomy);
		btnEdit.setVisible(false);
		
		Map uiContext = this.getUIContext();
		if(uiContext.get("proNumber")!=null&&OprtState.ADDNEW.equals(getOprtState()))
    	{
			StringBuffer sb = new StringBuffer();
    		sb.append(" select a.fid,a.CFProjectName,a.FNumber,b.fname_l2,isnull(sum(d.FAmount),0),isnull(sum(d.FCumulativeInvest),0),");
    		sb.append(" isnull(sum(d.FInvestAmount),0) ,isnull(sum(d.FBalance),0),case when isnull(sum(d.FAmount),0)<>0 then isnull(sum(d.FInvestAmount),0)/isnull(sum(d.FAmount),0)  else 0 end");
    		sb.append(" from CT_INV_YearInvestPlan a");
    		sb.append(" left join CT_BAS_InvestYear b on b.fid=a.CFYearID");
    		sb.append(" left join CT_INV_Programming c on c.fsourcebillid = a.fnumber");
    		sb.append(" left join CT_INV_Programmingentry d on d.FProgrammingID=c.fid");
    		sb.append(" WHERE A.FNUMBER IN('NDJH20140624-020','NDJH20140627-027','NDJH20140630-028')");
    		sb.append(" group by a.fid,a.CFProjectName,a.FNumber,b.fname_l2");
    		
    		IRowSet rowset = new XRSQLBuilder().appendSql(sb.toString()).executeQuery();
    		while(rowset.next())
    		{
    			IRow row = this.kdtCost.addRow();
    			row.getCell(0).setValue(rowset.getString(1));
    			row.getCell(1).setValue(rowset.getString(2));
    			row.getCell(2).setValue(rowset.getString(3));
    			row.getCell(3).setValue(rowset.getString(4));
    			row.getCell(4).setValue(rowset.getString(5));
    			row.getCell(5).setValue(rowset.getString(6));
    			row.getCell(6).setValue(rowset.getString(7));
    			row.getCell(7).setValue(rowset.getString(8));
    			row.getCell(8).setValue(rowset.getString(9));
    		}
			
    	}
		
		
		if (this.oprtState.equals(OprtState.VIEW)) {
			isEditable(false);
			ctrButtonEnable(false);
		} else if (this.oprtState.equals(OprtState.EDIT) || this.oprtState.equals(OprtState.ADDNEW)) {
			isEditable(true);
			ctrButtonEnable(true);
		}
		
		txtAmount.addDataChangeListener(new DataChangeListener() {
			public void dataChanged(DataChangeEvent e) {
				if (e.getOldValue() != e.getNewValue()) {
					

				}
			}
		});
	}
	
	public void actionSelect_actionPerformed(ActionEvent e) throws Exception {
		Map ctx = new HashMap();
		ProjectInfo project = (ProjectInfo) this.getUIContext().get("project");
//		ctx.put("query", this.getQueryInfo());
		EntityViewInfo entityView = new EntityViewInfo();
		FilterInfo filter = new FilterInfo();
		if (project != null) {
			filter.getFilterItems().add(new FilterItemInfo("curProject.id", project.getId().toString(), CompareType.EQUALS));
		} else {
			filter.getFilterItems().add(new FilterItemInfo("curProject.id", "error", CompareType.EQUALS));
		}
		entityView.setFilter(filter);
		ctx.put("view", entityView);
		ctx.put("col", this.pcCollection);
//		ctx.put("aimCost", (AimCostInfo) this.getUIContext().get("aimCostInfo"));
		try  {
			IUIFactory uiFactory = UIFactory.createUIFactory(UIFactoryName.MODEL);
			IUIWindow costAccountDialog = uiFactory.create(CostAccountF7UI.class.getName(),ctx,null,OprtState.VIEW,WinStyle.SHOW_ONLYLEFTSTATUSBAR);
	        costAccountDialog.show();
	        CostAccountF7UI caF7UI = (CostAccountF7UI) costAccountDialog.getUIObject();
	        CostAccountInfo [] newCostAccount=null;
	        if(!caF7UI.isCancel()){
//	        	newCostAccount=caF7UI.getData();
	        }
	        if(newCostAccount!=null&&newCostAccount.length>0){
	        	
	        	List account=new ArrayList();
	        	for(int j=0;j<newCostAccount.length;j++){
	        		boolean isAdd=true;
	        		CostAccountInfo newCostAccountInfo=newCostAccount[j];
	        		for(int i=0;i<kdtCost.getRowCount();i++){
		        		CostAccountInfo costAccountObj = (CostAccountInfo)kdtCost.getCell(i, COSTACCOUNT).getValue();
	        			if(costAccountObj!=null&&newCostAccountInfo.getId().equals(costAccountObj.getId())){
	        				isAdd=false;
	        				break;
	        			}
	        		}
	        		if(isAdd){
	        			account.add(newCostAccountInfo);
	        		}
        		}
	        	BigDecimal allAssigned = FDCHelper.ZERO;
				for(int i=0;i<account.size();i++){
					CostAccountInfo newCostAccountInfo=(CostAccountInfo) account.get(i);
//					for(int j=0;j<kdtCost.getRowCount();j++){
//						if (isCostAccountDup(newCostAccountInfo, project, j)) {
//							continue;
//						}
//					}
					IRow row=kdtCost.addRow();
					int rowIndex=row.getRowIndex();
					kdtCost.getCell(rowIndex, COSTACCOUNT).setValue(newCostAccountInfo);
					row.getCell(COST_ID).setValue(BOSUuid.create("9E6FDD26"));
					row.getCell(PROJECT).setValue(project);
					//初始化 目标成本，已分配，待分配，本合约分配
					row.getCell(GOALCOST).setValue(FDCHelper.ZERO);
					row.getCell(ASSIGNED).setValue(FDCHelper.ZERO);
					row.getCell(ASSIGNING).setValue(FDCHelper.ZERO);
					row.getCell(CONTRACTASSIGN).setValue(FDCHelper.ZERO);
					projectF7();
					costAccountCellF7(project, rowIndex, kdtCost.getColumnIndex(COSTACCOUNT),this.pcCollection);

//					kdtCost.getCell(rowIndex, COSTACCOUNT_NUMBER).setValue(newCostAccountInfo.getLongNumber().replace('!', '.'));
					// 2.
//					AimCostInfo aimCostInfo = (AimCostInfo) this.getUIContext().get("aimCostInfo");// 目标成本
//					if (aimCostInfo == null) {
//						// 2.1
////						ProgrammingEntryUtil.clearCell(kdtCost, rowIndex, GOALCOST, ASSIGNED, ASSIGNING, CONTRACTASSIGN, COST_DES);
//						ProgrammingEntryUtil.setZero(kdtCost, rowIndex, GOALCOST, ASSIGNED, ASSIGNING, CONTRACTASSIGN, COST_DES);
//					} 
//					else {
                       // 2.2是否是已审批
//						if (!isAimCostAudit(aimCostInfo)) {
//							// 2.2.1
//							ProgrammingEntryUtil.setZero(kdtCost, rowIndex, GOALCOST, ASSIGNED, ASSIGNING, CONTRACTASSIGN, COST_DES);
//						} 
//					else {
//							// 2.2.2 取出目标成本的值
//							// ProjectInfo project = (ProjectInfo) kdtCost.getCell(rowIndex, PROJECT).getValue();
//							if (project != null) {
//								BigDecimal goalCost = ProgrammingEntryUtil.getGoalCostBy_costAcc_aimCost(newCostAccountInfo,
//										aimCostInfo);
//								if (goalCost.compareTo(FDCHelper.ZERO) == 0) {
//									ProgrammingEntryUtil.setZero(kdtCost, rowIndex, GOALCOST, ASSIGNED, ASSIGNING, CONTRACTASSIGN, COST_DES);
//									afterContractAssignChange();
//									afterPlanAmountChange();
//								} else {
//									allAssigned = getAllContractAssign(newCostAccountInfo, false);// 已分配
//									// 算出"待分配" == "目标成本" - "已分配"
//									BigDecimal assigning = goalCost.subtract(allAssigned);// 待分配
//									// 带出"本合约分配"="待分配"
//									BigDecimal contractAssign = assigning;// 本合约分配
//									// 显示在单元格中
//									kdtCost.getCell(rowIndex, GOALCOST).setValue(goalCost);// 目标成本
//									kdtCost.getCell(rowIndex, ASSIGNED).setValue(allAssigned);// 已分配
//									kdtCost.getCell(rowIndex, ASSIGNING).setValue(assigning);// 待分配
//									kdtCost.getCell(rowIndex, CONTRACTASSIGN).setValue(contractAssign);// 本合约分配
//
//									// 本合约分配带出后又自动算出"规划金额"
//									afterContractAssignChange();
//									// 自动算出"规划金额"后，动态改变经济条款中"付款比例"和"付款金额"
//									// 默认以"付款比例"为定值，改变"付款金额"
//									afterPlanAmountChange();
//								}
//							}
//						}
//					}
				}
	        }
		}catch (BOSException ex) {
	        ExceptionHandler.handle(ex);
	        SysUtil.abort();
		}
	}

	public void onShow() throws Exception {
		super.onShow();
		initTabalFormat();
		this.btnAttachment.setIcon(EASResource.getIcon("imgTbtn_affixmanage"));
		this.txtParentLongName.setEditable(false);// 上级合约框架长名称不可编辑
		this.txtAttachment.setEditable(false);// 附件文本框仅提供浏览，可根据操作状态决定在附件管理器里是编辑状态还是查看
		Map uiContext = this.getUIContext();
		Object object = uiContext.get("inviteProject");
		Object contractBillProg = uiContext.get("programmingContractTemp");
		if (object != null || contractBillProg != null) {
			this.btnSave.setVisible(false);// 招标立项关联里查看进入，把保存按钮去掉
		}
		pcInfo = (ProgrammingEntryInfo) uiContext.get("programmingContract");
		if (pcInfo.getId() == null) {
			pcInfo.setId(BOSUuid.create(pcInfo.getBOSType()));
		}
		oldPcInfo = pcInfo;

		oldAmount = pcInfo.getAmount();
		if(oldAmount==null){
			oldAmount = FDCHelper.ZERO;
		}
		oldControlAmount = pcInfo.getControlAmount();
		if(oldControlAmount == null){
			oldControlAmount = FDCHelper.ZERO;
		}
		oldbalance = pcInfo.getBalance();
		if (oldbalance == null) {
			oldbalance = FDCHelper.ZERO;
		}
		oldcontrolBalance = pcInfo.getControlBalance();
		if (oldcontrolBalance == null) {
			oldcontrolBalance = FDCHelper.ZERO;
		}
		pcCollection = (ProgrammingEntryCollection) uiContext.get("pcCollection");
		preparePCData();
		
		txtName.setDocument(new LimitedLengthDocument() {
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (str.matches("^\\.+$")) {
					return;
				}
				super.insertString(offs, str, a);
			}

			public void remove(int offs, int len) throws BadLocationException {
				super.remove(offs, len);
			}
		});
		txtName.setText(pcInfo.getName() == null ? null : pcInfo.getName().trim());
		
	}

	/**
	 * 在投资规划页签中添加新增、删除按钮
	 */
	private void setSmallButton() {
		btnAddnewLine_cost = new KDWorkButton();
		btnRemoveLines_cost = new KDWorkButton();

		btnAddnewLine_cost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					actionAddnewLine_cost_actionPerformed(e);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

		});
		btnRemoveLines_cost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					actionRemoveLine_cost_actionPerformed(e);
					if (kdtCost.getRowCount() == 0) {
						btnRemoveLines_cost.setEnabled(false);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		setButtonStyle(kdContainerCost, btnAddnewLine_cost, "新增行", "imgTbtn_addline");
		setButtonStyle(kdContainerCost, btnRemoveLines_cost, "删除行", "imgTbtn_deleteline");

		if (OprtState.VIEW.equals(getOprtState())) {
			setButtionEnable(false);
		} else {
			setButtionEnable(true);
		}
	}

	/**
	 * 设置按钮样式
	 * 
	 * @param kdContainer
	 * @param button
	 * @param text
	 * @param icon
	 */
	private void setButtonStyle(KDContainer kdContainer, KDWorkButton button, String text, String icon) {
		button.setText(text);
		button.setToolTipText(text);
		button.setVisible(true);
		button.setIcon(EASResource.getIcon(icon));
		kdContainer.addButton(button);
	}

	/**
	 * 控制按钮是否可用
	 * 
	 * @param isEnable
	 */
	private void setButtionEnable(boolean isEnable) {
		btnAddnewLine_cost.setEnabled(isEnable);
		btnRemoveLines_cost.setEnabled(isEnable);
		this.actionSelect.setEnabled(isEnable);
	}
	protected void actionAddnewLine_cost_actionPerformed(ActionEvent e) {
		IRow row = kdtCost.addRow();
		int rowIndex = row.getRowIndex();
		YearInvestPlanInfo yipInfo = (YearInvestPlanInfo) this.getUIContext().get("PlanNumber");

//		ProjectInfo project = (ProjectInfo) this.getUIContext().get("project");
		row.getCell(COST_ID).setValue(BOSUuid.create("9E6FDD26"));
		row.getCell(PROJECT).setValue(yipInfo);
		row.getCell(COSTACCOUNT_NUMBER).setValue(yipInfo.getNumber());
		row.getCell(COSTACCOUNT_NUMBER).getStyleAttributes().setLocked(true);
		//初始化 目标成本，已分配，待分配，本合约分配
		row.getCell(GOALCOST).setValue(FDCHelper.ZERO);
		row.getCell(ASSIGNED).setValue(FDCHelper.ZERO);
		row.getCell(ASSIGNING).setValue(FDCHelper.ZERO);
		row.getCell(CONTRACTASSIGN).setValue(FDCHelper.ZERO);
		projectF7();
//		costAccountCellF7(project, rowIndex, kdtCost.getColumnIndex(COSTACCOUNT),this.pcCollection);
	}

	protected void actionRemoveLine_cost_actionPerformed(ActionEvent e) throws Exception {
		if (kdtCost.getSelectManager().getActiveRowIndex() < 0) {
			FDCMsgBox.showInfo("请选择行");
			SysUtil.abort();
		}
		if (FDCMsgBox.OK == FDCMsgBox.showConfirm2("是否确认删除数据？")) {
//			if(pcInfo.isIsCiting()||pcInfo.isIsWTCiting()){
//	    		MsgBox.showInfo("存在被引用的框架合约,无法删除！");
//	    		SysUtil.abort();
//	    	}
			int rowIndex = this.kdtCost.getSelectManager().getActiveRowIndex();
			Object contractAssignObj = kdtCost.getCell(rowIndex, CONTRACTASSIGN).getValue();
			if (contractAssignObj != null) {
				BigDecimal contractAssign = new BigDecimal(contractAssignObj.toString());
				Object amountObj = this.txtAmount.getValue();
				if (amountObj != null) {
					BigDecimal amount = new BigDecimal(amountObj.toString());
					txtAmount.setValue(amount.subtract(contractAssign));
					afterPlanAmountChange();
				}
			}
			removeLine(kdtCost, rowIndex);
		}
	}

	protected void actionAddnewLine_enocomy_actionPerformed(ActionEvent e) {
	}

	protected void actionRemoveLine_enocomy_actionPerformed(ActionEvent e) throws Exception {
	}

	/**
	 * 显示单据行
	 */
	protected void loadLineFields(KDTable table, IRow row, IObjectValue obj) {
		dataBinder.loadLineFields(table, row, obj);
	}

	/**
	 * 新建单据行，返回一个新的分录行的默认值
	 */
	protected IObjectValue createNewDetailData(KDTable table, Object obj) {
		if (table == null) {
			return null;
		}
		if (obj instanceof ProgrammingEntryCostEntryInfo) {
			ProgrammingEntryCostEntryInfo newDetailInfo = (ProgrammingEntryCostEntryInfo) obj;
			newDetailInfo.setId(BOSUuid.create("4F1A141F"));
			return (IObjectValue) newDetailInfo;
		}
		if (obj instanceof ProgrammingEntryEconomyEntryInfo) {
			ProgrammingEntryEconomyEntryInfo newDetailInfo = new ProgrammingEntryEconomyEntryInfo();
			newDetailInfo.setId(BOSUuid.create("144467E3"));
			return (IObjectValue) newDetailInfo;
		}
		
		return null;
	}

	/**
	 * 在指定表格中删除指定的行
	 * 
	 * @param table
	 * @param rowIndex
	 * @throws Exception
	 */
	protected void removeLine(KDTable table, int rowIndex) throws Exception {
		IObjectValue detailData = (IObjectValue) table.getRow(rowIndex).getUserObject();
		table.removeRow(rowIndex);
		IObjectCollection collection = (IObjectCollection) table.getUserObject();
		if (collection != null) {
			if (detailData != null) {
				collection.removeObject(rowIndex);
			}
		}
	}

	/**
	 * 格式化KDFormattedTextField文本框
	 */
	private void initFormat(){
		setTextFormat(txtAmount);
		setTextFormat(txtControlAmount);
		setTextFormat(txtEstimateAmount);
		filterORG();
	}

	private void filterORG() {
		EntityViewInfo view = prmtInviteOrg.getEntityViewInfo();
		if (view == null) {
			view = new EntityViewInfo();
		}
		if (view.getFilter() == null) {
			view.setFilter(new FilterInfo());
		}
		Set idSet = null;
		try {
			idSet = FDCUtils.getAuthorizedOrgs(null);
		} catch (Exception e) {
			logger.error(e);
		}
		view.getFilter().getFilterItems().add(new FilterItemInfo("COSTCENTERORGUNIT.id", idSet, CompareType.INCLUDE));
		prmtInviteOrg.setEntityViewInfo(view);
	}

	private void initTabalFormat(){
		// kdtCost.getSelectManager().setSelectMode(KDTSelectManager.CELL_SELECT);
		// kdtCost.setActiveCellStatus(KDTStyleConstants.ACTIVE_CELL_EDIT);
		// kdtEconomy.getSelectManager().setSelectMode(KDTSelectManager.CELL_SELECT);
		// kdtEconomy.setActiveCellStatus(KDTStyleConstants.ACTIVE_CELL_EDIT);
		/* 成本构成 */
		this.kdtCost.checkParsed();
		// 目标成本
		FDCHelper.formatTableNumber(kdtCost, GOALCOST);
		// 已分配
		FDCHelper.formatTableNumber(kdtCost, ASSIGNED);
		// 待分配
		FDCHelper.formatTableNumber(kdtCost, ASSIGNING);
		// 本合约分配
		FDCHelper.formatTableNumber(kdtCost, CONTRACTASSIGN);
		KDTDefaultCellEditor cellEditor0 = new KDTDefaultCellEditor(new KDFormattedTextField());
		KDFormattedTextField kdftf = (KDFormattedTextField) cellEditor0.getComponent();
		kdftf.setDataType(1);
		kdftf.setSupportedEmpty(true);
		kdftf.setMinimumValue(FDCHelper.ZERO);
		kdftf.setPrecision(2);
		kdtCost.getColumn(CONTRACTASSIGN).setEditor(cellEditor0);
		
		// 备注
		KDTDefaultCellEditor cellEditorDes = new KDTDefaultCellEditor(new KDTextField());
		KDTextField kdtfDes = (KDTextField) cellEditorDes.getComponent();
		kdtfDes.setMaxLength(80);
		this.kdtCost.getColumn(COST_DES).setEditor(cellEditorDes);

		KDFormattedTextField txtWeight = new KDFormattedTextField();
		txtWeight.setDataType(KDFormattedTextField.BIGDECIMAL_TYPE);
		txtWeight.setDataVerifierType(KDFormattedTextField.NO_VERIFIER);
		txtWeight.setPrecision(2);
		txtWeight.setMaximumValue(new BigDecimal(100));
		txtWeight.setMinimumValue(FDCHelper.ZERO);
		KDTDefaultCellEditor weight = new KDTDefaultCellEditor(txtWeight);
		this.kdtCost.getColumn("scale").setEditor(weight);
		this.kdtCost.getColumn("scale").getStyleAttributes().setNumberFormat("#0.00");
		this.kdtCost.getColumn("scale").getStyleAttributes().setHorizontalAlign(HorizontalAlignment.RIGHT);
		

	}
	/**
	 * 初始化KDFormattedTextField的相关基础属性
	 * */
	private static void setTextFormat(KDFormattedTextField textField) {
		textField.setRemoveingZeroInDispaly(false);
		textField.setRemoveingZeroInEdit(false);
		textField.setPrecision(2);
		textField.setHorizontalAlignment(JTextField.RIGHT);
		textField.setSupportedEmpty(true);
	}
	/**
	 * 按钮控制
	 * 
	 * @param b
	 */
	private void ctrButtonEnable(boolean isEnable) {
		btnAttachment.setEnabled(true);
		btnSave.setEnabled(isEnable);
		btnAddnewLine_cost.setEnabled(isEnable);
		btnRemoveLines_cost.setEnabled(false);
		this.actionSelect.setEnabled(isEnable);
	}

	/**
	 * 编辑控制
	 * 
	 * @param b
	 */
	private void isEditable(boolean isEditable) {
		// 文件编辑框
		txtParentLongName.setEditable(isEditable);
		txtNumber.setEditable(isEditable);
		txtName.setEditable(isEditable);
		txtAmount.setEditable(isEditable);
		txtControlAmount.setEditable(isEditable);
		txtEstimateAmount.setEditable(isEditable);

		txtWorkContent.setEditable(isEditable);
		txtSupMaterial.setEditable(isEditable);
		txtDescription.setEditable(isEditable);
		kdcInviteWay.setEditable(isEditable);
		kdcInviteWay.setEnabled(isEditable);
		prmtInviteOrg.setEditable(isEditable);
		prmtInviteOrg.setEnabled(isEditable);
	    
		// 分录
		kdtCost.setEditable(isEditable);
//		kdtEconomy.setEditable(isEditable);
	}

	/**
	 * 关闭窗口前事件
	 */
	private boolean directExit = false;
	protected boolean checkBeforeWindowClosing() {
		this.kdtCost.getEditManager().editingStopped();
		this.kdtEconomy.getEditManager().editingStopped();
		try {
			this.txtControlAmount.commitEdit();
			this.txtAmount.commitEdit();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		if (this.oprtState.equals(OprtState.ADDNEW) || this.oprtState.equals(OprtState.EDIT)) {
			if (verifyIsModify()) {
				int i = FDCMsgBox.showConfirm3("数据已修改，是否保存并退出?");
				if (i == FDCMsgBox.OK) {
					try {
						directExit = true;
						actionSubmit_actionPerformed(null);
					} catch (Exception e) {
						SysUtil.abort();
						e.printStackTrace();
					}
				} else if (i == FDCMsgBox.NO) {
					return super.checkBeforeWindowClosing();
				} else if (i == FDCMsgBox.CANCEL) {
					return false;
				}
			}
		}
		return super.checkBeforeWindowClosing();
	}

	/**
	 * 判断单据是否已做修改
	 * 
	 * @return
	 * 
	 *         true表示已修改过
	 * 
	 *         false表示未做修改
	 */
	private boolean verifyIsModify() {
		if (oprtState.equals(OprtState.EDIT) || oprtState.equals(OprtState.ADDNEW)) {
			// 长编码
			if (FDCHelper.isEmpty(txtNumber.getText()) ^ FDCHelper.isEmpty(oldPcInfo.getLongNumber())) {
				return true;
			}
			if (!FDCHelper.isEmpty(txtNumber.getText()) & !FDCHelper.isEmpty(oldPcInfo.getLongNumber())) {
				if (!txtNumber.getText().equals(oldPcInfo.getLongNumber())) {
					return true;
				}
			}
			// 名称
			if (FDCHelper.isEmpty(txtName.getText()) ^ FDCHelper.isEmpty(oldPcInfo.getName())) {
				return true;
			}
			if (!FDCHelper.isEmpty(txtName.getText()) & !FDCHelper.isEmpty(oldPcInfo.getName())) {
				if (!txtName.getText().equals(oldPcInfo.getName().trim())) {
					return true;
				}
			}
			// 控制金额
			if (FDCHelper.isEmpty(txtControlAmount.getNumberValue()) ^ FDCHelper.isEmpty(oldPcInfo.getControlAmount())) {
				return true;
			}
			if (!FDCHelper.isEmpty(txtControlAmount.getNumberValue()) & !FDCHelper.isEmpty(oldPcInfo.getControlAmount())) {
				if (new BigDecimal(txtControlAmount.getNumberValue().toString()).compareTo(oldPcInfo.getControlAmount()) != 0) {
					return true;
				}
			}

			// 规划金额
			if (FDCHelper.isEmpty(txtAmount.getNumberValue()) ^ FDCHelper.isEmpty(oldPcInfo.getAmount())) {
				return true;
			}
			if (!FDCHelper.isEmpty(txtAmount.getNumberValue()) & !FDCHelper.isEmpty(oldPcInfo.getAmount())) {
				if (new BigDecimal(txtAmount.getNumberValue().toString()).compareTo(oldPcInfo.getAmount()) != 0) {
					return true;
				}
			}

			// 招标方式
			if (FDCHelper.isEmpty(kdcInviteWay.getSelectedItem()) ^ FDCHelper.isEmpty(oldPcInfo.getInviteWay())) {
				return true;
			}
			if (!FDCHelper.isEmpty(kdcInviteWay.getSelectedItem()) & !FDCHelper.isEmpty(oldPcInfo.getInviteWay())) {
				if (!kdcInviteWay.getSelectedItem().equals(oldPcInfo.getInviteWay())) {
					return true;
				}
			}

			// 招标组织
			if (FDCHelper.isEmpty(prmtInviteOrg.getData()) ^ FDCHelper.isEmpty(oldPcInfo.getInviteOrg())) {
				return true;
			}
			if (!FDCHelper.isEmpty(prmtInviteOrg.getData()) & !FDCHelper.isEmpty(oldPcInfo.getInviteOrg())) {
				if (!prmtInviteOrg.getData().equals(oldPcInfo.getInviteOrg())) {
					return true;
				}
			}

			// 工作内容
			if (FDCHelper.isEmpty(txtWorkContent.getText()) ^ FDCHelper.isEmpty(oldPcInfo.getWorkContent())) {
				return true;
			}
			if (!FDCHelper.isEmpty(txtWorkContent.getText()) & !FDCHelper.isEmpty(oldPcInfo.getWorkContent())) {
				if (!txtWorkContent.getText().equals(oldPcInfo.getWorkContent())) {
					return true;
				}
			}
			// 甲供及甲指材设
			if (FDCHelper.isEmpty(txtSupMaterial.getText()) ^ FDCHelper.isEmpty(oldPcInfo.getSupMaterial())) {
				return true;
			}
			if (!FDCHelper.isEmpty(txtSupMaterial.getText()) & !FDCHelper.isEmpty(oldPcInfo.getSupMaterial())) {
				if (!txtSupMaterial.getText().equals(oldPcInfo.getSupMaterial())) {
					return true;
				}
			}
			
			// 备注
			if (FDCHelper.isEmpty(txtDescription.getText()) ^ FDCHelper.isEmpty(oldPcInfo.getDescription())) {
				return true;
			}
			if (!FDCHelper.isEmpty(txtDescription.getText()) & !FDCHelper.isEmpty(oldPcInfo.getDescription())) {
				if (!txtDescription.getText().equals(oldPcInfo.getDescription())) {
					return true;
				}
			}
			// 附件
			// if (FDCHelper.isEmpty(txtAttachment.getText()) ^ FDCHelper.isEmpty(oldPcInfo.getAttachment())) {
			// return true;
			// }
			// if (!FDCHelper.isEmpty(txtAttachment.getText()) & !FDCHelper.isEmpty(oldPcInfo.getAttachment())) {
			// if (!txtAttachment.getText().equals(oldPcInfo.getAttachment())) {
			// return true;
			// }
			// }

			if (kdtCost.getRowCount() == oldPcInfo.getCostEntries().size()) {
				for (int i = 0; i < kdtCost.getRowCount(); i++) {
					for (int j = 0; j < oldPcInfo.getCostEntries().size(); j++) {
						if (oldPcInfo.getCostEntries().get(j).getId() == kdtCost.getCell(i, COST_ID).getValue()) {
							ProjectInfo project = (ProjectInfo) this.getUIContext().get("project");
							CostAccountInfo infoCA = oldPcInfo.getCostEntries().get(j).getCostAccount();
							BigDecimal infoContractAssign = oldPcInfo.getCostEntries().get(j).getContractAssign();
							String infoDescription = oldPcInfo.getCostEntries().get(j).getDescription();

							ProjectInfo tablePro = (ProjectInfo) kdtCost.getCell(j, PROJECT).getValue();
							CostAccountInfo tableCA = (CostAccountInfo) kdtCost.getCell(j, COSTACCOUNT).getValue();
							Object contractAssign = kdtCost.getCell(j, CONTRACTASSIGN).getValue();
							Object description = kdtCost.getCell(j, COST_DES).getValue();

							// 工程项目
							if (FDCHelper.isEmpty(project) ^ FDCHelper.isEmpty(tablePro)) {
								return true;
							}
							if (!FDCHelper.isEmpty(project) & !FDCHelper.isEmpty(tablePro)) {
								if (!project.getName().equals(tablePro.getName())) {
									return true;
								}
							}
							// 成本科目F7
							if (FDCHelper.isEmpty(infoCA) ^ FDCHelper.isEmpty(tableCA)) {
								return true;
							}
							if (!FDCHelper.isEmpty(infoCA) & !FDCHelper.isEmpty(tableCA)) {
								if (!infoCA.getName().equals(tableCA.getName())) {
									return true;
								}
							}
							// 本合约分配
							if (FDCHelper.isEmpty(infoContractAssign) ^ FDCHelper.isEmpty(contractAssign)) {
								return true;
							}
							if (!FDCHelper.isEmpty(infoContractAssign) & !FDCHelper.isEmpty(contractAssign)) {
								BigDecimal temp = new BigDecimal(contractAssign.toString());
								if (infoContractAssign.compareTo(temp) != 0) {
									return true;
								}
							}
							// 备注
							if (FDCHelper.isEmpty(infoDescription) ^ FDCHelper.isEmpty(description)) {
								return true;
							}
							if (!FDCHelper.isEmpty(infoDescription) & !FDCHelper.isEmpty(description)) {
								if (!infoDescription.equals(description.toString())) {
									return true;
								}
							}
						}
					}
				}
			} else {
				return true;
			}
		}
		if (oprtState.equals(OprtState.VIEW)) {
			return false;
		}
		return false;
	}

	/**
	 * 保存
	 */
	public void actionSubmit_actionPerformed(ActionEvent e) throws Exception {
		verifyIsEmpty();
		verifyAllData();
		/* 保存单据头信息 */
		pcInfo.setLongNumber(txtNumber.getText());// 长编码
		if (FDCHelper.isEmpty(txtParentLongName.getText())) {
			pcInfo.setDisplayName(txtParentLongName.getText());// 上级合约长名称
		} else {
			pcInfo.setDisplayName(txtParentLongName.getText() + "." + txtName.getText());
		}
		pcInfo.setNumber(txtNumber.getText());
		pcInfo.setName(setNameIndent(pcInfo.getLevel()) + txtName.getText());// 名称
		pcInfo.setInviteWay((InviteFormEnum) kdcInviteWay.getSelectedItem());// 招标方式
		pcInfo.setInviteOrg((CostCenterOrgUnitInfo) prmtInviteOrg.getData());		
		pcInfo.setAmount((BigDecimal) txtAmount.getValue());// 规划金额
		pcInfo.setControlAmount((BigDecimal) txtControlAmount.getValue());// 控制金额
		pcInfo.setWorkContent(txtWorkContent.getText());// 工作内容
		pcInfo.setSupMaterial(txtSupMaterial.getText());// 甲供及甲指材设	
		pcInfo.setDescription(txtDescription.getText());// 备注
		if (FDCHelper.isEmpty(txtAttachment.getText())) {
			pcInfo.setAttachment(null);// 附件
		} else {
			pcInfo.setAttachment(txtAttachment.getText());// 附件
		}
		
		updateBalance(pcInfo);
		/* 保存单据分录信息 */
		/* 成本构成 */
		pcInfo.getCostEntries().clear();
		int cost_rowCount = kdtCost.getRowCount();
		StringBuffer costAccountNames = new StringBuffer(); 
		for (int i = 0; i < cost_rowCount; i++) {
			ProgrammingEntryCostEntryInfo pccInfo = new ProgrammingEntryCostEntryInfo();
			Object project = kdtCost.getCell(i, PROJECT).getValue();// 工程项目
			Object costAccount = kdtCost.getCell(i, COSTACCOUNT).getValue();// 成本科目
			Object goalCost = kdtCost.getCell(i, GOALCOST).getValue();// 目标成本
			Object assigned = kdtCost.getCell(i, ASSIGNED).getValue();// 已分配
			Object assigning = kdtCost.getCell(i, ASSIGNING).getValue();// 待分配
			Object contractAssign = kdtCost.getCell(i, CONTRACTASSIGN).getValue();// 本合约分配
			String description = (String) kdtCost.getCell(i, COST_DES).getValue();// 备注

//			ProjectInfo projectInfo = (ProjectInfo) project;
			CostAccountInfo costAccountInfo = (CostAccountInfo) costAccount;
			if(i > 0){
				costAccountNames.append(";");
			}
//			costAccountNames.append(costAccountInfo.getName());
//			costAccountInfo.setCurProject(projectInfo);
			pccInfo.setCostAccount(costAccountInfo);
			if (!FDCHelper.isEmpty(goalCost)) {
				pccInfo.setGoalCost(new BigDecimal(goalCost.toString()));
			}
			if (!FDCHelper.isEmpty(assigned)) {
				pccInfo.setAssigned(new BigDecimal(assigned.toString()));
			}
			if (!FDCHelper.isEmpty(assigning)) {
				pccInfo.setAssigning(new BigDecimal(assigning.toString()));
			}
			if (!FDCHelper.isEmpty(contractAssign)) {
				pccInfo.setContractAssign(new BigDecimal(contractAssign.toString()));
			}
			pccInfo.setDescription(description);
			pcInfo.getCostEntries().add(pccInfo);
		}
		pcInfo.setCostAccountNames(costAccountNames.toString());
		// 动态更新成本构成"待分配"的值
		int pteSize = pcCollection.size();
		for (int i = 0; i < pteSize; i++) {
			ProgrammingEntryCostEntryCollection pccCollection = pcCollection.get(i).getCostEntries();// 成本构成集合
			for (int j = 0; j < pccCollection.size(); j++) {
				ProgrammingEntryCostEntryInfo pccInfo = pccCollection.get(j);// 成本构成
				BigDecimal oldContractAssign = pccInfo.getContractAssign();// 本合约分配
				CostAccountInfo costAccountInfo = pccInfo.getCostAccount();// 成本科目
				BigDecimal goalCost = pccInfo.getGoalCost();// 目标成本
				if (costAccountInfo != null) {
					BigDecimal allCostAccountAssign = getAllContractAssign(costAccountInfo, true);// "本合约分配"金额之和
					if (oldContractAssign != null) {
						// 更新"待分配":"目标成本"-"本合约分配"金额之和+自身"本合约分配"
						BigDecimal newAssigning = goalCost.subtract(allCostAccountAssign).add(oldContractAssign);
						// 更新"已分配"："本合约分配"金额之和-自身"本合约分配"
						BigDecimal newAssigned = allCostAccountAssign.subtract(oldContractAssign);
						pccInfo.setAssigning(newAssigning);
						pccInfo.setAssigned(newAssigned);
					}
				}
			}

		}

		if (directExit) {
			// 直接保存退出，不作提示
			// verifyIsEmpty();
			// verifyAllData();
		} else {
			FDCMsgBox.showInfo("保存框架合约成功！");
		}
		oldPcInfo = pcInfo;
		// destroyWindow();
	}

	/**
	 * 更新规划余额，控制余额
	 */
	private void updateBalance(ProgrammingEntryInfo pcInfo) {
		BigDecimal newAmount = pcInfo.getAmount();// 规划金额
		if (newAmount == null) {
			newAmount = FDCHelper.ZERO;
		}
		BigDecimal newControlAmount = pcInfo.getControlAmount();// 控制金额
		if (newControlAmount == null) {
			newControlAmount = FDCHelper.ZERO;
		}
		pcInfo.setControlBalance(newAmount);
		pcInfo.setBalance(oldbalance.add(newAmount.subtract(oldAmount)));
		//新增时的未签合同金额
		if(!(pcInfo.isIsCiting()||pcInfo.isIsWTCiting())){
			pcInfo.setBudgetAmount(newAmount);
		}else{
			pcInfo.setBudgetAmount(FDCHelper.ZERO);
		}
	}

	/**
	 * 在名称前添加空格，显示缩进效果
	 * 
	 * @param level
	 * @return
	 */
	private String setNameIndent(int level) {
		StringBuffer blank = new StringBuffer("");
		for (int i = level; i > 1; i--) {
			blank.append("        ");
		}
		return blank.toString();
	}

	/**
	 * 必录项判空验证
	 */
	private void verifyIsEmpty() {
		ProgrammingEntryInfo head = pcInfo.getParent();
		if (head != null) {
			String longNumber = head.getLongNumber();
			if (txtNumber.getText().equals(longNumber + ".")) {
				FDCMsgBox.showInfo("框架合约编码不能为空！");
				txtNumber.requestFocus();
				SysUtil.abort();
			}
			for (int i = 0; i < pcCollection.size(); i++) {
				ProgrammingEntryInfo programmingContractInfo = pcCollection.get(i);
				if (programmingContractInfo.getLongNumber().equals(txtNumber.getText())
						&& !pcInfo.getId().toString().equals(programmingContractInfo.getId().toString())) {
					FDCMsgBox.showInfo("框架合约编码已存在，请重新输入！");
					txtNumber.requestFocus();
					SysUtil.abort();
				}
			}
		} else {
			if (FDCHelper.isEmpty(txtNumber.getText())) {
				FDCMsgBox.showInfo("框架合约编码不能为空！");
				txtNumber.requestFocus();
				SysUtil.abort();
			}
		}
		if (FDCHelper.isEmpty(txtName.getText())) {
			FDCMsgBox.showInfo("框架合约名称不能为空！");
			txtName.requestFocus();
			SysUtil.abort();
		} else {
			for (int i = 0; i < pcCollection.size(); i++) {
				ProgrammingEntryInfo rowObject = pcCollection.get(i);
				String name = rowObject.getName();
				if (!FDCHelper.isEmpty(name)) {
					if (txtName.getText().equals(name.trim()) && !pcInfo.getId().toString().equals(rowObject.getId().toString())) {
						FDCMsgBox.showInfo("框架合约名称已存在，请重新输入！");
						txtName.requestFocus();
						SysUtil.abort();
					}
				}
			}
		}
		// if (this.oprtState.equals(OprtState.ADDNEW)) {
		// isNameDup(txtName.getText(), pcInfo.getId().toString());
		// isNumberDup(txtNumber.getText(), null);
		// } else if (this.oprtState.equals(OprtState.EDIT)) {
		// isNameDup(txtName.getText(), pcInfo.getId().toString());
		// isNumberDup(txtNumber.getText(), pcInfo.getId().toString());
		// }
		// 成本构成分录必录项验空
		for (int i = 0; i < kdtCost.getRowCount(); i++) {
			IRow row = kdtCost.getRow(i);
			ICell projectCell = row.getCell(PROJECT);
			ICell costAccountCell_number = row.getCell(COSTACCOUNT_NUMBER);
			ICell costAccountCell_name = row.getCell(COSTACCOUNT);
			ICell goalCost = row.getCell(GOALCOST);
			ICell assigned = row.getCell(ASSIGNED);
			ICell assigning = row.getCell(ASSIGNING);
			ICell contractAssign = row.getCell(CONTRACTASSIGN);
			if (FDCHelper.isEmpty(projectCell.getValue())) {
				FDCMsgBox.showInfo("工程项目不能为空！");
				SysUtil.abort();
			}
			if (FDCHelper.isEmpty(costAccountCell_number.getValue())) {
				FDCMsgBox.showInfo("成本科目编码不能为空！");
				SysUtil.abort();
			}
//			if (FDCHelper.isEmpty(costAccountCell_name.getValue())) {
//				FDCMsgBox.showInfo("成本科目名称不能为空！");
//				SysUtil.abort();
//			}
			if (FDCHelper.isEmpty(goalCost.getValue())) {
				FDCMsgBox.showInfo("目标成本不能为空！");
				SysUtil.abort();
			}
//			if (FDCHelper.isEmpty(assigned.getValue())) {
//				FDCMsgBox.showInfo("已分配不能为空！");
//				SysUtil.abort();
//			}
//			if (FDCHelper.isEmpty(assigning.getValue())) {
//				FDCMsgBox.showInfo("待分配不能为空！");
//				SysUtil.abort();
//			}
//			if (FDCHelper.isEmpty(contractAssign.getValue())) {
//				FDCMsgBox.showInfo("本合约分配不能为空！");
//				SysUtil.abort();
//			}
		}
	}

	private void verifyAllData() {


		BigDecimal amount = (BigDecimal) txtAmount.getValue();// 规划金额
		BigDecimal controlAmount = (BigDecimal) txtControlAmount.getValue();// 控制金额
		if (amount != null) {
			// 规划金额不能小于0
			if (amount.compareTo(FDCHelper.ZERO) < 0) {
				FDCMsgBox.showInfo("规划金额不能小于0");
				SysUtil.abort();
			}
		}
//		if (controlAmount != null) {
//			// 控制金额不能小于0
//			if (controlAmount.compareTo(FDCHelper.ZERO) < 0) {
//				FDCMsgBox.showInfo("控制金额不能小于0");
//				SysUtil.abort();
//			}
//		}
		if (amount != null && controlAmount != null) {
			// 控制金额不能大于规划金额
//			if (controlAmount.compareTo(amount) > 0) {
//				FDCMsgBox.showInfo("控制金额不得大于规划金额");
//				SysUtil.abort();
//			}
			// 规划金额不能小于引用的合约的已发生金额
			if (amount.compareTo(getHappenedAmount()) < 0) {
				FDCMsgBox.showInfo("规划金额不能小于已发生金额");
				SysUtil.abort();
			}
		}

		int costRowCount = kdtCost.getRowCount();
		for (int i = 0; i < costRowCount; i++) {
			Object assigningObj = kdtCost.getCell(i, ASSIGNING).getValue();
			Object contractAssignObj = kdtCost.getCell(i, CONTRACTASSIGN).getValue();
			if (assigningObj != null && contractAssignObj != null) {
				BigDecimal assigning = new BigDecimal(assigningObj.toString());
				if(assigning.compareTo(FDCHelper.ZERO)<0) continue;
				
				BigDecimal contractAssign = new BigDecimal(contractAssignObj.toString());
				if (assigning.compareTo(contractAssign) < 0) {
					FDCMsgBox.showInfo("本合约分配金额不得大于待分配金额");
					SysUtil.abort();
				}
			}
		}

	}

	/**
	 * 已发生金额
	 * 
	 * @return
	 */
	private BigDecimal getHappenedAmount() {
		BigDecimal happenedAmount = FDCHelper.ZERO;
		if(pcInfo.getSrcId()!=null&&pcInfo.getId()!=null&&!pcInfo.getSrcId().equals(pcInfo.getId().toString())){
			
		}else{
			BigDecimal signUpAmount = pcInfo.getSignUpAmount();// 签约金额
			BigDecimal changeAmount = pcInfo.getChangeAmount();// 变更金额
			BigDecimal settleAmount = pcInfo.getSettleAmount();// 结算金额
			BigDecimal estimateAmount=pcInfo.getEstimateAmount();
			
			if (FDCHelper.isEmpty(signUpAmount)) {
				signUpAmount = new BigDecimal(0);
			}
			if (FDCHelper.isEmpty(changeAmount)) {
				changeAmount = new BigDecimal(0);
			}
			if (FDCHelper.isEmpty(settleAmount)) {
				settleAmount = new BigDecimal(0);
			}
			if (FDCHelper.isEmpty(settleAmount)) {
				settleAmount = new BigDecimal(0);
			}
			if (FDCHelper.isEmpty(estimateAmount)) {
				estimateAmount = new BigDecimal(0);
			}
			if(settleAmount.compareTo(FDCHelper.ZERO)>0){
				happenedAmount=settleAmount;
			}else{
				happenedAmount=signUpAmount.add(changeAmount).add(estimateAmount);
			}
		}
		return happenedAmount;
	}



	/**
	 * 成本构成分录单击事件
	 */
	protected void kdtCost_tableClicked(KDTMouseEvent e) throws Exception {
		int rowIndex = e.getRowIndex();
		// 选中行则“删除行”按钮可用
		if (e.getRowIndex() > -1 && !oprtState.equals(OprtState.VIEW) && e.getType() != KDTStyleConstants.HEAD_ROW) {
			btnRemoveLines_cost.setEnabled(true);
		}
//		if (e.getColIndex() == kdtCost.getColumnIndex(COSTACCOUNT) && e.getType() != KDTStyleConstants.HEAD_ROW) {
//			ProjectInfo project = (ProjectInfo) kdtCost.getCell(rowIndex, PROJECT).getValue();
//			if (project == null) {
//				FDCMsgBox.showInfo("请先录入工程项目");
//			} else {
//				kdtCost.getColumn(COSTACCOUNT).getStyleAttributes().setLocked(false);
//			}
//		}
	}

	/**
	 * 成本构成分录数据编辑处理
	 */
	protected void kdtCost_editStopped(KDTEditEvent e) throws Exception {
		int rowIndex = e.getRowIndex();
		int colIndex = e.getColIndex();
		Object oldValue = e.getOldValue();
		
		if(colIndex == kdtCost.getColumnIndex(INVESTYEAR))
		{
			Object projectObj = kdtCost.getCell(rowIndex, colIndex).getValue();
			if(projectObj != null)
			{
				InvestYearInfo IyInfo = (InvestYearInfo) projectObj;
//				YearInvestPlanInfo yipInfo = 
				
//				this.kdtCost.getCell(rowIndex,PROJECT).setValue(IyInfo);
				
	       
		}
	  }
		
//		// 编辑"工程项目"
//		if (colIndex == kdtCost.getColumnIndex(PROJECT)) {
//			Object projectObj = kdtCost.getCell(rowIndex, colIndex).getValue();
//			if (projectObj != null) {
//				ProjectInfo newProject = (ProjectInfo) projectObj;
//				costAccountCellF7(newProject, rowIndex, kdtCost.getColumnIndex(COSTACCOUNT),this.pcCollection);// 通过工程项目为条件重新加载成本科目F7
//				// 工程项目不变，则不做处理
//				if (oldValue != null) {
//					ProjectInfo oldProject = (ProjectInfo) oldValue;
//					if (newProject.getNumber().equals(oldProject.getNumber())) {
//						return;
//					}
//				}
//
//				// 取目标成本，为空则把当前行除工程项目外所有值置空
////				AimCostInfo aimCostInfo = null;
////				Object aimCostObj = this.getUIContext().get("aimCostInfo");
////				if (aimCostObj == null) {
////					ProgrammingEntryUtil.clearCell(kdtCost, rowIndex, COSTACCOUNT_NUMBER, COSTACCOUNT, GOALCOST, ASSIGNED, ASSIGNING,
////							CONTRACTASSIGN, COST_DES);
////				} else {
////					aimCostInfo = (AimCostInfo) aimCostObj;
////				}
//				/*
//				 * 工程项目改变之后
//				 * 
//				 * 1.
//				 * 
//				 * 2.判断当前行是否已选了成本科目
//				 * 
//				 * 2.1:若无：清空除工程项目外所有当前行单元格
//				 * 
//				 * 2.2:若有：判断所变的工程项目是否也有此成本科目
//				 * 
//				 * 2.2.1:若无：清空除工程项目外所有当前行单元格
//				 * 
//				 * 2.2.2:若有：
//				 * 
//				 * 2.2.2.1:给当前行成本科目单元格重新关联新的成本科目
//				 * 
//				 * 2.2.2.2：获取"目标成本"
//				 * 
//				 * 2.2.2.2.1：判断目标成本是否为0
//				 * 
//				 * 2.2.2.2.1.1:为0，给"目标成本","已分配","待分配","本合约分配"赋值0,备注清空
//				 * 
//				 * 2.2.2.2.1.2:不为0，算出"已分配","待分配","本合约分配"各值
//				 * 
//				 * 最后把牵涉到的值：规划金额，经济条款中"付款比例"、"付款金额"等值 更新一遍
//				 */
//				// 1
//
//				Object costAccountObj = kdtCost.getCell(rowIndex, COSTACCOUNT).getValue();
//				// 2
//				if (costAccountObj == null) {
//					// 2.1
//					ProgrammingEntryUtil.clearCell(kdtCost, rowIndex, COSTACCOUNT_NUMBER, COSTACCOUNT, GOALCOST, ASSIGNED, ASSIGNING,
//							CONTRACTASSIGN, COST_DES);
//				} 
//				else {
//					// 2.2
//					CostAccountInfo costAccount = (CostAccountInfo) costAccountObj;
//					String newCostAccountID = ProgrammingEntryUtil.isExitCostAccount(newProject, costAccount);
//					if (newCostAccountID == null) {
//						// 2.2.1
//						ProgrammingEntryUtil.clearCell(kdtCost, rowIndex, COSTACCOUNT_NUMBER, COSTACCOUNT, GOALCOST, ASSIGNED,
//								ASSIGNING, CONTRACTASSIGN, COST_DES);
//					} 
////					else {
//						// 2.2.2
//						// 2.2.2.1给当前行成本科目单元格重新关联新的成本科目
////						CostAccountInfo newCostAccountInfo = ProgrammingEntryUtil.getCostAccountByNewID(newCostAccountID);
////						kdtCost.getCell(rowIndex, COSTACCOUNT).setValue(newCostAccountInfo);
////						kdtCost.getCell(rowIndex, COSTACCOUNT_NUMBER).setValue(newCostAccountInfo.getLongNumber().replace('!', '.'));
//						// 2.2.2.2：获取"目标成本"
////						BigDecimal goalCost = ProgrammingEntryUtil.getGoalCostBy_costAcc_aimCost(newCostAccountInfo,
////								aimCostInfo);
//						// 2.2.2.2.1
////						if (goalCost.compareTo(FDCHelper.ZERO) == 0) {
////							// 2.2.2.2.1.1
////							ProgrammingEntryUtil.setZero(kdtCost, rowIndex, GOALCOST, ASSIGNED, ASSIGNING, CONTRACTASSIGN, COST_DES);
////							afterContractAssignChange();
////							afterPlanAmountChange();
////						} 
//						else {
//							// 2.2.2.2.1.2 算出"已分配","待分配","本合约分配"各值
////							BigDecimal allAssigned = FDCHelper.ZERO;// 已分配
//							// 算出"待分配" == "目标成本" - "已分配"
////							BigDecimal assigning = goalCost.subtract(allAssigned);
////							// 带出"本合约分配"="待分配"
////							BigDecimal contractAssign = assigning;
////							// 显示在单元格中
////							kdtCost.getCell(rowIndex, GOALCOST).setValue(goalCost);// 目标成本
////							kdtCost.getCell(rowIndex, ASSIGNED).setValue(allAssigned);// 已分配
////							kdtCost.getCell(rowIndex, ASSIGNING).setValue(assigning);// 待分配
////							kdtCost.getCell(rowIndex, CONTRACTASSIGN).setValue(contractAssign);// 本合约分配
////
////							// 本合约分配带出后又自动算出"规划金额"
////							afterContractAssignChange();
////							// 自动算出"规划金额"后，动态改变经济条款中"付款比例"和"付款金额"
////							// 默认以"付款比例"为定值，改变"付款金额"
////							afterPlanAmountChange();
////						}
//					}
//				}
//			} else {
//				//工程项目变为空则清空所在行所有行
//				ProgrammingEntryUtil.clearCell(kdtCost, rowIndex, PROJECT, COSTACCOUNT_NUMBER, COSTACCOUNT, GOALCOST, ASSIGNED,
//						ASSIGNING, CONTRACTASSIGN, COST_DES);
//				kdtCost.getColumn(COSTACCOUNT).getStyleAttributes().setLocked(true);
//			}
//
//		}
//		// 编辑"本合约分配"
//		if (colIndex == kdtCost.getColumnIndex(CONTRACTASSIGN)) {
//			afterContractAssignChange();
//			
////			ObjectValueRender render_scale = new ObjectValueRender();
////			render_scale.setFormat(new IDataFormat() {
////				public String format(Object o) {
////					String str = o.toString();
////					if (!FDCHelper.isEmpty(str)) {
////						return str + "%";
////					}
////					return str;
////				}
////			});
////			kdtCost.getColumn("scale").setRenderer(render_scale);
//			BigDecimal amount=(BigDecimal) kdtCost.getRow(e.getRowIndex()).getCell(CONTRACTASSIGN).getValue();
//			BigDecimal goalCost=(BigDecimal) kdtCost.getRow(e.getRowIndex()).getCell(GOALCOST).getValue();
//			if(amount!=null&&goalCost!=null&&goalCost.compareTo(FDCHelper.ZERO)!=0){
//				amount=goalCost==null?FDCHelper.ZERO:amount.multiply(new BigDecimal(100)).divide(goalCost,2,BigDecimal.ROUND_HALF_UP);
//				kdtCost.getRow(e.getRowIndex()).getCell("scale").setValue(amount);
//			}else{
//				kdtCost.getRow(e.getRowIndex()).getCell("scale").setValue(FDCHelper.ZERO);
//			}
//		}
//
//		// 选择"成本科目F7"
////		if (colIndex == kdtCost.getColumnIndex(COSTACCOUNT)) {
////			/*
////			 * 1.取出成本科目的值
////			 * 
////			 * 1.1判断成本科目是否为空
////			 * 
////			 * 1.1.1为空：置空当前行除工程项目外的所有单元格
////			 * 
////			 * 1.1.2不为空：取值
////			 * 
////			 * 1.2判断所选的成本科目是否重复，重复直接返回,不重复继续
////			 * 
////			 * 2.取出目标成本信息
////			 * 
////			 * 2.1若为空，把"目标成本","已分配","待分配","本合约分配","备注"置空；
////			 * 
////			 * 2.2若不为空： 判断目标成本是否是已审批之后状态
////			 * 
////			 * 2.2.1若不为审批之后状态，把各单元格数值置0，备注项置空 ；
////			 * 
////			 * 2.2.2若为审批之后状态，取出相应成本科目的目标成本值（需要用到成本科目，目标成本作为条件）
////			 * 
////			 * 3.算出"已分配","待分配","本合约分配"各值
////			 * 
////			 * 最后把牵涉到的值：规划金额，经济条款中"付款比例"、"付款金额"等值 更新一遍
////			 */
////			BigDecimal allAssigned = FDCHelper.ZERO;// "已分配"
////			ProjectInfo project = (ProjectInfo) kdtCost.getCell(rowIndex, PROJECT).getValue();// 工程项目
////			// 1.
////			Object newValue = kdtCost.getCell(rowIndex, COSTACCOUNT).getValue();
////			// 1.1
////			if(newValue == null){
////				// 1.1.1
////				ProgrammingEntryUtil.clearCell(kdtCost, rowIndex, COSTACCOUNT_NUMBER, COSTACCOUNT, GOALCOST, ASSIGNED, ASSIGNING,
////						CONTRACTASSIGN, COST_DES);
////			}
////			else{
////				// 1.1.2
////				CostAccountInfo newCostAccountInfo = (CostAccountInfo) newValue;// 成本科目
////				kdtCost.getCell(rowIndex, COSTACCOUNT_NUMBER).setValue(newCostAccountInfo.getLongNumber().replace('!', '.'));
////				// 1.2
////				if (isCostAccountDup(newCostAccountInfo, project, rowIndex)) {
////					return;
////				}
//				// 2.
////				AimCostInfo aimCostInfo = (AimCostInfo) this.getUIContext().get("aimCostInfo");// 目标成本
////				if (aimCostInfo == null) {
////					// 2.1
//////					ProgrammingEntryUtil.clearCell(kdtCost, rowIndex, GOALCOST, ASSIGNED, ASSIGNING, CONTRACTASSIGN, COST_DES);
////					ProgrammingEntryUtil.setZero(kdtCost, rowIndex, GOALCOST, ASSIGNED, ASSIGNING, CONTRACTASSIGN, COST_DES);
////				} else {
////					// 2.2是否是已审批
////					if (!isAimCostAudit(aimCostInfo)) {
////						// 2.2.1
////						ProgrammingEntryUtil.setZero(kdtCost, rowIndex, GOALCOST, ASSIGNED, ASSIGNING, CONTRACTASSIGN, COST_DES);
////					} else {
////						// 2.2.2 取出目标成本的值
////						// ProjectInfo project = (ProjectInfo) kdtCost.getCell(rowIndex, PROJECT).getValue();
////						if (project != null) {
////							BigDecimal goalCost = ProgrammingEntryUtil.getGoalCostBy_costAcc_aimCost(newCostAccountInfo,
////									aimCostInfo);
////							if (goalCost.compareTo(FDCHelper.ZERO) == 0) {
////								ProgrammingEntryUtil.setZero(kdtCost, rowIndex, GOALCOST, ASSIGNED, ASSIGNING, CONTRACTASSIGN, COST_DES);
////								afterContractAssignChange();
////								afterPlanAmountChange();
////							}
////				else {
////								allAssigned = getAllContractAssign(newCostAccountInfo, false);// 已分配
////								// 算出"待分配" == "目标成本" - "已分配"
////								BigDecimal assigning = goalCost.subtract(allAssigned);// 待分配
////								// 带出"本合约分配"="待分配"
////								BigDecimal contractAssign = assigning;// 本合约分配
////								// 显示在单元格中
////								kdtCost.getCell(rowIndex, GOALCOST).setValue(goalCost);// 目标成本
////								kdtCost.getCell(rowIndex, ASSIGNED).setValue(allAssigned);// 已分配
////								kdtCost.getCell(rowIndex, ASSIGNING).setValue(assigning);// 待分配
////								kdtCost.getCell(rowIndex, CONTRACTASSIGN).setValue(contractAssign);// 本合约分配
////
////								// 本合约分配带出后又自动算出"规划金额"
////								afterContractAssignChange();
////								// 自动算出"规划金额"后，动态改变经济条款中"付款比例"和"付款金额"
////								// 默认以"付款比例"为定值，改变"付款金额"
////								afterPlanAmountChange();
////							}
////						}
////					}
////				}
////			}
////		}
//		if(colIndex == kdtCost.getColumnIndex("scale")){
//			// 绘制付款比例显示 郊果
//			ObjectValueRender render_scale = new ObjectValueRender();
//			render_scale.setFormat(new IDataFormat() {
//				public String format(Object o) {
//					String str = o.toString();
//					if (!FDCHelper.isEmpty(str)) {
//						return str + "%";
//					}
//					return str;
//				}
//			});
//			kdtCost.getColumn("scale").setRenderer(render_scale);
//			BigDecimal amount=(BigDecimal) kdtCost.getRow(e.getRowIndex()).getCell("scale").getValue();
//			BigDecimal goalCost=(BigDecimal) kdtCost.getRow(e.getRowIndex()).getCell(GOALCOST).getValue();
//			if(amount!=null){
//				amount=goalCost==null?FDCHelper.ZERO:goalCost.multiply(amount).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
//				kdtCost.getRow(e.getRowIndex()).getCell(CONTRACTASSIGN).setValue(amount);
//			}else{
//				kdtCost.getRow(e.getRowIndex()).getCell(CONTRACTASSIGN).setValue(FDCHelper.ZERO);
//			}
//			afterContractAssignChange();
//		}
	}

	/**
	 * "本合约分配"值改变后，汇总规划金额
	 */
	private void afterContractAssignChange() {
		BigDecimal allContractAssign = FDCHelper.ZERO;
		for (int i = 0; i < kdtCost.getRowCount(); i++) {
			Object contractAssignObj = kdtCost.getCell(i, CONTRACTASSIGN).getValue();// 本合约分配
			if (contractAssignObj == null) {
				allContractAssign = allContractAssign.add(FDCHelper.ZERO);
			} else {
				allContractAssign = allContractAssign.add(new BigDecimal(contractAssignObj.toString()));
			}
		}
		txtAmount.setValue(allContractAssign);
		txtControlAmount.setValue(allContractAssign);
	}

	/**
	 * "规划金额"值改变后，动态改变经济条款中付款金额的值
	 */
	private void afterPlanAmountChange() {
		BigDecimal planAmount = (BigDecimal) txtAmount.getValue();
	}

	/**
	 * 判断在一个合约之内相同工程项目下成本科目是否重复
	 * 
	 * @param rowIndex
	 */
	private boolean isCostAccountDup(CostAccountInfo currentInfo, ProjectInfo project, int rowIndex) {
		ICell costAccountNameCell = kdtCost.getCell(rowIndex, COSTACCOUNT);
		ICell costAccountNumberCell = kdtCost.getCell(rowIndex, COSTACCOUNT_NUMBER);
		if (FDCHelper.isEmpty(currentInfo)) {
			return false;
		}
		int rowCount = kdtCost.getRowCount();
		if (!FDCHelper.isEmpty(currentInfo.getLongNumber())) {
			int flag = 0;
			for (int i = 0; i < rowCount; i++) {
				CostAccountInfo forInfo = (CostAccountInfo) kdtCost.getCell(i, COSTACCOUNT).getValue();
				ProjectInfo forProjectInfo = (ProjectInfo) kdtCost.getCell(i, PROJECT).getValue();
				if (forInfo == null) {
					break;
				}
				if (!FDCHelper.isEmpty(forInfo.getLongNumber())) {
					if (currentInfo.getLongNumber().equals(forInfo.getLongNumber()) && project.getNumber().equals(forProjectInfo.getNumber())) {
						flag++;
						if (flag >= 2) {
							FDCMsgBox.showInfo("本框架合约成本构成内已经有\"" + currentInfo.getName() + "\"成本科目，不能再继续添加此成本科目了！");
							costAccountNameCell.setValue(null);
							costAccountNumberCell.setValue(null);
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * 算出框架内相同成本科目的"本合约分配"金额之和
	 * 
	 * @param caInfo
	 * 
	 * @param flag
	 * 
	 *            true表示求出所有成本构成中"本合约分配"之和
	 * 
	 *            false 表示求出除本合约之外所有成本构成中"本合约分配"之和
	 * @return
	 */
	private BigDecimal getAllContractAssign(CostAccountInfo caInfo, boolean flag) {
		BigDecimal allContractAssign = FDCHelper.ZERO;
		for (int i = 0; i < pcCollection.size(); i++) {
			ProgrammingEntryInfo programmingContractInfo = pcCollection.get(i);
			if (flag) {
				ProgrammingEntryCostEntryCollection costEntries = programmingContractInfo.getCostEntries();
				for (int j = 0; j < costEntries.size(); j++) {
					ProgrammingEntryCostEntryInfo pccInfo = costEntries.get(j);
					CostAccountInfo costAccountInfo = pccInfo.getCostAccount();
					if (costAccountInfo != null) {
						if(costAccountInfo.getLongNumber()!=null){
							if (costAccountInfo.getLongNumber().equals(caInfo.getLongNumber())) {
								BigDecimal contractAssign = pccInfo.getContractAssign();
								if (contractAssign == null) {
									contractAssign = FDCHelper.ZERO;
								}
								allContractAssign = allContractAssign.add(contractAssign);
							}
						}
					}
				}
			} else {
				if (!programmingContractInfo.getId().toString().equals(pcInfo.getId().toString())) {
					ProgrammingEntryCostEntryCollection costEntries = programmingContractInfo.getCostEntries();
					for (int j = 0; j < costEntries.size(); j++) {
						ProgrammingEntryCostEntryInfo pccInfo = costEntries.get(j);
						CostAccountInfo costAccountInfo = pccInfo.getCostAccount();
						if (costAccountInfo != null) {
							if(costAccountInfo.getLongNumber()!=null){
								if (costAccountInfo.getLongNumber().equals(caInfo.getLongNumber())) {
									BigDecimal contractAssign = pccInfo.getContractAssign();
									if (contractAssign == null) {
										contractAssign = FDCHelper.ZERO;
									}
									allContractAssign = allContractAssign.add(contractAssign);
								}
							}
						}
					}
				}
			}
		}
		return allContractAssign;
	}

	/**
	 * 经济条款分录单击事件
	 */
	protected void kdtEconomy_tableClicked(KDTMouseEvent e) throws Exception {
	}

	/**
	 * 经济条款分录数据编辑处理
	 */
	protected void kdtEconomy_editStopped(KDTEditEvent e) throws Exception {

	}

	/**
	 * 判断单元格的值在编辑前后是否改变
	 * 
	 * @param oldValue
	 * @param newValue
	 */
	private void editStopedCheckIsChange(Object oldValue, Object newValue) {
		if (newValue != null && oldValue != null) {
			if (new BigDecimal(newValue.toString()).compareTo(new BigDecimal(oldValue.toString())) == 0) {
				SysUtil.abort();
			}
		}
	}

	private void preparePCData() {
		// 单据头信息
		if (pcInfo.getParent() != null) {
			String longName = pcInfo.getDisplayName();
			if(longName!=null){
				String headName = longName.substring(0, longName.lastIndexOf('.'));
				this.txtParentLongName.setText(headName);// 上级框架合约长名称
			}
		}
		txtNumber.setText(pcInfo.getLongNumber());// 框架合约长编码
		txtName.setText(pcInfo.getName() == null ? null : pcInfo.getName().trim());// 名称
		txtAmount.setValue(pcInfo.getAmount());// 规划金额
		txtControlAmount.setValue(pcInfo.getControlAmount());// 控制金额
		kdcInviteWay.setSelectedItem(pcInfo.getInviteWay());// 招标方式
		txtEstimateAmount.setValue(pcInfo.getEstimateAmount());// 预估金额
		if(this.oprtState.equals(OprtState.VIEW)){
			if(pcInfo.getInviteOrg()!=null){
				CostCenterOrgUnitInfo orgUnitInfo = null;
				try {
					orgUnitInfo = CostCenterOrgUnitFactory.getRemoteInstance().getCostCenterOrgUnitInfo(new ObjectUuidPK(pcInfo.getInviteOrg().getId()));
				} catch (EASBizException e) {
					e.printStackTrace();
				} catch (BOSException e) {
					e.printStackTrace();
				}
				prmtInviteOrg.setData(orgUnitInfo);
			}
		}else{
			prmtInviteOrg.setData(pcInfo.getInviteOrg());// 招标组织
		}
		txtWorkContent.setText(pcInfo.getWorkContent());// 工作内容
		txtSupMaterial.setText(pcInfo.getSupMaterial());// 甲供及甲指材设		
		txtDescription.setText(pcInfo.getDescription());// 备注
		txtDescription.setToolTipText(pcInfo.getDescription());
		txtAttachment.setText(getAllAttachmentName(pcInfo.getId().toString()).toString());// 附件

		ObjectValueRender render_scale = new ObjectValueRender();
		render_scale.setFormat(new IDataFormat() {
			public String format(Object o) {
				String str = o.toString();
				if (!FDCHelper.isEmpty(str)) {
					return str + "%";
				}
				return str;
			}
		});

		// 分录信息
		addCostLine(kdtCost);

		int level = pcInfo.getLevel();
		if (level > 1) {
			String longNumber = pcInfo.getLongNumber();
			if (!FDCHelper.isEmpty(longNumber)) {
				LimitedTextDocument document = new LimitedTextDocument(longNumber);
				String text = txtNumber.getText();
				txtNumber.setDocument(document);
				document.setIsAutoUpdate(true);
				document.setIsOnload(true);
				txtNumber.setText(text);
				document.setIsAutoUpdate(false);
				document.setIsOnload(false);
			}
		}
		if (level == 1) {
			LimitedTextDocument document = new LimitedTextDocument("");
			String text = txtNumber.getText();
			txtNumber.setDocument(document);
			document.setIsAutoUpdate(true);
			document.setIsOnload(true);
			txtNumber.setText(text);
			document.setIsAutoUpdate(false);
			document.setIsOnload(false);
		}
	}

	/**
	 * 初始化框架合约成本构成分录数据
	 * 
	 * @param table
	 */
	private void addCostLine(KDTable table) {
		projectF7();
		
		if (pcInfo.getCostEntries().size() > 0) {
			sortCollection(pcInfo.getCostEntries(), "costAccount.longNumber", true);
			IRow row;
			for (int i = 0; i < pcInfo.getCostEntries().size(); i++) {
				ProgrammingEntryCostEntryInfo pccInfo = pcInfo.getCostEntries().get(i);
				row = table.addRow();
				if (pccInfo.getId() == null) {
					pccInfo.setId(BOSUuid.create(pccInfo.getBOSType()));
				}
				row.getCell(COST_ID).setValue(pccInfo.getId());
				ProjectInfo project = null;
				if (pccInfo.getCostAccount() != null) {
					project = pccInfo.getCostAccount().getCurProject();
				}
				row.getCell(PROJECT).setValue(project);
				costAccountCellF7(project, i, kdtCost.getColumnIndex(COSTACCOUNT),this.pcCollection);// 根据当前行工程项目加载F7成本科目
//				row.getCell(COSTACCOUNT_NUMBER).setValue(pccInfo.getCostAccount().getLongNumber().replace('!', '.'));
				row.getCell(COSTACCOUNT).setValue(pccInfo.getCostAccount());
				row.getCell(GOALCOST).setValue(pccInfo.getGoalCost());
				row.getCell(ASSIGNED).setValue(pccInfo.getAssigned());
				row.getCell(ASSIGNING).setValue(pccInfo.getAssigning());
				row.getCell(CONTRACTASSIGN).setValue(pccInfo.getContractAssign());
				row.getCell(COST_DES).setValue(pccInfo.getDescription());
			}
		}
	}
	
	/**
	 * 对集合按某字段进行排序,该字段的值需要是Comparable类型的.
	 * @param cols 要排序的集合
	 * @param sortColName 要排序的字段
	 * @param sortType 是否正序
	 * */
	public  void sortCollection(IObjectCollection cols, final String sortColName, final boolean sortType) {
		Object[] toSortData = cols.toArray();
		
		Arrays.sort(toSortData, new Comparator(){
			public int compare(Object arg0, Object arg1) {
				IObjectValue obj0 = (IObjectValue)arg0;
				IObjectValue obj1 = (IObjectValue)arg1;
				if(obj0 == null  ||  obj1 == null){
					return 0;
				}
				
				Comparable tmp0 = (Comparable)getValue(obj0,sortColName);
				Comparable tmp1 = (Comparable)getValue(obj1,sortColName);
				if(tmp0 == null  ||  tmp1 == null){
					return 0;
				}
				
				return sortType ? tmp0.compareTo(tmp1) : -tmp0.compareTo(tmp1);
			}
		});
		
		cols.clear();
		for(int j=0; j<toSortData.length; j++){
			cols.addObject((IObjectValue) toSortData[j]);
		}
	}
	
	public  Object getValue(IObjectValue value, String key){
		int in = key.indexOf(".");
		if(in == -1){
			return value.get(key);
		}else{
			Object tmp = value.get(key.substring(0, in));
			if(tmp != null  &&  tmp instanceof IObjectValue){
				return getValue((IObjectValue) tmp, key.substring(in + 1, key.length()));
			}
		}
		return null;
	}

	/**
	 * 通过框架ID获取所关联的经济条款
	 * 
	 * @param pteID
	 * @return
	 */
	private ProgrammingEntryEconomyEntryCollection getPCEconomy(String pcID) {
		ProgrammingEntryEconomyEntryInfo pcEnonomyInfo = null;
		PaymentTypeInfo paymentInfo = null;
		ProgrammingEntryEconomyEntryCollection pceCollection = new ProgrammingEntryEconomyEntryCollection();
		FDCSQLBuilder fdcBuilder = new FDCSQLBuilder();
		fdcBuilder.appendSql(" select FID,FPAYMENTTYPEID  from CT_INV_ProgrammingEEE ");
		fdcBuilder.appendSql(" where FContractID = '" + pcID + "'");
		IRowSet rs = null;
		try {
			rs = fdcBuilder.executeQuery();
			for (int i = 0; i < rs.size(); i++) {
				if (rs.next()) {
					pcEnonomyInfo = ProgrammingEntryEconomyEntryFactory.getRemoteInstance().getProgrammingEntryEconomyEntryInfo(
							new ObjectUuidPK(rs.getString("FID")));
					paymentInfo = PaymentTypeFactory.getRemoteInstance().getPaymentTypeInfo(
							new ObjectUuidPK(rs.getString("FPAYMENTTYPEID")));
					pcEnonomyInfo.setPaymentType(paymentInfo);
					pceCollection.add(pcEnonomyInfo);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return pceCollection;
	}

	/**
	 * 框架合约名称是否已存在
	 * 
	 * @param name
	 */
	private void isNameDup(String name, String id) {
		FDCSQLBuilder fdcBuilder = new FDCSQLBuilder();
		fdcBuilder.appendSql(" select * from CT_INV_ProgrammingEntry where FName_l2 = '" + name + "' ");
		fdcBuilder.appendSql(" and FID <> '" + id + "' ");
		try {
			IRowSet iRowSet = fdcBuilder.executeQuery();
			if (iRowSet.next()) {
				FDCMsgBox.showInfo("框架合约名称已存在，请重新输入");
				txtName.requestFocus();
				SysUtil.abort();
			}
		} catch (BOSException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 框架合约编码是否已存在
	 * 
	 * @param number
	 */
	private void isNumberDup(String longNumber, String id) {
		FDCSQLBuilder fdcBuilder = new FDCSQLBuilder();
		fdcBuilder.appendSql(" select * from CT_INV_ProgrammingEntry where FLongNumber = '" + longNumber + "' ");
		fdcBuilder.appendSql(" and FID <> '" + id + "' ");
		try {
			IRowSet iRowSet = fdcBuilder.executeQuery();
			if (iRowSet.next()) {
				FDCMsgBox.showInfo("框架合约编码已存在，请重新输入");
				txtNumber.requestFocus();
				SysUtil.abort();
			}
		} catch (BOSException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	// 在新增状态下，要进行附件的新增，故保留临时附件的id
	private String attachMentTempID = null;

	/**
	 * 附件管理
	 */
	public void actionAttachment_actionPerformed(ActionEvent e) throws Exception {
		boolean isEdit = false;// 默认为查看状态
		if (OprtState.EDIT.equals(getOprtState()) || OprtState.ADDNEW.equals(getOprtState())) {
			isEdit = true;
		}
		AttachmentClientManager acm = AttachmentManagerFactory.getClientManager();
		AttachmentUIContextInfo info = getAttacheInfo();
		if (info == null) {
			info = new AttachmentUIContextInfo();
		}
		if (FDCHelper.isEmpty(info.getBoID())) {
			String boID = getSelectBOID();
			if (boID == null) {
				if (!isEdit) {
					if (attachMentTempID == null) {
						boID = acm.getAttID().toString();
						attachMentTempID = boID;
					} else {
						boID = attachMentTempID;
					}
				} else {
					return;
				}
			}
			info.setBoID(boID);
			acm.showAttachmentListUIByBoID(boID, this, isEdit);
		}
		info.setEdit(isEdit);
		if (getSelectBOID() != null) {
			StringBuffer allAttachmentName = getAllAttachmentName(getSelectBOID());
			if (!FDCHelper.isEmpty(allAttachmentName.toString())) {
				pcInfo.setAttachment(allAttachmentName.toString());
				this.txtAttachment.setText(allAttachmentName.toString());
				this.txtAttachment.setToolTipText(allAttachmentName.toString());
			} else {
				pcInfo.setAttachment(null);
				this.txtAttachment.setText(null);
			}
		}
	}

	protected AttachmentUIContextInfo getAttacheInfo() {
		return null;
	}

	protected final String getSelectBOID() {
		if (pcInfo == null)
			return null;
		String boID = pcInfo.getId() != null ? pcInfo.getId().toString() : null;
		return boID;
	}

	/**
	 * 获取合约框架所有附件名称字符串，名称与乐称以";"相隔
	 * 
	 * @param boID
	 * @return
	 */
	private StringBuffer getAllAttachmentName(String boID) {
		FDCSQLBuilder fdcBuilder = new FDCSQLBuilder();
		fdcBuilder.appendSql(" select * from T_BAS_Attachment at");
		fdcBuilder.appendSql(" join T_BAS_BoAttchAsso boAt on at.FID=boAt.FAttachmentID");
		fdcBuilder.appendSql(" where boAt.FBoID = '" + boID + "'");
		System.out.println("sql:" + fdcBuilder.getSql().toString());
		StringBuffer sb = new StringBuffer();
		IRowSet rs = null;
		try {
			rs = fdcBuilder.executeQuery();
			while (rs.next()) {
				if (rs.isLast()) {
					sb.append(rs.getString("FName_l2") + "." + rs.getString("FSimpleName"));
				} else {
					sb.append(rs.getString("FName_l2") + "." + rs.getString("FSimpleName") + ",");
				}
			}
		} catch (BOSException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return sb;
	}

	/**
	 * 投资年度F7
	 * 
	 */
	private void projectF7() {
		KDBizPromptBox kdtEconomyEntriese_costAccount_PromptBox = new KDBizPromptBox();
		kdtEconomyEntriese_costAccount_PromptBox.setQueryInfo("com.kingdee.eas.port.pm.base.app.InvestYearQuery");
		kdtEconomyEntriese_costAccount_PromptBox.setVisible(true);
		kdtEconomyEntriese_costAccount_PromptBox.setEditable(true);
		kdtEconomyEntriese_costAccount_PromptBox.setDisplayFormat("$number$");
		kdtEconomyEntriese_costAccount_PromptBox.setEditFormat("$number$");
		kdtEconomyEntriese_costAccount_PromptBox.setCommitFormat("$number$");
		KDTDefaultCellEditor kdtEconomyEntriese_costAccount_CellEditor = new KDTDefaultCellEditor(kdtEconomyEntriese_costAccount_PromptBox);
		this.kdtCost.getColumn(INVESTYEAR).setEditor(kdtEconomyEntriese_costAccount_CellEditor);
		ObjectValueRender kdtCostEntries_paymentType_OVR = new ObjectValueRender();
		kdtCostEntries_paymentType_OVR.setFormat(new BizDataFormat("$name$"));
		this.kdtCost.getColumn(INVESTYEAR).setRenderer(kdtCostEntries_paymentType_OVR);
		
	}

	/**
	 * 成本科目F7 根椐工程项目过滤
	 */
	private void costAccountF7(ProjectInfo project,ProgrammingEntryCollection pcCol) {
//		CostAccountPromptBox selector = new CostAccountPromptBox(this,pcCol,(AimCostInfo) this.getUIContext().get("aimCostInfo"));
//		KDBizPromptBox prmtCostAccount = new KDBizPromptBox() {
//			protected String valueToString(Object o) {
//				String str = null;
//				if (o != null && o instanceof CostAccountInfo) {
//					str = ((CostAccountInfo) o).getLongNumber().replace('!', '.');
//				}
//				return str;
//			}
//		};
//		prmtCostAccount.setSelector(selector);
//		prmtCostAccount.setEnabledMultiSelection(false);
//		prmtCostAccount.setDisplayFormat("$longNumber$");
//		prmtCostAccount.setEditFormat("$longNumber$");
//		prmtCostAccount.setCommitFormat("$longNumber$");
//
//		KDTDefaultCellEditor caEditor = new KDTDefaultCellEditor(prmtCostAccount);
//		EntityViewInfo entityView = new EntityViewInfo();
//		FilterInfo filter = new FilterInfo();
//		// filter.getFilterItems().add(new FilterItemInfo("fullOrgUnit.id",OrgConstants.DEF_CU_ID,CompareType.EQUALS));
//		filter.getFilterItems().add(new FilterItemInfo("curProject.id", project.getId().toString(), CompareType.EQUALS));
//		entityView.setFilter(filter);
//		prmtCostAccount.setEntityViewInfo(entityView);
//		kdtCost.getColumn(COSTACCOUNT).setEditor(caEditor);
//		ObjectValueRender kdtCostEntries_costAccount_OVR = new ObjectValueRender();
//		kdtCostEntries_costAccount_OVR.setFormat(new BizDataFormat("$name$"));
//		this.kdtCost.getColumn(COSTACCOUNT).setRenderer(kdtCostEntries_costAccount_OVR);
	}

	/**
	 * 成本科目F7工程项目过滤
	 */
	private void costAccountCellF7(ProjectInfo project,int rowIndex,int colIndex,ProgrammingEntryCollection pcCol) {
//		CostAccountPromptBox selector = new CostAccountPromptBox(this,pcCol,(AimCostInfo) this.getUIContext().get("aimCostInfo"));
//		KDBizPromptBox prmtCostAccount = new KDBizPromptBox() {
//			protected String valueToString(Object o) {
//				String str = null;
//				if (o != null && o instanceof CostAccountInfo) {
//					str = ((CostAccountInfo) o).getLongNumber().replace('!', '.');
//				}
//				return str;
//			}
//		};
//		prmtCostAccount.setSelector(selector);
//		prmtCostAccount.setEnabledMultiSelection(false);
//		prmtCostAccount.setDisplayFormat("$longNumber$");
//		prmtCostAccount.setEditFormat("$longNumber$");
//		prmtCostAccount.setCommitFormat("$longNumber$");
//
//		KDTDefaultCellEditor caEditor = new KDTDefaultCellEditor(prmtCostAccount);
//		EntityViewInfo entityView = new EntityViewInfo();
//		FilterInfo filter = new FilterInfo();
//		if (project != null) {
//			filter.getFilterItems().add(new FilterItemInfo("curProject.id", project.getId().toString(), CompareType.EQUALS));
//		} else {
//			filter.getFilterItems().add(new FilterItemInfo("curProject.id", "error", CompareType.EQUALS));
//		}
//		entityView.setFilter(filter);
//		prmtCostAccount.setEntityViewInfo(entityView);
//		caEditor.setValue(prmtCostAccount);
//		kdtCost.getCell(rowIndex, colIndex).setEditor(caEditor);
//		ObjectValueRender kdtCostEntries_costAccount_OVR = new ObjectValueRender();
//		kdtCostEntries_costAccount_OVR.setFormat(new BizDataFormat("$name$"));
//		kdtCost.getCell(rowIndex, colIndex).setRenderer(kdtCostEntries_costAccount_OVR);
	}


	public void actionEdit_actionPerformed(ActionEvent e) throws Exception {
		if (MsgBox.showConfirm2("是否确定修改？") == MsgBox.CANCEL) {
			return;
		}
//		ProgrammingEntryFactory.getRemoteInstance().submit(pcInfo);
	}
	
	
}