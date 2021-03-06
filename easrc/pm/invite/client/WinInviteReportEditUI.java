/**
 * output package name
 */
package com.kingdee.eas.port.pm.invite.client;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.ctrl.extendcontrols.BizDataFormat;
import com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox;
import com.kingdee.bos.ctrl.kdf.table.IColumn;
import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.KDTDefaultCellEditor;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent;
import com.kingdee.bos.ctrl.kdf.table.util.KDTableUtil;
import com.kingdee.bos.ctrl.kdf.util.render.ObjectValueRender;
import com.kingdee.bos.ctrl.swing.KDScrollPane;
import com.kingdee.bos.ctrl.swing.KDTabbedPane;
import com.kingdee.bos.ctrl.swing.KDTextField;
import com.kingdee.bos.ctrl.swing.event.DataChangeEvent;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.metadata.bot.BOTMappingFactory;
import com.kingdee.bos.metadata.bot.BOTMappingInfo;
import com.kingdee.bos.metadata.bot.IBOTMapping;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.entity.SelectorItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.ui.face.UIRuleUtil;
import com.kingdee.eas.base.uiframe.client.UIFactoryHelper;
import com.kingdee.eas.basedata.assistant.ProjectFactory;
import com.kingdee.eas.basedata.assistant.ProjectInfo;
import com.kingdee.eas.basedata.org.AdminOrgUnitFactory;
import com.kingdee.eas.basedata.org.AdminOrgUnitInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.common.client.OprtState;
import com.kingdee.eas.common.client.UIContext;
import com.kingdee.eas.cp.bc.BizCollUtil;
import com.kingdee.eas.framework.CoreBillBaseCollection;
import com.kingdee.eas.port.equipment.maintenance.IMonMainPlanE1;
import com.kingdee.eas.port.equipment.maintenance.MonMainPlanE1Collection;
import com.kingdee.eas.port.equipment.maintenance.MonMainPlanE1Factory;
import com.kingdee.eas.port.equipment.maintenance.MonMainPlanFactory;
import com.kingdee.eas.port.equipment.maintenance.MonMainPlanInfo;
import com.kingdee.eas.port.equipment.uitl.ToolHelp;
import com.kingdee.eas.port.markesupplier.subill.MarketSupplierStockFactory;
import com.kingdee.eas.port.markesupplier.subill.MarketSupplierStockInfo;
import com.kingdee.eas.port.pm.base.InviteTypeFactory;
import com.kingdee.eas.port.pm.base.JudgesFactory;
import com.kingdee.eas.port.pm.base.JudgesInfo;
import com.kingdee.eas.port.pm.contract.ContractBillBudgetEntryFactory;
import com.kingdee.eas.port.pm.contract.IContractBillBudgetEntry;
import com.kingdee.eas.port.pm.invest.ProjectBudgetFacadeFactory;
import com.kingdee.eas.port.pm.invest.investplan.IProgrammingEntryCostEntry;
import com.kingdee.eas.port.pm.invest.investplan.ProgrammingEntryCostEntryFactory;
import com.kingdee.eas.port.pm.invest.investplan.ProgrammingEntryCostEntryInfo;
import com.kingdee.eas.port.pm.invest.uitls.CLUtil;
import com.kingdee.eas.port.pm.invite.EvaluationCollection;
import com.kingdee.eas.port.pm.invite.EvaluationEntryTotalCollection;
import com.kingdee.eas.port.pm.invite.EvaluationEntryTotalInfo;
import com.kingdee.eas.port.pm.invite.EvaluationEntryUnitCollection;
import com.kingdee.eas.port.pm.invite.EvaluationEntryUnitInfo;
import com.kingdee.eas.port.pm.invite.EvaluationFactory;
import com.kingdee.eas.port.pm.invite.EvaluationInfo;
import com.kingdee.eas.port.pm.invite.InviteReportEntry4Collection;
import com.kingdee.eas.port.pm.invite.InviteReportEntry4Factory;
import com.kingdee.eas.port.pm.invite.InviteReportEntry4Info;
import com.kingdee.eas.port.pm.invite.InviteReportInfo;
import com.kingdee.eas.port.pm.invite.JudgesComfirmCollection;
import com.kingdee.eas.port.pm.invite.JudgesComfirmEntryCollection;
import com.kingdee.eas.port.pm.invite.JudgesComfirmEntryInfo;
import com.kingdee.eas.port.pm.invite.JudgesComfirmFactory;
import com.kingdee.eas.port.pm.invite.JudgesComfirmInfo;
import com.kingdee.eas.port.pm.invite.OpenRegistrationCollection;
import com.kingdee.eas.port.pm.invite.OpenRegistrationEntryCollection;
import com.kingdee.eas.port.pm.invite.OpenRegistrationEntryInfo;
import com.kingdee.eas.port.pm.invite.OpenRegistrationFactory;
import com.kingdee.eas.port.pm.invite.OpenRegistrationInfo;
import com.kingdee.eas.port.pm.invite.WinInviteReportBudgetEntryCollection;
import com.kingdee.eas.port.pm.invite.WinInviteReportBudgetEntryFactory;
import com.kingdee.eas.port.pm.invite.WinInviteReportBudgetEntryInfo;
import com.kingdee.eas.port.pm.invite.WinInviteReportFactory;
import com.kingdee.eas.port.pm.invite.WinInviteReportInfo;
import com.kingdee.eas.rptclient.newrpt.util.MsgBox;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.eas.xr.app.XRBillStatusEnum;

