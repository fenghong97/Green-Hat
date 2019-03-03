package com.hd.ele.Utils;

import android.content.Context;
import android.util.Log;

import com.hd.ele.Bean.Comment;
import com.hd.ele.Bean.Food;
import com.hd.ele.Bean.Type;
import com.hd.ele.HttpUse.Connect;
import com.hd.ele.HttpUse.Content;
import com.hd.ele.HttpUse.HttpCallbackListener;
import com.hd.ele.HttpUse.HttpUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Administrator on 2018/12/19 0019.
 */

public class BaseUtils {
    private int ifFind = 0;
    private static String TAG = new String("DEBUGN");
    public static List<Type> getTypes() throws IOException {
        ArrayList<Type> tList = new ArrayList<>();

        Connect con = new Connect();
        String inf = con.Con("type="+Content.COMMODITYT_TYPE);
        Log.i(TAG, "getTypes: TYPE OK!!"+inf);

        BufferedReader reader = new BufferedReader(new StringReader(inf));
        String line = null;
        while((line = reader.readLine()) != null) {
            Type type = new Type();
            type.setName(line);
            tList.add(type);
        }
        reader.close();
        /*
        for (int i = 0; i < 5; i++) {
            Type type = new Type();
            type.setName("类别" + i);
            tList.add(type);
        }*/
        return tList;
    }

    public static List<Food> getDatas(Context context) throws IOException {
        ArrayList<Food> fList = new ArrayList<>();
        Connect con = new Connect();
        String inf = con.Con("type="+Content.COMMODITYI_TYPE);
        Log.i(TAG, "getTypes: TYPE OK!"+inf);

        BufferedReader reader = new BufferedReader(new StringReader(inf));
        String line = null;


        int id = 0;
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

            Food food = new Food();
            food.setId(id);
            food.setName(f.get("Name"));
            food.setPrice(new BigDecimal(f.get("Price")));
            food.setSale("月售" + f.get("Sales"));
            food.setType(f.get("Type"));
            int resID = context.getResources().getIdentifier(f.get("Imagename"), "drawable", "com.hd.ele");

            food.setIcon(resID);
            fList.add(food);
            ++id;
        }
        reader.close();/*
        for (int i = id; i < 100; i++) {
            Food food = new Food();
            food.setId(i);
            food.setName("食品" + i + 1);
            food.setPrice(BigDecimal.valueOf((new Random().nextDouble() * 100)).setScale(1, BigDecimal.ROUND_HALF_DOWN));
            food.setSale("月售" + new Random().nextInt(100));
            food.setType("类别" + i / 10);
            int resID = context.getResources().getIdentifier("food" + new Random().nextInt(8), "drawable", "com.hd.ele");
            food.setIcon(resID);
            fList.add(food);
        }*/
        return fList;
    }

    public static List<Food> getDetails(List<Food> fList) {
        ArrayList<Food> flist = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            if (fList.size() > i * 10) {
                flist.add(fList.get(i * 10 - 1));
                flist.add(fList.get(i * 10));
            } else {
                break;
            }
        }
        return flist;
    }

    public static List<Comment> getComment() {
        ArrayList<Comment> cList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            cList.add(new Comment());
        }
        return cList;
    }
}

