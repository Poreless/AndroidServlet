package com.wzu.server;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.wzu.db.ActiveDaoImp;
import com.wzu.db.ActiveRequestDaoImp;
import com.wzu.db.CommentDaoImp;
import com.wzu.db.FriendDaoImp;
import com.wzu.db.TagDaoImp;
import com.wzu.db.UserDaoImp;
import com.wzu.model.Active;

/**
 * Servlet implementation class UserDeleteServlet
 */
@WebServlet("/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDaoImp userDaoImp = new UserDaoImp();
		FriendDaoImp frienddaoimp = new FriendDaoImp();
		CommentDaoImp commentdaoimp = new CommentDaoImp();
		ActiveRequestDaoImp requestdaoimp = new ActiveRequestDaoImp();
		ActiveDaoImp activedaoimp = new ActiveDaoImp();
		TagDaoImp tagdaoimp = new TagDaoImp();
		
		String userID = request.getParameter("uid");
		 JSONObject objData = new JSONObject();
		if(userID!=null){
			frienddaoimp.DeletebyID(userID);
			commentdaoimp.DeletebyID(userID);
			requestdaoimp.DeletebyID(userID);
			tagdaoimp.DeletebyID(userID);
			userDaoImp.DeleteTimebyID(userID);
			
			ArrayList<Active> listact = activedaoimp.FindActiveByUserID(userID);
			if(listact!=null){
				for(int i=0;i<listact.size();i++){
					//先删除与该活动的相关申请
					requestdaoimp.DeletebyActID(listact.get(i).getActive_id()+"");
					
					//再删除与该活动相关的评价
					commentdaoimp.DeletebyactID(listact.get(i).getActive_id()+"");
				}
				
			}
			activedaoimp.DeletebyID(userID);

			 
			if(userDaoImp.DeletebyID(userID)==true){
			 	objData.put("code", 200);
	            objData.put("msg", "success"); 
			}else{
	        	objData.put("code", 200);
	            objData.put("msg", "fail");  
			}
		}else{
        	objData.put("code", 200);
            objData.put("msg", "fail");
		}
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
