package com.wzu.model;

import java.util.ArrayList;

public interface ActiveRequestDao {
	public abstract Boolean AddActiveRequest(String userid,String actid);
	public abstract ArrayList<ModelRequest> RequestByActID(String actid);   //���������Ϣ
	public abstract int RequestSuccessNum(String  actid);
	public abstract String ChangeRequestNum(String actid,String uid,String num);
	public abstract int SearchNum(String actid,String uid);
	public abstract ArrayList<String> ReqSuccessByUserID(String userid);  //ĳ������ɹ��Ļ���б�
	public abstract ArrayList<String> ReqSuccessByActID(String actid);   //ĳ�����ɹ������б�
	
	public abstract boolean DeletebyID(String userid);
	public abstract boolean DeletebyActID(String activeid);
}
