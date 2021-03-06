/**
 * output package name
 */
package com.kingdee.eas.fdc.aimcost.report.productdynamic.client;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.ctrl.kdf.table.IColumn;
import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.KDTDefaultCellEditor;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.kdf.table.event.KDTDataRequestEvent;
import com.kingdee.bos.ctrl.swing.KDFormattedTextField;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.eas.base.permission.client.longtime.ILongTimeTask;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.fdc.aimcost.report.productdynamic.ProductDynamicReportFacadeFactory;
import com.kingdee.eas.framework.*;
import com.kingdee.eas.framework.report.ICommRptBase;
import com.kingdee.eas.framework.report.client.CommRptBaseConditionUI;
import com.kingdee.eas.framework.report.util.KDTableUtil;
import com.kingdee.eas.framework.report.util.RptParams;
import com.kingdee.eas.framework.report.util.RptTableHeader;
import com.kingdee.eas.ma.budget.client.LongTimeDialog;

/**
 * output class name
 */
public class ProductDynamicReportUI extends AbstractProductDynamicReportUI
{
    private static final Logger logger = CoreUIObject.getLogger(ProductDynamicReportUI.class);
    private boolean isQuery = false;
    /**
     * output class constructor
     */
    public ProductDynamicReportUI() throws Exception
    {
        super();
    }

    /**
     * output storeFields method
     */
    public void storeFields()
    {
        super.storeFields();
    }

