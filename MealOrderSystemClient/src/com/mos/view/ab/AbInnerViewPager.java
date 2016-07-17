package com.mos.view.ab;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * 
 * ��ƣ�AbInnerViewPager.java 
 * ���������ViewPager������ⲿ�ǿɹ���View��List����scrollView��
 * ���ڲ��ɻ���View���¼���ͻ����
 */
@SuppressWarnings("deprecation")
public class AbInnerViewPager extends ViewPager {

	/** The parent scroll view. */
	private ScrollView parentScrollView;
	
	/** The parent list view. */
	private ListView parentListView;
	
	private GestureDetector mGestureDetector;
	
	/**
	 * ��ʼ������ڲ���ViewPager.
	 *
	 * @param context the context
	 */
	
	public AbInnerViewPager(Context context) {
		super(context);
		mGestureDetector = new GestureDetector(new YScrollDetector());
		setFadingEdgeLength(0);
	}

	/**
	 * ��ʼ������ڲ���ViewPager.
	 *
	 * @param context the context
	 * @param attrs the attrs
	 */
	public AbInnerViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		mGestureDetector = new GestureDetector(new YScrollDetector());
		setFadingEdgeLength(0);
	}
	
	/**
	 * �����������¼�.
	 *
	 * @param ev the ev
	 * @return true, if successful
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return super.onInterceptTouchEvent(ev)
				&& mGestureDetector.onTouchEvent(ev);
	}

	/**
	 * ���ø�����View.
	 *
	 * @param flag ���Ƿ��������
	 */
	private void setParentScrollAble(boolean flag) {
		if(parentScrollView!=null){
			parentScrollView.requestDisallowInterceptTouchEvent(!flag);
		}
		
		if(parentListView!=null){
			parentListView.requestDisallowInterceptTouchEvent(!flag);
		}
		
	}

	/**
	 * ��������ScrollView��Ҫ����.
	 *
	 * @param parentScrollView the new parent scroll view
	 */
	public void setParentScrollView(ScrollView parentScrollView) {
		this.parentScrollView = parentScrollView;
	}
	
	/**
	 * ��������ListView��Ҫ����.
	 *
	 * @param parentListView the new parent scroll view
	 */
	public void setParentListView(ListView parentListView) {
		this.parentListView = parentListView;
	}
	
	
	class YScrollDetector extends SimpleOnGestureListener {
		
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			
			if (Math.abs(distanceX) >= Math.abs(distanceY)) {
				//���ײ�����
				setParentScrollAble(false);
				return true;
			}else{
				setParentScrollAble(true);
			}
			return false;
		}
	}

	

}
