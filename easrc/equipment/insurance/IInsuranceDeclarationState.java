package com.kingdee.eas.port.equipment.insurance;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.entity.SorterItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.xr.IXRBillBase;

public interface IInsuranceDeclarationState extends IXRBillBase
{
    public void addnew(IObjectPK pk, InsuranceDeclarationStateInfo model) throws BOSException, EASBizException;
    public IObjectPK addnew(InsuranceDeclarationStateInfo model) throws BOSException, EASBizException;
    public void audit(IObjectPK pk) throws BOSException, EASBizException;
    public void delete(IObjectPK pk) throws BOSException, EASBizException;
    public void delete(IObjectPK[] arrayPK) throws BOSException, EASBizException;
    public IObjectPK[] delete(FilterInfo filter) throws BOSException, EASBizException;
    public IObjectPK[] delete(String oql) throws BOSException, EASBizException;
    public boolean exists(IObjectPK pk) throws BOSException, EASBizException;
    public boolean exists(FilterInfo filter) throws BOSException, EASBizException;
    public boolean exists(String oql) throws BOSException, EASBizException;
    public InsuranceDeclarationStateCollection getInsuranceDeclarationStateCollection() throws BOSException;
    public InsuranceDeclarationStateCollection getInsuranceDeclarationStateCollection(EntityViewInfo view) throws BOSException;
    public InsuranceDeclarationStateCollection getInsuranceDeclarationStateCollection(String oql) throws BOSException;
    public IObjectPK[] getPKList() throws BOSException, EASBizException;
    public IObjectPK[] getPKList(FilterInfo filter, SorterItemCollection sorter) throws BOSException, EASBizException;
    public IObjectPK[] getPKList(String oql) throws BOSException, EASBizException;
    public InsuranceDeclarationStateInfo getInsuranceDeclarationStateInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public InsuranceDeclarationStateInfo getInsuranceDeclarationStateInfo(IObjectPK pk) throws BOSException, EASBizException;
    public InsuranceDeclarationStateInfo getInsuranceDeclarationStateInfo(String oql) throws BOSException, EASBizException;
    public void unAudit(IObjectPK pk) throws BOSException, EASBizException;
    public void unAudit(IObjectPK[] pks) throws BOSException, EASBizException;
    public void update(IObjectPK pk, InsuranceDeclarationStateInfo model) throws BOSException, EASBizException;
    public void updateBigObject(IObjectPK pk, InsuranceDeclarationStateInfo model) throws BOSException;
    public void updatePartial(InsuranceDeclarationStateInfo model, SelectorItemCollection selector) throws BOSException, EASBizException;
    public String getBindingProperty() throws BOSException;
}