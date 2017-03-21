package cn.ucai.fulicenter.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.application.FuLiCenterApplication;
import cn.ucai.fulicenter.model.bean.User;
import cn.ucai.fulicenter.model.utils.ImageLoader;
import cn.ucai.fulicenter.model.utils.L;
import cn.ucai.fulicenter.model.utils.MFGT;
import cn.ucai.fulicenter.ui.activity.MainActivity;

/**
 * Created by LPP on 2017/3/20.
 */

public class PersonalCenterFragment extends BaseFragment {
    private static final String TAG = PersonalCenterFragment.class.getSimpleName();
    @BindView(R.id.iv_user_avatar)
    ImageView mIvUserAvatar;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    User user = null;
    MainActivity mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = View.inflate(getActivity(), R.layout.fragment_personal_center, null);
        ButterKnife.bind(this, layout);
        mContext = (MainActivity) getActivity();
        return layout;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        user = FuLiCenterApplication.getUser();
        L.e(TAG, "user=" + user);
        if (user == null) {
            MFGT.gotoLogin(mContext);
        } else {
            ImageLoader.setAvatar(ImageLoader.getAvatarUrl(user), mContext, mIvUserAvatar);
            mTvUserName.setText(user.getMuserNick());
        }
    }

    @Override
    protected void setListener() {

    }
}
