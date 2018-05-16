package com.wzu.model;

import java.util.ArrayList;
import java.util.Date;

public class ActiveRequest {
	private String date;
	private String active_id;
	public String getActive_id() {
		return active_id;
	}
	public void setActive_id(String active_id) {
		this.active_id = active_id;
	}
	/**num==
	 * 0表示待定
	 * 1表示同意
	 * 2表示拒绝
	 * 3表示忽略
	 */
	private String num;
	private User user;
	private String active_name;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getActive_name() {
		return active_name;
	}
	public void setActive_name(String active_name) {
		this.active_name = active_name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}

	

}
