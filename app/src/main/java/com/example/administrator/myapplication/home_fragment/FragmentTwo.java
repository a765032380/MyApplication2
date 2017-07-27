package com.example.administrator.myapplication.home_fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.shop.R;
import com.example.administrator.myapplication.adapter.HomeAdapter;
import com.example.administrator.myapplication.status.BianLiDianStatus;
import com.example.administrator.myapplication.model.DingDan;
import com.example.administrator.myapplication.request.RequestURL;
import com.example.administrator.myapplication.update.network.RequestCenter;
import com.example.administrator.myapplication.util.MyUntil;

import java.util.List;

/**
 * Created by Administrator on 2017/6/29 0029.
 */

public class FragmentTwo extends Fragment  implements SwipeRefreshLayout.OnRefreshListener{
    private static final int TYPE_TWO=1;

    private View view;
    private ListView mListView;
    private HomeAdapter adapter;
    private Context mContext;
    private SwipeRefreshLayout swipe;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.home_fragment_select,container,false);
        mContext=getActivity();
        initUI();
        initData();
        return view;
    }
    private void initUI(){
        swipe= (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        swipe.setOnRefreshListener(this);
        mListView= (ListView) view.findViewById(R.id.lv_home);
    }
    private void initData() {
      getmList();
    }
    private List<DingDan.ResultBean> mList;
    public void getmList(){
        String url= RequestURL.URL_ORDER_LIST+"type="+TYPE_TWO;
        RequestCenter.order_list(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DingDan dingDan= (DingDan) responseObj;
                if (dingDan.getCode()== BianLiDianStatus.STATUS_CODE_SUCCESS){
                    mList=dingDan.getResult();
                    for (int i=0;i<mList.size();i++){
                        if (mList.get(i).getOrderInfo().getSubStatus()!=0){
                            mList.remove(i);
                        }
                    }

                    adapter=new HomeAdapter(mContext,mList,TYPE_TWO);
                    mListView.setAdapter(adapter);
                }else {
                    MyUntil.show(getContext(),dingDan.getMsg());
                }
            }
            @Override
            public void onFailure(Object reasonObj) {

            }
        });
    }

    @Override
    public void onRefresh() {
        initData();
        swipe.setRefreshing(false);
    }
}