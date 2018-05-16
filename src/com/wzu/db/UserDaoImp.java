// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2018/3/21 22:48:40
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   UserDaoImp.java

package com.wzu.db;

import com.wzu.model.User;
import com.wzu.model.UserDao;
import com.wzu.util.JDBCUtil;
import java.io.PrintStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDaoImp
    implements UserDao
{

    public UserDaoImp()
    {
    }

    public boolean UserFind(String id, String userPwd)
    {
        Connection conn;
        Statement sta;
        ResultSet rs;
        String sql;
        conn = JDBCUtil.getConnection();
        sta = null;
        rs = null;
        sql = "select * from user where user_id='"+id+"' and password='"+userPwd+"'";
        try {
			sta = conn.createStatement();
	        rs = sta.executeQuery(sql);
	        if(rs.next()){
		        System.out.println(rs.getString(2)+"��½��==");
	            return true;
	        }else{
				System.out.println("�����ڸ��û�==");
	        }
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs);								//�رս��������
			JDBCUtil.close(sta);							//�ر�Ԥ�������
			JDBCUtil.close(conn);							//�ر����Ӷ���
		}
        return false;
    }

    public User UserMessage(String id, String userPwd)
    {
        User user;
        Connection conn;
        Statement sta;
        ResultSet rs;
        String sql;
        user = null;
        conn = JDBCUtil.getConnection();
        sta = null;
        rs = null;
        sql = "select * from user where user_id='"+id+"' and password='"+userPwd+"'";
        try
        {
            sta = conn.createStatement();
            rs = sta.executeQuery(sql);
            if(rs.next())
            {
                user = new User();
                user.setUserID(rs.getString(1));
                user.setUserName(rs.getString(2));
                user.setUserPwd(rs.getString(3));
                user.setUserSex(rs.getString(4));
                user.setUserIcon(rs.getString(5));
                user.setUserTel(rs.getString(6));
                user.setSchool(rs.getString("school"));
            }
        }   catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs);								//�رս��������
			JDBCUtil.close(sta);							//�ر�Ԥ�������
			JDBCUtil.close(conn);							//�ر����Ӷ���
		}
        return user;
    }

    public User UserFindByID(String userID)
    {
    	
        User user;
        Connection conn;
        Statement sta;
        ResultSet rs;
        String sql;
        user = null;
        conn = JDBCUtil.getConnection();
        sta = null;
        rs = null;
        sql = "select * from user where user_id='"+userID+"'";
        try
        {
            sta = conn.createStatement();
            rs = sta.executeQuery(sql);
            if(rs.next())
            {
                user = new User();
                user.setUserID(rs.getString(1));
                user.setUserName(rs.getString(2));
                user.setUserPwd(rs.getString(3));
                user.setUserSex(rs.getString(4));
                user.setUserIcon(rs.getString(5));
                user.setUserTel(rs.getString(6));
                user.setSchool(rs.getString("school"));
            }
        }    catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs);								//�رս��������
			JDBCUtil.close(sta);							//�ر�Ԥ�������
			JDBCUtil.close(conn);							//�ر����Ӷ���
		}
        return user;
    }

	@Override
	public String UserCreate(String userid, String userphone, String username, String userpwd,String school) {
		// TODO Auto-generated method stub
	        Connection conn;
	        Statement sta = null;
	        ResultSet rs = null;
	        String sql;
	        PreparedStatement stmt;
	        conn = JDBCUtil.getConnection();
	        if(UserFindByID(userid)!=null){
	        	return new String("ѧ���Ѵ���");
	        }else{
		        //sql = "insert into user values('"+userid+"','"+username+"','"+userpwd+"','','','"+userphone+"')";
		        String sqlInset = "insert into user(user_id, user_name, password, user_phone,school) values(?, ?, ?, ?,?)";
    
		       // System.out.println(sql);
		        try
		        {
			        stmt = conn.prepareStatement(sqlInset);   //���׳��쳣   
			        stmt.setString(1, userid);        
			        stmt.setString(2, username);    
			        stmt.setString(3, userpwd);       
			        stmt.setString(4,userphone); 
			        stmt.setString(5,school);
			        if(stmt.executeUpdate()>0)
/*		            sta = conn.createStatement();
		            rs = sta.executeQuery(sql);*/
			        	return new String("success");
			        else
			        	return new String("����ʧ��");
			        
		        }   catch (SQLException e) {
					e.printStackTrace();
					return new String("���ݴ���");
				} finally{
					JDBCUtil.close(rs);								//�رս��������
					JDBCUtil.close(sta);							//�ر�Ԥ�������
					JDBCUtil.close(conn);							//�ر����Ӷ���
				}
	        }


	}

	@Override
	public boolean MessageSetByID(String userid, String userIcon, String sex, String name) {
		// TODO Auto-generated method stub

				Connection conn;
		        Statement sta = null;
		        ResultSet rs = null;
		        PreparedStatement stmt;
		        conn = JDBCUtil.getConnection();
				String sql = "update user set user_icon = ?,sex = ?,user_name = ? where user_id = ?";
				  try
			        {
				        stmt = conn.prepareStatement(sql);   //���׳��쳣   
				        stmt.setString(1, userIcon);        
				        stmt.setString(2, sex); 
				        stmt.setString(3, name); 
				        stmt.setString(4, userid);
		 
				        if(stmt.executeUpdate()>0)
				        	return true;
		/*		            sta = conn.createStatement();
			            rs = sta.executeQuery(sql);*/

				        
			        }   catch (SQLException e) {
						e.printStackTrace();

					} finally{
						JDBCUtil.close(rs);								//�رս��������
						JDBCUtil.close(sta);							//�ر�Ԥ�������
						JDBCUtil.close(conn);							//�ر����Ӷ���
					}
			        return false;
	}

	@Override
	public boolean UpdateLoginTime(String userid) {
		// TODO Auto-generated method stub
		Connection conn;
        Statement sta = null;
        ResultSet rs = null;
        PreparedStatement stmt;
        conn = JDBCUtil.getConnection();
		String sql = "update usertime set time = ? where user_id = ?";
		  try
	        {
		        stmt = conn.prepareStatement(sql);   //���׳��쳣   
		        stmt.setString(1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));        
		        stmt.setString(2, userid); 
		        if(stmt.executeUpdate()>0){
		        	System.out.println(userid+"��"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
		        			+"��¼");
		        	return true;
		        }
/*		            sta = conn.createStatement();
	            rs = sta.executeQuery(sql);*/

		        
	        }   catch (SQLException e) {
				e.printStackTrace();

			} finally{
				JDBCUtil.close(rs);								//�رս��������
				JDBCUtil.close(sta);							//�ر�Ԥ�������
				JDBCUtil.close(conn);							//�ر����Ӷ���
			}
	        return false;
	}

	@Override
	public boolean CreateLoginTime(String userid) {
		// TODO Auto-generated method stub
		Connection conn;
        Statement sta = null;
        ResultSet rs = null;
        PreparedStatement stmt;
        conn = JDBCUtil.getConnection();
        String sql = "insert into usertime(user_id, time) values(?,?)";
		  try
	        {
		        stmt = conn.prepareStatement(sql);   //���׳��쳣   
		        stmt.setString(1, userid);
		        stmt.setString(2, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));        
		      
		        if(stmt.executeUpdate()>0){
		        	System.out.println(userid+"��"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
		        			+"��¼");
		        	return true;
		        }
		        
/*		            sta = conn.createStatement();
	            rs = sta.executeQuery(sql);*/

		        
	        }   catch (SQLException e) {
				e.printStackTrace();

			} finally{
				JDBCUtil.close(rs);								//�رս��������
				JDBCUtil.close(sta);							//�ر�Ԥ�������
				JDBCUtil.close(conn);							//�ر����Ӷ���
			}
	        return false;
	}

	@Override
	public String SearchLoginTime(String userid) {
		// TODO Auto-generated method stub
        Connection conn;
        Statement sta;
        ResultSet rs;
        String sql;
        String time = null;
        conn = JDBCUtil.getConnection();
        sta = null;
        rs = null;
        sql = "select * from usertime where user_id='"+userid+"'";
        try
        {
            sta = conn.createStatement();
            rs = sta.executeQuery(sql);
            if(rs.next())
            {
            	time = rs.getString("time");
            }
        }    catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs);								//�رս��������
			JDBCUtil.close(sta);							//�ر�Ԥ�������
			JDBCUtil.close(conn);							//�ر����Ӷ���
		}
        return time;
	}

	@Override
	public boolean AdminLogin(String s, String s1) {
		// TODO Auto-generated method stub
		Connection conn;
        Statement sta;
        ResultSet rs;
        String sql;
        conn = JDBCUtil.getConnection();
        sta = null;
        rs = null;
        sql = "select * from admin where admin_id='"+s+"' and pwd='"+s1+"'";
        try {
			sta = conn.createStatement();
	        rs = sta.executeQuery(sql);
	        if(rs.next()){
		        System.out.println("����Ա"+rs.getString("admin_id")+"��½��==");
	            return true;
	        }else{
				System.out.println("�����ڸù���Ա==");
	        }
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs);								//�رս��������
			JDBCUtil.close(sta);							//�ر�Ԥ�������
			JDBCUtil.close(conn);							//�ر����Ӷ���
		}
        return false;
	}

	@Override
	public boolean DeletebyID(String userid) {
		// TODO Auto-generated method stub
				Connection conn;
		        Statement sta = null;
		        ResultSet rs = null;
		        PreparedStatement stmt;
		        conn = JDBCUtil.getConnection();
				String sql = "delete from user where user_id = ?";
				  try
			        {
				        stmt = conn.prepareStatement(sql);   //���׳��쳣   
				        stmt.setString(1, userid);        

				        if(stmt.executeUpdate()>0){
				        	System.out.println("ɾ��"+stmt.executeUpdate()+"���û���¼");
				        	return true;
				        }
		/*		            sta = conn.createStatement();
			            rs = sta.executeQuery(sql);*/

				        
			        }   catch (SQLException e) {
						e.printStackTrace();

					} finally{
						JDBCUtil.close(rs);								//�رս��������
						JDBCUtil.close(sta);							//�ر�Ԥ�������
						JDBCUtil.close(conn);							//�ر����Ӷ���
					}
			        return false;
	}

	@Override
	public boolean DeleteTimebyID(String userid) {
		// TODO Auto-generated method stub
		Connection conn;
        Statement sta = null;
        ResultSet rs = null;
        PreparedStatement stmt;
        conn = JDBCUtil.getConnection();
		String sql = "delete from usertime where user_id = ?";
		  try
	        {
		        stmt = conn.prepareStatement(sql);   //���׳��쳣   
		        stmt.setString(1, userid);        

		        if(stmt.executeUpdate()>0){
		        	System.out.println("ɾ��"+stmt.executeUpdate()+"����¼��¼");
		        	return true;
		        }
/*		            sta = conn.createStatement();
	            rs = sta.executeQuery(sql);*/

		        
	        }   catch (SQLException e) {
				e.printStackTrace();

			} finally{
				JDBCUtil.close(rs);								//�رս��������
				JDBCUtil.close(sta);							//�ر�Ԥ�������
				JDBCUtil.close(conn);							//�ر����Ӷ���
			}
	        return false;
	}




}