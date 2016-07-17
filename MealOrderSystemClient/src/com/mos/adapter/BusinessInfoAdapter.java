package com.mos.adapter;

import java.util.HashMap;
import java.util.List;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.mos.activity.R;
import com.mos.adapter.OrderAdapter.ViewHolder;
import com.mos.common.Constant;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BusinessInfoAdapter extends BaseAdapter{

	LayoutInflater inflater;
	Context context;
	private List<HashMap<String,Object>> datas;
	
	public BusinessInfoAdapter(Context context,List<HashMap<String,Object>> datas){
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
			convertView = inflater.inflate(R.layout.item_list_restaurant, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.iv_logo = (ImageView)convertView.findViewById(R.id.logo_iv);
			viewHolder.tv_businessName = (TextView)convertView.findViewById(R.id.business_name_tv);
			viewHolder.tv_sendOutPrice = (TextView)convertView.findViewById(R.id.sendOutPrice_tv);
			viewHolder.tv_distributionPrice = (TextView)convertView.findViewById(R.id.distributionPrice_tv);
			viewHolder.tv_shopHours = (TextView)convertView.findViewById(R.id.shopHours_tv);
			
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder)convertView.getTag();
		}
	
		//创建一个RequestQueue对象
		RequestQueue requestQueue = Volley.newRequestQueue(context);
		     	
	    //创建ImageRequest对象
		ImageRequest imageRequest = new ImageRequest(
				Constant.URL_WEB_SERVER+datas.get(position).get("logo").toString(),//url
		    	new Response.Listener<Bitmap>() {//监听器Listener
					@Override
		    		public void onResponse(Bitmap response) {
		    			viewHolder.iv_logo.setImageBitmap(response);
		    		}
		    		//参数3、4表示图片宽高,Bitmap.Config.ARGB_8888表示图片每个像素占据4个字节大小
		    	}, 0, 0, Config.ARGB_8888, new Response.ErrorListener() {//图片加载请求失败的回调Listener
		    			@Override
		    			public void onErrorResponse(VolleyError error) {
		    				viewHolder.iv_logo.setImageResource(R.drawable.ic_normal_pic);
		    			}
		    	});
		//将ImageRequest加载到Queue
		 requestQueue.add(imageRequest);
		    
		viewHolder.tv_businessName.setText(datas.get(position).get("businessName").toString());
		viewHolder.tv_sendOutPrice.setText("￥"+datas.get(position).get("sendOutPrice").toString());
		viewHolder.tv_distributionPrice.setText("配送价:"+datas.get(position).get("distributionPrice").toString());
		viewHolder.tv_shopHours.setText("送货时间:"+datas.get(position).get("shopHours").toString());
		return convertView;
	}
	
	class ViewHolder{
		ImageView iv_logo;
		TextView tv_businessName;
		TextView tv_sendOutPrice;
		TextView tv_distributionPrice;
		TextView tv_shopHours;
	}

}
