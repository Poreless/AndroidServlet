// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 2018/3/21 22:25:38
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Active.java

package com.wzu.model;

import java.util.ArrayList;

// Referenced classes of package com.wzu.model:
//            User

public class Active
{
    private int active_id;
    private String active_name;
    private String active_place;
    private String active_depict;
    private String active_icon;
    private int maxnum;
    private String create_time;
	private String finish_time;
    private String active_theme;  //用于兴趣搜索
    private ArrayList<Comment> commentlist;   //2018/4/19 评论列表
    public ArrayList<Comment> getCommentlist() {
		return commentlist;
	}

	public void setCommentlist(ArrayList<Comment> commentlist) {
		this.commentlist = commentlist;
	}

	public String getActive_theme() {
		return active_theme;
	}

	public void setActive_theme(String active_theme) {
		this.active_theme = active_theme;
	}

	private User user;

    public Active()
    {
    }

    public int getMaxnum()
    {
        return maxnum;
    }

    public void setMaxnum(int maxnum)
    {
        this.maxnum = maxnum;
    }

    public String getActive_icon()
    {
        return active_icon;
    }

    public void setActive_icon(String active_icon)
    {
        this.active_icon = active_icon;
    }

    public int getActive_id()
    {
        return active_id;
    }

    public void setActive_id(int i)
    {
        active_id = i;
    }

    public String getActive_name()
    {
        return active_name;
    }

    public void setActive_name(String active_name)
    {
        this.active_name = active_name;
    }

    public String getActive_place()
    {
        return active_place;
    }

    public void setActive_place(String active_place)
    {
        this.active_place = active_place;
    }

    public String getActive_depict()
    {
        return active_depict;
    }

    public void setActive_depict(String active_depict)
    {
        this.active_depict = active_depict;
    }

    public String getCreate_time()
    {
        return create_time;
    }

    public void setCreate_time(String create_time)
    {
        this.create_time = create_time;
    }

    public String getFinish_time()
    {
        return finish_time;
    }

    public void setFinish_time(String finish_time)
    {
        this.finish_time = finish_time;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }


}
