package com.kingdee.eas.fdc.contract.app;

import javax.ejb.*;
import java.rmi.RemoteException;

public interface ContractBaseDataControllerRemoteHome extends EJBHome
{
    ContractBaseDataControllerRemote create() throws CreateException, RemoteException;
}