package com.kingdee.eas.fdc.contract.app;

import javax.ejb.*;
import java.rmi.RemoteException;

public interface SupplierContentEntryControllerRemoteHome extends EJBHome
{
    SupplierContentEntryControllerRemote create() throws CreateException, RemoteException;
}