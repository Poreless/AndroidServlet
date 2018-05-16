package com.wzu.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.wzu.model.ActiveRequest;
import com.wzu.model.ActiveRequestDao;
import com.wzu.model.ModelRequest;
import com.wzu.model.User;
import com.wzu.util.JDBCUtil;

public class ActiveRequestDaoImp implements ActiveRequestDao{

	@Override
	public Boolean AddActiveRequest(String userid,String actid) {
		// TODO Auto-generated method stub
		  	Connection conn;
	        Statement sta = null;
	        ResultSet rs = null;
	        String sql;
	        PreparedStatement stmt;
	        conn = JDBCUtil.getConnection();
	        //sql = "insert into user values('"+userid+"','"+username+"','"+userpwd+"','','','"+userphone+"')";
	        String sqlInset = "insert into act_user(user_id, active_id, time, num) values(?, ?, ?, ?)";
		        try
		        {
			        stmt = conn.prepareStatement(sqlInset);   //会抛出异常   
			        stmt.setString(1, userid);        
			        stmt.setString(2, actid);    
			        stmt.setString(3, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));       
			        stmt.setInt(4,0); 
			        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			        if(stmt.executeUpdate()>0);{
/*		            sta = conn.createStatement();
		            rs = sta.executeQuery(sql);*/
			        System.out.println("申请成功！！");
			        return true;}
			       
			        
		        }   catch (SQLException e) {
		        	System.out.println("申请失败！！");
					e.printStackTrace();
					
				} finally{
					JDBCUtil.close(rs);								//关闭结果集对象
					JDBCUtil.close(sta);							//关闭预处理对象
					JDBCUtil.close(conn);							//关闭连接对象
				}
		        return false;

	}

	@Override
	public ArrayList<ModelRequest> RequestByActID(String actid) {
		ArrayList<ModelRequest> Requestlist = new ArrayList();
		ModelRequest mr;
		// TODO Auto-generated method stub
		Connection conn;
        int num =0;
        Statement sta;
        ResultSet rs;
        String sql;
        conn = JDBCUtil.getConnection();
        sta = null;
        rs = null;
        sql = "select * from act_user where active_id = '"+actid+"'";
        try
        {
            sta = conn.createStatement();
            rs = sta.executeQuery(sql);
            while(rs.next())
            {
            	mr = new ModelRequest();
            	mr.setActive_id(rs.getString("active_id"));
            	mr.setDate(rs.getString("time"));
            	mr.setUser_id(rs.getString("user_id"));
            	mr.setNum(rs.getString("num"));
            	Requestlist.add(mr);
            }
        }    catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs);								//关闭结果集对象
			JDBCUtil.close(sta);							//关闭预处理对象
			JDBCUtil.close(conn);							//关闭连接对象
		}
        return Requestlist;
	}

	@Override
	public int RequestSuccessNum(String  actid) {
        Connection conn;
        int num =0;
        Statement sta;
        ResultSet rs;
        String sql;
        conn = JDBCUtil.getConnection();
        sta = null;
        rs = null;
        sql = "select COUNT(*) from act_user where active_id='"+actid+"' and num=1";
        try
        {
            sta = conn.createStatement();
            rs = sta.executeQuery(sql);
            if(rs.next())
            {
            	num=rs.getInt(1);
           // 	num++;
            }
        }    catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs);								//关闭结果集对象
			JDBCUtil.close(sta);							//关闭预处理对象
			JDBCUtil.close(conn);							//关闭连接对象
		}
        return num;
	}

	@Override
	public String ChangeRequestNum(String actid, String uid, String num) {
		// TODO Auto-generated method stub
	  	Connection conn;
        Statement sta = null;
        ResultSet rs = null;
        String sql;
        PreparedStatement stmt;
        conn = JDBCUtil.getConnection();
		sql = "update act_user set num =? where user_id =? and active_id=?";
		if(SearchNum(actid,uid)==0){
			 try {
				stmt = conn.prepareStatement(sql);   //会抛出异常   
		        stmt.setString(1, num);        
		        stmt.setString(2, uid);    
		        stmt.setString(3, actid);       
		        if(stmt.executeUpdate()>0){
		        	return "请求已处理";
		        }else{
		        	return "数据修改失败";
		        }
		        
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      
		}
		else if((SearchNum(actid,uid)==1)){
			return "已同意,请勿重复操作";
		}
		else if((SearchNum(actid,uid)==2)){
			return "已拒绝,请勿重复操作";
		}
		else if((SearchNum(actid,uid)==1)){
			return "已忽略,请勿重复操作";
		}
		return null;
	}

	@Override
	public int SearchNum(String actid, String uid) {
		// TODO Auto-generated method stub
		Connection conn;
        int num = 1 ;
        Statement sta;
        ResultSet rs;
        String sql;
        conn = JDBCUtil.getConnection();
        sta = null;
        rs = null;
        sql = "select num from act_user where active_id='"+actid+"' and user_id='"+uid+"'";
        try
        {
            sta = conn.createStatement();
            rs = sta.executeQuery(sql);
            if(rs.next())
            {
            	num = rs.getInt(1);
            	System.out.println(num+"");
           // 	num++;
            }
        }    catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs);								//关闭结果集对象
			JDBCUtil.close(sta);							//关闭预处理对象
			JDBCUtil.close(conn);							//关闭连接对象
		}
        return  num;
	}

	@Override
	public ArrayList<String> ReqSuccessByUserID(String usertid) {   //加入成功的活动名，用于获取评价列表
		// TODO Auto-generated method stub
		ArrayList<String> ActIDList = new ArrayList();
		String ActID;
		// TODO Auto-generated method stub
		Connection conn;
        int num =0;
        Statement sta;
        ResultSet rs;
        String sql;
        conn = JDBCUtil.getConnection();
        sta = null;
        rs = null;
        sql = "select * from act_user where user_id = '"+usertid+"' and num = 1";
        try
        {
            sta = conn.createStatement();
            rs = sta.executeQuery(sql);
            while(rs.next())
            {
            	ActID = new String();
            	ActID = rs.getString("active_id");
            	ActIDList.add(ActID);
            }
        }    catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs);								//关闭结果集对象
			JDBCUtil.close(sta);							//关闭预处理对象
			JDBCUtil.close(conn);							//关闭连接对象
		}
        return ActIDList;
	}

	@Override
	public ArrayList<String> ReqSuccessByActID(String actid) {
		// TODO Auto-generated method stub
		ArrayList<String> UserIDList = new ArrayList();
		String UserID;
		// TODO Auto-generated method stub
		Connection conn;
        int num =0;
        Statement sta;
        ResultSet rs;
        String sql;
        conn = JDBCUtil.getConnection();
        sta = null;
        rs = null;
        sql = "select * from act_user where active_id = '"+actid+"' and num = 1";
        try
        {
            sta = conn.createStatement();
            rs = sta.executeQuery(sql);
            while(rs.next())
            {
            	UserID = new String();
            	UserID = rs.getString("user_id");
            	UserIDList.add(UserID);
            }
        }    catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs);								//关闭结果集对象
			JDBCUtil.close(sta);							//关闭预处理对象
			JDBCUtil.close(conn);							//关闭连接对象
		}
        return UserIDList;
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
		String sql = "delete from act_user where user_id = ?";
		  try
	        {
		        stmt = conn.prepareStatement(sql);   //会抛出异常   
		        stmt.setString(1, userid);        

		        if(stmt.executeUpdate()>0){
		        	System.out.println("根据申请者删除"+stmt.executeUpdate()+"条活动申请记录");
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
	public boolean DeletebyActID(String activeid) {
		// TODO Auto-generated method stub
		Connection conn;
        Statement sta = null;
        ResultSet rs = null;
        PreparedStatement stmt;
        conn = JDBCUtil.getConnection();
		String sql = "delete from act_user where active_id = ?";
		  try
	        {
		        stmt = conn.prepareStatement(sql);   //会抛出异常   
		        stmt.setString(1, activeid);        

		        if(stmt.executeUpdate()>0){
		        	System.out.println("根据活动ID删除"+stmt.executeUpdate()+"条活动申请记录");
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
