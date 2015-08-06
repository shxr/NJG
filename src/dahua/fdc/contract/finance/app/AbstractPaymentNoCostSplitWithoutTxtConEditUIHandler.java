/**
 * output package name
 */
package com.kingdee.eas.fdc.finance.app;

import com.kingdee.bos.Context;
import com.kingdee.eas.framework.batchHandler.RequestContext;
import com.kingdee.eas.framework.batchHandler.ResponseContext;


/**
 * output class name
 */
public abstract class AbstractPaymentNoCostSplitWithoutTxtConEditUIHandler extends com.kingdee.eas.fdc.basedata.app.FDCNoCostSplitBillEditUIHandler

{
	public void handleActionViewPayment(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionViewPayment(request,response,context);
	}
	abstract protected void _handleActionViewPayment(RequestContext request,ResponseContext response, Context context)
		throws Exception;
	public void handleActionViewContract(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionViewContract(request,response,context);
	}
	abstract protected void _handleActionViewContract(RequestContext request,ResponseContext response, Context context)
		throws Exception;
}