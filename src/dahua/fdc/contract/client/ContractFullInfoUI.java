/**
 * output package name
 */
package com.kingdee.eas.fdc.contract.client;

import java.awt.Component;
import java.sql.SQLException;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.lang.BooleanUtils;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.ctrl.swing.KDScrollPane;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.metadata.data.SortType;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.entity.SorterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.ui.face.IUIObject;
import com.kingdee.bos.ui.face.IUIWindow;
import com.kingdee.bos.ui.face.UIException;
import com.kingdee.bos.ui.face.UIFactory;
import com.kingdee.eas.base.uiframe.client.UIFactoryHelper;
import com.kingdee.eas.common.client.OprtState;
import com.kingdee.eas.common.client.UIContext;
import com.kingdee.eas.common.client.UIFactoryName;
import com.kingdee.eas.fdc.basedata.FDCBillStateEnum;
import com.kingdee.eas.fdc.basedata.FDCSQLBuilder;
import com.kingdee.eas.fdc.contract.ConChangeSplitCollection;
import com.kingdee.eas.fdc.contract.ConChangeSplitFactory;
import com.kingdee.eas.fdc.contract.ConNoCostSplitCollection;
import com.kingdee.eas.fdc.contract.ConNoCostSplitFactory;
import com.kingdee.eas.fdc.contract.ConNoCostSplitInfo;
import com.kingdee.eas.fdc.contract.ContractBillFactory;
import com.kingdee.eas.fdc.contract.ContractCostSplitCollection;
import com.kingdee.eas.fdc.contract.ContractCostSplitFactory;
import com.kingdee.eas.fdc.contract.ContractCostSplitInfo;
import com.kingdee.eas.fdc.contract.ContractSettlementBillCollection;
import com.kingdee.eas.fdc.contract.ContractSettlementBillFactory;
import com.kingdee.eas.fdc.contract.ContractSettlementBillInfo;
import com.kingdee.eas.fdc.contract.SettlementCostSplitCollection;
import com.kingdee.eas.fdc.contract.SettlementCostSplitFactory;
import com.kingdee.eas.fdc.finance.client.DeductBillInfoUI;
import com.kingdee.eas.fdc.finance.client.PaymentBillEditUI;
import com.kingdee.eas.framework.ICoreBase;
import com.kingdee.eas.util.client.EASResource;
import com.kingdee.jdbc.rowset.IRowSet;

/**
 * output class name
 */
public class ContractFullInfoUI extends AbstractContractFullInfoUI {
	public static final String resourcePath = "com.kingdee.eas.fdc.contract.client.ContractFullResource";
	private boolean isFirstLoad=true;
	/**
	 * output class constructor
	 */
	public ContractFullInfoUI() throws Exception {
		super();
	}

	private String getNoSplitId(String contractId) throws BOSException {
		ConNoCostSplitCollection contractCostSplitCollection = ConNoCostSplitFactory
				.getRemoteInstance().getConNoCostSplitCollection(
						"select id where contractBill='" + contractId + "'");
		if (contractCostSplitCollection.size() > 0) {
			ConNoCostSplitInfo info = contractCostSplitCollection.get(0);
			return info.getId().toString();
		}
		return null;
	}

	private String getSplitId(String contractId) throws BOSException {
		EntityViewInfo view=new EntityViewInfo();
		view.setFilter(new FilterInfo());
		view.getFilter().appendFilterItem("contractBill.id", contractId);
		view.getFilter().getFilterItems().add(new FilterItemInfo("state", FDCBillStateEnum.INVALID_VALUE,CompareType.NOTEQUALS));
		ContractCostSplitCollection contractCostSplitCollection = ContractCostSplitFactory
				.getRemoteInstance().getContractCostSplitCollection(view);
		if (contractCostSplitCollection.size() > 0) {
			ContractCostSplitInfo info = contractCostSplitCollection.get(0);
			return info.getId().toString();
		}
		return null;
	}
	
