package cn.ucai.fulicenter.model.net;

import android.content.Context;

import cn.ucai.fulicenter.model.bean.CategoryChildBean;
import cn.ucai.fulicenter.model.bean.CategoryGroupBean;

/**
 * Created by LPP on 2017/3/15.
 */

public interface ICategoryModel {
    void loadGroupData(Context context, OnCompleteListener<CategoryGroupBean[]> onCompleteListener);
    void loadChildData(Context context, int parentId, OnCompleteListener<CategoryChildBean[]> onCompleteListener);
}
