package com.kingdee.eas.fdc.basedata.app;

import javax.ejb.*;
import java.rmi.RemoteException;

public interface ChangeReasonControllerLocalHome extends EJBLocalHome
{
    ChangeReasonControllerLocal create() throws CreateException;
}