/**
 * output class name
 */
public class WinInviteReportEditUI extends AbstractWinInviteReportEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(WinInviteReportEditUI.class);
    private IContractBillBudgetEntry IContractBillBudgetEntry = ContractBillBudgetEntryFactory.getRemoteInstance();
    /**
     * output class constructor
     */
    public WinInviteReportEditUI() throws Exception
    {
        super();
    }
    public void onLoad() throws Exception {
    	contCU.setVisible(false);
    	contBizStatus.setVisible(false);
    	contBizDate.setVisible(false);
    	contDescription.setVisible(false);
    	prmtinviteType.setEnabled(false);
    	evaSolution.setEnabled(false);
    	this.kdtJudges.getColumn("unitName").getStyleAttributes().setLocked(true);
    	this.kdtUnit.getColumn("seq").getStyleAttributes().setHided(true);
    	this.kdtBudgetEntry.getColumn("seq").getStyleAttributes().setHided(true);
    	this.kdtBudgetEntry.getColumn("budgetNumber").getStyleAttributes().setLocked(true);
    	this.kdtBudgetEntry.getColumn("budgetName").getStyleAttributes().setLocked(true);
    	this.kdtBudgetEntry.getColumn("budgetAmount").getStyleAttributes().setLocked(true);
    	this.kdtBudgetEntry.getColumn("balance").getStyleAttributes().setLocked(true);
    	this.kdtBudgetEntry.getColumn("lastAmount").getStyleAttributes().setLocked(true);
    	this.kdtBudgetEntry.getColumn("diffAmount").getStyleAttributes().setLocked(true);
    	this.kdtBudgetEntry.getColumn("sectBudget").getStyleAttributes().setLocked(true);
    	this.kdtBudgetEntry.getColumn("Project").getStyleAttributes().setLocked(true);
    	this.kdtUnit.getColumn("quality").getStyleAttributes().setHided(true);
    	continvitedAmount.setEnabled(false);
    	contwinInviteUnit.setEnabled(false);
    	super.onLoad();
    	prmtinviteReport.setDisplayFormat("$reportName$");
    	ProjectInfo info = (ProjectInfo) getUIContext().get("treeInfo");
    	if(info != null) {
    		EntityViewInfo evi = new EntityViewInfo();
    		FilterInfo filter = new FilterInfo();
    		evi.setFilter(filter);
    		filter.getFilterItems().add(new FilterItemInfo("proName.longnumber", info.getLongNumber()+"%", CompareType.LIKE));
    		prmtinviteReport.setEntityViewInfo(evi);
		}
    	
    	final KDBizPromptBox kdtEntry4_name_PromptBox = new KDBizPromptBox();
        kdtEntry4_name_PromptBox.setQueryInfo("com.kingdee.eas.port.pm.invest.investplan.app.ProgrammingCostEntryQuery");
        kdtEntry4_name_PromptBox.setVisible(true);
        kdtEntry4_name_PromptBox.setEditable(true);
        kdtEntry4_name_PromptBox.setDisplayFormat("$feeNumber$");
        kdtEntry4_name_PromptBox.setEditFormat("$feeNumber$");
        kdtEntry4_name_PromptBox.setCommitFormat("$feeNumber$");
        EntityViewInfo view = new EntityViewInfo();
        FilterInfo filInfo = new FilterInfo();
		if(prmtinviteReport.getValue()!=null){
			InviteReportInfo project = (InviteReportInfo)prmtinviteReport.getValue();
			filInfo.getFilterItems().add(new FilterItemInfo("number",project.getProName().getNumber()));
			filInfo.getFilterItems().add(new FilterItemInfo("isLast","1"));
			filInfo.getFilterItems().add(new FilterItemInfo("beizhu","最新"));
			view.setFilter(filInfo);
			kdtEntry4_name_PromptBox.setEntityViewInfo(view);
		}
        KDTDefaultCellEditor kdtEntry4_name_CellEditor = new KDTDefaultCellEditor(kdtEntry4_name_PromptBox);
        this.kdtBudgetEntry.getColumn("budgetNumber").setEditor(kdtEntry4_name_CellEditor);
        ObjectValueRender kdtEntry4_name_OVR = new ObjectValueRender();
        kdtEntry4_name_OVR.setFormat(new BizDataFormat("$feeNumber$"));
        this.kdtBudgetEntry.getColumn("budgetNumber").setRenderer(kdtEntry4_name_OVR);
        
        this.kdtBudgetEntry_detailPanel.getAddNewLineButton().setVisible(false);
        this.kdtBudgetEntry_detailPanel.getInsertLineButton().setVisible(false);
        this.kdtBudgetEntry_detailPanel.getRemoveLinesButton().setVisible(false);
        this.kdtBudgetEntry_detailPanel.setTitle("预算信息");
    }
    
    
	public void kdtBudgetEntry_Changed(int rowIndex, int colIndex) throws Exception {
		super.kdtBudgetEntry_Changed(rowIndex, colIndex);
		IObjectValue budgetNumber = (IObjectValue)kdtBudgetEntry.getCell(rowIndex,"budgetNumber").getValue();
		IRow row = kdtBudgetEntry.getRow(rowIndex);
		String colName = (String)kdtBudgetEntry.getColumn(colIndex).getKey();
		setBudgetEntry(budgetNumber, row, colName);
	}
	
	void setBudgetEntry(IObjectValue budgetNumber,IRow row, String colName)throws Exception{
		BigDecimal budgetAmount = UIRuleUtil.getBigDecimal(UIRuleUtil.getProperty(budgetNumber,"contractAssign"));
    	BigDecimal reportInviteAmount = UIRuleUtil.getBigDecimal(UIRuleUtil.getProperty(budgetNumber,"invitReportedAmount"));
    	BigDecimal InvitedAmount = UIRuleUtil.getBigDecimal(UIRuleUtil.getProperty(budgetNumber,"invitedAmount"));
    	BigDecimal contractAmount = UIRuleUtil.getBigDecimal(UIRuleUtil.getProperty(budgetNumber,"contractedAmount"));
    	BigDecimal nocontractAmount = UIRuleUtil.getBigDecimal(UIRuleUtil.getProperty(budgetNumber,"noContractedAmount"));
    	BigDecimal noInviteContractAmount = UIRuleUtil.getBigDecimal(UIRuleUtil.getProperty(budgetNumber,"noInviteContractAmount"));
    	BigDecimal balanceAmount = budgetAmount.subtract(InvitedAmount).subtract(nocontractAmount).subtract(noInviteContractAmount);
    	InviteReportInfo inviteReport = (InviteReportInfo) this.prmtinviteReport.getValue();
    	InviteReportEntry4Collection coll = inviteReport.getEntry4();
    	if ("budgetNumber".equalsIgnoreCase(colName)&&row.getCell("budgetName")!=null) {
    		row.getCell("budgetName").setValue(UIRuleUtil.getString(UIRuleUtil.getProperty(budgetNumber,"feeName")));
    		row.getCell("budgetAmount").setValue(UIRuleUtil.getBigDecimal(UIRuleUtil.getProperty(budgetNumber,"contractAssign")));
    		row.getCell("balance").setValue(balanceAmount);
    		BigDecimal amount = balanceAmount.subtract(UIRuleUtil.getBigDecimal(row.getCell("amount").getValue()));
    		row.getCell("lastAmount").setValue(amount);
       }
        if ("amount".equalsIgnoreCase(colName)) {
        	BigDecimal amount = balanceAmount.subtract(UIRuleUtil.getBigDecimal(row.getCell("amount").getValue()));
        	row.getCell("lastAmount").setValue(amount);
        	InviteReportEntry4Info entry = coll.get(row.getRowIndex());
        	row.getCell("diffAmount").setValue(UIRuleUtil.getBigDecimal(row.getCell("amount").getValue()).subtract(UIRuleUtil.getBigDecimal(entry.getAmount())));
        	amount = new BigDecimal(0);
        	for (int i = 0; i < kdtBudgetEntry.getRowCount(); i++) {
        		row = kdtBudgetEntry.getRow(i);
        		amount = amount.add(UIRuleUtil.getBigDecimal(row.getCell("amount").getValue()));
			}
        	txtinvitedAmount.setValue(amount);
        }
	}
	protected void prmtinviteReport_dataChanged(DataChangeEvent e)throws Exception {
    	super.prmtinviteReport_dataChanged(e);
    	boolean ischange = BizCollUtil.isF7ValueChanged(e);
    	if(!ischange||e.getNewValue()==null)
    		return;
    	InviteReportInfo inviteReport = (InviteReportInfo) e.getNewValue();
    	txtbudgetAmount.setValue(UIRuleUtil.getBigDecimal(inviteReport.getInviteBudget()));
    	this.txtcontent.setText(inviteReport.getBIMUDF0004());
    	this.evaSolution.setSelectedItem(inviteReport.getJudgeSolution());//设置评标访法
    	this.prmtinviteType.setValue(InviteTypeFactory.getRemoteInstance().getInviteTypeInfo(new ObjectUuidPK(inviteReport.getInviteType().getId())));
    	//过滤开标登记
    	String oql = "where reportName.id = '" + inviteReport.getId() + "'";
    	OpenRegistrationCollection openRegColl = OpenRegistrationFactory.getRemoteInstance().getOpenRegistrationCollection(oql);
    	OpenRegistrationInfo openRegInfo = openRegColl.get(0);
    	for(int i = 0; i < openRegColl.size(); i++) {
			OpenRegistrationInfo tempInfo = openRegColl.get(i);
			if(tempInfo.isCancel() != true) {
				openRegInfo = tempInfo;
				break;
			}
		}
    	
    	if(openRegInfo!=null){
    		this.txtaddress.setText(openRegInfo.getOpLocation());
    		this.pkinviteDate.setValue(openRegInfo.getOpDate() == null ? null : openRegInfo.getOpDate());
    	}
    	//过滤专家评标
    	oql = "where inviteReport.id = '" + inviteReport.getId() + "'";
    	EvaluationCollection evaColl = EvaluationFactory.getRemoteInstance().getEvaluationCollection(oql);
    	
    	IRow rowTotal = null;
    	if(evaColl.size()>0){
    		EvaluationInfo evaInfo = evaColl.get(0);
    		this.pkevaDate.setValue(evaInfo.getEvaDate() == null ? null : evaInfo.getEvaDate());
    		EvaluationEntryUnitCollection enterpriseColl = evaInfo.getEntryUnit();
    		EvaluationEntryTotalCollection totalColl = evaInfo.getEntryTotal();
    		
    		KDTable kdtable = new KDTable();
            kdtable.addHeadRow(0);
        	IColumn col = kdtable.addColumn();
    		col.setKey("enterprise");
    		col.getStyleAttributes().setLocked(true);
    		kdtable.getHeadRow(0).getCell("enterprise").setValue("投标单位");
        	//构建表头投标单位
    		for(int i = 0; i < enterpriseColl.size(); i++) {
    			EvaluationEntryUnitInfo info = enterpriseColl.get(i);
    			col = kdtable.addColumn();
    			col.setKey("Unit"+i);
    			kdtable.getHeadRow(0).getCell("Unit"+i).setValue(info.getEnterprise());
    		}
    		
    		IRow row = kdtable.addRow();
    		row.getCell("enterprise").setValue("投标报价");
    		row = kdtable.addRow();
    		row.getCell("enterprise").setValue("符合性结果");
    		row = kdtable.addRow();
    		row.getCell("enterprise").setValue("技术标得分");
    		row = kdtable.addRow();
    		row.getCell("enterprise").setValue("商务标得分");
    		row = kdtable.addRow();
    		row.getCell("enterprise").setValue("总分");
    		row = kdtable.addRow();
    		row.getCell("enterprise").setValue("排名");
        	
    		int line = 0;
    		for(int i = 0; i < totalColl.size(); i += enterpriseColl.size()) {
    			IRow rowAdd = kdtable.getRow(line++);
    			for(int j = i; j < enterpriseColl.size() + i; j++) {
    				EvaluationEntryTotalInfo info = totalColl.get(j);
    				rowAdd.getCell("enterprise").setValue(info.getIndicators());
        			rowAdd.getCell(j-i+1).setValue(info.getResult());
    			}
    		}		
    	    rowTotal = kdtable.getRow(5);
    	    System.out.println(totalColl.size());
    	    for(int i = 0; i < kdtable.getRowCount(); i++) {
    	    	IRow rowT = kdtable.getRow(i);
    	    	for(int j = 0; j < kdtable.getColumnCount(); j++) {
    	    		System.out.print(rowT.getCell(j).getValue() + "   ");
    	    	}
    	    	System.out.println();
    	    }
    	}
    	
    	//投标单位分录
    	this.kdtUnit.removeRows();
    	if(openRegInfo!=null){
    		OpenRegistrationEntryCollection opRegEntryColl = openRegInfo.getEntry();
    		for(int i = 0; i < opRegEntryColl.size(); i++) {
    			OpenRegistrationEntryInfo opRegEntryInfo = opRegEntryColl.get(i);
    			
	    			MarketSupplierStockInfo supplier = opRegEntryInfo.getSupplierName();//投标单位
	    			supplier = MarketSupplierStockFactory.getRemoteInstance().getMarketSupplierStockInfo(new ObjectUuidPK(supplier.getId()));
	    			IRow rowAdd = this.kdtUnit.addRow();
	    			rowAdd.getCell("unitName").setValue(supplier);
	    			rowAdd.getCell("quality").setValue(opRegEntryInfo.getQuality());
	    			if(opRegEntryInfo.getQuotedPrice() != null){
	    				rowAdd.getCell("inviteAmount").setValue(new BigDecimal(opRegEntryInfo.getQuotedPrice()));
	    			}
	    			rowAdd.getCell("win").setValue(false);
	    			if(rowTotal!=null){
	    				if(rowTotal.getCell(i+1).getValue() != null) {
	    					rowAdd.getCell("ranking").setValue(rowTotal.getCell(i+1).getValue());
	    					if(rowTotal.getCell(i+1).getValue().toString().equals("1")){
	    						rowAdd.getCell("win").setValue(true);
	    						txtinvitedAmount.setValue(rowAdd.getCell("inviteAmount").getValue());
	    						prmtwinInviteUnit.setValue(supplier);
	    					}
	    				}
	    		}
    		}
    	}
    	//过滤专家确定
    	this.kdtJudges.removeRows();
    	oql = "where planName.id = '" + inviteReport.getId() + "'";
    	JudgesComfirmCollection juColl = JudgesComfirmFactory.getRemoteInstance().getJudgesComfirmCollection(oql);
    	
    	if( juColl.size()>0){
    		JudgesComfirmInfo juInfo = juColl.get(0);
    		JudgesComfirmEntryCollection juEntryColl = juInfo.getEntry();
    		for(int i = 0; i < juEntryColl.size(); i++) {
    			JudgesComfirmEntryInfo juEntryInfo = juEntryColl.get(i);
    			JudgesInfo judgeInfo = juEntryInfo.getJudgeNumber();
    			judgeInfo = JudgesFactory.getRemoteInstance().getJudgesInfo(new ObjectUuidPK(judgeInfo.getId()));
    			AdminOrgUnitInfo admin = judgeInfo.getCurDep();
    			IRow rowAdd = this.kdtJudges.addRow();
    			rowAdd.getCell("judgesName").setValue(judgeInfo);
    			if(judgeInfo.isIsOuter()){
    				rowAdd.getCell("unitName").setValue("外部");
    			}else {
    				if(judgeInfo.getSsAddmin() != null){
    				String id = ((AdminOrgUnitInfo)judgeInfo.getSsAddmin()).getId().toString();
    				AdminOrgUnitInfo adminInfo = AdminOrgUnitFactory.getRemoteInstance().getAdminOrgUnitInfo(new ObjectUuidPK(id));
    				rowAdd.getCell("unitName").setValue(adminInfo.getName());
    				}else {
    					rowAdd.getCell("unitName").setValue(null);
    				}
    			}
    			
    			if(admin !=null) {
    				admin = AdminOrgUnitFactory.getRemoteInstance().getAdminOrgUnitInfo(new ObjectUuidPK(admin.getId()));
    				rowAdd.getCell("org").setValue(admin);
    			} else {
    				rowAdd.getCell("org").setValue(null);
    			}
    		}
    	}
    	//加载开标登记的列表界面,重载开标登记列表界面的getQueryExecutor方法
    	while(this.kDTabbedPane1.getTabCount()-1 >= 2)
    		this.kDTabbedPane1.removeTabAt(this.kDTabbedPane1.getTabCount()-1);
    	addTab(this.kDTabbedPane1, OpenRegistrationListUI.class.getName(), inviteReport.getId().toString(), "开标登记");
    	addTab(this.kDTabbedPane1, EvaluationListUI.class.getName(), inviteReport.getId().toString(), "专家评标");
    	addTab(this.kDTabbedPane1, InviteReportListUI.class.getName(), inviteReport.getId().toString(), "招标方案申报");
    	
    	InviteReportEntry4Collection coll = inviteReport.getEntry4();
    	BigDecimal sumAmount = new BigDecimal(0);
    	kdtBudgetEntry.removeRows();
    	for (int i = 0; i < coll.size(); i++) {
    		InviteReportEntry4Info entry = coll.get(i);
    		entry = InviteReportEntry4Factory.getRemoteInstance().getInviteReportEntry4Info(new ObjectUuidPK(entry.getId()));
    		IRow brow = kdtBudgetEntry.addRow();
    		ProgrammingEntryCostEntryInfo budgerNumber = ProgrammingEntryCostEntryFactory.getRemoteInstance().getProgrammingEntryCostEntryInfo(new ObjectUuidPK(entry.getBudgetNumber().getId()));
    		brow.getCell("budgetNumber").setValue(budgerNumber);
    		brow.getCell("sectBudget").setValue(entry.getAmount());
    		 
    		if(entry.getEntryProject()!=null)
    			brow.getCell("Project").setValue(ProjectFactory.getRemoteInstance().getProjectInfo(new ObjectUuidPK(entry.getEntryProject().getId())));
    		BigDecimal invitedAmount = UIRuleUtil.getBigDecimal(txtinvitedAmount.getValue());
    		BigDecimal budgetAmount = UIRuleUtil.getBigDecimal(txtbudgetAmount.getValue());
    		if(i==coll.size()-1)
    			brow.getCell("amount").setValue(invitedAmount.subtract(sumAmount));
    		else
    			brow.getCell("amount").setValue(UIRuleUtil.getBigDecimal(entry.getAmount()).divide(budgetAmount, 4, 4).multiply(invitedAmount));
    		brow.getCell("seq").setValue((i+1));
    		brow.getCell("diffAmount").setValue(UIRuleUtil.getBigDecimal(brow.getCell("amount").getValue()).subtract(UIRuleUtil.getBigDecimal(entry.getAmount())));
    		sumAmount = sumAmount.add(UIRuleUtil.getBigDecimal(brow.getCell("amount").getValue()));
    		setBudgetEntry(budgerNumber, brow, "budgetNumber");
		}
    }
    
    /**
     * 添加标签显示其他相关单据的列表界面
     * 对应的被listUI需要重载getQueryExecutor方法过滤
     * @param kDTabbedPane1
     * @param className (全路径)
     * @param id
     * @param tabName
     * @throws Exception
     */
    private void addTab(KDTabbedPane kDTabbedPane1, String className, String id, String tabName) throws Exception{
    	KDScrollPane panel = new KDScrollPane();
		panel.setMinimumSize(new Dimension(1013,600));		
		panel.setPreferredSize(new Dimension(1013,600));
    	UIContext uiContext = new UIContext(this);
        uiContext.put("reportId", id);
		panel.setViewportView((Component) UIFactoryHelper.initUIObject(className, uiContext, null, OprtState.VIEW));
		panel.setKeyBoardControl(true);
		panel.setEnabled(false);
		kDTabbedPane1.add(panel,tabName);
    }
    protected void verifyInput(ActionEvent e) throws Exception {
    	super.verifyInput(e);
		com.kingdee.eas.xr.helper.ClientVerifyXRHelper.verifyNull(this,txtinvitedAmount, "中标金额");

    }
    protected void kdtUnit_editValueChanged(KDTEditEvent e) throws Exception {
    	super.kdtUnit_editValueChanged(e);
    	int colIndex = e.getColIndex();
    	int rowIndex = e.getRowIndex();
    	String key = kdtUnit.getColumnKey(colIndex);
    	if("win".equals(key)){
    		if(!UIRuleUtil.getBoolean(kdtUnit.getCell(rowIndex, key).getValue())){
    			prmtwinInviteUnit.setValue(kdtUnit.getCell(rowIndex, "unitName").getValue());
    			
    		}
    	}
    }
    /**
     * output loadFields method
     */
    public void loadFields()
    {
        super.loadFields();
        for (int j = 0; j < kdtBudgetEntry.getRowCount(); j++) 
    	{
    		IRow row = kdtBudgetEntry.getRow(j);
    		
    		if(row.getUserObject() !=null &&row.getUserObject() instanceof WinInviteReportBudgetEntryInfo&&UIRuleUtil.isNotNull(((WinInviteReportBudgetEntryInfo)row.getUserObject()).getId())){
    			String entryId = ((WinInviteReportBudgetEntryInfo)row.getUserObject()).getId().toString();
    			
    			try {
    				if(IContractBillBudgetEntry.exists("select id where sourceBillID='"+entryId+"'")){
    					row.getStyleAttributes().setBackground(Color.GREEN);
    				}
    			} catch (EASBizException e) {
    				e.printStackTrace();
    			} catch (BOSException e) {
    				e.printStackTrace();
    			}
    		}
		}
    }
    
    
    public static void checkBgtItemAmount(String pk,String actionName) throws EASBizException, BOSException{
    	if(actionName.equals("AUDIT")){
    		WinInviteReportInfo info = WinInviteReportFactory.getRemoteInstance().getWinInviteReportInfo(new ObjectUuidPK(pk));
    		IProgrammingEntryCostEntry iProgrammingEntryCostEntry = ProgrammingEntryCostEntryFactory.getRemoteInstance();
    		for (int i = 0; i <info.getBudgetEntry().size(); i++) {
    			WinInviteReportBudgetEntryInfo entry = info.getBudgetEntry().get(i);
    			ProgrammingEntryCostEntryInfo budgetInfo= iProgrammingEntryCostEntry.getProgrammingEntryCostEntryInfo(new ObjectUuidPK(entry.getBudgetNumber().getId()));
    			String projectNumber = budgetInfo.getNumber();
    			String budgetNumber = budgetInfo.getFeeNumber();
    			String budgetName = budgetInfo.getFeeName();
    			String year = budgetInfo.getYear();
    			BigDecimal backAmount = UIRuleUtil.getBigDecimal(entry.getDiffAmount());
    			String[] str = ProjectBudgetFacadeFactory.getRemoteInstance().subBudgetAmount(projectNumber,year,budgetNumber
    					,String.valueOf(entry.getAmount()),CLUtil.stag_zb,true,backAmount.toString());
    			if("失败".equals(str[0])){
//    				throw new XRBillException(XRBillException.NOBUDGET, new Object[] {
//    						"预算编码："+budgetNumber+","+budgetName , str[1]
//    				});
    				MsgBox.showWarning("预算编码："+budgetNumber+","+budgetName +" 该费用预算不足"+ str[1]);
    			}
    		}
    	}else{
    		WinInviteReportInfo info = WinInviteReportFactory.getRemoteInstance().getWinInviteReportInfo(new ObjectUuidPK(pk));
    		IProgrammingEntryCostEntry iProgrammingEntryCostEntry = ProgrammingEntryCostEntryFactory.getRemoteInstance();
    		for (int i = 0; i <info.getBudgetEntry().size(); i++) {
    			WinInviteReportBudgetEntryInfo entry = info.getBudgetEntry().get(i);
    			ProgrammingEntryCostEntryInfo budgetInfo= iProgrammingEntryCostEntry.getProgrammingEntryCostEntryInfo(new ObjectUuidPK(entry.getBudgetNumber().getId()));
    			String projectNumber = budgetInfo.getNumber();
    			String budgetNumber = budgetInfo.getFeeNumber();
    			String budgetName = budgetInfo.getFeeName();
    			String year = budgetInfo.getYear();
    			BigDecimal backAmount = UIRuleUtil.getBigDecimal(entry.getDiffAmount());
    			String[] str = ProjectBudgetFacadeFactory.getRemoteInstance().backBudgetAmount(projectNumber,year,budgetNumber
    																,String.valueOf(entry.getAmount()),CLUtil.stag_zb,true,backAmount.toString());
    			if("失败".equals(str[0])){
//    				 throw new XRBillException(XRBillException.NOBUDGET, new Object[] {
//    						 "预算编码："+budgetNumber+","+budgetName , str[1]
//    			            });
    				 MsgBox.showWarning("预算编码："+budgetNumber+","+budgetName +" 该费用预算不足"+ str[1]);
    			}
    		}
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
//        super.actionCreateTo_actionPerformed(e);
        int selectIndex[] = KDTableUtil.getSelectedRows(kdtBudgetEntry);
    	if(!this.editData.getStatus().equals(XRBillStatusEnum.AUDITED)){
    		com.kingdee.eas.util.client.MsgBox.showWarning("单据未审批，不能生成合同！");
    		SysUtil.abort();
    	}
    	if(selectIndex.length==0){
    		com.kingdee.eas.util.client.MsgBox.showWarning("请选择数据行！");
    		SysUtil.abort();
    	}
    	
    	Set<String> idset = new HashSet<String>();
    	
    	for (int j = 0; j < selectIndex.length; j++) 
    	{
    		IRow row = kdtBudgetEntry.getRow(selectIndex[j]);
    		
    		String entryId = ((WinInviteReportBudgetEntryInfo)row.getUserObject()).getId().toString();
    		
    		if(IContractBillBudgetEntry.exists("select id where sourceBillID='"+entryId+"'")){
    			com.kingdee.eas.util.client.MsgBox.showWarning("第<"+(j+1)+">行，已经生成项目合同了！");
    			SysUtil.abort();
    		}
    		idset.add(entryId);
		}
		
		SelectorItemCollection sic = getSelectors();
		sic.add(new SelectorItemInfo("inviteReport.*"));
		sic.add(new SelectorItemInfo("inviteReport.proName.*"));
		WinInviteReportInfo Info = WinInviteReportFactory.getRemoteInstance().getWinInviteReportInfo(new ObjectUuidPK(editData.getId()),sic);
		
		EntityViewInfo view = new EntityViewInfo();
		FilterInfo filInfo = new FilterInfo();
		filInfo.getFilterItems().add(new FilterItemInfo("id",idset,CompareType.INCLUDE));
		view.setFilter(filInfo);
		Info.getBudgetEntry().clear();
		Info.getBudgetEntry().addCollection(WinInviteReportBudgetEntryFactory.getRemoteInstance().getWinInviteReportBudgetEntryCollection(view));
    	
		getBillEdit().CreateTo(Info);
//		MsgBox.showInfo("生成项目合同成功！");
		
		loadFields();  
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
        checkBgtItemAmount(editData.getId().toString(), "AUDIT");
    }

    /**
     * output actionUnAudit_actionPerformed
     */
    public void actionUnAudit_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionUnAudit_actionPerformed(e);
        checkBgtItemAmount(editData.getId().toString(), "UNAUDIT");
    }

    /**
     * output getBizInterface method
     */
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return com.kingdee.eas.port.pm.invite.WinInviteReportFactory.getRemoteInstance();
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
        com.kingdee.eas.port.pm.invite.WinInviteReportInfo objectValue = new com.kingdee.eas.port.pm.invite.WinInviteReportInfo();
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