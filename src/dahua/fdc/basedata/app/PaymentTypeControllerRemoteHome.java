package com.kingdee.eas.fdc.basedata.app;

import javax.ejb.*;
import java.rmi.RemoteException;

public interface PaymentTypeControllerRemoteHome extends EJBHome
{
    PaymentTypeControllerRemote create() throws CreateException, RemoteException;
}