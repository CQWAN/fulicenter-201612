package cn.ucai.fulicenter.model.net;

import android.content.Context;

import cn.ucai.fulicenter.model.bean.GoodsDetailsBean;

/**
 * Created by LPP on 2017/3/15.
 */

public interface INewGoodsDetailsModel {
    void loadData(Context context, int goodsId, OnCompleteListener<GoodsDetailsBean> onCompleteListener);
}
