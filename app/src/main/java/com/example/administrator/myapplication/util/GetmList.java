package com.example.administrator.myapplication.util;

import android.content.Context;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.example.administrator.myapplication.status.BianLiDianStatus;
import com.example.administrator.myapplication.model.DingDan;
import com.example.administrator.myapplication.request.RequestURL;
import com.example.administrator.myapplication.update.network.RequestCenter;

import java.util.List;

/**
 * Created by Administrator on 2017/7/3 0003.
 */

public class GetmList {

    private List<DingDan.ResultBean> mList;
    public void getmList(final Context context, int type){

        String url= RequestURL.URL_ORDER_LIST+"type="+type;
        RequestCenter.order_list(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DingDan dingDan= (DingDan) responseObj;
                if (dingDan.getCode()== BianLiDianStatus.STATUS_CODE_SUCCESS){
                    mList=dingDan.getResult();
                }else {
                    MyUntil.show(context,dingDan.getMsg());
                }

            }
            @Override
            public void onFailure(Object reasonObj) {

            }
        });
    }
}
