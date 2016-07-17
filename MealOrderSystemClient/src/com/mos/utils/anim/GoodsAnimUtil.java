package com.mos.utils.anim;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import com.mos.activity.R;
import com.nineoldandroids.animation.Animator;
/**
 * Copy from fml on 2015/12/18 0018.
 */
public abstract class GoodsAnimUtil {
    private static View view;
    private static Animation animation;
    /** 动画层 */
    private static ViewGroup anim_mask_layout;
    private static Activity mActivity;
    private static View mImgcar;
    private static OnEndAnimListener mEndAnimListener;
    /** 定义结束之后的接口 */
    public interface OnEndAnimListener{
        void onEndAnim();
    }
    public static void setOnEndAnimListener(OnEndAnimListener listenr){
       mEndAnimListener = listenr;
    }
    public static void setAnim(Activity activity , View imgphoto , View imgcar){
        mActivity = activity;
        mImgcar = imgcar;
        // 一个整型数组，用来存储按钮的在屏幕的X、Y坐标
        int[] start_location = new int[2];
        // 这是获取购买按钮的在屏幕的X、Y坐标（这也是动画开始的坐标）
        imgphoto.getLocationInWindow(start_location);
        int[] start_location1 = new int[]{start_location[0], start_location[1]};
        // buyImg是动画的图片，我的是一个小球（R.drawable.sign）
        ImageView buyImg = new ImageView(mActivity);
        // 设置buyImg的图片
        buyImg.setImageResource(R.drawable.aii);
        // 开始执行动画
        startAnim(buyImg, start_location1);
    }
    /**
     *开始动画
     */
    private static void startAnim(final View v, int[] start_location) {
        anim_mask_layout = null;
        anim_mask_layout = createAnimLayout();
        anim_mask_layout.addView(v);//把动画小球添加到动画层
        view = addViewToAnimLayout(anim_mask_layout, v,start_location);
        int[] end_location = new int[2];// 这是用来存储动画结束位置的X、Y坐标
        mImgcar.getLocationInWindow(end_location);// shopCart是那个购物车
        int width = getWindowsWidth(mActivity);
        // 计算位移
        int endY = end_location[1] - start_location[1];// 动画位移的y坐标
        int endX = end_location[0] - start_location[0] + (mImgcar.getWidth() / 2);// 动画位移的X坐标
        TranslateAnimation translateAnimationX = new TranslateAnimation(0,endX, 0, 0);
        translateAnimationX.setInterpolator(new LinearInterpolator());
        translateAnimationX.setRepeatCount(0);// 动画重复执行的次数
        translateAnimationX.setFillAfter(true);
        TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0,0, endY);
        translateAnimationY.setInterpolator(new AccelerateInterpolator());
        translateAnimationY.setRepeatCount(0);// 动画重复执行的次数
        translateAnimationX.setFillAfter(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.3f, 1.0f, 0.3f);
        scaleAnimation.setInterpolator(new AccelerateInterpolator());
        scaleAnimation.setRepeatCount(0);// 动画重复执行的次数
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(300);
        final AnimationSet set = new AnimationSet(false);
        set.setFillAfter(false);
        set.addAnimation(translateAnimationY);
        set.addAnimation(translateAnimationX);
        //set.setStartOffset(300);
        set.setDuration(800);// 动画的执行时间
        view.startAnimation(set);
        // 动画监听事件
        set.setAnimationListener(new Animation.AnimationListener() {
            // 动画的开始
            @Override
            public void onAnimationStart(Animation animation) {
                v.setVisibility(View.VISIBLE);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
            // 动画的结束
            @Override
            public void onAnimationEnd(Animation animation) {
                v.setVisibility(View.GONE);
                anim_mask_layout.removeAllViews();
                YoYo.with(Techniques.Bounce).withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {}
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mEndAnimListener.onEndAnim();
                    }
                    @Override
                    public void onAnimationCancel(Animator animation) {}
                    @Override
                    public void onAnimationRepeat(Animator animation) {}
                }).interpolate(new BounceInterpolator()).duration(400).playOn(mImgcar);
            }
        });
    }
    /**
     * @param
     * @return void
     * @throws
     * @Description: 创建动画层
     */
    private static ViewGroup createAnimLayout() {
        ViewGroup rootView = (ViewGroup)mActivity.getWindow().getDecorView();
        LinearLayout animLayout = new LinearLayout(mActivity);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setId(Integer.MAX_VALUE);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }
    private static View addViewToAnimLayout(final ViewGroup vg, final View view,int[] location) {
        int x = location[0];
        int y = location[1];
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setLayoutParams(lp);
        return view;
    }
    /**
     * 获取屏幕的宽度
     */
    public final static int getWindowsWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
}
