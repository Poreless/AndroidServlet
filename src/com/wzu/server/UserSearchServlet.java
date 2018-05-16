package com.wzu.server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.wzu.db.UserDaoImp;
import com.wzu.model.User;

/**
 * Servlet implementation class UserSearchServlet
 */
@WebServlet("/UserSearchServlet")
public class UserSearchServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDaoImp userDaoImp = new UserDaoImp();
		String userID = request.getParameter("uid");
		User user = null;
		String time = null;
		JSONObject objData = new JSONObject();
		JSONObject message = new JSONObject();
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		if(userID!=null){
			user= new User();
			time = new String();
			user = userDaoImp.UserFindByID(userID);
			time = userDaoImp.SearchLoginTime(userID);
			
			if(user!=null){   //是否为有效ID
				message.put("userid",userID);
				message.put("username",user.getUserName());
				if(time!=null){
					message.put("time",time);
				}
				objData.put("code", 200);
		        objData.put("msg", "success");     
		        objData.put("result", message);
			}else{
				objData.put("code", 200);
		        objData.put("msg", "fail");     
			}

		}else{
			objData.put("code", 400);
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
