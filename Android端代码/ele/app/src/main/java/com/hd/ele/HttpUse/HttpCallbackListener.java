package com.hd.ele.HttpUse;

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
    String getRes();
}
