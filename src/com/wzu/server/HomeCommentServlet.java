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
import com.wzu.db.CommentDaoImp;
import com.wzu.db.UserDaoImp;
import com.wzu.model.Active;
import com.wzu.model.Comment;
import com.wzu.model.CommentModel;
/**
 * �����Ǹ�����ҳչʾ���۵�����
 * @author Aspiration
 *
 */
@WebServlet("/HomeCommentServlet")
public class HomeCommentServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ActiveDaoImp activedaoimp = new ActiveDaoImp();
		UserDaoImp userdaoimp = new UserDaoImp();
		CommentDaoImp commdaoimp = new CommentDaoImp();
		ArrayList<Active> actlist = new ArrayList();
		ArrayList<Comment> commentlist = new ArrayList();
		//ArrayList<CommentModel> ModelList = new ArrayList();
		String userID = request.getParameter("uid");
		
		System.out.println("userID:"+userID);
		JSONObject objData = new JSONObject();
		Map map = new HashMap();
		if(userID!=null){
	        actlist = activedaoimp.FindFinishByUserID(userID);
	        System.out.println(actlist.size());
		}
        //�����������ʾΪutf-8
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        if(actlist!= null)
        {
            //map.put("request_active_info", );
          /*  for(int i=0;i<actlist.size();i++){      	
      	//���ж�ȡ�ѽ����Ļ
            	if(actlist.get(i).getFinish_time()==null){
            		
            		actlist.remove(i);
            	}
            	
            }*/
            map.put("active_comment",actlist);
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
