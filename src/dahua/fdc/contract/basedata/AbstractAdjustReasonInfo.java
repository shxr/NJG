package com.kingdee.eas.fdc.basedata;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractAdjustReasonInfo extends com.kingdee.eas.fdc.basedata.FDCDataBaseInfo implements Serializable 
{
    public AbstractAdjustReasonInfo()
    {
        this("id");
    }
    protected AbstractAdjustReasonInfo(String pkField)
    {
        super(pkField);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("3880CAA5");
    }
}