package com.moss.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.moss.utils.DBHelperUtil;

public class BusinessDao {

	DBHelperUtil manager;
	String sql = "";
	ResultSet rs;
	
	public List<Vector> getAllBusinessInfo(){
		List<Vector> list = new ArrayList<Vector>();
		try{
			manager = DBHelperUtil.createInstance();
			manager.connectDB();
			sql = "select * from tb_business";
			Object[] params = new Object[]{};
			rs = manager.executeQuery(sql, params);
			while(rs.next()){
				Vector vector = new Vector();
				vector.add(rs.getString("businessName"));
				vector.add(rs.getDouble("sendOutPrice"));
				vector.add(rs.getDouble("distributionPrice"));
				vector.add(rs.getDate("shopHours"));
				vector.add(rs.getString("businessAddress"));
				vector.add(rs.getString("businessDepict"));
				vector.add(rs.getString("notice"));
				vector.add(rs.getString("businessScenery"));
				vector.add(rs.getString("logo"));
				list.add(vector);
			}
			
			manager.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
}
