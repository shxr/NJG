package com.kingdee.eas.bpm.selectors;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.entity.SelectorItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.eas.basedata.master.account.AccountViewCollection;
import com.kingdee.eas.basedata.master.account.AccountViewFactory;
import com.kingdee.eas.basedata.org.AdminOrgUnitFactory;
import com.kingdee.eas.basedata.org.AdminOrgUnitInfo;
import com.kingdee.eas.basedata.org.CompanyOrgUnitFactory;
import com.kingdee.eas.basedata.org.CompanyOrgUnitInfo;
import com.kingdee.eas.bpm.BPMLogFactory;
import com.kingdee.eas.bpm.BPMLogInfo;
import com.kingdee.eas.bpm.BillBaseSelector;
import com.kingdee.eas.bpm.common.StringUtilBPM;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.fdc.basedata.FDCBillStateEnum;
import com.kingdee.eas.fdc.basedata.FDCHelper;
import com.kingdee.eas.fdc.basedata.FDCSQLBuilder;
import com.kingdee.eas.fdc.basedata.PaymentTypeFactory;
import com.kingdee.eas.fdc.basedata.PaymentTypeInfo;
import com.kingdee.eas.fdc.contract.ContractBailEntryFactory;
import com.kingdee.eas.fdc.contract.ContractBailEntryInfo;
import com.kingdee.eas.fdc.contract.ContractBillCollection;
import com.kingdee.eas.fdc.contract.ContractBillEntryFactory;
import com.kingdee.eas.fdc.contract.ContractBillEntryInfo;
import com.kingdee.eas.fdc.contract.ContractBillFactory;
import com.kingdee.eas.fdc.contract.ContractBillInfo;
import com.kingdee.eas.fdc.contract.ContractBillReviseEntryFactory;
import com.kingdee.eas.fdc.contract.ContractBillReviseEntryInfo;
import com.kingdee.eas.fdc.contract.ContractBillReviseFactory;
import com.kingdee.eas.fdc.contract.ContractBillReviseInfo;
import com.kingdee.eas.fdc.contract.ContractPayItemFactory;
import com.kingdee.eas.fdc.contract.ContractPayItemInfo;
import com.kingdee.eas.fdc.contract.ContractWithoutTextFactory;
import com.kingdee.eas.fdc.contract.ContractWithoutTextInfo;
import com.kingdee.eas.fdc.contract.IContractBill;
import com.kingdee.eas.fdc.contract.programming.ProgrammingContractCollection;
import com.kingdee.eas.fdc.contract.programming.ProgrammingContractFactory;
import com.kingdee.eas.fdc.contract.programming.ProgrammingContractInfo;
import com.kingdee.eas.fdc.contract.programming.ProgrammingFactory;
import com.kingdee.eas.util.app.ContextUtil;
import com.kingdee.jdbc.rowset.IRowSet;

public class ContractReviseFacade implements BillBaseSelector{

	public String[] ApproveBack(Context ctx, String strBTID,
			IObjectValue billInfo, String strXML) {
        return null;
	}

