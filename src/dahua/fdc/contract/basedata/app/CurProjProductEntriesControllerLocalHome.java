package com.kingdee.eas.fdc.basedata.app;

import javax.ejb.*;
import java.rmi.RemoteException;

public interface CurProjProductEntriesControllerLocalHome extends EJBLocalHome
{
    CurProjProductEntriesControllerLocal create() throws CreateException;
}