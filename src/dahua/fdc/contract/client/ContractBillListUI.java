/**
 * output package name
 */
package com.kingdee.eas.fdc.contract.client;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.RowSet;
import javax.swing.Action;

import org.apache.log4j.Logger;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.ctrl.kdf.table.ICell;
import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.KDTSelectManager;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.kdf.table.event.KDTDataFillListener;
import com.kingdee.bos.ctrl.kdf.table.event.KDTDataRequestEvent;
import com.kingdee.bos.ctrl.swing.KDFileChooser;
import com.kingdee.bos.ctrl.swing.tree.DefaultKingdeeTreeNode;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.ormapping.ObjectStringPK;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.dao.query.IQueryExecutor;
import com.kingdee.bos.metadata.IMetaDataPK;
import com.kingdee.bos.metadata.MetaDataPK;
import com.kingdee.bos.metadata.data.SortType;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemCollection;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.entity.SelectorItemInfo;
import com.kingdee.bos.metadata.entity.SorterItemCollection;
import com.kingdee.bos.metadata.entity.SorterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.metadata.resource.BizEnumValueInfo;
import com.kingdee.bos.ui.face.BOSUIContext;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.ui.face.IUIFactory;
import com.kingdee.bos.ui.face.IUIWindow;
import com.kingdee.bos.ui.face.ItemAction;
import com.kingdee.bos.ui.face.UIFactory;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.base.attachment.client.AttachmentUIContextInfo;
import com.kingdee.eas.base.attachment.common.AttachmentClientManager;
import com.kingdee.eas.base.attachment.common.AttachmentManagerFactory;
import com.kingdee.eas.base.attachment.util.FileGetter;
import com.kingdee.eas.base.attachment.util.Resrcs;
import com.kingdee.eas.base.attachment.util.StringUtil4File;
import com.kingdee.eas.base.multiapprove.MultiApproveInfo;
import com.kingdee.eas.base.multiapprove.client.MultiApproveUtil;
import com.kingdee.eas.base.permission.PermissionFactory;
import com.kingdee.eas.basedata.org.FullOrgUnitInfo;
import com.kingdee.eas.basedata.org.OrgStructureInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.common.client.OprtState;
import com.kingdee.eas.common.client.SysContext;
import com.kingdee.eas.common.client.UIContext;
import com.kingdee.eas.common.client.UIFactoryName;
import com.kingdee.eas.fdc.basedata.ContractTypeInfo;
import com.kingdee.eas.fdc.basedata.CostSplitStateEnum;
import com.kingdee.eas.fdc.basedata.CurProjectFactory;
import com.kingdee.eas.fdc.basedata.CurProjectInfo;
import com.kingdee.eas.fdc.basedata.FDCBillInfo;
import com.kingdee.eas.fdc.basedata.FDCBillStateEnum;
import com.kingdee.eas.fdc.basedata.FDCConstants;
import com.kingdee.eas.fdc.basedata.FDCHelper;
import com.kingdee.eas.fdc.basedata.FDCSQLBuilder;
import com.kingdee.eas.fdc.basedata.ProjectHelper;
import com.kingdee.eas.fdc.basedata.client.FDCClientHelper;
import com.kingdee.eas.fdc.basedata.client.FDCClientUtils;
import com.kingdee.eas.fdc.basedata.client.FDCMsgBox;
import com.kingdee.eas.fdc.basedata.util.FdcWfUtil;
import com.kingdee.eas.fdc.basedata.util.FullOrgUnitHelper;
import com.kingdee.eas.fdc.contract.ContractBillFactory;
import com.kingdee.eas.fdc.contract.ContractBillInfo;
import com.kingdee.eas.fdc.contract.ContractContentCollection;
import com.kingdee.eas.fdc.contract.ContractContentFactory;
import com.kingdee.eas.fdc.contract.ContractContentInfo;
import com.kingdee.eas.fdc.contract.ContractUtil;
import com.kingdee.eas.fdc.contract.FDCUtils;
import com.kingdee.eas.fdc.contract.ForWriteMarkHelper;
import com.kingdee.eas.fdc.contract.IContractBill;
import com.kingdee.eas.fdc.contract.programming.ProgrammingContractInfo;
import com.kingdee.eas.fdc.contract.programming.client.ContractBillLinkProgContEditUI;
import com.kingdee.eas.fm.common.ContextHelperFactory;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.FrameWorkException;
import com.kingdee.eas.framework.ICoreBase;
import com.kingdee.eas.framework.ICoreBillBase;
import com.kingdee.eas.framework.IFWEntityStruct;
import com.kingdee.eas.framework.TreeBaseInfo;
import com.kingdee.eas.tools.datatask.DatataskMode;
import com.kingdee.eas.tools.datatask.DatataskParameter;
import com.kingdee.eas.tools.datatask.client.DatataskCaller;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.eas.util.client.EASResource;
import com.kingdee.eas.util.client.MsgBox;
import com.kingdee.jdbc.rowset.IRowSet;

/**
 * 
 * ����:��ͬ��ʱ������
 * 
 * @author liupd date:2006-7-25
 *         <p>
 * @version EAS5.1.3
 */
public class ContractBillListUI extends AbstractContractBillListUI {
	
	/**
	 * ���ҽ�� ��
	 */
	private static final String AMOUNT = "amount";

	private static final Logger logger = CoreUIObject.getLogger(ContractBillListUI.class);

	private int viewType = 0;//��ͬ�༭��Ϣ
	private Map contentMap = new HashMap();
	private Map attachMap = new HashMap();
	// �ٴι鵵ʱ���º�ͬ���
	private boolean isUpdateConNo = false;
	// ��ͬ������������ϴ�����
    private boolean canUploadForAudited = false;
    private boolean isMultiProject = false;
	/**
	 * ��������ˣ��������ʱ��
	 */
	Map auditMap = new HashMap();
	//���ز�ҵ��ϵͳ���Ĺ����Ƿ����������ʼ����۹���
	boolean isUseWriteMark=false;
	//�����ͬ����ѡ���ύ״̬������ͬ
	private boolean isSupply = false;
	
	public ContractBillListUI() throws Exception {
		super();
	}
	
	public void onLoad() throws Exception {
		this.btnAddContent.setEnabled(false);
		this.menuItemAddContent.setEnabled(false);
		this.actionAddContent.setVisible(false);
		this.btnViewContent.setEnabled(true);
		this.menuItemViewContent.setEnabled(true);
			
		this.tblMain.getDataRequestManager().addDataFillListener(new KDTDataFillListener(){

			/*
			 * ���������ͬ���룬��������ļ��Σ�����ͬΪ���������Ӻ�ͬΪ���Ӽ�
			 */
			public void afterDataFill(KDTDataRequestEvent e) {
				boolean isMainOrder = false; // �Ƿ񰴡�����ͬ���롱����
				SorterItemCollection sortItems = mainQuery.getSorter();
				if(sortItems!=null && sortItems.size()>0){
		        	for(Iterator it=sortItems.iterator();it.hasNext();){
		        		SorterItemInfo sortItem = (SorterItemInfo)it.next();
		        		if(sortItem.getPropertyName().equals("mainContractNumber")){
		        			isMainOrder = true;
		        			break;
		        		}
		        	}
		        }
				if(attachMap==null){
					attachMap=new HashMap();
				}
				if(auditMap==null){
					auditMap=new HashMap();
				}
				if(contentMap==null){
					contentMap=new HashMap();
				}
				String preNumber = null;
				for (int i = e.getFirstRow(); i <= e.getLastRow(); i++) {
					IRow row = tblMain.getRow(i);
					String idkey = row.getCell("id").getValue().toString();
					if(contentMap.containsKey(idkey)){
						row.getCell("content").setValue(Boolean.TRUE);
					}
					else{
						row.getCell("content").setValue(Boolean.FALSE);
					}
					if(attachMap.containsKey(idkey)){
						row.getCell("attachment").setValue(Boolean.TRUE);
					}
					else{
						row.getCell("attachment").setValue(Boolean.FALSE);
					}
					if(auditMap.containsKey(idkey)){
						MultiApproveInfo info = (MultiApproveInfo)auditMap.get(idkey);
						//����ж༶��������˱���ͻᵼ����������˺��������ʱ�����ݲ���ȷ,BUG,by Cassiel_peng
//						if(row.getCell("auditor").getValue()==null){
							row.getCell("auditor").setValue(info.getCreator().getName());
							row.getCell("auditTime").setValue(info.getCreateTime());
//						}
					}
					if(isMainOrder){
						String number = "";
						if(row.getCell("mainContractNumber")!=null){
							if (row.getCell("mainContractNumber").getValue() != null) {
								number = row.getCell("mainContractNumber").getValue().toString();
							}
							if (!number.equals(preNumber)) {
								row.setTreeLevel(0);
							} else if (!"".equals(number)) {
								row.setTreeLevel(1);
								tblMain.getTreeColumn().setDepth(2);
								row.getCell("number").setValue("   " + row.getCell("number").getValue());
							}
							preNumber = number;
						}
					}
					
					Object number = row.getCell("number").getValue();
					Object mainNumber = row.getCell("mainContractNumber").getValue();
					// �����ͬ��ʾ
					if (number != null && mainNumber != null && number.toString().equals(mainNumber.toString())) {
						row.getCell("tabShowIsAmtWithoutCost").setValue(null);
					}
				}
			}
		});
		
		super.onLoad();
		actionStore.putValue(Action.SMALL_ICON, EASResource.getIcon("imgTbtn_archive"));
		actionAntiStore.putValue(Action.SMALL_ICON, EASResource.getIcon("imgTbtn_undistribute"));
		
		kDSplitPane2.add(contContrList, "bottom");
		kDSplitPane2.setDividerLocation(1.0);
		actionConMove.setEnabled(true);
		actionQuery.setVisible(true);
		actionQuery.setEnabled(true);
		//���ع�����Լ���
		actionProgram.setVisible(false);
	}
	
	protected void updateButtonStatus() {
		super.updateButtonStatus();
		
//		�ſ��������������ɾ��
		actionAddNew.setEnabled(true);
		actionEdit.setEnabled(true);
		actionRemove.setEnabled(true);
		actionAddNew.setVisible(true);
		actionEdit.setVisible(true);
		actionRemove.setVisible(true);
		actionAttachment.setEnabled(true);
		actionAttachment.setVisible(true);
		menuEdit.setVisible(true);
		actionRespite.setVisible(true);
		actionCancelRespite.setVisible(true);
	}
	
