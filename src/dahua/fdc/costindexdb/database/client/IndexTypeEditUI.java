/**
 * output package name
 */
package com.kingdee.eas.fdc.costindexdb.database.client;

import java.awt.event.*;
import org.apache.log4j.Logger;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.eas.framework.*;

/**
 * output class name
 */
public class IndexTypeEditUI extends AbstractIndexTypeEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(IndexTypeEditUI.class);
    
    /**
     * output class constructor
     */
    public IndexTypeEditUI() throws Exception
    {
        super();
    }

    public void onLoad() throws Exception {
    	// TODO Auto-generated method stub
    	super.onLoad();
    }
    
    /**
     * output getBizInterface method
     */
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return com.kingdee.eas.fdc.costindexdb.database.IndexTypeFactory.getRemoteInstance();
    }

    /**
     * output createNewData method
     */
    protected com.kingdee.bos.dao.IObjectValue createNewData()
    {
        com.kingdee.eas.fdc.costindexdb.database.IndexTypeInfo objectValue = new com.kingdee.eas.fdc.costindexdb.database.IndexTypeInfo();
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));
		
        return objectValue;
    }

}