	private String getConChangeSplitId(String contractId)throws BOSException {
		EntityViewInfo view=new EntityViewInfo();
		view.setFilter(new FilterInfo());
		view.getFilter().appendFilterItem("contractBill.id", contractId);
		view.getFilter().getFilterItems().add(new FilterItemInfo("state", FDCBillStateEnum.INVALID_VALUE,CompareType.NOTEQUALS));
		ConChangeSplitCollection  concoll = ConChangeSplitFactory.getRemoteInstance().getConChangeSplitCollection(view);
		if(concoll.size() > 0)
			return concoll.get(0).getId().toString();
		return null;
	}
	
	private String getSettlementCostSplitId(String contractId)throws BOSException {
		EntityViewInfo view=new EntityViewInfo();
		view.setFilter(new FilterInfo());
		view.getFilter().appendFilterItem("contractBill.id", contractId);
		view.getFilter().getFilterItems().add(new FilterItemInfo("state", FDCBillStateEnum.INVALID_VALUE,CompareType.NOTEQUALS));
		SettlementCostSplitCollection sccoll = SettlementCostSplitFactory.getRemoteInstance().getSettlementCostSplitCollection(view);
		if(sccoll.size() > 0)
			return sccoll.get(0).getId().toString();
		return null;
	}

	private String getSettleId(String contractId) throws BOSException {
		EntityViewInfo view=new EntityViewInfo();
		view.setFilter(new FilterInfo());
		view.getFilter().appendFilterItem("contractBill.id", contractId);
		view.getFilter().getFilterItems().add(new FilterItemInfo("state", FDCBillStateEnum.INVALID_VALUE,CompareType.NOTEQUALS));
		//支持多次结算，取最后一个
		SorterItemInfo sorter = new SorterItemInfo("createTime");
		sorter.setSortType(SortType.DESCEND);
		view.getSorter().add(sorter);
		ContractSettlementBillCollection settles = ContractSettlementBillFactory
				.getRemoteInstance().getContractSettlementBillCollection(view);
		if (settles.size() > 0) {
			ContractSettlementBillInfo info = settles.get(0);
			return info.getId().toString();
		}
		return null;
	}

	public void loadFields() {
		super.loadFields();
	}
	
	private void addPanel(String id, String uiClass, String title)
			throws UIException {
		if (id == null) {
			return;
		}
		if(isFirstLoad){
			KDScrollPane scrollPane=new KDScrollPane();
			this.plContrastFullInfo.add(scrollPane, title);
			int index = plContrastFullInfo.indexOfTab(title);
			if(index==0){
				//打开界面时加载第一个页签
				UIContext uiContext = new UIContext(ui);
				uiContext.put(UIContext.ID, id);
				CoreUIObject plUI = (CoreUIObject) UIFactoryHelper.initUIObject(uiClass, uiContext,
						null, "VIEW");
				scrollPane.setViewportView(plUI);
				scrollPane.setKeyBoardControl(true);
			}
			scrollPane.setUserObject(new String[]{id,uiClass});
		}
	}
	
	private void lazyLoadPanel()throws UIException {
		if(isFirstLoad) return;
		int index = plContrastFullInfo.getSelectedIndex();
		if(index==0){
			return;
		}
		KDScrollPane scrollPane=(KDScrollPane)plContrastFullInfo.getComponentAt(index);
		String contractId = (String) this.getUIContext().get(UIContext.ID);
		Object obj = scrollPane.getUserObject();
		if(obj!=null&&obj.getClass().isArray()){
			String s[]=(String[])obj;
			UIContext uiContext = new UIContext(ui);
			uiContext.put(UIContext.ID, s[0]);
			uiContext.put("contractBillId", contractId);
			uiContext.put("fromContractQuery", Boolean.TRUE);
			//12.15添加 在结算单 页签显示时 把汇总 页签隐藏
			if(s[1].equals(ContractSettlementBillEditUI.class.getName()))
				uiContext.put("isShowPanelCollection", Boolean.FALSE);
			
			Component plUI = (CoreUIObject) UIFactoryHelper.initUIObject(s[1], uiContext,
					null, OprtState.VIEW);
			if(plUI instanceof PaymentBillEditUI){
				//付款单只显示工程付款情况表
				PaymentBillEditUI ui=(PaymentBillEditUI)plUI;
				plUI=ui.getPayBillEntryPanel();
			}
			scrollPane.setViewportView(plUI);
			scrollPane.setKeyBoardControl(true);
			scrollPane.setUserObject("hasLoad");
		}
	}

