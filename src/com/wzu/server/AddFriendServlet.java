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
		if(frienddaoimp.ListFriendRequest(userID, friendID).size()==0){ //数据库无唯一性约束，则插入时需要验证
		if(friendID!=null&&userID!=null){
			if(frienddaoimp.addfriend(userID, friendID)==true){
				objData.put("code", 200);
	            objData.put("msg", "success");
	            objData.put("log", "添加成功");
			}
			else{  //数据库插入失误
				objData.put("code", 300);
	            objData.put("msg", "fail"); 
	            objData.put("log", "处理错误");
			}
		}else{//空数据
			objData.put("code", 400);
            objData.put("msg", "fail");
            objData.put("log", "数据丢失");
		}
		}else{
			objData.put("code", 500);
            objData.put("msg", "fail");
            objData.put("log", "勿重复添加，请耐心等待^_^");
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
