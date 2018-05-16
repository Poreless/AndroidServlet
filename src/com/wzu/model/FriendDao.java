package com.wzu.model;

import java.util.ArrayList;

public  interface FriendDao {
	public abstract ArrayList<String> FindReqFriendListByID(String uid);
	public abstract boolean addfriend(String uid,String fid); //前者为申请者，后者为被申请人
	//监测重复申请，未设定唯一约束时需要
	public abstract ArrayList<Friend> ListFriendRequest(String uid,String fid); //前者为申请者，后者为被申请人
	public abstract ArrayList<Friend> RequestByUserID(String uid); //前者为申请者，后者为被申请人
	public abstract int SearchNum(String userid,String friendid);   //验证是否处理过
	public abstract String ChangeFriendRequestNum(String userid,String friendid,String num);
	
	public abstract boolean DeletebyID(String userid);
}
