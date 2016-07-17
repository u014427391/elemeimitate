package com.moss.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.moss.entity.Order;
import com.moss.utils.DBHelperUtil;

public class OrderDao {

	DBHelperUtil manager;
	String sql = "";
	ResultSet rs;
	
	/**
	 * 获取用户的订单
	 * @param id
	 * 			用户ID
	 * @return
	 */
	public List getInfoById(String id) {
		List list = new ArrayList();
		
		try{
			manager = DBHelperUtil.createInstance();
			manager.connectDB();
			
			sql = "select tb_menus.menusImagePath,tb_menus.menusName,amount,commitTime,totalPrice,status from tb_order,tb_menus,tb_members " +
					"where tb_members.memberID=tb_order.memberID and tb_menus.menusID=tb_order.menusID and tb_order.memberID=?";
			Object[] params = new Object[]{id};
			rs = manager.executeQuery(sql, params);
			while(rs.next()){
				Vector vector = new Vector();
				vector.add(rs.getString("menusImagePath"));
				vector.add(rs.getString("menusName"));
				vector.add(rs.getInt("amount"));
				vector.add(rs.getDate("commitTime"));
				vector.add(rs.getDouble("totalPrice"));
				vector.add(rs.getString("status"));
				list.add(vector);
			}
			manager.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 新增订单
	 * @param order
	 * 			订单信息的实体类
	 * @return
	 */
	public boolean addOrder(Order order) {
		boolean flag = false;
		
		try {
			manager = DBHelperUtil.createInstance();
			manager.connectDB();
			
			sql = "insert into tb_order(orderID,commitTime,amount,totalPrice,menusID,memberID) " +
					"values (?,?,?,?,?,?)";
			
			Object[] params = new Object[]{
					order.getOrderID(),
					new Date(),
					order.getAmount(),
					order.getTotalPrice(),
					order.getMenusID(),
					order.getMemberID()
			};
			
			flag = manager.executeUpdate(sql, params);
				
			manager.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
			
		return flag;
	}
}
