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
import com.wzu.db.CommentDaoImp;
import com.wzu.model.Active;
/**
 * 用于写评价页，参与的活动中已结束的活动信息
 * @author Aspiration
 *
 */
@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ActiveDaoImp activedaoimp = new ActiveDaoImp();
		CommentDaoImp commentdaoimp = new CommentDaoImp();
		ActiveRequestDaoImp requestdaoimp = new ActiveRequestDaoImp();
		ArrayList<String> ActIDList = new ArrayList();
		ArrayList<Active> actlist = new ArrayList();
		String userID = request.getParameter("uid");
		Active active;
		System.out.println("userID:"+userID);
		JSONObject objData = new JSONObject();
		Map map = new HashMap();
		if(userID!=null){
	        try {
	        	ActIDList = requestdaoimp.ReqSuccessByUserID(userID);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
        //在浏览器中显示为utf-8
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        if(ActIDList!=null){
            for(int i=0;i<ActIDList.size();i++){
            	active = activedaoimp.FindActiveByID(ActIDList.get(i));
                if(active.getFinish_time()!=null){  //活动已结束且未评价
                	if(commentdaoimp.FindComment(userID, ActIDList.get(i))==false){
                		actlist.add(active);
                	}
                	
                }
            }
            map.put("active_finish_info", actlist);
            objData.put("msg", "success");
            objData.put("code", 200);
            Object json = JSONObject.toJSON(map);
            objData.put("result", json);
            response.getOutputStream().write(objData.toString().getBytes("utf-8"));
        }
        else{
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
