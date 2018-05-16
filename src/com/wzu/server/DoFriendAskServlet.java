package com.wzu.server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.wzu.db.ActiveRequestDaoImp;
import com.wzu.db.FriendDaoImp;

/**
 * Servlet implementation class DoFriendAskServlet
 */
@WebServlet("/DoFriendAskServlet")
public class DoFriendAskServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		FriendDaoImp frienddaoimp = new FriendDaoImp();
		String userID = request.getParameter("r_uid");
		String friendID = request.getParameter("r_fid");
		String num = request.getParameter("num");
		System.out.println("r_fid:"+friendID+"r_usid:"+userID+"num:"+num);;
		JSONObject objData = new JSONObject();
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		if(userID!=null&&friendID!=null&&num!=null){
			String res = frienddaoimp.ChangeFriendRequestNum(userID, friendID, num);
			System.out.println(res);
			objData.put("msg", "success");
            objData.put("code", 200);
            objData.put("result", res);
		}else{
			objData.put("msg", "erro");
            objData.put("code", 400);
            objData.put("result", "ŒﬁœÏ”¶");
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
