package com.ltmcp.mail.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ltmcp.mail.dao.CityMailDao;
import com.ltmcp.mail.dao.DBUtil;
import com.ltmcp.mail.entity.CityMail;


/**
 * 查询邮件
 * @author Administrator
 *
 */
public class CityMailDao {
	/**
	 * 根据邮箱级别查看邮箱
	 * @param rankMail
	 * @return
	 */
	public List<CityMail> cityMail(){
		List<CityMail> cm = new ArrayList<CityMail>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement("select * from city_mail");
			rs = ps.executeQuery();
			while(rs.next()){
				CityMail c = new CityMail();
				c.setId(rs.getInt("id"));
				c.setSiteMail(rs.getString("sitemail"));
				c.setRankMail(rs.getInt("rankmail"));
				c.setStandby1(rs.getString("standby01"));
				c.setStandby2(rs.getString("standby02"));
				cm.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cm;
	}
	/**
	 * 根据ID查询
	 * @param rankMail
	 * @return
	 */
	public List<CityMail> QueryAllById(int rankmail){
		List<CityMail> cm = new ArrayList<CityMail>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement("select * from city_mail where rankmail=?");
			ps.setInt(1, rankmail);
			rs = ps.executeQuery();
			while(rs.next()){
				CityMail c = new CityMail();
				c.setId(rs.getInt("id"));
				c.setSiteMail(rs.getString("sitemail"));
				c.setRankMail(rs.getInt("rankmail"));
				c.setStandby1(rs.getString("standby01"));
				c.setStandby2(rs.getString("standby02"));
				cm.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cm;
	}
	public static void main(String[] args) {
		List<CityMail> cm = new CityMailDao().QueryAllById(1);
		System.out.println(cm);
		for(CityMail c : cm){
			if(c.getRankMail()==0){
				System.out.println("市区");
			}else if(c.getRankMail()==1){
				System.out.println("县区");
			}else if(c.getRankMail()==2){
				System.out.println("乡");
			}
		}
	}
}
