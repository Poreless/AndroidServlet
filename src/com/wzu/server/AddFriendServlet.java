package com.wzu.server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.wzu.db.FriendDaoImp;

/**
 * Servlet implementation class AddFriendServlet
 */
@WebServlet("/AddFriendServlet")
public class AddFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFriendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		FriendDaoImp frienddaoimp = new FriendDaoImp();
		String friendID = request.getParameter("fid");
		String userID = request.getParameter("uid");
		System.out.println("friendID:"+ friendID );
		System.out.println("userID:"+ userID );
		JSONObject objData = new JSONObject();
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		if(frienddaoimp.ListFriendRequest(userID, friendID).size()==0){ //���ݿ���Ψһ��Լ���������ʱ��Ҫ��֤
		if(friendID!=null&&userID!=null){
			if(frienddaoimp.addfriend(userID, friendID)==true){
				objData.put("code", 200);
	            objData.put("msg", "success");
	            objData.put("log", "��ӳɹ�");
			}
			else{  //���ݿ����ʧ��
				objData.put("code", 300);
	            objData.put("msg", "fail"); 
	            objData.put("log", "�������");
			}
		}else{//������
			objData.put("code", 400);
            objData.put("msg", "fail");
            objData.put("log", "���ݶ�ʧ");
		}
		}else{
			objData.put("code", 500);
            objData.put("msg", "fail");
            objData.put("log", "���ظ���ӣ������ĵȴ�^_^");
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
