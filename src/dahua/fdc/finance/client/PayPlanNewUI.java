/**
 * output package name
 */
package com.kingdee.eas.fdc.finance.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.ctrl.extendcontrols.BizDataFormat;
import com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox;
import com.kingdee.bos.ctrl.kdf.table.IColumn;
import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.kdf.table.KDTableHelper;
import com.kingdee.bos.ctrl.kdf.table.event.KDTEditAdapter;
import com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent;
import com.kingdee.bos.ctrl.kdf.table.render.RenderObject;
import com.kingdee.bos.ctrl.kdf.table.util.KDTableUtil;
import com.kingdee.bos.ctrl.kdf.util.render.ObjectValueRender;
import com.kingdee.bos.ctrl.kdf.util.render.TextIconRender;
import com.kingdee.bos.ctrl.kdf.util.style.Style;
import com.kingdee.bos.ctrl.swing.KDComboBox;
import com.kingdee.bos.ctrl.swing.KDFormattedTextField;
import com.kingdee.bos.ctrl.swing.KDWorkButton;
import com.kingdee.bos.ctrl.swing.event.CommitEvent;
import com.kingdee.bos.ctrl.swing.event.CommitListener;
import com.kingdee.bos.ctrl.swing.event.SelectorEvent;
import com.kingdee.bos.ctrl.swing.event.SelectorListener;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.base.param.IParamControl;
import com.kingdee.eas.base.param.ParamControlFactory;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.common.client.OprtState;
import com.kingdee.eas.common.client.UIContext;
import com.kingdee.eas.fdc.basedata.CostAccountInfo;
import com.kingdee.eas.fdc.basedata.CurProjectInfo;
import com.kingdee.eas.fdc.basedata.FDCConstants;
import com.kingdee.eas.fdc.basedata.FDCHelper;
import com.kingdee.eas.fdc.basedata.PaymentTypeCollection;
import com.kingdee.eas.fdc.basedata.PaymentTypeFactory;
import com.kingdee.eas.fdc.basedata.PaymentTypeInfo;
import com.kingdee.eas.fdc.basedata.client.FDCMsgBox;
import com.kingdee.eas.fdc.basedata.client.FDCTableHelper;
import com.kingdee.eas.fdc.contract.programming.ProgrammingContracCostInfo;
import com.kingdee.eas.fdc.contract.programming.ProgrammingContractInfo;
import com.kingdee.eas.fdc.contract.programming.ProgrammingInfo;
import com.kingdee.eas.fdc.contract.programming.client.CostAccountPromptBox;
import com.kingdee.eas.fdc.dahuaschedule.schedule.DahuaScheduleEntryCollection;
import com.kingdee.eas.fdc.dahuaschedule.schedule.DahuaScheduleEntryFactory;
import com.kingdee.eas.fdc.dahuaschedule.schedule.DahuaScheduleEntryInfo;
import com.kingdee.eas.fdc.finance.CalStandardEnum;
import com.kingdee.eas.fdc.finance.CalTypeEnum;
import com.kingdee.eas.fdc.finance.PayPlanModeEnum;
import com.kingdee.eas.fdc.finance.PayPlanNewByMonthInfo;
import com.kingdee.eas.fdc.finance.PayPlanNewByScheduleCollection;
import com.kingdee.eas.fdc.finance.PayPlanNewByScheduleDatazCollection;
import com.kingdee.eas.fdc.finance.PayPlanNewByScheduleDatazInfo;
import com.kingdee.eas.fdc.finance.PayPlanNewByScheduleInfo;
import com.kingdee.eas.fdc.finance.PayPlanNewByScheduleTaskCollection;
import com.kingdee.eas.fdc.finance.PayPlanNewByScheduleTaskInfo;
import com.kingdee.eas.fdc.finance.PayPlanNewByScheduleTaskNameCollection;
import com.kingdee.eas.fdc.finance.PayPlanNewByScheduleTaskNameInfo;
import com.kingdee.eas.fdc.finance.PayPlanNewCollection;
import com.kingdee.eas.fdc.finance.PayPlanNewDataCollection;
import com.kingdee.eas.fdc.finance.PayPlanNewDataInfo;
import com.kingdee.eas.fdc.finance.PayPlanNewFactory;
import com.kingdee.eas.fdc.finance.PayPlanNewInfo;
import com.kingdee.eas.fdc.finance.PrepayWriteOffEnum;
import com.kingdee.eas.fdc.finance.client.util.PayPlanClientUtil;
import com.kingdee.eas.fdc.schedule.FDCScheduleTaskInfo;
import com.kingdee.eas.framework.ICoreBase;
import com.kingdee.eas.framework.client.FrameWorkClientUtils;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.eas.util.client.EASResource;
import com.kingdee.eas.util.client.MsgBox;
import com.kingdee.util.DateTimeUtils;
import com.kingdee.util.NumericExceptionSubItem;
import com.kingdee.util.StringUtils;

public class PayPlanNewUI extends AbstractPayPlanNewUI {
	
	private static final String BIZ_TYPE_CASHFLOW = "+BONIPDySL67xM7mEm9tXmLF6cA=";

	private static final String COL_PLAN_PAY_AMOUNT = "planPayAmount";

	private static final String COL_TASK_MEASURE_AMOUNT = "taskMeasureAmount";

	private static final String COL_CAL_TYPE = "calType";

	private static final String COL_WRITE_OFF_TYPE = "writeOffType";

	private static final String COL_CAL_STANDARD = "calStandard";

	private static final String COL_PAY_SCALE = "payScale";

	private static final String COL_DELAY_DAY = "delayDay";
	
	private static final String COL_TASKNAME = "taskName";

	private static final String COL_TASK = "task";

	private static final String COL_SCHEDULE = "scheduleTask";
	
	private static final String COL_COST_ACCOUNT = "costAccount";

	private static final String COL_PAYMENT_TYPE = "paymentType";

	private static final String COL_PLAN_PAY_DATE = "planPayDate";

	private static final String COL_END_DATE = "endDate";

	private static final String COL_BEGIN_DATE = "beginDate";

	private static final Logger logger = CoreUIObject.getLogger(PayPlanNewUI.class);

	public KDWorkButton btnAddnewLine;
	public KDWorkButton btnInsertLine;
	public KDWorkButton btnRemoveLines;
	public KDWorkButton btnCopyLines;

	//合约规划
	private ProgrammingContractInfo pInfo;

	private KDBizPromptBox prmtCostAccountBySchedule;

	private KDBizPromptBox prmtTask;
	
	private KDBizPromptBox prmtTaskName;

	protected KDBizPromptBox prmtCostAccountByMonth;
	
	//0 一次性冲销且不可修改#
	//1 一次性冲销且可修改#
	//2 分批冲销且不可修改#
	//3 分批冲销且可修改
	protected int prePayParam = 0;
	
	protected List taskList = null;
	public BigDecimal planAmount = BigDecimal.ZERO;
	private DahuaScheduleEntryInfo dsInfo = null;

	public PayPlanNewUI() throws Exception {
		super();
	}
	
