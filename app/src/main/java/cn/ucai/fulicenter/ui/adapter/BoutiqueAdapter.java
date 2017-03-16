package cn.ucai.fulicenter.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.model.bean.BoutiqueBean;
import cn.ucai.fulicenter.model.utils.ImageLoader;
import cn.ucai.fulicenter.ui.activity.BoutiqueChildActivity;

/**
 * Created by LPP on 2017/3/15.
 */

public class BoutiqueAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<BoutiqueBean> boutiqueList;

    public BoutiqueAdapter(Context context, ArrayList<BoutiqueBean> boutiqueList) {
        this.context = context;
        this.boutiqueList = boutiqueList;
    }

    public void initBoutiqueList(ArrayList<BoutiqueBean> mBoutiqueList) {
        this.boutiqueList.clear();
        this.boutiqueList.addAll(mBoutiqueList);
        notifyDataSetChanged();
    }

    class BoutiqueHolder extends RecyclerView.ViewHolder {
        ImageView ivBoutiqueImg;
        TextView tvBoutiqueTitle,tvBoutiqueName,tvBoutiqueDescription;
        public BoutiqueHolder(View itemView) {
            super(itemView);
            ivBoutiqueImg = (ImageView) itemView.findViewById(R.id.ivBoutiqueImg);
            tvBoutiqueTitle = (TextView) itemView.findViewById(R.id.tvBoutiqueTitle);
            tvBoutiqueName = (TextView) itemView.findViewById(R.id.tvBoutiqueName);
            tvBoutiqueDescription = (TextView) itemView.findViewById(R.id.tvBoutiqueDescription);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BoutiqueHolder(View.inflate(context, R.layout.item_boutique, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BoutiqueHolder boutiqueHolder = (BoutiqueHolder) holder;
        final BoutiqueBean boutique = boutiqueList.get(position);
        boutiqueHolder.tvBoutiqueTitle.setText(boutique.getTitle());
        boutiqueHolder.tvBoutiqueName.setText(boutique.getName());
        boutiqueHolder.tvBoutiqueDescription.setText(boutique.getDescription());
        ImageLoader.downloadImg(context,boutiqueHolder.ivBoutiqueImg,boutique.getImageurl());
        boutiqueHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, BoutiqueChildActivity.class)
                        .putExtra(I.Boutique.CAT_ID,boutique.getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return boutiqueList!=null?boutiqueList.size():0;
    }
}
