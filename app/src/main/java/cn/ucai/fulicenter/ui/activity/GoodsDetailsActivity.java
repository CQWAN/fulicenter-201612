package cn.ucai.fulicenter.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.model.bean.GoodsDetailsBean;
import cn.ucai.fulicenter.model.net.INewGoodsDetailsModel;
import cn.ucai.fulicenter.model.net.NewGoodsDetailsModel;
import cn.ucai.fulicenter.model.net.OnCompleteListener;

public class GoodsDetailsActivity extends AppCompatActivity {
    int goodsId = 0;
    INewGoodsDetailsModel mGoodsDetailsModel;
    TextView mtvGoodsEnglishName,mtvGoodsName,mtvCurrencyPrice,mtvPromotePrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);
        initView();
        goodsId = getIntent().getIntExtra(I.Goods.KEY_GOODS_ID, 0);
        initData();
    }

    private void initView() {
        mtvGoodsEnglishName = (TextView) findViewById(R.id.tvEnglishGoodsName);
        mtvGoodsName = (TextView) findViewById(R.id.tvGoodsName);
        mtvCurrencyPrice = (TextView) findViewById(R.id.tvCurrencyPrice);
        mtvPromotePrice = (TextView) findViewById(R.id.tvPromotePrice);
    }

    private void initData() {
        mGoodsDetailsModel = new NewGoodsDetailsModel();
        mGoodsDetailsModel.loadData(this, goodsId, new OnCompleteListener<GoodsDetailsBean>() {
            @Override
            public void onSuccess(GoodsDetailsBean result) {
                if (result != null) {
                    // 成功后显示数据
                    String goodsEnglishName = result.getGoodsEnglishName();
                    mtvGoodsEnglishName.setText(goodsEnglishName);
                    String goodsName = result.getGoodsName();
                    mtvGoodsName.setText(goodsName);
                    String currencyPrice = result.getCurrencyPrice();
                    mtvCurrencyPrice.setText(currencyPrice);
                    String promotePrice = result.getPromotePrice();
                    mtvPromotePrice.setText(promotePrice);
                } else {
                    Toast.makeText(GoodsDetailsActivity.this, "数据加载失败", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onError(String error) {
                Log.e("main", error);
            }
        });
    }
}
