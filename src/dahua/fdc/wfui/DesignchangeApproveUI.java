/**
 * output package name
 */
package com.kingdee.eas.fdc.wfui;

import java.awt.event.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Map;

import org.apache.log4j.Logger;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.KDTDefaultCellEditor;
import com.kingdee.bos.ctrl.kdf.table.KDTMergeManager;
import com.kingdee.bos.ctrl.kdf.table.KDTableHelper;
import com.kingdee.bos.ctrl.kdf.table.event.KDTMouseEvent;
import com.kingdee.bos.ctrl.kdf.util.style.Styles;
import com.kingdee.bos.ctrl.kdf.util.style.Styles.HorizontalAlignment;
import com.kingdee.bos.ctrl.swing.KDCheckBox;
import com.kingdee.bos.ctrl.swing.KDFormattedTextField;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.eas.fdc.basedata.ApportionTypeInfo;
import com.kingdee.eas.fdc.basedata.FDCHelper;
import com.kingdee.eas.fdc.basedata.FDCSQLBuilder;
import com.kingdee.eas.fdc.basedata.ProjectStageEnum;
import com.kingdee.eas.fdc.basedata.client.FDCMsgBox;
import com.kingdee.eas.fdc.basedata.client.FDCTableHelper;
import com.kingdee.eas.framework.*;
import com.kingdee.jdbc.rowset.IRowSet;

/**
 * 设计变更申请单审批界面
 * output class name
 */
public class DesignchangeApproveUI extends AbstractDesignchangeApproveUI
{
    private static final Logger logger = CoreUIObject.getLogger(DesignchangeApproveUI.class);
    
    /**
     * output class constructor
     */
    public DesignchangeApproveUI() throws Exception
    {
        super();
    }

    
    public void onLoad() throws Exception {
    	
    	super.onLoad();
    	this.kDTable1.getStyleAttributes().setWrapText(true);
    	initUI();
    	
    	KDTableHelper.autoFitRowHeight(this.kDTable1, 4);
    }
   
