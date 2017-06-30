package com.ltmcp.mail.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.ltmcp.util.UrlAndPathComm;

/**
 * 数据库连接对象
 * @author Administrator
 *
 */
public class DBUtil {
	/**
	 * 数据库连接
	 * @return
	 */
	public static Connection getConnection(){
		//获取数据库连接方式
		Connection con = null;
		try {
			//得到数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://"+UrlAndPathComm.DataBases+"/ltmcp",
					"root",
					"root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	/**
	 * 关闭数据库
	 * @param con
	 * @throws SQLException
	 */
	public static void close(Connection con) throws SQLException{
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
