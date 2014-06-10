package com.kingdee.eas.port.pm.invest.investplan;

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
import com.kingdee.eas.framework.ITreeBase;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;

public interface IProgrammingEntry extends ITreeBase
{
    public ProgrammingEntryInfo getProgrammingEntryInfo(IObjectPK pk) throws BOSException, EASBizException;
    public ProgrammingEntryInfo getProgrammingEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public ProgrammingEntryInfo getProgrammingEntryInfo(String oql) throws BOSException, EASBizException;
    public ProgrammingEntryCollection getProgrammingEntryCollection() throws BOSException;
    public ProgrammingEntryCollection getProgrammingEntryCollection(EntityViewInfo view) throws BOSException;
    public ProgrammingEntryCollection getProgrammingEntryCollection(String oql) throws BOSException;
}