package cn.ucai.fulicenter.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.model.bean.CategoryChildBean;
import cn.ucai.fulicenter.model.bean.CategoryGroupBean;
import cn.ucai.fulicenter.model.net.CategoryModel;
import cn.ucai.fulicenter.model.net.ICategoryModel;
import cn.ucai.fulicenter.model.net.OnCompleteListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {


    @BindView(R.id.elvCategory)
    ExpandableListView elvCategory;
    Unbinder bind;
    ICategoryModel mCategoryModel;
    List<CategoryGroupBean> mCategoryGroupList;
    List<List<CategoryChildBean>> mCategoryChildList;
    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_category, container, false);
        bind = ButterKnife.bind(this, layout);
        mCategoryGroupList = new ArrayList<>();
        mCategoryChildList = new ArrayList<>();
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCategoryModel = new CategoryModel();
        downLoadCategory();
    }

    private void downLoadCategory() {
        downLoadGroupCategory();
    }
    private void downLoadGroupCategory() {
        mCategoryModel.loadGroupData(getActivity(), new OnCompleteListener<CategoryGroupBean[]>() {
            @Override
            public void onSuccess(CategoryGroupBean[] result) {
                downLoadChildCategory(1);
            }

            @Override
            public void onError(String error) {

            }
        });
    }
    private void downLoadChildCategory(int parendId) {

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
