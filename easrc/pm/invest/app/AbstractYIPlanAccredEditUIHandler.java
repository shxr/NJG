/**
 * output package name
 */
package com.kingdee.eas.port.pm.invest.app;

import com.kingdee.bos.Context;
import com.kingdee.eas.framework.batchHandler.RequestContext;
import com.kingdee.eas.framework.batchHandler.ResponseContext;


/**
 * output class name
 */
public abstract class AbstractYIPlanAccredEditUIHandler extends com.kingdee.eas.xr.app.XRBillBaseEditUIHandler

{
	public void handleActionEditProject(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionEditProject(request,response,context);
	}
	protected void _handleActionEditProject(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
}