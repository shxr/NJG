/**
 * output package name
 */
package com.kingdee.eas.fdc.contract.client;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox;
import com.kingdee.bos.ctrl.swing.KDTree;
import com.kingdee.bos.ctrl.swing.event.SelectorEvent;
import com.kingdee.bos.ctrl.swing.event.SelectorListener;
import com.kingdee.bos.ctrl.swing.tree.DefaultKingdeeTreeNode;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.ui.face.ItemAction;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.base.commonquery.client.CustomerParams;
import com.kingdee.eas.basedata.master.cssp.SupplierFactory;
import com.kingdee.eas.basedata.master.cssp.SupplierInfo;
import com.kingdee.eas.basedata.org.CostCenterOrgUnitInfo;
import com.kingdee.eas.basedata.org.NewOrgUtils;
import com.kingdee.eas.basedata.org.OUException;
import com.kingdee.eas.basedata.org.OrgStructureInfo;
import com.kingdee.eas.basedata.org.OrgUnitInfo;
import com.kingdee.eas.basedata.org.OrgViewType;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.common.client.SysContext;
import com.kingdee.eas.fdc.basedata.FDCBillStateEnum;
import com.kingdee.eas.fdc.basedata.FDCCustomerParams;
import com.kingdee.eas.fdc.basedata.FDCHelper;
import com.kingdee.eas.fdc.basedata.ProjectInfo;
import com.kingdee.eas.fdc.basedata.client.FDCClientHelper;
import com.kingdee.eas.fdc.basedata.client.ProjectTreeBuilder;
import com.kingdee.eas.fdc.contract.ContractBillCollection;
import com.kingdee.eas.fdc.contract.ContractBillFactory;
import com.kingdee.eas.fdc.contract.ContractBillInfo;
import com.kingdee.eas.fdc.contract.ContractWithoutTextCollection;
import com.kingdee.eas.fdc.contract.ContractWithoutTextFactory;
import com.kingdee.eas.fdc.contract.ContractWithoutTextInfo;
import com.kingdee.eas.framework.client.ListUI;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.eas.util.client.EASResource;
import com.kingdee.eas.util.client.MsgBox;

/**
 * output class name
 */
public class PayRequestFullFilterUI extends AbstractPayRequestFullFilterUI {
	public static final String resourcePath = "com.kingdee.eas.fdc.contract.client.ContractFullResource";

	protected ItemAction actionListOnLoad;

	private TreeSelectDialog companySelectDlg;

	private boolean isLoaded;

	protected ListUI listUI;

	private TreeSelectDialog projectSelectDlg;
	private Object param=null;	
	
	//是否使用成本月结,当前财务组织的期间
	private Date[] pkdate ;
	/**
	 * output class constructor
	 */
	public PayRequestFullFilterUI(ListUI listUI, ItemAction actionListOnLoad)
			throws Exception {
		super();
		this.listUI = listUI;
		this.actionListOnLoad = actionListOnLoad;		
		
		pkdate = FDCClientHelper.getCompanyCurrentDate();
	}

	protected void btnCompanySelect_actionPerformed(ActionEvent e)
			throws Exception {
		if (this.companySelectDlg == null) {
			this.initCompanyDlg(null);
		}
		Object object = companySelectDlg.showDialog();
		setCompanyByTree(object);
		super.btnCompanySelect_actionPerformed(e);
	}

	protected void btnProjectSelect_actionPerformed(ActionEvent e)
			throws Exception {
		if (this.projectSelectDlg == null) {
			this.initProjectDlg(null);
		}
		Object object = projectSelectDlg.showDialog();
		setProjectByTree(object);
		super.btnProjectSelect_actionPerformed(e);
	}

