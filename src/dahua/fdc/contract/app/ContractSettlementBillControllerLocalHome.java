package com.kingdee.eas.fdc.contract.app;

import javax.ejb.*;
import java.rmi.RemoteException;

public interface ContractSettlementBillControllerLocalHome extends EJBLocalHome
{
    ContractSettlementBillControllerLocal create() throws CreateException;
}