	public void refreshListForOrder() throws Exception
    {
        SorterItemCollection sortItems = this.mainQuery.getSorter();
        if(sortItems!=null && sortItems.size()>0){
        	for(Iterator it=sortItems.iterator();it.hasNext();){
        		SorterItemInfo sortItem = (SorterItemInfo)it.next();
        		if(sortItem.getPropertyName().equals("mainContractNumber")){
        			SorterItemInfo cloneSort = (SorterItemInfo)sortItem.clone();
        			SorterItemInfo newSort = new SorterItemInfo();
        			newSort.setPropertyName("contractPropert");
        			newSort.setSortType(SortType.ASCEND);
        			this.mainQuery.getSorter().clear();
        			this.mainQuery.getSorter().add(cloneSort);
        			this.mainQuery.getSorter().add(newSort);
        			break;
        		}
        	}
        }
        super.refreshListForOrder();
    }

	protected void tblMain_tableSelectChanged(
			com.kingdee.bos.ctrl.kdf.table.event.KDTSelectEvent e)
			throws Exception {
		super.tblMain_tableSelectChanged(e);	
		IRow row;
		if (this.tblMain.getSelectManager().getActiveRowIndex()!= -1) {
			row = this.tblMain.getRow(this.tblMain.getSelectManager().getActiveRowIndex());
			if((tblMain.getSelectManager().getBlocks().size() > 1) 
				||(e.getSelectBlock().getBottom() - e.getSelectBlock().getTop() >0)){
				actionRespite.setEnabled(true);
				actionCancelRespite.setEnabled(true);
			}else{
				if(Boolean.TRUE.equals(row.getCell("isRespite").getValue())){
					actionRespite.setEnabled(false);
					actionCancelRespite.setEnabled(true);
				}else{
					actionRespite.setEnabled(true);
					actionCancelRespite.setEnabled(false);
				}
			}
			if(row.getCell("state").getValue().equals("�ϼ�")) return;
			if (row.getCell("state") != null) {		
				if (FDCBillStateEnum.AUDITTED_VALUE.equalsIgnoreCase(((BizEnumValueInfo) row.getCell("state").getValue()).getValue()
						.toString())) {
					if((e.getSelectBlock().getBottom() - e.getSelectBlock().getTop() ==0)&&
							(tblMain.getSelectManager().getBlocks().size()== 1)){
						actionCancelRespite.setEnabled(true);
						actionRespite.setEnabled(false);
					}
					
					if(!((Boolean)row.getCell("isArchived").getValue()).booleanValue()){
						actionStore.setEnabled(true);
						actionAntiStore.setEnabled(false);
					}else{
						actionAntiStore.setEnabled(true);
						actionStore.setEnabled(false);
					}
				}else{
					actionStore.setEnabled(false);
					actionAntiStore.setEnabled(false);
				}
			}
		}
		String selectedKeyValue = getSelectedKeyValue();
		if (null != selectedKeyValue) {
			SelectorItemCollection sic = new SelectorItemCollection();

			sic.add("id");
			sic.add("programmingContract");
			sic.add("state");

			ContractBillInfo contractBillInfo = ContractBillFactory.getRemoteInstance().getContractBillInfo(
					new ObjectUuidPK(selectedKeyValue), sic);
			if (contractBillInfo.getState().equals(FDCBillStateEnum.AUDITTED)
					|| contractBillInfo.getState().equals(FDCBillStateEnum.AUDITTING) || isRelateCon(contractBillInfo.getId().toString())) {
				this.btnProgram.setEnabled(false);
			} else {
				this.btnProgram.setEnabled(true);
			}
		}
		// modify end
	}

	private boolean isRelateCon(String conId) throws BOSException, SQLException {
		FDCSQLBuilder builder = new FDCSQLBuilder();
		builder.appendSql(" select con.fid conId from t_con_contractbillentry entry");
		builder
				.appendSql(" inner join T_CON_Contractbill con on con.fid=entry.fparentid  and con.fisAmtWithoutCost=1 and con.fcontractPropert='SUPPLY'  ");
		builder
				.appendSql(" inner join T_Con_contractBill parent on parent.fnumber = con.fmainContractNumber  and parent.fcurprojectid=con.fcurprojectid	 ");
		builder.appendSql("  where entry.FRowkey='am' and");
		builder.appendParam("  con.fid", conId);
		IRowSet rowSet = builder.executeQuery();
		while (rowSet.next()) {
			return true;
		}
		return false;
	}

	/**
	 * output actionStore_actionPerformed method
	 */
	public void actionStore_actionPerformed(ActionEvent e) throws Exception {
		checkSelected();
		HashMap hm = new UIContext(this);
		hm.put("isUpdateConNo", Boolean.valueOf(isUpdateConNo));
		IRow row;
		if (this.tblMain.getSelectManager().getActiveRowIndex()== -1) {
			//��ʾѡ��
		}else{
			row = this.tblMain.getRow(this.tblMain.getSelectManager().getActiveRowIndex());
			
			if(row.getCell("id")!=null){
				hm.put("id", row.getCell("id").getValue().toString());
			}
			if(row.getCell("number")!=null){
				hm.put("number", row.getCell("number").getValue().toString());
			}
			/*
			 * �ѵ�ǰѡ�еĹ�����Ŀ�ͺ�ͬ���ʹ���EditUI
			 */
//			BOSUuid projId = ((CurProjectInfo) getProjSelectedTreeNode().getUserObject()).getId();
//			BOSUuid typeId = null;
//			if (getTypeSelectedTreeNode() != null && getTypeSelectedTreeNode().isLeaf()) {
//
//				typeId = ((ContractTypeInfo) getTypeSelectedTreeNode().getUserObject()).getId();
//			}
//			hm.put("projectId", projId);
//			hm.put("contractTypeId", typeId);
			
		}	
		IUIFactory uiFactory = null;
		uiFactory = UIFactory.createUIFactory(UIFactoryName.MODEL);
		IUIWindow uiWindow = uiFactory.create("com.kingdee.eas.fdc.contract.client.ContractBillStoreUI", hm, null,null);
		uiWindow.show();
		actionRefresh_actionPerformed(e);	
	}
	//����ʱ�������ݻ���
	public void actionAudit_actionPerformed(ActionEvent e) throws Exception {
		isCanOperation();
//		��ȡ�û�ѡ��Ŀ�
		List idList =ContractClientUtils.getSelectedIdValues(getBillListTable(), getKeyFieldName());
		boolean hasMutex = false;
		try{
			
			FDCClientUtils.requestDataObjectLock(this, idList, "Audit");
			if(isRelatedTask()){
				for(int i = 0 ;i<idList.size();i++){
					String idKey = idList.get(i).toString();
					ContractBillInfo contract = ContractBillFactory.getRemoteInstance().getContractBillInfo(new ObjectUuidPK(idKey));
					ContractClientUtils.checkConRelatedTaskSubmit(contract);
				}
			}			
			
			super.actionAudit_actionPerformed(e);	
		}
		catch (Throwable e1) {
			this.handUIException(e1);
			hasMutex = FDCClientUtils.hasMutexed(e1);
		}
		finally
		{
			if (!hasMutex) {
				try {
					FDCClientUtils.releaseDataObjectLock(this, idList);
				} catch (Throwable e1) {
					this.handUIException(e1);
				}
			}	
		}
	}
	//��ͬ�ύ����ʱ�Ƿ������ƻ�������й��� 2010-08-09
	private boolean isRelatedTask(){
		boolean isRealtedTask = false;
		String cuID = SysContext.getSysContext().getCurrentCostUnit().getId().toString();
		try {
			isRealtedTask = FDCUtils.getDefaultFDCParamByKey(null, cuID, FDCConstants.FDC_PARAM_RELATEDTASK);
		} catch (EASBizException e) {
			handUIExceptionAndAbort(e);
		} catch (BOSException e) {
			handUIExceptionAndAbort(e);
		}
		return isRealtedTask;
	}
	
	public void actionUnAudit_actionPerformed(ActionEvent e) throws Exception {
		isCanOperation();
		
		/* modified by zhaoqin for R140109-0016 on 2014/01/15 start */
		//FDCClientHelper.checkAuditor(getSelectedIdValues(), "t_con_ContractBill");
		FDCClientHelper.checkAuditor(getSelectedIdValues(), auditMap);
		/* modified by zhaoqin for R140109-0016 on 2014/01/15 end */
		
		
		//R110603-0148:������ڱ��ǩ֤���룬������������
	    if (ContractUtil.hasChangeAuditBill(null, getSelectedIdValues())) {
    		FDCMsgBox.showWarning(this, EASResource.getString("com.kingdee.eas.fdc.contract.client.ContractResource", "hasChangeAuditBill"));
			this.abort();
    	}
    	
    	//R110603-0148:������ڱ��ǩ֤ȷ�ϣ�������������
    	if (ContractUtil.hasContractChangeBill(null, getSelectedIdValues())) {
    		FDCMsgBox.showWarning(this, EASResource.getString("com.kingdee.eas.fdc.contract.client.ContractResource", "hasContractChangeBill"));
			this.abort();
    	}
    	
		if(isRelatedTask()){
			checkConRelatedTaskDelUnAudit();
		}
		super.actionUnAudit_actionPerformed(e);
	}
	
	public void actionRemove_actionPerformed(ActionEvent e) throws Exception {
		isCanOperation();
		if(isRelatedTask()){
			checkConRelatedTaskDelUnAudit();
		}

		super.actionRemove_actionPerformed(e);
		// ά����ܺ�Լ�Ƿ������ֶ�
	}

	/**
	 * ���״̬�ĵ��ݲ���ɾ�� - add by zhaoqin for R130812-0007 on 2013/08/21
	 * @see com.kingdee.eas.fdc.contract.client.ContractListBaseUI#getBillStateForRemove()
	 */
	protected String[] getBillStateForRemove() {
		// return new String[] { FDCBillStateEnum.BACK_VALUE, FDCBillStateEnum.SAVED_VALUE, FDCBillStateEnum.SUBMITTED_VALUE };
		return new String[] { FDCBillStateEnum.SAVED_VALUE, FDCBillStateEnum.SUBMITTED_VALUE };
	}
	
	/**
	 * ����ͬ�Ƿ������ܺ�Լ
	 * 
	 * @return
	 * @throws Exception
	 */
	private Set checkReaPre() throws Exception {
		List selectedIdValues = ContractClientUtils.getSelectedIdValues(getBillListTable(), getKeyFieldName());
		ContractBillInfo contractBillInfo = null;
		FDCSQLBuilder builder = new FDCSQLBuilder();
		Set billId = new HashSet();
		SelectorItemCollection sic = new SelectorItemCollection();
		sic.add("*");
		sic.add("programmingContract.*");
		builder.clear();
		builder.appendSql("select fprogrammingContract from T_CON_CONTRACTBILL where 1=1 and ");
		builder.appendParam("fid", selectedIdValues.toArray());
		IRowSet rowSet = builder.executeQuery();
		while (rowSet.next()) {
			if (rowSet.getString("fprogrammingContract") != null) {
				billId.add(rowSet.getString("fprogrammingContract"));
			}
		}
		return billId;
	}

