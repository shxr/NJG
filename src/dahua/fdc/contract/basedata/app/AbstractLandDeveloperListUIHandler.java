/**
 * output package name
 */
package com.kingdee.eas.fdc.basedata.app;

import com.kingdee.bos.Context;
import com.kingdee.eas.framework.batchHandler.RequestContext;
import com.kingdee.eas.framework.batchHandler.ResponseContext;


/**
 * output class name
 */
public abstract class AbstractLandDeveloperListUIHandler extends com.kingdee.eas.basedata.framework.app.DataBaseSIListUIHandler

{
	public void handleActionEnabled(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionEnabled(request,response,context);
	}
	protected void _handleActionEnabled(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionDisEnabled(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionDisEnabled(request,response,context);
	}
	protected void _handleActionDisEnabled(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
}