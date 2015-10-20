/**
 * output package name
 */
package com.kingdee.eas.fdc.contract.programming.client;

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
public abstract class AbstractProgrammingTemplateEditUI extends com.kingdee.eas.basedata.framework.client.DataBaseSIEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractProgrammingTemplateEditUI.class);
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer2;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer4;
    protected com.kingdee.bos.ctrl.swing.KDTabbedPane paneBIZLayerControl9;
    protected com.kingdee.bos.ctrl.swing.KDContainer pnlContract;
    protected com.kingdee.bos.ctrl.swing.KDPanel pnlSubject;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtName;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtRemark;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtEntires;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtCosetEntries;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtNumber;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnRefresh;
    protected com.kingdee.bos.ctrl.swing.KDMenuItem menuItemRefresh;
    protected com.kingdee.eas.fdc.contract.programming.ProgrammingTemplateInfo editData = null;
    protected ActionRefresh actionRefresh = null;
    /**
     * output class constructor
     */
    public AbstractProgrammingTemplateEditUI() throws Exception
    {
        super();
        this.defaultObjectName = "editData";
        jbInit();
        
        initUIP();
    }

    /**
     * output jbInit method
     */
    private void jbInit() throws Exception
    {
        this.resHelper = new ResourceBundleHelper(AbstractProgrammingTemplateEditUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        //actionRefresh
        this.actionRefresh = new ActionRefresh(this);
        getActionManager().registerAction("actionRefresh", actionRefresh);
         this.actionRefresh.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        this.kDLabelContainer2 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer4 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.paneBIZLayerControl9 = new com.kingdee.bos.ctrl.swing.KDTabbedPane();
        this.pnlContract = new com.kingdee.bos.ctrl.swing.KDContainer();
        this.pnlSubject = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.txtName = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtRemark = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.kdtEntires = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.kdtCosetEntries = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.txtNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.btnRefresh = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.menuItemRefresh = new com.kingdee.bos.ctrl.swing.KDMenuItem();
        this.kDLabelContainer2.setName("kDLabelContainer2");
        this.kDLabelContainer4.setName("kDLabelContainer4");
        this.paneBIZLayerControl9.setName("paneBIZLayerControl9");
        this.pnlContract.setName("pnlContract");
        this.pnlSubject.setName("pnlSubject");
        this.txtName.setName("txtName");
        this.txtRemark.setName("txtRemark");
        this.kdtEntires.setName("kdtEntires");
        this.kdtCosetEntries.setName("kdtCosetEntries");
        this.txtNumber.setName("txtNumber");
        this.btnRefresh.setName("btnRefresh");
        this.menuItemRefresh.setName("menuItemRefresh");
        // CoreUI		
        this.kDSeparator1.setVisible(false);		
        this.btnAddNew.setVisible(false);		
        this.btnSave.setVisible(false);		
        this.btnCopy.setVisible(false);		
        this.btnRemove.setVisible(false);		
        this.btnCancelCancel.setVisible(false);		
        this.btnCancelCancel.setEnabled(false);		
        this.btnCancel.setVisible(false);		
        this.btnCancel.setEnabled(false);		
        this.btnFirst.setVisible(false);		
        this.btnFirst.setEnabled(false);		
        this.btnPre.setVisible(false);		
        this.btnPre.setEnabled(false);		
        this.btnNext.setVisible(false);		
        this.btnNext.setEnabled(false);		
        this.btnLast.setVisible(false);		
        this.btnLast.setEnabled(false);		
        this.btnPrint.setVisible(false);		
        this.btnPrintPreview.setVisible(false);		
        this.menuItemAddNew.setVisible(false);		
        this.menuItemSave.setVisible(false);		
        this.menuItemPrint.setVisible(false);		
        this.menuItemPrintPreview.setVisible(false);		
        this.menuItemCopy.setVisible(false);		
        this.menuItemCopy.setEnabled(false);		
        this.menuItemFirst.setVisible(false);		
        this.menuItemPre.setVisible(false);		
        this.menuItemNext.setVisible(false);		
        this.menuItemLast.setVisible(false);		
        this.btnAttachment.setVisible(false);		
        this.menuItemRemove.setVisible(false);		
        this.menuItemRemove.setEnabled(false);		
        this.MenuItemAttachment.setVisible(false);		
        this.menuBiz.setVisible(false);		
        this.menuItemCancelCancel.setVisible(false);		
        this.menuItemCancel.setVisible(false);
        // kDLabelContainer2		
        this.kDLabelContainer2.setBoundLabelText(resHelper.getString("kDLabelContainer2.boundLabelText"));		
        this.kDLabelContainer2.setBoundLabelLength(100);		
        this.kDLabelContainer2.setBoundLabelUnderline(true);		
        this.kDLabelContainer2.setBoundLabelAlignment(7);		
        this.kDLabelContainer2.setVisible(true);		
        this.kDLabelContainer2.setForeground(new java.awt.Color(0,0,0));
        // kDLabelContainer4		
        this.kDLabelContainer4.setBoundLabelText(resHelper.getString("kDLabelContainer4.boundLabelText"));		
        this.kDLabelContainer4.setBoundLabelLength(100);		
        this.kDLabelContainer4.setBoundLabelUnderline(true);		
        this.kDLabelContainer4.setBoundLabelAlignment(7);		
        this.kDLabelContainer4.setVisible(true);		
        this.kDLabelContainer4.setForeground(new java.awt.Color(0,0,0));
        // paneBIZLayerControl9		
        this.paneBIZLayerControl9.setVisible(true);
        this.paneBIZLayerControl9.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent e) {
                try {
                    pnlBizLayer_stateChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // pnlContract		
        this.pnlContract.setVisible(true);
        // pnlSubject		
        this.pnlSubject.setVisible(true);
        // txtName		
        this.txtName.setMaxLength(80);		
        this.txtName.setRequired(true);
        // txtRemark		
        this.txtRemark.setMaxLength(255);
        // kdtEntires		
        this.kdtEntires.setFormatXml(resHelper.getString("kdtEntires.formatXml"));
        this.kdtEntires.addKDTMouseListener(new com.kingdee.bos.ctrl.kdf.table.event.KDTMouseListener() {
            public void tableClicked(com.kingdee.bos.ctrl.kdf.table.event.KDTMouseEvent e) {
                try {
                    kdtEntires_tableClicked(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        this.kdtEntires.addKDTActiveCellListener(new com.kingdee.bos.ctrl.kdf.table.event.KDTActiveCellListener() {
            public void activeCellChanged(com.kingdee.bos.ctrl.kdf.table.event.KDTActiveCellEvent e) {
                try {
                    kdtEntires_activeCellChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        this.kdtEntires.addKDTEditListener(new com.kingdee.bos.ctrl.kdf.table.event.KDTEditAdapter() {
            public void editStopped(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) {
                try {
                    kdtEntires_editStopped(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
            public void editStarting(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) {
                try {
                    kdtEntires_editStarting(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
            public void editStarted(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) {
                try {
                    kdtEntires_editStarted(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });

                this.kdtEntires.putBindContents("editData",new String[] {"longNumber","name","","attachment","description","id","number","level","head.longNumber","displayName","sortNumber"});


        this.kdtEntires.checkParsed();
        KDTextField kdtEntires_attachment_TextField = new KDTextField();
        kdtEntires_attachment_TextField.setName("kdtEntires_attachment_TextField");
        kdtEntires_attachment_TextField.setMaxLength(255);
        KDTDefaultCellEditor kdtEntires_attachment_CellEditor = new KDTDefaultCellEditor(kdtEntires_attachment_TextField);
        this.kdtEntires.getColumn("attachment").setEditor(kdtEntires_attachment_CellEditor);
        KDFormattedTextField kdtEntires_sortNumber_TextField = new KDFormattedTextField();
        kdtEntires_sortNumber_TextField.setName("kdtEntires_sortNumber_TextField");
        kdtEntires_sortNumber_TextField.setVisible(true);
        kdtEntires_sortNumber_TextField.setEditable(true);
        kdtEntires_sortNumber_TextField.setHorizontalAlignment(2);
        kdtEntires_sortNumber_TextField.setDataType(0);
        KDTDefaultCellEditor kdtEntires_sortNumber_CellEditor = new KDTDefaultCellEditor(kdtEntires_sortNumber_TextField);
        this.kdtEntires.getColumn("sortNumber").setEditor(kdtEntires_sortNumber_CellEditor);
        // kdtCosetEntries		
        this.kdtCosetEntries.setFormatXml(resHelper.getString("kdtCosetEntries.formatXml"));

        

        this.kdtCosetEntries.checkParsed();
        // txtNumber		
        this.txtNumber.setVisible(false);		
        this.txtNumber.setEnabled(false);
        // btnRefresh
        this.btnRefresh.setAction((IItemAction)ActionProxyFactory.getProxy(actionRefresh, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnRefresh.setText(resHelper.getString("btnRefresh.text"));
        // menuItemRefresh
        this.menuItemRefresh.setAction((IItemAction)ActionProxyFactory.getProxy(actionRefresh, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemRefresh.setText(resHelper.getString("menuItemRefresh.text"));		
        this.menuItemRefresh.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_refresh"));
        this.setFocusTraversalPolicy(new com.kingdee.bos.ui.UIFocusTraversalPolicy(new java.awt.Component[] {kdtEntires}));
        this.setFocusCycleRoot(true);
		//Register control's property binding
		registerBindings();
		registerUIState();


    }

    /**
     * output initUIContentLayout method
     */
    public void initUIContentLayout()
    {
        this.setBounds(new Rectangle(0, 0, 1013, 629));
        this.setLayout(new KDLayout());
        this.putClientProperty("OriginalBounds", new Rectangle(0, 0, 1013, 629));
        kDLabelContainer2.setBounds(new Rectangle(9, 16, 393, 19));
        this.add(kDLabelContainer2, new KDLayout.Constraints(9, 16, 393, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDLabelContainer4.setBounds(new Rectangle(453, 16, 552, 19));
        this.add(kDLabelContainer4, new KDLayout.Constraints(453, 16, 552, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        paneBIZLayerControl9.setBounds(new Rectangle(10, 45, 997, 577));
        this.add(paneBIZLayerControl9, new KDLayout.Constraints(10, 45, 997, 577, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        txtNumber.setBounds(new Rectangle(228, 105, 170, 19));
        this.add(txtNumber, new KDLayout.Constraints(228, 105, 170, 19, 0));
        //kDLabelContainer2
        kDLabelContainer2.setBoundEditor(txtName);
        //kDLabelContainer4
        kDLabelContainer4.setBoundEditor(txtRemark);
        //paneBIZLayerControl9
        paneBIZLayerControl9.add(pnlContract, resHelper.getString("pnlContract.constraints"));
        paneBIZLayerControl9.add(pnlSubject, resHelper.getString("pnlSubject.constraints"));
        //pnlContract
pnlContract.getContentPane().setLayout(new BorderLayout(0, 0));        pnlContract.getContentPane().add(kdtEntires, BorderLayout.CENTER);
        //pnlSubject
pnlSubject.setLayout(new BorderLayout(0, 0));        pnlSubject.add(kdtCosetEntries, BorderLayout.CENTER);

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
        this.menuBar.add(menuHelp);
        //menuFile
        menuFile.add(menuItemAddNew);
        menuFile.add(kDSeparator1);
        menuFile.add(menuItemSave);
        menuFile.add(menuItemSubmit);
        menuFile.add(menuSubmitOption);
        menuFile.add(rMenuItemSubmit);
        menuFile.add(rMenuItemSubmitAndAddNew);
        menuFile.add(rMenuItemSubmitAndPrint);
        menuFile.add(separatorFile1);
        menuFile.add(MenuItemAttachment);
        menuFile.add(kDSeparator2);
        menuFile.add(menuItemPageSetup);
        menuFile.add(menuItemPrint);
        menuFile.add(menuItemPrintPreview);
        menuFile.add(kDSeparator3);
        menuFile.add(menuItemExitCurrent);
        //menuSubmitOption
        menuSubmitOption.add(chkMenuItemSubmitAndAddNew);
        menuSubmitOption.add(chkMenuItemSubmitAndPrint);
        //menuEdit
        menuEdit.add(menuItemCopy);
        menuEdit.add(menuItemEdit);
        menuEdit.add(menuItemRemove);
        menuEdit.add(kDSeparator4);
        //menuView
        menuView.add(menuItemFirst);
        menuView.add(menuItemPre);
        menuView.add(menuItemNext);
        menuView.add(menuItemLast);
        menuView.add(menuItemRefresh);
        //menuBiz
        menuBiz.add(menuItemCancelCancel);
        menuBiz.add(menuItemCancel);
        //menuTool
        menuTool.add(menuItemMsgFormat);
        menuTool.add(menuItemSendMessage);
        menuTool.add(menuItemCalculator);
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
        this.toolBar.add(btnEdit);
        this.toolBar.add(btnReset);
        this.toolBar.add(btnSave);
        this.toolBar.add(btnSubmit);
        this.toolBar.add(btnRefresh);
        this.toolBar.add(btnCopy);
        this.toolBar.add(btnRemove);
        this.toolBar.add(btnAttachment);
        this.toolBar.add(separatorFW1);
        this.toolBar.add(btnPageSetup);
        this.toolBar.add(btnPrint);
        this.toolBar.add(btnPrintPreview);
        this.toolBar.add(separatorFW2);
        this.toolBar.add(btnFirst);
        this.toolBar.add(btnPre);
        this.toolBar.add(btnNext);
        this.toolBar.add(btnLast);
        this.toolBar.add(separatorFW3);
        this.toolBar.add(btnCancelCancel);
        this.toolBar.add(btnCancel);

    }

	//Regiester control's property binding.
	private void registerBindings(){
		dataBinder.registerBinding("name", String.class, this.txtName, "text");
		dataBinder.registerBinding("description", String.class, this.txtRemark, "text");
		dataBinder.registerBinding("Entires", com.kingdee.eas.fdc.contract.programming.ProgrammingTemplateEntireInfo.class, this.kdtEntires, "userObject");
		dataBinder.registerBinding("Entires.number", String.class, this.kdtEntires, "number.text");
		dataBinder.registerBinding("Entires.name", String.class, this.kdtEntires, "name.text");
		dataBinder.registerBinding("Entires.description", String.class, this.kdtEntires, "remark.text");
		dataBinder.registerBinding("Entires.id", com.kingdee.bos.util.BOSUuid.class, this.kdtEntires, "id.text");
		dataBinder.registerBinding("Entires.level", int.class, this.kdtEntires, "level.text");
		dataBinder.registerBinding("Entires.longNumber", String.class, this.kdtEntires, "longNumber.text");
		dataBinder.registerBinding("Entires.head.longNumber", String.class, this.kdtEntires, "headNumber.text");
		dataBinder.registerBinding("Entires.attachment", String.class, this.kdtEntires, "attachment.text");
		dataBinder.registerBinding("Entires.displayName", String.class, this.kdtEntires, "longName.text");
		dataBinder.registerBinding("Entires.sortNumber", int.class, this.kdtEntires, "sortNumber.text");
		dataBinder.registerBinding("number", String.class, this.txtNumber, "text");		
	}
	//Regiester UI State
	private void registerUIState(){		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.fdc.contract.programming.app.ProgrammingTemplateEditUIHandler";
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
     * output onShow method
     */
    public void onShow() throws Exception
    {
        super.onShow();
        this.kdtEntires.requestFocusInWindow();
    }

	
	

    /**
     * output setDataObject method
     */
    public void setDataObject(IObjectValue dataObject)
    {
        IObjectValue ov = dataObject;        	    	
        super.setDataObject(ov);
        this.editData = (com.kingdee.eas.fdc.contract.programming.ProgrammingTemplateInfo)ov;
    }
    protected void removeByPK(IObjectPK pk) throws Exception {
    	IObjectValue editData = this.editData;
    	super.removeByPK(pk);
    	recycleNumberByOrg(editData,"NONE",editData.getString("number"));
    }
    
    protected void recycleNumberByOrg(IObjectValue editData,String orgType,String number) {
        if (!StringUtils.isEmpty(number))
        {
            try {
            	String companyID = null;            
            	com.kingdee.eas.base.codingrule.ICodingRuleManager iCodingRuleManager = com.kingdee.eas.base.codingrule.CodingRuleManagerFactory.getRemoteInstance();
				if(!com.kingdee.util.StringUtils.isEmpty(orgType) && !"NONE".equalsIgnoreCase(orgType) && com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum(orgType))!=null) {
					companyID =com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum(orgType)).getString("id");
				}
				else if (com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit() != null) {
					companyID = ((com.kingdee.eas.basedata.org.OrgUnitInfo)com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit()).getString("id");
            	}				
				if (!StringUtils.isEmpty(companyID) && iCodingRuleManager.isExist(editData, companyID) && iCodingRuleManager.isUseIntermitNumber(editData, companyID)) {
					iCodingRuleManager.recycleNumber(editData,companyID,number);					
				}
            }
            catch (Exception e)
            {
                handUIException(e);
            }
        }
    }
    protected void setAutoNumberByOrg(String orgType) {
		if (editData.getNumber() == null) {
            try {
            	String companyID = null;
				if(!com.kingdee.util.StringUtils.isEmpty(orgType) && !"NONE".equalsIgnoreCase(orgType) && com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum(orgType))!=null) {
					companyID = com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum(orgType)).getString("id");
				}
				else if (com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit() != null) {
					companyID = ((com.kingdee.eas.basedata.org.OrgUnitInfo)com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit()).getString("id");
            	}
				com.kingdee.eas.base.codingrule.ICodingRuleManager iCodingRuleManager = com.kingdee.eas.base.codingrule.CodingRuleManagerFactory.getRemoteInstance();
		        if (iCodingRuleManager.isExist(editData, companyID)) {
		            if (iCodingRuleManager.isAddView(editData, companyID)) {
		            	editData.setNumber(iCodingRuleManager.getNumber(editData,companyID));
		            }
	                txtNumber.setEnabled(false);
		        }
            }
            catch (Exception e) {
                handUIException(e);
                this.oldData = editData;
                com.kingdee.eas.util.SysUtil.abort();
            } 
        } 
        else {
            if (editData.getNumber().trim().length() > 0) {
                txtNumber.setText(editData.getNumber());
            }
        }
    }

    /**
     * output loadFields method
     */
    public void loadFields()
    {
        		setAutoNumberByOrg("NONE");
        dataBinder.loadFields();
    }
		protected void setOrgF7(KDBizPromptBox f7,com.kingdee.eas.basedata.org.OrgType orgType) throws Exception
		{
			com.kingdee.bos.ctrl.extendcontrols.ext.OrgUnitFilterInfoProducer oufip=(com.kingdee.bos.ctrl.extendcontrols.ext.OrgUnitFilterInfoProducer)com.kingdee.bos.ctrl.extendcontrols.ext.FilterInfoProducerFactory.getOrgUnitFilterInfoProducer(orgType);
			oufip.getModel().setIsCUFilter(true);
			f7.setFilterInfoProducer(oufip);
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
		getValidateHelper().registerBindProperty("name", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("description", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Entires", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Entires.number", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Entires.name", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Entires.description", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Entires.id", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Entires.level", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Entires.longNumber", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Entires.head.longNumber", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Entires.attachment", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Entires.displayName", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Entires.sortNumber", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("number", ValidateHelper.ON_SAVE);    		
	}



    /**
     * output setOprtState method
     */
    public void setOprtState(String oprtType)
    {
        super.setOprtState(oprtType);
        if (STATUS_ADDNEW.equals(this.oprtState)) {
        } else if (STATUS_EDIT.equals(this.oprtState)) {
        } else if (STATUS_VIEW.equals(this.oprtState)) {
        }
    }

    /**
     * output pnlBizLayer_stateChanged method
     */
    protected void pnlBizLayer_stateChanged(javax.swing.event.ChangeEvent e) throws Exception
    {
    }

    /**
     * output kdtEntires_tableClicked method
     */
    protected void kdtEntires_tableClicked(com.kingdee.bos.ctrl.kdf.table.event.KDTMouseEvent e) throws Exception
    {
    }

    /**
     * output kdtEntires_editStopped method
     */
    protected void kdtEntires_editStopped(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) throws Exception
    {
    }

    /**
     * output kdtEntires_editStarting method
     */
    protected void kdtEntires_editStarting(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) throws Exception
    {
    }

    /**
     * output kdtEntires_editStarted method
     */
    protected void kdtEntires_editStarted(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) throws Exception
    {
    }

    /**
     * output kdtEntires_activeCellChanged method
     */
    protected void kdtEntires_activeCellChanged(com.kingdee.bos.ctrl.kdf.table.event.KDTActiveCellEvent e) throws Exception
    {
    }

    /**
     * output getSelectors method
     */
    public SelectorItemCollection getSelectors()
    {
        SelectorItemCollection sic = new SelectorItemCollection();
        sic.add(new SelectorItemInfo("name"));
        sic.add(new SelectorItemInfo("description"));
        sic.add(new SelectorItemInfo("Entires.*"));
//        sic.add(new SelectorItemInfo("Entires.number"));
    sic.add(new SelectorItemInfo("Entires.number"));
    sic.add(new SelectorItemInfo("Entires.name"));
    sic.add(new SelectorItemInfo("Entires.description"));
    sic.add(new SelectorItemInfo("Entires.id"));
    sic.add(new SelectorItemInfo("Entires.level"));
    sic.add(new SelectorItemInfo("Entires.longNumber"));
    sic.add(new SelectorItemInfo("Entires.head.longNumber"));
    sic.add(new SelectorItemInfo("Entires.attachment"));
    sic.add(new SelectorItemInfo("Entires.displayName"));
    sic.add(new SelectorItemInfo("Entires.sortNumber"));
        sic.add(new SelectorItemInfo("number"));
        return sic;
    }        
    	

    /**
     * output actionRefresh_actionPerformed method
     */
    public void actionRefresh_actionPerformed(ActionEvent e) throws Exception
    {
    }

    /**
     * output ActionRefresh class
     */     
    protected class ActionRefresh extends ItemAction {     
    
        public ActionRefresh()
        {
            this(null);
        }

        public ActionRefresh(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionRefresh.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionRefresh.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionRefresh.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractProgrammingTemplateEditUI.this, "ActionRefresh", "actionRefresh_actionPerformed", e);
        }
    }

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.fdc.contract.programming.client", "ProgrammingTemplateEditUI");
    }

    /**
     * output getEditUIName method
     */
    protected String getEditUIName()
    {
        return com.kingdee.eas.fdc.contract.programming.client.ProgrammingTemplateEditUI.class.getName();
    }

    /**
     * output getBizInterface method
     */
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return com.kingdee.eas.fdc.contract.programming.ProgrammingTemplateFactory.getRemoteInstance();
    }

    /**
     * output createNewData method
     */
    protected IObjectValue createNewData()
    {
        com.kingdee.eas.fdc.contract.programming.ProgrammingTemplateInfo objectValue = new com.kingdee.eas.fdc.contract.programming.ProgrammingTemplateInfo();
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));		
        return objectValue;
    }



    /**
     * output getDetailTable method
     */
    protected KDTable getDetailTable() {
        return kdtEntires;
	}
    /**
     * output applyDefaultValue method
     */
    protected void applyDefaultValue(IObjectValue vo) {        
    }        
	protected void setFieldsNull(com.kingdee.bos.dao.AbstractObjectValue arg0) {
		super.setFieldsNull(arg0);
		arg0.put("number",null);
	}

}