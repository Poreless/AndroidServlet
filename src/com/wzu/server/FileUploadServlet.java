package com.wzu.server;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wzu.db.TagDaoImp;
import com.wzu.db.UserDaoImp;
import com.wzu.model.User;

/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/FileUploadServlet")
public class FileUploadServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding( "utf-8" );
		UserDaoImp userdaoimp = new UserDaoImp();
		TagDaoImp tagdaoimp = new TagDaoImp();  //��Ҫ����Ȥ��ѯ
		String userID = URLDecoder.decode(request.getHeader("id"), "UTF-8");
		String userName = URLDecoder.decode(request.getHeader("name"), "UTF-8");
		String userSex =  URLDecoder.decode(request.getHeader("sex"), "UTF-8");
		System.out.println("����ͼƬ�����servlet"+"\n"+"ID:"+userID+"\n"+"�������ƣ�"+userName+"\n"
				+"�����Ա�"+userSex+"\n");
        User user = userdaoimp.UserFindByID(userID);
        //����Ҫ�漰��Ȥ���õ��޸ģ���ѯ��Ϊ�޸��Ժ�
        JSONObject objData;
	    JSONArray tagjson;  //����Ҫ��ֵ
	    JSONObject tagname;
        tagjson = new JSONArray();
		 	ArrayList<String> sel = tagdaoimp.TagFindByID(userID);
        try {
            for(int i=0;i<sel.size();i++){
           	 tagname = new JSONObject();
                tagname.put("name",sel.get(i));
                tagjson.add(tagname);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //�ļ�����
		response.setHeader("Content-type", "text/html;charset=UTF-8");
        //��ô����ļ���Ŀ����    
        DiskFileItemFactory factory = new DiskFileItemFactory();    
        //��ȡ�ļ���Ҫ�ϴ�����·��    
        //String path = request.getRealPath("/upload");    
        String path = this.getServletContext().getRealPath("/user_icon");
        System.out.println(path);
        if(user.getUserIcon()!=null){
        	File file = new File(path+user.getUserIcon());
        	   if (!file.exists()) {
                   System.out.println("�ļ������ڣ����޸��ļ�·��.");
                   return;
               }else{
               	if (file.delete()) {  
                    System.out.println(file.getName() + "is deleted");  
                  } else {  
                    System.out.println("Delete failed.");  
                  }  
               }     
        }
        File file=new File(path);  
        if(!file.exists()){  
            file.mkdirs();  
        }  
        factory.setRepository(new File(path));    
        //���� ����Ĵ�С  
        factory.setSizeThreshold(1024*1024) ;    
        //�ļ��ϴ�����    
        ServletFileUpload upload = new ServletFileUpload(factory);    
        try {    
            //�����ϴ�����ļ�    
            List<FileItem> list = (List<FileItem>)upload.parseRequest(request);    
            for(FileItem item : list){    
                //��ȡ��������    
                String name = item.getFieldName();    
                //�����ȡ�� ����Ϣ����ͨ�� �ı� ��Ϣ    
                if(item.isFormField()){                       
                    //��ȡ�û�����������ַ���,��Ϊ���ύ�������� �ַ������͵�    
                    String value = item.getString() ;    
                    request.setAttribute(name, value);    
                }else{    
                    //��ȡ·����    
                    String value = item.getName() ;    
                    //���������һ����б��    
                    int start = value.lastIndexOf("\\");    
                    //��ȡ �ϴ��ļ��� �ַ������֣���1�� ȥ����б�ܣ�    
                    String filename = value.substring(start+1);    
                    request.setAttribute(name, filename);    
                    //д��������    
                    item.write( new File(path,filename) );//�������ṩ��    
                    System.out.println("�ϴ��ɹ���"+filename); 
                    if(userID!=null&&userName!=null&&userSex!=null){
                    	if(userdaoimp.MessageSetByID(userID, "/"+filename, userSex, userName)==true){
                    		User userupdate = userdaoimp.UserFindByID(userID);
                            objData = new JSONObject();
                            Map map = new HashMap();
                            objData.put("code", 200);
                            objData.put("msg", "success");    
                            objData.put("tag", tagjson.toJSONString());
                        	map.put("user_info", userupdate);
                            Object json = JSONObject.toJSON(map);
                            objData.put("result", json);
                            response.getOutputStream().write(objData.toString().getBytes("utf-8"));
                    	}else{
                    		objData = new JSONObject();
                            objData.put("msg", "error");
                            objData.put("code", 400);
                            Object json = JSONObject.toJSON(new User());
                            objData.put("user_info", json);
                            response.getOutputStream().write(objData.toString().getBytes("utf-8"));
                    	}
                    }else{
                    	objData = new JSONObject();
                        objData.put("msg", "error");
                        objData.put("code", 400);
                        Object json = JSONObject.toJSON(new User());
                        objData.put("user_info", json);
                        response.getOutputStream().write(objData.toString().getBytes("utf-8"));
                    }
     

                }    
            }    
                
        } catch (Exception e) {    
            System.out.println("�ϴ�ʧ��");  
            response.getWriter().print("�ϴ�ʧ�ܣ�"+e.getMessage());  
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
