package com.wzu.model;


/**
 * 用于记载评价信息的实体类
 * 包含用户信息，评价内容
 * @author Aspiration
 *已弃用
 */
public class CommentModel {
	User user;
	String text;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
