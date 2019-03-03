package com.hd.ele.Detail;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;
import com.hd.ele.Adapter.CarAdapter;
import com.hd.ele.Bean.Food;
import com.hd.ele.FoodListActivity;
import com.hd.ele.Fragment.HomeFragment;
import com.hd.ele.R;
import com.hd.ele.BaseActivity;
import com.hd.ele.Utils.ViewUtils;
import com.hd.ele.View.AddWidget;
import com.hd.ele.View.ListContainer;
import com.hd.ele.View.ShopCarView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.hd.ele.FoodListActivity.CLEARCAR_ACTION;

/**
 * Created by Administrator on 2018/12/19 0019.
 */

public class DetailActivity extends BaseActivity implements AddWidget.OnAddClick {
    private Food food;
    private AddWidget detail_add;
    public BottomSheetBehavior behavior;
    private ShopCarView shopCarView;
    private CarAdapter carAdapter;
    private CoordinatorLayout detail_main;
    private DetailHeaderBehavior dhb;
    private View headerView;
    private int position = -1;
    private DetailAdapter dAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        food = (Food) getIntent().getSerializableExtra("food");
        position = getIntent().getIntExtra("position", -1);
        initViews();
    }

    private void initViews() {
        detail_main = (CoordinatorLayout) findViewById(R.id.detail_main);
        headerView = findViewById(R.id.headerview);
        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) headerView.getLayoutParams();
        dhb = (DetailHeaderBehavior) lp.getBehavior();
        ImageView ic_close=(ImageView)findViewById(R.id.ic_close) ;
        ic_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DetailActivity.this, FoodListActivity.class);
                startActivity(intent);
            }
        });
        ImageView iv_detail = (ImageView) findViewById(R.id.iv_detail);
        iv_detail.setImageResource(food.getIcon());
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText(food.getName());
        TextView detail_name = (TextView) findViewById(R.id.detail_name);
        detail_name.setText(food.getName());
        TextView detail_sale = (TextView) findViewById(R.id.detail_sale);
        detail_sale.setText(food.getSale() + " 好评率100%");
        TextView detail_price = (TextView) findViewById(R.id.detail_price);
        detail_price.setText(food.getStrPrice(mContext));
        detail_add = (AddWidget) findViewById(R.id.detail_add);
        detail_add.setData(this, food);
        detail_add.setState(food.getSelectCount());
        initRecyclerView();
        initShopCar();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.detail_recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        recyclerView.addItemDecoration(new SpaceItemDecoration());
        ((DefaultItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        dAdapter = new DetailAdapter(ListContainer.commandList, this);
        View header = View.inflate(mContext, R.layout.item_detail_header, null);
        dAdapter.addHeaderView(header);
        TextView footer = new TextView(mContext);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewUtils.dip2px(mContext, 100));
        footer.setText("已经没有更多了");
        footer.setTextSize(12);
        footer.setTextColor(ContextCompat.getColor(mContext, R.color.detail_hint));
        footer.setGravity(Gravity.CENTER_HORIZONTAL);
        footer.setPadding(0, 30, 0, 0);
        footer.setLayoutParams(lp);
        dAdapter.addFooterView(footer);
        dAdapter.bindToRecyclerView(recyclerView);
    }


    private void initShopCar() {
        shopCarView = (ShopCarView) findViewById(R.id.car_mainfl);
        final View blackView = findViewById(R.id.blackview);
        behavior = BottomSheetBehavior.from(findViewById(R.id.car_container));
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                shopCarView.sheetScrolling = false;
                dhb.setDragable(false);
                if (newState == BottomSheetBehavior.STATE_COLLAPSED || newState == BottomSheetBehavior.STATE_HIDDEN) {
                    blackView.setVisibility(View.GONE);
                    dhb.setDragable(true);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                shopCarView.sheetScrolling = true;
                blackView.setVisibility(View.VISIBLE);
                ViewCompat.setAlpha(blackView, slideOffset);
            }
        });
        blackView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                return true;
            }
        });
        shopCarView.setBehavior(behavior);
        RecyclerView carRecView = (RecyclerView) findViewById(R.id.car_recyclerview);
        carRecView.setLayoutManager(new LinearLayoutManager(mContext));
        ((DefaultItemAnimator) carRecView.getItemAnimator()).setSupportsChangeAnimations(false);
        ArrayList<Food> flist = new ArrayList<>();
        flist.addAll(FoodListActivity.carAdapter.getData());
        carAdapter = new CarAdapter(flist, this);
        carAdapter.bindToRecyclerView(carRecView);
        handleCommand(food);
        shopCarView.post(new Runnable() {
            @Override
            public void run() {
                dealCar(food);
            }
        });
    }

    @Override
    public void onAddClick(View view, Food fb) {
        dealCar(fb);
        ViewUtils.addTvAnim(view, shopCarView.carLoc, mContext, detail_main);
        if (!dhb.canDrag) {
            ViewAnimator.animate(headerView).alpha(1, -4).duration(410).onStop(new AnimationListener.Stop() {
                @Override
                public void onStop() {
                    finish();
                }
            }).start();
        }
    }

    @Override
    public void onSubClick(Food fb) {
        dealCar(fb);
    }


    private void dealCar(Food food) {
        BigDecimal amount = new BigDecimal(0.0);
        int total = 0;
        boolean hasFood = false;
        if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED || food.getId() == this.food.getId() && food.getSelectCount() !=
                this.food.getSelectCount()) {
            this.food = food;
            detail_add.expendAdd(food.getSelectCount());
            handleCommand(food);
        }
        List<Food> flist = carAdapter.getData();
        int p = -1;
        for (int i = 0; i < flist.size(); i++) {
            Food fb = flist.get(i);
            total += fb.getSelectCount();
            amount = amount.add(fb.getPrice().multiply(BigDecimal.valueOf(fb.getSelectCount())));
            if (fb.getId() == food.getId()) {
                hasFood = true;
                if (food.getSelectCount() == 0) {
                    p = i;
                } else {
                    carAdapter.setData(i, food);
                }
            }
        }
        if (p >= 0) {
            carAdapter.remove(p);
        } else if (!hasFood && food.getSelectCount() > 0) {
            carAdapter.addData(food);
            amount = amount.add(food.getPrice().multiply(BigDecimal.valueOf(food.getSelectCount())));
            total += food.getSelectCount();
        }
        shopCarView.showBadge(total);
        shopCarView.updateAmount(amount);
        Intent intent = new Intent(FoodListActivity.CAR_ACTION);
        if (food.getId() == this.food.getId()) {
            intent.putExtra("position", position);
        }
        intent.putExtra("food", food);
        sendBroadcast(intent);
    }

    private class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        SpaceItemDecoration() {
            this.space = ViewUtils.dip2px(mContext, 2);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.top = space;
            outRect.right = space;
            if (parent.getChildLayoutPosition(view) % 2 == 0) {
                outRect.left = 0;
            }
        }

    }

    private void handleCommand(Food food) {
        for (int i = 0; i < dAdapter.getData().size(); i++) {
            Food fb = dAdapter.getItem(i);
            if (fb.getId() == food.getId() && fb.getSelectCount() != food.getSelectCount()) {
                fb.setSelectCount(food.getSelectCount());
                dAdapter.setData(i, fb);
                dAdapter.notifyItemChanged(i);
                break;
            }
        }
    }

    public void clearCar(View view) {
        ViewUtils.showClearCar(mContext, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                List<Food> flist = carAdapter.getData();
                for (int i = 0; i < flist.size(); i++) {
                    Food fb = flist.get(i);
                    fb.setSelectCount(0);
                }
                carAdapter.setNewData(new ArrayList<Food>());
                shopCarView.showBadge(0);
                shopCarView.updateAmount(new BigDecimal(0.0));
                sendBroadcast(new Intent(CLEARCAR_ACTION));
            }
        });
    }
}