	public void clear() {
		this.companySelectDlg = null;
		this.txtCompany.setText(null);
		this.txtCompany.setUserObject(null);
		CostCenterOrgUnitInfo currentCompany = SysContext.getSysContext()
				.getCurrentCostUnit();
		if (currentCompany.isIsBizUnit()) {
			this.btnCompanySelect.setEnabled(false);
			this.txtCompany.setText(currentCompany.getName());
			this.txtCompany.setUserObject(new String[] { currentCompany.getId()
					.toString() });
		}
		this.projectSelectDlg = null;
		this.txtProject.setText(null);
		this.txtProject.setUserObject(null);
		this.f7Contract.setValue(null);

		this.f7Payee.setData(null);
		this.pkDateFrom.setValue(pkdate[0]);
		this.pkDateTo.setValue(pkdate[1]);
		this.radioStateAll.setSelected(true);
		this.f7Contract.setDisplayFormat("$name$");
		this.f7Contract.setEditFormat("$number$");
		this.f7Contract
				.setQueryInfo("com.kingdee.eas.fdc.contract.app.ContractAndWithoutUnionQuery");
		
		f7Contract.addSelectorListener(new SelectorListener(){
			public void willShow(SelectorEvent e)
			{
				if(txtProject.getUserObject()==null||((Object[]) txtProject.getUserObject()).length<1){
					MsgBox.showWarning(EASResource.getString(resourcePath, "SelectCurProj"));
					SysUtil.abort();
//					e.setCanceled(true);
//					return;
				}
				Object [] arrays=(Object [])txtProject.getUserObject();
				HashSet idSet=new HashSet();
				for(int i=0;i<arrays.length;i++){
					idSet.add(arrays[i]);
				}
				KDBizPromptBox f7=(KDBizPromptBox)e.getSource();
				f7.getQueryAgent().resetRuntimeEntityView();
				EntityViewInfo view=new EntityViewInfo();
				FilterInfo filter=new FilterInfo();
				filter.getFilterItems().add(new FilterItemInfo("curProject.id",idSet,CompareType.INCLUDE));
				view.setFilter(filter);
				f7.setEntityViewInfo(view);
				
			}
		});
		this.f7Payee
				.setQueryInfo("com.kingdee.eas.basedata.master.cssp.app.F7SupplierQuery");
	}

