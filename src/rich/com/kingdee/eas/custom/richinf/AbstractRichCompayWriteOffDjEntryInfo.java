package com.kingdee.eas.custom.richinf;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractRichCompayWriteOffDjEntryInfo extends com.kingdee.eas.framework.CoreBillEntryBaseInfo implements Serializable 
{
    public AbstractRichCompayWriteOffDjEntryInfo()
    {
        this("id");
    }
    protected AbstractRichCompayWriteOffDjEntryInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: 到检单 's null property 
     */
    public com.kingdee.eas.custom.richinf.RichCompayWriteOffInfo getParent()
    {
        return (com.kingdee.eas.custom.richinf.RichCompayWriteOffInfo)get("parent");
    }
    public void setParent(com.kingdee.eas.custom.richinf.RichCompayWriteOffInfo item)
    {
        put("parent", item);
    }
    /**
     * Object: 到检单 's 开票单位 property 
     */
    public com.kingdee.eas.basedata.master.cssp.CustomerInfo getKpUnit()
    {
        return (com.kingdee.eas.basedata.master.cssp.CustomerInfo)get("kpUnit");
    }
    public void setKpUnit(com.kingdee.eas.basedata.master.cssp.CustomerInfo item)
    {
        put("kpUnit", item);
    }
    /**
     * Object: 到检单 's 到检机构 property 
     */
    public com.kingdee.eas.basedata.org.CompanyOrgUnitInfo getDjjg()
    {
        return (com.kingdee.eas.basedata.org.CompanyOrgUnitInfo)get("djjg");
    }
    public void setDjjg(com.kingdee.eas.basedata.org.CompanyOrgUnitInfo item)
    {
        put("djjg", item);
    }
    /**
     * Object: 到检单 's 到检编号 property 
     */
    public com.kingdee.eas.custom.richinf.RichExamedInfo getDjNo()
    {
        return (com.kingdee.eas.custom.richinf.RichExamedInfo)get("djNo");
    }
    public void setDjNo(com.kingdee.eas.custom.richinf.RichExamedInfo item)
    {
        put("djNo", item);
    }
    /**
     * Object:到检单's 落单号property 
     */
    public String getLdNo()
    {
        return getString("ldNo");
    }
    public void setLdNo(String item)
    {
        setString("ldNo", item);
    }
    /**
     * Object:到检单's 结算金额property 
     */
    public String getJsAmount()
    {
        return getString("jsAmount");
    }
    public void setJsAmount(String item)
    {
        setString("jsAmount", item);
    }
    /**
     * Object:到检单's 备注property 
     */
    public String getBeizhu()
    {
        return getString("beizhu");
    }
    public void setBeizhu(String item)
    {
        setString("beizhu", item);
    }
    /**
     * Object:到检单's 已核销金额property 
     */
    public java.math.BigDecimal getDj_yhx()
    {
        return getBigDecimal("dj_yhx");
    }
    public void setDj_yhx(java.math.BigDecimal item)
    {
        setBigDecimal("dj_yhx", item);
    }
    /**
     * Object:到检单's 本次核销金额property 
     */
    public java.math.BigDecimal getBencihx()
    {
        return getBigDecimal("bencihx");
    }
    public void setBencihx(java.math.BigDecimal item)
    {
        setBigDecimal("bencihx", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("77C11D0B");
    }
}