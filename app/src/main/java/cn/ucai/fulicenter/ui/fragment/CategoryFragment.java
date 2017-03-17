package cn.ucai.fulicenter.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.model.bean.CategoryChildBean;
import cn.ucai.fulicenter.model.bean.CategoryGroupBean;
import cn.ucai.fulicenter.model.net.CategoryModel;
import cn.ucai.fulicenter.model.net.ICategoryModel;
import cn.ucai.fulicenter.model.net.OnCompleteListener;
import cn.ucai.fulicenter.model.utils.ResultUtils;
import cn.ucai.fulicenter.ui.activity.CategoryChildActivity;
import cn.ucai.fulicenter.ui.adapter.CategoryAdatper;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {
    @BindView(R.id.elvCategory)
    ExpandableListView elvCategory;
    List<CategoryGroupBean> mCategoryGroupList;
    List<List<CategoryChildBean>> mCategoryChildList;
    CategoryAdatper mCategoryAdapter;
    Unbinder bind;
    ICategoryModel mCategoryModel;
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
        setListener();
        return layout;
    }

    private void setListener() {
        elvCategory.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                getActivity().startActivity(new Intent(getActivity(),CategoryChildActivity.class)
                .putExtra(I.CategoryChild.CAT_ID,mCategoryChildList.get(groupPosition).get(childPosition).getId()));
                return false;
            }
        });
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCategoryModel = new CategoryModel();
        downloadGroupCategory();
    }
    private void downloadGroupCategory() {
        mCategoryModel.loadGroupData(getActivity(), new OnCompleteListener<CategoryGroupBean[]>() {
            @Override
            public void onSuccess(CategoryGroupBean[] result) {
                mCategoryGroupList = ResultUtils.array2List(result);
                for(int i=0;i<mCategoryGroupList.size();i++) {
                    int parendId = mCategoryGroupList.get(i).getId();
                    mCategoryChildList.add(new ArrayList<CategoryChildBean>());
                    downloadChildCategory(parendId,i);
                }
                elvCategory.setGroupIndicator(null);
                mCategoryAdapter = new CategoryAdatper(getActivity(), mCategoryGroupList, mCategoryChildList);
                elvCategory.setAdapter(mCategoryAdapter);
            }

            @Override
            public void onError(String error) {
                Log.i("main", error);
            }
        });
    }
    private void downloadChildCategory(int parentId, final int index){
        mCategoryModel.loadChildData(getActivity(), parentId, new OnCompleteListener<CategoryChildBean[]>() {
            @Override
            public void onSuccess(CategoryChildBean[] result) {
                ArrayList<CategoryChildBean> categoryChildList = ResultUtils.array2List(result);
                mCategoryChildList.set(index,categoryChildList);
            }
            @Override
            public void onError(String error) {
                Log.i("main", error);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}