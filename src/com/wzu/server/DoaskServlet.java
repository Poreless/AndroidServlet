package com.wzu.server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.wzu.db.ActiveRequestDaoImp;
/**
 * 用于回应其他用户申请的Servlet
 * @author Aspiration
 *
 */

public class DoaskServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ActiveRequestDaoImp activerequestdaoimp = new ActiveRequestDaoImp();
		String userID = request.getParameter("r_usid");
		String actID = request.getParameter("r_aid");
		String num = request.getParameter("num");
		System.out.println("r_aid:"+actID+"r_usid:"+userID+"num:"+num);;
		JSONObject objData = new JSONObject();
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		if(userID!=null&&actID!=null&&num!=null){
			String res = activerequestdaoimp.ChangeRequestNum(actID, userID, num);
			System.out.println(res);
			objData.put("msg", "success");
            objData.put("code", 200);
            objData.put("result", res);
		}else{
			objData.put("msg", "erro");
            objData.put("code", 400);
            objData.put("result", "无响应");
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
