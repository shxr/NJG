package com.kingdee.eas.fdc.contract;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import com.kingdee.bos.util.*;
import com.kingdee.eas.framework.DataBase;
import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.fdc.contract.app.*;
import com.kingdee.eas.framework.IDataBase;
import com.kingdee.eas.framework.CoreBaseCollection;

public class ConChangeTemp extends DataBase implements IConChangeTemp
{
    public ConChangeTemp()
    {
        super();
        registerInterface(IConChangeTemp.class, this);
    }
    public ConChangeTemp(Context ctx)
    {
        super(ctx);
        registerInterface(IConChangeTemp.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("452A8C6B");
    }
    private ConChangeTempController getController() throws BOSException
    {
        return (ConChangeTempController)getBizController();
    }
}