    /**
     * output actionPageSetup_actionPerformed
     */
    public void actionPageSetup_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionPageSetup_actionPerformed(e);
    }

    /**
     * output actionExitCurrent_actionPerformed
     */
    public void actionExitCurrent_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionExitCurrent_actionPerformed(e);
    }

    /**
     * output actionHelp_actionPerformed
     */
    public void actionHelp_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionHelp_actionPerformed(e);
    }

    /**
     * output actionAbout_actionPerformed
     */
    public void actionAbout_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionAbout_actionPerformed(e);
    }

    /**
     * output actionOnLoad_actionPerformed
     */
    public void actionOnLoad_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionOnLoad_actionPerformed(e);
    }

    /**
     * output actionSendMessage_actionPerformed
     */
    public void actionSendMessage_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionSendMessage_actionPerformed(e);
    }

    /**
     * output actionCalculator_actionPerformed
     */
    public void actionCalculator_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionCalculator_actionPerformed(e);
    }

    /**
     * output actionExport_actionPerformed
     */
    public void actionExport_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionExport_actionPerformed(e);
    }

    /**
     * output actionExportSelected_actionPerformed
     */
    public void actionExportSelected_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionExportSelected_actionPerformed(e);
    }

    /**
     * output actionRegProduct_actionPerformed
     */
    public void actionRegProduct_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionRegProduct_actionPerformed(e);
    }

    /**
     * output actionPersonalSite_actionPerformed
     */
    public void actionPersonalSite_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionPersonalSite_actionPerformed(e);
    }

    /**
     * output actionProcductVal_actionPerformed
     */
    public void actionProcductVal_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionProcductVal_actionPerformed(e);
    }

    /**
     * output actionExportSave_actionPerformed
     */
    public void actionExportSave_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionExportSave_actionPerformed(e);
    }

    /**
     * output actionExportSelectedSave_actionPerformed
     */
    public void actionExportSelectedSave_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionExportSelectedSave_actionPerformed(e);
    }

    /**
     * output actionKnowStore_actionPerformed
     */
    public void actionKnowStore_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionKnowStore_actionPerformed(e);
    }

    /**
     * output actionAnswer_actionPerformed
     */
    public void actionAnswer_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionAnswer_actionPerformed(e);
    }

    /**
     * output actionRemoteAssist_actionPerformed
     */
    public void actionRemoteAssist_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionRemoteAssist_actionPerformed(e);
    }

    /**
     * output actionPopupCopy_actionPerformed
     */
    public void actionPopupCopy_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionPopupCopy_actionPerformed(e);
    }

    /**
     * output actionHTMLForMail_actionPerformed
     */
    public void actionHTMLForMail_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionHTMLForMail_actionPerformed(e);
    }

    /**
     * output actionExcelForMail_actionPerformed
     */
    public void actionExcelForMail_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionExcelForMail_actionPerformed(e);
    }

    /**
     * output actionHTMLForRpt_actionPerformed
     */
    public void actionHTMLForRpt_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionHTMLForRpt_actionPerformed(e);
    }

    /**
     * output actionExcelForRpt_actionPerformed
     */
    public void actionExcelForRpt_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionExcelForRpt_actionPerformed(e);
    }

    /**
     * output actionLinkForRpt_actionPerformed
     */
    public void actionLinkForRpt_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionLinkForRpt_actionPerformed(e);
    }

    /**
     * output actionPopupPaste_actionPerformed
     */
    public void actionPopupPaste_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionPopupPaste_actionPerformed(e);
    }

    /**
     * output actionToolBarCustom_actionPerformed
     */
    public void actionToolBarCustom_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionToolBarCustom_actionPerformed(e);
    }

    /**
     * output actionCloudFeed_actionPerformed
     */
    public void actionCloudFeed_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionCloudFeed_actionPerformed(e);
    }

    /**
     * output actionCloudShare_actionPerformed
     */
    public void actionCloudShare_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionCloudShare_actionPerformed(e);
    }

    /**
     * output actionCloudScreen_actionPerformed
     */
    public void actionCloudScreen_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionCloudScreen_actionPerformed(e);
    }

    /**
     * output actionRefresh_actionPerformed
     */
    public void actionRefresh_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionRefresh_actionPerformed(e);
    }

    /**
     * output actionQuery_actionPerformed
     */
    public void actionQuery_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionQuery_actionPerformed(e);
    }

    /**
     * output actionPrint_actionPerformed
     */
    public void actionPrint_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionPrint_actionPerformed(e);
    }

    /**
     * output actionPrintPreview_actionPerformed
     */
    public void actionPrintPreview_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionPrintPreview_actionPerformed(e);
    }

    /**
     * output actionChart_actionPerformed
     */
    public void actionChart_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionChart_actionPerformed(e);
    }

	@Override
	protected RptParams getParamsForInit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected CommRptBaseConditionUI getQueryDialogUserPanel() throws Exception {
		// TODO Auto-generated method stub
		return new ProductDynamicReportFilterUI();
	}

	@Override
	protected ICommRptBase getRemoteInstance() throws BOSException {
		// TODO Auto-generated method stub
		return ProductDynamicReportFacadeFactory.getRemoteInstance();
	}

	@Override
	protected KDTable getTableForPrintSetting() {
		// TODO Auto-generated method stub
		return new KDTable();
	}

	@Override
	protected void query() {
		this.kDTabbedPane1.removeAll();
		try {
			RptParams resultRpt = getRemoteInstance().query(params);
			Set productSet = (Set) resultRpt.getObject("product");
			Map costMap = (Map) resultRpt.getObject("cost");
			Map productRateMap = (Map) resultRpt.getObject("productRate");
			
			System.out.println("");
			
			for(Iterator it = productSet.iterator(); it.hasNext();) {
				KDTable table = initTable();
				String productKey = it.next().toString();
				this.kDTabbedPane1.addTab(productKey, table);
				
				Set keySet = costMap.keySet();
				for(Iterator keyIt = keySet.iterator();keyIt.hasNext();) {
					IRow addRow = table.addRow();
					String key = keyIt.next().toString();
					Map detailMap = (Map) costMap.get(key);
					addRow.getCell("costAccount").setValue(detailMap.get("costAccount"));
					addRow.getCell("costAccountNumber").setValue(detailMap.get("costAccountNumber"));
					
					BigDecimal rate = (BigDecimal) productRateMap.get(productKey);
//					BigDecimal rate = new BigDecimal("1");
					BigDecimal aimCost = (BigDecimal) detailMap.get("aimCost");
					BigDecimal dynamicCost = (BigDecimal) detailMap.get("dynamicCost");
					addRow.getCell("aimCost").setValue(aimCost.multiply(rate));
					addRow.getCell("dynamicCost").setValue(dynamicCost.multiply(rate));
				}
			}
		} catch (EASBizException e) {
			e.printStackTrace();
		} catch (BOSException e) {
			e.printStackTrace();
		}
	}

	private KDTable initTable() {
		KDTable table = new KDTable();
		IRow headRow = table.addHeadRow();
		IColumn addColumn = table.addColumn();
		addColumn.setKey("costAccount");
		headRow.getCell("costAccount").setValue("成本科目");
		
		addColumn = table.addColumn();
		addColumn.setKey("costAccountNumber");
		headRow.getCell("costAccountNumber").setValue("成本科目编码");
		
		addColumn = table.addColumn();
		addColumn.setKey("aimCost");
		headRow.getCell("aimCost").setValue("目标成本");
		
		addColumn = table.addColumn();
		addColumn.setKey("dynamicCost");
		headRow.getCell("dynamicCost").setValue("动态成本");
		
		table.getColumn("aimCost").getStyleAttributes().setNumberFormat("#.###");
		table.getColumn("dynamicCost").getStyleAttributes().setNumberFormat("#.###");
		table.getStyleAttributes().setLocked(true);
		enableExportExcel(table);
		return table;
	}
	@Override
	public void tableDataRequest(KDTDataRequestEvent arg0) {
		if(isQuery)
			return;
		isQuery = true;
		Window win = SwingUtilities.getWindowAncestor(this);
		LongTimeDialog dialog = null;
		if (win instanceof Frame) {
			dialog = new LongTimeDialog((Frame) win);
		} else if (win instanceof Dialog) {
			dialog = new LongTimeDialog((Dialog) win);
		}
		if (dialog == null) {
			dialog = new LongTimeDialog(new Frame());
		}
		dialog.setLongTimeTask(new ILongTimeTask() {
			public Object exec() throws Exception {
				RptParams resultRpt = getRemoteInstance().query(params);
				return resultRpt;
			}
			public void afterExec(Object result) throws Exception {
				RptParams rpt = getRemoteInstance().createTempTable(params);
//				RptTableHeader header = (RptTableHeader) rpt.getObject("header");
				Set productSet = (Set) params.getObject("product");
				Map costMap = (Map) params.getObject("cost");
				Map productRateMap = (Map) params.getObject("productRate");
				System.out.println("");
			}
		});
		dialog.show();
		isQuery = false;
	}
}