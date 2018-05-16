package com.wzu.server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.wzu.db.ActiveDaoImp;
import com.wzu.db.ActiveRequestDaoImp;

/**�����û��������˵Ļ��Servlet(������)
 * Servlet implementation class RequestServlet
 */
public class RequestServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ActiveRequestDaoImp actreqdaoimp= new ActiveRequestDaoImp();
		request.setCharacterEncoding("utf-8");

		ActiveDaoImp activedaoimp = new ActiveDaoImp();
		String user_id =request.getParameter("uid");
		String active_id = request.getParameter("aid");
		System.out.println("user_id:"+user_id+"\n"+"active_id:"+active_id);
		JSONObject objData = new JSONObject();
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		 if(user_id!=null&&active_id!=null){
			if(actreqdaoimp.RequestSuccessNum(active_id)   //�ж������Ƿ񳬶�
					<activedaoimp.FindActiveByID(active_id).getMaxnum()){
				if(actreqdaoimp.AddActiveRequest(user_id, active_id)==true){
					objData.put("code", 200);
		            objData.put("msg", "success");
		            objData.put("log", "����ɹ��������ĵȴ�"); 
				}else{
					objData.put("code", 400);
		            objData.put("msg", "fail"); 
		            objData.put("log", "����������������ٴ����룡��"); 
				}
			}else{
				objData.put("code", 400);
	            objData.put("msg", "fail"); 
	            objData.put("log", "������������"); 
			}
		 }
		 response.getOutputStream().write(objData.toString().getBytes("utf-8"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        System.out.println("doPost");
        doGet(request, response);
	}

}
