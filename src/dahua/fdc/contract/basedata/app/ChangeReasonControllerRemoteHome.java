package com.kingdee.eas.fdc.basedata.app;

import javax.ejb.*;
import java.rmi.RemoteException;

public interface ChangeReasonControllerRemoteHome extends EJBHome
{
    ChangeReasonControllerRemote create() throws CreateException, RemoteException;
}