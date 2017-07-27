package com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.shop.R;
import com.example.administrator.myapplication.model.CreateDianZhu;
import com.example.administrator.myapplication.model.GetDianZhu;
import com.example.administrator.myapplication.model.ImageUrl;
import com.example.administrator.myapplication.request.RequestURL;
import com.example.administrator.myapplication.status.BianLiDianStatus;
import com.example.administrator.myapplication.update.network.RequestCenter;
import com.example.administrator.myapplication.util.MyUntil;
import com.example.administrator.myapplication.view.CircleImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/30 0030.
 */

public class DianZhuXinXiActivity extends MySwipeBackActivity implements View.OnClickListener {


    final int RESULT_LOAD_IMAGE=1;
    final int RESULT_LOAD_IMAGE_YINGYE=2;
    final int RESULT_LOAD_IMAGE_WEISHENG=3;

    final String RESULT_LOAD_IMAGE_STRING="piclist";
    List<Map<String,File>> list=new ArrayList<>();
//    final String RESULT_LOAD_IMAGE_YINGYE_STRING="";
//    final String RESULT_LOAD_IMAGE_WEISHENG_STRING="";

    /**
     * UI
     */
    private RelativeLayout rl_dianzhuxinxi_fanhui;
    private TextView tv__dianzhuxinxi_save;
    private EditText et_dianzhuxinxi_name;
    private ImageView iv_dianzhuxinxi_men;
    private ImageView iv_dianzhuxinxi_women;
    private EditText et_dianzhuxinxi_tel;
    private EditText et_dianzhuxinxi_age;
    private ImageView iv_yingyezhizhao;
    private ImageView iv_weishengxuke;
    private CircleImageView iv_xiugaixinxi_xiugai_touxiang;

    /**
     * Data
     */
    private int sex=1;
    private String name;
    private String phone;
    private String age;
    private String touxiang;
    private String yingye;
    private String weisheng;


    private int type1;
    private String picturePath;
    private File mFile;
    private File mFile_yingye;
    private File mFile_weisheng;

