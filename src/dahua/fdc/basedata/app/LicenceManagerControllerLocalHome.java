package com.kingdee.eas.fdc.basedata.app;

import javax.ejb.*;
import java.rmi.RemoteException;

public interface LicenceManagerControllerLocalHome extends EJBLocalHome
{
    LicenceManagerControllerLocal create() throws CreateException;
}