    private void initUI() throws BOSException, SQLException{
    	

    	//新增布尔控件
    	KDCheckBox cb = new KDCheckBox();
    	KDTDefaultCellEditor editor = new KDTDefaultCellEditor(cb);
    	
    	this.kDTable1.addColumns(13);
    	KDTMergeManager mergeManager = kDTable1.getMergeManager();
    	
    	//第一行
    	IRow addRow = this.kDTable1.addRow();
    	addRow.getCell(0).setValue("事项名称:");

    	//融合(1)-(3)是行 2-4是列
    	mergeManager.mergeBlock(0, 1, 0, 12);

    	//第二行
    	IRow addRowtwo = this.kDTable1.addRow();
    	addRowtwo.getCell(0).setValue("适用范围:");
    	//融合(1)-(3)是行 2-4是列
    	mergeManager.mergeBlock(1, 1, 1, 12);
    	
    	//第三行
    	IRow addRowthree = this.kDTable1.addRow();
    	addRowthree.getCell(0).setValue("提出方:");
//    	addRowthree.getCell(1).setValue("XX");
    	addRowthree.getCell(1).setEditor(editor);
    	addRowthree.getCell(1).setValue(Boolean.FALSE);
//    	addRowthree.getCell(1).getStyleAttributes().setHorizontalAlign(Styles.HorizontalAlignment.RIGHT);//对齐
    	addRowthree.getCell(2).setValue("设计部");
//    	addRowthree.getCell(3).setValue("XX");
    	addRowthree.getCell(3).setEditor(editor);
    	addRowthree.getCell(3).setValue(Boolean.FALSE);
    	addRowthree.getCell(4).setValue("工程部");
//    	addRowthree.getCell(5).setValue("XX");
    	addRowthree.getCell(5).setEditor(editor);
    	addRowthree.getCell(5).setValue(Boolean.FALSE);
    	addRowthree.getCell(6).setValue("前期配套部");
//    	addRowthree.getCell(7).setValue("XX");
    	addRowthree.getCell(7).setEditor(editor);
    	addRowthree.getCell(7).setValue(Boolean.FALSE);
    	addRowthree.getCell(8).setValue("销售部");
//    	addRowthree.getCell(9).setValue("XX");
    	addRowthree.getCell(9).setEditor(editor);
    	addRowthree.getCell(9).setValue(Boolean.FALSE);
    	addRowthree.getCell(10).setValue("其他");    
    	//融合(1)-(3)是行 2-4是列
    	mergeManager.mergeBlock(2, 0, 3, 0);
    	mergeManager.mergeBlock(2, 10,2, 12);

    	
    	
    	//第四行
    	IRow addRowfour = this.kDTable1.addRow();
    	addRowfour.getCell(0).setValue("提出方");
    	addRowfour.getCell(2).setValue("经办人:");
    	addRowfour.getCell(3).setValue("人员");
    	addRowfour.getCell(6).setValue("审核人:");   
    	addRowfour.getCell(7).setValue("人员");   
    	addRowfour.getCell(10).setValue("提出时间:");
    	//融合(1)-(3)是行 2-4是列
//    	mergeManager.mergeBlock(3, 1, 3, 3);
    	mergeManager.mergeBlock(3, 3, 3, 5);
    	mergeManager.mergeBlock(3, 7, 3, 9);
    	mergeManager.mergeBlock(3, 11, 3, 12);
    	mergeManager.mergeBlock(2, 0, 3, 0);

    	//第五行
    	IRow addRowfive = this.kDTable1.addRow();
    	addRowfive.getCell(0).setValue("变更原因及建议方案");
    	
    	//融合(1)-(3)是行 2-4是列
    	mergeManager.mergeBlock(4, 1, 4, 12);

    	
    	//第六行
    	IRow addRowsix = this.kDTable1.addRow();
    	addRowsix.getCell(1).setValue("部门");
    	addRowsix.getCell(5).setValue("预估产生影响");
    	addRowsix.getCell(11).setValue("负责人签字");
    	addRowsix.getCell(12).setValue("日期");
    	//融合(1)-(3)是行 2-4是列
    	mergeManager.mergeBlock(5, 2, 5, 10);
    	
    	//第七行
    	IRow addRowseven = this.kDTable1.addRow();
    	addRowseven.getCell(1).setValue("设计部");
    	addRowseven.getCell(2).setValue("产品品质");
    	addRowseven.getCell(3).setEditor(editor);
    	addRowseven.getCell(3).setValue(Boolean.FALSE);
    	addRowseven.getCell(4).setValue("提高");
    	addRowseven.getCell(5).setEditor(editor);
    	addRowseven.getCell(5).setValue(Boolean.FALSE);
    	addRowseven.getCell(6).setValue("降低");
    	addRowseven.getCell(7).setEditor(editor);
    	addRowseven.getCell(7).setValue(Boolean.FALSE);
    	addRowseven.getCell(8).setValue("无影响");
    	//融合(1)-(3)是行 2-4是列
    	mergeManager.mergeBlock(6, 8, 6, 10);


    	
    	//第八行
    	IRow addRoweight = this.kDTable1.addRow();
    	addRoweight.getCell(1).setValue("工程部:");
    	addRoweight.getCell(2).setValue("工期");
//    	addRoweight.getCell(3).setValue("XX");
    	addRoweight.getCell(3).setEditor(editor);
    	addRoweight.getCell(3).setValue(Boolean.FALSE);
    	addRoweight.getCell(4).setValue("缩短");
    	addRoweight.getCell(5).setEditor(editor);
    	addRoweight.getCell(5).setValue(Boolean.FALSE);
    	addRoweight.getCell(6).setValue("延长");
    	addRoweight.getCell(7).setEditor(editor);
    	addRoweight.getCell(7).setValue(Boolean.FALSE);
    	addRoweight.getCell(8).setValue("无影响");
    	
//    	mergeManager.mergeBlock(5, 0, 10, 0);
    	
    	
    	//第九行
    	IRow addRownine = this.kDTable1.addRow();
    	addRownine.getCell(2).setValue("是否返工");
    	addRownine.getCell(3).setEditor(editor);
    	addRownine.getCell(3).setValue(Boolean.FALSE);
    	addRownine.getCell(4).setValue("需要返工");
    	addRownine.getCell(5).setEditor(editor);
    	addRownine.getCell(5).setValue(Boolean.FALSE);
    	addRownine.getCell(6).setValue("不需返工");

    	//第十行
    	IRow addRowten = this.kDTable1.addRow();
    	addRowten.getCell(2).setValue("是否影响二级及以上节点");
    	addRowten.getCell(5).setEditor(editor);
    	addRowten.getCell(5).setValue(Boolean.FALSE);
    	addRowten.getCell(6).setValue("是");
    	addRowten.getCell(7).setEditor(editor);
    	addRowten.getCell(7).setValue(Boolean.FALSE);
    	addRowten.getCell(8).setValue("否");
    	mergeManager.mergeBlock(9, 2, 9, 4);
    	mergeManager.mergeBlock(7, 1, 9, 1);

    	
    	//第十一行
    	IRow addRowelev = this.kDTable1.addRow();
    	addRowelev.getCell(0).setValue("部门意见");
    	addRowelev.getCell(1).setValue("营销部");
    	addRowelev.getCell(2).setValue("销    售");
    	addRowelev.getCell(3).setEditor(editor);
    	addRowelev.getCell(3).setValue(Boolean.FALSE);
    	addRowelev.getCell(4).setValue("有利");
    	addRowelev.getCell(5).setEditor(editor);
    	addRowelev.getCell(5).setValue(Boolean.FALSE);
    	addRowelev.getCell(6).setValue("不利");


    	//第十二行
    	IRow addRowtwev= this.kDTable1.addRow();
    	
    	addRowtwev.getCell(2).setValue("销售承诺");
    	addRowtwev.getCell(3).setEditor(editor);
    	addRowtwev.getCell(3).setValue(Boolean.FALSE);
    	addRowtwev.getCell(4).setValue("影响");
    	addRowtwev.getCell(5).setEditor(editor);
    	addRowtwev.getCell(5).setValue(Boolean.FALSE);
    	addRowtwev.getCell(6).setValue("无影响");
    	mergeManager.mergeBlock(10, 1, 11, 1);
    	
    	
    	//第十三行
    	IRow addRowthirt= this.kDTable1.addRow();
    	
    	
    	addRowthirt.getCell(1).setValue("前期配套部");
    	addRowthirt.getCell(2).setValue("报建指标");
    	addRowthirt.getCell(3).setEditor(editor);
    	addRowthirt.getCell(3).setValue(Boolean.FALSE);
    	addRowthirt.getCell(4).setValue("影响");
    	addRowthirt.getCell(5).setEditor(editor);
    	addRowthirt.getCell(5).setValue(Boolean.FALSE);
    	addRowthirt.getCell(6).setValue("不影响");
    	
  	
    	//第十四行
    	IRow addRowfout= this.kDTable1.addRow();
    	addRowfout.getCell(1).setValue("成本部");
    	addRowfout.getCell(2).setValue("成    本");
    	addRowfout.getCell(3).setEditor(editor);
    	addRowfout.getCell(3).setValue(Boolean.FALSE);
    	addRowfout.getCell(4).setValue("增加");
    	addRowfout.getCell(5).setEditor(editor);
    	addRowfout.getCell(5).setValue(Boolean.FALSE);
    	addRowfout.getCell(6).setValue("减少");
    	addRowfout.getCell(7).setEditor(editor);
    	addRowfout.getCell(7).setValue(Boolean.FALSE);
    	addRowfout.getCell(8).setValue("零费用");
    	
    	//第十五行
    	IRow addRowfift= this.kDTable1.addRow();
    	addRowfift.getCell(1).setValue("成本部");
    	addRowfift.getCell(2).setValue("估价");
    	addRowfift.getCell(5).setValue("万元");
    	mergeManager.mergeBlock(14, 3, 14, 4);
    	mergeManager.mergeBlock(13, 1, 14, 1);
    	mergeManager.mergeBlock(5, 0, 14, 0);
    	//单元格设定只输入数字
    	KDFormattedTextField kdtEntrys_pointValue_TextField = new KDFormattedTextField();
		kdtEntrys_pointValue_TextField.setHorizontalAlignment(2);
        kdtEntrys_pointValue_TextField.setDataType(1);
        kdtEntrys_pointValue_TextField.setMinimumValue(new BigDecimal("-1.0E26"));
    	kdtEntrys_pointValue_TextField.setMaximumValue(new BigDecimal("1.0E26"));
    	kdtEntrys_pointValue_TextField.setPrecision(2);
    	KDTDefaultCellEditor kdtEntrys_pointValue_CellEditor = new KDTDefaultCellEditor(kdtEntrys_pointValue_TextField);
    	addRowfift.getCell(3).setEditor(kdtEntrys_pointValue_CellEditor);
    	
    	
    	IRow addRow16= this.kDTable1.addRow();
    	addRow16.getCell(0).setValue("公司第一负责人:");
    	
    	IRow addRow17= this.kDTable1.addRow();
    	IRow addRow18= this.kDTable1.addRow();
    	addRow18.getCell(8).setValue("第一负责人签字:");
    	addRow18.getCell(11).setValue("日期");
    	mergeManager.mergeBlock(15, 1, 16, 12);
    	mergeManager.mergeBlock(17, 1, 17, 7);
    	mergeManager.mergeBlock(17, 8, 17, 9);
    	mergeManager.mergeBlock(15, 0, 17, 0);
    	IRow addRow19= this.kDTable1.addRow();
    	addRow19.getCell(0).setValue("城市公司或地区总部第一负责人");

    	IRow addRow20= this.kDTable1.addRow();
    	IRow addRow21= this.kDTable1.addRow();
    	addRow21.getCell(8).setValue("第一负责人签字:");
    	addRow21.getCell(11).setValue("日期");
    	mergeManager.mergeBlock(18, 1, 19, 12);
    	mergeManager.mergeBlock(20, 1, 20, 7);
    	mergeManager.mergeBlock(20, 8, 20, 9);
    	mergeManager.mergeBlock(18, 0, 20, 0);
    	
    	
    	this.kDTable1.getColumn(0).setWidth(100);
    	this.kDTable1.getColumn(1).setWidth(70);
    	this.kDTable1.getColumn(2).setWidth(60);
    	this.kDTable1.getColumn(3).setWidth(40);
    	this.kDTable1.getColumn(4).setWidth(60);
    	this.kDTable1.getColumn(5).setWidth(40);
    	this.kDTable1.getColumn(6).setWidth(75);
    	this.kDTable1.getColumn(7).setWidth(40);
    	this.kDTable1.getColumn(8).setWidth(70);
    	this.kDTable1.getColumn(9).setWidth(40);
    	this.kDTable1.getColumn(10).setWidth(70);
    	this.kDTable1.getColumn(11).setWidth(72);
    	this.kDTable1.getColumn(12).setWidth(90);
    	this.kDTable1.getIndexColumn().getStyleAttributes().setHided(true);
    	
    	
    	

    	String billId = "FrYcumfRTUq/iL87J1M2VXARYRc=";
    	StringBuffer sb = new StringBuffer();
    	sb.append(" select ChangeAB.FCurProjectName 项目名称1 ,ChangeAB.FNumber 申请编号1 ,ChangeAB.Fname 事项名称1,BaseU.Fname_l2 提出部门 , ");
    	sb.append(" ChangeAB.Freadesc 适用范围1 ,entry.FChangeContent");
    	sb.append(" from T_CON_ChangeAuditBill ChangeAB ");
    	sb.append(" left join T_ORG_BaseUnit BaseU on BaseU.fid=ChangeAB.FConductDeptID");
    	sb.append(" left join T_CON_ChangeAuditEntry entry on entry.FParentID = ChangeAB.fid ");
    	sb.append(" where ChangeAB.fid = '").append(billId).append("'");
    	
    	IRowSet rowset = new FDCSQLBuilder().appendSql(sb.toString()).executeQuery();
    	StringBuffer Yuanyi = new StringBuffer();
    	while(rowset.next()){
    		this.kDTextField1.setText(rowset.getString(1));
    		this.kDTextField2.setText(rowset.getString(2));
    		this.kDTable1.getCell(0, 1).setValue(rowset.getString(3));
    		this.kDTable1.getCell(1, 1).setValue(rowset.getString(5));
    		Yuanyi.append(rowset.getString(6)+"\n");
    	}
    	this.kDTable1.getCell(4, 1).setValue(Yuanyi.toString());
    	this.kDTable1.getCell(4, 1).getStyleAttributes().setWrapText(true);
    	//工作流审批意见
    	Map<String, String> apporveResultForMap = WFResultApporveHelper.getApporveResultForMap(billId);
    	this.kDTable1.getCell(3, 3).setValue(apporveResultForMap.get("提出方经办人"));
    	this.kDTable1.getCell(3, 7).setValue(apporveResultForMap.get("提出方审核人"));
    	this.kDTable1.getCell(3, 11).setValue(apporveResultForMap.get("提出时间;审核时间"));
    	this.kDTable1.getCell(6, 11).setValue(apporveResultForMap.get("设计部负责人;审核人"));
    	this.kDTable1.getCell(6, 12).setValue(apporveResultForMap.get("设计部日期"));
    	this.kDTable1.getCell(7, 11).setValue(apporveResultForMap.get("工程部负责人;审核人"));
    	this.kDTable1.getCell(7, 12).setValue(apporveResultForMap.get("工程部日期"));
    	this.kDTable1.getCell(8, 11).setValue(apporveResultForMap.get("销售部负责人;审核人"));
    	this.kDTable1.getCell(8, 12).setValue(apporveResultForMap.get("销售部日期"));
    	this.kDTable1.getCell(9, 11).setValue(apporveResultForMap.get("前期配套部负责人;审核人"));
    	this.kDTable1.getCell(9, 12).setValue(apporveResultForMap.get("前期配套部日期"));
    	this.kDTable1.getCell(10, 11).setValue(apporveResultForMap.get("合约部负责人;审核人"));
    	this.kDTable1.getCell(10, 12).setValue(apporveResultForMap.get("合约部日期"));
    	this.kDTable1.getCell(11, 1).setValue(apporveResultForMap.get("公司第一负责人;意见"));
    	this.kDTable1.getCell(12, 3).setValue(apporveResultForMap.get("公司第一负责人;签字"));
    	this.kDTable1.getCell(12, 12).setValue(apporveResultForMap.get("公司第一负责人;日期"));
    	this.kDTable1.getCell(13, 1).setValue(apporveResultForMap.get("城市公司或地区总部第一负责人;意见"));
    	this.kDTable1.getCell(14, 3).setValue(apporveResultForMap.get("城市公司或地区总部第一负责人;签字"));
    	this.kDTable1.getCell(14, 12).setValue(apporveResultForMap.get("城市公司或地区总部第一负责人;日期"));
    	
    	
    }
    
    protected void kDTable1_tableClicked(KDTMouseEvent e) throws Exception {
    	super.kDTable1_tableClicked(e);
    	FDCMsgBox.showInfo("行："+e.getRowIndex()+"\n列："+e.getColIndex());
    }

  
 

	protected IObjectValue createNewData() {
		return null;
	}

	protected ICoreBase getBizInterface() throws Exception {
		return null;
	}

}