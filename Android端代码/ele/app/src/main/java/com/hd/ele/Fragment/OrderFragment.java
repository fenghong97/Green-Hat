package com.hd.ele.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hd.ele.Adapter.OrderAdapter;
import com.hd.ele.Bean.Food;
import com.hd.ele.Bean.Order;
import com.hd.ele.HttpUse.Connect;
import com.hd.ele.HttpUse.Content;
import com.hd.ele.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.chad.library.adapter.base.listener.SimpleClickListener.TAG;

/**
 * Created by Administrator on 2018/12/21 0021.
 */

public class OrderFragment extends Fragment {

    private List<Order> orderList=new ArrayList<>();
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private OrderAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);


        //订单列表
        initOrdersList();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view_order);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new OrderAdapter(orderList);
        recyclerView.setAdapter(adapter);
        return view;
    }


    private void initOrdersList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    OkHttpClient client = new OkHttpClient();
//                    Request request = new Request.Builder()
//                            .url(jsonURL)
//                            .build();
//                    //现在要将从json中读取数据改成从数据库中读取数据，数据格式是
//                    Response response = client.newCall(request).execute();
//                    String responseData = response.body().string();
//                    Gson gson=new Gson();
//                    List<Order> morderList=gson.fromJson(responseData,new TypeToken<List<Order>>()
//                    {}.getType());
//
//                    Log.d("1",responseData);
                    Connect con = new Connect();
                    String inf = con.Con("type="+Content.ORDER_TYPE);

                    BufferedReader reader = new BufferedReader(new StringReader(inf));
                    String line = null;

                    while(null != (line = reader.readLine())) {
                        Log.i(TAG, "getDatas line: "+line);
                        Map<String,String> f = new HashMap<String,String>();
                        for(int i = 1;i < line.length();++i) {
                            String key = new String("");
                            String value = new String("");
                            while(i<line.length() && line.charAt(i)!='=') key += line.charAt(i++); ++i;
                            while(i<line.length() && line.charAt(i)!='&') value += line.charAt(i++);
                            f.put(key,value);
                            Log.i(TAG+"FFUFFK", "getDatas: " + key + " " + value);
                        }
                        Order order = new Order(f.get("Name"),f.get("Time"),f.get("Price"),f.get("Number"));
                        String tv_name = order.getTv_name();
                        String tv_price = order.getTv_price();
                        String tv_time = order.getTv_time();
                        String tv_number = order.getTv_number();
                        Order order1 = new Order(tv_name, tv_time, tv_price, tv_number);
                        orderList.add(order1);
                    }
                    reader.close();
//public Order(String tv_name, String tv_time, String tv_price, String tv_number)

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        long s = (new Date()).getTime();
        while((new Date().getTime()) <= (s+300)) {
        }
//        Shop a = new Shop();
//        shopList.add(a);
//        Shop b = new Shop();
//        shopList.add(b);
    }
}