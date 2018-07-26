package com.appsino.bingluo.databingtest.Login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.appsino.bingluo.databingtest.R;
import com.appsino.bingluo.databingtest.Utils.SPUtils;
import com.appsino.bingluo.databingtest.app.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Answer on 2018/7/12.
 */
@Route(path = "/com/loginactivity")
public class LoginActvity extends BaseActivity {
    @BindView(R.id.et_login_name)
    EditText etLoginName;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.btn_login_sb)
    Button btnLoginSb;
    @BindView(R.id.btn_login_zc)
    Button btnLoginZc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_login_sb, R.id.btn_login_zc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login_sb:
                SPUtils.putBoolean(LoginActvity.this,"islogin",true);
                break;
            case R.id.btn_login_zc:
                break;
        }
    }
}
