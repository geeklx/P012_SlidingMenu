package com.liangxiao.myslidingmenu;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class MainUI extends RelativeLayout {

	private Context context;
	private FrameLayout leftMenu;
	private FrameLayout middleMenu;
	private FrameLayout rightMenu;

	public MainUI(Context context) {
		super(context);
		initView(context);
	}

	public MainUI(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	private void initView(Context context) {
		this.context = context;
		leftMenu = new FrameLayout(context);
		middleMenu = new FrameLayout(context);
		rightMenu = new FrameLayout(context);

		leftMenu.setBackgroundColor(Color.argb(255, 0, 255, 0));
		middleMenu.setBackgroundColor(Color.WHITE);
		rightMenu.setBackgroundColor(Color.TRANSPARENT);

		addView(leftMenu);
		addView(middleMenu);
		addView(rightMenu);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		middleMenu.measure(widthMeasureSpec, heightMeasureSpec);
		// 屏幕的宽度
		int realWidth = MeasureSpec.getSize(widthMeasureSpec);
		// 左右显示的宽度
		int tempWidthMeasure_left = MeasureSpec.makeMeasureSpec(
				(int) (realWidth * 0.4f), MeasureSpec.EXACTLY);
		int tempWidthMeasure_right = MeasureSpec.makeMeasureSpec(
				(int) (realWidth * 0.8f), MeasureSpec.EXACTLY);
		// 设置左右
		leftMenu.measure(tempWidthMeasure_left, heightMeasureSpec);
		rightMenu.measure(tempWidthMeasure_right, heightMeasureSpec);

	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
		middleMenu.layout(l, t, r, b);
		leftMenu.layout(l - leftMenu.getMeasuredWidth(), t, r, b);
		rightMenu.layout(
				l + middleMenu.getMeasuredWidth(),
				t,
				l + middleMenu.getMeasuredWidth()
						+ rightMenu.getMeasuredWidth(), b);

	}

	private Boolean isTestCompete = false;
	private Boolean isLeftRightEvent;

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (!isTestCompete) {
			getEventType(ev);
			return true;
		}
		if (isLeftRightEvent) {
			switch (ev.getActionMasked()) {
			case MotionEvent.ACTION_MOVE:
				int curScrollX = getScrollX();// 滚动的距离
				int dis_x = (int) (ev.getX() - point.x);// 滑动的距离
				int expectX = -dis_x + curScrollX;
				int finalX = 0;
				if (expectX < 20) {
					finalX = Math.max(expectX, -leftMenu.getMeasuredWidth());
				} else {
					finalX = Math.min(expectX, rightMenu.getMeasuredWidth());
				}
				scrollTo(finalX, 0);
				point.x = (int) ev.getX();
				break;
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_CANCEL:
				isLeftRightEvent = false;
				isTestCompete = false;
				break;
			default:
				break;
			}
		} else {

		}

		return super.dispatchTouchEvent(ev);
	}

	private Point point = new Point();
	private static final int TEST_DIS = 20;

	private void getEventType(MotionEvent ev) {
		switch (ev.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
			point.x = (int) ev.getX();
			point.y = (int) ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			int dx = (int) Math.abs(ev.getX() - point.x);// 取绝对值与正负无关
			int dy = (int) Math.abs(ev.getY() - point.y);
			if (dx >= TEST_DIS && dx > dy) {
				// 左右滑动
				isLeftRightEvent = true;
				isTestCompete = true;
				point.x = (int) ev.getX();
				point.y = (int) ev.getY();

			} else if (dy >= TEST_DIS && dy > dx) {
				// 上下滑动
				isLeftRightEvent = false;
				isTestCompete = true;
				point.x = (int) ev.getX();
				point.y = (int) ev.getY();
			}
			break;
		case MotionEvent.ACTION_UP:

			break;
		case MotionEvent.ACTION_CANCEL:

			break;
		default:
			break;
		}
	}
}
