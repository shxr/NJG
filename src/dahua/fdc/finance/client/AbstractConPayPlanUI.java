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
public abstract class AbstractConPayPlanUI extends com.kingdee.eas.framework.client.EditUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractConPayPlanUI.class);
    protected com.kingdee.bos.ctrl.swing.KDContainer contProgramming;
    protected com.kingdee.bos.ctrl.swing.KDRadioButton btnBySchedule;
    protected com.kingdee.bos.ctrl.swing.KDRadioButton btnByMonth;
    protected com.kingdee.bos.ctrl.swing.KDButtonGroup kDButtonGroup1;
    protected com.kingdee.bos.ctrl.swing.KDContainer contPayPlanLst;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable tblBySchedule;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable tblByMonth;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable tblPayPlanLst;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnByBuilding;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnByMonths;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnImportPayPlan;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnMonthSettle;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnContractFullInfo;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnContractView;
    protected com.kingdee.eas.fdc.finance.ConPayPlanInfo editData = null;
    protected ActionByBuilding actionByBuilding = null;
    protected ActionByMonth actionByMonth = null;
    protected ActionImportPayPlan actionImportPayPlan = null;
    protected ActionMonthSettle actionMonthSettle = null;
    protected ActionContractFullInfo actionContractFullInfo = null;
    protected ActionContractView actionContractView = null;
    /**
     * output class constructor
     */
    public AbstractConPayPlanUI() throws Exception
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
        this.resHelper = new ResourceBundleHelper(AbstractConPayPlanUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        //actionSubmit
        String _tempStr = null;
        actionSubmit.setEnabled(true);
        actionSubmit.setDaemonRun(false);

        _tempStr = resHelper.getString("ActionSubmit.SHORT_DESCRIPTION");
        actionSubmit.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionSubmit.LONG_DESCRIPTION");
        actionSubmit.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionSubmit.NAME");
        actionSubmit.putValue(ItemAction.NAME, _tempStr);
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionAttachment
        actionAttachment.setEnabled(false);
        actionAttachment.setDaemonRun(false);

        _tempStr = resHelper.getString("ActionAttachment.SHORT_DESCRIPTION");
        actionAttachment.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionAttachment.LONG_DESCRIPTION");
        actionAttachment.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionAttachment.NAME");
        actionAttachment.putValue(ItemAction.NAME, _tempStr);
         this.actionAttachment.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionByBuilding
        this.actionByBuilding = new ActionByBuilding(this);
        getActionManager().registerAction("actionByBuilding", actionByBuilding);
         this.actionByBuilding.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionByMonth
        this.actionByMonth = new ActionByMonth(this);
        getActionManager().registerAction("actionByMonth", actionByMonth);
         this.actionByMonth.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionImportPayPlan
        this.actionImportPayPlan = new ActionImportPayPlan(this);
        getActionManager().registerAction("actionImportPayPlan", actionImportPayPlan);
         this.actionImportPayPlan.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionMonthSettle
        this.actionMonthSettle = new ActionMonthSettle(this);
        getActionManager().registerAction("actionMonthSettle", actionMonthSettle);
         this.actionMonthSettle.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionContractFullInfo
        this.actionContractFullInfo = new ActionContractFullInfo(this);
        getActionManager().registerAction("actionContractFullInfo", actionContractFullInfo);
         this.actionContractFullInfo.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionContractView
        this.actionContractView = new ActionContractView(this);
        getActionManager().registerAction("actionContractView", actionContractView);
         this.actionContractView.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        this.contProgramming = new com.kingdee.bos.ctrl.swing.KDContainer();
        this.btnBySchedule = new com.kingdee.bos.ctrl.swing.KDRadioButton();
        this.btnByMonth = new com.kingdee.bos.ctrl.swing.KDRadioButton();
        this.kDButtonGroup1 = new com.kingdee.bos.ctrl.swing.KDButtonGroup();
        this.contPayPlanLst = new com.kingdee.bos.ctrl.swing.KDContainer();
        this.tblBySchedule = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.tblByMonth = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.tblPayPlanLst = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.btnByBuilding = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnByMonths = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnImportPayPlan = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnMonthSettle = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnContractFullInfo = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnContractView = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.contProgramming.setName("contProgramming");
        this.btnBySchedule.setName("btnBySchedule");
        this.btnByMonth.setName("btnByMonth");
        this.contPayPlanLst.setName("contPayPlanLst");
        this.tblBySchedule.setName("tblBySchedule");
        this.tblByMonth.setName("tblByMonth");
        this.tblPayPlanLst.setName("tblPayPlanLst");
        this.btnByBuilding.setName("btnByBuilding");
        this.btnByMonths.setName("btnByMonths");
        this.btnImportPayPlan.setName("btnImportPayPlan");
        this.btnMonthSettle.setName("btnMonthSettle");
        this.btnContractFullInfo.setName("btnContractFullInfo");
        this.btnContractView.setName("btnContractView");
        // CoreUI		
        this.btnAddNew.setVisible(false);		
        this.btnSave.setVisible(false);		
        this.btnCopy.setVisible(false);		
        this.btnRemove.setVisible(false);		
        this.btnCancelCancel.setVisible(false);		
        this.btnCancel.setVisible(false);		
        this.btnFirst.setVisible(false);		
        this.btnPre.setVisible(false);		
        this.btnNext.setVisible(false);		
        this.btnLast.setVisible(false);		
        this.btnPrint.setVisible(false);		
        this.btnPrintPreview.setVisible(false);
        this.btnAttachment.setAction((IItemAction)ActionProxyFactory.getProxy(actionAttachment, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnAttachment.setText(resHelper.getString("btnAttachment.text"));		
        this.btnAttachment.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_affixmanage"));		
        this.btnAttachment.setVisible(false);
        // contProgramming		
        this.contProgramming.setTitle(resHelper.getString("contProgramming.title"));
        // btnBySchedule		
        this.btnBySchedule.setText(resHelper.getString("btnBySchedule.text"));		
        this.btnBySchedule.setVisible(false);
        this.btnBySchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                beforeActionPerformed(e);
                try {
                    btnBySchedule_actionPerformed(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                    afterActionPerformed(e);
                }
            }
        });
        // btnByMonth		
        this.btnByMonth.setText(resHelper.getString("btnByMonth.text"));		
        this.btnByMonth.setVisible(false);
        this.btnByMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                beforeActionPerformed(e);
                try {
                    btnByMonth_actionPerformed(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                    afterActionPerformed(e);
                }
            }
        });
        // kDButtonGroup1
        this.kDButtonGroup1.add(this.btnBySchedule);
        this.kDButtonGroup1.add(this.btnByMonth);
        // contPayPlanLst		
        this.contPayPlanLst.setTitle(resHelper.getString("contPayPlanLst.title"));
        // tblBySchedule
		String tblByScheduleStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol1\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol4\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol5\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol6\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol10\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol11\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol12\"><c:NumberFormat>&amp;int</c:NumberFormat></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"id\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"0\" t:styleID=\"sCol0\" /><t:Column t:key=\"seq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"1\" t:styleID=\"sCol1\" /><t:Column t:key=\"paymentType\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"2\" /><t:Column t:key=\"task\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"3\" /><t:Column t:key=\"costAccount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"4\" t:styleID=\"sCol4\" /><t:Column t:key=\"taskMeasureAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"5\" t:styleID=\"sCol5\" /><t:Column t:key=\"taskSettleAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"6\" t:styleID=\"sCol6\" /><t:Column t:key=\"calType\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"7\" /><t:Column t:key=\"beginDate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"8\" /><t:Column t:key=\"endDate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"9\" /><t:Column t:key=\"payScale\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"10\" t:styleID=\"sCol10\" /><t:Column t:key=\"calStandard\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"11\" t:styleID=\"sCol11\" /><t:Column t:key=\"delayDay\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"12\" t:styleID=\"sCol12\" /><t:Column t:key=\"planPayDate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"13\" /><t:Column t:key=\"writeOffType\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"14\" /><t:Column t:key=\"planPayAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"15\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{id}</t:Cell><t:Cell>$Resource{seq}</t:Cell><t:Cell>$Resource{paymentType}</t:Cell><t:Cell>$Resource{task}</t:Cell><t:Cell>$Resource{costAccount}</t:Cell><t:Cell>$Resource{taskMeasureAmount}</t:Cell><t:Cell>$Resource{taskSettleAmount}</t:Cell><t:Cell>$Resource{calType}</t:Cell><t:Cell>$Resource{beginDate}</t:Cell><t:Cell>$Resource{endDate}</t:Cell><t:Cell>$Resource{payScale}</t:Cell><t:Cell>$Resource{calStandard}</t:Cell><t:Cell>$Resource{delayDay}</t:Cell><t:Cell>$Resource{planPayDate}</t:Cell><t:Cell>$Resource{writeOffType}</t:Cell><t:Cell>$Resource{planPayAmount}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot> ";
		
        this.tblBySchedule.setFormatXml(resHelper.translateString("tblBySchedule",tblByScheduleStrXML));

                this.tblBySchedule.putBindContents("editData",new String[] {"id","seq","paymentType","","costAccount","taskMeasureAmount","taskSettleAmount","calType","beginDate","endDate","payScale","calStandard","delayDay","planPayDate","writeOffType","planPayAmount"});


        // tblByMonth
		String tblByMonthStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol1\"><c:Protection hidden=\"true\" /></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"id\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"0\" t:configured=\"false\" t:styleID=\"sCol0\" /><t:Column t:key=\"seq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"1\" t:configured=\"false\" t:styleID=\"sCol1\" /><t:Column t:key=\"paymentItem\" t:width=\"150\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"2\" t:configured=\"false\" /><t:Column t:key=\"payAmount\" t:width=\"150\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"3\" t:configured=\"false\" /><t:Column t:key=\"beginDate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"4\" t:configured=\"false\" /><t:Column t:key=\"endDate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"5\" t:configured=\"false\" /><t:Column t:key=\"costAccount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"6\" t:configured=\"false\" /><t:Column t:key=\"usage\" t:width=\"250\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"7\" t:configured=\"false\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:configured=\"false\"><t:Cell>$Resource{id}</t:Cell><t:Cell>$Resource{seq}</t:Cell><t:Cell>$Resource{paymentItem}</t:Cell><t:Cell>$Resource{payAmount}</t:Cell><t:Cell>$Resource{beginDate}</t:Cell><t:Cell>$Resource{endDate}</t:Cell><t:Cell>$Resource{costAccount}</t:Cell><t:Cell>$Resource{usage}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.tblByMonth.setFormatXml(resHelper.translateString("tblByMonth",tblByMonthStrXML));
        this.tblByMonth.addKDTEditListener(new com.kingdee.bos.ctrl.kdf.table.event.KDTEditAdapter() {
            public void editStarting(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) {
                try {
                    tblByMonth_editStarting(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
            public void editStopped(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) {
                try {
                    tblByMonth_editStopped(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });

        

        // tblPayPlanLst
		String tblPayPlanLstStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles /><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup /><t:Head /></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.tblPayPlanLst.setFormatXml(resHelper.translateString("tblPayPlanLst",tblPayPlanLstStrXML));

        

        // btnByBuilding
        this.btnByBuilding.setAction((IItemAction)ActionProxyFactory.getProxy(actionByBuilding, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnByBuilding.setText(resHelper.getString("btnByBuilding.text"));		
        this.btnByBuilding.setVisible(false);
        // btnByMonths
        this.btnByMonths.setAction((IItemAction)ActionProxyFactory.getProxy(actionByMonth, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnByMonths.setText(resHelper.getString("btnByMonths.text"));		
        this.btnByMonths.setVisible(false);
        // btnImportPayPlan
        this.btnImportPayPlan.setAction((IItemAction)ActionProxyFactory.getProxy(actionImportPayPlan, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnImportPayPlan.setText(resHelper.getString("btnImportPayPlan.text"));		
        this.btnImportPayPlan.setVisible(false);
        // btnMonthSettle
        this.btnMonthSettle.setAction((IItemAction)ActionProxyFactory.getProxy(actionMonthSettle, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnMonthSettle.setText(resHelper.getString("btnMonthSettle.text"));		
        this.btnMonthSettle.setVisible(false);
        // btnContractFullInfo
        this.btnContractFullInfo.setAction((IItemAction)ActionProxyFactory.getProxy(actionContractFullInfo, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnContractFullInfo.setText(resHelper.getString("btnContractFullInfo.text"));		
        this.btnContractFullInfo.setVisible(false);
        // btnContractView
        this.btnContractView.setAction((IItemAction)ActionProxyFactory.getProxy(actionContractView, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnContractView.setText(resHelper.getString("btnContractView.text"));		
        this.btnContractView.setVisible(false);
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
        this.setBounds(new Rectangle(10, 10, 680, 570));
        this.setLayout(new KDLayout());
        this.putClientProperty("OriginalBounds", new Rectangle(10, 10, 680, 570));
        contProgramming.setBounds(new Rectangle(6, 33, 672, 386));
        this.add(contProgramming, new KDLayout.Constraints(6, 33, 672, 386, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM_SCALE | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        btnBySchedule.setBounds(new Rectangle(44, 11, 140, 19));
        this.add(btnBySchedule, new KDLayout.Constraints(44, 11, 140, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        btnByMonth.setBounds(new Rectangle(419, 11, 140, 19));
        this.add(btnByMonth, new KDLayout.Constraints(419, 11, 140, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contPayPlanLst.setBounds(new Rectangle(5, 425, 673, 138));
        this.add(contPayPlanLst, new KDLayout.Constraints(5, 425, 673, 138, KDLayout.Constraints.ANCHOR_TOP_SCALE | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        //contProgramming
        contProgramming.getContentPane().setLayout(null);        tblBySchedule.setBounds(new Rectangle(1, 2, 322, 405));
        contProgramming.getContentPane().add(tblBySchedule, null);
        tblByMonth.setBounds(new Rectangle(326, 2, 344, 402));
        contProgramming.getContentPane().add(tblByMonth, null);
        //contPayPlanLst
contPayPlanLst.getContentPane().setLayout(new BorderLayout(0, 0));        contPayPlanLst.getContentPane().add(tblPayPlanLst, BorderLayout.CENTER);

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
        this.menuBar.add(menuHelp);
        //menuFile
        menuFile.add(menuItemAddNew);
        menuFile.add(kDSeparator1);
        menuFile.add(menuItemCloudFeed);
        menuFile.add(menuItemSave);
        menuFile.add(menuItemCloudScreen);
        menuFile.add(menuItemSubmit);
        menuFile.add(menuItemCloudShare);
        menuFile.add(menuSubmitOption);
        menuFile.add(kdSeparatorFWFile1);
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
        menuEdit.add(menuItemReset);
        //MenuService
        MenuService.add(MenuItemKnowStore);
        MenuService.add(MenuItemAnwser);
        MenuService.add(SepratorService);
        MenuService.add(MenuItemRemoteAssist);
        //menuView
        menuView.add(menuItemFirst);
        menuView.add(menuItemPre);
        menuView.add(menuItemNext);
        menuView.add(menuItemLast);
        //menuBiz
        menuBiz.add(menuItemCancelCancel);
        menuBiz.add(menuItemCancel);
        //menuTool
        menuTool.add(menuItemMsgFormat);
        menuTool.add(menuItemSendMessage);
        menuTool.add(menuItemCalculator);
        menuTool.add(menuItemToolBarCustom);
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
        this.toolBar.add(btnPageSetup);
        this.toolBar.add(btnCloud);
        this.toolBar.add(btnAddNew);
        this.toolBar.add(btnEdit);
        this.toolBar.add(kDSeparatorCloud);
        this.toolBar.add(btnAttachment);
        this.toolBar.add(btnReset);
        this.toolBar.add(btnSave);
        this.toolBar.add(btnSubmit);
        this.toolBar.add(btnCopy);
        this.toolBar.add(btnRemove);
        this.toolBar.add(separatorFW1);
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
        this.toolBar.add(btnByBuilding);
        this.toolBar.add(btnByMonths);
        this.toolBar.add(btnImportPayPlan);
        this.toolBar.add(btnMonthSettle);
        this.toolBar.add(btnContractFullInfo);
        this.toolBar.add(btnContractView);


    }

	//Regiester control's property binding.
	private void registerBindings(){
		dataBinder.registerBinding("BySchedule.id", com.kingdee.bos.util.BOSUuid.class, this.tblBySchedule, "id.text");
		dataBinder.registerBinding("BySchedule.seq", int.class, this.tblBySchedule, "seq.text");
		dataBinder.registerBinding("BySchedule", com.kingdee.eas.fdc.finance.PayPlanNewByScheduleInfo.class, this.tblBySchedule, "userObject");
		dataBinder.registerBinding("BySchedule.calType", com.kingdee.eas.fdc.finance.CalTypeEnum.class, this.tblBySchedule, "calType.text");
		dataBinder.registerBinding("BySchedule.calStandard", com.kingdee.eas.fdc.finance.CalStandardEnum.class, this.tblBySchedule, "calStandard.text");
		dataBinder.registerBinding("BySchedule.delayDay", int.class, this.tblBySchedule, "delayDay.text");
		dataBinder.registerBinding("BySchedule.writeOffType", com.kingdee.eas.fdc.finance.PrepayWriteOffEnum.class, this.tblBySchedule, "writeOffType.text");
		dataBinder.registerBinding("BySchedule.costAccount", com.kingdee.eas.fdc.basedata.CostAccountInfo.class, this.tblBySchedule, "costAccount.text");
		dataBinder.registerBinding("BySchedule.paymentType", com.kingdee.eas.basedata.assistant.PaymentTypeInfo.class, this.tblBySchedule, "paymentType.text");
		dataBinder.registerBinding("BySchedule.taskMeasureAmount", java.math.BigDecimal.class, this.tblBySchedule, "taskMeasureAmount.text");
		dataBinder.registerBinding("BySchedule.beginDate", java.util.Date.class, this.tblBySchedule, "beginDate.text");
		dataBinder.registerBinding("BySchedule.endDate", java.util.Date.class, this.tblBySchedule, "endDate.text");
		dataBinder.registerBinding("BySchedule.planPayDate", java.util.Date.class, this.tblBySchedule, "planPayDate.text");
		dataBinder.registerBinding("BySchedule.planPayAmount", java.math.BigDecimal.class, this.tblBySchedule, "planPayAmount.text");
		dataBinder.registerBinding("BySchedule.payScale", java.math.BigDecimal.class, this.tblBySchedule, "payScale.text");
		dataBinder.registerBinding("BySchedule.taskSettleAmount", java.math.BigDecimal.class, this.tblBySchedule, "taskSettleAmount.text");		
	}
	//Regiester UI State
	private void registerUIState(){		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.fdc.finance.app.ConPayPlanUIHandler";
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
        this.editData = (com.kingdee.eas.fdc.finance.ConPayPlanInfo)ov;
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
		getValidateHelper().registerBindProperty("BySchedule.id", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("BySchedule.seq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("BySchedule", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("BySchedule.calType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("BySchedule.calStandard", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("BySchedule.delayDay", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("BySchedule.writeOffType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("BySchedule.costAccount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("BySchedule.paymentType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("BySchedule.taskMeasureAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("BySchedule.beginDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("BySchedule.endDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("BySchedule.planPayDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("BySchedule.planPayAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("BySchedule.payScale", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("BySchedule.taskSettleAmount", ValidateHelper.ON_SAVE);    		
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
     * output btnBySchedule_actionPerformed method
     */
    protected void btnBySchedule_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
    }

    /**
     * output btnByMonth_actionPerformed method
     */
    protected void btnByMonth_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
    }

    /**
     * output tblByMonth_editStarting method
     */
    protected void tblByMonth_editStarting(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) throws Exception
    {
    }

    /**
     * output tblByMonth_editStopped method
     */
    protected void tblByMonth_editStopped(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) throws Exception
    {
    }

    /**
     * output getSelectors method
     */
    public SelectorItemCollection getSelectors()
    {
        SelectorItemCollection sic = new SelectorItemCollection();
		String selectorAll = System.getProperty("selector.all");
		if(StringUtils.isEmpty(selectorAll)){
			selectorAll = "true";
		}
    	sic.add(new SelectorItemInfo("BySchedule.id"));
    	sic.add(new SelectorItemInfo("BySchedule.seq"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("BySchedule.*"));
		}
		else{
		}
    	sic.add(new SelectorItemInfo("BySchedule.calType"));
    	sic.add(new SelectorItemInfo("BySchedule.calStandard"));
    	sic.add(new SelectorItemInfo("BySchedule.delayDay"));
    	sic.add(new SelectorItemInfo("BySchedule.writeOffType"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("BySchedule.costAccount.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("BySchedule.costAccount.id"));
			sic.add(new SelectorItemInfo("BySchedule.costAccount.name"));
        	sic.add(new SelectorItemInfo("BySchedule.costAccount.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("BySchedule.paymentType.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("BySchedule.paymentType.id"));
			sic.add(new SelectorItemInfo("BySchedule.paymentType.name"));
        	sic.add(new SelectorItemInfo("BySchedule.paymentType.number"));
		}
    	sic.add(new SelectorItemInfo("BySchedule.taskMeasureAmount"));
    	sic.add(new SelectorItemInfo("BySchedule.beginDate"));
    	sic.add(new SelectorItemInfo("BySchedule.endDate"));
    	sic.add(new SelectorItemInfo("BySchedule.planPayDate"));
    	sic.add(new SelectorItemInfo("BySchedule.planPayAmount"));
    	sic.add(new SelectorItemInfo("BySchedule.payScale"));
    	sic.add(new SelectorItemInfo("BySchedule.taskSettleAmount"));
        return sic;
    }        
    	

    /**
     * output actionSubmit_actionPerformed method
     */
    public void actionSubmit_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionSubmit_actionPerformed(e);
    }
    	

    /**
     * output actionAttachment_actionPerformed method
     */
    public void actionAttachment_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionAttachment_actionPerformed(e);
    }
    	

    /**
     * output actionByBuilding_actionPerformed method
     */
    public void actionByBuilding_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionByMonth_actionPerformed method
     */
    public void actionByMonth_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionImportPayPlan_actionPerformed method
     */
    public void actionImportPayPlan_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionMonthSettle_actionPerformed method
     */
    public void actionMonthSettle_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionContractFullInfo_actionPerformed method
     */
    public void actionContractFullInfo_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionContractView_actionPerformed method
     */
    public void actionContractView_actionPerformed(ActionEvent e) throws Exception
    {
    }
	public RequestContext prepareActionSubmit(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionSubmit(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionSubmit() {
    	return false;
    }
	public RequestContext prepareActionAttachment(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionAttachment(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionAttachment() {
    	return false;
    }
	public RequestContext prepareActionByBuilding(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionByBuilding() {
    	return false;
    }
	public RequestContext prepareActionByMonth(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionByMonth() {
    	return false;
    }
	public RequestContext prepareActionImportPayPlan(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionImportPayPlan() {
    	return false;
    }
	public RequestContext prepareActionMonthSettle(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionMonthSettle() {
    	return false;
    }
	public RequestContext prepareActionContractFullInfo(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionContractFullInfo() {
    	return false;
    }
	public RequestContext prepareActionContractView(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionContractView() {
    	return false;
    }

    /**
     * output ActionByBuilding class
     */     
    protected class ActionByBuilding extends ItemAction {     
    
        public ActionByBuilding()
        {
            this(null);
        }

        public ActionByBuilding(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionByBuilding.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionByBuilding.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionByBuilding.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractConPayPlanUI.this, "ActionByBuilding", "actionByBuilding_actionPerformed", e);
        }
    }

    /**
     * output ActionByMonth class
     */     
    protected class ActionByMonth extends ItemAction {     
    
        public ActionByMonth()
        {
            this(null);
        }

        public ActionByMonth(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionByMonth.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionByMonth.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionByMonth.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractConPayPlanUI.this, "ActionByMonth", "actionByMonth_actionPerformed", e);
        }
    }

    /**
     * output ActionImportPayPlan class
     */     
    protected class ActionImportPayPlan extends ItemAction {     
    
        public ActionImportPayPlan()
        {
            this(null);
        }

        public ActionImportPayPlan(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionImportPayPlan.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionImportPayPlan.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionImportPayPlan.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractConPayPlanUI.this, "ActionImportPayPlan", "actionImportPayPlan_actionPerformed", e);
        }
    }

    /**
     * output ActionMonthSettle class
     */     
    protected class ActionMonthSettle extends ItemAction {     
    
        public ActionMonthSettle()
        {
            this(null);
        }

        public ActionMonthSettle(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionMonthSettle.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionMonthSettle.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionMonthSettle.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractConPayPlanUI.this, "ActionMonthSettle", "actionMonthSettle_actionPerformed", e);
        }
    }

    /**
     * output ActionContractFullInfo class
     */     
    protected class ActionContractFullInfo extends ItemAction {     
    
        public ActionContractFullInfo()
        {
            this(null);
        }

        public ActionContractFullInfo(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionContractFullInfo.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionContractFullInfo.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionContractFullInfo.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractConPayPlanUI.this, "ActionContractFullInfo", "actionContractFullInfo_actionPerformed", e);
        }
    }

    /**
     * output ActionContractView class
     */     
    protected class ActionContractView extends ItemAction {     
    
        public ActionContractView()
        {
            this(null);
        }

        public ActionContractView(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionContractView.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionContractView.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionContractView.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractConPayPlanUI.this, "ActionContractView", "actionContractView_actionPerformed", e);
        }
    }

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.fdc.finance.client", "ConPayPlanUI");
    }




}