	/**
	 * �����Ͽ�ܺ�Լ�Ƿ�����
	 * 
	 * @throws Exception
	 */
	private void updateOldProg() throws Exception {
		Set checkReaPre = checkReaPre();
		if (checkReaPre != null && checkReaPre.size() != 0) {
			for (Iterator it = checkReaPre.iterator(); it.hasNext();) {
				String temp = it.next().toString();
				int count = 0;// ������Լ��
				int linkInviteProject = isCitingByInviteProject(temp);// ����Լ�����б�������
				int linkContractBill = isCitingByContractBill(temp);// ����Լ������ͬ��
				int linkContractWithoutText = isCitingByContractWithoutText(temp);// ����Լ�������ı���ͬ��������
				count = linkInviteProject + linkContractBill + linkContractWithoutText;
				boolean isCiting = preVersionProg(temp);
				if (count <= 1 && !isCiting) {
					updateProgrammingContract(temp, 0);
				}
			}
		}
	}

	/**
	 * �����¿�ܺ�Լ�Ƿ�����
	 * 
	 * @throws Exception
	 */
	private void updateNewProg() throws Exception {
		List selectedIdValues = ContractClientUtils.getSelectedIdValues(getBillListTable(), getKeyFieldName());
		ContractBillInfo contractBillInfo = null;
		SelectorItemCollection sic = new SelectorItemCollection();
		sic.add("*");
		sic.add("programmingContract.*");
		for (int i = 0; i < selectedIdValues.size(); i++) {
			contractBillInfo = ContractBillFactory.getRemoteInstance().getContractBillInfo(new ObjectUuidPK(selectedIdValues.get(i).toString()), sic);
			if (contractBillInfo.getProgrammingContract() != null) {
				updateProgrammingContract(contractBillInfo.getProgrammingContract().getId().toString(), 1);
			}
		}
	}
	
	private void checkConRelatedTaskDelUnAudit() throws BOSException, SQLException{
		List selectedIdValues = ContractClientUtils.getSelectedIdValues(
				getBillListTable(), getKeyFieldName());
		
		if(selectedIdValues!=null&&selectedIdValues.size()==0){
			return;
		}
		
		Set contractIds = FDCHelper.getSetByList(selectedIdValues);
		FDCSQLBuilder builder = new FDCSQLBuilder();
		builder.appendParam("select fid,count(fid) jishu from T_SCH_ContractAndTaskRel where FContractID",contractIds.toArray());
		builder.appendSql(" group by fid ");
		IRowSet rowSet = builder.executeQuery();
		Set ids = new HashSet();
		while(rowSet.next()){
			int count = rowSet.getInt("jishu");
			if(count >0){
				String id = rowSet.getString("fid");
				ids.add(id);
			}
		}
		boolean flag = false;
		if(ids.size()>0){
			builder.clear();
			builder.appendParam("select count(fid) jishu from T_SCH_ContractAndTaskRelEntry where FParentID  ",ids.toArray());
			IRowSet _rowSet = builder.executeQuery();
			while(_rowSet.next()){
				int _count = _rowSet.getInt("jishu");
				if(_count > 0){
					flag = true;
					break;
				}
			}
		}
		if(flag){
			FDCMsgBox.showInfo("��ͬ�ѱ��������������ִ�д˲�����");
			SysUtil.abort();
		}
	}
	
	//���鵵
    public void actionAntiStore_actionPerformed(ActionEvent e) throws Exception
    {
    	checkSelected();
    	
    	checkBillState(new String[]{getStateForUnAudit()}, "selectRightRowForUnAudit");
    	
    	List selectedIdValues = ContractClientUtils.getSelectedIdValues(getBillListTable(), getKeyFieldName());
    	
		IContractBill icb = ContractBillFactory.getRemoteInstance();
		boolean flag = false;
		flag = icb.contractBillAntiStore(selectedIdValues);
		if (flag) {				
			FDCClientUtils.showOprtOK(this);
			actionRefresh_actionPerformed(e);	
		}
    }

    /**
     * ֧�ֶ�λ���ܣ���λ�ֶ��У���ͬ��ţ���ͬ���ƣ���ͬ���ͣ�ǩԼʱ�䣬״̬��ǩԼ�ҷ�����ͬԭ�ҽ���λ�ҽ��
     * Modified by Owen_wen 2010-09-06
     */
	protected String[] getLocateNames()
    {
		return new String[] {IFWEntityStruct.dataBase_Number, IFWEntityStruct.dataBase_Name, "contractType.name", 
				"signDate", "state", "partB.name", "originalAmount", AMOUNT,};
    }
	/**
	 * output getEditUIName method
	 */
	protected String getEditUIName() {
		if(viewType==0){
			return com.kingdee.eas.fdc.contract.client.ContractBillEditUI.class.getName();
		}else{
			return getContractUIName() ;
		}
	}
	
	protected String getContractUIName() {
		return com.kingdee.eas.fdc.contract.client.ContractFullInfoUI.class.getName();
	}

	/**
	 * output getBizInterface method
	 */
	protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception {
		return com.kingdee.eas.fdc.contract.ContractBillFactory.getRemoteInstance();
	}

	/**
	 * output createNewData method
	 */
	protected com.kingdee.bos.dao.IObjectValue createNewData() {
		com.kingdee.eas.fdc.contract.ContractBillInfo objectValue = new com.kingdee.eas.fdc.contract.ContractBillInfo();
		objectValue.setCurProject((CurProjectInfo) getProjSelectedTreeNode().getUserObject());
		if (getTypeSelectedTreeNode() != null && getTypeSelectedTreeNode().isLeaf()) {
			objectValue.setContractType((ContractTypeInfo) getTypeSelectedTreeNode().getUserObject());
		}
		return objectValue;
	}

	protected ICoreBillBase getRemoteInterface() throws BOSException {
		
		return (ICoreBillBase) ContractBillFactory.getRemoteInstance();
	}

	protected void audit(List ids) throws Exception {
		FDCSQLBuilder builder = new FDCSQLBuilder();
	    builder.appendSql("select bill.fcontractpropert from t_con_contractbillentry entry ");
	    builder.appendSql("inner join t_con_contractbill bill on  bill.fid=entry.fparentid ");
	    builder.appendSql("inner join t_con_contractbill main on main.fid=entry.fcontent and main.fstate <> '4AUDITTED' ");
	    builder.appendSql("where ");
	    builder.appendParam("bill.fid", FDCHelper.list2Set(ids).toArray());
	    builder.appendSql(" and bill.fcontractpropert='SUPPLY' ");
	    builder.appendSql(" and entry.fdetail='��Ӧ����ͬ����' ");
	    IRowSet rs = builder.executeQuery();
	    if(rs!=null&&rs.size()==1){
	    	rs.next();
	    	String prop = rs.getString("fcontractpropert");
	    	if("SUPPLY".equals(prop)){
	    		FDCMsgBox.showWarning(this,"����ѡ��������д�������ͬδ�����Ĳ����ͬ�����ܽ��д˲���!");
		    	this.abort();
	    	}
	    }
	        
		ContractBillFactory.getRemoteInstance().audit(ids);
	}
	
	/**
	 * ���Ϊ�ǵ������㲹���ͬ��������������
	 * 
	 * @param billId
	 */
	private int unLongContract(List ids) {
		FDCSQLBuilder builder = new FDCSQLBuilder();
		builder.appendSql(" select count(1) from t_con_contractbillentry entry");
		builder.appendSql(" inner join t_con_contractbill con on con.fid = entry.fparentid   ");
		builder.appendSql(" inner join t_con_contractbillentry entry2 on entry2.fparentid = entry.fparentid ");
		builder.appendSql("  where entry.frowkey = 'am' and");
		builder.appendParam(" entry2.fcontent", FDCHelper.list2Set(ids).toArray());
		try {
			RowSet rowSet = builder.executeQuery();
			while (rowSet.next()) {
				return rowSet.getInt(1);
			}
		} catch (Exception e) {
			logger.error(e);
			handUIExceptionAndAbort(e);
		}
		return 0;
	}

	protected void unAudit(List ids) throws Exception {
		// ���Ϊ�ǵ������㲹���ͬ��������������
		int unLongContract = unLongContract(ids);
		if (unLongContract == 1) {
			FDCMsgBox.showWarning("��ͬ�ѹ��������ͬ���ܽ��д˲���");
			SysUtil.abort();
		}
		
		FDCSQLBuilder builder = new FDCSQLBuilder();
	    builder.appendSql("select * from t_con_contractbillentry entry ");
	    builder.appendSql("inner join t_con_contractbill bill on bill.fid=entry.fparentid ");
	    builder.appendSql("inner join t_con_contractbill main on  main.fid=entry.fcontent ");
	    builder.appendSql("where");
	    builder.appendParam("main.fid", FDCHelper.list2Set(ids).toArray());
	    if(isSupply){
	    	builder.appendSql(" and bill.fstate='4AUDITTED'");
	    }
	    IRowSet rs = builder.executeQuery();
	    if(rs!=null&&rs.size()>0){
			FDCMsgBox.showWarning(this, "����ѡ������ݴ��ڱ������ͬ���õĺ�ͬ�����ܽ��д˲���!");
			this.abort();
		}
	    
	    builder.clear();
	    builder.appendSql("select count(fid) from t_con_contractbill where ");
	    builder.appendParam("FMainContractID", FDCHelper.list2Set(ids).toArray());
	    rs = builder.executeQuery();
	    if(rs!= null && rs.size() > 0 && rs.next() && rs.getInt(1) > 0){
	    	FDCMsgBox.showWarning(this, "����ѡ������ݴ��ڱ�ս���Ӻ�ͬ���õĺ�ͬ�����ܽ��д˲���!");
			this.abort();
	    }
		ContractBillFactory.getRemoteInstance().unAudit(ids);
	}

	protected KDTable getBillListTable() {

		return getMainTable();
	}

	protected void initTable() {
		super.initTable();
		FDCHelper.formatTableNumber(getMainTable(), AMOUNT);
		getMainTable().getColumn("exRate").getStyleAttributes().setHided(true);
		getMainTable().getSelectManager().setSelectMode(KDTSelectManager.MULTIPLE_ROW_SELECT);
	}

	protected ArrayList getExportParam() {		
		DatataskParameter param = new DatataskParameter();
		param.solutionName = getSolutionName();
		param.alias = getDatataskAlias();
		param.datataskMode = DatataskMode.ExpMode;
		ArrayList paramList = new ArrayList();
		paramList.add(param);
		return paramList;		
	}
	
