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
import com.wzu.db.UserDaoImp;
import com.wzu.model.User;

/**用于处理用户活动创建请求的Servlet
 * Servlet implementation class AddActServlet
 */
@WebServlet("/AddActServlet")
public class AddActServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
    	
		JSONObject objData = new JSONObject();
		Map map = new HashMap();
		
        ActiveDaoImp activeDaoImp = new ActiveDaoImp();
        String addactjson = null;
        try {
            addactjson = request.getParameter("addactjson");
		} catch (Exception e) {
			// TODO: handle exception
		}

        response.setHeader("Content-type", "text/html;charset=UTF-8");
        System.out.println("addactjson:"+addactjson);
        //解析传输过来的json字符串
        JSONObject json = JSONObject.parseObject(addactjson);  
        System.out.println(JSONObject.toJSONString(json, true));
        boolean key = activeDaoImp.ActiveCreate(json.getString("user_id"), json.getString("actadd_title"),json.getString("actaddr_theme"),json.getString("actadd_place"),json.getString("actadd_artdcl"), json.getString("actaddr_time"),json.getString("actaddr_usernum"));
        if(key==true){
            objData.put("msg", "success");
            objData.put("code", 200);
            Object returnjson= JSONObject.toJSON(map);
            objData.put("result", returnjson);
        }else{
            objData.put("msg", "error");
            //objData.put("code", 400);
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
