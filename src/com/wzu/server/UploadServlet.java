// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2018/3/21 23:42:32
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   UploadServlet.java

package com.wzu.server;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet
{
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if(isMultipart)
        {
            String realpath = request.getSession().getServletContext().getRealPath("/files");
            System.out.println(realpath);
            File dir = new File(realpath);
            if(!dir.exists())
                dir.mkdirs();
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            try
            {
                List items = upload.parseRequest(request);
                for(Iterator iterator = items.iterator(); iterator.hasNext();)
                {
                    FileItem item = (FileItem)iterator.next();
                    if(item.isFormField())
                    {
                        String name1 = item.getFieldName();
                        String value = item.getString("UTF-8");
                        System.out.println((new StringBuilder(String.valueOf(name1))).append("=").append(value).toString());
                    } else
                    {
                        item.write(new File(dir, (new StringBuilder(String.valueOf(System.currentTimeMillis()))).append(item.getName().substring(item.getName().lastIndexOf("."))).toString()));
                    }
                }

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}