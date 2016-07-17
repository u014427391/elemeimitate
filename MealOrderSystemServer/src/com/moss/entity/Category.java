package com.moss.entity;
/**
 * 
 * 项目名称：MealOrderSystemServer    
 * 类名称：Category    
 * 类描述：经营类别信息实体类    
 * 创建人：Nicky
 * 创建时间：2016年7月4日 下午4:06:27      
 * @version
 */
public class Category {

	/**
	 * 经营类别id
	 */
	private int categoryID;
	
	/**
	 * 经营类别名称
	 */
	private String categoryName;

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
