package cn.ucai.fulicenter.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.ui.fragment.NewGoodsFragment;

public class BoutiqueChildActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boutique_child);

        getSupportFragmentManager().beginTransaction().replace(R.id.layout_content,new NewGoodsFragment()).commit();
    }
}
