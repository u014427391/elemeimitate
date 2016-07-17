package com.moss.entity;
/**
 * 
 * 项目名称：MealOrderSystemServer    
 * 类名称：Menus    
 * 类描述：餐品信息实体类    
 * 创建人：Nicky
 * 创建时间：2016年7月4日 下午4:05:56      
 * @version
 */
public class Menus {

	/**
	 * 餐品ID
	 */
	private int menusID;
	
	/**
	 * 餐品名称
	 */
	private String menusName;
	
	/**
	 * 餐品价钱
	 */
	private Double menusPrice;
	
	/**
	 * 餐品描述
	 */
	private String menusDepict;
	
	/**
	 * 餐品图片路径
	 */
	private String menusImagePath;
	
	/**
	 * 商家Id，外键
	 */
	private int businessID;

	public int getMenusID() {
		return menusID;
	}

	public void setMenusID(int menusID) {
		this.menusID = menusID;
	}

	public String getMenusName() {
		return menusName;
	}

	public void setMenusName(String menusName) {
		this.menusName = menusName;
	}

	public Double getMenusPrice() {
		return menusPrice;
	}

	public void setMenusPrice(Double menusPrice) {
		this.menusPrice = menusPrice;
	}

	public String getMenusDepict() {
		return menusDepict;
	}

	public void setMenusDepict(String menusDepict) {
		this.menusDepict = menusDepict;
	}

	public String getMenusImagePath() {
		return menusImagePath;
	}

	public void setMenusImagePath(String menusImagePath) {
		this.menusImagePath = menusImagePath;
	}

	public int getBusinessID() {
		return businessID;
	}

	public void setBusinessID(int businessID) {
		this.businessID = businessID;
	}
	
}
