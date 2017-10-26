package com.ljs.navigation;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.ljs.application.XApplication;
import com.ljs.sumery.R;
import com.ljs.utils.SystemUtils;

import afinal.FinalActivity;
import afinal.annotation.view.ViewInject;

/**
 * Created by 暮雨而歌 on 2017/10/12.
 */

/*
   1.NavigationView 提供了侧滑菜单的Header，里面可以定义ListView,RecyclerView 等，
   可以根据获取数据动态去管理，设置显示的数据
   2.可以将DrawerLayout 里面放置ListView或等等各种控件，通过设置layout_gravity属性去让该控件居左还是居右
*/

public class NavigationMainActivity extends AppCompatActivity{
    @ViewInject (idString = "drawlayout_main") DrawerLayout mDrawerLayout;
    @ViewInject (idString = "navigation_main") NavigationView mNavigationView;
    @ViewInject (idString = "id_toolbar")
    Toolbar mToolbar;
    ActionBarDrawerToggle mDrawerToggle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.acitivity_navigation);

        initView();
        initToolBar();
    }

    private void initToolBar() {
        mToolbar.setTitle(getString(R.string.action_settings));
        setSupportActionBar(mToolbar);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, 0, 0) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
//                invalidateOptionsMenu();
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
//                invalidateOptionsMenu();
            }
        };
        //设置指示器的动画效果
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SystemUtils.openOrCloseDrawer(mDrawerLayout,mNavigationView);
            }
        });

        mToolbar.inflateMenu(R.menu.tablayout_menu);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                switch (id){
                    case R.id.menu_add:
                        Toast.makeText(XApplication.getApplication(),
                                "menu_add is clicked",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_delete:
                        Toast.makeText(XApplication.getApplication(),
                                "menu_delete is clicked",Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tablayout_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initView(){
        FinalActivity.initInjectedView(this);
        mDrawerLayout.setScrimColor(Color.TRANSPARENT);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.action_common:
                        Toast.makeText(NavigationMainActivity.this,"点击了收藏",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_menu:
                        Toast.makeText(NavigationMainActivity.this,"点击了相册",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_weixin:
                        Toast.makeText(NavigationMainActivity.this,"点击了微信",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_qq:
                        Toast.makeText(NavigationMainActivity.this,"点击了QQ",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_transform:
                        Toast.makeText(NavigationMainActivity.this,"点击了文件",Toast.LENGTH_SHORT).show();
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

    //动态在NavigationView菜单里面去添加菜单
    private void dynamicAddMenu(){
        mDrawerLayout.setScrimColor(getResources().getColor(R.color.color_green));

        mNavigationView.getMenu().add(1, 1, 1, "menu_1").setIcon(R.mipmap.ic_launcher_round).setCheckable(true);//动态添加menu
        mNavigationView.getMenu().add(2, 2, 2, "menu_2").setIcon(R.mipmap.ic_launcher_round).setCheckable(true);
        mNavigationView.getMenu().add(3, 3, 3, "menu_3").setIcon(R.mipmap.ic_launcher).setCheckable(true);

        //设置背景色
        mNavigationView.setBackgroundColor(getResources().getColor(R.color.color_light_gray));
    }

}
