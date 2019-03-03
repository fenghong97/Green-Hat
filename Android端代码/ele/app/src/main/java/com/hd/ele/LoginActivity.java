package com.hd.ele;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hd.ele.Fragment.MeFragment;
//import com.hd.ele.Fragment.Me_unload_Fragment;
//import com.hd.ele.Fragment.Me_unload_Fragment;

import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TimerTask tt;
    private Timer tm;
    private ImageView back;
    private EditText et_phonenum;
    private Button btn_check;
    private EditText et_checkecode;
    private Button btn_sure;
    private int TIME = 60;//倒计时60s这里应该多设置些因为mob后台需要60s,我们前端会有差异的建议设置90，100或者120
    public String country="86";//这是中国区号，如果需要其他国家列表，可以使用getSupportedCountries();获得国家区号
    private String phone;
    private static final int CODE_REPEAT = 1; //重新发送


    Handler hd = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == CODE_REPEAT) {
                btn_check.setEnabled(true);
                btn_sure.setEnabled(true);
                TIME = 60;//时间重置
                btn_check.setText("重新发送");
            }else {
                btn_check.setText(TIME + "S重新发送");
            }
        }
    };
    //回调
    EventHandler eh=new EventHandler(){
        @Override
        public void afterEvent(int event, int result, Object data) {
            if (result == SMSSDK.RESULT_COMPLETE) {
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    toast("登录成功");
                    SMSSDK.unregisterEventHandler(eh);
                    TextView login=(TextView) findViewById(R.id.login);
                    String message=et_phonenum.getText().toString();
                    Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("id",4);
                    intent.putExtra("data",message);
                    startActivity(intent);

                }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){       //获取验证码成功
                    toast("验证码已发送");
                }
            }else{
                //((Throwable)data).printStackTrace();
                //String str = data.toString();
                toast("发送失败或者验证码错误");
            }
        }
    };
    //吐司的一个小方法
    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //配置app你的 SMSSDK.initSDK(this, "您的appkey", "您的appsecret");
        SMSSDK.initSDK(this, "231bf05445a50", "af4b03e87e0a7c3808da92913e97a46c");
        SMSSDK.registerEventHandler(eh); //注册短信回调（记得销毁，避免泄露内存）
        initView();
        back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("backme", true);
                startActivity(intent);
            }
        });

    }
    private void initView() {
        et_phonenum = (EditText) findViewById(R.id.et_phonenum);
        btn_check = (Button) findViewById(R.id.btn_check);
        et_checkecode = (EditText) findViewById(R.id.et_checkecode);
        btn_sure = (Button) findViewById(R.id.btn_sure);
        btn_check.setOnClickListener(this);
        btn_sure.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_check:
                phone = et_phonenum.getText().toString().trim().replaceAll("/s","");
                if (!TextUtils.isEmpty(phone)) {
                    //定义需要匹配的正则表达式的规则
                    String REGEX_MOBILE_SIMPLE =  "[1][358]\\d{9}";
                    //把正则表达式的规则编译成模板
                    Pattern pattern = Pattern.compile(REGEX_MOBILE_SIMPLE);
                    //把需要匹配的字符给模板匹配，获得匹配器
                    Matcher matcher = pattern.matcher(phone);
                    // 通过匹配器查找是否有该字符，不可重复调用重复调用matcher.find()
                    if (matcher.find()) {//匹配手机号是否存在
                        SMSSDK.getVerificationCode(country, phone);
                        //做倒计时操作
                        btn_check.setEnabled(false);
                        btn_sure.setEnabled(true);
                        tm = new Timer();
                        tt = new TimerTask() {
                            @Override
                            public void run() {
                                hd.sendEmptyMessage(TIME--);
                            }
                        };
                        tm.schedule(tt,0,1000);
                    } else {
                        toast("手机号格式错误");
                    }
                } else {
                    toast("请先输入手机号");
                }
                break;
            case R.id.btn_sure:
                //获得用户输入的验证码
                String code = et_checkecode.getText().toString().replaceAll("/s","");
                if (!TextUtils.isEmpty(code)) {//判断验证码是否为空
                    //验证
                    SMSSDK.submitVerificationCode( country,  phone,  code);
                }else{//如果用户输入的内容为空，提醒用户
                    toast("请输入验证码后再提交");
                }
                break;
        }
    }
}