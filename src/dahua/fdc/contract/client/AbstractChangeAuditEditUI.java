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
public abstract class AbstractChangeAuditEditUI extends com.kingdee.eas.fdc.basedata.client.FDCBillEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractChangeAuditEditUI.class);
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCreator;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCreateTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contNumber;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contAuditor;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contName;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contAuditTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contAuditType;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contChangeReason;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contChangeState;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contUrgentDegree;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contJobType;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contSpecialtyType;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contChangeSubject;
    protected com.kingdee.bos.ctrl.swing.KDTabbedPane tbpChangAudit;
    protected com.kingdee.bos.ctrl.swing.KDContainer contAheadDisPatch;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contOrg;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contConductDept;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer1;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer2;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contOffer;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contReaDesc;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contConstrUnit;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contDesignUnit;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contConstrSite;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contConductUnit;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer lblAttachmentContainer;
    protected com.kingdee.bos.ctrl.swing.KDButton btnViewAttachment;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chkIsImportChange;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer conAttenTwo;
    protected com.kingdee.bos.ctrl.swing.KDButton viewAttenTwo;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtSpecialtyType;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPanel1;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCurProject;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtCreator;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkCreateTime;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtNumber;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtAuditor;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtName;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkAuditTime;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtAuditType;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtChangeReason;
    protected com.kingdee.bos.ctrl.swing.KDComboBox comboChangeState;
    protected com.kingdee.bos.ctrl.swing.KDComboBox comboUrgentDegree;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtJobType;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtSpecialtyType;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtChangeSubject;
    protected com.kingdee.bos.ctrl.swing.KDPanel pnlContent;
    protected com.kingdee.bos.ctrl.swing.KDPanel pnlSupp;
    protected com.kingdee.bos.ctrl.swing.KDContainer ctnEntrys;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtEntrys;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contGraphCount;
    protected com.kingdee.bos.ctrl.swing.KDComboBox comboGraphCount;
    protected com.kingdee.bos.ctrl.swing.KDContainer ctnSuppEntrys;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtSuppEntry;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contTotalCost;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contAmoutA;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contDutyAmout;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contNoUse;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contReason;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox cbxNoUse;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtTotalCost;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtAmountA;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtDutyAmount;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtNoUse;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox bmptResaon;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contAheadReason;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contConnectType;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contValidator;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtAheadReason;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtConnectType;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtValidator;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtOrg;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtConductDept;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkbookedDate;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox cbPeriod;
    protected com.kingdee.bos.ctrl.swing.KDComboBox comboOffer;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizMultiLangArea txtReaDesc;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtConstrUnit;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtDesignUnit;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtConstrSite;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtConductUnit;
    protected com.kingdee.bos.ctrl.swing.KDComboBox cmbAttachment;
    protected com.kingdee.bos.ctrl.swing.KDComboBox cmbAttenTwo;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contbillType;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer3;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contputForwardTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contquality;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer conttimeLi;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contcost;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contsale;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contreworkVisa;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contcontractAmPro;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer conttotalChangeAmount;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contdesignChangeAmount;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contchangeEstimate;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtSplitEntry;
	protected com.kingdee.eas.framework.client.multiDetail.DetailPanel kdtSplitEntry_detailPanel = null;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contimgNumber;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contcompDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contappliAmount;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contpunisAmount;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contapprovedAmount;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contconstructionHead;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contworkNote;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contBIMUDF0052;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contdelyDay;
    protected com.kingdee.bos.ctrl.swing.KDComboBox billType;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtSpecialtyType;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkputForwardTime;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtquality;
    protected com.kingdee.bos.ctrl.swing.KDTextField txttimeLi;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtcost;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtsale;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtreworkVisa;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtcontractAmPro;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txttotalChangeAmount;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtdesignChangeAmount;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtchangeEstimate;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtimgNumber;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkcompDate;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtappliAmount;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtpunisAmount;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtapprovedAmount;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtconstructionHead;
    protected com.kingdee.bos.ctrl.swing.KDScrollPane scrollPaneworkNote;
    protected com.kingdee.bos.ctrl.swing.KDTextArea txtworkNote;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtBIMUDF0052;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtdelyDay;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtCurProject;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnViewContract;
    protected javax.swing.JToolBar.Separator separator4;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnAttenTwo;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnRegister;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnDisPatch;
    protected com.kingdee.bos.ctrl.swing.KDMenuItem menuItemRegister;
    protected com.kingdee.bos.ctrl.swing.KDMenuItem menuItemDispatch;
    protected com.kingdee.bos.ctrl.swing.KDMenuItem menuViewContract;
    protected com.kingdee.eas.fdc.contract.ChangeAuditBillInfo editData = null;
    protected ActionAddSupp actionAddSupp = null;
    protected ActionDelSupp actionDelSupp = null;
    protected ActionRegister actionRegister = null;
    protected ActionDisPatch actionDisPatch = null;
    protected ActionAheadDisPatch actionAheadDisPatch = null;
    protected ActionViewAttachment actionViewAttachment = null;
    protected ActionViewContract actionViewContract = null;
    protected ActionAttenTwo actionAttenTwo = null;
    protected ActionViewTwo actionViewTwo = null;
    /**
     * output class constructor
     */
    public AbstractChangeAuditEditUI() throws Exception
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
        this.resHelper = new ResourceBundleHelper(AbstractChangeAuditEditUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        //actionSave
        String _tempStr = null;
        actionSave.setEnabled(true);
        actionSave.setDaemonRun(false);

        actionSave.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("shift ctrl S"));
        _tempStr = resHelper.getString("ActionSave.SHORT_DESCRIPTION");
        actionSave.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionSave.LONG_DESCRIPTION");
        actionSave.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionSave.NAME");
        actionSave.putValue(ItemAction.NAME, _tempStr);
        this.actionSave.setBindWorkFlow(true);
         this.actionSave.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionSave.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionSave.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionSubmit
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
        this.actionSubmit.setExtendProperty("canForewarn", "true");
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.ForewarnService());
        //actionAttachment
        actionAttachment.setEnabled(true);
        actionAttachment.setDaemonRun(false);

        actionAttachment.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl shift H"));
        _tempStr = resHelper.getString("ActionAttachment.SHORT_DESCRIPTION");
        actionAttachment.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionAttachment.LONG_DESCRIPTION");
        actionAttachment.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionAttachment.NAME");
        actionAttachment.putValue(ItemAction.NAME, _tempStr);
         this.actionAttachment.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionAudit
        actionAudit.setEnabled(false);
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
        //actionAddSupp
        this.actionAddSupp = new ActionAddSupp(this);
        getActionManager().registerAction("actionAddSupp", actionAddSupp);
         this.actionAddSupp.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionDelSupp
        this.actionDelSupp = new ActionDelSupp(this);
        getActionManager().registerAction("actionDelSupp", actionDelSupp);
         this.actionDelSupp.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionRegister
        this.actionRegister = new ActionRegister(this);
        getActionManager().registerAction("actionRegister", actionRegister);
        this.actionRegister.setBindWorkFlow(true);
         this.actionRegister.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionDisPatch
        this.actionDisPatch = new ActionDisPatch(this);
        getActionManager().registerAction("actionDisPatch", actionDisPatch);
         this.actionDisPatch.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionAheadDisPatch
        this.actionAheadDisPatch = new ActionAheadDisPatch(this);
        getActionManager().registerAction("actionAheadDisPatch", actionAheadDisPatch);
         this.actionAheadDisPatch.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionViewAttachment
        this.actionViewAttachment = new ActionViewAttachment(this);
        getActionManager().registerAction("actionViewAttachment", actionViewAttachment);
         this.actionViewAttachment.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionViewContract
        this.actionViewContract = new ActionViewContract(this);
        getActionManager().registerAction("actionViewContract", actionViewContract);
         this.actionViewContract.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionAttenTwo
        this.actionAttenTwo = new ActionAttenTwo(this);
        getActionManager().registerAction("actionAttenTwo", actionAttenTwo);
         this.actionAttenTwo.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionViewTwo
        this.actionViewTwo = new ActionViewTwo(this);
        getActionManager().registerAction("actionViewTwo", actionViewTwo);
         this.actionViewTwo.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        this.contCreator = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contCreateTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contNumber = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contAuditor = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contName = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contAuditTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contAuditType = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contChangeReason = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contChangeState = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contUrgentDegree = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contJobType = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contSpecialtyType = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contChangeSubject = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.tbpChangAudit = new com.kingdee.bos.ctrl.swing.KDTabbedPane();
        this.contAheadDisPatch = new com.kingdee.bos.ctrl.swing.KDContainer();
        this.contOrg = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contConductDept = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer1 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer2 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contOffer = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contReaDesc = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contConstrUnit = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contDesignUnit = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contConstrSite = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contConductUnit = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.lblAttachmentContainer = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.btnViewAttachment = new com.kingdee.bos.ctrl.swing.KDButton();
        this.chkIsImportChange = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.conAttenTwo = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.viewAttenTwo = new com.kingdee.bos.ctrl.swing.KDButton();
        this.kdtSpecialtyType = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.kDPanel1 = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.contCurProject = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.prmtCreator = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.pkCreateTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.prmtAuditor = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtName = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.pkAuditTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.prmtAuditType = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtChangeReason = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.comboChangeState = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.comboUrgentDegree = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.prmtJobType = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtSpecialtyType = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtChangeSubject = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.pnlContent = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.pnlSupp = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.ctnEntrys = new com.kingdee.bos.ctrl.swing.KDContainer();
        this.kdtEntrys = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.contGraphCount = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.comboGraphCount = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.ctnSuppEntrys = new com.kingdee.bos.ctrl.swing.KDContainer();
        this.kdtSuppEntry = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.contTotalCost = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contAmoutA = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contDutyAmout = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contNoUse = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contReason = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.cbxNoUse = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.txtTotalCost = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtAmountA = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtDutyAmount = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtNoUse = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.bmptResaon = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.contAheadReason = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contConnectType = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contValidator = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.txtAheadReason = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtConnectType = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtValidator = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtOrg = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.prmtConductDept = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.pkbookedDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.cbPeriod = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.comboOffer = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.txtReaDesc = new com.kingdee.bos.ctrl.extendcontrols.KDBizMultiLangArea();
        this.prmtConstrUnit = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtDesignUnit = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtConstrSite = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.prmtConductUnit = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.cmbAttachment = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.cmbAttenTwo = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.contbillType = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer3 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contputForwardTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contquality = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.conttimeLi = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contcost = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contsale = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contreworkVisa = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contcontractAmPro = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.conttotalChangeAmount = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contdesignChangeAmount = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contchangeEstimate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kdtSplitEntry = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.contimgNumber = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contcompDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contappliAmount = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contpunisAmount = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contapprovedAmount = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contconstructionHead = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contworkNote = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contBIMUDF0052 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contdelyDay = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.billType = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.txtSpecialtyType = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.pkputForwardTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtquality = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txttimeLi = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtcost = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtsale = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtreworkVisa = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtcontractAmPro = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txttotalChangeAmount = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtdesignChangeAmount = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtchangeEstimate = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtimgNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.pkcompDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtappliAmount = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtpunisAmount = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtapprovedAmount = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtconstructionHead = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.scrollPaneworkNote = new com.kingdee.bos.ctrl.swing.KDScrollPane();
        this.txtworkNote = new com.kingdee.bos.ctrl.swing.KDTextArea();
        this.prmtBIMUDF0052 = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtdelyDay = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.prmtCurProject = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.btnViewContract = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.separator4 = new javax.swing.JToolBar.Separator();
        this.btnAttenTwo = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnRegister = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnDisPatch = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.menuItemRegister = new com.kingdee.bos.ctrl.swing.KDMenuItem();
        this.menuItemDispatch = new com.kingdee.bos.ctrl.swing.KDMenuItem();
        this.menuViewContract = new com.kingdee.bos.ctrl.swing.KDMenuItem();
        this.contCreator.setName("contCreator");
        this.contCreateTime.setName("contCreateTime");
        this.contNumber.setName("contNumber");
        this.contAuditor.setName("contAuditor");
        this.contName.setName("contName");
        this.contAuditTime.setName("contAuditTime");
        this.contAuditType.setName("contAuditType");
        this.contChangeReason.setName("contChangeReason");
        this.contChangeState.setName("contChangeState");
        this.contUrgentDegree.setName("contUrgentDegree");
        this.contJobType.setName("contJobType");
        this.contSpecialtyType.setName("contSpecialtyType");
        this.contChangeSubject.setName("contChangeSubject");
        this.tbpChangAudit.setName("tbpChangAudit");
        this.contAheadDisPatch.setName("contAheadDisPatch");
        this.contOrg.setName("contOrg");
        this.contConductDept.setName("contConductDept");
        this.kDLabelContainer1.setName("kDLabelContainer1");
        this.kDLabelContainer2.setName("kDLabelContainer2");
        this.contOffer.setName("contOffer");
        this.contReaDesc.setName("contReaDesc");
        this.contConstrUnit.setName("contConstrUnit");
        this.contDesignUnit.setName("contDesignUnit");
        this.contConstrSite.setName("contConstrSite");
        this.contConductUnit.setName("contConductUnit");
        this.lblAttachmentContainer.setName("lblAttachmentContainer");
        this.btnViewAttachment.setName("btnViewAttachment");
        this.chkIsImportChange.setName("chkIsImportChange");
        this.conAttenTwo.setName("conAttenTwo");
        this.viewAttenTwo.setName("viewAttenTwo");
        this.kdtSpecialtyType.setName("kdtSpecialtyType");
        this.kDPanel1.setName("kDPanel1");
        this.contCurProject.setName("contCurProject");
        this.prmtCreator.setName("prmtCreator");
        this.pkCreateTime.setName("pkCreateTime");
        this.txtNumber.setName("txtNumber");
        this.prmtAuditor.setName("prmtAuditor");
        this.txtName.setName("txtName");
        this.pkAuditTime.setName("pkAuditTime");
        this.prmtAuditType.setName("prmtAuditType");
        this.prmtChangeReason.setName("prmtChangeReason");
        this.comboChangeState.setName("comboChangeState");
        this.comboUrgentDegree.setName("comboUrgentDegree");
        this.prmtJobType.setName("prmtJobType");
        this.prmtSpecialtyType.setName("prmtSpecialtyType");
        this.txtChangeSubject.setName("txtChangeSubject");
        this.pnlContent.setName("pnlContent");
        this.pnlSupp.setName("pnlSupp");
        this.ctnEntrys.setName("ctnEntrys");
        this.kdtEntrys.setName("kdtEntrys");
        this.contGraphCount.setName("contGraphCount");
        this.comboGraphCount.setName("comboGraphCount");
        this.ctnSuppEntrys.setName("ctnSuppEntrys");
        this.kdtSuppEntry.setName("kdtSuppEntry");
        this.contTotalCost.setName("contTotalCost");
        this.contAmoutA.setName("contAmoutA");
        this.contDutyAmout.setName("contDutyAmout");
        this.contNoUse.setName("contNoUse");
        this.contReason.setName("contReason");
        this.cbxNoUse.setName("cbxNoUse");
        this.txtTotalCost.setName("txtTotalCost");
        this.txtAmountA.setName("txtAmountA");
        this.txtDutyAmount.setName("txtDutyAmount");
        this.txtNoUse.setName("txtNoUse");
        this.bmptResaon.setName("bmptResaon");
        this.contAheadReason.setName("contAheadReason");
        this.contConnectType.setName("contConnectType");
        this.contValidator.setName("contValidator");
        this.txtAheadReason.setName("txtAheadReason");
        this.txtConnectType.setName("txtConnectType");
        this.txtValidator.setName("txtValidator");
        this.txtOrg.setName("txtOrg");
        this.prmtConductDept.setName("prmtConductDept");
        this.pkbookedDate.setName("pkbookedDate");
        this.cbPeriod.setName("cbPeriod");
        this.comboOffer.setName("comboOffer");
        this.txtReaDesc.setName("txtReaDesc");
        this.prmtConstrUnit.setName("prmtConstrUnit");
        this.prmtDesignUnit.setName("prmtDesignUnit");
        this.txtConstrSite.setName("txtConstrSite");
        this.prmtConductUnit.setName("prmtConductUnit");
        this.cmbAttachment.setName("cmbAttachment");
        this.cmbAttenTwo.setName("cmbAttenTwo");
        this.contbillType.setName("contbillType");
        this.kDLabelContainer3.setName("kDLabelContainer3");
        this.contputForwardTime.setName("contputForwardTime");
        this.contquality.setName("contquality");
        this.conttimeLi.setName("conttimeLi");
        this.contcost.setName("contcost");
        this.contsale.setName("contsale");
        this.contreworkVisa.setName("contreworkVisa");
        this.contcontractAmPro.setName("contcontractAmPro");
        this.conttotalChangeAmount.setName("conttotalChangeAmount");
        this.contdesignChangeAmount.setName("contdesignChangeAmount");
        this.contchangeEstimate.setName("contchangeEstimate");
        this.kdtSplitEntry.setName("kdtSplitEntry");
        this.contimgNumber.setName("contimgNumber");
        this.contcompDate.setName("contcompDate");
        this.contappliAmount.setName("contappliAmount");
        this.contpunisAmount.setName("contpunisAmount");
        this.contapprovedAmount.setName("contapprovedAmount");
        this.contconstructionHead.setName("contconstructionHead");
        this.contworkNote.setName("contworkNote");
        this.contBIMUDF0052.setName("contBIMUDF0052");
        this.contdelyDay.setName("contdelyDay");
        this.billType.setName("billType");
        this.txtSpecialtyType.setName("txtSpecialtyType");
        this.pkputForwardTime.setName("pkputForwardTime");
        this.txtquality.setName("txtquality");
        this.txttimeLi.setName("txttimeLi");
        this.txtcost.setName("txtcost");
        this.txtsale.setName("txtsale");
        this.txtreworkVisa.setName("txtreworkVisa");
        this.txtcontractAmPro.setName("txtcontractAmPro");
        this.txttotalChangeAmount.setName("txttotalChangeAmount");
        this.txtdesignChangeAmount.setName("txtdesignChangeAmount");
        this.txtchangeEstimate.setName("txtchangeEstimate");
        this.txtimgNumber.setName("txtimgNumber");
        this.pkcompDate.setName("pkcompDate");
        this.txtappliAmount.setName("txtappliAmount");
        this.txtpunisAmount.setName("txtpunisAmount");
        this.txtapprovedAmount.setName("txtapprovedAmount");
        this.txtconstructionHead.setName("txtconstructionHead");
        this.scrollPaneworkNote.setName("scrollPaneworkNote");
        this.txtworkNote.setName("txtworkNote");
        this.prmtBIMUDF0052.setName("prmtBIMUDF0052");
        this.txtdelyDay.setName("txtdelyDay");
        this.prmtCurProject.setName("prmtCurProject");
        this.btnViewContract.setName("btnViewContract");
        this.separator4.setName("separator4");
        this.btnAttenTwo.setName("btnAttenTwo");
        this.btnRegister.setName("btnRegister");
        this.btnDisPatch.setName("btnDisPatch");
        this.menuItemRegister.setName("menuItemRegister");
        this.menuItemDispatch.setName("menuItemDispatch");
        this.menuViewContract.setName("menuViewContract");
        // CoreUI		
        this.setPreferredSize(new Dimension(1013,629));		
        this.btnAttachment.setText(resHelper.getString("btnAttachment.text"));		
        this.btnAttachment.setToolTipText(resHelper.getString("btnAttachment.toolTipText"));		
        this.chkMenuItemSubmitAndPrint.setVisible(false);		
        this.chkMenuItemSubmitAndPrint.setEnabled(false);		
        this.MenuItemAttachment.setText(resHelper.getString("MenuItemAttachment.text"));		
        this.MenuItemAttachment.setToolTipText(resHelper.getString("MenuItemAttachment.toolTipText"));		
        this.btnAddLine.setEnabled(false);		
        this.btnAddLine.setVisible(false);		
        this.btnInsertLine.setEnabled(false);		
        this.btnInsertLine.setVisible(false);		
        this.btnRemoveLine.setEnabled(false);		
        this.btnRemoveLine.setVisible(false);		
        this.btnCreateFrom.setEnabled(false);		
        this.btnCreateFrom.setVisible(false);		
        this.btnTraceUp.setVisible(false);		
        this.btnTraceUp.setEnabled(false);
        // contCreator		
        this.contCreator.setBoundLabelText(resHelper.getString("contCreator.boundLabelText"));		
        this.contCreator.setBoundLabelLength(100);		
        this.contCreator.setBoundLabelUnderline(true);
        // contCreateTime		
        this.contCreateTime.setBoundLabelText(resHelper.getString("contCreateTime.boundLabelText"));		
        this.contCreateTime.setBoundLabelLength(100);		
        this.contCreateTime.setBoundLabelUnderline(true);
        // contNumber		
        this.contNumber.setBoundLabelText(resHelper.getString("contNumber.boundLabelText"));		
        this.contNumber.setBoundLabelLength(100);		
        this.contNumber.setBoundLabelUnderline(true);
        // contAuditor		
        this.contAuditor.setBoundLabelText(resHelper.getString("contAuditor.boundLabelText"));		
        this.contAuditor.setBoundLabelLength(100);		
        this.contAuditor.setBoundLabelUnderline(true);
        // contName		
        this.contName.setBoundLabelText(resHelper.getString("contName.boundLabelText"));		
        this.contName.setBoundLabelLength(100);		
        this.contName.setBoundLabelUnderline(true);
        // contAuditTime		
        this.contAuditTime.setBoundLabelText(resHelper.getString("contAuditTime.boundLabelText"));		
        this.contAuditTime.setBoundLabelLength(100);		
        this.contAuditTime.setBoundLabelUnderline(true);
        // contAuditType		
        this.contAuditType.setBoundLabelText(resHelper.getString("contAuditType.boundLabelText"));		
        this.contAuditType.setBoundLabelLength(100);		
        this.contAuditType.setBoundLabelUnderline(true);
        // contChangeReason		
        this.contChangeReason.setBoundLabelText(resHelper.getString("contChangeReason.boundLabelText"));		
        this.contChangeReason.setBoundLabelLength(100);		
        this.contChangeReason.setBoundLabelUnderline(true);
        // contChangeState		
        this.contChangeState.setBoundLabelText(resHelper.getString("contChangeState.boundLabelText"));		
        this.contChangeState.setBoundLabelLength(60);		
        this.contChangeState.setBoundLabelUnderline(true);
        // contUrgentDegree		
        this.contUrgentDegree.setBoundLabelText(resHelper.getString("contUrgentDegree.boundLabelText"));		
        this.contUrgentDegree.setBoundLabelLength(100);		
        this.contUrgentDegree.setBoundLabelUnderline(true);
        // contJobType		
        this.contJobType.setBoundLabelText(resHelper.getString("contJobType.boundLabelText"));		
        this.contJobType.setBoundLabelLength(100);		
        this.contJobType.setBoundLabelUnderline(true);
        // contSpecialtyType		
        this.contSpecialtyType.setBoundLabelText(resHelper.getString("contSpecialtyType.boundLabelText"));		
        this.contSpecialtyType.setBoundLabelLength(100);		
        this.contSpecialtyType.setBoundLabelUnderline(true);
        // contChangeSubject		
        this.contChangeSubject.setBoundLabelText(resHelper.getString("contChangeSubject.boundLabelText"));		
        this.contChangeSubject.setBoundLabelLength(100);		
        this.contChangeSubject.setBoundLabelUnderline(true);
        // tbpChangAudit
        // contAheadDisPatch		
        this.contAheadDisPatch.setTitle(resHelper.getString("contAheadDisPatch.title"));		
        this.contAheadDisPatch.setEnableActive(false);
        // contOrg		
        this.contOrg.setBoundLabelText(resHelper.getString("contOrg.boundLabelText"));		
        this.contOrg.setBoundLabelLength(100);		
        this.contOrg.setBoundLabelUnderline(true);
        // contConductDept		
        this.contConductDept.setBoundLabelText(resHelper.getString("contConductDept.boundLabelText"));		
        this.contConductDept.setBoundLabelLength(100);		
        this.contConductDept.setBoundLabelUnderline(true);
        // kDLabelContainer1		
        this.kDLabelContainer1.setBoundLabelText(resHelper.getString("kDLabelContainer1.boundLabelText"));		
        this.kDLabelContainer1.setBoundLabelLength(100);		
        this.kDLabelContainer1.setBoundLabelUnderline(true);
        // kDLabelContainer2		
        this.kDLabelContainer2.setBoundLabelText(resHelper.getString("kDLabelContainer2.boundLabelText"));		
        this.kDLabelContainer2.setBoundLabelUnderline(true);		
        this.kDLabelContainer2.setBoundLabelLength(100);
        // contOffer		
        this.contOffer.setBoundLabelText(resHelper.getString("contOffer.boundLabelText"));		
        this.contOffer.setBoundLabelLength(100);		
        this.contOffer.setBoundLabelUnderline(true);
        // contReaDesc		
        this.contReaDesc.setBoundLabelText(resHelper.getString("contReaDesc.boundLabelText"));		
        this.contReaDesc.setBoundLabelLength(100);		
        this.contReaDesc.setBoundLabelUnderline(true);
        // contConstrUnit		
        this.contConstrUnit.setBoundLabelText(resHelper.getString("contConstrUnit.boundLabelText"));		
        this.contConstrUnit.setBoundLabelLength(100);		
        this.contConstrUnit.setBoundLabelUnderline(true);
        // contDesignUnit		
        this.contDesignUnit.setBoundLabelText(resHelper.getString("contDesignUnit.boundLabelText"));		
        this.contDesignUnit.setBoundLabelLength(100);		
        this.contDesignUnit.setBoundLabelUnderline(true);
        // contConstrSite		
        this.contConstrSite.setBoundLabelText(resHelper.getString("contConstrSite.boundLabelText"));		
        this.contConstrSite.setBoundLabelLength(100);		
        this.contConstrSite.setBoundLabelUnderline(true);
        // contConductUnit		
        this.contConductUnit.setBoundLabelText(resHelper.getString("contConductUnit.boundLabelText"));		
        this.contConductUnit.setBoundLabelLength(100);		
        this.contConductUnit.setBoundLabelUnderline(true);
        // lblAttachmentContainer		
        this.lblAttachmentContainer.setBoundLabelText(resHelper.getString("lblAttachmentContainer.boundLabelText"));		
        this.lblAttachmentContainer.setBoundLabelLength(100);		
        this.lblAttachmentContainer.setBoundLabelUnderline(true);
        // btnViewAttachment
        this.btnViewAttachment.setAction((IItemAction)ActionProxyFactory.getProxy(actionViewAttachment, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnViewAttachment.setText(resHelper.getString("btnViewAttachment.text"));
        // chkIsImportChange		
        this.chkIsImportChange.setText(resHelper.getString("chkIsImportChange.text"));
        // conAttenTwo		
        this.conAttenTwo.setBoundLabelText(resHelper.getString("conAttenTwo.boundLabelText"));		
        this.conAttenTwo.setBoundLabelLength(100);
        // viewAttenTwo
        this.viewAttenTwo.setAction((IItemAction)ActionProxyFactory.getProxy(actionViewTwo, new Class[] { IItemAction.class }, getServiceContext()));		
        this.viewAttenTwo.setText(resHelper.getString("viewAttenTwo.text"));
        // kdtSpecialtyType
		String kdtSpecialtyTypeStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles /><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"id\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"specialtyTypeID\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header1\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{id}</t:Cell><t:Cell>$Resource{specialtyTypeID}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdtSpecialtyType.setFormatXml(resHelper.translateString("kdtSpecialtyType",kdtSpecialtyTypeStrXML));		
        this.kdtSpecialtyType.setVisible(false);

                this.kdtSpecialtyType.putBindContents("editData",new String[] {"id","specialtyType"});


        this.kdtSpecialtyType.checkParsed();
        // kDPanel1		
        this.kDPanel1.setBorder(new TitledBorder(BorderFactory.createEtchedBorder(new Color(255,255,255),new Color(148,145,140)), resHelper.getString("kDPanel1.border.title")));
        // contCurProject		
        this.contCurProject.setBoundLabelText(resHelper.getString("contCurProject.boundLabelText"));		
        this.contCurProject.setBoundLabelLength(100);		
        this.contCurProject.setBoundLabelUnderline(true);
        // prmtCreator		
        this.prmtCreator.setEnabled(false);		
        this.prmtCreator.setDisplayFormat("$name$");		
        this.prmtCreator.setEditFormat("$number$");		
        this.prmtCreator.setCommitFormat("$name$");
        // pkCreateTime		
        this.pkCreateTime.setEnabled(false);
        // txtNumber		
        this.txtNumber.setMaxLength(80);		
        this.txtNumber.setRequired(true);
        // prmtAuditor		
        this.prmtAuditor.setEnabled(false);		
        this.prmtAuditor.setDisplayFormat("$name$");		
        this.prmtAuditor.setEditFormat("$number$");		
        this.prmtAuditor.setCommitFormat("$name$");
        // txtName		
        this.txtName.setRequired(true);
        // pkAuditTime		
        this.pkAuditTime.setEnabled(false);
        // prmtAuditType		
        this.prmtAuditType.setDisplayFormat("$name$");		
        this.prmtAuditType.setEditFormat("$number$");		
        this.prmtAuditType.setCommitFormat("$number$");		
        this.prmtAuditType.setRequired(true);		
        this.prmtAuditType.setQueryInfo("com.kingdee.eas.fdc.basedata.app.F7ChangeTypeQuery");		
        this.prmtAuditType.setEditable(true);
        this.prmtAuditType.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    prmtAuditType_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // prmtChangeReason		
        this.prmtChangeReason.setEditable(true);		
        this.prmtChangeReason.setDisplayFormat("$name$");		
        this.prmtChangeReason.setEditFormat("$number$");		
        this.prmtChangeReason.setCommitFormat("$number$");		
        this.prmtChangeReason.setQueryInfo("com.kingdee.eas.fdc.basedata.app.F7ChangeReasonQuery");		
        this.prmtChangeReason.setRequired(true);
        // comboChangeState		
        this.comboChangeState.addItems(EnumUtils.getEnumList("com.kingdee.eas.fdc.contract.ChangeBillStateEnum").toArray());		
        this.comboChangeState.setEnabled(false);
        // comboUrgentDegree		
        this.comboUrgentDegree.addItems(EnumUtils.getEnumList("com.kingdee.eas.fdc.contract.ChangeUrgentDegreeEnum").toArray());		
        this.comboUrgentDegree.setRequired(true);
        // prmtJobType		
        this.prmtJobType.setDisplayFormat("$name$");		
        this.prmtJobType.setEditFormat("$number$");		
        this.prmtJobType.setRequired(true);		
        this.prmtJobType.setQueryInfo("com.kingdee.eas.fdc.basedata.app.F7JobTypeQuery");		
        this.prmtJobType.setEditable(true);		
        this.prmtJobType.setCommitFormat("$number$");
        // prmtSpecialtyType		
        this.prmtSpecialtyType.setDisplayFormat("$name$");		
        this.prmtSpecialtyType.setEditFormat("$number$");		
        this.prmtSpecialtyType.setRequired(true);		
        this.prmtSpecialtyType.setCommitFormat("$number$");		
        this.prmtSpecialtyType.setQueryInfo("com.kingdee.eas.fdc.basedata.app.F7SpecialtyTypeQuery");		
        this.prmtSpecialtyType.setEditable(true);		
        this.prmtSpecialtyType.setEnabledMultiSelection(true);
        this.prmtSpecialtyType.addSelectorListener(new com.kingdee.bos.ctrl.swing.event.SelectorListener() {
            public void willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) {
                try {
                    prmtSpecialtyType_willShow(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        this.prmtSpecialtyType.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    prmtSpecialtyType_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // txtChangeSubject		
        this.txtChangeSubject.setMaxLength(80);
        // pnlContent		
        this.pnlContent.setAutoscrolls(true);
        // pnlSupp		
        this.pnlSupp.setAutoscrolls(true);
        // ctnEntrys		
        this.ctnEntrys.setTitle(resHelper.getString("ctnEntrys.title"));		
        this.ctnEntrys.setAutoscrolls(true);		
        this.ctnEntrys.setEnableActive(false);
        // kdtEntrys
		String kdtEntrysStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol1\"><c:Alignment horizontal=\"center\" /></c:Style><c:Style id=\"sCol4\"><c:Protection hidden=\"true\" /></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"id\" t:width=\"0\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol0\" /><t:Column t:key=\"number\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol1\" /><t:Column t:key=\"changeContent\" t:width=\"750\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"true\" t:index=\"-1\" /><t:Column t:key=\"isBack\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"seq\" t:width=\"0\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol4\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{id}</t:Cell><t:Cell>$Resource{number}</t:Cell><t:Cell>$Resource{changeContent}</t:Cell><t:Cell>$Resource{isBack}</t:Cell><t:Cell>$Resource{seq}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdtEntrys.setFormatXml(resHelper.translateString("kdtEntrys",kdtEntrysStrXML));
        this.kdtEntrys.addKDTMouseListener(new com.kingdee.bos.ctrl.kdf.table.event.KDTMouseListener() {
            public void tableClicked(com.kingdee.bos.ctrl.kdf.table.event.KDTMouseEvent e) {
                try {
                    kdtEntrys_tableClicked(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        this.kdtEntrys.addKDTEditListener(new com.kingdee.bos.ctrl.kdf.table.event.KDTEditAdapter() {
            public void editStopped(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) {
                try {
                    kdtEntrys_editStopped(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });

                this.kdtEntrys.putBindContents("editData",new String[] {"id","number","changeContent","isBack","seq"});


        this.kdtEntrys.checkParsed();
        KDTextField kdtEntrys_number_TextField = new KDTextField();
        kdtEntrys_number_TextField.setName("kdtEntrys_number_TextField");
        kdtEntrys_number_TextField.setMaxLength(80);
        KDTDefaultCellEditor kdtEntrys_number_CellEditor = new KDTDefaultCellEditor(kdtEntrys_number_TextField);
        this.kdtEntrys.getColumn("number").setEditor(kdtEntrys_number_CellEditor);
        KDTextField kdtEntrys_changeContent_TextField = new KDTextField();
        kdtEntrys_changeContent_TextField.setName("kdtEntrys_changeContent_TextField");
        kdtEntrys_changeContent_TextField.setMaxLength(500);
        KDTDefaultCellEditor kdtEntrys_changeContent_CellEditor = new KDTDefaultCellEditor(kdtEntrys_changeContent_TextField);
        this.kdtEntrys.getColumn("changeContent").setEditor(kdtEntrys_changeContent_CellEditor);
        KDCheckBox kdtEntrys_isBack_CheckBox = new KDCheckBox();
        kdtEntrys_isBack_CheckBox.setName("kdtEntrys_isBack_CheckBox");
        KDTDefaultCellEditor kdtEntrys_isBack_CellEditor = new KDTDefaultCellEditor(kdtEntrys_isBack_CheckBox);
        this.kdtEntrys.getColumn("isBack").setEditor(kdtEntrys_isBack_CellEditor);
        // contGraphCount		
        this.contGraphCount.setBoundLabelText(resHelper.getString("contGraphCount.boundLabelText"));		
        this.contGraphCount.setBoundLabelLength(100);		
        this.contGraphCount.setBoundLabelUnderline(true);		
        this.contGraphCount.setPreferredSize(new Dimension(270,19));
        // comboGraphCount		
        this.comboGraphCount.addItems(EnumUtils.getEnumList("com.kingdee.eas.fdc.contract.GraphCountEnum").toArray());		
        this.comboGraphCount.setRequired(true);
        // ctnSuppEntrys		
        this.ctnSuppEntrys.setTitle(resHelper.getString("ctnSuppEntrys.title"));		
        this.ctnSuppEntrys.setEnableActive(false);
        // kdtSuppEntry
		String kdtSuppEntryStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol4\"><c:Protection hidden=\"true\" /></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"supp\" t:width=\"60\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" /><t:Column t:key=\"thing\" t:width=\"140\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" /><t:Column t:key=\"name\" t:width=\"160\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" /><t:Column t:key=\"content\" t:width=\"500\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" /><t:Column t:key=\"id\" t:width=\"0\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:styleID=\"sCol4\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{supp}</t:Cell><t:Cell>$Resource{thing}</t:Cell><t:Cell>$Resource{name}</t:Cell><t:Cell>$Resource{content}</t:Cell><t:Cell>$Resource{id}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot> ";
		
        this.kdtSuppEntry.setFormatXml(resHelper.translateString("kdtSuppEntry",kdtSuppEntryStrXML));
        this.kdtSuppEntry.addKDTMouseListener(new com.kingdee.bos.ctrl.kdf.table.event.KDTMouseListener() {
            public void tableClicked(com.kingdee.bos.ctrl.kdf.table.event.KDTMouseEvent e) {
                try {
                    kdtSuppEntry_tableClicked(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        this.kdtSuppEntry.addKDTEditListener(new com.kingdee.bos.ctrl.kdf.table.event.KDTEditAdapter() {
            public void editStopped(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) {
                try {
                    kdtSuppEntry_editStopped(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
            public void editStopping(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) {
                try {
                    kdtSuppEntry_editStopping(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });

        

        this.kdtSuppEntry.checkParsed();
        // contTotalCost		
        this.contTotalCost.setBoundLabelText(resHelper.getString("contTotalCost.boundLabelText"));		
        this.contTotalCost.setBoundLabelLength(100);		
        this.contTotalCost.setBoundLabelUnderline(true);
        // contAmoutA		
        this.contAmoutA.setBoundLabelText(resHelper.getString("contAmoutA.boundLabelText"));		
        this.contAmoutA.setBoundLabelLength(100);		
        this.contAmoutA.setBoundLabelUnderline(true);		
        this.contAmoutA.setVisible(false);
        // contDutyAmout		
        this.contDutyAmout.setBoundLabelText(resHelper.getString("contDutyAmout.boundLabelText"));		
        this.contDutyAmout.setBoundLabelLength(100);		
        this.contDutyAmout.setBoundLabelUnderline(true);		
        this.contDutyAmout.setEnabled(false);
        // contNoUse		
        this.contNoUse.setBoundLabelText(resHelper.getString("contNoUse.boundLabelText"));		
        this.contNoUse.setBoundLabelLength(100);		
        this.contNoUse.setBoundLabelUnderline(true);
        // contReason		
        this.contReason.setBoundLabelText(resHelper.getString("contReason.boundLabelText"));		
        this.contReason.setBoundLabelLength(100);		
        this.contReason.setBoundLabelUnderline(true);
        // cbxNoUse		
        this.cbxNoUse.setText(resHelper.getString("cbxNoUse.text"));
        this.cbxNoUse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                beforeActionPerformed(e);
                try {
                    cbxNoUse_actionPerformed(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                    afterActionPerformed(e);
                }
            }
        });
        // txtTotalCost		
        this.txtTotalCost.setDataType(1);		
        this.txtTotalCost.setPrecision(2);		
        this.txtTotalCost.setEnabled(false);
        // txtAmountA		
        this.txtAmountA.setDataType(1);		
        this.txtAmountA.setPrecision(2);
        // txtDutyAmount		
        this.txtDutyAmount.setDataType(1);		
        this.txtDutyAmount.setPrecision(2);		
        this.txtDutyAmount.setEnabled(false);
        // txtNoUse		
        this.txtNoUse.setDataType(1);		
        this.txtNoUse.setPrecision(2);
        // bmptResaon		
        this.bmptResaon.setQueryInfo("com.kingdee.eas.fdc.basedata.app.F7InvalidCostReasonQuery");		
        this.bmptResaon.setCommitFormat("$number$");		
        this.bmptResaon.setEditFormat("$number$");		
        this.bmptResaon.setDisplayFormat("$name$");
        // contAheadReason		
        this.contAheadReason.setBoundLabelText(resHelper.getString("contAheadReason.boundLabelText"));		
        this.contAheadReason.setBoundLabelLength(100);		
        this.contAheadReason.setBoundLabelUnderline(true);
        // contConnectType		
        this.contConnectType.setBoundLabelText(resHelper.getString("contConnectType.boundLabelText"));		
        this.contConnectType.setBoundLabelLength(100);		
        this.contConnectType.setBoundLabelUnderline(true);
        // contValidator		
        this.contValidator.setBoundLabelText(resHelper.getString("contValidator.boundLabelText"));		
        this.contValidator.setBoundLabelLength(100);		
        this.contValidator.setBoundLabelUnderline(true);
        // txtAheadReason		
        this.txtAheadReason.setEnabled(false);
        // txtConnectType		
        this.txtConnectType.setEnabled(false);
        // txtValidator		
        this.txtValidator.setEnabled(false);
        // txtOrg		
        this.txtOrg.setMaxLength(80);		
        this.txtOrg.setRequired(true);		
        this.txtOrg.setEditable(false);
        // prmtConductDept		
        this.prmtConductDept.setQueryInfo("com.kingdee.eas.basedata.org.app.AdminOrgUnitQuery");		
        this.prmtConductDept.setCommitFormat("$number$");		
        this.prmtConductDept.setDisplayFormat("$name$");		
        this.prmtConductDept.setEditFormat("$number$");		
        this.prmtConductDept.setRequired(true);		
        this.prmtConductDept.setEditable(true);
        // pkbookedDate
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
        this.cbPeriod.setEnabled(false);
        // comboOffer		
        this.comboOffer.addItems(EnumUtils.getEnumList("com.kingdee.eas.fdc.contract.OfferEnum").toArray());
        this.comboOffer.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                try {
                    comboOffer_itemStateChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // txtReaDesc
        // prmtConstrUnit		
        this.prmtConstrUnit.setQueryInfo("com.kingdee.eas.basedata.master.cssp.app.F7SupplierQueryWithDefaultStandard");		
        this.prmtConstrUnit.setCommitFormat("$number$");		
        this.prmtConstrUnit.setEditFormat("$number$");		
        this.prmtConstrUnit.setDisplayFormat("$number$ $name$");
        // prmtDesignUnit		
        this.prmtDesignUnit.setQueryInfo("com.kingdee.eas.basedata.master.cssp.app.F7SupplierQueryWithDefaultStandard");		
        this.prmtDesignUnit.setCommitFormat("$number$");		
        this.prmtDesignUnit.setEditFormat("$number$");		
        this.prmtDesignUnit.setDisplayFormat("$number$ $name$");
        // txtConstrSite
        // prmtConductUnit		
        this.prmtConductUnit.setDisplayFormat("$number$ $name$");		
        this.prmtConductUnit.setEditFormat("$number$");		
        this.prmtConductUnit.setCommitFormat("$number$");		
        this.prmtConductUnit.setQueryInfo("com.kingdee.eas.basedata.master.cssp.app.F7SupplierQueryWithDefaultStandard");
        // cmbAttachment
        // cmbAttenTwo
        // contbillType		
        this.contbillType.setBoundLabelText(resHelper.getString("contbillType.boundLabelText"));		
        this.contbillType.setBoundLabelLength(100);		
        this.contbillType.setBoundLabelUnderline(true);		
        this.contbillType.setVisible(true);
        // kDLabelContainer3		
        this.kDLabelContainer3.setBoundLabelText(resHelper.getString("kDLabelContainer3.boundLabelText"));		
        this.kDLabelContainer3.setBoundLabelLength(100);		
        this.kDLabelContainer3.setBoundLabelUnderline(true);		
        this.kDLabelContainer3.setVisible(false);		
        this.kDLabelContainer3.setEnabled(false);
        // contputForwardTime		
        this.contputForwardTime.setBoundLabelText(resHelper.getString("contputForwardTime.boundLabelText"));		
        this.contputForwardTime.setBoundLabelLength(100);		
        this.contputForwardTime.setBoundLabelUnderline(true);		
        this.contputForwardTime.setVisible(true);
        // contquality		
        this.contquality.setBoundLabelText(resHelper.getString("contquality.boundLabelText"));		
        this.contquality.setBoundLabelLength(100);		
        this.contquality.setBoundLabelUnderline(true);		
        this.contquality.setVisible(true);
        // conttimeLi		
        this.conttimeLi.setBoundLabelText(resHelper.getString("conttimeLi.boundLabelText"));		
        this.conttimeLi.setBoundLabelLength(100);		
        this.conttimeLi.setBoundLabelUnderline(true);		
        this.conttimeLi.setVisible(true);
        // contcost		
        this.contcost.setBoundLabelText(resHelper.getString("contcost.boundLabelText"));		
        this.contcost.setBoundLabelLength(100);		
        this.contcost.setBoundLabelUnderline(true);		
        this.contcost.setVisible(true);
        // contsale		
        this.contsale.setBoundLabelText(resHelper.getString("contsale.boundLabelText"));		
        this.contsale.setBoundLabelLength(100);		
        this.contsale.setBoundLabelUnderline(true);		
        this.contsale.setVisible(true);
        // contreworkVisa		
        this.contreworkVisa.setBoundLabelText(resHelper.getString("contreworkVisa.boundLabelText"));		
        this.contreworkVisa.setBoundLabelLength(100);		
        this.contreworkVisa.setBoundLabelUnderline(true);		
        this.contreworkVisa.setVisible(true);
        // contcontractAmPro		
        this.contcontractAmPro.setBoundLabelText(resHelper.getString("contcontractAmPro.boundLabelText"));		
        this.contcontractAmPro.setBoundLabelLength(100);		
        this.contcontractAmPro.setBoundLabelUnderline(true);		
        this.contcontractAmPro.setVisible(true);
        // conttotalChangeAmount		
        this.conttotalChangeAmount.setBoundLabelText(resHelper.getString("conttotalChangeAmount.boundLabelText"));		
        this.conttotalChangeAmount.setBoundLabelLength(100);		
        this.conttotalChangeAmount.setBoundLabelUnderline(true);		
        this.conttotalChangeAmount.setVisible(true);
        // contdesignChangeAmount		
        this.contdesignChangeAmount.setBoundLabelText(resHelper.getString("contdesignChangeAmount.boundLabelText"));		
        this.contdesignChangeAmount.setBoundLabelLength(100);		
        this.contdesignChangeAmount.setBoundLabelUnderline(true);		
        this.contdesignChangeAmount.setVisible(true);
        // contchangeEstimate		
        this.contchangeEstimate.setBoundLabelText(resHelper.getString("contchangeEstimate.boundLabelText"));		
        this.contchangeEstimate.setBoundLabelLength(100);		
        this.contchangeEstimate.setBoundLabelUnderline(true);		
        this.contchangeEstimate.setVisible(true);
        // kdtSplitEntry
		String kdtSplitEntryStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:NumberFormat>&amp;int</c:NumberFormat></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"seq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol0\" /><t:Column t:key=\"contractBill\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{seq}</t:Cell><t:Cell>$Resource{contractBill}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdtSplitEntry.setFormatXml(resHelper.translateString("kdtSplitEntry",kdtSplitEntryStrXML));

                this.kdtSplitEntry.putBindContents("editData",new String[] {"seq","contractBill"});


        this.kdtSplitEntry.checkParsed();
        final KDBizPromptBox kdtSplitEntry_contractBill_PromptBox = new KDBizPromptBox();
        kdtSplitEntry_contractBill_PromptBox.setQueryInfo("com.kingdee.eas.fdc.contract.app.ContractBillQuery");
        kdtSplitEntry_contractBill_PromptBox.setVisible(true);
        kdtSplitEntry_contractBill_PromptBox.setEditable(true);
        kdtSplitEntry_contractBill_PromptBox.setDisplayFormat("$number$");
        kdtSplitEntry_contractBill_PromptBox.setEditFormat("$number$");
        kdtSplitEntry_contractBill_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtSplitEntry_contractBill_CellEditor = new KDTDefaultCellEditor(kdtSplitEntry_contractBill_PromptBox);
        this.kdtSplitEntry.getColumn("contractBill").setEditor(kdtSplitEntry_contractBill_CellEditor);
        ObjectValueRender kdtSplitEntry_contractBill_OVR = new ObjectValueRender();
        kdtSplitEntry_contractBill_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtSplitEntry.getColumn("contractBill").setRenderer(kdtSplitEntry_contractBill_OVR);
        // contimgNumber		
        this.contimgNumber.setBoundLabelText(resHelper.getString("contimgNumber.boundLabelText"));		
        this.contimgNumber.setBoundLabelLength(100);		
        this.contimgNumber.setBoundLabelUnderline(true);		
        this.contimgNumber.setVisible(true);
        // contcompDate		
        this.contcompDate.setBoundLabelText(resHelper.getString("contcompDate.boundLabelText"));		
        this.contcompDate.setBoundLabelLength(100);		
        this.contcompDate.setBoundLabelUnderline(true);		
        this.contcompDate.setVisible(true);
        // contappliAmount		
        this.contappliAmount.setBoundLabelText(resHelper.getString("contappliAmount.boundLabelText"));		
        this.contappliAmount.setBoundLabelLength(100);		
        this.contappliAmount.setBoundLabelUnderline(true);		
        this.contappliAmount.setVisible(true);
        // contpunisAmount		
        this.contpunisAmount.setBoundLabelText(resHelper.getString("contpunisAmount.boundLabelText"));		
        this.contpunisAmount.setBoundLabelLength(100);		
        this.contpunisAmount.setBoundLabelUnderline(true);		
        this.contpunisAmount.setVisible(true);
        // contapprovedAmount		
        this.contapprovedAmount.setBoundLabelText(resHelper.getString("contapprovedAmount.boundLabelText"));		
        this.contapprovedAmount.setBoundLabelLength(100);		
        this.contapprovedAmount.setBoundLabelUnderline(true);		
        this.contapprovedAmount.setVisible(true);
        // contconstructionHead		
        this.contconstructionHead.setBoundLabelText(resHelper.getString("contconstructionHead.boundLabelText"));		
        this.contconstructionHead.setBoundLabelLength(100);		
        this.contconstructionHead.setBoundLabelUnderline(true);		
        this.contconstructionHead.setVisible(true);
        // contworkNote		
        this.contworkNote.setBoundLabelText(resHelper.getString("contworkNote.boundLabelText"));		
        this.contworkNote.setBoundLabelLength(100);		
        this.contworkNote.setBoundLabelUnderline(true);		
        this.contworkNote.setVisible(true);
        // contBIMUDF0052		
        this.contBIMUDF0052.setBoundLabelText(resHelper.getString("contBIMUDF0052.boundLabelText"));		
        this.contBIMUDF0052.setBoundLabelLength(100);		
        this.contBIMUDF0052.setBoundLabelUnderline(true);		
        this.contBIMUDF0052.setVisible(true);
        // contdelyDay		
        this.contdelyDay.setBoundLabelText(resHelper.getString("contdelyDay.boundLabelText"));		
        this.contdelyDay.setBoundLabelLength(100);		
        this.contdelyDay.setBoundLabelUnderline(true);		
        this.contdelyDay.setVisible(true);
        // billType		
        this.billType.setVisible(true);		
        this.billType.addItems(EnumUtils.getEnumList("com.kingdee.eas.fdc.contract.ChangeAuditBillType").toArray());		
        this.billType.setRequired(false);
        // txtSpecialtyType		
        this.txtSpecialtyType.setMaxLength(80);		
        this.txtSpecialtyType.setVisible(false);
        // pkputForwardTime		
        this.pkputForwardTime.setVisible(true);		
        this.pkputForwardTime.setRequired(false);
        // txtquality		
        this.txtquality.setVisible(true);		
        this.txtquality.setHorizontalAlignment(2);		
        this.txtquality.setMaxLength(100);		
        this.txtquality.setRequired(false);
        // txttimeLi		
        this.txttimeLi.setVisible(true);		
        this.txttimeLi.setHorizontalAlignment(2);		
        this.txttimeLi.setMaxLength(100);		
        this.txttimeLi.setRequired(false);
        // txtcost		
        this.txtcost.setVisible(true);		
        this.txtcost.setHorizontalAlignment(2);		
        this.txtcost.setMaxLength(100);		
        this.txtcost.setRequired(false);
        // txtsale		
        this.txtsale.setVisible(true);		
        this.txtsale.setHorizontalAlignment(2);		
        this.txtsale.setMaxLength(100);		
        this.txtsale.setRequired(false);
        // txtreworkVisa		
        this.txtreworkVisa.setVisible(true);		
        this.txtreworkVisa.setHorizontalAlignment(2);		
        this.txtreworkVisa.setDataType(1);		
        this.txtreworkVisa.setSupportedEmpty(true);		
        this.txtreworkVisa.setMinimumValue( new java.math.BigDecimal("-1.0E26"));		
        this.txtreworkVisa.setMaximumValue( new java.math.BigDecimal("1.0E26"));		
        this.txtreworkVisa.setPrecision(2);		
        this.txtreworkVisa.setRequired(false);
        // txtcontractAmPro		
        this.txtcontractAmPro.setVisible(true);		
        this.txtcontractAmPro.setHorizontalAlignment(2);		
        this.txtcontractAmPro.setDataType(1);		
        this.txtcontractAmPro.setSupportedEmpty(true);		
        this.txtcontractAmPro.setMinimumValue( new java.math.BigDecimal("-1.0E26"));		
        this.txtcontractAmPro.setMaximumValue( new java.math.BigDecimal("1.0E26"));		
        this.txtcontractAmPro.setPrecision(2);		
        this.txtcontractAmPro.setRequired(false);
        // txttotalChangeAmount		
        this.txttotalChangeAmount.setVisible(true);		
        this.txttotalChangeAmount.setHorizontalAlignment(2);		
        this.txttotalChangeAmount.setDataType(1);		
        this.txttotalChangeAmount.setSupportedEmpty(true);		
        this.txttotalChangeAmount.setMinimumValue( new java.math.BigDecimal("-1.0E26"));		
        this.txttotalChangeAmount.setMaximumValue( new java.math.BigDecimal("1.0E26"));		
        this.txttotalChangeAmount.setPrecision(2);		
        this.txttotalChangeAmount.setRequired(false);
        // txtdesignChangeAmount		
        this.txtdesignChangeAmount.setVisible(true);		
        this.txtdesignChangeAmount.setHorizontalAlignment(2);		
        this.txtdesignChangeAmount.setDataType(1);		
        this.txtdesignChangeAmount.setSupportedEmpty(true);		
        this.txtdesignChangeAmount.setMinimumValue( new java.math.BigDecimal("-1.0E26"));		
        this.txtdesignChangeAmount.setMaximumValue( new java.math.BigDecimal("1.0E26"));		
        this.txtdesignChangeAmount.setPrecision(2);		
        this.txtdesignChangeAmount.setRequired(false);
        // txtchangeEstimate		
        this.txtchangeEstimate.setVisible(true);		
        this.txtchangeEstimate.setHorizontalAlignment(2);		
        this.txtchangeEstimate.setMaxLength(100);		
        this.txtchangeEstimate.setRequired(false);
        // txtimgNumber		
        this.txtimgNumber.setVisible(true);		
        this.txtimgNumber.setHorizontalAlignment(2);		
        this.txtimgNumber.setMaxLength(100);		
        this.txtimgNumber.setRequired(false);
        // pkcompDate		
        this.pkcompDate.setVisible(true);		
        this.pkcompDate.setRequired(false);
        // txtappliAmount		
        this.txtappliAmount.setVisible(true);		
        this.txtappliAmount.setHorizontalAlignment(2);		
        this.txtappliAmount.setDataType(1);		
        this.txtappliAmount.setSupportedEmpty(true);		
        this.txtappliAmount.setMinimumValue( new java.math.BigDecimal("-1.0E26"));		
        this.txtappliAmount.setMaximumValue( new java.math.BigDecimal("1.0E26"));		
        this.txtappliAmount.setPrecision(2);		
        this.txtappliAmount.setRequired(false);
        // txtpunisAmount		
        this.txtpunisAmount.setVisible(true);		
        this.txtpunisAmount.setHorizontalAlignment(2);		
        this.txtpunisAmount.setDataType(1);		
        this.txtpunisAmount.setSupportedEmpty(true);		
        this.txtpunisAmount.setMinimumValue( new java.math.BigDecimal("-1.0E26"));		
        this.txtpunisAmount.setMaximumValue( new java.math.BigDecimal("1.0E26"));		
        this.txtpunisAmount.setPrecision(2);		
        this.txtpunisAmount.setRequired(false);
        // txtapprovedAmount		
        this.txtapprovedAmount.setVisible(true);		
        this.txtapprovedAmount.setHorizontalAlignment(2);		
        this.txtapprovedAmount.setDataType(1);		
        this.txtapprovedAmount.setSupportedEmpty(true);		
        this.txtapprovedAmount.setMinimumValue( new java.math.BigDecimal("-1.0E26"));		
        this.txtapprovedAmount.setMaximumValue( new java.math.BigDecimal("1.0E26"));		
        this.txtapprovedAmount.setPrecision(2);		
        this.txtapprovedAmount.setRequired(false);
        // txtconstructionHead		
        this.txtconstructionHead.setVisible(true);		
        this.txtconstructionHead.setHorizontalAlignment(2);		
        this.txtconstructionHead.setMaxLength(100);		
        this.txtconstructionHead.setRequired(false);
        // scrollPaneworkNote
        // txtworkNote		
        this.txtworkNote.setVisible(true);		
        this.txtworkNote.setRequired(false);		
        this.txtworkNote.setMaxLength(500);
        // prmtBIMUDF0052		
        this.prmtBIMUDF0052.setQueryInfo("com.kingdee.eas.basedata.person.app.PersonQuery");		
        this.prmtBIMUDF0052.setVisible(true);		
        this.prmtBIMUDF0052.setEditable(true);		
        this.prmtBIMUDF0052.setDisplayFormat("$name$");		
        this.prmtBIMUDF0052.setEditFormat("$number$");		
        this.prmtBIMUDF0052.setCommitFormat("$number$");		
        this.prmtBIMUDF0052.setRequired(false);
        // txtdelyDay		
        this.txtdelyDay.setVisible(true);		
        this.txtdelyDay.setHorizontalAlignment(2);		
        this.txtdelyDay.setDataType(0);		
        this.txtdelyDay.setSupportedEmpty(true);		
        this.txtdelyDay.setRequired(false);
        // prmtCurProject		
        this.prmtCurProject.setQueryInfo("com.kingdee.eas.fdc.basedata.app.F7ProjectQuery");		
        this.prmtCurProject.setCommitFormat("$name$");		
        this.prmtCurProject.setDisplayFormat("$name$");		
        this.prmtCurProject.setEditFormat("$name$");		
        this.prmtCurProject.setEnabled(false);
        // btnViewContract
        this.btnViewContract.setAction((IItemAction)ActionProxyFactory.getProxy(actionViewContract, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnViewContract.setText(resHelper.getString("btnViewContract.text"));		
        this.btnViewContract.setToolTipText(resHelper.getString("btnViewContract.toolTipText"));
        // separator4
        // btnAttenTwo
        this.btnAttenTwo.setAction((IItemAction)ActionProxyFactory.getProxy(actionAttenTwo, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnAttenTwo.setText(resHelper.getString("btnAttenTwo.text"));
        // btnRegister
        this.btnRegister.setAction((IItemAction)ActionProxyFactory.getProxy(actionRegister, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnRegister.setText(resHelper.getString("btnRegister.text"));		
        this.btnRegister.setToolTipText(resHelper.getString("btnRegister.toolTipText"));
        // btnDisPatch
        this.btnDisPatch.setAction((IItemAction)ActionProxyFactory.getProxy(actionDisPatch, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnDisPatch.setText(resHelper.getString("btnDisPatch.text"));
        // menuItemRegister
        this.menuItemRegister.setAction((IItemAction)ActionProxyFactory.getProxy(actionRegister, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemRegister.setText(resHelper.getString("menuItemRegister.text"));
        // menuItemDispatch
        this.menuItemDispatch.setAction((IItemAction)ActionProxyFactory.getProxy(actionDisPatch, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemDispatch.setText(resHelper.getString("menuItemDispatch.text"));
        // menuViewContract
        this.menuViewContract.setAction((IItemAction)ActionProxyFactory.getProxy(actionViewContract, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuViewContract.setText(resHelper.getString("menuViewContract.text"));		
        this.menuViewContract.setToolTipText(resHelper.getString("menuViewContract.toolTipText"));
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
        this.setBounds(new Rectangle(10, 10, 1013, 690));
        this.setLayout(new KDLayout());
        this.putClientProperty("OriginalBounds", new Rectangle(10, 10, 1013, 690));
        kDSeparator6.setBounds(new Rectangle(8, 625, 990, 5));
        this.add(kDSeparator6, new KDLayout.Constraints(8, 625, 990, 5, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        kDSeparator7.setBounds(new Rectangle(8, 235, 990, 8));
        this.add(kDSeparator7, new KDLayout.Constraints(8, 235, 990, 8, KDLayout.Constraints.ANCHOR_CENTRE | KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        contCreator.setBounds(new Rectangle(9, 633, 470, 19));
        this.add(contCreator, new KDLayout.Constraints(9, 633, 470, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contCreateTime.setBounds(new Rectangle(9, 657, 470, 19));
        this.add(contCreateTime, new KDLayout.Constraints(9, 657, 470, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contNumber.setBounds(new Rectangle(12, 37, 270, 19));
        this.add(contNumber, new KDLayout.Constraints(12, 37, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contAuditor.setBounds(new Rectangle(527, 632, 470, 19));
        this.add(contAuditor, new KDLayout.Constraints(527, 632, 470, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contName.setBounds(new Rectangle(377, 37, 270, 19));
        this.add(contName, new KDLayout.Constraints(377, 37, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contAuditTime.setBounds(new Rectangle(527, 657, 470, 19));
        this.add(contAuditTime, new KDLayout.Constraints(527, 657, 470, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contAuditType.setBounds(new Rectangle(377, 62, 270, 19));
        this.add(contAuditType, new KDLayout.Constraints(377, 62, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contChangeReason.setBounds(new Rectangle(730, 37, 270, 19));
        this.add(contChangeReason, new KDLayout.Constraints(730, 37, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contChangeState.setBounds(new Rectangle(842, 188, 158, 19));
        this.add(contChangeState, new KDLayout.Constraints(842, 188, 158, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contUrgentDegree.setBounds(new Rectangle(730, 87, 270, 19));
        this.add(contUrgentDegree, new KDLayout.Constraints(730, 87, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contJobType.setBounds(new Rectangle(377, 87, 270, 19));
        this.add(contJobType, new KDLayout.Constraints(377, 87, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contSpecialtyType.setBounds(new Rectangle(377, 112, 270, 19));
        this.add(contSpecialtyType, new KDLayout.Constraints(377, 112, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contChangeSubject.setBounds(new Rectangle(730, 62, 270, 19));
        this.add(contChangeSubject, new KDLayout.Constraints(730, 62, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        tbpChangAudit.setBounds(new Rectangle(9, 242, 569, 314));
        this.add(tbpChangAudit, new KDLayout.Constraints(9, 242, 569, 314, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contAheadDisPatch.setBounds(new Rectangle(10, 565, 991, 53));
        this.add(contAheadDisPatch, new KDLayout.Constraints(10, 565, 991, 53, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        contOrg.setBounds(new Rectangle(12, 12, 480, 19));
        this.add(contOrg, new KDLayout.Constraints(12, 12, 480, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contConductDept.setBounds(new Rectangle(12, 112, 270, 19));
        this.add(contConductDept, new KDLayout.Constraints(12, 112, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDLabelContainer1.setBounds(new Rectangle(12, 62, 270, 19));
        this.add(kDLabelContainer1, new KDLayout.Constraints(12, 62, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDLabelContainer2.setBounds(new Rectangle(12, 87, 270, 19));
        this.add(kDLabelContainer2, new KDLayout.Constraints(12, 87, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contOffer.setBounds(new Rectangle(12, 162, 270, 19));
        this.add(contOffer, new KDLayout.Constraints(12, 162, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contReaDesc.setBounds(new Rectangle(730, 138, 270, 43));
        this.add(contReaDesc, new KDLayout.Constraints(730, 138, 270, 43, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contConstrUnit.setBounds(new Rectangle(377, 137, 270, 19));
        this.add(contConstrUnit, new KDLayout.Constraints(377, 137, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contDesignUnit.setBounds(new Rectangle(730, 112, 270, 19));
        this.add(contDesignUnit, new KDLayout.Constraints(730, 112, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contConstrSite.setBounds(new Rectangle(377, 162, 270, 19));
        this.add(contConstrSite, new KDLayout.Constraints(377, 162, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contConductUnit.setBounds(new Rectangle(12, 137, 270, 19));
        this.add(contConductUnit, new KDLayout.Constraints(12, 137, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        lblAttachmentContainer.setBounds(new Rectangle(13, 188, 379, 19));
        this.add(lblAttachmentContainer, new KDLayout.Constraints(13, 188, 379, 19, 0));
        btnViewAttachment.setBounds(new Rectangle(482, 188, 101, 21));
        this.add(btnViewAttachment, new KDLayout.Constraints(482, 188, 101, 21, 0));
        chkIsImportChange.setBounds(new Rectangle(730, 188, 99, 19));
        this.add(chkIsImportChange, new KDLayout.Constraints(730, 188, 99, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        conAttenTwo.setBounds(new Rectangle(13, 213, 379, 19));
        this.add(conAttenTwo, new KDLayout.Constraints(13, 213, 379, 19, 0));
        viewAttenTwo.setBounds(new Rectangle(482, 213, 101, 21));
        this.add(viewAttenTwo, new KDLayout.Constraints(482, 213, 101, 21, 0));
        kdtSpecialtyType.setBounds(new Rectangle(293, 39, 68, 140));
        this.add(kdtSpecialtyType, new KDLayout.Constraints(293, 39, 68, 140, 0));
        kDPanel1.setBounds(new Rectangle(581, 242, 428, 335));
        this.add(kDPanel1, new KDLayout.Constraints(581, 242, 428, 335, 0));
        contCurProject.setBounds(new Rectangle(520, 12, 480, 19));
        this.add(contCurProject, new KDLayout.Constraints(520, 12, 480, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        //contCreator
        contCreator.setBoundEditor(prmtCreator);
        //contCreateTime
        contCreateTime.setBoundEditor(pkCreateTime);
        //contNumber
        contNumber.setBoundEditor(txtNumber);
        //contAuditor
        contAuditor.setBoundEditor(prmtAuditor);
        //contName
        contName.setBoundEditor(txtName);
        //contAuditTime
        contAuditTime.setBoundEditor(pkAuditTime);
        //contAuditType
        contAuditType.setBoundEditor(prmtAuditType);
        //contChangeReason
        contChangeReason.setBoundEditor(prmtChangeReason);
        //contChangeState
        contChangeState.setBoundEditor(comboChangeState);
        //contUrgentDegree
        contUrgentDegree.setBoundEditor(comboUrgentDegree);
        //contJobType
        contJobType.setBoundEditor(prmtJobType);
        //contSpecialtyType
        contSpecialtyType.setBoundEditor(prmtSpecialtyType);
        //contChangeSubject
        contChangeSubject.setBoundEditor(txtChangeSubject);
        //tbpChangAudit
        tbpChangAudit.add(pnlContent, resHelper.getString("pnlContent.constraints"));
        tbpChangAudit.add(pnlSupp, resHelper.getString("pnlSupp.constraints"));
        //pnlContent
pnlContent.setLayout(new BorderLayout(0, 0));        pnlContent.add(ctnEntrys, BorderLayout.CENTER);
        //ctnEntrys
        ctnEntrys.getContentPane().setLayout(new KDLayout());
        ctnEntrys.getContentPane().putClientProperty("OriginalBounds", new Rectangle(0, 0, 568, 281));        kdtEntrys.setBounds(new Rectangle(0, 0, 973, 196));
        ctnEntrys.getContentPane().add(kdtEntrys, new KDLayout.Constraints(0, 0, 973, 196, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        contGraphCount.setBounds(new Rectangle(5, 216, 270, 19));
        ctnEntrys.getContentPane().add(contGraphCount, new KDLayout.Constraints(5, 216, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        //contGraphCount
        contGraphCount.setBoundEditor(comboGraphCount);
        //pnlSupp
pnlSupp.setLayout(new BorderLayout(0, 0));        pnlSupp.add(ctnSuppEntrys, BorderLayout.CENTER);
        //ctnSuppEntrys
        ctnSuppEntrys.getContentPane().setLayout(new KDLayout());
        ctnSuppEntrys.getContentPane().putClientProperty("OriginalBounds", new Rectangle(0, 0, 568, 281));        kdtSuppEntry.setBounds(new Rectangle(0, 0, 980, 194));
        ctnSuppEntrys.getContentPane().add(kdtSuppEntry, new KDLayout.Constraints(0, 0, 980, 194, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        contTotalCost.setBounds(new Rectangle(4, 214, 270, 19));
        ctnSuppEntrys.getContentPane().add(contTotalCost, new KDLayout.Constraints(4, 214, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contAmoutA.setBounds(new Rectangle(698, 214, 270, 19));
        ctnSuppEntrys.getContentPane().add(contAmoutA, new KDLayout.Constraints(698, 214, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contDutyAmout.setBounds(new Rectangle(352, 214, 270, 19));
        ctnSuppEntrys.getContentPane().add(contDutyAmout, new KDLayout.Constraints(352, 214, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contNoUse.setBounds(new Rectangle(352, 238, 270, 19));
        ctnSuppEntrys.getContentPane().add(contNoUse, new KDLayout.Constraints(352, 238, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contReason.setBounds(new Rectangle(698, 238, 270, 19));
        ctnSuppEntrys.getContentPane().add(contReason, new KDLayout.Constraints(698, 238, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        cbxNoUse.setBounds(new Rectangle(5, 238, 270, 19));
        ctnSuppEntrys.getContentPane().add(cbxNoUse, new KDLayout.Constraints(5, 238, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        //contTotalCost
        contTotalCost.setBoundEditor(txtTotalCost);
        //contAmoutA
        contAmoutA.setBoundEditor(txtAmountA);
        //contDutyAmout
        contDutyAmout.setBoundEditor(txtDutyAmount);
        //contNoUse
        contNoUse.setBoundEditor(txtNoUse);
        //contReason
        contReason.setBoundEditor(bmptResaon);
        //contAheadDisPatch
        contAheadDisPatch.getContentPane().setLayout(new KDLayout());
        contAheadDisPatch.getContentPane().putClientProperty("OriginalBounds", new Rectangle(10, 565, 991, 53));        contAheadReason.setBounds(new Rectangle(580, 2, 400, 19));
        contAheadDisPatch.getContentPane().add(contAheadReason, new KDLayout.Constraints(580, 2, 400, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contConnectType.setBounds(new Rectangle(290, 2, 270, 19));
        contAheadDisPatch.getContentPane().add(contConnectType, new KDLayout.Constraints(290, 2, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contValidator.setBounds(new Rectangle(0, 2, 270, 19));
        contAheadDisPatch.getContentPane().add(contValidator, new KDLayout.Constraints(0, 2, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        //contAheadReason
        contAheadReason.setBoundEditor(txtAheadReason);
        //contConnectType
        contConnectType.setBoundEditor(txtConnectType);
        //contValidator
        contValidator.setBoundEditor(txtValidator);
        //contOrg
        contOrg.setBoundEditor(txtOrg);
        //contConductDept
        contConductDept.setBoundEditor(prmtConductDept);
        //kDLabelContainer1
        kDLabelContainer1.setBoundEditor(pkbookedDate);
        //kDLabelContainer2
        kDLabelContainer2.setBoundEditor(cbPeriod);
        //contOffer
        contOffer.setBoundEditor(comboOffer);
        //contReaDesc
        contReaDesc.setBoundEditor(txtReaDesc);
        //contConstrUnit
        contConstrUnit.setBoundEditor(prmtConstrUnit);
        //contDesignUnit
        contDesignUnit.setBoundEditor(prmtDesignUnit);
        //contConstrSite
        contConstrSite.setBoundEditor(txtConstrSite);
        //contConductUnit
        contConductUnit.setBoundEditor(prmtConductUnit);
        //lblAttachmentContainer
        lblAttachmentContainer.setBoundEditor(cmbAttachment);
        //conAttenTwo
        conAttenTwo.setBoundEditor(cmbAttenTwo);
        //kDPanel1
        kDPanel1.setLayout(new KDLayout());
        kDPanel1.putClientProperty("OriginalBounds", new Rectangle(581, 242, 428, 335));        contbillType.setBounds(new Rectangle(9, 15, 270, 19));
        kDPanel1.add(contbillType, new KDLayout.Constraints(9, 15, 270, 19, 0));
        kDLabelContainer3.setBounds(new Rectangle(21, 40, 270, 19));
        kDPanel1.add(kDLabelContainer3, new KDLayout.Constraints(21, 40, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contputForwardTime.setBounds(new Rectangle(29, 59, 270, 19));
        kDPanel1.add(contputForwardTime, new KDLayout.Constraints(29, 59, 270, 19, 0));
        contquality.setBounds(new Rectangle(19, 76, 270, 19));
        kDPanel1.add(contquality, new KDLayout.Constraints(19, 76, 270, 19, 0));
        conttimeLi.setBounds(new Rectangle(11, 97, 270, 19));
        kDPanel1.add(conttimeLi, new KDLayout.Constraints(11, 97, 270, 19, 0));
        contcost.setBounds(new Rectangle(23, 117, 270, 19));
        kDPanel1.add(contcost, new KDLayout.Constraints(23, 117, 270, 19, 0));
        contsale.setBounds(new Rectangle(16, 133, 270, 19));
        kDPanel1.add(contsale, new KDLayout.Constraints(16, 133, 270, 19, 0));
        contreworkVisa.setBounds(new Rectangle(9, 154, 270, 19));
        kDPanel1.add(contreworkVisa, new KDLayout.Constraints(9, 154, 270, 19, 0));
        contcontractAmPro.setBounds(new Rectangle(7, 175, 270, 19));
        kDPanel1.add(contcontractAmPro, new KDLayout.Constraints(7, 175, 270, 19, 0));
        conttotalChangeAmount.setBounds(new Rectangle(8, 196, 270, 19));
        kDPanel1.add(conttotalChangeAmount, new KDLayout.Constraints(8, 196, 270, 19, 0));
        contdesignChangeAmount.setBounds(new Rectangle(17, 215, 270, 19));
        kDPanel1.add(contdesignChangeAmount, new KDLayout.Constraints(17, 215, 270, 19, 0));
        contchangeEstimate.setBounds(new Rectangle(16, 235, 270, 19));
        kDPanel1.add(contchangeEstimate, new KDLayout.Constraints(16, 235, 270, 19, 0));
        kdtSplitEntry.setBounds(new Rectangle(278, 16, 156, 170));
        kdtSplitEntry_detailPanel = (com.kingdee.eas.framework.client.multiDetail.DetailPanel)com.kingdee.eas.framework.client.multiDetail.HMDUtils.buildDetail(this,dataBinder,kdtSplitEntry,new com.kingdee.eas.fdc.contract.ChangeAuditBillSplitEntryInfo(),null,false);
        kDPanel1.add(kdtSplitEntry_detailPanel, new KDLayout.Constraints(278, 16, 156, 170, 0));
        contimgNumber.setBounds(new Rectangle(16, 252, 270, 19));
        kDPanel1.add(contimgNumber, new KDLayout.Constraints(16, 252, 270, 19, 0));
        contcompDate.setBounds(new Rectangle(17, 271, 270, 19));
        kDPanel1.add(contcompDate, new KDLayout.Constraints(17, 271, 270, 19, 0));
        contappliAmount.setBounds(new Rectangle(284, 189, 270, 19));
        kDPanel1.add(contappliAmount, new KDLayout.Constraints(284, 189, 270, 19, 0));
        contpunisAmount.setBounds(new Rectangle(283, 211, 270, 19));
        kDPanel1.add(contpunisAmount, new KDLayout.Constraints(283, 211, 270, 19, 0));
        contapprovedAmount.setBounds(new Rectangle(290, 234, 270, 19));
        kDPanel1.add(contapprovedAmount, new KDLayout.Constraints(290, 234, 270, 19, 0));
        contconstructionHead.setBounds(new Rectangle(293, 254, 270, 19));
        kDPanel1.add(contconstructionHead, new KDLayout.Constraints(293, 254, 270, 19, 0));
        contworkNote.setBounds(new Rectangle(294, 278, 270, 19));
        kDPanel1.add(contworkNote, new KDLayout.Constraints(294, 278, 270, 19, 0));
        contBIMUDF0052.setBounds(new Rectangle(300, 298, 270, 19));
        kDPanel1.add(contBIMUDF0052, new KDLayout.Constraints(300, 298, 270, 19, 0));
        contdelyDay.setBounds(new Rectangle(21, 295, 270, 19));
        kDPanel1.add(contdelyDay, new KDLayout.Constraints(21, 295, 270, 19, 0));
        //contbillType
        contbillType.setBoundEditor(billType);
        //kDLabelContainer3
        kDLabelContainer3.setBoundEditor(txtSpecialtyType);
        //contputForwardTime
        contputForwardTime.setBoundEditor(pkputForwardTime);
        //contquality
        contquality.setBoundEditor(txtquality);
        //conttimeLi
        conttimeLi.setBoundEditor(txttimeLi);
        //contcost
        contcost.setBoundEditor(txtcost);
        //contsale
        contsale.setBoundEditor(txtsale);
        //contreworkVisa
        contreworkVisa.setBoundEditor(txtreworkVisa);
        //contcontractAmPro
        contcontractAmPro.setBoundEditor(txtcontractAmPro);
        //conttotalChangeAmount
        conttotalChangeAmount.setBoundEditor(txttotalChangeAmount);
        //contdesignChangeAmount
        contdesignChangeAmount.setBoundEditor(txtdesignChangeAmount);
        //contchangeEstimate
        contchangeEstimate.setBoundEditor(txtchangeEstimate);
        //contimgNumber
        contimgNumber.setBoundEditor(txtimgNumber);
        //contcompDate
        contcompDate.setBoundEditor(pkcompDate);
        //contappliAmount
        contappliAmount.setBoundEditor(txtappliAmount);
        //contpunisAmount
        contpunisAmount.setBoundEditor(txtpunisAmount);
        //contapprovedAmount
        contapprovedAmount.setBoundEditor(txtapprovedAmount);
        //contconstructionHead
        contconstructionHead.setBoundEditor(txtconstructionHead);
        //contworkNote
        contworkNote.setBoundEditor(scrollPaneworkNote);
        //scrollPaneworkNote
        scrollPaneworkNote.getViewport().add(txtworkNote, null);
        //contBIMUDF0052
        contBIMUDF0052.setBoundEditor(prmtBIMUDF0052);
        //contdelyDay
        contdelyDay.setBoundEditor(txtdelyDay);
        //contCurProject
        contCurProject.setBoundEditor(prmtCurProject);

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
        this.menuBar.add(menuTable1);
        this.menuBar.add(menuTool);
        this.menuBar.add(menuWorkflow);
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
        menuFile.add(menuItemSendMail);
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
        menuEdit.add(separator1);
        menuEdit.add(menuItemCreateFrom);
        menuEdit.add(menuItemCreateTo);
        menuEdit.add(menuItemCopyFrom);
        menuEdit.add(separatorEdit1);
        menuEdit.add(menuItemEnterToNextRow);
        menuEdit.add(separator2);
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
        menuView.add(separator3);
        menuView.add(menuItemTraceUp);
        menuView.add(menuItemTraceDown);
        menuView.add(menuItemLocate);
        //menuBiz
        menuBiz.add(menuItemCancelCancel);
        menuBiz.add(menuItemCancel);
        menuBiz.add(MenuItemVoucher);
        menuBiz.add(menuItemDelVoucher);
        menuBiz.add(menuItemAudit);
        menuBiz.add(menuItemUnAudit);
        menuBiz.add(menuItemRegister);
        menuBiz.add(menuItemDispatch);
        menuBiz.add(menuViewContract);
        //menuTable1
        menuTable1.add(menuItemAddLine);
        menuTable1.add(menuItemCopyLine);
        menuTable1.add(menuItemInsertLine);
        menuTable1.add(menuItemRemoveLine);
        //menuTool
        menuTool.add(menuItemSendMessage);
        menuTool.add(menuItemMsgFormat);
        menuTool.add(menuItemCalculator);
        menuTool.add(menuItemToolBarCustom);
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
        this.toolBar.add(btnCloud);
        this.toolBar.add(btnEdit);
        this.toolBar.add(kDSeparatorCloud);
        this.toolBar.add(btnSave);
        this.toolBar.add(btnReset);
        this.toolBar.add(btnSubmit);
        this.toolBar.add(btnCopy);
        this.toolBar.add(btnRemove);
        this.toolBar.add(btnCancelCancel);
        this.toolBar.add(btnCancel);
        this.toolBar.add(btnAttachment);
        this.toolBar.add(separatorFW1);
        this.toolBar.add(btnPageSetup);
        this.toolBar.add(btnPrint);
        this.toolBar.add(btnPrintPreview);
        this.toolBar.add(separatorFW2);
        this.toolBar.add(btnViewContract);
        this.toolBar.add(separator4);
        this.toolBar.add(btnFirst);
        this.toolBar.add(btnPre);
        this.toolBar.add(btnNext);
        this.toolBar.add(btnLast);
        this.toolBar.add(separatorFW3);
        this.toolBar.add(btnTraceUp);
        this.toolBar.add(btnTraceDown);
        this.toolBar.add(btnWorkFlowG);
        this.toolBar.add(btnSignature);
        this.toolBar.add(separatorFW4);
        this.toolBar.add(separatorFW7);
        this.toolBar.add(btnNumberSign);
        this.toolBar.add(btnCreateFrom);
        this.toolBar.add(btnViewSignature);
        this.toolBar.add(btnCopyFrom);
        this.toolBar.add(separatorFW5);
        this.toolBar.add(separatorFW8);
        this.toolBar.add(btnCopyLine);
        this.toolBar.add(btnCreateTo);
        this.toolBar.add(btnAddLine);
        this.toolBar.add(btnInsertLine);
        this.toolBar.add(btnRemoveLine);
        this.toolBar.add(separatorFW6);
        this.toolBar.add(separatorFW9);
        this.toolBar.add(btnVoucher);
        this.toolBar.add(btnDelVoucher);
        this.toolBar.add(btnAuditResult);
        this.toolBar.add(btnMultiapprove);
        this.toolBar.add(btnWFViewdoProccess);
        this.toolBar.add(btnWFViewSubmitProccess);
        this.toolBar.add(btnNextPerson);
        this.toolBar.add(btnAudit);
        this.toolBar.add(btnUnAudit);
        this.toolBar.add(btnCalculator);
        this.toolBar.add(btnAttenTwo);
        this.toolBar.add(btnRegister);
        this.toolBar.add(btnDisPatch);


    }

	//Regiester control's property binding.
	private void registerBindings(){
		dataBinder.registerBinding("isImportChange", boolean.class, this.chkIsImportChange, "selected");
		dataBinder.registerBinding("specialtyTypeEntry.specialtyType", com.kingdee.eas.fdc.basedata.SpecialtyTypeInfo.class, this.kdtSpecialtyType, "specialtyTypeID.text");
		dataBinder.registerBinding("specialtyTypeEntry", com.kingdee.eas.fdc.contract.SpecialtyTypeEntryInfo.class, this.kdtSpecialtyType, "userObject");
		dataBinder.registerBinding("specialtyTypeEntry.id", com.kingdee.bos.util.BOSUuid.class, this.kdtSpecialtyType, "id.text");
		dataBinder.registerBinding("creator", com.kingdee.eas.base.permission.UserInfo.class, this.prmtCreator, "data");
		dataBinder.registerBinding("createTime", java.sql.Timestamp.class, this.pkCreateTime, "value");
		dataBinder.registerBinding("number", String.class, this.txtNumber, "text");
		dataBinder.registerBinding("auditor", com.kingdee.eas.base.permission.UserInfo.class, this.prmtAuditor, "data");
		dataBinder.registerBinding("name", String.class, this.txtName, "text");
		dataBinder.registerBinding("auditTime", java.util.Date.class, this.pkAuditTime, "value");
		dataBinder.registerBinding("auditType", com.kingdee.eas.fdc.basedata.ChangeTypeInfo.class, this.prmtAuditType, "data");
		dataBinder.registerBinding("changeReason", com.kingdee.eas.fdc.basedata.ChangeReasonInfo.class, this.prmtChangeReason, "data");
		dataBinder.registerBinding("changeState", com.kingdee.eas.fdc.contract.ChangeBillStateEnum.class, this.comboChangeState, "selectedItem");
		dataBinder.registerBinding("urgentDegree", com.kingdee.eas.fdc.contract.ChangeUrgentDegreeEnum.class, this.comboUrgentDegree, "selectedItem");
		dataBinder.registerBinding("jobType", com.kingdee.eas.fdc.basedata.JobTypeInfo.class, this.prmtJobType, "data");
		dataBinder.registerBinding("specialtyType", com.kingdee.eas.fdc.basedata.SpecialtyTypeInfo.class, this.prmtSpecialtyType, "data");
		dataBinder.registerBinding("changeSubject", String.class, this.txtChangeSubject, "text");
		dataBinder.registerBinding("entrys.changeContent", String.class, this.kdtEntrys, "changeContent.text");
		dataBinder.registerBinding("entrys.isBack", boolean.class, this.kdtEntrys, "isBack.text");
		dataBinder.registerBinding("entrys", com.kingdee.eas.fdc.contract.ChangeAuditEntryInfo.class, this.kdtEntrys, "userObject");
		dataBinder.registerBinding("entrys.number", String.class, this.kdtEntrys, "number.text");
		dataBinder.registerBinding("entrys.id", com.kingdee.bos.util.BOSUuid.class, this.kdtEntrys, "id.text");
		dataBinder.registerBinding("entrys.seq", int.class, this.kdtEntrys, "seq.text");
		dataBinder.registerBinding("graphCount", com.kingdee.eas.fdc.contract.GraphCountEnum.class, this.comboGraphCount, "selectedItem");
		dataBinder.registerBinding("isNoUse", boolean.class, this.cbxNoUse, "selected");
		dataBinder.registerBinding("totalCost", java.math.BigDecimal.class, this.txtTotalCost, "value");
		dataBinder.registerBinding("amountA", java.math.BigDecimal.class, this.txtAmountA, "value");
		dataBinder.registerBinding("amountDutySupp", java.math.BigDecimal.class, this.txtDutyAmount, "value");
		dataBinder.registerBinding("costNouse", java.math.BigDecimal.class, this.txtNoUse, "value");
		dataBinder.registerBinding("invalidCostReason", com.kingdee.eas.fdc.basedata.InvalidCostReasonInfo.class, this.bmptResaon, "data");
		dataBinder.registerBinding("aheadReason", String.class, this.txtAheadReason, "text");
		dataBinder.registerBinding("connectType", String.class, this.txtConnectType, "text");
		dataBinder.registerBinding("validator", String.class, this.txtValidator, "text");
		dataBinder.registerBinding("conductDept", com.kingdee.eas.basedata.org.AdminOrgUnitInfo.class, this.prmtConductDept, "data");
		dataBinder.registerBinding("bookedDate", java.util.Date.class, this.pkbookedDate, "value");
		dataBinder.registerBinding("period", com.kingdee.eas.basedata.assistant.PeriodInfo.class, this.cbPeriod, "data");
		dataBinder.registerBinding("offer", com.kingdee.eas.fdc.contract.OfferEnum.class, this.comboOffer, "selectedItem");
		dataBinder.registerBinding("reaDesc", String.class, this.txtReaDesc, "_multiLangItem");
		dataBinder.registerBinding("constrUnit", com.kingdee.eas.basedata.master.cssp.SupplierInfo.class, this.prmtConstrUnit, "data");
		dataBinder.registerBinding("designUnit", com.kingdee.eas.basedata.master.cssp.SupplierInfo.class, this.prmtDesignUnit, "data");
		dataBinder.registerBinding("constrSite", String.class, this.txtConstrSite, "text");
		dataBinder.registerBinding("conductUnit", com.kingdee.eas.basedata.master.cssp.SupplierInfo.class, this.prmtConductUnit, "data");
		dataBinder.registerBinding("SplitEntry.seq", int.class, this.kdtSplitEntry, "seq.text");
		dataBinder.registerBinding("SplitEntry", com.kingdee.eas.fdc.contract.ChangeAuditBillSplitEntryInfo.class, this.kdtSplitEntry, "userObject");
		dataBinder.registerBinding("SplitEntry.contractBill", java.lang.Object.class, this.kdtSplitEntry, "contractBill.text");
		dataBinder.registerBinding("billType", com.kingdee.eas.fdc.contract.ChangeAuditBillType.class, this.billType, "selectedItem");
		dataBinder.registerBinding("specialName", String.class, this.txtSpecialtyType, "text");
		dataBinder.registerBinding("putForwardTime", java.util.Date.class, this.pkputForwardTime, "value");
		dataBinder.registerBinding("quality", String.class, this.txtquality, "text");
		dataBinder.registerBinding("timeLi", String.class, this.txttimeLi, "text");
		dataBinder.registerBinding("cost", String.class, this.txtcost, "text");
		dataBinder.registerBinding("sale", String.class, this.txtsale, "text");
		dataBinder.registerBinding("reworkVisa", java.math.BigDecimal.class, this.txtreworkVisa, "value");
		dataBinder.registerBinding("contractAmPro", java.math.BigDecimal.class, this.txtcontractAmPro, "value");
		dataBinder.registerBinding("totalChangeAmount", java.math.BigDecimal.class, this.txttotalChangeAmount, "value");
		dataBinder.registerBinding("designChangeAmount", java.math.BigDecimal.class, this.txtdesignChangeAmount, "value");
		dataBinder.registerBinding("changeEstimate", String.class, this.txtchangeEstimate, "text");
		dataBinder.registerBinding("imgNumber", String.class, this.txtimgNumber, "text");
		dataBinder.registerBinding("compDate", java.util.Date.class, this.pkcompDate, "value");
		dataBinder.registerBinding("appliAmount", java.math.BigDecimal.class, this.txtappliAmount, "value");
		dataBinder.registerBinding("punisAmount", java.math.BigDecimal.class, this.txtpunisAmount, "value");
		dataBinder.registerBinding("approvedAmount", java.math.BigDecimal.class, this.txtapprovedAmount, "value");
		dataBinder.registerBinding("constructionHead", String.class, this.txtconstructionHead, "text");
		dataBinder.registerBinding("workNote", String.class, this.txtworkNote, "text");
		dataBinder.registerBinding("BIMUDF0052", com.kingdee.eas.basedata.person.PersonInfo.class, this.prmtBIMUDF0052, "data");
		dataBinder.registerBinding("delyDay", int.class, this.txtdelyDay, "value");
		dataBinder.registerBinding("curProject", com.kingdee.eas.fdc.basedata.CurProjectInfo.class, this.prmtCurProject, "data");		
	}
	//Regiester UI State
	private void registerUIState(){					 	        		
	        getActionManager().registerUIState(STATUS_VIEW, this.actionAddLine, ActionStateConst.DISABLED);					 	        		
	        getActionManager().registerUIState(STATUS_VIEW, this.actionRemoveLine, ActionStateConst.DISABLED);					 	        		
	        getActionManager().registerUIState(STATUS_VIEW, this.actionAddSupp, ActionStateConst.DISABLED);					 	        		
	        getActionManager().registerUIState(STATUS_VIEW, this.actionDelSupp, ActionStateConst.DISABLED);					 	        		
	        getActionManager().registerUIState(STATUS_FINDVIEW, this.actionAudit, ActionStateConst.DISABLED);					 	        		
	        getActionManager().registerUIState(STATUS_FINDVIEW, this.actionUnAudit, ActionStateConst.DISABLED);					 	        		
	        getActionManager().registerUIState(STATUS_FINDVIEW, this.actionAddSupp, ActionStateConst.DISABLED);					 	        		
	        getActionManager().registerUIState(STATUS_FINDVIEW, this.actionDelSupp, ActionStateConst.DISABLED);		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.fdc.contract.app.ChangeAuditEditUIHandler";
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
        this.editData = (com.kingdee.eas.fdc.contract.ChangeAuditBillInfo)ov;
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
    	if (editData == null) return;
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
			com.kingdee.eas.basedata.org.client.f7.NewOrgUnitFilterInfoProducer oufip = new com.kingdee.eas.basedata.org.client.f7.NewOrgUnitFilterInfoProducer(orgType);
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
		getValidateHelper().registerBindProperty("isImportChange", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("specialtyTypeEntry.specialtyType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("specialtyTypeEntry", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("specialtyTypeEntry.id", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("creator", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("createTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("number", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("auditor", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("name", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("auditTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("auditType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("changeReason", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("changeState", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("urgentDegree", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("jobType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("specialtyType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("changeSubject", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.changeContent", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.isBack", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.number", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.id", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.seq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("graphCount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("isNoUse", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("totalCost", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("amountA", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("amountDutySupp", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("costNouse", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("invalidCostReason", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("aheadReason", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("connectType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("validator", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("conductDept", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("bookedDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("period", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("offer", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("reaDesc", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("constrUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("designUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("constrSite", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("conductUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("SplitEntry.seq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("SplitEntry", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("SplitEntry.contractBill", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("billType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("specialName", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("putForwardTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("quality", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("timeLi", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("cost", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("sale", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("reworkVisa", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("contractAmPro", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("totalChangeAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("designChangeAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("changeEstimate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("imgNumber", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("compDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("appliAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("punisAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("approvedAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("constructionHead", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("workNote", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("BIMUDF0052", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("delyDay", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("curProject", ValidateHelper.ON_SAVE);    		
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
		            this.actionAddLine.setEnabled(false);
		            this.actionRemoveLine.setEnabled(false);
		            this.actionAddSupp.setEnabled(false);
		            this.actionDelSupp.setEnabled(false);
        } else if (STATUS_FINDVIEW.equals(this.oprtState)) {
		            this.actionAudit.setEnabled(false);
		            this.actionUnAudit.setEnabled(false);
		            this.actionAddSupp.setEnabled(false);
		            this.actionDelSupp.setEnabled(false);
        }
    }

    /**
     * output prmtAuditType_dataChanged method
     */
    protected void prmtAuditType_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output prmtSpecialtyType_willShow method
     */
    protected void prmtSpecialtyType_willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) throws Exception
    {
    }

    /**
     * output prmtSpecialtyType_dataChanged method
     */
    protected void prmtSpecialtyType_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output kdtEntrys_tableClicked method
     */
    protected void kdtEntrys_tableClicked(com.kingdee.bos.ctrl.kdf.table.event.KDTMouseEvent e) throws Exception
    {
    }

    /**
     * output kdtEntrys_editStopped method
     */
    protected void kdtEntrys_editStopped(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) throws Exception
    {
    }

    /**
     * output kdtSuppEntry_editStopped method
     */
    protected void kdtSuppEntry_editStopped(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) throws Exception
    {
    }

    /**
     * output kdtSuppEntry_editStopping method
     */
    protected void kdtSuppEntry_editStopping(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) throws Exception
    {
    }

    /**
     * output kdtSuppEntry_tableClicked method
     */
    protected void kdtSuppEntry_tableClicked(com.kingdee.bos.ctrl.kdf.table.event.KDTMouseEvent e) throws Exception
    {
    }

    /**
     * output cbxNoUse_actionPerformed method
     */
    protected void cbxNoUse_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
    }

    /**
     * output pkbookedDate_dataChanged method
     */
    protected void pkbookedDate_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output comboOffer_itemStateChanged method
     */
    protected void comboOffer_itemStateChanged(java.awt.event.ItemEvent e) throws Exception
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
        sic.add(new SelectorItemInfo("isImportChange"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("specialtyTypeEntry.specialtyType.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("specialtyTypeEntry.specialtyType.id"));
			sic.add(new SelectorItemInfo("specialtyTypeEntry.specialtyType.name"));
        	sic.add(new SelectorItemInfo("specialtyTypeEntry.specialtyType.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("specialtyTypeEntry.*"));
		}
		else{
		}
    	sic.add(new SelectorItemInfo("specialtyTypeEntry.id"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("creator.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("creator.id"));
        	sic.add(new SelectorItemInfo("creator.number"));
        	sic.add(new SelectorItemInfo("creator.name"));
		}
        sic.add(new SelectorItemInfo("createTime"));
        sic.add(new SelectorItemInfo("number"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("auditor.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("auditor.id"));
        	sic.add(new SelectorItemInfo("auditor.number"));
        	sic.add(new SelectorItemInfo("auditor.name"));
		}
        sic.add(new SelectorItemInfo("name"));
        sic.add(new SelectorItemInfo("auditTime"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("auditType.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("auditType.id"));
        	sic.add(new SelectorItemInfo("auditType.number"));
        	sic.add(new SelectorItemInfo("auditType.name"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("changeReason.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("changeReason.id"));
        	sic.add(new SelectorItemInfo("changeReason.number"));
        	sic.add(new SelectorItemInfo("changeReason.name"));
		}
        sic.add(new SelectorItemInfo("changeState"));
        sic.add(new SelectorItemInfo("urgentDegree"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("jobType.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("jobType.id"));
        	sic.add(new SelectorItemInfo("jobType.number"));
        	sic.add(new SelectorItemInfo("jobType.name"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("specialtyType.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("specialtyType.id"));
        	sic.add(new SelectorItemInfo("specialtyType.number"));
        	sic.add(new SelectorItemInfo("specialtyType.name"));
		}
        sic.add(new SelectorItemInfo("changeSubject"));
    	sic.add(new SelectorItemInfo("entrys.changeContent"));
    	sic.add(new SelectorItemInfo("entrys.isBack"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("entrys.number"));
		}
    	sic.add(new SelectorItemInfo("entrys.id"));
    	sic.add(new SelectorItemInfo("entrys.seq"));
        sic.add(new SelectorItemInfo("graphCount"));
        sic.add(new SelectorItemInfo("isNoUse"));
        sic.add(new SelectorItemInfo("totalCost"));
        sic.add(new SelectorItemInfo("amountA"));
        sic.add(new SelectorItemInfo("amountDutySupp"));
        sic.add(new SelectorItemInfo("costNouse"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("invalidCostReason.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("invalidCostReason.id"));
        	sic.add(new SelectorItemInfo("invalidCostReason.number"));
        	sic.add(new SelectorItemInfo("invalidCostReason.name"));
		}
        sic.add(new SelectorItemInfo("aheadReason"));
        sic.add(new SelectorItemInfo("connectType"));
        sic.add(new SelectorItemInfo("validator"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("conductDept.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("conductDept.id"));
        	sic.add(new SelectorItemInfo("conductDept.number"));
        	sic.add(new SelectorItemInfo("conductDept.name"));
		}
        sic.add(new SelectorItemInfo("bookedDate"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("period.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("period.id"));
        	sic.add(new SelectorItemInfo("period.number"));
		}
        sic.add(new SelectorItemInfo("offer"));
        sic.add(new SelectorItemInfo("reaDesc"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("constrUnit.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("constrUnit.id"));
        	sic.add(new SelectorItemInfo("constrUnit.number"));
        	sic.add(new SelectorItemInfo("constrUnit.name"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("designUnit.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("designUnit.id"));
        	sic.add(new SelectorItemInfo("designUnit.number"));
        	sic.add(new SelectorItemInfo("designUnit.name"));
		}
        sic.add(new SelectorItemInfo("constrSite"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("conductUnit.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("conductUnit.id"));
        	sic.add(new SelectorItemInfo("conductUnit.number"));
        	sic.add(new SelectorItemInfo("conductUnit.name"));
		}
    	sic.add(new SelectorItemInfo("SplitEntry.seq"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("SplitEntry.*"));
		}
		else{
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("SplitEntry.contractBill.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("SplitEntry.contractBill.id"));
			sic.add(new SelectorItemInfo("SplitEntry.contractBill.name"));
        	sic.add(new SelectorItemInfo("SplitEntry.contractBill.number"));
		}
        sic.add(new SelectorItemInfo("billType"));
        sic.add(new SelectorItemInfo("specialName"));
        sic.add(new SelectorItemInfo("putForwardTime"));
        sic.add(new SelectorItemInfo("quality"));
        sic.add(new SelectorItemInfo("timeLi"));
        sic.add(new SelectorItemInfo("cost"));
        sic.add(new SelectorItemInfo("sale"));
        sic.add(new SelectorItemInfo("reworkVisa"));
        sic.add(new SelectorItemInfo("contractAmPro"));
        sic.add(new SelectorItemInfo("totalChangeAmount"));
        sic.add(new SelectorItemInfo("designChangeAmount"));
        sic.add(new SelectorItemInfo("changeEstimate"));
        sic.add(new SelectorItemInfo("imgNumber"));
        sic.add(new SelectorItemInfo("compDate"));
        sic.add(new SelectorItemInfo("appliAmount"));
        sic.add(new SelectorItemInfo("punisAmount"));
        sic.add(new SelectorItemInfo("approvedAmount"));
        sic.add(new SelectorItemInfo("constructionHead"));
        sic.add(new SelectorItemInfo("workNote"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("BIMUDF0052.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("BIMUDF0052.id"));
        	sic.add(new SelectorItemInfo("BIMUDF0052.number"));
        	sic.add(new SelectorItemInfo("BIMUDF0052.name"));
		}
        sic.add(new SelectorItemInfo("delyDay"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("curProject.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("curProject.id"));
        	sic.add(new SelectorItemInfo("curProject.number"));
        	sic.add(new SelectorItemInfo("curProject.name"));
		}
        return sic;
    }        
    	

    /**
     * output actionSave_actionPerformed method
     */
    public void actionSave_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionSave_actionPerformed(e);
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
     * output actionAddSupp_actionPerformed method
     */
    public void actionAddSupp_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionDelSupp_actionPerformed method
     */
    public void actionDelSupp_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionRegister_actionPerformed method
     */
    public void actionRegister_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionDisPatch_actionPerformed method
     */
    public void actionDisPatch_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionAheadDisPatch_actionPerformed method
     */
    public void actionAheadDisPatch_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionViewAttachment_actionPerformed method
     */
    public void actionViewAttachment_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionViewContract_actionPerformed method
     */
    public void actionViewContract_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionAttenTwo_actionPerformed method
     */
    public void actionAttenTwo_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionViewTwo_actionPerformed method
     */
    public void actionViewTwo_actionPerformed(ActionEvent e) throws Exception
    {
    }
	public RequestContext prepareActionSave(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionSave(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionSave() {
    	return false;
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
	public RequestContext prepareActionAddSupp(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionAddSupp() {
    	return false;
    }
	public RequestContext prepareActionDelSupp(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionDelSupp() {
    	return false;
    }
	public RequestContext prepareActionRegister(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionRegister() {
    	return false;
    }
	public RequestContext prepareActionDisPatch(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionDisPatch() {
    	return false;
    }
	public RequestContext prepareActionAheadDisPatch(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionAheadDisPatch() {
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
	public RequestContext prepareActionViewContract(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionViewContract() {
    	return false;
    }
	public RequestContext prepareActionAttenTwo(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionAttenTwo() {
    	return false;
    }
	public RequestContext prepareActionViewTwo(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionViewTwo() {
    	return false;
    }

    /**
     * output ActionAddSupp class
     */     
    protected class ActionAddSupp extends ItemAction {     
    
        public ActionAddSupp()
        {
            this(null);
        }

        public ActionAddSupp(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionAddSupp.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAddSupp.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAddSupp.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractChangeAuditEditUI.this, "ActionAddSupp", "actionAddSupp_actionPerformed", e);
        }
    }

    /**
     * output ActionDelSupp class
     */     
    protected class ActionDelSupp extends ItemAction {     
    
        public ActionDelSupp()
        {
            this(null);
        }

        public ActionDelSupp(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionDelSupp.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionDelSupp.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionDelSupp.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractChangeAuditEditUI.this, "ActionDelSupp", "actionDelSupp_actionPerformed", e);
        }
    }

    /**
     * output ActionRegister class
     */     
    protected class ActionRegister extends ItemAction {     
    
        public ActionRegister()
        {
            this(null);
        }

        public ActionRegister(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionRegister.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionRegister.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionRegister.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractChangeAuditEditUI.this, "ActionRegister", "actionRegister_actionPerformed", e);
        }
    }

    /**
     * output ActionDisPatch class
     */     
    protected class ActionDisPatch extends ItemAction {     
    
        public ActionDisPatch()
        {
            this(null);
        }

        public ActionDisPatch(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionDisPatch.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionDisPatch.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionDisPatch.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractChangeAuditEditUI.this, "ActionDisPatch", "actionDisPatch_actionPerformed", e);
        }
    }

    /**
     * output ActionAheadDisPatch class
     */     
    protected class ActionAheadDisPatch extends ItemAction {     
    
        public ActionAheadDisPatch()
        {
            this(null);
        }

        public ActionAheadDisPatch(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionAheadDisPatch.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAheadDisPatch.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAheadDisPatch.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractChangeAuditEditUI.this, "ActionAheadDisPatch", "actionAheadDisPatch_actionPerformed", e);
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
            this.setEnabled(false);
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
            innerActionPerformed("eas", AbstractChangeAuditEditUI.this, "ActionViewAttachment", "actionViewAttachment_actionPerformed", e);
        }
    }

    /**
     * output ActionViewContract class
     */     
    protected class ActionViewContract extends ItemAction {     
    
        public ActionViewContract()
        {
            this(null);
        }

        public ActionViewContract(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionViewContract.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionViewContract.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionViewContract.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractChangeAuditEditUI.this, "ActionViewContract", "actionViewContract_actionPerformed", e);
        }
    }

    /**
     * output ActionAttenTwo class
     */     
    protected class ActionAttenTwo extends ItemAction {     
    
        public ActionAttenTwo()
        {
            this(null);
        }

        public ActionAttenTwo(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionAttenTwo.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAttenTwo.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAttenTwo.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractChangeAuditEditUI.this, "ActionAttenTwo", "actionAttenTwo_actionPerformed", e);
        }
    }

    /**
     * output ActionViewTwo class
     */     
    protected class ActionViewTwo extends ItemAction {     
    
        public ActionViewTwo()
        {
            this(null);
        }

        public ActionViewTwo(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionViewTwo.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionViewTwo.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionViewTwo.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractChangeAuditEditUI.this, "ActionViewTwo", "actionViewTwo_actionPerformed", e);
        }
    }

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.fdc.contract.client", "ChangeAuditEditUI");
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
        return com.kingdee.eas.fdc.contract.client.ChangeAuditEditUI.class.getName();
    }

    /**
     * output getBizInterface method
     */
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return com.kingdee.eas.fdc.contract.ChangeAuditBillFactory.getRemoteInstance();
    }

    /**
     * output createNewData method
     */
    protected IObjectValue createNewData()
    {
        com.kingdee.eas.fdc.contract.ChangeAuditBillInfo objectValue = new com.kingdee.eas.fdc.contract.ChangeAuditBillInfo();
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));		
        return objectValue;
    }


    	protected String getTDFileName() {
    	return "/bim/fdc/contract/ChangeAuditBill";
	}
    protected IMetaDataPK getTDQueryPK() {
    	return new MetaDataPK("com.kingdee.eas.fdc.contract.app.ChangeAuditQuery");
	}
    

    /**
     * output getDetailTable method
     */
    protected KDTable getDetailTable() {
        return kdtEntrys;
	}
    /**
     * output applyDefaultValue method
     */
    protected void applyDefaultValue(IObjectValue vo) {        
		vo.put("billType","10");
        
    }        
	protected void setFieldsNull(com.kingdee.bos.dao.AbstractObjectValue arg0) {
		super.setFieldsNull(arg0);
		arg0.put("number",null);
	}

}