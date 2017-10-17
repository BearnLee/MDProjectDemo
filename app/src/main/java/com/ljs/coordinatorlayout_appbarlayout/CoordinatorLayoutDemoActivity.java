package com.ljs.coordinatorlayout_appbarlayout;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ljs.application.XApplication;
import com.ljs.navigation.NavigationMainActivity;
import com.ljs.sumery.IObject;
import com.ljs.sumery.R;
import com.ljs.utils.SystemUtils;
import com.ljs.utils.ToastManager;

import java.util.ArrayList;
import java.util.List;

import afinal.FinalActivity;
import afinal.annotation.view.ViewInject;

/**
 * Created by 暮雨而歌 on 2017/10/13.
 */

public class CoordinatorLayoutDemoActivity extends AppCompatActivity{
    @ViewInject (idString = "tablayout") TabLayout mTabLayout;
    @ViewInject (idString = "toolbar")   Toolbar mToolBar;
    @ViewInject (idString = "viewpager") ViewPager mViewPager;
    @ViewInject (idString = "drawlayout_main") DrawerLayout mDrawerLayout;
    @ViewInject (idString = "navigation_main") NavigationView mNavigationView;

    private String mTitles[];
    private List<ViewPagerFragment> mPages = new ArrayList<>();
    private ActionBarDrawerToggle toggle;
    private int count = 30;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);

        initView();
        initData();
        initAdapter();
    }

    private void initAdapter() {
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mPages.get(position);
            }

            @Override
            public int getCount() {
                return mPages.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        });
    }

    private void initData() {
        for(int i = 0 ; i < 9;i++){
            ViewPagerFragment fragment = new ViewPagerFragment();
            mPages.add(fragment);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tablayout_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initView() {
        FinalActivity.initInjectedView(this);

        setToolBar();
        setTabLayout();
    }

    private void setTabLayout(){
        mTitles = getResources().getStringArray(R.array.tablayout_array);
        for(int i=0;i<9;i++){
            mTabLayout.addTab(mTabLayout.newTab().setText(mTitles[i]));
        }
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setToolBar(){
        setSupportActionBar(mToolBar);
        mToolBar.inflateMenu(R.menu.tablayout_menu);

        toggle = new ActionBarDrawerToggle(this,mDrawerLayout,0,0){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        mDrawerLayout.addDrawerListener(toggle);

        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SystemUtils.openOrCloseDrawer(mDrawerLayout,mNavigationView);
            }
        });

        mToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                switch (id){
                    case R.id.menu_add:
                        mPages.get(mViewPager.getCurrentItem()).addData(0,new IObject(count++));
                        break;
                    case R.id.menu_delete:
                        mPages.get(mViewPager.getCurrentItem()).deleteData(0);
                        count--;
                        break;
                }
                return true;
            }
        });

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.action_common:
                        ToastManager.getInstance().show("点击了收藏");
                        break;
                    case R.id.action_menu:
                        ToastManager.getInstance().show("点击了相册");
                        break;
                    case R.id.action_weixin:
                        ToastManager.getInstance().show("点击了微信");
                        break;
                    case R.id.action_qq:
                        ToastManager.getInstance().show("点击了QQ");
                        break;
                    case R.id.action_transform:
                        ToastManager.getInstance().show("点击了文件");
                        break;
                    default:
                        break;
                }
                item.setChecked(true);
                //点击是否关闭侧滑菜单
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

}