	public void actionExportData_actionPerformed(ActionEvent e) throws Exception
    {
		checkSelected();
		List idList = ContractClientUtils.getSelectedIdValues(
				getBillListTable(), getKeyFieldName());
		ArrayList para = getExportParam();
        if (para == null || para.size() <= 0) {
            throw new FrameWorkException(FrameWorkException.EXPORTDATAPARANULL);// "��������ȷ������������"�˴���Ҫ�޸ģ�����EASBizException�ж�����쳣��Ϣ
        }

        Object tmp = para.get(0);
        if (tmp instanceof DatataskParameter)
        {
            DatataskParameter dp = (DatataskParameter) tmp;
            IMetaDataPK contractExportQueryPK = new MetaDataPK("com.kingdee.eas.fdc.contract.app", "ContractBillForExportQuery");
            EntityViewInfo view = new EntityViewInfo();
            FilterInfo filter = new FilterInfo();
            filter.getFilterItems().add(new FilterItemInfo("id",FDCHelper.list2Set(idList),CompareType.INCLUDE));
            view.setFilter(filter);
            dp.putContextParam("mainQueryPK", contractExportQueryPK);
            dp.putContextParam("mainQuery", view);
        }

        DatataskCaller dc = new DatataskCaller();
        dc.setParentComponent(this);
        dc.invoke(para, DatataskMode.ExpMode);
    }

	/**
	 * 
	 * ������Ϊ��ǰ���ݵ��������༭���鿴׼��Context
	 * @author:liupd
	 * @see com.kingdee.eas.framework.client.CoreBillListUI#prepareUIContext(com.kingdee.eas.common.client.UIContext, java.awt.event.ActionEvent)
	 */
	protected void prepareUIContext(UIContext uiContext, ActionEvent e) {
		super.prepareUIContext(uiContext, e);
		ItemAction act = getActionFromActionEvent(e);
		if (act.equals(actionAddNew)) {

			/*
			 * �ѵ�ǰѡ�еĹ�����Ŀ�ͺ�ͬ���ʹ���EditUI
			 */
			BOSUuid projId = ((CurProjectInfo) getProjSelectedTreeNode().getUserObject()).getId();
			BOSUuid typeId = null;
			if (getTypeSelectedTreeNode() != null && getTypeSelectedTreeNode().isLeaf()) {
				if(getTypeSelectedTreeNode().getUserObject() instanceof ContractTypeInfo){
					typeId = ((ContractTypeInfo) getTypeSelectedTreeNode().getUserObject()).getId();
				}
			}
			uiContext.put("projectId", projId);
			uiContext.put("contractTypeId", typeId);
			
			if(projId!=null){
				try {
					SelectorItemCollection sic = new SelectorItemCollection();
					sic.add("isWholeAgeStage");
					CurProjectInfo curInfo = CurProjectFactory.getRemoteInstance().getCurProjectInfo(new ObjectUuidPK(projId),sic);
					if(curInfo.isIsWholeAgeStage()){
						FDCMsgBox.showWarning("�㵱ǰѡ�����ȫ����Ŀ����ע�⣬ лл��");
					}
				} catch (EASBizException e1) {
					e1.printStackTrace();
				} catch (BOSException e1) {
					e1.printStackTrace();
				}
			}
		}
		uiContext.put("prjIds", getUIContext().get("prjIds"));
		
		//���ñ�ʶ��ֻҪ���Ǵ�list����򿪵ĺ�ͬ������ɾ�������޸Ķ�����
		uiContext.put("isFromList", Boolean.TRUE);
	}

	protected boolean isHasBillTable() {
		return false;
	}

	/**
	 * 
	 * ����������Ƿ��й�������
	 * @author:liupd
	 * ����ʱ�䣺2006-8-26 <p>
	 */
	protected void checkRef(String id) throws Exception {
		ContractClientUtils.checkContractBillRef(this, id);
	}

	/**
	 * ���̸���
	 */
	public void actionProjectAttachment_actionPerformed(ActionEvent e) throws Exception {
		//�����������ڵ�����̸�����ʱ��ʹûѡ����ֵѡ���˹���Ҳ��Ҫ�����Ӹ��� fengYJ 2008-12-16
		//		checkSelected();
		super.actionProjectAttachment_actionPerformed(e);

		AttachmentClientManager acm = AttachmentManagerFactory.getClientManager();
		String contractId = this.getSelectedKeyValue();
		if(contractId!=null){
			ContractBillInfo contract = ContractBillFactory.getRemoteInstance().getContractBillInfo(new ObjectUuidPK(BOSUuid.read(contractId)));
			CurProjectInfo curProject = contract.getCurProject();
			if (curProject == null) {
				return;
			}
			acm.showAttachmentListUIByBoID(curProject.getId().toString(), this);
			
		//update by david_yang 2011.03.29
		}else if(getProjSelectedTreeNode().getUserObject() instanceof CurProjectInfo){
			CurProjectInfo curProject = (CurProjectInfo) getProjSelectedTreeNode().getUserObject();
			if (curProject == null) {
				return;
			}
			acm.showAttachmentListUIByBoID(curProject.getId().toString(), this);
		}else{
			MsgBox.showWarning("��ѡ�񹤳���Ŀ�������Ӹ�����");
		}
	}
	
    public void actionAttachment_actionPerformed(ActionEvent e) throws Exception
    {
    	isCanOperation();
		//    	super.actionAttachment_actionPerformed(e);
		checkSelected(tblMain);
		boolean isEdit = false;
		AttachmentClientManager acm = AttachmentManagerFactory.getClientManager();
		String boID = this.getSelectedKeyValue();
		if (boID == null) {
			return;
		}

		//////////////////////////////////////////////////////////////////////////
		// R130718-0394:ʱ�򲾽��棬�ڹ������У��������༭����
		//////////////////////////////////////////////////////////////////////////

		boolean flag = FdcWfUtil.isBillInWorkflow(boID);
		if (flag) {
			ICoreBase coreBase = getBizInterface();
			if (null == coreBase) {
				return;
			}

			CoreBaseInfo editData = coreBase.getValue(new ObjectUuidPK(boID));
			AttachmentUIContextInfo info = new AttachmentUIContextInfo();
			info.setBoID(boID);
			MultiApproveUtil.showAttachmentManager(info, this, editData, String.valueOf("1"), false);

			return;
		}

		//////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////

		if (getBillStatePropertyName() != null) {
    		int rowIdx=tblMain.getSelectManager().getActiveRowIndex();
    		ICell cell =tblMain.getCell(rowIdx, getBillStatePropertyName());
    		Object obj=cell.getValue();
    		isEdit=ContractClientUtils.canUploadAttaForAudited(obj, canUploadForAudited);
    	}
    	//add by david_yang PT043562 2011.03.29
    	 if(isEdit){
         	//��ͬ
 	        String orgId = SysContext.getSysContext().getCurrentOrgUnit().getId().toString();
 	        String userId = SysContext.getSysContext().getCurrentUserInfo().getId().toString();
 			if(this.getClass().getName().equals("com.kingdee.eas.fdc.contract.client.ContractBillListUI")){
 				String uiName = "com.kingdee.eas.fdc.contract.client.ContractBillEditUI";
 				boolean hasFunctionPermission = PermissionFactory.getRemoteInstance().hasFunctionPermission(
 	    				new ObjectUuidPK(userId),
 	    				new ObjectUuidPK(orgId),
 	    				new MetaDataPK(uiName),
 	    				new MetaDataPK("ActionAttamentCtrl") );
 				//���δ���ò�������Ȩ�޵��û����ܽ��и���ά��,����Ѿ������˲������Ƶ��˵��ڵ�ǰ�û�����Ȩ�޲��ܽ��� ά��
 	        	if(hasFunctionPermission){
 	        		boolean creatorCtrl=FDCUtils.getDefaultFDCParamByKey(null, null, FDCConstants.FDC_PARAM_CREATORATTACHMENT);
 	        		if(creatorCtrl){
 	        			//�Ƶ���Ҫ���ڵ�ǰ�û�����
 	        			FDCSQLBuilder builder=new FDCSQLBuilder();
 	        			builder.appendSql("select 1 from T_Con_ContractBill where fid=? and fcreatorId=?");
 	        			builder.addParam(boID);
 	        			builder.addParam(userId);
 	        			if(!builder.isExist()){
 	        				isEdit=false;
 	        			}
 	        		}
 	        	}else{
 	        		isEdit=false;
 	        	}
 	        }
    	}
    	acm.showAttachmentListUIByBoID(boID,this,isEdit);
    	this.refreshList();
    }

	/**
	 * �鿴����
	 */
	public void actionViewContent_actionPerformed(ActionEvent arg0) throws Exception {
		isCanOperation();	
			this.checkSelected();
			ContractBillInfo billInfo=ContractBillFactory.getRemoteInstance().getContractBillInfo(new ObjectUuidPK(BOSUuid.read(getSelectedKeyValue())));
			/*if(isUseWriteMark&&billInfo!=null&&billInfo instanceof FDCBillInfo){//����ֵΪ�ǣ����һ���Ҫ��FDCBill.
				//��ʱ������Ĳ���״̬Ĭ�ϵ�ΪVIEW,������Ҫ�ܹ�ʹ������ʱ��������(���赥��״̬����)�ܹ����ӡ��޸ġ�ɾ�����ľͲ���ֱ�Ӵ���һ�� getOprtState()״̬ 
				ForWriteMarkHelper.isUseWriteMarkForListUI(billInfo,OprtState.EDIT,this);
			}else{
				ContractClientUtils.viewContent(this, this.getSelectedKeyValue());
			}*/
			
			if(billInfo!=null&&billInfo instanceof FDCBillInfo){//����ֵΪ�ǣ����һ���Ҫ��FDCBill.
				//��ʱ������Ĳ���״̬Ĭ�ϵ�ΪVIEW,������Ҫ�ܹ�ʹ������ʱ��������(���赥��״̬����)�ܹ����ӡ��޸ġ�ɾ�����ľͲ���ֱ�Ӵ���һ�� getOprtState()״̬ 
				ForWriteMarkHelper.isUseWriteMarkForListUI(billInfo,OprtState.EDIT,this);
			}
		}
	
