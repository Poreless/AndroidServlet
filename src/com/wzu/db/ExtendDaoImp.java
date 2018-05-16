// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2018/3/21 22:56:29
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ExtendDaoImp.java

package com.wzu.db;

import com.wzu.model.Extend;
import com.wzu.model.ExtendDao;
import com.wzu.util.JDBCUtil;
import java.io.PrintStream;
import java.sql.*;
import java.util.ArrayList;

// Referenced classes of package com.wzu.db:
//            UserDaoImp

public class ExtendDaoImp
    implements ExtendDao
{

    public ExtendDaoImp()
    {
    }

    public ArrayList FindAllExtend()
    {
        ArrayList Extendlist;
        Connection conn;
        Statement sta;
        ResultSet rs;
        String sql;
        Extendlist = new ArrayList<Extend>();
    	Extend ext;
        UserDaoImp userdaoimp = new UserDaoImp();
        conn = JDBCUtil.getConnection();
        sta = null;
        rs = null;
        sql = "select * from extend";
        try
        {
            sta = conn.createStatement();
            rs = sta.executeQuery(sql); 
            while(rs.next())
            {

                ext = new Extend();
                ext.setExtend_name(rs.getString("extend_name"));
                ext.setIcon_url(rs.getString("icon_url"));
                ext.setWeb_url(rs.getString("web_url"));
                Extendlist.add(ext);
                System.out.println("查询广告=="+rs.getString(3));
            }

        }catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs);								//关闭结果集对象
			JDBCUtil.close(sta);							//关闭预处理对象
			JDBCUtil.close(conn);							//关闭连接对象
		}
        return Extendlist;
    }
}