	public void onLoad() throws Exception {
		queryData();
		initTable(); 
		super.onLoad();
		actionUpdateBySchedule.setVisible(false);
		String id = (String) getUIContext().get("programmingId");
		if(id != null){
			DahuaScheduleEntryCollection dscoll = null;
			dscoll=DahuaScheduleEntryFactory.getRemoteInstance().getDahuaScheduleEntryCollection(" where progamming='"+id+"'");
			if(dscoll.size() > 0)
				dsInfo = dscoll.get(0);
		}
	}
	/**
	 * @see com.kingdee.eas.framework.client.EditUI#actionEdit_actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionEdit_actionPerformed(ActionEvent e) throws Exception {
		super.actionEdit_actionPerformed(e);
		btnAddnewLine.setEnabled(true);
		btnRemoveLines.setEnabled(true);
		btnInsertLine.setEnabled(true);
	}
	
	protected void queryData() throws Exception {
		HashMap key = new HashMap();
		key.put(FDCConstants.FDC551_PREPEYMENTAPPROVE, null);
        IParamControl pc = ParamControlFactory.getRemoteInstance();
        HashMap param = pc.getParamHashMap(key);
        
        prePayParam =  new Integer(param.get(FDCConstants.FDC551_PREPEYMENTAPPROVE).toString());
		
		pInfo = (ProgrammingContractInfo) getUIContext().get("programming");
		
		if(pInfo.containsKey("PayPlan")){
			PayPlanNewInfo info = (PayPlanNewInfo) pInfo.get("PayPlan");
			getUIContext().put(UIContext.INIT_DATAOBJECT, info);
			setOprtState(OprtState.ADDNEW);
			return;
		}

		String id = (String) getUIContext().get("programmingId");
		if (id != null) {
			PayPlanNewCollection coll = PayPlanNewFactory
					.getRemoteInstance().getPayPlanNewCollection(
							" where programming.id = '" + id + "'");
			
			if(coll != null && coll.get(0) != null){
				PayPlanNewInfo info = coll.get(0);
				getUIContext().put(UIContext.ID, info.getId().toString());
				if(OprtState.ADDNEW.equals(getOprtState())){
					setOprtState(OprtState.EDIT);
				}
			}else{
				setOprtState(OprtState.ADDNEW);
			}
		}else {
			setOprtState(OprtState.ADDNEW);
		}
	}
	
	protected List getTaskList() {
		if(taskList == null){
			CurProjectInfo curProject = (CurProjectInfo) getUIContext().get("project");
			String projectID = curProject.getId().toString();
			try {
				taskList = PayPlanNewFactory.getRemoteInstance().getFDCSchTasksListByProjectID(projectID );
			} catch (Exception e) {
				e.printStackTrace();
				this.handleException(e);
			}
		}
		return taskList;
	}

	protected void initTable() throws BOSException {
		initTableBySchedule();

	}
	protected void initTableByScheduleOldMethod() throws BOSException {
		KDComboBox comboCalType = PayPlanClientUtil.initComboBoxCell(tblBySchedule,COL_CAL_TYPE);
		comboCalType.addItems(CalTypeEnum.getEnumList().toArray());
		KDComboBox comboCalStandard = PayPlanClientUtil.initComboBoxCell(tblBySchedule, COL_CAL_STANDARD);
		comboCalStandard.addItems(CalStandardEnum.getEnumList().toArray());
		KDComboBox comboWriteOffType = PayPlanClientUtil.initComboBoxCell(tblBySchedule,COL_WRITE_OFF_TYPE);
		comboWriteOffType.addItems(PrepayWriteOffEnum.getEnumList().toArray());
//		tblBySchedule.getColumn(COL_DELAY_DAY).setRenderer(new DelayDateCellRender());
		
		prmtCostAccountBySchedule = PayPlanClientUtil.initF7Cell(tblBySchedule,COL_COST_ACCOUNT);
		CostAccountPromptBox selector = new CostAccountPromptBox(this);
		prmtCostAccountBySchedule.setSelector(selector);
		prmtCostAccountBySchedule.addSelectorListener(new SelectorListener() {
			public void willShow(SelectorEvent e) {
				initPrmtCostAccount(true);
			}
		});
		
		prmtCostAccountBySchedule.addCommitListener(new CommitListener() {
			public void willCommit(CommitEvent e) {
				initPrmtCostAccount(true);
			}
		});
	}
	protected void initTableBySchedule() throws BOSException {
		tblBySchedule.checkParsed(); 
		tblBySchedule.getColumn(COL_TASK).getStyleAttributes().setHided(true);
		tblBySchedule.getColumn(COL_TASKNAME).getStyleAttributes().setHided(true);
		tblBySchedule.getColumn(COL_COST_ACCOUNT).getStyleAttributes().setHided(true);
		tblBySchedule.getColumn(COL_TASK_MEASURE_AMOUNT).getStyleAttributes().setHided(true);
		tblBySchedule.getColumn(COL_WRITE_OFF_TYPE).getStyleAttributes().setHided(true);
		tblBySchedule.getColumn(COL_CAL_STANDARD).getStyleAttributes().setHided(true);
		// modify by yxl 20150909
		tblBySchedule.getColumn(COL_CAL_TYPE).getStyleAttributes().setHided(true);	
		tblBySchedule.getColumn(COL_DELAY_DAY).getStyleAttributes().setHided(true);	
		tblBySchedule.getColumn(COL_PLAN_PAY_DATE).getStyleAttributes().setHided(true);	
		tblBySchedule.getHeadRow(0).getCell(COL_PAY_SCALE).setValue("比例(%)");
		tblBySchedule.getHeadRow(0).getCell(COL_PLAN_PAY_AMOUNT).setValue("金额");
		tblBySchedule.getHeadRow(0).getCell(COL_SCHEDULE).setValue("支付节点");
		//付款类型由基础资料转换下拉列表
		KDComboBox comboPaymentType = PayPlanClientUtil.initComboBoxCell(tblBySchedule, COL_PAYMENT_TYPE);
		PaymentTypeCollection coll = PaymentTypeFactory.getRemoteInstance().getPaymentTypeCollection("order by number");
		comboPaymentType.addItems(coll.toArray());
		//比例
		KDFormattedTextField txt = PayPlanClientUtil.initFormattedTextCell(tblBySchedule, COL_PAY_SCALE, 2);
		txt.setMinimumValue(new BigDecimal(1));
		txt.setMaximumValue(new BigDecimal(100));
		txt.setNegatived(false);
		txt.setDataType(BigDecimal.class);
		txt = PayPlanClientUtil.initFormattedTextCell(tblBySchedule,COL_DELAY_DAY, 0);
		txt.setMinimumValue(new Integer(0));
		txt.setDataType(Integer.class);
		
		
		
		CurProjectInfo curProject = (CurProjectInfo) getUIContext().get("project");
		prmtTask = PayPlanClientUtil.initF7Cell(tblBySchedule,COL_SCHEDULE);
		FilterInfo filter = new FilterInfo();
		filter.appendFilterItem("project.id", curProject.getId().toString());
		EntityViewInfo evi = new EntityViewInfo();
		evi.setFilter(filter);
//		filter.appendFilterItem("businessType.id", BIZ_TYPE_CASHFLOW);  project.id
		prmtTask.setDisplayFormat("$task$");
		prmtTask.setEditFormat("$number$");
		prmtTask.setCommitFormat("$id$");
		prmtTask.setEnabledMultiSelection(false); 
		prmtTask.setEntityViewInfo(evi);
		prmtTask.setQueryInfo("com.kingdee.eas.fdc.contract.programming.app.ScheduleForProgrammingContract");
		ObjectValueRender scheduleTask_ovr = new ObjectValueRender();
		scheduleTask_ovr.setFormat(new BizDataFormat("$task$"));
		tblBySchedule.getColumn(COL_SCHEDULE).setRenderer(scheduleTask_ovr);
//		prmtTask.setSelector(new F7ScheduleTaskPromptBox(curProject.getId().toString(), false));
//		tblBySchedule.getColumn(COL_TASK).setRenderer(new FDCScheduleTaskCellRender());
		//暂时放开
		//tblBySchedule.getColumn(COL_TASK).getStyleAttributes().setHided(true);
		
//		prmtTaskName = PayPlanClientUtil.initF7Cell(tblBySchedule,COL_TASKNAME);
//		filter = new FilterInfo();
//		filter.appendFilterItem("schedule.project.id", curProject.getId().toString());
////		filter.appendFilterItem("businessType.id", BIZ_TYPE_CASHFLOW);
//		prmtTaskName.setDisplayFormat("$name$");
//		prmtTaskName.setEditFormat("$name$");
////		prmtTaskName.setCommitFormat("$id$");
//		prmtTaskName.setEnabledMultiSelection(false);
//		prmtTaskName.setSelector(new F7ScheduleTaskPromptBox(curProject.getId().toString(), false));
//		tblBySchedule.getColumn(COL_TASKNAME).setRenderer(new FDCScheduleTaskCellRender());
//		prmtTaskName.addDataChangeListener(new DataChangeListener() {
//			public void dataChanged(DataChangeEvent e) {
//				if(e.getNewValue() != null){
//					KDTable tblMain = getTable();
//					if (tblMain.getSelectManager().size() > 0) {
//						int top = tblMain.getSelectManager().get().getTop();
//						IRow row = tblMain.getRow(top);
//						row.getCell(COL_TASK).setValue(e.getNewValue());
//					}
//				}else{
//					KDTable tblMain = getTable();
//
//					if (tblMain.getSelectManager().size() > 0) {
//						int top = tblMain.getSelectManager().get().getTop();
//						IRow row = tblMain.getRow(top);
//						row.getCell(COL_TASK).setValue(null);
//					}
//				}
//			}
//		});
		
		//付款类型的融合，暂时关闭
//		tblBySchedule.getColumn(COL_PAYMENT_TYPE).setMergeable(true);
//		tblBySchedule.getColumn(COL_PAYMENT_TYPE).setGroup(true);
//		tblBySchedule.getMergeManager().setMergeMode(KDTMergeManager.SPECIFY_MERGE);
//		tblBySchedule.getMergeManager().setDataMode(KDTMergeManager.DATA_UNIFY);

		tblBySchedule.addKDTEditListener(new KDTEditAdapter() {
			public void editStopped(KDTEditEvent e) {
//				final KDTEditEvent evt = e;
//				SwingUtilities.invokeLater(new Runnable() {
//					public void run() {
//						PayPlanClientUtil.doAutoMerge(tblBySchedule,COL_PAYMENT_TYPE,evt);
//					}
//				});

				tblBySchedule_editStopped(e);
			}

			public void editStarting(KDTEditEvent e) {
//				tblBySchedule_editStarting(e);
			}

		});
		
		PayPlanClientUtil.initDateCell(tblBySchedule, COL_BEGIN_DATE);
		PayPlanClientUtil.initDateCell(tblBySchedule, COL_END_DATE);
		PayPlanClientUtil.initFormattedTextCell(tblBySchedule, COL_PLAN_PAY_AMOUNT, 2);
		// modify by yxl 
//		tblBySchedule.getColumn(COL_BEGIN_DATE).setRenderer(new BeginDateCellRender());
//		tblBySchedule.getColumn(COL_END_DATE).setRenderer(new EndDateCellRender());
//		PayPlanClientUtil.initDateCell(tblBySchedule, COL_PLAN_PAY_DATE);
//		tblBySchedule.getColumn(COL_PLAN_PAY_DATE).setRenderer(new PlanPayDateCellRender());
//		PayPlanClientUtil.initFormattedTextCell(tblBySchedule, COL_TASK_MEASURE_AMOUNT, 2);

		//		tblBySchedule.getColumn(COL_CAL_TYPE).getStyleAttributes().setLocked(true);
		//		tblBySchedule.getColumn(COL_CAL_STANDARD).setRequired(true);
//		tblBySchedule.getColumn(COL_WRITE_OFF_TYPE).setRequired(false);
		tblBySchedule.getColumn(COL_PAYMENT_TYPE).setRequired(true);
		//		tblBySchedule.getColumn(COL_TASK).setRequired(true);
		//		tblBySchedule.getColumn(COL_TASKNAME).setRequired(true);
//		tblBySchedule.getColumn(COL_COST_ACCOUNT).setRequired(true);
		tblBySchedule.getColumn(COL_PAY_SCALE).setRequired(true);
//		tblBySchedule.getColumn(COL_TASK_MEASURE_AMOUNT).setRequired(true);
		
//		if(prePayParam == 0){
//			initLockColumn(tblBySchedule.getColumn(COL_WRITE_OFF_TYPE));
//		}else if(prePayParam == 1){
//		}else if(prePayParam == 2){
//			initLockColumn(tblBySchedule.getColumn(COL_WRITE_OFF_TYPE));
//		}else if(prePayParam == 3){
//		}
		
		// modify by yxl 20150909
//		initLockColumn(tblBySchedule.getColumn(COL_BEGIN_DATE));
//		initLockColumn(tblBySchedule.getColumn(COL_PLAN_PAY_AMOUNT));
		initLockColumn(tblBySchedule.getColumn(COL_END_DATE));
		//		initLockColumn(tblBySchedule.getColumn(COL_PLAN_PAY_DATE));
//		initLockColumn(tblBySchedule.getColumn(COL_CAL_TYPE));
	}
	
	protected void initLockColumn(IColumn column){
		if(column != null){
			column.getStyleAttributes().setLocked(true);
			column.getStyleAttributes().setBackground(FDCTableHelper.cantEditColor);
		}
	}


	protected void tblBySchedule_editStarting(KDTEditEvent e) {
		IRow row = tblBySchedule.getRow(e.getRowIndex());
		PayPlanNewByScheduleInfo info = (PayPlanNewByScheduleInfo) row.getUserObject();
		if(info.getSrcID() != null && e.getColIndex() == tblBySchedule.getColumnIndex(COL_PAYMENT_TYPE)){
			e.setCancel(true);
			return;
		}
		if (e.getColIndex() == tblBySchedule.getColumnIndex(COL_WRITE_OFF_TYPE)) {
			Object value = row.getCell(COL_PAYMENT_TYPE).getValue();
			if (value instanceof PaymentTypeInfo) {
				PaymentTypeInfo pInfo = (PaymentTypeInfo) value;
				if (!pInfo.isPreType()) {
					e.setCancel(true);
					return;
				}
			} else {
				e.setCancel(true);
				return;
			}
		}
	}
	
	public void updateAmount(BigDecimal amount){
		planAmount = amount;
		BigDecimal sumAmount = FDCHelper.ZERO;
		for (int i = 0; i < tblBySchedule.getRowCount(); i++) {
			IRow row = tblBySchedule.getRow(i);
			BigDecimal scale = (BigDecimal)row.getCell(COL_PAY_SCALE).getValue();
			if(scale == null)
				continue;
			scale = scale.multiply(planAmount).divide(FDCHelper.ONE_HUNDRED);
			row.getCell(COL_PLAN_PAY_AMOUNT).setValue(scale);
			Date beginDate = (Date)row.getCell(COL_BEGIN_DATE).getValue();
			Date endDate = (Date)row.getCell(COL_END_DATE).getValue();
			if(beginDate!=null && endDate!=null)
				calMonthAmount(beginDate,endDate,scale);
//			BigDecimal planPayAmount = FDCHelper.divide(
//					FDCHelper.multiply(amount, row.getCell(COL_PAY_SCALE).getValue()),
//					FDCHelper.ONE_HUNDRED);
//			if(i == tblBySchedule.getRowCount() - 1) {
//				row.getCell(COL_PLAN_PAY_AMOUNT).setValue(FDCHelper.subtract(amount, sumAmount));
//			} else {
//				row.getCell(COL_PLAN_PAY_AMOUNT).setValue(planPayAmount);
//			}
//			
//			Object paymentType = row.getCell(COL_PAYMENT_TYPE).getValue();
//			if(paymentType!=null && !((PaymentTypeInfo)paymentType).isPreType()){
//				sumAmount = sumAmount.add(planPayAmount);
//			}
//			row.getCell(COL_COST_ACCOUNT).getStyleAttributes().setBackground(Color.WHITE);
//			row.getCell(COL_TASK_MEASURE_AMOUNT).getStyleAttributes().setBackground(Color.WHITE);

			}
	}
	
	protected void tblBySchedule_editStopped(KDTEditEvent e) {
		int rowIndex = e.getRowIndex();
		int colIndex = e.getColIndex();
		IRow row = tblBySchedule.getRow(rowIndex);
		if(colIndex==tblBySchedule.getColumnIndex(COL_PAYMENT_TYPE)) {
			Object value = row.getCell(COL_PAYMENT_TYPE).getValue();
			if (value instanceof PaymentTypeInfo) {
				PaymentTypeInfo pInfo = (PaymentTypeInfo) value;
				if("02".equals(pInfo.getNumber())){
					row.getCell(COL_END_DATE).getStyleAttributes().setLocked(false);
					row.getCell(COL_END_DATE).getStyleAttributes().setBackground(Color.WHITE);
				}else{
					row.getCell(COL_END_DATE).setValue(null);
					row.getCell(COL_END_DATE).getStyleAttributes().setLocked(true);
					row.getCell(COL_END_DATE).getStyleAttributes().setBackground(FDCTableHelper.cantEditColor);
				}
//				if (!pInfo.isPreType()) {
//					row.getCell(COL_WRITE_OFF_TYPE).setValue(null);
//					return;
//				} else {
//					if (prePayParam == 0) {
//						row.getCell(COL_WRITE_OFF_TYPE).setValue(PrepayWriteOffEnum.ONCE);
//					} else if (prePayParam == 1) {
//						row.getCell(COL_WRITE_OFF_TYPE).setValue(PrepayWriteOffEnum.ONCE);
//					} else if (prePayParam == 2) {
//						row.getCell(COL_WRITE_OFF_TYPE).setValue(PrepayWriteOffEnum.BATCH);
//					} else if (prePayParam == 3) {
//						row.getCell(COL_WRITE_OFF_TYPE).setValue(PrepayWriteOffEnum.BATCH);
//					}
//					return;
//				}
			}
		} else if(colIndex==tblBySchedule.getColumnIndex(COL_TASK)) {
			Object value = row.getCell(COL_TASK).getValue();
			if (value != null) {
				row.getCell(COL_CAL_TYPE).getStyleAttributes().setLocked(false);
				row.getCell(COL_CAL_TYPE).getStyleAttributes().setBackground(Color.WHITE);
				row.getCell(COL_DELAY_DAY).getStyleAttributes().setBackground(Color.WHITE);
				row.getCell(COL_PLAN_PAY_DATE).getStyleAttributes().setLocked(true);
				row.getCell(COL_PLAN_PAY_DATE).getStyleAttributes().setBackground(FDCTableHelper.cantEditColor);
			}else{
				row.getCell(COL_CAL_TYPE).setValue(CalTypeEnum.TIME);
				row.getCell(COL_CAL_TYPE).getStyleAttributes().setLocked(true);
				row.getCell(COL_CAL_TYPE).getStyleAttributes().setBackground(FDCTableHelper.cantEditColor);
				row.getCell(COL_DELAY_DAY).getStyleAttributes().setBackground(FDCTableHelper.cantEditColor);
				row.getCell(COL_PLAN_PAY_DATE).getStyleAttributes().setLocked(false);
				row.getCell(COL_PLAN_PAY_DATE).getStyleAttributes().setBackground(FDCTableHelper.requiredColor);

			}
		} else if(colIndex==tblBySchedule.getColumnIndex(COL_BEGIN_DATE) && row.getCell(colIndex).getValue()!=null) {
			Object value = row.getCell(COL_PAYMENT_TYPE).getValue();
			if (value instanceof PaymentTypeInfo) {
				PaymentTypeInfo pInfo = (PaymentTypeInfo) value;
				if("02".equals(pInfo.getNumber()) && row.getCell(COL_END_DATE).getValue()!=null){
					Date beginDate = (Date)row.getCell(colIndex).getValue();
					Date endDate = (Date)row.getCell(COL_END_DATE).getValue();
					if(beginDate.compareTo(endDate) > 0){
						FDCMsgBox.showInfo("开始日期应在结束日期之前");
						return;
					}
					if(row.getCell(COL_PLAN_PAY_AMOUNT).getValue()!=null)
						calMonthAmount(beginDate,endDate,(BigDecimal)row.getCell(COL_PLAN_PAY_AMOUNT).getValue());
				}
			}
			
		} else if(colIndex==tblBySchedule.getColumnIndex(COL_END_DATE) && row.getCell(colIndex).getValue()!=null) {
			Object value = row.getCell(COL_PAYMENT_TYPE).getValue();
			if (value instanceof PaymentTypeInfo) {
				PaymentTypeInfo pInfo = (PaymentTypeInfo) value;
				if("02".equals(pInfo.getNumber()) && row.getCell(COL_BEGIN_DATE).getValue()!=null){
					Date beginDate = (Date)row.getCell(COL_BEGIN_DATE).getValue();
					Date endDate = (Date)row.getCell(colIndex).getValue();
					if(beginDate.compareTo(endDate) > 0){
						FDCMsgBox.showInfo("开始日期应在结束日期之前");
						return;
					}
					if(row.getCell(COL_PLAN_PAY_AMOUNT).getValue()!=null)
						calMonthAmount(beginDate,endDate,(BigDecimal)row.getCell(COL_PLAN_PAY_AMOUNT).getValue());
				}
			}
		} else if(colIndex==tblBySchedule.getColumnIndex(COL_PLAN_PAY_AMOUNT) && row.getCell(colIndex).getValue()!=null){
			BigDecimal amount = (BigDecimal)row.getCell(colIndex).getValue();
			row.getCell(COL_PAY_SCALE).setValue(amount.multiply(FDCHelper.ONE_HUNDRED).divide(planAmount,2,RoundingMode.HALF_UP));
			Date beginDate = (Date)row.getCell(COL_BEGIN_DATE).getValue();
			Date endDate = (Date)row.getCell(COL_END_DATE).getValue();
			if(beginDate!=null && endDate!=null)
				calMonthAmount(beginDate,endDate,(BigDecimal)row.getCell(colIndex).getValue());
		} else if(colIndex==tblBySchedule.getColumnIndex(COL_PAY_SCALE) && row.getCell(colIndex).getValue()!=null){
			BigDecimal scale = (BigDecimal)row.getCell(colIndex).getValue();
			scale = scale.multiply(planAmount).divide(FDCHelper.ONE_HUNDRED);
			row.getCell(COL_PLAN_PAY_AMOUNT).setValue(scale);
			Date beginDate = (Date)row.getCell(COL_BEGIN_DATE).getValue();
			Date endDate = (Date)row.getCell(COL_END_DATE).getValue();
			if(beginDate!=null && endDate!=null)
				calMonthAmount(beginDate,endDate,scale);
		} else if(colIndex==tblBySchedule.getColumnIndex(COL_SCHEDULE) && row.getCell(colIndex).getValue()!=null){
			DahuaScheduleEntryInfo sdeinfo = (DahuaScheduleEntryInfo)row.getCell(colIndex).getValue();
			if(row.getCell(COL_PAYMENT_TYPE).getValue() == null){
				row.getCell(COL_BEGIN_DATE).setValue(sdeinfo.getStartDate());
				row.getCell(COL_END_DATE).setValue(sdeinfo.getEndDate());
			}else{
				PaymentTypeInfo pInfo = (PaymentTypeInfo)row.getCell(COL_PAYMENT_TYPE).getValue();
				if("02".equals(pInfo.getNumber())){
					row.getCell(COL_BEGIN_DATE).setValue(sdeinfo.getStartDate());
					row.getCell(COL_END_DATE).setValue(sdeinfo.getEndDate());
				}else{
					row.getCell(COL_BEGIN_DATE).setValue(sdeinfo.getStartDate());
				}
			}
		}
		
//			BigDecimal planPayAmount = FDCHelper.divide(FDCHelper.multiply(pInfo.getAmount(), row.getCell(COL_PAY_SCALE).getValue()),
//					FDCHelper.ONE_HUNDRED);
//			row.getCell(COL_PLAN_PAY_AMOUNT).setValue(planPayAmount);
//			row.getCell(COL_COST_ACCOUNT).getStyleAttributes().setBackground(Color.WHITE);
//			row.getCell(COL_TASK_MEASURE_AMOUNT).getStyleAttributes().setBackground(Color.WHITE);
		
	}

	private void calMonthAmount(Date beginDate, Date endDate,BigDecimal amount) {
		Calendar calendar = Calendar.getInstance();
		List<Integer> months = new ArrayList<Integer>();
		while(beginDate.compareTo(endDate) <= 0) {
			calendar.setTime(beginDate);
			months.add(new Integer(calendar.get(Calendar.YEAR)*100+calendar.get(Calendar.MONTH)+1));
			calendar.add(Calendar.MONTH, 1);
			beginDate = calendar.getTime();
		}
		PayPlanNewDataCollection ppdcoll = new PayPlanNewDataCollection();
		PayPlanNewDataInfo ppdinfo = null;
		amount = amount.divide(new BigDecimal(months.size()),2,RoundingMode.HALF_UP);
		for (int i = 0; i < months.size(); i++) {
			ppdinfo = new PayPlanNewDataInfo();
			ppdinfo.setPayAmount(amount);
			ppdinfo.setPayMonth(months.get(i));
			ppdcoll.add(ppdinfo);
		}
		editData.put("Data",ppdcoll);
		loadDataTable();
	}

	private void initPrmtCostAccount(boolean isBySchedule) {
		CurProjectInfo curProject = (CurProjectInfo) getUIContext().get("project");
		EntityViewInfo evi = new EntityViewInfo();
		FilterInfo filter = new FilterInfo();
		filter.getFilterItems().add(new FilterItemInfo("curProject.id", curProject.getId().toString(), CompareType.EQUALS));

		Set idSet = getCostAccountIdSet();
		if (idSet.size() > 0) {
			filter.getFilterItems().add(new FilterItemInfo("id", idSet, CompareType.INCLUDE));
		} else {
			filter.getFilterItems().add(new FilterItemInfo("id", "error"));
		}
		evi.setFilter(filter);
		if(isBySchedule){
			prmtCostAccountBySchedule.setEntityViewInfo(evi);
			prmtCostAccountBySchedule.getQueryAgent().resetRuntimeEntityView();
		}else{
			prmtCostAccountByMonth.setEntityViewInfo(evi);
			prmtCostAccountByMonth.getQueryAgent().resetRuntimeEntityView();
		}
	}

	public void loadFields() {
		super.loadFields();
		if (PayPlanModeEnum.BYSCHEDULE.equals(editData.getMode())) {
			btnBySchedule.setSelected(true);
			isBySchedule = true;
			for (int i = 0; i < tblBySchedule.getRowCount(); i++) {
				IRow row = tblBySchedule.getRow(i);
//				PayPlanNewByScheduleInfo info = (PayPlanNewByScheduleInfo) row.getUserObject();
//				List lst = new ArrayList();
//				if(info.getTask() != null && info.getTask().size() > 0){
//					for (int j = 0; j < info.getTask().size(); j++) {
//						lst.add(info.getTask().get(j).getTask());
//					}
//				}
//				row.getCell(COL_TASK).setValue(lst.toArray());
//				if (lst.size() == 0) {
//					row.getCell(COL_PLAN_PAY_DATE).getStyleAttributes().setBackground(FDCTableHelper.requiredColor);
//					row.getCell(COL_PLAN_PAY_DATE).getStyleAttributes().setLocked(false);
//				} else {
//					row.getCell(COL_PLAN_PAY_DATE).getStyleAttributes().setBackground(FDCTableHelper.cantEditColor);
//					row.getCell(COL_PLAN_PAY_DATE).getStyleAttributes().setLocked(true);
//				}
				Object value = row.getCell(COL_PAYMENT_TYPE).getValue();
				if (value instanceof PaymentTypeInfo) {
					PaymentTypeInfo pInfo = (PaymentTypeInfo) value;
					if("02".equals(pInfo.getNumber())){
						row.getCell(COL_END_DATE).getStyleAttributes().setLocked(false);
						row.getCell(COL_END_DATE).getStyleAttributes().setBackground(Color.WHITE);
					}
				}
				
//				lst = new ArrayList();
//				if(info.getTaskName() != null && info.getTaskName().size() > 0){
//					for (int j = 0; j < info.getTaskName().size(); j++) {
//						lst.add(info.getTaskName().get(j).getName());
//					}
//				}
//				row.getCell(COL_TASKNAME).setValue(lst.size() == 0 ? null : lst.get(0));

//				if(CalStandardEnum.SCHEDULEAMOUNT.equals(row.getCell(COL_CAL_STANDARD).getValue())){
//					row.getCell(COL_COST_ACCOUNT).getStyleAttributes().setBackground(FDCTableHelper.requiredColor);
//					row.getCell(COL_TASK_MEASURE_AMOUNT).getStyleAttributes().setBackground(FDCTableHelper.requiredColor);
//				}else{
//					row.getCell(COL_COST_ACCOUNT).getStyleAttributes().setBackground(Color.WHITE);
//					row.getCell(COL_TASK_MEASURE_AMOUNT).getStyleAttributes().setBackground(Color.WHITE);
//				}
				//modify by yxl
//				Object pObj = row.getCell(COL_PAYMENT_TYPE).getValue();
//				if(pObj instanceof PaymentTypeInfo){
//					PaymentTypeInfo pInfo = (PaymentTypeInfo) pObj;
//					if(!pInfo.isPreType()){
//						row.getCell(COL_WRITE_OFF_TYPE).setValue(null);
//					}
//				}
			}
		}
//		if (tblBySchedule.getRowCount() > 0) {
//			PayPlanClientUtil.doAutoMerge(tblBySchedule,COL_PAYMENT_TYPE,new KDTEditEvent(tblBySchedule, null, tblBySchedule
//					.getRow(0).getCell(COL_PAYMENT_TYPE).getValue(), 0,
//					tblBySchedule.getColumnIndex(COL_PAYMENT_TYPE), false,
//					KDTStyleConstants.BODY_ROW));
//		}
		updateTable();
		loadDataTable();
	}
	
	public void storeFields() {
		super.storeFields();
		if(btnBySchedule.isSelected()){
			editData.setMode(PayPlanModeEnum.BYSCHEDULE);
			//保存节点
			Map taskSchMap = new HashMap();
			for (int i = 0; i < tblBySchedule.getRowCount(); i++) {
				IRow row = tblBySchedule.getRow(i);
				PayPlanNewByScheduleInfo info = (PayPlanNewByScheduleInfo) row.getUserObject();
				info.put("Task", new PayPlanNewByScheduleTaskCollection());
				info.put("TaskName", new PayPlanNewByScheduleTaskNameCollection());
				Object obj = tblBySchedule.getCell(i, COL_TASK).getValue();
				if(obj instanceof FDCScheduleTaskInfo){
					PayPlanNewByScheduleTaskInfo bInfo = new PayPlanNewByScheduleTaskInfo();
					FDCScheduleTaskInfo task = (FDCScheduleTaskInfo) obj;
					bInfo.setTask(task);
					info.getTask().add(bInfo);
					taskSchMap.put(task.getId().toString(), info);
				} else if (obj instanceof Object[]) {
					Object[] object = (Object[]) obj;
					for (int j = 0; j < object.length; j++) {
						PayPlanNewByScheduleTaskInfo bInfo = new PayPlanNewByScheduleTaskInfo();
						FDCScheduleTaskInfo task = (FDCScheduleTaskInfo) object[j];
						bInfo.setTask(task);
						info.getTask().add(bInfo);
						taskSchMap.put(task.getId().toString(), info);
					}
				} else if (obj == null && tblBySchedule.getCell(i, COL_PLAN_PAY_DATE).getValue() != null) {
					info.setEndDate((Date) tblBySchedule.getCell(i, COL_PLAN_PAY_DATE).getValue());
				}
				
				obj = tblBySchedule.getCell(i, COL_TASKNAME).getValue();
				if(obj instanceof String){
					String name = obj.toString();
					Object value = tblBySchedule.getCell(i, COL_TASK).getValue();
					String number = "";
					if (value instanceof FDCScheduleTaskInfo) {
						FDCScheduleTaskInfo task = (FDCScheduleTaskInfo) value;
						number = task.getLongNumber();
					} else if (value instanceof Object[]) {
						Object[] object = (Object[]) value;
						FDCScheduleTaskInfo task = (FDCScheduleTaskInfo) object[0];
						number = task.getLongNumber();
					}
					if(!StringUtils.isEmpty(name)){
						PayPlanNewByScheduleTaskNameInfo bInfo = new PayPlanNewByScheduleTaskNameInfo();
						bInfo.setName(name);
						bInfo.setNumber(number);
						info.getTaskName().add(bInfo);
					}
				}else if(obj instanceof FDCScheduleTaskInfo){
					PayPlanNewByScheduleTaskNameInfo bInfo = new PayPlanNewByScheduleTaskNameInfo();
					FDCScheduleTaskInfo task = (FDCScheduleTaskInfo) obj;
					bInfo.setName(task.getName());
					bInfo.setNumber(task.getLongNumber());
					info.getTaskName().add(bInfo);
				} else if (obj instanceof Object[]) {
					Object[] object = (Object[]) obj;
					for (int j = 0; j < object.length; j++) {
						PayPlanNewByScheduleTaskNameInfo bInfo = new PayPlanNewByScheduleTaskNameInfo();
						FDCScheduleTaskInfo task = (FDCScheduleTaskInfo) object[j];
						bInfo.setName(task.getName());
						bInfo.setNumber(task.getLongNumber());
						info.getTaskName().add(bInfo);
					}
				}
			}
		}else if(btnByMonth.isSelected()){
			editData.setMode(PayPlanModeEnum.BYMONTH);
		}
		
		//传到后台计算使用，暂存情况的时候
		ProgrammingContractInfo conInfo = new ProgrammingContractInfo();
		conInfo.setAmount(pInfo.getAmount());
		conInfo.setIsLeaf(pInfo.isIsLeaf());
		editData.put("uiInfo", conInfo);
		

	}
	
	
	private Map getAmountMap() {
		Map map = new HashMap();
		int cost_rowCount = pInfo.getCostEntries().size();
		for (int i = 0; i < cost_rowCount; i++) {
			ProgrammingContracCostInfo cInfo = pInfo.getCostEntries().get(i);
			CostAccountInfo costAccount = cInfo.getCostAccount();
			if(costAccount != null){
				String key = costAccount.getId().toString();
				map.put(key, FDCHelper.add(map.get(key),cInfo.getContractAssign()));
			}
		}
		
		return map;
	}
	
	
	protected void loadDataTable() {
		tblPayPlanLst.removeColumns();
		tblPayPlanLst.checkParsed();
		PayPlanNewDataCollection datas = editData.getData();
		if(datas != null && datas.size() > 0){
			String[] columnKeys = new String[datas.size() + 1];
			Object[] head = new Object[datas.size() + 1];
			Object[][] body = new Object[1][datas.size() + 1];
			for(int i = 1; i <= datas.size();i ++){
				PayPlanNewDataInfo info = datas.get(i - 1);
				columnKeys[i] = "" + info.getPayMonth();
				head[i] = "" + info.getPayMonth()/100 + "年" + info.getPayMonth()%100 + "月"+"18日";
				body[0][i] = info.getPayAmount();
			}
			columnKeys[0] = "payMonth";
			head[0] = "付款时间";
			body[0][0] = "付款金额";
			
			KDTableHelper.initTable(tblPayPlanLst, columnKeys, head, body);
			for(int i = 1; i <= datas.size();i ++){
				PayPlanClientUtil.initFormattedTextCell(tblPayPlanLst,columnKeys[i], 2);
			}
			tblPayPlanLst.getStyleAttributes().setLocked(true);

		}
		tblPayPlanLst.reLayoutAndPaint();

	}

	protected boolean isContinueAddNew() {
		return false;
	}
	
	
	protected void beforeStoreFields(ActionEvent e) throws Exception {
		super.beforeStoreFields(e);
		// modify by yxl 
//	if(btnBySchedule.isSelected()){
//			BigDecimal sum = FDCHelper.ZERO;
//			for(int i = 0; i < tblBySchedule.getRowCount();i ++){
//				IRow row = tblBySchedule.getRow(i);
//				Object value = row.getCell(COL_PAYMENT_TYPE).getValue();
//				if(value instanceof PaymentTypeInfo){
//					PaymentTypeInfo pInfo = (PaymentTypeInfo) value;
//					if(pInfo.isPreType()){
//						continue;
//					}
//				}
//				sum = FDCHelper.add(sum, row.getCell(COL_PLAN_PAY_AMOUNT).getValue());
//			}
//			if(pInfo.getAmount() != null && sum.compareTo(pInfo.getAmount()) > 0){
//				MsgBox.showInfo("计划付款金额之和与规划金额不相等，当前付款金额之和为:" + sum.toString());
//				SysUtil.abort();
//			}
//			for (int i = 0; i < tblBySchedule.getRowCount(); i++) {
//				IRow row = tblBySchedule.getRow(i);
//				PaymentTypeInfo info = (PaymentTypeInfo) row.getCell(COL_PAYMENT_TYPE).getValue();
//				if(info != null){
//					if(info.isPreType()){
//						checkTableCellEmpty(tblBySchedule, i, COL_WRITE_OFF_TYPE, null, 0);
//					}
//					checkTableCellEmpty(tblBySchedule, i, COL_PAYMENT_TYPE, null, 0);
//					checkTableCellEmpty(tblBySchedule, i, COL_PAY_SCALE, null, 0);
//				}
//			}
//		}
	}
	public void checkTableCellEmpty(KDTable table, int rowIndex, String colName, String msg, int headRow) throws EASBizException {
		Object obj = table.getCell(rowIndex, colName).getValue();
		if (obj == null) {
			throw new EASBizException(new NumericExceptionSubItem("021", (msg == null ? "" : msg) + "第" + (rowIndex + 1) + "行的"
					+ table.getHeadRow(headRow).getCell(colName).getValue() + "不能为空。"));
		}
		if (obj instanceof Object[]) {
			Object[] objs = (Object[]) obj;
			if (objs.length < 1) {
				throw new EASBizException(new NumericExceptionSubItem("021", (msg == null ? "" : msg) + "第" + (rowIndex + 1) + "行的"
						+ table.getHeadRow(headRow).getCell(colName).getValue() + "不能为空。"));
			}
		}
	}

	protected IObjectValue createNewData() {
		PayPlanNewInfo info = new PayPlanNewInfo();
		info.setMode(PayPlanModeEnum.BYSCHEDULE);

		if (!pInfo.isIsLeaf()) {
			return info;
		}
		
		String srcId = (String) getUIContext().get("programmingSrcId");
		if (srcId != null) {
			try {
				EntityViewInfo evi = new EntityViewInfo(); 
				FilterInfo filter = new FilterInfo(); 
				filter.getFilterItems().add(new FilterItemInfo("programming.id",srcId));
				evi.setFilter(filter);
				
				evi.setSelector(getSelectors());
				
				PayPlanNewCollection coll = PayPlanNewFactory
						.getRemoteInstance().getPayPlanNewCollection(evi);
				
				if(coll != null && coll.get(0) != null){
					PayPlanNewInfo srcInfo = coll.get(0);

					info = (PayPlanNewInfo) srcInfo.clone();
					
					info.setId(null);
					PayPlanNewByScheduleCollection  sColl = info.getBySchedule();
					for(int i = 0;sColl != null && i < sColl.size();i ++){
						PayPlanNewByScheduleInfo sInfo = sColl.get(i);
						sInfo.setSrcID(sInfo.getId());
						sInfo.setId(null);
						
						PayPlanNewByScheduleTaskCollection task = sInfo.getTask();
						for (int j = 0; j < task.size(); j++) {
							PayPlanNewByScheduleTaskInfo taskInfo = task.get(j);
							taskInfo.setId(null);
						}
						
						PayPlanNewByScheduleDatazCollection dataz = sInfo.getDataz();
						for (int j = 0; j < dataz.size(); j++) {
							PayPlanNewByScheduleDatazInfo datazInfo = dataz.get(j);
							datazInfo.setId(null);
						}
					}
					
					PayPlanNewDataCollection data = info.getData();
					for (int i = 0; i < data.size(); i++) {
						PayPlanNewDataInfo dataInfo = data.get(i);
						dataInfo.setId(null);
					}
				}
			} catch (BOSException e) {
				e.printStackTrace();
			}
			

		
		}
		
		ProgrammingContractInfo proConInfo = (ProgrammingContractInfo) getUIContext().get("programming");
		info.setProgramming(proConInfo);
		
		return info;
	}

	protected IObjectValue createNewEntryData() {
		if (btnByMonth.isSelected()) {
			PayPlanNewByMonthInfo info = new PayPlanNewByMonthInfo();
			return info;

		} else if (btnBySchedule.isSelected()) {
			PayPlanNewByScheduleInfo info = new PayPlanNewByScheduleInfo();
//			if(prePayParam == 0){
//				info.setWriteOffType(PrepayWriteOffEnum.ONCE);
//			}else if(prePayParam == 1){
//				info.setWriteOffType(PrepayWriteOffEnum.ONCE);
//			}else if(prePayParam == 2){
//				info.setWriteOffType(PrepayWriteOffEnum.BATCH);
//			}else if(prePayParam == 3){
//				info.setWriteOffType(PrepayWriteOffEnum.BATCH);
//			}
//			info.setCalType(CalTypeEnum.TIME);
//			info.setCalStandard(CalStandardEnum.CONTRACTAMOUNT);
			info.setScheduleTask(dsInfo);
			return info;
		}

		return null;
	}
	
	public SelectorItemCollection getSelectors() {
		SelectorItemCollection sic = super.getSelectors();
		sic.add("*");
		sic.add("Data.*");
		sic.add("BySchedule.Task.task.name");
		sic.add("BySchedule.Task.task.longNumber");
		sic.add("BySchedule.Task.task.start");
		sic.add("BySchedule.Task.task.end");
		sic.add("BySchedule.TaskName.*");
		sic.add("BySchedule.Dataz.*");
		return sic;
	}
	
	public IObjectPK runSubmit() throws Exception {
		if(editData.getId() == null){
			editData.setId(BOSUuid.create(editData.getBOSType()));
		}
		ProgrammingContractInfo pInfo = (ProgrammingContractInfo) getUIContext().get("programming");
		ProgrammingContractInfo conInfo = new ProgrammingContractInfo();
		
		ProgrammingInfo programming = new ProgrammingInfo();
		programming.setId(pInfo.getProgramming().getId());
		conInfo.setProgramming(programming);
		
		conInfo.setAmount(pInfo.getAmount());
		conInfo.setName(pInfo.getName());
		conInfo.setId(pInfo.getId());
		
		CurProjectInfo curProject = (CurProjectInfo) getUIContext().get("project");
		conInfo.getProgramming().setProject(curProject);
		editData.setProgramming(conInfo);
		
		PayPlanNewInfo info = PayPlanNewFactory.getRemoteInstance().caculate(editData);
		if (getUIContext().get("editPayPlan") != null) {
			info.put("updateCon", true);
			PayPlanNewFactory.getRemoteInstance().submit(info);
		}
		editData = info;
		setDataObject(info);
		loadFields();
		pInfo.put("PayPlan", editData);
		
//		lockUIComponent();
		tblBySchedule.setEditable(false);
		btnAddnewLine.setEnabled(false);
		btnRemoveLines.setEnabled(false);
		btnInsertLine.setEnabled(false);

		return new ObjectUuidPK(editData.getId());
	}
	
	protected void doAfterSubmit(IObjectPK pk) throws Exception{
		loadFields();
        setSave(true);
        setSaved(true);
        initOldData(editData);
        actionEdit.setEnabled(true);
		actionSubmit.setEnabled(false);
		setOprtState(STATUS_VIEW);
	}

	protected void initWorkButton() {
		super.initWorkButton();

		chkMenuItemSubmitAndAddNew.setVisible(false);

		btnAddnewLine = new KDWorkButton();
		btnInsertLine = new KDWorkButton();
		btnRemoveLines = new KDWorkButton();
		btnCopyLines = new KDWorkButton();

		btnAddnewLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					actionAddnewLine_actionPerformed(e);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		btnInsertLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					actionInsertLine_actionPerformed(arg0);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		});

		btnRemoveLines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					actionRemoveLine_actionPerformed(e);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		btnCopyLines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					actionCopyLine_actionPerformed(e);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		setButtonStyle(btnAddnewLine, "新增行", "imgTbtn_addline");
		setButtonStyle(btnRemoveLines, "删除行", "imgTbtn_deleteline");
		setButtonStyle(btnInsertLine, "插入行", "imgTbtn_insert");
		//setButtonStyle(btnCopyLines, "复制行", "imgTbtn_copy");
		
		if(OprtState.VIEW.equals(getOprtState())){
			btnAddnewLine.setEnabled(false);
			btnRemoveLines.setEnabled(false);
			btnInsertLine.setEnabled(false);
			btnCopyLines.setEnabled(false);
			btnBySchedule.setEnabled(false);
			btnByMonth.setEnabled(false);
		}
		
	}

	private void setButtonStyle(KDWorkButton button, String text, String icon) {
		button.setText(text);
		button.setToolTipText(text);
		button.setVisible(true);
		button.setIcon(EASResource.getIcon(icon));
		contProgramming.addButton(button);
	}

	public void actionCopyLine_actionPerformed(ActionEvent e) {
		KDTable tblMain = getTable();

		IRow row = null;
		if (tblMain.getSelectManager().size() > 0) {
			int top = tblMain.getSelectManager().get().getTop();
			IRow oldRow = tblMain.getRow(top);
			IObjectValue obj = (IObjectValue) oldRow.getUserObject();
			dataBinder.storeLineFields(tblMain, oldRow, obj);
			row = tblMain.addRow(top + 1);
			dataBinder.loadLineFields(tblMain, row, obj);
		} else {
			row = tblMain.addRow();
			IObjectValue obj = createNewEntryData();
			dataBinder.loadLineFields(tblMain, row, obj);
		}

	}

	public void actionRemoveLine_actionPerformed(ActionEvent e) {
		KDTable tblMain = getTable();
		if (tblMain.getRowCount() == 0
				|| tblMain.getSelectManager().size() == 0) {
			MsgBox.showInfo("请选中行！");
			SysUtil.abort();
		}

//		int index = tblMain.getSelectManager().getActiveRowIndex();
		int[] selectRows = KDTableUtil.getSelectedRows(tblMain);
		
//		for(int i=0;i<selectRows.length;i++){
//			int index = selectRows[i];
//			if (btnByMonth.isSelected()) {
//				PayPlanNewByMonthInfo byMonth = (PayPlanNewByMonthInfo) tblMain.getRow(index).getUserObject();
//				if (byMonth.getSrcID() != null) {
//					MsgBox.showInfo("模板导入数据不能删除！");
//					SysUtil.abort();
//				}
//			}else if (btnBySchedule.isSelected()) {
//				PayPlanNewByScheduleInfo bySchedule = (PayPlanNewByScheduleInfo) tblMain.getRow(index).getUserObject();
//				if (bySchedule.getSrcID() != null) {
//					MsgBox.showInfo("模板导入数据不能删除！");
//					SysUtil.abort();
//				}
//			}
//		}
		
		
		if(FDCMsgBox.isOk(FDCMsgBox.showConfirm2("确认删除？"))){
			int i = selectRows.length;
			for(;i>=0;i--){
				int index = selectRows[i-1];
				tblMain.removeRow(index);
			}
		}
	}

	public void actionInsertLine_actionPerformed(ActionEvent arg0) {
		KDTable tblMain = getTable();
		IRow row = null;
		if (tblMain.getSelectManager().size() > 0) {
			int top = tblMain.getSelectManager().get().getTop();
			row = tblMain.addRow(top);
		} else {
			row = tblMain.addRow();
		}
		IObjectValue obj = createNewEntryData();
		dataBinder.loadLineFields(tblMain, row, obj);
	}

	public void actionAddnewLine_actionPerformed(ActionEvent e) {
		KDTable tblMain = getTable();
		IRow row = tblMain.addRow();
		IObjectValue obj = createNewEntryData();
		dataBinder.loadLineFields(tblMain, row, obj);
	}

	private KDTable getTable() {
		if (btnByMonth.isSelected()) {
			return tblByMonth;

		} else if (btnBySchedule.isSelected()) {
			return tblBySchedule;
		}

		return null;
	}

    protected void checkSelected(){
    	KDTable tblMain = getTable();
        if (tblMain.getRowCount() == 0 || tblMain.getSelectManager().size() == 0){
            MsgBox.showWarning(this, 
            		EASResource.getString(FrameWorkClientUtils.strResource + "Msg_MustSelected"));
            SysUtil.abort();
        }
    }
	
	protected ICoreBase getBizInterface() throws Exception {
		return PayPlanNewFactory.getRemoteInstance();
	}
	
	boolean isBySchedule = false;
	boolean isByMonth = false;

	protected void btnByMonth_actionPerformed(ActionEvent e) throws Exception {
		super.btnByMonth_actionPerformed(e);
		if (isByMonth) {
			return;
		}
		
		if(MsgBox.isYes(MsgBox.showConfirm2New(this,"切换通用零星合同将清空表格的数据，是否继续？"))){
			tblBySchedule.removeRows();
			updateTable();
		}else{
			btnBySchedule.setSelected(true);
		}
		setByType();
	}

	private void setByType() {
		isBySchedule = btnBySchedule.isSelected();
		isByMonth = btnByMonth.isSelected();
	}
	
	protected void btnBySchedule_actionPerformed(ActionEvent e)
			throws Exception {
		super.btnBySchedule_actionPerformed(e);
		if (isBySchedule) {
			return;
		}

		if (MsgBox.isYes(MsgBox.showConfirm2New(this, "切换通用合同将清空表格的数据，是否继续？"))) {
			tblByMonth.removeRows();
			updateTable();
		}else{
			btnByMonth.setSelected(true);
		}
		setByType();
	}

	public void actionUpdateBySchedule_actionPerformed(ActionEvent e) throws Exception {
		super.actionUpdateBySchedule_actionPerformed(e);
		if(editData.getId() == null){
			MsgBox.showInfo("请先保存！");
			return;
		}
		
		PayPlanNewFactory.getRemoteInstance().updateBySchedule(editData.getId().toString());
		
		setDataObject(getValue(new ObjectUuidPK(editData.getId())));
		
		loadFields();
	}
	
	protected void updateTable() {
		if (btnBySchedule.isSelected()) {
			contProgramming.getContentPane().remove(tblByMonth);
			contProgramming.getContentPane().remove(tblBySchedule);
			contProgramming.getContentPane().setLayout(new BorderLayout());
			contProgramming.getContentPane().add(this.tblBySchedule,BorderLayout.CENTER);
			contProgramming.getContentPane().updateUI();
			tblBySchedule.reLayoutAndPaint();
		}
	}

	protected void verifyInput(ActionEvent e) throws Exception {
		super.verifyInput(e);
		if(tblBySchedule.getRowCount()>0){
			BigDecimal payScale =FDCHelper.ZERO;
			for(int i=0;i<tblBySchedule.getRowCount();i++){
				boolean isTaskNull =false;
				Object object = tblBySchedule.getCell(i, COL_TASK).getValue();
				if(object instanceof Object[]){
					Object[] ob = (Object[]) object;
					if(ob.length==0){
						isTaskNull = true;
					}
				}else{
					if(tblBySchedule.getCell(i, COL_TASK).getValue()==null){
						isTaskNull = true;
					}
				}
				// modify by yxl 20150910
//				if(isTaskNull && tblBySchedule.getCell(i, COL_PLAN_PAY_DATE).getValue()==null){
//					FDCMsgBox.showWarning("第"+(i+1)+"行计划支付日期不能为空");
//					abort();
//				}
				if(tblBySchedule.getCell(i,COL_PAYMENT_TYPE).getValue() == null){
					FDCMsgBox.showInfo("第"+(i+1)+"行的付款类型不能为空！");
					abort();
				}
				if(tblBySchedule.getCell(i,COL_PAY_SCALE).getValue() == null){
					FDCMsgBox.showInfo("第"+(i+1)+"行的比例不能为空！");
					abort();
				}
				payScale = FDCHelper.add(payScale, tblBySchedule.getCell(i, COL_PAY_SCALE).getValue());
				
//				Object value = tblBySchedule.getCell(i, COL_PAYMENT_TYPE).getValue();
//				if (value instanceof PaymentTypeInfo) {
//					PaymentTypeInfo pInfo = (PaymentTypeInfo) value;
//					if(!pInfo.isPreType()){
//						payScale = FDCHelper.add(payScale, tblBySchedule.getCell(i, COL_PAY_SCALE).getValue());
//					}
//				}
			}
			if(FDCHelper.compareTo(payScale, FDCHelper.ONE_HUNDRED)!=0){
				FDCMsgBox.showWarning("总比例不等于100%，不能保存！");
				abort();
			}
		}
	}
	
	protected Set getCostAccountIdSet() {
		Set idSet = new HashSet();
		int cost_rowCount = pInfo.getCostEntries().size();
		for (int i = 0; i < cost_rowCount; i++) {
			ProgrammingContracCostInfo cInfo = pInfo.getCostEntries().get(i);
			CostAccountInfo costAcct = cInfo.getCostAccount();
			if(costAcct != null){
				idSet.add(costAcct.getId().toString());
			}
		}
		return idSet;
	}

	

	class FDCScheduleTaskCellRender extends ObjectValueRender {
		BizDataFormat formater = new BizDataFormat("$name$");
		public void draw(Graphics graphics, Shape clip, Object object,
				Style cellStyle, Object extObject) {
			super.draw(graphics, clip, object, cellStyle, extObject);
			
		}
		public String getText(Object obj) {
			if (obj instanceof Object[]) {
				Object[] objs = (Object[]) obj;
				StringBuffer txt = new StringBuffer();
				for (int i = 0; i < objs.length; i++) {
					txt.append(formater.format(objs[i]));
					txt.append("");
				}
				
				return txt.toString();
			} else {
				return super.getText(obj);
			}
		}
	}
	
	class PlanPayDateCellRender extends TextIconRender {
		public void draw(Graphics graphics, Shape clip, Object object, Style cellStyle, Object extObject) {
			if (extObject instanceof RenderObject) {
				RenderObject rObj = (RenderObject) extObject;

				if (CalTypeEnum.TIMERANGE.equals(tblBySchedule.getCell(rObj.getRowIndex(), tblBySchedule.getColumnIndex(COL_CAL_TYPE))
						.getValue())) {
					tblBySchedule.getCell(rObj.getRowIndex(), rObj.getColIndex()).setValue(null);
					object = "按月支付";
				}else if(CalTypeEnum.TIME.equals(tblBySchedule.getCell(rObj.getRowIndex(), tblBySchedule.getColumnIndex(COL_CAL_TYPE)).getValue())){
					if(tblBySchedule.getCell(rObj.getRowIndex(), tblBySchedule.getColumnIndex(COL_END_DATE)).getValue() != null 
							&& tblBySchedule.getCell(rObj.getRowIndex(), tblBySchedule.getColumnIndex(COL_DELAY_DAY)).getValue() != null){
						
						Date endDate = (Date) tblBySchedule.getCell(rObj.getRowIndex(), tblBySchedule.getColumnIndex(COL_END_DATE)).getValue();
						Integer delayDate = (Integer) tblBySchedule.getCell(rObj.getRowIndex(), tblBySchedule.getColumnIndex(COL_DELAY_DAY)).getValue();
						Date payDate = DateTimeUtils.addDay(endDate,delayDate.longValue());
						tblBySchedule.getCell(rObj.getRowIndex(), rObj.getColIndex()).setValue(payDate);
						
					}else if(tblBySchedule.getCell(rObj.getRowIndex(), tblBySchedule.getColumnIndex(COL_TASK)).getValue() != null){
						Date endDate = null;
						Object obj = tblBySchedule.getCell(rObj.getRowIndex(), tblBySchedule.getColumnIndex(COL_TASK)).getValue();
						if (obj instanceof Object[]) {
							Object[] objs = (Object[]) obj;
							for (int j = 0; j < objs.length; j++) {
								FDCScheduleTaskInfo info = (FDCScheduleTaskInfo) objs[j];
								if(endDate == null || info.getEnd().after(endDate)){
									endDate = info.getEnd();
								}
							}
						}
						if(endDate != null){
							Integer delayDate = (Integer) tblBySchedule.getCell(rObj.getRowIndex(), tblBySchedule.getColumnIndex(COL_DELAY_DAY)).getValue();
							Date payDate = DateTimeUtils.addDay(endDate,delayDate.longValue());
							tblBySchedule.getCell(rObj.getRowIndex(), rObj.getColIndex()).setValue(payDate);
							
						}
					}
					
				}
				super.draw(graphics, clip, object, cellStyle, extObject);
			} else {
				super.draw(graphics, clip, object, cellStyle, extObject);
			}
		}
	}
	
	
	class BeginDateCellRender extends TextIconRender {
		public void draw(Graphics graphics, Shape clip, Object object, Style cellStyle, Object extObject) {
			if (extObject instanceof RenderObject) {
				RenderObject rObj = (RenderObject) extObject;

				Object obj = tblBySchedule.getCell(rObj.getRowIndex(), COL_TASK).getValue();
				if(obj instanceof FDCScheduleTaskInfo){
					FDCScheduleTaskInfo task = (FDCScheduleTaskInfo) obj;
					tblBySchedule.getCell(rObj.getRowIndex(), rObj.getColIndex()).setValue(task.getStart());
				} else if (obj instanceof Object[]) {
					Object[] objs = (Object[]) obj;
					if(objs.length == 1 && objs[0] instanceof FDCScheduleTaskInfo){
						FDCScheduleTaskInfo task = (FDCScheduleTaskInfo) objs[0];
						tblBySchedule.getCell(rObj.getRowIndex(), rObj.getColIndex()).setValue(task.getStart());
					}else if(objs.length > 1){
						tblBySchedule.getCell(rObj.getRowIndex(), rObj.getColIndex()).setValue(null);
						object = null;
					}else if(objs.length==0){
						tblBySchedule.getCell(rObj.getRowIndex(), rObj.getColIndex()).setValue(null);
						object = null;
					}
				}else if(obj==null){
					tblBySchedule.getCell(rObj.getRowIndex(), rObj.getColIndex()).setValue(null);
				}
				super.draw(graphics, clip, object, cellStyle, extObject);
			} else {
				super.draw(graphics, clip, object, cellStyle, extObject);
			}
		}
	}
	class EndDateCellRender extends TextIconRender {
		public void draw(Graphics graphics, Shape clip, Object object, Style cellStyle, Object extObject) {
			if (extObject instanceof RenderObject) {
				RenderObject rObj = (RenderObject) extObject;

				Object obj = tblBySchedule.getCell(rObj.getRowIndex(), COL_TASK).getValue();
				if(obj instanceof FDCScheduleTaskInfo){
					FDCScheduleTaskInfo task = (FDCScheduleTaskInfo) obj;
					tblBySchedule.getCell(rObj.getRowIndex(), rObj.getColIndex()).setValue(task.getEnd());
				} else if (obj instanceof Object[]) {
					Object[] objs = (Object[]) obj;
					if(objs.length == 1 && objs[0] instanceof FDCScheduleTaskInfo){
						FDCScheduleTaskInfo task = (FDCScheduleTaskInfo) objs[0];
						tblBySchedule.getCell(rObj.getRowIndex(), rObj.getColIndex()).setValue(task.getEnd());
					}else if(objs.length > 1){
						tblBySchedule.getCell(rObj.getRowIndex(), rObj.getColIndex()).setValue(null);
						object = null;
					}else if(objs.length==0){
						tblBySchedule.getCell(rObj.getRowIndex(), rObj.getColIndex()).setValue(null);
						object = null;
					}
				}else if(obj==null){
					tblBySchedule.getCell(rObj.getRowIndex(), rObj.getColIndex()).setValue(null);
				}
				super.draw(graphics, clip, object, cellStyle, extObject);
			} else {
				super.draw(graphics, clip, object, cellStyle, extObject);
			}
		}
	}
//	

}