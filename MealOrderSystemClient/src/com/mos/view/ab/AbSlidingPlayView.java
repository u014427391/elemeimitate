
package com.mos.view.ab;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;


/**
 * ��ƣ�AbPlayView �������ɲ�����ʾ��View.
 * 
 * @author tans
 * @date 2014-09-26
 * @version
 */
@SuppressLint("HandlerLeak")
public class AbSlidingPlayView extends LinearLayout {

	/** The tag. */
	private  String TAG = "AbSlidingPlayView";

	/** The Constant D. */
	@SuppressWarnings("unused")
	private  final boolean D = AbAppData.DEBUG;

	/** The context. */
	private Context context;

	/** The m view pager. */
	private AbInnerViewPager mViewPager;

	/** The page line layout. */
	private LinearLayout pageLineLayout;

	/** The layout params pageLine. */
	public LinearLayout.LayoutParams pageLineLayoutParams = null;

	/** The i. */
	private int count, position;

	/** The hide image. */
	private Bitmap displayImage, hideImage;

	/** The m on item click listener. */
	private AbOnItemClickListener mOnItemClickListener;

	/** The m ab change listener. */
	private AbOnChangeListener mAbChangeListener;

	/** The m ab scrolled listener. */
	private AbOnScrollListener mAbScrolledListener;

	/** The m ab touched listener. */
	private AbOnTouchListener mAbOnTouchListener;

	/** The layout params ff. */
	public LinearLayout.LayoutParams layoutParamsFF = null;

	/** The layout params fw. */
	public LinearLayout.LayoutParams layoutParamsFW = null;

	/** The layout params wf. */
	public LinearLayout.LayoutParams layoutParamsWF = null;

	/** The m list views. */
	private ArrayList<View> mListViews = null;

	/** The m ab view pager adapter. */
	private AbViewPagerAdapter mAbViewPagerAdapter = null;

	/** �����ĵ㸸View */
	private LinearLayout mPageLineLayoutParent = null;

	/** The page line horizontal gravity. */
	private int pageLineHorizontalGravity = Gravity.RIGHT;

	/** ���ŵķ��� */
	private int playingDirection = 0;

	/** ���ŵĿ��� */
	private boolean play = false;
	/** ���ŵļ��ʱ�� */
	private int sleepTime = 5000;
	/** ���ŷ���ʽ��1˳�򲥷ź�0���ز��ţ� */
	private int playType = 1;

	/**
	 * ����һ��AbSlidingPlayView
	 * 
	 * @param context
	 */
	public AbSlidingPlayView(Context context) {
		super(context);
		initView(context);
	}

