package com.mos.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;

import com.mos.activity.R;
import com.mos.activity.business.RestaurantDetailActivity;
import com.mos.adapter.BusinessInfoAdapter;
import com.mos.adapter.GridViewAdapter;
import com.mos.common.Constant;
import com.mos.service.SyncHttp;
import com.mos.view.RefreshableListView;
import com.mos.view.RefreshableListView.OnRefreshListener;
import com.mos.view.ab.AbOnItemClickListener;
import com.mos.view.ab.AbSlidingPlayView;

/**
 * 首页的Fragment
 *
 */
public class EleFragment extends Fragment{

	    private View view;
	  
	    private RefreshableListView listview;
	    
	    private View listHeaderView;
		
		//分类的九宫格
		private GridView gridView_classify;
		
		//GridView的适配器
		private GridViewAdapter classify_adapter;
		
		//首页轮播
		private AbSlidingPlayView viewPager;// 分类九宫格的资源文件
		
		private int[] pic_path_classify = { R.drawable.ic_category_1, R.drawable.ic_category_2,R.drawable.ic_category_3,R.drawable.ic_category_4,
				 R.drawable.ic_category_5, R.drawable.ic_category_6, R.drawable.ic_category_7, R.drawable.ic_category_8};
		private String[] text_classify = {"餐饮","专送","KTV","新商家","优惠","精选","甜品饮品","超市"};
		/**存储首页轮播的界面*/
		private ArrayList<View> allListView;
		/**首页轮播的界面的资源*/
		private int[] resId = { R.drawable.show_m1, R.drawable.menu_viewpager_1, R.drawable.menu_viewpager_2, R.drawable.menu_viewpager_3, R.drawable.menu_viewpager_4, R.drawable.menu_viewpager_5 };

	  
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        view = inflater.inflate(R.layout.fragment_ele,container,false);
			
			initView(view);
			
	        return view;
	    }
	    
	    public void initView(View view){
	    	
	    	listHeaderView = getActivity().getLayoutInflater().inflate(
					R.layout.home_layout_view, null);
			
			gridView_classify = (GridView) listHeaderView.findViewById(R.id.my_gridview);
			gridView_classify.setSelector(new ColorDrawable(Color.TRANSPARENT));
			classify_adapter = new GridViewAdapter(getActivity(), pic_path_classify,text_classify);
			gridView_classify.setAdapter(classify_adapter);
	    	
	    	viewPager = (AbSlidingPlayView) listHeaderView.findViewById(R.id.viewPager_menu);
			//设置播放方式为顺序播放
			viewPager.setPlayType(1);
			//设置播放间隔时间
			viewPager.setSleepTime(3000);
			
			initViewPager();
			
			listview = (RefreshableListView)view.findViewById(R.id.listview);
			new GetAllInfoAsyncTask().execute(Constant.URL_GetBusinessInfo);
			listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					HashMap<String, Object> map = (HashMap<String,Object>)arg0.getItemAtPosition(arg2);
					Intent intent = new Intent(getActivity(), RestaurantDetailActivity.class);
					intent.putExtra("map", map);
					startActivity(intent);
				}
		
			});
			
			listview.setOnRefreshListener(new OnRefreshListener() {

				@Override
				public void onRefresh(RefreshableListView listView) {
					new GetAllInfoAsyncTask().execute(Constant.URL_GetBusinessInfo);
				}
			});
			
			listview.addHeaderView(listHeaderView);
			
	    }
	    
	    private void initViewPager() {

			if (allListView != null) {
				allListView.clear();
				allListView = null;
			}
			allListView = new ArrayList<View>();

			for (int i = 0; i < resId.length; i++) {
				//导入ViewPager的布局
				View view = LayoutInflater.from(getActivity()).inflate(R.layout.pic_item, null);
				ImageView imageView = (ImageView) view.findViewById(R.id.pic_item);
				imageView.setImageResource(resId[i]);
				allListView.add(view);
			}
			
			
			viewPager.addViews(allListView);
			//开始轮播
			viewPager.startPlay();
			viewPager.setOnItemClickListener(new AbOnItemClickListener() {
				@Override
				public void onClick(int position) {
					//跳转到详情界面
				}
			});
		}
	    
	    
	    public List<HashMap<String,Object>> getAllInfo(String url){
	    	List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
	    	String params = "";
	    	
	    	SyncHttp syncHttp = new SyncHttp();
	    	try{
	    		String retStr = syncHttp.httpGet(url, params);
	    		JSONObject jsonObject = new JSONObject(retStr);
	    		
	    		int ret = jsonObject.getInt("ret");
	    		if(ret==0){
	    			JSONObject dataObject = jsonObject.getJSONObject("data");
	    			
	    			int totalNum = dataObject.getInt("totalNum");
	    			if(totalNum>0){
	    				JSONArray infoList = dataObject.getJSONArray("list");
	    				for(int i=0;i<infoList.length();i++){
	    					JSONObject infoMap = (JSONObject)infoList.opt(i);
	    					HashMap<String,Object> map = new HashMap<String,Object>();
	    					map.put("businessName", infoMap.get("businessName"));
	    					map.put("sendOutPrice", infoMap.get("sendOutPrice"));
	    					map.put("distributionPrice", infoMap.get("distributionPrice"));
	    					map.put("shopHours", infoMap.get("shopHours"));
	    					map.put("businessAddress", infoMap.get("businessAddress"));
	    					map.put("businessDepict", infoMap.get("businessDepict"));
	    					map.put("notice", infoMap.get("notice"));
	    					map.put("businessScenery", infoMap.get("businessScenery"));
	    					map.put("logo", infoMap.get("logo"));
	    					list.add(map);
	    				}
	    			}
	    		}
	    		
	    	}catch (Exception e) {
	    		e.printStackTrace();
			}
	    	return list;
	    }
	    
	    class GetAllInfoAsyncTask extends AsyncTask<String, Void, List<HashMap<String,Object>>>{
	    	/**第1个参数String是传入的参数类型,修改的话,doInBackground的方法参数类型也要修改
			 * 参数3是doInBackground的返回类型,修改的话,doInBackground的参数返回类型也要修改
			 * **/
			@Override
			protected List<HashMap<String,Object>> doInBackground(String... params) {
				List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
				try{
					list = getAllInfo(params[0]);
				}catch (Exception e) {
					// TODO: handle exception
				}
				
				return list;
			}
			
			/**doInBackground获取到的List<HashMap<String,Object>>参数传入到onPostExecute里调用**/	
			@Override
			protected void onPostExecute(List<HashMap<String, Object>> list) {
				// TODO Auto-generated method stub
				BusinessInfoAdapter adapter = new BusinessInfoAdapter(getActivity(), list);
				listview.setAdapter(adapter);
				
			}
	    }


}
