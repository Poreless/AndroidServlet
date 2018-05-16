package com.wzu.control;
import java.io.IOException;  
import java.util.Enumeration;  
import java.util.Iterator;  
  
import javax.servlet.Filter;  
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
import org.apache.log4j.Logger;  
  
/** 
 * 防sql注入过滤器,使用时需要注意可能会过滤掉正常访问 
 * @version 1.0 
 */  
public class SqlFilter implements Filter {  
    public static final Logger logger = Logger.getLogger(Object.class);  
      
    //需要过滤的post参数值字符(不需要空格 可能会对系统访问有影响，请注意删减关键字)  
    private static String postStr="%20,script";  
    //需要过滤的post字符(可能会对系统访问有影响，请注意删减关键字)  
    //private static String sqlStr="<,>,and,exec,insert,select,%20,delete,update,count,*,%,chr,mid,master,truncate,char,like,declare,&,#,(,),/**/,=,script,\u0023,redirect:,xwork2";  
    // --and , count  
    private static String sqlStr="exec,insert,select,%20,delete,update,chr,master,truncate,char,like,declare,#,/**/,script,\u0023,redirect:,xwork2";  
    //需要过滤的url字符(可能会对系统访问有影响，请注意删减关键字)  
    private static String urlStr="%20,%22,%27,<,>,master,truncate,char,script,java.lang.ProcessBuilder,java.lang.String,/etc/,\u0023,redirect:,xwork2,\u0073\u0063\u0072\u0069\u0070\u0074";  
    public void destroy() {  
   }  
  
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {  
        request.setCharacterEncoding("utf-8");  
        HttpServletRequest req = (HttpServletRequest)request;  
        HttpServletResponse res = (HttpServletResponse)response;      
  
        Enumeration names = req.getParameterNames();//获取所有的表单参数  
        String gotoUrl=req.getRequestURI();  //获取访问的url  
        String queryString = req.getQueryString();  
          
        //判断所有的参数名是否有非法字符  
        while(names.hasMoreElements()){           
        String st=names.nextElement().toString();  
               if(strInj(st,sqlStr)||strInj2(st,urlStr)){  
                   req.getSession().setAttribute("msgStr", "请不要输入非法参数："+req.getParameter(st)+" !");  
                   res.sendRedirect(req.getContextPath()+"/jsp/common/error.jsp");  
                   return;  
               }  
     
        }   
          
        //判断所有的参数值是否有非法字符  
        Iterator values = req.getParameterMap().values().iterator();//获取所有的表单参数  
        while(values.hasNext()){  
           String[] value = (String[])values.next();  
           for(int i = 0;i < value.length;i++){  
               if(strInj(value[i],sqlStr)||strInj2(value[i],postStr)){  
                   request.setAttribute("msgStr", "请不要输入非法参数："+value[i]+" !");  
                   res.sendRedirect(req.getContextPath()+"/jsp/common/error.jsp");  
                   return;  
               }  
  
           }  
        }   
          
        //判断访问的url中是否有非法参数  
        if(queryString!=null&&strInj2(queryString,urlStr)){  
                req.getSession().setAttribute("msgStr", "请不要输入非法参数 !");  
                res.sendRedirect(req.getContextPath()+"/jsp/common/error.jsp");  
                return;  
        }  
      
          
        chain.doFilter(request, response);  
    }  
      
    /** 
     * 判断字符是否包含非法字符 
     * @param str 
     * @return 
     */  
    public static boolean strInj(String str,String standStr){  
        if(str==null||str.length()==0)return false;  
        String[] inj_stra=standStr.split(",");  
        for (int i=0 ; i < inj_stra.length ; i++ ){  
            if (inj_stra[i].length()>0&&str.toLowerCase().indexOf(inj_stra[i])>=0){     
                System.out.println(inj_stra[i]);  
               return true;   
            }  
        }     
        return false;  
    }  
      
    /** 
     * 判断字符是否包含非法字符,没有空格 
     * @param str 
     * @return 
     */  
    public boolean strInj2(String str,String standStr){  
        if(str==null||str.length()==0)return false;  
        String[] inj_stra=standStr.split(",");  
        for (int i=0 ; i < inj_stra.length ; i++ ){  
            if (inj_stra[i].length()>0&&str.toLowerCase().indexOf(inj_stra[i])>=0){     
               return true;   
            }  
        }     
        return false;  
    }  
  
  
    public void init(FilterConfig cfg) throws ServletException {  
          
    }  
      
    public static void main(String[] args){  
        SqlFilter sf = new SqlFilter();  
        String st = "standardPrice";  
        if(strInj(st,sqlStr)){  
            System.out.println("=======:"+sqlStr.indexOf(st));  
           }  
        if(sf.strInj2(st,urlStr)){  
            System.out.println("=======:"+urlStr.indexOf(st));  
        }  
    }  
}  