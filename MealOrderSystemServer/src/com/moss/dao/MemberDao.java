package com.moss.dao;

import java.sql.ResultSet;

import com.moss.entity.Member;
import com.moss.utils.DBHelperUtil;

public class MemberDao {
	
	DBHelperUtil manager;
	String sql = "";
	ResultSet rs;
	
	/**
	 * 登录验证
	 */
	public boolean loginCheck(String account,String password){
		boolean flag = false;
		
		sql = "select * from tb_members where memberID=?";
		try{
			manager = DBHelperUtil.createInstance();
			manager.connectDB();
			Object[] params = new Object[]{account}; 
			rs = manager.executeQuery(sql, params);
			if(rs.next()){
				String pwd = rs.getString("password");
				if(password.equals(pwd)){
					flag = true;
				}else{
					flag = false;
				}
			}else{
				flag = false;
			}
			manager.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return flag;
	}

	/**
	 * 会员注册
	 */
	public void register(Member member) {
		
		sql = "insert into tb_members(memberID,password,rank,credit,imgPath) " +
				"values(?,?,?,?,?)";
		
		try {
			
			manager = DBHelperUtil.createInstance();
			manager.connectDB();
			
			Object[] params = new Object[]{
					member.getMemberID(),
					member.getPassword(),
					member.getRank(),
					member.getCredit(),
					member.getImgPath()
			};
			
			manager.executeUpdate(sql, params);
			manager.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
