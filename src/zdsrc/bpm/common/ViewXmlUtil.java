package com.kingdee.eas.bpm.common;

import java.sql.SQLException;
import java.util.Date;

import javax.sql.RowSet;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.bpm.ApproveBackSetFactory;
import com.kingdee.eas.bpm.ApproveBackSetInfo;
import com.kingdee.eas.bpm.BPMLogFactory;
import com.kingdee.eas.bpm.BPMLogInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.fi.arap.util.DBUtil;

public class ViewXmlUtil {
	public static String[] getViewXmlString(Context ctx,String BTID,String BOID){
		String[] str = new String[3];
		str[0] = "Y";
		str[1] = "�ɹ���";
		ApproveBackSetInfo info = null;
		try {
			info = ApproveBackSetFactory.getLocalInstance(ctx).getApproveBackSetInfo(" where number='"+BTID+"'");
		} catch (EASBizException e1) {
			str[0] = "N";
			str[1] = BTID+"---��ȡ������д���ö���ʧ�ܣ������ã�";
			str[2] = "����ID-BOID:"+BOID;
			e1.printStackTrace();
		} catch (BOSException e1) {
			str[0] = "N";
			str[1] = BTID+"---��ȡ������д���ö���ʧ�ܣ������ã�";
			str[2] = "����ID-BOID:"+BOID;
			e1.printStackTrace();
		}
		if(info==null){
			str[0] = "N";
			str[1] = "���ݱ�ʾ-BTID:"+BTID+",δ�ҵ�������д���ã��������Ӧ����";
			str[2] = "����ID-BOID:"+BOID;
		}
		String view = info.getView();
		if(view==null || "".equals(view)){
			str[0] = "N";
			str[1] = "���ݱ�ʾ-BTID:"+BTID+",��ͼΪ�գ�����д���ݶ�Ӧ��ͼ";
			str[2] = "����ID-BOID:"+BOID;
		}
		try {
			String where = " where fid='"+BOID+"'";
			StringBuffer xml = new StringBuffer();
			xml.append("<DATA>\n");
			RowSet rs = DBUtil.executeQuery(ctx,"select * from "+view+where );
			int colunmCount;
			try {
				colunmCount = rs.getMetaData().getColumnCount();
		        String[] colNameArr = new String[colunmCount];  
		        String[] colTypeArr = new String[colunmCount];  
		        while(rs.next()){
		        	for (int i = 0; i < colunmCount; i++) {  
			        	colNameArr[i] = rs.getMetaData().getColumnName(i + 1);  
			        	colTypeArr[i] = rs.getMetaData().getColumnTypeName(i + 1);  
			        	xml.append("<"+colNameArr[i]+">");
			        	xml.append(rs.getObject(i+1));
			        	xml.append("</"+colNameArr[i]+">\n");
			        }  
		        }
		        xml.append("<DATA>\n");
		        str[1]=xml.toString();
			} catch (SQLException e) {
				str[0] = "N";
				str[1] = "���ݱ�ʾ-BTID:"+BTID+",ִ����ͼ���ʧ�ܣ���view��"+view;
				str[2] = "����ID-BOID:"+BOID;
				e.printStackTrace();
			}  
		} catch (BOSException e) {
			str[0] = "N";
			str[1] = "���ݱ�ʾ-BTID:"+BTID+",ִ����ͼ���ʧ�ܣ���view��"+view;
			str[2] = "����ID-BOID:"+BOID;
			e.printStackTrace();
		}finally{
			BPMLogInfo log = new BPMLogInfo();
			try {
				log.setLogDate(new Date());
				log.setName("EAS���:"+str[0]);
				log.setDescription("������Ϣ"+str[2]);
				log.setBeizhu("���ýӿڷ�����getViewXmlString");
				BPMLogFactory.getLocalInstance(ctx).save(log);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return str;
	}
}