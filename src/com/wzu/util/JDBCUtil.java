// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2018/3/21 22:41:56
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   JDBCUtil.java

package com.wzu.util;

import java.sql.*;

public class JDBCUtil
{

    private static final String DBDRIVER = "com.mysql.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/db_0136";
    private static final String DBUSER = "root";
    private static final String DBPASSWORD = "root";
    public JDBCUtil()
    {
    }

    public static Connection getConnection()
    {
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_0136", "root", "root");
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(Connection conn)
    {
        if(conn != null)
            try
            {
                conn.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
    }

    public static void close(Statement sta)
    {
        if(sta != null)
            try
            {
                sta.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
    }

    public static void close(PreparedStatement pstmt)
    {
        if(pstmt != null)
            try
            {
                pstmt.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
    }

    public static void close(ResultSet rs)
    {
        if(rs != null)
            try
            {
                rs.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
    }

}