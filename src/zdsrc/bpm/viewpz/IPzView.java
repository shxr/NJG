package com.kingdee.eas.bpm.viewpz;

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
import com.kingdee.eas.framework.IDataBase;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;

public interface IPzView extends IDataBase
{
    public PzViewInfo getPzViewInfo(IObjectPK pk) throws BOSException, EASBizException;
    public PzViewInfo getPzViewInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public PzViewInfo getPzViewInfo(String oql) throws BOSException, EASBizException;
    public PzViewCollection getPzViewCollection() throws BOSException;
    public PzViewCollection getPzViewCollection(EntityViewInfo view) throws BOSException;
    public PzViewCollection getPzViewCollection(String oql) throws BOSException;
}