package com.kingdee.eas.fdc.basedata.app;

import javax.ejb.*;
import java.rmi.RemoteException;

public interface CounterclaimTypeControllerRemoteHome extends EJBHome
{
    CounterclaimTypeControllerRemote create() throws CreateException, RemoteException;
}