	public String[] ApproveClose(Context ctx, String strBSID,
			IObjectValue billInfo, int procInstID,
			String processInstanceResult, String strComment, Date dtTime) {
		ContractBillReviseInfo Info = (ContractBillReviseInfo)billInfo;
    	String[] str = new String[3];
    	str[0] = "Y";
    	String sql="";
		try {
			try{
				Info = ContractBillReviseFactory.getLocalInstance(ctx).getContractBillReviseInfo(new ObjectUuidPK(Info.getId()),getSelectors());
			}catch (EASBizException e) {
				str[2] = "根据单据getSelectors获取对象数据，请检查getSelectors方法中属性是否正确,并查看服务器log日志！";
				e.printStackTrace();
			}
			try{
				if("1".equals(processInstanceResult)){
					if(FDCBillStateEnum.AUDITTING.equals(Info.getState()))
					{   
						Info.setState(FDCBillStateEnum.SUBMITTED);
						CompanyOrgUnitInfo company = CompanyOrgUnitFactory.getLocalInstance(ctx).getCompanyOrgUnitInfo(new ObjectUuidPK(Info.getCU().getId()));
						AdminOrgUnitInfo admin=AdminOrgUnitFactory.getLocalInstance(ctx).getAdminOrgUnitInfo(new ObjectUuidPK(Info.getCU().getId()));                            
						ContextUtil.setCurrentFIUnit(ctx, company);
						ContextUtil.setCurrentOrgUnit(ctx, admin);
						ContractBillReviseFactory.getLocalInstance(ctx).audit(Info.getId());
						Info.setState(FDCBillStateEnum.AUDITTED);
					}
					else{
						str[2] = "审批通过失败，该记录状态不是审批中！";
						str[0] = "N";
					}
				}
				if("0".equals(processInstanceResult)){
					if(FDCBillStateEnum.AUDITTING.equals(Info.getState()))
						Info.setState(FDCBillStateEnum.SAVED);
					else{
						str[2] = "审批不通过失败，该记录状态不是审批中！";
						str[0] = "N";
					}
				}
				if("2".equals(processInstanceResult)){
					if(FDCBillStateEnum.AUDITTING.equals(Info.getState()))
						Info.setState(FDCBillStateEnum.SAVED);
					else{
						str[2] = "审批打回失败，该记录状态不是审批中！";
						str[0] = "N";
					}
				}
				if("3".equals(processInstanceResult)){
					if(FDCBillStateEnum.AUDITTING.equals(Info.getState()))
					{
						Info.setState(FDCBillStateEnum.SAVED);
						sql = " update T_CON_ContractBillRevise set fDescription='' where fid='"+Info.getId()+"'";
						FDCSQLBuilder bu = new FDCSQLBuilder(ctx);
						bu.appendSql(sql);
						bu.executeUpdate(ctx);
					}
					else{
						str[2] = "撤销失败，该记录状态不是审批中！";
						str[0] = "N";
					}
				}
				 sql = " update T_CON_ContractBillRevise set fState='"+Info.getState().getValue()+"' where fid='"+Info.getId()+"'";
				FDCSQLBuilder bu = new FDCSQLBuilder(ctx);
				bu.appendSql(sql);
				bu.executeUpdate(ctx);
			}
			catch (BOSException e) {
				str[2] = "根据单据state值更新状态sql失败，请检查getState方法是否有值,并查看服务器log日志！";
				e.printStackTrace();
			}
		}catch (Exception e) {
			str[2] = "其他异常，请查看服务器log日志！";
			e.printStackTrace();
		}finally{
			BPMLogInfo log = new BPMLogInfo();
			try {
				log.setLogDate(new Date());
				log.setName("合同修订单据ID："+Info.getId()+"; 编号："+Info.getNumber());
				log.setDescription("BPM结果："+processInstanceResult+"; EAS结果:"+str[0]+"; 错误信息"+str[1]+str[2]);
				log.setSimpleName("BPM流程号："+procInstID+";BPM反馈信息:"+strComment);
				log.setBeizhu("调用接口方法：ApproveClose");
				BPMLogFactory.getLocalInstance(ctx).save(log);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return str;
	}

	public String[] GetbillInfo(Context ctx, String strBSID,
			IObjectValue billInfo) {
		ContractBillReviseInfo Info = (ContractBillReviseInfo)billInfo;
    	String[] str = new String[3];
    	str[0] = "Y";
    	StringBuffer xml = new StringBuffer();
		try {
			try{
				Info = ContractBillReviseFactory.getLocalInstance(ctx).getContractBillReviseInfo(new ObjectUuidPK(Info.getId()),getSelectors());
			}catch (EASBizException e) {
				str[2] = "根据单据getSelectors获取对象数据，请检查getSelectors方法中属性是否正确,并查看服务器log日志！";
				e.printStackTrace();
			}
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			  xml.append("<DATA>\n"); 
  			xml.append("<OrgName>"+StringUtilBPM.isNULl(Info.getOrgUnit().getDisplayName())+"</OrgName>\n");
  			xml.append("<DeptName>"+StringUtilBPM.isNULl(Info.getRespDept().getName())+"</DeptName>\n");
  			xml.append("<ApplyDate>"+dateFormat.format(Info.getCreateTime())+"</ApplyDate>\n");
  			xml.append("<Applicant>"+StringUtilBPM.isNULl(Info.getCreator().getName())+"</Applicant>\n");
  			xml.append("<Position>CEO秘书</Position>\n");
  			xml.append("<Topic>"+StringUtilBPM.isNULl(Info.getName())+"</Topic>\n");
  			xml.append("<CompanyName>"+StringUtilBPM.isNULl(Info.getLandDeveloper().getName())+"</CompanyName>\n");
  			xml.append("<Phase>"+StringUtilBPM.isNULl(Info.getCurProject().getDisplayName())+"</Phase>\n");
  			xml.append("<OrgCode>"+StringUtilBPM.isNULl(Info.getOrgUnit().getNumber().split("-")[0])+"</OrgCode>\n");
  			xml.append("<contractName>"+StringUtilBPM.isNULl(Info.getName())+"</contractName>\n");
  			xml.append("<partA>"+StringUtilBPM.isNULl(Info.getLandDeveloper().getName())+"</partA>\n");
  			xml.append("<partB>"+StringUtilBPM.isNULl(Info.getPartB().getName())+"</partB>\n");
  			if(Info.getPartC()!=null)
  				xml.append("<partC>"+StringUtilBPM.isNULl(Info.getPartC().getName())+"</partC>\n");
  				xml.append("<bizDate>"+dateFormat.format(Info.getCreateTime())+"</bizDate>\n");
  			if(Info.getGrtRate()!=null)
  				xml.append("<grtRate>"+Info.getGrtRate()+"</grtRate>\n");
  			if(Info.getGrtAmount()!=null)
  				xml.append("<grtAmount>"+Info.getGrtAmount()+"</grtAmount>\n");
  			xml.append("<period>"+Info.getPeriod().getNumber()+"</period>\n");
  			xml.append("<contractSource>"+StringUtilBPM.isNULl(Info.getContractSourceId().getName())+"</contractSource>\n");
  			xml.append("<costProperty>"+StringUtilBPM.isNULl(Info.getCostProperty().getAlias())+"</costProperty>\n");
  			if(Info.getPayScale()!=null)
  				xml.append("<payScale>"+Info.getPayScale()+"</payScale>\n");
  			if(Info.getStampTaxAmt()!=null)
  				xml.append("<stampTaxAmt>"+Info.getStampTaxAmt()+"</stampTaxAmt>\n");
  			if(Info.getStampTaxRate()!=null)
  				xml.append("<stampTaxRate>"+Info.getStampTaxRate()+"</stampTaxRate>\n");
  			xml.append("<contractTypeName>"+StringUtilBPM.isNULl(Info.getContractType().getName())+"</contractTypeName>\n");
  			xml.append("<contractTypeNumber>"+StringUtilBPM.isNULl(Info.getContractType().getNumber())+"</contractTypeNumber>\n");
  			xml.append("<contractAmount>"+Info.getAmount()+"</contractAmount>\n");
  			xml.append("<responsiblePerson>"+StringUtilBPM.isNULl(Info.getRespPerson().getName())+"</responsiblePerson>\n");
  			if(Info.getSignDate()!=null)
  				xml.append("<signDate>"+Info.getSignDate()+"</signDate>\n");
  			if(Info.getOriginalAmount()!=null)
  				xml.append("<contractCurrencyAmount>"+Info.getOriginalAmount()+"</contractCurrencyAmount>\n");
  			xml.append("<contractNature>"+StringUtilBPM.isNULl(Info.getContractPropert().getAlias())+"</contractNature>\n");
  			xml.append("<currencyType>"+StringUtilBPM.isNULl(Info.getCurrency().getName())+"</currencyType>\n");
  			xml.append("<rate>"+Info.getExRate()+"</rate>\n");
  			xml.append("<RevLocalAmount>"+Info.getRevLocalAmount()+"</RevLocalAmount>\n"); 
  			xml.append("<RevAmount>"+Info.getRevAmount()+"</RevAmount>\n"); 
  			xml.append("<ReviseReason>"+Info.getReviseReason()+"</ReviseReason>\n");
  			xml.append("<creator>"+Info.getCreator().getName()+"</creator>\n");
  			xml.append("<createTime>"+dateFormat.format(Info.getCreateTime())+"</createTime>\n");
  			xml.append("<respDept>"+Info.getRespDept().getName()+"</respDept>\n");
  			xml.append("<payAlertRate>"+Info.getPayPercForWarn()+"</payAlertRate>\n");
  			if(false==Info.isIsPartAMaterialCon())
  			xml.append("<isPartAMaterialCon>否</isPartAMaterialCon>\n");
  			else
  			{
  				xml.append("<isPartAMaterialCon>是</isPartAMaterialCon>\n");	
  			}
  			if(false==Info.isIsCoseSplit())
  			xml.append("<isCoseSplit>否</isCoseSplit>\n");
  			else
  			{
  				xml.append("<isCoseSplit>是</isCoseSplit>\n");
  			}
  			xml.append("<chgPercForWarn>"+Info.getChgPercForWarn()+"</chgPercForWarn>\n");
  			xml.append("<contactNumber>"+Info.getContractBill().getNumber()+"</contactNumber>\n");
  			

  			
  			  EntityViewInfo Myavevi = new EntityViewInfo();
		      FilterInfo Myavfilter = new FilterInfo();
		      Myavfilter.getFilterItems().add(new FilterItemInfo("number",Info.getContractBill().getNumber().toString().trim(),CompareType.EQUALS));
		      Myavevi.setFilter(Myavfilter);
		      ContractBillCollection myavc=ContractBillFactory.getLocalInstance(ctx).getContractBillCollection(Myavevi);
		      if(myavc.size()>0)
		      {
		        for(int i=0;i< myavc.size();i++){  	     
         	    ContractBillInfo info=ContractBillFactory.getLocalInstance(ctx).getContractBillInfo(new ObjectUuidPK(myavc.get(i).getId()));
         	    //ProgrammingContractInfo info=myavc.get(i).getProgrammingContract();
         	     if(info.getProgrammingContract()!=null)
         	     { 
          	     EntityViewInfo Myavevi2 = new EntityViewInfo();
  		         FilterInfo Myavfilter2 = new FilterInfo();
  		         Myavfilter2.getFilterItems().add(new FilterItemInfo("id",info.getProgrammingContract().getId()));
         	     ProgrammingContractInfo proInfo2=ProgrammingContractFactory.getLocalInstance(ctx).getProgrammingContractInfo(new ObjectUuidPK(info.getProgrammingContract().getId()));
         	     if(proInfo2!=null)
           	     xml.append("<programmingContract>"+proInfo2.getName()+"</programmingContract>\n");
       		      String sql="select a.SFCostAmount as SFa,b.SFceremonyb as SFb from  ";
	    		  sql+="(select sum(c.FCostAmount) as SFCostAmount,a.flongNumber as fida from T_CON_ProgrammingContract a left join  T_CON_ContractBill b on a.fid=b.FProgrammingContract ";
	    		  sql+="left join (select b.FCostAmount as FCostAmount,b.FcontractBillID as FcontractBillID,a.FChangeState as FChangeState from T_CON_ChangeAuditBill a left join ";
	    		  sql+="T_CON_ChangeSupplierEntry b on a.fid=b.fparentid)as c on b.fid=c.FcontractBillID where c.FchangeState = '4Auditting' group by a.flongnumber) a  ";
	    		  sql+="right join (select sum(b.Fceremonyb) as SFceremonyb,a.flongNumber as fidb  from T_CON_ProgrammingContract a left join  T_CON_ContractBill b on a.fid=b.FProgrammingContract left ";
	    		  sql+="join (select b.FCostAmount as FCostAmount,b.FcontractBillID as FcontractBillID,a.FChangeState as FChangeState from T_CON_ChangeAuditBill a left join ";
	    		  sql+="T_CON_ChangeSupplierEntry b on a.fid=b.fparentid)as c on b.fid=c.FcontractBillID where b.Fstate='3AUDITTING' and b.FProgrammingContract='"+proInfo2.getId()+"' group by a.flongnumber) b on a.fida= b.fidb";
	    		  FDCSQLBuilder builder=new FDCSQLBuilder(ctx);
	    		  builder.appendSql(sql);
	              IRowSet Rowset=builder.executeQuery();
	              if(Rowset.size()==1)
	              {
	               Rowset.next();  
	               xml.append("<HTMoney>" +FDCHelper.toBigDecimal(Rowset.getBigDecimal("SFb")) + "</HTMoney>\n");//在途金额汇总
			       xml.append("<BGMoney>" +FDCHelper.toBigDecimal(Rowset.getBigDecimal("SFa"))+"</BGMoney>\n");//在途变更金额汇总
	              }
	              builder.clear();
           	     if(proInfo2.getControlBalance()!=null)
           	     xml.append("<controlBalance>"+proInfo2.getControlBalance()+"</controlBalance>\n");
         	     xml.append("<overRate>"+info.getOverRate()+"</overRate>\n");
         	     }
         	     
                }
		      }
		      
   
  			
  			
  			
  		xml.append("<billEntries>\n");
			for(int i=0;i<Info.getEntrys().size();i++){
				ContractBillReviseEntryInfo entry = Info.getEntrys().get(i);
				entry = ContractBillReviseEntryFactory.getLocalInstance(ctx).getContractBillReviseEntryInfo(new ObjectUuidPK(entry.getId()));
				xml.append("<item>\n");
				xml.append("<detail>"+StringUtilBPM.isNULl(entry.getDetail())+"</detail>\n");
				xml.append("<content>"+StringUtilBPM.isNULl(entry.getContent())+"</content>\n");
				xml.append("<remark>"+StringUtilBPM.isNULl(entry.getDesc())+"</remark>\n");
				xml.append("</item>\n");
			 }
  	     xml.append("</billEntries>\n");
		 xml.append("</DATA>"); 
		 str[1] = xml.toString();	
  		}catch (Exception e) {
  			str[0] = "N";
  			str[2] = "其他异常，请查看服务器log日志！";
  			e.printStackTrace();
  		}finally{
  			BPMLogInfo log = new BPMLogInfo();
  			try {
  				log.setLogDate(new Date());
  				log.setName("合同单据ID："+Info.getId()+"; 编号："+Info.getNumber());
  				log.setDescription("EAS结果:"+str[0]+"; 错误信息"+str[1]+str[2]);
  				log.setBeizhu("调用接口方法：GetbillInfo");
  				BPMLogFactory.getLocalInstance(ctx).save(log);
  			} catch (Exception e) {
  				e.printStackTrace();
  			}
  		}
  		return str;
	}

	public String[] GetrRelatedBillInfo(Context ctx, String strBTID,
			IObjectValue billInfo, String strRelatedCode) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] SubmitResult(Context ctx, String strBSID,
			IObjectValue billInfo, boolean success, int procInstID,
			String procURL, String strMessage) {
		ContractBillReviseInfo Info = (ContractBillReviseInfo)billInfo;
    	String[] str = new String[3];
    	str[0] = "Y";
		try {
			try{
				Info = ContractBillReviseFactory.getLocalInstance(ctx).getContractBillReviseInfo(new ObjectUuidPK(Info.getId()),getSelectors());
			}catch (EASBizException e) {
				str[0] = "N";
				str[2] = "根据单据getSelectors获取对象数据，请检查getSelectors方法中属性是否正确,并查看服务器log日志！";
				e.printStackTrace();
			}
			try{
				Info.setState(FDCBillStateEnum.AUDITTING);
				String sql = " update T_CON_ContractBillRevise set fState='"+Info.getState().getValue()+"'" +
						", fDescription='"+procURL+"' " +
						", FSourceFunction='"+procInstID+"' where fid='"+Info.getId()+"'";
				FDCSQLBuilder bu = new FDCSQLBuilder(ctx);
				bu.appendSql(sql);
				bu.executeUpdate(ctx);
			}
			catch (BOSException e) {
				str[0] = "N";
				str[2] = "根据单据state值更新状态sql失败，请检查getState方法是否有值,并查看服务器log日志！";
				e.printStackTrace();
			}
		}catch (Exception e) {
			str[0] = "N";
			str[2] = "其他异常，请查看服务器log日志！";
			e.printStackTrace();
		}finally{
			BPMLogInfo log = new BPMLogInfo();
			try {
				log.setLogDate(new Date());
				log.setName("合同修订单据ID："+Info.getId()+"; 编号："+Info.getNumber());
				log.setDescription("BPM结果："+success+"; EAS结果:"+str[0]+"; 错误信息:"+str[1]+str[2]);
				log.setSimpleName("BPM流程号："+procInstID+";流程URL:"+procURL);
				log.setBeizhu("调用接口方法：_SubmitResult");
				BPMLogFactory.getLocalInstance(ctx).save(log);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return str;
	}

	public SelectorItemCollection getSelectors() {
		SelectorItemCollection sic = new SelectorItemCollection();
		sic.add(new SelectorItemInfo("LandDeveloper.id"));
		sic.add(new SelectorItemInfo("LandDeveloper.number"));
		sic.add(new SelectorItemInfo("LandDeveloper.name"));
		sic.add(new SelectorItemInfo("RespDept.id"));
		sic.add(new SelectorItemInfo("RespDept.name"));
		sic.add(new SelectorItemInfo("RespDept.number"));
		sic.add(new SelectorItemInfo("CreateTime"));
		sic.add(new SelectorItemInfo("Creator.id"));
		sic.add(new SelectorItemInfo("Creator.name"));
		sic.add(new SelectorItemInfo("Creator.number"));
		sic.add(new SelectorItemInfo("curProject.id"));
		sic.add(new SelectorItemInfo("curProject.number"));
		sic.add(new SelectorItemInfo("curProject.name"));
		sic.add(new SelectorItemInfo("curProject.DisplayName"));
		
		 sic.add(new SelectorItemInfo("partC.id"));
		 sic.add(new SelectorItemInfo("partC.number"));
		 sic.add(new SelectorItemInfo("partC.name"));
		 sic.add(new SelectorItemInfo("currency.id"));
		 sic.add(new SelectorItemInfo("currency.number"));
		 sic.add(new SelectorItemInfo("currency.name"));
		 sic.add(new SelectorItemInfo("name"));
		 sic.add(new SelectorItemInfo("OrgUnit.id"));
		 sic.add(new SelectorItemInfo("OrgUnit.name"));
		 sic.add(new SelectorItemInfo("OrgUnit.number"));
		 sic.add(new SelectorItemInfo("OrgUnit.DisplayName"));
		 sic.add(new SelectorItemInfo("PartB.id"));
		 sic.add(new SelectorItemInfo("PartB.number"));
		 sic.add(new SelectorItemInfo("PartB.name"));
		 sic.add(new SelectorItemInfo("PartC.id"));
		 sic.add(new SelectorItemInfo("PartC.name"));
		 sic.add(new SelectorItemInfo("PartC.number"));
		 sic.add(new SelectorItemInfo("Period"));
		 
		 
		 sic.add(new SelectorItemInfo("BizDate"));
		 sic.add(new SelectorItemInfo("GrtRate"));
		 sic.add(new SelectorItemInfo("GrtAmount"));
		 sic.add(new SelectorItemInfo("ContractSourceId.id"));
		 sic.add(new SelectorItemInfo("ContractSourceId.name"));
		 sic.add(new SelectorItemInfo("ContractSourceId.number"));
		 sic.add(new SelectorItemInfo("CostProperty"));
		 sic.add(new SelectorItemInfo("PayScale"));
		 sic.add(new SelectorItemInfo("StampTaxAmt"));
		 sic.add(new SelectorItemInfo("StampTaxRate"));
		 sic.add(new SelectorItemInfo("ContractType.id"));
		 sic.add(new SelectorItemInfo("ContractType.name"));
		 sic.add(new SelectorItemInfo("ContractType.number"));
		 sic.add(new SelectorItemInfo("Amount"));
		 sic.add(new SelectorItemInfo("RespPerson.id"));
		 sic.add(new SelectorItemInfo("RespPerson.name"));
		 sic.add(new SelectorItemInfo("RespPerson.number"));
		 sic.add(new SelectorItemInfo("SignDate"));
		 
		 sic.add(new SelectorItemInfo("OriginalAmount"));
		 sic.add(new SelectorItemInfo("ContractPropert"));
		 sic.add(new SelectorItemInfo("Currency.id"));
		 sic.add(new SelectorItemInfo("Currency.name"));
		 sic.add(new SelectorItemInfo("Currency.number"));
		 sic.add(new SelectorItemInfo("ExRate"));
		 sic.add(new SelectorItemInfo("IsPartAMaterialCon"));
		 sic.add(new SelectorItemInfo("IsCoseSplit"));
		 sic.add(new SelectorItemInfo("chgPercForWarn"));
		 sic.add(new SelectorItemInfo("payPercForWarn"));
		 
		 
		 
		 sic.add(new SelectorItemInfo("ContractBill.number"));
		 
		 sic.add(new SelectorItemInfo("RevAmount"));
		 sic.add(new SelectorItemInfo("RevLocalAmount"));
		 sic.add(new SelectorItemInfo("ReviseReason"));
		 
		 
		 sic.add(new SelectorItemInfo("Detail"));
		 sic.add(new SelectorItemInfo("Content"));
		 sic.add(new SelectorItemInfo("Desc"));
		 sic.add(new SelectorItemInfo("State"));
		 sic.add(new SelectorItemInfo("cu.id"));
		 
		 
		 sic.add(new SelectorItemInfo("ProgrammingContract"));
		return sic;
	}

}
