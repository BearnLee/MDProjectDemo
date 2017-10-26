package com.ljs.edittextinput;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.ljs.sumery.R;
import com.ljs.utils.MaterialDesignUtils;

/**
 * Created by 暮雨而歌 on 2017/10/24.
 */

public class LoginTextInputActivityDemo extends AppCompatActivity{
    private final String TAG = "LoginTextInputActivityDemo";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_text_input_demo);
        initView();
    }

    private void initView(){
//        final Toolbar mToolBar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(mToolBar);
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }
//        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
//
//        getSupportActionBar().setTitle("用户登陆");

        final TextInputLayout mTextInputLayoutUser = (TextInputLayout) findViewById(R.id.til_username);
        final TextInputLayout mTextInputLayoutPass = (TextInputLayout) findViewById(R.id.til_password);
        final EditText userEditText = mTextInputLayoutUser.getEditText();
        final EditText pwdEditText = mTextInputLayoutPass.getEditText();
        //设置hint提示,也可直接在xml中设置(个人感觉如果在布局中已经设置了hint,代码中就不必在设置了.)
        //userEditText.setHint("Username");
        //pwdEditText.setHint("Password");

        MaterialDesignUtils.changeTextInputLayoutLabelColor(mTextInputLayoutUser,R.color.color_gray);
        MaterialDesignUtils.changeTextInputLayoutLabelColor(mTextInputLayoutPass,R.color.color_gray);

        //EditText添加文本变化监听
        userEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 7) {
                    mTextInputLayoutUser.setErrorEnabled(true);//设置是否打开错误提示
                    MaterialDesignUtils.setErrorTextColor(mTextInputLayoutUser,R.color.material_blue_700);
                    mTextInputLayoutUser.setError("用户名长度不能超过8个");//设置错误提示的信息
                    mTextInputLayoutUser.setDrawingCacheBackgroundColor(
                            mTextInputLayoutUser.getResources().getColor(R.color.material_blue_700));
                } else {
                    mTextInputLayoutUser.setErrorEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        pwdEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 6 && s.length() > 0) {
                    mTextInputLayoutPass.setErrorEnabled(true);
                    MaterialDesignUtils.setErrorTextColor(mTextInputLayoutPass,R.color.material_blue_700);
                    mTextInputLayoutPass.setError("密码长度不能小于6个");
                    mTextInputLayoutPass.setDrawingCacheBackgroundColor(
                            mTextInputLayoutPass.getResources().getColor(R.color.material_blue_700));
                } else {
                    mTextInputLayoutPass.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}