	/**
	 * ��ִͬ����Ϣ
	 */
    public void actionViewContract_actionPerformed(ActionEvent e) throws Exception
    {
    	viewType=-1;
    	try {
			actionView_actionPerformed(e);
		} catch (Exception e1) {
			handUIExceptionAndAbort(e1);
		}finally{
			viewType=0;
		}    	
    }
    //�������������Ӻ�ͬ����  by cassiel_peng
    private boolean isAddContentAfterAudited() {
		boolean returnVal=false;
		try {
			returnVal=FDCUtils.getDefaultFDCParamByKey(null, null,FDCConstants.FDC_PARAM_ADDCONTENTAUDITED);
		} catch (Exception e) {
			handUIExceptionAndAbort(e);
		}
		return returnVal;
	}
	/**
	 * ��������
	 */
	public void actionAddContent_actionPerformed(ActionEvent e) throws Exception {
		this.checkSelected();
		String selectedKeyValue = this.getSelectedKeyValue();
		/*ContractBillInfo contract = new ContractBillInfo();
		String selectedKeyValue = this.getSelectedKeyValue();
		contract.setId(BOSUuid.read(selectedKeyValue));
		FilterInfo filter = new FilterInfo();
		filter.appendFilterItem("id", selectedKeyValue);
		filter.appendFilterItem("state", FDCBillStateEnum.CANCEL_VALUE);
		if(ContractBillFactory.getRemoteInstance().exists(filter)) {
			MsgBox.showWarning(this, "��ͬ����ֹ�������������ģ�");
			SysUtil.abort();
		}*/
		//�������ĺ�ͬ������������   by cassiel_peng 2009-11-06
		SelectorItemCollection selector=new SelectorItemCollection();
		selector.add("id");
		selector.add("state");
		ContractBillInfo contractBill=ContractBillFactory.getRemoteInstance().getContractBillInfo(new ObjectUuidPK(BOSUuid.read(selectedKeyValue)),selector);
		if(contractBill!=null&&contractBill.getState()!=null&&FDCBillStateEnum.CANCEL.equals(contractBill.getState())){
			MsgBox.showWarning(this, "��ͬ����ֹ�������������ģ�");
			SysUtil.abort();
		}
		if(!isAddContentAfterAudited()){
			if(contractBill!=null&&contractBill.getState()!=null&&FDCBillStateEnum.AUDITTED.equals(contractBill.getState())){
				MsgBox.showWarning(this, "��ͬ�������������������ģ�");
				SysUtil.abort();
			}
		}
		
		ContractContentCollection coll = ContractContentFactory.getRemoteInstance().getContractContentCollection("select id,filetype where contract='" + contractBill.getId().toString() + "'");
		File file = this.chooseFileByDialog();
		if (file == null) {
			return;
		}
		
		String fullname = file.getName();
		String extName = StringUtil4File.getExtendedFileName(fullname);
		for(int i=0;i<coll.size();i++){
			if(fullname.equals(((ContractContentInfo)coll.get(i)).getFileType())){
				MsgBox.showWarning(fullname+" �Ѿ����ڣ����������ӣ�");
				return;
			}
		}
		if(!file.canRead()) {
			MsgBox.showWarning(this, ContractClientUtils.getRes("readFileError"));
			SysUtil.abort();
		}
		byte[] content = null;
		try {
			content = FileGetter.getBytesFromFile(file);
		} catch (IOException ex) {
			MsgBox.showWarning(this, ContractClientUtils.getRes("readFileError"));
			SysUtil.abort();
		}
		
		ContractContentInfo contentInfo = new ContractContentInfo();
		contentInfo.setVersion(new BigDecimal("1.0"));
		contentInfo.setContract(contractBill);
		contentInfo.setFileType(fullname);
		contentInfo.setContentFile(content);
		ContractContentFactory.getRemoteInstance().addnew(contentInfo);
		super.actionAddContent_actionPerformed(e);
	}
	
	public void actionProgram_actionPerformed(ActionEvent e) throws Exception {
		checkSelected();
		String selectedKeyValue = getSelectedKeyValue();
		if (FDCUtils.isRunningWorkflow(selectedKeyValue)) {
			FDCMsgBox.showInfo("���ڹ����������У����Ժ�����!");
			SysUtil.abort();
		}
		SelectorItemCollection sic = new SelectorItemCollection();
		sic.add("*");
		sic.add("programmingContract.*");
		ContractBillInfo contractBillInfo = ContractBillFactory.getRemoteInstance().getContractBillInfo(new ObjectUuidPK(selectedKeyValue), sic);
		ProgrammingContractInfo pc = (ProgrammingContractInfo) contractBillInfo.getProgrammingContract();
		if (pc != null) {
			FDCSQLBuilder builder = new FDCSQLBuilder();
			builder.appendSql("select con.fid from t_con_contractbill con ");
			builder.appendSql(" inner join T_INV_AcceptanceLetter accep on accep.fid = con.fsourcebillid");
			builder.appendSql(" inner join t_inv_inviteProject invite on invite.fid = accep.FInviteProjectID where ");
			builder.appendSql("  con.fprogrammingcontract = invite.fprogrammingcontractid and ");
			builder.appendParam("con.fid", contractBillInfo.getId().toString());
			IRowSet rowSet = builder.executeQuery();
			if (rowSet.next()) {
				FDCMsgBox.showInfo("��ܺ�Լ���б�֪ͨ����룬�����޸�");
				this.abort();
			}
		}
		IUIWindow uiWindow = null;
		UIContext uiContext = new UIContext(this);
		uiContext.put("contractBillInfo", contractBillInfo);
		uiWindow = UIFactory.createUIFactory(UIFactoryName.MODEL).create(ContractBillLinkProgContEditUI.class.getName(), uiContext, null,
				OprtState.EDIT);
		uiWindow.show();
		if (contractBillInfo.getProgrammingContract() != null) {

			Object object = uiWindow.getUIObject().getUIContext().get("cancel");
			if (object != null) {
				//
			} else {
				// ======================��Լ��ܿ��ƺ�ͬǩ���߼�����
				String param = null;
				SelectorItemCollection sict = new SelectorItemCollection();
				sict.add("*");
				sict.add("fullOrgUnit.*");
				// BOSUuid idcu = contractBillInfo.getCurProject().getId();
				// CurProjectInfo curProjectInfo =
				// CurProjectFactory.getRemoteInstance().getCurProjectInfo(new
				// ObjectUuidPK(idcu), sict);
				ObjectUuidPK pk = new ObjectUuidPK(contractBillInfo.getOrgUnit().getId());
				// if
				// (SysContext.getSysContext().getCurrentCostUnit().isIsBizUnit
				// ()) {
				// ObjectUuidPK pk = new ObjectUuidPK(SysContext.getSysContext()
				// .getCurrentCostUnit().getId());
				param = ContextHelperFactory.getRemoteInstance().getStringParam("FDC228_ISSTRICTCONTROL", pk);
				// }else{
				// ObjectUuidPK pk = new ObjectUuidPK(SysContext.getSysContext()
				// .getCurrentCostUnit().getId());
				// param = ContextHelperFactory.getRemoteInstance()
				// .getStringParam("FDC228_ISSTRICTCONTROL", pk);
				// }
				if (!com.kingdee.util.StringUtils.isEmpty(param)) {
					if (!contractBillInfo.getState().equals(FDCBillStateEnum.SAVED)) {
						int i = Integer.parseInt(param);
						switch (i) {
						case 0:
							// �ϸ����ʱ
							if (contractBillInfo.getProgrammingContract() != null) {
								if (contractBillInfo.getAmount()
										.compareTo(
												FDCHelper.toBigDecimal(FDCHelper.toBigDecimal(contractBillInfo.getProgrammingContract()
														.getControlBalance()))) > 0) {
									FDCMsgBox.showWarning(this, "��ͬǩԼ���������ĺ�Լ�������������ύ");
									SysUtil.abort();
								}
							} else {
								FDCMsgBox.showWarning(this, "δ������ܺ�Լ���������ύ");
								SysUtil.abort();
							}
							break;
						case 1:
							// ��ʾ����ʱ
							if (contractBillInfo.getProgrammingContract() != null) {
								if (contractBillInfo.getAmount().compareTo(
										FDCHelper.toBigDecimal(contractBillInfo.getProgrammingContract().getControlBalance())) > 0) {
									if (FDCMsgBox.showConfirm2(this, "��ͬǩԼ���������ĺ�Լ��������ȷ���Ƿ��ύ") == FDCMsgBox.CANCEL) {
										SysUtil.abort();
									}
								}
							} else {
								FDCMsgBox.showWarning(this, "δ������ܺ�Լ���������ύ");
								SysUtil.abort();
							}
							break;
						case 2:
							// ������ʱ
							if (contractBillInfo.getProgrammingContract() != null) {
								if (contractBillInfo.getAmount().compareTo(
										FDCHelper.toBigDecimal(contractBillInfo.getProgrammingContract().getControlBalance())) > 0) {
									if (FDCMsgBox.showConfirm2(this, "��ͬǩԼ���������ĺ�Լ��������ȷ���Ƿ��ύ") == FDCMsgBox.CANCEL) {
										SysUtil.abort();
									}
								}
							}
							break;
						}
						// ����old�Ŀ�ܺ�Լ
						updateOldProg();
						// ά��ԴID���ڶ�̬�滮
						if (contractBillInfo.getProgrammingContract() != null) {
							contractBillInfo.setSrcProID(contractBillInfo.getProgrammingContract().getId().toString());
						}
						SelectorItemCollection sicz = new SelectorItemCollection();
						sicz.add("programmingContract");
						sicz.add("srcProID");
						ContractBillFactory.getRemoteInstance().updatePartial(contractBillInfo, sicz);
						// ����new�Ŀ�ܺ�Լ
						updateNewProg();
						FDCMsgBox.showInfo("������ܺ�Լ�ɹ�");
					} else {

						// ����old�Ŀ�ܺ�Լ
						updateOldProg();
						// ά��ԴID���ڶ�̬�滮
						if (contractBillInfo.getProgrammingContract() != null) {
							contractBillInfo.setSrcProID(contractBillInfo.getProgrammingContract().getId().toString());
						}
						ContractBillFactory.getRemoteInstance().save(contractBillInfo);
						// ����new�Ŀ�ܺ�Լ
						updateNewProg();
						FDCMsgBox.showInfo("������ܺ�Լ�ɹ�");
					}
				}
				}
			}
		}
	
	private File chooseFileByDialog() {
		File retFile = null;
		int retVal;
		KDFileChooser fc = new KDFileChooser(System.getProperty("user.home"));
		fc.setFileSelectionMode(KDFileChooser.FILES_ONLY);
		fc.setMultiSelectionEnabled(false);

		retVal = fc.showOpenDialog(this);
		if (retVal == KDFileChooser.CANCEL_OPTION)
			return retFile;
		retFile = fc.getSelectedFile();
		if (!retFile.exists()) {
			MsgBox.showInfo(Resrcs.getString("FileNotExisted"));
			return null;
		}
		if (retFile.length() > StringUtil4File.FILE_BYTES_LIMIT_SINGLE) {
			MsgBox.showInfo(Resrcs.getString("FileSizeNotAllowed"));
			return null;
		}
		return retFile;
	}

	protected void initWorkButton() {
		super.initWorkButton();
		/**
		 * ���ء����롱�͡��������˵��һ�������﹦�������⣬��һ��������Ǩ�ƹ�������ʵ��
		 * Added by Owen_wen 2012-3-1
		 */
		this.btnAddContent.setIcon(EASResource.getIcon("imgTbtn_upenumnew"));
		this.btnViewContent.setIcon(EASResource.getIcon("imgTbtn_seeperformance"));
		this.btnProjectAttachment.setIcon(EASResource.getIcon("imgTbtn_createcredence"));
		this.menuItemAddContent.setIcon(EASResource.getIcon("imgTbtn_upenumnew"));
		this.menuItemViewContent.setIcon(EASResource.getIcon("imgTbtn_seeperformance"));
		this.menuItemProjectAttachment.setIcon(EASResource.getIcon("imgTbtn_createcredence"));
		this.btnConMove.setIcon(FDCClientHelper.ICON_MOVE);
		this.menuItemConMove.setIcon(FDCClientHelper.ICON_MOVE);
		
		this.menuItemViewContract.setIcon(EASResource.getIcon("imgTbtn_execute"));
		this.btnViewContract.setIcon(EASResource.getIcon("imgTbtn_execute"));
		
		actionRespite.setVisible(true);
		actionRespite.setEnabled(true);
		actionCancelRespite.setVisible(true);
		actionCancelRespite.setEnabled(true);
	}

