// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2018/3/21 22:39:20
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ActiveDaoImp.java

package com.wzu.db;

import com.wzu.model.Active;
import com.wzu.model.ActiveDao;
import com.wzu.model.Comment;
import com.wzu.model.User;
import com.wzu.util.JDBCUtil;
import java.io.PrintStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

// Referenced classes of package com.wzu.db:
//            UserDaoImp

public class ActiveDaoImp
    implements ActiveDao
{

    public ActiveDaoImp()
    {
    }

    public ArrayList UserListByID(String  active_id)
    {
        return null;
    }

    public ArrayList FindAllActive()
    {
        ArrayList activelist;
        UserDaoImp userdaoimp;
        Connection conn;
        Statement sta;
        ResultSet rs;
        String sql;
        Active act;
        activelist = new ArrayList<Active>();
        userdaoimp = new UserDaoImp();
        conn = JDBCUtil.getConnection();
        sta = null;
        rs = null;
        sql = "select * from active";
        try
        {
            sta = conn.createStatement();
            rs = sta.executeQuery(sql); 
            while(rs.next())
            {
                act = new Active();
                act.setActive_id(rs.getInt("active_id"));
                act.setActive_name(rs.getString("active_name"));
                act.setActive_place(rs.getString("active_place"));
                act.setActive_depict(rs.getString("active_depict"));
                act.setActive_icon(rs.getString("active_icon"));
                java.sql.Date date = rs.getDate("create_time");
                //jdbc的缺陷，在getdate时丢失时分，这里直接获取字符串并进行处理
                if(rs.getString("create_time")!=null){
                	act.setCreate_time(strformat(rs.getString("create_time")));
                }
                if(rs.getString("finish_time")!=null)
                	act.setFinish_time(strformat(rs.getString("finish_time")));
                act.setActive_theme(rs.getString("active_theme"));
                act.setMaxnum(rs.getInt("maxnum"));
                act.setUser(userdaoimp.UserFindByID(rs.getString("user_id")));
                activelist.add(act);
                System.out.println("活动名称=="+rs.getString(3));
            }
        }
        catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs);								//关闭结果集对象
			JDBCUtil.close(sta);							//关闭预处理对象
			JDBCUtil.close(conn);							//关闭连接对象
		}
        return activelist;
    }

	@Override
	public Active FindActiveByID(String  i) {
		// TODO Auto-generated method stub
		User user;
		UserDaoImp userdaoimp = new UserDaoImp();
	 	Active active;
        Connection conn;
        Statement sta;
        ResultSet rs;
        String sql;
        active = null;
        conn = JDBCUtil.getConnection();
        sta = null;
        rs = null;
        sql = "select * from active where active_id='"+i+"'";
        try
        {
            sta = conn.createStatement();
            rs = sta.executeQuery(sql);
            if(rs.next())
            {
                active = new Active();
                active.setActive_depict(rs.getString("active_depict"));
                active.setActive_icon(rs.getString("active_icon"));
                active.setActive_id(rs.getInt("active_id"));
                active.setActive_name(rs.getString("active_name"));
                if(rs.getString("create_time")!=null)
                	active.setCreate_time(strformat(rs.getString("create_time")));
                if(rs.getString("finish_time")!=null)
                	active.setFinish_time(strformat(rs.getString("finish_time")));
                active.setActive_theme(rs.getString("active_theme"));
                active.setMaxnum(rs.getInt("maxnum"));
                active.setActive_theme(rs.getString("active_theme"));
                active.setUser(userdaoimp.UserFindByID(rs.getString("user_id")));
            }
        }    catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs);								//关闭结果集对象
			JDBCUtil.close(sta);							//关闭预处理对象
			JDBCUtil.close(conn);							//关闭连接对象
		}
        return active;
	}

	@Override
	public ArrayList<Active> FindActiveByUserID(String userid) {
		// TODO Auto-generated method stub
		 	ArrayList<Active> activelist;
	        UserDaoImp userdaoimp;
	        CommentDaoImp commdaoimp;
	        Connection conn;
	        Statement sta;
	        ResultSet rs;
	        String sql;
	        Active act;
	        activelist = new ArrayList<Active>();
	        ArrayList<Comment> commentlist = new ArrayList();
	        userdaoimp = new UserDaoImp();
	        commdaoimp = new CommentDaoImp();
	        conn = JDBCUtil.getConnection();
	        sta = null;
	        rs = null;
	        sql = "select * from active where user_id = '"+userid+"'";
	        try
	        {
	            sta = conn.createStatement();
	            rs = sta.executeQuery(sql); 
	            while(rs.next())
	            {
	                act = new Active();
	                act.setActive_id(rs.getInt("active_id"));
	                act.setActive_name(rs.getString("active_name"));
	                act.setActive_place(rs.getString("active_place"));
	                act.setActive_depict(rs.getString("active_depict"));
	                act.setActive_icon(rs.getString("active_icon"));
	                if(rs.getString("create_time")!=null)
	                	act.setCreate_time(strformat(rs.getString("create_time")));
	                if(rs.getString("finish_time")!=null)
	                	act.setFinish_time(strformat(rs.getString("finish_time")));
	                act.setMaxnum(rs.getInt("maxnum"));
	                act.setActive_theme(rs.getString("active_theme"));
	                act.setUser(userdaoimp.UserFindByID(rs.getString("user_id")));
	                act.setCommentlist(commdaoimp.FindCommentByActID(rs.getInt("active_id")));
	                activelist.add(act);
	                System.out.println("活动名称=="+rs.getString(3));
	            }
	        }
	        catch (SQLException e) {
				e.printStackTrace();
			} finally{
				JDBCUtil.close(rs);								//关闭结果集对象
				JDBCUtil.close(sta);							//关闭预处理对象
				JDBCUtil.close(conn);							//关闭连接对象
			}
	        return activelist;
	}

	@Override
	public Boolean ActiveCreate(String user_id, String active_name, String active_theme,String active_place, String active_decipt,
			String active_time, String active_num) {
		// TODO Auto-generated method stub
		Connection conn;
        Statement sta = null;
        ResultSet rs = null;
        String sql;
        PreparedStatement stmt;
        conn = JDBCUtil.getConnection();

	        //sql = "insert into user values('"+userid+"','"+username+"','"+userpwd+"','','','"+userphone+"')";
	        String sqlInset = "insert into active(user_id, active_name, active_theme, active_place,active_depict,create_time,maxnum,active_icon) values(?,?,?,?,?,?,?,?)";

	       // System.out.println(sql);
	        try
	        {
		        stmt = conn.prepareStatement(sqlInset);   //会抛出异常   
		        stmt.setString(1, user_id);        
		        stmt.setString(2, active_name);    
		        stmt.setString(3, active_theme); 
		        stmt.setString(4,active_place);
		        stmt.setString(5,active_decipt); 
		        stmt.setString(6,active_time); 
		        stmt.setString(7,active_num); 
		        stmt.setString(8,"/3.jpg");    //这里直接设置默认活动图片
		        if(stmt.executeUpdate()>0)
		        	return true;
/*		            sta = conn.createStatement();
	            rs = sta.executeQuery(sql);*/

		        
	        }   catch (SQLException e) {
				e.printStackTrace();

			} finally{
				JDBCUtil.close(rs);								//关闭结果集对象
				JDBCUtil.close(sta);							//关闭预处理对象
				JDBCUtil.close(conn);							//关闭连接对象
			}
	        return false;
	}
	
	public String strformat(String str){
		String newstr = str.substring(0,str.indexOf("."));
		return newstr;
		
	}

	@Override
	public Boolean ActiveFinish(String activeid) {
		// TODO Auto-generated method stub
		Connection conn;
        Statement sta = null;
        ResultSet rs = null;
        PreparedStatement stmt;
        conn = JDBCUtil.getConnection();
		String sql = "update active set finish_time = ? where active_id = ?";
		  try
	        {
		        stmt = conn.prepareStatement(sql);   //会抛出异常   
		        stmt.setString(1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));        
		        stmt.setString(2, activeid);    
 
		        if(stmt.executeUpdate()>0)
		        	return true;
/*		            sta = conn.createStatement();
	            rs = sta.executeQuery(sql);*/

		        
	        }   catch (SQLException e) {
				e.printStackTrace();

			} finally{
				JDBCUtil.close(rs);								//关闭结果集对象
				JDBCUtil.close(sta);							//关闭预处理对象
				JDBCUtil.close(conn);							//关闭连接对象
			}
	        return false;
	}

	@Override
	public boolean DeletebyID(String userid) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				Connection conn;
		        Statement sta = null;
		        ResultSet rs = null;
		        PreparedStatement stmt;
		        conn = JDBCUtil.getConnection();
				String sql = "delete from active where user_id = ?";
				  try
			        {
				        stmt = conn.prepareStatement(sql);   //会抛出异常   
				        stmt.setString(1, userid);        
   	 
				        if(stmt.executeUpdate()>0){
				        	System.out.println("删除"+stmt.executeUpdate()+"条活动创建记录");
				        	return true;
				        }
		/*		            sta = conn.createStatement();
			            rs = sta.executeQuery(sql);*/

				        
			        }   catch (SQLException e) {
						e.printStackTrace();

					} finally{
						JDBCUtil.close(rs);								//关闭结果集对象
						JDBCUtil.close(sta);							//关闭预处理对象
						JDBCUtil.close(conn);							//关闭连接对象
					}
			        return false;
	}

	@Override
	public ArrayList<Active> FindFreshByUserID(String userid) {
		// TODO Auto-generated method stub
	 	ArrayList<Active> activelist;
        UserDaoImp userdaoimp;
        CommentDaoImp commdaoimp;
        Connection conn;
        Statement sta;
        ResultSet rs;
        String sql;
        Active act;
        activelist = new ArrayList<Active>();
        ArrayList<Comment> commentlist = new ArrayList();
        userdaoimp = new UserDaoImp();
        commdaoimp = new CommentDaoImp();
        conn = JDBCUtil.getConnection();
        sta = null;
        rs = null;
        //查询结束时间不为空
        sql = "select * from active where user_id = '"+userid+"' and finish_time is  null";
        try
        {
            sta = conn.createStatement();
            rs = sta.executeQuery(sql); 
            while(rs.next())
            {
                act = new Active();
                act.setActive_id(rs.getInt("active_id"));
                act.setActive_name(rs.getString("active_name"));
                act.setActive_place(rs.getString("active_place"));
                act.setActive_depict(rs.getString("active_depict"));
                act.setActive_icon(rs.getString("active_icon"));
                if(rs.getString("create_time")!=null)
                	act.setCreate_time(strformat(rs.getString("create_time")));
                if(rs.getString("finish_time")!=null)
                	act.setFinish_time(strformat(rs.getString("finish_time")));
                act.setMaxnum(rs.getInt("maxnum"));
                act.setUser(userdaoimp.UserFindByID(rs.getString("user_id")));
                act.setCommentlist(commdaoimp.FindCommentByActID(rs.getInt("active_id")));
                activelist.add(act);
                System.out.println("活动名称=="+rs.getString(3));
            }
        }
        catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs);								//关闭结果集对象
			JDBCUtil.close(sta);							//关闭预处理对象
			JDBCUtil.close(conn);							//关闭连接对象
		}
        return activelist;
	}

	@Override
	public Boolean ActChangeByID(String act_id, String active_name, String active_theme, String active_place,
			String active_decipt, String active_time, String active_num) {
		// TODO Auto-generated method stub
				Connection conn;
		        Statement sta = null;
		        ResultSet rs = null;
		        String sql;
		        PreparedStatement stmt;
		        conn = JDBCUtil.getConnection();
			        String sqlInset = "update active set active_name =?, active_theme=?, active_place=?,active_depict=?,create_time=?,maxnum=? where active_id=?";

			       // System.out.println(sql);
			        try
			        {
				        stmt = conn.prepareStatement(sqlInset);   //会抛出异常           
				        stmt.setString(1, active_name);    
				        stmt.setString(2, active_theme); 
				        stmt.setString(3,active_place);
				        stmt.setString(4,active_decipt); 
				        stmt.setString(5,active_time); 
				        stmt.setString(6,active_num); 
				        stmt.setString(7, act_id);
				        if(stmt.executeUpdate()>0){
				        	System.out.println("修改成功！");
				        	return true;
				        }
				        	
		/*		            sta = conn.createStatement();
			            rs = sta.executeQuery(sql);*/

				        
			        }   catch (SQLException e) {
						e.printStackTrace();

					} finally{
						JDBCUtil.close(rs);								//关闭结果集对象
						JDBCUtil.close(sta);							//关闭预处理对象
						JDBCUtil.close(conn);							//关闭连接对象
					}
			        return false;
	}

	@Override
	public ArrayList<Active> FindFinishByUserID(String userid) {
		// TODO Auto-generated method stub
			 	ArrayList<Active> activelist;
		        UserDaoImp userdaoimp;
		        CommentDaoImp commdaoimp;
		        Connection conn;
		        Statement sta;
		        ResultSet rs;
		        String sql;
		        Active act;
		        activelist = new ArrayList<Active>();
		        ArrayList<Comment> commentlist = new ArrayList();
		        userdaoimp = new UserDaoImp();
		        commdaoimp = new CommentDaoImp();
		        conn = JDBCUtil.getConnection();
		        sta = null;
		        rs = null;
		        //查询结束时间不为空
		        sql = "select * from active where user_id = '"+userid+"' and finish_time is not null";
		        try
		        {
		            sta = conn.createStatement();
		            rs = sta.executeQuery(sql); 
		            while(rs.next())
		            {
		                act = new Active();
		                act.setActive_id(rs.getInt("active_id"));
		                act.setActive_name(rs.getString("active_name"));
		                act.setActive_place(rs.getString("active_place"));
		                act.setActive_depict(rs.getString("active_depict"));
		                act.setActive_icon(rs.getString("active_icon"));
		                if(rs.getString("create_time")!=null)
		                	act.setCreate_time(strformat(rs.getString("create_time")));
		                if(rs.getString("finish_time")!=null)
		                	act.setFinish_time(strformat(rs.getString("finish_time")));
		                act.setMaxnum(rs.getInt("maxnum"));
		                act.setUser(userdaoimp.UserFindByID(rs.getString("user_id")));
		                act.setCommentlist(commdaoimp.FindCommentByActID(rs.getInt("active_id")));
		                activelist.add(act);
		                System.out.println("活动名称=="+rs.getString(3));
		            }
		        }
		        catch (SQLException e) {
					e.printStackTrace();
				} finally{
					JDBCUtil.close(rs);								//关闭结果集对象
					JDBCUtil.close(sta);							//关闭预处理对象
					JDBCUtil.close(conn);							//关闭连接对象
				}
		        return activelist;
	}
}