package com.kingdee.eas.fdc.aimcost;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractTemplateMeasureEntryInfo extends com.kingdee.eas.framework.DataBaseInfo implements Serializable 
{
    public AbstractTemplateMeasureEntryInfo()
    {
        this("id");
    }
    protected AbstractTemplateMeasureEntryInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: 测算分录 's 父级成本科目 property 
     */
    public com.kingdee.eas.fdc.basedata.CostAccountInfo getCostAccount()
    {
        return (com.kingdee.eas.fdc.basedata.CostAccountInfo)get("costAccount");
    }
    public void setCostAccount(com.kingdee.eas.fdc.basedata.CostAccountInfo item)
    {
        put("costAccount", item);
    }
    /**
     * Object:测算分录's 条目名称property 
     */
    public String getEntryName()
    {
        return getString("entryName");
    }
    public void setEntryName(String item)
    {
        setString("entryName", item);
    }
    /**
     * Object:测算分录's 工作量property 
     */
    public java.math.BigDecimal getWorkload()
    {
        return getBigDecimal("workload");
    }
    public void setWorkload(java.math.BigDecimal item)
    {
        setBigDecimal("workload", item);
    }
    /**
     * Object:测算分录's 系数名称property 
     */
    public String getCoefficientName()
    {
        return getString("coefficientName");
    }
    public void setCoefficientName(String item)
    {
        setString("coefficientName", item);
    }
    /**
     * Object:测算分录's 系数property 
     */
    public java.math.BigDecimal getCoefficient()
    {
        return getBigDecimal("coefficient");
    }
    public void setCoefficient(java.math.BigDecimal item)
    {
        setBigDecimal("coefficient", item);
    }
    /**
     * Object:测算分录's 单价property 
     */
    public java.math.BigDecimal getPrice()
    {
        return getBigDecimal("price");
    }
    public void setPrice(java.math.BigDecimal item)
    {
        setBigDecimal("price", item);
    }
    /**
     * Object:测算分录's 总价property 
     */
    public java.math.BigDecimal getCostAmount()
    {
        return getBigDecimal("costAmount");
    }
    public void setCostAmount(java.math.BigDecimal item)
    {
        setBigDecimal("costAmount", item);
    }
    /**
     * Object: 测算分录 's 所属产品 property 
     */
    public com.kingdee.eas.fdc.basedata.ProductTypeInfo getProduct()
    {
        return (com.kingdee.eas.fdc.basedata.ProductTypeInfo)get("product");
    }
    public void setProduct(com.kingdee.eas.fdc.basedata.ProductTypeInfo item)
    {
        put("product", item);
    }
    /**
     * Object: 测算分录 's 头 property 
     */
    public com.kingdee.eas.fdc.aimcost.TemplateMeasureCostInfo getHead()
    {
        return (com.kingdee.eas.fdc.aimcost.TemplateMeasureCostInfo)get("head");
    }
    public void setHead(com.kingdee.eas.fdc.aimcost.TemplateMeasureCostInfo item)
    {
        put("head", item);
    }
    /**
     * Object:测算分录's 合价property 
     */
    public java.math.BigDecimal getAmount()
    {
        return getBigDecimal("amount");
    }
    public void setAmount(java.math.BigDecimal item)
    {
        setBigDecimal("amount", item);
    }
    /**
     * Object:测算分录's 调整金额property 
     */
    public java.math.BigDecimal getAdjustAmt()
    {
        return getBigDecimal("adjustAmt");
    }
    public void setAdjustAmt(java.math.BigDecimal item)
    {
        setBigDecimal("adjustAmt", item);
    }
    /**
     * Object:测算分录's 调整系数property 
     */
    public java.math.BigDecimal getAdjustCoefficient()
    {
        return getBigDecimal("adjustCoefficient");
    }
    public void setAdjustCoefficient(java.math.BigDecimal item)
    {
        setBigDecimal("adjustCoefficient", item);
    }
    /**
     * Object: 测算分录 's 单位 property 
     */
    public com.kingdee.eas.basedata.assistant.MeasureUnitInfo getUnit()
    {
        return (com.kingdee.eas.basedata.assistant.MeasureUnitInfo)get("unit");
    }
    public void setUnit(com.kingdee.eas.basedata.assistant.MeasureUnitInfo item)
    {
        put("unit", item);
    }
    /**
     * Object:测算分录's 合约规划property 
     */
    public String getProgram()
    {
        return getString("program");
    }
    public void setProgram(String item)
    {
        setString("program", item);
    }
    /**
     * Object:测算分录's 备注property 
     */
    public String getDesc()
    {
        return getString("desc");
    }
    public void setDesc(String item)
    {
        setString("desc", item);
    }
    /**
     * Object:测算分录's 变化原因property 
     */
    public String getChangeReason()
    {
        return getString("changeReason");
    }
    public void setChangeReason(String item)
    {
        setString("changeReason", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("5E698F25");
    }
}