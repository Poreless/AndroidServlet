// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2018/3/21 22:36:40
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   UserDao.java

package com.wzu.model;


// Referenced classes of package com.wzu.model:
//            User

public interface UserDao
{

    public abstract boolean UserFind(String s, String s1);

    public abstract User UserMessage(String s, String s1);

    public abstract User UserFindByID(String s);
    
    public abstract String UserCreate(String userid, String userphone,String username,String userpwd,String school);
    
    public abstract boolean MessageSetByID(String userid,String userIcon,String sex,String name);
    
    public abstract boolean UpdateLoginTime(String userid);
    public abstract boolean CreateLoginTime(String userid);
    public abstract String SearchLoginTime(String userid);
    
    public abstract boolean AdminLogin(String s, String s1);
    public abstract boolean DeletebyID(String userid);
    
    public abstract boolean DeleteTimebyID(String userid);
}