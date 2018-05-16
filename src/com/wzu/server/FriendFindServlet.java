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

/**���ڲ�ѯ������ʶ���˵�Servlet
 * ����ɹ��һ������������߱����룬���ڻ��ϵ
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
		String userID = request.getParameter("uid");  //���������Ϣ
		
		UserDaoImp userdaoimp = new UserDaoImp();  //tͨ��id��ȡ�����û���Ϣ
		ActiveRequestDaoImp requestdaoimp = new ActiveRequestDaoImp();  //��ѯ���˼���Ļ���Լ��뱾�˻�й�������
		ActiveDaoImp activedaoimp = new ActiveDaoImp();  //��ѯ���˴����Ļ
		
		actIDlist = requestdaoimp.ReqSuccessByUserID(userID);   //����ɹ��Ļ�б�
		if(actIDlist!=null){
			for(int i=0;i<actIDlist.size();i++){
				if(activedaoimp.FindActiveByID(actIDlist.get(i)).getFinish_time()!=null){
					userIDlist = new ArrayList();
					userIDlist = requestdaoimp.ReqSuccessByActID(actIDlist.get(i));  //�����Ĳ�����   
						for(int j =0;j<userIDlist.size();j++){
							System.out.println(userIDlist.get(j));
							if(userIDlist.get(j).equals(userID)){
								userIDlist.remove(j);  //��ȥ�Լ���һ����Ϊ��
							}
						}
						//���ϻ������
						System.out.println("�����ߣ�"+activedaoimp.FindActiveByID(actIDlist.get(i)).getUser().getUserID());
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
		
		actlist = activedaoimp.FindActiveByUserID(userID);   //������
		if(actlist!=null){
			for(int i=0;i<actlist.size();i++){
				if(actlist.get(i).getFinish_time()!=null){   //��ѽ���
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
		for(int i =0;i<userIDlistAll.size()-1;i++){       //ȥ���ظ����㷨��ʱ�临�Ӷȴ� 
         for  (int j  =userIDlistAll.size() - 1 ; j > i; j -- ) {       
              if  (userIDlistAll.get(j).equals(userIDlistAll.get(i)))  {       
            	  userIDlistAll.remove(j);       
               }        
           }        
		}
		//��ӡ������
		for(int i=0;i<userIDlistAll.size();i++){
			User user = userdaoimp.UserFindByID(userIDlistAll.get(i).toString());
			userlist.add(user);
		}
        //�����������ʾΪutf-8
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
