package com.hd.ele.Adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hd.ele.Bean.Food;
import com.hd.ele.R;
import com.hd.ele.View.AddWidget;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2018/12/19 0019.
 */

public class CarAdapter extends BaseQuickAdapter<Food, BaseViewHolder> {
    private AddWidget.OnAddClick onAddClick;

    public CarAdapter(@Nullable List<Food> data, AddWidget.OnAddClick onAddClick) {
        super(R.layout.item_car, data);
        this.onAddClick = onAddClick;
    }


    @Override
    protected void convert(BaseViewHolder helper, Food item) {
        helper.setText(R.id.car_name, item.getName())
                .setText(R.id.car_price, item.getStrPrice(mContext, item.getPrice().multiply(BigDecimal.valueOf(item.getSelectCount()))))
        ;
        AddWidget addWidget = helper.getView(R.id.car_addwidget);
//		addWidget.setData(this, helper.getAdapterPosition(), onAddClick);
        addWidget.setData(onAddClick,item);
    }
}
