package com.moss.entity;

import java.util.Date;

/**
 * 
 * 项目名称：MealOrderSystemServer    
 * 类名称：Order    
 * 类描述：订单信息实体类    
 * 创建人：Nicky
 * 创建时间：2016年7月2日 下午9:26:19      
 * @version
 */
public class Order {
	
	/**
	 * 订单编号
	 */
	private String orderID;
	
	/**
	 * 订单提交时间
	 */
	private Date commitTime;
	
	/**
	 * 购买数量
	 */
	private int amount;
	
	/**
	 * 总价钱
	 */
	private Double totalPrice;
	
	/**
	 * 订单状态
	 */
	private String status;
	
	/**
	 * 餐品ID
	 */
	private int menusID;
	
	/**
	 * 会员ID
	 */
	private int memberID;

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public Date getCommitTime() {
		return commitTime;
	}

	public void setCommitTime(Date commitTime) {
		this.commitTime = commitTime;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getMenusID() {
		return menusID;
	}

	public void setMenusID(int menusID) {
		this.menusID = menusID;
	}

	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}
	
	
}
