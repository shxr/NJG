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
import com.kingdee.eas.fdc.basedata.IFDCBill;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;

public interface IContractBillRevise extends IFDCBill
{
    public ContractBillReviseCollection getContractBillReviseCollection() throws BOSException;
    public ContractBillReviseCollection getContractBillReviseCollection(EntityViewInfo view) throws BOSException;
    public ContractBillReviseCollection getContractBillReviseCollection(String oql) throws BOSException;
    public ContractBillReviseInfo getContractBillReviseInfo(IObjectPK pk) throws BOSException, EASBizException;
    public ContractBillReviseInfo getContractBillReviseInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public ContractBillReviseInfo getContractBillReviseInfo(String oql) throws BOSException, EASBizException;
    public boolean contractBillStore(ContractBillReviseInfo cbInfo, String storeNumber) throws BOSException, EASBizException;
}