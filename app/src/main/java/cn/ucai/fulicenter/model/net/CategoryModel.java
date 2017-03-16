package cn.ucai.fulicenter.model.net;

import android.content.Context;

import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.model.bean.CategoryChildBean;
import cn.ucai.fulicenter.model.bean.CategoryGroupBean;
import cn.ucai.fulicenter.model.utils.OkHttpUtils;

/**
 * Created by LPP on 2017/3/16.
 */

public class CategoryModel implements ICategoryModel {
    @Override
    public void loadGroupData(Context context, OnCompleteListener<CategoryGroupBean[]> onCompleteListener) {
        OkHttpUtils<CategoryGroupBean[]> okHttpUtils = new OkHttpUtils<>(context);
        okHttpUtils.setRequestUrl(I.REQUEST_FIND_CATEGORY_GROUP)
                .targetClass(CategoryGroupBean[].class)
                .execute(onCompleteListener);
    }
    @Override
    public void loadChildData(Context context, int parentId, OnCompleteListener<CategoryChildBean[]> onCompleteListener) {
        OkHttpUtils<CategoryChildBean[]> okHttpUtils = new OkHttpUtils<>(context);
        okHttpUtils.setRequestUrl(I.REQUEST_FIND_CATEGORY_CHILDREN)
                .addParam(I.CategoryGroup.ID,String.valueOf(parentId))
                .targetClass(CategoryChildBean[].class)
                .execute(onCompleteListener);
    }
}
