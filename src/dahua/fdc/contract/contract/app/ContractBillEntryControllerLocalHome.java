package com.kingdee.eas.fdc.contract.app;

import javax.ejb.*;
import java.rmi.RemoteException;

public interface ContractBillEntryControllerLocalHome extends EJBLocalHome
{
    ContractBillEntryControllerLocal create() throws CreateException;
}