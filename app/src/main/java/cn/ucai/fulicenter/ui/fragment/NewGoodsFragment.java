package cn.ucai.fulicenter.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.model.bean.NewGoodsBean;
import cn.ucai.fulicenter.model.net.INewGoodsModel;
import cn.ucai.fulicenter.model.net.NewGoodsModel;
import cn.ucai.fulicenter.model.net.OnCompleteListener;
import cn.ucai.fulicenter.model.utils.ResultUtils;
import cn.ucai.fulicenter.ui.activity.MainActivity;
import cn.ucai.fulicenter.ui.adapter.NewGoodsAdapter;
import cn.ucai.fulicenter.ui.view.SpaceItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewGoodsFragment extends Fragment {
    RecyclerView mrvNewGoods;
    ArrayList<NewGoodsBean> mNewGoodsList;
    GridLayoutManager mLayoutManager;
    int mPageId = 1;
    MainActivity mActivity;
    NewGoodsAdapter mNewGoodsAdapter;
    INewGoodsModel mNewGoodsModel;



    public NewGoodsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_new_goods, container, false);
        initView(layout);
        return layout;
    }
    private void initView(View layout) {
        mrvNewGoods = (RecyclerView) layout.findViewById(R.id.rvNewGoods);
        mActivity = (MainActivity) getActivity();
        mLayoutManager = new GridLayoutManager(mActivity, I.COLUM_NUM);
        mrvNewGoods.setLayoutManager(mLayoutManager);
        mNewGoodsList = new ArrayList<>();
        mNewGoodsAdapter = new NewGoodsAdapter(mActivity, mNewGoodsList);
        mrvNewGoods.setAdapter(mNewGoodsAdapter);
        mrvNewGoods.addItemDecoration(new SpaceItemDecoration(12));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 下载数据
        mNewGoodsModel = new NewGoodsModel();
        mNewGoodsModel.loadData(mActivity, mPageId, new OnCompleteListener<NewGoodsBean[]>() {
            @Override
            public void onSuccess(NewGoodsBean[] result) {
                mNewGoodsList = ResultUtils.array2List(result);
                Log.e("main",mNewGoodsList.toString());
                mNewGoodsAdapter.initNewGoodsList(mNewGoodsList);
            }

            @Override
            public void onError(String error) {
                Log.e("main", error);
            }
        });

    }
}