package com.hd.ele;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        //目前不需要此活动，此活动是调用支付页面的，而现在不需要此页面，
        // 因为点击提交订单后不会跳转到此页面，而是直接跳转到订单页面（fragment_order.xml）
        Intent intent=new Intent(PayActivity.this, MainActivity.class);
        intent.putExtra("pay", true);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startActivity(intent);
    }
}
