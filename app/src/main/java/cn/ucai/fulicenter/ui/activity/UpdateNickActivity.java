package cn.ucai.fulicenter.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.application.FuLiCenterApplication;
import cn.ucai.fulicenter.model.bean.User;
import cn.ucai.fulicenter.model.utils.CommonUtils;
import cn.ucai.fulicenter.model.utils.MFGT;
import cn.ucai.fulicenter.ui.view.DisplayUtils;

/**
 * Created by LPP on 2017/3/22.
 */
public class UpdateNickActivity extends BaseActivity {
    @BindView(R.id.et_update_user_name)
    EditText mEtUpdateUserName;
    UpdateNickActivity mContext;
    User user = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_update_nick);
        ButterKnife.bind(this);
        mContext = this;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        DisplayUtils.initBackWithTitle(mContext,getResources().getString(R.string.update_user_nick));
    }

    @Override
    protected void initData() {
        user = FuLiCenterApplication.getUser();
        if (user != null) {
            mEtUpdateUserName.setText(user.getMuserNick());
            mEtUpdateUserName.setSelectAllOnFocus(true);
        } else {
            MFGT.finish(UpdateNickActivity.this);
        }
    }

    @Override
    protected void setListener() {
    }

    @OnClick({R.id.backClickArea, R.id.btn_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backClickArea:
                MFGT.finish(UpdateNickActivity.this);
                break;
            case R.id.btn_save:
                if (checkedNick()) { // 检查昵称
                    // 保存昵称
                    updataUserNick();
                }
                break;
        }
    }

    private boolean checkedNick() {
        if (user != null) {
            String userNick = mEtUpdateUserName.getText().toString().trim();
            if (TextUtils.isEmpty(userNick)) {
                CommonUtils.showLongToast(R.string.nick_name_connot_be_empty);
                return false;
            } else if (userNick.equals(user.getMuserNick())) {
                CommonUtils.showLongToast(R.string.update_nick_fail_unmodify);
                return false;
            }
        }
        return true;
    }
}
