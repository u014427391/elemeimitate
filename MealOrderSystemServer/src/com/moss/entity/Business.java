package com.moss.entity;

import java.util.Date;
/**
 * 
 * 项目名称：MealOrderSystemServer    
 * 类名称：Business    
 * 类描述：商家信息实体类    
 * 创建人：Nicky
 * 创建时间：2016年7月4日 下午4:05:32      
 * @version
 */
public class Business {

	/**
	 * 商家ID
	 */
	private int businessID;
	
	/**
	 * 商家名称
	 */
	private String businessName;
	
	/**
	 * 起送价钱
	 */
	private Double sendOutPrice;
	
	/**
	 * 配送价钱
	 */
	private Double distributionPrice;
	
	/**
	 * 营业时间
	 */
	private Date shopHours;
	
	/**
	 * 商家地址
	 */
	private String businessAddress;
	
	/**
	 * 商家简介
	 */
	private String businessDepict;
	
	/**
	 * 公共与活动
	 */
	private String notice;
	
	/**
	 * 商家实景图片路径
	 */
	private String businessScenery;
	
	/**
	 * 商家logo
	 */
	private String logo;
	
	/**
	 * 经营类别ID，外键
	 */
	private int categoryID;

	public int getBusinessID() {
		return businessID;
	}

	public void setBusinessID(int businessID) {
		this.businessID = businessID;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public Double getSendOutPrice() {
		return sendOutPrice;
	}

	public void setSendOutPrice(Double sendOutPrice) {
		this.sendOutPrice = sendOutPrice;
	}

	public Double getDistributionPrice() {
		return distributionPrice;
	}

	public void setDistributionPrice(Double distributionPrice) {
		this.distributionPrice = distributionPrice;
	}

	public Date getShopHours() {
		return shopHours;
	}

	public void setShopHours(Date shopHours) {
		this.shopHours = shopHours;
	}

	public String getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}

	public String getBusinessDepict() {
		return businessDepict;
	}

	public void setBusinessDepict(String businessDepict) {
		this.businessDepict = businessDepict;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getBusinessScenery() {
		return businessScenery;
	}

	public void setBusinessScenery(String businessScenery) {
		this.businessScenery = businessScenery;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	
}
