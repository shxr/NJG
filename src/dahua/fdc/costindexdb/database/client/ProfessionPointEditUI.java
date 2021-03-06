/**
 * output package name
 */
package com.kingdee.eas.fdc.costindexdb.database.client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.log4j.Logger;

import com.kingdee.bos.ctrl.extendcontrols.BizDataFormat;
import com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox;
import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.KDTDefaultCellEditor;
import com.kingdee.bos.ctrl.kdf.table.KDTSelectBlock;
import com.kingdee.bos.ctrl.kdf.table.KDTSelectManager;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.kdf.util.render.ObjectValueRender;
import com.kingdee.bos.ctrl.swing.KDWorkButton;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.eas.basedata.org.OrgConstants;
import com.kingdee.eas.common.client.OprtState;
import com.kingdee.eas.common.client.SysContext;
import com.kingdee.eas.fdc.basedata.CostAccountInfo;
import com.kingdee.eas.fdc.basedata.FDCHelper;
import com.kingdee.eas.fdc.basedata.client.FDCMsgBox;
import com.kingdee.eas.fdc.contract.programming.client.CostAccountPromptBox;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.eas.util.client.EASResource;
import com.kingdee.eas.util.client.MsgBox;

/**
 * output class name
 */
public class ProfessionPointEditUI extends AbstractProfessionPointEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(ProfessionPointEditUI.class);
    
    /**
     * output class constructor
     */
    public ProfessionPointEditUI() throws Exception
    {
        super();
    }
    
    public void onLoad() throws Exception {
    	super.onLoad();
    	actionAddNew.setVisible(false);
    	actionCopy.setVisible(false);
    	actionSubmit.setVisible(false);
    	actionCancel.setVisible(false);
    	actionCancelCancel.setVisible(false);
    	actionFirst.setVisible(false);
    	actionLast.setVisible(false);
    	actionPre.setVisible(false);
    	actionNext.setVisible(false);
    	btnSave.setIcon(btnSubmit.getIcon());
    	btnSave.setText(btnSubmit.getText());
    	btnSave.setToolTipText(btnSubmit.getToolTipText());
    	reBuildCostAccount();
    	String cuID = SysContext.getSysContext().getCurrentOrgUnit().getId().toString();
		if(!cuID.equals(OrgConstants.DEF_CU_ID)) {
			actionEdit.setEnabled(false);
			actionRemove.setEnabled(false);
			actionSave.setEnabled(false);
		}
    }
    
    public void onShow() throws Exception {
    	super.onShow();
    	kdtEntry.getColumn("costAccount").setRequired(true);
    	kdtEntry.getColumn("pointName").setRequired(true);
    	kdtEntry.getSelectManager().setSelectMode(KDTSelectManager.ROW_SELECT);
    	kDContainer1.getContentPane().remove(kdtEntry_detailPanel);
    	kDContainer1.getContentPane().add(kdtEntry, BorderLayout.CENTER);
    	KDWorkButton addLine = new KDWorkButton("新增行");
    	KDWorkButton copyLine = new KDWorkButton("复制行");
		KDWorkButton insetLine = new KDWorkButton("插入行");
		KDWorkButton removeLine = new KDWorkButton("删除行");
		addLine.setName("addLine");
		insetLine.setName("insetLine");
		copyLine.setName("copyLine");
		removeLine.setName("removeLine");
		addLine.setIcon(EASResource.getIcon("imgTbtn_addline"));
		copyLine.setIcon(EASResource.getIcon("imgTbtn_addline"));
		insetLine.setIcon(EASResource.getIcon("imgTbtn_insert"));
		removeLine.setIcon(EASResource.getIcon("imgTbtn_deleteline"));
		kDContainer1.addButton(addLine);
		kDContainer1.addButton(copyLine);
		kDContainer1.addButton(insetLine);
		kDContainer1.addButton(removeLine);
		MyActionListener baseAL = new MyActionListener(kdtEntry);
		addLine.addActionListener(baseAL);
		insetLine.addActionListener(baseAL);
		removeLine.addActionListener(baseAL);
		copyLine.addActionListener(baseAL);
		if(OprtState.VIEW.equals(getOprtState())){
    		setButtonStatus(false);
    	}
    }
    
    private void setButtonStatus(boolean flag){
    	Object[] btns = kDContainer1.getButtons();
		for(int i = 0; i < btns.length; i++) {
			((KDWorkButton)btns[i]).setEnabled(flag);
		}
		
    }

    class MyActionListener implements ActionListener{
    	private KDTable table;
    	
    	public MyActionListener() {
		}
    	public MyActionListener(KDTable tab) {
    		table = tab;
		}
    	public void actionPerformed(ActionEvent e) {
    		String type = ((KDWorkButton)e.getSource()).getName();
    		IRow row = null;
    		if("addLine".equals(type)){
    			row = table.addRow();
    			row.getCell("splitBuild").setValue(Boolean.FALSE);
    		}else if("insetLine".equals(type)){
    			if(table.getSelectManager().size() > 0) {
    	            int top = table.getSelectManager().get().getTop();
    	            if(isTableColumnSelected(table))
    	            	row = table.addRow();
    	            else
    	            	row = table.addRow(top+1);
    	        } else {
    	        	row = table.addRow();
    	        }
    			row.getCell("splitBuild").setValue(Boolean.FALSE);
    		}else if("removeLine".equals(type)){
    			if(table.getSelectManager().size() == 0){
    	            MsgBox.showInfo(EASResource.getString("com.kingdee.eas.framework.FrameWorkResource.Msg_NoneEntry"));
    	            return;
    	        }
    	        int top = table.getSelectManager().get().getTop();
    	        if(table.getRow(top) == null){
    	            MsgBox.showInfo(EASResource.getString("com.kingdee.eas.framework.FrameWorkResource.Msg_NoneEntry"));
    	            return;
    	        }
    	        table.removeRow(top);
    		}else {
    			if(table.getSelectManager().size() == 0){
    	            MsgBox.showInfo("请选择一条记录！");
    	            return;
    	        }
    	        int top = table.getSelectManager().get().getTop();
    	        if(table.getRow(top) == null){
    	            MsgBox.showInfo("请选择一条记录！");
    	            return;
    	        }
    	        row = table.addRow(top+1);
    	        row.getCell("costAccount").setValue(table.getCell(top,"costAccount").getValue());
    	        row.getCell("pointName").setValue(table.getCell(top,"pointName").getValue());
    	        row.getCell("splitBuild").setValue(table.getCell(top,"splitBuild").getValue());
    		}
    	}
    }
    
    protected final boolean isTableColumnSelected(KDTable table){
        if(table.getSelectManager().size() > 0){
            KDTSelectBlock block = table.getSelectManager().get();
            if(block.getMode() == 4 || block.getMode() == 8)
                return true;
        }
        return false;
    }
    
	private void reBuildCostAccount() {
		//rebuild costaccount
    	CostAccountPromptBox selector = new CostAccountPromptBox(this);
		KDBizPromptBox prmtCostAccount = new KDBizPromptBox() {
			protected String valueToString(Object o) {
				String str = null;
				if (o != null && o instanceof CostAccountInfo) {
					str = ((CostAccountInfo) o).getLongNumber().replace('!', '.');
				}
				return str;
			}
		};
		selector.setShowParent(false);
		prmtCostAccount.setSelector(selector);
		prmtCostAccount.setEnabledMultiSelection(false);
		prmtCostAccount.setDisplayFormat("$longNumber$");
		prmtCostAccount.setEditFormat("$longNumber$");
		prmtCostAccount.setCommitFormat("$longNumber$");
		KDTDefaultCellEditor caEditor = new KDTDefaultCellEditor(prmtCostAccount);
		EntityViewInfo entityView = new EntityViewInfo();
		FilterInfo filter = new FilterInfo();
		filter.getFilterItems().add(new FilterItemInfo("fullOrgUnit.id", OrgConstants.DEF_CU_ID, CompareType.EQUALS));
		entityView.setFilter(filter);
		prmtCostAccount.setEntityViewInfo(entityView);
		kdtEntry.getColumn("costAccount").setEditor(caEditor);
		ObjectValueRender kdtCostEntries_costAccount_OVR = new ObjectValueRender();
		kdtCostEntries_costAccount_OVR.setFormat(new BizDataFormat("$name$"));
		kdtEntry.getColumn("costAccount").setRenderer(kdtCostEntries_costAccount_OVR);
	}
    
	protected void verifyInput(ActionEvent e) throws Exception {
		//先校验一遍有没有空的要素，再把两边的空格去掉，重新写入单元格
		int rowCount = kdtEntry.getRowCount3();
		for(int i = 0; i < rowCount; i++) {
			if(kdtEntry.getCell(i,"costAccount").getValue() == null){
				FDCMsgBox.showInfo("第"+(i+1)+"行的成本科目不能为空！");
				SysUtil.abort();
			}
			if(FDCHelper.isEmpty(kdtEntry.getCell(i,"pointName").getValue())){
				FDCMsgBox.showInfo("第"+(i+1)+"行的要素不能为空！");
				SysUtil.abort();
			}
		}
		//判断要素有没有重复的
//		for(int i = 0; i < rowCount; i++) {
//			String pointName = (String)kdtEntry.getCell(i,"pointName").getValue();
//			for(int j = i+1; j < rowCount; j++) {
//				if(pointName.equals(kdtEntry.getCell(j,"pointName").getValue())){
//					FDCMsgBox.showInfo("第"+(i+1)+"行的要素与第"+(j+1)+"行的要素名称重复！");
//					SysUtil.abort();
//				}
//			}
//		}
	}
	
	public void actionEdit_actionPerformed(ActionEvent arg0) throws Exception {
		super.actionEdit_actionPerformed(arg0);
		setButtonStatus(true);
	}
	

    /**
     * output getBizInterface method
     */
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return com.kingdee.eas.fdc.costindexdb.database.ProfessionPointFactory.getRemoteInstance();
    }

    /**
     * output createNewData method
     */
    protected com.kingdee.bos.dao.IObjectValue createNewData()
    {
        com.kingdee.eas.fdc.costindexdb.database.ProfessionPointInfo objectValue = new com.kingdee.eas.fdc.costindexdb.database.ProfessionPointInfo();
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));
		
        return objectValue;
    }

}