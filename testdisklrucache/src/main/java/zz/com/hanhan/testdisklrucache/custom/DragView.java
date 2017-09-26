package zz.com.hanhan.testdisklrucache.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;

/**
 * Created by lenovo on 2017/1/13.
 */

public class DragView extends LinearLayout {
    private final int minimumFlingVelocity;//velocity 速度

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDragHelper();
        minimumFlingVelocity = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
    }

    private ViewDragHelper mViewDragHelper;


    private void initDragHelper() {
        mViewDragHelper = ViewDragHelper.create(DragView.this, 1.0f/8f, mDragCallback);

    }

    //通过重写onFinishInflate()来获取childView子控件
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    /**
     *  ViewDragHelper回调接口
     */
    private ViewDragHelper.Callback mDragCallback = new ViewDragHelper.Callback() {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {//可以用来指定哪一个childView可以拖动
            return true;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {// 水平拖动
            return 0;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {//竖直拖动
//            return Math.min(0, top);//竖直向上
            return Math.max(0, top);//竖直向下
        }
        @Override
        public int getViewHorizontalDragRange(View child) {
            return 0;
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return getHeight();
        }

        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            if (callback != null) {
                callback.onPullStart();
            }
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            if (callback != null) {
                callback.onPull((float) top / (float) getHeight());
            }
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            int slop = yvel > minimumFlingVelocity ? getHeight() / 6 : getHeight() / 3;
            if (releasedChild.getTop() > slop) {
                if (callback != null) {
                    callback.onPullComplete();
                }
            } else {
                if (callback != null) {
                    callback.onPullCancel();
                }

                mViewDragHelper.settleCapturedViewAt(0, 0);
                invalidate();
            }
        }

    };

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {//拦截事件
        return mViewDragHelper.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {//消费事件
        //将触摸事件传递给`ViewDragHelper`，必不可少
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            //<p>Cause an invalidate to happen on the next animation time step, typically the
            //* next display frame.</p>
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Nullable
    private DragView.Callback callback;
    public interface Callback {

        void onPullStart();

        void onPull(float progress);

        void onPullCancel();

        void onPullComplete();

    }
    public void setCallback(@Nullable DragView.Callback callback) {
        this.callback = callback;
    }
}
