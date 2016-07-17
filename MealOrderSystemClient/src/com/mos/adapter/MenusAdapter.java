package com.mos.adapter;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.mos.activity.R;
import com.mos.common.Constant;

/**
 * 餐品信息的适配器
 *
 */
public class MenusAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private Context context;
	private List<HashMap<String,Object>> datas;
	
	public MenusAdapter(Context context,List<HashMap<String,Object>> datas){
		inflater = LayoutInflater.from(context);
		this.context = context;
		this.datas = datas;
	}
	
	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder ;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.item_menu_content, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.iv_image = (ImageView)convertView.findViewById(R.id.item_menu_content_img);
			viewHolder.tv_menusName = (TextView)convertView.findViewById(R.id.item_menu_content_title);
			viewHolder.tv_price = (TextView)convertView.findViewById(R.id.item_menu_content_price);
			
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder)convertView.getTag();
		}
	
		//创建一个RequestQueue对象
		RequestQueue requestQueue = Volley.newRequestQueue(context);
		     	
	    //创建ImageRequest对象
		ImageRequest imageRequest = new ImageRequest(
				Constant.URL_WEB_SERVER+datas.get(position).get("menusImagePath").toString(),//url
		    	new Response.Listener<Bitmap>() {//监听器Listener
					@Override
		    		public void onResponse(Bitmap response) {
		    			viewHolder.iv_image.setImageBitmap(response);
		    		}
		    		//参数3、4表示图片宽高,Bitmap.Config.ARGB_8888表示图片每个像素占据4个字节大小
		    	}, 0, 0, Config.ARGB_8888, new Response.ErrorListener() {//图片加载请求失败的回调Listener
		    			@Override
		    			public void onErrorResponse(VolleyError error) {
		    				viewHolder.iv_image.setImageResource(R.drawable.ic_normal_pic);
		    			}
		    	});
		//将ImageRequest加载到Queue
		 requestQueue.add(imageRequest);
		    
		viewHolder.tv_menusName.setText(datas.get(position).get("menuName").toString());
		viewHolder.tv_price.setText("￥"+datas.get(position).get("total_price").toString());
		return convertView;
	}
	
	class ViewHolder{
		ImageView iv_image;//图片的ImageView
		TextView tv_menusName;//餐品名称的TextView
		TextView tv_price;//餐品价钱的TextView
	}

}
