package com.wzu.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.wzu.db.ActiveDaoImp;
import com.wzu.model.Active;
import com.wzu.model.ActiveRequest;

/**
 * 显示用户创建的活动的servlet
 * @author Aspiration
 *
 */
public class ActchangeServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ActiveDaoImp activedaoimp = new ActiveDaoImp();
		ArrayList<Active> actlist = new ArrayList();
		ArrayList<Active> actlist_fresh = new ArrayList();
		String userID = request.getParameter("uid");
		
		System.out.println("userID:"+userID);
		JSONObject objData = new JSONObject();
		Map map = new HashMap();
		if(userID!=null){
	        try {
	        	actlist = activedaoimp.FindActiveByUserID(userID);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
        //在浏览器中显示为utf-8
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        if(actlist!= null)
        {
            //map.put("request_active_info", );
            for(int i=0;i<actlist.size();i++){
            	//从中读取还没结束的活动
            	if(actlist.get(i).getFinish_time()==null){
            		actlist_fresh.add(actlist.get(i));
            	}
            }
            map.put("fresh_list",actlist_fresh);
            objData.put("msg", "success");
            objData.put("code", 200);
            Object json = JSONObject.toJSON(map);
            objData.put("result", json);
            response.getOutputStream().write(objData.toString().getBytes("utf-8"));
        } else
        {
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
