package com.kingdee.eas.xr.helper;

import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.ctrl.kds.expans.model.data.ParameterImpl;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.ui.face.IUIFactory;
import com.kingdee.bos.ui.face.IUIWindow;
import com.kingdee.bos.ui.face.UIException;
import com.kingdee.bos.ui.face.UIFactory;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.base.btp.BTPException;
import com.kingdee.eas.base.btp.BTPManagerFactory;
import com.kingdee.eas.base.btp.BTPTransformResult;
import com.kingdee.eas.base.codingrule.CodingRuleException;
import com.kingdee.eas.base.codingrule.CodingRuleManagerFactory;
import com.kingdee.eas.base.codingrule.ICodingRuleManager;
import com.kingdee.eas.base.message.BMCMessageFactory;
import com.kingdee.eas.base.message.BMCMessageInfo;
import com.kingdee.eas.base.message.IBMCMessage;
import com.kingdee.eas.base.message.MsgPriority;
import com.kingdee.eas.base.message.MsgStatus;
import com.kingdee.eas.base.message.MsgType;
import com.kingdee.eas.base.permission.PermissionUtils;
import com.kingdee.eas.base.permission.UserFactory;
import com.kingdee.eas.base.permission.UserInfo;
import com.kingdee.eas.base.receiver.BasReceiverCollection;
import com.kingdee.eas.base.receiver.BasReceiverInfo;
import com.kingdee.eas.basedata.org.AdminOrgUnitInfo;
import com.kingdee.eas.basedata.org.SaleOrgUnitInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.common.client.SysContext;
import com.kingdee.eas.common.client.UIContext;
import com.kingdee.eas.common.client.UIFactoryName;
import com.kingdee.eas.fm.common.ContextHelperFactory;
import com.kingdee.eas.fm.common.IContextHelper;
import com.kingdee.eas.framework.CoreBillBaseInfo;
import com.kingdee.eas.framework.DataBaseInfo;
import com.kingdee.eas.port.pm.JavaDataSet.MyExtReportRunUI;
import com.kingdee.eas.rpts.ctrlreport.client.ExtReportRunUI;
import com.kingdee.eas.util.app.ContextUtil;
import com.kingdee.eas.util.client.ExceptionHandler;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.StringUtils;

