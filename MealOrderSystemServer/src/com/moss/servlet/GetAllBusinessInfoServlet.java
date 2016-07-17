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

import org.json.JSONException;
import org.json.JSONObject;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.moss.dao.BusinessDao;

public class GetAllBusinessInfoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		BusinessDao dao = new BusinessDao();
		List<Vector> infoList = new ArrayList<Vector>();
		infoList = dao.getAllBusinessInfo();
		
		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		for(Vector v:infoList){
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("businessName", v.get(0));
			map.put("sendOutPrice", v.get(1));
			map.put("distributionPrice", v.get(2));
			map.put("shopHours", v.get(3));
			map.put("businessAddress", v.get(4));
			map.put("businessDepict", v.get(5));
			map.put("notice", v.get(6));
			map.put("businessScenery", v.get(7));
			map.put("logo", v.get(8));
			list.add(map);
		}
		
		JSONObject jsonObject = new JSONObject();
		try{
			JSONObject jsonObject2 = new JSONObject();
			jsonObject2.put("totalNum", list.size());
			jsonObject2.put("list", list);
			
			jsonObject.put("ret", 0);
			jsonObject.put("data", jsonObject2);
		}catch(Exception e){
			try {
				jsonObject.put("ret", 1);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
		
		out.print(jsonObject);
		out.flush();
		out.close();
	}

	/**
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		throw new NotImplementedException();
	}

}
