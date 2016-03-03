/**
 * output package name
 */
package com.kingdee.eas.fdc.finance.client;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.Action;
import javax.swing.SwingUtilities;

import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.ctrl.kdf.servertable.KDTStyleConstants;
import com.kingdee.bos.ctrl.kdf.table.ICell;
import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.KDTDefaultCellEditor;
import com.kingdee.bos.ctrl.kdf.table.KDTSelectBlock;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.kdf.table.event.BeforeActionEvent;
import com.kingdee.bos.ctrl.kdf.table.event.BeforeActionListener;
import com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent;
import com.kingdee.bos.ctrl.kdf.table.event.KDTPropertyChangeEvent;
import com.kingdee.bos.ctrl.kdf.table.event.KDTPropertyChangeListener;
import com.kingdee.bos.ctrl.kdf.util.editor.ICellEditor;
import com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper;
import com.kingdee.bos.ctrl.swing.KDFormattedTextField;
import com.kingdee.bos.ctrl.swing.KDLayout;
import com.kingdee.bos.ctrl.swing.KDMenuItem;
import com.kingdee.bos.ctrl.swing.KDTextField;
import com.kingdee.bos.ctrl.swing.StringUtils;
import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.framework.cache.ActionCache;
import com.kingdee.bos.metadata.data.SortType;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.entity.SelectorItemInfo;
import com.kingdee.bos.metadata.entity.SorterItemCollection;
import com.kingdee.bos.metadata.entity.SorterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.ui.face.IUIWindow;
import com.kingdee.bos.ui.face.ItemAction;
import com.kingdee.bos.ui.face.UIFactory;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.base.permission.client.longtime.ILongTimeTask;
import com.kingdee.eas.base.permission.client.longtime.LongTimeDialog;
import com.kingdee.eas.base.permission.client.util.UITools;
import com.kingdee.eas.basedata.assistant.PeriodInfo;
import com.kingdee.eas.basedata.assistant.PeriodUtils;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.common.client.OprtState;
import com.kingdee.eas.common.client.SysContext;
import com.kingdee.eas.common.client.UIContext;
import com.kingdee.eas.common.client.UIFactoryName;
import com.kingdee.eas.fdc.basedata.CostAccountFactory;
import com.kingdee.eas.fdc.basedata.CostAccountInfo;
import com.kingdee.eas.fdc.basedata.CostAccountWithAccountCollection;
import com.kingdee.eas.fdc.basedata.CostAccountWithAccountFactory;
import com.kingdee.eas.fdc.basedata.CostAccountWithAccountInfo;
import com.kingdee.eas.fdc.basedata.CostSplitBillTypeEnum;
import com.kingdee.eas.fdc.basedata.CostSplitStateEnum;
import com.kingdee.eas.fdc.basedata.CostSplitTypeEnum;
import com.kingdee.eas.fdc.basedata.CurProjectInfo;
import com.kingdee.eas.fdc.basedata.FDCBasedataException;
import com.kingdee.eas.fdc.basedata.FDCBillStateEnum;
import com.kingdee.eas.fdc.basedata.FDCConstants;
import com.kingdee.eas.fdc.basedata.FDCHelper;
import com.kingdee.eas.fdc.basedata.FDCNumberHelper;
import com.kingdee.eas.fdc.basedata.FDCSQLBuilder;
import com.kingdee.eas.fdc.basedata.FDCSQLFacadeFactory;
import com.kingdee.eas.fdc.basedata.FDCSplitBillEntryCollection;
import com.kingdee.eas.fdc.basedata.FDCSplitBillEntryInfo;
import com.kingdee.eas.fdc.basedata.ICostAccount;
import com.kingdee.eas.fdc.basedata.ICostAccountWithAccount;
import com.kingdee.eas.fdc.basedata.IFDCBill;
import com.kingdee.eas.fdc.basedata.PaymentTypeInfo;
import com.kingdee.eas.fdc.basedata.client.FDCClientUtils;
import com.kingdee.eas.fdc.basedata.client.FDCMsgBox;
import com.kingdee.eas.fdc.basedata.client.FDCSplitClientHelper;
import com.kingdee.eas.fdc.basedata.client.FDCTableHelper;
import com.kingdee.eas.fdc.basedata.util.FdcMapUtil;
import com.kingdee.eas.fdc.basedata.util.client.RENoteDataProvider;
import com.kingdee.eas.fdc.contract.ConChangeSplitCollection;
import com.kingdee.eas.fdc.contract.ConChangeSplitFactory;
import com.kingdee.eas.fdc.contract.ConChangeSplitInfo;
import com.kingdee.eas.fdc.contract.ContractBillFactory;
import com.kingdee.eas.fdc.contract.ContractBillInfo;
import com.kingdee.eas.fdc.contract.ContractCostSplitCollection;
import com.kingdee.eas.fdc.contract.ContractCostSplitFactory;
import com.kingdee.eas.fdc.contract.ContractCostSplitInfo;
import com.kingdee.eas.fdc.contract.ContractSettlementBillCollection;
import com.kingdee.eas.fdc.contract.ContractSettlementBillFactory;
import com.kingdee.eas.fdc.contract.ContractSettlementBillInfo;
import com.kingdee.eas.fdc.contract.FDCUtils;
import com.kingdee.eas.fdc.contract.PayRequestBillFactory;
import com.kingdee.eas.fdc.contract.PayRequestBillInfo;
import com.kingdee.eas.fdc.contract.SettlementCostSplitEntryCollection;
import com.kingdee.eas.fdc.contract.SettlementCostSplitEntryFactory;
import com.kingdee.eas.fdc.contract.SettlementCostSplitEntryInfo;
import com.kingdee.eas.fdc.contract.SettlementCostSplitFactory;
import com.kingdee.eas.fdc.contract.client.ContractBillEditUI;
import com.kingdee.eas.fdc.contract.client.ContractClientUtils;
import com.kingdee.eas.fdc.finance.FDCPaymentBillCollection;
import com.kingdee.eas.fdc.finance.FDCPaymentBillFactory;
import com.kingdee.eas.fdc.finance.FDCPaymentBillInfo;
import com.kingdee.eas.fdc.finance.IPaymentSplitEntry;
import com.kingdee.eas.fdc.finance.PaySplit4VoucherInfo;
import com.kingdee.eas.fdc.finance.PaymentAutoSplitHelper;
import com.kingdee.eas.fdc.finance.PaymentSplitCollection;
import com.kingdee.eas.fdc.finance.PaymentSplitEntryCollection;
import com.kingdee.eas.fdc.finance.PaymentSplitEntryFactory;
import com.kingdee.eas.fdc.finance.PaymentSplitEntryInfo;
import com.kingdee.eas.fdc.finance.PaymentSplitFactory;
import com.kingdee.eas.fdc.finance.PaymentSplitHelper;
import com.kingdee.eas.fdc.finance.PaymentSplitInfo;
import com.kingdee.eas.fdc.finance.ProjectPeriodStatusFactory;
import com.kingdee.eas.fdc.finance.ProjectPeriodStatusInfo;
import com.kingdee.eas.fdc.finance.SettledMonthlyCollection;
import com.kingdee.eas.fdc.finance.SettledMonthlyFactory;
import com.kingdee.eas.fdc.finance.SettledMonthlyInfo;
import com.kingdee.eas.fi.cas.BillStatusEnum;
import com.kingdee.eas.fi.cas.PaymentBillFactory;
import com.kingdee.eas.fi.cas.PaymentBillInfo;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.eas.util.client.EASResource;
import com.kingdee.eas.util.client.MsgBox;
import com.kingdee.jdbc.rowset.IRowSet;
import com.mycompany.internal.edu.emory.mathcs.backport.java.util.Arrays;

/**
 * 付款拆分
 */
public class PaymentSplitEditUI extends AbstractPaymentSplitEditUI {
	/**
	 * 归属付款金额
	 */
	private static final String COL_PAYED_AMT = "payedAmt";

	/**
	 *  成本拆分金额
	 */
	private static final String COL_COST_AMT = "costAmt";

	/**
	 *  归属发票金额
	 */
	private static final String COL_INVOICE_AMT = "invoiceAmt";

	/**
	 *  已拆分发票金额
	 */
	private static final String COL_SPLITED_INVOICE_AMT = "splitedInvoiceAmt";
	
	/**
	 *  直接成本金额amount
	 */
	private static final String COL_DIRECT_AMT = "directAmt";
	/**
	 *  归属成本金额
	 */
	private static final String COL_AMOUT = "amount";
	
	/**
	 *  已拆分成本金额splitedCostAmt
	 */
	private static final String COL_SPLITED_COST_AMT= "splitedCostAmt";
	
	private static final Logger logger = CoreUIObject.getLogger(PaymentSplitEditUI.class);

	private boolean hasDirectAmt = false;
	private BigDecimal payAmount = null; 
	
	/**
	 *  是否工程量确认合同类型
	 */
	private boolean isLoadWorkConfirm = false;
	
	/**
	 * kdtEntrys 分录表上的拆分数据集，使用延迟加载解决性能问题 
	 */
	private PaymentSplitEntryCollection payEntrys = null;

	/**
	 * 付款单对象
	 */
	protected PaymentBillInfo paymentBillInfo;
	/**
	 * 付款申请单对象
	 */
	protected PayRequestBillInfo payRequestBillInfo;
	
	/**
	 * output class constructor
	 */
	public PaymentSplitEditUI() throws Exception {
		super();
	}

	/**
	 * 第二行的Left Rectangle
	 */
	private Rectangle leftRec =null;
	/**
	 * 第二行的right Rectangle
	 */
	private Rectangle rightRec =null;
	
	public void initUIContentLayout() {
		super.initUIContentLayout();
		leftRec = (Rectangle)this.contContrName.getBounds().clone();
		rightRec = (Rectangle)this.contPayType.getBounds().clone();
	}

	protected void kdtEntrys_editStopped(
			com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e)
			throws Exception {
		// super.kdtEntrys_editStopped(e);

		IRow row = getDetailTable().getRow(e.getRowIndex());
		
		if (e.getColIndex() == kdtEntrys.getColumnIndex(COL_AMOUT)) {
			if (e.getValue() != e.getOldValue()) {
				editData.setDescription("PlainSplit");
				BigDecimal amount = FDCHelper.ZERO;
				amount = amount.setScale(10);
				FDCSplitBillEntryInfo entry;
				entry = (FDCSplitBillEntryInfo) kdtEntrys.getRow(
						e.getRowIndex()).getUserObject();
				Object cellVal = kdtEntrys.getRow(e.getRowIndex()).getCell(
						COL_AMOUT).getValue();
				if (cellVal != null) {
					amount = new BigDecimal(cellVal.toString());
					if (amount.compareTo(FDCHelper.ZERO) == 0) {
						entry.setAmount(FDCHelper.ZERO);
						apptAmount(e.getRowIndex());
						calcAmount(0);
//						if (editData.getQualityAmount() != null) {
//							setQuaAmt();
//						}
						calCmpAmtTotal();
						return;
					}
				}
				
				// 统计总金额
				calAmtTotal("amount");
				entry.setAmount(amount);
			}
		}
		if (e.getColIndex() == kdtEntrys.getColumnIndex("directAmt")) {
			if (e.getValue() != e.getOldValue()) {
				recoverDirectAmt(e, row);
				editData.setDescription("ManualSplit");
				BigDecimal amount = FDCHelper.ZERO;
				amount = amount.setScale(10);
				PaymentSplitEntryInfo entry;
				entry = (PaymentSplitEntryInfo) kdtEntrys.getRow(e.getRowIndex()).getUserObject();
				Object cellVal = kdtEntrys.getRow(e.getRowIndex()).getCell("directAmt").getValue();
				if (cellVal != null) {
					amount = new BigDecimal(cellVal.toString());
				}
				entry.setDirectAmt(amount);
				setHasDirectAmt();
			}
		}
		
		if (e.getColIndex() == kdtEntrys.getColumnIndex("directPayedAmt")) {
			if (e.getValue() != e.getOldValue()) {
				recoverDirectPayedAmt(e, row);
				editData.setDescription("ManualSplit");
				BigDecimal amount = FDCHelper.ZERO;
				amount = amount.setScale(10);
				PaymentSplitEntryInfo entry;
				entry = (PaymentSplitEntryInfo) kdtEntrys.getRow(e.getRowIndex()).getUserObject();
				Object cellVal = kdtEntrys.getRow(e.getRowIndex()).getCell("directPayedAmt").getValue();
				if (cellVal != null) {
					amount = new BigDecimal(cellVal.toString());
				}
				entry.setDirectPayedAmt(amount);
				setHasDirectAmt();
			}
		}
		
		if (e.getColIndex() == kdtEntrys.getColumnIndex("directInvoiceAmt")) {
			if (e.getValue() != e.getOldValue()) {
				recoverDirectInvoiceAmt(e, row);
				editData.setDescription("ManualSplit");
				PaymentSplitEntryInfo entry;
				entry = (PaymentSplitEntryInfo) kdtEntrys.getRow(e.getRowIndex()).getUserObject();
				Object cellVal = kdtEntrys.getRow(e.getRowIndex()).getCell("directInvoiceAmt").getValue();
				BigDecimal amount=FDCHelper.toBigDecimal(cellVal);
				entry.setDirectInvoiceAmt(amount);
				setHasDirectAmt();
			}
		}
		
		/**
		 * 合同付款拆分时，若本期成本金额与本期实付金额相同，则在录入指定成本金额之后自动将对应的值录入指定付款金额。
		 * by jian_wen 2009.12.21
		 */
		if(txtCompletePrjAmt.getBigDecimalValue()!=null && txtAmount.getBigDecimalValue()!=null
				&&txtCompletePrjAmt.getBigDecimalValue().compareTo(txtAmount.getBigDecimalValue()) == 0){
			if(e.getColIndex() == kdtEntrys.getColumnIndex("directAmt")){
				row.getCell("directPayedAmt").setValue(row.getCell("directAmt").getValue());
			}
			if(e.getColIndex() == kdtEntrys.getColumnIndex("directPayedAmt")){
				row.getCell("directAmt").setValue(row.getCell("directPayedAmt").getValue());
			}
		}			
		
		/**
		 * 合同付款拆分时，若本期发票金额与本期实付金额相同，则在录入指定发票金额之后自动将对应的值录入指定付款金额。
		 * by 罗罗 2010.4.29
		 */
		if(txtInvoiceAmt.getBigDecimalValue()!=null && txtAmount.getBigDecimalValue()!=null
				&&txtInvoiceAmt.getBigDecimalValue().compareTo(txtAmount.getBigDecimalValue()) == 0){
			if(e.getColIndex() == kdtEntrys.getColumnIndex("directInvoiceAmt")){
				if(FDCHelper.toBigDecimal(row.getCell("directPayedAmt").getValue(),2).signum()==0){//为0时相同
					row.getCell("directPayedAmt").setValue(row.getCell("directInvoiceAmt").getValue());
				}
			}
			if(e.getColIndex() == kdtEntrys.getColumnIndex("directPayedAmt")){
				if(FDCHelper.toBigDecimal(row.getCell("directInvoiceAmt").getValue(),2).signum()==0){
					row.getCell("directInvoiceAmt").setValue(row.getCell("directPayedAmt").getValue());
				}
			}
		}			
		
		if (hasDirectAmt) {
			// 清空
			for (int i = 0; i < getDetailTable().getRowCount(); i++) {
				((FDCSplitBillEntryInfo) getDetailTable().getRow(i).getUserObject()).put(COL_AMOUT, FDCHelper.ZERO);
				getDetailTable().getCell(i, COL_AMOUT).setValue(FDCHelper.ZERO);
				((FDCSplitBillEntryInfo) getDetailTable().getRow(i).getUserObject()).put(COL_PAYED_AMT, FDCHelper.ZERO);
				getDetailTable().getCell(i, COL_PAYED_AMT).setValue(FDCHelper.ZERO);
				
				((FDCSplitBillEntryInfo) getDetailTable().getRow(i).getUserObject()).put(COL_INVOICE_AMT, FDCHelper.ZERO);
				getDetailTable().getCell(i, COL_INVOICE_AMT).setValue(FDCHelper.ZERO);
			}
		} 
		handleMeasureCalc(e, row);
	}

	/**
	 * 
	 * 描述：当启用参数FDC017_LIMITCOST参数时，若directAmt> costAmt- splitedCostAmt时，恢复原值
	 * @Author：owen_wen
	 * @CreateTime：2013-3-8
	 */
	private void recoverDirectAmt(KDTEditEvent e, IRow row) {
		if (isLimitCost()) {
			BigDecimal maxValue = FDCHelper.toBigDecimal(row.getCell(COL_COST_AMT).getValue(), 2).subtract(
					FDCHelper.toBigDecimal(row.getCell("splitedCostAmt").getValue(), 2));
			if (FDCHelper.compareTo(e.getValue(), maxValue) > 0) {
				row.getCell(COL_DIRECT_AMT).setValue(e.getOldValue());
				abort();
			}
		}
	}

	/**
	 * 
	 * 描述：当启用参数FDC017_LIMITCOST参数时，若directPayedAmt> ShouldPayAmt - splitedPayedAmt 时，恢复原值
	 * @Author：owen_wen
	 * @CreateTime：2013-3-8
	 */
	private void recoverDirectPayedAmt(KDTEditEvent e, IRow row) {
		if (isLimitCost()) {
			BigDecimal maxValue = FDCHelper.toBigDecimal(FDCHelper.subtract(row.getCell("shouldPayAmt").getValue(), row.getCell(
					"splitedPayedAmt").getValue()), 2);
			if (FDCHelper.compareTo(e.getValue(), maxValue) > 0) {
				row.getCell(COL_DIRECT_AMT).setValue(e.getOldValue());
				abort();
			}
		}
	}
	
	/**
	 * 
	 * 描述：当启用参数FDC017_LIMITCOST参数时，若directInvoiceAmt > costAmt - splitedInvoiceAmt 时，恢复原值
	 * @Author：owen_wen
	 * @CreateTime：2013-3-8
	 */
	private void recoverDirectInvoiceAmt(KDTEditEvent e, IRow row) {
		if (isLimitCost()) {
			BigDecimal maxValue = FDCHelper.toBigDecimal(row.getCell(COL_COST_AMT).getValue(), 2).subtract(
					FDCHelper.toBigDecimal(row.getCell(COL_SPLITED_INVOICE_AMT).getValue(), 2));
			if (FDCHelper.compareTo(e.getValue(), maxValue) > 0) {
				row.getCell(COL_DIRECT_AMT).setValue(e.getOldValue());
				abort();
			}
		}
	}
	
	protected void checkTotalCostAmt() {
		if(isSimpleInvoice() || isInvoiceMgr()){
			//发票模式的时候，不需要校验成本
		} else {			
			super.checkTotalCostAmt();
		}
	}

	/**
	 * 检查 发票拆分 汇总金额 <br>
	 * TODO 需要将成本拆分的代码移到checkTotalCostAmt() Added by Owen_wen
	 */
	private void checkTotalInvoiceAmtBeforeSave() {		
		BigDecimal invoiceAmt=txtInvoiceAmt.getBigDecimalValue();
		BigDecimal amount = txtAmount.getBigDecimalValue();
		if(invoiceAmt==null){
			invoiceAmt=FDCHelper.ZERO;
		}
		if(amount ==null){
			amount = FDCHelper.ZERO;
		}
		invoiceAmt = invoiceAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
		amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal splitedInvoiceAmt = FDCHelper.toBigDecimal(kdtEntrys.getRow(0).getCell(COL_INVOICE_AMT).getValue()).setScale(2,
				BigDecimal.ROUND_HALF_UP);
    	BigDecimal splitAmount = FDCHelper.toBigDecimal(kdtEntrys.getRow(0).getCell(COL_PAYED_AMT).getValue()).setScale(2,
				BigDecimal.ROUND_HALF_UP);

    	if(splitAmount==null){
    		splitAmount=FDCHelper.ZERO;
    	}
    	if(splitedInvoiceAmt==null){
    		splitedInvoiceAmt = FDCHelper.ZERO;
    		
    	}
    	if((splitedInvoiceAmt==null||splitedInvoiceAmt.compareTo(FDCHelper.ZERO)==0)&&invoiceAmt.compareTo(FDCHelper.ZERO)!=0){
    		FDCMsgBox.showWarning(this,FDCSplitClientHelper.getRes("notSplited"));
    		SysUtil.abort();
    	}else if(invoiceAmt.compareTo(splitedInvoiceAmt)>0 || amount.compareTo(splitAmount)>0 ){
    		FDCMsgBox.showWarning(this,FDCSplitClientHelper.getRes("notAllSplit"));
        	SysUtil.abort();
    	}else if(invoiceAmt.compareTo(splitedInvoiceAmt)==0 || amount.compareTo(splitAmount)==0 ){
    		editData.setSplitState(CostSplitStateEnum.ALLSPLIT);
    		
    		//检查非明细工程项目的科目是否已拆分	//Jelon 	Dec 11, 2006
    		for(int i=0; i<kdtEntrys.getRowCount(); i++){
    			IRow row=kdtEntrys.getRow(i);
    			FDCSplitBillEntryInfo entry=(FDCSplitBillEntryInfo)row.getUserObject();
    			
    			if(entry.getLevel()<0) continue;//总计行
    			
    			if(entry.getLevel()==0 && entry.isIsLeaf()){
    				if (!entry.getCostAccount().getCurProject().isIsLeaf()) {
						FDCMsgBox.showWarning(this, "必须拆分到最明细的工程项目的成本科目!");
						SysUtil.abort();
					}
    			}    			
    		}    		
    	}else{
    		if(!isSimpleInvoice()){
    			FDCMsgBox.showWarning(this,FDCSplitClientHelper.getRes("moreThan"));
    			SysUtil.abort();
    		}
    	}
	}
	
	public boolean checkClickTime() {
		return true;
	}
	
	/**
	 * output actionSave_actionPerformed
	 */
	public void actionSave_actionPerformed(ActionEvent e) throws Exception {
		if(isWorkLoadSeparate()){
			
			//update by david_yang PT048096 2011.04.12 发票模式下 全项目动态成本标中已实现成本为空
			//暂时将归属成本金额全部更新成0,以后付款申请单会控制保证
//			for(int i=0;i<getDetailTable().getRowCount();i++){
			//				getDetailTable().getCell(i, COL_AMOUT).setValue(FDCHelper.ZERO);
//			}
		}
		if (editData.getDescription() != null
				&& !editData.getDescription().equals("AutoSplit")) {
			btnSplit.doClick();
			
			//add by zkyan 不知道干嘛在取一遍，如果再取一遍，简单模式下值就被清空了
			//			updateAccAimt();
			// if (editData.getCompletePrjAmt() != null) {
			// setPayedAmt();
			// }
//			if (editData.getQualityAmount() != null) {
//				setQuaAmt();
//			}
		}
		if(isSimpleInvoice() || isInvoiceMgr()){
			checkTotalInvoiceAmtBeforeSave();
		}
		isParentCostAccount();
		super.actionSubmit_actionPerformed(e);
		setFirstLine();
	}

	/**
	 * 加了进度条提示。当待保存的分录超过1000条时，才会使用进度条 Added by Owen_wen 2013-04-28
	 */
	public IObjectPK runSave() throws Exception {
		if (editData.getEntrys().size() > 1000) {
			showLongTimeDialog();
			return null;
		} else {
			return super.runSave();
		}
	}

	/**
	 * 
	 * 描述：为保存运作添加进度条，增加友好性
	 * @Author：owen_wen
	 * @CreateTime：2013-4-28
	 */
	private void showLongTimeDialog() {
		LongTimeDialog dialog = UITools.getDialog(this);
		dialog.setLongTimeTask(new ILongTimeTask() {
			public Object exec() throws Exception {
				return getBizInterface().save(editData);
			}
			
			public void afterExec(Object result) throws Exception {
				doAfterSave((IObjectPK) result);
			}
		});
		dialog.show();		
	}

	/**
	 * @see com.kingdee.eas.fdc.basedata.client.FDCBillEditUI#verifyInput(java.awt.event.ActionEvent)
	 */
	protected void verifyInput(ActionEvent e) throws Exception {
		super.verifyInput(e);
		if (kdtEntrys.getRowCount() > 0 && isLimitCost()) {
		/**
		 * 当合同存在预付款时，不在校验没行付款拆分金额与已拆分成本金额
		 */
			if(!StringUtils.isEmpty(editData.getPaymentBill().getContractBillId())){
				FilterInfo filter = new FilterInfo();
				filter.getFilterItems().add(new FilterItemInfo("contractBillId", editData.getPaymentBill().getContractBillId()));
				filter.getFilterItems().add(new FilterItemInfo("fdcPayType.name", "预付款"));
				
				boolean exists = PaymentBillFactory.getRemoteInstance().exists(filter);
				if(exists){
					return;
				}
			}
			for (int i = 0; i < kdtEntrys.getRowCount(); i++) {
				IRow row = kdtEntrys.getRow(i);
				BigDecimal splitedCostAmt = FDCHelper.toBigDecimal(row.getCell("splitedCostAmt").getValue());
				BigDecimal splitedPayedAmt = FDCHelper.toBigDecimal(row.getCell("splitedPayedAmt").getValue());
				BigDecimal amount = FDCHelper.toBigDecimal(row.getCell("directAmt").getValue() == null ? row.getCell(COL_AMOUT).getValue()
						: row.getCell("directAmt").getValue());
				BigDecimal payedAmt = FDCHelper.toBigDecimal(row.getCell("directPayedAmt").getValue() == null ? row.getCell(COL_PAYED_AMT)
						.getValue() : row.getCell("directPayedAmt").getValue());

				if (isWorkLoadSeparate()) {
					if (FDCHelper.add(splitedPayedAmt, payedAmt).compareTo(splitedCostAmt) == 1 && isLoadWorkConfirm) {
						FDCMsgBox.showWarning("付款拆分金额不能大于已拆分成本金额");
						abort();
					}
				} else {
					// 已拆分付款金额 + 直接付款金额 > 已拆分成本金额 + 直接成本金额
					if (FDCHelper.add(splitedPayedAmt, payedAmt).compareTo(FDCHelper.add(splitedCostAmt, amount)) == 1) {
						FDCMsgBox.showWarning("付款拆分金额不能大于已拆分成本拆分金额");
						abort();
					}
				}
			}
		}
	}
	
	/**
	 * 是否为预付款 - R131226-0323
	 * 
	 * @author RD_zhaoqin
	 * @date 2013/12/31
	 */
	private boolean isPrePay() {
		boolean isPrePay = false;
		if(editData.getPaymentBill()!=null&&editData.getPaymentBill().getFdcPayType()!=null){
			String name = editData.getPaymentBill().getFdcPayType().getName();
			if("预付款".equals(name) ||"預付款".equals(name) ){
				isPrePay=true;
			}
		}
		return isPrePay;
	}

