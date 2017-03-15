package cn.ucai.fulicenter.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.model.bean.NewGoodsBean;
import cn.ucai.fulicenter.ui.activity.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewGoodsFragment extends Fragment {
    RecyclerView mrvNewGoods;
    ArrayList<NewGoodsBean> mNewGoodsList;
    GridLayoutManager mLayoutManager;
    int mPageId;
    MainActivity mActivity;


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

    }

    class FooterHolder extends RecyclerView.ViewHolder {
        TextView tvFooter;
        public FooterHolder(View itemView) {
            super(itemView);
            tvFooter = (TextView) itemView.findViewById(R.id.tvFooter);
        }
    }

    class NewGoodsHolder extends RecyclerView.ViewHolder {
        ImageView ivNewGoods;
        public NewGoodsHolder(View itemView) {
            super(itemView);
            ivNewGoods = (ImageView) itemView.findViewById(R.id.ivNewGoods);
        }
    }

    class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        Context context;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}