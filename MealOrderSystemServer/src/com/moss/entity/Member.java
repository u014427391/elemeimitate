package com.moss.entity;

/**
 * 
 * 项目名称：MealOrderSystemServer    
 * 类名称：Member    
 * 类描述：会员信息的实体类    
 * 创建人：Nicky
 * 创建时间：2016年7月2日 上午12:36:23      
 * @version
 */
public class Member {
	
	/**
	 * 会员账号
	 */
	private String memberID;
	
	/**
	 * 会员密码
	 */
	private String password;
	
	/**
	 * 会员等级
	 */
	private String rank;
	
	/**
	 * 会员积分
	 */
	private int credit;

	/**
	 * 会员手机号
	 */
	private String phone;
	
	/**
	 * 会员上传的头像图片路径
	 */
	private String imgPath;

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
}
