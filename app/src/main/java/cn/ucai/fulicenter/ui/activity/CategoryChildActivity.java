package cn.ucai.fulicenter.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.ui.fragment.NewGoodsFragment;

public class CategoryChildActivity extends AppCompatActivity implements View.OnClickListener{
    int sortByPrice = I.SORT_BY_PRICE_ASC;
    int sortByTime = I.SORT_BY_PRICE_DESC;
    TextView tvChildGoodsPrice,tvChildGoodsTime;
    NewGoodsFragment newGoodsFragment = new NewGoodsFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_child);
        // 下载并适配数据
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_content, newGoodsFragment).commit();
        // 调用排序的方法
        initView();
        setListener();
    }

    private void setListener() {
        tvChildGoodsPrice.setOnClickListener(this);
        tvChildGoodsTime.setOnClickListener(this);
    }

    private void initView() {
        tvChildGoodsPrice = (TextView) findViewById(R.id.tvChildGoodsPrice);
        tvChildGoodsTime = (TextView) findViewById(R.id.tvChildGoodsTime);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvChildGoodsPrice:
                if (sortByPrice == I.SORT_BY_PRICE_ASC) {
                    newGoodsFragment.mNewGoodsAdapter.sortBy(I.SORT_BY_ADDTIME_DESC);
                } else {
                    newGoodsFragment.mNewGoodsAdapter.sortBy(I.SORT_BY_ADDTIME_ASC);
                }
                break;
            case R.id.tvChildGoodsTime:
                if (sortByTime == I.SORT_BY_ADDTIME_DESC) {
                    newGoodsFragment.mNewGoodsAdapter.sortBy(I.SORT_BY_ADDTIME_ASC);
                } else {
                    newGoodsFragment.mNewGoodsAdapter.sortBy(I.SORT_BY_ADDTIME_DESC);
                }
                break;
        }
    }
}
