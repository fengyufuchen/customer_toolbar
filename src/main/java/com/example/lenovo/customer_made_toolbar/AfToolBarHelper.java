package com.example.lenovo.customer_made_toolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.Map;
import java.util.Set;

/**
 * Created by Sachin on 2016/3/26.
 */
public class AfToolBarHelper {

    /**
     * 定义在哪个activity上显示
     */
    private Context mContext;
    private Toolbar toolBar;
    //toolBar的视图view
    private View toolBarView;
    //用于装载toolbar
    private FrameLayout contentView;
    LayoutInflater inflater;

    private Map<String, Integer> mapAFToolbarLayoutId;


    private int[] attrs = new int[]{
            android.R.attr.windowActionBarOverlay,
            android.R.attr.actionBarSize

    };


    public AfToolBarHelper(Context pContext, int layoutId, Map<String, Integer> mapToolbarLayoutId) {
        mContext = pContext;
        System.out.println("init:" + mapToolbarLayoutId);
        this.mapAFToolbarLayoutId = mapToolbarLayoutId;
        System.out.println(mapAFToolbarLayoutId.get(MainActivity.TOOLBARVIEWTYPE_FRAGMENTTABFIRST));


        inflater = LayoutInflater.from(mContext);
        initContentGroup();
        initAFToolBar();
        initCustomerView(layoutId);
    }


    private void initContentGroup() {
        contentView = new FrameLayout(mContext);
        new ViewGroup.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        contentView.setLayoutParams(params);
        contentView.setBackgroundColor(R.color.content_background);
    }


    void initAFToolBar() {
        View rootView = inflater.inflate(R.layout.toobar, contentView);
        toolBar = (Toolbar) rootView.findViewById(R.id.toolbar);

        Set<Map.Entry<String, Integer>> entrymap = mapAFToolbarLayoutId.entrySet();
        for (Map.Entry<String, Integer> entryItem : entrymap) {
            View customerToolBaraView = inflater.inflate(entryItem.getValue(), null);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            toolBar.addView(customerToolBaraView, 0);
            break;

        }
       /* ImageView iv_toolbar = new ImageView(mContext);
        iv_toolbar.setImageResource(R.mipmap.icon_search);
        toolBar.addView(iv_toolbar, 0);*/


    }

    void initCustomerView(int userLayoutId) {
        View userRootView = inflater.inflate(userLayoutId, null);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TypedArray typedArray = mContext.getTheme().obtainStyledAttributes(attrs);

        boolean isOverlay = typedArray.getBoolean(0, false);
        //  typedArray.getInteger(1,)
        int toolbarsize = (int) typedArray.getDimension(1, mContext.getResources().getDimension(R.dimen.abc_action_bar_default_height_material));//1表示我们要获取attrs数组中的actionbarsize的属性的值
        typedArray.recycle();
        toolbarsize = isOverlay ? 0 : toolbarsize;
        params.topMargin = toolbarsize;
        contentView.addView(userRootView, params);
    }


    public ViewGroup getContentView() {
        return contentView;
    }

    public Toolbar getToolbar() {
        return toolBar;
    }

    public boolean changeTooBarContentView(String toolBarTag) {

        int layoutId = mapAFToolbarLayoutId.get(toolBarTag);
        System.out.println("更改toolbar的视图view,布局id"+layoutId);
        toolBar.removeViewAt(0);
        View customerToolBaraView = inflater.inflate(layoutId, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        System.out.println("customerToolBaraView:"+customerToolBaraView.getId());
        toolBar.addView(customerToolBaraView, 0, params);
        return true;
    }


}
