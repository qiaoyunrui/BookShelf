package com.juhezi.bookshelf.shelf;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by qiaoyunrui on 16-8-6.
 */
public class SwipeLayout extends LinearLayout{

    private static final String TAG = "SwipeLayout";

    private ViewDragHelper viewDragHelper;
    private View contentView;
    private View actionView;
    private int dragDistance;
    private final double AUTO_OPEN_SPEED_LIMIT = 800.0;
    private int draggedX;

    public SwipeLayout(Context context) {
        this(context,null);
    }

    public SwipeLayout(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public SwipeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        viewDragHelper = ViewDragHelper.create(this,new DragHelperCallback());
    }

    private class DragHelperCallback extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View view, int pointerId) {
            return view == contentView || view == actionView;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            draggedX = left;
            if(changedView == contentView) {
                actionView.offsetLeftAndRight(dx);
            } else {
                contentView.offsetLeftAndRight(dx);
            }
            if(actionView.getVisibility() == View.GONE) {
                actionView.setVisibility(View.VISIBLE);
            }
            invalidate();
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if(child == contentView) {
                final int leftBound = getPaddingLeft();
                final int minLeftBound = -leftBound - dragDistance;
                final int newLeft = Math.min(Math.max(minLeftBound,left),0);
                return newLeft;
            } else {
                final int minLeftBound = getPaddingLeft()
                        + contentView.getMeasuredWidth()
                        - dragDistance;
                final int maxLeftBound = getPaddingLeft()
                        + contentView.getMeasuredWidth()
                        + getPaddingRight();
                final int newLeft = Math.min(Math.max(left,minLeftBound),maxLeftBound);
                return newLeft;
            }
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return dragDistance;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            boolean settleToOpean = false;
            if(xvel > AUTO_OPEN_SPEED_LIMIT) {
                settleToOpean = false;
            } else if (xvel < -AUTO_OPEN_SPEED_LIMIT) {
                settleToOpean = true;
            } else if (draggedX <= -dragDistance / 2) {
                settleToOpean = true;
            } else if (draggedX > -dragDistance / 2) {
                settleToOpean = false;
            }
            final int settleDestX = settleToOpean ? -dragDistance : 0;
            viewDragHelper.smoothSlideViewTo(contentView,settleDestX,0);
            ViewCompat.postInvalidateOnAnimation(SwipeLayout.this);
        }
    }

    @Override
    protected void onFinishInflate() {
        contentView = getChildAt(0);
        actionView = getChildAt(1);
        actionView.setVisibility(GONE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        dragDistance = actionView.getMeasuredWidth();
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        if(viewDragHelper.shouldInterceptTouchEvent(event)) {
            return true;
        }
        return super.onInterceptHoverEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(viewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
