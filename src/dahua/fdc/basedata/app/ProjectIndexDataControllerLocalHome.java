package com.kingdee.eas.fdc.basedata.app;

import javax.ejb.*;
import java.rmi.RemoteException;

public interface ProjectIndexDataControllerLocalHome extends EJBLocalHome
{
    ProjectIndexDataControllerLocal create() throws CreateException;
}