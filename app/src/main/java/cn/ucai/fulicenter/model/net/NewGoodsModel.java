package cn.ucai.fulicenter.model.net;

import android.content.Context;

import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.model.bean.NewGoodsBean;
import cn.ucai.fulicenter.model.utils.OkHttpUtils;

/**
 * Created by LPP on 2017/3/15.
 */

public class NewGoodsModel implements INewGoodsModel {
    @Override
    public void loadData(Context context, int pageId,OnCompleteListener onCompleteListener) {
        OkHttpUtils<NewGoodsBean[]> okHttpUtils = new OkHttpUtils<>(context);
        okHttpUtils.setRequestUrl(I.REQUEST_FIND_NEW_BOUTIQUE_GOODS)
                .addParam(I.NewAndBoutiqueGoods.CAT_ID, String.valueOf(I.CAT_ID))
                .addParam(I.PAGE_ID,String.valueOf(pageId))
                .addParam(I.PAGE_SIZE,String.valueOf(I.PAGE_ID_DEFAULT))
                .targetClass(NewGoodsBean[].class)
                .execute(onCompleteListener);
    }
}
