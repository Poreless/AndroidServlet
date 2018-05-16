package com.wzu.server;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wzu.db.TagDaoImp;

/**
 * Servlet implementation class TagServlet
 */
@WebServlet("/TagServlet")
public class TagServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("���ʱ�ǩ�������");
	    JSONArray tagjson;  //����Ҫ��ֵ
	    JSONObject tagname;
		TagDaoImp tagdaoimp = new TagDaoImp();
		request.setCharacterEncoding("utf-8");
		String userID = request.getParameter("uid");
		String taglist = request.getParameter("taglistjson");
		JSONArray json = JSONArray.parseArray(taglist);
        //�����������ʾΪutf-8
        response.setHeader("Content-type", "text/html;charset=UTF-8");
		if(json.size()>0){  
			  for(int i=0;i<json.size();i++){  
			 // ���� jsonarray ���飬��ÿһ������ת�� json ����  
				  JSONObject tag = json.getJSONObject(i);   
				  System.out.println(tag.get("name")); 
				  tagdaoimp.TagCreate(userID, tag.get("name").toString());
			  }
		}		
		 tagjson = new JSONArray();
		 ArrayList<String> sel = tagdaoimp.TagFindByID(userID);
         try {
             for(int i=0;i<sel.size();i++){
            	 tagname = new JSONObject();
                 tagname.put("name",sel.get(i));
                 tagjson.add(tagname);
             }

         } catch (Exception e) {
             e.printStackTrace();
         }
       // System.out.println(json.toString());
        JSONObject objData = new JSONObject();
        objData.put("msg", "success");
        objData.put("code", 200);
        //Object returnjson= JSONObject.toJSON(tagjson);
        objData.put("result", tagjson.toJSONString());
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
