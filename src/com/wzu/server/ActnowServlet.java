package com.wzu.server;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wzu.db.ActiveDaoImp;
import com.wzu.db.ActiveRequestDaoImp;
import com.wzu.db.UserDaoImp;
import com.wzu.model.Active;
import com.wzu.model.User;

/**
 * 显示当前正在参与
 */
@WebServlet("/ActnowServlet")
public class ActnowServlet extends HttpServlet {

	@SuppressWarnings({ "unused", "null" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ActiveDaoImp activedaoimp = new ActiveDaoImp();
		ActiveRequestDaoImp requestdaoimp = new ActiveRequestDaoImp();
		//UserDaoImp userdaoimp = new UserDaoImp();
		String userID = request.getParameter("uid");
		ArrayList<String> liststr = new ArrayList<String>();
		ArrayList<Active> actlistmine = null;
		ArrayList<Active> actlist = null;
		JSONArray actjson =  new JSONArray();;  //储存每一个信息单元
		JSONObject message = new JSONObject();
		ArrayList listobj = new ArrayList();
		Active active;
	    JSONObject actarray = null;  //信息单元
	    
	    response.setHeader("Content-type", "text/html;charset=UTF-8");
		if(userID!=null){
	        try {
	        	actlistmine = new ArrayList<Active>();
	        	actlistmine = activedaoimp.FindFreshByUserID(userID);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		if(actlistmine!=null){
			for(int i=0;i<actlistmine.size();i++){
				actarray = new JSONObject();
				actarray.put("act", actlistmine.get(i));
				//查询当前人数
				actarray.put("num", requestdaoimp.RequestSuccessNum(actlistmine.get(i).getActive_id()+""));
				listobj.add(actarray);
				System.out.println("添加创建");
			}
		}
		
		if(userID!=null){
	        try {
	        	actlist = new ArrayList<Active>();
	        	liststr = requestdaoimp.ReqSuccessByUserID(userID);
	        	if(liststr!=null){
	        		for(int i=0;i<liststr.size();i++){
	        			active= activedaoimp.FindActiveByID(liststr.get(i));
	        			if(active.getFinish_time()==null)
	        				actlist.add(active);
	        		}
	        	}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		if(actlist!=null){
			for(int i=0;i<actlist.size();i++){
				actarray = new JSONObject();
				actarray.put("act", actlist.get(i));
				//查询当前人数
				actarray.put("num", requestdaoimp.RequestSuccessNum(actlist.get(i).getActive_id()+""));
				listobj.add(actarray);
				System.out.println("添加申请");
			}
		}
		if(actjson!=null){
			message.put("code", 200);
			message.put("msg", "success");  
			message.put("result", listobj);
		}else{
			message.put("code", 300);
			message.put("msg", "fail");  
		}
		  response.getOutputStream().write(message.toString().getBytes("utf-8")); 


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
