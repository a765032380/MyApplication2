package com.example.administrator.myapplication.activity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.shop.R;
import com.example.administrator.myapplication.fragment.HomeFragment;
import com.example.administrator.myapplication.fragment.MyFragment;
import com.example.administrator.myapplication.fragment.ShoppFragment;

/**
 * Created by gll on 17-5-20.
 */

public class MainActivity extends BeasActivity implements View.OnClickListener{

    private RelativeLayout mHomeLayout;
    private RelativeLayout mShangPingLayout;
    private RelativeLayout mMyLayout;

    private TextView mHomeView;
    private TextView mShoppView;
    private TextView mMyView;

    private FragmentManager fm;
    private Fragment mHomeFragment;
    private Fragment mMyFragment;
    private Fragment mShoppFragment;

    public static LinearLayout linearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDate();
        initUi();
    }
    private void initUi() {
        fm=getSupportFragmentManager();
        //获取事务
        FragmentTransaction fragmentTransaction=fm.beginTransaction();
        //显示首页的Fragment
        mHomeFragment=new HomeFragment();
        fragmentTransaction.add(R.id.content_layout,mHomeFragment).show(mHomeFragment);
        fragmentTransaction.commit();

        mHomeLayout= (RelativeLayout) findViewById(R.id.dingdan_layout_view);
        mHomeLayout.setOnClickListener(this);
        mShangPingLayout=(RelativeLayout) findViewById(R.id.shangpin_layout_view);
        mShangPingLayout.setOnClickListener(this);
        mMyLayout= (RelativeLayout) findViewById(R.id.shangjia_layout_view);
        mMyLayout.setOnClickListener(this);
        mHomeView= (TextView) findViewById(R.id.home_image_view);
        mShoppView= (TextView) findViewById(R.id.fish_image_view);
        mMyView= (TextView) findViewById(R.id.mine_image_view);
        mHomeView.setBackgroundResource(R.drawable.z_icon_dingdan);

        linearLayout= (LinearLayout) findViewById(R.id.linearLayout);
    }
    private void initDate() {

    }
    //四个按键的监听事件，以及对四个Fragment的操作
    public void onClick(View view) {
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        switch (view.getId()) {
            //主页
            case R.id.dingdan_layout_view:
                mHomeView.setBackgroundResource(R.drawable.z_icon_dingdan);
                mShoppView.setBackgroundResource(R.drawable.z_icon_shangpin_a);
                mMyView.setBackgroundResource(R.drawable.z_icon_dianpu_a);
                hideFragment(mHomeFragment,fragmentTransaction);
                hideFragment(mMyFragment,fragmentTransaction);
                hideFragment(mShoppFragment,fragmentTransaction);
//                将我们的HomeFragment显示出来
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.content_layout, mHomeFragment).show(mHomeFragment).commit();
                } else {
                    fragmentTransaction.show(mHomeFragment).commit();
                }
                break;
            //金融
            case R.id.shangpin_layout_view:
                mHomeView.setBackgroundResource(R.drawable.z_icon_dingdan_a);
                mShoppView.setBackgroundResource(R.drawable.z_icon_shangpin);
                mMyView.setBackgroundResource(R.drawable.z_icon_dianpu_a);
                hideFragment(mHomeFragment, fragmentTransaction);
                hideFragment(mMyFragment, fragmentTransaction);
                hideFragment(mShoppFragment, fragmentTransaction);
                //将我们的HomeFragment显示出来
                if (mShoppFragment == null) {
                    mShoppFragment = new ShoppFragment();
                    fragmentTransaction.add(R.id.content_layout, mShoppFragment).show(mShoppFragment).commit();
                } else {
                    fragmentTransaction.show(mShoppFragment).commit();
                }
                break;
            //我的
            case R.id.shangjia_layout_view:
                mHomeView.setBackgroundResource(R.drawable.z_icon_dingdan_a);
                mShoppView.setBackgroundResource(R.drawable.z_icon_shangpin_a);
                mMyView.setBackgroundResource(R.drawable.z_icon_dianpu);
                hideFragment(mHomeFragment, fragmentTransaction);
                hideFragment(mShoppFragment, fragmentTransaction);
                hideFragment(mMyFragment, fragmentTransaction);
                //将我们的HomeFragment显示出来
                if (mMyFragment == null) {
                    mMyFragment = new MyFragment();
                    fragmentTransaction.add(R.id.content_layout, mMyFragment).show(mMyFragment).commit();
                } else {
                    fragmentTransaction.show(mMyFragment).commit();
                }
                break;
        }
    }
        /**
         * 用来隐藏具体的Fragment
         * @param fragment
         * @param ft
         */
    private void hideFragment(Fragment fragment, FragmentTransaction ft) {
        if (fragment != null) {
            ft.hide(fragment);
        }
    }

    private static final String[] PHONES_PROJECTION = new String[] {
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER};
    //动态权限
//    private void quanxian(){
//        if (Build.VERSION.SDK_INT >= 23) {
//            getQuanXian(Manifest.permission.WRITE_EXTERNAL_STORAGE,1);
//            getQuanXian(Manifest.permission.READ_EXTERNAL_STORAGE,1);
//            getQuanXian(Manifest.permission.READ_CONTACTS,10);
//            getQuanXian(Manifest.permission.READ_PHONE_STATE,1);
//            getQuanXian(Manifest.permission.CALL_PHONE,3);
//            getQuanXian(Manifest.permission.GET_ACCOUNTS,4);
//            getQuanXian(Manifest.permission.WRITE_CALENDAR,5);
//            getQuanXian(Manifest.permission.RECORD_AUDIO,7);
//            getQuanXian(Manifest.permission.READ_SMS,8);
//        }
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.WRITE_CONTACTS) == PackageManager.PERMISSION_GRANTED){
//            if (!SPManager.getInstance().getBoolean("isShangchuan",false)){
//                getPhoneNumberFromMobile();
//            }
//        }
//    }
    //动态权限的封装
    private void getQuanXian(String permission,int code){
        int permissionInt= ContextCompat.checkSelfPermission(this,
                permission);
        if(permissionInt != PackageManager.PERMISSION_GRANTED){
            android.support.v4.app.ActivityCompat.requestPermissions(this,
                    new String[]{permission},code);
            return;
        }else{

        }

    }


}

