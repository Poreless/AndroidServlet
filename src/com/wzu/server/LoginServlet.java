// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2018/3/21 23:41:57
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   LoginServlet.java

package com.wzu.server;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wzu.db.TagDaoImp;
import com.wzu.db.UserDaoImp;
import com.wzu.model.Active;
import com.wzu.model.User;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        UserDaoImp userDaoImp = new UserDaoImp();
        TagDaoImp tagdaoimp = new TagDaoImp();  //需要做兴趣查询
        String userID = request.getParameter("userID");
        String password = request.getParameter("password");
	    JSONArray tagjson;  //不需要键值
	    JSONObject tagname;
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        System.out.println("userID:"+userID);
        System.out.println("password:"+password);
        
        User user = userDaoImp.UserMessage(userID, password);
        
        ArrayList ListUser = new ArrayList<User>();
        Map<String, User> map = new HashMap();
        JSONObject objData = new JSONObject();
        
        if(userDaoImp.UserFind(userID, password))
        {
        	if(userDaoImp.SearchLoginTime(userID)!=null){
        		userDaoImp.UpdateLoginTime(userID);
        	}else{
        		userDaoImp.CreateLoginTime(userID);
        	}
            objData.put("code", 200);
            objData.put("msg", "success");     
        	map.put("user_info", user);
            Object json = JSONObject.toJSON(map);
            objData.put("result", json);
            System.out.println(objData.toString());
            
            tagjson = new JSONArray();
   		 	ArrayList<String> sel = tagdaoimp.TagFindByID(userID);
            try {
                for(int i=0;i<sel.size();i++){
               	 tagname = new JSONObject();
                    tagname.put("name",sel.get(i));
                    tagjson.add(tagname);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            objData.put("tag", tagjson.toJSONString());
            response.getOutputStream().write(objData.toString().getBytes("utf-8"));
/*            response.getOutputStream().write(user.getUserName().getBytes("utf-8"));
            response.getOutputStream().write("login success".getBytes());*/
        } else
        {
            objData.put("msg", "error");
            objData.put("code", 400);
            Object json = JSONObject.toJSON(new User());
            objData.put("user_info", json);
            response.getOutputStream().write(objData.toString().getBytes("utf-8"));     	
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        System.out.println("doPost");
        doGet(request, response);
    }
}