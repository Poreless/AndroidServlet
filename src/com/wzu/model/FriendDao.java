package com.wzu.model;

import java.util.ArrayList;

public  interface FriendDao {
	public abstract ArrayList<String> FindReqFriendListByID(String uid);
	public abstract boolean addfriend(String uid,String fid); //ǰ��Ϊ�����ߣ�����Ϊ��������
	//����ظ����룬δ�趨ΨһԼ��ʱ��Ҫ
	public abstract ArrayList<Friend> ListFriendRequest(String uid,String fid); //ǰ��Ϊ�����ߣ�����Ϊ��������
	public abstract ArrayList<Friend> RequestByUserID(String uid); //ǰ��Ϊ�����ߣ�����Ϊ��������
	public abstract int SearchNum(String userid,String friendid);   //��֤�Ƿ����
	public abstract String ChangeFriendRequestNum(String userid,String friendid,String num);
	
	public abstract boolean DeletebyID(String userid);
}
