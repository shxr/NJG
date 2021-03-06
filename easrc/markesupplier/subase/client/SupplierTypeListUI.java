/**
 * output package name
 */
package com.kingdee.eas.port.markesupplier.subase.client;

import java.awt.event.*;

import org.apache.log4j.Logger;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.eas.basedata.org.OrgConstants;
import com.kingdee.eas.common.client.SysContext;
import com.kingdee.eas.common.client.UIFactoryName;
import com.kingdee.eas.framework.*;
import com.kingdee.eas.port.markesupplier.subase.MarketSupplierBusinessModeInfo;
import com.kingdee.eas.port.markesupplier.subase.SupplierTypeInfo;
import com.kingdee.eas.rptclient.newrpt.util.MsgBox;
import com.kingdee.eas.util.SysUtil;

/**
 * output class name
 */
public class SupplierTypeListUI extends AbstractSupplierTypeListUI
{
    private static final Logger logger = CoreUIObject.getLogger(SupplierTypeListUI.class);
    
    /**
     * output class constructor
     */
    public SupplierTypeListUI() throws Exception
    {
        super();
    }

    protected String getEditUIModal() {
		return UIFactoryName.MODEL;
	}
    public void onLoad() throws Exception {
    	super.onLoad();
    	btnCancel.setVisible(true);
    	btnCancelCancel.setVisible(true);
    	
    	if (OrgConstants.DEF_CU_ID.equals(SysContext.getSysContext().getCurrentCtrlUnit().getId().toString())) {
    		this.actionEdit.setEnabled(true);
    		this.actionAddNew.setEnabled(true);
    		this.actionRemove.setEnabled(true);
    	} else {
    		this.actionEdit.setEnabled(false);
    		this.actionAddNew.setEnabled(false);
    		this.actionRemove.setEnabled(false);
    	}
    }

    public void actionCancel_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionCancel_actionPerformed(e);
        refresh(e);
    }

    /**
     * output actionCancelCancel_actionPerformed
     */
    public void actionCancelCancel_actionPerformed(ActionEvent e) throws Exception
    {
    	checkSelected();
    	ObjectUuidPK pk = new ObjectUuidPK(this.getSelectedKeyValue());
    	getBizInterface().cancelCancel(pk, getBizInterface().getValue(pk));
        refresh(e);
    }


    /**
     * output actionEdit_actionPerformed
     */
    public void actionEdit_actionPerformed(ActionEvent e) throws Exception
    {
    	checkSelected();
    	ObjectUuidPK pk = new ObjectUuidPK(this.getSelectedKeyValue());
    	SupplierTypeInfo Info = (SupplierTypeInfo)getBizInterface().getValue(pk);
    	if(Info.isIsEnable()){
    		MsgBox.showWarning("已启用，不能执行此操作！");SysUtil.abort();
    	}
        super.actionEdit_actionPerformed(e);
    }

    /**
     * output actionRemove_actionPerformed
     */
    public void actionRemove_actionPerformed(ActionEvent e) throws Exception
    {
    	checkSelected();
    	ObjectUuidPK pk = new ObjectUuidPK(this.getSelectedKeyValue());
    	SupplierTypeInfo Info = (SupplierTypeInfo)getBizInterface().getValue(pk);
    	if(Info.isIsEnable()){
    		MsgBox.showWarning("已启用，不能执行此操作！");SysUtil.abort();
    	}
        super.actionRemove_actionPerformed(e);
    }

    /**
     * output getBizInterface method
     */
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return com.kingdee.eas.port.markesupplier.subase.SupplierTypeFactory.getRemoteInstance();
    }

    /**
     * output createNewData method
     */
    protected com.kingdee.bos.dao.IObjectValue createNewData()
    {
        com.kingdee.eas.port.markesupplier.subase.SupplierTypeInfo objectValue = new com.kingdee.eas.port.markesupplier.subase.SupplierTypeInfo();
		
        return objectValue;
    }

}