package com.example.lenovo.customer_made_toolbar;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.Map;

public abstract class CustomerToolBarActivity extends AppCompatActivity {
    private AfToolBarHelper helper;
    private Toolbar toolBar;
    private Map<String, Integer> toolBarLayoutId;

    public abstract void onCreateCustomerToolBar(Toolbar toolBar);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("super onCreate");

    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {

        helper = new AfToolBarHelper(this, layoutResID, toolBarLayoutId);
        toolBar = helper.getToolbar();
        View contentView = helper.getContentView();
        System.out.println("contentView" + contentView);
        super.setContentView(helper.getContentView());

        onCreateCustomerToolBar(toolBar);

    }


    public void setUpToolbarView(Map<String, Integer> toolBarLayoutId) {
        System.out.println("父类toolBarLayoutId" + toolBarLayoutId);
        this.toolBarLayoutId = toolBarLayoutId;

    }


    public void toChangeToolBar(String tabStringtag, int layoutId) {

        helper.changeTooBarContentView(tabStringtag);


    }


}
