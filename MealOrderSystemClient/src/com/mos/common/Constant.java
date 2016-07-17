package com.mos.common;

/**
 * Constane类，保存一些全局的常量和内部类
 * 
 */
public class Constant {

	//WEB服务器
	public static final String URL_WEB_SERVER = "http://172.16.107.29:8080/MealOrderSystemServer";
	//登录验证的URL
	public static final String URL_Login = URL_WEB_SERVER + "/LoginServlet";
	//注册的URL
	public static final String URL_Register = URL_WEB_SERVER + "/RegisterServlet";
	
	//获取用户订单的URL
	public static final String URL_GetOrder = URL_WEB_SERVER + "/GetMemberOrderServlet";
	//提交订单的URL
	public static final String URL_AddOrder = URL_WEB_SERVER + "/AddOrderServlet";
	
	//获取所有的商家信息
	public static final String URL_GetBusinessInfo = URL_WEB_SERVER + "/GetAllBusinessInfoServlet";
	
	//通过ID获取改商家的所有商品信息
	public static final String URL_GetMenus = URL_WEB_SERVER + "/GetMenusServlet";
	//验证用户是否登录的内部类
	public static class LoginMsg {
		public static boolean isLogin = false;
	}
}
