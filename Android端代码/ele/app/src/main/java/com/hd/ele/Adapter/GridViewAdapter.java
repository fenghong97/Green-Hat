package com.hd.ele.Adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.hd.ele.Bean.Kind;
import com.hd.ele.R;

import java.util.List;


public class GridViewAdapter extends BaseAdapter{
    private Context context;
    private List<Kind> lists;//数据源
    private int mIndex; // 页数下标，标示第几页，从0开始
    private int mPargerSize;// 每页显示的最大的数量

        public GridViewAdapter(Context context, List<Kind> lists, int mIndex, int mPargerSize) {
            this.context = context;
            this.lists = lists;
            this.mIndex = mIndex;
            this.mPargerSize = mPargerSize;
        }

        /**
         * 先判断数据集的大小是否足够显示满本页,如果够，则直接返回每一页显示的最大条目个数pageSize,如果不够，则有几项就返回几,(也就是最后一页的时候就显示剩余item)
         */
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return lists.size() > (mIndex + 1) * mPargerSize ?
                    mPargerSize : (lists.size() - mIndex*mPargerSize);
        }

    @Override
    public Kind getItem(int arg0) {
        // TODO Auto-generated method stub
        return lists.get(arg0 + mIndex * mPargerSize);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0 + mIndex * mPargerSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_kind, null);
            //holder.tv_name = (TextView)convertView.findViewById(R.id.item_name);
            holder.iv_nul = (ImageView)convertView.findViewById(R.id.item_image);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        //重新确定position因为拿到的总是数据源，数据源是分页加载到每页的GridView上的
        final int pos = position + mIndex * mPargerSize;//假设mPageSiez
        //假设mPagerSize=8，假如点击的是第二页（即mIndex=1）上的第二个位置item(position=1),那么这个item的实际位置就是pos=9
        //holder.tv_name.setText(lists.get(pos).getName() + "");
        holder.iv_nul.setImageResource(lists.get(pos).getUrl());
        //添加item监听
//        convertView.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                Toast.makeText(context, "您点击了"  + lists.get(pos).getName(), Toast.LENGTH_SHORT).show();
//            }
//        });
        return convertView;
    }
    static class ViewHolder{
        //private TextView tv_name;
        private ImageView iv_nul;
    }
}

