package com.moss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.moss.dao.MenusDao;
import com.moss.utils.TextUtility;

public class GetMenusServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		throw new NotImplementedException();
	}

	/**
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String idString = request.getParameter("id");
		int businessID = TextUtility.String2Int(idString);
		MenusDao dao = new MenusDao();
		List<Vector> menusList = dao.getInfoById(businessID);
		
		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		for(Vector vector:menusList){
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("businessName", vector.get(0));
			map.put("menusName", vector.get(1));
			map.put("businessImagePath", vector.get(2));
			map.put("menusPrice", vector.get(3));
			map.put("menusDepict", vector.get(4));
			list.add(map);
		}
		
		JSONObject jsonObject = new JSONObject();
		try{
			JSONObject jsonObject2 = new JSONObject();
			jsonObject2.put("totalNum", list.size());
			jsonObject2.put("menusList", list);
			
			jsonObject.put("data", jsonObject2);
			jsonObject.put("ret", 0);
		}catch(Exception e){
			e.printStackTrace();
			try{
				jsonObject.put("ret", 1);
			}catch(Exception ex){}
		}
		out.flush();
		out.close();
	}

}
