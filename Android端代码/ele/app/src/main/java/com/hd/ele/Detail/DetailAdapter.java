package com.hd.ele.Detail;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hd.ele.Bean.Food;
import com.hd.ele.R;
import com.hd.ele.View.AddWidget;

import java.util.List;

/**
 * Created by Administrator on 2018/12/19 0019.
 */

class DetailAdapter extends BaseQuickAdapter<Food, BaseViewHolder> {
    private AddWidget.OnAddClick onAddClick;

    DetailAdapter(@Nullable List<Food> data, AddWidget.OnAddClick onAddClick) {
        super(R.layout.item_detail, data);
        this.onAddClick = onAddClick;
    }

    @Override
    protected void convert(BaseViewHolder helper, Food item) {
        helper.setText(R.id.textView6, item.getName())
                .setText(R.id.textView7, item.getSale())
                .setText(R.id.textView8, item.getStrPrice(mContext))
                .setImageResource(R.id.imageView2, item.getIcon())
        ;
        AddWidget addWidget = helper.getView(R.id.detail_addwidget);
        addWidget.setData(onAddClick,item);
    }
}

