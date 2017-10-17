package com.ljs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ljs.bottomsheet.BottomSheetViewActivity;
import com.ljs.coordinatorlayout_appbarlayout.CoordinatorLayoutDemoActivity;
import com.ljs.navigation.NavigationMainActivity;
import com.ljs.sumery.R;
import com.ljs.utils.SystemUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doClick(View view){
        int id = view.getId();

        switch (id){
            case R.id.do_navigation:
                SystemUtils.lauchActivity(this, NavigationMainActivity.class);
                break;
            case R.id.do_bootom_sheet:
                SystemUtils.lauchActivity(this, BottomSheetViewActivity.class);
                break;
            case R.id.do_tab_layout:
                SystemUtils.lauchActivity(this, CoordinatorLayoutDemoActivity.class);
                break;
        }
    }
}
