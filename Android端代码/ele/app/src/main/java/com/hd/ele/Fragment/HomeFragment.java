package com.hd.ele.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hd.ele.Adapter.GridViewAdapter;
import com.hd.ele.Adapter.ShopAdapter;
import com.hd.ele.Adapter.ViewPagerAdapter;
import com.hd.ele.Bean.Kind;
import com.hd.ele.Bean.Shop;
import com.hd.ele.HttpUse.IP;
import com.hd.ele.LocationAndPoiSearchActivity;
import com.hd.ele.Widget.EleSearchActivity;
import com.hd.ele.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Administrator on 2018/12/19 0019.
 */

public class HomeFragment extends Fragment {

    private TextView mSearchBGTxt;
    private TextView mLocation;

    private View view;
    private View fragmentview;
    private ViewPager viewPager;
    private int totalPage; //总的页数
    private int mPageSize = 10; //每页显示的最大的数量
    private List<Kind> listDatas;//总的数据源
    private List<View> viewPagerList;//GridView作为一个View对象添加到ViewPager集合中

    private ImageView mImageView_user;

    private  static List<Shop> shopList=new ArrayList<>();
    private static boolean isInit = false;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ShopAdapter adapter;

    private String[] proName = {"美食", "商超便利", "水果", "甜品饮品", "星选好店", "送药上门",
            "大牌惠吃", "蜂鸟专送", "签到领红包", "地方美食", "速食简餐", "汉堡披萨", "米粉面馆",
            "包子粥店", "全城送", "炸鸡炸串", "厨房生鲜", "鲜花绿植", "鸭脖卤味", "烧烤海鲜"};

    //服务端数据地址
    private static String jsonURL = IP.ServerIP+"ele/shopdata.json";

    final String TAG = new String("DEBUGN");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        fragmentview = inflater.inflate(R.layout.item_kind, container, false);

        //定位
        mLocation=(TextView)view.findViewById(R.id.location);
        mLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onCreateView: 4");
                Intent intent=new Intent(getActivity(),LocationAndPoiSearchActivity.class);
                startActivity(intent);
            }
        });
        Log.i(TAG, "onCreateView: 1");
        //搜索框
        mSearchBGTxt = (TextView) view.findViewById(R.id.tv_search_bg);
        mSearchBGTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onCreateView: 5");
                Intent intent = new Intent(getActivity(), EleSearchActivity.class);
                int location[] = new int[2];
                mSearchBGTxt.getLocationOnScreen(location);
                intent.putExtra("x", location[0]);
                intent.putExtra("y", location[1]);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
            }
        });

        Log.i(TAG, "onCreateView: 2");
        mImageView_user=(ImageView)view.findViewById(R.id.new_user);
        mImageView_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"点击失败",Toast.LENGTH_SHORT).show();
            }
        });

        //横向分页列表
        initKindList();

        //商家列表
//        initShopsList();
//        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view_shop);
//        linearLayoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(linearLayoutManager);
//        adapter = new ShopAdapter(shopList);
//        recyclerView.setAdapter(adapter);
//
//        Log.i(TAG, "onCreateView: 3");
//
////        Log.i("***","size="+shopList.size());
//
//        return view;

        if (!isInit) {
            initShopsList();
//            recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view_shop);
//            linearLayoutManager = new LinearLayoutManager(getActivity());
//            recyclerView.setLayoutManager(linearLayoutManager);
//            adapter = new ShopAdapter(shopList);
//            recyclerView.setAdapter(adapter);
            isInit = true;
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view_shop);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ShopAdapter(shopList);
        recyclerView.setAdapter(adapter);




        Log.i(TAG, "onCreateView: 3");

