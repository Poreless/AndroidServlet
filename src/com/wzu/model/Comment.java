package com.wzu.model;

public class Comment {
    private int active_id;
    private String user_id;
    private String date;
    private String text;
    private int num; //活动评分
    private User user; //2018/4/19 加入用户信息
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getActive_id() {
		return active_id;
	}
	public void setActive_id(int active_id) {
		this.active_id = active_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
    

}
