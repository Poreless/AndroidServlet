package com.wzu.model;


/**
 * ���ڼ���������Ϣ��ʵ����
 * �����û���Ϣ����������
 * @author Aspiration
 *������
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
