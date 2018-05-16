package com.wzu.server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.wzu.db.CommentDaoImp;

/**
 * ������۵�servlet
 */
@WebServlet("/CommentAddServlet")
public class CommentAddServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CommentDaoImp commentdaoimp = new CommentDaoImp();
		request.setCharacterEncoding("utf-8");
		
		String userID = request.getParameter("uid");
		String actID = request.getParameter("aid");
		String num = request.getParameter("num");
		String text = request.getParameter("text");
		JSONObject objData = new JSONObject();
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		System.out.println("user_ID:"+userID+"\n"+"actID:"+actID+"\n"+"num:"+num+"\n"+"text:"+text);
		if(commentdaoimp.AddComment(userID, actID, text, num)!=false){
			objData.put("code", 200);
            objData.put("msg", "success");
            objData.put("log", "����ɹ��������ĵȴ�"); 
		}else{
			objData.put("code", 300);
            objData.put("msg", "erro");
            objData.put("log", "����ʧ��"); 
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
