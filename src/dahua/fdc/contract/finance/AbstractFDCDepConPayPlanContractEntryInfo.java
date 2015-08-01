package com.kingdee.eas.fdc.finance;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractFDCDepConPayPlanContractEntryInfo extends com.kingdee.eas.framework.CoreBillEntryBaseInfo implements Serializable 
{
    public AbstractFDCDepConPayPlanContractEntryInfo()
    {
        this("id");
    }
    protected AbstractFDCDepConPayPlanContractEntryInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: 已签定合同分录中月度支付 's 父结点 property 
     */
    public com.kingdee.eas.fdc.finance.FDCDepConPayPlanContractInfo getParentC()
    {
        return (com.kingdee.eas.fdc.finance.FDCDepConPayPlanContractInfo)get("parentC");
    }
    public void setParentC(com.kingdee.eas.fdc.finance.FDCDepConPayPlanContractInfo item)
    {
        put("parentC", item);
    }
    /**
     * Object:已签定合同分录中月度支付's 计划支付property 
     */
    public java.math.BigDecimal getPlanPay()
    {
        return getBigDecimal("planPay");
    }
    public void setPlanPay(java.math.BigDecimal item)
    {
        setBigDecimal("planPay", item);
    }
    /**
     * Object: 已签定合同分录中月度支付 's 款项类型 property 
     */
    public com.kingdee.eas.fdc.basedata.PaymentTypeInfo getMoneyDefine()
    {
        return (com.kingdee.eas.fdc.basedata.PaymentTypeInfo)get("moneyDefine");
    }
    public void setMoneyDefine(com.kingdee.eas.fdc.basedata.PaymentTypeInfo item)
    {
        put("moneyDefine", item);
    }
    /**
     * Object:已签定合同分录中月度支付's 用款说明property 
     */
    public String getExplain()
    {
        return getString("explain");
    }
    public void setExplain(String item)
    {
        setString("explain", item);
    }
    /**
     * Object:已签定合同分录中月度支付's 最终批复金额property 
     */
    public java.math.BigDecimal getOfficialPay()
    {
        return getBigDecimal("officialPay");
    }
    public void setOfficialPay(java.math.BigDecimal item)
    {
        setBigDecimal("officialPay", item);
    }
    /**
     * Object:已签定合同分录中月度支付's 计划月份property 
     */
    public java.util.Date getMonth()
    {
        return getDate("month");
    }
    public void setMonth(java.util.Date item)
    {
        setDate("month", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("12816238");
    }
}