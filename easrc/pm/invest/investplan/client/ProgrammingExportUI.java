/**
 * output package name
 */
package com.kingdee.eas.port.pm.invest.investplan.client;

import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.entity.SorterItemCollection;
import com.kingdee.bos.metadata.entity.SorterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.base.attachment.AttachmentFactory;
import com.kingdee.eas.base.attachment.BoAttchAssoFactory;
import com.kingdee.eas.base.attachment.BoAttchAssoInfo;
import com.kingdee.eas.basedata.org.OrgConstants;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.fdc.basedata.CostAccountFactory;
import com.kingdee.eas.fdc.basedata.CostAccountInfo;
import com.kingdee.eas.fdc.basedata.FDCHelper;
import com.kingdee.eas.fdc.basedata.FDCSQLBuilder;
import com.kingdee.eas.framework.IFWEntityStruct;
import com.kingdee.eas.port.pm.invest.investplan.ProgrammingEntryCollection;
import com.kingdee.eas.port.pm.invest.investplan.ProgrammingEntryCostEntryCollection;
import com.kingdee.eas.port.pm.invest.investplan.ProgrammingEntryCostEntryInfo;
import com.kingdee.eas.port.pm.invest.investplan.ProgrammingEntryEconomyEntryCollection;
import com.kingdee.eas.port.pm.invest.investplan.ProgrammingEntryEconomyEntryInfo;
import com.kingdee.eas.port.pm.invest.investplan.ProgrammingEntryInfo;
import com.kingdee.eas.port.pm.invest.investplan.ProgrammingException;
import com.kingdee.eas.port.pm.invest.investplan.ProgrammingInfo;
import com.kingdee.eas.port.pm.invest.investplan.ProgrammingTemplateEntireCollection;
import com.kingdee.eas.port.pm.invest.investplan.ProgrammingTemplateEntireInfo;
import com.kingdee.eas.port.pm.invest.investplan.ProgrammingTemplateEntirePteCostCollection;
import com.kingdee.eas.port.pm.invest.investplan.ProgrammingTemplateEntirePteCostInfo;
import com.kingdee.eas.port.pm.invest.investplan.ProgrammingTemplateEntirePteEnonomyCollection;
import com.kingdee.eas.port.pm.invest.investplan.ProgrammingTemplateEntirePteEnonomyInfo;
import com.kingdee.eas.port.pm.invest.investplan.ProgrammingTemplateFactory;
import com.kingdee.eas.port.pm.invest.investplan.ProgrammingTemplateInfo;
import com.kingdee.eas.util.client.MsgBox;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.NumericExceptionSubItem;
import com.kingdee.util.StringUtils;

/**
 * output class name
 */
public class ProgrammingExportUI extends AbstractProgrammingExportUI
{
    private static final Logger logger = CoreUIObject.getLogger(ProgrammingExportUI.class);
    private ProgrammingInfo info = null;
    
    /**
     * output class constructor
     */
    public ProgrammingExportUI() throws Exception
    {
        super();
    }
    
