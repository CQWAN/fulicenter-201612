package cn.ucai.fulicenter.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.model.bean.BoutiqueBean;
import cn.ucai.fulicenter.model.net.BoutiqueModel;
import cn.ucai.fulicenter.model.net.IBoutiqueModel;
import cn.ucai.fulicenter.model.net.OnCompleteListener;
import cn.ucai.fulicenter.model.utils.ResultUtils;
import cn.ucai.fulicenter.ui.activity.MainActivity;
import cn.ucai.fulicenter.ui.adapter.BoutiqueAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoutiqueFragment extends Fragment {
    RecyclerView mrvBoutique;
    ArrayList<BoutiqueBean> mBoutiqueList;
    LinearLayoutManager mLayoutManager;
    int mPageId = 1;
    BoutiqueAdapter mBoutiqueAdapter;
    MainActivity mActivity;
    IBoutiqueModel mBoutiqueModel;
    public BoutiqueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_boutique, container, false);
        initView(layout);
        mBoutiqueModel = new BoutiqueModel();
        return layout;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mBoutiqueModel.loadData(mActivity, new OnCompleteListener<BoutiqueBean[]>() {
            @Override
            public void onSuccess(BoutiqueBean[] result) {
                mBoutiqueList = ResultUtils.array2List(result);
                mBoutiqueAdapter.initBoutiqueList(mBoutiqueList);
            }

            @Override
            public void onError(String error) {
                Log.e("main", error);
            }
        });
    }

    private void initView(View layout) {
        mrvBoutique = (RecyclerView) layout.findViewById(R.id.rvBoutique);
        mActivity = (MainActivity) getActivity();
        mLayoutManager = new LinearLayoutManager(mActivity);
        mrvBoutique.setLayoutManager(mLayoutManager);
        mBoutiqueList = new ArrayList<>();
        mBoutiqueAdapter = new BoutiqueAdapter(mActivity, mBoutiqueList);
        mrvBoutique.setAdapter(mBoutiqueAdapter);
    }
}
