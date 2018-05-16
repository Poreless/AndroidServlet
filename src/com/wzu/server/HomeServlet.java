// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2018/3/21 23:38:54
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   HomeServlet.java

package com.wzu.server;

import com.alibaba.fastjson.JSONObject;
import com.wzu.db.ActiveDaoImp;
import com.wzu.db.ExtendDaoImp;
import com.wzu.db.FriendDaoImp;
import com.wzu.db.TagDaoImp;
import com.wzu.db.UserDaoImp;
import com.wzu.model.Active;
import com.wzu.model.User;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;

public class HomeServlet extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
		
		String userID = request.getParameter("uid");
		User user = null;
        ActiveDaoImp activedaoimp = new ActiveDaoImp();
        ExtendDaoImp extenddaoimp = new ExtendDaoImp();
        UserDaoImp userdaoimp = new UserDaoImp();
        FriendDaoImp frienddaoimp = new FriendDaoImp();
        TagDaoImp tagdaoimp = new TagDaoImp();  //��Ҫ����Ȥ��ѯ
        
        ArrayList<Active>  ListActiveAll = new ArrayList(); //ȫ���
        
        ArrayList<Active>  ListActive = new ArrayList(); //��Ч�
        ArrayList NearActive = new ArrayList();  //����
        ArrayList<Active> FriendActiveList = new ArrayList();  //���Ѵ���
        ArrayList<Active>  SchoolActiveList = new ArrayList();  //У�Ѵ���
        ArrayList<Active> LoveActiveList = new ArrayList();   //��Ȥ�Ƽ�
        
        ListActiveAll = activedaoimp.FindAllActive();
        if(userID!=null){
        	user = userdaoimp.UserFindByID(userID);
        }
        //ȫ��
        if(ListActiveAll!=null){
			for(int j=0;j<ListActiveAll.size();j++){
				if(DateFresh(ListActiveAll.get(j).getCreate_time())){
					ListActive.add(ListActiveAll.get(j));
				}
			}
		}
        //����
        ArrayList<String> ListString  = new ArrayList();
        if(userID!=null){
	        ListString=frienddaoimp.FindReqFriendListByID(userID);
	        if(ListString!=null){
	        	for(int i=0;i<ListString.size();i++){
	        		ArrayList<Active> list = new ArrayList(); 
	        		list = activedaoimp.FindActiveByUserID(ListString.get(i));
	        		if(list!=null){
	        			for(int j=0;j<list.size();j++){
	        				if(DateFresh(list.get(j).getCreate_time())){
	        					FriendActiveList.add(list.get(j));
	        				}
	        			}
	        		}
	        	}
	        }
        }
        //ѧУ
        if(user!=null){
	        if(ListActive!=null){
				for(int j=0;j<ListActive.size();j++){
					String school = ListActive.get(j).getUser().getSchool();
					if(school!=null&&user.getSchool()!=null){
						if(school.equals(user.getSchool())){
							SchoolActiveList.add(ListActive.get(j));
						}
					}
	
				}
			}
        }
       //��Ȥ
        if(userID!=null){
        ArrayList<String> sel = tagdaoimp.TagFindByID(userID);
        if(ListActive!=null){
				for(int i=0;i<ListActive.size();i++){
						if(sel!=null){
							for(int j=0;j<sel.size();j++){
								String theme =ListActive.get(i).getActive_theme();
								if(theme!=null){
									if(theme.equals(sel.get(j))){ //��������һ����Ȥ
										LoveActiveList.add(ListActive.get(i));
									}
								}				
						}
	
					}
				}
			}
        }
        Map map = new HashMap();
        JSONObject objData = new JSONObject();
        
        //�����������ʾΪutf-8
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        if(activedaoimp.FindAllActive() != null || extenddaoimp.FindAllExtend() != null)
        {
            map.put("active_info", ListActive);
           map.put("active_school", SchoolActiveList);
            map.put("active_friend", FriendActiveList);
            map.put("active_love", LoveActiveList);
            map.put("extend_info", extenddaoimp.FindAllExtend());
            objData.put("msg", "success");
            objData.put("code", 200);
            Object json = JSONObject.toJSON(map);
            objData.put("result", json);
            response.getOutputStream().write(objData.toString().getBytes("utf-8"));
        } else
        {
            objData.put("msg", "error");
            objData.put("code", 400);
            Object json = JSONObject.toJSON(new Active());
            objData.put("active_info", json);
            response.getOutputStream().write(objData.toString().getBytes("utf-8"));
        }
    }

	private boolean DateFresh(String finish_time) {
		// TODO Auto-generated method stub
		 Date now = new Date();	//��ȡ���ڵ����ڶ���
         Date thedate = null;
		try {
            thedate =  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                    .parse(finish_time);
		} catch (Exception e) {
			// TODO: handle exception
		}
         int i = now.compareTo(thedate);
         if(i > 0){
        	 return false;  //��������������֮ǰ
         }else{
        	 return true;
         }
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        doGet(request, response);
    }


}