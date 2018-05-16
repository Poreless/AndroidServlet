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
import com.wzu.db.UserDaoImp;
import com.wzu.model.Active;
import com.wzu.model.User;

/**用于查询可能认识的人的Servlet
 * 申请成功且活动结束，加入或者被加入，存在活动联系
 * Servlet implementation class FriendFindServlet
 */
@WebServlet("/FriendFindServlet")
public class FriendFindServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<User> userlist = new ArrayList();
		ArrayList<String> userIDlist = new ArrayList();
		ArrayList<String> userIDlistAll = new ArrayList();
		ArrayList<String> userIDlistAll2 = new ArrayList();
		ArrayList<String> actIDlist = new ArrayList();
		ArrayList<Active> actlist = new ArrayList();
		String userID = request.getParameter("uid");  //本人身份信息
		
		UserDaoImp userdaoimp = new UserDaoImp();  //t通过id获取完整用户信息
		ActiveRequestDaoImp requestdaoimp = new ActiveRequestDaoImp();  //查询本人加入的活动，以及与本人活动有关联的人
		ActiveDaoImp activedaoimp = new ActiveDaoImp();  //查询本人创建的活动
		
		actIDlist = requestdaoimp.ReqSuccessByUserID(userID);   //申请成功的活动列表
		if(actIDlist!=null){
			for(int i=0;i<actIDlist.size();i++){
				if(activedaoimp.FindActiveByID(actIDlist.get(i)).getFinish_time()!=null){
					userIDlist = new ArrayList();
					userIDlist = requestdaoimp.ReqSuccessByActID(actIDlist.get(i));  //其他的参与者   
						for(int j =0;j<userIDlist.size();j++){
							System.out.println(userIDlist.get(j));
							if(userIDlist.get(j).equals(userID)){
								userIDlist.remove(j);  //除去自己，一定不为空
							}
						}
						//加上活动创建者
						System.out.println("创建者："+activedaoimp.FindActiveByID(actIDlist.get(i)).getUser().getUserID());
						userIDlist.add(activedaoimp.FindActiveByID(actIDlist.get(i)).getUser().getUserID());
						if(userIDlist!=null){							
				         for(String str:userIDlist) {
				        	 userIDlistAll.add(str);
				         	}
						}
							//AddList(userIDlist,userIDlistAll);
					
					
				}
			}
		}
		
		actlist = activedaoimp.FindActiveByUserID(userID);   //创建的
		if(actlist!=null){
			for(int i=0;i<actlist.size();i++){
				if(actlist.get(i).getFinish_time()!=null){   //活动已结束
					userIDlist = requestdaoimp.ReqSuccessByActID(actlist.get(i).getActive_id()+"");
					if(userIDlist!=null){
				   for(String str:userIDlist) {
			        	 userIDlistAll.add(str);
			         	}
					}
						//AddList(userIDlist,userIDlistAll2);
				}
			}
		}
		
		
		


/*		for(int i=0;i<userIDlistAll2.size();i++){
			User user = userdaoimp.UserFindByID(userIDlistAll2.get(i).toString());
			userlist.add(user);
		}*/
		for(int i =0;i<userIDlistAll.size()-1;i++){       //去除重复的算法，时间复杂度大 
         for  (int j  =userIDlistAll.size() - 1 ; j > i; j -- ) {       
              if  (userIDlistAll.get(j).equals(userIDlistAll.get(i)))  {       
            	  userIDlistAll.remove(j);       
               }        
           }        
		}
		//打印，测试
		for(int i=0;i<userIDlistAll.size();i++){
			User user = userdaoimp.UserFindByID(userIDlistAll.get(i).toString());
			userlist.add(user);
		}
        //在浏览器中显示为utf-8
        response.setHeader("Content-type", "text/html;charset=UTF-8");
		JSONObject objData = new JSONObject();
		Map map = new HashMap();
		map.put("user_info", userlist);
        objData.put("msg", "success");
        objData.put("code", 200);
        Object json = JSONObject.toJSON(map);
        objData.put("result", json);
        response.getOutputStream().write(objData.toString().getBytes("utf-8"));
	}


	private void AddList(ArrayList<String> userIDlist, ArrayList<String> userIDlistAll) {
		// TODO Auto-generated method stub
		int key = 0;
		for(int i=0;i<userIDlist.size();i++){
			if(userIDlistAll!=null){
				for(int j=0;j<userIDlistAll.size();j++){
					if(userIDlist.get(i).equals(userIDlistAll.get(j))){
						key =1;
					}
				}
				if(key==0){
					userIDlistAll.add(userIDlist.get(i));
				}
			}
			else{
				userIDlistAll.add(userIDlist.get(i));
			}
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