	public FilterInfo getFilterInfo() {
		FDCCustomerParams para=new FDCCustomerParams(getCustomerParams());
		FilterInfo filter = new FilterInfo();
		filter.getFilterItems().add(
				new FilterItemInfo("id", null, CompareType.NOTEQUALS));
		if (para.getStringArray("companyIds") != null) {
			filter.getFilterItems().add(
					new FilterItemInfo("orgUnit.id", FDCHelper
							.getSetByArray(para.getStringArray("companyIds")),
							CompareType.INCLUDE));
		}else{
			if(companySelectDlg==null){
				try{
					initCompanyDlg(null);
				}catch(Exception e){
					e.printStackTrace();
					SysUtil.abort();
				}
			}
			TreeModel tree = (DefaultTreeModel) companySelectDlg.getTree();
			DefaultKingdeeTreeNode root = (DefaultKingdeeTreeNode) tree.getRoot();
			java.util.List list = new ArrayList();
			if(root.getUserObject()!=null){
				list.add(root.getUserObject());
			}
			popNode(list, root);
			HashSet set=new HashSet();
			for(Iterator iter=list.iterator();iter.hasNext();){
				OrgStructureInfo company = (OrgStructureInfo)iter.next();
				set.add(company.getUnit().getId().toString());
			}
			
			if(set.size()>0){
				filter.getFilterItems().add(new FilterItemInfo("orgUnit.id", set,CompareType.INCLUDE));	
			}
			
			
		}
		if (para.getStringArray("projectIds") != null) {
			filter.getFilterItems().add(
					new FilterItemInfo("curProject.id", FDCHelper
							.getSetByArray(para.getStringArray("projectIds")),
							CompareType.INCLUDE));
		}else{//没有工程项目则只显示已启用的
			filter.getFilterItems().add(new FilterItemInfo("curProject.isEnabled", Boolean.TRUE));
		}
		if (para.getStringArray("contractIds") != null) {
			filter.getFilterItems().add(
					new FilterItemInfo("contractId", FDCHelper
							.getSetByArray(para.getStringArray("contractIds")),
							CompareType.INCLUDE));
		}
		if (para.get("payeeId")!= null) {
			filter.getFilterItems().add(
					new FilterItemInfo("supplier.id", para.get("payeeId")));
		}
		if (para.getDate("dateFrom") != null) {
			filter.getFilterItems().add(
					new FilterItemInfo("payDate", para.getDate("dateFrom"),
							CompareType.GREATER_EQUALS));
		}
		if (para.getDate("dateTo") != null) {
			filter.getFilterItems().add(
					new FilterItemInfo("payDate", FDCHelper.getNextDay(para.getDate("dateTo")), CompareType.LESS));
		}
		String state = null;
		if(para.isNotNull("payState")){
			if (para.getInt("payState") == 0) {
				state =FDCBillStateEnum.SAVED_VALUE;
			} else if (para.getInt("payState") == 1) {
				state =FDCBillStateEnum.SUBMITTED_VALUE;
			} else if (para.getInt("payState") == 2) {
				state =FDCBillStateEnum.AUDITTING_VALUE;
			} else if (para.getInt("payState") == 3) {
				state =FDCBillStateEnum.AUDITTED_VALUE;
			}
		}

		if (state != null) {
			filter.getFilterItems().add(new FilterItemInfo("state", state));
		}
		if(para.isNotNull("chkIncludeClose")&&!para.getBoolean("chkIncludeClose")){
			filter.getFilterItems().add(new FilterItemInfo("hasClosed", Boolean.FALSE));
		}

		/**
		 * 如果是无文本合同生成的，且还是提交状态，是不能看见的，不然可以修改<br>
		 * R110317-188 emanon
		 */
		FilterInfo withouttext = new FilterInfo();
		withouttext.getFilterItems().add(
				new FilterItemInfo("source", "0D6DD1F4"));
		withouttext.getFilterItems().add(
				new FilterItemInfo("source", "3D9A5388"));
		withouttext.getFilterItems().add(
				new FilterItemInfo("state", FDCBillStateEnum.AUDITTED_VALUE));
		withouttext.setMaskString("#0 or (#1 and #2)");
		try {
			filter.mergeFilter(withouttext, "and");
		} catch (BOSException e) {
			e.printStackTrace();
			SysUtil.abort();
		}
		
		return filter;
	}

/*	public Object getParam() {
		PayRequestFullFilterParam param = new PayRequestFullFilterParam();
		param.setCompayIds((String[]) this.txtCompany.getUserObject());
		param.setProjectIds((String[]) this.txtProject.getUserObject());

		Object[] contracts = (Object[]) this.f7Contract.getValue();
		if (!FDCHelper.isEmpty(contracts)) {
			String[] ids = new String[contracts.length];
			for (int i = 0; i < contracts.length; i++) {
				if (contracts[i] instanceof ContractBillInfo) {
					ids[i] = ((ContractBillInfo) contracts[i]).getId()
							.toString();
				} else if (contracts[i] instanceof ContractWithoutTextInfo) {
					ids[i] = ((ContractWithoutTextInfo) contracts[i]).getId()
							.toString();
				}
			}
			param.setContractIds(ids);
		}

		SupplierInfo supplierInfo = (SupplierInfo) this.f7Payee.getValue();
		if (supplierInfo != null) {
			param.setPayeeId(supplierInfo.getId().toString());
		}
		param.setDateFrom((Date) this.pkDateFrom.getValue());
		param.setDateTo((Date) this.pkDateTo.getValue());

		if (this.radioSave.isSelected()) {
			param.setPayState(0);
		} else if (this.radioSubmit.isSelected()) {
			param.setPayState(1);
		} else if (this.radioAuditing.isSelected()) {
			param.setPayState(2);
		} else if (this.radioAudited.isSelected()) {
			param.setPayState(3);
		} else if (this.radioStateAll.isSelected()) {
			param.setPayState(4);
		}
		param.setIncludeClose(this.chkIncludeClose.isSelected());
		return param;
	}*/

	private void initCompanyDlg(String[] selectCompayIds) throws OUException,
			Exception {
		/*CtrlUnitInfo cuInfo = SysContext.getSysContext().getCurrentCtrlUnit();
		if (cuInfo == null) {
			throw new OUException(OUException.CU_CAN_NOT_NULL);
		}*/
		
		//非CU组织会过滤出当前CU的其它财务组织数据，以当前财务组织过滤
		OrgUnitInfo orgUnitInfo = SysContext.getSysContext().getCurrentFIUnit();
		if(orgUnitInfo==null){
			orgUnitInfo=SysContext.getSysContext().getCurrentOrgUnit();
		}
		TreeModel orgTreeModel = NewOrgUtils.getTreeModel(
				OrgViewType.COSTCENTER, "", orgUnitInfo.getId().toString(),
				null, FDCHelper
						.getActionPK(this.actionListOnLoad));
		this.companySelectDlg = new TreeSelectDialog(this, orgTreeModel,
				"getUnit,getId,toString", selectCompayIds);
	}

