package cn.ucai.fulicenter.model.net;

import android.content.Context;

import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.model.bean.GoodsDetailsBean;
import cn.ucai.fulicenter.model.utils.OkHttpUtils;

/**
 * Created by LPP on 2017/3/16.
 */

public class NewGoodsDetailsModel implements INewGoodsDetailsModel {
    @Override
    public void loadData(Context context, int goodsId, OnCompleteListener<GoodsDetailsBean> onCompleteListener) {
        OkHttpUtils<GoodsDetailsBean> okHttpUtils = new OkHttpUtils<>(context);
        okHttpUtils.setRequestUrl(I.REQUEST_FIND_GOOD_DETAILS)
                .addParam(I.Goods.KEY_GOODS_ID, String.valueOf(goodsId))
                .targetClass(GoodsDetailsBean.class)
                .execute(onCompleteListener);
    }
}
