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

/**用于显示好友主页
 * Servlet implementation class FriendMessage
 */
@WebServlet("/FriendMessage")
public class FriendMessageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("访问FriendMessageServlet，根据好友ID做好友信息查询===" );
		UserDaoImp userDaoImp = new UserDaoImp();
		TagDaoImp tagdaoimp = new TagDaoImp();  //需要做兴趣查询
		ActiveDaoImp activedaoimp = new ActiveDaoImp();
		CommentDaoImp commdaoimp = new CommentDaoImp();
		ArrayList<Active> actlist;
		ArrayList<Comment> commentlist = new ArrayList();
		
		String friendID = request.getParameter("fid");
		System.out.println("待查询的好友ID为:"+ friendID );
		
		User user = userDaoImp.UserFindByID(friendID);
		
		JSONArray tagjson;  //不需要键值
		JSONObject tagname;
		JSONObject objData = new JSONObject();
		Map map = new HashMap();
		
		if(friendID!=null){
		    //先做兴趣查询
					tagjson = new JSONArray();
					ArrayList<String> sel = tagdaoimp.TagFindByID(friendID);
					 try {
			          for(int i=0;i<sel.size();i++){
			         	 tagname = new JSONObject();
			              tagname.put("name",sel.get(i));  //形成有键的单元对象
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
        //在浏览器中显示为utf-8
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        if(actlist!= null)
        {
            //map.put("request_active_info", );
            for(int i=0;i<actlist.size();i++){
            	//从中读取已结束的活动
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