    private Map map=new HashMap();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dianzhuxinxi);

        initUI();
        initData();
    }

    private void initData() {
        String shop_get=RequestURL.URL_SHOP_GET;
        RequestCenter.shop_get(shop_get, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                GetDianZhu getdianzhu= (GetDianZhu) responseObj;
                if (getdianzhu.getCode()==BianLiDianStatus.STATUS_CODE_SUCCESS){
                    GetDianZhu.ResultBean result= getdianzhu.getResult();
                    et_dianzhuxinxi_name.setText(result.getName());
                    et_dianzhuxinxi_tel.setText(result.getPhone());
                    et_dianzhuxinxi_age.setText(result.getAge()+"");
                    ImageLoaderManager.getInstance(DianZhuXinXiActivity.this)
                            .displayImage(iv_xiugaixinxi_xiugai_touxiang,result.getLogo());
                    ImageLoaderManager.getInstance(DianZhuXinXiActivity.this)
                            .displayImage(iv_yingyezhizhao,result.getBusinessLicense());
                    ImageLoaderManager.getInstance(DianZhuXinXiActivity.this)
                            .displayImage(iv_weishengxuke,result.getHealthLicense());
                    if (result.getSex()==1){
                        sex=1;
                        iv_dianzhuxinxi_men.setImageResource(R.drawable.b_c_a_btn_yes_pre);
                        iv_dianzhuxinxi_women.setImageResource(R.drawable.b_c_b_a_btn_unselected_pre);
                    }else if (result.getSex()==2){
                        sex=2;
                        iv_dianzhuxinxi_men.setImageResource(R.drawable.b_c_b_a_btn_unselected_pre);
                        iv_dianzhuxinxi_women.setImageResource(R.drawable.b_c_a_btn_yes_pre);
                    }

                }
            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        });

    }

    private void initUI() {
        iv_xiugaixinxi_xiugai_touxiang= (CircleImageView) findViewById(R.id.iv_xiugaixinxi_xiugai_touxiang);
        rl_dianzhuxinxi_fanhui= (RelativeLayout) findViewById(R.id.rl_dianzhuxinxi_fanhui);
        tv__dianzhuxinxi_save= (TextView) findViewById(R.id.tv__dianzhuxinxi_save);
        et_dianzhuxinxi_name= (EditText) findViewById(R.id.et_dianzhuxinxi_name);
        iv_dianzhuxinxi_men= (ImageView) findViewById(R.id.iv_dianzhuxinxi_men);
        iv_dianzhuxinxi_women= (ImageView) findViewById(R.id.iv_dianzhuxinxi_women);
        et_dianzhuxinxi_tel= (EditText) findViewById(R.id.et_dianzhuxinxi_tel);
        et_dianzhuxinxi_age= (EditText) findViewById(R.id.et_dianzhuxinxi_age);
        iv_yingyezhizhao= (ImageView) findViewById(R.id.iv_yingyezhizhao);
        iv_weishengxuke= (ImageView) findViewById(R.id.iv_weishengxuke);
        rl_dianzhuxinxi_fanhui.setOnClickListener(this);
        tv__dianzhuxinxi_save.setOnClickListener(this);
        iv_xiugaixinxi_xiugai_touxiang.setOnClickListener(this);
        iv_dianzhuxinxi_men.setOnClickListener(this);
        iv_dianzhuxinxi_women.setOnClickListener(this);
        iv_yingyezhizhao.setOnClickListener(this);
        iv_weishengxuke.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //返回的按键
            case R.id.rl_dianzhuxinxi_fanhui:
                finish();
                break;
            //保存的按键
            case R.id.tv__dianzhuxinxi_save:
                name= String.valueOf(et_dianzhuxinxi_name.getText());
                phone= String.valueOf(et_dianzhuxinxi_tel.getText());
                age= String.valueOf(et_dianzhuxinxi_age.getText());

                if (touxiang==null){
                    shangchuanImage();
                }else {

                    if (touxiang != null && !touxiang.equals("")) {
                        String[] s1 = touxiang.split("/");//以","为分隔符，截取上面的字符串。结果为三段
                        for (int i = 0; i < s1.length; i++) {
                            if (s1.length - 1 == i) {
                                touxiang = s1[i];
                                Log.i("YYYY", s1[i]);
                            }
                        }
                    }
                    if (yingye != null && !yingye.equals("")) {
                        String[] s2 = yingye.split("/");
                        for (int i = 0; i < s2.length; i++) {
                            if (s2.length - 1 == i) {
                                yingye = s2[i];
                                Log.i("YYYY", s2[i]);
                            }
                        }
                    }
                    if (weisheng != null && !weisheng.equals("")) {
                        String[] s3 = weisheng.split("/");
                        for (int i = 0; i < s3.length; i++) {
                            if (s3.length - 1 == i) {
                                weisheng = s3[i];
                                Log.i("YYYY", s3[i]);
                            }
                        }
                    }
                    String update = RequestURL.URL_SHOP_UPDATE + "sex=" + sex + "&age=" + age + "&logo=" + touxiang
                            + "&name=" + name + "&phone=" + phone + "&businessLicense=" + yingye +
                            "&healthLicense=" + weisheng;
                    RequestCenter.shop_update(update, new DisposeDataListener() {
                        @Override
                        public void onSuccess(Object responseObj) {
                            CreateDianZhu createDianZhu = (CreateDianZhu) responseObj;
                            if (createDianZhu.getCode() == BianLiDianStatus.STATUS_CODE_SUCCESS) {
                                MyUntil.show(DianZhuXinXiActivity.this, "保存成功");
                            }
                        }

                        @Override
                        public void onFailure(Object reasonObj) {

                        }
                    });
                }
                break;
            //头像的按键
            case R.id.iv_xiugaixinxi_xiugai_touxiang:
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_LOAD_IMAGE);
                break;
            //选择男的按键
            case R.id.iv_dianzhuxinxi_men:
                sex=1;
                iv_dianzhuxinxi_men.setImageResource(R.drawable.b_c_a_btn_yes_pre);
                iv_dianzhuxinxi_women.setImageResource(R.drawable.b_c_b_a_btn_unselected_pre);
                break;
            //选择女的按键
            case R.id.iv_dianzhuxinxi_women:
                sex=2;
                iv_dianzhuxinxi_men.setImageResource(R.drawable.b_c_b_a_btn_unselected_pre);
                iv_dianzhuxinxi_women.setImageResource(R.drawable.b_c_a_btn_yes_pre);
                break;
            //选择营业执照的按键
            case R.id.iv_yingyezhizhao:
                Intent intent1 = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent1, RESULT_LOAD_IMAGE_YINGYE);
                break;
            //选择卫生许可证的按键
            case R.id.iv_weishengxuke:
                Intent intent2 = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent2, RESULT_LOAD_IMAGE_WEISHENG);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, intent);
