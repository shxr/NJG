package com.kingdee.eas.port.markesupplier.subase;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.bos.util.*;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.framework.ITreeBase;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;

public interface ISupplierInvoiceTypeTree extends ITreeBase
{
    public SupplierInvoiceTypeTreeInfo getSupplierInvoiceTypeTreeInfo(IObjectPK pk) throws BOSException, EASBizException;
    public SupplierInvoiceTypeTreeInfo getSupplierInvoiceTypeTreeInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public SupplierInvoiceTypeTreeInfo getSupplierInvoiceTypeTreeInfo(String oql) throws BOSException, EASBizException;
    public SupplierInvoiceTypeTreeCollection getSupplierInvoiceTypeTreeCollection() throws BOSException;
    public SupplierInvoiceTypeTreeCollection getSupplierInvoiceTypeTreeCollection(EntityViewInfo view) throws BOSException;
    public SupplierInvoiceTypeTreeCollection getSupplierInvoiceTypeTreeCollection(String oql) throws BOSException;
}