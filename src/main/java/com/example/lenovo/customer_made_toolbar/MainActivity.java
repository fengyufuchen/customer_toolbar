package com.example.lenovo.customer_made_toolbar;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends CustomerToolBarActivity {
    private FragmentTabHost mfragmentTabHost;

    private Map<String, Integer> toobarLayoutId = new HashMap<String, Integer>();
    public static final String TOOLBARVIEWTYPE_FRAGMENTTABFIRST = "FragmentTabFirst";
    private static final String TOOLBARVIEWTYPE_FRAGMENTTABSECOND = "FragmentTabSecond";


    public void onCreateCustomerToolBar(Toolbar toolBar) {
        toolBar.setNavigationIcon(R.mipmap.ic_menu_home);
        toolBar.setLogo(null);
        setSupportActionBar(toolBar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolBarLayoutIdMap();
        setUpToolbarView(toobarLayoutId);
        setContentView(R.layout.activity_main);
        mfragmentTabHost = (FragmentTabHost) findViewById(R.id.tabhost);

        initView();


    }

    private void initToolBarLayoutIdMap() {
        toobarLayoutId.put(TOOLBARVIEWTYPE_FRAGMENTTABFIRST, R.layout.customer_toolbarfirsttype);
        toobarLayoutId.put(TOOLBARVIEWTYPE_FRAGMENTTABSECOND, R.layout.customer_toolbarecondtype);

    }


    private void initView() {
        mfragmentTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mfragmentTabHost.addTab(mfragmentTabHost.newTabSpec("tab1").setIndicator(getTabItemView(R.mipmap.ic_tab_friends, "tab1")), FragmentTabFirst.class, null);
        mfragmentTabHost.addTab(mfragmentTabHost.newTabSpec("tab2").setIndicator(getTabItemView(R.mipmap.ic_message_dynamic, "tab2")), FragmentTabSecond.class, null);

        mfragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                System.out.println("tab改变" + tabId);

                if (tabId.equals("tab1")) {

                    //显示tab1 的toolbar
                    toChangeToolBar(TOOLBARVIEWTYPE_FRAGMENTTABFIRST, R.layout.fragment_fragment_tab_first);

                    return;

                }
                if (tabId.equals("tab2")) {
                    toChangeToolBar(TOOLBARVIEWTYPE_FRAGMENTTABSECOND, R.layout.fragment_customer_tab_second);
                    return;
                }


            }
        });


    }

    private View getTabItemView(int resourceId, String tabTitle) {
        View rootView = this.getLayoutInflater().inflate(R.layout.main_tab_item_view, null);
        ImageView iv_tab_item = (ImageView) rootView.findViewById(R.id.iv_main_tab_item_imageView);
        iv_tab_item.setImageResource(resourceId);
        ((TextView) rootView.findViewById(R.id.tv_main_tab_item)).setText(tabTitle);
        return rootView;

    }

}