//        if (requestCode == RESULT_CANCELED && resultCode == RESULT_OK) {
//            Bundle extras = intent.getExtras();//从Intent中获取附加值
//            Bitmap bitmap=(Bitmap) extras.get("data");
//            mFile=compressImagefile(bitmap);
////            saveBitmapToSharedPreferences((Bitmap) extras.get("data"));
////            从附加值中获取返回的图像
////            iv_xiugaixinxi_xiugai_touxiang.setImageBitmap(bitmap);
//            bitmap.recycle();
//        }
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK) {
            Uri uri1 = intent.getData();
            getImage(uri1,RESULT_LOAD_IMAGE);
//            Bitmap bitmap = getBitmapFromUri(uri1);
//            String[] filePathColumn = {MediaStore.Images.Media.DATA};
//            Cursor cursor = getContentResolver().query(uri1,
//                    filePathColumn, null, null, null);
//            cursor.moveToFirst();
//            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//            picturePath = cursor.getString(columnIndex);
//            mFile = new File(picturePath);
//            cursor.close();
//            bitmap.recycle();
            ImageLoaderManager.getInstance(DianZhuXinXiActivity.this)
                    .displayImage(iv_xiugaixinxi_xiugai_touxiang, String.valueOf(uri1));
        }
        if (requestCode == RESULT_LOAD_IMAGE_YINGYE && resultCode == RESULT_OK) {
            Uri uri1 = intent.getData();
            getImage(uri1,RESULT_LOAD_IMAGE_YINGYE);
//            Bitmap bitmap = getBitmapFromUri(uri1);
//            String[] filePathColumn = {MediaStore.Images.Media.DATA};
//            Cursor cursor = getContentResolver().query(uri1,
//                    filePathColumn, null, null, null);
//            cursor.moveToFirst();
//            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//            picturePath = cursor.getString(columnIndex);
//            mFile = new File(picturePath);
//            cursor.close();
//            bitmap.recycle();
            ImageLoaderManager.getInstance(DianZhuXinXiActivity.this)
                    .displayImage(iv_yingyezhizhao, String.valueOf(uri1));
        }
        if (requestCode == RESULT_LOAD_IMAGE_WEISHENG && resultCode == RESULT_OK) {
            Uri uri1 = intent.getData();
            getImage(uri1,RESULT_LOAD_IMAGE_WEISHENG);
//            Bitmap bitmap = getBitmapFromUri(uri1);
//            String[] filePathColumn = {MediaStore.Images.Media.DATA};
//            Cursor cursor = getContentResolver().query(uri1,
//                    filePathColumn, null, null, null);
//            cursor.moveToFirst();
//            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//            picturePath = cursor.getString(columnIndex);
//            mFile = new File(picturePath);
//            cursor.close();
//            bitmap.recycle();
            ImageLoaderManager.getInstance(DianZhuXinXiActivity.this)
                    .displayImage(iv_weishengxuke, String.valueOf(uri1));
        }
    }
    private Bitmap getBitmapFromUri(Uri uri) {
        try
        {
            // 读取uri所在的图片
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            return bitmap;
        }
        catch (Exception e)
        {
            Log.e("[Android]", e.getMessage());
            Log.e("[Android]", "目录为：" + uri);
            e.printStackTrace();
            return null;
        }
    }

    private void getImage(Uri uri1,int type){
        Bitmap bitmap = getBitmapFromUri(uri1);
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri1,
                filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        picturePath = cursor.getString(columnIndex);
        switch (type){
            case RESULT_LOAD_IMAGE:
                type1=RESULT_LOAD_IMAGE;
                mFile = new File(picturePath);
                map.put(RESULT_LOAD_IMAGE_STRING,mFile);
//                shangchuanImage(mFile,RESULT_LOAD_IMAGE);
                break;
            case RESULT_LOAD_IMAGE_YINGYE:
                type1=RESULT_LOAD_IMAGE_YINGYE;
                mFile_yingye = new File(picturePath);
                map.put(RESULT_LOAD_IMAGE_STRING,mFile_yingye);
//                shangchuanImage(mFile_yingye,RESULT_LOAD_IMAGE_YINGYE);
                break;
            case RESULT_LOAD_IMAGE_WEISHENG:
                type1=RESULT_LOAD_IMAGE_WEISHENG;
                mFile_weisheng = new File(picturePath);
                map.put(RESULT_LOAD_IMAGE_STRING,mFile_weisheng);
//                shangchuanImage(mFile_weisheng,RESULT_LOAD_IMAGE_WEISHENG);
                break;
        }
        cursor.close();
        bitmap.recycle();
    }
    private void shangchuanImage(){
        String url=RequestURL.URL_IMAGE;
        map.clear();

        map.put(RESULT_LOAD_IMAGE_STRING,mFile);

        if (map.get(RESULT_LOAD_IMAGE_STRING)==null){
            MyUntil.show(DianZhuXinXiActivity.this,"请添加图片");
        }
        RequestCenter.uploadPictures(url, map, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                ImageUrl imageUrl= (ImageUrl) responseObj;
                if (imageUrl.getCode().equals("1000")){
                   touxiang=imageUrl.getResult().get(0);
                    String update = RequestURL.URL_SHOP_UPDATE + "sex=" + sex + "&age=" + age + "&logo=" + touxiang
                            + "&name=" + name + "&phone=" + phone + "&businessLicense=" + yingye +
                            "&healthLicense=" + weisheng;
                    RequestCenter.shop_update(update, new DisposeDataListener() {
                        @Override
                        public void onSuccess(Object responseObj) {
                            CreateDianZhu createDianZhu = (CreateDianZhu) responseObj;
                            if (createDianZhu.getCode() == BianLiDianStatus.STATUS_CODE_SUCCESS) {
                                MyUntil.show(DianZhuXinXiActivity.this, "保存成功");
                            }
                        }

                        @Override
                        public void onFailure(Object reasonObj) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        });



    }


}
