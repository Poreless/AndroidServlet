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
import com.wzu.db.ActiveRequestDaoImp;
import com.wzu.db.ExtendDaoImp;
import com.wzu.db.UserDaoImp;
import com.wzu.model.Active;
import com.wzu.model.ActiveRequest;
import com.wzu.model.ModelRequest;
import com.wzu.model.User;

/**显示请求的servlet，使用jsonobject可以精简代码，这里过多使用构造的类
 * Servlet implementation class MessageServlet
 */
public class MessageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 	ActiveDaoImp activedaoimp = new ActiveDaoImp();
		 	UserDaoImp userdaoimp = new UserDaoImp();
		 	ArrayList<Active> actlist = new ArrayList();  //返回信息列表
		 	ArrayList<ModelRequest> mrlist;   //申请表，取userid足够
		 	ArrayList<ActiveRequest> arlist = new ArrayList();
		 	ActiveRequest ar;
	        ActiveRequestDaoImp activerequestdaoimp = new ActiveRequestDaoImp();
	        String userID = request.getParameter("uid");
	        System.out.println("userID:"+userID);
	        ArrayList<Active> ListActive = new ArrayList();
	        Map map = new HashMap();
	        String actid;
	        JSONObject objData = new JSONObject();
	        try {
	        	actlist = activedaoimp.FindActiveByUserID(userID);
			} catch (Exception e) {
				// TODO: handle exception
			}
	        	
	        //在浏览器中显示为utf-8
	        response.setHeader("Content-type", "text/html;charset=UTF-8");
	        if(actlist!= null)
	        {
	            map.put("active_info", actlist);
	            //map.put("request_active_info", );
	            for(int i=0;i<actlist.size();i++){
	            	actid = actlist.get(i).getActive_id()+"";
	            	mrlist = activerequestdaoimp.RequestByActID(actid);
	            	for(int j=0;j<mrlist.size();j++){
	            		ar = new ActiveRequest();
	            		ar.setUser(userdaoimp.UserFindByID(mrlist.get(j).getUser_id()));
	            		ar.setActive_id(actid);
	            		ar.setActive_name(activedaoimp.FindActiveByID(mrlist.get(j).getActive_id()).getActive_name());
	            		ar.setDate(mrlist.get(j).getDate());
	            		ar.setNum(mrlist.get(j).getNum());       		
	            		arlist.add(ar);
	            	}
	            }
	            map.put("request_act_info", arlist);
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
