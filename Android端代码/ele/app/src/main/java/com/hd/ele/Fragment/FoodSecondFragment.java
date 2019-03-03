package com.hd.ele.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hd.ele.Adapter.CommentAdapter;
import com.hd.ele.Bean.Comment;
import com.hd.ele.R;
import com.hd.ele.Utils.BaseUtils;
import com.hd.ele.View.CustomLoadMoreView;
import com.shizhefei.fragment.LazyFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/12/19 0019.
 */

public class FoodSecondFragment extends LazyFragment implements BaseQuickAdapter.RequestLoadMoreListener {
    private Context mContext;
    private ArrayList<Comment> cList = new ArrayList<>();
    private RecyclerView recycler;
    private CommentAdapter cAdapter;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_food_second);
        mContext = getActivity();
        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(mContext));
        cAdapter = new CommentAdapter(BaseUtils.getComment());
        View headerView = View.inflate(mContext, R.layout.item_comment_header, null);
        cAdapter.addHeaderView(headerView);
        cAdapter.setLoadMoreView(new CustomLoadMoreView());
        cAdapter.setOnLoadMoreListener(this, recycler);
        recycler.setAdapter(cAdapter);
    }


    @Override
    public void onLoadMoreRequested() {
        recycler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (cAdapter.getItemCount() > 30) {
                    cAdapter.loadMoreEnd();
                } else {
                    cAdapter.addData(BaseUtils.getComment());
                    cAdapter.loadMoreComplete();
                }
            }
        }, 2000);
    }
}

