package com.hd.ele.HttpUse;

import android.util.Log;

public class Connect {
    private static String TAG = "DEBUGN";
    public String Con(String sendParam) {
        String getParam = null;
        HttpCallbackListener hcbl = new HttpCallbackListener() {
            public String Temp = null;
            @Override
            public void onFinish(String response) {
                Log.i(TAG, "onFinish: YEH"+response);
                Temp = response;
                Log.i(TAG, "onFinish: YES"+Temp);
            }

            @Override
            public void onError(Exception e) {
                Log.i(TAG, "onError: " + "CONNECTION ERROR");
            }

            @Override
            public  String getRes() {
                return Temp;
            }
        };
        HttpUtil.sendHttpRequest(sendParam, hcbl);
        int cnt = 0;
        while((getParam = hcbl.getRes())==null&&cnt<2e9)++cnt;
        return getParam;
    }
}
