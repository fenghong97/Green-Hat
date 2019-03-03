package com.hd.ele;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hd.ele.Fragment.DiscoverFragment;
import com.hd.ele.Fragment.HomeFragment;
import com.hd.ele.Fragment.MeFragment;
import com.hd.ele.Fragment.OrderFragment;
import com.hd.ele.HttpUse.Content;
import com.hd.ele.HttpUse.HttpCallbackListener;
import com.hd.ele.HttpUse.HttpUtil;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    // 定义4个Fragment对象
    private HomeFragment fg1;
    private DiscoverFragment fg2;
    private OrderFragment fg3;
    private MeFragment fg4;
    // 帧布局对象，用来存放Fragment对象
    private FrameLayout frameLayout;
    // 定义每个选项中的相关控件
    private RelativeLayout homeLayout;
    private RelativeLayout discoverLayout;
    private RelativeLayout orderLayout;
    private RelativeLayout meLayout;
    private ImageView homeImage;
    private ImageView discoverImage;
    private ImageView orderImage;
    private ImageView meImage;
//    private TextView homeText;
//    private TextView discoverText;
//    private TextView orderText;
//    private TextView meText;
    // 定义FragmentManager对象管理器
    private FragmentManager fragmentManager;


    final String TAG = new String("DEBUGN1");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_bottom_bar);

        //发送http请求更新shopdata.json文件
        HttpUtil.sendHttpRequest("type=" + Content.GETSHOP_TYPE, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                //NULL
                Log.i(TAG, "onFinish: UPDATA!");
            }

            @Override
            public void onError(Exception e) {
                Log.i(TAG, "onError: SHOPDATA.JSON");
            }

            @Override
            public String getRes() {
                return null;
            }
        });


        Log.i(TAG, "onCreateView: 1");
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        fragmentManager = getSupportFragmentManager();
        initView(); // 初始化界面控件
        setChioceItem(0); // 初始化页面加载时显示第一个选项卡
        Intent intent2 = getIntent();
        if (intent2 != null && intent2.getBooleanExtra("pay", false)) {
            setChioceItem(2);
        }
        else if(intent2 != null && intent2.getBooleanExtra("backme", false)) {
            setChioceItem(3);
        }

        Log.i(TAG, "onCreateView: 2");
        int id = getIntent().getIntExtra("id", 0);
        Log.i(TAG, "onCreateView: 3");
        if(id==4){
            Log.i(TAG, "onCreateView: 4");
            setChioceItem(3);
            Intent intent=getIntent();
            String data=intent.getStringExtra("data");
            Log.d("data",data);

            MeFragment fgme=new MeFragment();
            Bundle bundle=new Bundle();
            bundle.putString("phone",data);
            fgme.setArguments(bundle);
            FragmentTransaction fragmentTransaction1=fragmentManager.beginTransaction();
            fragmentTransaction1.replace(R.id.content,fgme);
            fragmentTransaction1.commit();
            Log.i(TAG, "onCreateView: 5");
        }

    }

    //初始化页面
    private void initView() {

        Log.i(TAG, "onCreateView: view");
        // 初始化底部导航栏的控件
        homeImage = (ImageView) findViewById(R.id.home_image);
        discoverImage = (ImageView) findViewById(R.id.discover_image);
        orderImage = (ImageView) findViewById(R.id.order_image);
        meImage = (ImageView) findViewById(R.id.me_image);
//        homeText = (TextView) findViewById(R.id.home_text);
//        discoverText = (TextView) findViewById(R.id.discover_text);
//        orderText = (TextView) findViewById(R.id.order_text);
//        meText = (TextView) findViewById(R.id.me_text);
        homeLayout = (RelativeLayout) findViewById(R.id.home_layout);
        discoverLayout = (RelativeLayout) findViewById(R.id.discover_layout);
        orderLayout = (RelativeLayout) findViewById(R.id.order_layout);
        meLayout = (RelativeLayout) findViewById(R.id.me_layout);
        homeLayout.setOnClickListener(MainActivity.this);
        discoverLayout.setOnClickListener(MainActivity.this);
        orderLayout.setOnClickListener(MainActivity.this);
        meLayout.setOnClickListener(MainActivity.this);
        Log.i(TAG, "onCreateView: viewo");
    }
    @Override
    public void onClick(View v) {
        Log.i(TAG, "onCreateView: on");
        switch (v.getId()) {
            case R.id.home_layout:
                setChioceItem(0);
                break;
            case R.id.discover_layout:
                setChioceItem(1);
                break;
            case R.id.order_layout:
                setChioceItem(2);
                break;
            case R.id.me_layout:
                setChioceItem(3);
                break;
            default:
                break;
        }
        Log.i(TAG, "onCreateView: ono");
    }

    // 设置点击选项卡的事件处理
    private void setChioceItem(int index) {
        Log.i(TAG, "onCreateView: set");
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        clearChioce(); // 清空, 重置选项, 隐藏所有Fragment
        hideFragments(fragmentTransaction);
        switch (index) {
            case 0:
                homeImage.setImageResource(R.drawable.home_fill);
//                homeText.setTextColor(0Xff0089ff);
                // 如果fg1为空，则创建一个并添加到界面上
//                if (fg1 == null) {
//                    fg1 = new HomeFragment();
//                    fragmentTransaction.add(R.id.content, fg1);
//                } else {
//                // 如果不为空，则直接将它显示出来
//                    fragmentTransaction.show(fg1);
//                }
//                if (fg1 != null)
//                fragmentTransaction.remove(fg1);
                fg1 = new HomeFragment();
                fragmentTransaction.replace(R.id.content, fg1);

                break;
            case 1:
                discoverImage.setImageResource(R.drawable.discover_fill);
//                discoverText.setTextColor(0Xff0089ff);
//                if (fg2 == null) {
//                    fg2 = new DiscoverFragment();
//                    fragmentTransaction.add(R.id.content, fg2);
//                } else {
//                    fragmentTransaction.show(fg2);
//                }
//                if (fg2 != null)
//                    fragmentTransaction.remove(fg2);
                fg2 = new DiscoverFragment();
                fragmentTransaction.replace(R.id.content, fg2);
                break;
            case 2:
                orderImage.setImageResource(R.drawable.order_fill);
//                orderText.setTextColor(0Xff0089ff);
//                if (fg3 == null) {
//                    fg3 = new OrderFragment();
//                    fragmentTransaction.add(R.id.content, fg3);
//                } else {
//                    fragmentTransaction.show(fg3);
//                }
//                if (fg3 != null)
//                    fragmentTransaction.remove(fg3);
                fg3 = new OrderFragment();
                fragmentTransaction.replace(R.id.content, fg3);

                break;
            case 3:
                meImage.setImageResource(R.drawable.me_fill);
//                meText.setTextColor(0Xff0089ff);
//                if (fg4 == null) {
//                    fg4 = new MeFragment();
//                    fragmentTransaction.add(R.id.content, fg4);
//                } else {
//                    fragmentTransaction.show(fg4);
//                }
//                if (fg4 != null)
//                    fragmentTransaction.remove(fg4);
                fg4 = new MeFragment();
                fragmentTransaction.replace(R.id.content, fg4);

                break;
        }
        fragmentTransaction.commit(); // 提交
        Log.i(TAG, "onCreateView: seto");
    }

    //当选中其中一个选项卡时，其他选项卡重置为默认
    private void clearChioce() {
        Log.i(TAG, "onCreateView: cl");
        homeImage.setImageResource(R.drawable.home);
//        homeText.setTextColor(0Xff838383);
        discoverImage.setImageResource(R.drawable.discover);
//        discoverText.setTextColor(0Xff838383);
        orderImage.setImageResource(R.drawable.order);
//        orderText.setTextColor(0Xff838383);
        meImage.setImageResource(R.drawable.me);
//        meText.setTextColor(0Xff838383);
        Log.i(TAG, "onCreateView: clo");
    }

    // 隐藏Fragment
    private void hideFragments(FragmentTransaction fragmentTransaction) {

        Log.i(TAG, "onCreateView: hi");
        if (fg1 != null) {
            fragmentTransaction.hide(fg1);
        }
        if (fg2 != null) {
            fragmentTransaction.hide(fg2);
        }
        if (fg3 != null) {
            fragmentTransaction.hide(fg3);
        }
        if (fg4 != null) {
            fragmentTransaction.hide(fg4);
        }
        Log.i(TAG, "onCreateView: hio");
    }
}