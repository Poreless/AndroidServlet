// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 2018/3/21 22:26:55
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Friend.java

package com.wzu.model;


public class Friend
{
	private int id;
	private String uid;
	private String fid;
	private int num;
	private String time;

    public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}


	public Friend()
    {
    }
}
