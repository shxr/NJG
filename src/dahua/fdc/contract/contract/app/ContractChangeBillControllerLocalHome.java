package com.kingdee.eas.fdc.contract.app;

import javax.ejb.*;
import java.rmi.RemoteException;

public interface ContractChangeBillControllerLocalHome extends EJBLocalHome
{
    ContractChangeBillControllerLocal create() throws CreateException;
}