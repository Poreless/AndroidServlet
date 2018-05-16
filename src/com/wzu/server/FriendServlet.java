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
import com.wzu.db.ExtendDaoImp;
import com.wzu.db.FriendDaoImp;
import com.wzu.db.UserDaoImp;
import com.wzu.model.Active;
import com.wzu.model.FriendListInfo;

/**
 * Servlet implementation class FriendServlet
 */
public class FriendServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        FriendDaoImp frienddaoimp = new FriendDaoImp();
        String uid =request.getParameter("uid");
        FriendListInfo friendlistinfo;
        UserDaoImp userdaoimp = new UserDaoImp();
        ArrayList<String> ListString  = new ArrayList();
        ArrayList<FriendListInfo> ListFriend = new ArrayList();
        Map map = new HashMap();
        JSONObject objData = new JSONObject();
        ListString=frienddaoimp.FindReqFriendListByID(uid);
        //在浏览器中显示为utf-8
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        System.out.println();
        if(!ListString.isEmpty())
        	
        {
        	objData.put("code", 200);
            objData.put("msg", "success");
        	for(int i=0;i<ListString.size();i++){
        		friendlistinfo = new FriendListInfo();
        		friendlistinfo.setFid(ListString.get(i));
        		friendlistinfo.setImageUrl(userdaoimp.UserFindByID(ListString.get(i)).getUserIcon());
        		friendlistinfo.setFriendname(userdaoimp.UserFindByID(ListString.get(i)).getUserName());
        		friendlistinfo.setNum(1);   //请求成功的
        		ListFriend.add(friendlistinfo);

        	}
    		map.put("friend_info",ListFriend);
            Object json = JSONObject.toJSON(map);
            objData.put("result", json);
            System.out.println(objData.toString());
            response.getOutputStream().write(objData.toString().getBytes("utf-8"));
        } else
        {
            objData.put("msg", "error");
            objData.put("code", 400);
    		map.put("friend_info",ListFriend);
            Object json = JSONObject.toJSON(map);
            objData.put("result", json);
            response.getOutputStream().write(objData.toString().getBytes("utf-8"));
        }
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
