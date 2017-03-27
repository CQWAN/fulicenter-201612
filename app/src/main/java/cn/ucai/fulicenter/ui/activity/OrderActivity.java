package cn.ucai.fulicenter.ui.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.application.FuLiCenterApplication;
import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.model.bean.CartBean;
import cn.ucai.fulicenter.model.bean.User;
import cn.ucai.fulicenter.model.net.NetDao;
import cn.ucai.fulicenter.model.utils.L;
import cn.ucai.fulicenter.model.utils.OkHttpUtils;
import cn.ucai.fulicenter.model.utils.ResultUtils;
import cn.ucai.fulicenter.ui.view.DisplayUtils;

/**
 * Created by LPP on 2017/3/27.
 */
public class OrderActivity extends BaseActivity {
    public static final String TAG = OrderActivity.class.getSimpleName();
    @BindView(R.id.tv_common_title)
    TextView mTvCommonTitle;
    @BindView(R.id.ed_order_name)
    EditText mEtOrderName;
    @BindView(R.id.ed_order_phone)
    EditText mEtOrderPhone;
    @BindView(R.id.spin_order_province)
    Spinner mSpinOrderProvince;
    @BindView(R.id.ed_order_street)
    EditText mEtOrderStreet;
    @BindView(R.id.tv_order_price)
    TextView mTvOrderPrice;
    OrderActivity mContext;
    User user = null;
    String cartIds = "";
    ArrayList<CartBean> mList = null;
    String[] ids = new String[]{};
    int rankPrice = 0;

    private static String URL = "http://218.244.151.190/demo/charge";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        mContext = this;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        DisplayUtils.initBackWithTitle(mContext,getString(R.string.confirm_order));
    }

    @Override
    protected void initData() {
        cartIds = getIntent().getStringExtra(I.Cart.ID);
        user = FuLiCenterApplication.getUser();
        L.e(TAG,"cartIds="+cartIds);
        if(cartIds==null || cartIds.equals("")
                || user==null){
            finish();
        }
        ids = cartIds.split(",");
        geOrderList();
    }

    private void geOrderList() {

    }

    @Override
    protected void setListener() {
        NetDao.downloadCart(mContext, user.getMuserName(), new OkHttpUtils.OnCompleteListener<String>() {
            @Override
            public void onSuccess(String s) {
                ArrayList<CartBean> list = ResultUtils.getCartFromJson(s);
                if(list==null || list.size()==0){
                    finish();
                }else{
                    mList.addAll(list);
                    sumPrice();
                }
            }

            private void sumPrice() {
                rankPrice = 0;
                if(mList!=null && mList.size()>0){
                    for (CartBean c:mList){
                        L.e(TAG,"c.id="+c.getId());
                        for (String id:ids) {
                            L.e(TAG,"order.id="+id);
                            if (id.equals(String.valueOf(c.getId()))) {
                                rankPrice += getPrice(c.getGoods().getRankPrice()) * c.getCount();
                            }
                        }
                    }
                }
                mTvOrderPrice.setText("合计:￥"+Double.valueOf(rankPrice));
            }
            private int getPrice(String price){
                price = price.substring(price.indexOf("￥")+1);
                return Integer.valueOf(price);
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    @OnClick(R.id.backClickArea)
    public void onClick() {
    }
    @OnClick(R.id.tv_order_buy)
    public void buy(){

    }
}
