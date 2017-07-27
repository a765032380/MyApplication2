package com.example.administrator.myapplication.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.baisi.myapplication.okhttp.request.RequestParams;
import com.bjxiyang.zhinengshequ.shop.R;
import com.example.administrator.myapplication.activity.DengLuActivity;
import com.example.administrator.myapplication.activity.DianZhuXinXiActivity;
import com.example.administrator.myapplication.activity.ShangJiaXinXiActivity;
import com.example.administrator.myapplication.bluetooth.MainActivity;
import com.example.administrator.myapplication.bluetooth.PrinterConnectDialog;
import com.example.administrator.myapplication.manager.SPManager;
import com.example.administrator.myapplication.model.UpdateVersion;
import com.example.administrator.myapplication.status.BianLiDianStatus;
import com.example.administrator.myapplication.dialog.MyDialog;
import com.example.administrator.myapplication.manager.UserManager;
import com.example.administrator.myapplication.model.Logout;
import com.example.administrator.myapplication.request.RequestURL;
import com.example.administrator.myapplication.update.CommonDialog;
import com.example.administrator.myapplication.update.network.RequestCenter;
import com.example.administrator.myapplication.update.service.UpdateService;
import com.example.administrator.myapplication.update.util.Util;
import com.example.administrator.myapplication.util.DialogUntil;
import com.example.administrator.myapplication.util.MyUntil;
import com.example.administrator.myapplication.view.CircleImageView;

/**
 * Created by Administrator on 2017/6/29 0029.
 */

public class MyFragment extends MainActivity implements View.OnClickListener{
    private View view;

    /**
     * UI
     */
    private CircleImageView dianpu_touxiang;
    private ImageView iv_dianpu_yingyezhong;
    private ImageView iv_dianpu_connected;
    private TextView tv_dianpu_yingyezhong;
    private TextView tv_dianpu_connected;
    private LinearLayout ll_shangjiaxinxi;
    private LinearLayout ll_dianzhuxinxi;
    private Button siginoutbutton;
//    private Button jianchagengxin;
    private LinearLayout dianpu_update;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_dianpu,container,false);
        initUi();
        return view;
    }

    private void initUi() {
        dianpu_update= (LinearLayout) view.findViewById(R.id.dianpu_update);
        dianpu_update.setOnClickListener(this);
//        jianchagengxin = (Button) view.findViewById(R.id.jianchagengxin);
        tv_dianpu_connected= (TextView) view.findViewById(R.id.tv_dianpu_connected);
        tv_dianpu_yingyezhong= (TextView) view.findViewById(R.id.tv_dianpu_yingyezhong);
        dianpu_touxiang= (CircleImageView) view.findViewById(R.id.dianpu_touxiang);
        iv_dianpu_yingyezhong= (ImageView) view.findViewById(R.id.iv_dianpu_yingyezhong);
        iv_dianpu_connected= (ImageView) view.findViewById(R.id.iv_dianpu_connected);
        ll_shangjiaxinxi= (LinearLayout) view.findViewById(R.id.ll_shangjiaxinxi);
        ll_dianzhuxinxi= (LinearLayout) view.findViewById(R.id.ll_dianzhuxinxi);
        siginoutbutton= (Button) view.findViewById(R.id.siginoutbutton);
//        jianchagengxin.setOnClickListener(this);
        iv_dianpu_yingyezhong.setOnClickListener(this);
        iv_dianpu_connected.setOnClickListener(this);
        ll_shangjiaxinxi.setOnClickListener(this);
        ll_dianzhuxinxi.setOnClickListener(this);
        siginoutbutton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //店铺营业状态的按键
            case R.id.iv_dianpu_yingyezhong:

                break;
            //店铺打印机的按键
            case R.id.iv_dianpu_connected:
                Intent bluetooth = new Intent(getContext(),PrinterConnectDialog.class);
                boolean[] state = getConnectState();
                bluetooth.putExtra(CONNECT_STATUS, state);
                startActivity(bluetooth);
                break;
            //商家信息的按键
            case R.id.ll_shangjiaxinxi:
                Intent intent=new Intent(getContext(), ShangJiaXinXiActivity.class);
                startActivity(intent);
                break;
            //店铺信息的按键
            case R.id.ll_dianzhuxinxi:
                Intent intent1=new Intent(getContext(), DianZhuXinXiActivity.class);
                startActivity(intent1);
                break;
            //检查更新的按键
//            case R.id.jianchagengxin:
//                checkVersion();
//                break;
            case R.id.dianpu_update:
                checkVersion();
                break;

            //退出的按键
            case R.id.siginoutbutton:
                logout();
                break;
        }
    }
    //检查更新代码
    private void checkVersion() {
        DialogUntil.showLoadingDialog(getContext(),"正在检查更新",true);
        RequestCenter.checkVersion(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DialogUntil.closeLoadingDialog();

                final UpdateVersion updateModel = (UpdateVersion) responseObj;

                if (updateModel.getCode().equals("1000")) {
                    UpdateVersion.ObjBean obj=updateModel.getObj();
                    if (Double.valueOf(Util.getVersionCode(getActivity()))<Double.valueOf(obj.getVerSn())) {
                        //说明有新版本,开始下载
                        CommonDialog dialog = new CommonDialog(getActivity(),
                                getString(R.string.update_new_version),
                                obj.getVerDescript(),
                                getString(R.string.cancel),
                                getString(R.string.update_install),
                                new CommonDialog.DialogClickListener() {
                                    @Override
                                    public void onDialogClick() {
                                        Intent intent = new Intent(getActivity(), UpdateService.class);
                                        intent.putExtra("APPURL", updateModel.getObj().getVerUrl());
                                        getActivity().startService(intent);
                                    }
                                });
                        dialog.setCancelable(false);
                        dialog.show();
                    } else {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                        dialog.setTitle("提示");
                        dialog.setCancelable(false);
                        dialog.setMessage("该版本已是最新版本");
                        dialog.setIcon(R.mipmap.ic_launcher);
                        dialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                            }
                        });
                        dialog.show();
                        //弹出一个toast提示当前已经是最新版本等处理
                    }
                }else {
//                    MyUntil.show(getContext(),updateModel.getMsg());
                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();

                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                AlertDialog dialog=builder
                        .setIcon(R.mipmap.ic_launcher)
                        .setTitle("检查更新")
                        .setMessage("网络连接失败")
                        .setPositiveButton("确定", null)
                        .show();

            }
        });
    }
    private void logout(){
        DialogUntil.showLoadingDialog(getContext(),"正在登陆",true);
        String url= RequestURL.URL_LOGOUT;
        RequestParams headers=new RequestParams();
        headers.put("private-token", SPManager.getInstance().getString("loginKey",""));
        RequestCenter.logout(url,headers, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DialogUntil.closeLoadingDialog();
                Logout logout= (Logout) responseObj;
                if (logout.getCode()== BianLiDianStatus.STATUS_CODE_SUCCESS){
                    SPManager.getInstance().remove("loginKey");
                    Intent intent=new Intent(getContext(), DengLuActivity.class);
                    startActivity(intent);
                }else {
                    MyUntil.show(getContext(),logout.getMsg());
                }
            }
            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();
                MyDialog.showDialog(getContext());
            }
        });


    }

}