	/**
	 * output storeFields method
	 */
	public void storeFields() {
		super.storeFields();
	}

	public void onLoad() throws Exception {
		isFirstLoad=true;
		this.actionAddNew.setVisible(false);
		this.actionEdit.setVisible(false);
		this.actionRemove.setVisible(false);
		this.actionSave.setVisible(false);
		this.actionSubmit.setVisible(false);
		this.actionCopy.setVisible(false);
		this.actionCancel.setVisible(false);
		this.actionCancelCancel.setVisible(false);
		this.menuSubmitOption.setVisible(false);
		actionPrint.setVisible(false);
		actionPrintPreview.setVisible(false);
		actionPre.setVisible(false);
		actionFirst.setVisible(false);
		actionLast.setVisible(false);
		actionNext.setVisible(false);
		super.onLoad();
		plContrastFullInfo.removeAll();
		String contractId = (String) this.getUIContext().get(UIContext.ID);

		// //////////////////////////////////////////////////////////////////////////
		boolean isCoseSplit = false;

		// ContractBillInfo contract = ContractBillFactory.getRemoteInstance().getContractBillInfo(new ObjectUuidPK(BOSUuid.read(contractId)));
		// isCoseSplit = contract.isIsCoseSplit();
		// 合同单据's 是否成本拆分
		isCoseSplit = isContractCoseSplit(contractId);

		// //////////////////////////////////////////////////////////////////////////

		// addPanel(contractId, ContractDetailFullInfoUI.class.getName(), this.getResouce("billInfo"));
		//合同信息
		addPanel(contractId, ContractBillEditUI.class.getName(), this.getResouce("billInfo"));
		//拆分信息  this.getResouce("splitInfo")
		if (isCoseSplit) {
			addPanel(this.getSplitId(contractId), ContractCostSplitEditUI.class.getName(), "合同拆分");
		} else {
			addPanel(this.getNoSplitId(contractId), ConNoCostSplitEditUI.class.getName(), "合同拆分");
		}
		//变更信息  成本信息  付款信息
		addPanel(contractId, ContractChangeFullUI.class.getName(), this.getResouce("changeInfo"));
		addPanel(getConChangeSplitId(contractId), ConChangeSplitEditUI.class.getName(), "变更拆分");
		//结算单加载汇总信息时不加载自身 (暂时放出来，不根据参数进行判断)
//		Object obj = getUIContext().get("addSettlePanel");
//		if(obj==null||obj.equals(Boolean.TRUE)){
//			addPanel(this.getSettleId(contractId),ContractSettlementBillEditUI.class.getName(), this.getResouce("settleInfo"));
//		}
		addPanel(getSettleId(contractId),ContractSettlementBillEditUI.class.getName(), this.getResouce("settleInfo"));
		addPanel(getSettlementCostSplitId(contractId), SettlementCostSplitEditUI.class.getName(), "结算拆分");
		addPanel(contractId, ContractCostFullInfoUI.class.getName(), this.getResouce("costInfo"));
		addPanel(contractId, PaymentFullInfoUI.class.getName(), this.getResouce("payInfo"));
		
		//合同执行信息里面的那个“付款计划”页签暂时屏蔽
		//		addPanel(contractId, ContractPayPlanEditUI.class.getName(), this.getResouce("payPlan"));
		//付款申请   交底信息
		addPanel(contractId, PayRequestFullInfoUI.class.getName(), this.getResouce("payRequest"));
		addPanel(contractId, ContractMoveHistoryListUI.class.getName(), this.getResouce("bakInfo"));
		//关联合同页签
		addPanel(contractId, ContractBillFullUI.class.getName(), "关联合同页签");
		
		//违约单、扣款单、奖励单
		addPanel(contractId, CompensationBillInfoUI.class.getName(), this.getResouce("Compensation"));
		addPanel(contractId, DeductBillInfoUI.class.getName(), this.getResouce("Guerdon"));
		addPanel(contractId, GuerdonBilInfoUI.class.getName(), this.getResouce("Deduct"));
		
		FilterInfo filter=new FilterInfo();
		filter.appendFilterItem("contractBillId", contractId);
		FDCSQLBuilder builder=new FDCSQLBuilder();
		builder.appendSql("select top 1 fid from T_CAS_PaymentBill where fcontractbillid=? order by fcreateTime desc");
		builder.addParam(contractId);
		IRowSet rowSet=builder.executeQuery();
		if(rowSet!=null&&rowSet.size()==1){
			rowSet.next();
			String fid=rowSet.getString("fid");
			addPanel(fid, PaymentBillEditUI.class.getName(), "工程付款情况表");
		}
		
		plContrastFullInfo.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				try {
					lazyLoadPanel();
				} catch (UIException e1) {
					handUIExceptionAndAbort(e1);
				}
			}
		});
		isFirstLoad=false;
		
		menuItemCopy.setVisible(false);
		btnCopy.setVisible(false);
		
		
	}

	/**
	 * 描述：合同单据's 是否成本拆分
	 * 
	 * @param contractId
	 * @return
	 * @author rd_skyiter_wang
	 * @createDate 2014-11-10
	 */
	private boolean isContractCoseSplit(String contractId) {
		boolean isCoseSplit = false;
		if (null == contractId) {
			return isCoseSplit;
		}

		FDCSQLBuilder builder = new FDCSQLBuilder();
		// 注意：字段是FIsCostSplit，而不是FIsCoseSplit
		builder.appendSql(" SELECT FIsCostSplit FROM T_CON_ContractBill WHERE FID = ? ");
		builder.addParam(contractId.toString());
		try {
			IRowSet executeQuery = builder.executeQuery();
			if (executeQuery.next()) {
				int isCoseSplitRs = executeQuery.getInt("FIsCostSplit");
				isCoseSplit = BooleanUtils.toBoolean(isCoseSplitRs);
			}
		} catch (BOSException e) {
			handUIExceptionAndAbort(e);
		} catch (SQLException e) {
			handUIExceptionAndAbort(e);
		}

		return isCoseSplit;
	}

	private String getResouce(String resName) {
		return EASResource.getString(resourcePath, resName);
	}

	/**
	 * 根据id显示窗体
	 */
	public static boolean showDialogWindows(IUIObject ui, String id)
			throws UIException {
		UIContext uiContext = new UIContext(ui);
		uiContext.put(UIContext.ID, id);
		// 创建UI对象并显示
		IUIWindow uiWindow = UIFactory.createUIFactory(UIFactoryName.NEWTAB)
				.create(ContractFullInfoUI.class.getName(), uiContext, null,
						"VIEW");
		uiWindow.show();
		return true;
	}

	protected IObjectValue createNewData() {
		return null;
	}

	protected ICoreBase getBizInterface() throws Exception {
		return ContractBillFactory.getRemoteInstance();
	}
	
//	public SelectorItemCollection getSelectors() {
//		// TODO Auto-generated method stub
//		SelectorItemCollection sic = super.getSelectors();
//		sic.add(new SelectorItemInfo("mainContract.*"));
//		sic.add(new SelectorItemInfo("effectiveStartDate"));
//		sic.add(new SelectorItemInfo("effectiveEndDate"));
//		sic.add(new SelectorItemInfo("isSubContract"));
//		sic.add(new SelectorItemInfo("information"));
//		return sic;
//	}
}