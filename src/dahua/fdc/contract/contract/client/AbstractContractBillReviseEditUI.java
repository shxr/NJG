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
public abstract class AbstractContractBillReviseEditUI extends com.kingdee.eas.fdc.basedata.client.FDCBillEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractContractBillReviseEditUI.class);
    protected com.kingdee.bos.ctrl.swing.KDTabbedPane kDTabbedPane2;
    protected com.kingdee.bos.ctrl.swing.KDPanel mainPanel;
    protected com.kingdee.bos.ctrl.swing.KDPanel ecoItemPanel;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCreateTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contNumber;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contamount;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contchgPercForWarn;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contpayPercForWarn;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contsignDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contlandDeveloper;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contcontractType;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contcostProperty;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contcontractPropert;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contcontractSource;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contpartB;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contpartC;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contcontractName;
    protected com.kingdee.bos.ctrl.swing.KDTabbedPane kDTabbedPane1;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chkCostSplit;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contExRate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contLocalAmount;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contGrtAmount;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCurrency;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contRespDept;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contPayScale;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contStampTaxRate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contStampTaxAmt;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contRespPerson;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCreator;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contOrg;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contProj;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contGrtRate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contOldAmount;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contOldLAmount;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contReviseReason;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer1;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer2;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chkIsPartAMaterialCon;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer lblAttachmentContainer;
    protected com.kingdee.bos.ctrl.swing.KDButton btnViewAttachment;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker kDDateCreateTime;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtNumber;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtamount;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtchgPercForWarn;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtpayPercForWarn;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pksignDate;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtlandDeveloper;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtcontractType;
    protected com.kingdee.bos.ctrl.swing.KDComboBox costProperty;
    protected com.kingdee.bos.ctrl.swing.KDComboBox contractPropert;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox contractSource;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtpartB;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtpartC;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtcontractName;
    protected com.kingdee.bos.ctrl.swing.KDPanel pnlInviteInfo;
    protected com.kingdee.bos.ctrl.swing.KDScrollPane pnlDetail;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contRemark;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCoopLevel;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contPriceType;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chkIsSubMainContract;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer conEffectiveStartDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer conEffectiveEndDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer conMainContract;
    protected com.kingdee.bos.ctrl.swing.KDScrollPane kDScrollPane2;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer conInformation;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contlowestPrice;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contlowerPrice;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contmiddlePrice;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer conthigherPrice;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer conthighestPrice;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contbasePrice;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contsecondPrice;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer continviteType;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contwinPrice;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contwinUnit;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contfileNo;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contquantity;
    protected com.kingdee.bos.ctrl.swing.KDLabel lblPrice;
    protected com.kingdee.bos.ctrl.swing.KDLabel lblUnit;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtlowestPriceUnit;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtlowerPriceUnit;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtmiddlePriceUnit;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmthigherPriceUnit;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmthighestPriceUni;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtRemark;
    protected com.kingdee.bos.ctrl.swing.KDComboBox comboCoopLevel;
    protected com.kingdee.bos.ctrl.swing.KDComboBox comboPriceType;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker kDDatePicker1;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker kdpEffectiveStartDate;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker kdpEffectiveEndDate;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtMainContract;
    protected com.kingdee.bos.ctrl.swing.KDTextArea txtInformation;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtlowestPrice;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtlowerPrice;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtmiddlePrice;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txthigherPrice;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txthighestPrice;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtbasePrice;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtsecondPrice;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtinviteType;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtwinPrice;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtwinUnit;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtfileNo;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtquantity;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable tblDetail;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtExRate;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtLocalAmount;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtGrtAmount;
    protected com.kingdee.bos.ctrl.swing.KDComboBox comboCurrency;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtRespDept;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtPayScale;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtStampTaxRate;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtStampTaxAmt;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtRespPerson;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtCreator;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtOrg;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtProj;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtGrtRate;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtOldAmount;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtOldLAmount;
    protected com.kingdee.bos.ctrl.swing.KDScrollPane kDScrollPane1;
    protected com.kingdee.bos.ctrl.swing.KDTextArea txtReviseReason;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkbookedDate;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox cbPeriod;
    protected com.kingdee.bos.ctrl.swing.KDComboBox cmbAttachment;
    protected com.kingdee.bos.ctrl.swing.KDContainer kDContainer1;
    protected com.kingdee.bos.ctrl.swing.KDSplitPane kDSplitPane1;
    protected com.kingdee.bos.ctrl.swing.KDContainer contPayItem;
    protected com.kingdee.bos.ctrl.swing.KDContainer contBailItem;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable tblEconItem;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contBailOriAmount;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contBailRate;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable tblBail;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtBailOriAmount;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtBailRate;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnSplit;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnViewContent;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnContractPlan;
    protected com.kingdee.bos.ctrl.swing.KDMenuItem menuItemViewContent;
    protected com.kingdee.bos.ctrl.swing.KDMenuItem menuItemSplit;
    protected com.kingdee.eas.fdc.contract.ContractBillReviseInfo editData = null;
    protected ActionSplit actionSplit = null;
    protected ActionViewContent actionViewContent = null;
    protected ActionContractPlan actionContractPlan = null;
    protected ActionViewAttachment actionViewAttachment = null;
    /**
     * output class constructor
     */
    public AbstractContractBillReviseEditUI() throws Exception
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
        this.resHelper = new ResourceBundleHelper(AbstractContractBillReviseEditUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        //actionSubmit
        String _tempStr = null;
        actionSubmit.setEnabled(true);
        actionSubmit.setDaemonRun(false);

        actionSubmit.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl S"));
        _tempStr = resHelper.getString("ActionSubmit.SHORT_DESCRIPTION");
        actionSubmit.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionSubmit.LONG_DESCRIPTION");
        actionSubmit.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionSubmit.NAME");
        actionSubmit.putValue(ItemAction.NAME, _tempStr);
        this.actionSubmit.setBindWorkFlow(true);
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionPrint
        actionPrint.setEnabled(true);
        actionPrint.setDaemonRun(false);

        actionPrint.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl P"));
        _tempStr = resHelper.getString("ActionPrint.SHORT_DESCRIPTION");
        actionPrint.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPrint.LONG_DESCRIPTION");
        actionPrint.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPrint.NAME");
        actionPrint.putValue(ItemAction.NAME, _tempStr);
         this.actionPrint.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionPrint.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionPrint.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionPrintPreview
        actionPrintPreview.setEnabled(true);
        actionPrintPreview.setDaemonRun(false);

        actionPrintPreview.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("shift ctrl P"));
        _tempStr = resHelper.getString("ActionPrintPreview.SHORT_DESCRIPTION");
        actionPrintPreview.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPrintPreview.LONG_DESCRIPTION");
        actionPrintPreview.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPrintPreview.NAME");
        actionPrintPreview.putValue(ItemAction.NAME, _tempStr);
         this.actionPrintPreview.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionPrintPreview.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionPrintPreview.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionAudit
        actionAudit.setEnabled(true);
        actionAudit.setDaemonRun(false);

        _tempStr = resHelper.getString("ActionAudit.SHORT_DESCRIPTION");
        actionAudit.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionAudit.LONG_DESCRIPTION");
        actionAudit.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionAudit.NAME");
        actionAudit.putValue(ItemAction.NAME, _tempStr);
        this.actionAudit.setBindWorkFlow(true);
         this.actionAudit.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionUnAudit
        actionUnAudit.setEnabled(false);
        actionUnAudit.setDaemonRun(false);

        _tempStr = resHelper.getString("ActionUnAudit.SHORT_DESCRIPTION");
        actionUnAudit.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionUnAudit.LONG_DESCRIPTION");
        actionUnAudit.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionUnAudit.NAME");
        actionUnAudit.putValue(ItemAction.NAME, _tempStr);
        this.actionUnAudit.setBindWorkFlow(true);
         this.actionUnAudit.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionSplit
        this.actionSplit = new ActionSplit(this);
        getActionManager().registerAction("actionSplit", actionSplit);
         this.actionSplit.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionViewContent
        this.actionViewContent = new ActionViewContent(this);
        getActionManager().registerAction("actionViewContent", actionViewContent);
         this.actionViewContent.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionContractPlan
        this.actionContractPlan = new ActionContractPlan(this);
        getActionManager().registerAction("actionContractPlan", actionContractPlan);
         this.actionContractPlan.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionViewAttachment
        this.actionViewAttachment = new ActionViewAttachment(this);
        getActionManager().registerAction("actionViewAttachment", actionViewAttachment);
         this.actionViewAttachment.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        this.kDTabbedPane2 = new com.kingdee.bos.ctrl.swing.KDTabbedPane();
        this.mainPanel = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.ecoItemPanel = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.contCreateTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contNumber = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contamount = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contchgPercForWarn = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contpayPercForWarn = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contsignDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contlandDeveloper = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contcontractType = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contcostProperty = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contcontractPropert = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contcontractSource = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contpartB = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contpartC = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contcontractName = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDTabbedPane1 = new com.kingdee.bos.ctrl.swing.KDTabbedPane();
        this.chkCostSplit = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.contExRate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contLocalAmount = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contGrtAmount = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contCurrency = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contRespDept = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contPayScale = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contStampTaxRate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contStampTaxAmt = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contRespPerson = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contCreator = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contOrg = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contProj = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contGrtRate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contOldAmount = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contOldLAmount = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contReviseReason = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer1 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer2 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.chkIsPartAMaterialCon = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.lblAttachmentContainer = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.btnViewAttachment = new com.kingdee.bos.ctrl.swing.KDButton();
        this.kDDateCreateTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtamount = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtchgPercForWarn = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtpayPercForWarn = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.pksignDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.prmtlandDeveloper = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtcontractType = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.costProperty = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.contractPropert = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.contractSource = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtpartB = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtpartC = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtcontractName = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.pnlInviteInfo = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.pnlDetail = new com.kingdee.bos.ctrl.swing.KDScrollPane();
        this.contRemark = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contCoopLevel = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contPriceType = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.chkIsSubMainContract = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.conEffectiveStartDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.conEffectiveEndDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.conMainContract = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDScrollPane2 = new com.kingdee.bos.ctrl.swing.KDScrollPane();
        this.conInformation = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contlowestPrice = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contlowerPrice = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contmiddlePrice = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.conthigherPrice = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.conthighestPrice = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contbasePrice = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contsecondPrice = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.continviteType = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contwinPrice = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contwinUnit = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contfileNo = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contquantity = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.lblPrice = new com.kingdee.bos.ctrl.swing.KDLabel();
        this.lblUnit = new com.kingdee.bos.ctrl.swing.KDLabel();
        this.prmtlowestPriceUnit = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtlowerPriceUnit = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtmiddlePriceUnit = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmthigherPriceUnit = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmthighestPriceUni = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtRemark = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.comboCoopLevel = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.comboPriceType = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.kDDatePicker1 = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.kdpEffectiveStartDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.kdpEffectiveEndDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.prmtMainContract = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtInformation = new com.kingdee.bos.ctrl.swing.KDTextArea();
        this.txtlowestPrice = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtlowerPrice = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtmiddlePrice = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txthigherPrice = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txthighestPrice = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtbasePrice = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtsecondPrice = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.prmtinviteType = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtwinPrice = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.prmtwinUnit = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtfileNo = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtquantity = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.tblDetail = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.txtExRate = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtLocalAmount = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtGrtAmount = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.comboCurrency = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.prmtRespDept = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtPayScale = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtStampTaxRate = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtStampTaxAmt = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.prmtRespPerson = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtCreator = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtOrg = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtProj = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtGrtRate = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtOldAmount = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtOldLAmount = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.kDScrollPane1 = new com.kingdee.bos.ctrl.swing.KDScrollPane();
        this.txtReviseReason = new com.kingdee.bos.ctrl.swing.KDTextArea();
        this.pkbookedDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.cbPeriod = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.cmbAttachment = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.kDContainer1 = new com.kingdee.bos.ctrl.swing.KDContainer();
        this.kDSplitPane1 = new com.kingdee.bos.ctrl.swing.KDSplitPane();
        this.contPayItem = new com.kingdee.bos.ctrl.swing.KDContainer();
        this.contBailItem = new com.kingdee.bos.ctrl.swing.KDContainer();
        this.tblEconItem = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.contBailOriAmount = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contBailRate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.tblBail = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.txtBailOriAmount = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtBailRate = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.btnSplit = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnViewContent = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnContractPlan = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.menuItemViewContent = new com.kingdee.bos.ctrl.swing.KDMenuItem();
        this.menuItemSplit = new com.kingdee.bos.ctrl.swing.KDMenuItem();
        this.kDTabbedPane2.setName("kDTabbedPane2");
        this.mainPanel.setName("mainPanel");
        this.ecoItemPanel.setName("ecoItemPanel");
        this.contCreateTime.setName("contCreateTime");
        this.contNumber.setName("contNumber");
        this.contamount.setName("contamount");
        this.contchgPercForWarn.setName("contchgPercForWarn");
        this.contpayPercForWarn.setName("contpayPercForWarn");
        this.contsignDate.setName("contsignDate");
        this.contlandDeveloper.setName("contlandDeveloper");
        this.contcontractType.setName("contcontractType");
        this.contcostProperty.setName("contcostProperty");
        this.contcontractPropert.setName("contcontractPropert");
        this.contcontractSource.setName("contcontractSource");
        this.contpartB.setName("contpartB");
        this.contpartC.setName("contpartC");
        this.contcontractName.setName("contcontractName");
        this.kDTabbedPane1.setName("kDTabbedPane1");
        this.chkCostSplit.setName("chkCostSplit");
        this.contExRate.setName("contExRate");
        this.contLocalAmount.setName("contLocalAmount");
        this.contGrtAmount.setName("contGrtAmount");
        this.contCurrency.setName("contCurrency");
        this.contRespDept.setName("contRespDept");
        this.contPayScale.setName("contPayScale");
        this.contStampTaxRate.setName("contStampTaxRate");
        this.contStampTaxAmt.setName("contStampTaxAmt");
        this.contRespPerson.setName("contRespPerson");
        this.contCreator.setName("contCreator");
        this.contOrg.setName("contOrg");
        this.contProj.setName("contProj");
        this.contGrtRate.setName("contGrtRate");
        this.contOldAmount.setName("contOldAmount");
        this.contOldLAmount.setName("contOldLAmount");
        this.contReviseReason.setName("contReviseReason");
        this.kDLabelContainer1.setName("kDLabelContainer1");
        this.kDLabelContainer2.setName("kDLabelContainer2");
        this.chkIsPartAMaterialCon.setName("chkIsPartAMaterialCon");
        this.lblAttachmentContainer.setName("lblAttachmentContainer");
        this.btnViewAttachment.setName("btnViewAttachment");
        this.kDDateCreateTime.setName("kDDateCreateTime");
        this.txtNumber.setName("txtNumber");
        this.txtamount.setName("txtamount");
        this.txtchgPercForWarn.setName("txtchgPercForWarn");
        this.txtpayPercForWarn.setName("txtpayPercForWarn");
        this.pksignDate.setName("pksignDate");
        this.prmtlandDeveloper.setName("prmtlandDeveloper");
        this.prmtcontractType.setName("prmtcontractType");
        this.costProperty.setName("costProperty");
        this.contractPropert.setName("contractPropert");
        this.contractSource.setName("contractSource");
        this.prmtpartB.setName("prmtpartB");
        this.prmtpartC.setName("prmtpartC");
        this.txtcontractName.setName("txtcontractName");
        this.pnlInviteInfo.setName("pnlInviteInfo");
        this.pnlDetail.setName("pnlDetail");
        this.contRemark.setName("contRemark");
        this.contCoopLevel.setName("contCoopLevel");
        this.contPriceType.setName("contPriceType");
        this.chkIsSubMainContract.setName("chkIsSubMainContract");
        this.conEffectiveStartDate.setName("conEffectiveStartDate");
        this.conEffectiveEndDate.setName("conEffectiveEndDate");
        this.conMainContract.setName("conMainContract");
        this.kDScrollPane2.setName("kDScrollPane2");
        this.conInformation.setName("conInformation");
        this.contlowestPrice.setName("contlowestPrice");
        this.contlowerPrice.setName("contlowerPrice");
        this.contmiddlePrice.setName("contmiddlePrice");
        this.conthigherPrice.setName("conthigherPrice");
        this.conthighestPrice.setName("conthighestPrice");
        this.contbasePrice.setName("contbasePrice");
        this.contsecondPrice.setName("contsecondPrice");
        this.continviteType.setName("continviteType");
        this.contwinPrice.setName("contwinPrice");
        this.contwinUnit.setName("contwinUnit");
        this.contfileNo.setName("contfileNo");
        this.contquantity.setName("contquantity");
        this.lblPrice.setName("lblPrice");
        this.lblUnit.setName("lblUnit");
        this.prmtlowestPriceUnit.setName("prmtlowestPriceUnit");
        this.prmtlowerPriceUnit.setName("prmtlowerPriceUnit");
        this.prmtmiddlePriceUnit.setName("prmtmiddlePriceUnit");
        this.prmthigherPriceUnit.setName("prmthigherPriceUnit");
        this.prmthighestPriceUni.setName("prmthighestPriceUni");
        this.txtRemark.setName("txtRemark");
        this.comboCoopLevel.setName("comboCoopLevel");
        this.comboPriceType.setName("comboPriceType");
        this.kDDatePicker1.setName("kDDatePicker1");
        this.kdpEffectiveStartDate.setName("kdpEffectiveStartDate");
        this.kdpEffectiveEndDate.setName("kdpEffectiveEndDate");
        this.prmtMainContract.setName("prmtMainContract");
        this.txtInformation.setName("txtInformation");
        this.txtlowestPrice.setName("txtlowestPrice");
        this.txtlowerPrice.setName("txtlowerPrice");
        this.txtmiddlePrice.setName("txtmiddlePrice");
        this.txthigherPrice.setName("txthigherPrice");
        this.txthighestPrice.setName("txthighestPrice");
        this.txtbasePrice.setName("txtbasePrice");
        this.txtsecondPrice.setName("txtsecondPrice");
        this.prmtinviteType.setName("prmtinviteType");
        this.txtwinPrice.setName("txtwinPrice");
        this.prmtwinUnit.setName("prmtwinUnit");
        this.txtfileNo.setName("txtfileNo");
        this.txtquantity.setName("txtquantity");
        this.tblDetail.setName("tblDetail");
        this.txtExRate.setName("txtExRate");
        this.txtLocalAmount.setName("txtLocalAmount");
        this.txtGrtAmount.setName("txtGrtAmount");
        this.comboCurrency.setName("comboCurrency");
        this.prmtRespDept.setName("prmtRespDept");
        this.txtPayScale.setName("txtPayScale");
        this.txtStampTaxRate.setName("txtStampTaxRate");
        this.txtStampTaxAmt.setName("txtStampTaxAmt");
        this.prmtRespPerson.setName("prmtRespPerson");
        this.txtCreator.setName("txtCreator");
        this.txtOrg.setName("txtOrg");
        this.txtProj.setName("txtProj");
        this.txtGrtRate.setName("txtGrtRate");
        this.txtOldAmount.setName("txtOldAmount");
        this.txtOldLAmount.setName("txtOldLAmount");
        this.kDScrollPane1.setName("kDScrollPane1");
        this.txtReviseReason.setName("txtReviseReason");
        this.pkbookedDate.setName("pkbookedDate");
        this.cbPeriod.setName("cbPeriod");
        this.cmbAttachment.setName("cmbAttachment");
        this.kDContainer1.setName("kDContainer1");
        this.kDSplitPane1.setName("kDSplitPane1");
        this.contPayItem.setName("contPayItem");
        this.contBailItem.setName("contBailItem");
        this.tblEconItem.setName("tblEconItem");
        this.contBailOriAmount.setName("contBailOriAmount");
        this.contBailRate.setName("contBailRate");
        this.tblBail.setName("tblBail");
        this.txtBailOriAmount.setName("txtBailOriAmount");
        this.txtBailRate.setName("txtBailRate");
        this.btnSplit.setName("btnSplit");
        this.btnViewContent.setName("btnViewContent");
        this.btnContractPlan.setName("btnContractPlan");
        this.menuItemViewContent.setName("menuItemViewContent");
        this.menuItemSplit.setName("menuItemSplit");
        // CoreUI		
        this.setPreferredSize(new Dimension(1013,629));		
        this.btnSubmit.setText(resHelper.getString("btnSubmit.text"));		
        this.btnSubmit.setToolTipText(resHelper.getString("btnSubmit.toolTipText"));		
        this.menuItemSubmit.setText(resHelper.getString("menuItemSubmit.text"));		
        this.menuSubmitOption.setVisible(false);		
        this.btnCreateFrom.setVisible(false);		
        this.btnCopyFrom.setVisible(false);		
        this.btnTraceUp.setVisible(false);		
        this.btnTraceDown.setVisible(false);		
        this.btnAuditResult.setVisible(false);		
        this.menuItemCreateFrom.setVisible(false);		
        this.menuItemCopyFrom.setVisible(false);		
        this.separator1.setVisible(false);		
        this.separator2.setVisible(false);		
        this.separator3.setVisible(false);		
        this.menuItemTraceUp.setVisible(false);		
        this.menuItemTraceDown.setVisible(false);		
        this.menuItemViewSubmitProccess.setVisible(false);		
        this.menuItemViewDoProccess.setVisible(false);		
        this.menuItemAuditResult.setVisible(false);		
        this.menuTable1.setVisible(false);		
        this.menuItemAddLine.setVisible(false);		
        this.menuItemInsertLine.setVisible(false);		
        this.menuItemRemoveLine.setVisible(false);
        this.btnAudit.setAction((IItemAction)ActionProxyFactory.getProxy(actionAudit, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnAudit.setText(resHelper.getString("btnAudit.text"));		
        this.btnAudit.setToolTipText(resHelper.getString("btnAudit.toolTipText"));
        this.menuItemAudit.setAction((IItemAction)ActionProxyFactory.getProxy(actionAudit, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemAudit.setText(resHelper.getString("menuItemAudit.text"));		
        this.menuItemAudit.setToolTipText(resHelper.getString("menuItemAudit.toolTipText"));		
        this.menuItemUnAudit.setText(resHelper.getString("menuItemUnAudit.text"));		
        this.menuItemUnAudit.setToolTipText(resHelper.getString("menuItemUnAudit.toolTipText"));
        // kDTabbedPane2
        // mainPanel
        // ecoItemPanel
        // contCreateTime		
        this.contCreateTime.setBoundLabelText(resHelper.getString("contCreateTime.boundLabelText"));		
        this.contCreateTime.setBoundLabelLength(100);		
        this.contCreateTime.setBoundLabelUnderline(true);		
        this.contCreateTime.setEnabled(false);		
        this.contCreateTime.setBoundLabelAlignment(7);		
        this.contCreateTime.setVisible(true);
        // contNumber		
        this.contNumber.setBoundLabelText(resHelper.getString("contNumber.boundLabelText"));		
        this.contNumber.setBoundLabelLength(100);		
        this.contNumber.setBoundLabelUnderline(true);		
        this.contNumber.setBoundLabelAlignment(7);		
        this.contNumber.setVisible(true);
        // contamount		
        this.contamount.setBoundLabelText(resHelper.getString("contamount.boundLabelText"));		
        this.contamount.setBoundLabelLength(120);		
        this.contamount.setBoundLabelUnderline(true);		
        this.contamount.setVisible(true);		
        this.contamount.setBoundLabelAlignment(7);
        // contchgPercForWarn		
        this.contchgPercForWarn.setBoundLabelText(resHelper.getString("contchgPercForWarn.boundLabelText"));		
        this.contchgPercForWarn.setBoundLabelLength(100);		
        this.contchgPercForWarn.setBoundLabelUnderline(true);		
        this.contchgPercForWarn.setVisible(true);		
        this.contchgPercForWarn.setBoundLabelAlignment(7);
        // contpayPercForWarn		
        this.contpayPercForWarn.setBoundLabelText(resHelper.getString("contpayPercForWarn.boundLabelText"));		
        this.contpayPercForWarn.setBoundLabelLength(100);		
        this.contpayPercForWarn.setBoundLabelUnderline(true);		
        this.contpayPercForWarn.setVisible(true);		
        this.contpayPercForWarn.setBoundLabelAlignment(7);
        // contsignDate		
        this.contsignDate.setBoundLabelText(resHelper.getString("contsignDate.boundLabelText"));		
        this.contsignDate.setBoundLabelLength(100);		
        this.contsignDate.setBoundLabelUnderline(true);		
        this.contsignDate.setVisible(true);		
        this.contsignDate.setBoundLabelAlignment(7);
        // contlandDeveloper		
        this.contlandDeveloper.setBoundLabelText(resHelper.getString("contlandDeveloper.boundLabelText"));		
        this.contlandDeveloper.setBoundLabelLength(100);		
        this.contlandDeveloper.setBoundLabelUnderline(true);		
        this.contlandDeveloper.setVisible(true);		
        this.contlandDeveloper.setBoundLabelAlignment(7);
        // contcontractType		
        this.contcontractType.setBoundLabelText(resHelper.getString("contcontractType.boundLabelText"));		
        this.contcontractType.setBoundLabelLength(100);		
        this.contcontractType.setBoundLabelUnderline(true);		
        this.contcontractType.setVisible(true);		
        this.contcontractType.setBoundLabelAlignment(7);
        // contcostProperty		
        this.contcostProperty.setBoundLabelText(resHelper.getString("contcostProperty.boundLabelText"));		
        this.contcostProperty.setBoundLabelLength(100);		
        this.contcostProperty.setBoundLabelUnderline(true);		
        this.contcostProperty.setVisible(true);		
        this.contcostProperty.setBoundLabelAlignment(7);
        // contcontractPropert		
        this.contcontractPropert.setBoundLabelText(resHelper.getString("contcontractPropert.boundLabelText"));		
        this.contcontractPropert.setBoundLabelLength(100);		
        this.contcontractPropert.setBoundLabelUnderline(true);		
        this.contcontractPropert.setVisible(true);		
        this.contcontractPropert.setBoundLabelAlignment(7);
        // contcontractSource		
        this.contcontractSource.setBoundLabelText(resHelper.getString("contcontractSource.boundLabelText"));		
        this.contcontractSource.setBoundLabelLength(100);		
        this.contcontractSource.setBoundLabelUnderline(true);		
        this.contcontractSource.setVisible(true);		
        this.contcontractSource.setBoundLabelAlignment(7);
        // contpartB		
        this.contpartB.setBoundLabelText(resHelper.getString("contpartB.boundLabelText"));		
        this.contpartB.setBoundLabelLength(100);		
        this.contpartB.setBoundLabelUnderline(true);		
        this.contpartB.setVisible(true);		
        this.contpartB.setBoundLabelAlignment(7);
        // contpartC		
        this.contpartC.setBoundLabelText(resHelper.getString("contpartC.boundLabelText"));		
        this.contpartC.setBoundLabelLength(100);		
        this.contpartC.setBoundLabelUnderline(true);		
        this.contpartC.setVisible(true);		
        this.contpartC.setBoundLabelAlignment(7);
        // contcontractName		
        this.contcontractName.setBoundLabelText(resHelper.getString("contcontractName.boundLabelText"));		
        this.contcontractName.setBoundLabelLength(100);		
        this.contcontractName.setBoundLabelUnderline(true);		
        this.contcontractName.setVisible(true);		
        this.contcontractName.setBoundLabelAlignment(7);
        // kDTabbedPane1
        // chkCostSplit		
        this.chkCostSplit.setText(resHelper.getString("chkCostSplit.text"));		
        this.chkCostSplit.setToolTipText(resHelper.getString("chkCostSplit.toolTipText"));
        this.chkCostSplit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                try {
                    chkCostSplit_mouseClicked(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });
        // contExRate		
        this.contExRate.setBoundLabelText(resHelper.getString("contExRate.boundLabelText"));		
        this.contExRate.setBoundLabelLength(100);		
        this.contExRate.setBoundLabelUnderline(true);
        // contLocalAmount		
        this.contLocalAmount.setBoundLabelText(resHelper.getString("contLocalAmount.boundLabelText"));		
        this.contLocalAmount.setBoundLabelLength(120);		
        this.contLocalAmount.setBoundLabelUnderline(true);
        // contGrtAmount		
        this.contGrtAmount.setBoundLabelText(resHelper.getString("contGrtAmount.boundLabelText"));		
        this.contGrtAmount.setBoundLabelLength(100);		
        this.contGrtAmount.setBoundLabelUnderline(true);
        // contCurrency		
        this.contCurrency.setBoundLabelText(resHelper.getString("contCurrency.boundLabelText"));		
        this.contCurrency.setBoundLabelLength(100);		
        this.contCurrency.setBoundLabelUnderline(true);
        // contRespDept		
        this.contRespDept.setBoundLabelText(resHelper.getString("contRespDept.boundLabelText"));		
        this.contRespDept.setBoundLabelLength(100);		
        this.contRespDept.setBoundLabelUnderline(true);
        // contPayScale		
        this.contPayScale.setBoundLabelText(resHelper.getString("contPayScale.boundLabelText"));		
        this.contPayScale.setBoundLabelLength(100);		
        this.contPayScale.setBoundLabelUnderline(true);
        // contStampTaxRate		
        this.contStampTaxRate.setBoundLabelText(resHelper.getString("contStampTaxRate.boundLabelText"));		
        this.contStampTaxRate.setBoundLabelLength(100);		
        this.contStampTaxRate.setBoundLabelUnderline(true);
        // contStampTaxAmt		
        this.contStampTaxAmt.setBoundLabelText(resHelper.getString("contStampTaxAmt.boundLabelText"));		
        this.contStampTaxAmt.setBoundLabelLength(100);		
        this.contStampTaxAmt.setBoundLabelUnderline(true);
        // contRespPerson		
        this.contRespPerson.setBoundLabelText(resHelper.getString("contRespPerson.boundLabelText"));		
        this.contRespPerson.setBoundLabelLength(100);		
        this.contRespPerson.setBoundLabelUnderline(true);
        // contCreator		
        this.contCreator.setBoundLabelText(resHelper.getString("contCreator.boundLabelText"));		
        this.contCreator.setBoundLabelLength(100);		
        this.contCreator.setBoundLabelUnderline(true);		
        this.contCreator.setEnabled(false);		
        this.contCreator.setBoundLabelAlignment(7);		
        this.contCreator.setVisible(true);
        // contOrg		
        this.contOrg.setBoundLabelText(resHelper.getString("contOrg.boundLabelText"));		
        this.contOrg.setBoundLabelLength(100);		
        this.contOrg.setBoundLabelUnderline(true);
        // contProj		
        this.contProj.setBoundLabelText(resHelper.getString("contProj.boundLabelText"));		
        this.contProj.setBoundLabelLength(100);		
        this.contProj.setBoundLabelUnderline(true);
        // contGrtRate		
        this.contGrtRate.setBoundLabelText(resHelper.getString("contGrtRate.boundLabelText"));		
        this.contGrtRate.setBoundLabelLength(100);		
        this.contGrtRate.setBoundLabelUnderline(true);
        // contOldAmount		
        this.contOldAmount.setBoundLabelText(resHelper.getString("contOldAmount.boundLabelText"));		
        this.contOldAmount.setBoundLabelLength(120);		
        this.contOldAmount.setBoundLabelUnderline(true);		
        this.contOldAmount.setVisible(true);		
        this.contOldAmount.setBoundLabelAlignment(7);
        // contOldLAmount		
        this.contOldLAmount.setBoundLabelText(resHelper.getString("contOldLAmount.boundLabelText"));		
        this.contOldLAmount.setBoundLabelLength(120);		
        this.contOldLAmount.setBoundLabelUnderline(true);
        // contReviseReason		
        this.contReviseReason.setBoundLabelText(resHelper.getString("contReviseReason.boundLabelText"));		
        this.contReviseReason.setBoundLabelLength(100);		
        this.contReviseReason.setBoundLabelUnderline(true);
        // kDLabelContainer1		
        this.kDLabelContainer1.setBoundLabelText(resHelper.getString("kDLabelContainer1.boundLabelText"));		
        this.kDLabelContainer1.setBoundLabelLength(100);		
        this.kDLabelContainer1.setBoundLabelUnderline(true);
        // kDLabelContainer2		
        this.kDLabelContainer2.setBoundLabelText(resHelper.getString("kDLabelContainer2.boundLabelText"));		
        this.kDLabelContainer2.setBoundLabelLength(100);		
        this.kDLabelContainer2.setBoundLabelUnderline(true);
        // chkIsPartAMaterialCon		
        this.chkIsPartAMaterialCon.setText(resHelper.getString("chkIsPartAMaterialCon.text"));
        // lblAttachmentContainer		
        this.lblAttachmentContainer.setBoundLabelText(resHelper.getString("lblAttachmentContainer.boundLabelText"));		
        this.lblAttachmentContainer.setBoundLabelLength(100);		
        this.lblAttachmentContainer.setBoundLabelUnderline(true);
        // btnViewAttachment
        this.btnViewAttachment.setAction((IItemAction)ActionProxyFactory.getProxy(actionViewAttachment, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnViewAttachment.setText(resHelper.getString("btnViewAttachment.text"));
        // kDDateCreateTime		
        this.kDDateCreateTime.setTimeEnabled(true);		
        this.kDDateCreateTime.setEnabled(false);		
        this.kDDateCreateTime.setVisible(true);
        // txtNumber		
        this.txtNumber.setMaxLength(80);		
        this.txtNumber.setVisible(true);		
        this.txtNumber.setEnabled(true);		
        this.txtNumber.setHorizontalAlignment(2);		
        this.txtNumber.setRequired(true);
        // txtamount		
        this.txtamount.setVisible(true);		
        this.txtamount.setDataType(1);		
        this.txtamount.setSupportedEmpty(true);		
        this.txtamount.setMinimumValue( new java.math.BigDecimal(-1.0E18));		
        this.txtamount.setMaximumValue( new java.math.BigDecimal(1.0E18));		
        this.txtamount.setPrecision(2);		
        this.txtamount.setEnabled(true);		
        this.txtamount.setRequired(true);
        this.txtamount.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    txtamount_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        this.txtamount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                try {
                    txtamount_focusGained(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });
        // txtchgPercForWarn		
        this.txtchgPercForWarn.setVisible(true);		
        this.txtchgPercForWarn.setHorizontalAlignment(2);		
        this.txtchgPercForWarn.setDataType(1);		
        this.txtchgPercForWarn.setSupportedEmpty(true);		
        this.txtchgPercForWarn.setMinimumValue( new java.math.BigDecimal(0.0));		
        this.txtchgPercForWarn.setMaximumValue( new java.math.BigDecimal(100.0));		
        this.txtchgPercForWarn.setPrecision(2);		
        this.txtchgPercForWarn.setEnabled(true);		
        this.txtchgPercForWarn.setRequired(true);
        // txtpayPercForWarn		
        this.txtpayPercForWarn.setVisible(true);		
        this.txtpayPercForWarn.setHorizontalAlignment(2);		
        this.txtpayPercForWarn.setDataType(1);		
        this.txtpayPercForWarn.setSupportedEmpty(true);		
        this.txtpayPercForWarn.setMinimumValue( new java.math.BigDecimal(0.0));		
        this.txtpayPercForWarn.setMaximumValue( new java.math.BigDecimal(100.0));		
        this.txtpayPercForWarn.setPrecision(2);		
        this.txtpayPercForWarn.setEnabled(true);		
        this.txtpayPercForWarn.setRequired(true);
        // pksignDate		
        this.pksignDate.setVisible(true);		
        this.pksignDate.setEnabled(true);
        // prmtlandDeveloper		
        this.prmtlandDeveloper.setQueryInfo("com.kingdee.eas.fdc.basedata.app.LandDeveloperQuery");		
        this.prmtlandDeveloper.setVisible(true);		
        this.prmtlandDeveloper.setEditable(true);		
        this.prmtlandDeveloper.setDisplayFormat("$number$ $name$");		
        this.prmtlandDeveloper.setEditFormat("$number$");		
        this.prmtlandDeveloper.setCommitFormat("$number$");		
        this.prmtlandDeveloper.setRequired(true);
        // prmtcontractType		
        this.prmtcontractType.setVisible(true);		
        this.prmtcontractType.setEditable(true);		
        this.prmtcontractType.setDisplayFormat("$number$ $name$");		
        this.prmtcontractType.setEditFormat("$number$");		
        this.prmtcontractType.setCommitFormat("$number$");		
        this.prmtcontractType.setRequired(true);		
        this.prmtcontractType.setDefaultF7UIName("com.kingdee.eas.fdc.basedata.client.ContractTypeF7UI");		
        this.prmtcontractType.setQueryInfo("com.kingdee.eas.fdc.basedata.app.F7ContractTypeQuery");
        this.prmtcontractType.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    prmtcontractType_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        this.prmtcontractType.addSelectorListener(new com.kingdee.bos.ctrl.swing.event.SelectorListener() {
            public void willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) {
                try {
                    prmtcontractType_willShow(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // costProperty		
        this.costProperty.setVisible(true);		
        this.costProperty.addItems(EnumUtils.getEnumList("com.kingdee.eas.fdc.contract.CostPropertyEnum").toArray());		
        this.costProperty.setEnabled(true);		
        this.costProperty.setRequired(true);
        this.costProperty.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                try {
                    costProperty_itemStateChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // contractPropert		
        this.contractPropert.setVisible(true);		
        this.contractPropert.addItems(EnumUtils.getEnumList("com.kingdee.eas.fdc.contract.ContractPropertyEnum").toArray());		
        this.contractPropert.setEnabled(true);
        this.contractPropert.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                try {
                    contractPropert_itemStateChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // contractSource		
        this.contractSource.setQueryInfo("com.kingdee.eas.fdc.basedata.app.ContractSourceQuery");		
        this.contractSource.setCommitFormat("$number$");		
        this.contractSource.setDisplayFormat("$number$ $name$");		
        this.contractSource.setEditFormat("$number$");		
        this.contractSource.setRequired(true);
        this.contractSource.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    contractSource_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        this.contractSource.addSelectorListener(new com.kingdee.bos.ctrl.swing.event.SelectorListener() {
            public void willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) {
                try {
                    contractSource_willShow(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // prmtpartB		
        this.prmtpartB.setQueryInfo("com.kingdee.eas.basedata.master.cssp.app.F7SupplierQueryWithDefaultStandard");		
        this.prmtpartB.setVisible(true);		
        this.prmtpartB.setEditable(true);		
        this.prmtpartB.setDisplayFormat("$number$ $name$");		
        this.prmtpartB.setEditFormat("$number$");		
        this.prmtpartB.setCommitFormat("$number$");		
        this.prmtpartB.setRequired(true);
        // prmtpartC		
        this.prmtpartC.setQueryInfo("com.kingdee.eas.basedata.master.cssp.app.F7SupplierQueryWithDefaultStandard");		
        this.prmtpartC.setVisible(true);		
        this.prmtpartC.setEditable(true);		
        this.prmtpartC.setDisplayFormat("$number$ $name$");		
        this.prmtpartC.setEditFormat("$number$");		
        this.prmtpartC.setCommitFormat("$number$");
        // txtcontractName		
        this.txtcontractName.setVisible(true);		
        this.txtcontractName.setHorizontalAlignment(2);		
        this.txtcontractName.setMaxLength(100);		
        this.txtcontractName.setEnabled(true);		
        this.txtcontractName.setRequired(true);
        // pnlInviteInfo
        // pnlDetail
        // contRemark		
        this.contRemark.setBoundLabelText(resHelper.getString("contRemark.boundLabelText"));		
        this.contRemark.setBoundLabelLength(100);		
        this.contRemark.setBoundLabelUnderline(true);		
        this.contRemark.setVisible(false);
        // contCoopLevel		
        this.contCoopLevel.setBoundLabelText(resHelper.getString("contCoopLevel.boundLabelText"));		
        this.contCoopLevel.setBoundLabelLength(100);		
        this.contCoopLevel.setBoundLabelUnderline(true);
        // contPriceType		
        this.contPriceType.setBoundLabelText(resHelper.getString("contPriceType.boundLabelText"));		
        this.contPriceType.setBoundLabelLength(100);		
        this.contPriceType.setBoundLabelUnderline(true);		
        this.contPriceType.setVisible(false);
        // chkIsSubMainContract		
        this.chkIsSubMainContract.setText(resHelper.getString("chkIsSubMainContract.text"));
        // conEffectiveStartDate		
        this.conEffectiveStartDate.setBoundLabelText(resHelper.getString("conEffectiveStartDate.boundLabelText"));		
        this.conEffectiveStartDate.setBoundLabelUnderline(true);		
        this.conEffectiveStartDate.setBoundLabelLength(100);
        // conEffectiveEndDate		
        this.conEffectiveEndDate.setBoundLabelText(resHelper.getString("conEffectiveEndDate.boundLabelText"));		
        this.conEffectiveEndDate.setBoundLabelUnderline(true);		
        this.conEffectiveEndDate.setBoundLabelLength(100);
        // conMainContract		
        this.conMainContract.setBoundLabelText(resHelper.getString("conMainContract.boundLabelText"));		
        this.conMainContract.setBoundLabelLength(100);		
        this.conMainContract.setBoundLabelUnderline(true);
        // kDScrollPane2
        // conInformation		
        this.conInformation.setBoundLabelText(resHelper.getString("conInformation.boundLabelText"));		
        this.conInformation.setBoundLabelUnderline(true);
        // contlowestPrice		
        this.contlowestPrice.setBoundLabelText(resHelper.getString("contlowestPrice.boundLabelText"));		
        this.contlowestPrice.setBoundLabelLength(100);		
        this.contlowestPrice.setBoundLabelUnderline(true);		
        this.contlowestPrice.setVisible(true);		
        this.contlowestPrice.setBoundLabelAlignment(7);
        // contlowerPrice		
        this.contlowerPrice.setBoundLabelText(resHelper.getString("contlowerPrice.boundLabelText"));		
        this.contlowerPrice.setBoundLabelLength(100);		
        this.contlowerPrice.setBoundLabelUnderline(true);		
        this.contlowerPrice.setVisible(true);		
        this.contlowerPrice.setBoundLabelAlignment(7);
        // contmiddlePrice		
        this.contmiddlePrice.setBoundLabelText(resHelper.getString("contmiddlePrice.boundLabelText"));		
        this.contmiddlePrice.setBoundLabelLength(100);		
        this.contmiddlePrice.setBoundLabelUnderline(true);		
        this.contmiddlePrice.setVisible(true);		
        this.contmiddlePrice.setBoundLabelAlignment(7);
        // conthigherPrice		
        this.conthigherPrice.setBoundLabelText(resHelper.getString("conthigherPrice.boundLabelText"));		
        this.conthigherPrice.setBoundLabelLength(100);		
        this.conthigherPrice.setBoundLabelUnderline(true);		
        this.conthigherPrice.setVisible(true);		
        this.conthigherPrice.setBoundLabelAlignment(7);
        // conthighestPrice		
        this.conthighestPrice.setBoundLabelText(resHelper.getString("conthighestPrice.boundLabelText"));		
        this.conthighestPrice.setBoundLabelLength(100);		
        this.conthighestPrice.setBoundLabelUnderline(true);		
        this.conthighestPrice.setVisible(true);		
        this.conthighestPrice.setBoundLabelAlignment(7);
        // contbasePrice		
        this.contbasePrice.setBoundLabelText(resHelper.getString("contbasePrice.boundLabelText"));		
        this.contbasePrice.setBoundLabelLength(100);		
        this.contbasePrice.setBoundLabelUnderline(true);		
        this.contbasePrice.setVisible(true);		
        this.contbasePrice.setBoundLabelAlignment(7);
        // contsecondPrice		
        this.contsecondPrice.setBoundLabelText(resHelper.getString("contsecondPrice.boundLabelText"));		
        this.contsecondPrice.setBoundLabelLength(100);		
        this.contsecondPrice.setBoundLabelUnderline(true);		
        this.contsecondPrice.setVisible(true);		
        this.contsecondPrice.setBoundLabelAlignment(7);
        // continviteType		
        this.continviteType.setBoundLabelText(resHelper.getString("continviteType.boundLabelText"));		
        this.continviteType.setBoundLabelLength(100);		
        this.continviteType.setBoundLabelUnderline(true);		
        this.continviteType.setVisible(true);		
        this.continviteType.setBoundLabelAlignment(7);
        // contwinPrice		
        this.contwinPrice.setBoundLabelText(resHelper.getString("contwinPrice.boundLabelText"));		
        this.contwinPrice.setBoundLabelLength(100);		
        this.contwinPrice.setBoundLabelUnderline(true);		
        this.contwinPrice.setVisible(true);		
        this.contwinPrice.setBoundLabelAlignment(7);
        // contwinUnit		
        this.contwinUnit.setBoundLabelText(resHelper.getString("contwinUnit.boundLabelText"));		
        this.contwinUnit.setBoundLabelLength(100);		
        this.contwinUnit.setBoundLabelUnderline(true);		
        this.contwinUnit.setVisible(true);		
        this.contwinUnit.setBoundLabelAlignment(7);
        // contfileNo		
        this.contfileNo.setBoundLabelText(resHelper.getString("contfileNo.boundLabelText"));		
        this.contfileNo.setBoundLabelLength(100);		
        this.contfileNo.setBoundLabelUnderline(true);		
        this.contfileNo.setVisible(true);		
        this.contfileNo.setBoundLabelAlignment(7);
        // contquantity		
        this.contquantity.setBoundLabelText(resHelper.getString("contquantity.boundLabelText"));		
        this.contquantity.setBoundLabelLength(100);		
        this.contquantity.setBoundLabelUnderline(true);		
        this.contquantity.setVisible(true);		
        this.contquantity.setBoundLabelAlignment(7);
        // lblPrice		
        this.lblPrice.setText(resHelper.getString("lblPrice.text"));
        // lblUnit		
        this.lblUnit.setText(resHelper.getString("lblUnit.text"));
        // prmtlowestPriceUnit		
        this.prmtlowestPriceUnit.setQueryInfo("com.kingdee.eas.basedata.master.cssp.app.F7SupplierQuery");		
        this.prmtlowestPriceUnit.setVisible(true);		
        this.prmtlowestPriceUnit.setEditable(true);		
        this.prmtlowestPriceUnit.setDisplayFormat("$number$  $name$");		
        this.prmtlowestPriceUnit.setEditFormat("$number$");		
        this.prmtlowestPriceUnit.setCommitFormat("$number$");
        // prmtlowerPriceUnit		
        this.prmtlowerPriceUnit.setQueryInfo("com.kingdee.eas.basedata.master.cssp.app.F7SupplierQuery");		
        this.prmtlowerPriceUnit.setVisible(true);		
        this.prmtlowerPriceUnit.setEditable(true);		
        this.prmtlowerPriceUnit.setDisplayFormat("$number$ $name$");		
        this.prmtlowerPriceUnit.setEditFormat("$number$");		
        this.prmtlowerPriceUnit.setCommitFormat("$number$");
        // prmtmiddlePriceUnit		
        this.prmtmiddlePriceUnit.setQueryInfo("com.kingdee.eas.basedata.master.cssp.app.F7SupplierQuery");		
        this.prmtmiddlePriceUnit.setVisible(true);		
        this.prmtmiddlePriceUnit.setEditable(true);		
        this.prmtmiddlePriceUnit.setDisplayFormat("$number$ $name$");		
        this.prmtmiddlePriceUnit.setEditFormat("$number$");		
        this.prmtmiddlePriceUnit.setCommitFormat("$number$");
        // prmthigherPriceUnit		
        this.prmthigherPriceUnit.setQueryInfo("com.kingdee.eas.basedata.master.cssp.app.F7SupplierQuery");		
        this.prmthigherPriceUnit.setVisible(true);		
        this.prmthigherPriceUnit.setEditable(true);		
        this.prmthigherPriceUnit.setDisplayFormat("$number$ $name$");		
        this.prmthigherPriceUnit.setEditFormat("$number$");		
        this.prmthigherPriceUnit.setCommitFormat("$number$");
        // prmthighestPriceUni		
        this.prmthighestPriceUni.setQueryInfo("com.kingdee.eas.basedata.master.cssp.app.F7SupplierQuery");		
        this.prmthighestPriceUni.setVisible(true);		
        this.prmthighestPriceUni.setEditable(true);		
        this.prmthighestPriceUni.setDisplayFormat("$number$ $name$");		
        this.prmthighestPriceUni.setEditFormat("$number$");		
        this.prmthighestPriceUni.setCommitFormat("$number$");
        // txtRemark
        // comboCoopLevel		
        this.comboCoopLevel.addItems(EnumUtils.getEnumList("com.kingdee.eas.fdc.contract.CoopLevelEnum").toArray());
        // comboPriceType		
        this.comboPriceType.addItems(EnumUtils.getEnumList("com.kingdee.eas.fdc.contract.PriceTypeEnum").toArray());
        // kDDatePicker1
        // kdpEffectiveStartDate
        // kdpEffectiveEndDate
        // prmtMainContract		
        this.prmtMainContract.setDisplayFormat("$name$");		
        this.prmtMainContract.setEditFormat("$number$");		
        this.prmtMainContract.setCommitFormat("$number$");		
        this.prmtMainContract.setQueryInfo("com.kingdee.eas.fdc.contract.app.F7MainContractBillQuery");
        // txtInformation
        // txtlowestPrice		
        this.txtlowestPrice.setVisible(true);		
        this.txtlowestPrice.setHorizontalAlignment(2);		
        this.txtlowestPrice.setDataType(1);		
        this.txtlowestPrice.setSupportedEmpty(true);		
        this.txtlowestPrice.setMinimumValue( new java.math.BigDecimal(-1.0E18));		
        this.txtlowestPrice.setMaximumValue( new java.math.BigDecimal(1.0E18));		
        this.txtlowestPrice.setPrecision(2);		
        this.txtlowestPrice.setEnabled(true);
        // txtlowerPrice		
        this.txtlowerPrice.setVisible(true);		
        this.txtlowerPrice.setHorizontalAlignment(2);		
        this.txtlowerPrice.setDataType(1);		
        this.txtlowerPrice.setSupportedEmpty(true);		
        this.txtlowerPrice.setMinimumValue( new java.math.BigDecimal(-1.0E18));		
        this.txtlowerPrice.setMaximumValue( new java.math.BigDecimal(1.0E18));		
        this.txtlowerPrice.setPrecision(2);		
        this.txtlowerPrice.setEnabled(true);
        // txtmiddlePrice		
        this.txtmiddlePrice.setVisible(true);		
        this.txtmiddlePrice.setHorizontalAlignment(2);		
        this.txtmiddlePrice.setDataType(1);		
        this.txtmiddlePrice.setSupportedEmpty(true);		
        this.txtmiddlePrice.setMinimumValue( new java.math.BigDecimal(-1.0E18));		
        this.txtmiddlePrice.setMaximumValue( new java.math.BigDecimal(1.0E18));		
        this.txtmiddlePrice.setPrecision(2);		
        this.txtmiddlePrice.setEnabled(true);
        // txthigherPrice		
        this.txthigherPrice.setVisible(true);		
        this.txthigherPrice.setHorizontalAlignment(2);		
        this.txthigherPrice.setDataType(1);		
        this.txthigherPrice.setSupportedEmpty(true);		
        this.txthigherPrice.setMinimumValue( new java.math.BigDecimal(-1.0E18));		
        this.txthigherPrice.setMaximumValue( new java.math.BigDecimal(1.0E18));		
        this.txthigherPrice.setPrecision(2);		
        this.txthigherPrice.setEnabled(true);
        // txthighestPrice		
        this.txthighestPrice.setVisible(true);		
        this.txthighestPrice.setHorizontalAlignment(2);		
        this.txthighestPrice.setDataType(1);		
        this.txthighestPrice.setSupportedEmpty(true);		
        this.txthighestPrice.setMinimumValue( new java.math.BigDecimal(-1.0E18));		
        this.txthighestPrice.setMaximumValue( new java.math.BigDecimal(1.0E18));		
        this.txthighestPrice.setPrecision(2);		
        this.txthighestPrice.setEnabled(true);
        // txtbasePrice		
        this.txtbasePrice.setVisible(true);		
        this.txtbasePrice.setHorizontalAlignment(2);		
        this.txtbasePrice.setDataType(1);		
        this.txtbasePrice.setSupportedEmpty(true);		
        this.txtbasePrice.setMinimumValue( new java.math.BigDecimal(-1.0E18));		
        this.txtbasePrice.setMaximumValue( new java.math.BigDecimal(1.0E18));		
        this.txtbasePrice.setPrecision(2);		
        this.txtbasePrice.setEnabled(true);
        // txtsecondPrice		
        this.txtsecondPrice.setVisible(true);		
        this.txtsecondPrice.setHorizontalAlignment(2);		
        this.txtsecondPrice.setDataType(1);		
        this.txtsecondPrice.setSupportedEmpty(true);		
        this.txtsecondPrice.setMinimumValue( new java.math.BigDecimal(-1.0E18));		
        this.txtsecondPrice.setMaximumValue( new java.math.BigDecimal(1.0E18));		
        this.txtsecondPrice.setPrecision(2);		
        this.txtsecondPrice.setEnabled(true);
        // prmtinviteType		
        this.prmtinviteType.setQueryInfo("com.kingdee.eas.fdc.basedata.app.InviteTypeQuery");		
        this.prmtinviteType.setVisible(true);		
        this.prmtinviteType.setEditable(true);		
        this.prmtinviteType.setDisplayFormat("$number$ $name$");		
        this.prmtinviteType.setEditFormat("$number$");		
        this.prmtinviteType.setCommitFormat("$number$");
        // txtwinPrice		
        this.txtwinPrice.setVisible(true);		
        this.txtwinPrice.setHorizontalAlignment(2);		
        this.txtwinPrice.setDataType(1);		
        this.txtwinPrice.setSupportedEmpty(true);		
        this.txtwinPrice.setMinimumValue( new java.math.BigDecimal(-1.0E18));		
        this.txtwinPrice.setMaximumValue( new java.math.BigDecimal(1.0E18));		
        this.txtwinPrice.setPrecision(2);		
        this.txtwinPrice.setEnabled(true);
        // prmtwinUnit		
        this.prmtwinUnit.setQueryInfo("com.kingdee.eas.basedata.master.cssp.app.F7SupplierQuery");		
        this.prmtwinUnit.setVisible(true);		
        this.prmtwinUnit.setEditable(true);		
        this.prmtwinUnit.setDisplayFormat("$number$ $name$");		
        this.prmtwinUnit.setEditFormat("$number$");		
        this.prmtwinUnit.setCommitFormat("$number$");
        // txtfileNo		
        this.txtfileNo.setVisible(true);		
        this.txtfileNo.setHorizontalAlignment(2);		
        this.txtfileNo.setMaxLength(100);		
        this.txtfileNo.setEnabled(true);
        // txtquantity		
        this.txtquantity.setVisible(true);		
        this.txtquantity.setHorizontalAlignment(2);		
        this.txtquantity.setDataType(1);		
        this.txtquantity.setSupportedEmpty(true);		
        this.txtquantity.setMinimumValue( new java.math.BigDecimal(-1.0E18));		
        this.txtquantity.setMaximumValue( new java.math.BigDecimal(1.0E18));		
        this.txtquantity.setPrecision(4);		
        this.txtquantity.setEnabled(true);
        // tblDetail
		String tblDetailStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol1\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol4\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol5\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol6\"><c:Protection hidden=\"true\" /></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"id\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:styleID=\"sCol0\" /><t:Column t:key=\"detail\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:styleID=\"sCol1\" /><t:Column t:key=\"content\" t:width=\"300\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" /><t:Column t:key=\"desc\" t:width=\"500\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" /><t:Column t:key=\"rowKey\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:styleID=\"sCol4\" /><t:Column t:key=\"dataType\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:styleID=\"sCol5\" /><t:Column t:key=\"detailDef.id\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:styleID=\"sCol6\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{id}</t:Cell><t:Cell>$Resource{detail}</t:Cell><t:Cell>$Resource{content}</t:Cell><t:Cell>$Resource{desc}</t:Cell><t:Cell>$Resource{rowKey}</t:Cell><t:Cell>$Resource{dataType}</t:Cell><t:Cell>$Resource{detailDef.id}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot> ";
		
        this.tblDetail.setFormatXml(resHelper.translateString("tblDetail",tblDetailStrXML));

        

        // txtExRate		
        this.txtExRate.setRequired(true);		
        this.txtExRate.setPrecision(2);		
        this.txtExRate.setDataType(1);
        this.txtExRate.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    txtExRate_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // txtLocalAmount		
        this.txtLocalAmount.setDataType(1);		
        this.txtLocalAmount.setPrecision(2);		
        this.txtLocalAmount.setRequired(true);		
        this.txtLocalAmount.setEditable(false);
        // txtGrtAmount		
        this.txtGrtAmount.setDataType(1);		
        this.txtGrtAmount.setPrecision(2);
        // comboCurrency
        this.comboCurrency.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                try {
                    comboCurrency_itemStateChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // prmtRespDept		
        this.prmtRespDept.setDisplayFormat("$name$");		
        this.prmtRespDept.setEditFormat("$number");		
        this.prmtRespDept.setDefaultF7UIName("com.kingdee.eas.basedata.org.client.f7.AdminF7");		
        this.prmtRespDept.setCommitFormat("$number$");		
        this.prmtRespDept.setEditable(true);		
        this.prmtRespDept.setRequired(true);
        // txtPayScale		
        this.txtPayScale.setDataType(1);		
        this.txtPayScale.setPrecision(2);		
        this.txtPayScale.setSupportedEmpty(true);
        // txtStampTaxRate		
        this.txtStampTaxRate.setDataType(1);		
        this.txtStampTaxRate.setPrecision(2);		
        this.txtStampTaxRate.setSupportedEmpty(true);
        this.txtStampTaxRate.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    txtStampTaxRate_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // txtStampTaxAmt		
        this.txtStampTaxAmt.setDataType(1);		
        this.txtStampTaxAmt.setPrecision(2);		
        this.txtStampTaxAmt.setSupportedEmpty(true);
        // prmtRespPerson		
        this.prmtRespPerson.setQueryInfo("com.kingdee.eas.basedata.person.app.PersonQuery");		
        this.prmtRespPerson.setDisplayFormat("$name$");		
        this.prmtRespPerson.setEditFormat("$number$");		
        this.prmtRespPerson.setCommitFormat("$number$");		
        this.prmtRespPerson.setRequired(true);		
        this.prmtRespPerson.setDefaultF7UIName("com.kingdee.eas.basedata.person.client.PersonF7UI");
        // txtCreator		
        this.txtCreator.setEditable(false);
        // txtOrg		
        this.txtOrg.setEditable(false);
        // txtProj		
        this.txtProj.setEditable(false);
        // txtGrtRate		
        this.txtGrtRate.setRequired(true);		
        this.txtGrtRate.setPrecision(2);		
        this.txtGrtRate.setDataType(1);
        this.txtGrtRate.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    txtGrtRate_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // txtOldAmount		
        this.txtOldAmount.setVisible(true);		
        this.txtOldAmount.setDataType(1);		
        this.txtOldAmount.setSupportedEmpty(true);		
        this.txtOldAmount.setMinimumValue( new java.math.BigDecimal(-1.0E18));		
        this.txtOldAmount.setMaximumValue( new java.math.BigDecimal(1.0E18));		
        this.txtOldAmount.setPrecision(2);		
        this.txtOldAmount.setEnabled(true);		
        this.txtOldAmount.setRequired(true);
        // txtOldLAmount		
        this.txtOldLAmount.setDataType(1);		
        this.txtOldLAmount.setPrecision(2);		
        this.txtOldLAmount.setRequired(true);		
        this.txtOldLAmount.setEditable(false);
        // kDScrollPane1
        // txtReviseReason		
        this.txtReviseReason.setMaxLength(500);
        // pkbookedDate		
        this.pkbookedDate.setEnabled(false);
        this.pkbookedDate.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    pkbookedDate_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // cbPeriod		
        this.cbPeriod.setQueryInfo("com.kingdee.eas.basedata.assistant.app.F7PeriodQuery");
        // cmbAttachment
        // kDContainer1
        // kDSplitPane1		
        this.kDSplitPane1.setOrientation(0);		
        this.kDSplitPane1.setResizeWeight(0.5);
        // contPayItem		
        this.contPayItem.setTitle(resHelper.getString("contPayItem.title"));
        // contBailItem		
        this.contBailItem.setTitle(resHelper.getString("contBailItem.title"));
        // tblEconItem
		String tblEconItemStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol3\"><c:NumberFormat>###.00</c:NumberFormat><c:Alignment horizontal=\"right\" /></c:Style><c:Style id=\"sCol4\"><c:NumberFormat>###,##0.00</c:NumberFormat><c:Alignment horizontal=\"right\" /></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"date\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"payType\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"true\" t:index=\"-1\" /><t:Column t:key=\"payCondition\" t:width=\"180\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"true\" t:index=\"-1\" /><t:Column t:key=\"payRate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"true\" t:index=\"-1\" t:styleID=\"sCol3\" /><t:Column t:key=\"payAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"true\" t:index=\"-1\" t:styleID=\"sCol4\" /><t:Column t:key=\"desc\" t:width=\"390\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header1\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{date}</t:Cell><t:Cell>$Resource{payType}</t:Cell><t:Cell>$Resource{payCondition}</t:Cell><t:Cell>$Resource{payRate}</t:Cell><t:Cell>$Resource{payAmount}</t:Cell><t:Cell>$Resource{desc}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.tblEconItem.setFormatXml(resHelper.translateString("tblEconItem",tblEconItemStrXML));
        this.tblEconItem.addKDTMouseListener(new com.kingdee.bos.ctrl.kdf.table.event.KDTMouseListener() {
            public void tableClicked(com.kingdee.bos.ctrl.kdf.table.event.KDTMouseEvent e) {
                try {
                    tblEconItem_tableClicked(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        this.tblEconItem.addKDTEditListener(new com.kingdee.bos.ctrl.kdf.table.event.KDTEditAdapter() {
            public void editStopped(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) {
                try {
                    tblEconItem_editStopped(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });

                this.tblEconItem.putBindContents("editData",new String[] {"payItemDate","paymentType","payCondition","prop","amount","desc"});


        // contBailOriAmount		
        this.contBailOriAmount.setBoundLabelText(resHelper.getString("contBailOriAmount.boundLabelText"));		
        this.contBailOriAmount.setBoundLabelLength(140);		
        this.contBailOriAmount.setBoundLabelUnderline(true);
        // contBailRate		
        this.contBailRate.setBoundLabelText(resHelper.getString("contBailRate.boundLabelText"));		
        this.contBailRate.setBoundLabelLength(140);		
        this.contBailRate.setBoundLabelUnderline(true);
        // tblBail
		String tblBailStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol2\"><c:NumberFormat>###.00</c:NumberFormat><c:Alignment horizontal=\"right\" /></c:Style><c:Style id=\"sCol3\"><c:NumberFormat>###,##0.00</c:NumberFormat><c:Alignment horizontal=\"right\" /></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"bailDate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"bailCondition\" t:width=\"200\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"bailRate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"true\" t:index=\"-1\" t:styleID=\"sCol2\" /><t:Column t:key=\"bailAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"true\" t:index=\"-1\" t:styleID=\"sCol3\" /><t:Column t:key=\"desc\" t:width=\"460\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header1\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{bailDate}</t:Cell><t:Cell>$Resource{bailCondition}</t:Cell><t:Cell>$Resource{bailRate}</t:Cell><t:Cell>$Resource{bailAmount}</t:Cell><t:Cell>$Resource{desc}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.tblBail.setFormatXml(resHelper.translateString("tblBail",tblBailStrXML));
        this.tblBail.addKDTMouseListener(new com.kingdee.bos.ctrl.kdf.table.event.KDTMouseListener() {
            public void tableClicked(com.kingdee.bos.ctrl.kdf.table.event.KDTMouseEvent e) {
                try {
                    tblBail_tableClicked(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        this.tblBail.addKDTEditListener(new com.kingdee.bos.ctrl.kdf.table.event.KDTEditAdapter() {
            public void editStopped(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) {
                try {
                    tblBail_editStopped(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });

                this.tblBail.putBindContents("editData",new String[] {"ail.entry.bailDate","ail.entry.bailConditon","ail.entry.prop","ail.entry.amount","ail.entry.desc"});


        // txtBailOriAmount		
        this.txtBailOriAmount.setDataType(1);
        this.txtBailOriAmount.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    txtBailOriAmount_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // txtBailRate		
        this.txtBailRate.setDataType(1);
        this.txtBailRate.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    txtBailRate_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // btnSplit
        this.btnSplit.setAction((IItemAction)ActionProxyFactory.getProxy(actionSplit, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnSplit.setText(resHelper.getString("btnSplit.text"));		
        this.btnSplit.setToolTipText(resHelper.getString("btnSplit.toolTipText"));
        // btnViewContent
        this.btnViewContent.setAction((IItemAction)ActionProxyFactory.getProxy(actionViewContent, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnViewContent.setText(resHelper.getString("btnViewContent.text"));
        // btnContractPlan
        this.btnContractPlan.setAction((IItemAction)ActionProxyFactory.getProxy(actionContractPlan, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnContractPlan.setText(resHelper.getString("btnContractPlan.text"));		
        this.btnContractPlan.setToolTipText(resHelper.getString("btnContractPlan.toolTipText"));
        // menuItemViewContent
        this.menuItemViewContent.setAction((IItemAction)ActionProxyFactory.getProxy(actionViewContent, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemViewContent.setText(resHelper.getString("menuItemViewContent.text"));		
        this.menuItemViewContent.setToolTipText(resHelper.getString("menuItemViewContent.toolTipText"));
        // menuItemSplit
        this.menuItemSplit.setAction((IItemAction)ActionProxyFactory.getProxy(actionSplit, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemSplit.setText(resHelper.getString("menuItemSplit.text"));		
        this.menuItemSplit.setToolTipText(resHelper.getString("menuItemSplit.toolTipText"));
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
        this.setBounds(new Rectangle(0, 0, 1013, 629));
this.setLayout(new BorderLayout(0, 0));
        this.add(kDTabbedPane2, BorderLayout.CENTER);
        //kDTabbedPane2
        kDTabbedPane2.add(mainPanel, resHelper.getString("mainPanel.constraints"));
        kDTabbedPane2.add(ecoItemPanel, resHelper.getString("ecoItemPanel.constraints"));
        //mainPanel
        mainPanel.setLayout(new KDLayout());
        mainPanel.putClientProperty("OriginalBounds", new Rectangle(0, 0, 1012, 596));        contCreateTime.setBounds(new Rectangle(12, 287, 270, 19));
        mainPanel.add(contCreateTime, new KDLayout.Constraints(12, 287, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contNumber.setBounds(new Rectangle(532, 37, 470, 19));
        mainPanel.add(contNumber, new KDLayout.Constraints(532, 37, 470, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contamount.setBounds(new Rectangle(373, 187, 270, 19));
        mainPanel.add(contamount, new KDLayout.Constraints(373, 187, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contchgPercForWarn.setBounds(new Rectangle(732, 262, 270, 19));
        mainPanel.add(contchgPercForWarn, new KDLayout.Constraints(732, 262, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contpayPercForWarn.setBounds(new Rectangle(732, 237, 270, 19));
        mainPanel.add(contpayPercForWarn, new KDLayout.Constraints(732, 237, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contsignDate.setBounds(new Rectangle(12, 137, 270, 19));
        mainPanel.add(contsignDate, new KDLayout.Constraints(12, 137, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contlandDeveloper.setBounds(new Rectangle(12, 87, 470, 19));
        mainPanel.add(contlandDeveloper, new KDLayout.Constraints(12, 87, 470, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contcontractType.setBounds(new Rectangle(12, 37, 470, 19));
        mainPanel.add(contcontractType, new KDLayout.Constraints(12, 37, 470, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contcostProperty.setBounds(new Rectangle(373, 262, 270, 19));
        mainPanel.add(contcostProperty, new KDLayout.Constraints(373, 262, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contcontractPropert.setBounds(new Rectangle(532, 112, 470, 19));
        mainPanel.add(contcontractPropert, new KDLayout.Constraints(532, 112, 470, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contcontractSource.setBounds(new Rectangle(373, 237, 270, 19));
        mainPanel.add(contcontractSource, new KDLayout.Constraints(373, 237, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contpartB.setBounds(new Rectangle(532, 87, 470, 19));
        mainPanel.add(contpartB, new KDLayout.Constraints(532, 87, 470, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contpartC.setBounds(new Rectangle(12, 112, 470, 19));
        mainPanel.add(contpartC, new KDLayout.Constraints(12, 112, 470, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contcontractName.setBounds(new Rectangle(12, 62, 990, 19));
        mainPanel.add(contcontractName, new KDLayout.Constraints(12, 62, 990, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDTabbedPane1.setBounds(new Rectangle(10, 381, 994, 239));
        mainPanel.add(kDTabbedPane1, new KDLayout.Constraints(10, 381, 994, 239, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM_SCALE | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        chkCostSplit.setBounds(new Rectangle(373, 288, 104, 19));
        mainPanel.add(chkCostSplit, new KDLayout.Constraints(373, 288, 104, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contExRate.setBounds(new Rectangle(732, 137, 270, 19));
        mainPanel.add(contExRate, new KDLayout.Constraints(732, 137, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contLocalAmount.setBounds(new Rectangle(732, 187, 270, 19));
        mainPanel.add(contLocalAmount, new KDLayout.Constraints(732, 187, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contGrtAmount.setBounds(new Rectangle(732, 212, 270, 19));
        mainPanel.add(contGrtAmount, new KDLayout.Constraints(732, 212, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contCurrency.setBounds(new Rectangle(373, 137, 270, 19));
        mainPanel.add(contCurrency, new KDLayout.Constraints(373, 137, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contRespDept.setBounds(new Rectangle(12, 187, 270, 19));
        mainPanel.add(contRespDept, new KDLayout.Constraints(12, 187, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contPayScale.setBounds(new Rectangle(733, 287, 270, 19));
        mainPanel.add(contPayScale, new KDLayout.Constraints(733, 287, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contStampTaxRate.setBounds(new Rectangle(733, 317, 270, 19));
        mainPanel.add(contStampTaxRate, new KDLayout.Constraints(733, 317, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contStampTaxAmt.setBounds(new Rectangle(733, 352, 270, 19));
        mainPanel.add(contStampTaxAmt, new KDLayout.Constraints(733, 352, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contRespPerson.setBounds(new Rectangle(12, 162, 270, 19));
        mainPanel.add(contRespPerson, new KDLayout.Constraints(12, 162, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contCreator.setBounds(new Rectangle(12, 262, 270, 19));
        mainPanel.add(contCreator, new KDLayout.Constraints(12, 262, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contOrg.setBounds(new Rectangle(12, 12, 470, 19));
        mainPanel.add(contOrg, new KDLayout.Constraints(12, 12, 470, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contProj.setBounds(new Rectangle(532, 12, 470, 19));
        mainPanel.add(contProj, new KDLayout.Constraints(532, 12, 470, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contGrtRate.setBounds(new Rectangle(373, 212, 270, 19));
        mainPanel.add(contGrtRate, new KDLayout.Constraints(373, 212, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contOldAmount.setBounds(new Rectangle(373, 162, 270, 19));
        mainPanel.add(contOldAmount, new KDLayout.Constraints(373, 162, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contOldLAmount.setBounds(new Rectangle(732, 162, 270, 19));
        mainPanel.add(contOldLAmount, new KDLayout.Constraints(732, 162, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contReviseReason.setBounds(new Rectangle(12, 341, 638, 36));
        mainPanel.add(contReviseReason, new KDLayout.Constraints(12, 341, 638, 36, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDLabelContainer1.setBounds(new Rectangle(12, 212, 270, 19));
        mainPanel.add(kDLabelContainer1, new KDLayout.Constraints(12, 212, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDLabelContainer2.setBounds(new Rectangle(12, 237, 270, 19));
        mainPanel.add(kDLabelContainer2, new KDLayout.Constraints(12, 237, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        chkIsPartAMaterialCon.setBounds(new Rectangle(517, 288, 124, 19));
        mainPanel.add(chkIsPartAMaterialCon, new KDLayout.Constraints(517, 288, 124, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        lblAttachmentContainer.setBounds(new Rectangle(12, 316, 462, 19));
        mainPanel.add(lblAttachmentContainer, new KDLayout.Constraints(12, 316, 462, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        btnViewAttachment.setBounds(new Rectangle(541, 313, 102, 22));
        mainPanel.add(btnViewAttachment, new KDLayout.Constraints(541, 313, 102, 22, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        //contCreateTime
        contCreateTime.setBoundEditor(kDDateCreateTime);
        //contNumber
        contNumber.setBoundEditor(txtNumber);
        //contamount
        contamount.setBoundEditor(txtamount);
        //contchgPercForWarn
        contchgPercForWarn.setBoundEditor(txtchgPercForWarn);
        //contpayPercForWarn
        contpayPercForWarn.setBoundEditor(txtpayPercForWarn);
        //contsignDate
        contsignDate.setBoundEditor(pksignDate);
        //contlandDeveloper
        contlandDeveloper.setBoundEditor(prmtlandDeveloper);
        //contcontractType
        contcontractType.setBoundEditor(prmtcontractType);
        //contcostProperty
        contcostProperty.setBoundEditor(costProperty);
        //contcontractPropert
        contcontractPropert.setBoundEditor(contractPropert);
        //contcontractSource
        contcontractSource.setBoundEditor(contractSource);
        //contpartB
        contpartB.setBoundEditor(prmtpartB);
        //contpartC
        contpartC.setBoundEditor(prmtpartC);
        //contcontractName
        contcontractName.setBoundEditor(txtcontractName);
        //kDTabbedPane1
        kDTabbedPane1.add(pnlInviteInfo, resHelper.getString("pnlInviteInfo.constraints"));
        kDTabbedPane1.add(pnlDetail, resHelper.getString("pnlDetail.constraints"));
        //pnlInviteInfo
        pnlInviteInfo.setLayout(null);        contRemark.setBounds(new Rectangle(300, 189, 270, 19));
        pnlInviteInfo.add(contRemark, null);
        contCoopLevel.setBounds(new Rectangle(45, 90, 270, 19));
        pnlInviteInfo.add(contCoopLevel, null);
        contPriceType.setBounds(new Rectangle(13, 191, 270, 19));
        pnlInviteInfo.add(contPriceType, null);
        chkIsSubMainContract.setBounds(new Rectangle(44, 34, 140, 19));
        pnlInviteInfo.add(chkIsSubMainContract, null);
        conEffectiveStartDate.setBounds(new Rectangle(370, 34, 270, 19));
        pnlInviteInfo.add(conEffectiveStartDate, null);
        conEffectiveEndDate.setBounds(new Rectangle(694, 34, 270, 19));
        pnlInviteInfo.add(conEffectiveEndDate, null);
        conMainContract.setBounds(new Rectangle(46, 57, 270, 19));
        pnlInviteInfo.add(conMainContract, null);
        kDScrollPane2.setBounds(new Rectangle(370, 90, 596, 49));
        pnlInviteInfo.add(kDScrollPane2, null);
        conInformation.setBounds(new Rectangle(370, 57, 270, 19));
        pnlInviteInfo.add(conInformation, null);
        contlowestPrice.setBounds(new Rectangle(8, 33, 270, 19));
        pnlInviteInfo.add(contlowestPrice, null);
        contlowerPrice.setBounds(new Rectangle(8, 60, 270, 19));
        pnlInviteInfo.add(contlowerPrice, null);
        contmiddlePrice.setBounds(new Rectangle(8, 87, 270, 19));
        pnlInviteInfo.add(contmiddlePrice, null);
        conthigherPrice.setBounds(new Rectangle(8, 114, 270, 19));
        pnlInviteInfo.add(conthigherPrice, null);
        conthighestPrice.setBounds(new Rectangle(8, 141, 270, 19));
        pnlInviteInfo.add(conthighestPrice, null);
        contbasePrice.setBounds(new Rectangle(8, 33, 270, 19));
        pnlInviteInfo.add(contbasePrice, null);
        contsecondPrice.setBounds(new Rectangle(8, 60, 270, 19));
        pnlInviteInfo.add(contsecondPrice, null);
        continviteType.setBounds(new Rectangle(636, 114, 346, 19));
        pnlInviteInfo.add(continviteType, null);
        contwinPrice.setBounds(new Rectangle(636, 33, 346, 19));
        pnlInviteInfo.add(contwinPrice, null);
        contwinUnit.setBounds(new Rectangle(636, 60, 346, 19));
        pnlInviteInfo.add(contwinUnit, null);
        contfileNo.setBounds(new Rectangle(636, 141, 346, 19));
        pnlInviteInfo.add(contfileNo, null);
        contquantity.setBounds(new Rectangle(636, 87, 346, 19));
        pnlInviteInfo.add(contquantity, null);
        lblPrice.setBounds(new Rectangle(169, 8, 58, 19));
        pnlInviteInfo.add(lblPrice, null);
        lblUnit.setBounds(new Rectangle(431, 8, 65, 19));
        pnlInviteInfo.add(lblUnit, null);
        prmtlowestPriceUnit.setBounds(new Rectangle(298, 33, 292, 19));
        pnlInviteInfo.add(prmtlowestPriceUnit, null);
        prmtlowerPriceUnit.setBounds(new Rectangle(298, 60, 292, 19));
        pnlInviteInfo.add(prmtlowerPriceUnit, null);
        prmtmiddlePriceUnit.setBounds(new Rectangle(298, 87, 292, 19));
        pnlInviteInfo.add(prmtmiddlePriceUnit, null);
        prmthigherPriceUnit.setBounds(new Rectangle(298, 114, 292, 19));
        pnlInviteInfo.add(prmthigherPriceUnit, null);
        prmthighestPriceUni.setBounds(new Rectangle(298, 141, 292, 19));
        pnlInviteInfo.add(prmthighestPriceUni, null);
        //contRemark
        contRemark.setBoundEditor(txtRemark);
        //contCoopLevel
        contCoopLevel.setBoundEditor(comboCoopLevel);
        //contPriceType
        contPriceType.setBoundEditor(comboPriceType);
        //conEffectiveStartDate
        conEffectiveStartDate.setBoundEditor(kDDatePicker1);
        conEffectiveStartDate.setBoundEditor(kdpEffectiveStartDate);
        //conEffectiveEndDate
        conEffectiveEndDate.setBoundEditor(kdpEffectiveEndDate);
        //conMainContract
        conMainContract.setBoundEditor(prmtMainContract);
        //kDScrollPane2
        kDScrollPane2.getViewport().add(txtInformation, null);
        //contlowestPrice
        contlowestPrice.setBoundEditor(txtlowestPrice);
        //contlowerPrice
        contlowerPrice.setBoundEditor(txtlowerPrice);
        //contmiddlePrice
        contmiddlePrice.setBoundEditor(txtmiddlePrice);
        //conthigherPrice
        conthigherPrice.setBoundEditor(txthigherPrice);
        //conthighestPrice
        conthighestPrice.setBoundEditor(txthighestPrice);
        //contbasePrice
        contbasePrice.setBoundEditor(txtbasePrice);
        //contsecondPrice
        contsecondPrice.setBoundEditor(txtsecondPrice);
        //continviteType
        continviteType.setBoundEditor(prmtinviteType);
        //contwinPrice
        contwinPrice.setBoundEditor(txtwinPrice);
        //contwinUnit
        contwinUnit.setBoundEditor(prmtwinUnit);
        //contfileNo
        contfileNo.setBoundEditor(txtfileNo);
        //contquantity
        contquantity.setBoundEditor(txtquantity);
        //pnlDetail
        pnlDetail.getViewport().add(tblDetail, null);
        //contExRate
        contExRate.setBoundEditor(txtExRate);
        //contLocalAmount
        contLocalAmount.setBoundEditor(txtLocalAmount);
        //contGrtAmount
        contGrtAmount.setBoundEditor(txtGrtAmount);
        //contCurrency
        contCurrency.setBoundEditor(comboCurrency);
        //contRespDept
        contRespDept.setBoundEditor(prmtRespDept);
        //contPayScale
        contPayScale.setBoundEditor(txtPayScale);
        //contStampTaxRate
        contStampTaxRate.setBoundEditor(txtStampTaxRate);
        //contStampTaxAmt
        contStampTaxAmt.setBoundEditor(txtStampTaxAmt);
        //contRespPerson
        contRespPerson.setBoundEditor(prmtRespPerson);
        //contCreator
        contCreator.setBoundEditor(txtCreator);
        //contOrg
        contOrg.setBoundEditor(txtOrg);
        //contProj
        contProj.setBoundEditor(txtProj);
        //contGrtRate
        contGrtRate.setBoundEditor(txtGrtRate);
        //contOldAmount
        contOldAmount.setBoundEditor(txtOldAmount);
        //contOldLAmount
        contOldLAmount.setBoundEditor(txtOldLAmount);
        //contReviseReason
        contReviseReason.setBoundEditor(kDScrollPane1);
        //kDScrollPane1
        kDScrollPane1.getViewport().add(txtReviseReason, null);
        //kDLabelContainer1
        kDLabelContainer1.setBoundEditor(pkbookedDate);
        //kDLabelContainer2
        kDLabelContainer2.setBoundEditor(cbPeriod);
        //lblAttachmentContainer
        lblAttachmentContainer.setBoundEditor(cmbAttachment);
        //ecoItemPanel
ecoItemPanel.setLayout(new BorderLayout(0, 0));        ecoItemPanel.add(kDContainer1, BorderLayout.CENTER);
        //kDContainer1
kDContainer1.getContentPane().setLayout(new BorderLayout(0, 0));        kDContainer1.getContentPane().add(kDSplitPane1, BorderLayout.CENTER);
        //kDSplitPane1
        kDSplitPane1.add(contPayItem, "top");
        kDSplitPane1.add(contBailItem, "bottom");
        //contPayItem
contPayItem.getContentPane().setLayout(new BorderLayout(0, 0));        contPayItem.getContentPane().add(tblEconItem, BorderLayout.CENTER);
        //contBailItem
        contBailItem.getContentPane().setLayout(new KDLayout());
        contBailItem.getContentPane().putClientProperty("OriginalBounds", new Rectangle(0, 0, 1011, 292));        contBailOriAmount.setBounds(new Rectangle(5, 8, 463, 19));
        contBailItem.getContentPane().add(contBailOriAmount, new KDLayout.Constraints(5, 8, 463, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contBailRate.setBounds(new Rectangle(544, 8, 450, 19));
        contBailItem.getContentPane().add(contBailRate, new KDLayout.Constraints(544, 8, 450, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        tblBail.setBounds(new Rectangle(3, 40, 995, 218));
        contBailItem.getContentPane().add(tblBail, new KDLayout.Constraints(3, 40, 995, 218, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        //contBailOriAmount
        contBailOriAmount.setBoundEditor(txtBailOriAmount);
        //contBailRate
        contBailRate.setBoundEditor(txtBailRate);

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
        this.menuBar.add(menuTable1);
        this.menuBar.add(menuTool);
        this.menuBar.add(menuWorkflow);
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
        menuEdit.add(separator1);
        menuEdit.add(menuItemCreateFrom);
        menuEdit.add(menuItemCreateTo);
        menuEdit.add(menuItemCopyFrom);
        menuEdit.add(separatorEdit1);
        menuEdit.add(separator2);
        //menuView
        menuView.add(menuItemFirst);
        menuView.add(menuItemPre);
        menuView.add(menuItemNext);
        menuView.add(menuItemLast);
        menuView.add(separator3);
        menuView.add(menuItemTraceUp);
        menuView.add(menuItemTraceDown);
        menuView.add(menuItemViewContent);
        //menuBiz
        menuBiz.add(menuItemCancelCancel);
        menuBiz.add(menuItemCancel);
        menuBiz.add(MenuItemVoucher);
        menuBiz.add(menuItemDelVoucher);
        menuBiz.add(menuItemAudit);
        menuBiz.add(menuItemUnAudit);
        menuBiz.add(menuItemSplit);
        //menuTable1
        menuTable1.add(menuItemAddLine);
        menuTable1.add(menuItemInsertLine);
        menuTable1.add(menuItemRemoveLine);
        //menuTool
        menuTool.add(menuItemSendMessage);
        menuTool.add(menuItemMsgFormat);
        menuTool.add(menuItemCalculator);
        //menuWorkflow
        menuWorkflow.add(menuItemStartWorkFlow);
        menuWorkflow.add(separatorWF1);
        menuWorkflow.add(menuItemViewSubmitProccess);
        menuWorkflow.add(menuItemViewDoProccess);
        menuWorkflow.add(MenuItemWFG);
        menuWorkflow.add(menuItemWorkFlowList);
        menuWorkflow.add(separatorWF2);
        menuWorkflow.add(menuItemMultiapprove);
        menuWorkflow.add(menuItemNextPerson);
        menuWorkflow.add(menuItemAuditResult);
        menuWorkflow.add(kDSeparator5);
        menuWorkflow.add(kDMenuItemSendMessage);
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
        this.toolBar.add(btnSave);
        this.toolBar.add(btnReset);
        this.toolBar.add(btnSubmit);
        this.toolBar.add(btnCopy);
        this.toolBar.add(btnCancelCancel);
        this.toolBar.add(btnRemove);
        this.toolBar.add(btnAttachment);
        this.toolBar.add(btnCancel);
        this.toolBar.add(btnPrint);
        this.toolBar.add(btnPrintPreview);
        this.toolBar.add(btnPageSetup);
        this.toolBar.add(separatorFW1);
        this.toolBar.add(btnFirst);
        this.toolBar.add(btnPre);
        this.toolBar.add(btnNext);
        this.toolBar.add(btnLast);
        this.toolBar.add(separatorFW2);
        this.toolBar.add(btnAddLine);
        this.toolBar.add(btnInsertLine);
        this.toolBar.add(btnRemoveLine);
        this.toolBar.add(separatorFW3);
        this.toolBar.add(btnCreateFrom);
        this.toolBar.add(btnSignature);
        this.toolBar.add(btnCopyFrom);
        this.toolBar.add(btnViewSignature);
        this.toolBar.add(btnTraceUp);
        this.toolBar.add(btnWorkFlowG);
        this.toolBar.add(btnTraceDown);
        this.toolBar.add(separatorFW4);
        this.toolBar.add(btnCreateTo);
        this.toolBar.add(btnAuditResult);
        this.toolBar.add(separatorFW7);
        this.toolBar.add(btnMultiapprove);
        this.toolBar.add(btnWFViewdoProccess);
        this.toolBar.add(btnWFViewSubmitProccess);
        this.toolBar.add(separatorFW5);
        this.toolBar.add(btnNextPerson);
        this.toolBar.add(separatorFW8);
        this.toolBar.add(btnAudit);
        this.toolBar.add(btnUnAudit);
        this.toolBar.add(separatorFW6);
        this.toolBar.add(separatorFW9);
        this.toolBar.add(btnVoucher);
        this.toolBar.add(btnDelVoucher);
        this.toolBar.add(btnCalculator);
        this.toolBar.add(btnSplit);
        this.toolBar.add(btnViewContent);
        this.toolBar.add(btnContractPlan);


    }

	//Regiester control's property binding.
	private void registerBindings(){
		dataBinder.registerBinding("isCoseSplit", boolean.class, this.chkCostSplit, "selected");
		dataBinder.registerBinding("isPartAMaterialCon", boolean.class, this.chkIsPartAMaterialCon, "selected");
		dataBinder.registerBinding("createTime", java.sql.Timestamp.class, this.kDDateCreateTime, "value");
		dataBinder.registerBinding("number", String.class, this.txtNumber, "text");
		dataBinder.registerBinding("revAmount", java.math.BigDecimal.class, this.txtamount, "value");
		dataBinder.registerBinding("chgPercForWarn", java.math.BigDecimal.class, this.txtchgPercForWarn, "value");
		dataBinder.registerBinding("payPercForWarn", java.math.BigDecimal.class, this.txtpayPercForWarn, "value");
		dataBinder.registerBinding("signDate", java.util.Date.class, this.pksignDate, "value");
		dataBinder.registerBinding("landDeveloper", com.kingdee.eas.fdc.basedata.LandDeveloperInfo.class, this.prmtlandDeveloper, "data");
		dataBinder.registerBinding("contractType", com.kingdee.eas.fdc.basedata.ContractTypeInfo.class, this.prmtcontractType, "data");
		dataBinder.registerBinding("costProperty", com.kingdee.eas.fdc.contract.CostPropertyEnum.class, this.costProperty, "selectedItem");
		dataBinder.registerBinding("contractPropert", com.kingdee.eas.fdc.contract.ContractPropertyEnum.class, this.contractPropert, "selectedItem");
		dataBinder.registerBinding("contractSourceId", com.kingdee.eas.fdc.basedata.ContractSourceInfo.class, this.contractSource, "data");
		dataBinder.registerBinding("partB", com.kingdee.eas.basedata.master.cssp.SupplierInfo.class, this.prmtpartB, "data");
		dataBinder.registerBinding("partC", com.kingdee.eas.basedata.master.cssp.SupplierInfo.class, this.prmtpartC, "data");
		dataBinder.registerBinding("name", String.class, this.txtcontractName, "text");
		dataBinder.registerBinding("isSubContract", boolean.class, this.chkIsSubMainContract, "selected");
		dataBinder.registerBinding("lowestPriceUnit", com.kingdee.eas.basedata.master.cssp.SupplierInfo.class, this.prmtlowestPriceUnit, "data");
		dataBinder.registerBinding("lowerPriceUnit", com.kingdee.eas.basedata.master.cssp.SupplierInfo.class, this.prmtlowerPriceUnit, "data");
		dataBinder.registerBinding("middlePriceUnit", com.kingdee.eas.basedata.master.cssp.SupplierInfo.class, this.prmtmiddlePriceUnit, "data");
		dataBinder.registerBinding("higherPriceUnit", com.kingdee.eas.basedata.master.cssp.SupplierInfo.class, this.prmthigherPriceUnit, "data");
		dataBinder.registerBinding("highestPriceUni", com.kingdee.eas.basedata.master.cssp.SupplierInfo.class, this.prmthighestPriceUni, "data");
		dataBinder.registerBinding("remark", String.class, this.txtRemark, "text");
		dataBinder.registerBinding("coopLevel", com.kingdee.eas.fdc.contract.CoopLevelEnum.class, this.comboCoopLevel, "selectedItem");
		dataBinder.registerBinding("priceType", com.kingdee.eas.fdc.contract.PriceTypeEnum.class, this.comboPriceType, "selectedItem");
		dataBinder.registerBinding("effectiveStartDate", java.util.Date.class, this.kdpEffectiveStartDate, "value");
		dataBinder.registerBinding("effectiveEndDate", java.util.Date.class, this.kdpEffectiveEndDate, "value");
		dataBinder.registerBinding("mainContract", com.kingdee.eas.fdc.contract.ContractBillInfo.class, this.prmtMainContract, "data");
		dataBinder.registerBinding("information", String.class, this.txtInformation, "text");
		dataBinder.registerBinding("lowestPrice", java.math.BigDecimal.class, this.txtlowestPrice, "value");
		dataBinder.registerBinding("lowerPrice", java.math.BigDecimal.class, this.txtlowerPrice, "value");
		dataBinder.registerBinding("middlePrice", java.math.BigDecimal.class, this.txtmiddlePrice, "value");
		dataBinder.registerBinding("higherPrice", java.math.BigDecimal.class, this.txthigherPrice, "value");
		dataBinder.registerBinding("highestPrice", java.math.BigDecimal.class, this.txthighestPrice, "value");
		dataBinder.registerBinding("basePrice", java.math.BigDecimal.class, this.txtbasePrice, "value");
		dataBinder.registerBinding("secondPrice", java.math.BigDecimal.class, this.txtsecondPrice, "value");
		dataBinder.registerBinding("inviteType", com.kingdee.eas.fdc.basedata.InviteTypeInfo.class, this.prmtinviteType, "data");
		dataBinder.registerBinding("winPrice", java.math.BigDecimal.class, this.txtwinPrice, "value");
		dataBinder.registerBinding("winUnit", com.kingdee.eas.basedata.master.cssp.SupplierInfo.class, this.prmtwinUnit, "data");
		dataBinder.registerBinding("fileNo", String.class, this.txtfileNo, "text");
		dataBinder.registerBinding("quantity", java.math.BigDecimal.class, this.txtquantity, "value");
		dataBinder.registerBinding("exRate", java.math.BigDecimal.class, this.txtExRate, "value");
		dataBinder.registerBinding("revLocalAmount", java.math.BigDecimal.class, this.txtLocalAmount, "value");
		dataBinder.registerBinding("grtAmount", java.math.BigDecimal.class, this.txtGrtAmount, "value");
		dataBinder.registerBinding("currency", com.kingdee.eas.basedata.assistant.CurrencyInfo.class, this.comboCurrency, "selectedItem");
		dataBinder.registerBinding("respDept", com.kingdee.eas.basedata.org.AdminOrgUnitInfo.class, this.prmtRespDept, "data");
		dataBinder.registerBinding("payScale", java.math.BigDecimal.class, this.txtPayScale, "value");
		dataBinder.registerBinding("stampTaxRate", java.math.BigDecimal.class, this.txtStampTaxRate, "value");
		dataBinder.registerBinding("stampTaxAmt", java.math.BigDecimal.class, this.txtStampTaxAmt, "value");
		dataBinder.registerBinding("respPerson", com.kingdee.eas.basedata.person.PersonInfo.class, this.prmtRespPerson, "data");
		dataBinder.registerBinding("creator.name", String.class, this.txtCreator, "text");
		dataBinder.registerBinding("grtRate", java.math.BigDecimal.class, this.txtGrtRate, "value");
		dataBinder.registerBinding("originalAmount", java.math.BigDecimal.class, this.txtOldAmount, "value");
		dataBinder.registerBinding("amount", java.math.BigDecimal.class, this.txtOldLAmount, "value");
		dataBinder.registerBinding("reviseReason", String.class, this.txtReviseReason, "text");
		dataBinder.registerBinding("bookedDate", java.util.Date.class, this.pkbookedDate, "value");
		dataBinder.registerBinding("period", com.kingdee.eas.basedata.assistant.PeriodInfo.class, this.cbPeriod, "data");
		dataBinder.registerBinding("payItems.payItemDate", java.util.Date.class, this.tblEconItem, "date.text");
		dataBinder.registerBinding("payItems.paymentType", com.kingdee.eas.fdc.basedata.PaymentTypeInfo.class, this.tblEconItem, "payType.text");
		dataBinder.registerBinding("payItems.payCondition", String.class, this.tblEconItem, "payCondition.text");
		dataBinder.registerBinding("payItems.prop", java.math.BigDecimal.class, this.tblEconItem, "payRate.text");
		dataBinder.registerBinding("payItems.amount", java.math.BigDecimal.class, this.tblEconItem, "payAmount.text");
		dataBinder.registerBinding("payItems.desc", String.class, this.tblEconItem, "desc.text");
		dataBinder.registerBinding("payItems", com.kingdee.eas.fdc.contract.ContractRevisePayItemInfo.class, this.tblEconItem, "userObject");
		dataBinder.registerBinding("bail.entry.bailDate", java.util.Date.class, this.tblBail, "bailDate.text");
		dataBinder.registerBinding("bail.entry.bailConditon", String.class, this.tblBail, "bailCondition.text");
		dataBinder.registerBinding("bail.entry.prop", java.math.BigDecimal.class, this.tblBail, "bailRate.text");
		dataBinder.registerBinding("bail.entry.amount", java.math.BigDecimal.class, this.tblBail, "bailAmount.text");
		dataBinder.registerBinding("bail.entry.desc", String.class, this.tblBail, "desc.text");
		dataBinder.registerBinding("bail.entry", com.kingdee.eas.fdc.contract.ContractReviseBailEntryInfo.class, this.tblBail, "userObject");
		dataBinder.registerBinding("bail.amount", java.math.BigDecimal.class, this.txtBailOriAmount, "value");
		dataBinder.registerBinding("bail.prop", java.math.BigDecimal.class, this.txtBailRate, "value");		
	}
	//Regiester UI State
	private void registerUIState(){					 	        		
	        getActionManager().registerUIState(STATUS_FINDVIEW, this.actionAudit, ActionStateConst.DISABLED);					 	        		
	        getActionManager().registerUIState(STATUS_FINDVIEW, this.actionUnAudit, ActionStateConst.DISABLED);					 	        		
	        getActionManager().registerUIState(STATUS_FINDVIEW, this.actionSplit, ActionStateConst.DISABLED);		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.fdc.contract.app.ContractBillReviseEditUIHandler";
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
        this.editData = (com.kingdee.eas.fdc.contract.ContractBillReviseInfo)ov;
    }
			protected com.kingdee.eas.basedata.org.OrgType getMainBizOrgType() {
			return com.kingdee.eas.basedata.org.OrgType.getEnum("CostCenter");
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
		getValidateHelper().registerBindProperty("isCoseSplit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("isPartAMaterialCon", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("createTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("number", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("revAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("chgPercForWarn", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("payPercForWarn", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("signDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("landDeveloper", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("contractType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("costProperty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("contractPropert", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("contractSourceId", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("partB", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("partC", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("name", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("isSubContract", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("lowestPriceUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("lowerPriceUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("middlePriceUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("higherPriceUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("highestPriceUni", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("remark", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("coopLevel", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("priceType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("effectiveStartDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("effectiveEndDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("mainContract", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("information", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("lowestPrice", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("lowerPrice", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("middlePrice", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("higherPrice", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("highestPrice", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("basePrice", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("secondPrice", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("inviteType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("winPrice", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("winUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("fileNo", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("quantity", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("exRate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("revLocalAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("grtAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("currency", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("respDept", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("payScale", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("stampTaxRate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("stampTaxAmt", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("respPerson", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("creator.name", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("grtRate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("originalAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("amount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("reviseReason", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("bookedDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("period", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("payItems.payItemDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("payItems.paymentType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("payItems.payCondition", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("payItems.prop", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("payItems.amount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("payItems.desc", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("payItems", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("bail.entry.bailDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("bail.entry.bailConditon", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("bail.entry.prop", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("bail.entry.amount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("bail.entry.desc", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("bail.entry", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("bail.amount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("bail.prop", ValidateHelper.ON_SAVE);    		
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
        } else if (STATUS_FINDVIEW.equals(this.oprtState)) {
		            this.actionAudit.setVisible(false);
		            this.actionAudit.setEnabled(false);
		            this.actionUnAudit.setVisible(false);
		            this.actionUnAudit.setEnabled(false);
		            this.actionSplit.setEnabled(false);
        }
    }

    /**
     * output chkCostSplit_mouseClicked method
     */
    protected void chkCostSplit_mouseClicked(java.awt.event.MouseEvent e) throws Exception
    {
    }

    /**
     * output txtamount_dataChanged method
     */
    protected void txtamount_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output txtamount_focusGained method
     */
    protected void txtamount_focusGained(java.awt.event.FocusEvent e) throws Exception
    {
    }

    /**
     * output prmtcontractType_dataChanged method
     */
    protected void prmtcontractType_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output prmtcontractType_willShow method
     */
    protected void prmtcontractType_willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) throws Exception
    {
    }

    /**
     * output costProperty_itemStateChanged method
     */
    protected void costProperty_itemStateChanged(java.awt.event.ItemEvent e) throws Exception
    {
    }

    /**
     * output contractPropert_itemStateChanged method
     */
    protected void contractPropert_itemStateChanged(java.awt.event.ItemEvent e) throws Exception
    {
    }

    /**
     * output contractSource_dataChanged method
     */
    protected void contractSource_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output contractSource_willShow method
     */
    protected void contractSource_willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) throws Exception
    {
    }

    /**
     * output txtExRate_dataChanged method
     */
    protected void txtExRate_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output comboCurrency_itemStateChanged method
     */
    protected void comboCurrency_itemStateChanged(java.awt.event.ItemEvent e) throws Exception
    {
    }

    /**
     * output txtStampTaxRate_dataChanged method
     */
    protected void txtStampTaxRate_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output txtGrtRate_dataChanged method
     */
    protected void txtGrtRate_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output pkbookedDate_dataChanged method
     */
    protected void pkbookedDate_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output tblEconItem_editStopped method
     */
    protected void tblEconItem_editStopped(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) throws Exception
    {
    }

    /**
     * output tblEconItem_tableClicked method
     */
    protected void tblEconItem_tableClicked(com.kingdee.bos.ctrl.kdf.table.event.KDTMouseEvent e) throws Exception
    {
    }

    /**
     * output tblBail_editStopped method
     */
    protected void tblBail_editStopped(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) throws Exception
    {
    }

    /**
     * output tblBail_tableClicked method
     */
    protected void tblBail_tableClicked(com.kingdee.bos.ctrl.kdf.table.event.KDTMouseEvent e) throws Exception
    {
    }

    /**
     * output txtBailOriAmount_dataChanged method
     */
    protected void txtBailOriAmount_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output txtBailRate_dataChanged method
     */
    protected void txtBailRate_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output getSelectors method
     */
    public SelectorItemCollection getSelectors()
    {
        SelectorItemCollection sic = new SelectorItemCollection();
        sic.add(new SelectorItemInfo("isCoseSplit"));
        sic.add(new SelectorItemInfo("isPartAMaterialCon"));
        sic.add(new SelectorItemInfo("createTime"));
        sic.add(new SelectorItemInfo("number"));
        sic.add(new SelectorItemInfo("revAmount"));
        sic.add(new SelectorItemInfo("chgPercForWarn"));
        sic.add(new SelectorItemInfo("payPercForWarn"));
        sic.add(new SelectorItemInfo("signDate"));
        sic.add(new SelectorItemInfo("landDeveloper.*"));
        sic.add(new SelectorItemInfo("contractType.*"));
        sic.add(new SelectorItemInfo("costProperty"));
        sic.add(new SelectorItemInfo("contractPropert"));
        sic.add(new SelectorItemInfo("contractSourceId.*"));
        sic.add(new SelectorItemInfo("partB.*"));
        sic.add(new SelectorItemInfo("partC.*"));
        sic.add(new SelectorItemInfo("name"));
        sic.add(new SelectorItemInfo("isSubContract"));
        sic.add(new SelectorItemInfo("lowestPriceUnit.*"));
        sic.add(new SelectorItemInfo("lowerPriceUnit.*"));
        sic.add(new SelectorItemInfo("middlePriceUnit.*"));
        sic.add(new SelectorItemInfo("higherPriceUnit.*"));
        sic.add(new SelectorItemInfo("highestPriceUni.*"));
        sic.add(new SelectorItemInfo("remark"));
        sic.add(new SelectorItemInfo("coopLevel"));
        sic.add(new SelectorItemInfo("priceType"));
        sic.add(new SelectorItemInfo("effectiveStartDate"));
        sic.add(new SelectorItemInfo("effectiveEndDate"));
        sic.add(new SelectorItemInfo("mainContract.*"));
        sic.add(new SelectorItemInfo("information"));
        sic.add(new SelectorItemInfo("lowestPrice"));
        sic.add(new SelectorItemInfo("lowerPrice"));
        sic.add(new SelectorItemInfo("middlePrice"));
        sic.add(new SelectorItemInfo("higherPrice"));
        sic.add(new SelectorItemInfo("highestPrice"));
        sic.add(new SelectorItemInfo("basePrice"));
        sic.add(new SelectorItemInfo("secondPrice"));
        sic.add(new SelectorItemInfo("inviteType.*"));
        sic.add(new SelectorItemInfo("winPrice"));
        sic.add(new SelectorItemInfo("winUnit.*"));
        sic.add(new SelectorItemInfo("fileNo"));
        sic.add(new SelectorItemInfo("quantity"));
        sic.add(new SelectorItemInfo("exRate"));
        sic.add(new SelectorItemInfo("revLocalAmount"));
        sic.add(new SelectorItemInfo("grtAmount"));
        sic.add(new SelectorItemInfo("currency"));
        sic.add(new SelectorItemInfo("respDept.*"));
        sic.add(new SelectorItemInfo("payScale"));
        sic.add(new SelectorItemInfo("stampTaxRate"));
        sic.add(new SelectorItemInfo("stampTaxAmt"));
        sic.add(new SelectorItemInfo("respPerson.*"));
        sic.add(new SelectorItemInfo("creator.name"));
        sic.add(new SelectorItemInfo("grtRate"));
        sic.add(new SelectorItemInfo("originalAmount"));
        sic.add(new SelectorItemInfo("amount"));
        sic.add(new SelectorItemInfo("reviseReason"));
        sic.add(new SelectorItemInfo("bookedDate"));
        sic.add(new SelectorItemInfo("period.*"));
    sic.add(new SelectorItemInfo("payItems.payItemDate"));
        sic.add(new SelectorItemInfo("payItems.paymentType.*"));
//        sic.add(new SelectorItemInfo("payItems.paymentType.number"));
    sic.add(new SelectorItemInfo("payItems.payCondition"));
    sic.add(new SelectorItemInfo("payItems.prop"));
    sic.add(new SelectorItemInfo("payItems.amount"));
    sic.add(new SelectorItemInfo("payItems.desc"));
        sic.add(new SelectorItemInfo("payItems.*"));
//        sic.add(new SelectorItemInfo("payItems.number"));
    sic.add(new SelectorItemInfo("bail.entry.bailDate"));
    sic.add(new SelectorItemInfo("bail.entry.bailConditon"));
    sic.add(new SelectorItemInfo("bail.entry.prop"));
    sic.add(new SelectorItemInfo("bail.entry.amount"));
    sic.add(new SelectorItemInfo("bail.entry.desc"));
        sic.add(new SelectorItemInfo("bail.entry.*"));
//        sic.add(new SelectorItemInfo("bail.entry.number"));
        sic.add(new SelectorItemInfo("bail.amount"));
        sic.add(new SelectorItemInfo("bail.prop"));
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
     * output actionPrint_actionPerformed method
     */
    public void actionPrint_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionPrint_actionPerformed(e);
    }
    	

    /**
     * output actionPrintPreview_actionPerformed method
     */
    public void actionPrintPreview_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionPrintPreview_actionPerformed(e);
    }
    	

    /**
     * output actionAudit_actionPerformed method
     */
    public void actionAudit_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionAudit_actionPerformed(e);
    }
    	

    /**
     * output actionUnAudit_actionPerformed method
     */
    public void actionUnAudit_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionUnAudit_actionPerformed(e);
    }
    	

    /**
     * output actionSplit_actionPerformed method
     */
    public void actionSplit_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionViewContent_actionPerformed method
     */
    public void actionViewContent_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionContractPlan_actionPerformed method
     */
    public void actionContractPlan_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionViewAttachment_actionPerformed method
     */
    public void actionViewAttachment_actionPerformed(ActionEvent e) throws Exception
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
	public RequestContext prepareActionPrint(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionPrint(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionPrint() {
    	return false;
    }
	public RequestContext prepareActionPrintPreview(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionPrintPreview(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionPrintPreview() {
    	return false;
    }
	public RequestContext prepareActionAudit(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionAudit(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionAudit() {
    	return false;
    }
	public RequestContext prepareActionUnAudit(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionUnAudit(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionUnAudit() {
    	return false;
    }
	public RequestContext prepareActionSplit(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionSplit() {
    	return false;
    }
	public RequestContext prepareActionViewContent(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionViewContent() {
    	return false;
    }
	public RequestContext prepareActionContractPlan(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionContractPlan() {
    	return false;
    }
	public RequestContext prepareActionViewAttachment(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionViewAttachment() {
    	return false;
    }

    /**
     * output ActionSplit class
     */     
    protected class ActionSplit extends ItemAction {     
    
        public ActionSplit()
        {
            this(null);
        }

        public ActionSplit(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionSplit.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionSplit.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionSplit.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractContractBillReviseEditUI.this, "ActionSplit", "actionSplit_actionPerformed", e);
        }
    }

    /**
     * output ActionViewContent class
     */     
    protected class ActionViewContent extends ItemAction {     
    
        public ActionViewContent()
        {
            this(null);
        }

        public ActionViewContent(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionViewContent.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionViewContent.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionViewContent.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractContractBillReviseEditUI.this, "ActionViewContent", "actionViewContent_actionPerformed", e);
        }
    }

    /**
     * output ActionContractPlan class
     */     
    protected class ActionContractPlan extends ItemAction {     
    
        public ActionContractPlan()
        {
            this(null);
        }

        public ActionContractPlan(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionContractPlan.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionContractPlan.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionContractPlan.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractContractBillReviseEditUI.this, "ActionContractPlan", "actionContractPlan_actionPerformed", e);
        }
    }

    /**
     * output ActionViewAttachment class
     */     
    protected class ActionViewAttachment extends ItemAction {     
    
        public ActionViewAttachment()
        {
            this(null);
        }

        public ActionViewAttachment(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionViewAttachment.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionViewAttachment.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionViewAttachment.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractContractBillReviseEditUI.this, "ActionViewAttachment", "actionViewAttachment_actionPerformed", e);
        }
    }

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.fdc.contract.client", "ContractBillReviseEditUI");
    }
    /**
     * output isBindWorkFlow method
     */
    public boolean isBindWorkFlow()
    {
        return true;
    }

    /**
     * output getEditUIName method
     */
    protected String getEditUIName()
    {
        return com.kingdee.eas.fdc.contract.client.ContractBillEditUI.class.getName();
    }

    /**
     * output getBizInterface method
     */
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return com.kingdee.eas.fdc.contract.ContractBillFactory.getRemoteInstance();
    }

    /**
     * output createNewData method
     */
    protected IObjectValue createNewData()
    {
        com.kingdee.eas.fdc.contract.ContractBillInfo objectValue = new com.kingdee.eas.fdc.contract.ContractBillInfo();		
        return objectValue;
    }




}