	protected FilterInfo getTreeSelectChangeFilter() {
		FilterInfo filter = new FilterInfo();
		FilterItemCollection filterItems = filter.getFilterItems();
		filterItems.add(new FilterItemInfo("curProject.isEnabled", Boolean.TRUE));
		return filter;
	}
	
	/**
	 * 
	 * ������������ͬǰ�ȼ���ͬ�����Ƿ񱻽��ã����ѽ��ã��������ʾ���ú�ͬ�����²�����������ͬ��
	 * @Author��owen_wen
	 * @CreateTime��2013-2-19
	 */
	private void checkContractTypeIsEnableBeforeAddNew() {
		DefaultKingdeeTreeNode typeNode = getTypeSelectedTreeNode();
		if (typeNode.getUserObject() instanceof ContractTypeInfo) {
			ContractTypeInfo contractTypeInfo = (ContractTypeInfo) typeNode.getUserObject();
			if (!contractTypeInfo.isIsEnabled()) { // δ����
				FDCMsgBox.showInfo(this, "ѡ�еĺ�ͬ����δ���ã���ѡ��������ͬ���͡�");
				this.abort();
			}
		}
	}

	public void actionAddNew_actionPerformed(ActionEvent e) throws Exception {
		
		//�жϵ�ǰ��û�д������Ĵ���
		IUIWindow win = FDCClientUtils.findUIWindow(this.getEditUIName(), this.getUIContext(), dataObjects, OprtState.ADDNEW);
		if (null != win) {
			//��ʾ����
			win.show();
			//��ʾ����ͼ�رգ���������Ĵ������������ݾͻ���ʾ�û������û�о�ֱ�ӹص�����һ���µ�
			win.close();
		}
		    
		FDCClientUtils.checkSelectProj(this, getProjSelectedTreeNode());
		FDCClientUtils.checkProjWithCostOrg(this, getProjSelectedTreeNode());
		checkContractTypeIsEnableBeforeAddNew();
		super.actionAddNew_actionPerformed(e);
	}

	
	/**
	 * ����
	 * @param ids
	 * @throws Exception
	 */
	protected void cancel(List ids) throws Exception {
		IObjectPK[] pkArray = FDCHelper.idListToPKArray(ids);
		ContractBillFactory.getRemoteInstance().cancel(pkArray);
	}

	/**
	 * ��Ч
	 * @param ids
	 * @throws Exception
	 */
	protected void cancelCancel(List ids) throws Exception {
		IObjectPK[] pkArray = FDCHelper.idListToPKArray(ids);

		ContractBillFactory.getRemoteInstance().cancelCancel(pkArray);
	}
	
	public void actionConMove_actionPerformed(ActionEvent e) throws Exception {
		super.actionConMove_actionPerformed(e);
		
		checkSelected();
		checkBillState(new String[]{FDCBillStateEnum.AUDITTED_VALUE}, "selectRightRowForConMove");
		
		if(ContractMoveEditUI.showMe(getSelectedKeyValue(), this)) {
			refreshList();
		}
		
	}
	
    /**
     * 
     * �������޸�ǰ���
     * @author:liupd
     * ����ʱ�䣺2006-8-26 <p>
     * @throws Exception 
     */
    protected void checkBeforeEdit() throws Exception {
	    checkSelected();
		
		//CoreBillBaseInfo billInfo = getRemoteInterface().getCoreBillBaseInfo(new ObjectUuidPK(getSelectedKeyValue(getBillListTable())));
		String selectedKeyValue = getSelectedKeyValue();
		SelectorItemCollection selector = new SelectorItemCollection();
		selector.add(getBillStatePropertyName());
		selector.add("splitState");
		ContractBillInfo contractBillInfo = ContractBillFactory.getRemoteInstance().getContractBillInfo(new ObjectStringPK(selectedKeyValue), selector);		
	    
	    String billState = contractBillInfo.getString(getBillStatePropertyName());
		String[] states = getBillStateForEditOrRemove();
		boolean pass = false;
		for (int i = 0; i < states.length; i++) {
			if(billState.equals(states[i])) {
				pass = true;
			}
		}
		if(!pass) {
			MsgBox.showWarning(this, ContractClientUtils.getRes("cantEdit"));
			SysUtil.abort();
		}
		
		//�ú�ͬ�Ѿ������˲�֣����ܽ����޸�
		if(contractBillInfo.getSplitState()!=null && !CostSplitStateEnum.NOSPLIT.equals(contractBillInfo.getSplitState())){
			MsgBox.showWarning(this, "�ú�ͬ�Ѿ������˲�֣����ܽ����޸�");
			SysUtil.abort();
		}
    }
    
	protected void checkBeforeCancel() throws Exception {
		super.checkBeforeCancel();
		
		String selectedKeyValue = getSelectedKeyValue();
		SelectorItemCollection selector = new SelectorItemCollection();
		selector.add("hasSettled");
		selector.add("state");
		ContractBillInfo contractBillInfo = ContractBillFactory.getRemoteInstance().getContractBillInfo(new ObjectStringPK(selectedKeyValue), selector);
		if(contractBillInfo.getState()==FDCBillStateEnum.SUBMITTED || contractBillInfo.getState()==FDCBillStateEnum.AUDITTING){
			//R101224-250�ύ״̬�Ŀ���ֱ����ֹ
		}else{
			//����״̬��ֻ�н����˲ſ�����ֹ
			if(contractBillInfo.getState()==FDCBillStateEnum.AUDITTED&&!contractBillInfo.isHasSettled()) {
				MsgBox.showWarning(this, ContractClientUtils.getRes("hasNotSettled"));
				SysUtil.abort();
			}
		}
		
	}
	

	//���ӣ����Զ��ύ�������еĺ�ͬ������ֹ��״̬
	protected String[] getBillStateEnum() {
    	return new String[]{FDCBillStateEnum.AUDITTED_VALUE,FDCBillStateEnum.SUBMITTED_VALUE,FDCBillStateEnum.AUDITTING_VALUE};
	}
	

	// ������Լ�鿴����
	public void actionHeYue_actionPerformed(ActionEvent e) throws Exception {
		BOSUIContext uiCtx = new BOSUIContext();

		IUIWindow uiWindow = null;
		uiCtx.put(UIContext.ID, getSelectedKeyValue());
		// com.kingdee.eas.fdc.contract.client.HeYueDanEditUI
		uiWindow = UIFactory.createUIFactory(UIFactoryName.MODEL).create(HeYueDanEditUI.class.getName(), uiCtx, null, OprtState.VIEW);
		uiWindow.show();
		super.actionHeYue_actionPerformed(e);
	}

	/**
	 * �Ƿ����CU����
	 * <p>
	 * ʼ�շ����ǣ���Ϊ�Ѿ����Զ��������getTreeSelectFilter
	 * 
	 * 
	 * @see com.kingdee.eas.fdc.contract.client.ContractListBaseUI#isIgnoreCUFilter()
	 */
	protected boolean isIgnoreCUFilter() {
		return true;
	}

	//���ò�ѯ��
	protected IQueryExecutor getQueryExecutor(IMetaDataPK queryPK, EntityViewInfo viewInfo) {
		IQueryExecutor queryExecutor = super.getQueryExecutor(queryPK, viewInfo);
		// modify by lihaiou,2014-09-12,��ӡSQL���������������⣬���Ҵ˴��ظ���ӡ������
//		queryExecutor.option().isAutoIgnoreZero = false;
//
//		String sql = null;
//		try {
//			sql = queryExecutor.getSQL();
//
//			logger.info("================================================================");
//			logger.info("logger.info��ContractBillListUI.getQueryExecutor().sql:" + sql);
//			logger.info("logger.info��ContractBillListUI.getQueryExecutor().viewInfo:" + viewInfo);
//			logger.info("================================================================");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		return queryExecutor;
	}

	protected IRow appendFootRow() {
		IRow footRow = super.appendFootRow();
		footRow.getCell(AMOUNT).getStyleAttributes().setNumberFormat(FDCHelper.strDataFormat);
		return footRow;
	}

	//�����Tableǰ��������
    public void onGetRowSet(IRowSet rowSet) {
    	super.onGetRowSet(rowSet);
		try {
			rowSet.beforeFirst();

			Set contractIds = new HashSet() ;
			while (rowSet.next()) {
				String id  = rowSet.getString("id");
				contractIds.add(id);					
			}
			Map retValue = ContractBillFactory.getRemoteInstance().getOtherInfo(contractIds);
	    	contentMap = (Map)retValue.get("contentMap");
	    	attachMap = (Map) retValue.get("attachMap");
	    	auditMap = (Map) retValue.get("auditMap");
		}catch(Exception e){
			handUIExceptionAndAbort(e);
		}finally{
			try {
				rowSet.beforeFirst();
			} catch (SQLException e) {
				handUIExceptionAndAbort(e);
			}
		}
/*    	��getOtherInfo�ڽ���ͳһ���� by sxhong 2009-05-07 15:24:37
    	contentMap = (Map) ActionCache.get("ContractBillListUIHandler.contentMap");
    	attachMap = (Map) ActionCache.get("ContractBillListUIHandler.attachMap");
    	auditMap = (Map) ActionCache.get("ContractBillListUIHandler.auditMap");
    	
    	if(contentMap==null || attachMap==null){
    		contentMap = new HashMap();
    		attachMap = new HashMap();
    		
			try {
				rowSet.beforeFirst();
	
				Set contractIds = new HashSet() ;
				while (rowSet.next()) {
					String id  = rowSet.getString("id");
					contractIds.add(id);					
				}
				
				auditMap = FDCBillWFFacadeFactory.getRemoteInstance().getWFBillLastAuditorAndTime(contractIds);
				
				if(contractIds.size()>0){
					*//**
					 * ���������,���б��л�ȡ��ͬ��ID�����е�ID���Ǻ�ͬ������ID��
					 * Ȼ��ͨ�����ù�����Ϣ��ʹ��Զ�̵��ã��鿴��ͬ���ĸ����������Ƿ��к�ͬ��ID.
					 * ����ú�ͬ�����ĸ����ͽ��б�ǣ����򲻱��
					 *//*
					EntityViewInfo viewContent = new EntityViewInfo();
					FilterInfo filterContent = new FilterInfo();
					viewContent.getSelector().clear();
					viewContent.getSelector().add("contract.id");
					filterContent.getFilterItems().add(new FilterItemInfo("contract.id", contractIds, CompareType.INCLUDE));
					viewContent.setFilter(filterContent);
					ContractContentCollection colContent = ContractContentFactory.getRemoteInstance().getContractContentCollection(viewContent);				
					for(int j = 0; j < colContent.size(); ++j){
						contentMap.put(colContent.get(j).getContract().getId().toString(),Boolean.TRUE);
					}						
					
					*//**
					 * ��丽����,���б��л�ȡ��ͬ��ID�����е�ID���Ǻ�ͬ������ID��
					 * Ȼ��ͨ�����ù�����Ϣ��ʹ��Զ�̵��ã��鿴��ͬ���ĸ����������Ƿ��к�ͬ��ID.
					 * ����ú�ͬ��ҵ����ظ����ͽ��б�ǣ����򲻱��
					 *//*
					EntityViewInfo viewAttachment = new EntityViewInfo();
					FilterInfo filterAttachment = new FilterInfo();
					viewContent.getSelector().clear();
					viewAttachment.getSelector().add("boID");
					filterAttachment.getFilterItems().add(new FilterItemInfo("boID", contractIds, CompareType.INCLUDE));
					viewAttachment.setFilter(filterAttachment);
					BoAttchAssoCollection colAttachment = BoAttchAssoFactory.getRemoteInstance().getBoAttchAssoCollection(viewAttachment);
						
					for(int j = 0; j < colAttachment.size(); ++j){
						attachMap.put(colAttachment.get(j).getBoID().toString(),Boolean.TRUE);
					}			
					//���������afterDataFill
				}
				rowSet.beforeFirst();
			} catch (Exception e) {
				handUIException(e);
			}
    	}*/
    	
    }
    
