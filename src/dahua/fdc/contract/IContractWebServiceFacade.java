package com.kingdee.eas.fdc.contract;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import com.kingdee.bos.util.*;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.framework.*;

public interface IContractWebServiceFacade extends IBizCtrl
{
    public void setContract(ContractBillInfo contractBill) throws BOSException, EASBizException;
    public boolean validate(ContractBillInfo contractBill) throws BOSException, EASBizException;
}