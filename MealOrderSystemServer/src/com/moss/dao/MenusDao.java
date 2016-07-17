package com.moss.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.moss.utils.DBHelperUtil;

public class MenusDao {

	DBHelperUtil manager;
	String sql = "";
	ResultSet rs;
	
	public List getInfoById(int businessID){
		List list = new ArrayList();
		try{
			manager = DBHelperUtil.createInstance();
			manager.connectDB();
			sql = "select tb_business.businessName,menusImagePath,menusName,menusPrice,menusDepict from tb_business,tb_menus "
					+ "where tb_business.businessID=tb_menus.businessID";
			Object[] params = new Object[]{businessID};
			rs = manager.executeQuery(sql, params);
			while(rs.next()){
				Vector v = new Vector();
				v.add(rs.getString("businessName"));
				v.add(rs.getString("menusName"));
				v.add(rs.getString("menusImagePath"));
				v.add(rs.getDouble("menusPrice"));
				v.add(rs.getString("menusDepict"));
				list.add(v);
			}
			manager.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
}