	protected void fetchInitData() throws Exception {
		
		super.fetchInitData();
		// �ɱ����ļ�����
		String orgId = SysContext.getSysContext().getCurrentOrgUnit().getId().toString();
		//modify by lihaiou, 2014-09-12,�޸���������
		//Map paramMap = FDCUtils.getDefaultFDCParam(null, orgId);
		IObjectPK comPK = new ObjectUuidPK(orgId);
		HashMap hmParamIn = new HashMap();
		//���Ų������ٴι鵵ʱ���º�ͬ���
        hmParamIn.put(FDCConstants.FDC_PARAM_UPDATECONTRACTNO, null);
        //����״̬�ĵ��ݿ����ϴ�����
        hmParamIn.put(FDCConstants.FDC_PARAM_UPLOADAUDITEDBILL,comPK);
        //���ز�ҵ��ϵͳ�����������ñʼ����۹���
        hmParamIn.put(FDCConstants.FDC_PARAM_WRITEMARK, null);
        
	    hmParamIn.put(FDCConstants.FDC_PARAM_SELECTSUPPLY, comPK);
	    //�� ��Ŀ��ֵĺ�ͬ�ڸ���Ŀ�µ���������������ۿ����
        hmParamIn.put(FDCConstants.FDC_PARAM_MULTIPROJECT, null);
        
        Map paramMap = FDCUtils.getParamHashMapBatch(null, hmParamIn);
		if(paramMap.get(FDCConstants.FDC_PARAM_UPDATECONTRACTNO)!=null){
			//�뼯��һ��ֵ
			isUpdateConNo = Boolean.valueOf(paramMap.get(FDCConstants.FDC_PARAM_UPDATECONTRACTNO).toString()).booleanValue();
		}
		if(paramMap.get(FDCConstants.FDC_PARAM_UPLOADAUDITEDBILL)!=null){
			canUploadForAudited = Boolean.valueOf(paramMap.get(FDCConstants.FDC_PARAM_UPLOADAUDITEDBILL).toString()).booleanValue();
		}
		if(paramMap.get(FDCConstants.FDC_PARAM_SELECTSUPPLY)!=null){
			isSupply = Boolean.valueOf(paramMap.get(FDCConstants.FDC_PARAM_SELECTSUPPLY).toString()).booleanValue();
		}
		if(paramMap.get(FDCConstants.FDC_PARAM_WRITEMARK)!=null){
			isUseWriteMark = Boolean.valueOf(paramMap.get(FDCConstants.FDC_PARAM_WRITEMARK).toString()).booleanValue();
		}
		if(paramMap.get(FDCConstants.FDC_PARAM_MULTIPROJECT)!=null){
			isMultiProject = Boolean.valueOf(paramMap.get(FDCConstants.FDC_PARAM_MULTIPROJECT).toString()).booleanValue();
		}
	}
	
	protected SelectorItemCollection genBillQuerySelector() {
		return null;
	}
	
	
	/**
	 * RPC���죬�κ�һ���¼�ֻ��һ��RPC
	 */
	
	public boolean isPrepareInit() {
    	return false;
    }
	
	public boolean isPrepareActionSubmit() {
    	return true;
    }
	
	public boolean isPrepareActionSave() {
    	return true;
    }
		
	public void actionImportData_actionPerformed(ActionEvent e) throws Exception{
		DatataskCaller task = new DatataskCaller();
        task.setParentComponent(this);
        if (getImportParam() != null) {
			task.invoke(getImportParam(), DatataskMode.ImpMode, true, true);
		}
		actionRefresh_actionPerformed(e);		
	}
	
	protected ArrayList getImportParam() {			
		DatataskParameter param = new DatataskParameter();
		Hashtable hs = new Hashtable();
		param.setContextParam(hs);
        param.solutionName = getSolutionName();      
        param.alias = getDatataskAlias();
        ArrayList paramList = new ArrayList();
        paramList.add(param);
        return paramList;
    }
	protected String getSolutionName(){
		return "eas.fdc.contract.ContractBillImport";
    }
    
    protected String getDatataskAlias(){
    	return "��ͬ";
    } 

	/**
	 * �ҳ��б������������Ŀ�ܺ�Լ�ļ�¼��
	 * 
	 * @param proContId
	 * @return
	 */
	private int isCitingByInviteProject(String proContId) {
		FDCSQLBuilder buildSQL = new FDCSQLBuilder();
		buildSQL.appendSql("select count(*) count from T_INV_InviteProject ");
		buildSQL.appendSql("where FProgrammingContractId = '" + proContId + "' ");
		int count = 0;
		try {
			IRowSet iRowSet = buildSQL.executeQuery();
			if (iRowSet.next()) {
				count = iRowSet.getInt("count");
			}
		} catch (BOSException e) {
			handUIExceptionAndAbort(e);
		} catch (SQLException e) {
			handUIExceptionAndAbort(e);
		}
		return count;
	}

	/**
	 * �ҳ���ͬ�������Ŀ�ܺ�Լ�ļ�¼��
	 * 
	 * @param proContId
	 * @return
	 */
	private int isCitingByContractBill(String proContId) {
		FDCSQLBuilder buildSQL = new FDCSQLBuilder();
		buildSQL.appendSql("select count(*) count from T_CON_ContractBill ");
		buildSQL.appendSql("where FProgrammingContract = '" + proContId + "' ");
		int count = 0;
		try {
			IRowSet iRowSet = buildSQL.executeQuery();
			if (iRowSet.next()) {
				count = iRowSet.getInt("count");
			}
		} catch (BOSException e) {
			handUIExceptionAndAbort(e);
		} catch (SQLException e) {
			handUIExceptionAndAbort(e);
		}
		return count;
	}

	/**
	 * �ҳ����ı���ͬ�������Ŀ�ܺ�Լ�ļ�¼��
	 * 
	 * @param proContId
	 * @return
	 */
	private int isCitingByContractWithoutText(String proContId) {
		FDCSQLBuilder buildSQL = new FDCSQLBuilder();
		buildSQL.appendSql("select count(*) count from T_CON_ContractWithoutText ");
		buildSQL.appendSql("where FProgrammingContract = '" + proContId + "' ");
		int count = 0;
		try {
			IRowSet iRowSet = buildSQL.executeQuery();
			if (iRowSet.next()) {
				count = iRowSet.getInt("count");
			}
		} catch (BOSException e) {
			handUIExceptionAndAbort(e);
		} catch (SQLException e) {
			handUIExceptionAndAbort(e);
		}
		return count;
	}

	/**
	 * ���¹滮��Լ"�Ƿ�����"�ֶ�
	 * 
	 * @param proContId
	 * @param isCiting
	 */
	private void updateProgrammingContract(String proContId, int isCiting) {
		FDCSQLBuilder buildSQL = new FDCSQLBuilder();
		buildSQL.appendSql("update T_CON_ProgrammingContract set FIsCiting = " + isCiting + " ");
		buildSQL.appendSql("where FID = '" + proContId + "' ");
		try {
			buildSQL.executeUpdate();
		} catch (BOSException e) {
			handUIExceptionAndAbort(e);
		}
	}

	private boolean preVersionProg(String progId) throws BOSException, SQLException {
		boolean isCityingProg = false;
		int tempIsCiting = 0;
		FDCSQLBuilder buildSQL = new FDCSQLBuilder();
		buildSQL.appendSql(" select t1.FIsCiting isCiting from t_con_programmingContract t1 where t1.fid = (");
		buildSQL.appendSql(" select t2.FSrcId from t_con_programmingContract t2 where t2.fid = '" + progId + "')");
		IRowSet rowSet = buildSQL.executeQuery();
		while (rowSet.next()) {
			tempIsCiting = rowSet.getInt("isCiting");
		}
		if (tempIsCiting > 0) {
			isCityingProg = true;
		}
		return isCityingProg;
	}
	