	/**
	 * 判断是否为父科目
	 * 
	 * @description
	 * @author 何鹏
	 * @createDate 2011-8-31
	 * @version EAS7.0
	 * @see
	 */
	public void isParentCostAccount() {
		try {
			// 在启用财务一体化复杂模式下做此判断
			if (FDCUtils.IsFinacial(null, SysContext.getSysContext().getCurrentFIUnit().getId()
					.toString())) {
				if (kdtEntrys.getRowCount() > 0) {
					for (int i = 0; i < kdtEntrys.getRowCount(); i++) {
						Object obj = kdtEntrys.getRow(i).getUserObject();
						if ((obj instanceof PaymentSplitEntryInfo)
								&& ((PaymentSplitEntryInfo) obj).getLevel() > -1) {
							PaymentSplitEntryInfo entry = (PaymentSplitEntryInfo) obj;
							if (!entry.getCostAccount().isIsLeaf()) {
								MsgBox.showInfo(this, "合同付款拆分必须拆分到项目的成本科目体系中最明细的科目，不能保存！");
								SysUtil.abort();
							}
						}
					}
				}
			}
		} catch (EASBizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BOSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void getAccFromCost() throws Exception {
		ICostAccountWithAccount iCostAccountWithAccount = CostAccountWithAccountFactory
				.getRemoteInstance();
		IPaymentSplitEntry iPaymentSplitEntry = PaymentSplitEntryFactory
				.getRemoteInstance();
		PaymentSplitEntryCollection coll;
		CostAccountWithAccountCollection entryColl = null;
		CostAccountWithAccountInfo entryInfo = new CostAccountWithAccountInfo();
		ICostAccount iCostAccount = CostAccountFactory.getRemoteInstance();
		EntityViewInfo view = new EntityViewInfo();
		FilterInfo filter = new FilterInfo();
		filter.getFilterItems().add(
				new FilterItemInfo("parent.id", editData.getId()));
		filter.getFilterItems().add(new FilterItemInfo("isLeaf", Boolean.TRUE));
		view.setFilter(filter);
		view.getSelector().add(new SelectorItemInfo("id"));
		view.getSelector().add(new SelectorItemInfo("seq"));
		view.getSelector().add(new SelectorItemInfo("costAccount.*"));
		view.getSelector().add(new SelectorItemInfo("accountView.*"));
		coll = iPaymentSplitEntry.getPaymentSplitEntryCollection(view);
		int entrySize = coll.size();
		for (int j = 0; j < entrySize; j++) {
			PaymentSplitEntryInfo info = coll.get(j);
			CostAccountInfo costAcc = info.getCostAccount();
			getAccForEntry(info, costAcc, iCostAccountWithAccount, entryColl,
					entryInfo, iPaymentSplitEntry, iCostAccount);
		}
	}

	private void getAccForEntry(PaymentSplitEntryInfo info,
			CostAccountInfo costAcc,
			ICostAccountWithAccount iCostAccountWithAccount,
			CostAccountWithAccountCollection entryColl,
			CostAccountWithAccountInfo entryInfo,
			IPaymentSplitEntry iPaymentSplitEntry, ICostAccount iCostAccount)
			throws Exception {
		EntityViewInfo entryView = new EntityViewInfo();
		FilterInfo entryFilter = new FilterInfo();
		entryFilter.getFilterItems().add(
				new FilterItemInfo("costAccount.id", costAcc.getId()));
		if (!iCostAccountWithAccount.exists(entryFilter)) {
			if (costAcc.getLevel() == 1) {
				MsgBox.showWarning(this, "成本科目未设置与会计科目的对应关系！");
				SysUtil.abort();
			} else {
				SelectorItemCollection selector = new SelectorItemCollection();
				selector.add("id");
				selector.add("level");
				selector.add("parent.*");
				selector.add("curProject.name");
				selector.add("fullOrgUnit.name");
				// if(costAcc.getParent().getId()!= null)
				CostAccountInfo parent = iCostAccount
						.getCostAccountInfo(new ObjectUuidPK(costAcc
								.getParent().getId()), selector);
				getAccForEntry(info, parent, iCostAccountWithAccount,
						entryColl, entryInfo, iPaymentSplitEntry, iCostAccount);
			}
		}
		entryView.setFilter(entryFilter);
		entryColl = iCostAccountWithAccount
				.getCostAccountWithAccountCollection(entryView);
		if (entryColl.size() == 1) {
			entryInfo = entryColl.get(0);
			if (entryInfo.getAccount() != null) {
				info.setAccountView(entryInfo.getAccount());
				SelectorItemCollection selector = new SelectorItemCollection();
				selector.add("accountView");
				iPaymentSplitEntry.updatePartial(info, selector);
			}
		}
	}
	/**
	 * 按比例拆分 
	 */
	public void actionSplitBaseOnProp_actionPerformed(ActionEvent e) throws Exception
    {
		hasDirectAmt = false;
		editData.setDescription("splitBaseOnProp");//按比例拆分
		FDCSplitBillEntryCollection entrys = getEntrys();
		//归属成本金额
		HashMap costMap = new HashMap();
		costMap.put("headCostAmt", txtCompletePrjAmt.getBigDecimalValue());//表头的实付金额
		costMap.put("splitCostAmtSum",kdtEntrys.getRow(0).getCell(COL_COST_AMT).getValue());//成本拆分金额个明细行合计
		costMap.put("hadSplitCostAmtSum", kdtEntrys.getRow(0).getCell("splitedCostAmt").getValue());//已拆分付款金额各明细行合计
		PaymentAutoSplitHelper.splitCostAmtBaseOnProp(entrys, costMap);
		// 归属成本金额
		calDirAmt();
		// 计算表头拆分成本金额
		calcAmount(0);
		//归属付款金额
		HashMap payedMap = new HashMap();
		payedMap.put("headPayedAmt", txtAmount.getBigDecimalValue());//表头的实付金额
		payedMap.put("splitCostAmtSum",kdtEntrys.getRow(0).getCell(COL_COST_AMT).getValue());//成本拆分金额个明细行合计
		payedMap.put("hadSplitPayedAmtSum", kdtEntrys.getRow(0).getCell("splitedPayedAmt").getValue());//已拆分付款金额各明细行合计
		PaymentAutoSplitHelper.splitPayedAmtBaseOnProp(entrys, payedMap);
		// 归属付款金额
		calPayedAmt();
		// 归属成本金额
		// calDirAmt();
		if(isInvoiceMgr() || isSimpleInvoice()){
			//归属发票金额
			HashMap invoiceMap = new HashMap();
			invoiceMap.put("headInvoiceAmt", txtInvoiceAmt.getBigDecimalValue());//表头的发票金额
			invoiceMap.put("splitCostAmtSum",kdtEntrys.getRow(0).getCell(COL_COST_AMT).getValue());//成本拆分金额个明细行合计
			invoiceMap.put("hadSplitInvoiceAmtSum", kdtEntrys.getRow(0).getCell(COL_SPLITED_INVOICE_AMT).getValue());// 已拆分发票金额各明细行合计
			PaymentAutoSplitHelper.splitInvoiceAmtBaseOnProp(entrys, invoiceMap);
			calInvoiceAmt();
			// //发票没有已完工
			// txtSplitedAmount.setValue(FDCHelper.ZERO);
			// txtCompletePrjAmt.setValue(FDCHelper.ZERO);
		}
		if (getDetailTable().getRowCount() > 0) {
			for (int i = 0; i < getDetailTable().getRowCount(); i++) {
				IRow row = getDetailTable().getRow(i);
				row.getCell("directAmt").setValue(null);
				row.getCell("directPayedAmt").setValue(null);
				if(isInvoiceMgr() || isSimpleInvoice()){
					//处理发票
					row.getCell("directInvoiceAmt").setValue(null);
				}
			}
		}
		setFirstLine();
	}

	/**
	 * 自动匹配算法 <P>
	 * 
	 *  新增发票的自动匹配，以为发票可以超过工程量，所以没有办法通过完工工程量来匹配，所以发票的匹配算法匹配与完工工程量的同
	 * @see com.kingdee.eas.fdc.finance.client.AbstractPaymentSplitEditUI#actionAutoMatchSplit_actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionAutoMatchSplit_actionPerformed(ActionEvent e)
			throws Exception {
		hasDirectAmt = false;
		editData.setDescription("AutoSplit");
		FilterInfo filterPay = new FilterInfo();
		filterPay.getFilterItems().add(new FilterItemInfo("paymentBill.contractBillId", editData.getPaymentBill().getContractBillId()));
		filterPay.getFilterItems().add(new FilterItemInfo("isProgress", Boolean.TRUE));
		filterPay.getFilterItems().add(new FilterItemInfo("state", FDCBillStateEnum.INVALID_VALUE, CompareType.NOTEQUALS));
		boolean hasSettlePayed = getBizInterface().exists(filterPay);
		boolean isKeepAmt = editData.getPaymentBill().getFdcPayType().getPayType().getId().toString().equals(PaymentTypeInfo.keepID);
		boolean isSettle = editData.isIsProgress();
		if (!hasSettlePayed
				&& editData.getPaymentBill().getFdcPayType().getPayType().getId().toString().equals(PaymentTypeInfo.settlementID)) {
			isSettle = true;
		}
		
		FDCSplitBillEntryCollection entrys = getEntrys();
		PaymentAutoSplitHelper.autoSplit(entrys, txtCompletePrjAmt.getBigDecimalValue());
		PaymentAutoSplitHelper.autoSplitAmt(entrys, txtAmount.getBigDecimalValue(), true, hasSettlePayed, isSettle, isKeepAmt,
				isAdjustVourcherModel());

		//简单模式处理发票
		if(isSimpleInvoice()){
			//为什么在这里还要在处理一次付款金额，上面autoSplitAmt方法里已经处理过了，而且在这里合同已经结算并有保修金时，付款金额处理有误
			//			PaymentAutoSplitHelper.autoSplitSimpleInvoicePayAmt(entrys, txtAmount.getBigDecimalValue());
			PaymentAutoSplitHelper.autoSplitSimpleInvoiceInvoiceAmt(entrys, txtInvoiceAmt.getBigDecimalValue());
			calInvoiceAmt();
		}
		if(isInvoiceMgr()){
			//处理发票
			PaymentAutoSplitHelper.autoSplitInvoiceAmt(entrys, txtInvoiceAmt.getBigDecimalValue());
			calInvoiceAmt();
		}
		calDirAmt();
		calPayedAmt();
		calcAmount(0);
		if (getDetailTable().getRowCount() > 0) {
			for (int i = 0; i < getDetailTable().getRowCount(); i++) {
				IRow row = getDetailTable().getRow(i);
				row.getCell("directAmt").setValue(null);
				row.getCell("directPayedAmt").setValue(null);
				if(isInvoiceMgr() || isSimpleInvoice()){
					//处理发票
					row.getCell("directInvoiceAmt").setValue(null);
				}
			}
		}

		if (editData.getPaymentBill().getActPayLocAmt() != null) {
			setFirstLine();
		}
//		if (editData.getQualityAmount() != null) {
//			setQuaAmt();
//		}
	}


	protected String getEditUIName() {
		return com.kingdee.eas.fdc.finance.client.PaymentSplitEditUI.class.getName();
	}

	protected com.kingdee.eas.framework.ICoreBase getBizInterface()
			throws Exception {
		return PaymentSplitFactory.getRemoteInstance();
	}

	/**
	 * output getDetailTable method
	 */
	protected KDTable getDetailTable() {
		return this.kdtEntrys;
	}

	/**
	 * output createNewDetailData method
	 */
	protected IObjectValue createNewDetailData(KDTable table) {
		return new PaymentSplitEntryInfo();
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see com.kingdee.eas.fdc.basedata.client.FDCBillEditUI#createNewData()
	 */
	protected IObjectValue createNewData() {
		PaymentSplitInfo objectValue = new PaymentSplitInfo();
		// objectValue.setCompany((com.kingdee.eas.basedata.org.CompanyOrgUnitInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentFIUnit()));
		objectValue.setCreator((com.kingdee.eas.common.client.SysContext
				.getSysContext().getCurrentUserInfo()));
		objectValue.setIsInvalid(false);
		
		try {
			
			if(isSimpleFinancial()){
				if(isSimpleFinancialExtend()){
					if(!"预付款".equals(paymentBillInfo.getFdcPayType().getName())){
						objectValue.setCompletePrjAmt(paymentBillInfo.getProjectPriceInContract());
					}
//					objectValue.setAmount(costBillInfo.getProjectPriceInContract());
					objectValue.setPayAmount(paymentBillInfo.getProjectPriceInContract());
				}else{
					objectValue.setPayAmount(paymentBillInfo.getLocalAmt());
					objectValue.setCompletePrjAmt(paymentBillInfo.getLocalAmt());
				}
				
			}else if(isFinacial()){
				//调整模式付款金额=合同内工程款
				//红冲模式也=合同内工程款  2009-5-16
				objectValue.setPayAmount(paymentBillInfo.getProjectPriceInContract());
				paymentBillInfo.setActPayAmt(paymentBillInfo.getProjectPriceInContract());
			} else{
				//未启用简单或复杂模式取实付款 2009-05-20 茂业要求
				objectValue.setPayAmount(paymentBillInfo.getProjectPriceInContract());
			}
			
			if ((!isSimpleFinancial() || isSimpleInvoice() || isInvoiceMgr() ) && paymentBillInfo.getFdcPayReqID() != null){
				/*
				 * 如果多张付款单,那么成本归属只要第一张的:是否已存在拆分的付款单
				 * 第一张付款单应该是最先生成的那张，不能随便取；以为完工及发票都放在第一张上面，如果随便取得话，将会导致单据跟拆分的不一致
				 * 
				 */
				boolean isClosed = true ;	//应该指第一张单据
/*
				filter.getFilterItems().add(new FilterItemInfo("isColsed",Boolean.TRUE));
				filter.getFilterItems().add(new FilterItemInfo("isInvalid",Boolean.FALSE));
				filter.getFilterItems().add(new FilterItemInfo("paymentBill.fdcPayReqID",costBillInfo.getFdcPayReqID()));
				
*/
				//by tim_gao 为什么要根据第一张取
				FDCSQLBuilder builder=new FDCSQLBuilder();
				//判断是否还有比当前付款单更早的了,如果存在则表明当前单据不是第一张
				builder.appendSql("select 1 from T_CAS_PaymentBill a,T_CAS_PaymentBill b where a.ffdcPayReqID=b.ffdcPayReqID and b.fid=? and a.fcreateTime<b.fcreateTime");
				builder.addParam(paymentBillInfo.getId().toString());
				if (builder.isExist()) {
					isClosed = false ;
				}
				PayRequestBillInfo payReqInfo =null;
				if(isClosed){
					SelectorItemCollection selectorReq = new SelectorItemCollection();
					selectorReq.add("id");
					selectorReq.add("completePrjAmt");
					selectorReq.add(COL_INVOICE_AMT);
					payReqInfo = PayRequestBillFactory.getRemoteInstance().getPayRequestBillInfo(new ObjectUuidPK(BOSUuid.read(paymentBillInfo.getFdcPayReqID())), selectorReq);
				}
				
				//设置发票金额
				//R110702-0041：发票金额取数发生变更，之前取付款申请的数据，现在取付款单的数据
//					objectValue.setInvoiceAmt(payReqInfo.getInvoiceAmt());
				SelectorItemCollection selectorReq = new SelectorItemCollection();
				selectorReq.add("id");
				selectorReq.add(COL_INVOICE_AMT);
				EntityViewInfo view = new EntityViewInfo();
				FilterInfo filter = new FilterInfo();
				filter.getFilterItems().add(new FilterItemInfo("paymentBill.id", paymentBillInfo.getId()));
				view.setFilter(filter);
				view.setSelector(selectorReq);
				FDCPaymentBillCollection fdcPaymentCol = FDCPaymentBillFactory.getRemoteInstance().getFDCPaymentBillCollection(view);
				if (fdcPaymentCol != null && fdcPaymentCol.size() > 0) {
					FDCPaymentBillInfo fdcPaymentInfo = fdcPaymentCol.get(0);
					objectValue.setInvoiceAmt(fdcPaymentInfo.getInvoiceAmt());
				} else {
					objectValue.setInvoiceAmt(FDCHelper.ZERO);
				}
				objectValue.setIsColsed(isClosed);
				
				//非简单模式时
				if(true){
					BigDecimal completePrjAmt = FDCConstants.ZERO;
					//结算款
					if (paymentBillInfo.getFdcPayType().getPayType().getId()
							.toString().equals(PaymentTypeInfo.settlementID)) {
	
						handleSettlePayment(objectValue, paymentBillInfo,isClosed);
					}
					//保修款
					else if (paymentBillInfo.getFdcPayType().getPayType().getId()
							.toString().equals(PaymentTypeInfo.keepID)) {
						objectValue.setIsProgress(false);
						objectValue.setCompletePrjAmt(null);
						txtCompletePrjAmt.setValue(null);
						txtAmount.setValue(FDCHelper.toBigDecimal(paymentBillInfo.getActPayLocAmt(), 2));
	//					objectValue.setPayAmount(costBillInfo.getActPayAmt());
					}				
					//进度款
					else {
						if (isClosed && payReqInfo.getCompletePrjAmt() != null) {
							completePrjAmt = FDCHelper.toBigDecimal(payReqInfo.getCompletePrjAmt(), 2);
						}
						txtCompletePrjAmt.setValue(completePrjAmt);
						objectValue.setCompletePrjAmt(completePrjAmt);
						
						txtAmount.setValue(FDCHelper.toBigDecimal(paymentBillInfo.getActPayLocAmt(), 2));
	//					objectValue.setPayAmount(costBillInfo.getActPayAmt());
					}
				}
			}

		} catch (Exception e) {
			handUIExceptionAndAbort(e);
		}
		objectValue.setPaymentBill(paymentBillInfo);
		if(paymentBillInfo.getCurProject()!=null)
			objectValue.setCurProject(paymentBillInfo.getCurProject());
		txtCostBillNumber.setText(paymentBillInfo.getNumber());
		// txtCostBillName.setText(costBillInfo());
		// txtAmount.setValue(costBillInfo.getAmount());

		setContractBillId(paymentBillInfo.getContractBillId());
		
		//by tim_gao要根据合同ID 来得合同类型做判断
		
		try {
			ContractBillInfo tempCon = getContractRelationInfo(paymentBillInfo.getContractBillId());
			this.isLoadWorkConfirm = tempCon.getContractType().isIsWorkLoadConfirm();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.handleException(e);
		}
		
		objectValue.setIsConWithoutText(false);
		objectValue.setFivouchered(false);
		
		//修改拆分的小数位，以免由于小数位导致拆分不能完全，影响范围全部拆分,拆分实际只支持两位小数 by zhiyuan_tang 2010/11/29
		objectValue.setCompletePrjAmt(FDCHelper.toBigDecimal(objectValue.getCompletePrjAmt(), 2));
		objectValue.setPayAmount(FDCHelper.toBigDecimal(objectValue.getPayAmount(), 2));
		objectValue.setInvoiceAmt(FDCHelper.toBigDecimal(objectValue.getInvoiceAmt(), 2));
		return objectValue;
	}

	/**
	 * 描述：获取付款单和付款申请单
	 * 
	 * @param paymentBillId
	 * @return
	 * @author rd_skyiter_wang
	 * @createDate 2014-12-30
	 */
	private void fetchPaymentBillAndPayRequestBill(String paymentBillId) {
		paymentBillInfo = null;
		payRequestBillInfo = null;

		// ///////////////////////////////////////////////////////////////////////////
		if (null == paymentBillId) {
			return;
		}
		SelectorItemCollection selectors = new SelectorItemCollection();
		selectors.add("id");
		selectors.add("number");
		selectors.add("name");
		selectors.add(COL_AMOUT);
		selectors.add("localAmt");//取本币
		selectors.add("projectPriceInContract");
		selectors.add("contractBillId");
		selectors.add("fdcPayReqID");
		selectors.add("fdcPayType.payType.id");
		selectors.add("fdcPayType.number");
		selectors.add("fdcPayType.name");
		selectors.add("curProject.id");
		selectors.add("payerAccount.id");
		selectors.add("payerAccount.isBank");
		selectors.add("payerAccount.isCash");
		selectors.add("payerAccount.isCashEquivalent");
		selectors.add("company.id");
		selectors.add("actPayAmt");
		selectors.add("actPayLocAmt");
		selectors.add("curProject.id");
		selectors.add("curProject.longNumber");
		try {
			paymentBillInfo = PaymentBillFactory.getRemoteInstance().getPaymentBillInfo(new ObjectUuidPK(BOSUuid.read(paymentBillId)), selectors);
		} catch (Exception e) {
			handUIExceptionAndAbort(e);
		}

		// ///////////////////////////////////////////////////////////////////////////
		if (null == paymentBillInfo) {
			return;
		}
		String fdcPayReqID = paymentBillInfo.getFdcPayReqID();
		selectors = new SelectorItemCollection();
		selectors.add("id");
		selectors.add("number");
		selectors.add("completePrjAmt");
		try {
			payRequestBillInfo = PayRequestBillFactory.getRemoteInstance().getPayRequestBillInfo(
					new ObjectUuidPK(BOSUuid.read(fdcPayReqID)), selectors);
		} catch (Exception e) {
			handUIExceptionAndAbort(e);
		}
	}

	/**
	 * 处理结算款
	 * @param objectValue
	 * @param costBillInfo
	 * @param isClosed
	 * @throws BOSException
	 * @throws EASBizException
	 * @throws Exception
	 * by sxhong
	 */
	private void handleSettlePayment(PaymentSplitInfo objectValue,
			PaymentBillInfo costBillInfo, boolean isClosed) throws BOSException, EASBizException,
			Exception {
		BigDecimal completePrjAmt = FDCHelper.ZERO;
		FilterInfo filterPay = new FilterInfo();
		filterPay.getFilterItems().add(new FilterItemInfo("paymentBill.contractBillId", costBillInfo.getContractBillId()));
		filterPay.getFilterItems().add(new FilterItemInfo("state", FDCBillStateEnum.INVALID_VALUE, CompareType.NOTEQUALS));
		filterPay.getFilterItems().add(new FilterItemInfo("paymentBill.fdcPayType.payType.id", PaymentTypeInfo.settlementID));
		filterPay.getFilterItems().add(new FilterItemInfo("isProgress", Boolean.TRUE));
		if (getBizInterface().exists(filterPay)) {
			objectValue.setIsProgress(false);
			objectValue.setCompletePrjAmt(null);
			objectValue.setPayAmount(costBillInfo.getProjectPriceInContract());
		} else
			handleFirstSettlePayment(objectValue, costBillInfo, isClosed);
	}

	/**
	 * 第一笔结算款处理
	 * TODO 此方法后续要移到服务器端
	 * @param objectValue
	 * @param costBillInfo
	 * @param isClosed
	 * @throws BOSException
	 * @throws EASBizException
	 * by sxhong
	 */
	private void handleFirstSettlePayment(PaymentSplitInfo objectValue,
			PaymentBillInfo costBillInfo, boolean isClosed) throws BOSException, EASBizException {
		{
			BigDecimal completePrjAmt = FDCHelper.ZERO;//已完工工程量
			BigDecimal progressTotal=FDCHelper.ZERO;//进度款已完工之和
			BigDecimal progressAmtTotal=FDCHelper.ZERO;//进度款已付款之和
			SelectorItemCollection selector = new SelectorItemCollection();
			selector.add(new SelectorItemInfo("id"));
			selector.add(new SelectorItemInfo("hasSettled"));
			selector.add(new SelectorItemInfo("isCoseSplit"));
			selector.add(new SelectorItemInfo("settleAmt"));
			ContractBillInfo conInfo = ContractBillFactory.getRemoteInstance().getContractBillInfo(
					new ObjectUuidPK(BOSUuid.read(costBillInfo.getContractBillId())), selector);

			if (conInfo.isHasSettled() && conInfo.getSettleAmt() != null) {
				BigDecimal amountSplit = FDCHelper.ZERO;
				amountSplit = amountSplit.setScale(2);
				EntityViewInfo viewSplit = new EntityViewInfo();
				FilterInfo filterSplit = new FilterInfo();
				filterSplit.getFilterItems().add(new FilterItemInfo("paymentBill.contractBillId", costBillInfo.getContractBillId()));
				filterSplit.getFilterItems().add(new FilterItemInfo("paymentBill.fdcPayType.payType.id", PaymentTypeInfo.progressID));
				if (isFinacial() && !isAdjustVourcherModel()) {
					filterSplit.getFilterItems().add(new FilterItemInfo("Fivouchered", Boolean.TRUE));
				}
				filterSplit.getFilterItems().add(new FilterItemInfo("isInvalid", Boolean.TRUE, CompareType.NOTEQUALS));
				viewSplit.setFilter(filterSplit);
				viewSplit.getSelector().add("id");
				viewSplit.getSelector().add("payAmount");
				viewSplit.getSelector().add("completePrjAmt");
				viewSplit.getSelector().add("paymentBill.actPayAmt");
				viewSplit.getSelector().add("paymentBill.actPayLocAmt");
				viewSplit.getSelector().add("paymentBill.company.id");
				viewSplit.getSelector().add("paymentBill.payerAccount.*");
				viewSplit.getSelector().add("paymentBill.payerAccountBank.*");
				viewSplit.getSelector().add("paymentBill.currency.*");
				viewSplit.getSelector().add("paymentBill.exchangeRate");
				viewSplit.getSelector().add("paymentBill.fdcPayReqID");

				PaymentSplitCollection splitColl = PaymentSplitFactory
						.getRemoteInstance().getPaymentSplitCollection(
								viewSplit);
				PaymentSplitInfo splitInfo = new PaymentSplitInfo();
				int sizeSplit = splitColl.size();
				for (int i = 0; i < sizeSplit; i++) {
					splitInfo = splitColl.get(i);
					PaySplit4VoucherInfo forEntry = new PaySplit4VoucherInfo();
					forEntry.setParent(objectValue);
					forEntry.setPaymentBill(splitInfo.getPaymentBill());
					forEntry.setAccountView(splitInfo.getPaymentBill()
							.getPayerAccount());
					forEntry.setBankAccount(splitInfo.getPaymentBill()
							.getPayerAccountBank());
					forEntry.setCurrency(splitInfo.getPaymentBill()
							.getCurrency());
					forEntry.setAmount(splitInfo.getPaymentBill()
							.getActPayLocAmt());
					forEntry.setExchangeRate(splitInfo.getPaymentBill()
							.getExchangeRate());
					if (splitInfo.getPaymentBill() != null
							&& splitInfo.getPaymentBill().getPayerAccount() != null
							&& (splitInfo.getPaymentBill().getPayerAccount()
									.isIsBank()
									|| splitInfo.getPaymentBill()
											.getPayerAccount().isIsCash() || splitInfo
									.getPaymentBill().getPayerAccount()
									.isIsCashEquivalent())) {
						forEntry.setIsNeedTransit(true);
						forEntry.setTransitAccount(FDCUtils
								.getDefaultFDCParamAccount(null, splitInfo
										.getPaymentBill().getCompany().getId()
										.toString()));
					}
					//TODO 后续改成批量获取
					SelectorItemCollection payReqSelector=new SelectorItemCollection();
					payReqSelector.add("id");
					payReqSelector.add("costAmount");
					String payReqID = splitInfo.getPaymentBill()
							.getFdcPayReqID();
					PayRequestBillInfo request = PayRequestBillFactory
							.getRemoteInstance().getPayRequestBillInfo(
									new ObjectUuidPK(BOSUuid.read(payReqID)),payReqSelector);
					forEntry.setPayRequestBill(request);

					//
					forEntry.setCostAmount(request.getCostAmount());
					// forEntry.setCostAmount(FDCConstants.z);

					objectValue.getVoucherEntrys().add(forEntry);
					if (splitInfo.getPayAmount() != null)
						amountSplit = amountSplit.add(splitInfo.getPayAmount());
					//计算进度款已完工之和
					progressTotal=FDCHelper.add(progressTotal, splitInfo.getCompletePrjAmt());
					//计算进度款已付款之和
					progressAmtTotal=FDCHelper.add(progressAmtTotal, splitInfo.getPayAmount());
				}
				
				if(isAdjustVourcherModel()){
					txtAmount.setValue(FDCHelper.toBigDecimal(costBillInfo.getProjectPriceInContract(), 2));
					objectValue.setPayAmount(costBillInfo.getProjectPriceInContract());
				}else if(isFinacial()){
					//TODO 红冲的时候应该所有实付款这和
					txtAmount.setValue(FDCHelper.toBigDecimal(FDCNumberHelper.add(costBillInfo.getActPayLocAmt(),progressAmtTotal), 2));
					objectValue.setPayAmount(FDCNumberHelper.add(costBillInfo.getActPayLocAmt(),progressAmtTotal));
				}else{
					//未启用简单或复杂模式取实付款 2009-05-20 
					objectValue.setPayAmount(costBillInfo.getProjectPriceInContract());
				}
				// 如果多张付款单,那么成本归属只要第一张的

				if (isClosed) {
					completePrjAmt = conInfo.getSettleAmt();
					if (isAdjustVourcherModel() || (!isFinacial())) {// 支持简单模式:
						// 否则已拆分大于成本拆分无法保存
						// if(isAdjustVourcherModel() ||
						// (!isSimpleFinancial()&&!isFinacial())){
						//已完工工程量＝结算价－进度款已完工工程量之和
						completePrjAmt=FDCNumberHelper.subtract(completePrjAmt,progressTotal);
						objectValue.put("firstSettCompletePrjAmt", completePrjAmt);
					}
				}
				completePrjAmt = FDCHelper.toBigDecimal(completePrjAmt);
				txtCompletePrjAmt.setValue(completePrjAmt);
				objectValue.setCompletePrjAmt(completePrjAmt);

				// 质保金
				EntityViewInfo viewSett = new EntityViewInfo();
				FilterInfo filterSett = new FilterInfo();
				filterSett.getFilterItems().add(
						new FilterItemInfo("contractBill.id", costBillInfo
								.getContractBillId()));
				filterSett.getFilterItems().add(
						new FilterItemInfo("isFinalSettle", Boolean.TRUE,
								CompareType.EQUALS));
				viewSett.setFilter(filterSett);
				viewSett.getSelector().add("id");
				viewSett.getSelector().add("qualityGuarante");
				ContractSettlementBillCollection settColl = ContractSettlementBillFactory
						.getRemoteInstance()
						.getContractSettlementBillCollection(viewSett);
				if (settColl.size() == 1) {
					ContractSettlementBillInfo settInfo = settColl.get(0);
					// if (settInfo.getQualityGuarante() != null)
					objectValue.setQualityAmount(FDCHelper
							.toBigDecimal(settInfo.getQualityGuarante()));
				}

				objectValue.setIsProgress(true);
			}
		}
		if(isWorkLoadSeparate()){
			objectValue.setIsProgress(false);
		}
	}

	public void onLoad() throws Exception {
		super.onLoad();
		setSplitBillType(CostSplitBillTypeEnum.PAYMENTSPLIT);
		getDetailTable().getColumn("directAmt").setEditor(
				FDCSplitClientHelper.getCellNumberEdit());
		FDCHelper.formatTableNumber(getDetailTable(), "directPayedAmt");
		if (STATUS_ADDNEW.equals(getOprtState())) {
			if (editData != null
					&& editData.getPaymentBill() != null
					&& editData.getPaymentBill().getCurProject() != null
					&& editData.getPaymentBill().getCurProject().getId() != null) {
				String prjID = editData.getPaymentBill().getCurProject()
						.getId().toString();
				ProjectPeriodStatusInfo prjStatusInfo = getPrjPeriodStatus(prjID);
				if (isIncorporation && prjStatusInfo!=null && prjStatusInfo.isIsCostEnd()
						&& !prjStatusInfo.isIsFinacialEnd()) {
					importCostSplit(prjID);
				} else {
					importCostSplist();
				} 
			} else {
				importCostSplist();
			}
			// importCostSplit();
		//使用之前要将值同步到editData
			storeFields();
			setAdd();
			setChange();
			if (editData.getPaymentBill() != null
					&& editData.getPaymentBill().getFdcPayType() != null
					&& editData.getPaymentBill().getFdcPayType().getPayType() != null
					&& editData.getPaymentBill().getFdcPayType().getPayType()
							.getId().toString().equals(PaymentTypeInfo.keepID))
					setQuality();
//					setAmtDisplay();
		} else if(editData!=null&&!editData.getState().equals(FDCBillStateEnum.INVALID)
				//编辑状态才重算 by sxhong 2008/12/14
				&&STATUS_EDIT.equals(getOprtState())){
				setAdd();
				setQuality();
		}
		setAmtDisplay();
		setFirstLine();
		setOprtState(getOprtState());
		/*
		 * 项目资金计划 - modified by zhaoqin on 2013/10/15
		if (isWorkLoadSeparate()) {
			txtCompletePrjAmt.setValue(FDCHelper.ZERO);
		}*/
		if(STATUS_ADDNEW.equals(getOprtState())){
			setOneEntryAmt(txtAmount.getBigDecimalValue());
		}
		//非编辑状态时时自动计算已拆分发票金额;
		calInvoiceAmt();
		calPayedAmt();
		
		txtAmount.setValue(this.editData.getPayAmount());
		
		// /////////////////////////////////////////////////////////////////////////
		// 处理值_工程量确认流程与付款流程分离参数（参数FDC317）
		dealWithValue4WorkLoadSeparate();
		// /////////////////////////////////////////////////////////////////////////
		
	}

	/**
	 * 描述：处理值_工程量确认流程与付款流程分离参数（参数FDC317）
	 * 
	 * @throws BOSException
	 * @throws SQLException
	 * @author rd_skyiter_wang
	 * @createDate 2014-12-29
	 */
	private void dealWithValue4WorkLoadSeparate() throws BOSException, SQLException {
		// start==================
		// R141107-0203 by skyiter_wang 2014-12-29 start
		// 取得已拆分成本金额_工程量确认流程与付款流程分离参数（参数FDC317）
		Map amountMap = getSplitedCostAmt4WorkLoadSeparate();
		/*
		 * modified by zhaojie for BT870197 on 2015/03/27
		 */
		/* ===========start=========== */
		// 获取当前合同的合同类型的“工程量确认”选项是否被勾选
		try {
			ContractBillInfo tempCon = getContractRelationInfo(paymentBillInfo.getContractBillId());
			this.isLoadWorkConfirm = tempCon.getContractType().isIsWorkLoadConfirm();
		} catch (Exception e) {
			handleException(e);
		}
		/*
		 * modified by zhaojie for BT870197 on 2015/03/31
		 */
		// 根据上次考虑的情况，一种情况需要（表格.归属成本金额=表头.本期成本金额 * 按照比例拆分的金额）
		// 而上次去的是合同付款拆分分录上的“拆分比例”列对应数据，后面经过查看发现该列被隐藏掉，值为空
		// 所以拆分比例取值有问题，因此在这儿获取合同拆分的拆分比例
		/* start=================== */
		EntityViewInfo view = new EntityViewInfo();
		view.getSelector().add("entrys.splitScale");
		FilterInfo filter = new FilterInfo();
		filter.getFilterItems().add(new FilterItemInfo("contractBill.id", getContractBillId()));
		view.setFilter(filter);
		ContractCostSplitCollection cColl = ContractCostSplitFactory.getRemoteInstance()
				.getContractCostSplitCollection(view);
		/* end===================== */
		// 将公用的表格部分抽取出来，与该BUG没有关系
		// 表格
		KDTable table = getDetailTable();
		int costAccountIdColIndex = table.getColumnIndex("costAccount.id");
		// 表格.已拆分成本金额
		int splitedCostAmtColIndex = table.getColumnIndex("splitedCostAmt");
		// 表格.归属成本金额
		int amountColIndex = table.getColumnIndex("amount");
		// 表格.归属付款金额
		int payedAmttColIndex = table.getColumnIndex("payedAmt");
		/**
		 * 一、参数317”分离“为”是”，合同类型中“工程量”勾选： 付款拆分单据 1、表头.本期成本金额=0 2、表头.已拆分成本金额=0 3、表格.已拆分成本金额=SUM（工程量拆分单据.表格.归属成本金额）
		 * 4、表格.归属成本金额=0
		 * 
		 * 三、参数317”分离“为”否“，合同类型中“工程量”勾选： 付款拆分单据 1、表头.本期成本金额=付款申请单.表头.本期完工工程量 2、表头.已拆分成本金额=表格.已拆分成本金额
		 * 3、表格.已拆分成本金额=SUM（付款拆分单据.表格.归属成本金额），过滤条件：当前单据之前 4、表格.归属成本金额=表头.本期成本金额 按照比例拆分的金额（提交时会校验要求两者相等）
		 * 
		 */
		// 合同类型中“工程量”勾选
		if (isLoadWorkConfirm) {
			// 参数317”分离“为”是”
			if (isWorkLoadSeparate()) {
				txtCompletePrjAmt.setValue(null);
				txtSplitedAmount.setValue(null);
				for (int i = 0, rowCount = table.getRowCount(); i < rowCount; i++) {
					table.getCell(i, amountColIndex).setValue(null);

					Object costAccountId = table.getCell(i, costAccountIdColIndex).getValue();
					if (null == costAccountId) {
						continue;
					}
					BigDecimal splitedCostAmt = (BigDecimal) amountMap.get(costAccountId.toString());
					table.getCell(i, splitedCostAmtColIndex).setValue(splitedCostAmt);
				}
			}
			// 参数317”分离“为”否“
			else {
				if (null != payRequestBillInfo) {
					txtCompletePrjAmt.setValue(payRequestBillInfo.getCompletePrjAmt());
				}
				if (FDCTableHelper.isNotEmpty(table)) {
					calAmtTotal("splitedCostAmt");
					calAmtTotal("amount");
					IRow row = getDetailTable().getRow(0);
					BigDecimal splitedCostAmt = FDCHelper.toBigDecimal(row.getCell("splitedCostAmt").getValue(), 2);
					txtSplitedAmount.setValue(splitedCostAmt);
				}
				for (int i = 0, rowCount = table.getRowCount(); i < rowCount; i++) {
					/*
					 * modified by zhaojie for BT870197 on 2015/03/31
					 */
					/* start=================== */
					// table.getCell(i, amountColIndex).setValue(
					// FDCHelper.multiply(txtCompletePrjAmt.getBigDecimalValue(), table.getCell(i, "splitScale")
					// .getValue(), 2));
					if (i != 0 && cColl.get(0).getEntrys().get(i-1) != null) {
						table.getCell(i, amountColIndex).setValue(
								FDCHelper.divide(FDCHelper.multiply(txtCompletePrjAmt.getBigDecimalValue(), cColl
										.get(0).getEntrys().get(i - 1).getSplitScale(), 2), 100, 2));
					}
					/* end===================== */
					Object costAccountId = table.getCell(i, costAccountIdColIndex).getValue();
					if (null == costAccountId) {
						continue;
					}
					BigDecimal splitedCostAmt = (BigDecimal) amountMap.get(costAccountId.toString());
					table.getCell(i, splitedCostAmtColIndex).setValue(splitedCostAmt);
				}
			}
		}
		/**
		 * 二、参数317”分离“为”是”，合同类型中“工程量”不勾选： 付款拆分单据 1、表头.本期成本金额=0 2、表头.已拆分成本金额=0 3、表格.已拆分成本金额=0 4、表格.归属成本金额=0
		 * 
		 * 四、参数317”分离“为”否“，合同类型中“工程量”不勾选： 付款拆分单据 1、表头.本期成本金额=0 2、表头.已拆分成本金额=0 3、表格.已拆分成本金额=0 4、表格.归属成本金额=0
		 * 
		 */
		// 合同类型中“工程量”不勾选
		else {
			// 表头
			txtCompletePrjAmt.setValue(null);
			txtSplitedAmount.setValue(null);
			for (int i = 0, rowCount = table.getRowCount(); i < rowCount; i++) {
				table.getCell(i, amountColIndex).setValue(null);
				table.getCell(i, splitedCostAmtColIndex).setValue(null);
			}
		}
		/* ===========start=========== */
		// end==================
	}

	/**
	 * 描述：取得已拆分成本金额_工程量确认流程与付款流程分离参数（参数FDC317）
	 * 
	 * @return
	 * @throws BOSException
	 * @throws SQLException
	 * @author rd_skyiter_wang
	 * @createDate 2014-12-29
	 */
	private Map getSplitedCostAmt4WorkLoadSeparate() throws BOSException, SQLException {
		Map amountMap = new LinkedHashMap();
		
		StringBuffer sqlSb = new StringBuffer();
		
		// 工程量拆分单据.表格.归属成本金额
		if (isWorkLoadSeparate()) {
			sqlSb.append("	SELECT tfncPaySE.FCostAccountID, SUM(tfncPaySE.FAmount) FAmount	\r\n");
			sqlSb.append("	  FROM T_FNC_WorkLoadConfirmBill tfncWorLCB	\r\n");
			sqlSb.append("	 INNER JOIN T_FNC_PaymentSplit tfncPayS	\r\n");
			sqlSb.append("	    ON tfncPayS.FWorkLoadBillID = tfncWorLCB.FID	\r\n");
			sqlSb.append("	   AND tfncPayS.FIsInvalid = 0	\r\n");
			sqlSb.append("	 INNER JOIN T_FNC_PaymentSplitEntry tfncPaySE	\r\n");
			sqlSb.append("	    ON tfncPaySE.FParentID = tfncPayS.FID	\r\n");
			sqlSb.append("	   AND tfncPaySE.FLevel <> -1000	\r\n");
			sqlSb.append("	 WHERE tfncWorLCB.FContractBillId = ?	\r\n");
			sqlSb.append("	   AND tfncWorLCB.FState = '4AUDITTED'	\r\n");
			sqlSb.append("	   AND ((tfncPayS.FState IS NULL) OR (tfncPayS.FState <> '9INVALID'))	\r\n");
			sqlSb.append("	   AND tfncPayS.FCreateTime < ?	\r\n");
			sqlSb.append("	   AND tfncPaySE.FCostAccountID IS NOT NULL	\r\n");
			sqlSb.append("	 GROUP BY tfncPaySE.FCostAccountID	\r\n");
		}
		// 付款拆分单据.表格.归属成本金额
		else {
			sqlSb.append("	SELECT tfncPaySE.FCostAccountID, SUM(tfncPaySE.FAmount) FAmount	\r\n");
			sqlSb.append("	  FROM T_CAS_PaymentBill tcasPayB	\r\n");
			sqlSb.append("	 INNER JOIN T_FNC_PaymentSplit tfncPayS	\r\n");
			sqlSb.append("	    ON tfncPayS.FPaymentBillID = tcasPayB.FID	\r\n");
			sqlSb.append("	   AND tfncPayS.FIsInvalid = 0	\r\n");
			sqlSb.append("	 INNER JOIN T_FNC_PaymentSplitEntry tfncPaySE	\r\n");
			sqlSb.append("	    ON tfncPaySE.FParentID = tfncPayS.FID	\r\n");
			sqlSb.append("	   AND tfncPaySE.FLevel <> -1000	\r\n");
			sqlSb.append("	 WHERE tcasPayB.FContractBillId = ?	\r\n");
			sqlSb.append("	   AND tcasPayB.FBillStatus >= 12	\r\n");
			sqlSb.append("	   AND tfncPayS.FCreateTime < ?	\r\n");
			sqlSb.append("	   AND tfncPaySE.FCostAccountID IS NOT NULL	\r\n");
			sqlSb.append("	 GROUP BY tfncPaySE.FCostAccountID	\r\n");
		}
		
		String sql = sqlSb.toString();

		String contractId = this.getContractBillId();
		Timestamp createTime = this.editData.getCreateTime();
		if (null == createTime && null != serverDate) {
			createTime = new Timestamp(serverDate.getTime());
			this.editData.setCreateTime(createTime);
		}
		Object[] paramArr = new Object[] { contractId, createTime };
		List paramList = Arrays.asList(paramArr);
		IRowSet rs = FDCSQLFacadeFactory.getRemoteInstance().executeQuery(sql, paramList);
		while (rs.next()) {
			String costAccountId = rs.getString("FCostAccountID");
			BigDecimal amount = rs.getBigDecimal("FAmount");

			amountMap.put(costAccountId, amount);
		}

		// //////////////////////////////////////////////////////////////////////////
		// //////////////////////////////////////////////////////////////////////////

		logger.info("PaymentSplitEditUI.getSplitedCostAmt4WorkLoadSeparate()----------------------->, start");
		logger.info("sql : " + sql);
		logger.info("paramList : " + paramList);
		logger.info("amountMap : " + amountMap);
		MapUtils.debugPrint(System.out, "amountMap", amountMap);
		logger.info("PaymentSplitEditUI.getSplitedCostAmt4WorkLoadSeparate()----------------------->, end");

		// //////////////////////////////////////////////////////////////////////////
		// //////////////////////////////////////////////////////////////////////////
		
		return amountMap;
	}

	/**
	 * 描述：校验_工程量确认流程与付款流程分离参数（参数FDC317）
	 * 
	 * @author rd_skyiter_wang
	 * @createDate 2014-12-29
	 */
	private void check4WorkLoadSeparate() {
		// start==================
		// R141107-0203 by skyiter_wang 2014-12-29 start

		if (FDCTableHelper.isEmpty(getDetailTable())) {
			return;
		}
		IRow row = getDetailTable().getRow(0);
		BigDecimal splitedPayedAmt = FDCHelper.toBigDecimal(row.getCell("splitedPayedAmt").getValue(), 2);
		BigDecimal payedAmt = FDCHelper.toBigDecimal(row.getCell("payedAmt").getValue(), 2);
		BigDecimal splitedCostAmt = FDCHelper.toBigDecimal(row.getCell("splitedCostAmt").getValue(), 2);
		BigDecimal amount = FDCHelper.toBigDecimal(row.getCell("amount").getValue(), 2);

		BigDecimal allSplitedPayedAmt = FDCNumberHelper.add(splitedPayedAmt, payedAmt);
		BigDecimal allSplitedCostAmt = FDCNumberHelper.add(splitedCostAmt, amount);

		/*
		 * modified by zhaojie for BT870197 on 2015/03/27
		 */
		/* ===========start============== */
		/**
		 * 一、参数317”分离“为”是”，合同类型中“工程量”勾选： 校验： 1、增加校验：截止本期已拆分付款金额（表格.已拆分付款金额 + 表格.归属付款金额） <= 截止本期已拆分成本金额（表格.已拆分成本金额 +
		 * 表格.归属成本金额）。 ***预付款无需要进行校验。*** (业务说明：做付款拆分之前，必须先做工程量拆分)
		 * 
		 * 
		 * 三、参数317”分离“为”否“，合同类型中“工程量”勾选： 1、增加校验：截止本期已拆分付款金额（表格.已拆分付款金额 + 表格.归属付款金额） <= 截止本期已拆分成本金额（表格.已拆分成本金额 +
		 * 表格.归属成本金额）。 ***预付款无需要进行校验。*** (业务说明：做付款拆分之前，必须先做工程量拆分)
		 */
		// “预付款”不需遵守此校验
		/*
		 * if (!"预付款".equals(paymentBillInfo.getFdcPayType().getName())) { // 合同类型中“工程量”勾选时，不需校验参数317 if
		 * (isLoadWorkConfirm) { // 校验：截止本期已拆分付款金额（表格.已拆分付款金额 + 表格.归属付款金额） <= 截止本期已拆分成本金额（表格.已拆分成本金额 + 表格.归属成本金额）。 if
		 * (FDCNumberHelper.isGreaterThan(allSplitedPayedAmt, allSplitedCostAmt)) { FDCMsgBox.showWarning(this,
		 * "已拆分付款金额不能大于已拆分成本金额！"); SysUtil.abort(); } }
		 */
			// /**
			// * <pre>
			// * 二、参数317”分离“为”否“：
			// * 付款拆分单据
			// * 1、表头.本期成本金额=付款申请单.表头.本期完工工程量
			// * 2、表头.已拆分成本金额=表格.已拆分成本金额
			// * 3、表格.已拆分成本金额=SUM（付款拆分单据.表格.归属成本金额），过滤条件：当前单据之前
			// * 4、表格.归属成本金额=表格.归属付款金额
			// *
			// * 校验：
			// * 1、校验：截止本期已拆分付款金额（表格.已拆分付款金额 + 表格.归属付款金额） &lt;= 截止本期已拆分成本金额（表格.已拆分成本金额 + 表格.归属成本金额）。
			// (业务说明：做付款拆分之前，必须先做工程量拆分)
			// * </pre>
			// */
			// else {
			// // // 校验：截止本期已拆分付款金额（表格.已拆分付款金额 + 表格.归属付款金额） = 截止本期已拆分成本金额（表格.已拆分成本金额 + 表格.归属成本金额）。
			// // if (!FDCNumberHelper.isEqual(allSplitedPayedAmt, allSplitedCostAmt)) {
			// // FDCMsgBox.showWarning(this, "已拆分付款金额必须等于已拆分成本金额！");
			// // SysUtil.abort();
			// // }
			// // 未了兼容历史数据，校验改为小于或等于
			// // 校验：截止本期已拆分付款金额（表格.已拆分付款金额 + 表格.归属付款金额） <= 截止本期已拆分成本金额（表格.已拆分成本金额 + 表格.归属成本金额）。
			// if (FDCNumberHelper.isGreaterThan(allSplitedPayedAmt, allSplitedCostAmt)) {
			// FDCMsgBox.showWarning(this, "已拆分付款金额不能大于已拆分成本金额！");
			// SysUtil.abort();
			// }
			// }
		// }
		/* ===========end============== */
		// end==================
	}

		/**
	 * @description 根据工程量计算已拆分金额splitedCostAmt
	 * @author 陈伟
	 * @createDate 2011-11-17
	 * @version EAS7.0
	 * @throws BOSException
	 * @see
	 */
	private void updateAccAimt() throws BOSException {
		EntityViewInfo view = new EntityViewInfo();
		view.getSelector().add("*");
		view.getSelector().add("entrys.*");
		view.getSelector().add("entrys.costAccount.*");
		FilterInfo filter = new FilterInfo();
		filter.getFilterItems().add(
				new FilterItemInfo("contractBill.id", getContractBillId()));
		view.setFilter(filter);		
		SorterItemCollection sorter = new SorterItemCollection();
		SorterItemInfo seq = new SorterItemInfo("createTime");
		seq.setSortType(SortType.ASCEND);
		sorter.add(seq);
		view.setSorter(sorter);
		PaymentSplitCollection pColl = PaymentSplitFactory.getRemoteInstance()
				.getPaymentSplitCollection(view);
		Map amt = new HashMap();
		BigDecimal costAmtSum = FDCHelper.ZERO;
		BigDecimal contractAmt = FDCHelper.ZERO;
		BigDecimal changeAmt = FDCHelper.ZERO;
		BigDecimal costAmts = FDCHelper.ZERO;
		if (pColl != null && pColl.size() > 0) {
			PaymentSplitInfo info = pColl.get(0);
			PaymentSplitEntryInfo entryInfo = null;
			for (int i = 0; i < info.getEntrys().size(); i++) {
				entryInfo = info.getEntrys().get(i);
				// 汇总行数据
				BigDecimal splitedCostAmt = entryInfo.getAmount();
				if (entryInfo.getLevel() == -1000) {
			     amt.put("sum", splitedCostAmt);
			     contractAmt = entryInfo.getContractAmt();
			     changeAmt = entryInfo.getChangeAmt();
			     costAmts = entryInfo.getCostAmt();

				} else {
					String number = entryInfo.getCostAccount().getLongNumber();
					number = number.replace('!', '.');
					amt.put(number, splitedCostAmt);
					costAmtSum = FDCHelper.add(costAmtSum, splitedCostAmt);
				}
			}

		}
		IRow row = null;
		BigDecimal sumPayedAmt = FDCHelper.ZERO;
		
		for (int i = 1; i < this.kdtEntrys.getRowCount(); i++) {
             
			row = kdtEntrys.getRow(i);
			String number = (String) row.getCell("costAccount.number").getValue();
			 
			if (amt.get(number) != null) {
				BigDecimal costAmt = (BigDecimal) amt.get(number);
				
				row.getCell("splitedCostAmt").setValue(costAmt);
			}
			sumPayedAmt = FDCHelper.add(row.getCell(COL_PAYED_AMT).getValue(), sumPayedAmt);
			// contractAmt
			
		}
		
		row = kdtEntrys.getRow(0);
		row.getCell("splitedCostAmt").setValue(costAmtSum);
		row.getCell("contractAmt").setValue(contractAmt);
		row.getCell("changeAmt").setValue(changeAmt);
		row.getCell("costAmt").setValue(costAmts);
		// row.getCell("splitedCostAmt").setValue(changeAmt);
	    // row.getCell("payedAmt").setValue(sumPayedAmt);
		if (payAmount == null) {
			payAmount = this.editData.getPayAmount();
		}
//		txtAmount.setValue(FDCHelper.subtract(payAmount, sumPayedAmt));

	}
	/**
     * 描述：针对一个科目的情况增加自动填入变更金额的功能
     * 后续可能会抽象到基类中支持所有拆分
     * 
     * @param shouldSplitAmt:应拆金额
     */
	private void setOneEntryAmt(BigDecimal shouldSplitAmt) throws Exception{
		if(kdtEntrys.getRowCount()==2 ){//存在合计行
			actionAutoMatchSplit_actionPerformed(null);
		}else if(!isLimitCost()){
			for(int i=0;i<getDetailTable().getRowCount();i++){
				PaymentSplitEntryInfo entryInfo = (PaymentSplitEntryInfo)getDetailTable().getRow(i).getUserObject();
				if(entryInfo!=null){
					if(entryInfo.isIsLeaf()){
						final IRow row = kdtEntrys.getRow(i);
						entryInfo.setAmount(txtCompletePrjAmt.getBigDecimalValue());
						row.getCell(COL_AMOUT).setValue(txtCompletePrjAmt.getBigDecimalValue());
						entryInfo.setInvoiceAmt(txtInvoiceAmt.getBigDecimalValue());
						row.getCell(COL_INVOICE_AMT).setValue(txtInvoiceAmt.getBigDecimalValue());
						entryInfo.setPayedAmt(shouldSplitAmt);
						row.getCell(COL_PAYED_AMT).setValue(shouldSplitAmt);
						//调用方法汇总
//						txtSplitedAmount.setValue(txtCompletePrjAmt.getBigDecimalValue());
//						txtSplitInvoiceAmt.setValue(txtInvoiceAmt.getBigDecimalValue());
						return;
					}else{
						continue;
					}
				}
			}
		}
	}
	
	private void setQuality() throws Exception {
		if (getDetailTable().getRowCount() > 0) {
			for (int i = 0; i < getDetailTable().getRowCount(); i++) {
				Object obj = getDetailTable().getRow(i).getUserObject();
				if ((obj instanceof PaymentSplitEntryInfo)
						&& ((PaymentSplitEntryInfo) obj).getLevel() > -1) {
					PaymentSplitEntryInfo entry = (PaymentSplitEntryInfo) obj;
					String acc = entry.getCostAccount().getId().toString();
					
					EntityViewInfo viewPay = new EntityViewInfo();
					FilterInfo filterPay = new FilterInfo();
					filterPay.getFilterItems().add(
							new FilterItemInfo("costAccount", acc));
					if (entry.getCostBillId() != null) {
						String costId = entry.getCostBillId().toString();
						filterPay.getFilterItems().add(new FilterItemInfo("costBillId", costId));
					}
					filterPay.getFilterItems().add(
							new FilterItemInfo("Parent.isRedVouchered",
									Boolean.TRUE, CompareType.NOTEQUALS));
					filterPay.getFilterItems().add(
							new FilterItemInfo("Parent.state",
									FDCBillStateEnum.INVALID_VALUE,
									CompareType.NOTEQUALS));
					filterPay.getFilterItems().add(
							new FilterItemInfo(
									"Parent.paymentBill.fdcPayType.payType.id",
									PaymentTypeInfo.keepID));
					if (editData.getId() != null) {
						filterPay.getFilterItems().add(
								new FilterItemInfo("Parent.id", editData
										.getId().toString(),
										CompareType.NOTEQUALS));
					}
					if (entry.getProduct() != null) {
						String product = entry.getProduct().getId().toString();
						filterPay.getFilterItems().add(
								new FilterItemInfo("product.id", product));
					} else {
						filterPay.getFilterItems().add(
								new FilterItemInfo("product.id", null));
					}
					viewPay.setFilter(filterPay);
					viewPay.getSelector().add(COL_AMOUT);
					viewPay.getSelector().add(COL_PAYED_AMT);
					PaymentSplitEntryInfo itempay = null;
					BigDecimal payedQuality = FDCHelper.ZERO;
					PaymentSplitEntryCollection collpay = PaymentSplitEntryFactory
							.getRemoteInstance()
							.getPaymentSplitEntryCollection(viewPay);
					for (Iterator iter = collpay.iterator(); iter.hasNext();) {
						itempay = (PaymentSplitEntryInfo) iter.next();
						if (itempay.getPayedAmt() != null) {
							payedQuality = payedQuality.add(itempay
									.getPayedAmt());
						}
					}
					entry.setSplitQualityAmt(payedQuality);
					getDetailTable().getRow(i).getCell("splitedQualityAmt")
							.setValue(payedQuality);
				}
			}
		}
	}

	// 在引入合同和变更的时候，修改对应的列属性。
	private void setChange() {
		if (getDetailTable().getRowCount() > 0) {
			for (int i = 0; i < getDetailTable().getRowCount(); i++) {
				Object obj = getDetailTable().getRow(i).getUserObject();
				if ((obj instanceof PaymentSplitEntryInfo)
						&& ((PaymentSplitEntryInfo) obj).getLevel() > -1) {
					PaymentSplitEntryInfo entry = (PaymentSplitEntryInfo) obj;
					if ((entry.getSplitType() != null)
							&& (entry.getSplitType()
									.equals(CostSplitTypeEnum.BOTUPSPLIT))) {
						// by sxhong 2009-04-24 09:26:54 不能这样设置,这样设置会导致结算前与结算后的无法匹配
/*						entry.setSplitType(CostSplitTypeEnum.PROJSPLIT);
						getDetailTable().getRow(i).getCell("splitType")
								.setValue(CostSplitTypeEnum.PROJSPLIT);*/
					}
					if (entry.getCostAmt() != null) {
						entry.setApportionValue(entry.getCostAmt());
						entry.setApportionValueTotal(entry.getCostAmt());
						entry.setOtherRatioTotal(entry.getCostAmt());
						getDetailTable().getRow(i).getCell("apportionValue")
								.setValue(entry.getCostAmt());
						getDetailTable().getRow(i).getCell(
								"apportionValueTotal").setValue(
								entry.getCostAmt());
						getDetailTable().getRow(i).getCell("otherRatioTotal")
								.setValue(entry.getCostAmt());
					} else {
						entry.setApportionValue(FDCHelper.ZERO);
						entry.setApportionValueTotal(FDCHelper.ZERO);
						entry.setOtherRatioTotal(FDCHelper.ZERO);
						getDetailTable().getRow(i).getCell("apportionValue")
								.setValue(FDCHelper.ZERO);
						getDetailTable().getRow(i).getCell(
								"apportionValueTotal").setValue(FDCHelper.ZERO);
						getDetailTable().getRow(i).getCell("otherRatioTotal")
								.setValue(FDCHelper.ZERO);
					}
					if (entry.isIsAddlAccount()) {
						// 不知道为什么以前会设置成false,那样的话附加科目的界面显示就会有问题,表现在
						// 会在附加科目栏内显示工程项目及科目的编码
						entry.setIsAddlAccount(false);
						entry.setIsAddlAccount(true);
					}
				}
			}
		}
	}

	// 改变了导入方式,暂时取消
	private void importCostSplist() {
		//by tim_gao 在一下方法的处理是将原来的多次循环分录都写到MAP里,
		//然后在一块在loadCostSplitBatch中处理
		importContractCostSplist();
		importChangeCostSplist();
		
		//by tim_gao
		EntityViewInfo view = new EntityViewInfo();
		String filterField=null;
		FilterInfo filter = new FilterInfo();	
    	SelectorItemCollection sic=view.getSelector();  
    	try {
			loadCostSplitBatch(false,setSelectorsEntry(sic,true));
		} catch (EASBizException e) {
			// TODO Auto-generated catch block
			handleException(e);
			logger.warn("***************************加载分录信息失败");
			SysUtil.abort();
		} catch (BOSException e) {
			// TODO Auto-generated catch block
			handleException(e);
			logger.warn("***************************加载分录信息失败");
			SysUtil.abort();
		}

		setDisplay();
		setAmtDisplay();
	}

	/**
	 * TODO 此种方法引用在在成本未反月结的时候可行，但是如果成本也反月结了的话就不行的
	 * 看看以后什么时候一起改了吧  by sxhong 2009-06-03 09:44:43
	 * @param prjID
	 * @throws BOSException
	 * @throws EASBizException
	 */
	private void importCostSplit(String prjID) throws BOSException,
			EASBizException {
		PeriodInfo currentPeriod = FDCUtils.getCurrentPeriod(null, prjID, true);
		if(currentPeriod!=null){
			PeriodInfo usedPeriod = PeriodUtils.getPrePeriodInfo(null,
					currentPeriod);
			importContractCostSplit(prjID, usedPeriod.getId().toString());
			importChangeCostSplit(prjID, usedPeriod.getId().toString());
			
			//by tim_gao
			EntityViewInfo view = new EntityViewInfo();
			String filterField=null;
			FilterInfo filter = new FilterInfo();	
	    	SelectorItemCollection sic=view.getSelector();  
	    	try {
				loadCostSplitBatch(false,setSelectorsEntry(sic,true));
			} catch (EASBizException e) {
				// TODO Auto-generated catch block
				handleException(e);
				logger.warn("***************************加载分录信息失败");
				SysUtil.abort();
			} catch (BOSException e) {
				// TODO Auto-generated catch block
				handleException(e);
				logger.warn("***************************加载分录信息失败");
				SysUtil.abort();
			}
			
			setDisplay();
			setAmtDisplay();
		}
	}

	private void importContractCostSplit(String prjID, String periodID)
			throws BOSException, EASBizException {
		ContractCostSplitInfo info = new ContractCostSplitInfo();
		Set idListCon = new HashSet();
		EntityViewInfo contractView = new EntityViewInfo();
		FilterInfo contractFilter = new FilterInfo();
		contractFilter.getFilterItems().add(new FilterItemInfo("entityID", info.getBOSType().toString()));
		contractFilter.getFilterItems().add(new FilterItemInfo("contractID", getContractBillId()));
		contractFilter.getFilterItems().add(new FilterItemInfo("settlePeriod", periodID));
		// contractFilter.getFilterItems().add(
		// new FilterItemInfo("curProjectID", prjID));
		contractView.setFilter(contractFilter);
		SettledMonthlyCollection monthColl = SettledMonthlyFactory.getRemoteInstance().getSettledMonthlyCollection(contractView);
		for (Iterator iter = monthColl.iterator(); iter.hasNext();) {
			SettledMonthlyInfo item = (SettledMonthlyInfo) iter.next();
			idListCon.add(item.getObjectID().toString());
		}

		if (idListCon.size() == 0) {
			MsgBox.showError(this, "成本月结时此付款单对应的合同未拆分!");
			SysUtil.abort();
		}

		contractView = new EntityViewInfo();
		contractFilter = new FilterInfo();
		contractFilter.getFilterItems().add(new FilterItemInfo("id", idListCon, CompareType.INCLUDE));
		contractView.setFilter(contractFilter);
		contractView.getSelector().add("id");
		contractView.getSelector().add("contractBill.id");
		ContractCostSplitCollection contractCollection = ContractCostSplitFactory.getRemoteInstance().getContractCostSplitCollection(contractView);
		//优化位置一 by tim_gao
		
//		for (Iterator iter = contractCollection.iterator(); iter.hasNext();) {
//			ContractCostSplitInfo changeInfo = (ContractCostSplitInfo) iter.next();
//			loadCostSplit(CostSplitBillTypeEnum.CONTRACTSPLIT, getCostSplitEntryCollection(CostSplitBillTypeEnum.CONTRACTSPLIT, changeInfo.getId(), changeInfo.getContractBill().getId()));
//		}
		Set costIDS = new HashSet();
		for (Iterator iter = contractCollection.iterator(); iter.hasNext();) {
			ContractCostSplitInfo changeInfo = (ContractCostSplitInfo) iter.next();
			costIDS.add(changeInfo.getId().toString());
		}
		this.addSplitIdsMap(CostSplitBillTypeEnum.CONTRACTSPLIT, costIDS);
	}

	private void importChangeCostSplit(String prjID, String periodID)
			throws BOSException, EASBizException {
		
		Set idListCon = new HashSet();
		ConChangeSplitInfo info = new ConChangeSplitInfo();
		EntityViewInfo contractView = new EntityViewInfo();
		FilterInfo contractFilter = new FilterInfo();
		contractFilter.getFilterItems().add(
				new FilterItemInfo("entityID", info.getBOSType().toString()));
		contractFilter.getFilterItems().add(
				new FilterItemInfo("contractID", getContractBillId()));
		contractFilter.getFilterItems().add(
				new FilterItemInfo("settlePeriod", periodID));
//		contractFilter.getFilterItems().add(
//				new FilterItemInfo("curProjectID", prjID));
		contractView.setFilter(contractFilter);
		SettledMonthlyCollection monthColl = SettledMonthlyFactory
				.getRemoteInstance().getSettledMonthlyCollection(contractView);

		for (Iterator iter = monthColl.iterator(); iter.hasNext();) {
			SettledMonthlyInfo item = (SettledMonthlyInfo) iter.next();
			idListCon.add(item.getObjectID().toString());
		}

		if (idListCon.size() > 0) {
			contractView = new EntityViewInfo();
			contractFilter = new FilterInfo();
			contractFilter.getFilterItems().add(
					new FilterItemInfo("id", idListCon, CompareType.INCLUDE));
			contractView.setFilter(contractFilter);
			contractView.getSelector().add("id");
			contractView.getSelector().add("contractChange.id");
			ConChangeSplitCollection changeColl = ConChangeSplitFactory
					.getRemoteInstance().getConChangeSplitCollection(
							contractView);
			//优化4 by tim_gao
//			for (Iterator iter = changeColl.iterator(); iter.hasNext();) {
//				ConChangeSplitInfo changeInfo = (ConChangeSplitInfo) iter
//						.next();
//				loadCostSplit(CostSplitBillTypeEnum.CNTRCHANGESPLIT,
//						getCostSplitEntryCollection(
//								CostSplitBillTypeEnum.CNTRCHANGESPLIT,
//								changeInfo.getId(), changeInfo
//										.getContractChange().getId()));
//			}
			//优化4 by tim_gao
			Set sets = new HashSet();
			for (Iterator iter = changeColl.iterator(); iter.hasNext();) {
				ConChangeSplitInfo changeInfo = (ConChangeSplitInfo) iter
						.next();
				sets.add(changeInfo.getId().toString());

			}
			
			this.addSplitIdsMap(CostSplitBillTypeEnum.CNTRCHANGESPLIT, sets);
		}
	}

	private ProjectPeriodStatusInfo getPrjPeriodStatus(String prjId)
			throws BOSException, EASBizException {
		ProjectPeriodStatusInfo prjInfo = new ProjectPeriodStatusInfo();
		EntityViewInfo viewPrj = new EntityViewInfo();
		FilterInfo filterPrj = new FilterInfo();
		filterPrj.appendFilterItem("project.id", prjId);
		viewPrj.setFilter(filterPrj);
		prjInfo = ProjectPeriodStatusFactory.getRemoteInstance()
				.getProjectPeriodStatusCollection(viewPrj).get(0);
		return prjInfo;
	}

	private void importContractCostSplist() {
//		loadCostSplit(CostSplitBillTypeEnum.CONTRACTSPLIT,
//				getCostSplitEntryCollection(
//						CostSplitBillTypeEnum.CONTRACTSPLIT, null));
		Set temp = new HashSet();
		temp.add(getContractBillId());
		this.addEnumContractIdsMap(CostSplitBillTypeEnum.CONTRACTSPLIT, temp);
	}

	protected void loadCostSplit(CostSplitBillTypeEnum costBillType,
			FDCSplitBillEntryCollection entrys) {
		//优化2by tim_gao
		IObjectValue entry = null;
		if (costBillType.equals(CostSplitBillTypeEnum.CONTRACTSPLIT)) {
			// contractAmt
			for (Iterator iter = entrys.iterator(); iter.hasNext();) {
				entry = (IObjectValue) iter.next();
				entry.put("contractAmt", entry.get(COL_AMOUT));
				entry.put("shouldPayAmt", entry.get(COL_AMOUT));
				// entry.put(COL_AMOUT, FDCHelper.ZERO);
				entry.put("costWorkLoad", FDCHelper.toBigDecimal(entry.get("workLoad")).signum()==0?null:entry.get("workLoad"));
				entry.put("price", FDCHelper.toBigDecimal(entry.get("price")).signum()==0?null:entry.get("price"));
				entry.put(COL_AMOUT, null);
				entry.put("workLoad", null);

			}
		} else if (costBillType.equals(CostSplitBillTypeEnum.CNTRCHANGESPLIT)) {
			// changeAmt
			for (Iterator iter = entrys.iterator(); iter.hasNext();) {
				entry = (IObjectValue) iter.next();
				
				entry.put("changeAmt", entry.get(COL_AMOUT));
				entry.put("shouldPayAmt", entry.get(COL_AMOUT));
				// entry.put(COL_AMOUT, FDCHelper.ZERO);
				entry.put("costWorkLoad", FDCHelper.toBigDecimal(entry.get("workLoad")).signum()==0?null:entry.get("workLoad"));
				entry.put("price", FDCHelper.toBigDecimal(entry.get("price")).signum()==0?null:entry.get("price"));
				entry.put(COL_AMOUT, null);
				entry.put("workLoad", null);

			}
		}
		super.loadCostSplit(entrys);
	}

	private void importChangeCostSplist() {
		// if(true) return;
		// loadCostSplit(getCostSplitEntryCollection(CostSplitBillTypeEnum.CONTRACTSPLIT));
		EntityViewInfo view = new EntityViewInfo();
		FilterInfo filter = new FilterInfo();
		filter.getFilterItems().add(
				new FilterItemInfo("contractChange.contractBill.id",
						getContractBillId()));
		filter.getFilterItems().add(
				new FilterItemInfo("state", FDCBillStateEnum.INVALID_VALUE,
						CompareType.NOTEQUALS));
		view.setFilter(filter);
		SelectorItemCollection sic = view.getSelector();
		sic.add(new SelectorItemInfo("contractChange.contractBill.id"));

		ConChangeSplitCollection coll = null;
		try {
			coll = ConChangeSplitFactory.getRemoteInstance()
					.getConChangeSplitCollection(view);
		} catch (BOSException e) {
			handleException(e);
		}

		if (coll == null || coll.size() == 0) {
			return;
		}
//优化位置 3 by tim_gao
//		for (Iterator iter = coll.iterator(); iter.hasNext();) {
//			ConChangeSplitInfo item = (ConChangeSplitInfo) iter.next();
//			loadCostSplit(CostSplitBillTypeEnum.CNTRCHANGESPLIT,
//					getCostSplitEntryCollection(
//							CostSplitBillTypeEnum.CNTRCHANGESPLIT, item
//									.getContractChange().getId()));
//		}
		Set setids = new HashSet();
		for (Iterator iter = coll.iterator(); iter.hasNext();) {
			ConChangeSplitInfo item = (ConChangeSplitInfo) iter.next();
			setids.add(item.getContractChange().getId().toString());
		}
		this.addEnumContractIdsMap(CostSplitBillTypeEnum.CNTRCHANGESPLIT, setids);
	}

	/**
	 * 
	 * 描述：返回编码控件（子类必须实现）
	 * 
	 * @return
	 * @author:liupd 创建时间：2006-10-13
	 *               <p>
	 */
	protected KDTextField getNumberCtrl() {
		return txtNumber;
	}

	public void setOprtState(String oprtType) {
		super.setOprtState(oprtType);
		if (STATUS_VIEW.equals(oprtType)) {
			if (editData != null && (!editData.isFivouchered())) {
				if (editData.getState().equals(FDCBillStateEnum.INVALID))
					actionVoucher.setVisible(false);
				else if (editData.getPaymentBill().getBillStatus().equals(
						BillStatusEnum.PAYED)) {
					actionVoucher.setVisible(true);
				} else
					actionVoucher.setVisible(false);
			} else
				actionVoucher.setVisible(false);
		} else {
			actionVoucher.setVisible(false);
		}
		if(isAdjustVourcherModel()){
			actionVoucher.setVisible(false);
		}
		/*
		 * if(!STATUS_ADDNEW.equals(oprtType)) {
		 * prmtcurProject.setEnabled(false); }
		 */
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see com.kingdee.eas.fdc.basedata.client.AbstractFDCSplitBillEditUI#loadFields()
	 */
	public void loadFields() {
		super.loadFields();
		setContractBillId(editData.getPaymentBill().getContractBillId());
		ContractBillInfo con = new ContractBillInfo();
		String conId = editData.getPaymentBill().getContractBillId();
		if (conId != null && conId.length() > 1) {
			try {
				if ((BOSUuid.read(conId).getType()).equals(con.getBOSType())) {
					ContractBillInfo contractBill = ContractBillFactory
							.getRemoteInstance().getContractBillInfo(
									new ObjectUuidPK(BOSUuid.read(conId)));
					if (contractBill != null) {
						this.txtCostBillName.setText(contractBill.getNumber());
						this.txtContrName.setText(contractBill.getName());
						isMeasure=contractBill.isIsMeasureContract();
					}
				}
			} catch (Exception e) {
				super.handUIException(e);
			}
		}
		setDisplay();
		//setAmtDisplay();
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see com.kingdee.eas.fdc.finance.client.AbstractPaymentSplitEditUI#getSelectors()
	 */
	public SelectorItemCollection getSelectors() {
		// return super.getSelectors();

		SelectorItemCollection sic = super.getSelectors();
		sic.add("paymentBill.actPayAmt");
		sic.add("paymentBill.contractBillId");
		sic.add("paymentBill.billStatus");
		sic.add("paymentBill.fdcPayType.payType.*");
		sic.add("paymentBill.fdcPayType.number");
		sic.add("paymentBill.fdcPayType.name");
		sic.add("paymentBill.actPayLocAmt");
		sic.add("state");
		sic.add("description");
		sic.add("isProgress");
		sic.add("Fivouchered");
		sic.add("paymentBill.payerAccount.id");
		sic.add("paymentBill.payerAccount.isBank");
		sic.add("paymentBill.payerAccount.isCash");
		sic.add("paymentBill.payerAccount.isCashEquivalent");
		sic.add("paymentBill.company.id");
//		sic.add("paymentBill.prjPayEntry.*");
		sic.add("curProject.id");
		sic.add("curProject.number");
		sic.add("curProject.name");
		
		sic.add("contractBill.id");
		sic.add(new SelectorItemInfo("contractBill.contractType.isWorkLoadConfirm"));
		
		sic.add("conWithoutText.id");
		sic.add("contractBaseData.id");
		sic.add("entrys.splitedInvoiceAmt");
//		sic.add("directPayedAmt");
		
		return setSelectors(sic);
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see com.kingdee.eas.fdc.basedata.client.FDCSplitBillEditUI#getSplitBillEntryClassName()
	 */
	protected String getSplitBillEntryClassName() {
		// return super.getSplitBillEntryClassName();

		return PaymentSplitEntryInfo.class.getName();
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see com.kingdee.eas.fdc.basedata.client.FDCSplitBillEditUI#initWorkButton()
	 */
	protected void initWorkButton() {
		super.initWorkButton();
		actionSplit.putValue(Action.SMALL_ICON, EASResource
				.getIcon("imgTbtn_showparent"));
		// this.btnSplit.setIcon(EASResource.getIcon("imgTbtn_showparent"));
		actionSplit.setEnabled(true);

		actionAutoMatchSplit.putValue(Action.SMALL_ICON, EASResource
				.getIcon("imgTbtn_split"));
		actionAutoMatchSplit.setEnabled(true);
		actionAutoMatchSplit.setVisible(true);
		
		actionsplitBaseOnProp.putValue(Action.SMALL_ICON, EASResource
				.getIcon("imgTbtn_split"));
		actionsplitBaseOnProp.setEnabled(true);
		actionsplitBaseOnProp.setVisible(true);

		btnImpContrSplit.setVisible(false);
		actionImpContrSplit.setVisible(false);
		actionImpContrSplit.setEnabled(false);

		actionSplitBotUp.setVisible(false);
		actionSplitProd.setVisible(false);
		actionSplitProj.setVisible(false);
		actionAcctSelect.setVisible(false);
		// actionAudit.setVisible(false);
		// actionUnAudit.setVisible(false);
		actionRemoveLine.setVisible(false);
		actionRemoveLine.setEnabled(false);
		actionAddLine.setVisible(false);
		actionAttachment.setVisible(false);

		actionViewContract.setEnabled(true);
		actionViewPayment.setEnabled(true);
		actionViewContract.putValue(Action.SMALL_ICON, EASResource
				.getIcon("imgTbtn_sequencecheck"));
		actionViewPayment.putValue(Action.SMALL_ICON, EASResource
				.getIcon("imgTbtn_linkviewlist"));

		actionSplitBotUp.setEnabled(false);
		actionSplitProj.setEnabled(false);
		actionAcctSelect.setEnabled(false);
		actionSplitProd.setEnabled(false);
		actionVoucher.setVisible(false);
		actionVoucher.setEnabled(false);
		
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see com.kingdee.eas.fdc.basedata.client.FDCSplitBillEditUI#actionImpContrSplit_actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionImpContrSplit_actionPerformed(ActionEvent e)
			throws Exception {
		// super.actionImpContrSplit_actionPerformed(e);
	}

	public void onShow() throws Exception {
		getDetailTable().getColumn("costWorkLoad").getStyleAttributes().setHided(!isMeasureContract());
		//by tim_gao 有关于工程量控制的初始化
		initParamOnLoadForWorkLoad();
		super.onShow();
		if (editData.getContractBill() != null && editData.getContractBill().getContractType() != null) {
			isLoadWorkConfirm = editData.getContractBill().getContractType().isIsWorkLoadConfirm();
		}
		// super里有一些操作所以必须super后，工程量与付款分离之后成本金额相关控制隐藏
		//handleSplitUI();
		if (isSplitBaseOnProp()) {
			actionAutoMatchSplit.setEnabled(false);
			actionAutoMatchSplit.setVisible(false);
		}
		setFirstLine();
		setHasDirectAmt();
		if (getOprtState().equals(OprtState.VIEW)) {
			actionRemove.setEnabled(false);
			actionSplit.setEnabled(false);
			actionAutoMatchSplit.setEnabled(false);
			actionsplitBaseOnProp.setEnabled(false);
		}
		getDetailTable().getColumn("costWorkLoad").getStyleAttributes().setLocked(true);
		getDetailTable().getColumn("workLoad").getStyleAttributes().setLocked(true);
		getDetailTable().getColumn("price").getStyleAttributes().setLocked(true);
		getDetailTable().getColumn("splitedQualityAmt").getStyleAttributes().setLocked(true);
		//归属成本金额不能录入     by jian_wen 2009.12.22
		getDetailTable().getColumn(COL_AMOUT).getStyleAttributes().setLocked(true);
		getDetailTable().getColumn(COL_AMOUT).getStyleAttributes().setBackground(
				FDCHelper.KDTABLE_TOTAL_BG_COLOR);
		
		//避免在未作更改时，直接退出提示”是否保存“，先storeFields()一下。
		if(!STATUS_VIEW.equals(this.getOprtState())){
			super.initOldData(this.editData);
		}
		// TODO setadd方法已经处理了，这里是？
		// updateAccAimt();

		// modified by zhaoqin for R130704-0262 on 2013/07/22 start
		// 飞鹰提单补单：付款拆分界面要求增加流程审批和套打功能，流程图查看、套打功能没有完成
		this.actionPrint.setEnabled(true);
		this.actionPrint.setVisible(true);
		this.btnPrint.setVisible(true);
		this.actionPrintPreview.setEnabled(true);
		this.actionPrintPreview.setVisible(true);
		this.btnWorkFlowG.setVisible(true);
		this.btnWorkFlowG.setEnabled(true);
		// modified by zhaoqin for R130704-0262 on 2013/07/22 end
	}

	/**
	 * 
	 * 合同类型上的 工程量确认 为工程类合同时选择
	 * 
	 * 如果 工程量确认 
	 *      显示成本相关列 
	 *      如果 发票 
	 *           显示发票相关列
	 *      否则 隐藏发票相关列
	 * 否则 
	 *     如果 发票
	 *     显示 发票相关列
	 * 
	 */
	private void handleSplitUI() {
		// 1.合同类型 工程量确认
		if (isLoadWorkConfirm) {
			// 1.1显示成本相关列
			contCostAmt.setVisible(true);
			getDetailTable().getColumn(COL_AMOUT).getStyleAttributes().setHided(false);
			getDetailTable().getColumn("directAmt").getStyleAttributes().setHided(false);
			getDetailTable().getColumn(COL_SPLITED_COST_AMT).getStyleAttributes().setHided(false);
			// 1.2启用发票参数
			if(isInvoiceMgr() || isSimpleInvoice()){
				contSplitInvoice.setVisible(true);
				// txtInvoiceAmt.setValue(FDCHelper.ZERO);
				// this.editData.setInvoiceAmt(FDCHelper.ZERO);
				contInvoice.setVisible(true);
				getDetailTable().getColumn(COL_INVOICE_AMT).getStyleAttributes().setHided(false);
				getDetailTable().getColumn(COL_SPLITED_INVOICE_AMT).getStyleAttributes().setHided(false);
				getDetailTable().getColumn("directInvoiceAmt").getStyleAttributes().setHided(false);
				// 先这么写着 ，候燕的组织参数问题, 列显示了 by hpw
				FDCHelper.formatTableNumber(getDetailTable(), COL_SPLITED_INVOICE_AMT);

			}else{
				// 1.2.1不启用发票，隐藏发票相关列,同时上移表
				txtInvoiceAmt.setValue(FDCHelper.ZERO);
				this.editData.setInvoiceAmt(FDCHelper.ZERO);
				contInvoice.setVisible(false);
				contSplitInvoice.setVisible(false);
				getDetailTable().getColumn(COL_INVOICE_AMT).getStyleAttributes().setHided(true);
				getDetailTable().getColumn(COL_SPLITED_INVOICE_AMT).getStyleAttributes().setHided(true);
				getDetailTable().getColumn("directInvoiceAmt").getStyleAttributes().setHided(true);
				
				
				int width = (int) leftRec.getWidth() * 2 / 3;
				int gap = (rightRec.x + rightRec.width - leftRec.x - 3 * width) / 2;
				Rectangle leftRecTmp = new Rectangle(leftRec.x, (int) leftRec.getY() + 24, width, (int) leftRec.getHeight());
				Rectangle middleRecTmp = new Rectangle(leftRec.x + width + gap, (int) rightRec.getY() + 24, width, (int) rightRec.getHeight());
				Rectangle rightRecTmp = new Rectangle(rightRec.x + rightRec.width - width, (int) rightRec.getY() + 24, width, (int) rightRec.getHeight());

				contCostAmt.setBounds(leftRecTmp);
				this.add(contCostAmt, new KDLayout.Constraints(KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE, leftRecTmp));
				kDLabelContainer5.setBounds(middleRecTmp);
				this.add(kDLabelContainer5, new KDLayout.Constraints(KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE, middleRecTmp));
				kDLabelContainer3.setBounds(rightRecTmp);
				this.add(kDLabelContainer3, new KDLayout.Constraints(KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT, rightRecTmp));
			}
			
			if (true) {// isLoadWorkConfirm==true强制
				
				int width = (int) leftRec.getWidth() * 2 / 3;
				int gap = (rightRec.x + rightRec.width - leftRec.x - 3 * width) / 2;
				Rectangle leftRecTmp = new Rectangle(leftRec.x, (int) leftRec.getY() + 24, width, (int) leftRec.getHeight());
				Rectangle middleRecTmp = new Rectangle(leftRec.x + width + gap, (int) rightRec.getY() + 24, width, (int) rightRec
						.getHeight());
				Rectangle rightRecTmp = new Rectangle(rightRec.x + rightRec.width - width, (int) rightRec.getY() + 24, width,
						(int) rightRec.getHeight());

				contCostAmt.setBounds(leftRecTmp);
				this.add(contCostAmt, new KDLayout.Constraints(KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT
						| KDLayout.Constraints.ANCHOR_RIGHT_SCALE, leftRecTmp));
				kDLabelContainer5.setBounds(middleRecTmp);
				this.add(kDLabelContainer5, new KDLayout.Constraints(KDLayout.Constraints.ANCHOR_TOP
						| KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE, middleRecTmp));
				kDLabelContainer3.setBounds(rightRecTmp);
				this.add(kDLabelContainer3, new KDLayout.Constraints(KDLayout.Constraints.ANCHOR_TOP
						| KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT, rightRecTmp));
				
				 contCostAmt.setVisible(false);
				this.kDLabelContainer5.setVisible(false);// 已拆分成本金额
				getDetailTable().getColumn(COL_AMOUT).getStyleAttributes().setHided(true);
				getDetailTable().getColumn("directAmt").getStyleAttributes().setHided(true);
				
				
				// 已拆分票上移
				Rectangle invoicedRec = contSplitInvoice.getBounds();
				Rectangle thirddRec = new Rectangle(leftRec.x + width + gap, (int) invoicedRec.getY() - 24, width, (int) invoicedRec
						.getHeight());
				contSplitInvoice.setBounds(thirddRec);
				this.add(contSplitInvoice, new KDLayout.Constraints(KDLayout.Constraints.ANCHOR_TOP
						| KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE
						| KDLayout.Constraints.ANCHOR_RIGHT, thirddRec));
				// 本期发票上移
				Rectangle invoiceRec = contInvoice.getBounds();
				Rectangle thirdRec = new Rectangle(invoiceRec.x, (int) invoiceRec.getY() - 24, width, (int) invoiceRec.getHeight());
				contInvoice.setBounds(thirdRec);
				this.add(contInvoice, new KDLayout.Constraints(KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT
						| KDLayout.Constraints.ANCHOR_RIGHT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT, thirdRec));
				
				
				// 表上移
				Rectangle tblRec = kdtEntrys.getBounds();
				// 不知道为何值太大超界了，用固定 值
				Rectangle topRec = new Rectangle(tblRec.x, (int) tblRec.getY() - 24, 993, 430);
				kdtEntrys.setBounds(topRec);
				this.add(kdtEntrys, new KDLayout.Constraints(KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM
						| KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT, topRec));
				
			}
			
		} else {
			// 2合同类型 工程量确认为否
			// 2.1隐藏成本相关列
			contCostAmt.setVisible(false);
			kDLabelContainer5.setVisible(false);
			getDetailTable().getColumn(COL_SPLITED_COST_AMT).getStyleAttributes().setHided(true);
			getDetailTable().getColumn("directAmt").getStyleAttributes().setHided(true);
			getDetailTable().getColumn(COL_AMOUT).getStyleAttributes().setHided(true);

			// 2.2上移表
			int width = (int) leftRec.getWidth() * 2 / 3;
			int gap = (rightRec.x + rightRec.width - leftRec.x - 3 * width) / 2;

			// 表上移
			Rectangle tblRec = kdtEntrys.getBounds();
			// 不知道为何值太大超界了，用固定 值
			Rectangle topRec = new Rectangle(tblRec.x, (int) tblRec.getY() - 24, 993, 430);
			kdtEntrys.setBounds(topRec);
			this.add(kdtEntrys, new KDLayout.Constraints(KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT,
					topRec));
			
			if (isInvoiceMgr() || isSimpleInvoice()) {
				// 隐藏成本列

				contSplitInvoice.setVisible(true);
				// this.editData.setInvoiceAmt(FDCHelper.ZERO);
				contInvoice.setVisible(true);
				getDetailTable().getColumn(COL_INVOICE_AMT).getStyleAttributes().setHided(false);
				getDetailTable().getColumn(COL_SPLITED_INVOICE_AMT).getStyleAttributes().setHided(false);
				getDetailTable().getColumn("directInvoiceAmt").getStyleAttributes().setHided(false);
				// 先这么写着 ，候燕的组织参数问题, 列显示了 by hpw
				FDCHelper.formatTableNumber(getDetailTable(), COL_SPLITED_INVOICE_AMT);
				
				width = (int) leftRec.getWidth() * 2 / 3;
				gap = (rightRec.x + rightRec.width - leftRec.x - 3 * width) / 2;
				
				// 已拆分票上移
				Rectangle invoicedRec = contSplitInvoice.getBounds();
				Rectangle thirddRec = new Rectangle(leftRec.x + width + gap, (int) invoicedRec.getY() - 24, width, (int) invoicedRec.getHeight());
				contSplitInvoice.setBounds(thirddRec);
				this.add(contSplitInvoice, new KDLayout.Constraints(KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE
						| KDLayout.Constraints.ANCHOR_RIGHT, thirddRec));
				// 本期发票上移
				Rectangle invoiceRec = contInvoice.getBounds();
				Rectangle thirdRec = new Rectangle(invoiceRec.x, (int) invoiceRec.getY() - 24, width, (int) invoiceRec.getHeight());
				contInvoice.setBounds(thirdRec);
				this.add(contInvoice, new KDLayout.Constraints(KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE
						| KDLayout.Constraints.ANCHOR_RIGHT, thirdRec));

				// 已拆发票、发票、付款同行，间距
				width = (int) leftRec.getWidth() * 2 / 3;
				gap = (rightRec.x + rightRec.width - leftRec.x - 3 * width) / 2;
				Rectangle leftRecTmp = new Rectangle(leftRec.x, (int) leftRec.getY() + 24, width, (int) leftRec.getHeight());
				Rectangle middleRecTmp = new Rectangle(leftRec.x + width + gap, (int) rightRec.getY() + 24, width, (int) rightRec.getHeight());
				Rectangle rightRecTmp = new Rectangle(rightRec.x + rightRec.width - width, (int) rightRec.getY() + 24, width, (int) rightRec.getHeight());

				contCostAmt.setBounds(leftRecTmp);
				this.add(contCostAmt, new KDLayout.Constraints(KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE, leftRecTmp));
				kDLabelContainer5.setBounds(middleRecTmp);
				this.add(kDLabelContainer5, new KDLayout.Constraints(KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE, middleRecTmp));
				kDLabelContainer3.setBounds(rightRecTmp);
				this.add(kDLabelContainer3, new KDLayout.Constraints(KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT, rightRecTmp));
										
				
			} else {
				txtInvoiceAmt.setValue(FDCHelper.ZERO);
				this.editData.setInvoiceAmt(FDCHelper.ZERO);
				contInvoice.setVisible(false);
				contSplitInvoice.setVisible(false);
				getDetailTable().getColumn(COL_INVOICE_AMT).getStyleAttributes().setHided(true);
				getDetailTable().getColumn(COL_SPLITED_INVOICE_AMT).getStyleAttributes().setHided(true);
				getDetailTable().getColumn("directInvoiceAmt").getStyleAttributes().setHided(true);
				
				// 两个控件 则跟上面的对齐,及X轴不变，Y轴向下移24个像素即可
				Rectangle leftRecTmp = new Rectangle((int) leftRec.getX(), (int) leftRec.getY() + 24, (int) leftRec.getWidth(), (int) leftRec.getHeight());
				this.kDLabelContainer3.setBounds(leftRecTmp);
				this.add(this.kDLabelContainer3, new KDLayout.Constraints(KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE, leftRecTmp));
				
			}
			
		}
		
		if (isSplitBaseOnProp()) {
			actionAutoMatchSplit.setEnabled(false);
			actionAutoMatchSplit.setVisible(false);
		}
		getDetailTable().getColumn("splitScale").getStyleAttributes().setHided(true);

	}

	private void setFirstLine() {
		if (getDetailTable().getRowCount() > 0) {
			IRow row = getDetailTable().getRow(0);
			Object obj = row.getUserObject();
			if (!(obj instanceof PaymentSplitEntryInfo)
					|| ((PaymentSplitEntryInfo) obj).getLevel() > -1) {
				// 没有总计行
				row = getDetailTable().addRow(0);
				PaymentSplitEntryInfo entry = new PaymentSplitEntryInfo();
				entry.setLevel(-1000);
				row.setUserObject(entry);
			}
			row.getCell(2).setValue(FDCSplitClientHelper.getRes("total"));// 合计
			// int
			// contractAmt_Index=getDetailTable().getColumnIndex("contractAmt");
			BigDecimal contractAmt = FDCHelper.ZERO;
			BigDecimal changeAmt = FDCHelper.ZERO;
			BigDecimal costAmt = FDCHelper.ZERO;
			BigDecimal shouldAmt = FDCHelper.ZERO;
			BigDecimal splitAmt = FDCHelper.ZERO;
			BigDecimal payedAmt = FDCHelper.ZERO;
			BigDecimal splitPayedAmt = FDCHelper.ZERO;
			BigDecimal splitQuaAmt = FDCHelper.ZERO;
			BigDecimal amount = FDCHelper.ZERO;

			BigDecimal invoiceAmt = FDCHelper.ZERO;
			BigDecimal splitedInvoiceAmt = FDCHelper.ZERO;
			
			BigDecimal workLoad = FDCHelper.ZERO;
			BigDecimal costWorkLoad = FDCHelper.ZERO;

			for (int i = 0; i < getDetailTable().getRowCount(); i++) {
				IRow tempRow = getDetailTable().getRow(i);
				obj = tempRow.getUserObject();
				if (obj instanceof PaymentSplitEntryInfo) {
					PaymentSplitEntryInfo entry = (PaymentSplitEntryInfo) obj;
					if (entry.getLevel() == 0) {
						if (entry.getContractAmt() instanceof BigDecimal) {
							contractAmt = contractAmt.add(entry.getContractAmt());
						}
						if (entry.getChangeAmt() instanceof BigDecimal) {
							changeAmt = changeAmt.add(entry.getChangeAmt());
						}
						if (tempRow.getCell(COL_COST_AMT).getValue() != null) {
							BigDecimal temp = toBigDecimal(tempRow.getCell(COL_COST_AMT).getValue());
							costAmt = costAmt.add(temp);
						}
						if (tempRow.getCell("splitedCostAmt").getValue() != null) {
							BigDecimal temp = toBigDecimal(tempRow.getCell("splitedCostAmt").getValue());
							splitAmt = splitAmt.add(temp);
						}
						if (tempRow.getCell(COL_PAYED_AMT).getValue() != null) {
							BigDecimal temp = toBigDecimal(tempRow.getCell(COL_PAYED_AMT).getValue());
							payedAmt = payedAmt.add(temp);
						}
						if (tempRow.getCell("splitedPayedAmt").getValue() != null) {
							BigDecimal temp = toBigDecimal(tempRow.getCell("splitedPayedAmt").getValue());
							splitPayedAmt = splitPayedAmt.add(temp);
						}
						//发票金额  by sxhong 2009-07-20 13:48:29
						invoiceAmt = FDCHelper.add(invoiceAmt, tempRow.getCell(COL_INVOICE_AMT).getValue());
						splitedInvoiceAmt = FDCHelper.add(splitedInvoiceAmt, tempRow.getCell(COL_SPLITED_INVOICE_AMT).getValue());
						
						if (tempRow.getCell("shouldPayAmt").getValue() != null) {
							BigDecimal temp = toBigDecimal(tempRow.getCell("shouldPayAmt").getValue());
							shouldAmt = shouldAmt.add(temp);
						}
						if (tempRow.getCell("splitedQualityAmt").getValue() != null) {
							BigDecimal temp = toBigDecimal(tempRow.getCell("splitedQualityAmt").getValue());
							splitQuaAmt = splitQuaAmt.add(temp);
						}
						if (tempRow.getCell(COL_AMOUT).getValue() != null) {
							BigDecimal temp = toBigDecimal(tempRow.getCell(COL_AMOUT).getValue());
							amount = amount.add(temp);
						}
						if (tempRow.getCell("costWorkLoad").getValue() != null) {
							BigDecimal temp = toBigDecimal(tempRow.getCell("costWorkLoad").getValue());
							costWorkLoad = costWorkLoad.add(temp);
						}
						if (tempRow.getCell("workLoad").getValue() != null) {
							BigDecimal temp = toBigDecimal(tempRow.getCell("workLoad").getValue());
							workLoad = workLoad.add(temp);
						}
					}
				}
			}
			row.getCell("contractAmt").setValue(contractAmt);
			row.getCell("changeAmt").setValue(changeAmt);
			row.getCell(COL_COST_AMT).setValue(costAmt);
			row.getCell("splitedCostAmt").setValue(splitAmt);
			row.getCell(COL_PAYED_AMT).setValue(payedAmt);
			row.getCell("splitedPayedAmt").setValue(splitPayedAmt);
			row.getCell("shouldPayAmt").setValue(shouldAmt);
			row.getCell("splitedQualityAmt").setValue(splitQuaAmt);
			row.getCell(COL_AMOUT).setValue(amount);
			row.getCell(COL_INVOICE_AMT).setValue(invoiceAmt);
			row.getCell(COL_SPLITED_INVOICE_AMT).setValue(splitedInvoiceAmt);
			row.getStyleAttributes().setLocked(true);
			row.getStyleAttributes().setBackground(
					FDCHelper.KDTABLE_TOTAL_BG_COLOR);
		}
	}

	private void setAdd() throws Exception {
		FilterInfo filterSett = new FilterInfo();
		filterSett.getFilterItems().add(
				new FilterItemInfo("settlementBill.contractBill.id", editData
						.getPaymentBill().getContractBillId()));
		filterSett.getFilterItems().add(
				new FilterItemInfo("settlementBill.isFinalSettle",
						Boolean.TRUE, CompareType.EQUALS));
		filterSett.getFilterItems().add(
				new FilterItemInfo("state", FDCBillStateEnum.INVALID_VALUE,
						CompareType.NOTEQUALS));
		boolean hasSettleSplit = SettlementCostSplitFactory.getRemoteInstance()
				.exists(filterSett);
		if (!hasSettleSplit) {//处理没有最终结算拆分包括：1.最终结算没有拆分；2.结算未最终结算.3.没有结算
			handleAdjustModelSplitedAmt();
			editData.setHasEffected(false);
		} else {
			editData.setHasEffected(true);
			//TODO 以后都应该调用handleAdjustModelSplitedAmt处理??? by hpw
			//1.简单模式与调整模式（默认启用）都调用handleAdjustModelSplitedAmt()
//			if(isAdjustVourcherModel()){
//				handleAdjustModelSplitedAmt();
//			}else{
//				//2.这种情况是复杂模式，禁用调整参数的处理
//				setSettle();
//			}
			//复杂且非调整第一笔结算已拆分成本金额为0 by hpw 2010-06-08 
			if(isFinacial()&&!isAdjustVourcherModel()){
				setSettle();
			}else{
				handleAdjustModelSplitedAmt();
			}
		}
	}
	
	/**
	 * 调整模式下处理结算拆分成本金额,已拆分成本金额,已拆分付款金额,已拆分保修款金额等
	 * 跟服务器一起使用公用的方法，以便维护
	 *  by sxhong 2009-07-21 16:59:43
	 * @throws Exception
	 */
	private void handleAdjustModelSplitedAmt() throws Exception{
		if(getDetailTable().getRowCount()<=0){
			return;
		}
		String contractId=getContractBillId();
		if(contractId==null){
			contractId=this.editData.getPaymentBill().getContractBillId();
		}
		if(contractId==null){
			return;
		}
		PaymentSplitHelper.handleCostAdjustModelSplitedAmt(null, this.editData, contractId);
		for(int i=0;i<getDetailTable().getRowCount();i++){
			IRow row=getDetailTable().getRow(i);
			Object obj = row.getUserObject();
			if (!(obj instanceof PaymentSplitEntryInfo) 
					|| ((PaymentSplitEntryInfo) obj).getLevel() <0) {
				continue;
			}
			PaymentSplitEntryInfo entry = (PaymentSplitEntryInfo) obj;

			row.getCell(COL_COST_AMT).setValue(entry.getCostAmt());
			row.getCell("shouldPayAmt").setValue(entry.getShouldPayAmt());
			row.getCell("splitedCostAmt").setValue(entry.getSplitedCostAmt());
			row.getCell("splitedPayedAmt").setValue(entry.getSplitedPayedAmt());
			row.getCell(COL_SPLITED_INVOICE_AMT).setValue(entry.getSplitedInvoiceAmt());
			//在界面进行值设置
			row.getCell("splitedQualityAmt").setValue(entry.getSplitQualityAmt());
			
		}

		
/*		注释掉，以后删除
		String contractId=getContractBillId();
		if(contractId==null){
			contractId=this.editData.getPaymentBill().getContractBillId();
		}
		if(contractId==null){
			return;
		}
		//结算拆分分录
		Map settleSplitEntryMap=new HashMap();
		//以前的付款拆分分录
		Map paySplitEntryMap=new HashMap();
		//已拆分的保修款付款拆分分录
		Map paySplitGrtEntryMap=new HashMap();
		
		EntityViewInfo view = new EntityViewInfo();
		FilterInfo filter = new FilterInfo();
		filter.getFilterItems().add(new FilterItemInfo("state", FDCBillStateEnum.INVALID_VALUE, CompareType.NOTEQUALS));
		filter.getFilterItems().add(new FilterItemInfo("settlementBill.isFinalSettle", Boolean.TRUE, CompareType.EQUALS));
		filter.getFilterItems().add(new FilterItemInfo("settlementBill.contractBill.id", contractId, CompareType.EQUALS));
		
		view.getSelector().add("entrys.amount");
		view.getSelector().add("entrys.grtSplitAmt");
		view.getSelector().add("entrys.grtSplitAmt");
		view.getSelector().add("entrys.product.id");
		view.getSelector().add("entrys.costAccount.id");
		view.getSelector().add("entrys.costBillId");
		view.getSelector().add("entrys.splitType");
		view.setFilter(filter);
		SettlementCostSplitCollection c=SettlementCostSplitFactory.getRemoteInstance().getSettlementCostSplitCollection(view);
		for(int i=0;i<c.size();i++){
			for (Iterator iter = c.get(i).getEntrys().iterator(); iter.hasNext();) {
				SettlementCostSplitEntryInfo entry = (SettlementCostSplitEntryInfo) iter.next();
				if(entry.getCostAccount()==null){
					continue;
				}
				String key=entry.getCostAccount().getId().toString();
				key=key+entry.getCostBillId().toString();
				if(entry.getSplitType()!=null){
					key=key+entry.getSplitType().getValue();
				}
				if(entry.getProduct()!=null){
					key=key+entry.getProduct().getId().toString();
				}
				settleSplitEntryMap.put(key, entry);
			}
			
		}
		//所有之前的付款拆分
		view=new EntityViewInfo();
		view.setFilter(new FilterInfo());
		view.getFilter().appendFilterItem("contractBill.id", contractId);
		view.getFilter().getFilterItems().add(new FilterItemInfo("state", FDCBillStateEnum.INVALID_VALUE, CompareType.NOTEQUALS));
		if(this.editData!=null&&this.editData.getId()!=null){
			view.getFilter().getFilterItems().add(new FilterItemInfo("id", this.editData.getId().toString(), CompareType.NOTEQUALS));
		}
		view.getSelector().add("entrys.amount");
		view.getSelector().add("entrys.payedAmt");
		view.getSelector().add("entrys.invoiceAmt");
		view.getSelector().add("entrys.product.id");
		view.getSelector().add("entrys.costAccount.id");
		view.getSelector().add("entrys.costBillId");
		view.getSelector().add("entrys.splitType");
		view.getSelector().add("entrys.parent.paymentBill.fdcPayType.payType.id");
		
		PaymentSplitCollection paymentSplitCollection = PaymentSplitFactory.getRemoteInstance().getPaymentSplitCollection(view);
		for(int i=0;i<paymentSplitCollection.size();i++){
			for (Iterator iter = paymentSplitCollection.get(i).getEntrys().iterator(); iter.hasNext();) {
				PaymentSplitEntryInfo entry = (PaymentSplitEntryInfo) iter.next();
				if(entry.getCostAccount()==null){
					continue;
				}
				String key=entry.getCostAccount().getId().toString();
				key=key+entry.getCostBillId().toString();
				if(entry.getSplitType()!=null){
					key=key+entry.getSplitType().getValue();
				}
				if(entry.getProduct()!=null){
					key=key+entry.getProduct().getId().toString();
				}
				PaymentSplitEntryInfo mapEntry=(PaymentSplitEntryInfo)paySplitEntryMap.get(key);
				if(mapEntry==null){
					mapEntry=new PaymentSplitEntryInfo();
					paySplitEntryMap.put(key, mapEntry);
				}
				mapEntry.setAmount(FDCHelper.add(mapEntry.getAmount(), entry.getAmount()));
				mapEntry.setPayedAmt(FDCHelper.add(mapEntry.getPayedAmt(), entry.getPayedAmt()));
				mapEntry.setInvoiceAmt(FDCHelper.add(mapEntry.getInvoiceAmt(), entry.getInvoiceAmt()));
				
				if(entry.getParent().getPaymentBill()!=null
						&&entry.getParent().getPaymentBill().getFdcPayType()!=null
						&&entry.getParent().getPaymentBill().getFdcPayType().getPayType()!=null
						&&entry.getParent().getPaymentBill().getFdcPayType().getPayType().getId().toString().equals(PaymentTypeInfo.keepID)){
//					PaymentSplitEntryInfo mapGrtEntry=(PaymentSplitEntryInfo)paySplitGrtEntryMap.get(key);
//					if(mapEntry==null){
//						mapGrtEntry=new PaymentSplitEntryInfo();
//						paySplitGrtEntryMap.put(key, mapGrtEntry);
//					}
					mapEntry.setSplitQualityAmt(FDCHelper.add(mapEntry.getSplitQualityAmt(), entry.getPayedAmt()));
				}
					
			}
		}
		
		for(int i=0;i<getDetailTable().getRowCount();i++){
			IRow row=getDetailTable().getRow(i);
			Object obj = row.getUserObject();
			if (!(obj instanceof PaymentSplitEntryInfo) 
					|| ((PaymentSplitEntryInfo) obj).getLevel() <0) {
				continue;
			}
			PaymentSplitEntryInfo entry = (PaymentSplitEntryInfo) obj;
			String key=entry.getCostAccount().getId().toString();
			key=key+entry.getCostBillId().toString();
			if(entry.getSplitType()!=null){
				key=key+entry.getSplitType().getValue();
			}
			if(entry.getProduct()!=null){
				key=key+entry.getProduct().getId().toString();
			}
			SettlementCostSplitEntryInfo settleEntry=(SettlementCostSplitEntryInfo)settleSplitEntryMap.get(key);
			PaymentSplitEntryInfo paySplitEntry=(PaymentSplitEntryInfo)paySplitEntryMap.get(key);
//			PaymentSplitEntryInfo paySplitGrtEntry=(PaymentSplitEntryInfo)paySplitGrtEntryMap.get(key);
			//成本拆分金额
			entry.setCostAmt(settleEntry!=null?settleEntry.getAmount():FDCHelper.ZERO);
			//保修款拆分金额
//			entry.setQualityAmount(settleEntry.getGrtSplitAmt());
			//应付进度款=成本拆分金额-保修款拆分金额
			entry.setShouldPayAmt(settleEntry!=null?FDCNumberHelper.subtract(settleEntry.getAmount(), settleEntry.getGrtSplitAmt()):FDCHelper.ZERO);
			
			//已拆分成本金额
			entry.setSplitedCostAmt(paySplitEntry!=null?paySplitEntry.getAmount():FDCHelper.ZERO);
			//已拆分付款金额实际为已拆分进度款金额不包括保修款部分所以要减去保修款的拆分金额
			entry.setSplitedPayedAmt(paySplitEntry!=null?FDCHelper.subtract(paySplitEntry.getPayedAmt(), paySplitEntry.getSplitQualityAmt()):FDCHelper.ZERO);
			entry.setSplitedInvoiceAmt(paySplitEntry!=null?paySplitEntry.getInvoiceAmt():FDCHelper.ZERO);
			//已拆分保修款还是用以前的逻辑,暂时不处理
//			entry.setSplitQualityAmt(paySplitGrtEntry.getSplitQualityAmt());
			if(this.editData.isIsProgress()){
				entry.setQualityAmount(settleEntry.getGrtSplitAmt());
			}
			//设置到界面
			row.getCell("costAmt").setValue(entry.getCostAmt());
			row.getCell("shouldPayAmt").setValue(entry.getShouldPayAmt());
			row.getCell("splitedCostAmt").setValue(entry.getSplitedCostAmt());
			row.getCell("splitedPayedAmt").setValue(entry.getSplitedPayedAmt());
			row.getCell("splitedInvoiceAmt").setValue(entry.getSplitedInvoiceAmt());
			//在界面进行值设置
			row.getCell("splitedQualityAmt").setValue(paySplitEntry!=null?paySplitEntry.getSplitQualityAmt():FDCHelper.ZERO);
			
		}*/
	}
	
	private void setSettle() throws Exception {
		if (getDetailTable().getRowCount() > 0) {
			for (int i = 0; i < getDetailTable().getRowCount(); i++) {
				Object obj = getDetailTable().getRow(i).getUserObject();
				if ((obj instanceof PaymentSplitEntryInfo)
						&& ((PaymentSplitEntryInfo) obj).getLevel() > -1) {
					PaymentSplitEntryInfo entry = (PaymentSplitEntryInfo) obj;
					String acc = entry.getCostAccount().getId().toString();
					String costId = entry.getCostBillId().toString();
					BigDecimal temp = FDCHelper.ZERO;
					BigDecimal tempShould = FDCHelper.ZERO;
					BigDecimal tempInvoice = FDCHelper.ZERO;
					BigDecimal temppay = FDCHelper.ZERO;
					BigDecimal tempPayed = FDCHelper.ZERO;
					SettlementCostSplitEntryCollection coll = null;
					SettlementCostSplitEntryInfo item = null;
					PaymentSplitEntryCollection collpay = null;
					PaymentSplitEntryInfo itempay = null;
					EntityViewInfo viewPay = new EntityViewInfo();
					FilterInfo filterPay = new FilterInfo();
					filterPay.getFilterItems().add(
							new FilterItemInfo("costAccount", acc));
					filterPay.getFilterItems().add(
							new FilterItemInfo("costBillId", costId));
					filterPay.getFilterItems().add(
							new FilterItemInfo("Parent.isRedVouchered",
									Boolean.TRUE, CompareType.NOTEQUALS));
					filterPay.getFilterItems().add(
							new FilterItemInfo("Parent.state",
									FDCBillStateEnum.INVALID_VALUE,
									CompareType.NOTEQUALS));
					if (editData.getId() != null) {
						filterPay.getFilterItems().add(
								new FilterItemInfo("Parent.id", editData
										.getId().toString(),
										CompareType.NOTEQUALS));
					}
					FilterInfo filterSettPayed = new FilterInfo();
					filterSettPayed.getFilterItems().add(
							new FilterItemInfo("paymentBill.contractBillId",
									editData.getPaymentBill()
											.getContractBillId()));
					filterSettPayed.getFilterItems().add(
							new FilterItemInfo("isProgress", Boolean.TRUE));
					filterSettPayed.getFilterItems().add(
							new FilterItemInfo("state",
									FDCBillStateEnum.INVALID_VALUE,
									CompareType.NOTEQUALS));
					boolean hasSettlePayed = getBizInterface().exists(
							filterSettPayed);
					if (hasSettlePayed) {
						filterPay
								.getFilterItems()
								.add(
										new FilterItemInfo(
												"Parent.paymentBill.fdcPayType.payType.id",
												PaymentTypeInfo.settlementID));
					}
					if (entry.getProduct() != null) {
						String product = entry.getProduct().getId().toString();
						filterPay.getFilterItems().add(
								new FilterItemInfo("product.id", product));
					} else {
						filterPay.getFilterItems().add(
								new FilterItemInfo("product.id", null));
					}
					if (entry.getSplitType() != null) {
						String standard = entry.getSplitType().getValue();
						filterPay.getFilterItems().add(
								new FilterItemInfo("splitType", standard));
					} else {
						filterPay.getFilterItems().add(
								new FilterItemInfo("splitType", null));
					}
					viewPay.getSelector().add(COL_AMOUT);
					viewPay.getSelector().add(COL_PAYED_AMT);
					viewPay.setFilter(filterPay);

					EntityViewInfo view = new EntityViewInfo();
					FilterInfo filter = new FilterInfo();
					filter.getFilterItems().add(
							new FilterItemInfo("costAccount", acc));
					filter.getFilterItems().add(
							new FilterItemInfo("costBillId", costId));
					filter.getFilterItems().add(
							new FilterItemInfo("parent.state",
									FDCBillStateEnum.INVALID_VALUE,
									CompareType.NOTEQUALS));
					//TODO 多次结算时需要单元测试
					filter.getFilterItems().add(
							new FilterItemInfo("parent.settlementBill.isFinalSettle",
									Boolean.TRUE,CompareType.EQUALS));
					if (entry.getProduct() != null) {
						String product = entry.getProduct().getId().toString();
						filter.getFilterItems().add(
								new FilterItemInfo("product.id", product));
					} else {
						filter.getFilterItems().add(
								new FilterItemInfo("product.id", null));
					}
					if (entry.getSplitType() != null) {
						String standard = entry.getSplitType().getValue();
						filter.getFilterItems().add(
								new FilterItemInfo("splitType", standard));
					} else {
						filter.getFilterItems().add(
								new FilterItemInfo("splitType", null));
					}
					view.getSelector().add(COL_AMOUT);
					view.getSelector().add("grtSplitAmt");
					view.getSelector().add("workLoad");
					view.getSelector().add("price");
					
					BigDecimal settleWorkLoad=FDCHelper.ZERO;
					BigDecimal settlePrice=FDCHelper.ZERO;

					view.setFilter(filter);
					try {
						coll = SettlementCostSplitEntryFactory
								.getRemoteInstance()
								.getSettlementCostSplitEntryCollection(view);
						collpay = PaymentSplitEntryFactory.getRemoteInstance()
								.getPaymentSplitEntryCollection(viewPay);
						for (Iterator iter = coll.iterator(); iter.hasNext();) {
							item = (SettlementCostSplitEntryInfo) iter.next();
							if (item.getAmount() != null) {
								temp = temp.add(item.getAmount());
								if (item.getGrtSplitAmt() != null) {
									tempShould = tempShould.add(item
											.getAmount().subtract(
													item.getGrtSplitAmt()));
								} else {
									tempShould = tempShould.add(item
											.getAmount());
								}
							}
							settleWorkLoad=FDCHelper.add(settleWorkLoad, item.getWorkLoad());
							settlePrice=FDCHelper.add(settlePrice, item.getPrice());
						}
						for (Iterator iter = collpay.iterator(); iter.hasNext();) {
							itempay = (PaymentSplitEntryInfo) iter.next();
							if (itempay.getAmount() != null)
								temppay = temppay.add(itempay.getAmount());
							if (itempay.getPayedAmt() != null)
								tempPayed = tempPayed
										.add(itempay.getPayedAmt());
						}
					} catch (BOSException e) {
						handleException(e);
					}
					//成本工程量
					//成本单价
					entry.setCostWorkLoad(settleWorkLoad);
					entry.setPrice(settlePrice);
					getDetailTable().getRow(i).getCell("costWorkLoad").setValue(FDCHelper.toBigDecimal(settleWorkLoad).signum()==0?null:settleWorkLoad);
					getDetailTable().getRow(i).getCell("price").setValue(FDCHelper.toBigDecimal(settlePrice).signum()==0?null:settlePrice);
					
					getDetailTable().getRow(i).getCell(COL_COST_AMT)
							.setValue(temp);
					entry.setCostAmt(temp);
					//已拆分发票在下面单独处理
					getDetailTable().getRow(i).getCell("shouldPayAmt")
							.setValue(tempShould);
					entry.setShouldPayAmt(tempShould);
					
					if (editData.getPaymentBill() != null
							&& editData.getPaymentBill().getContractBillId() != null) {
						FilterInfo filterPayed = new FilterInfo();
						filterPayed.getFilterItems().add(
								new FilterItemInfo(
										"paymentBill.contractBillId", editData
												.getPaymentBill()
												.getContractBillId()));
						filterPayed.getFilterItems().add(
								new FilterItemInfo("state",
										FDCBillStateEnum.INVALID_VALUE,
										CompareType.NOTEQUALS));
						filterPayed.getFilterItems().add(
								new FilterItemInfo(
										"paymentBill.fdcPayType.payType.id",
										PaymentTypeInfo.settlementID));
						// 存在结算，但没有拆分,且不是多次结算
						if (!getBizInterface().exists(filterPayed)
								&& editData.getPaymentBill().getFdcPayType()
										.getPayType().getId().toString()
										.equals(PaymentTypeInfo.settlementID)&&!isMoreSetter()) {
							getDetailTable().getRow(i)
									.getCell("splitedCostAmt").setValue(
											FDCHelper.ZERO);
							entry.setSplitedCostAmt(FDCHelper.ZERO);
							getDetailTable().getRow(i).getCell(
									"splitedPayedAmt").setValue(FDCHelper.ZERO);
							entry.setSplitedPayedAmt(FDCHelper.ZERO);
						} else {
							getDetailTable().getRow(i)
									.getCell("splitedCostAmt")
									.setValue(temppay);
							entry.setSplitedCostAmt(temppay);
							getDetailTable().getRow(i).getCell(
									"splitedPayedAmt").setValue(tempPayed);
							entry.setSplitedPayedAmt(tempPayed);
						}
						
						if (isSimpleInvoice() || isInvoiceMgr()) {// 发票不存在结算控制(与上面代码相同去掉了结算条件)
							FilterInfo filterInvoice = new FilterInfo();
							filterInvoice.getFilterItems().add(
									new FilterItemInfo("costAccount", acc));
							filterInvoice.getFilterItems().add(
									new FilterItemInfo("costBillId", costId));
							filterInvoice.getFilterItems()
									.add(
											new FilterItemInfo(
													"Parent.isRedVouchered",
													Boolean.TRUE,
													CompareType.NOTEQUALS));
							filterInvoice.getFilterItems().add(
									new FilterItemInfo("Parent.state",
											FDCBillStateEnum.INVALID_VALUE,
											CompareType.NOTEQUALS));
							if (editData.getId() != null) {
								filterInvoice.getFilterItems().add(
										new FilterItemInfo("Parent.id",
												editData.getId().toString(),
												CompareType.NOTEQUALS));
							}

							if (entry.getProduct() != null) {
								String product = entry.getProduct().getId()
										.toString();
								filterInvoice.getFilterItems().add(
										new FilterItemInfo("product.id",
												product));
							} else {
								filterInvoice.getFilterItems().add(
										new FilterItemInfo("product.id", null));
							}
							if (entry.getSplitType() != null) {
								String standard = entry.getSplitType()
										.getValue();
								filterInvoice.getFilterItems().add(
										new FilterItemInfo("splitType",
												standard));
							} else {
								filterInvoice.getFilterItems().add(
										new FilterItemInfo("splitType", null));
							}
							viewPay.getSelector().add(COL_INVOICE_AMT);
							viewPay.setFilter(filterInvoice);
							collpay = PaymentSplitEntryFactory
									.getRemoteInstance()
									.getPaymentSplitEntryCollection(viewPay);

							for (Iterator iter = collpay.iterator(); iter
									.hasNext();) {
								itempay = (PaymentSplitEntryInfo) iter.next();
								if (itempay.getInvoiceAmt() != null) {
									tempInvoice = tempInvoice.add(itempay
											.getInvoiceAmt());
								}
							}
							getDetailTable().getRow(i).getCell(
									COL_SPLITED_INVOICE_AMT).setValue(tempInvoice);
							entry.setSplitedInvoiceAmt(tempInvoice);
						}//
					}
				}
			}
		}
	}

	/**
	 * Object对象转换为BigDecimal对象
	 */
	private BigDecimal toBigDecimal(Object obj) {
		if (obj == null) {
			return BigDecimal.valueOf(0);
		} else {
			if (obj instanceof BigDecimal) {
				return (BigDecimal) obj;
			} else {
				String str = obj.toString().trim();
				if (str.matches("\\.?\\d*") || str.matches("\\d+\\.?\\d*")) {
					return new BigDecimal(str);
				}
			}
		}
		return BigDecimal.valueOf(0);
	}

	protected void txtSplitedAmount_dataChanged(
			com.kingdee.bos.ctrl.swing.event.DataChangeEvent e)
			throws Exception {
		super.txtSplitedAmount_dataChanged(e);
		BigDecimal value = txtSplitedAmount.getBigDecimalValue();
		if (value != null && getDetailTable().getRow(0) != null) {
			//TODO 为什么这里要设置为value？如果有一行的分录刚好等于txtSplitedAmount时，就会被调用
			// 导致看起来有翻倍的效果，所以下面这行将来可能要去掉 By Owen_wen 
			//getDetailTable().getCell(0, COL_AMOUT).setValue(value);
			editData.setAmount(value);
		}
	}

	protected void calCmpAmtTotal() {
		if (editData.getCompletePrjAmt() != null) {
			BigDecimal amountTotal = FDCHelper.ZERO;
			BigDecimal amount = FDCHelper.ZERO;
			PaymentSplitEntryInfo entry = null;
			// 计算拆分总金额
			for (int i = 0; i < kdtEntrys.getRowCount(); i++) {
				entry = (PaymentSplitEntryInfo) kdtEntrys.getRow(i)
						.getUserObject();
				if (entry.getLevel() == 0) {
					amount = entry.getPayedAmt();
					if (amount != null) {
						amountTotal = amountTotal.add(amount);
					}
				}
			}
			getDetailTable().getCell(0, COL_PAYED_AMT).setValue(amountTotal);
		}
	}

	protected void updateButtonStatus() {
		// super.updateButtonStatus();
		// 如果是虚体财务组织，则不能增、删、改
		if ((!SysContext.getSysContext().getCurrentOrgUnit()
				.isIsCompanyOrgUnit())
				|| (!SysContext.getSysContext().getCurrentFIUnit()
						.isIsBizUnit())) {
			actionAddNew.setEnabled(false);
			actionEdit.setEnabled(false);
			actionRemove.setEnabled(false);
			actionAddNew.setVisible(false);
			actionEdit.setVisible(false);
			actionRemove.setVisible(false);
			actionSplit.setVisible(false);
			actionVoucher.setVisible(false);
		} else
			actionVoucher.setVisible(true);
		btnVoucher.setVisible(false);
		btnVoucher.setEnabled(false);
	}

	// 响应拆分按钮，当不存在直接金额的时候，按输入的归属金额拆分，如果有直接金额，就按照直接金额进行拆分。
	public void actionSplit_actionPerformed(ActionEvent e) throws Exception {
		super.actionSplit_actionPerformed(e);
		boolean temp = hasDirectAmt;
		if (temp == true) {
			// 手工拆分
			editData.setDescription("ManualSplit");
			if (getDetailTable().getRowCount() > 0) {
				for (int i = 0; i < getDetailTable().getRowCount(); i++) {
					Object obj = getDetailTable().getRow(i).getUserObject();
					if ((obj instanceof PaymentSplitEntryInfo) && ((PaymentSplitEntryInfo) obj).getLevel() > -1) {
						PaymentSplitEntryInfo entry = (PaymentSplitEntryInfo) obj;
						if (getDetailTable().getRow(i).getCell("directAmt").getValue() != null) {
							getDetailTable().getRow(i).getCell(COL_AMOUT).setValue(
									getDetailTable().getRow(i).getCell("directAmt").getValue());
							entry.setAmount(FDCHelper.toBigDecimal(getDetailTable().getRow(i).getCell("directAmt").getValue()));
						} else {
							getDetailTable().getRow(i).getCell(COL_AMOUT).setValue(FDCHelper.ZERO);
							entry.setAmount(FDCHelper.ZERO);
						}
						
						//直接付款金额
						entry.setPayedAmt(FDCHelper.toBigDecimal(getDetailTable().getRow(i).getCell("directPayedAmt").getValue()));
						getDetailTable().getRow(i).getCell(COL_PAYED_AMT).setValue(entry.getPayedAmt());
						
						////直接发票金额
						entry.setInvoiceAmt(FDCHelper.toBigDecimal(getDetailTable().getRow(i).getCell("directInvoiceAmt").getValue()));
						getDetailTable().getRow(i).getCell(COL_INVOICE_AMT).setValue(entry.getInvoiceAmt());
						if (isMeasureContract()) {
							BigDecimal workLoad = FDCHelper.divide(getDetailTable().getRow(i).getCell(COL_AMOUT).getValue(),
									getDetailTable().getRow(i).getCell("price").getValue());
							getDetailTable().getRow(i).getCell("workLoad").setValue(workLoad);
							entry.setWorkLoad(workLoad);
						}
					}
				}
			}
			calDirAmt();
			calInvoiceAmt();
			calPayedAmt();
			calcAmount(0);
			setFirstLine();
			return;//已经有直接金额,要求都用直接金额进行拆分
		} else {
			return;
		}

	}

	protected void setQuaAmt() throws Exception {
		if (getDetailTable().getRowCount() > 0) {
			for (int i = 0; i < getDetailTable().getRowCount(); i++) {
				Object obj = getDetailTable().getRow(i).getUserObject();
				if ((obj instanceof PaymentSplitEntryInfo)
						&& ((PaymentSplitEntryInfo) obj).getLevel() > -1) {
					PaymentSplitEntryInfo entry = (PaymentSplitEntryInfo) obj;
					if (entry.getAmount() != null
							&& editData.getQualityAmount() != null) {
						BigDecimal cmpAmt = FDCHelper.ZERO;
//						cmpAmt = (entry.getAmount().multiply(editData.getQualityAmount())).divide(editData.getCompletePrjAmt(), 2,BigDecimal.ROUND_HALF_EVEN);
						cmpAmt = FDCHelper.divide(FDCHelper.multiply(entry.getAmount(), editData.getQualityAmount()), editData.getCompletePrjAmt(), 2,BigDecimal.ROUND_HALF_EVEN);
						entry.setQualityAmount(cmpAmt);
					}
				}
			}
			if (editData.getAmount().compareTo(editData.getCompletePrjAmt()) == 0) {
				int idx = 0;
				BigDecimal amountMax = FDCHelper.ZERO;
				BigDecimal amount = FDCHelper.ZERO;// null;
				BigDecimal amountTotal = FDCHelper.ZERO;
				amountTotal = amountTotal.setScale(10);
				for (int i = 0; i < getDetailTable().getRowCount(); i++) {
					Object obj = getDetailTable().getRow(i).getUserObject();
					if ((obj instanceof PaymentSplitEntryInfo)
							&& ((PaymentSplitEntryInfo) obj).getLevel() > -1) {
						PaymentSplitEntryInfo entry = (PaymentSplitEntryInfo) obj;
						if (entry.getLevel() == 0) {
							amount = entry.getQualityAmount();
							if (amount == null) {
								amount = FDCHelper.ZERO;
							}
							amountTotal = amountTotal.add(amount);
							// 修正项为金额最大的项
							// if(amount.compareTo(FDCHelper.ZERO)!=0){
							if (amount.compareTo(amountMax) >= 0) {
								amountMax = amount;
								idx = i;
							}
						} else {
							continue;
						}
					}
				}
				if (idx > 0
						&& editData.getQualityAmount().compareTo(amountTotal) != 0) {
					PaymentSplitEntryInfo entry = (PaymentSplitEntryInfo) getPayEntrys()
							.getObject(idx);
					if (entry.getQualityAmount() != null) {
						if (!(entry.getQualityAmount().equals(FDCHelper.ZERO))) {
							if (txtCompletePrjAmt.getBigDecimalValue() != null
									&& txtSplitedAmount.getBigDecimalValue() != null)
								if (txtCompletePrjAmt.getBigDecimalValue().equals(txtSplitedAmount.getBigDecimalValue())) {
									amount = entry.getQualityAmount();
									if (amount == null) {
										amount = FDCHelper.ZERO;
									}
									amount = amount.add(editData.getQualityAmount().subtract(amountTotal));
									entry.setQualityAmount(amount);
								}
						}
					}
				}
				for (int i = 0; i < getDetailTable().getRowCount(); i++) {
					Object obj = getDetailTable().getRow(i).getUserObject();
					if ((obj instanceof PaymentSplitEntryInfo)
							&& ((PaymentSplitEntryInfo) obj).getLevel() > -1) {
						PaymentSplitEntryInfo entry = (PaymentSplitEntryInfo) obj;
						if (entry.getLevel() == 0) {
							int curIndex = this.searchEntryIn(getPayEntrys(), entry);
							PaymentSplitHelper.adjustQuaAmount(
									getPayEntrys(), entry);
							// 汇总生成非明细工程项目中附加科目的成本 jelon 12/29/2006
							PaymentSplitHelper.totAmountQuaAddlAcct(
									getPayEntrys(), curIndex);
							// calcAmount(0);
						}
					}
				}
			}
		}
	}

	// 付款金额按照已完工的比例进行拆分,暂时取消,采用匹配的方式
	protected void setPayedAmt() throws Exception {
		if (getDetailTable().getRowCount() > 0) {
			for (int i = 0; i < getDetailTable().getRowCount(); i++) {
				Object obj = getDetailTable().getRow(i).getUserObject();
				if ((obj instanceof PaymentSplitEntryInfo)
						&& ((PaymentSplitEntryInfo) obj).getLevel() > -1) {
					PaymentSplitEntryInfo entry = (PaymentSplitEntryInfo) obj;
					if (entry.getAmount() != null
							&& editData.getCompletePrjAmt() != null) {
						BigDecimal cmpAmt = FDCHelper.ZERO;
//						cmpAmt = (entry.getAmount().multiply(editData.getPaymentBill().getAmount())).divide(editData.getCompletePrjAmt(), 2,BigDecimal.ROUND_HALF_EVEN);
						cmpAmt = FDCHelper.divide(FDCHelper.multiply(entry.getAmount(), editData.getPaymentBill().getAmount()), editData.getCompletePrjAmt(), 2,BigDecimal.ROUND_HALF_EVEN);
						entry.setPayedAmt(cmpAmt);
						getDetailTable().getRow(i).getCell(COL_PAYED_AMT)
								.setValue(cmpAmt);
					}
				}
			}
			if (editData.getAmount().compareTo(editData.getCompletePrjAmt()) == 0) {
				int idx = 0;
				BigDecimal amountMax = FDCHelper.ZERO;
				BigDecimal amount = FDCHelper.ZERO;// null;
				BigDecimal amountTotal = FDCHelper.ZERO;
				amountTotal = amountTotal.setScale(10);
				for (int i = 0; i < getDetailTable().getRowCount(); i++) {
					Object obj = getDetailTable().getRow(i).getUserObject();
					if ((obj instanceof PaymentSplitEntryInfo)
							&& ((PaymentSplitEntryInfo) obj).getLevel() > -1) {
						PaymentSplitEntryInfo entry = (PaymentSplitEntryInfo) obj;
						if (entry.getLevel() == 0) {
							amount = entry.getPayedAmt();
							if (amount == null) {
								amount = FDCHelper.ZERO;
							}
							amountTotal = amountTotal.add(amount);
							// 修正项为金额最大的项
							// if(amount.compareTo(FDCHelper.ZERO)!=0){
							if (amount.compareTo(amountMax) >= 0) {
								amountMax = amount;
								idx = i;
							}
						} else {
							continue;
						}
					}
				}
				if (idx > 0 && editData.getPaymentBill().getActPayLocAmt().compareTo(amountTotal) != 0) {
					PaymentSplitEntryInfo entry = (PaymentSplitEntryInfo) getPayEntrys().getObject(idx);
					if (entry.getPayedAmt() != null) {
						if (!(entry.getPayedAmt().equals(FDCHelper.ZERO))) {
							if (txtCompletePrjAmt.getBigDecimalValue() != null && txtSplitedAmount.getBigDecimalValue() != null)
								if (txtCompletePrjAmt.getBigDecimalValue().equals(txtSplitedAmount.getBigDecimalValue())) {
									amount = entry.getPayedAmt();
									if (amount == null) {
										amount = FDCHelper.ZERO;
									}
									amount = amount.add(editData.getPaymentBill().getActPayLocAmt().subtract(amountTotal));
									entry.setPayedAmt(amount);
								}
						}
					}
				}
				for (int i = 0; i < getDetailTable().getRowCount(); i++) {
					Object obj = getDetailTable().getRow(i).getUserObject();
					if ((obj instanceof PaymentSplitEntryInfo)
							&& ((PaymentSplitEntryInfo) obj).getLevel() > -1) {
						PaymentSplitEntryInfo entry = (PaymentSplitEntryInfo) obj;
						if (entry.getLevel() == 0) {
							int curIndex = searchEntryIn(getPayEntrys(), entry);
							PaymentSplitHelper.adjustPayAmount(
									getPayEntrys(), entry);
							// 汇总生成非明细工程项目中附加科目的成本 jelon 12/29/2006
							PaymentSplitHelper.totAmountPayAddlAcct(
									getPayEntrys(), curIndex);
							// calcAmount(0);
						}
					}
				}
				for (int i = 0; i < getDetailTable().getRowCount(); i++) {
					Object obj = getDetailTable().getRow(i).getUserObject();
					if ((obj instanceof PaymentSplitEntryInfo)
							&& ((PaymentSplitEntryInfo) obj).getLevel() > -1) {
						PaymentSplitEntryInfo entry = (PaymentSplitEntryInfo) obj;
						if (entry.getPayedAmt() != null) {
							getDetailTable().getRow(i).getCell(COL_PAYED_AMT)
									.setValue(entry.getPayedAmt());
						}
					}
				}
			}
		}
	}

	protected PaymentSplitEntryCollection getPayEntrys() {
		if (payEntrys == null) {
			payEntrys = new PaymentSplitEntryCollection(); 
			for (int i = 0; i < kdtEntrys.getRowCount(); i++) {
				payEntrys.add((PaymentSplitEntryInfo) kdtEntrys.getRow(i).getUserObject());
			}			
		}
		
		return payEntrys;
	}

	// 设置直接金额与金额的可录入
	private void setAmtDisplay() {
		
		KDFormattedTextField formattedTextField = new KDFormattedTextField(KDFormattedTextField.BIGDECIMAL_TYPE);
		formattedTextField.setPrecision(2);
		formattedTextField.setRemoveingZeroInDispaly(false);
		formattedTextField.setRemoveingZeroInEdit(false);
		formattedTextField.setNegatived(true);
		formattedTextField.setMaximumValue(FDCHelper.MAX_VALUE);
		formattedTextField.setMinimumValue(FDCHelper.MIN_VALUE);
		ICellEditor numberEditor = new KDTDefaultCellEditor(formattedTextField);
		
			if (getDetailTable().getRowCount() > 0) {
				for (int i = 0; i < getDetailTable().getRowCount(); i++) {
					Object obj = getDetailTable().getRow(i).getUserObject();
					if ((obj instanceof PaymentSplitEntryInfo) && ((PaymentSplitEntryInfo) obj).getLevel() > -1) {
						IRow row = getDetailTable().getRow(i);
						PaymentSplitEntryInfo entry = (PaymentSplitEntryInfo) obj;
						if (entry.getLevel() == 0) {
							row.getStyleAttributes().setBackground(new Color(0xF6F6BF));
							row.getCell(COL_AMOUT).getStyleAttributes().setBackground(Color.WHITE);
						} else {
							row.getStyleAttributes().setBackground(new Color(0xF5F5E6));
							row.getCell(COL_AMOUT).getStyleAttributes().setLocked(false);
						}
						if (entry.getCostAccount() != null && entry.getCostAccount().getCurProject() != null && (entry.getCostAccount().getCurProject().isIsLeaf()) && (entry.isIsLeaf())) {
							row.getCell("directAmt").getStyleAttributes().setBackground(Color.WHITE);
							row.getCell("directPayedAmt").getStyleAttributes().setBackground(Color.WHITE);
						row.getCell("directInvoiceAmt").getStyleAttributes().setBackground(Color.WHITE);
						//							if(isLimitCost()){
						//								formattedTextField.setMaximumValue(FDCHelper.toBigDecimal(row.getCell(COL_COST_AMT).getValue(), 2).subtract(FDCHelper.toBigDecimal(row.getCell("splitedCostAmt").getValue(), 2)));
						//							}
							row.getCell("directAmt").setEditor(numberEditor);

						//							if(isLimitCost()){
						//								formattedTextField.setMaximumValue(FDCHelper.toBigDecimal(FDCHelper.subtract(row.getCell("shouldPayAmt").getValue(), row.getCell("splitedPayedAmt").getValue()), 2));
						//							}

						row.getCell("directPayedAmt").setEditor(numberEditor);
						//							if(isLimitCost()){
						//								formattedTextField.setMaximumValue(FDCHelper.toBigDecimal(row.getCell(COL_COST_AMT).getValue(), 2).subtract(FDCHelper.toBigDecimal(row.getCell(COL_SPLITED_INVOICE_AMT).getValue(), 2)));
						//							}
							row.getCell("directInvoiceAmt").setEditor(numberEditor);
						} else {
							row.getCell("directAmt").getStyleAttributes().setBackground(FDCHelper.KDTABLE_TOTAL_BG_COLOR);
							row.getCell("directAmt").getStyleAttributes().setLocked(true);
							
							row.getCell("directPayedAmt").getStyleAttributes().setBackground(FDCHelper.KDTABLE_TOTAL_BG_COLOR);
							row.getCell("directPayedAmt").getStyleAttributes().setLocked(true);
							
							row.getCell("directInvoiceAmt").getStyleAttributes().setBackground(FDCHelper.KDTABLE_TOTAL_BG_COLOR);
							row.getCell("directInvoiceAmt").getStyleAttributes().setLocked(true);
						}
					}
				}
			}
			getDetailTable().getColumn(COL_PAYED_AMT).getStyleAttributes().setBackground(FDCHelper.KDTABLE_TOTAL_BG_COLOR);
		getDetailTable().getColumn(COL_PAYED_AMT).getStyleAttributes().setLocked(true);
			
			getDetailTable().getColumn(COL_INVOICE_AMT).getStyleAttributes().setBackground(FDCHelper.KDTABLE_TOTAL_BG_COLOR);
			getDetailTable().getColumn(COL_INVOICE_AMT).getStyleAttributes().setLocked(true);
		 
	}

	/**
	 *  有直接金额，拆分后汇总
	 */
	private void calDirAmt() {
		// 工程量与付款分离
		if (this.isLoadWorkConfirm && isWorkLoadSeparate()) {
			for (int i = 0; i < kdtEntrys.getRowCount(); i++) {
				PaymentSplitEntryInfo entry = (PaymentSplitEntryInfo) kdtEntrys.getRow(i).getUserObject();
				int curIndex = searchEntryIn(getEntrys(), entry);
				if (curIndex == -1) {
					return;
				}
				if (entry.getLevel() == 0) {
					sumAccount(i);
					fdcCostSplit.totAmountAddlAcct(getEntrys(), curIndex);
				}
				// 因为 entry.getAmount() 在sumAccount(i)中有改变
				kdtEntrys.getRow(i).getCell(COL_AMOUT).setValue(FDCHelper.ZERO);
			}
		} else {
			for (int i = 0; i < kdtEntrys.getRowCount(); i++) {
				PaymentSplitEntryInfo entry = (PaymentSplitEntryInfo) kdtEntrys.getRow(i).getUserObject();
				int curIndex = searchEntryIn(getEntrys(), entry);
				if (curIndex == -1) {
					return;
				}
				if (entry.getLevel() == 0) {
					sumAccount(i);
					fdcCostSplit.totAmountAddlAcct(getEntrys(), curIndex);
				}
				// 因为 entry.getAmount() 在sumAccount(i)中有改变
				// entry.setAmount((BigDecimal) kdtEntrys.getRow(i).getCell(COL_PAYED_AMT).getValue());
				kdtEntrys.getRow(i).getCell(COL_AMOUT).setValue(FDCHelper.toBigDecimal(entry.getAmount()));
				//kdtEntrys.getRow(i).getCell(COL_AMOUT).setValue(kdtEntrys.getRow(i).getCell(COL_PAYED_AMT).getValue())
				// ;
			}
		}
		
	}
	
	private void calCostAmt() {
		for (int i = 1; i < kdtEntrys.getRowCount(); i++) {
			PaymentSplitEntryInfo entry = (PaymentSplitEntryInfo) kdtEntrys
					.getRow(i).getUserObject();
			int curIndex = searchEntryIn(getEntrys(), entry);
			if (curIndex == -1) {
				return;
			}
			if (entry.getLevel() == 0) {
				sumCostAmt(i);
				PaymentSplitHelper.totAmountPayAddlAcct(getPayEntrys(),
						curIndex);
			}
		}
		for (int i = 0; i < kdtEntrys.getRowCount(); i++) {
			IRow row = kdtEntrys.getRow(i);
			PaymentSplitEntryInfo entry = (PaymentSplitEntryInfo) row
					.getUserObject();
			BigDecimal amount = entry.getAmount();
			if (amount == null) {
				amount = FDCHelper.ZERO;
			}
			row.getCell(COL_AMOUT).setValue(amount);
		}
	}
	

	/**
	 * 描述：在分录集合中查找某条分录所在位置
	 * @param entrys 分录集合
	 * @param entry 某条分录
	 * @return 分录所在位置
	 * @Author：owen_wen
	 * @CreateTime：2012-11-15
	 */
	private int searchEntryIn(AbstractObjectCollection entrys, PaymentSplitEntryInfo entry) {
		for (int i = 0; i < entrys.size(); i++) {
			if (entry == entrys.getObject(i))
				return i;
		}
		return -1;
	}
	
	/**
	 * 描述：计算 归属付款金额 
	 */
	private void calPayedAmt() {
		for (int i = 1; i < kdtEntrys.getRowCount(); i++) {
			PaymentSplitEntryInfo entry = (PaymentSplitEntryInfo) kdtEntrys
					.getRow(i).getUserObject();
			int curIndex = searchEntryIn(getEntrys(), entry);
			if (curIndex == -1) {
				return;
			}
			if (entry.getLevel() == 0) {
				sumPayedAmt(i);
				PaymentSplitHelper.totAmountPayAddlAcct(getPayEntrys(),
						curIndex);
			}
			kdtEntrys.getRow(i).getCell(COL_PAYED_AMT).setValue(FDCHelper.toBigDecimal(entry.getPayedAmt()));
		}
	}

	private void calInvoiceAmt() {
		if(!isInvoiceMgr()&&!isSimpleInvoice()){
			return;
		}
		for (int i = 1; i < kdtEntrys.getRowCount(); i++) {
			PaymentSplitEntryInfo entry = (PaymentSplitEntryInfo) kdtEntrys
					.getRow(i).getUserObject();
			int curIndex = searchEntryIn(getEntrys(), entry);
			if (curIndex == -1) {
				return;
			}
			if (entry.getLevel() == 0) {
				sumInvoiceAmt(i);
				PaymentSplitHelper.totAmountInvoiceAddlAcct(getEntrys(), curIndex);
			}
		}
		
		BigDecimal amountTotal=FDCHelper.ZERO;
		for (int i = 0; i < kdtEntrys.getRowCount(); i++) {
			IRow row = kdtEntrys.getRow(i);
			PaymentSplitEntryInfo entry = (PaymentSplitEntryInfo) row
					.getUserObject();
			BigDecimal amount = entry.getInvoiceAmt();
			if (amount == null) {
				amount = FDCHelper.ZERO;
			} else if (entry.getLevel() == 0) {
				amountTotal = amountTotal.add(amount);
			}
			row.getCell(COL_INVOICE_AMT).setValue(amount);
		}
		txtSplitInvoiceAmt.setValue(amountTotal);
	}
	
	void sumCostAmt(int index){

		PaymentSplitEntryInfo curEntry = (PaymentSplitEntryInfo) kdtEntrys
				.getRow(index).getUserObject();
		int curLevel = curEntry.getLevel();
		PaymentSplitEntryInfo entry;
		if ((curEntry.getCostAccount().getCurProject().isIsLeaf())
				&& (curEntry.isIsLeaf())) {
			return;
		} else {
			if (!curEntry.getCostAccount().getCurProject().isIsLeaf()) {
				BigDecimal amtTotal = FDCHelper.ZERO;
				for (int i = index + 1; i < kdtEntrys.getRowCount(); i++) {
					entry = (PaymentSplitEntryInfo) kdtEntrys.getRow(i)
							.getUserObject();
					if (fdcCostSplit.isChildProjSameAcct(entry, curEntry)) {
						// if((entry.getLevel()==curLevel+1)&&(entry.getCostAccount().getCurProject().getLevel()==prjLevel+1)){
						sumCostAmt(i);
						if (entry.getAmount() != null)
							amtTotal = amtTotal.add(entry.getAmount());
					} else if (entry.getLevel() < curLevel + 1) {
						break;
					} else {
						continue;
					}
				}
				curEntry.setAmount(amtTotal);
				kdtEntrys.getRow(index).getCell(COL_AMOUT).setValue(amtTotal);
			} else {
				BigDecimal amtTotal = FDCHelper.ZERO;
				for (int i = index + 1; i < kdtEntrys.getRowCount(); i++) {
					entry = (PaymentSplitEntryInfo) kdtEntrys.getRow(i)
							.getUserObject();
					if (entry.getCostAccount().getCurProject().getId().equals(
							curEntry.getCostAccount().getCurProject().getId())) {
						if (entry.getLevel() == curLevel + 1) {
							sumCostAmt(i);
							if (entry.getAmount() != null)
								amtTotal = amtTotal.add(entry.getAmount());
						} else if (entry.getLevel() > curLevel + 1) {
							continue;
						} else {
							break;
						}
					} else
						break;
				}
				curEntry.setAmount(amtTotal);
				kdtEntrys.getRow(index).getCell(COL_AMOUT).setValue(amtTotal);
			}
		}
	
	}
	
	
	private void sumInvoiceAmt(int index) {
		PaymentSplitEntryInfo curEntry = (PaymentSplitEntryInfo) kdtEntrys
				.getRow(index).getUserObject();
		int curLevel = curEntry.getLevel();
		// int prjLevel = curEntry.getCostAccount().getCurProject().getLevel();
		PaymentSplitEntryInfo entry;
		if ((curEntry.getCostAccount().getCurProject().isIsLeaf())
				&& (curEntry.isIsLeaf())) {
			return;
		} else {
			if (!curEntry.getCostAccount().getCurProject().isIsLeaf()) {
				BigDecimal amtTotal = FDCHelper.ZERO;
				for (int i = index + 1; i < kdtEntrys.getRowCount(); i++) {
					entry = (PaymentSplitEntryInfo) kdtEntrys.getRow(i)
							.getUserObject();
					if (fdcCostSplit.isChildProjSameAcct(entry, curEntry)) {
						// if((entry.getLevel()==curLevel+1)&&(entry.getCostAccount().getCurProject().getLevel()==prjLevel+1)){
						sumInvoiceAmt(i);
						if (entry.getInvoiceAmt() != null)
							amtTotal = amtTotal.add(entry.getInvoiceAmt());
					} else if (entry.getLevel() < curLevel + 1) {
						break;
					} else {
						continue;
					}
				}
				curEntry.setInvoiceAmt(amtTotal);
				kdtEntrys.getRow(index).getCell(COL_INVOICE_AMT).setValue(amtTotal);
			} else {
				BigDecimal amtTotal = FDCHelper.ZERO;
				for (int i = index + 1; i < kdtEntrys.getRowCount(); i++) {
					entry = (PaymentSplitEntryInfo) kdtEntrys.getRow(i)
							.getUserObject();
					if (entry.getCostAccount().getCurProject().getId().equals(
							curEntry.getCostAccount().getCurProject().getId())) {
						if (entry.getLevel() == curLevel + 1) {
							sumInvoiceAmt(i);
							if (entry.getInvoiceAmt() != null)
								amtTotal = amtTotal.add(entry.getInvoiceAmt());
						} else if (entry.getLevel() > curLevel + 1) {
							continue;
						} else {
							break;
						}
					} else
						break;
				}
				curEntry.setInvoiceAmt(amtTotal);
				kdtEntrys.getRow(index).getCell(COL_INVOICE_AMT).setValue(amtTotal);
			}
		}
	}
	private void sumPayedAmt(int index) {
		PaymentSplitEntryInfo curEntry = (PaymentSplitEntryInfo) kdtEntrys
				.getRow(index).getUserObject();
		int curLevel = curEntry.getLevel();
		// int prjLevel = curEntry.getCostAccount().getCurProject().getLevel();
		PaymentSplitEntryInfo entry;
		if ((curEntry != null && curEntry.getCostAccount() != null && curEntry.getCostAccount().getCurProject() != null && curEntry.getCostAccount().getCurProject().isIsLeaf())
				&& (curEntry.isIsLeaf())) {
			return;
		} else {
			if (curEntry.getCostAccount().getCurProject() != null && !curEntry.getCostAccount().getCurProject().isIsLeaf()) {
				BigDecimal amtTotal = FDCHelper.ZERO;
				for (int i = index + 1; i < kdtEntrys.getRowCount(); i++) {
					entry = (PaymentSplitEntryInfo) kdtEntrys.getRow(i)
							.getUserObject();
					if (fdcCostSplit.isChildProjSameAcct(entry, curEntry)) {
						// if((entry.getLevel()==curLevel+1)&&(entry.getCostAccount().getCurProject().getLevel()==prjLevel+1)){
						sumPayedAmt(i);
						if (entry.getPayedAmt() != null)
							amtTotal = amtTotal.add(entry.getPayedAmt());
					} else if (entry.getLevel() < curLevel + 1) {
						break;
					} else {
						continue;
					}
				}
				curEntry.setPayedAmt(amtTotal);
				kdtEntrys.getRow(index).getCell(COL_PAYED_AMT).setValue(amtTotal);
			} else {
				BigDecimal amtTotal = FDCHelper.ZERO;
				for (int i = index + 1; i < kdtEntrys.getRowCount(); i++) {
					entry = (PaymentSplitEntryInfo) kdtEntrys.getRow(i)
							.getUserObject();
					if (entry.getCostAccount().getCurProject().getId().equals(
							curEntry.getCostAccount().getCurProject().getId())) {
						if (entry.getLevel() == curLevel + 1) {
							sumPayedAmt(i);
							if (entry.getPayedAmt() != null)
								amtTotal = amtTotal.add(entry.getPayedAmt());
						} else if (entry.getLevel() > curLevel + 1) {
							continue;
						} else {
							break;
						}
					} else
						break;
				}
				curEntry.setPayedAmt(amtTotal);
				kdtEntrys.getRow(index).getCell(COL_PAYED_AMT).setValue(amtTotal);
			}
		}
	}

	// 对level=0的进行汇总
	private void sumAccount(int index) {
		PaymentSplitEntryInfo curEntry = (PaymentSplitEntryInfo) kdtEntrys
				.getRow(index).getUserObject();
		int curLevel = curEntry.getLevel();
		// int prjLevel = curEntry.getCostAccount().getCurProject().getLevel();
		PaymentSplitEntryInfo entry;
		if ((curEntry.getCostAccount().getCurProject().isIsLeaf())
				&& (curEntry.isIsLeaf())) {
			return;
		} else {
			if (!curEntry.getCostAccount().getCurProject().isIsLeaf()) {
				BigDecimal amtTotal = FDCHelper.ZERO;
				for (int i = index + 1; i < kdtEntrys.getRowCount(); i++) {
					entry = (PaymentSplitEntryInfo) kdtEntrys.getRow(i)
							.getUserObject();
					if (fdcCostSplit.isChildProjSameAcct(entry, curEntry)) {
						// if((entry.getLevel()==curLevel+1)&&(entry.getCostAccount().getCurProject().getLevel()==prjLevel+1)){
						sumAccount(i);
						if (entry.getAmount() != null)
							amtTotal = amtTotal.add(entry.getAmount());
					} else if (entry.getLevel() < curLevel + 1) {
						break;
					} else {
						continue;
					}
				}
				curEntry.setAmount(amtTotal);
				kdtEntrys.getRow(index).getCell(COL_AMOUT).setValue(amtTotal);
			} else {
				BigDecimal amtTotal = FDCHelper.ZERO;
				for (int i = index + 1; i < kdtEntrys.getRowCount(); i++) {
					entry = (PaymentSplitEntryInfo) kdtEntrys.getRow(i)
							.getUserObject();
					if (entry.getCostAccount().getCurProject().getId().equals(
							curEntry.getCostAccount().getCurProject().getId())) {
						if (entry.getLevel() == curLevel + 1) {
							sumAccount(i);
							if (entry.getAmount() != null)
								amtTotal = amtTotal.add(entry.getAmount());
						} else if (entry.getLevel() > curLevel + 1) {
							continue;
						} else {
							break;
						}
					} else
						break;
				}
				curEntry.setAmount(amtTotal);
				kdtEntrys.getRow(index).getCell(COL_AMOUT).setValue(amtTotal);
			}
		}
	}

	protected void checkbeforeSave() {
		checkDirAmt();
		checkOver();
		checkPayAmt();
		super.checkbeforeSave();
	}
	protected void checkPayAmt(){
		IRow row = null;
		PaymentSplitEntryInfo entry=null;
		BigDecimal amount;
		BigDecimal amountTotal=null;
		CostAccountInfo acct=null;
		CurProjectInfo proj=null;		
		int level=0;
		BigDecimal splitAmount=FDCHelper.ZERO;
		for(int i=0; i<kdtEntrys.getRowCount(); i++){
			row=kdtEntrys.getRow(i);
			entry=(PaymentSplitEntryInfo)row.getUserObject();
			if(entry.getLevel()<0) continue;//总计行
			//明细工程项目
			if(entry.getCostAccount().getCurProject().isIsLeaf()
					&& !entry.getCostAccount().isIsLeaf()){		
				acct=entry.getCostAccount();
				proj=acct.getCurProject();				
				level=acct.getLevel();
								
				//汇总金额
				
				if(entry.getPayedAmt()!=null){
					amount=entry.getPayedAmt();					
				}else{
					amount=FDCHelper.ZERO;
				}
				
				//后一科目必须为附加明细科目
				row=kdtEntrys.getRow(i+1);
				if(row!=null){
					entry=(PaymentSplitEntryInfo)row.getUserObject();	
				}
				if(isAddlAcctLeaf(entry)){
					
					//金额累加
					amountTotal=FDCHelper.ZERO;

					for(int j=i+1; j<kdtEntrys.getRowCount(); j++){
						row=kdtEntrys.getRow(j);
						entry=(PaymentSplitEntryInfo)row.getUserObject();	
						
						//同一工程项目
						if(entry.getCostAccount().getCurProject().getId().equals(proj.getId())){
							//下一级成本科目
							if(entry.getCostAccount().getLevel()>level){
								if(entry.getCostAccount().getLevel()==level+1
										&& !isProdSplitLeaf(entry)
										&& entry.getPayedAmt()!=null){
									amountTotal=amountTotal.add(entry.getPayedAmt());	
									
								}
							}else{
								break;
							}
						}else{
							break;
						}						
					}
					if(amountTotal.compareTo(amount)!=0){
			    		FDCMsgBox.showWarning(this,FDCSplitClientHelper.getRes("mustEqu"));
			    		SysUtil.abort();						
					}
				}			
			}
		}
		//检查汇总金额
		amount=this.txtAmount.getBigDecimalValue();
		if(amount==null){
			amount=FDCHelper.ZERO;
		}
		amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
		/**
		 * 改进现有的提示，现在是本期成本金额未拆分或者本期实付金额未拆分，都提示为：未做任何拆分操作，无法保存。
		 * 改进为：未拆分成本时，提示“本期成本金额未拆分，将不做保存”；未拆分付款时，提示“本期实付金额未拆分，
		 * 将不做保存”。
		 * by jian_wen 2009.12.21
		 */
		//获取总计 归属成本金额
    	BigDecimal am = FDCHelper.toBigDecimal(kdtEntrys.getRow(0).getCell(COL_AMOUT).getValue(), 2);
    	splitAmount = FDCHelper.toBigDecimal(kdtEntrys.getRow(0).getCell(COL_PAYED_AMT).getValue(), 2);
    	//支持发票模式
	    if (splitAmount == null || splitAmount.compareTo(FDCHelper.ZERO) == 0
				&& amount.compareTo(FDCHelper.ZERO) != 0) {
			MsgBox.showWarning("本期实付金额未拆分，将不做保存");
			abort();
		} else if (FDCHelper.toBigDecimal(splitAmount).signum() == 0
				&& (am == null || am.compareTo(FDCHelper.ZERO) == 0)
				&& amount.compareTo(FDCHelper.ZERO) != 0) {
			// 加上实付金额判断
			MsgBox.showWarning("本期成本金额未拆分，将不做保存");
			abort();
		}
//		注释掉 以前的提示     	
//    	if((splitAmount==null||splitAmount.compareTo(FDCHelper.ZERO)==0)&&amount.compareTo(FDCHelper.ZERO)!=0){
//    		FDCMsgBox.showWarning(this,FDCSplitClientHelper.getRes("notSplited"));
//    		SysUtil.abort();
//    	}
    	else if(amount.compareTo(splitAmount)>0){
			FDCMsgBox.showWarning(this,FDCSplitClientHelper.getRes("notAllSplit"));
    		SysUtil.abort();
    	}else if(amount.compareTo(splitAmount)<0){
    		FDCMsgBox.showWarning(this,FDCSplitClientHelper.getRes("moreThan"));
    		SysUtil.abort();
    	}
	}
	
	
	protected KDFormattedTextField getTotalTxt() {
		return txtCompletePrjAmt;
	}

	private void checkOver() {
		// /////////////////////////////////////////////////////////////////////////
		// 校验_工程量确认流程与付款流程分离参数（参数FDC317）
		check4WorkLoadSeparate();
		// /////////////////////////////////////////////////////////////////////////
		
		if (getDetailTable().getRowCount() > 0) {
			IRow row = getDetailTable().getRow(0);
			BigDecimal splited = FDCHelper.toBigDecimal(row.getCell("splitedCostAmt").getValue(), 2);
			BigDecimal splitThis = FDCHelper.toBigDecimal(row.getCell(COL_AMOUT).getValue(), 2);
			BigDecimal payedAmt = FDCHelper.toBigDecimal(row.getCell(COL_PAYED_AMT).getValue(), 2);
			BigDecimal splitedPayedAmt = FDCHelper.toBigDecimal(row.getCell("splitedPayedAmt").getValue(), 2);
			BigDecimal splitedCostAmt = FDCHelper.toBigDecimal(row.getCell("splitedCostAmt").getValue(), 2);
			BigDecimal total = FDCHelper.toBigDecimal(row.getCell(COL_COST_AMT).getValue(), 2);
			BigDecimal invoiceAmt = FDCHelper.toBigDecimal(row.getCell(COL_INVOICE_AMT).getValue(), 2);

			/*
			 * modified by zhaojie for BT870197 on 2015/03/27
			 */
			/* =========start========== */
			/**
			 * 三、参数317”分离“为”否“，合同类型中“工程量”勾选： 付款拆分单据 1、表头.本期成本金额=付款申请单.表头.本期完工工程量 2、表头.已拆分成本金额=表格.已拆分成本金额
			 * 3、表格.已拆分成本金额=SUM（付款拆分单据.表格.归属成本金额），过滤条件：当前单据之前 4、表格.归属成本金额=表头.本期成本金额 按照比例拆分的金额（提交时会校验要求两者相等）
			 */
			// 参数317”分离“为”否“
			if (!isWorkLoadSeparate()) {
				// 合同类型中“工程量”勾选
				if (isLoadWorkConfirm) {
					if (FDCHelper.subtract(txtCompletePrjAmt.getBigDecimalValue(), splitThis).signum() != 0) {
						FDCMsgBox.showWarning(this, "归属成本金额不等于本期成本金额不能保存！");
						SysUtil.abort();
					}
				}
			}

			/**
			 * TODO 暂不支持,多次结算，多张付款 如成本拆分60000,未最终结算拆分61000,付款(65000)第一笔60500时,已拆分成本金额65000，实付60500
			 */
			/**
			 * 一、参数317”分离“为”是”，合同类型中“工程量”勾选： 2、去掉校验：表格.成本拆分金额 >= 表格.已拆分成本金额 。 (业务说明：
			 * 成本拆分金额实际上是（合同拆分+合同变更拆分）或结算拆分，已拆分成本金额实际上是工程量拆分金额)
			 */
			// 合同管理参数：FDC017：“付款拆分科目的金额受对应科目已拆分成本金额的限制” 将被作废，此处不做该参数的校验
			// if (isLimitCost()) {
			/*
			 * if (!isWorkLoadSeparate() && !isLoadWorkConfirm) { if (splited.add(splitThis).compareTo(total) == 1) {
			 * String msg = ""; if (isMoreSetter()) { msg = "已拆分金额大于成本拆分金额或结算拆分金额，不能保存！"; } else { msg =
			 * "已拆分金额大于成本拆分金额，不能保存！"; } MsgBox.showWarning(this, msg); SysUtil.abort(); } }
			 */
			/* =========end========== */
			boolean isPrePay = false;
			if(editData.getPaymentBill()!=null&&editData.getPaymentBill().getFdcPayType()!=null){
				String name = editData.getPaymentBill().getFdcPayType().getName();
				if("预付款".equals(name) ||"預付款".equals(name) ){
					isPrePay=true;
				}
			}
			//融创反馈，周鹏确认预付款不校验 by hpw
			//by tim_gao 增加合同类型为工程量确认
			if(this.isLoadWorkConfirm&&isWorkLoadSeparate() && !isPrePay){
				if (FDCHelper.subtract(splited, FDCHelper.add(splitedPayedAmt, payedAmt)).signum() < 0 && isLimitCost()) {
					MsgBox.showWarning(this, "已拆分付款金额大于已拆分成本金额，不能保存;请确认工程量确认单是否未拆分！");
					SysUtil.abort();
				}
				//实付款不等于已拆分付款金额，既没有完全拆分完
				if(FDCHelper.subtract(txtAmount.getBigDecimalValue(), payedAmt).signum()!=0){
					FDCMsgBox.showWarning(this,"实付款金额不等于已拆分付款金额不能保存！");
					SysUtil.abort();
				}
			}
			if(isInvoiceMgr() || isSimpleInvoice()){
				//发票金额不等于归属发票金额，既没有完全拆分完
				if(FDCHelper.subtract(txtInvoiceAmt.getBigDecimalValue(), invoiceAmt).signum()!=0){
					FDCMsgBox.showWarning(this, "本期发票金额不等于归属发票金额不能保存！");
					SysUtil.abort();
				}
				//实付款不等于已拆分付款金额，既没有完全拆分完
				if(FDCHelper.subtract(txtAmount.getBigDecimalValue(), payedAmt).signum()!=0){
					FDCMsgBox.showWarning(this,"实付款金额不等于已拆分付款金额不能保存！");
					SysUtil.abort();
				}
			}
			
		}
	}

	// 检查是否存在有直接金额，但没进行对应拆分的情况。
	private void checkDirAmt() {
		if (getDetailTable().getRowCount() > 0) {
			for (int i = 0; i < getDetailTable().getRowCount(); i++) {
				if (getDetailTable().getRow(i).getCell("directAmt").getValue() != null) {
					BigDecimal dir = toBigDecimal(getDetailTable().getRow(i).getCell("directAmt").getValue());
					if (dir.compareTo(FDCHelper.ZERO) > 0) {
						BigDecimal dirAmt = toBigDecimal(getDetailTable().getRow(i).getCell(COL_AMOUT).getValue());
						if (dir.compareTo(dirAmt) != 0) {
							MsgBox.showWarning(this, FDCSplitClientHelper.getRes("haveDirAmt"));
							SysUtil.abort();
						}
					}
				}
				
				if (getDetailTable().getRow(i).getCell("directPayedAmt").getValue() != null) {
					BigDecimal dir = toBigDecimal(getDetailTable().getRow(i).getCell("directPayedAmt").getValue());
					if (dir.compareTo(FDCHelper.ZERO) > 0) {
						BigDecimal dirAmt = toBigDecimal(getDetailTable().getRow(i).getCell(COL_PAYED_AMT).getValue());
						if (dir.compareTo(dirAmt) != 0) {
							MsgBox.showWarning(this, FDCSplitClientHelper.getRes("haveDirAmt"));
							SysUtil.abort();
						}
					}
				}
				
				if (getDetailTable().getRow(i).getCell("directInvoiceAmt").getValue() != null) {
					BigDecimal dir = toBigDecimal(getDetailTable().getRow(i).getCell("directInvoiceAmt").getValue());
					if (dir.compareTo(FDCHelper.ZERO) > 0) {
						BigDecimal dirAmt = toBigDecimal(getDetailTable().getRow(i).getCell(COL_INVOICE_AMT).getValue());
						if (dir.compareTo(dirAmt) != 0) {
							MsgBox.showWarning(this, FDCSplitClientHelper.getRes("haveDirAmt"));
							SysUtil.abort();
						}
					}
				}
			}
		}
	}

	/**
	 * 设置是否存在直接拆分
	 */
	private void setHasDirectAmt() {
		for (int i = 0; i < kdtEntrys.getRowCount(); i++) {
			IRow row = kdtEntrys.getRow(i);
			if (row.getUserObject() != null) {
				// for (Iterator iter = getPayEntrys().iterator();
				// iter.hasNext();) {
				// for (Iterator iter = editData.getEntrys().iterator();
				// iter.hasNext();) {
				PaymentSplitEntryInfo entry = (PaymentSplitEntryInfo) row.getUserObject();
				if (FDCHelper.toBigDecimal(entry.getDirectAmt()).signum()!=0) {
					hasDirectAmt = true;
					return;
				}
				
				if (FDCHelper.toBigDecimal(entry.getDirectPayedAmt()).signum()!=0) {
					hasDirectAmt = true;
					return;
				}
				//add direct invoice  by sxhong 2009-07-28 16:07:26
				if (FDCHelper.toBigDecimal(entry.getDirectInvoiceAmt()).signum()!=0) {
					hasDirectAmt = true;
					return;
				}
			}
		}
		hasDirectAmt = false;
	}

	public void actionViewContract_actionPerformed(ActionEvent e)
			throws Exception {
		if (editData.getPaymentBill() == null) {
			MsgBox.showWarning(this, ContractClientUtils
					.getRes("selectProjLeafPls"));
			SysUtil.abort();
		}
		UIContext uiContext = new UIContext(this);
		if (editData.getPaymentBill().getContractBillId() != null) {
			uiContext.put(UIContext.ID, editData.getPaymentBill()
					.getContractBillId());
			uiContext.put("contractBillId", editData.getPaymentBill()
					.getContractBillId());
			uiContext.put("contractBillNumber", editData.getPaymentBill()
					.getContractNo());
			IUIWindow testUI = UIFactory.createUIFactory(UIFactoryName.NEWTAB)
					.create(ContractBillEditUI.class.getName(), uiContext,
							null, OprtState.VIEW);
			testUI.show();
		}
	}

	public void actionViewPayment_actionPerformed(ActionEvent e)
			throws Exception {
		if (editData.getPaymentBill() == null) {
			MsgBox.showWarning(this, ContractClientUtils
					.getRes("selectProjLeafPls"));
			SysUtil.abort();
		}
		UIContext uiContext = new UIContext(this);
		if (editData.getPaymentBill().getContractBillId() != null) {
			uiContext.put(UIContext.ID, editData.getPaymentBill().getId());
			uiContext.put("contractBillId", editData.getPaymentBill()
					.getContractBillId());
			uiContext.put("contractBillNumber", editData.getPaymentBill()
					.getContractNo());
			IUIWindow testUI = UIFactory.createUIFactory(UIFactoryName.NEWTAB)
					.create(PaymentBillEditUI.class.getName(), uiContext, null,
							OprtState.VIEW);
			testUI.show();
		}
	}

	protected KDFormattedTextField getTxtSplitedAmount() {
		calcAmount(0);
		return txtSplitedAmount;
	}

	public void actionVoucher_actionPerformed(ActionEvent e) throws Exception {
		if (editData != null && editData.getId() != null) {
			List idList = new ArrayList();
			idList.add(editData.getId().toString());
			PaymentSplitFactory.getRemoteInstance().traceData(idList);
			super.actionVoucher_actionPerformed(e);
		}
	}

    public  KDMenuItem getAttachMenuItem( KDTable table )
    {
//    	if (getTableForCommon()!=null||getDetailTable()!=null)
//    	{
//    		KDMenuItem item = new KDMenuItem();
//            item.setName("menuItemAttachment");
//            item.setAction(new ActionAttachMent());
//            return item;
//    	}
    	return null;

    }
//  分录附件
    class ActionAttachMent extends ItemAction {
    	public ActionAttachMent() {
//            String _tempStr =EASResource.getString(FrameWorkClientUtils.strResource +"SubAttachMentText");
//            String _tempStr =EASResource.getString(FrameWorkClientUtils.strResource);
//            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
//            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
//            this.putValue(ItemAction.NAME, _tempStr);
         }
//        public void actionPerformed(ActionEvent e) {
//        	showSubAttacheMent(null);
//        }
    }
    
	
	private boolean isMeasure=false;
	protected boolean isMeasureContract() {
		return isMeasure;
	}
	
	/**
	 * 量价合同计算逻辑
	 * @param e
	 * @param row
	 */
	protected void handleMeasureCalc(KDTEditEvent e, final IRow row) {
		//量价汇总
		FDCSplitBillEntryInfo entry= (FDCSplitBillEntryInfo)row.getUserObject();
		BigDecimal workLoad=FDCHelper.divide(entry.getAmount(), row.getCell("price").getValue());
		if(workLoad!=null&&workLoad.signum()==0){
			workLoad=null;
		}
		entry.setWorkLoad(workLoad);
		row.getCell("workLoad").setValue(workLoad);
		setMeasureCtrl(row);
	}
	
	public void actionRemove_actionPerformed(ActionEvent e) throws Exception {
		checkBeforeRemove();
		super.actionRemove_actionPerformed(e);
	}
    private void checkBeforeRemove() throws Exception {
    	if(editData == null || editData.getId() == null || editData.getId().equals("")){
    		return;
    	}
		FilterInfo filter = new FilterInfo();
		filter.getFilterItems().add(new FilterItemInfo("id", editData.getId().toString()));
		filter.getFilterItems()
				.add(
						new FilterItemInfo("state",
								FDCBillStateEnum.AUDITTED_VALUE));
		if (getBizInterface().exists(filter)) {
			MsgBox.showWarning(FDCSplitClientHelper.getRes("listRemove"));
			SysUtil.abort();
		}
    }
    
    /* 
     * 重写处理键盘事件
     * @see com.kingdee.eas.fdc.basedata.client.FDCSplitBillEditUI#initCtrlListener()
     */
    protected void initCtrlListener(){
		//处理键盘delete事件
		getDetailTable().setBeforeAction(new BeforeActionListener(){
			public void beforeAction(BeforeActionEvent e)
			{
				if(BeforeActionEvent.ACTION_DELETE==e.getType()){
					for (int i = 0; i < getDetailTable().getSelectManager().size(); i++)
					{
						KDTSelectBlock block = getDetailTable().getSelectManager().get(i);
						for (int rowIndex = block.getBeginRow(); rowIndex <= block.getEndRow(); rowIndex++)
						{
							for(int colIndex=block.getBeginCol();colIndex<=block.getEndCol();colIndex++){
								int amount_index = getDetailTable().getColumnIndex(COL_AMOUT);
								int directAmt_index=getDetailTable().getColumnIndex("directAmt");
								int price_index=getDetailTable().getColumnIndex("price");
								int workLoad_index=getDetailTable().getColumnIndex("workLoad");
								int directPayAmt_index=getDetailTable().getColumnIndex("directPayedAmt");
								int directInvoiceAmt_index=getDetailTable().getColumnIndex("directInvoiceAmt");
								//如果列不是上面的列或者单元格锁定了的话，则取消事件
								if((colIndex!=amount_index
										&&colIndex!=directAmt_index
										&&colIndex!=price_index
										&&colIndex!=workLoad_index
										&&colIndex!=directPayAmt_index
										&&colIndex!=directInvoiceAmt_index)
										||(getDetailTable().getCell(rowIndex, colIndex).getStyleAttributes().isLocked())) {
									e.setCancel(true);
									continue;
								}
								try
								{
									getDetailTable().getCell(rowIndex, colIndex).setValue(FDCHelper.ZERO);
									kdtEntrys_editStopped(new KDTEditEvent(e.getSource(), null, FDCHelper.ZERO, 
											rowIndex, colIndex,false,1));
								} catch (Exception e1)
								{
									handUIException(e1);
								}
							}
//							e.setCancel(true);
						}
					}

				}
				else if(BeforeActionEvent.ACTION_PASTE==e.getType()){
/*					int col=getDetailTable().getSelectManager().getActiveColumnIndex();
					int row=getDetailTable().getSelectManager().getActiveRowIndex();
					if(col<0||row<0||getDetailTable().getCell(row, col).getStyleAttributes().isLocked()){
						e.setCancel(true);
					}*/
//					e.setCancel(true);
					getDetailTable().putClientProperty("ACTION_PASTE", "ACTION_PASTE");
				}
			}
		});
		
		getDetailTable().setAfterAction(new BeforeActionListener() {
			public void beforeAction(BeforeActionEvent e) {
				if (BeforeActionEvent.ACTION_PASTE == e.getType()) {
					getDetailTable().putClientProperty("ACTION_PASTE", null);
				}

			}
		});
		/*
		 * KDTable的KDTEditListener仅在编辑的时候触发，
		 * KDTPropertyChangeListener则是在删除，粘贴等导致单元格value发生变化的时候都会触发。
		 */
		getDetailTable().addKDTPropertyChangeListener(new KDTPropertyChangeListener(){
			public void propertyChange(KDTPropertyChangeEvent evt) {
			    // 表体单元格值发生变化
			    if ((evt.getType() == KDTStyleConstants.BODY_ROW) && (evt.getPropertyName().equals(KDTStyleConstants.CELL_VALUE)))
			    {
			    	if(getDetailTable().getClientProperty("ACTION_PASTE")!=null){
			    		//触发editStop事件
			    		int rowIndex = evt.getRowIndex();
			    		int colIndex = evt.getColIndex();
			    		KDTEditEvent event=new KDTEditEvent(getDetailTable());
			    		event.setColIndex(colIndex);
			    		event.setRowIndex(rowIndex);
			    		event.setOldValue(null);
			    		ICell cell = getDetailTable().getCell(rowIndex,colIndex);
			    		if(cell==null){
			    			return;
			    		}
			    		event.setValue(cell.getValue());
			    		try {
			    			kdtEntrys_editStopped(event);
			    			
			    		} catch (Exception e1) {
			    			handUIException(e1);
			    		}
			    	}
			    }
			}
		});
	}
    
	 public void actionAcctSelect_actionPerformed(ActionEvent arg0) throws Exception {
		super.actionAcctSelect_actionPerformed(arg0);
		if (this.getUIContext().get("isCanEnable") != null) {
			Boolean flag = Boolean.valueOf(this.getUIContext().get("isCanEnable").toString());
			if (!flag.booleanValue()) {
				this.btnAutoMatchSplit.setEnabled(false);
				this.btnSplitBaseOnProp.setEnabled(false);
				this.menuItemAutoMatchSplit.setEnabled(false);
			}
		}
		updateAccAimt();
	}
		protected void setTransSelector(){
			
			Map temp = this.getEnumEntrySelecMap();
			if(temp==null){
				temp =new  HashMap();
			}
			Map transSelector = new HashMap();
			transSelector.put("contractAmt", COL_AMOUT);
		transSelector.put("shouldPayAmt", COL_AMOUT);
			transSelector.put("costWorkLoad", "workLoad");
			transSelector.put("price", "price");
			transSelector.put(COL_AMOUT, null);
			transSelector.put("workLoad", null);
			temp.put(CostSplitBillTypeEnum.CONTRACTSPLIT, transSelector);
			transSelector = new HashMap();
			// 变更没有合同拆分金额by hpw
		// transSelector.put("contractAmt", COL_AMOUT);
		transSelector.put("changeAmt", COL_AMOUT);
		transSelector.put("shouldPayAmt", COL_AMOUT);
			transSelector.put("costWorkLoad", "workLoad");
			transSelector.put("price", "price");
			transSelector.put(COL_AMOUT, null);
			transSelector.put("workLoad", null);
			temp.put(CostSplitBillTypeEnum.CNTRCHANGESPLIT, transSelector);
			this.setEnumEntrySelecMap(temp);
		}
		protected void loadCostSplitBatch(boolean isMerge,SelectorItemCollection entrySel) throws EASBizException, BOSException{
			setTransSelector();	
			
			loadCostSplit(getCostSplitEntryColsBatchIds(getEnumContractIdsMap(),isMerge,entrySel,getSplitIdsMap(),getEnumEntrySelecMap(),createNewDetailData(null)));
		}
		/**
		 * 在onload中处理关于工程量对与界面显示控制的初始化都写在这里
		 * 建议如有其他与工程量相关逻辑初始化的修改也都写在这里
		 * @author tim_gao
		 * @throws Exception 
		 */
		protected void initParamOnLoadForWorkLoad() throws Exception{
			if(!isLoadWorkConfirm){

			}
		}
	
		
		protected ContractBillInfo getContractRelationInfo(String conId)throws Exception{
			SelectorItemCollection sic = new SelectorItemCollection();
			sic.add(new SelectorItemInfo("id"));
			sic.add(new SelectorItemInfo("contractType.isLeaf"));
			sic.add(new SelectorItemInfo("contractType.level"));
			sic.add(new SelectorItemInfo("contractType.number"));
			sic.add(new SelectorItemInfo("contractType.longnumber"));
			sic.add(new SelectorItemInfo("contractType.name"));
			sic.add(new SelectorItemInfo("contractType.isRefProgram"));
			sic.add(new SelectorItemInfo("contractType.isWorkLoadConfirm"));
			IObjectPK tempId = new ObjectUuidPK(conId);
			ContractBillInfo tempContract = (ContractBillInfo)ContractBillFactory.getRemoteInstance().getValue(tempId, sic);
			return tempContract;
		}
		
	
	// modified by zhaoqin for R130704-0262 on 2013/07/22 start
	/**
	 * Print
	 * @see com.kingdee.eas.framework.client.AbstractCoreBillEditUI#actionPrint_actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPrint_actionPerformed(ActionEvent e) throws Exception {
		invokePrintFunction(e, true);
	}

	/**
	 * Print View
	 * @see com.kingdee.eas.framework.client.AbstractCoreBillEditUI#actionPrintPreview_actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPrintPreview_actionPerformed(ActionEvent e) throws Exception {
		invokePrintFunction(e, false);
	}

	private void invokePrintFunction(ActionEvent e, boolean print) throws Exception {
		if (StringUtils.isEmpty(editData.getString("id"))) {
			MsgBox.showWarning(this, FDCClientUtils.getRes("cantPrint"));
			return;
		}
		RENoteDataProvider data = new RENoteDataProvider(editData.getId().toString());
		KDNoteHelper appHlp = new KDNoteHelper();
		if (print) {
			appHlp.print(getTDFileName(), data, SwingUtilities.getWindowAncestor(this));
		} else {
			appHlp.printPreview(getTDFileName(), data, SwingUtilities.getWindowAncestor(this));
		}
	}

	/**
	 * 套打对应的目录
	 */
	protected String getTDFileName() {
		return "/bim/fdc/finance/paymentBill";
	}
	// modified by zhaoqin for R130704-0262 on 2013/07/22 end
	
	/*****modify by lihaiou,2013.08.14, fix bug R130813-0121****/
	public void checkBeforeAuditOrUnAudit(FDCBillStateEnum state, String warning) throws Exception {

		isSameUserForUnAudit = FDCUtils.getDefaultFDCParamByKey(null, null, FDCConstants.FDC_PARAM_AUDITORMUSTBETHESAMEUSER);

		if (isSameUserForUnAudit && editData.getAuditor() != null) {

			if (!SysContext.getSysContext().getCurrentUserInfo().getId().equals(editData.getAuditor().getId())) {
				try {
					throw new FDCBasedataException(FDCBasedataException.AUDITORMUSTBETHESAMEUSER);
				} catch (FDCBasedataException e) {
					handUIExceptionAndAbort(e);
				}
			}
		}
		//检查单据是否在工作流中
		FDCClientUtils.checkBillInWorkflow(this, getSelectBOID());

		if (getOprtState().equals(STATUS_EDIT)) {
			String warn = null;
			if (state.equals(FDCBillStateEnum.AUDITTED)) {
				warn = "cantUnAuditEditState";
			} else {
				warn = "cantAuditEditState";
			}
			MsgBox.showWarning(this, FDCClientUtils.getRes(warn));
			SysUtil.abort();
		}
	}
	public void actionAudit_actionPerformed(ActionEvent e) throws Exception {
		if (isModify()) {
			FDCMsgBox.showInfo("单据已被修改，请先提交。");
			this.abort();
		}

		checkBeforeAuditOrUnAudit(FDCBillStateEnum.SUBMITTED, "cantAudit");
		FDCBillStateEnum billState = editData.getState();
		if (billState.equals(FDCBillStateEnum.AUDITTED)) {
			FDCMsgBox.showInfo("单据已被审批!");
			this.abort();
		}
		String id = getSelectBOID();
		if (id != null) {
			((IFDCBill) getBizInterface()).audit(BOSUuid.read(id));
		}
		FDCClientUtils.showOprtOK(this);
		syncDataFromDB();
		handleOldData();
		setSaveActionStatus();
	}
	public void actionUnAudit_actionPerformed(ActionEvent e) throws Exception {

		checkBeforeAuditOrUnAudit(FDCBillStateEnum.AUDITTED, "cantUnAudit");
		checkRef(getSelectBOID());
		FDCBillStateEnum billState = editData.getState();
		if (!billState.equals(FDCBillStateEnum.AUDITTED)) {
			FDCMsgBox.showInfo("单据未审批，不能反审批!");
			this.abort();
		}
		String id = getSelectBOID();
		if (id != null) {
			((IFDCBill) getBizInterface()).unAudit(BOSUuid.read(id));
		}
		FDCClientUtils.showOprtOK(this);
		syncDataFromDB();
		handleOldData();
		setSaveActionStatus();
		
	}

	protected void setSaveActionStatus() {
		if (editData.getState() == FDCBillStateEnum.SUBMITTED) {
			actionSave.setEnabled(false);
			actionAudit.setVisible(true);
			actionAudit.setEnabled(true);
			actionUnAudit.setVisible(false);
			actionUnAudit.setEnabled(false);
		} else if (editData.getState() == FDCBillStateEnum.AUDITTED) {
			actionAudit.setVisible(false);
			actionAudit.setEnabled(false);
			actionUnAudit.setVisible(true);
			actionUnAudit.setEnabled(true);
		} else if (editData.getState() == FDCBillStateEnum.SAVED) {
			actionAudit.setVisible(true);
			actionAudit.setEnabled(true);
			actionUnAudit.setVisible(false);
			actionUnAudit.setEnabled(false);
		}
	}
	/*********** modify end ***************/

	/**
	 * 描述：获取初始化参数
	 * 
	 * @throws Exception
	 * @author RD_skyiter_wang
	 * @createDate 2014-1-9
	 * @see com.kingdee.eas.fdc.basedata.client.FDCBillEditUI#fetchInitParam()
	 */
	protected void fetchInitParam() throws Exception {
		super.fetchInitParam();

		if (company == null) {
			return;
		}

		// 启用成本财务一体化
		Map paramItem = (Map) ActionCache.get("FDCBillEditUIHandler.comParamItem");
		if (FdcMapUtil.isEmpty(paramItem)) {
			isMerge = FDCUtils.getBooleanValue4FDCParamByKey(null, company.getId().toString(),
					FDCConstants.FDC351_IS_PAY_SPLIT_MERGE);
		} else {
			isMerge = FdcMapUtil.getBooleanValue(paramItem, FDCConstants.FDC351_IS_PAY_SPLIT_MERGE, false);
		}

		String costBillID;
		costBillID = (String) getUIContext().get("costBillID");
		// 获取付款单和付款申请单
		fetchPaymentBillAndPayRequestBill(costBillID);
	}

	/**
	 * 描述：合并成本科目
	 * 
	 * @return
	 * @author skyiter_wang
	 * @createDate 2013-12-25
	 * @see com.kingdee.eas.fdc.basedata.client.FDCSplitBillEditUI#isMerge()
	 */
	public boolean isMerge() {
		// return isMerge;

		return super.isMerge();
	}
	
	/**
	 * 描述：统计总金额
	 * 
	 * @param key
	 * @author rd_skyiter_wang
	 * @createDate 2014-12-30
	 */
	protected void calAmtTotal(String key) {
		KDTable table = getDetailTable();
		if (FDCTableHelper.isEmpty(table)) {
			return;
		}

		BigDecimal amountTotal = FDCHelper.ZERO;
		BigDecimal amount = FDCHelper.ZERO;
		PaymentSplitEntryInfo entry = null;
		// 计算拆分总金额
		for (int i = 0; i < table.getRowCount(); i++) {
			entry = (PaymentSplitEntryInfo) table.getRow(i).getUserObject();
			if (entry.getLevel() == 0) {
				IRow row = table.getRow(i);

				amount = (BigDecimal) row.getCell(key).getValue();
				if (amount != null) {
					amountTotal = amountTotal.add(amount);
				}
			}
		}
		getDetailTable().getCell(0, key).setValue(amountTotal);
	}
	
}