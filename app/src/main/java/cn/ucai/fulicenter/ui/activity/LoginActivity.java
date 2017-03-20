package cn.ucai.fulicenter.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.model.utils.CommonUtils;
import cn.ucai.fulicenter.model.utils.MFGT;
import cn.ucai.fulicenter.ui.view.DisplayUtils;

/**
 * Created by LPP on 2017/3/20.
 */

public class LoginActivity extends BaseActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();


    @BindView(R.id.username)
    EditText mUsername;
    @BindView(R.id.password)
    EditText mPassword;

    String userName;
    String password;
    LoginActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mContext = this;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        DisplayUtils.initBackWithTitle(mContext, getResources().getString(R.string.login));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login: // 点击登录开始登录
                if (checkedInput()) { // 先判断,后登录
                    login();
                }
                break;
            case R.id.btn_register:
                MFGT.gotoRegister(this);
                break;
        }
    }

    private void login() { // 开始登录

    }

    private boolean checkedInput() { // 登录前检查
        userName = mUsername.getText().toString().trim();
        password = mPassword.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            CommonUtils.showShortToast(R.string.user_name_connot_be_empty);
            mUsername.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(password)) {
            CommonUtils.showShortToast(R.string.password_connot_be_empty);
            mPassword.requestFocus();
            return false;
        }
        return true;
    }
}
