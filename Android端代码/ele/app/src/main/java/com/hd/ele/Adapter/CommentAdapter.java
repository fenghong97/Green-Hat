package com.hd.ele.Adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hd.ele.Bean.Comment;
import com.hd.ele.R;

import java.util.List;

/**
 * Created by Administrator on 2018/12/19 0019.
 */

public class CommentAdapter extends BaseQuickAdapter<Comment, BaseViewHolder> {

    public CommentAdapter(@Nullable List<Comment> data) {
        super(R.layout.item_comment, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Comment item) {

    }
}