package cn.ucai.fulicenter.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.model.bean.NewGoodsBean;
import cn.ucai.fulicenter.model.utils.ImageLoader;
import cn.ucai.fulicenter.ui.activity.GoodsDetailsActivity;

/**
 * Created by LPP on 2017/3/15.
 */

public class NewGoodsAdapter extends RecyclerView.Adapter {
    static final int TYPE_FOOTER = 0;
    static final int TYPE_ITEM = 1;

    Context context;
    ArrayList<NewGoodsBean> newGoodsList;
    public NewGoodsAdapter(Context context, ArrayList<NewGoodsBean> newGoodsList) {
        this.context = context;
        this.newGoodsList = newGoodsList;
    }
    String textFooter;

    public String getTextFooter() {
        return textFooter;
    }

    public void setTextFooter(String textFooter) {
        this.textFooter = textFooter;
        notifyDataSetChanged();
    }
    boolean isMore;

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
    }

    public void initNewGoodsList(ArrayList<NewGoodsBean> mNewGoodsList) {
        this.newGoodsList.clear();
        addNewGoodsList(mNewGoodsList);
    }

    public void addNewGoodsList(ArrayList<NewGoodsBean> mNewGoodsList) {
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

    class FooterHolder extends RecyclerView.ViewHolder {
        TextView tvFooter;
        public FooterHolder(View itemView) {
            super(itemView);
            tvFooter = (TextView) itemView.findViewById(R.id.tvFooter);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_FOOTER:
                return new FooterHolder(View.inflate(context, R.layout.item_footer, null));
            case TYPE_ITEM:
                return new NewGoodsHolder(View.inflate(context,R.layout.item_goods,null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_FOOTER) {
            FooterHolder footerHolder = (FooterHolder) holder;
            footerHolder.tvFooter.setVisibility(View.VISIBLE);
            footerHolder.tvFooter.setText(textFooter);
            return;
        }
        NewGoodsHolder newGoodsHolder = (NewGoodsHolder) holder;
        final NewGoodsBean newGoods = newGoodsList.get(position);
        newGoodsHolder.tvGoodsName.setText(newGoods.getGoodsName());
        newGoodsHolder.tvGoodsPrice.setText(newGoods.getCurrencyPrice());
        ImageLoader.downloadImg(context,newGoodsHolder.ivGoods,newGoods.getGoodsThumb());
        newGoodsHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, GoodsDetailsActivity.class)
                .putExtra(I.Goods.KEY_GOODS_ID,newGoods.getGoodsId()));
            }
        });
    }

    public void sortBy(int sortType) {
        switch (sortType) {
            case I.SORT_BY_ADDTIME_DESC:
                Collections.sort(newGoodsList, new Comparator<NewGoodsBean>() {
                    @Override
                    public int compare(NewGoodsBean o1, NewGoodsBean o2) {
                        return (int) (o2.getAddTime()-o1.getAddTime());
                    }
                });
                break;
            case I.SORT_BY_ADDTIME_ASC:
                Collections.sort(newGoodsList, new Comparator<NewGoodsBean>() {
                    @Override
                    public int compare(NewGoodsBean o1, NewGoodsBean o2) {
                        return (int) (o1.getAddTime()-o2.getAddTime());
                    }
                });
                break;
            case I.SORT_BY_PRICE_DESC:
                Collections.sort(newGoodsList, new Comparator<NewGoodsBean>() {
                    @Override
                    public int compare(NewGoodsBean o1, NewGoodsBean o2) {
                        return Integer.parseInt(o2.getCurrencyPrice().substring(o2.getCurrencyPrice().indexOf("￥"+1)))
                                -Integer.parseInt(o1.getCurrencyPrice().substring(o2.getCurrencyPrice().indexOf("￥"+1)));
                    }
                });
                break;
            case I.SORT_BY_PRICE_ASC:
                Collections.sort(newGoodsList, new Comparator<NewGoodsBean>() {
                    @Override
                    public int compare(NewGoodsBean o1, NewGoodsBean o2) {
                        return Integer.parseInt(o1.getCurrencyPrice().substring(o1.getCurrencyPrice().indexOf("￥"+1)))
                                -Integer.parseInt(o2.getCurrencyPrice().substring(o2.getCurrencyPrice().indexOf("￥"+1)));
                    }
                });
                break;
        }
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return newGoodsList.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }
}
