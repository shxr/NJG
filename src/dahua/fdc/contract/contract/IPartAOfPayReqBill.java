package com.kingdee.eas.fdc.contract;

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
import com.kingdee.eas.framework.ICoreBase;

public interface IPartAOfPayReqBill extends ICoreBase
{
    public PartAOfPayReqBillInfo getPartAOfPayReqBillInfo(IObjectPK pk) throws BOSException, EASBizException;
    public PartAOfPayReqBillInfo getPartAOfPayReqBillInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public PartAOfPayReqBillInfo getPartAOfPayReqBillInfo(String oql) throws BOSException, EASBizException;
    public PartAOfPayReqBillCollection getPartAOfPayReqBillCollection() throws BOSException;
    public PartAOfPayReqBillCollection getPartAOfPayReqBillCollection(EntityViewInfo view) throws BOSException;
    public PartAOfPayReqBillCollection getPartAOfPayReqBillCollection(String oql) throws BOSException;
}