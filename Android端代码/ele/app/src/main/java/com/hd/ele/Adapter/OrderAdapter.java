package com.hd.ele.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hd.ele.Bean.Order;
import com.hd.ele.Bean.Shop;
import com.hd.ele.FoodListActivity;
import com.hd.ele.R;

import java.util.List;


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private List<Order> mOrderList;
    private Context mContent;

    public OrderAdapter(List<Order> orderList){
        mOrderList=orderList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View orderView;
//        ImageView shopImage;
        TextView orderName;
        TextView orderPrice;
        TextView orderNumber;
        TextView orderTime;
//        ImageView orderImage;
//        TextView shopSale;
//        TextView shopSprice;
//        TextView shopDprice;
//        TextView shopDistance;
//        TextView shopTime;
        public ViewHolder(View view){
            super(view);
            orderView=view;
            orderName=(TextView)view.findViewById(R.id.tv_name);
            orderPrice=(TextView)view.findViewById(R.id.tv_price);
            orderNumber=(TextView)view.findViewById(R.id.tv_number);
            orderTime=(TextView)view.findViewById(R.id.tv_time);
//            orderImage=(ImageView)view.findViewById(R.id.tv_Image);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order,parent,false);
        mContent=parent.getContext();
        final ViewHolder holder=new ViewHolder(view);
        holder.orderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                Order order=mOrderList.get(position);
                //action
                Toast.makeText(mContent, order.getTv_name(),Toast.LENGTH_SHORT).show();
//                Intent intent=new Intent(mContent, FoodListActivity.class);
//                mContent.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Order order=mOrderList.get(position);
//        holder.shopImage.setText(shop.getImageURL());
        holder.orderName.setText(order.getTv_name());
        holder.orderPrice.setText("¥"+order.getTv_price());
        holder.orderTime.setText(order.getTv_time());
        holder.orderNumber.setText("数量："+order.getTv_number()+"件");
//        holder.orderImage.setImageResource(order.getTv_Image());
  //        String image=order.getTv_Image();
  //        Glide.with(mContent)
  //                .load(image)
  //                .into(holder.orderImage);
//        holder.shopSale.setText("月售"+shop.getSale());
//        holder.shopDprice.setText("起送¥"+shop.getDprice());
//        holder.shopSprice.setText("配送费¥"+shop.getSprice());
//        holder.shopDistance.setText(shop.getDistance()+"m");
//        holder.shopTime.setText(shop.getTime()+"分钟");
//        String url=order.getImageURL();
//        Log.d("---------------",url);
//        Glide.with(mContext)
//                .load(url)
//                .into(holder.shopImage);
    }

    @Override
    public int getItemCount(){
        return mOrderList.size();
    }

}