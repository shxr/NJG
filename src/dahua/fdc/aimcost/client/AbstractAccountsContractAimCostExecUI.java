/**
 * output package name
 */
package com.kingdee.eas.fdc.aimcost.client;

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
public abstract class AbstractAccountsContractAimCostExecUI extends com.kingdee.eas.framework.client.TreeListUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractAccountsContractAimCostExecUI.class);
    protected com.kingdee.bos.ctrl.swing.KDMenuItem menuItemNextVersion;
    protected com.kingdee.bos.ctrl.swing.KDMenuItem menuItemLastVersion;
    protected com.kingdee.bos.ctrl.swing.KDMenuItem menuItemAddRow;
    protected com.kingdee.bos.ctrl.swing.KDMenuItem menuItemDeleteRow;
    protected com.kingdee.bos.ctrl.swing.KDMenuItem menuItemRevert;
    protected com.kingdee.bos.ctrl.swing.KDMenuItem menuItemFirstVersion;
    protected com.kingdee.bos.ctrl.swing.KDMenuItem menuItemPreVersion;
    protected com.kingdee.bos.ctrl.swing.KDMenuItem menuItemAudit;
    protected com.kingdee.bos.ctrl.swing.KDMenuItem menuItemUnAudit;
    protected com.kingdee.bos.ctrl.swing.KDMenuItem menuItemApportion;
    protected com.kingdee.bos.ctrl.swing.KDMenuItem menuItemVersionInfo;
    protected com.kingdee.bos.ctrl.swing.KDMenuItem menuItemExpression;
    protected com.kingdee.bos.ctrl.swing.KDMenuItem menuItemSubmit;
    protected com.kingdee.bos.ctrl.swing.KDMenuItem menuItemRecense;
    /**
     * output class constructor
     */
    public AbstractAccountsContractAimCostExecUI() throws Exception
    {
        super();
        this.defaultObjectName = "mainQuery";
        jbInit();
        
        initUIP();
    }

    /**
     * output jbInit method
     */
    private void jbInit() throws Exception
    {
        this.resHelper = new ResourceBundleHelper(AbstractAccountsContractAimCostExecUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        mainQueryPK = new MetaDataPK("com.kingdee.eas.base.message", "MsgQuery");
        //actionMoveTree
        String _tempStr = null;
        actionMoveTree.setEnabled(true);
        actionMoveTree.setDaemonRun(false);

        _tempStr = resHelper.getString("ActionMoveTree.SHORT_DESCRIPTION");
        actionMoveTree.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionMoveTree.LONG_DESCRIPTION");
        actionMoveTree.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionMoveTree.NAME");
        actionMoveTree.putValue(ItemAction.NAME, _tempStr);
         this.actionMoveTree.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionMoveTree.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionMoveTree.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        this.menuItemNextVersion = new com.kingdee.bos.ctrl.swing.KDMenuItem();
        this.menuItemLastVersion = new com.kingdee.bos.ctrl.swing.KDMenuItem();
        this.menuItemAddRow = new com.kingdee.bos.ctrl.swing.KDMenuItem();
        this.menuItemDeleteRow = new com.kingdee.bos.ctrl.swing.KDMenuItem();
        this.menuItemRevert = new com.kingdee.bos.ctrl.swing.KDMenuItem();
        this.menuItemFirstVersion = new com.kingdee.bos.ctrl.swing.KDMenuItem();
        this.menuItemPreVersion = new com.kingdee.bos.ctrl.swing.KDMenuItem();
        this.menuItemAudit = new com.kingdee.bos.ctrl.swing.KDMenuItem();
        this.menuItemUnAudit = new com.kingdee.bos.ctrl.swing.KDMenuItem();
        this.menuItemApportion = new com.kingdee.bos.ctrl.swing.KDMenuItem();
        this.menuItemVersionInfo = new com.kingdee.bos.ctrl.swing.KDMenuItem();
        this.menuItemExpression = new com.kingdee.bos.ctrl.swing.KDMenuItem();
        this.menuItemSubmit = new com.kingdee.bos.ctrl.swing.KDMenuItem();
        this.menuItemRecense = new com.kingdee.bos.ctrl.swing.KDMenuItem();
        this.menuItemNextVersion.setName("menuItemNextVersion");
        this.menuItemLastVersion.setName("menuItemLastVersion");
        this.menuItemAddRow.setName("menuItemAddRow");
        this.menuItemDeleteRow.setName("menuItemDeleteRow");
        this.menuItemRevert.setName("menuItemRevert");
        this.menuItemFirstVersion.setName("menuItemFirstVersion");
        this.menuItemPreVersion.setName("menuItemPreVersion");
        this.menuItemAudit.setName("menuItemAudit");
        this.menuItemUnAudit.setName("menuItemUnAudit");
        this.menuItemApportion.setName("menuItemApportion");
        this.menuItemVersionInfo.setName("menuItemVersionInfo");
        this.menuItemExpression.setName("menuItemExpression");
        this.menuItemSubmit.setName("menuItemSubmit");
        this.menuItemRecense.setName("menuItemRecense");
        // CoreUI		
        this.tblMain.setFormatXml(resHelper.getString("tblMain.formatXml"));
        this.tblMain.addKDTEditListener(new com.kingdee.bos.ctrl.kdf.table.event.KDTEditAdapter() {
            public void editStopping(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) {
                try {
                    tblMain_editStopping(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
            public void editStopped(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) {
                try {
                    tblMain_editStopped(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });
                this.tblMain.putBindContents("mainQuery",new String[] {"","","","","","","","","","",""});

		
        this.menuItemAddNew.setVisible(false);		
        this.MenuItemAttachment.setEnabled(false);		
        this.MenuItemAttachment.setVisible(false);		
        this.pnlMain.setDividerLocation(180);		
        this.pnlMain.setOneTouchExpandable(true);
        // menuItemNextVersion		
        this.menuItemNextVersion.setText(resHelper.getString("menuItemNextVersion.text"));
        // menuItemLastVersion		
        this.menuItemLastVersion.setText(resHelper.getString("menuItemLastVersion.text"));
        // menuItemAddRow		
        this.menuItemAddRow.setText(resHelper.getString("menuItemAddRow.text"));
        // menuItemDeleteRow		
        this.menuItemDeleteRow.setText(resHelper.getString("menuItemDeleteRow.text"));
        // menuItemRevert		
        this.menuItemRevert.setText(resHelper.getString("menuItemRevert.text"));		
        this.menuItemRevert.setToolTipText(resHelper.getString("menuItemRevert.toolTipText"));
        // menuItemFirstVersion		
        this.menuItemFirstVersion.setText(resHelper.getString("menuItemFirstVersion.text"));
        // menuItemPreVersion		
        this.menuItemPreVersion.setText(resHelper.getString("menuItemPreVersion.text"));
        // menuItemAudit		
        this.menuItemAudit.setText(resHelper.getString("menuItemAudit.text"));
        // menuItemUnAudit		
        this.menuItemUnAudit.setText(resHelper.getString("menuItemUnAudit.text"));
        // menuItemApportion		
        this.menuItemApportion.setText(resHelper.getString("menuItemApportion.text"));
        // menuItemVersionInfo		
        this.menuItemVersionInfo.setText(resHelper.getString("menuItemVersionInfo.text"));		
        this.menuItemVersionInfo.setToolTipText(resHelper.getString("menuItemVersionInfo.toolTipText"));
        // menuItemExpression		
        this.menuItemExpression.setText(resHelper.getString("menuItemExpression.text"));		
        this.menuItemExpression.setToolTipText(resHelper.getString("menuItemExpression.toolTipText"));
        // menuItemSubmit		
        this.menuItemSubmit.setText(resHelper.getString("menuItemSubmit.text"));		
        this.menuItemSubmit.setToolTipText(resHelper.getString("menuItemSubmit.toolTipText"));
        // menuItemRecense		
        this.menuItemRecense.setText(resHelper.getString("menuItemRecense.text"));		
        this.menuItemRecense.setToolTipText(resHelper.getString("menuItemRecense.toolTipText"));
		//Register control's property binding
		registerBindings();
		registerUIState();


    }

    /**
     * output initUIContentLayout method
     */
    public void initUIContentLayout()
    {
        this.setBounds(new Rectangle(10, 10, 1016, 600));
        this.setLayout(new KDLayout());
        this.putClientProperty("OriginalBounds", new Rectangle(10, 10, 1016, 600));
        pnlMain.setBounds(new Rectangle(10, 10, 996, 580));
        this.add(pnlMain, new KDLayout.Constraints(10, 10, 996, 580, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        //pnlMain
        pnlMain.add(tblMain, "right");
        pnlMain.add(treeView, "left");
        //treeView
        treeView.setTree(treeMain);

    }


    /**
     * output initUIMenuBarLayout method
     */
    public void initUIMenuBarLayout()
    {
        this.menuBar.add(menuFile);
        this.menuBar.add(menuEdit);
        this.menuBar.add(menuView);
        this.menuBar.add(menuBiz);
        this.menuBar.add(menuTool);
        this.menuBar.add(menuTools);
        this.menuBar.add(menuHelp);
        //menuFile
        menuFile.add(menuItemAddNew);
        menuFile.add(menuItemImportData);
        menuFile.add(menuItemExportData);
        menuFile.add(separatorFile1);
        menuFile.add(MenuItemAttachment);
        menuFile.add(menuItemSubmit);
        menuFile.add(menuItemRecense);
        menuFile.add(kDSeparator1);
        menuFile.add(menuItemPageSetup);
        menuFile.add(menuItemPrint);
        menuFile.add(menuItemPrintPreview);
        menuFile.add(kDSeparator2);
        menuFile.add(menuItemExitCurrent);
        //menuEdit
        menuEdit.add(menuItemEdit);
        menuEdit.add(menuItemRemove);
        menuEdit.add(separatorEdit1);
        menuEdit.add(menuItemMoveTree);
        menuEdit.add(menuItemAddRow);
        menuEdit.add(menuItemDeleteRow);
        menuEdit.add(menuItemRevert);
        menuEdit.add(menuItemVersionInfo);
        menuEdit.add(menuItemExpression);
        //menuView
        menuView.add(menuItemView);
        menuView.add(menuItemLocate);
        menuView.add(separatorView1);
        menuView.add(menuItemQuery);
        menuView.add(menuItemRefresh);
        menuView.add(menuItemFirstVersion);
        menuView.add(menuItemPreVersion);
        menuView.add(menuItemNextVersion);
        menuView.add(menuItemLastVersion);
        //menuBiz
        menuBiz.add(menuItemCancelCancel);
        menuBiz.add(menuItemCancel);
        menuBiz.add(menuItemAudit);
        menuBiz.add(menuItemUnAudit);
        menuBiz.add(menuItemApportion);
        //menuTool
        menuTool.add(menuItemSendMessage);
        menuTool.add(menuItemCalculator);
        //menuTools
        menuTools.add(menuMail);
        menuTools.add(menuItemStartWorkFlow);
        menuTools.add(menuItemPublishReport);
        //menuMail
        menuMail.add(menuItemToHTML);
        menuMail.add(menuItemCopyScreen);
        menuMail.add(menuItemToExcel);
        //menuHelp
        menuHelp.add(menuItemHelp);
        menuHelp.add(kDSeparator12);
        menuHelp.add(menuItemRegPro);
        menuHelp.add(menuItemPersonalSite);
        menuHelp.add(helpseparatorDiv);
        menuHelp.add(menuitemProductval);
        menuHelp.add(kDSeparatorProduct);
        menuHelp.add(menuItemAbout);

    }

    /**
     * output initUIToolBarLayout method
     */
    public void initUIToolBarLayout()
    {
        this.toolBar.add(btnAddNew);
        this.toolBar.add(btnView);
        this.toolBar.add(btnEdit);
        this.toolBar.add(btnRemove);
        this.toolBar.add(btnQuery);
        this.toolBar.add(btnLocate);
        this.toolBar.add(btnMoveTree);
        this.toolBar.add(separatorFW1);
        this.toolBar.add(btnPageSetup);
        this.toolBar.add(btnRefresh);
        this.toolBar.add(btnAttachment);
        this.toolBar.add(btnPrint);
        this.toolBar.add(btnQueryScheme);
        this.toolBar.add(btnPrintPreview);
        this.toolBar.add(separatorFW2);
        this.toolBar.add(btnCancelCancel);
        this.toolBar.add(btnCancel);

    }

	//Regiester control's property binding.
	private void registerBindings(){		
	}
	//Regiester UI State
	private void registerUIState(){		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.fdc.aimcost.app.AccountsContractAimCostExecUIHandler";
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
     * output tblMain_editStopping method
     */
    protected void tblMain_editStopping(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) throws Exception
    {
        //write your code here
    }

    /**
     * output tblMain_editStopped method
     */
    protected void tblMain_editStopped(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) throws Exception
    {
        //write your code here
    }

    /**
     * output getSelectors method
     */
    public SelectorItemCollection getSelectors()
    {
        SelectorItemCollection sic = new SelectorItemCollection();
        return sic;
    }        
    	

    /**
     * output actionMoveTree_actionPerformed method
     */
    public void actionMoveTree_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionMoveTree_actionPerformed(e);
    }

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.fdc.aimcost.client", "AccountsContractAimCostExecUI");
    }




}