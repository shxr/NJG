package com.kingdee.eas.fdc.basedata;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractCostAccountInfo extends com.kingdee.eas.framework.TreeBaseInfo implements Serializable 
{
    public AbstractCostAccountInfo()
    {
        this("id");
    }
    protected AbstractCostAccountInfo(String pkField)
    {
        super(pkField);
        put("stageEntrys", new com.kingdee.eas.fdc.basedata.AccountStageCollection());
    }
    /**
     * Object: 成本科目 's 所在的工程项目 property 
     */
    public com.kingdee.eas.fdc.basedata.CurProjectInfo getCurProject()
    {
        return (com.kingdee.eas.fdc.basedata.CurProjectInfo)get("curProject");
    }
    public void setCurProject(com.kingdee.eas.fdc.basedata.CurProjectInfo item)
    {
        put("curProject", item);
    }
    /**
     * Object: 成本科目 's 父结点 property 
     */
    public com.kingdee.eas.fdc.basedata.CostAccountInfo getParent()
    {
        return (com.kingdee.eas.fdc.basedata.CostAccountInfo)get("parent");
    }
    public void setParent(com.kingdee.eas.fdc.basedata.CostAccountInfo item)
    {
        put("parent", item);
    }
    /**
     * Object: 成本科目 's 所在的组织 property 
     */
    public com.kingdee.eas.basedata.org.FullOrgUnitInfo getFullOrgUnit()
    {
        return (com.kingdee.eas.basedata.org.FullOrgUnitInfo)get("fullOrgUnit");
    }
    public void setFullOrgUnit(com.kingdee.eas.basedata.org.FullOrgUnitInfo item)
    {
        put("fullOrgUnit", item);
    }
    /**
     * Object:成本科目's 已分配property 
     */
    public boolean isAssigned()
    {
        return getBoolean("assigned");
    }
    public void setAssigned(boolean item)
    {
        setBoolean("assigned", item);
    }
    /**
     * Object:成本科目's 启用property 
     */
    public boolean isIsEnabled()
    {
        return getBoolean("isEnabled");
    }
    public void setIsEnabled(boolean item)
    {
        setBoolean("isEnabled", item);
    }
    /**
     * Object:成本科目's 是分配源property 
     */
    public boolean isIsSource()
    {
        return getBoolean("isSource");
    }
    public void setIsSource(boolean item)
    {
        setBoolean("isSource", item);
    }
    /**
     * Object:成本科目's 成本科目源idproperty 
     */
    public String getSrcCostAccountId()
    {
        return getString("srcCostAccountId");
    }
    public void setSrcCostAccountId(String item)
    {
        setString("srcCostAccountId", item);
    }
    /**
     * Object:成本科目's 类别property 
     */
    public com.kingdee.eas.fdc.basedata.CostAccountTypeEnum getType()
    {
        return com.kingdee.eas.fdc.basedata.CostAccountTypeEnum.getEnum(getString("type"));
    }
    public void setType(com.kingdee.eas.fdc.basedata.CostAccountTypeEnum item)
    {
		if (item != null) {
        setString("type", item.getValue());
		}
    }
    /**
     * Object:成本科目's 标识property 
     */
    public int getFlag()
    {
        return getInt("flag");
    }
    public void setFlag(int item)
    {
        setInt("flag", item);
    }
    /**
     * Object:成本科目's 是否进入成本数据库property 
     */
    public boolean isIsEnterDB()
    {
        return getBoolean("isEnterDB");
    }
    public void setIsEnterDB(boolean item)
    {
        setBoolean("isEnterDB", item);
    }
    /**
     * Object:成本科目's 是否成本类property 
     */
    public boolean isIsCostAccount()
    {
        return getBoolean("isCostAccount");
    }
    public void setIsCostAccount(boolean item)
    {
        setBoolean("isCostAccount", item);
    }
    /**
     * Object:成本科目's 成本科目编码property 
     */
    public String getCodingNumber()
    {
        return getString("codingNumber");
    }
    public void setCodingNumber(String item)
    {
        setString("codingNumber", item);
    }
    /**
     * Object: 成本科目 's 测算阶段 property 
     */
    public com.kingdee.eas.fdc.basedata.AccountStageCollection getStageEntrys()
    {
        return (com.kingdee.eas.fdc.basedata.AccountStageCollection)get("stageEntrys");
    }
    /**
     * Object:成本科目's 是否增加property 
     */
    public boolean isIsCanAdd()
    {
        return getBoolean("isCanAdd");
    }
    public void setIsCanAdd(boolean item)
    {
        setBoolean("isCanAdd", item);
    }
    /**
     * Object: 成本科目 's 创建组织 property 
     */
    public com.kingdee.eas.basedata.org.FullOrgUnitInfo getCreateOrg()
    {
        return (com.kingdee.eas.basedata.org.FullOrgUnitInfo)get("createOrg");
    }
    public void setCreateOrg(com.kingdee.eas.basedata.org.FullOrgUnitInfo item)
    {
        put("createOrg", item);
    }
    /**
     * Object:成本科目's 可拆分科目property 
     */
    public boolean isIsSplit()
    {
        return getBoolean("isSplit");
    }
    public void setIsSplit(boolean item)
    {
        setBoolean("isSplit", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("8423FF6E");
    }
}