//        Log.i("***","size="+shopList.size());

        return view;
    }

    private void initKindList() {
        Log.i(TAG, "onCreateView: kind");
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        listDatas = new ArrayList<Kind>();
        listDatas.add(new Kind(proName[0], R.drawable.kind1_1));
        listDatas.add(new Kind(proName[1], R.drawable.kind1_2));
        listDatas.add(new Kind(proName[2], R.drawable.kind1_3));
        listDatas.add(new Kind(proName[3], R.drawable.kind1_4));
        listDatas.add(new Kind(proName[4], R.drawable.kind1_5));
        listDatas.add(new Kind(proName[5], R.drawable.kind2_1));
        listDatas.add(new Kind(proName[6], R.drawable.kind2_2));
        listDatas.add(new Kind(proName[7], R.drawable.kind2_3));
        listDatas.add(new Kind(proName[8], R.drawable.kind2_4));
        listDatas.add(new Kind(proName[9], R.drawable.kind2_5));
        listDatas.add(new Kind(proName[10], R.drawable.kind3_1));
        listDatas.add(new Kind(proName[11], R.drawable.kind3_2));
        listDatas.add(new Kind(proName[12], R.drawable.kind3_3));
        listDatas.add(new Kind(proName[13], R.drawable.kind3_4));
        listDatas.add(new Kind(proName[14], R.drawable.kind3_5));
        listDatas.add(new Kind(proName[15], R.drawable.kind4_1));
        listDatas.add(new Kind(proName[16], R.drawable.kind4_2));
        listDatas.add(new Kind(proName[17], R.drawable.kind4_3));
        listDatas.add(new Kind(proName[18], R.drawable.kind4_4));
        listDatas.add(new Kind(proName[19], R.drawable.kind4_5));
        totalPage = (int) Math.ceil(listDatas.size() * 1.0 / mPageSize);
        viewPagerList = new ArrayList<View>();
        for (int i = 0; i < totalPage; i++) {
            //每个页面都是inflate出一个新实例
            final GridView gridView = (GridView) View.inflate(getActivity(), R.layout.gridview_kind, null);
            gridView.setAdapter(new GridViewAdapter(getActivity(), listDatas, i, mPageSize));
            //添加item点击监听
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {
                    // TODO Auto-generated method stub
                    Object obj = gridView.getItemAtPosition(position);
                    if (obj != null && obj instanceof Kind) {
                        System.out.println(obj);
                        Toast.makeText(getActivity(), ((Kind) obj).getName() + "！", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            //每一个GridView作为一个View对象添加到ViewPager集合中
            viewPagerList.add(gridView);
        }
        //设置ViewPager适配器
        viewPager.setAdapter(new ViewPagerAdapter(viewPagerList));
        Log.i(TAG, "onCreateView: kindo");
    }

    private void initShopsList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.i(TAG, "onCreateView: thread");
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(jsonURL)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Gson gson=new Gson();
                    List<Shop> mshopList=gson.fromJson(responseData,new TypeToken<List<Shop>>()
                    {}.getType());

                    Log.d("1",responseData);
                    for (Shop shop:mshopList) {
                        String name = shop.getName();
                        Log.d("1",shop.getImageURL());
                        String imageURL = shop.getImageURL();
                        String score = shop.getScore();
                        String sale = shop.getSale();
                        String sprice = shop.getSprice();
                        String dprice = shop.getDprice();
                        String distance = shop.getDistance();
                        String time = shop.getTime();
                        Log.i("hewllo1111", "run: "+"!!!!!!!!");
                        Shop shop1 = new Shop(name, IP.ServerIP + "ele/" + imageURL, score, sale, sprice, dprice, distance, time);
//                        Shop shop1 = new Shop(name, "R.drawable." + imageURL, score, sale, sprice, dprice, distance, time);
                        shopList.add(shop1);
                        Log.d("/////////////",shopList.toString());
                    }
                    Log.i(TAG, "onCreateView: thread");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
//        Shop a = new Shop();
//        shopList.add(a);
//        Shop b = new Shop();
//        shopList.add(b);

    }
}