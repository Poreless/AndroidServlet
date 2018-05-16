package com.wzu.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.wzu.model.Comment;
import com.wzu.model.CommentDao;
import com.wzu.util.JDBCUtil;;
public class CommentDaoImp  implements CommentDao{

	@Override
	public boolean FindComment(String uid, String aid) {
		// TODO Auto-generated method stub
		Connection conn;
        Statement sta;
        ResultSet rs;
        String sql;
        conn = JDBCUtil.getConnection();
        sta = null;
        rs = null;
        sql = "select * from comment where user_id='"+uid+"' and active_id='"+aid+"'";
        try {
			sta = conn.createStatement();
	        rs = sta.executeQuery(sql);
	        if(rs.next()){
		        System.out.println("你已评论该活动");
	            return true;
	        }else{
				System.out.println("你还没有评论该活动");
	        }
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs);								//关闭结果集对象
			JDBCUtil.close(sta);							//关闭预处理对象
			JDBCUtil.close(conn);							//关闭连接对象
		}
        return false;
	}

	@Override
	public boolean AddComment(String uid, String aid, String text, String num) {
		// TODO Auto-generated method stub
		Connection conn;
        Statement sta = null;
        ResultSet rs = null;
        String sql;
        PreparedStatement stmt;
        conn = JDBCUtil.getConnection();

	        //sql = "insert into user values('"+userid+"','"+username+"','"+userpwd+"','','','"+userphone+"')";
	        String sqlInset = "insert into comment(user_id, active_id, num, comm,time) values(?,?,?,?,?)";

	       // System.out.println(sql);
	        try
	        {
		        stmt = conn.prepareStatement(sqlInset);   //会抛出异常   
		        stmt.setString(1, uid);        
		        stmt.setString(2, aid);    
		        stmt.setString(3, num); 
		        stmt.setString(4,text);
		        stmt.setString(5,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));  
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
	public ArrayList<Comment> FindCommentByActID(int aid) {
		// TODO Auto-generated method stub
		Comment comm = null;
		UserDaoImp userdaoimp;
		userdaoimp= new UserDaoImp();
		ArrayList<Comment> commentlist = new ArrayList();
		Connection conn;
        Statement sta;
        ResultSet rs;
        String sql;
        conn = JDBCUtil.getConnection();
        sta = null;
        rs = null;
        sql = "select * from comment where active_id='"+aid+"'";
        try {
			sta = conn.createStatement();
	        rs = sta.executeQuery(sql);
	        if(rs.next()){
	        	comm = new Comment();
	        	comm.setActive_id(rs.getInt("active_id"));
	        	comm.setDate(strformat(rs.getString("time")));
	        	comm.setText(rs.getString("comm"));
	        	comm.setNum(rs.getInt("num"));
	        	comm.setUser_id(rs.getString("user_id"));
	        	comm.setUser(userdaoimp.UserFindByID(rs.getString("user_id")));
	        	commentlist.add(comm);
	        }else{
				System.out.println("暂时还没有评论");
	        }
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs);								//关闭结果集对象
			JDBCUtil.close(sta);							//关闭预处理对象
			JDBCUtil.close(conn);							//关闭连接对象
		}
        return commentlist;
	}
	
	
	public String strformat(String str){
		String newstr = str.substring(0,str.indexOf("."));
		return newstr;
		
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
		String sql = "delete from comment where user_id = ?";
		  try
	        {
		        stmt = conn.prepareStatement(sql);   //会抛出异常   
		        stmt.setString(1, userid);        

		        if(stmt.executeUpdate()>0){
		        	System.out.println("删除"+stmt.executeUpdate()+"条评价记录");
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
	public boolean DeletebyactID(String actid) {
		// TODO Auto-generated method stub
				Connection conn;
		        Statement sta = null;
		        ResultSet rs = null;
		        PreparedStatement stmt;
		        conn = JDBCUtil.getConnection();
				String sql = "delete from comment where active_id = ?";
				  try
			        {
				        stmt = conn.prepareStatement(sql);   //会抛出异常   
				        stmt.setString(1, actid);        

				        if(stmt.executeUpdate()>0){
				        	System.out.println("删除"+stmt.executeUpdate()+"条评价记录");
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