	private void initProjectDlg(String[] projectIds) throws Exception {
		ProjectTreeBuilder builder = new ProjectTreeBuilder(true);
		KDTree tree = new KDTree();
		if (this.companySelectDlg != null
				&& this.companySelectDlg.getSelectTree() != null) {
			builder.buildByCostOrgTree(tree, this.companySelectDlg.getSelectTree());
		} else {
			builder.build(this.listUI, tree, this.actionListOnLoad);
		}
		TreeModel model = tree.getModel();
		FDCHelper.setTreeForbidNode((DefaultKingdeeTreeNode) model.getRoot(),
				"isIsEnabled", Boolean.FALSE);
		projectSelectDlg = new TreeSelectDialog(this, model, "getId,toString",
				projectIds);
	}

	public void onLoad() throws Exception {
		super.onLoad();
		if (!isLoaded) {
			this.clear();
		}
		isLoaded = true;
	}

	private void setCompanyByTree(Object object) {
		List companyIdList = new ArrayList();
		if (object != null) {
			List companyList = (List) object;
			String text = "";
			for (int i = 0; i < companyList.size(); i++) {
				OrgStructureInfo company = (OrgStructureInfo) companyList
						.get(i);
				companyIdList.add(company.getUnit().getId().toString());
				if (company.getUnit().isIsCostOrgUnit()
						|| company.getUnit().isIsProfitOrgUnit()) {
					if (company.isIsLeaf()) {
						if (!text.equals("")) {
							text += ",";
						}
						text += company.getUnit().getName();
					}
				}
			}
			Object[] ids = companyIdList.toArray(new String[] {});
			ArrayList oldArray = new ArrayList(FDCHelper
					.getSetByArray((String[]) ids));
			ArrayList newArray = new ArrayList(FDCHelper
					.getSetByArray((String[]) this.txtCompany.getUserObject()));
			if (!oldArray.equals(newArray)) {
				try {
					this.initProjectDlg(null);
				} catch (Exception e) {
					e.printStackTrace();
					SysUtil.abort();
				}
				this.txtProject.setUserObject(null);
				this.txtProject.setText(null);
			}
			this.txtCompany.setText(text);
			if (FDCHelper.isEmpty(ids)) {
				this.txtCompany.setUserObject(null);
			} else {
				this.txtCompany.setUserObject(ids);
			}
		}
	}