	protected FilterInfo getTreeSelectFilter(Object projectNode, Object typeNode, boolean containConWithoutTxt) throws Exception {
		FilterInfo filter = getTreeSelectChangeFilter();
		Set prjIds = null;
		/*
		 * ������Ŀ��
		 */
		if (projectNode != null && projectNode instanceof CoreBaseInfo) {

			CoreBaseInfo projTreeNodeInfo = (CoreBaseInfo) projectNode;
			boolean isCompanyUint = false;
			FullOrgUnitInfo selectedOrg = new FullOrgUnitInfo();
			BOSUuid id = null;
			// ѡ����ǳɱ����ģ�ȡ�óɱ����ļ��¼��ɱ����ģ�����У��µ����к�ͬ
			if (projTreeNodeInfo instanceof OrgStructureInfo || projTreeNodeInfo instanceof FullOrgUnitInfo) {

				if (projTreeNodeInfo instanceof OrgStructureInfo) {
					id = ((OrgStructureInfo) projTreeNodeInfo).getUnit().getId();
					selectedOrg = ((OrgStructureInfo) projTreeNodeInfo).getUnit();
					isCompanyUint = selectedOrg.isIsCompanyOrgUnit();
				} else {
					selectedOrg = (FullOrgUnitInfo) projTreeNodeInfo;
					id = ((FullOrgUnitInfo) projTreeNodeInfo).getId();
					isCompanyUint = selectedOrg.isIsCompanyOrgUnit();
				}

				// ������Ŀ�ɱ����Ĺ�����
				FilterInfo filterInfo = dealWithProjectCostCenterFilter(id, authorizedOrgs);
				
				FilterInfo f2 = new FilterInfo();
				//TODO sql���Ż�
				
				if (isMultiProject&& (!isCompanyUint)) {
					prjIds = FDCClientUtils.getProjIdsOfCostOrg(selectedOrg, true);
					//Set idSet = FDCClientUtils.getSQLIdSet(FDCClientUtils.getSQLIdSet(prjIds));
					Set idSet = FDCClientUtils.getSQLIdSet(prjIds);
					if(idSet==null || idSet.size()==0) {//added by ken_liu ����Ϊ��ʱsql����
						idSet.add("null");
					}
					
					String filterSplitSql = "select distinct(fcontractbillid) from T_con_contractCostSplit head " + " inner join T_Con_contractCostSplitEntry entry on head.fid=entry.fparentid "
							+ " inner join T_FDC_CostAccount acct on acct.fid=entry.fcostaccountid where acct.fcurProject in " + " (" + idSet
							+ ") and fstate<>'9INVALID'";
					String filternoCostSplitSql = "select (fcontractbillid) from T_CON_ConNoCostSplit head " + " inner join T_CON_ConNoCostSplitEntry entry on head.fid=entry.fparentid "
					+ "  where entry.FCURPROJECTID in " + " (" + idSet
					+ ") and fstate<>'9INVALID'";
					f2.getFilterItems().add(new FilterItemInfo("id", filterSplitSql, CompareType.INNER));
					f2.getFilterItems().add(new FilterItemInfo("id", filternoCostSplitSql, CompareType.INNER));
					f2.setMaskString("(#0 or #1 )");
					if (filter != null) {
						filter.mergeFilter(f2, "or");
					}
				}else if(isMultiProject && (isCompanyUint)){
					// ������֯���ϵ���֯ by hpw 2011.110.19
					Set comIds = FullOrgUnitHelper.getChildrenCompanyOrgUnitInfos(selectedOrg.getLongNumber());
					prjIds = ProjectHelper.getCompanysPrjs(comIds);
					Set idSet = FDCClientUtils.getSQLIdSet(prjIds);
					if(idSet==null || idSet.size()==0) {///added by ken_liu	����Ϊ��ʱsql����
						idSet.add("null");
					}
					
					String filterSplitSql = "select distinct(fcontractbillid) from T_con_contractCostSplit head " + " inner join T_Con_contractCostSplitEntry entry on head.fid=entry.fparentid "
							+ " inner join T_FDC_CostAccount acct on acct.fid=entry.fcostaccountid where acct.fcurProject in " + " (" + idSet
							+ ") and fstate<>'9INVALID'";
					String filternoCostSplitSql = "select fcontractbillid from T_CON_ConNoCostSplit head " + " inner join T_CON_ConNoCostSplitEntry entry on head.fid=entry.fparentid "
					+ "  where entry.FCURPROJECTID in " + " (" + idSet
					+ ") and fstate<>'9INVALID'";
					f2.getFilterItems().add(new FilterItemInfo("id", filterSplitSql, CompareType.INNER));
					f2.getFilterItems().add(new FilterItemInfo("id", filternoCostSplitSql, CompareType.INNER));
					f2.setMaskString("(#0 or #1 )");
					if (filter != null) {
						filter.mergeFilter(f2, "and");
					}
					
				}
				if (filter != null) {
					filter.mergeFilter(filterInfo, "and");
				}
				
			}
			// ѡ�������Ŀ��ȡ����Ŀ���¼���Ŀ������У��µ����к�ͬ
			else if (projTreeNodeInfo instanceof CurProjectInfo) {
				id = projTreeNodeInfo.getId();
				prjIds = FDCClientUtils.genProjectIdSet(id);
				FilterInfo f = new FilterInfo();
				f.getFilterItems().add(new FilterItemInfo("curProject.id", prjIds, CompareType.INCLUDE));
				if (FDCUtils.getDefaultFDCParamByKey(null, null, FDCConstants.FDC_PARAM_MULTIPROJECT)) {
					Set sqlSet = FDCClientUtils.getSQLIdSet(prjIds);
					String filterSplitSql = "select distinct(fcontractbillid) from T_con_contractCostSplit head " + " inner join T_Con_contractCostSplitEntry entry on head.fid=entry.fparentid "
							+ " inner join T_FDC_CostAccount acct on acct.fid=entry.fcostaccountid where acct.fcurProject in " + " (" + sqlSet + ") and fstate<>'9INVALID'";
					String filterNoCostSplitSql = "select distinct(fcontractbillid) from T_CON_ConNoCostSplit head " + " inner join T_CON_ConNoCostSplitEntry entry on head.fid=entry.fparentid "
					+ "  where entry.FCURPROJECTID in " + " (" + sqlSet + ") and fstate<>'9INVALID'";
					f.getFilterItems().add(new FilterItemInfo("id", filterSplitSql, CompareType.INNER));
					f.getFilterItems().add(new FilterItemInfo("id", filterNoCostSplitSql, CompareType.INNER));
					f.setMaskString("(#0 or #1 or #2)");

				}
				if (filter != null) {
					filter.mergeFilter(f, "and");
				}
			}
		}
		//���ڴ�������Ŀ
		getUIContext().put("prjIds", prjIds);
		
		FilterInfo typefilter = new FilterInfo();
		FilterItemCollection typefilterItems = typefilter.getFilterItems();
		/*
		 * ��ͬ������
		 */
		if (typeNode != null && typeNode instanceof TreeBaseInfo) {
			TreeBaseInfo typeTreeNodeInfo = (TreeBaseInfo) typeNode;
			BOSUuid id = typeTreeNodeInfo.getId();
			Set idSet = FDCClientUtils.genContractTypeIdSet(id);
			typefilterItems.add(new FilterItemInfo("contractType.id", idSet, CompareType.INCLUDE));
		} else if (containConWithoutTxt && typeNode != null && typeNode.equals("allContract")) {
			// ����������ı���ͬ����ѯ����ʱ�������鲻����ͬ
			typefilterItems.add(new FilterItemInfo("contractType.id", "allContract"));
		}

		if (filter != null && typefilter != null) {
			filter.mergeFilter(typefilter, "and");
		}

		setIsAmtWithoutCostFilter(typefilter);

		return filter;
	}

	protected void setIsAmtWithoutCostFilter(FilterInfo filter) throws BOSException {
		// ��ͬ����ʾ
		// super.setIsAmtWithoutCostFilter(filter);
	}
	
	public void actionEdit_actionPerformed(ActionEvent e) throws Exception {
		isCanOperation();
		super.actionEdit_actionPerformed(e);
	}
	/**
	 * �Կ���Ŀ��ֺ�ͬ�����������˵������޸ġ�ɾ�������������������������ġ�����������ť���ʱ����ʾ��������Ŀ��ֹ����ĺ�ͬ���������˲�����
	 */
	private void isCanOperation() {
		checkSelected();
		int rowIndex = tblMain.getSelectManager().getActiveRowIndex();
		DefaultKingdeeTreeNode projectNode = getProjSelectedTreeNode();
		Object projectInfo = null;
		if(projectNode!=null){
			projectInfo = projectNode.getUserObject();
		}
		CoreBaseInfo projTreeNodeInfo = (CoreBaseInfo) projectInfo;
		String id = null;
		if (projTreeNodeInfo instanceof CurProjectInfo && projectNode.isLeaf()) {
			id = projTreeNodeInfo.getId().toString();
			IRow row = tblMain.getRow(rowIndex);
			if(row==null){
				return;
			}
			String proId = row.getCell("curProject.id").getValue().toString();
			if(proId != null){
				if(!proId.equals(id)){
					FDCMsgBox.showWarning(this,"������Ŀ��ֹ����ĺ�ͬ���������˲�����");
					this.abort();
				}
			}
		}
	}
	
	public SelectorItemCollection getSelectors() {
		SelectorItemCollection sic = new SelectorItemCollection();
		sic.add(new SelectorItemInfo("id"));
		sic.add(new SelectorItemInfo("number"));
		sic.add(new SelectorItemInfo("amount"));
		sic.add(new SelectorItemInfo("signDate"));
		sic.add(new SelectorItemInfo("costProperty"));
		sic.add(new SelectorItemInfo("contractPropert"));
		sic.add(new SelectorItemInfo("landDeveloper.name"));
		sic.add(new SelectorItemInfo("contractType.name"));
		sic.add(new SelectorItemInfo("partB.name"));
		sic.add(new SelectorItemInfo("partC.name"));
		sic.add(new SelectorItemInfo("state"));
		sic.add(new SelectorItemInfo("contractSourceId.name"));
		sic.add(new SelectorItemInfo("name"));
		sic.add(new SelectorItemInfo("currency.name"));
		sic.add(new SelectorItemInfo("exRate"));
		sic.add(new SelectorItemInfo("curProject.id"));
		//sic.add(new SelectorItemInfo("curProject.name"));
		sic.add(new SelectorItemInfo("orgUnit.name"));
		sic.add(new SelectorItemInfo("isArchived"));
		sic.add(new SelectorItemInfo("curProject.displayName"));
		sic.add(new SelectorItemInfo("originalAmount"));
		//sic.add(new SelectorItemInfo("currency.id"));
		//sic.add(new SelectorItemInfo("currency.precision"));
		sic.add(new SelectorItemInfo("auditor.name"));
		sic.add(new SelectorItemInfo("auditTime"));
		sic.add(new SelectorItemInfo("mainContractNumber"));
		sic.add(new SelectorItemInfo("attachment"));
		sic.add(new SelectorItemInfo("content"));
		sic.add(new SelectorItemInfo("creator.name"));
		sic.add(new SelectorItemInfo("createTime"));
		sic.add(new SelectorItemInfo("isRespite"));
		sic.add(new SelectorItemInfo("respDept.name"));
		sic.add(new SelectorItemInfo("ceremonyb"));
		sic.add(new SelectorItemInfo("ceremonybb"));
		sic.add(new SelectorItemInfo("tabShowIsAmtWithoutCost"));
		return sic;
	}
	
	 /**
	* �Ƿ���ҪԶ�̻�ȡ��ʼ���ݣ�����������
	* @return
	* @throws Exception
	*/
	protected boolean isNeedfetchInitData() throws Exception {
		return false;
		
	}
	/**
	 * ContractBillListUIDecimal��0ֵ����null����������ҵ�񸲸ǣ�����true���ڲ�ѯ����ʱBigDecimal�ֶε�ֵΪ0ʱ����null
	 * 
	 * @return
	 * @author jie_zhao2
	 * @createDate 2015-4-3
	 */
	public boolean isAutoIgnoreZero() {
		return false;
	}
}