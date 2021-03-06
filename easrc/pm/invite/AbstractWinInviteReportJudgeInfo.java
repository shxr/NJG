package com.kingdee.eas.port.pm.invite;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractWinInviteReportJudgeInfo extends com.kingdee.eas.framework.CoreBillEntryBaseInfo implements Serializable 
{
    public AbstractWinInviteReportJudgeInfo()
    {
        this("id");
    }
    protected AbstractWinInviteReportJudgeInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: 专家评委 's null property 
     */
    public com.kingdee.eas.port.pm.invite.WinInviteReportInfo getParent()
    {
        return (com.kingdee.eas.port.pm.invite.WinInviteReportInfo)get("parent");
    }
    public void setParent(com.kingdee.eas.port.pm.invite.WinInviteReportInfo item)
    {
        put("parent", item);
    }
    /**
     * Object: 专家评委 's 部门 property 
     */
    public com.kingdee.eas.basedata.org.AdminOrgUnitInfo getOrg()
    {
        return (com.kingdee.eas.basedata.org.AdminOrgUnitInfo)get("org");
    }
    public void setOrg(com.kingdee.eas.basedata.org.AdminOrgUnitInfo item)
    {
        put("org", item);
    }
    /**
     * Object: 专家评委 's 专家姓名 property 
     */
    public com.kingdee.eas.port.pm.base.JudgesInfo getJudgesName()
    {
        return (com.kingdee.eas.port.pm.base.JudgesInfo)get("judgesName");
    }
    public void setJudgesName(com.kingdee.eas.port.pm.base.JudgesInfo item)
    {
        put("judgesName", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("3E9385C7");
    }
}