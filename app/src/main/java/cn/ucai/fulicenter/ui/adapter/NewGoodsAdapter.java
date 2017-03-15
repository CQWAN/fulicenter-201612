package cn.ucai.fulicenter.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.model.bean.NewGoodsBean;

/**
 * Created by LPP on 2017/3/15.
 */

public class NewGoodsAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<NewGoodsBean> newGoodsList;

    public NewGoodsAdapter(Context context, ArrayList<NewGoodsBean> newGoodsList) {
        this.context = context;
        this.newGoodsList = newGoodsList;
    }

    public void initNewGoodsList(ArrayList<NewGoodsBean> mNewGoodsList) {
        this.newGoodsList.clear();
        this.newGoodsList.addAll(mNewGoodsList);
        notifyDataSetChanged();
    }

    class NewGoodsHolder extends RecyclerView.ViewHolder {
        TextView tvGoodsName,tvGoodsPrice;
        ImageView ivGoods;
        public NewGoodsHolder(View itemView) {
            super(itemView);
            tvGoodsName = (TextView) itemView.findViewById(R.id.tvGoodsName);
            tvGoodsPrice = (TextView) itemView.findViewById(R.id.tvGoodsPrice);
            ivGoods = (ImageView) itemView.findViewById(R.id.ivNewGoods);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewGoodsHolder(View.inflate(context,R.layout.item_goods,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewGoodsHolder newGoodsHolder = (NewGoodsHolder) holder;
        NewGoodsBean newGoods = newGoodsList.get(position);
        newGoodsHolder.tvGoodsName.setText(newGoods.getGoodsName());
        newGoodsHolder.tvGoodsPrice.setText(newGoods.getCurrencyPrice());
    }

    @Override
    public int getItemCount() {
        return newGoodsList!=null?newGoodsList.size():0;
    }
}
