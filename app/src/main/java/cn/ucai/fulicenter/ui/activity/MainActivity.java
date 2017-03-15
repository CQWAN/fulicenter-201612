package cn.ucai.fulicenter.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.ui.fragment.BoutiqueFragment;
import cn.ucai.fulicenter.ui.fragment.CartFragment;
import cn.ucai.fulicenter.ui.fragment.CategoryFragment;
import cn.ucai.fulicenter.ui.fragment.MeFragment;
import cn.ucai.fulicenter.ui.fragment.NewGoodsFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rbNewGoods)
    RadioButton mrbNewGoods;
    @BindView(R.id.rbBoutique)
    RadioButton mrbBoutique;
    @BindView(R.id.rbCategory)
    RadioButton mrbCategory;
    @BindView(R.id.rbCart)
    RadioButton mrbCart;
    @BindView(R.id.rbContact)
    RadioButton mrbContact;
    @BindView(R.id.rgMenu)
    RadioGroup mrgMenu;
    @BindView(R.id.layout_content)
    FrameLayout mlayoutContent;
    Unbinder bind;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind = ButterKnife.bind(this);
    }

    public void onCheckedChange(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.rbNewGoods:
                transaction.replace(R.id.layout_content,new NewGoodsFragment()).commit();
                break;
            case R.id.rbBoutique:
                transaction.replace(R.id.layout_content,new BoutiqueFragment()).commit();
                break;
            case R.id.rbCategory:
                transaction.replace(R.id.layout_content,new CategoryFragment()).commit();
                break;
            case R.id.rbCart:
                transaction.replace(R.id.layout_content,new CartFragment()).commit();
                break;
            case R.id.rbContact:
                transaction.replace(R.id.layout_content,new MeFragment()).commit();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
