package com.hd.ele.HttpUse;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class HttpUtil {/*
    String res = null;
    public static void setRes(String nres) { res = new String(nres); }
    public String getRes() { return res; }*/
    public static void sendHttpRequest(final String sendParam, final HttpCallbackListener listener) {
        final String TAG = new String("DEBUGHTTP");
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection con = null;
                try {
                    URL url = new URL(UriAPI.HTTPCustomer);
                    con = (HttpURLConnection) url.openConnection();
                    con.setConnectTimeout(6*1000);
                    HttpURLConnection.setFollowRedirects(true);
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    con.setRequestMethod("POST");	//设置为Post方法
                    con.setRequestProperty("Content-Type", "multipart/form-data");	//请求头 请求体
                    con.connect();

                    //将数据发送给服务器
                    OutputStream output = con.getOutputStream();
                    output.write(sendParam.getBytes());
                    output.flush();
                    output.close();

                    //接受服务器发送过来的信息
                    int HTTP_state = con.getResponseCode();
                    if (HttpURLConnection.HTTP_OK == HTTP_state) {
                        Log.i(TAG, "run: " + "HTTP_OK");
                        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                        StringBuffer sb = new StringBuffer();
                        String inf = new String("");
                        String res = new String("");
                        while ((inf = in.readLine()) != null) {
                            res = res + inf + "\n";
                            sb.append(inf);
                            sb.append("\n");
                        }
                        //setRes(inf);
                        Log.i(TAG, "run: now "+sb.toString());
                        in.close();
                        Log.i(TAG, "run: " + sb.toString());
                        if(listener != null) {
                            Log.i(TAG, "run: OK?"+sb.toString());
                            listener.onFinish(sb.toString());
                            Log.i(TAG, "run: OK:"+sb.toString());
                        }
                    }
                    else {
                        Log.i(TAG, "run: "+HTTP_state);
                    }

                } catch(Exception e) {
                    if(listener != null) {
                        listener.onError(e);
                    }
                } finally {
                    if(con != null) {
                        con.disconnect();
                    }
                }
            }
        }).start();
        long s = (new Date()).getTime();
        while((new Date().getTime()) <= (s+200)) {
        }
    }
    public class UriAPI {
        public static final String HTTPCustomer = "http://192.168.155.1:8080/elemeServlet/HttpSolve";
    }
}
