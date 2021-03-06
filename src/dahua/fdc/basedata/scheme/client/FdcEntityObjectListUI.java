/**
 * output package name
 */
package com.kingdee.eas.fdc.basedata.scheme.client;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.KeyStroke;

import org.apache.log4j.Logger;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.ctrl.analysis.util.editor.StyledEditor;
import com.kingdee.bos.ctrl.kdf.table.ICell;
import com.kingdee.bos.ctrl.kdf.table.IColumn;
import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.KDTDataRequestManager;
import com.kingdee.bos.ctrl.kdf.table.KDTSelectBlock;
import com.kingdee.bos.ctrl.kdf.table.KDTSelectManager;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.kdf.table.util.KDTableUtil;
import com.kingdee.bos.ctrl.swing.tree.DefaultKingdeeTreeNode;
import com.kingdee.bos.ctrl.swing.tree.KingdeeTreeModel;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.dao.query.IQueryExecutor;
import com.kingdee.bos.dao.query.QueryExecuteOption;
import com.kingdee.bos.metadata.IMetaDataPK;
import com.kingdee.bos.metadata.MetaDataTypeList;
import com.kingdee.bos.metadata.bot.DefineSysEnum;
import com.kingdee.bos.metadata.entity.EntityObjectInfo;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemCollection;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.metadata.view.IBriefViewTreeNode;
import com.kingdee.bos.metadata.view.MetaDataBriefInfo;
import com.kingdee.bos.metadata.view.filters.BotpBillFilter;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.ui.face.IItemAction;
import com.kingdee.bos.ui.face.IUIWindow;
import com.kingdee.bos.ui.face.UIFactory;
import com.kingdee.bos.ui.util.IUIActionPostman;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.bos.util.CryptoUtil;
import com.kingdee.eas.base.botp.client.BOTExportOptionUI;
import com.kingdee.eas.base.permission.Administrator;
import com.kingdee.eas.base.permission.UserException;
import com.kingdee.eas.base.permission.UserFactory;
import com.kingdee.eas.base.permission.UserInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.common.SubSystemUtils;
import com.kingdee.eas.common.client.OprtState;
import com.kingdee.eas.common.client.SysContext;
import com.kingdee.eas.common.client.UIContext;
import com.kingdee.eas.common.client.UIFactoryName;
import com.kingdee.eas.fdc.basedata.FDCNumberHelper;
import com.kingdee.eas.fdc.basedata.client.FDCClientHelper;
import com.kingdee.eas.fdc.basedata.client.FDCTableHelper;
import com.kingdee.eas.fdc.basedata.client.FdcZeroBaseRender;
import com.kingdee.eas.fdc.basedata.scheme.FdcColumnInfo;
import com.kingdee.eas.fdc.basedata.scheme.FdcEntityObjectFactory;
import com.kingdee.eas.fdc.basedata.scheme.FdcEntityObjectInfo;
import com.kingdee.eas.fdc.basedata.scheme.FdcEntityObjectUtil;
import com.kingdee.eas.fdc.basedata.scheme.FdcPropertyCollection;
import com.kingdee.eas.fdc.basedata.scheme.FdcPropertyFactory;
import com.kingdee.eas.fdc.basedata.scheme.FdcPropertyInfo;
import com.kingdee.eas.fdc.basedata.util.FdcClassUtil;
import com.kingdee.eas.fdc.basedata.util.FdcCollectionUtil;
import com.kingdee.eas.fdc.basedata.util.FdcFieldUtil;
import com.kingdee.eas.fdc.basedata.util.FdcObjectUtil;
import com.kingdee.eas.fdc.basedata.util.FdcParamUtil;
import com.kingdee.eas.fdc.basedata.util.FdcStringUtil;
import com.kingdee.eas.fi.gl.client.ClosePeriodUI;
import com.kingdee.eas.fm.common.client.FMIsqlUI;
import com.kingdee.eas.framework.ICoreBase;
import com.kingdee.eas.framework.ITreeBase;
import com.kingdee.eas.framework.batchHandler.RequestContext;
import com.kingdee.eas.framework.client.FrameWorkClientUtils;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.eas.util.client.AdvMsgBox;
import com.kingdee.eas.util.client.EASResource;
import com.kingdee.eas.util.client.MsgBox;
import com.kingdee.util.StringUtils;

/**
 * output class name
 */
