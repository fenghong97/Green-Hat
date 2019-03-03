package com.hd.ele;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.florent37.viewanimator.ViewAnimator;
import com.hd.ele.Adapter.CarAdapter;
import com.hd.ele.Bean.Food;
import com.hd.ele.Behavior.AppBarBehavior;
import com.hd.ele.Fragment.FoodFirstFragment;
import com.hd.ele.Fragment.FoodSecondFragment;
import com.hd.ele.Utils.ViewUtils;
import com.hd.ele.View.AddWidget;
import com.hd.ele.View.ShopCarView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FoodListActivity extends BaseActivity implements AddWidget.OnAddClick {

    public static final String CAR_ACTION = "handleCar";
    public static final String CLEARCAR_ACTION = "clearCar";
    private CoordinatorLayout rootview;
    public BottomSheetBehavior behavior;
    public View scroll_container;
    private FoodFirstFragment foodFirstFragment;
    public static CarAdapter carAdapter;
    private ShopCarView shopCarView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_food_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        IntentFilter intentFilter = new IntentFilter(CAR_ACTION);
        intentFilter.addAction(CLEARCAR_ACTION);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case CAR_ACTION:
                    Food food = (Food) intent.getSerializableExtra("food");
                    Food fb = food;
                    int p = intent.getIntExtra("position", -1);
                    if (p >= 0 && p < foodFirstFragment.getFoodAdapter().getItemCount()) {
                        fb = foodFirstFragment.getFoodAdapter().getItem(p);
                        fb.setSelectCount(food.getSelectCount());
                        foodFirstFragment.getFoodAdapter().setData(p, fb);
                    } else {
                        for (int i = 0; i < foodFirstFragment.getFoodAdapter().getItemCount(); i++) {
                            fb = foodFirstFragment.getFoodAdapter().getItem(i);
                            if (fb.getId() == food.getId()) {
                                fb.setSelectCount(food.getSelectCount());
                                foodFirstFragment.getFoodAdapter().setData(i, fb);
                                break;
                            }
                        }
                    }
                    dealCar(fb);
                    break;
                case CLEARCAR_ACTION:
                    clearCar();
                    break;
            }
            if (CAR_ACTION.equals(intent.getAction())) {

            }
        }
    };

    private void initViews() {
        rootview = (CoordinatorLayout) findViewById(R.id.rootview);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViewpager();
        initShopCar();
    }

    private void initShopCar() {
        behavior = BottomSheetBehavior.from(findViewById(R.id.car_container));
        shopCarView = (ShopCarView) findViewById(R.id.car_mainfl);
        View blackView = findViewById(R.id.blackview);
        shopCarView.setBehavior(behavior, blackView);
        RecyclerView carRecView = (RecyclerView) findViewById(R.id.car_recyclerview);
//		carRecView.setNestedScrollingEnabled(false);
        carRecView.setLayoutManager(new LinearLayoutManager(mContext));
        ((DefaultItemAnimator) carRecView.getItemAnimator()).setSupportsChangeAnimations(false);
        carAdapter = new CarAdapter(new ArrayList<Food>(), this);
        carAdapter.bindToRecyclerView(carRecView);
    }

    private void initViewpager() {
        scroll_container = findViewById(R.id.scroll_container);
        ScrollIndicatorView mSv = (ScrollIndicatorView) findViewById(R.id.indicator);
        ColorBar colorBar = new ColorBar(mContext, ContextCompat.getColor(mContext, R.color.dicator_line_blue), 6,
                ScrollBar.Gravity.BOTTOM);
        colorBar.setWidth(ViewUtils.dip2px(mContext, 30));
        mSv.setScrollBar(colorBar);
        mSv.setSplitAuto(false);
        mSv.setOnTransitionListener(new OnTransitionTextListener().setColor(ContextCompat.getColor(mContext, R.color.dicator_line_blue),
                ContextCompat.getColor(mContext, R.color.black)));
        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
        IndicatorViewPager indicatorViewPager = new IndicatorViewPager(mSv, mViewPager);
        foodFirstFragment = new FoodFirstFragment();
        ViewpagerAdapter myAdapter = new ViewpagerAdapter(getSupportFragmentManager());
        indicatorViewPager.setAdapter(myAdapter);
    }

    private class ViewpagerAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
        private LayoutInflater inflater;
        private int padding;
        private String[] tabs = new String[]{"商品", "评价（4.8分）"};

        ViewpagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            inflater = LayoutInflater.from(mContext);
            padding = ViewUtils.dip2px(mContext, 20);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            convertView = inflater.inflate(R.layout.item_textview, container, false);
            TextView textView = (TextView) convertView;
            textView.setText(tabs[position]); //名称
            textView.setPadding(padding, 0, padding, 0);
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            switch (position) {
                case 0:
                    return foodFirstFragment;
            }
            return new FoodSecondFragment();
        }
    }

    @Override
    public void onAddClick(View view, Food fb) {
        dealCar(fb);
        ViewUtils.addTvAnim(view, shopCarView.carLoc, mContext, rootview);
    }


    @Override
    public void onSubClick(Food fb) {
        dealCar(fb);
    }

    private void dealCar(Food food) {
        HashMap<String, Long> typeSelect = new HashMap<>();//更新左侧类别badge用
        BigDecimal amount = new BigDecimal(0.0);
        int total = 0;
        boolean hasFood = false;
        if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            foodFirstFragment.getFoodAdapter().notifyDataSetChanged();
        }
        List<Food> flist = carAdapter.getData();
        int p = -1;
        for (int i = 0; i < flist.size(); i++) {
            Food fb = flist.get(i);
            if (fb.getId() == food.getId()) {
                fb = food;
                hasFood = true;
                if (food.getSelectCount() == 0) {
                    p = i;
                } else {
                    carAdapter.setData(i, food);
                }
            }
            total += fb.getSelectCount();
            if (typeSelect.containsKey(fb.getType())) {
                typeSelect.put(fb.getType(), typeSelect.get(fb.getType()) + fb.getSelectCount());
            } else {
                typeSelect.put(fb.getType(), fb.getSelectCount());
            }
            amount = amount.add(fb.getPrice().multiply(BigDecimal.valueOf(fb.getSelectCount())));
        }
        if (p >= 0) {
            carAdapter.remove(p);
        } else if (!hasFood && food.getSelectCount() > 0) {
            carAdapter.addData(food);
            if (typeSelect.containsKey(food.getType())) {
                typeSelect.put(food.getType(), typeSelect.get(food.getType()) + food.getSelectCount());
            } else {
                typeSelect.put(food.getType(), food.getSelectCount());
            }
            amount = amount.add(food.getPrice().multiply(BigDecimal.valueOf(food.getSelectCount())));
            total += food.getSelectCount();
        }
        shopCarView.showBadge(total);
        foodFirstFragment.getTypeAdapter().updateBadge(typeSelect);
        shopCarView.updateAmount(amount);
    }

    public void expendCut(View view) {
        float cty = scroll_container.getTranslationY();
        if (!ViewUtils.isFastClick()) {
            ViewAnimator.animate(scroll_container)
                    .translationY(cty, cty == 0 ? AppBarBehavior.cutExpHeight : 0)
                    .decelerate()
                    .duration(100)
                    .start();
        }
    }

    public void clearCar(View view) {
        ViewUtils.showClearCar(mContext, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clearCar();
            }
        });
    }

    private void clearCar(){
        Log.i("CLEARCAR", "clearCar: begin");
        List<Food> flist = carAdapter.getData();
        for (int i = 0; i < flist.size(); i++) {
            Food fb = flist.get(i);
            fb.setSelectCount(0);
        }
        carAdapter.setNewData(new ArrayList<Food>());
        foodFirstFragment.getFoodAdapter().notifyDataSetChanged();
        shopCarView.showBadge(0);
        foodFirstFragment.getTypeAdapter().updateBadge(new HashMap<String, Long>());
        shopCarView.updateAmount(new BigDecimal(0.0));
        Log.i("CLEARCAR", "clearCar: end");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
        //System.exit(0);
    }
}
