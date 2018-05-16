package com.wzu.server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.wzu.db.TagDaoImp;
import com.wzu.db.UserDaoImp;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 UserDaoImp userDaoImp = new UserDaoImp();
        String adminID = request.getParameter("adminID");
        String password = request.getParameter("password");
        JSONObject objData = new JSONObject();
        if(userDaoImp.AdminLogin(adminID, password)==true){
        	objData.put("code", 200);
            objData.put("msg", "success");     
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
