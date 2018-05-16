package com.wzu.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.wzu.model.Extend;
import com.wzu.model.TagDao;
import com.wzu.util.JDBCUtil;

public class TagDaoImp implements  TagDao{

	@Override
	public Boolean TagCreate(String uid, String tag) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
	  	Connection conn;
        Statement sta = null;
        ResultSet rs = null;
        String sql;
        PreparedStatement stmt;
        conn = JDBCUtil.getConnection();
        String sqlInset = "insert into tag(user_id, mtag) values(?, ?)";
	        try
	        {
		        stmt = conn.prepareStatement(sqlInset);   //会抛出异常   
		        stmt.setString(1, uid);        
		        stmt.setString(2, tag);    
		        if(stmt.executeUpdate()>0);{
/*		            sta = conn.createStatement();
	            rs = sta.executeQuery(sql);*/
		        System.out.println("操作成功！！");
		        return true;}       
		        
	        }   catch (SQLException e) {
	        	System.out.println("操作失败！！");
				e.printStackTrace();
				
			} finally{
				JDBCUtil.close(rs);								//关闭结果集对象
				JDBCUtil.close(sta);							//关闭预处理对象
				JDBCUtil.close(conn);							//关闭连接对象
			}
	        return false;
	}

	@Override
	public ArrayList<String> TagFindByID(String uid) {
		// TODO Auto-generated method stub
		 	ArrayList<String> taglist;
	        Connection conn;
	        Statement sta;
	        ResultSet rs;
	        String sql;
	        taglist = new ArrayList<String>();
	        conn = JDBCUtil.getConnection();
	        sta = null;
	        rs = null;
	        sql = "select * from tag where user_id = '"+uid+"'";
	        try
	        {
	            sta = conn.createStatement();
	            rs = sta.executeQuery(sql); 
	            while(rs.next())
	            {

	                taglist.add(rs.getString("mtag"));
	            }

	        }catch (SQLException e) {
				e.printStackTrace();
			} finally{
				JDBCUtil.close(rs);								//关闭结果集对象
				JDBCUtil.close(sta);							//关闭预处理对象
				JDBCUtil.close(conn);							//关闭连接对象
			}
	        return taglist;
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
		String sql = "delete from tag where user_id = ?";
		  try
	        {
		        stmt = conn.prepareStatement(sql);   //会抛出异常   
		        stmt.setString(1, userid);        

		        if(stmt.executeUpdate()>0){
		        	System.out.println("删除"+stmt.executeUpdate()+"条兴趣记录");
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

}
