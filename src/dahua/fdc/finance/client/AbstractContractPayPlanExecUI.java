/**
 * output package name
 */
package com.kingdee.eas.fdc.finance.client;

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
public abstract class AbstractContractPayPlanExecUI extends com.kingdee.eas.fdc.basedata.client.ProjectTreeListBaseUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractContractPayPlanExecUI.class);
    protected com.kingdee.bos.ctrl.swing.KDSplitPane kDSplitPane2;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPanel1;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contStartMonth;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contStartYear;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contEndYear;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contEndMonth;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnSearch;
    protected com.kingdee.bos.ctrl.swing.KDSpinner spStartMonth;
    protected com.kingdee.bos.ctrl.swing.KDSpinner spStartYear;
    protected com.kingdee.bos.ctrl.swing.KDSpinner spEndYear;
    protected com.kingdee.bos.ctrl.swing.KDSpinner spEndMonth;
    protected ActionSearch actionSearch = null;
    /**
     * output class constructor
     */
    public AbstractContractPayPlanExecUI() throws Exception
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
        this.resHelper = new ResourceBundleHelper(AbstractContractPayPlanExecUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        mainQueryPK = new MetaDataPK("com.kingdee.eas.fdc.basedata.app", "CurProjectQuery");
        //actionSearch
        this.actionSearch = new ActionSearch(this);
        getActionManager().registerAction("actionSearch", actionSearch);
         this.actionSearch.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        this.kDSplitPane2 = new com.kingdee.bos.ctrl.swing.KDSplitPane();
        this.kDPanel1 = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.contStartMonth = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contStartYear = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contEndYear = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contEndMonth = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.btnSearch = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.spStartMonth = new com.kingdee.bos.ctrl.swing.KDSpinner();
        this.spStartYear = new com.kingdee.bos.ctrl.swing.KDSpinner();
        this.spEndYear = new com.kingdee.bos.ctrl.swing.KDSpinner();
        this.spEndMonth = new com.kingdee.bos.ctrl.swing.KDSpinner();
        this.kDSplitPane2.setName("kDSplitPane2");
        this.kDPanel1.setName("kDPanel1");
        this.contStartMonth.setName("contStartMonth");
        this.contStartYear.setName("contStartYear");
        this.contEndYear.setName("contEndYear");
        this.contEndMonth.setName("contEndMonth");
        this.btnSearch.setName("btnSearch");
        this.spStartMonth.setName("spStartMonth");
        this.spStartYear.setName("spStartYear");
        this.spEndYear.setName("spEndYear");
        this.spEndMonth.setName("spEndMonth");
        // CoreUI
		String tblMainStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles /><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup /><t:Head /></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot> ";

                this.tblMain.putBindContents("mainQuery",new String[] {});

		
        this.btnAddNew.setVisible(false);		
        this.btnView.setVisible(false);		
        this.btnEdit.setVisible(false);		
        this.btnRemove.setVisible(false);		
        this.btnRefresh.setVisible(false);		
        this.btnLocate.setVisible(false);		
        this.btnQuery.setVisible(false);		
        this.btnAttachment.setVisible(false);		
        this.btnQueryScheme.setVisible(false);		
        this.btnWorkFlowG.setVisible(false);		
        this.treeProject.setToolTipText(resHelper.getString("treeProject.toolTipText"));		
        this.btnAudit.setVisible(false);		
        this.btnUnAudit.setVisible(false);
        // kDSplitPane2		
        this.kDSplitPane2.setOrientation(0);		
        this.kDSplitPane2.setDividerLocation(35);
        // kDPanel1
        // contStartMonth		
        this.contStartMonth.setBoundLabelText(resHelper.getString("contStartMonth.boundLabelText"));		
        this.contStartMonth.setBoundLabelLength(30);		
        this.contStartMonth.setBoundLabelUnderline(true);
        // contStartYear		
        this.contStartYear.setBoundLabelText(resHelper.getString("contStartYear.boundLabelText"));		
        this.contStartYear.setBoundLabelLength(100);		
        this.contStartYear.setBoundLabelUnderline(true);
        // contEndYear		
        this.contEndYear.setBoundLabelText(resHelper.getString("contEndYear.boundLabelText"));		
        this.contEndYear.setBoundLabelLength(100);		
        this.contEndYear.setBoundLabelUnderline(true);
        // contEndMonth		
        this.contEndMonth.setBoundLabelText(resHelper.getString("contEndMonth.boundLabelText"));		
        this.contEndMonth.setBoundLabelLength(30);		
        this.contEndMonth.setBoundLabelUnderline(true);
        // btnSearch
        this.btnSearch.setAction((IItemAction)ActionProxyFactory.getProxy(actionSearch, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnSearch.setText(resHelper.getString("btnSearch.text"));
        // spStartMonth
        this.spStartMonth.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent e) {
                try {
                    spStartMonth_stateChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // spStartYear
        this.spStartYear.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent e) {
                try {
                    spStartYear_stateChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // spEndYear
        this.spEndYear.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent e) {
                try {
                    spEndYear_stateChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // spEndMonth
        this.spEndMonth.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent e) {
                try {
                    spEndMonth_stateChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
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
        this.setBounds(new Rectangle(10, 10, 1013, 629));
        this.setLayout(new KDLayout());
        this.putClientProperty("OriginalBounds", new Rectangle(10, 10, 1013, 629));
        kDSplitPane1.setBounds(new Rectangle(10, 10, 993, 609));
        this.add(kDSplitPane1, new KDLayout.Constraints(10, 10, 993, 609, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        //kDSplitPane1
        kDSplitPane1.add(treeProject, "left");
        kDSplitPane1.add(kDSplitPane2, "right");
        //kDSplitPane2
        kDSplitPane2.add(tblMain, "bottom");
        kDSplitPane2.add(kDPanel1, "top");
        //kDPanel1
        kDPanel1.setLayout(new KDLayout());
        kDPanel1.putClientProperty("OriginalBounds", new Rectangle(0, 0, 781, 34));        contStartMonth.setBounds(new Rectangle(194, 10, 106, 19));
        kDPanel1.add(contStartMonth, new KDLayout.Constraints(194, 10, 106, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT));
        contStartYear.setBounds(new Rectangle(10, 10, 166, 19));
        kDPanel1.add(contStartYear, new KDLayout.Constraints(10, 10, 166, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT));
        contEndYear.setBounds(new Rectangle(329, 10, 166, 19));
        kDPanel1.add(contEndYear, new KDLayout.Constraints(329, 10, 166, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT));
        contEndMonth.setBounds(new Rectangle(506, 10, 106, 19));
        kDPanel1.add(contEndMonth, new KDLayout.Constraints(506, 10, 106, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT));
        btnSearch.setBounds(new Rectangle(648, 10, 77, 19));
        kDPanel1.add(btnSearch, new KDLayout.Constraints(648, 10, 77, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT));
        //contStartMonth
        contStartMonth.setBoundEditor(spStartMonth);
        //contStartYear
        contStartYear.setBoundEditor(spStartYear);
        //contEndYear
        contEndYear.setBoundEditor(spEndYear);
        //contEndMonth
        contEndMonth.setBoundEditor(spEndMonth);

    }


    /**
     * output initUIMenuBarLayout method
     */
    public void initUIMenuBarLayout()
    {
        this.menuBar.add(menuFile);
        this.menuBar.add(menuEdit);
        this.menuBar.add(MenuService);
        this.menuBar.add(menuView);
        this.menuBar.add(menuBiz);
        this.menuBar.add(menuTool);
        this.menuBar.add(menuWorkFlow);
        this.menuBar.add(menuTools);
        this.menuBar.add(menuHelp);
        //menuFile
        menuFile.add(menuItemAddNew);
        menuFile.add(menuItemImportData);
        menuFile.add(menuItemExportData);
        menuFile.add(separatorFile1);
        menuFile.add(MenuItemAttachment);
        menuFile.add(kDSeparator1);
        menuFile.add(menuItemPageSetup);
        menuFile.add(menuItemPrint);
        menuFile.add(menuItemPrintPreview);
        menuFile.add(kDSeparator2);
        menuFile.add(menuItemExitCurrent);
        //menuEdit
        menuEdit.add(menuItemEdit);
        menuEdit.add(menuItemRemove);
        menuEdit.add(kDSeparator3);
        menuEdit.add(menuItemCreateTo);
        menuEdit.add(menuItemCopyTo);
        menuEdit.add(kDSeparator4);
        //MenuService
        MenuService.add(MenuItemKnowStore);
        MenuService.add(MenuItemAnwser);
        MenuService.add(SepratorService);
        MenuService.add(MenuItemRemoteAssist);
        //menuView
        menuView.add(menuItemView);
        menuView.add(menuItemLocate);
        menuView.add(kDSeparator5);
        menuView.add(menuItemQuery);
        menuView.add(menuItemRefresh);
        menuView.add(menuItemSwitchView);
        menuView.add(separatorView1);
        menuView.add(menuItemTraceUp);
        menuView.add(menuItemQueryScheme);
        menuView.add(menuItemTraceDown);
        menuView.add(kDSeparator6);
        //menuBiz
        menuBiz.add(menuItemCancelCancel);
        menuBiz.add(menuItemCancel);
        menuBiz.add(menuItemVoucher);
        menuBiz.add(menuItemDelVoucher);
        menuBiz.add(menuItemAudit);
        menuBiz.add(menuItemUnAudit);
        //menuTool
        menuTool.add(menuItemSendMessage);
        menuTool.add(menuItemCalculator);
        //menuWorkFlow
        menuWorkFlow.add(menuItemViewDoProccess);
        menuWorkFlow.add(menuItemMultiapprove);
        menuWorkFlow.add(menuItemWorkFlowG);
        menuWorkFlow.add(menuItemWorkFlowList);
        menuWorkFlow.add(separatorWF1);
        menuWorkFlow.add(menuItemNextPerson);
        menuWorkFlow.add(menuItemAuditResult);
        menuWorkFlow.add(kDSeparator7);
        menuWorkFlow.add(menuItemSendSmsMessage);
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
        this.toolBar.add(btnRefresh);
        this.toolBar.add(btnLocate);
        this.toolBar.add(btnAttachment);
        this.toolBar.add(btnPrint);
        this.toolBar.add(btnPrintPreview);
        this.toolBar.add(separatorFW1);
        this.toolBar.add(btnPageSetup);
        this.toolBar.add(btnQuery);
        this.toolBar.add(btnMultiapprove);
        this.toolBar.add(separatorFW2);
        this.toolBar.add(btnAuditResult);
        this.toolBar.add(btnNextPerson);
        this.toolBar.add(btnVoucher);
        this.toolBar.add(btnQueryScheme);
        this.toolBar.add(btnDelVoucher);
        this.toolBar.add(btnCreateTo);
        this.toolBar.add(separatorFW3);
        this.toolBar.add(btnCopyTo);
        this.toolBar.add(btnTraceUp);
        this.toolBar.add(btnTraceDown);
        this.toolBar.add(btnWorkFlowG);
        this.toolBar.add(btnWFViewdoProccess);
        this.toolBar.add(btnWorkFlowList);
        this.toolBar.add(btnSignature);
        this.toolBar.add(btnAudit);
        this.toolBar.add(btnViewSignature);
        this.toolBar.add(btnUnAudit);
        this.toolBar.add(separatorFW4);
        this.toolBar.add(btnCancel);
        this.toolBar.add(btnCancelCancel);


    }

	//Regiester control's property binding.
	private void registerBindings(){		
	}
	//Regiester UI State
	private void registerUIState(){		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.fdc.finance.app.ContractPayPlanExecUIHandler";
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
     * output spStartMonth_stateChanged method
     */
    protected void spStartMonth_stateChanged(javax.swing.event.ChangeEvent e) throws Exception
    {
    }

    /**
     * output spStartYear_stateChanged method
     */
    protected void spStartYear_stateChanged(javax.swing.event.ChangeEvent e) throws Exception
    {
    }

    /**
     * output spEndYear_stateChanged method
     */
    protected void spEndYear_stateChanged(javax.swing.event.ChangeEvent e) throws Exception
    {
    }

    /**
     * output spEndMonth_stateChanged method
     */
    protected void spEndMonth_stateChanged(javax.swing.event.ChangeEvent e) throws Exception
    {
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
     * output actionSearch_actionPerformed method
     */
    public void actionSearch_actionPerformed(ActionEvent e) throws Exception
    {
    }
	public RequestContext prepareActionSearch(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionSearch() {
    	return false;
    }

    /**
     * output ActionSearch class
     */     
    protected class ActionSearch extends ItemAction {     
    
        public ActionSearch()
        {
            this(null);
        }

        public ActionSearch(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionSearch.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionSearch.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionSearch.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractContractPayPlanExecUI.this, "ActionSearch", "actionSearch_actionPerformed", e);
        }
    }

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.fdc.finance.client", "ContractPayPlanExecUI");
    }




}