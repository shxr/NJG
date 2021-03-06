package com.kingdee.eas.port.equipment.insurance;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractInsuranceCoverageInfo extends com.kingdee.eas.xr.XRBillBaseInfo implements Serializable 
{
    public AbstractInsuranceCoverageInfo()
    {
        this("id");
    }
    protected AbstractInsuranceCoverageInfo(String pkField)
    {
        super(pkField);
        put("E1", new com.kingdee.eas.port.equipment.insurance.InsuranceCoverageE1Collection());
    }
    /**
     * Object: 保险投保明细表 's 险种 property 
     */
    public com.kingdee.eas.port.equipment.base.InsuranceInfo getInsurance()
    {
        return (com.kingdee.eas.port.equipment.base.InsuranceInfo)get("insurance");
    }
    public void setInsurance(com.kingdee.eas.port.equipment.base.InsuranceInfo item)
    {
        put("insurance", item);
    }
    /**
     * Object: 保险投保明细表 's 保险公司 property 
     */
    public com.kingdee.eas.port.equipment.base.InsuranceCompanyInfo getInsuranceCompany()
    {
        return (com.kingdee.eas.port.equipment.base.InsuranceCompanyInfo)get("insuranceCompany");
    }
    public void setInsuranceCompany(com.kingdee.eas.port.equipment.base.InsuranceCompanyInfo item)
    {
        put("insuranceCompany", item);
    }
    /**
     * Object: 保险投保明细表 's 第1个表体 property 
     */
    public com.kingdee.eas.port.equipment.insurance.InsuranceCoverageE1Collection getE1()
    {
        return (com.kingdee.eas.port.equipment.insurance.InsuranceCoverageE1Collection)get("E1");
    }
    /**
     * Object:保险投保明细表's 险种IDproperty 
     */
    public String getXianzhongID()
    {
        return getString("xianzhongID");
    }
    public void setXianzhongID(String item)
    {
        setString("xianzhongID", item);
    }
    /**
     * Object:保险投保明细表's 保单号码property 
     */
    public String getCoverNumber()
    {
        return getString("coverNumber");
    }
    public void setCoverNumber(String item)
    {
        setString("coverNumber", item);
    }
    /**
     * Object:保险投保明细表's 合同号码property 
     */
    public String getContNumber()
    {
        return getString("contNumber");
    }
    public void setContNumber(String item)
    {
        setString("contNumber", item);
    }
    /**
     * Object:保险投保明细表's 起保日期property 
     */
    public java.util.Date getEffectDate()
    {
        return getDate("effectDate");
    }
    public void setEffectDate(java.util.Date item)
    {
        setDate("effectDate", item);
    }
    /**
     * Object:保险投保明细表's 终保日期property 
     */
    public java.util.Date getEndDate()
    {
        return getDate("endDate");
    }
    public void setEndDate(java.util.Date item)
    {
        setDate("endDate", item);
    }
    /**
     * Object:保险投保明细表's 年度property 
     */
    public String getYear()
    {
        return getString("year");
    }
    public void setYear(String item)
    {
        setString("year", item);
    }
    /**
     * Object: 保险投保明细表 's 投保人名称 property 
     */
    public com.kingdee.eas.basedata.org.CompanyOrgUnitInfo getTbrmc()
    {
        return (com.kingdee.eas.basedata.org.CompanyOrgUnitInfo)get("tbrmc");
    }
    public void setTbrmc(com.kingdee.eas.basedata.org.CompanyOrgUnitInfo item)
    {
        put("tbrmc", item);
    }
    /**
     * Object:保险投保明细表's 险种代码property 
     */
    public String getXzdm()
    {
        return getString("xzdm");
    }
    public void setXzdm(String item)
    {
        setString("xzdm", item);
    }
    /**
     * Object:保险投保明细表's 保单号码2property 
     */
    public String getBaodanNo()
    {
        return getString("baodanNo");
    }
    public void setBaodanNo(String item)
    {
        setString("baodanNo", item);
    }
    /**
     * Object: 保险投保明细表 's 险种2 property 
     */
    public com.kingdee.eas.port.equipment.base.InsuranceInfo getXianzhongTwo()
    {
        return (com.kingdee.eas.port.equipment.base.InsuranceInfo)get("xianzhongTwo");
    }
    public void setXianzhongTwo(com.kingdee.eas.port.equipment.base.InsuranceInfo item)
    {
        put("xianzhongTwo", item);
    }
    /**
     * Object:保险投保明细表's 保单号码3property 
     */
    public String getPolicyNumThree()
    {
        return getString("policyNumThree");
    }
    public void setPolicyNumThree(String item)
    {
        setString("policyNumThree", item);
    }
    /**
     * Object:保险投保明细表's 保单号码4property 
     */
    public String getPolicyNumFour()
    {
        return getString("policyNumFour");
    }
    public void setPolicyNumFour(String item)
    {
        setString("policyNumFour", item);
    }
    /**
     * Object: 保险投保明细表 's 险种3 property 
     */
    public com.kingdee.eas.port.equipment.base.InsuranceInfo getXianzhongThree()
    {
        return (com.kingdee.eas.port.equipment.base.InsuranceInfo)get("xianzhongThree");
    }
    public void setXianzhongThree(com.kingdee.eas.port.equipment.base.InsuranceInfo item)
    {
        put("xianzhongThree", item);
    }
    /**
     * Object: 保险投保明细表 's 险种4 property 
     */
    public com.kingdee.eas.port.equipment.base.InsuranceInfo getXianzhongFour()
    {
        return (com.kingdee.eas.port.equipment.base.InsuranceInfo)get("xianzhongFour");
    }
    public void setXianzhongFour(com.kingdee.eas.port.equipment.base.InsuranceInfo item)
    {
        put("xianzhongFour", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("46F6E919");
    }
}