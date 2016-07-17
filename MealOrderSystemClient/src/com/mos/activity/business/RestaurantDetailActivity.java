package com.mos.activity.business;


import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mos.activity.R;
import com.mos.utils.InjectView;
import com.mos.utils.Injector;

public class RestaurantDetailActivity  extends Activity {
	
	 /**
     * 商品总价
     */
    @InjectView(R.id.m_list_all_price)
    TextView mListAllPrice;
    
    /**
     * 物品总数量
     */
    @InjectView(R.id.m_list_num)
    TextView mListAllNum;
    
    public static int SELECTPOSITION = 0;
    
    /**
     * 购物车布局
     */
    @InjectView(R.id.m_list_car_lay)
    RelativeLayout mCarLay;

    @Override
    protected void onCreate(Bundle arg0) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(arg0);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_restaurant_main);
        Injector.get(this).inject();//init views
        initView();
    }
    
    public void initView(){
    	
    }
   


}
