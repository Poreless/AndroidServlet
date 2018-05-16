package com.wzu.server;

import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wzu.db.UserDaoImp;
import com.wzu.model.Active;
import com.wzu.model.User;

import javassist.compiler.ast.Keyword;

public class RegistServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    		request.setCharacterEncoding("utf-8");
    	
    		//创建返回的用户数据
    		User user = new User();
    		JSONObject objData = new JSONObject();
    		Map map = new HashMap();
    		
            UserDaoImp userDaoImp = new UserDaoImp();
            String registjson = request.getParameter("registjson" );
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            System.out.println("registjson:"+registjson);
            //解析传输过来的json字符串
            JSONObject json = JSONObject.parseObject(registjson);  
            System.out.println(JSONObject.toJSONString(json, true));
            
            
            System.out.println("学号："+json.getString("idnumber")+"\n"+"电话号码："+json.getString("phonenumber")+"\n"+
            		"姓名："+json.getString("registname")+"\n"+"电话号码："+json.getString("registpwd"));
            String callback = userDaoImp.UserCreate(json.getString("idnumber"), json.getString("phonenumber"), json.getString("registname"), json.getString("registpwd"),json.getString("school"));
            if(callback.equals("success")){
            	userDaoImp.CreateLoginTime(json.getString("idnumber"));
            	user.setUserID(json.getString("idnumber"));
           		user = userDaoImp.UserFindByID(json.getString("idnumber"));
/*            	user.setUserName(json.getString("registname"));
            	user.setUserPwd(json.getString("registpwd"));
            	user.setUserTel(json.getString("phonenumber"));*/
            	map.put("user_info", user);
                objData.put("msg", "success");
                objData.put("code", 200);
                Object returnjson= JSONObject.toJSON(map);
                objData.put("result", returnjson);
            }else{
                objData.put("msg", "error");
                objData.put("code", 400);
                objData.put("erro", callback);
            }
            System.out.println(objData.toString()); 
            response.getOutputStream().write(objData.toString().getBytes("utf-8"));

        }

        public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
        {
            System.out.println("doPost");
            doGet(request, response);
        }

}
