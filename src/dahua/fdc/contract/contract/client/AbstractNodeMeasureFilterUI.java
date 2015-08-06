/**
 * output package name
 */
package com.kingdee.eas.fdc.contract.client;

import org.apache.log4j.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.event.*;
import javax.swing.KeyStroke;

import com.kingdee.bos.ctrl.swing.*;
import com.kingdee.bos.ctrl.kdf.table.*;
import com.kingdee.bos.ctrl.kdf.data.event.*;
import com.kingdee.bos.dao.*;
import com.kingdee.bos.dao.query.*;
import com.kingdee.bos.metadata.*;
import com.kingdee.bos.metadata.entity.*;
import com.kingdee.bos.ui.face.*;
import com.kingdee.bos.ui.util.ResourceBundleHelper;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.bos.service.ServiceContext;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.enums.EnumUtils;
import com.kingdee.bos.ui.face.UIRuleUtil;
import com.kingdee.bos.ctrl.swing.event.*;
import com.kingdee.bos.ctrl.kdf.table.event.*;
import com.kingdee.bos.ctrl.extendcontrols.*;
import com.kingdee.bos.ctrl.kdf.util.render.*;
import com.kingdee.bos.ui.face.IItemAction;
import com.kingdee.eas.framework.batchHandler.RequestContext;
import com.kingdee.bos.ui.util.IUIActionPostman;
import com.kingdee.bos.appframework.client.servicebinding.ActionProxyFactory;
import com.kingdee.bos.appframework.uistatemanage.ActionStateConst;
import com.kingdee.bos.appframework.validator.ValidateHelper;
import com.kingdee.bos.appframework.uip.UINavigator;


/**
 * output class name
 */
public abstract class AbstractNodeMeasureFilterUI extends com.kingdee.eas.base.commonquery.client.CustomerQueryPanel
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractNodeMeasureFilterUI.class);
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contContract;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contDateFrom;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contDateTo;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkDateTo;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkDateFrom;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtContract;
    /**
     * output class constructor
     */
    public AbstractNodeMeasureFilterUI() throws Exception
    {
        super();
        jbInit();
        
        initUIP();
    }

    /**
     * output jbInit method
     */
    private void jbInit() throws Exception
    {
        this.resHelper = new ResourceBundleHelper(AbstractNodeMeasureFilterUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        this.contContract = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contDateFrom = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contDateTo = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.pkDateTo = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.pkDateFrom = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.prmtContract = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.contContract.setName("contContract");
        this.contDateFrom.setName("contDateFrom");
        this.contDateTo.setName("contDateTo");
        this.pkDateTo.setName("pkDateTo");
        this.pkDateFrom.setName("pkDateFrom");
        this.prmtContract.setName("prmtContract");
        // CustomerQueryPanel
        // contContract		
        this.contContract.setBoundLabelText(resHelper.getString("contContract.boundLabelText"));		
        this.contContract.setBoundLabelLength(100);		
        this.contContract.setBoundLabelUnderline(true);
        // contDateFrom		
        this.contDateFrom.setBoundLabelText(resHelper.getString("contDateFrom.boundLabelText"));		
        this.contDateFrom.setBoundLabelLength(100);		
        this.contDateFrom.setBoundLabelUnderline(true);
        // contDateTo		
        this.contDateTo.setBoundLabelText(resHelper.getString("contDateTo.boundLabelText"));		
        this.contDateTo.setBoundLabelLength(100);		
        this.contDateTo.setBoundLabelUnderline(true);
        // pkDateTo
        // pkDateFrom
        // prmtContract		
        this.prmtContract.setEditable(true);		
        this.prmtContract.setEnabledMultiSelection(true);		
        this.prmtContract.setDisplayFormat("$name$");		
        this.prmtContract.setEditFormat("$number$");		
        this.prmtContract.setCommitFormat("$number$");		
        this.prmtContract.setQueryInfo("com.kingdee.eas.fdc.contract.app.F7ContractBillQuery");
		//Register control's property binding
		registerBindings();
		registerUIState();


    }

	public com.kingdee.bos.ctrl.swing.KDToolBar[] getUIMultiToolBar(){
		java.util.List list = new java.util.ArrayList();
		com.kingdee.bos.ctrl.swing.KDToolBar[] bars = super.getUIMultiToolBar();
		if (bars != null) {
			list.addAll(java.util.Arrays.asList(bars));
		}
		return (com.kingdee.bos.ctrl.swing.KDToolBar[])list.toArray(new com.kingdee.bos.ctrl.swing.KDToolBar[list.size()]);
	}




    /**
     * output initUIContentLayout method
     */
    public void initUIContentLayout()
    {
        this.setBounds(new Rectangle(10, 10, 400, 360));
        this.setLayout(null);
        contContract.setBounds(new Rectangle(10, 10, 270, 19));
        this.add(contContract, null);
        contDateFrom.setBounds(new Rectangle(10, 35, 270, 19));
        this.add(contDateFrom, null);
        contDateTo.setBounds(new Rectangle(10, 60, 270, 19));
        this.add(contDateTo, null);
        //contContract
        contContract.setBoundEditor(prmtContract);
        //contDateFrom
        contDateFrom.setBoundEditor(pkDateFrom);
        //contDateTo
        contDateTo.setBoundEditor(pkDateTo);

    }


    /**
     * output initUIMenuBarLayout method
     */
    public void initUIMenuBarLayout()
    {

    }

    /**
     * output initUIToolBarLayout method
     */
    public void initUIToolBarLayout()
    {


    }

	//Regiester control's property binding.
	private void registerBindings(){		
	}
	//Regiester UI State
	private void registerUIState(){		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.fdc.contract.app.NodeMeasureFilterUIHandler";
	}
	public IUIActionPostman prepareInit() {
		IUIActionPostman clientHanlder = super.prepareInit();
		if (clientHanlder != null) {
			RequestContext request = new RequestContext();
    		request.setClassName(getUIHandlerClassName());
			clientHanlder.setRequestContext(request);
		}
		return clientHanlder;
    }
	
	public boolean isPrepareInit() {
    	return false;
    }
    protected void initUIP() {
        super.initUIP();
    }



	
	

    /**
     * output setDataObject method
     */
    public void setDataObject(IObjectValue dataObject)
    {
        IObjectValue ov = dataObject;        	    	
        super.setDataObject(ov);
    }

    /**
     * output loadFields method
     */
    public void loadFields()
    {
        dataBinder.loadFields();
    }
    /**
     * output storeFields method
     */
    public void storeFields()
    {
		dataBinder.storeFields();
    }

	/**
	 * ????????��??
	 */
	protected void registerValidator() {
    	getValidateHelper().setCustomValidator( getValidator() );		
	}



    /**
     * output setOprtState method
     */
    public void setOprtState(String oprtType)
    {
        super.setOprtState(oprtType);
    }


    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.fdc.contract.client", "NodeMeasureFilterUI");
    }




}