    public void onLoad() throws Exception {
    	super.onLoad();
    	Object pro = getUIContext().get("Programming");
    	if(pro != null){
    		info = (ProgrammingInfo)pro;
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
     * output actionConfirm_actionPerformed
     */
    public void actionConfirm_actionPerformed(ActionEvent e) throws Exception
    {
    	String name = txtName.getText();
    	if(name == null || name.toString().trim().length() <= 0){
    		throw new ProgrammingException(ProgrammingException.NAMENOTNULL);
    	}
    	if(name.trim().length() > 80){
    		throw new EASBizException(new NumericExceptionSubItem("1","合约框架名称超长！"));
    	}
    	
    	FilterInfo filter = new FilterInfo();
		FilterItemInfo filterItem = new FilterItemInfo(IFWEntityStruct.dataBase_Name, name, CompareType.EQUALS);
		filter.getFilterItems().add(filterItem);

		EntityViewInfo view = new EntityViewInfo();
		view.setFilter(filter);
		SorterItemCollection sorter = new SorterItemCollection();
		sorter.add(new SorterItemInfo(IFWEntityStruct.coreBase_ID));
		if (ProgrammingTemplateFactory.getRemoteInstance().exists(filter)) {
			throw new ProgrammingException(ProgrammingException.CHECKDUPLICATED, new Object[] { name });
		}
    	
    	ProgrammingTemplateInfo template = new ProgrammingTemplateInfo();
    	template.setName(name);
		template.setNumber(getDateString());
		ProgrammingTemplateEntireCollection proTempEntries = template.getEntires();
		ProgrammingEntryCollection proEntries = info.getEntries();
		addProgrammingTemp(proTempEntries, proEntries);
		ProgrammingTemplateFactory.getRemoteInstance().addnew(template);
		MsgBox.showInfo("导出模板成功！");
		this.disposeUIWindow();
    }

    /**
     * 添加框架合约模板
     * @param proTempEntries
     * @param proEntries
     */
	private void addProgrammingTemp(ProgrammingTemplateEntireCollection proTempEntries, ProgrammingEntryCollection proEntries) {
		ProgrammingEntryInfo proInfo = null;
		ProgrammingTemplateEntireInfo proTempInfo = null;
		for(int i = 0 ; i < proEntries.size() ; i++){
			proTempInfo = new ProgrammingTemplateEntireInfo();
			proTempInfo.setId(BOSUuid.create(proTempInfo.getBOSType()));
			proInfo = proEntries.get(i);
			if(proInfo.getParent() != null){
				ProgrammingEntryInfo headProInfo = proInfo.getParent();
				boolean isHas = false;
				for(int j = 0 ; j < proTempEntries.size() ; j++){
					ProgrammingTemplateEntireInfo tInfo = proTempEntries.get(j);
					if(headProInfo.getLongNumber().equals(tInfo.getLongNumber())){
						proTempInfo.setHead(tInfo);
						isHas = true;
					}
				}
				if(!isHas){
					ProgrammingTemplateEntireInfo parentProTempInfo = addProgrammingTemplateEntireInfo(new ProgrammingTemplateEntireInfo() , headProInfo);
					parentProTempInfo.setId(BOSUuid.create(parentProTempInfo.getBOSType()));
					proTempInfo.setHead(parentProTempInfo);
					proTempEntries.add(parentProTempInfo);
					proTempEntries.add(addProgrammingTemplateEntireInfo(proTempInfo , proInfo));
				}else{
					proTempEntries.add(addProgrammingTemplateEntireInfo(proTempInfo , proInfo));
				}
			}else{
				proTempEntries.add(addProgrammingTemplateEntireInfo(proTempInfo , proInfo));
			}
		}
	}
    
	private ProgrammingTemplateEntireInfo addProgrammingTemplateEntireInfo(ProgrammingTemplateEntireInfo proTempInfo, ProgrammingEntryInfo proInfo) {
		proTempInfo.setLevel(proInfo.getLevel());
		proTempInfo.setSortNumber(proInfo.getSortNumber());
		proTempInfo.setLongNumber(proInfo.getLongNumber());
		proTempInfo.setNumber(proInfo.getNumber());
		proTempInfo.setDisplayName(proInfo.getDisplayName());
		proTempInfo.setName(proInfo.getName().trim());
		proTempInfo.setScope(proInfo.getWorkContent());
		proTempInfo.setProblem(proInfo.getSupMaterial());
		getAttachmentForCopy(proTempInfo , proInfo);
		proTempInfo.setAttachment(proInfo.getAttachment());
		proTempInfo.setDescription(proInfo.getDescription());
		ProgrammingTemplateEntirePteCostCollection pteCostCol = proTempInfo.getPteCost();
		ProgrammingEntryCostEntryCollection  proCostCol = proInfo.getCostEntries();
		addCostEntries(proTempInfo, pteCostCol, proCostCol);
		
		ProgrammingTemplateEntirePteEnonomyCollection pteEnonomyCol = proTempInfo.getPteEnonomy();
		ProgrammingEntryEconomyEntryCollection economyInfoCol = proInfo.getEconomyEntries();
		addEnonomyEntries(proTempInfo, pteEnonomyCol, economyInfoCol);
		
		proTempInfo.setContractType(proInfo.getContractType());
		return proTempInfo;
	}

	/**
	 * 添加经济条款页签数据
	 * @param proTempInfo
	 * @param pteEnonomyCol
	 * @param economyInfoCol
	 */
	private void addEnonomyEntries(ProgrammingTemplateEntireInfo proTempInfo, ProgrammingTemplateEntirePteEnonomyCollection pteEnonomyCol,
			ProgrammingEntryEconomyEntryCollection economyInfoCol) {
		for(int i = 0 ; i < economyInfoCol.size() ; i++){
			ProgrammingEntryEconomyEntryInfo economyInfo = economyInfoCol.get(i);
			ProgrammingTemplateEntirePteEnonomyInfo pteEconomyInfo = new ProgrammingTemplateEntirePteEnonomyInfo();
//			pteEconomyInfo.setParent(proTempInfo);
			pteEconomyInfo.setPaymentType(economyInfo.getPaymentType());
			pteEconomyInfo.setScale(economyInfo.getScale());
			pteEconomyInfo.setCondition(economyInfo.getCondition());
			pteEconomyInfo.setDescription(economyInfo.getDescription());
			pteEnonomyCol.add(pteEconomyInfo);
		}
	}

	/**
	 * 添加成本构成页签数据
	 * @param proTempInfo
	 * @param pteCostCol
	 * @param proCostCol
	 */
	private void addCostEntries(ProgrammingTemplateEntireInfo proTempInfo, ProgrammingTemplateEntirePteCostCollection pteCostCol, ProgrammingEntryCostEntryCollection proCostCol) {
		Map map = new HashMap();
		ProgrammingEntryCostEntryCollection proConCostCol = (ProgrammingEntryCostEntryCollection)proCostCol.clone();
		for(int i = 0 ; i < proConCostCol.size() ; i++){
			ProgrammingEntryCostEntryInfo costInfo = proConCostCol.get(i);
			CostAccountInfo oldCost = null;
			try {
				oldCost = CostAccountFactory.getRemoteInstance().getCostAccountInfo(new ObjectUuidPK(costInfo.getCostAccount().getId().toString()));
			} catch (EASBizException e) {
				e.printStackTrace();
			} catch (BOSException e) {
				e.printStackTrace();
			}
			if(oldCost == null)
				continue;
			CostAccountInfo cost = getOrgCostAccountInfo(oldCost);
			if(cost != null){
				ProgrammingTemplateEntirePteCostInfo pteCostInfo = createProgrammingTemplateEntirePteCost(proTempInfo, costInfo, cost);
				pteCostCol.add(pteCostInfo);
			}else{
				CostAccountInfo costAccountInfo = getOrgCostAccountInfo(oldCost, null);
				if (map.isEmpty()) {
					map.put(costAccountInfo.getId().toString(), costInfo);
				} else {
					if (map.containsKey(costAccountInfo.getId().toString())) {
						ProgrammingEntryCostEntryInfo proCon = (ProgrammingEntryCostEntryInfo) map.get(costAccountInfo.getId().toString());
						proCon.setContractAssign(proCon.getContractAssign().add(costInfo.getContractAssign()));// 本合约分配
						proCon.setGoalCost(proCon.getGoalCost().add(costInfo.getGoalCost()));// 目标成本
						proCon.setAssigning(proCon.getAssigning().add(costInfo.getAssigning()));// 待分配
					} else {
						map.put(costAccountInfo.getId().toString(), costInfo);
					}
				}
			}
		}
		
		if (!map.isEmpty()) {
			Set set = map.keySet();
			Iterator it = set.iterator();
			while (it.hasNext()) {
				ProgrammingTemplateEntirePteCostInfo pteCostInfo = new ProgrammingTemplateEntirePteCostInfo();
				pteCostInfo.setParent(proTempInfo);
				String id = it.next().toString();
				try {
					CostAccountInfo orgCost = CostAccountFactory.getRemoteInstance().getCostAccountInfo(new ObjectUuidPK(id));
					ProgrammingEntryCostEntryInfo proConCostInfo = (ProgrammingEntryCostEntryInfo)map.get(id);
					pteCostCol.add(createProgrammingTemplateEntirePteCost(proTempInfo, proConCostInfo, orgCost));
				} catch (EASBizException e) {
					logger.error(e);
				} catch (BOSException e) {
					logger.error(e);
				}
			}
		}
	}

	private ProgrammingTemplateEntirePteCostInfo createProgrammingTemplateEntirePteCost(ProgrammingTemplateEntireInfo proTempInfo, ProgrammingEntryCostEntryInfo costInfo, CostAccountInfo cost) {
		ProgrammingTemplateEntirePteCostInfo pteCostInfo = new ProgrammingTemplateEntirePteCostInfo();
		pteCostInfo.setCostAccount(cost);
		pteCostInfo.setParent(proTempInfo);
		pteCostInfo.setDescription(costInfo.getDescription());
		BigDecimal contractAssign = costInfo.getContractAssign();// 本合约分配
		BigDecimal goalCost = costInfo.getGoalCost();// 目标成本
		BigDecimal assigning = costInfo.getAssigning();//待分配
		if(goalCost != null && goalCost.compareTo(FDCHelper.ZERO) > 0){
			if (contractAssign != null) {
				BigDecimal percent = contractAssign.divide(goalCost, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
				if(percent.intValue() <= 0)
					pteCostInfo.setContractScale(FDCHelper.ZERO);
				else
					pteCostInfo.setContractScale(percent);
			}
			if(goalCost != null){
				BigDecimal percent = assigning.divide(goalCost, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
				if(percent.intValue() <= 0)
					pteCostInfo.setAssignScale(FDCHelper.ZERO);
				else
					pteCostInfo.setAssignScale(percent);
			}
		}
		return pteCostInfo;
	}
	
	private CostAccountInfo getOrgCostAccountInfo(CostAccountInfo info , CostAccountInfo orgCost){
		if(orgCost == null){
			if(info.getParent() != null){
				CostAccountInfo costInfo = null;
				try {
					costInfo = CostAccountFactory.getRemoteInstance().getCostAccountInfo(new ObjectUuidPK(info.getParent().getId()));
					orgCost = getOrgCostAccountInfo(costInfo);
				} catch (EASBizException e) {
					logger.error(e);
				} catch (BOSException e) {
					logger.error(e);
				}
				if(orgCost == null)
					return getOrgCostAccountInfo(costInfo , null);
			}
		}
		return orgCost;
	}
	
	private CostAccountInfo getOrgCostAccountInfo(CostAccountInfo cost){
		String longNumber = cost.getLongNumber();
		StringBuffer oql = new StringBuffer();
		
		oql.append("select id, number, name, longNumber where longnumber = '").append(longNumber).append("'");
		oql.append(" and curProject is null");
		oql.append(" and fullOrgUnit = '").append(OrgConstants.DEF_CU_ID).append("'");
		try {
			return CostAccountFactory.getRemoteInstance().getCostAccountInfo(oql.toString());
		} catch (Exception e) {
		}
		return null;
	}

    /**
     * output actionCancel_actionPerformed
     */
    public void actionCancel_actionPerformed(ActionEvent e) throws Exception
    {
    	this.disposeUIWindow();
    }
    
    private final String attIDColumn = "FATTACHMENTID";
    /**
     * 复制附件
     */
    private void getAttachmentForCopy(ProgrammingTemplateEntireInfo proTempInfo, ProgrammingEntryInfo proInfo){
    	FDCSQLBuilder sql = new FDCSQLBuilder("select * from T_BAS_BoAttchAsso where FBOID = '"+proInfo.getId()+"'");
    	BoAttchAssoInfo info = null;
    	try {
			IRowSet rs = sql.executeQuery();
			while(rs.next()){
				info = new BoAttchAssoInfo();
				String attID = rs.getString(attIDColumn);
				if(!StringUtils.isEmpty(attID))
					info.setAttachment(AttachmentFactory.getRemoteInstance().getAttachmentInfo(new ObjectUuidPK(attID)));
				info.setBoID(proTempInfo.getId().toString());
				info.setAssoBusObjType(proTempInfo.getBOSType().toString());
				info.setAssoType("系统已有附件");
				BoAttchAssoFactory.getRemoteInstance().addnew(info);
			}
		} catch (BOSException e) {
			logger.error(e);
		} catch (SQLException e) {
			logger.error(e);
		} catch (EASBizException e) {
			logger.error(e);
		}
    	
    }
    
    private String getDateString(){
    	Calendar cal = Calendar.getInstance();
    	Timestamp ts   =  new Timestamp(cal.getTimeInMillis());
    	Date bizDate = new Date(ts.getTime());
    	return bizDate.toString();
    }
}