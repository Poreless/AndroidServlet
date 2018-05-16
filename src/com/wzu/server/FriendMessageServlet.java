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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wzu.db.ActiveDaoImp;
import com.wzu.db.CommentDaoImp;
import com.wzu.db.TagDaoImp;
import com.wzu.db.UserDaoImp;
import com.wzu.model.Active;
import com.wzu.model.Comment;
import com.wzu.model.User;

/**������ʾ������ҳ
 * Servlet implementation class FriendMessage
 */
@WebServlet("/FriendMessage")
public class FriendMessageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("����FriendMessageServlet�����ݺ���ID��������Ϣ��ѯ===" );
		UserDaoImp userDaoImp = new UserDaoImp();
		TagDaoImp tagdaoimp = new TagDaoImp();  //��Ҫ����Ȥ��ѯ
		ActiveDaoImp activedaoimp = new ActiveDaoImp();
		CommentDaoImp commdaoimp = new CommentDaoImp();
		ArrayList<Active> actlist;
		ArrayList<Comment> commentlist = new ArrayList();
		
		String friendID = request.getParameter("fid");
		System.out.println("����ѯ�ĺ���IDΪ:"+ friendID );
		
		User user = userDaoImp.UserFindByID(friendID);
		
		JSONArray tagjson;  //����Ҫ��ֵ
		JSONObject tagname;
		JSONObject objData = new JSONObject();
		Map map = new HashMap();
		
		if(friendID!=null){
		    //������Ȥ��ѯ
					tagjson = new JSONArray();
					ArrayList<String> sel = tagdaoimp.TagFindByID(friendID);
					 try {
			          for(int i=0;i<sel.size();i++){
			         	 tagname = new JSONObject();
			              tagname.put("name",sel.get(i));  //�γ��м��ĵ�Ԫ����
			              tagjson.add(tagname);
			              tagname = null;
			          }
			          
			      } catch (Exception e) {
			          e.printStackTrace();
			      }
		      objData.put("tag", tagjson.toJSONString());
		      tagjson=null;
			}
		actlist = new ArrayList();
		if(friendID!=null){
	        try {
	        	
	        	actlist = activedaoimp.FindActiveByUserID(friendID);
	        	
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
        //�����������ʾΪutf-8
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        if(actlist!= null)
        {
            //map.put("request_active_info", );
            for(int i=0;i<actlist.size();i++){
            	//���ж�ȡ�ѽ����Ļ
            	if(actlist.get(i).getFinish_time()==null){
            		actlist.remove(i);
            	}
            }
            map.put("active_comment",actlist);
            map.put("friend_info", user);
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
