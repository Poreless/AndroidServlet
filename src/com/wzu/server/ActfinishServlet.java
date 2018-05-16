package com.wzu.server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.wzu.db.ActiveDaoImp;
/**
 * 用于处理用于结束活动操作的servlet
 * @author Aspiration
 *
 */

public class ActfinishServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ActiveDaoImp activedaoimp = new ActiveDaoImp();
		String activeID = request.getParameter("aid");	
		System.out.println("activeID:"+activeID);
		JSONObject objData = new JSONObject();
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		if(activeID!=null){
		if(activedaoimp.ActiveFinish(activeID)==true){
            objData.put("msg", "success");
            objData.put("code", 200);
            response.getOutputStream().write(objData.toString().getBytes("utf-8"));
		}else{
            objData.put("msg", "error");
            objData.put("code", 303);
            response.getOutputStream().write(objData.toString().getBytes("utf-8"));
		}
		}else{
            objData.put("msg", "error");
            objData.put("code", 400);
            response.getOutputStream().write(objData.toString().getBytes("utf-8"));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
