package com.example.administrator.myapplication.update.util;

import com.baisi.myapplication.okhttp.request.RequestParams;
import com.example.administrator.myapplication.manager.SPManager;
import com.example.administrator.myapplication.manager.UserManager;

/**
 * Created by Administrator on 2017/7/3 0003.
 */

public class GetHeaders {
    public static RequestParams getHeaders(){
        RequestParams headers=new RequestParams();
        headers.put("private-token", SPManager.getInstance().getString("loginKey",""));
//        headers.put("private-token", "a600dd01ffefbaf03d670bf5e20743e1");
//                UserManager.getInstance().getUser().getResult().getLoginKey());
        return headers;
    }

}