public class SysPlatformXRHelper {
	/**
	 * 是否新增显示
	 * 组织ID参数传入时已取了单据成本中心或当前组织ID，此处不应该再取当前组织ID
	 * @param caller
	 * @param orgId
	 * @return
	 */
	public static boolean isCodingRuleAddView(IObjectValue caller, String orgId) {
		ICodingRuleManager iCodingRuleManager;
		boolean isAddView = false;
		if(orgId==null){
			return isAddView;
		}
		try {
			iCodingRuleManager = CodingRuleManagerFactory.getRemoteInstance();
			isAddView = iCodingRuleManager.isAddView(caller, orgId);
		} catch (Exception e) {
			ExceptionHandler.handle(e);
		}

		return isAddView;
	}
	/**
	 * BOTP生成记录
	 * number: botp转换规则编码
	 * idArray：原单据ID集合ids
	 * entriesKey：分录ID集合ids
	 * desBosType:目标单据BOSTYPE
	 * srcBosType:目标单据BosType
	 * @param destEntryID  目标单据ID
	 * 
	 * */
	public boolean createToBill(String number,String[] idArray,List entriesKey
			,String desBosType, String srcBosType) throws BTPException, BOSException{
		String botpMapID = "";
		XRSQLBuilder bu = new XRSQLBuilder();
		bu.appendSql("select t.fid ,t.fsrcentityname,t.fdestentityname from T_BOT_Mapping t where t.fname='"+number+"'");
		IRowSet set = bu.executeQuery();
		try {
			while(set.next()){
				botpMapID = set.getString("fid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		BTPTransformResult result = BTPManagerFactory.getRemoteInstance().transformForBotp(idArray
				,new String[] {"entrys"}
			,entriesKey
			,new SelectorItemCollection()
			,desBosType
			,new ObjectUuidPK(botpMapID)
			,srcBosType);
		return result.getBills().size()>0;
	}
	/**
	 * 获取原单据ID
	 * @param destEntryID  目标单据ID
	 * */
	public static String getSrcEntryId(String destEntryID)throws Exception
	{
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT  FSRCENTRYID FROM T_BOT_RELATIONENTRY WHERE FDESTENTRYID ='"+destEntryID+"'");
			XRSQLBuilder fdc = new XRSQLBuilder();
			fdc.appendSql(buffer.toString());
			ResultSet rs = fdc.executeQuery();
			String srcEntryid = null;
			if(rs.next())
				srcEntryid = rs.getString("FSRCENTRYID");
			return srcEntryid;
	}
	/**
	 * 处理基础资料不允许断号的编码规则，在服务器端保存前调用
	 * @param ctx
	 * @param info 待保存的值对象
	 * */
	public static void handleIntermitNumber(Context ctx, DataBaseInfo info)
			throws BOSException, CodingRuleException, EASBizException {
		// 如果用户在客户端手工选择了断号,则此处不必在抢号
		if (info.getNumber() != null && info.getNumber().length() > 0)
			return;
		ICodingRuleManager iCodingRuleManager = CodingRuleManagerFactory.getLocalInstance(ctx);
		String costUnitId = ContextUtil.getCurrentOrgUnit(ctx).getId().toString();
		if (StringUtils.isEmpty(costUnitId)) {
			return;
		}
		if (iCodingRuleManager.isExist(info, costUnitId)) {
			// 选择了断号支持或者没有选择新增显示,获取并设置编号
			if (iCodingRuleManager.isUseIntermitNumber(info, costUnitId)
					|| !iCodingRuleManager.isAddView(info, costUnitId))
			// 此处的orgId与步骤1）的orgId时一致的，判断用户是否启用断号支持功能
			{
				// 启用了断号支持功能，此时只是读取当前最新编码，真正的抢号在保存时
				String number = iCodingRuleManager.getNumber(info, costUnitId);
				info.setNumber(number);
			}
		}
	}
	
	/**
	 * 回收Number，如果配置了编码规则并支持断号的话
	 * @param ctx
	 * @param info
	 * @throws BOSException
	 * @throws EASBizException
	 * @throws CodingRuleException
	 */
	public static void recycleNumber(Context ctx, DataBaseInfo info) throws BOSException, EASBizException, CodingRuleException {
		SaleOrgUnitInfo currentSaleUnit = ContextUtil.getCurrentSaleUnit(ctx);
		String curOrgId = currentSaleUnit.getId().toString();
		if(info.getNumber()!=null&&info.getNumber().length()>0){
			ICodingRuleManager iCodingRuleManager = CodingRuleManagerFactory.getLocalInstance(ctx);
	        if (iCodingRuleManager.isExist(info, curOrgId) && iCodingRuleManager.isUseIntermitNumber(info, curOrgId)) {
	            iCodingRuleManager.recycleNumber(info, curOrgId, info.getNumber());
	        }
		}
	}
	/**
	 * 获取单据编码规则定义编码
	 * 
	 * @return
	 * @throws BOSException
	 * @throws EASBizException
	 */
	public static String getBillNumber(Context ctx, CoreBillBaseInfo editData,String companyID)throws BOSException, EASBizException {
		
		ICodingRuleManager iCodingRuleManager=null;
		if(ctx==null){
			companyID = ((AdminOrgUnitInfo)SysContext.getSysContext().getCurrentAdminUnit()).getId().toString();
			iCodingRuleManager=CodingRuleManagerFactory.getRemoteInstance();
			if (iCodingRuleManager.isExist(editData,companyID)) {
				return iCodingRuleManager.getNumber(editData,companyID);
			}else{
				return null;
			}
		}else{
			companyID = ((AdminOrgUnitInfo)ContextUtil.getCurrentAdminUnit(ctx)).getId().toString();
			iCodingRuleManager= CodingRuleManagerFactory.getLocalInstance(ctx);
			if (iCodingRuleManager.isExist(editData,companyID)) {
				return iCodingRuleManager.getNumber(editData,companyID,"orderSort");
			}else{
				return null;
			}
		}
	}
	/**
	 * 比较用户名，密码
	 * userNumber:用户名
	 * userPwd:密码
	 * */
	public static boolean comparePassword(String userNumber,String userPwd){
		boolean isMatch = false;
		UserInfo userInfo;
		try {
			userInfo = UserFactory.getRemoteInstance().getUserInfo("where number = '"+userNumber+"'");
			String passwordINDB = userInfo.getPassword();
			if(StringUtils.isEmpty(userPwd)){
				isMatch = StringUtils.isEmpty(passwordINDB);
			} else{
				isMatch = PermissionUtils.encrypt(userInfo.getId().toString(), userPwd).equals(passwordINDB);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return isMatch;
	}
	/**
	 * 消息类型：例如通知消息，任务消息，状态更新消息
	 * title:消息标题
	 * body:消息体
	 * Receivers:消息接收人
	 * */
	public static void sendBMCMsgInfo(Context ctx, String title, String body, UserInfo Receivers)
    throws BOSException
	{
		if(Receivers == null)
			return;
	    IBMCMessage iBMCMessage;
	    IContextHelper localInstance;
	    if(ctx == null)
	    {
	    	iBMCMessage = BMCMessageFactory.getRemoteInstance();
	    	localInstance = ContextHelperFactory.getRemoteInstance();
	    } else
	    {
	    	iBMCMessage = BMCMessageFactory.getLocalInstance(ctx);
	    	localInstance = ContextHelperFactory.getLocalInstance(ctx);
	    }
	    BMCMessageInfo msgInfo = new BMCMessageInfo();
	    msgInfo.setTitle(title);
	    msgInfo.setBody(body);
	    msgInfo.setPriority(MsgPriority.MIDDLE);
	    msgInfo.setReceivers(Receivers.getName());
	    msgInfo.setType(MsgType.ONLINE);
	    msgInfo.setStatus(MsgStatus.UNREADED);
	    msgInfo.setId(BOSUuid.create(msgInfo.getBOSType()));
	    msgInfo.setSender(localInstance.getCurrentUser().getName());
	    BasReceiverInfo receiverInfo = new BasReceiverInfo();
	    receiverInfo.setType("1");
	    receiverInfo.setValue(Receivers.getId().toString());
	    receiverInfo.setDesc(Receivers.getName());
	    receiverInfo.setIsShow(false);
	    BasReceiverCollection receivercoll = new BasReceiverCollection();
	    receivercoll.add(receiverInfo);
	    iBMCMessage.addHandMsg(msgInfo, receivercoll);
	}
	
	public static void openPrintForRpt(CoreUIObject ui, String billId,String reportId) throws UIException
	{
		Map filterMap = new HashMap();
		
		Map valuesMap = new HashMap();
		valuesMap.put("DT", "0");
		valuesMap.put("NM", "billId");
		valuesMap.put("AA","billId" );
		valuesMap.put("VA", billId);
		ParameterImpl impl = new ParameterImpl();
		impl.fromJsonMap(valuesMap);
		
		filterMap.put("billId", impl);
		
		UIContext uiContext = new UIContext(ui);
		uiContext.put("checkLicense", "true");
		uiContext.put("UIClassParam", reportId);
		uiContext.put("MainMenuName", "打印数据预览");
		uiContext.put("canExcute", "true");
		uiContext.put("isEntryFromMenu", "false");

		uiContext.put("isLimitRecord", "false");
		uiContext.put("filterMap", filterMap);

		String name = MyExtReportRunUI.class.getName();
		
		IUIFactory uiFactory = UIFactory.createUIFactory(UIFactoryName.NEWWIN);
		IUIWindow uiWindow = uiFactory.create(name, uiContext,null, "VIEW");
		
		ExtReportRunUI rptui = (ExtReportRunUI)uiWindow.getUIObject();
		rptui.setPreferredSize(new Dimension(111,111));
		uiWindow.show();
	}
}
