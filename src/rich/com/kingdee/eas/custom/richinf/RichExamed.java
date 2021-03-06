package com.kingdee.eas.custom.richinf;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import java.lang.String;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBillBase;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.framework.ICoreBillBase;
import com.kingdee.eas.custom.richinf.app.*;

public class RichExamed extends CoreBillBase implements IRichExamed
{
    public RichExamed()
    {
        super();
        registerInterface(IRichExamed.class, this);
    }
    public RichExamed(Context ctx)
    {
        super(ctx);
        registerInterface(IRichExamed.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("7E855488");
    }
    private RichExamedController getController() throws BOSException
    {
        return (RichExamedController)getBizController();
    }
    /**
     *取集合-System defined method
     *@return
     */
    public RichExamedCollection getRichExamedCollection() throws BOSException
    {
        try {
            return getController().getRichExamedCollection(getContext());
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@param view 取集合
     *@return
     */
    public RichExamedCollection getRichExamedCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getRichExamedCollection(getContext(), view);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@param oql 取集合
     *@return
     */
    public RichExamedCollection getRichExamedCollection(String oql) throws BOSException
    {
        try {
            return getController().getRichExamedCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public RichExamedInfo getRichExamedInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getRichExamedInfo(getContext(), pk);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@param selector 取值
     *@return
     */
    public RichExamedInfo getRichExamedInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getRichExamedInfo(getContext(), pk, selector);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取值-System defined method
     *@param oql 取值
     *@return
     */
    public RichExamedInfo getRichExamedInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getRichExamedInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}