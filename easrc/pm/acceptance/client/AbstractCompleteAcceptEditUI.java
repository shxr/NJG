/**
 * output package name
 */
package com.kingdee.eas.port.pm.acceptance.client;

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
public abstract class AbstractCompleteAcceptEditUI extends com.kingdee.eas.xr.client.XRBillBaseEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractCompleteAcceptEditUI.class);
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCreator;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCreateTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contLastUpdateUser;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contLastUpdateTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCU;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contNumber;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contBizDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contDescription;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contAuditor;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contStatus;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contBizStatus;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contAuditTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contprojectName;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contprojectType;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtE1;
	protected com.kingdee.eas.framework.client.multiDetail.DetailPanel kdtE1_detailPanel = null;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtCreator;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkCreateTime;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtLastUpdateUser;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkLastUpdateTime;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtCU;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtNumber;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkBizDate;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtDescription;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtAuditor;
    protected com.kingdee.bos.ctrl.swing.KDComboBox comboStatus;
    protected com.kingdee.bos.ctrl.swing.KDComboBox comboBizStatus;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkAuditTime;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtprojectName;
    protected com.kingdee.bos.ctrl.swing.KDComboBox projectType;
    protected com.kingdee.eas.port.pm.acceptance.CompleteAcceptInfo editData = null;
    /**
     * output class constructor
     */
    public AbstractCompleteAcceptEditUI() throws Exception
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
        this.resHelper = new ResourceBundleHelper(AbstractCompleteAcceptEditUI.class.getName());
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
        this.actionSubmit.setExtendProperty("canForewarn", "true");
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.ForewarnService());
        //actionUnAudit
        actionUnAudit.setEnabled(true);
        actionUnAudit.setDaemonRun(false);

        _tempStr = resHelper.getString("ActionUnAudit.SHORT_DESCRIPTION");
        actionUnAudit.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionUnAudit.LONG_DESCRIPTION");
        actionUnAudit.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionUnAudit.NAME");
        actionUnAudit.putValue(ItemAction.NAME, _tempStr);
        this.actionUnAudit.setBindWorkFlow(true);
         this.actionUnAudit.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        this.contCreator = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contCreateTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contLastUpdateUser = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contLastUpdateTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contCU = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contNumber = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contBizDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contDescription = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contAuditor = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contStatus = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contBizStatus = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contAuditTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contprojectName = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contprojectType = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kdtE1 = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.prmtCreator = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.pkCreateTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.prmtLastUpdateUser = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.pkLastUpdateTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.prmtCU = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.pkBizDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtDescription = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.prmtAuditor = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.comboStatus = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.comboBizStatus = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.pkAuditTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.prmtprojectName = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.projectType = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.contCreator.setName("contCreator");
        this.contCreateTime.setName("contCreateTime");
        this.contLastUpdateUser.setName("contLastUpdateUser");
        this.contLastUpdateTime.setName("contLastUpdateTime");
        this.contCU.setName("contCU");
        this.contNumber.setName("contNumber");
        this.contBizDate.setName("contBizDate");
        this.contDescription.setName("contDescription");
        this.contAuditor.setName("contAuditor");
        this.contStatus.setName("contStatus");
        this.contBizStatus.setName("contBizStatus");
        this.contAuditTime.setName("contAuditTime");
        this.contprojectName.setName("contprojectName");
        this.contprojectType.setName("contprojectType");
        this.kdtE1.setName("kdtE1");
        this.prmtCreator.setName("prmtCreator");
        this.pkCreateTime.setName("pkCreateTime");
        this.prmtLastUpdateUser.setName("prmtLastUpdateUser");
        this.pkLastUpdateTime.setName("pkLastUpdateTime");
        this.prmtCU.setName("prmtCU");
        this.txtNumber.setName("txtNumber");
        this.pkBizDate.setName("pkBizDate");
        this.txtDescription.setName("txtDescription");
        this.prmtAuditor.setName("prmtAuditor");
        this.comboStatus.setName("comboStatus");
        this.comboBizStatus.setName("comboBizStatus");
        this.pkAuditTime.setName("pkAuditTime");
        this.prmtprojectName.setName("prmtprojectName");
        this.projectType.setName("projectType");
        // CoreUI
        // contCreator		
        this.contCreator.setBoundLabelText(resHelper.getString("contCreator.boundLabelText"));		
        this.contCreator.setBoundLabelLength(100);		
        this.contCreator.setBoundLabelUnderline(true);
        // contCreateTime		
        this.contCreateTime.setBoundLabelText(resHelper.getString("contCreateTime.boundLabelText"));		
        this.contCreateTime.setBoundLabelLength(100);		
        this.contCreateTime.setBoundLabelUnderline(true);
        // contLastUpdateUser		
        this.contLastUpdateUser.setBoundLabelText(resHelper.getString("contLastUpdateUser.boundLabelText"));		
        this.contLastUpdateUser.setBoundLabelLength(100);		
        this.contLastUpdateUser.setBoundLabelUnderline(true);
        // contLastUpdateTime		
        this.contLastUpdateTime.setBoundLabelText(resHelper.getString("contLastUpdateTime.boundLabelText"));		
        this.contLastUpdateTime.setBoundLabelLength(100);		
        this.contLastUpdateTime.setBoundLabelUnderline(true);
        // contCU		
        this.contCU.setBoundLabelText(resHelper.getString("contCU.boundLabelText"));		
        this.contCU.setBoundLabelLength(100);		
        this.contCU.setBoundLabelUnderline(true);		
        this.contCU.setEnabled(false);
        // contNumber		
        this.contNumber.setBoundLabelText(resHelper.getString("contNumber.boundLabelText"));		
        this.contNumber.setBoundLabelLength(100);		
        this.contNumber.setBoundLabelUnderline(true);
        // contBizDate		
        this.contBizDate.setBoundLabelText(resHelper.getString("contBizDate.boundLabelText"));		
        this.contBizDate.setBoundLabelLength(100);		
        this.contBizDate.setBoundLabelUnderline(true);
        // contDescription		
        this.contDescription.setBoundLabelText(resHelper.getString("contDescription.boundLabelText"));		
        this.contDescription.setBoundLabelLength(100);		
        this.contDescription.setBoundLabelUnderline(true);
        // contAuditor		
        this.contAuditor.setBoundLabelText(resHelper.getString("contAuditor.boundLabelText"));		
        this.contAuditor.setBoundLabelLength(100);		
        this.contAuditor.setBoundLabelUnderline(true);
        // contStatus		
        this.contStatus.setBoundLabelText(resHelper.getString("contStatus.boundLabelText"));		
        this.contStatus.setBoundLabelLength(100);		
        this.contStatus.setBoundLabelUnderline(true);		
        this.contStatus.setEnabled(false);
        // contBizStatus		
        this.contBizStatus.setBoundLabelText(resHelper.getString("contBizStatus.boundLabelText"));		
        this.contBizStatus.setBoundLabelLength(100);		
        this.contBizStatus.setBoundLabelUnderline(true);		
        this.contBizStatus.setEnabled(false);
        // contAuditTime		
        this.contAuditTime.setBoundLabelText(resHelper.getString("contAuditTime.boundLabelText"));		
        this.contAuditTime.setBoundLabelLength(100);		
        this.contAuditTime.setBoundLabelUnderline(true);
        // contprojectName		
        this.contprojectName.setBoundLabelText(resHelper.getString("contprojectName.boundLabelText"));		
        this.contprojectName.setBoundLabelLength(100);		
        this.contprojectName.setBoundLabelUnderline(true);		
        this.contprojectName.setVisible(true);
        // contprojectType		
        this.contprojectType.setBoundLabelText(resHelper.getString("contprojectType.boundLabelText"));		
        this.contprojectType.setBoundLabelLength(100);		
        this.contprojectType.setBoundLabelUnderline(true);		
        this.contprojectType.setVisible(true);
        // kdtE1
		String kdtE1StrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:NumberFormat>&amp;int</c:NumberFormat></c:Style><c:Style id=\"sCol3\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol8\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol9\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol11\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol12\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol13\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol16\"><c:NumberFormat>&amp;date</c:NumberFormat></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"seq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol0\" /><t:Column t:key=\"equ\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"type\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol3\" /><t:Column t:key=\"configuration\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"brand\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"model\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"location\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"useLife\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol8\" /><t:Column t:key=\"quantity\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol9\" /><t:Column t:key=\"measureUnit\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"price\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol11\" /><t:Column t:key=\"subtotal\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol12\" /><t:Column t:key=\"origin\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol13\" /><t:Column t:key=\"responsible\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"usePerson\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"completeDate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol16\" /><t:Column t:key=\"management\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"takeOverDep\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{seq}</t:Cell><t:Cell>$Resource{equ}</t:Cell><t:Cell>$Resource{type}</t:Cell><t:Cell>$Resource{name}</t:Cell><t:Cell>$Resource{configuration}</t:Cell><t:Cell>$Resource{brand}</t:Cell><t:Cell>$Resource{model}</t:Cell><t:Cell>$Resource{location}</t:Cell><t:Cell>$Resource{useLife}</t:Cell><t:Cell>$Resource{quantity}</t:Cell><t:Cell>$Resource{measureUnit}</t:Cell><t:Cell>$Resource{price}</t:Cell><t:Cell>$Resource{subtotal}</t:Cell><t:Cell>$Resource{origin}</t:Cell><t:Cell>$Resource{responsible}</t:Cell><t:Cell>$Resource{usePerson}</t:Cell><t:Cell>$Resource{completeDate}</t:Cell><t:Cell>$Resource{management}</t:Cell><t:Cell>$Resource{takeOverDep}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdtE1.setFormatXml(resHelper.translateString("kdtE1",kdtE1StrXML));
        kdtE1.addKDTEditListener(new KDTEditAdapter() {
		public void editStopped(KDTEditEvent e) {
			try {
				kdtE1_Changed(e.getRowIndex(),e.getColIndex());
			}
			catch (Exception exc) {
				handUIException(exc);
			}
		}
	});


                this.kdtE1.putBindContents("editData",new String[] {"seq","equ","type","name","configuration","brand","model","location","useLife","quantity","measureUnit","price","subtotal","origin","responsible","usePerson","completeDate","management","takeOverDep"});


        this.kdtE1.checkParsed();
        final KDBizPromptBox kdtE1_equ_PromptBox = new KDBizPromptBox();
        kdtE1_equ_PromptBox.setQueryInfo("com.kingdee.eas.port.equipment.record.app.EquIdQuery");
        kdtE1_equ_PromptBox.setVisible(true);
        kdtE1_equ_PromptBox.setEditable(true);
        kdtE1_equ_PromptBox.setDisplayFormat("$number$");
        kdtE1_equ_PromptBox.setEditFormat("$number$");
        kdtE1_equ_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtE1_equ_CellEditor = new KDTDefaultCellEditor(kdtE1_equ_PromptBox);
        this.kdtE1.getColumn("equ").setEditor(kdtE1_equ_CellEditor);
        ObjectValueRender kdtE1_equ_OVR = new ObjectValueRender();
        kdtE1_equ_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtE1.getColumn("equ").setRenderer(kdtE1_equ_OVR);
        final KDBizPromptBox kdtE1_type_PromptBox = new KDBizPromptBox();
        kdtE1_type_PromptBox.setQueryInfo("com.kingdee.eas.fi.fa.basedata.FaCatQuery");
        kdtE1_type_PromptBox.setVisible(true);
        kdtE1_type_PromptBox.setEditable(true);
        kdtE1_type_PromptBox.setDisplayFormat("$number$");
        kdtE1_type_PromptBox.setEditFormat("$number$");
        kdtE1_type_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtE1_type_CellEditor = new KDTDefaultCellEditor(kdtE1_type_PromptBox);
        this.kdtE1.getColumn("type").setEditor(kdtE1_type_CellEditor);
        ObjectValueRender kdtE1_type_OVR = new ObjectValueRender();
        kdtE1_type_OVR.setFormat(new BizDataFormat("$EqmCategory$"));
        this.kdtE1.getColumn("type").setRenderer(kdtE1_type_OVR);
        KDTextField kdtE1_name_TextField = new KDTextField();
        kdtE1_name_TextField.setName("kdtE1_name_TextField");
        kdtE1_name_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtE1_name_CellEditor = new KDTDefaultCellEditor(kdtE1_name_TextField);
        this.kdtE1.getColumn("name").setEditor(kdtE1_name_CellEditor);
        KDTextField kdtE1_configuration_TextField = new KDTextField();
        kdtE1_configuration_TextField.setName("kdtE1_configuration_TextField");
        kdtE1_configuration_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtE1_configuration_CellEditor = new KDTDefaultCellEditor(kdtE1_configuration_TextField);
        this.kdtE1.getColumn("configuration").setEditor(kdtE1_configuration_CellEditor);
        KDTextField kdtE1_brand_TextField = new KDTextField();
        kdtE1_brand_TextField.setName("kdtE1_brand_TextField");
        kdtE1_brand_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtE1_brand_CellEditor = new KDTDefaultCellEditor(kdtE1_brand_TextField);
        this.kdtE1.getColumn("brand").setEditor(kdtE1_brand_CellEditor);
        KDTextField kdtE1_model_TextField = new KDTextField();
        kdtE1_model_TextField.setName("kdtE1_model_TextField");
        kdtE1_model_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtE1_model_CellEditor = new KDTDefaultCellEditor(kdtE1_model_TextField);
        this.kdtE1.getColumn("model").setEditor(kdtE1_model_CellEditor);
        final KDBizPromptBox kdtE1_location_PromptBox = new KDBizPromptBox();
        kdtE1_location_PromptBox.setQueryInfo("com.kingdee.eas.basedata.assistant.app.F7AddressQuery");
        kdtE1_location_PromptBox.setVisible(true);
        kdtE1_location_PromptBox.setEditable(true);
        kdtE1_location_PromptBox.setDisplayFormat("$number$");
        kdtE1_location_PromptBox.setEditFormat("$number$");
        kdtE1_location_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtE1_location_CellEditor = new KDTDefaultCellEditor(kdtE1_location_PromptBox);
        this.kdtE1.getColumn("location").setEditor(kdtE1_location_CellEditor);
        ObjectValueRender kdtE1_location_OVR = new ObjectValueRender();
        kdtE1_location_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtE1.getColumn("location").setRenderer(kdtE1_location_OVR);
        KDFormattedTextField kdtE1_useLife_TextField = new KDFormattedTextField();
        kdtE1_useLife_TextField.setName("kdtE1_useLife_TextField");
        kdtE1_useLife_TextField.setVisible(true);
        kdtE1_useLife_TextField.setEditable(true);
        kdtE1_useLife_TextField.setHorizontalAlignment(2);
        kdtE1_useLife_TextField.setDataType(1);
        	kdtE1_useLife_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtE1_useLife_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtE1_useLife_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtE1_useLife_CellEditor = new KDTDefaultCellEditor(kdtE1_useLife_TextField);
        this.kdtE1.getColumn("useLife").setEditor(kdtE1_useLife_CellEditor);
        KDFormattedTextField kdtE1_quantity_TextField = new KDFormattedTextField();
        kdtE1_quantity_TextField.setName("kdtE1_quantity_TextField");
        kdtE1_quantity_TextField.setVisible(true);
        kdtE1_quantity_TextField.setEditable(true);
        kdtE1_quantity_TextField.setHorizontalAlignment(2);
        kdtE1_quantity_TextField.setDataType(1);
        	kdtE1_quantity_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E26"));
        	kdtE1_quantity_TextField.setMaximumValue(new java.math.BigDecimal("1.0E26"));
        kdtE1_quantity_TextField.setPrecision(2);
        KDTDefaultCellEditor kdtE1_quantity_CellEditor = new KDTDefaultCellEditor(kdtE1_quantity_TextField);
        this.kdtE1.getColumn("quantity").setEditor(kdtE1_quantity_CellEditor);
        final KDBizPromptBox kdtE1_measureUnit_PromptBox = new KDBizPromptBox();
        kdtE1_measureUnit_PromptBox.setQueryInfo("com.kingdee.eas.basedata.assistant.app.F7MeasureUnitQuery");
        kdtE1_measureUnit_PromptBox.setVisible(true);
        kdtE1_measureUnit_PromptBox.setEditable(true);
        kdtE1_measureUnit_PromptBox.setDisplayFormat("$number$");
        kdtE1_measureUnit_PromptBox.setEditFormat("$number$");
        kdtE1_measureUnit_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtE1_measureUnit_CellEditor = new KDTDefaultCellEditor(kdtE1_measureUnit_PromptBox);
        this.kdtE1.getColumn("measureUnit").setEditor(kdtE1_measureUnit_CellEditor);
        ObjectValueRender kdtE1_measureUnit_OVR = new ObjectValueRender();
        kdtE1_measureUnit_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtE1.getColumn("measureUnit").setRenderer(kdtE1_measureUnit_OVR);
        KDFormattedTextField kdtE1_price_TextField = new KDFormattedTextField();
        kdtE1_price_TextField.setName("kdtE1_price_TextField");
        kdtE1_price_TextField.setVisible(true);
        kdtE1_price_TextField.setEditable(true);
        kdtE1_price_TextField.setHorizontalAlignment(2);
        kdtE1_price_TextField.setDataType(1);
        	kdtE1_price_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E26"));
        	kdtE1_price_TextField.setMaximumValue(new java.math.BigDecimal("1.0E26"));
        kdtE1_price_TextField.setPrecision(2);
        KDTDefaultCellEditor kdtE1_price_CellEditor = new KDTDefaultCellEditor(kdtE1_price_TextField);
        this.kdtE1.getColumn("price").setEditor(kdtE1_price_CellEditor);
        KDFormattedTextField kdtE1_subtotal_TextField = new KDFormattedTextField();
        kdtE1_subtotal_TextField.setName("kdtE1_subtotal_TextField");
        kdtE1_subtotal_TextField.setVisible(true);
        kdtE1_subtotal_TextField.setEditable(true);
        kdtE1_subtotal_TextField.setHorizontalAlignment(2);
        kdtE1_subtotal_TextField.setDataType(1);
        	kdtE1_subtotal_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E26"));
        	kdtE1_subtotal_TextField.setMaximumValue(new java.math.BigDecimal("1.0E26"));
        kdtE1_subtotal_TextField.setPrecision(2);
        KDTDefaultCellEditor kdtE1_subtotal_CellEditor = new KDTDefaultCellEditor(kdtE1_subtotal_TextField);
        this.kdtE1.getColumn("subtotal").setEditor(kdtE1_subtotal_CellEditor);
        KDFormattedTextField kdtE1_origin_TextField = new KDFormattedTextField();
        kdtE1_origin_TextField.setName("kdtE1_origin_TextField");
        kdtE1_origin_TextField.setVisible(true);
        kdtE1_origin_TextField.setEditable(true);
        kdtE1_origin_TextField.setHorizontalAlignment(2);
        kdtE1_origin_TextField.setDataType(1);
        	kdtE1_origin_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtE1_origin_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtE1_origin_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtE1_origin_CellEditor = new KDTDefaultCellEditor(kdtE1_origin_TextField);
        this.kdtE1.getColumn("origin").setEditor(kdtE1_origin_CellEditor);
        final KDBizPromptBox kdtE1_responsible_PromptBox = new KDBizPromptBox();
        kdtE1_responsible_PromptBox.setQueryInfo("com.kingdee.eas.basedata.person.app.PersonQuery");
        kdtE1_responsible_PromptBox.setVisible(true);
        kdtE1_responsible_PromptBox.setEditable(true);
        kdtE1_responsible_PromptBox.setDisplayFormat("$number$");
        kdtE1_responsible_PromptBox.setEditFormat("$number$");
        kdtE1_responsible_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtE1_responsible_CellEditor = new KDTDefaultCellEditor(kdtE1_responsible_PromptBox);
        this.kdtE1.getColumn("responsible").setEditor(kdtE1_responsible_CellEditor);
        ObjectValueRender kdtE1_responsible_OVR = new ObjectValueRender();
        kdtE1_responsible_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtE1.getColumn("responsible").setRenderer(kdtE1_responsible_OVR);
        final KDBizPromptBox kdtE1_usePerson_PromptBox = new KDBizPromptBox();
        kdtE1_usePerson_PromptBox.setQueryInfo("com.kingdee.eas.basedata.person.app.PersonQuery");
        kdtE1_usePerson_PromptBox.setVisible(true);
        kdtE1_usePerson_PromptBox.setEditable(true);
        kdtE1_usePerson_PromptBox.setDisplayFormat("$number$");
        kdtE1_usePerson_PromptBox.setEditFormat("$number$");
        kdtE1_usePerson_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtE1_usePerson_CellEditor = new KDTDefaultCellEditor(kdtE1_usePerson_PromptBox);
        this.kdtE1.getColumn("usePerson").setEditor(kdtE1_usePerson_CellEditor);
        ObjectValueRender kdtE1_usePerson_OVR = new ObjectValueRender();
        kdtE1_usePerson_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtE1.getColumn("usePerson").setRenderer(kdtE1_usePerson_OVR);
        KDDatePicker kdtE1_completeDate_DatePicker = new KDDatePicker();
        kdtE1_completeDate_DatePicker.setName("kdtE1_completeDate_DatePicker");
        kdtE1_completeDate_DatePicker.setVisible(true);
        kdtE1_completeDate_DatePicker.setEditable(true);
        KDTDefaultCellEditor kdtE1_completeDate_CellEditor = new KDTDefaultCellEditor(kdtE1_completeDate_DatePicker);
        this.kdtE1.getColumn("completeDate").setEditor(kdtE1_completeDate_CellEditor);
        final KDBizPromptBox kdtE1_management_PromptBox = new KDBizPromptBox();
        kdtE1_management_PromptBox.setQueryInfo("com.kingdee.eas.basedata.org.app.AdminItemQuery");
        kdtE1_management_PromptBox.setVisible(true);
        kdtE1_management_PromptBox.setEditable(true);
        kdtE1_management_PromptBox.setDisplayFormat("$number$");
        kdtE1_management_PromptBox.setEditFormat("$number$");
        kdtE1_management_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtE1_management_CellEditor = new KDTDefaultCellEditor(kdtE1_management_PromptBox);
        this.kdtE1.getColumn("management").setEditor(kdtE1_management_CellEditor);
        ObjectValueRender kdtE1_management_OVR = new ObjectValueRender();
        kdtE1_management_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtE1.getColumn("management").setRenderer(kdtE1_management_OVR);
        final KDBizPromptBox kdtE1_takeOverDep_PromptBox = new KDBizPromptBox();
        kdtE1_takeOverDep_PromptBox.setQueryInfo("com.kingdee.eas.basedata.org.app.AdminItemQuery");
        kdtE1_takeOverDep_PromptBox.setVisible(true);
        kdtE1_takeOverDep_PromptBox.setEditable(true);
        kdtE1_takeOverDep_PromptBox.setDisplayFormat("$number$");
        kdtE1_takeOverDep_PromptBox.setEditFormat("$number$");
        kdtE1_takeOverDep_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtE1_takeOverDep_CellEditor = new KDTDefaultCellEditor(kdtE1_takeOverDep_PromptBox);
        this.kdtE1.getColumn("takeOverDep").setEditor(kdtE1_takeOverDep_CellEditor);
        ObjectValueRender kdtE1_takeOverDep_OVR = new ObjectValueRender();
        kdtE1_takeOverDep_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtE1.getColumn("takeOverDep").setRenderer(kdtE1_takeOverDep_OVR);
        // prmtCreator		
        this.prmtCreator.setEnabled(false);		
        this.prmtCreator.setCommitFormat("$name$");		
        this.prmtCreator.setEditFormat("$name$");		
        this.prmtCreator.setDisplayFormat("$name$");
        // pkCreateTime		
        this.pkCreateTime.setTimeEnabled(true);		
        this.pkCreateTime.setEnabled(false);
        // prmtLastUpdateUser		
        this.prmtLastUpdateUser.setEnabled(false);		
        this.prmtLastUpdateUser.setDisplayFormat("$name$");		
        this.prmtLastUpdateUser.setEditFormat("$name$");		
        this.prmtLastUpdateUser.setCommitFormat("$name$");
        // pkLastUpdateTime		
        this.pkLastUpdateTime.setTimeEnabled(true);		
        this.pkLastUpdateTime.setEnabled(false);
        // prmtCU		
        this.prmtCU.setEnabled(false);
        // txtNumber
        // pkBizDate
        // txtDescription
        // prmtAuditor		
        this.prmtAuditor.setEnabled(false);		
        this.prmtAuditor.setCommitFormat("$name$");		
        this.prmtAuditor.setDisplayFormat("$name$");		
        this.prmtAuditor.setEditFormat("$name$");
        // comboStatus		
        this.comboStatus.addItems(EnumUtils.getEnumList("com.kingdee.eas.xr.app.XRBillStatusEnum").toArray());		
        this.comboStatus.setEnabled(false);
        // comboBizStatus		
        this.comboBizStatus.addItems(EnumUtils.getEnumList("com.kingdee.eas.xr.app.XRBizActionEnum").toArray());		
        this.comboBizStatus.setEnabled(false);		
        this.comboBizStatus.setVisible(false);
        // pkAuditTime		
        this.pkAuditTime.setTimeEnabled(true);		
        this.pkAuditTime.setEnabled(false);
        // prmtprojectName		
        this.prmtprojectName.setQueryInfo("com.kingdee.eas.basedata.assistant.app.F7ProjectQuery");		
        this.prmtprojectName.setEditable(true);		
        this.prmtprojectName.setDisplayFormat("$name$");		
        this.prmtprojectName.setEditFormat("$number$");		
        this.prmtprojectName.setCommitFormat("$number$");		
        this.prmtprojectName.setRequired(false);
        prmtprojectName.addDataChangeListener(new DataChangeListener() {
		public void dataChanged(DataChangeEvent e) {
			try {
				prmtprojectName_Changed();
			}
			catch (Exception exc) {
				handUIException(exc);
			}
		}
	});

        // projectType		
        this.projectType.addItems(EnumUtils.getEnumList("com.kingdee.eas.basedata.assistant.ProjectTypeEnum").toArray());		
        this.projectType.setRequired(false);
        this.setFocusTraversalPolicy(new com.kingdee.bos.ui.UIFocusTraversalPolicy(new java.awt.Component[] {prmtprojectName,projectType,prmtCU,pkLastUpdateTime,prmtLastUpdateUser,pkCreateTime,prmtCreator,prmtAuditor,txtDescription,pkBizDate,kdtE1,txtNumber,pkAuditTime,comboBizStatus,comboStatus}));
        this.setFocusCycleRoot(true);
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
        this.setBounds(new Rectangle(10, 10, 1013, 400));
        this.setLayout(new KDLayout());
        this.putClientProperty("OriginalBounds", new Rectangle(10, 10, 1013, 400));
        contCreator.setBounds(new Rectangle(10, 328, 270, 19));
        this.add(contCreator, new KDLayout.Constraints(10, 328, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contCreateTime.setBounds(new Rectangle(10, 352, 270, 19));
        this.add(contCreateTime, new KDLayout.Constraints(10, 352, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contLastUpdateUser.setBounds(new Rectangle(371, 328, 270, 19));
        this.add(contLastUpdateUser, new KDLayout.Constraints(371, 328, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contLastUpdateTime.setBounds(new Rectangle(371, 352, 270, 19));
        this.add(contLastUpdateTime, new KDLayout.Constraints(371, 352, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contCU.setBounds(new Rectangle(10, 34, 270, 19));
        this.add(contCU, new KDLayout.Constraints(10, 34, 270, 19, KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contNumber.setBounds(new Rectangle(10, 10, 270, 19));
        this.add(contNumber, new KDLayout.Constraints(10, 10, 270, 19, KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contBizDate.setBounds(new Rectangle(733, 34, 270, 19));
        this.add(contBizDate, new KDLayout.Constraints(733, 34, 270, 19, KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contDescription.setBounds(new Rectangle(737, 379, 270, 19));
        this.add(contDescription, new KDLayout.Constraints(737, 379, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        contAuditor.setBounds(new Rectangle(733, 328, 270, 19));
        this.add(contAuditor, new KDLayout.Constraints(733, 328, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contStatus.setBounds(new Rectangle(733, 10, 270, 19));
        this.add(contStatus, new KDLayout.Constraints(733, 10, 270, 19, KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contBizStatus.setBounds(new Rectangle(457, 379, 270, 19));
        this.add(contBizStatus, new KDLayout.Constraints(457, 379, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        contAuditTime.setBounds(new Rectangle(733, 352, 270, 19));
        this.add(contAuditTime, new KDLayout.Constraints(733, 352, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contprojectName.setBounds(new Rectangle(371, 10, 270, 19));
        this.add(contprojectName, new KDLayout.Constraints(371, 10, 270, 19, KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contprojectType.setBounds(new Rectangle(371, 34, 270, 19));
        this.add(contprojectType, new KDLayout.Constraints(371, 34, 270, 19, KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kdtE1.setBounds(new Rectangle(10, 58, 993, 259));
        kdtE1_detailPanel = (com.kingdee.eas.framework.client.multiDetail.DetailPanel)com.kingdee.eas.framework.client.multiDetail.HMDUtils.buildDetail(this,dataBinder,kdtE1,new com.kingdee.eas.port.pm.acceptance.CompleteAcceptE1Info(),null,false);
        this.add(kdtE1_detailPanel, new KDLayout.Constraints(10, 58, 993, 259, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        //contCreator
        contCreator.setBoundEditor(prmtCreator);
        //contCreateTime
        contCreateTime.setBoundEditor(pkCreateTime);
        //contLastUpdateUser
        contLastUpdateUser.setBoundEditor(prmtLastUpdateUser);
        //contLastUpdateTime
        contLastUpdateTime.setBoundEditor(pkLastUpdateTime);
        //contCU
        contCU.setBoundEditor(prmtCU);
        //contNumber
        contNumber.setBoundEditor(txtNumber);
        //contBizDate
        contBizDate.setBoundEditor(pkBizDate);
        //contDescription
        contDescription.setBoundEditor(txtDescription);
        //contAuditor
        contAuditor.setBoundEditor(prmtAuditor);
        //contStatus
        contStatus.setBoundEditor(comboStatus);
        //contBizStatus
        contBizStatus.setBoundEditor(comboBizStatus);
        //contAuditTime
        contAuditTime.setBoundEditor(pkAuditTime);
        //contprojectName
        contprojectName.setBoundEditor(prmtprojectName);
        //contprojectType
        contprojectType.setBoundEditor(projectType);

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
        menuFile.add(kDSeparator6);
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
        menuView.add(kDSeparator7);
        menuView.add(menuItemLocate);
        //menuBiz
        menuBiz.add(menuItemCancelCancel);
        menuBiz.add(menuItemCancel);
        menuBiz.add(MenuItemVoucher);
        menuBiz.add(menuItemDelVoucher);
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
        this.toolBar.add(btnAudit);
        this.toolBar.add(btnUnAudit);
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
        this.toolBar.add(btnTraceUp);
        this.toolBar.add(btnTraceDown);
        this.toolBar.add(btnWorkFlowG);
        this.toolBar.add(btnSignature);
        this.toolBar.add(btnNumberSign);
        this.toolBar.add(btnViewSignature);
        this.toolBar.add(separatorFW4);
        this.toolBar.add(separatorFW7);
        this.toolBar.add(btnCreateFrom);
        this.toolBar.add(btnCopyFrom);
        this.toolBar.add(btnCreateTo);
        this.toolBar.add(separatorFW5);
        this.toolBar.add(separatorFW8);
        this.toolBar.add(btnAddLine);
        this.toolBar.add(btnCopyLine);
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


    }

	//Regiester control's property binding.
	private void registerBindings(){
		dataBinder.registerBinding("E1.seq", int.class, this.kdtE1, "seq.text");
		dataBinder.registerBinding("E1", com.kingdee.eas.port.pm.acceptance.CompleteAcceptE1Info.class, this.kdtE1, "userObject");
		dataBinder.registerBinding("E1.type", java.lang.Object.class, this.kdtE1, "type.text");
		dataBinder.registerBinding("E1.name", String.class, this.kdtE1, "name.text");
		dataBinder.registerBinding("E1.configuration", String.class, this.kdtE1, "configuration.text");
		dataBinder.registerBinding("E1.quantity", java.math.BigDecimal.class, this.kdtE1, "quantity.text");
		dataBinder.registerBinding("E1.price", java.math.BigDecimal.class, this.kdtE1, "price.text");
		dataBinder.registerBinding("E1.subtotal", java.math.BigDecimal.class, this.kdtE1, "subtotal.text");
		dataBinder.registerBinding("E1.usePerson", java.lang.Object.class, this.kdtE1, "usePerson.text");
		dataBinder.registerBinding("E1.completeDate", java.util.Date.class, this.kdtE1, "completeDate.text");
		dataBinder.registerBinding("E1.responsible", java.lang.Object.class, this.kdtE1, "responsible.text");
		dataBinder.registerBinding("E1.model", String.class, this.kdtE1, "model.text");
		dataBinder.registerBinding("E1.origin", java.math.BigDecimal.class, this.kdtE1, "origin.text");
		dataBinder.registerBinding("E1.brand", String.class, this.kdtE1, "brand.text");
		dataBinder.registerBinding("E1.management", java.lang.Object.class, this.kdtE1, "management.text");
		dataBinder.registerBinding("E1.takeOverDep", java.lang.Object.class, this.kdtE1, "takeOverDep.text");
		dataBinder.registerBinding("E1.location", java.lang.Object.class, this.kdtE1, "location.text");
		dataBinder.registerBinding("E1.useLife", java.math.BigDecimal.class, this.kdtE1, "useLife.text");
		dataBinder.registerBinding("E1.measureUnit", java.lang.Object.class, this.kdtE1, "measureUnit.text");
		dataBinder.registerBinding("E1.equ", java.lang.Object.class, this.kdtE1, "equ.text");
		dataBinder.registerBinding("creator", com.kingdee.eas.base.permission.UserInfo.class, this.prmtCreator, "data");
		dataBinder.registerBinding("createTime", java.sql.Timestamp.class, this.pkCreateTime, "value");
		dataBinder.registerBinding("lastUpdateUser", com.kingdee.eas.base.permission.UserInfo.class, this.prmtLastUpdateUser, "data");
		dataBinder.registerBinding("lastUpdateTime", java.sql.Timestamp.class, this.pkLastUpdateTime, "value");
		dataBinder.registerBinding("CU", com.kingdee.eas.basedata.org.CtrlUnitInfo.class, this.prmtCU, "data");
		dataBinder.registerBinding("number", String.class, this.txtNumber, "text");
		dataBinder.registerBinding("bizDate", java.util.Date.class, this.pkBizDate, "value");
		dataBinder.registerBinding("description", String.class, this.txtDescription, "text");
		dataBinder.registerBinding("auditor", com.kingdee.eas.base.permission.UserInfo.class, this.prmtAuditor, "data");
		dataBinder.registerBinding("status", com.kingdee.eas.xr.app.XRBillStatusEnum.class, this.comboStatus, "selectedItem");
		dataBinder.registerBinding("bizStatus", com.kingdee.eas.xr.app.XRBizActionEnum.class, this.comboBizStatus, "selectedItem");
		dataBinder.registerBinding("auditTime", java.sql.Timestamp.class, this.pkAuditTime, "value");
		dataBinder.registerBinding("projectName", com.kingdee.eas.basedata.assistant.ProjectInfo.class, this.prmtprojectName, "data");
		dataBinder.registerBinding("projectType", com.kingdee.eas.basedata.assistant.ProjectTypeEnum.class, this.projectType, "selectedItem");		
	}
	//Regiester UI State
	private void registerUIState(){		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.port.pm.acceptance.app.CompleteAcceptEditUIHandler";
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
        this.prmtprojectName.requestFocusInWindow();
    }

	
	

    /**
     * output setDataObject method
     */
    public void setDataObject(IObjectValue dataObject)
    {
        IObjectValue ov = dataObject;        	    	
        super.setDataObject(ov);
        this.editData = (com.kingdee.eas.port.pm.acceptance.CompleteAcceptInfo)ov;
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
		getValidateHelper().registerBindProperty("E1.seq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("E1", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("E1.type", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("E1.name", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("E1.configuration", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("E1.quantity", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("E1.price", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("E1.subtotal", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("E1.usePerson", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("E1.completeDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("E1.responsible", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("E1.model", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("E1.origin", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("E1.brand", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("E1.management", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("E1.takeOverDep", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("E1.location", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("E1.useLife", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("E1.measureUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("E1.equ", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("creator", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("createTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("lastUpdateUser", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("lastUpdateTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("CU", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("number", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("bizDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("description", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("auditor", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("status", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("bizStatus", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("auditTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("projectName", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("projectType", ValidateHelper.ON_SAVE);    		
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
        }
    }


    /**
     * output kdtE1_Changed(int rowIndex,int colIndex) method
     */
    public void kdtE1_Changed(int rowIndex,int colIndex) throws Exception
    {
            if ("type".equalsIgnoreCase(kdtE1.getColumn(colIndex).getKey())) {
kdtE1.setName(com.kingdee.bos.ui.face.UIRuleUtil.getString(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtE1.getCell(rowIndex,"type").getValue(),"name")));

}

    if ("type".equalsIgnoreCase(kdtE1.getColumn(colIndex).getKey())) {
kdtE1.getCell(rowIndex,"brand").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getString(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtE1.getCell(rowIndex,"type").getValue(),"mader")));

}

    if ("type".equalsIgnoreCase(kdtE1.getColumn(colIndex).getKey())) {
kdtE1.getCell(rowIndex,"model").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getString(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtE1.getCell(rowIndex,"type").getValue(),"model")));

}

    if ("type".equalsIgnoreCase(kdtE1.getColumn(colIndex).getKey())) {
kdtE1.getCell(rowIndex,"responsible").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getString(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtE1.getCell(rowIndex,"type").getValue(),"resPerson.name")));

}

    if ("type".equalsIgnoreCase(kdtE1.getColumn(colIndex).getKey())) {
kdtE1.getCell(rowIndex,"cost").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getBigDecimal(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtE1.getCell(rowIndex,"type").getValue(),"assetValue")));

}

    if ("type".equalsIgnoreCase(kdtE1.getColumn(colIndex).getKey())) {
kdtE1.getCell(rowIndex,"useLife").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getDateValue(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtE1.getCell(rowIndex,"type").getValue(),"deadline")));

}

    if ("type".equalsIgnoreCase(kdtE1.getColumn(colIndex).getKey())) {
kdtE1.getCell(rowIndex,"management").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getString(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtE1.getCell(rowIndex,"type").getValue(),"ssOrgUnit.name")));

}


    }

    /**
     * output prmtprojectName_Changed() method
     */
    public void prmtprojectName_Changed() throws Exception
    {
        System.out.println("prmtprojectName_Changed() Function is executed!");
            projectType.setSelectedItem(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)prmtprojectName.getData(),"type"));


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
    	sic.add(new SelectorItemInfo("E1.seq"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("E1.*"));
		}
		else{
			sic.add(new SelectorItemInfo("E1.name"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("E1.type.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("E1.type.id"));
			sic.add(new SelectorItemInfo("E1.type.EqmCategory"));
			sic.add(new SelectorItemInfo("E1.type.name"));
        	sic.add(new SelectorItemInfo("E1.type.number"));
		}
    	sic.add(new SelectorItemInfo("E1.configuration"));
    	sic.add(new SelectorItemInfo("E1.quantity"));
    	sic.add(new SelectorItemInfo("E1.price"));
    	sic.add(new SelectorItemInfo("E1.subtotal"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("E1.usePerson.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("E1.usePerson.id"));
			sic.add(new SelectorItemInfo("E1.usePerson.name"));
        	sic.add(new SelectorItemInfo("E1.usePerson.number"));
		}
    	sic.add(new SelectorItemInfo("E1.completeDate"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("E1.responsible.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("E1.responsible.id"));
			sic.add(new SelectorItemInfo("E1.responsible.name"));
        	sic.add(new SelectorItemInfo("E1.responsible.number"));
		}
    	sic.add(new SelectorItemInfo("E1.model"));
    	sic.add(new SelectorItemInfo("E1.origin"));
    	sic.add(new SelectorItemInfo("E1.brand"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("E1.management.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("E1.management.id"));
			sic.add(new SelectorItemInfo("E1.management.name"));
        	sic.add(new SelectorItemInfo("E1.management.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("E1.takeOverDep.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("E1.takeOverDep.id"));
			sic.add(new SelectorItemInfo("E1.takeOverDep.name"));
        	sic.add(new SelectorItemInfo("E1.takeOverDep.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("E1.location.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("E1.location.id"));
			sic.add(new SelectorItemInfo("E1.location.name"));
        	sic.add(new SelectorItemInfo("E1.location.number"));
		}
    	sic.add(new SelectorItemInfo("E1.useLife"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("E1.measureUnit.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("E1.measureUnit.id"));
			sic.add(new SelectorItemInfo("E1.measureUnit.name"));
        	sic.add(new SelectorItemInfo("E1.measureUnit.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("E1.equ.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("E1.equ.id"));
			sic.add(new SelectorItemInfo("E1.equ.name"));
        	sic.add(new SelectorItemInfo("E1.equ.number"));
		}
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
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("lastUpdateUser.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("lastUpdateUser.id"));
        	sic.add(new SelectorItemInfo("lastUpdateUser.number"));
        	sic.add(new SelectorItemInfo("lastUpdateUser.name"));
		}
        sic.add(new SelectorItemInfo("lastUpdateTime"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("CU.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("CU.id"));
        	sic.add(new SelectorItemInfo("CU.number"));
        	sic.add(new SelectorItemInfo("CU.name"));
		}
        sic.add(new SelectorItemInfo("number"));
        sic.add(new SelectorItemInfo("bizDate"));
        sic.add(new SelectorItemInfo("description"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("auditor.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("auditor.id"));
        	sic.add(new SelectorItemInfo("auditor.number"));
        	sic.add(new SelectorItemInfo("auditor.name"));
		}
        sic.add(new SelectorItemInfo("status"));
        sic.add(new SelectorItemInfo("bizStatus"));
        sic.add(new SelectorItemInfo("auditTime"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("projectName.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("projectName.id"));
        	sic.add(new SelectorItemInfo("projectName.number"));
        	sic.add(new SelectorItemInfo("projectName.name"));
		}
        sic.add(new SelectorItemInfo("projectType"));
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
     * output actionUnAudit_actionPerformed method
     */
    public void actionUnAudit_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionUnAudit_actionPerformed(e);
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

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.port.pm.acceptance.client", "CompleteAcceptEditUI");
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
        return com.kingdee.eas.port.pm.acceptance.client.CompleteAcceptEditUI.class.getName();
    }

    /**
     * output getBizInterface method
     */
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return com.kingdee.eas.port.pm.acceptance.CompleteAcceptFactory.getRemoteInstance();
    }

    /**
     * output createNewData method
     */
    protected IObjectValue createNewData()
    {
        com.kingdee.eas.port.pm.acceptance.CompleteAcceptInfo objectValue = new com.kingdee.eas.port.pm.acceptance.CompleteAcceptInfo();
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));		
        return objectValue;
    }


    	protected String getTDFileName() {
    	return "/bim/port/pm/acceptance/CompleteAccept";
	}
    protected IMetaDataPK getTDQueryPK() {
    	return new MetaDataPK("com.kingdee.eas.port.pm.acceptance.app.CompleteAcceptQuery");
	}
    

    /**
     * output getDetailTable method
     */
    protected KDTable getDetailTable() {
        return kdtE1;
	}
    /**
     * output applyDefaultValue method
     */
    protected void applyDefaultValue(IObjectValue vo) {        
		vo.put("projectType",new Integer(0));
        
    }        
	protected void setFieldsNull(com.kingdee.bos.dao.AbstractObjectValue arg0) {
		super.setFieldsNull(arg0);
		arg0.put("number",null);
	}

}