	/**
	 * ��xml��ʼ����AbSlidingPlayView
	 * 
	 * @param context
	 *            the context
	 * @param attrs
	 *            the attrs
	 */
	public AbSlidingPlayView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}
	

	/**
	 * 
	 * ��������ʼ�����View
	 * 
	 * @param context
	 * @throws
	 */
	public void initView(Context context) {
		this.context = context;
		layoutParamsFF = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		layoutParamsFW = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		layoutParamsWF = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		pageLineLayoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		this.setOrientation(LinearLayout.VERTICAL);
		this.setBackgroundColor(Color.rgb(255, 255, 255));

		RelativeLayout mRelativeLayout = new RelativeLayout(context);

		mViewPager = new AbInnerViewPager(context);
		// �ֶ�������ViewPager,�����fragment�������setId()��������һ��id
		mViewPager.setId(1985);
		// �����ĵ�
		mPageLineLayoutParent = new LinearLayout(context);
		mPageLineLayoutParent.setPadding(0, 5, 0, 5);
		pageLineLayout = new LinearLayout(context);
		pageLineLayout.setPadding(15, 1, 15, 1);
		pageLineLayout.setVisibility(View.INVISIBLE);
		mPageLineLayoutParent.addView(pageLineLayout, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		lp1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
		lp1.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
		lp1.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		mRelativeLayout.addView(mViewPager, lp1);

		RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		lp2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
		mRelativeLayout.addView(mPageLineLayoutParent, lp2);
		addView(mRelativeLayout, layoutParamsFW);
		
		//�õ��������ͼƬ��Դ�ļ�
		displayImage =getBitmapFormSrc("play_display.png");
		hideImage =getBitmapFormSrc("play_hide.png");

		mListViews = new ArrayList<View>();
		mAbViewPagerAdapter = new AbViewPagerAdapter(context, mListViews);
		mViewPager.setAdapter(mAbViewPagerAdapter);
		mViewPager.setFadingEdgeLength(0);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				makesurePosition();
				onPageSelectedCallBack(position);
			}

			@Override
			public void onPageScrollStateChanged(int state) {
			}

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				onPageScrolledCallBack(position);
			}

		});

	}
	
	/**
	 * �õ���ȡͼƬ
	 * @param name ͼƬ������
	 * @return
	 */
	private Bitmap getBitmapFormSrc(String name){
		Bitmap bitmap=null;
		
		try {
			InputStream is=getResources().getAssets().open(name);
			bitmap=BitmapFactory.decodeStream(is);
		} catch (IOException e) {
			Log.d(TAG, "��ȡͼƬ�쳣��"+e.getMessage());
		}
		return bitmap;
	}

	/**
	 * ����������.
	 */
	public void creatIndex() {
		// ��ʾ����ĵ�
		pageLineLayout.removeAllViews();
		mPageLineLayoutParent.setHorizontalGravity(pageLineHorizontalGravity);
		pageLineLayout.setGravity(Gravity.CENTER);
		pageLineLayout.setVisibility(View.VISIBLE);
		count = mListViews.size();
		for (int j = 0; j < count; j++) {
			ImageView imageView = new ImageView(context);
			pageLineLayoutParams.setMargins(5, 5, 5, 5);
			imageView.setLayoutParams(pageLineLayoutParams);
			if (j == 0) {
				imageView.setImageBitmap(displayImage);
			} else {
				imageView.setImageBitmap(hideImage);
			}
			pageLineLayout.addView(imageView, j);
		}
	}

	/**
	 * ��λ���λ��.
	 */
	public void makesurePosition() {
		position = mViewPager.getCurrentItem();
		for (int j = 0; j < count; j++) {
			if (position == j) {
				((ImageView) pageLineLayout.getChildAt(position)).setImageBitmap(displayImage);
			} else {
				((ImageView) pageLineLayout.getChildAt(j)).setImageBitmap(hideImage);
			}
		}
	}

	/**
	 * ��������ӿɲ�����ͼ.
	 * 
	 * @param view
	 *            the view
	 */
	public void addView(View view) {
		mListViews.add(view);
		if (view instanceof AbsListView) {
		} else {
			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (mOnItemClickListener != null) {
						mOnItemClickListener.onClick(position);
					}
				}
			});
			view.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View view, MotionEvent event) {
					if (mAbOnTouchListener != null) {
						mAbOnTouchListener.onTouch(event);
					}
					return false;
				}
			});
		}

		mAbViewPagerAdapter.notifyDataSetChanged();
		creatIndex();
	}

	/**
	 * ��������ӿɲ�����ͼ�б�.
	 * 
	 * @param views
	 *            the views
	 */
	public void addViews(List<View> views) {
		mListViews.addAll(views);
		for (View view : views) {
			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (mOnItemClickListener != null) {
						mOnItemClickListener.onClick(position);
					}
				}
			});

			view.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View view, MotionEvent event) {
					if (mAbOnTouchListener != null) {
						mAbOnTouchListener.onTouch(event);
					}
					return false;
				}
			});
		}
		mAbViewPagerAdapter.notifyDataSetChanged();
		creatIndex();
	}

	/**
	 * ������ɾ��ɲ�����ͼ.
	 * 
	 */
	@Override
	public void removeAllViews() {
		mListViews.clear();
		mAbViewPagerAdapter.notifyDataSetChanged();
		creatIndex();
	}

	/**
	 * ����������ҳ���л��¼�.
	 * 
	 * @param position
	 *            the position
	 */
	private void onPageScrolledCallBack(int position) {
		if (mAbScrolledListener != null) {
			mAbScrolledListener.onScroll(position);
		}

	}

	/**
	 * ����������ҳ���л��¼�.
	 * 
	 * @param position
	 *            the position
	 */
	private void onPageSelectedCallBack(int position) {
		if (mAbChangeListener != null) {
			mAbChangeListener.onChange(position);
		}

	}

	/** �����ֻ��� handler. */
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0) {
				//��ʾ���ŵ�ҳ��
				ShowPlay();
				if (play) {
					handler.postDelayed(runnable, sleepTime);
				}
			}
		}

	};

	/** �����ֲ����߳�. */
	private Runnable runnable = new Runnable() {
		public void run() {
			if (mViewPager != null) {
				handler.sendEmptyMessage(0);
			}
		}
	};

	/**
	 * �������Զ��ֲ�. sleepTime ���ŵļ��ʱ��
	 */
	public void startPlay() {
		if (handler != null) {
			play = true;
			handler.postDelayed(runnable, sleepTime);
		}
	}

	/**
	 * �������Զ��ֲ�.
	 */
	public void stopPlay() {
		if (handler != null) {
			play = false;
			handler.removeCallbacks(runnable);
		}
	}

	/**
	 * ���õ���¼�����.
	 * 
	 * @param onItemClickListener
	 *            the new on item click listener
	 */
	public void setOnItemClickListener(AbOnItemClickListener onItemClickListener) {
		mOnItemClickListener = onItemClickListener;
	}

	/**
	 * ����������ҳ���л��ļ�����.
	 * 
	 * @param abChangeListener
	 *            the new on page change listener
	 */
	public void setOnPageChangeListener(AbOnChangeListener abChangeListener) {
		mAbChangeListener = abChangeListener;
	}

	/**
	 * ����������ҳ�滬���ļ�����.
	 * 
	 * @param abScrolledListener
	 *            the new on page scrolled listener
	 */
	public void setOnPageScrolledListener(AbOnScrollListener abScrolledListener) {
		mAbScrolledListener = abScrolledListener;
	}

	/**
	 * 
	 * ����������ҳ��Touch�ļ�����.
	 * 
	 * @param abOnTouchListener
	 * @throws
	 */
	public void setOnTouchListener(AbOnTouchListener abOnTouchListener) {
		mAbOnTouchListener = abOnTouchListener;
	}

	/**
	 * Sets the page line image.
	 * 
	 * @param displayImage
	 *            the display image
	 * @param hideImage
	 *            the hide image
	 */
	public void setPageLineImage(Bitmap displayImage, Bitmap hideImage) {
		this.displayImage = displayImage;
		this.hideImage = hideImage;
		creatIndex();

	}

	/**
	 * ��������ȡ���������ViewPager��.
	 * 
	 * @return the view pager
	 */
	public ViewPager getViewPager() {
		return mViewPager;
	}

	/**
	 * ��������ȡ��ǰ��View������.
	 * 
	 * @return the count
	 */
	public int getCount() {
		return mListViews.size();
	}

	/**
	 * ����������ҳ��ʾ����λ��,��AddViewǰ����.
	 * 
	 * @param horizontalGravity
	 *            the new page line horizontal gravity
	 */
	public void setPageLineHorizontalGravity(int horizontalGravity) {
		pageLineHorizontalGravity = horizontalGravity;
	}

	/**
	 * ��������ScrollView��Ҫ����.
	 * 
	 * @param parentScrollView
	 *            the new parent scroll view
	 */
	public void setParentScrollView(ScrollView parentScrollView) {
		this.mViewPager.setParentScrollView(parentScrollView);
	}

	/**
	 * ��������ListView��Ҫ����.
	 * 
	 * @param parentListView
	 *            the new parent list view
	 */
	public void setParentListView(ListView parentListView) {
		this.mViewPager.setParentListView(parentListView);
	}

	/**
	 * 
	 * ���������õ�����ı���
	 * 
	 * @param resid
	 * @throws
	 */
	public void setPageLineLayoutBackground(int resid) {
		pageLineLayout.setBackgroundResource(resid);
	}

	/**
	 * ���������ò��ŵļ��ʱ��
	 * @param sleepTime  ���ʱ�䵥λ�Ǻ���
	 */
	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}

	/**
	 *  ���������ò��ŷ���ķ�ʽ��1˳�򲥷ź�0���ز��ţ� playType 
	 * @param playType    Ϊ0��ʾ���ز��ţ�Ϊ1��ʾ˳�򲥷�
	 */
	public void setPlayType(int playType) {
		this.playType = playType;
	}


	/**
	 * ������������ʾ���棨1˳�򲥷ź�0���ز��ţ� playType Ϊ0��ʾ���ز��ţ�Ϊ1��ʾ˳�򲥷�
	 */
	public void ShowPlay() {
		//��ҳ��
		int count = mListViews.size();
		// ��ǰ��ʾ��ҳ��
		int i = mViewPager.getCurrentItem();
		switch (playType) {
		case 0:
			// ���ز���
			if (playingDirection == 0) {
				if (i == count - 1) {
					playingDirection = -1;
					i--;
				} else {
					i++;
				}
			} else {
				if (i == 0) {
					playingDirection = 0;
					i++;
				} else {
					i--;
				}
			}
			break;
		case 1:
			// ˳�򲥷�
			if (i == count - 1) {
				i = 0;
			} else {
				i++;
			}

			break;

		default:
			break;
		}
		// ������ʾ�ڼ�ҳ
		mViewPager.setCurrentItem(i, true);
	}

}
