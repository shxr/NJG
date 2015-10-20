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
public abstract class AbstractNodeMeasureFullListUI extends com.kingdee.eas.fdc.basedata.client.FDCBillListUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractNodeMeasureFullListUI.class);
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer conProject;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtProject;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer conContract;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtContract;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer conAmount;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtAmount;
    /**
     * output class constructor
     */
    public AbstractNodeMeasureFullListUI() throws Exception
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
        this.resHelper = new ResourceBundleHelper(AbstractNodeMeasureFullListUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        mainQueryPK = new MetaDataPK("com.kingdee.eas.fdc.contract.app", "NodeMeasureQuery");
        this.conProject = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.prmtProject = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.conContract = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.prmtContract = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.conAmount = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.txtAmount = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.conProject.setName("conProject");
        this.prmtProject.setName("prmtProject");
        this.conContract.setName("conContract");
        this.prmtContract.setName("prmtContract");
        this.conAmount.setName("conAmount");
        this.txtAmount.setName("txtAmount");
        // CoreUI
		String tblMainStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol2\"><c:Alignment horizontal=\"right\" /></c:Style><c:Style id=\"sCol3\"><c:Alignment horizontal=\"right\" /></c:Style><c:Style id=\"sCol4\"><c:Alignment horizontal=\"right\" /></c:Style><c:Style id=\"sCol5\"><c:Alignment horizontal=\"right\" /></c:Style><c:Style id=\"sCol6\"><c:Alignment horizontal=\"right\" /></c:Style><c:Style id=\"sCol7\"><c:Alignment horizontal=\"right\" /></c:Style><c:Style id=\"sCol8\"><c:Alignment horizontal=\"right\" /></c:Style><c:Style id=\"sCol9\"><c:Alignment horizontal=\"right\" /></c:Style><c:Style id=\"sCol10\"><c:Alignment horizontal=\"right\" /></c:Style><c:Style id=\"sCol11\"><c:Alignment horizontal=\"right\" /></c:Style><c:Style id=\"sCol12\"><c:Alignment horizontal=\"right\" /></c:Style><c:Style id=\"sCol13\"><c:Alignment horizontal=\"right\" /></c:Style><c:Style id=\"sCol14\"><c:Alignment horizontal=\"right\" /></c:Style><c:Style id=\"sCol15\"><c:Alignment horizontal=\"right\" /></c:Style><c:Style id=\"sCol16\"><c:Alignment horizontal=\"right\" /></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"accountName\" t:width=\"120\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"true\" t:required=\"false\" /><t:Column t:key=\"unit\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" /><t:Column t:key=\"conTotal\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:styleID=\"sCol2\" /><t:Column t:key=\"conPrice\" t:width=\"40\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:styleID=\"sCol3\" /><t:Column t:key=\"conValue\" t:width=\"60\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:styleID=\"sCol4\" /><t:Column t:key=\"changeTotal\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:styleID=\"sCol5\" /><t:Column t:key=\"changePrice\" t:width=\"40\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:styleID=\"sCol6\" /><t:Column t:key=\"changeValue\" t:width=\"60\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:styleID=\"sCol7\" /><t:Column t:key=\"settleTotal\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:styleID=\"sCol8\" /><t:Column t:key=\"settlePrice\" t:width=\"40\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:styleID=\"sCol9\" /><t:Column t:key=\"settleValue\" t:width=\"60\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:styleID=\"sCol10\" /><t:Column t:key=\"nodeTotal\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:styleID=\"sCol11\" /><t:Column t:key=\"nodePrice\" t:width=\"80\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:styleID=\"sCol12\" /><t:Column t:key=\"nodeValue\" t:width=\"120\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:styleID=\"sCol13\" /><t:Column t:key=\"payTotal\" t:width=\"60\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:styleID=\"sCol14\" /><t:Column t:key=\"payPrice\" t:width=\"40\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:styleID=\"sCol15\" /><t:Column t:key=\"payValue\" t:width=\"40\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:styleID=\"sCol16\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header1\" t:height=\"19\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{accountName}</t:Cell><t:Cell>$Resource{unit}</t:Cell><t:Cell>$Resource{conTotal}</t:Cell><t:Cell>$Resource{conPrice}</t:Cell><t:Cell>$Resource{conValue}</t:Cell><t:Cell>$Resource{changeTotal}</t:Cell><t:Cell>$Resource{changePrice}</t:Cell><t:Cell>$Resource{changeValue}</t:Cell><t:Cell>$Resource{settleTotal}</t:Cell><t:Cell>$Resource{settlePrice}</t:Cell><t:Cell>$Resource{settleValue}</t:Cell><t:Cell>$Resource{nodeTotal}</t:Cell><t:Cell>$Resource{nodePrice}</t:Cell><t:Cell>$Resource{nodeValue}</t:Cell><t:Cell>$Resource{payTotal}</t:Cell><t:Cell>$Resource{payPrice}</t:Cell><t:Cell>$Resource{payValue}</t:Cell></t:Row><t:Row t:name=\"header2\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{accountName_Row2}</t:Cell><t:Cell>$Resource{unit_Row2}</t:Cell><t:Cell>$Resource{conTotal_Row2}</t:Cell><t:Cell>$Resource{conPrice_Row2}</t:Cell><t:Cell>$Resource{conValue_Row2}</t:Cell><t:Cell>$Resource{changeTotal_Row2}</t:Cell><t:Cell>$Resource{changePrice_Row2}</t:Cell><t:Cell>$Resource{changeValue_Row2}</t:Cell><t:Cell>$Resource{settleTotal_Row2}</t:Cell><t:Cell>$Resource{settlePrice_Row2}</t:Cell><t:Cell>$Resource{settleValue_Row2}</t:Cell><t:Cell>$Resource{nodeTotal_Row2}</t:Cell><t:Cell>$Resource{nodePrice_Row2}</t:Cell><t:Cell>$Resource{nodeValue_Row2}</t:Cell><t:Cell>$Resource{payTotal_Row2}</t:Cell><t:Cell>$Resource{payPrice_Row2}</t:Cell><t:Cell>$Resource{payValue_Row2}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head><t:Block t:top=\"0\" t:left=\"0\" t:bottom=\"1\" t:right=\"0\" /><t:Block t:top=\"0\" t:left=\"1\" t:bottom=\"1\" t:right=\"1\" /><t:Block t:top=\"0\" t:left=\"2\" t:bottom=\"0\" t:right=\"4\" /><t:Block t:top=\"0\" t:left=\"5\" t:bottom=\"0\" t:right=\"7\" /><t:Block t:top=\"0\" t:left=\"8\" t:bottom=\"0\" t:right=\"10\" /><t:Block t:top=\"0\" t:left=\"11\" t:bottom=\"0\" t:right=\"13\" /><t:Block t:top=\"0\" t:left=\"14\" t:bottom=\"0\" t:right=\"16\" /></t:Head></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot> ";
		
        this.tblMain.setFormatXml(resHelper.translateString("tblMain",tblMainStrXML));
                this.tblMain.putBindContents("mainQuery",new String[] {"","","","","","","","","","","","","","","","",""});


        // conProject		
        this.conProject.setBoundLabelText(resHelper.getString("conProject.boundLabelText"));		
        this.conProject.setBoundLabelLength(100);		
        this.conProject.setBoundLabelUnderline(true);		
        this.conProject.setEnabled(false);
        // prmtProject		
        this.prmtProject.setEnabled(false);		
        this.prmtProject.setCommitFormat("$number$");		
        this.prmtProject.setDisplayFormat("$name$");		
        this.prmtProject.setEditFormat("$number$");
        // conContract		
        this.conContract.setBoundLabelText(resHelper.getString("conContract.boundLabelText"));		
        this.conContract.setBoundLabelLength(100);		
        this.conContract.setBoundLabelUnderline(true);
        // prmtContract		
        this.prmtContract.setDisplayFormat("$name$");		
        this.prmtContract.setEditFormat("$number$");		
        this.prmtContract.setCommitFormat("$number$");		
        this.prmtContract.setEnabled(false);
        // conAmount		
        this.conAmount.setBoundLabelText(resHelper.getString("conAmount.boundLabelText"));		
        this.conAmount.setEnabled(false);		
        this.conAmount.setBoundLabelUnderline(true);		
        this.conAmount.setBoundLabelLength(100);
        // txtAmount		
        this.txtAmount.setDataType(1);		
        this.txtAmount.setEnabled(false);
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
        tblMain.setBounds(new Rectangle(7, 39, 996, 580));
        this.add(tblMain, new KDLayout.Constraints(7, 39, 996, 580, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        conProject.setBounds(new Rectangle(11, 12, 320, 19));
        this.add(conProject, new KDLayout.Constraints(11, 12, 320, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        conContract.setBounds(new Rectangle(346, 12, 320, 19));
        this.add(conContract, new KDLayout.Constraints(346, 12, 320, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        conAmount.setBounds(new Rectangle(683, 12, 320, 19));
        this.add(conAmount, new KDLayout.Constraints(683, 12, 320, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        //conProject
        conProject.setBoundEditor(prmtProject);
        //conContract
        conContract.setBoundEditor(prmtContract);
        //conAmount
        conAmount.setBoundEditor(txtAmount);

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
        //menuView
        menuView.add(menuItemView);
        menuView.add(menuItemLocate);
        menuView.add(kDSeparator5);
        menuView.add(menuItemQuery);
        menuView.add(menuItemRefresh);
        menuView.add(separatorView1);
        menuView.add(menuItemTraceUp);
        menuView.add(menuItemTraceDown);
        menuView.add(kDSeparator6);
        //menuBiz
        menuBiz.add(menuItemCancelCancel);
        menuBiz.add(menuItemCancel);
        menuBiz.add(menuItemVoucher);
        menuBiz.add(menuItemDelVoucher);
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
        this.toolBar.add(btnQuery);
        this.toolBar.add(btnLocate);
        this.toolBar.add(btnAttachment);
        this.toolBar.add(separatorFW1);
        this.toolBar.add(btnPageSetup);
        this.toolBar.add(btnPrint);
        this.toolBar.add(btnPrintPreview);
        this.toolBar.add(separatorFW2);
        this.toolBar.add(btnCreateTo);
        this.toolBar.add(btnCopyTo);
        this.toolBar.add(btnQueryScheme);
        this.toolBar.add(separatorFW3);
        this.toolBar.add(btnTraceUp);
        this.toolBar.add(btnTraceDown);
        this.toolBar.add(btnWorkFlowG);
        this.toolBar.add(btnWorkFlowList);
        this.toolBar.add(btnSignature);
        this.toolBar.add(btnViewSignature);
        this.toolBar.add(separatorFW4);
        this.toolBar.add(btnVoucher);
        this.toolBar.add(btnDelVoucher);
        this.toolBar.add(btnMultiapprove);
        this.toolBar.add(btnNextPerson);
        this.toolBar.add(btnAuditResult);
        this.toolBar.add(btnCancel);
        this.toolBar.add(btnCancelCancel);
        this.toolBar.add(btnWFViewdoProccess);


    }

	//Regiester control's property binding.
	private void registerBindings(){		
	}
	//Regiester UI State
	private void registerUIState(){		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.fdc.contract.app.NodeMeasureFullListUIHandler";
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
     * output getSelectors method
     */
    public SelectorItemCollection getSelectors()
    {
        SelectorItemCollection sic = new SelectorItemCollection();
        return sic;
    }        

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.fdc.contract.client", "NodeMeasureFullListUI");
    }




}