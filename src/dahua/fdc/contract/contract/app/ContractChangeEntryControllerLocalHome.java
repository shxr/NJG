package com.kingdee.eas.fdc.contract.app;

import javax.ejb.*;
import java.rmi.RemoteException;

public interface ContractChangeEntryControllerLocalHome extends EJBLocalHome
{
    ContractChangeEntryControllerLocal create() throws CreateException;
}