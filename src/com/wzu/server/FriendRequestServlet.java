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
        
        JSONArray friendarray;  //不需要键值
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
        	  String id = FriendList.get(i).getUid();  //作为申请处理人，我们是被申请者，查询的是UID
        	  friendobject.put("name",userdaoimp.UserFindByID(id).getUserName());  //形成有键的单元对象,与转化后的map对象相似
        	  friendobject.put("icon",userdaoimp.UserFindByID(id).getUserIcon());
        	  friendobject.put("time",FriendList.get(i).getTime());
        	  friendobject.put("num",FriendList.get(i).getNum());
        	  friendobject.put("fid",id);
        	  friendarray.add(friendobject);   //Array集合添加这个构造类
        	  friendobject = null;
          }
          
      } catch (Exception e) {
          e.printStackTrace();
      }
	//在浏览器中显示为utf-8
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
