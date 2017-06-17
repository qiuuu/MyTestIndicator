package com.pangge.parsecity;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;

import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import com.pangge.parsecity.draw.data.Indicator;

/**
 * Created by iuuu on 17/6/16.
 */

public class ViewPagerIndicator extends View implements ViewPager.OnPageChangeListener, IndicatorManager.Listener{

    private IndicatorManager manager;

    private ViewPager viewPager;
    private DataSetObserver setObserver;


    public ViewPagerIndicator(Context context) {
        super(context);
        //init(null);
    }


    public ViewPagerIndicator(Context context, AttributeSet attrs){
        super(context,attrs);

        init(context,attrs);

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        findViewPager();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //indicator = new Indicator();


        Pair<Integer, Integer> pair = manager.drawer().measureViewSize(widthMeasureSpec,heightMeasureSpec);


        setMeasuredDimension(pair.first,pair.second);



    }

    @Override
    protected void onDraw(Canvas canvas) {
        manager.drawer().draw(canvas);


    }

    @Override
    public void onIndicatorUpdated() {
        invalidate();

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      //  selectInteractiveIndicator(position, positionOffset);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageSelected(int position) {
        //selectIndicator(position);
        setSelection(position);

    }

    private int getViewPagerCount() {
        if (viewPager != null && viewPager.getAdapter() != null) {
            return viewPager.getAdapter().getCount();
        } else {
            return manager.indicator().getCount();
        }
    }



    public void setDynamicCount(boolean dynamicCount) {
        manager.indicator().setDynamicCount(dynamicCount);

        if (dynamicCount) {
            registerSetObserver();
        } else {
            unRegisterSetObserver();
        }
    }


    public void setCount(int count) {
        if (count >= 0 && manager.indicator().getCount() != count) {
            manager.indicator().setCount(count);
            updateVisibility();

            requestLayout();
        }
    }

    private void updateVisibility() {
        if (manager.indicator().isAutoVisibility()) {
            return;
        }

        int count = manager.indicator().getCount();
        int visibility = getVisibility();

        if (visibility != VISIBLE && count > Indicator.MIN_COUNT) {
            setVisibility(VISIBLE);

        } else if (visibility != INVISIBLE && count <= Indicator.MIN_COUNT) {
            setVisibility(View.INVISIBLE);
        }
    }

    private void registerSetObserver() {
        if (setObserver != null || viewPager == null || viewPager.getAdapter() == null) {
            return;
        }

        setObserver = new DataSetObserver() {
            @Override
            public void onChanged() {
                updateCount();
            }
        };

        try {
            viewPager.getAdapter().registerDataSetObserver(setObserver);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    private void updateCount() {
        if (viewPager == null || viewPager.getAdapter() == null) {
            return;
        }

        int newCount = viewPager.getAdapter().getCount();
        int currItem = viewPager.getCurrentItem();

        manager.indicator().setSelectedPosition(currItem);
        manager.indicator().setSelectingPosition(currItem);
        manager.indicator().setLastSelectedPosition(currItem);
        // manager.animate().end();

        setCount(newCount);
    }

    private void unRegisterSetObserver() {
        if (setObserver == null || viewPager == null || viewPager.getAdapter() == null) {
            return;
        }

        try {
            viewPager.getAdapter().unregisterDataSetObserver(setObserver);
            setObserver = null;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    private void init(Context context,  AttributeSet attrs) {
        manager = new IndicatorManager(this);
        manager.drawer().initAttributes(context, attrs);


        Indicator indicator = manager.indicator();
        indicator.setPaddingLeft(getPaddingLeft());
        indicator.setPaddingTop(getPaddingTop());
        indicator.setPaddingRight(getPaddingRight());
        indicator.setPaddingBottom(getPaddingBottom());

    }

    public void setSelection(int position) {
        Indicator indicator = manager.indicator();
       /* if (indicator.isInteractiveAnimation() && indicator.getAnimationType() != AnimationType.NONE) {
            return;
        }*/

        int selectedPosition = indicator.getSelectedPosition();
        int count = indicator.getCount();
        int lastPosition = count - 1;

        if (position < 0) {
            position = 0;
        } else if (position > lastPosition) {
            position = lastPosition;
        }

        if (selectedPosition == position) {
            return;
        }

        indicator.setLastSelectedPosition(indicator.getSelectedPosition());
        indicator.setSelectedPosition(position);
        manager.animate().basic();
    }

    private void findViewPager() {
        if (getContext() instanceof Activity) {
            Activity activity = (Activity) getContext();
            int viewPagerId = manager.indicator().getViewPagerId();

            View view = activity.findViewById(viewPagerId);
            if (view != null && view instanceof ViewPager) {
                setViewPager((ViewPager) view);
            }
        }
    }



    public void setViewPager(ViewPager pager) {
        releaseViewPager();
        if (pager == null) {
            return;
        }

        viewPager = pager;
        viewPager.addOnPageChangeListener(this);
        manager.indicator().setViewPagerId(viewPager.getId());
        Log.i("viewPager id",""+viewPager.getId());


        setDynamicCount(manager.indicator().isDynamicCount());
        int count = getViewPagerCount();
        Log.i("viewPager count",""+count);

        /*if (isRtl()) {
            int selectedPosition = (count - 1) - viewPager.getCurrentItem();
            manager.indicator().setSelectedPosition(selectedPosition);
        }*/

        setCount(count);
    }

    public void releaseViewPager() {
        if (viewPager != null) {
            viewPager.removeOnPageChangeListener(this);
            viewPager = null;
        }
    }
}
