package com.wzu.model;

import java.util.ArrayList;

public interface ActiveRequestDao {
	public abstract Boolean AddActiveRequest(String userid,String actid);
	public abstract ArrayList<ModelRequest> RequestByActID(String actid);   //活动的申请信息
	public abstract int RequestSuccessNum(String  actid);
	public abstract String ChangeRequestNum(String actid,String uid,String num);
	public abstract int SearchNum(String actid,String uid);
	public abstract ArrayList<String> ReqSuccessByUserID(String userid);  //某人请求成功的活动名列表
	public abstract ArrayList<String> ReqSuccessByActID(String actid);   //某活动请求成功的人列表
	
	public abstract boolean DeletebyID(String userid);
	public abstract boolean DeletebyActID(String activeid);
}
