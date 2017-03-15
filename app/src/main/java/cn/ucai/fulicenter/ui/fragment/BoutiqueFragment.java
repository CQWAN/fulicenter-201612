package cn.ucai.fulicenter.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.model.bean.NewGoodsBean;
import cn.ucai.fulicenter.model.net.INewGoodsModel;
import cn.ucai.fulicenter.model.net.NewGoodsModel;
import cn.ucai.fulicenter.model.net.OnCompleteListener;
import cn.ucai.fulicenter.model.utils.ImageLoader;
import cn.ucai.fulicenter.model.utils.ResultUtils;
import cn.ucai.fulicenter.ui.activity.MainActivity;
import cn.ucai.fulicenter.ui.adapter.NewGoodsAdapter;
import cn.ucai.fulicenter.ui.view.SpaceItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoutiqueFragment extends Fragment {
    static final int ACTION_DOWNLOAD = 1;
    static final int ACTION_PULL_DOWN = 2;
    static final int ACTION_PULL_UP = 3;
    SwipeRefreshLayout mSrl;
    TextView mtvRefreshHint;
    RecyclerView mrvNewGoods;
    ArrayList<NewGoodsBean> mNewGoodsList;
    GridLayoutManager mLayoutManager;
    int mPageId = 1;
    MainActivity mActivity;
    NewGoodsAdapter mNewGoodsAdapter;
    INewGoodsModel mNewGoodsModel = new NewGoodsModel();



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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        // 下载首页并设置监听
        super.onActivityCreated(savedInstanceState);
        mPageId = 1;
        downloadNewGoodsList(mPageId,ACTION_DOWNLOAD);
        setListener();
    }

    private void setListener() {
        setPullUpListener();
        setPullDownListener();
    }

    private void setPullDownListener() {
        mSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 开始下拉刷新
                ImageLoader.release();
                mSrl.setRefreshing(true);
                mtvRefreshHint.setVisibility(View.VISIBLE);
                mPageId = 1;
                downloadNewGoodsList(mPageId,ACTION_PULL_DOWN);
            }
        });
    }

    private void setPullUpListener() {
        // 开始上拉加载
        mrvNewGoods.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int position = mLayoutManager.findLastCompletelyVisibleItemPosition();
                if (position == mNewGoodsAdapter.getItemCount() - 1 && RecyclerView.SCROLL_STATE_IDLE == newState && mNewGoodsAdapter.isMore()) {
                    mPageId++;
                    downloadNewGoodsList(mPageId,ACTION_PULL_UP);
                }
            }
        });
    }

    private void downloadNewGoodsList(int mPageId, final int action) {
        mNewGoodsModel.loadData(mActivity, mPageId, new OnCompleteListener<NewGoodsBean[]>() {
            @Override
            public void onSuccess(NewGoodsBean[] result) {
                mNewGoodsAdapter.setMore(result != null && result.length > 0);
                if (!mNewGoodsAdapter.isMore()) {
                    if (action == ACTION_PULL_UP) {
                        mNewGoodsAdapter.setTextFooter("没有更多数据啦");
                    }
                    return;
                }
                mNewGoodsList = ResultUtils.array2List(result);
                switch (action) {
                    case ACTION_DOWNLOAD:
                        mNewGoodsAdapter.initNewGoodsList(mNewGoodsList);
                        mNewGoodsAdapter.setTextFooter("加载更多...");
                        break;
                    case ACTION_PULL_DOWN:
                        Log.d("main", "onSuccess: " + mSrl.isRefreshing());
                        mSrl.setRefreshing(false);
                        mtvRefreshHint.setVisibility(View.GONE);
                        mNewGoodsAdapter.initNewGoodsList(mNewGoodsList);
                        mNewGoodsAdapter.setTextFooter("加载更多");
                        break;
                    case ACTION_PULL_UP:
                        mNewGoodsAdapter.addNewGoodsList(mNewGoodsList);
                        mNewGoodsAdapter.setTextFooter("加载更多");
                        break;
                }
            }

            @Override
            public void onError(String error) {
                mSrl.setRefreshing(false);
                Log.e("main", error);
            }
        });
    }
    private void initView(View layout) {
        mSrl = (SwipeRefreshLayout) layout.findViewById(R.id.srl);
        mSrl.setColorSchemeColors(getResources().getColor(R.color.google_blue),
                getResources().getColor(R.color.google_green),
                getResources().getColor(R.color.google_red),
                getResources().getColor(R.color.google_yellow));
        mtvRefreshHint = (TextView) layout.findViewById(R.id.tvRefreshHint);
        mrvNewGoods = (RecyclerView) layout.findViewById(R.id.rvNewGoods);
        mActivity = (MainActivity) getActivity();
        mLayoutManager = new GridLayoutManager(mActivity, I.COLUM_NUM);
        // 设置页脚居中显示
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
            @Override
            public int getSpanSize(int position) {
                int viewType = mNewGoodsAdapter.getItemViewType(position);
                if (viewType == 0) {
                    return 2;
                }
                return 1;
            }
        });
        mrvNewGoods.setLayoutManager(mLayoutManager);
        mNewGoodsList = new ArrayList<>();
        mNewGoodsAdapter = new NewGoodsAdapter(mActivity, mNewGoodsList);
        mrvNewGoods.setAdapter(mNewGoodsAdapter);
        mrvNewGoods.addItemDecoration(new SpaceItemDecoration(12));
    }
}