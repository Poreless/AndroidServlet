// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 2018/3/21 22:26:16
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ActiveDao.java

package com.wzu.model;

import java.util.ArrayList;

public interface ActiveDao
{

    public abstract ArrayList<Active> FindAllActive();
//从json传入的id都为string类型
    public abstract ArrayList<User> UserListByID(String i);
    
    public abstract Active FindActiveByID(String i);
    public abstract Boolean ActiveCreate(String user_id,String active_name,String active_theme,String active_place,String active_decipt,String active_time,String active_num);
    public abstract ArrayList<Active> FindActiveByUserID(String userid);
    public abstract ArrayList<Active> FindFreshByUserID(String userid);
    public abstract ArrayList<Active> FindFinishByUserID(String userid);
    public abstract Boolean ActiveFinish(String activeid);
    
    public abstract boolean DeletebyID(String userid); //这里的activeid有外键约束
    
    public abstract Boolean ActChangeByID(String act_id,String active_name,String active_theme,String active_place,String active_decipt,String active_time,String active_num);
}