public class FdcEntityObjectListUI extends AbstractFdcEntityObjectListUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2525899832083406524L;

	private static final Logger logger = CoreUIObject.getLogger(FdcEntityObjectListUI.class);

	// //////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////

	public final String ACTIONKEY_SYNCHRONIZE_EAS_MD = "SYNCHRONIZE_EAS_MD";

	// 第一次打开序时簿
	private boolean isFirstQuery = true;

	// 当前所房地产属性集合
	private FdcPropertyCollection curCols = null;

	// //////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////

	/**
	 * 子系统_房地产
	 */
	public static String SUBSYSTEM_FDC = "com.kingdee.eas.fdc";

	// //////////////////////////////////////////////////////////////////////////

	/**
	 * 子系统_房地产_项目运营
	 */
	public static String SUBSYSTEM_FDC_REPM = "com.kingdee.eas.fdcrepm";

	/**
	 * 子系统_房地产_营销服务
	 */
	public static String SUBSYSTEM_FDC_REMK = "com.kingdee.eas.fdcremk";

	/**
	 * 子系统_房地产_CRM_基础
	 */
	public static String SUBSYSTEM_FDC_CRM_BASEDATA = "com.kingdee.eas.fdc.crm.basedata.crm";

	/**
	 * 子系统_房地产_项目运营_列表
	 */
	public static List SUBSYSTEM_FDC_REPM_LIST = new ArrayList();

	/**
	 * 子系统_房地产_营销服务_列表
	 */
	public static List SUBSYSTEM_FDC_REMK_LIST = new ArrayList();

	/**
	 * 子系统_房地产_CRM_基础_列表
	 */
	public static List SUBSYSTEM_FDC_CRM_BASEDATA_LIST = new ArrayList();

	/**
	 * 子系统_映射
	 */
	public static Map SUBSYSTEM_MAP = new LinkedHashMap();

	// //////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////

	static {
		// 子系统_房地产_项目运营_列表
		SUBSYSTEM_FDC_REPM_LIST.add("com.kingdee.eas.fdc.aimcost");
		SUBSYSTEM_FDC_REPM_LIST.add("com.kingdee.eas.fdc.basedata");
		SUBSYSTEM_FDC_REPM_LIST.add("com.kingdee.eas.fdc.contract");
		SUBSYSTEM_FDC_REPM_LIST.add("com.kingdee.eas.fdc.costdb");
		SUBSYSTEM_FDC_REPM_LIST.add("com.kingdee.eas.fdc.finance");
		SUBSYSTEM_FDC_REPM_LIST.add("com.kingdee.eas.fdc.invite");
		SUBSYSTEM_FDC_REPM_LIST.add("com.kingdee.eas.fdc.material");
		SUBSYSTEM_FDC_REPM_LIST.add("com.kingdee.eas.fdc.measure");
		SUBSYSTEM_FDC_REPM_LIST.add("com.kingdee.eas.fdc.migrate");
		SUBSYSTEM_FDC_REPM_LIST.add("com.kingdee.eas.fdc.pm");
		SUBSYSTEM_FDC_REPM_LIST.add("com.kingdee.eas.fdc.repm");
		SUBSYSTEM_FDC_REPM_LIST.add("com.kingdee.eas.fdc.schedule");
		SUBSYSTEM_FDC_REPM_LIST.add("com.kingdee.eas.fdc.supply");

		// 子系统_房地产_营销服务_列表
		SUBSYSTEM_FDC_REMK_LIST.add("com.kingdee.eas.fdc.basecrm");
		SUBSYSTEM_FDC_REMK_LIST.add("com.kingdee.eas.fdc.crm_basedata");
		SUBSYSTEM_FDC_REMK_LIST.add("com.kingdee.eas.fdc.csm");
		SUBSYSTEM_FDC_REMK_LIST.add("com.kingdee.eas.fdc.customerservice");
		SUBSYSTEM_FDC_REMK_LIST.add("com.kingdee.eas.fdc.insider");
		SUBSYSTEM_FDC_REMK_LIST.add("com.kingdee.eas.fdc.market");
		SUBSYSTEM_FDC_REMK_LIST.add("com.kingdee.eas.fdc.merch");
		SUBSYSTEM_FDC_REMK_LIST.add("com.kingdee.eas.fdc.propertymgmt");
		SUBSYSTEM_FDC_REMK_LIST.add("com.kingdee.eas.fdc.remk");
		SUBSYSTEM_FDC_REMK_LIST.add("com.kingdee.eas.fdc.sales");
		SUBSYSTEM_FDC_REMK_LIST.add("com.kingdee.eas.fdc.sellhouse");
		SUBSYSTEM_FDC_REMK_LIST.add("com.kingdee.eas.fdc.tenancy");

		// 子系统_房地产_CRM_基础_列表
		SUBSYSTEM_FDC_CRM_BASEDATA_LIST.add("com.kingdee.eas.fdc.crm.basedata");

		// 子系统_映射
		SUBSYSTEM_MAP.put(SUBSYSTEM_FDC_REPM, SUBSYSTEM_FDC_REPM_LIST);
		SUBSYSTEM_MAP.put(SUBSYSTEM_FDC_REMK, SUBSYSTEM_FDC_REMK_LIST);
		SUBSYSTEM_MAP.put(SUBSYSTEM_FDC_CRM_BASEDATA, SUBSYSTEM_FDC_CRM_BASEDATA_LIST);
	}

	// //////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////

	/**
	 * output class constructor
	 */
	public FdcEntityObjectListUI() throws Exception {
		super();
	}

	/**
	 * 加载数据到界面
	 */
	public void loadFields() {
		super.loadFields();
	}

	/**
	 * 附加监听器
	 */
	protected void attachListeners() {
		ActionMap entryActionMap = this.getActionMap();

		// ACTIONKEY_SYNCHRONIZE_EAS_MD
		entryActionMap.put(ACTIONKEY_SYNCHRONIZE_EAS_MD, this.synchronizeEasMDListener);
	}

	/**
	 * 拆卸监听器
	 */
	protected void detachListeners() {
		ActionMap entryActionMap = this.getActionMap();
		entryActionMap.put(ACTIONKEY_SYNCHRONIZE_EAS_MD, null);
	}

	/**
	 *output storeFields method
	 */
	public void storeFields() {
		super.storeFields();
	}

	/**
	 * @return
	 */
	protected String getEditUIName() {
		// return FdcEntityObjectEditUI.class.getName();

		return null;
	}

	protected EditShortcutListener synchronizeEasMDListener = new EditShortcutListener(ACTIONKEY_SYNCHRONIZE_EAS_MD); //

	// updateLongname
	class EditShortcutListener extends AbstractAction {
		String shortcut;

		EditShortcutListener(String sc) {
			shortcut = sc;
		}

		public void actionPerformed(ActionEvent evt) {
			if (ACTIONKEY_SYNCHRONIZE_EAS_MD.equals(shortcut)) {
				try {
					actionSynchronizeEasMD_actionPerformed(null);
				} catch (Exception e) {
					e.printStackTrace();
					FdcEntityObjectListUI.this.handleException(e);
				}
			}
		}
	}

	/**
	 * output tblMain_tableClicked method
	 */
	protected void tblMain_tableClicked(com.kingdee.bos.ctrl.kdf.table.event.KDTMouseEvent e) throws Exception {
		// super.tblMain_tableClicked(e);

		// 双击
		if (e.getClickCount() == 2) {
			// 查询数据表
			queryDataTable();
		}
	}

	/**
	 * 查询数据表
	 * 
	 * @throws Exception
	 */
	protected void queryDataTable() throws Exception {
		// 取得数据库表名
		String dataTableName = (String) FDCTableHelper.getSelectCellValue(this, getMainTable(), "dataTable.name");
		String sql = null;
		try {
			// 生成查询SQL
			sql = FdcEntityObjectUtil.generateQuerySql(null, dataTableName, curCols, null);
		} catch (EASBizException e) {
			if ("10-EC_00001".equals(e.getCode())) {
				// 更新数据库中是否存在标识
				FdcEntityObjectUtil.updateIsExist(null, dataTableName);

				// 设置表格中，数据库中是否存在标识
				KDTable table = getMainTable();
				IRow row = KDTableUtil.getSelectedRow(table);
				FDCTableHelper.setCellValue(row, "dataTable.isExist", Boolean.FALSE);

				table = getDetailTable();
				FDCTableHelper.setColumnValue(table, "column.isExist", Boolean.FALSE);
			}

			// 重新抛出异常
			throw e;
		}

		if (FdcStringUtil.isNotEmpty(sql)) {
			sql += ";";

			// 创建UI对象并显示
			Map uiContext = this.getUIContext();
			String uiClassname = FdcIsqlUI.class.getName();
			IUIWindow uiWindow = UIFactory.createUIFactory(UIFactoryName.NEWTAB).create(uiClassname, uiContext, null,
					OprtState.VIEW);
			FMIsqlUI uiObject = (FMIsqlUI) uiWindow.getUIObject();
			StyledEditor txtScript = (StyledEditor) FdcFieldUtil.getValue(uiObject, "txtScript", true);
			txtScript.setText(sql);
			uiWindow.show();

			// 执行SQL
			uiObject.actionExecute_actionPerformed(null);
		}
	}

	/**
	 * output tblMain_tableSelectChanged method
	 */
	protected void tblMain_tableSelectChanged(com.kingdee.bos.ctrl.kdf.table.event.KDTSelectEvent e) throws Exception {
		// super.tblMain_tableSelectChanged(e);

		// 刷新分录
		refreshDetail();
	}

	/**
	 * output treeMain_valueChanged method
	 */
	protected void treeMain_valueChanged(javax.swing.event.TreeSelectionEvent e) throws Exception {
		// super.treeMain_valueChanged(e);

		// 处理实体视图
		dealWithEntityViewInfo(mainQuery, false);

		DefaultKingdeeTreeNode treeNode = (DefaultKingdeeTreeNode) treeMain.getLastSelectedPathComponent();
		if (treeNode == null) {
			return;
		}

		// if (treeNode.isRoot()) {
		// // 根节点禁用
		// FDCClientHelper.setActionEnable(actionSynchronizeMD, false);
		// } else {
		// FDCClientHelper.setActionEnable(actionSynchronizeMD, true);
		// }
		
		// TreeNode[] nodePath = treeNode.getPath();
		// if (FdcArrayUtil.isNotEmpty(nodePath) && 2 == nodePath.length) {
		// FDCClientHelper.setActionEnable(actionSynchronizeMD, true);
		// } else {
		// // 非一级叶子节点，禁用
		// FDCClientHelper.setActionEnable(actionSynchronizeMD, false);
		// }

		MetaDataBriefInfo briefInfo = (MetaDataBriefInfo) treeNode.getUserObject();
		if (treeNode.isRoot() || briefInfo.getMetaDataType().equals(MetaDataTypeList.ENTITY)) {
			// 根节点和叶子节点，禁用“同步元数据”功能
			FDCClientHelper.setActionEnable(actionSynchronizeMD, false);
		} else if (briefInfo.isPackage()) {
			FDCClientHelper.setActionEnable(actionSynchronizeMD, true);
		}

		if (treeNode.isRoot() && isFirstQuery) {
			FilterInfo filterInfo = new FilterInfo();
			mainQuery.setFilter(filterInfo);

			super.execQuery();
			refresh(null);
			KDTableUtil.setSelectedRow(tblMain, 0);

			isFirstQuery = false;
			return;
		}

		super.execQuery();
		refresh(null);
		KDTableUtil.setSelectedRow(tblMain, 0);
	}

	/**
	 * @return
	 */
	protected String getKeyFieldName() {
		return "id";
	}

	private DefineSysEnum getDefineSys() {
		if ("DAP".equals(getUIContext().get(UIContext.UICLASSPARAM))) {
			return DefineSysEnum.DAP;
		} else if ("BTP".equals(getUIContext().get(UIContext.UICLASSPARAM))) {
			return DefineSysEnum.BTP;
		} else {
			return null;
		}
	}

	public String getUIHandlerClassName() {
		// return "com.kingdee.eas.fdc.basedata.scheme.app.FdcEntityObjectListUIHandler";
		return null;
	}

	/**
	 * 描述：初始化过滤项
	 * 
	 * @param treeNode
	 * @return
	 * @throws EASBizException
	 * @throws BOSException
	 * @author rd_skyiter_wang
	 * @createDate 2015-5-21
	 */
	private FilterInfo initFilterItem(DefaultKingdeeTreeNode treeNode) throws EASBizException, BOSException {
		FilterInfo filter = new FilterInfo();
		MetaDataBriefInfo briefInfo = (MetaDataBriefInfo) treeNode.getUserObject();

		List list = new ArrayList();
		if (briefInfo.getMetaDataType().equals(MetaDataTypeList.ENTITY)) {
			String fullName = briefInfo.getFullName();

			FilterItemCollection filterItems = filter.getFilterItems();
			filterItems.add(new FilterItemInfo("fullName", fullName, CompareType.EQUALS));
			list.add(briefInfo);
		} else if (briefInfo.isPackage()) {
			String packgeName = briefInfo.getPackgeName();
			List subSystemList = (List) SUBSYSTEM_MAP.get(packgeName);

			if (FdcCollectionUtil.isNotEmpty(subSystemList)) {
				// 初始化过滤项_子系统隐射
				initFilterItem_subSystemMap(filter, subSystemList);
			} else {
				FilterItemCollection filterItems = filter.getFilterItems();
				filterItems.add(new FilterItemInfo("fullName", packgeName + ".%", CompareType.LIKE));
			}
		}

		return filter;
	}

	/**
	 * 描述：初始化过滤项_子系统隐射
	 * 
	 * @param filter
	 * @param subSystemList
	 * @author rd_skyiter_wang
	 * @createDate 2015-5-21
	 */
	private void initFilterItem_subSystemMap(FilterInfo filter, List subSystemList) {
		int size = subSystemList.size();
		String subSystemName = null;

		FilterItemCollection filterItems = filter.getFilterItems();
		String maskString = "(";
		for (int i = 0; i < size; i++) {
			subSystemName = (String) subSystemList.get(i);
			filterItems.add(new FilterItemInfo("fullName", subSystemName + ".%", CompareType.LIKE));

			if (i != 0) {
				maskString += " or ";
			}
			maskString += "#" + i;
		}
		maskString += ")";

		filter.setMaskString(maskString);
	}

	/**
	 * 深度优先遍历指定结点的子孙结点，找出所有的叶子结点
	 * 
	 * @param treeNode
	 * @return
	 */
	protected static void initEntityBriefList(List list, DefaultKingdeeTreeNode treeNode) {
		if (treeNode.getParent() == null) {
			return;
		}
		int count = treeNode.getChildCount();

		for (int i = 0; i < count; i++) {
			DefaultKingdeeTreeNode childNode = (DefaultKingdeeTreeNode) treeNode.getChildAt(i);
			MetaDataBriefInfo childBriefInfo = (MetaDataBriefInfo) childNode.getUserObject();

			if (childBriefInfo.getMetaDataType().equals(MetaDataTypeList.ENTITY)) {
				list.add(childBriefInfo);
			}

			if (childBriefInfo.isPackage()) {
				initEntityBriefList(list, childNode);
			}
		}
	}

	public boolean isPrepareInit() {
		return true;
	}

	public boolean isPrepareActionAddNew() {
		return true;
	}

	public void actionAddNew_actionPerformed(ActionEvent e) throws Exception {
		super.actionAddNew_actionPerformed(e);
	}

	/**
	 * RPC初始化
	 */
	public IUIActionPostman prepareInit() {
		IUIActionPostman clientHanlder = super.prepareInit();
		RequestContext request = (RequestContext) clientHanlder.getRequestContext();
		request.put("FdcEntityObjectListUI_init_mdbViewName", BotpBillFilter.VIEWNAME);
		//
		return clientHanlder;
	}

	/**
	 * @throws Exception
	 */
	public void onLoad() throws Exception {
		// 只获取集团参数
		fetchInitParam();

		// 获取初始化界面数据
		fetchInitData();

		super.onLoad();

		// 表头标题控件风格设置
		initHeadStyle();

		// 表体设置
		initTableStyle();

		detachListeners();
		// 注册快捷键
		registeShortKey();
		attachListeners();

		if (getDefineSys() != null) {
			// 设置标题
			this.setUITitle(getDefineSys().getAlias());
		}

		if (getDefineSys() != null) {
			// 如果特定为某个系统，则不用显示所属系统列
			IColumn col = this.tblMain.getColumn("defineSys");
			col.getStyleAttributes().setHided(true);
		}

		if (DefineSysEnum.BTP.equals(getDefineSys())) {
			// 如果特定为单据转换平台，则不显示公司列
			IColumn col = this.tblMain.getColumn("org.name");
			col.getStyleAttributes().setHided(true);
		}

		// initTree();

		// 刷新标题
		refreshUITitle();

		// 设置控件按钮状态
		setButtonStatus();

	}

	/**
	 * @throws Exception
	 */
	public void onShow() throws Exception {
		super.onShow();

		// 默认选择第一行
		treeMain.setSelectionRow(0);

	}

	// 子类可以重载
	protected Map fetchInitParam() throws Exception {
		// 这里只获取集团参数
		Map param = FdcParamUtil.getDefaultFdcParam(null);

		return param;

	}

	protected void fetchInitData() throws Exception {

	}

	/**
	 * 设置各按钮的文字与图标状态
	 */
	protected void initTree() {
		// RPC获取子系统树：
		IBriefViewTreeNode vtn = SubSystemUtils.getSubSystemByName("com_kingdee_eas_base_subsystemEntity",
				MetaDataTypeList.ENTITY);

		// 无RPC
		IBriefViewTreeNode rvtn = SubSystemUtils.getRemoveEmptyPackageNode(vtn);
		// 无RPC
		DefaultKingdeeTreeNode root = SubSystemUtils.getKDTreeNode(rvtn);

		((KingdeeTreeModel) treeMain.getModel()).setRoot(root);
	}

	/**
	 * 设置各按钮的文字与图标状态
	 */
	protected void initWorkButton() {
		super.initWorkButton();

		FDCClientHelper.setActionEnV(actionAddNew, false);
		FDCClientHelper.setActionEnV(actionEdit, false);
		FDCClientHelper.setActionEnV(actionRemove, false);
		// FDCClientHelper.setActionEnV(actionView, false);
		FDCClientHelper.setActionEnV(actionCancelCancel, false);
		FDCClientHelper.setActionEnV(actionCancel, false);
		FDCClientHelper.setActionEnV(actionPrintPreview, false);
		FDCClientHelper.setActionEnV(actionPrint, false);
		FDCClientHelper.setActionEnV(actionSegment, false);

		// 暂时先隐藏
		FDCClientHelper.setActionEnV(actionRefresh, false);
		FDCClientHelper.setActionEnV(actionQuery, true);
		FDCClientHelper.setActionEnV(actionLocate, true);

		FDCClientHelper.setActionEnV(actionSynchronizeMD, true);
		// 暂时先隐藏
		FDCClientHelper.setActionEnV(actionSynchronizeEasMD, false);
		FDCClientHelper.setActionEnV(actionSynchronizeBaseMD, true);
		FDCClientHelper.setActionEnV(actionSynchronizeFdcMD, true);
	}

	// 子类可以重载
	protected void initHeadStyle() throws Exception {

	}

	protected void initTableStyle() throws Exception {
		tblMain.setColumnMoveable(true);
		tblMain.getSelectManager().setSelectMode(KDTSelectManager.MULTIPLE_ROW_SELECT);
		// 不锁定，以方便复制
		tblMain.getStyleAttributes().setLocked(false);

		kdtEntries.setColumnMoveable(true);
		kdtEntries.getSelectManager().setSelectMode(KDTSelectManager.MULTIPLE_ROW_SELECT);
		// 不锁定，以方便复制
		kdtEntries.getStyleAttributes().setLocked(false);

		FDCTableHelper.setRender(kdtEntries, "column.length", FdcZeroBaseRender.getInstance());
		FDCTableHelper.setRender(kdtEntries, "column.scale", FdcZeroBaseRender.getInstance());
		FDCTableHelper.setRender(kdtEntries, "column.precision", FdcZeroBaseRender.getInstance());

		// 虚模式分页
		tblMain.getDataRequestManager().setDataRequestMode(KDTDataRequestManager.VIRTUAL_MODE_PAGE);
		tblMain.getDataRequestManager().setPageRowCount(100);

		// 明细表格数据不多，无须虚模式分页
		// kdtEntries.getDataRequestManager().setDataRequestMode(
		// KDTDataRequestManager.VIRTUAL_MODE_PAGE);
		// kdtEntries.getDataRequestManager().setPageRowCount(100);

		// 同步字典时，更新标志位"是否存在"存在性能问题，暂时先隐藏表格中"是否存在"列
		// tblMain.getColumn("dataTable.isExist").getStyleAttributes().setHided(true);
		// kdtEntries.getColumn("column.isExist").getStyleAttributes().setHided(true);
	}

	/**
	 * 注册快捷键
	 * 
	 * @throws Exception
	 */
	protected void registeShortKey() throws Exception {
		InputMap imEntry = this.getInputMap(ClosePeriodUI.WHEN_IN_FOCUSED_WINDOW);
		// ACTIONKEY_SYNCHRONIZE_EAS_MD
		KeyStroke ctrl_shift_f12 = KeyStroke
				.getKeyStroke(KeyEvent.VK_F12, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK);
		imEntry.put(ctrl_shift_f12, ACTIONKEY_SYNCHRONIZE_EAS_MD);
	}

	public KDTable getMainTable() {
		return super.getMainTable();
	}

	public KDTable getDetailTable() {
		return this.kdtEntries;
	}

	protected void refreshUITitle() {
	}

	protected void setButtonStatus() {
	}

	/**
	 * @param uiContext
	 * @param e
	 */
	protected void prepareUIContext(UIContext uiContext, ActionEvent e) {
		super.prepareUIContext(uiContext, e);

		if (e.getSource().equals(btnAddNew)) {
			// 对新增，如果选中了一个单据，则将其作为源单据带入
			DefaultKingdeeTreeNode treeNode = (DefaultKingdeeTreeNode) treeMain.getLastSelectedPathComponent();

			// 处理当treeNode为空的中断错误。
			if (treeNode == null)
				return;

			MetaDataBriefInfo briefInfo = (MetaDataBriefInfo) treeNode.getUserObject();

			EntityObjectInfo srcEntity = SubSystemUtils.getEntityObjectInfo(briefInfo);

			if (srcEntity != null) {
				uiContext.put("srcEntity", srcEntity);
			}

			if (getDefineSys() != null) {
				uiContext.put("defineSys", getDefineSys());
			}
		}
	}

	public boolean isPrepareActionDisable() {
		return false;
	}

	public boolean isPrepareActionEnable() {
		return false;
	}

	public void initUIContentLayout() {
		super.initUIContentLayout();
	}

	public boolean isPrepareActionRemove() {
		return false;
	}

	// 定位
	protected String[] getLocateNames() {
		// String[] locateNames = new String[7];
		//
		// locateNames[0] = "fullName";
		// locateNames[1] = IFWEntityStruct.dataBase_Name;
		// locateNames[2] = "alias";
		// locateNames[4] = "fullBosTypeStr";
		// locateNames[3] = "bosTypeStr";
		// locateNames[5] = "dataTable.name";
		// locateNames[6] = "dataTable.isExist";

		String[] locateNames = new String[] { "fullName", "name", "alias", "fullBosTypeStr", "bosTypeStr",
				"dataTable.name", "dataTable.isExist" };

		return locateNames;
	}

	//
	public RequestContext prepareActionEdit(IItemAction itemAction) throws Exception {
		RequestContext request = super.prepareActionEdit(itemAction);
		// Query刷新需要的参数
		RequestContext requestQuery = this.prepareQueryContext(null);
		request.putAll(requestQuery);
		// 需要传递的参数准备
		checkSelected();
		IRow row = this.tblMain.getRow(this.tblMain.getSelectManager().getActiveRowIndex());
		// IN
		String mappingId = row.getCell(getKeyFieldName()).getValue().toString();
		String currentCU = SysContext.getSysContext().getCurrentCtrlUnit().getId().toString();
		request.put("FdcEntityObjectListUI_ActionEdit_mappingId", mappingId);
		request.put("FdcEntityObjectListUI_ActionEdit_currentCU", currentCU);

		return request;
	}

	public boolean isPrepareActionEdit() {
		return true;
	}

	public boolean isPrepareActionView() {
		return true;
	}

	public RequestContext prepareActionView(IItemAction itemAction) throws Exception {
		checkSelected();

		RequestContext request = super.prepareActionView(itemAction);

		IObjectPK pk = new ObjectUuidPK(BOSUuid.read(getSelectedKeyValue()));
		request.put("FdcEntityObjectListUI_ActionView_mappingId", pk);

		return request;
	}

	public void actionView_actionPerformed(ActionEvent e) throws Exception {
		// TODO 自动生成方法存根
		// super.actionView_actionPerformed(e);

		// 查询数据表
		queryDataTable();
	}

	protected boolean isOrderForClickTableHead() {
		return false;
	}

	public boolean isPrepareActionImportRule() {
		return false;
	}

	public boolean isPrepareActionExportRule() {
		return false;
	}

	/**
	 * output actionExport_actionPerformed method
	 */
	public void actionExportRule_actionPerformed(ActionEvent e) throws Exception {
		checkSelected();
		Map uiContext = new UIContext(this);
		uiContext.put("kdtable", this.tblMain);
		uiContext.put("idList", this.getSelectedIdValues());
		//
		IUIWindow uiWindow = UIFactory.createUIFactory(UIFactoryName.MODEL).create(BOTExportOptionUI.class.getName(),
				uiContext);
		uiWindow.show();
	}

	/**
	 * @return
	 */
	protected String getQueryFieldName() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return
	 */
	protected IObjectPK getSelectedTreeKeyValue() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kingdee.eas.framework.client.TreeListUI#getTreeInterface()
	 */
	protected ITreeBase getTreeInterface() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kingdee.eas.framework.client.TreeDetailListUI#getBizInterface()
	 */
	protected ICoreBase getBizInterface() throws Exception {
		return FdcEntityObjectFactory.getRemoteInstance();
	}

	/**
	 * 刷新分录
	 * 
	 * @throws Exception
	 */
	protected void refreshDetail() throws Exception {
		// 刷新分录表格
		refreshDetailTable();
		// 刷新分录按钮
		refreshDetailButtons();
	}

	/**
	 * 刷新分录表格
	 * 
	 * @throws Exception
	 */
	protected void refreshDetailTable() throws Exception {
		KDTable tbl = getMainTable();
		String billId = getSelectedKeyValue(tbl);

		if (FdcStringUtil.isNotBlank(billId)) {
			// 取得分录集合
			curCols = getEntryCollection(billId);
			// 填充分录表格
			fillDetailTable(curCols);
		} else {
			curCols = null;
			// 清空分录表格
			getDetailTable().removeRows();
		}

	}

	/**
	 * 刷新分录按钮
	 * 
	 * @throws Exception
	 */
	protected void refreshDetailButtons() throws Exception {
	}

	/**
	 * 取得分录集合
	 * 
	 * @param billId
	 * @return
	 * @throws Exception
	 */
	protected FdcPropertyCollection getEntryCollection(String billId) throws Exception {
		FdcPropertyCollection cols = null;

		EntityViewInfo view = new EntityViewInfo();

		SelectorItemCollection selector = new SelectorItemCollection();
		view.setSelector(selector);
		selector.add("id");
		selector.add("name");
		selector.add("alias");
		selector.add("mappingField.id");
		selector.add("mappingField.name");
		selector.add("mappingField.sqlType");
		selector.add("mappingField.length");
		selector.add("mappingField.scale");
		selector.add("mappingField.precision");
		selector.add("mappingField.isNullable");
		selector.add("mappingField.defaultValue");
		selector.add("mappingField.isMultilingual");
		selector.add("mappingField.isExist");
		selector.add("linkProperty.id");
		selector.add("linkProperty.fullName");
		selector.add("linkProperty.name");
		selector.add("linkProperty.alias");
		selector.add("linkProperty.bosTypeStr");

		FilterInfo filter = new FilterInfo();
		view.setFilter(filter);
		filter.appendFilterItem("bill.id", billId);

		cols = FdcPropertyFactory.getRemoteInstance().getFdcPropertyCollection(view);

		return cols;
	}

	/**
	 * 填充分录表格
	 * 
	 * @param clos
	 * @throws Exception
	 */
	protected void fillDetailTable(FdcPropertyCollection clos) throws Exception {
		KDTable tbl = getDetailTable();
		// 清空分录表格
		tbl.removeRows();

		FdcPropertyInfo entryInfo = null;
		IRow row = null;
		for (Iterator iterator = clos.iterator(); iterator.hasNext();) {
			entryInfo = (FdcPropertyInfo) iterator.next();
			row = tbl.addRow();

			// 增加分录表格行
			addDetailTableLine(row, entryInfo);
		}
	}

	/**
	 * 增加分录表格行
	 * 
	 * @param row
	 * @param entryInfo
	 * @throws Exception
	 */
	protected void addDetailTableLine(IRow row, FdcPropertyInfo entryInfo) throws Exception {
		row.setUserObject(entryInfo);

		row.getCell("property.id").setValue(entryInfo.getId());
		row.getCell("property.name").setValue(entryInfo.getName());
		row.getCell("property.alias").setValue(entryInfo.getAlias());

		FdcColumnInfo columnInfo = entryInfo.getMappingField();
		if (null != columnInfo) {
			row.getCell("column.id").setValue(columnInfo.getId());
			row.getCell("column.name").setValue(columnInfo.getName());

			row.getCell("column.sqlType").setValue(columnInfo.getSqlType());
			row.getCell("column.length").setValue(FDCNumberHelper.toBigDecimal(columnInfo.getLength() + ""));
			row.getCell("column.scale").setValue(FDCNumberHelper.toBigDecimal(columnInfo.getScale() + ""));
			row.getCell("column.precision").setValue(FDCNumberHelper.toBigDecimal(columnInfo.getPrecision() + ""));

			row.getCell("column.isNullable").setValue(Boolean.valueOf(columnInfo.isIsNullable()));
			row.getCell("column.defaultValue").setValue(columnInfo.getDefaultValue());
			row.getCell("column.isMultilingual").setValue(Boolean.valueOf(columnInfo.isIsMultilingual()));
			row.getCell("column.isExist").setValue(Boolean.valueOf(columnInfo.isIsExist()));
		}
		FdcEntityObjectInfo linkPropertyInfo = entryInfo.getLinkProperty();
		if (null != linkPropertyInfo) {
			row.getCell("linkProperty.id").setValue(linkPropertyInfo.getId());
			row.getCell("linkProperty.fullName").setValue(linkPropertyInfo.getFullName());
			row.getCell("linkProperty.name").setValue(linkPropertyInfo.getName());
			row.getCell("linkProperty.alias").setValue(linkPropertyInfo.getAlias());
			row.getCell("linkProperty.bosTypeStr").setValue(linkPropertyInfo.getBosTypeStr());
		}

		row.getCell("property.relationshipUrlName").setValue(entryInfo.getRelationshipUrlName());
	}

	/**
	 * 获取当前选择行的主键
	 * 
	 * @return 返回当前选择行的主键，若当前选择行为空，或者当前选中行的主键列为空，则返回null
	 */
	protected String getSelectedKeyValue(KDTable table) {
		String keyValueStr = null;

		IRow row = getSelectedRow(table);
		if (row == null) {
			return null;
		}

		ICell cell = row.getCell(getKeyFieldName());
		if (cell == null) {
			MsgBox.showError(EASResource.getString(FrameWorkClientUtils.strResource + "Error_KeyField_Fail"));
			SysUtil.abort();
		}

		Object keyValue = cell.getValue();
		if (keyValue != null) {
			keyValueStr = keyValue.toString();
		}

		return keyValueStr;
	}

	/**
	 * 获取当前选择行的主键
	 * 
	 * @return 返回当前选择行的主键，若当前选择行为空，或者当前选中行的主键列为空，则返回null
	 */
	protected IRow getSelectedRow(KDTable table) {
		IRow row = null;

		KDTSelectBlock selectBlock = table.getSelectManager().get();
		if (selectBlock != null) {
			int rowIndex = selectBlock.getTop();
			row = table.getRow(rowIndex);
		}

		return row;
	}

	public void actionSynchronizeMD_actionPerformed(ActionEvent e) throws Exception {
		DefaultKingdeeTreeNode treeNode = (DefaultKingdeeTreeNode) treeMain.getLastSelectedPathComponent();
		// 检查选择节点
		checkSelectNode(treeNode);

		// 检查是否Administrator登录
		checkIsLogin();

		int result = MsgBox.showConfirm2(this, "同步元数据将花费较长的时间，是否继续？");
		if (AdvMsgBox.OK_OPTION == result) {
			MetaDataBriefInfo briefInfo = (MetaDataBriefInfo) treeNode.getUserObject();
			String fullName = null;
			boolean isPackage = false;
			if (briefInfo.getMetaDataType().equals(MetaDataTypeList.ENTITY)) {
				fullName = briefInfo.getFullName();
				isPackage = false;
			} else if (briefInfo.isPackage()) {
				fullName = briefInfo.getPackgeName();
				// 选择"房地产_项目运营"或"房地产_营销服务"，直接同步所有房地产的元数据
				if (SUBSYSTEM_FDC_REPM.equals(fullName) || SUBSYSTEM_FDC_REPM.equals(fullName)) {
					fullName = SUBSYSTEM_FDC;
				}

				isPackage = true;
			}

			long startTime = System.currentTimeMillis();
			FdcEntityObjectFactory.getRemoteInstance().synchronizeMD(fullName, isPackage);
			long endTime = System.currentTimeMillis();
			double time = (endTime - startTime) * 1.0 / 1000;

			MsgBox.showInfo(this, "同步完成，耗时" + time + "秒");
			this.treeMain_valueChanged(null);
		}

	}

	public void actionSynchronizeEasMD_actionPerformed(ActionEvent e) throws Exception {
		// 检查是否Administrator登录
		checkIsLogin();

		int result = MsgBox.showConfirm2(this, "从根节点同步元数据，在低配置机器上有可能导致内存溢出，是否继续？");
		if (AdvMsgBox.OK_OPTION == result) {
			long startTime = System.currentTimeMillis();
			FdcEntityObjectFactory.getRemoteInstance().synchronizeEasMD();
			long endTime = System.currentTimeMillis();
			double time = (endTime - startTime) * 1.0 / 1000;

			MsgBox.showInfo(this, "同步完成，耗时" + time + "秒");
			this.treeMain_valueChanged(null);
		}
	}

	public void actionSynchronizeBaseMD_actionPerformed(ActionEvent e) throws Exception {
		// 检查是否Administrator登录
		checkIsLogin();

		int result = MsgBox.showConfirm2(this, "同步元数据将花费较长的时间，是否继续？");
		if (AdvMsgBox.OK_OPTION == result) {
			long startTime = System.currentTimeMillis();
			FdcEntityObjectFactory.getRemoteInstance().synchronizeBaseMD();
			long endTime = System.currentTimeMillis();
			double time = (endTime - startTime) * 1.0 / 1000;

			MsgBox.showInfo(this, "同步完成，耗时" + time + "秒");
			this.treeMain_valueChanged(null);
		}
	}

	public void actionSynchronizeFdcMD_actionPerformed(ActionEvent e) throws Exception {
		// 检查是否Administrator登录
		checkIsLogin();

		int result = MsgBox.showConfirm2(this, "同步元数据将花费较长的时间，是否继续？");
		if (AdvMsgBox.OK_OPTION == result) {
			long startTime = System.currentTimeMillis();
			FdcEntityObjectFactory.getRemoteInstance().synchronizeFdcMD();
			long endTime = System.currentTimeMillis();
			double time = (endTime - startTime) * 1.0 / 1000;

			MsgBox.showInfo(this, "同步完成，耗时" + time + "秒");
			this.treeMain_valueChanged(null);
		}
	}

	/**
	 * 检查选择节点
	 * 
	 * @throws Exception
	 */
	public void checkSelectNode(DefaultKingdeeTreeNode treeNode) throws Exception {
		if (treeNode == null) {
			MsgBox.showWarning(this, "请选择树形节点");
			SysUtil.abort();
		}

		if (treeNode.isRoot()) {
			// 根节点禁用
			FDCClientHelper.setActionEnable(actionSynchronizeMD, false);
			MsgBox.showWarning(this, "从根节点同步元数据，在低配置机器上有可能引起宕机，禁止操作！");
			SysUtil.abort();
		} else {
			FDCClientHelper.setActionEnable(actionSynchronizeMD, true);
		}
	}

	/**
	 * 检查是否Administrator登录
	 * 
	 * @throws Exception
	 */
	public void checkIsLogin() throws Exception {
		boolean flag = isLogin();
		if (!flag) {
			abort();
		}
	}

	/**
	 * 是否登录
	 * 
	 * @return
	 * @throws EASBizException
	 */
	public boolean isLogin() throws EASBizException {
		// UserInfo userInfo = ContextHelperFactory.getRemoteInstance().getCurrentUser();
		UserInfo userInfo = SysContext.getSysContext().getCurrentUserInfo();
		if (com.kingdee.eas.base.permission.Administrator.isSuperCUAdmin(new ObjectUuidPK(userInfo.getId()))) {
			return true;
		}

		if (Administrator.ID.equals(userInfo.getId().toString())) {
			return true;
		}

		try {
			userInfo = UserFactory.getRemoteInstance().getUserByID(new ObjectUuidPK(Administrator.ID));
		} catch (BOSException e) {
			logger.error("", e);
		}

		JPasswordField pwd = new JPasswordField();

		Object[] message = {
				com.kingdee.eas.util.client.EASResource.getString(
						"com.kingdee.eas.fm.common.COMMONAutoGenerateResource", "49_FMIsqlUI"), pwd };

		int res = JOptionPane.showConfirmDialog(this, message, " ", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		if (res != JOptionPane.OK_OPTION) {
			return false;
		}

		// String userPassword = JOptionPane.showInputDialog(this, "请输入管理员密码!", "");
		String logonPassword = new String(pwd.getPassword());

		if (matchPassword(userInfo, logonPassword)) {
			return true;
		}

		return false;
	}

	/**
	 * 匹配密码
	 * 
	 * @param userInfo
	 * @param password
	 * @return
	 * @throws EASBizException
	 */
	public static boolean matchPassword(UserInfo userInfo, String password) throws EASBizException {
		if (StringUtils.isEmpty(password)) {
			return StringUtils.isEmpty(userInfo.getPassword());
		} else {
			return encrypt(userInfo.getId().toString(), password).equals(userInfo.getPassword());
		}
	}

	/**
	 * 加密
	 * 
	 * @param userID
	 * @param password
	 * @return
	 * @throws EASBizException
	 */
	public static String encrypt(String userID, String password) throws EASBizException {

		try {
			return CryptoUtil.encrypt(userID + password.trim());
		} catch (Exception e) {
			throw new UserException(UserException.ENCRYPT_FAIL);
		}
	}

	// /////////////////////////////////////////////////////////////////////////
	// 与树形结构相关方法
	// /////////////////////////////////////////////////////////////////////////

	// 获取选中的元数据
	protected MetaDataBriefInfo getSelectedMetaDataBriefInfo() {
		DefaultKingdeeTreeNode treeNode = (DefaultKingdeeTreeNode) treeMain.getLastSelectedPathComponent();

		if (treeNode != null && treeNode.getUserObject() instanceof MetaDataBriefInfo) {
			return (MetaDataBriefInfo) treeNode.getUserObject();
		}

		return null;
	}

	// 获取选中的实体
	protected MetaDataBriefInfo getSelectedEntityInfo() {
		return getSelectedMetaDataBriefInfo();
	}

	// 查询数据时,加入cu,table,type的过滤
	protected IQueryExecutor getQueryExecutor(IMetaDataPK queryPK, EntityViewInfo viewInfo) {
		IQueryExecutor queryExecutor = super.getQueryExecutor(queryPK, getQueryEntityViewInfo(viewInfo));
		QueryExecuteOption queryExecuteOption = queryExecutor.option();
		queryExecuteOption.isAutoIgnoreZero = true;
		queryExecuteOption.isAutoTranslateBoolean = true;
		queryExecuteOption.isAutoTranslateEnum = true;

		// 打印SQL存在性能问题，暂时屏蔽掉 by skyiter_wang 2015-05-21
		String sql = null;
		try {
			if (logger.isDebugEnabled()) {
				sql = queryExecutor.getSQL();
				String classSimpleName = FdcClassUtil.getSimpleName(this.getClass());

				logger.info("================================================================");
				logger.info(classSimpleName + ".getQueryExecutor()，sql:" + sql);
				logger.info(classSimpleName + ".getQueryExecutor()，viewInfo:" + viewInfo);
				logger.info("================================================================");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return queryExecutor;
	}

	protected EntityViewInfo getQueryEntityViewInfo(EntityViewInfo viewInfo) {
		EntityViewInfo view = (EntityViewInfo) viewInfo.clone();

		FilterInfo filter = new FilterInfo();
		view.setFilter(filter);
		try {
			// 默认过滤
			FilterInfo defaultFilter = getDefaultFilterForQuery();
			// 树过滤
			FilterInfo treeFilter = getSubSystemTreeQuery();
			// 当前过滤
			FilterInfo currentFilter = viewInfo.getFilter();

			filter.mergeFilter(defaultFilter, "and");
			filter.mergeFilter(treeFilter, "and");
			filter.mergeFilter(currentFilter, "and");
		} catch (Exception e) {
			e.printStackTrace();
			this.handUIException(e);
		}

		// 处理实体视图
		dealWithEntityViewInfo(view, true);

		return view;
	}

	/**
	 * 处理实体视图
	 * 
	 * @param view
	 * @param setIdIsNull
	 */
	protected void dealWithEntityViewInfo(EntityViewInfo view, boolean setIdIsNull) {
		// 采用上下分录表格时，只查询实体，数据不是很多，可暂时不处理
		if (true) {
			return;
		}

		if (setIdIsNull) {
			FilterInfo filter = view.getFilter();
			filter = (FilterInfo) FdcObjectUtil.defaultIfNull(filter, new FilterInfo());
			view.setFilter(filter);
			FilterItemCollection filterItems = filter.getFilterItems();

			// 没有任何过滤条件，选择根节点，查询结果太多，影响性能，故不显示任何结果
			boolean flag = false;
			if (filterItems.isEmpty()) {
				filterItems.add(new FilterItemInfo("id", null));

				flag = true;
			} else if (filterItems.size() == 1) {
				FilterItemInfo item0 = filterItems.get(0);
				if (null == item0) {
					item0 = new FilterItemInfo("id", null);
					flag = true;
				} else if ("fullName like 'com.kingdee.eas.%'".equalsIgnoreCase(item0.toString())) {
					item0 = new FilterItemInfo("id", null);
					flag = true;
				}

				filterItems.set(0, item0);
			}
			if (flag) {
				// MsgBox.showWarning(this, "没有任何过滤条件，选择根节点，查询结果太多，影响性能，故不显示任何结果！");
				// SysUtil.abort();
			}
		} else {
			FilterInfo filter = view.getFilter();
			filter = (FilterInfo) FdcObjectUtil.defaultIfNull(filter, new FilterInfo());
			view.setFilter(filter);
			FilterItemCollection filterItems = filter.getFilterItems();

			DefaultKingdeeTreeNode treeNode = (DefaultKingdeeTreeNode) treeMain.getLastSelectedPathComponent();
			if (treeNode == null) {
				return;
			}
			MetaDataBriefInfo briefInfo = (MetaDataBriefInfo) treeNode.getUserObject();
			int size = filterItems.size();
			if (briefInfo.isPackage() && size >= 1) {
				FilterItemInfo item0 = filterItems.get(0);
				if ((null != item0) && "id IS NULL".equalsIgnoreCase(item0.toString())) {
					// 选择eas时，至少有一个额外过滤条件
					if (briefInfo.getPackgeName().equalsIgnoreCase("com.kingdee.eas") && size >= 2) {
						item0 = new FilterItemInfo("id", null, CompareType.NOTEQUALS);
					}
					// 非eas
					else {
						item0 = new FilterItemInfo("id", null, CompareType.NOTEQUALS);
					}
				}
				filterItems.set(0, item0);
			}
		}

	}

	// 怱略CU过滤
	protected boolean isIgnoreCUFilter() {
		return true;
	}

	protected FilterInfo getBizOrgFilter(EntityViewInfo viewInfo) throws Exception {
		FilterInfo filter = new FilterInfo();

		// // 有业务范围的组织
		// Set orgSet = EcUtils.getAuthorizedOrgs(OrgType.Transport);
		// if (orgSet != null) {
		// filter.getFilterItems().add(new FilterItemInfo("projectOrg.id", orgSet,
		// CompareType.INCLUDE));
		// }
		//
		// if (viewInfo.getFilter() != null) {
		// filter.mergeFilter(viewInfo.getFilter(), "and");
		// }

		return filter;
	}

	// 默认进行当前CU的过滤。子类可重载。
	protected FilterInfo getDefaultFilterForQuery() {
		// super.getDefaultFilterForQuery();
		FilterInfo filter = new FilterInfo();
		return filter;
	}

	// 左边子系统树过滤
	protected FilterInfo getSubSystemTreeQuery() {
		DefaultKingdeeTreeNode treeNode = (DefaultKingdeeTreeNode) treeMain.getLastSelectedPathComponent();

		FilterInfo filterInfo = null;
		try {
			filterInfo = initFilterItem(treeNode);
		} catch (Exception e) {
			this.handUIException(e);
		}

		return filterInfo;
	}

	// 新增时，必须选择左边树明细节点
	public boolean mustSelectDetail() {
		return true;
	}

	// /////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////

}