	public void setCustomerParams(CustomerParams cp)
	{
		FDCCustomerParams para=new FDCCustomerParams(cp);
		try {
			initCompanyDlg(para.getStringArray("companyIds"));
			setCompanyByTree(companySelectDlg.getUserObject());
			initProjectDlg(para.getStringArray("projectIds"));
			setProjectByTree(projectSelectDlg.getUserObject());
		} catch (Exception e) {
			e.printStackTrace();
			SysUtil.abort();
		}

		if (!FDCHelper.isEmpty(para.getStringArray("contractIds"))) {
			ContractBillCollection contractBills = null;
			ContractWithoutTextCollection contractBillWithoutTexts = null;
			try {
				contractBills = ContractBillFactory.getRemoteInstance()
						.getContractBillCollection(
								FDCHelper.getIncludeEntityView("id", para.getStringArray("contractIds")));
				contractBillWithoutTexts = ContractWithoutTextFactory
						.getRemoteInstance().getContractWithoutTextCollection(
								FDCHelper.getIncludeEntityView("id", para.getStringArray("contractIds")));
			} catch (BOSException e) {
				e.printStackTrace();
				SysUtil.abort();
			}
			int size=contractBills.size()+ contractBillWithoutTexts.size();
			Object [] objects=new Object[size];
			for (int i = 0; i < contractBills.size(); i++) {
				objects[i]=contractBills.get(i);
			}
			for (int j = contractBills.size(); j < size; j++) {
				objects[j]=contractBillWithoutTexts.get(j-contractBills.size());
			}
			this.f7Contract.setValue(objects);
		}

		if (para.get("payeeId") != null) {
			SupplierInfo supplier = null;
			try {
				supplier = SupplierFactory.getRemoteInstance().getSupplierInfo(
						new ObjectUuidPK(BOSUuid.read(para.get("payeeId"))));
			} catch (EASBizException e) {
				e.printStackTrace();
				SysUtil.abort();
			} catch (BOSException e) {
				e.printStackTrace();
				SysUtil.abort();
			}
			this.f7Payee.setValue(supplier);
		} else {
			this.f7Payee.setValue(null);
		}
		this.pkDateFrom.setValue(para.getDate("dateFrom"));
		this.pkDateTo.setValue(para.getDate("dateTo"));
		if(para.isNotNull("payState")){
			if (para.getInt("payState") == 0) {
				this.radioSave.setSelected(true);
			} else if (para.getInt("payState") == 1) {
				this.radioSubmit.setSelected(true);
			} else if (para.getInt("payState") == 2) {
				this.radioAuditing.setSelected(true);
			} else if (para.getInt("payState") == 3) {
				this.radioAudited.setSelected(true);
			} else if (para.getInt("payState") == 4) {
				this.radioStateAll.setSelected(true);
			}
		}
		if(para.isNotNull("chkIncludeClose")){
			this.chkIncludeClose.setSelected(para.getBoolean("chkIncludeClose"));
		}
		super.setCustomerParams(cp);
	}
/*	public void setParam(Object param) {

		PayRequestFullFilterParam para = (PayRequestFullFilterParam) param;
//		CustomerParams  para = new CustomerParams ();
		try {
			initCompanyDlg(para.getCompayIds());
			setCompanyByTree(companySelectDlg.getUserObject());
			initProjectDlg(para.getProjectIds());
			setProjectByTree(projectSelectDlg.getUserObject());
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!FDCHelper.isEmpty(para.getContractIds())) {
			ContractBillCollection contractBills = null;
			ContractWithoutTextCollection contractBillWithoutTexts = null;
			try {
				contractBills = ContractBillFactory.getRemoteInstance()
						.getContractBillCollection(
								FDCHelper.getIncludeEntityView("id", para
										.getContractIds()));
				contractBillWithoutTexts = ContractWithoutTextFactory
						.getRemoteInstance().getContractWithoutTextCollection(
								FDCHelper.getIncludeEntityView("id", para
										.getContractIds()));
			} catch (BOSException e) {
				e.printStackTrace();
			}
			int size=contractBills.size()+ contractBillWithoutTexts.size();
			Object [] objects=new Object[size];
			for (int i = 0; i < contractBills.size(); i++) {
				objects[i]=contractBills.get(i);
			}
			for (int j = contractBills.size(); j < size; j++) {
				objects[j]=contractBillWithoutTexts.get(j-contractBills.size());
			}
			this.f7Contract.setValue(objects);
		}

		if (para.getPayeeId() != null) {
			SupplierInfo supplier = null;
			try {
				supplier = SupplierFactory.getRemoteInstance().getSupplierInfo(
						new ObjectUuidPK(BOSUuid.read(para.getPayeeId())));
			} catch (EASBizException e) {
				e.printStackTrace();
			} catch (BOSException e) {
				e.printStackTrace();
			}
			this.f7Payee.setValue(supplier);
		} else {
			this.f7Payee.setValue(null);
		}
		this.pkDateFrom.setValue(para.getDateFrom());
		this.pkDateTo.setValue(para.getDateTo());
		if (para.getPayState() == 0) {
			this.radioSave.setSelected(true);
		} else if (para.getPayState() == 1) {
			this.radioSubmit.setSelected(true);
		} else if (para.getPayState() == 2) {
			this.radioAuditing.setSelected(true);
		} else if (para.getPayState() == 3) {
			this.radioAudited.setSelected(true);
		} else if (para.getPayState() == 4) {
			this.radioStateAll.setSelected(true);
		}
		this.chkIncludeClose.setSelected(para.isIncludeClose());
//		super.setParam(param);
	}*/
	public CustomerParams getCustomerParams()
	{
//		PayRequestFullFilterParam param = new PayRequestFullFilterParam();
		FDCCustomerParams param=new FDCCustomerParams();
		param.add("companyIds", (String[]) this.txtCompany.getUserObject());
		param.add("projectIds", (String[]) this.txtProject.getUserObject());

//		param.setCompayIds((String[]) this.txtCompany.getUserObject());
//		param.setProjectIds((String[]) this.txtProject.getUserObject());

		Object[] contracts = (Object[]) this.f7Contract.getValue();
		if (!FDCHelper.isEmpty(contracts)) {
			String[] ids = new String[contracts.length];
			for (int i = 0; i < contracts.length; i++) {
				if (contracts[i] instanceof ContractBillInfo) {
					ids[i] = ((ContractBillInfo) contracts[i]).getId()
							.toString();
				} else if (contracts[i] instanceof ContractWithoutTextInfo) {
					ids[i] = ((ContractWithoutTextInfo) contracts[i]).getId()
							.toString();
				}
			}
//			param.setContractIds(ids);
			param.add("contractIds",ids);
		}

		SupplierInfo supplierInfo = (SupplierInfo) this.f7Payee.getValue();
		if (supplierInfo != null) {
			param.add("payeeId",supplierInfo.getId().toString());
//			param.setPayeeId(supplierInfo.getId().toString());
		}
		param.add("dateFrom", (Date)this.pkDateFrom.getValue());
		param.add("dateTo", (Date)this.pkDateTo.getValue());
//		param.setDateFrom((Date) this.pkDateFrom.getValue());
//		param.setDateTo((Date) this.pkDateTo.getValue());

		if (this.radioSave.isSelected()) {
			param.add("payState", 0);
//			param.setPayState(0);
		} else if (this.radioSubmit.isSelected()) {
			param.add("payState", 1);
//			param.setPayState(1);
		} else if (this.radioAuditing.isSelected()) {
			param.add("payState", 2);
//			param.setPayState(2);
		} else if (this.radioAudited.isSelected()) {
			param.add("payState", 3);
//			param.setPayState(3);
		} else if (this.radioStateAll.isSelected()) {
			param.add("payState", 4);
//			param.setPayState(4);
		}
		param.add("chkIncludeClose", this.chkIncludeClose.isSelected());
//		param.setIncludeClose(this.chkIncludeClose.isSelected());
		return param.getCustomerParams();

	}
	private void setProjectByTree(Object object) {
		List projectIdList = new ArrayList();
		if (object != null) {
			List projectList = (List) object;
			String text = "";
			for (int i = 0; i < projectList.size(); i++) {
				if (projectList.get(i) instanceof ProjectInfo) {
					ProjectInfo project = (ProjectInfo) projectList.get(i);
					if (project.isIsLeaf()) {
						if (!text.equals("")) {
							text += ",";
						}
						text += project.getName();
					}
					projectIdList.add(project.getId().toString());
				}
			}
			this.txtProject.setText(text);
			Object[] ids = projectIdList.toArray(new String[] {});
			if (FDCHelper.isEmpty(ids)) {
				this.txtProject.setUserObject(null);
			} else {
				this.txtProject.setUserObject(ids);
			}
		}
	}

	/**
	 * output storeFields method
	 */
	public void storeFields() {
		super.storeFields();
	}

	public boolean verify() {
		if(this.pkDateTo.getValue()!=null&&this.pkDateFrom
				.getValue() != null) {
			if (((Date) this.pkDateTo.getValue()).before((Date) this.pkDateFrom
					.getValue())) {
				MsgBox.showWarning(this, EASResource.getString(resourcePath,
						"DateBoundErrer"));
				return false;
			}
		}
		return true;
	}
	
	private void popNode(java.util.List list, DefaultKingdeeTreeNode root) {
		for (Enumeration c = root.children(); c.hasMoreElements();) {
			DefaultKingdeeTreeNode node = (DefaultKingdeeTreeNode) c
					.nextElement();
			if(node.isLeaf()) {
				list.add(node.getUserObject());
			}
			popNode(list, node);
		}
	}

}