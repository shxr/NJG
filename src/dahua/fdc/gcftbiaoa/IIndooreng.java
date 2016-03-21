package com.kingdee.eas.fdc.gcftbiaoa;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.bos.util.*;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.ICoreBillBase;
import com.kingdee.util.NumericException;

public interface IIndooreng extends ICoreBillBase
{
    public IndoorengCollection getIndoorengCollection() throws BOSException;
    public IndoorengCollection getIndoorengCollection(EntityViewInfo view) throws BOSException;
    public IndoorengCollection getIndoorengCollection(String oql) throws BOSException;
    public IndoorengInfo getIndoorengInfo(IObjectPK pk) throws BOSException, EASBizException;
    public IndoorengInfo getIndoorengInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public IndoorengInfo getIndoorengInfo(String oql) throws BOSException, EASBizException;
    public void actionAudit(IndoorengInfo model) throws BOSException, NumericException;
    public void actionUnAudit(IndoorengInfo model) throws BOSException, EASBizException;
}