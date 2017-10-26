package com.ljs.colorfulstatusbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ljs.sumery.R;
import com.ljs.toolbar_manager.TranslucentStatusBarUtils;

public class ColorfulStatusActivityDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);

//        TranslucentStatusBarUtils.setStatusBarColor(this,R.color.color_red);
        TranslucentStatusBarUtils.setStatusBarColor(this,getResources().getColor(R.color.colorAccent));
    }

}
