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
import com.wzu.model.ExtendDao;
import com.wzu.model.Friend;
import com.wzu.model.FriendDao;
import com.wzu.model.FriendListInfo;
import com.wzu.util.JDBCUtil;

public class FriendDaoImp implements FriendDao{

	@Override
	public ArrayList<String> FindReqFriendListByID(String uid) {   //包括自己申请成功与别人申请自己成功的
		ArrayList friendIDlist;
        Connection conn;
        Statement sta;
        ResultSet rs;
        PreparedStatement stmt;
        String sql;
        friendIDlist = new ArrayList<String>();
    	String fid = null;
        conn = JDBCUtil.getConnection();
        sta = null;
        rs = null;
        sql = "select distinct * from friend where (uid=? or fid=?) and num=1";   
        //设置ID自增后一般不会有重复字段，and后的条件判别好友请求是否被允许
        try
        {

		        stmt = conn.prepareStatement(sql);   //会抛出异常   
		        stmt.setString(1, uid);  
		        stmt.setString(2, uid);
		        rs = stmt.executeQuery();
/*		            sta = conn.createStatement();
		            rs = sta.executeQuery(sql);*/
		        while(rs.next())
	            {
		        	if(rs.getString("fid").equals(rs.getString("uid")))continue;
		            if(rs.getString("fid").equals(uid)){
		                	fid = rs.getString("uid");
		                	}else {
		                	fid = rs.getString("fid");
		                	}
	                friendIDlist.add(fid);
	                System.out.println("查询到的记录有=="+fid);
	                for (int i= 0;i <friendIDlist.size()-1 ;i ++ ) {        //去除重复的算法，时间复杂度大 
	                    for  (int j  =friendIDlist.size() - 1 ; j > i; j -- ) {       
	                         if  (friendIDlist.get(j).equals(friendIDlist.get(i)))  {       
	                        	 friendIDlist.remove(j);       
	                          }        
	                      }        
	                    }  
	            }


        }catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs);								//关闭结果集对象
			JDBCUtil.close(sta);							//关闭预处理对象
			JDBCUtil.close(conn);							//关闭连接对象
		}
        return friendIDlist;
	}

	@Override
	public boolean addfriend(String uid, String fid) {
		Connection conn;
        Statement sta = null;
        ResultSet rs = null;
        String sql;
        PreparedStatement stmt;
        conn = JDBCUtil.getConnection();
        //sql = "insert into user values('"+userid+"','"+username+"','"+userpwd+"','','','"+userphone+"')";
        String sqlInset = "insert into friend(uid, fid, time, num) values(?, ?, ?, ?)";
	        try
	        {
		        stmt = conn.prepareStatement(sqlInset);   //会抛出异常   
		        stmt.setString(1, uid);        
		        stmt.setString(2, fid);    
		        stmt.setString(3, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));       
		        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		        stmt.setInt(4,0);
		        if(stmt.executeUpdate()>0);{
/*		            sta = conn.createStatement();
	            rs = sta.executeQuery(sql);*/
		        System.out.println("申请成功！！");
		        return true;
		        }     
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
	public ArrayList<Friend> ListFriendRequest(String uid, String fid) {
		// TODO Auto-generated method stub
		ArrayList<Friend> friendlist;
        Connection conn;
        Statement sta;
        ResultSet rs;
        String sql;
        friendlist = new ArrayList<Friend>();
    	Friend friend;
        UserDaoImp userdaoimp = new UserDaoImp();
        conn = JDBCUtil.getConnection();
        sta = null;
        rs = null;
        sql = "select * from friend where uid ='"+uid+"' and fid ='"+fid+"'";
        try
        {
            sta = conn.createStatement();
            rs = sta.executeQuery(sql); 
            while(rs.next())
            {
                friend = new Friend();
                friend.setFid(rs.getString("fid"));
                friend.setUid(rs.getString("uid"));
                friend.setId(rs.getInt("id"));
                friend.setNum(rs.getInt("num"));
                if(rs.getString("time")!=null)
                	friend.setTime(strformat(rs.getString("time")));
                friendlist.add(friend);
                System.out.println("查询广告=="+rs.getString(3));
            }

        }catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs);								//关闭结果集对象
			JDBCUtil.close(sta);							//关闭预处理对象
			JDBCUtil.close(conn);							//关闭连接对象
		}
        return friendlist;
	}

	public String strformat(String str){
		String newstr = str.substring(0,str.indexOf("."));
		return newstr;
		
	}

	@Override
	public ArrayList<Friend> RequestByUserID(String uid) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				ArrayList<Friend> friendlist;
		        Connection conn;
		        Statement sta;
		        ResultSet rs;
		        String sql;
		        friendlist = new ArrayList<Friend>();
		    	Friend friend;
		        UserDaoImp userdaoimp = new UserDaoImp();
		        conn = JDBCUtil.getConnection();
		        sta = null;
		        rs = null;
		        sql = "select * from friend where fid ='"+uid+"'";   //被申请人是自己
		        try
		        {
		            sta = conn.createStatement();
		            rs = sta.executeQuery(sql); 
		            while(rs.next())
		            {
		                friend = new Friend();
		                friend.setFid(rs.getString("fid"));
		                friend.setUid(rs.getString("uid"));
		                friend.setId(rs.getInt("id"));
		                friend.setNum(rs.getInt("num"));
		                if(rs.getString("time")!=null)
		                	friend.setTime(strformat(rs.getString("time")));
		                friendlist.add(friend);
		                System.out.println("查询广告=="+rs.getString(3));
		            }

		        }catch (SQLException e) {
					e.printStackTrace();
				} finally{
					JDBCUtil.close(rs);								//关闭结果集对象
					JDBCUtil.close(sta);							//关闭预处理对象
					JDBCUtil.close(conn);							//关闭连接对象
				}
		        return friendlist;
	}

	@Override
	public String ChangeFriendRequestNum(String userid, String friendid, String num) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
	  	Connection conn;
        Statement sta = null;
        ResultSet rs = null;
        String sql;
        PreparedStatement stmt;
        conn = JDBCUtil.getConnection();
		sql = "update friend set num =? where uid =? and fid=?";
		if(SearchNum(userid,friendid)==0){
			 try {
				stmt = conn.prepareStatement(sql);   //会抛出异常   
		        stmt.setString(1, num);        
		        stmt.setString(2, userid);    
		        stmt.setString(3, friendid);       
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
		else if((SearchNum(userid,friendid)==1)){
			return "已同意,请勿重复操作";
		}
		else if((SearchNum(userid,friendid)==2)){
			return "已拒绝,请勿重复操作";
		}
		else if((SearchNum(userid,friendid)==1)){
			return "已忽略,请勿重复操作";
		}
		return null;
	}

	@Override
	public int SearchNum(String userid, String friendid) {
		// TODO Auto-generated method stub
		Connection conn;
        int num = 1 ;
        Statement sta;
        ResultSet rs;
        String sql;
        conn = JDBCUtil.getConnection();
        sta = null;
        rs = null;
        sql = "select num from friend where uid='"+userid+"' and fid='"+friendid+"'";
        try
        {
            sta = conn.createStatement();
            rs = sta.executeQuery(sql);
            if(rs.next())
            {
            	num = rs.getInt(1);
            	//System.out.println(num+"");
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
	public boolean DeletebyID(String userid) {
		// TODO Auto-generated method stub
				Connection conn;
		        Statement sta = null;
		        ResultSet rs = null;
		        PreparedStatement stmt;
		        conn = JDBCUtil.getConnection();
				String sql = "delete from friend where uid=? or fid=?";
				  try
			        {
				        stmt = conn.prepareStatement(sql);   //会抛出异常   
				        stmt.setString(1, userid);        
				        stmt.setString(2, userid);
				        if(stmt.executeUpdate()>0){
				        	System.out.println("删除"+stmt.executeUpdate()+"条好友记录");
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
