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
		TagDaoImp tagdaoimp = new TagDaoImp();  //需要做兴趣查询
		String userID = URLDecoder.decode(request.getHeader("id"), "UTF-8");
		String userName = URLDecoder.decode(request.getHeader("name"), "UTF-8");
		String userSex =  URLDecoder.decode(request.getHeader("sex"), "UTF-8");
		System.out.println("进入图片处理的servlet"+"\n"+"ID:"+userID+"\n"+"更新名称："+userName+"\n"
				+"更新性别："+userSex+"\n");
        User user = userdaoimp.UserFindByID(userID);
        //若需要涉及兴趣爱好的修改，查询需为修改以后
        JSONObject objData;
	    JSONArray tagjson;  //不需要键值
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
        //文件操作
		response.setHeader("Content-type", "text/html;charset=UTF-8");
        //获得磁盘文件条目工厂    
        DiskFileItemFactory factory = new DiskFileItemFactory();    
        //获取文件需要上传到的路径    
        //String path = request.getRealPath("/upload");    
        String path = this.getServletContext().getRealPath("/user_icon");
        System.out.println(path);
        if(user.getUserIcon()!=null){
        	File file = new File(path+user.getUserIcon());
        	   if (!file.exists()) {
                   System.out.println("文件不存在，请修改文件路径.");
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
        //设置 缓存的大小  
        factory.setSizeThreshold(1024*1024) ;    
        //文件上传处理    
        ServletFileUpload upload = new ServletFileUpload(factory);    
        try {    
            //可以上传多个文件    
            List<FileItem> list = (List<FileItem>)upload.parseRequest(request);    
            for(FileItem item : list){    
                //获取属性名字    
                String name = item.getFieldName();    
                //如果获取的 表单信息是普通的 文本 信息    
                if(item.isFormField()){                       
                    //获取用户具体输入的字符串,因为表单提交过来的是 字符串类型的    
                    String value = item.getString() ;    
                    request.setAttribute(name, value);    
                }else{    
                    //获取路径名    
                    String value = item.getName() ;    
                    //索引到最后一个反斜杠    
                    int start = value.lastIndexOf("\\");    
                    //截取 上传文件的 字符串名字，加1是 去掉反斜杠，    
                    String filename = value.substring(start+1);    
                    request.setAttribute(name, filename);    
                    //写到磁盘上    
                    item.write( new File(path,filename) );//第三方提供的    
                    System.out.println("上传成功："+filename); 
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
            System.out.println("上传失败");  
            response.getWriter().print("上传失败："+e.getMessage());  
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
