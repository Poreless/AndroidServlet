package com.wzu.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.wzu.db.ActiveDaoImp;

/**
 * 用于修改活动信息的Servlet
 */
@WebServlet("/ChangeActServlet")
public class ChangeActServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				request.setCharacterEncoding("utf-8");
		    	
				JSONObject objData = new JSONObject();
				Map map = new HashMap();
				
		        ActiveDaoImp activeDaoImp = new ActiveDaoImp();
		        String actchangejson = null;
		        try {
		        	actchangejson = request.getParameter("actchangejson");
				} catch (Exception e) {
					// TODO: handle exception
				}
		        if(actchangejson!=null){
			        response.setHeader("Content-type", "text/html;charset=UTF-8");
			        System.out.println("addactjson:"+actchangejson);
			        //解析传输过来的json字符串
			        JSONObject json = JSONObject.parseObject(actchangejson);  
			        System.out.println(JSONObject.toJSONString(json, true));
			        if(activeDaoImp.ActChangeByID(json.getString("active_id"), 
			        		json.getString("actchange_title"),json.getString("actchange_theme"),
			        		json.getString("actchange_place"),json.getString("actchange_artdcl"), 
			        		json.getString("actchange_time"),json.getString("actchange_usernum"))){
			            objData.put("msg", "success");
			            objData.put("code", 200);
			        }else{
			            objData.put("msg", "error");
			            //objData.put("code", 400);
			        }
		        }
		        System.out.println(objData.toString()); 
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
