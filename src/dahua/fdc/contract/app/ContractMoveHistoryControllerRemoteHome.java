package com.kingdee.eas.fdc.contract.app;

import javax.ejb.*;
import java.rmi.RemoteException;

public interface ContractMoveHistoryControllerRemoteHome extends EJBHome
{
    ContractMoveHistoryControllerRemote create() throws CreateException, RemoteException;
}