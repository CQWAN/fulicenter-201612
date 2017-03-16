package cn.ucai.fulicenter.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.ui.fragment.NewGoodsFragment;

public class BoutiqueChildActivity extends AppCompatActivity {
    TextView mtvGoodsTitle;
    ImageView mivBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boutique_child);
        initView();
        setListener();
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_content,new NewGoodsFragment()).commit();
    }

    private void setListener() {
        mivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        mtvGoodsTitle = (TextView) findViewById(R.id.tvMySelect);
        mtvGoodsTitle.setText(getIntent().getStringExtra("goodsTitle"));
        mivBack = (ImageView) findViewById(R.id.ivBack);
    }
}
