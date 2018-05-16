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
import com.wzu.db.UserDaoImp;
import com.wzu.model.Active;
import com.wzu.model.User;

/**
 * Servlet implementation class JoinIconServlet
 */
@WebServlet("/JoinIconServlet")
public class JoinIconServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ActiveRequestDaoImp activerequestdaoimp = new ActiveRequestDaoImp();
		UserDaoImp userimp = new UserDaoImp();
        String actID = request.getParameter("aid");
        JSONObject objData = new JSONObject();
        JSONObject IConName;
        ArrayList<JSONObject> ListICon = new ArrayList();
        //User user = null;
        int num=0;   //当前人数
        
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        
        ArrayList<String> ListIcon = new ArrayList();
        try {
        	ListIcon = activerequestdaoimp.ReqSuccessByActID(actID);
		} catch (Exception e) {
			// TODO: handle exception
		}
        
        try {
        	num = activerequestdaoimp.RequestSuccessNum(actID);
		} catch (Exception e) {
			// TODO: handle exception
		}
        
        if(ListIcon!=null){
        	for(int i=0;i<ListIcon.size();i++){
        		User user = userimp.UserFindByID(ListIcon.get(i));
        		IConName = new JSONObject();
        		IConName.put("icon", user.getUserIcon());
        		IConName.put("name", user.getUserName());
        		ListICon.add(IConName);
        	}
        }
        objData.put("msg", "success");
        objData.put("code", 200);
        objData.put("num", num);
        objData.put("result",ListICon);
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
