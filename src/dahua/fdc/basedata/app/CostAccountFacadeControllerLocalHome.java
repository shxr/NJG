package com.kingdee.eas.fdc.basedata.app;

import javax.ejb.*;
import java.rmi.RemoteException;

public interface CostAccountFacadeControllerLocalHome extends EJBLocalHome
{
    CostAccountFacadeControllerLocal create() throws CreateException;
}