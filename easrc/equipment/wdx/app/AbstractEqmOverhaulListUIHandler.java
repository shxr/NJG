/**
 * output package name
 */
package com.kingdee.eas.port.equipment.wdx.app;

import com.kingdee.bos.Context;
import com.kingdee.eas.framework.batchHandler.RequestContext;
import com.kingdee.eas.framework.batchHandler.ResponseContext;


/**
 * output class name
 */
public abstract class AbstractEqmOverhaulListUIHandler extends com.kingdee.eas.xr.app.XRBillBaseListUIHandler

{
	public void handleActionFinish(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionFinish(request,response,context);
	}
	protected void _handleActionFinish(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
}