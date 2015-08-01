/**
 * output package name
 */
package com.kingdee.eas.fdc.costdb.app;

import com.kingdee.bos.Context;
import com.kingdee.eas.framework.batchHandler.RequestContext;
import com.kingdee.eas.framework.batchHandler.ResponseContext;


/**
 * output class name
 */
public abstract class AbstractDynamicCostImportSourceUIHandler extends com.kingdee.eas.framework.app.CoreUIHandler

{
	public void handleActionSave(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionSave(request,response,context);
	}
	abstract protected void _handleActionSave(RequestContext request,ResponseContext response, Context context)
		throws Exception;
	public void handleActionCancel(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionCancel(request,response,context);
	}
	abstract protected void _handleActionCancel(RequestContext request,ResponseContext response, Context context)
		throws Exception;
}