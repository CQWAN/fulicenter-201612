package cn.ucai.fulicenter.model.net;

import android.content.Context;

import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.model.bean.BoutiqueBean;
import cn.ucai.fulicenter.model.utils.OkHttpUtils;

/**
 * Created by LPP on 2017/3/15.
 */

public class BoutiqueModel implements IBoutiqueModel {

    @Override
    public void loadData(Context context, OnCompleteListener<BoutiqueBean[]> onCompleteListener) {
        OkHttpUtils<BoutiqueBean[]> okHttpUtils = new OkHttpUtils<>(context);
        okHttpUtils.setRequestUrl(I.REQUEST_FIND_BOUTIQUES)
               .targetClass(BoutiqueBean[].class)
                .execute(onCompleteListener);
    }
}
