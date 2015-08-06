package com.kingdee.eas.fdc.contract;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractPaymentLayoutPayByStageInfo extends com.kingdee.eas.framework.BillEntryBaseInfo implements Serializable 
{
    public AbstractPaymentLayoutPayByStageInfo()
    {
        this("id");
    }
    protected AbstractPaymentLayoutPayByStageInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: ���� 's null property 
     */
    public com.kingdee.eas.fdc.contract.PaymentLayoutInfo getParent()
    {
        return (com.kingdee.eas.fdc.contract.PaymentLayoutInfo)get("parent");
    }
    public void setParent(com.kingdee.eas.fdc.contract.PaymentLayoutInfo item)
    {
        put("parent", item);
    }
    /**
     * Object:����'s �ƻ���������property 
     */
    public java.util.Date getPayDate()
    {
        return getDate("payDate");
    }
    public void setPayDate(java.util.Date item)
    {
        setDate("payDate", item);
    }
    /**
     * Object:����'s �ƻ������·�property 
     */
    public java.util.Date getMonth()
    {
        return getDate("month");
    }
    public void setMonth(java.util.Date item)
    {
        setDate("month", item);
    }
    /**
     * Object:����'s �ƻ��������property 
     */
    public java.math.BigDecimal getPayBili()
    {
        return getBigDecimal("payBili");
    }
    public void setPayBili(java.math.BigDecimal item)
    {
        setBigDecimal("payBili", item);
    }
    /**
     * Object:����'s �ƻ�������property 
     */
    public java.math.BigDecimal getPayMoney()
    {
        return getBigDecimal("payMoney");
    }
    public void setPayMoney(java.math.BigDecimal item)
    {
        setBigDecimal("payMoney", item);
    }
    /**
     * Object:����'s ��עproperty 
     */
    public String getRemark()
    {
        return getString("remark");
    }
    public void setRemark(String item)
    {
        setString("remark", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("7B247F74");
    }
}