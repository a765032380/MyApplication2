package com.bjxiyang.zhinengshequ.shop.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.shop.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class TongJiFragment extends Fragment {

    /**
     * UI
     */
    @BindView(R.id.tv_shangpinzongshu)
    TextView mSPAllCount;
    @BindView(R.id.tv_zhuanhualv)
    TextView tv_zhuanhualv;
    @BindView(R.id.tv_xinzengrenshu)
    TextView tv_xinzengrenshu;

    @BindView(R.id.tv_huitouke)
    TextView tv_huitouke;
    @BindView(R.id.tv_kehuzongshu)
    TextView tv_kehuzongshu;
    @BindView(R.id.tv_huitoukebili)
    TextView tv_huitoukebili;
    @BindView(R.id.tv_zhiifudingdan)
    TextView tv_zhiifudingdan;
    @BindView(R.id.tv_tuikuandingdan)
    TextView tv_tuikuandingdan;
    @BindView(R.id.tv_zhifuzhuanhualv)
    TextView tv_zhifuzhuanhualv;
    @BindView(R.id.tv_tuiluanlv)
    TextView tv_tuiluanlv;



    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_count,container,false);
        ButterKnife.bind(this,view) ;
        initUi();
        return view;
    }

    private void initUi() {

    }
}
