package com.wzu.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wzu.db.FriendDaoImp;
import com.wzu.db.UserDaoImp;
import com.wzu.model.Friend;
import com.wzu.model.FriendListInfo;

/**
 * Servlet implementation class FriendRequestServlet
 */
@WebServlet("/FriendRequestServlet")
public class FriendRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("null")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        FriendDaoImp frienddaoimp = new FriendDaoImp();
        FriendListInfo friendlistinfo;
        
        String uid =request.getParameter("uid");
        System.out.println("uid:"+uid);
        UserDaoImp userdaoimp = new UserDaoImp();
       
        ArrayList<Friend> FriendList = new ArrayList();
        
        JSONArray friendarray;  //����Ҫ��ֵ
		JSONObject friendobject;
        JSONObject objData =  new JSONObject();
        if(uid!=null){
        	try {
        		FriendList = frienddaoimp.RequestByUserID(uid);
			} catch (Exception e) {
				// TODO: handle exception
			}
        }
        friendarray = new JSONArray();
		 try {
          for(int i=0;i<FriendList.size();i++){
        	  friendobject = new JSONObject();
        	  String id = FriendList.get(i).getUid();  //��Ϊ���봦���ˣ������Ǳ������ߣ���ѯ����UID
        	  friendobject.put("name",userdaoimp.UserFindByID(id).getUserName());  //�γ��м��ĵ�Ԫ����,��ת�����map��������
        	  friendobject.put("icon",userdaoimp.UserFindByID(id).getUserIcon());
        	  friendobject.put("time",FriendList.get(i).getTime());
        	  friendobject.put("num",FriendList.get(i).getNum());
        	  friendobject.put("fid",id);
        	  friendarray.add(friendobject);   //Array����������������
        	  friendobject = null;
          }
          
      } catch (Exception e) {
          e.printStackTrace();
      }
	//�����������ʾΪutf-8
	response.setHeader("Content-type", "text/html;charset=UTF-8");

    objData.put("msg", "success");
    objData.put("code", 200);
    objData.put("result", friendarray);
    response.getOutputStream().write(objData.toString().getBytes("utf-8"));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
