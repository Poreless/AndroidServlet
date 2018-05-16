package com.wzu.model;

public class FriendListInfo{
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	private String fid;
	private String ImageUrl;
	private String friendname;
	private int num;  //ÇëÇó×´Ì¬
	public String getFriendname() {
		return friendname;
	}
	public void setFriendname(String friendname) {
		this.friendname = friendname;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getImageUrl() {
		return ImageUrl;
	}
	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}
}
