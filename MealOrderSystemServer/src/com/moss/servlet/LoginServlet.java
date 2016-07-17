package com.moss.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.moss.dao.MemberDao;
import com.moss.utils.TextUtility;

/**
 * 
 * 项目名称：MealOrderSystemServer    
 * 类名称：LoginServlet    
 * 类描述：登录的控制类    
 * 创建人：Nicky
 * 创建时间：2016年7月4日 下午1:40:53      
 * @version
 */
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		throw new NotImplementedException();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		
		JSONObject jObject = new JSONObject();
		
		try {
			
			MemberDao dao = new MemberDao();
			boolean flag = dao.loginCheck(account, password);
			
			if(flag){
				jObject.put("ret", 0);
				jObject.put("msg", "ok");
				jObject.put("data", "");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try
			{
				jObject.put("ret", 1);
				jObject.put("msg", "error");
				jObject.put("data", "");
			} catch (JSONException ex)
			{
				ex.printStackTrace();
			}
		}
		
		PrintWriter out = response.getWriter();
		out.println(jObject);
		out.flush();
		out.close();
	}

}
