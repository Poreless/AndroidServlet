// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 2018/3/21 22:27:25
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   User.java

package com.wzu.model;


public class User
{

    private String userID;
    private String userName;
    private String userPwd;
    private String userSex;
    public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	private String userIcon;
    private String userTel;
    private String school;

    public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public User()
    {
    }

    public String getUserID()
    {
        return userID;
    }

    public void setUserID(String userID)
    {
        this.userID = userID;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserPwd()
    {
        return userPwd;
    }

    public void setUserPwd(String userPwd)
    {
        this.userPwd = userPwd;
    }

    public String getUserSex()
    {
        return userSex;
    }

    public void setUserSex(String userSex)
    {
        this.userSex = userSex;
    }

}
