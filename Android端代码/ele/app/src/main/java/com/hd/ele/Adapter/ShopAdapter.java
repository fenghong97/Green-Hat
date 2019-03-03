package com.hd.ele.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hd.ele.Bean.Shop;
import com.hd.ele.FoodListActivity;
import com.hd.ele.R;


import java.util.List;

/**
 * Created by Administrator on 2018/12/20 0020.
 */

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {

   private List<Shop> mShopList;
   private Context mContext;

   public ShopAdapter(List<Shop> shopList){
       mShopList=shopList;
   }

   static class ViewHolder extends RecyclerView.ViewHolder{
       View shopView;
       ImageView shopImage;
       TextView shopName;
       TextView shopScore;
       TextView shopSale;
       TextView shopSprice;
       TextView shopDprice;
       TextView shopDistance;
       TextView shopTime;
       public ViewHolder(View view){
           super(view);
           shopView=view;
           shopImage=(ImageView)view.findViewById(R.id.shop_image);
           shopName=(TextView)view.findViewById(R.id.shop_name);
           shopScore=(TextView)view.findViewById(R.id.shop_score) ;
           shopSale=(TextView)view.findViewById(R.id.shop_sale) ;
           shopSprice=(TextView)view.findViewById(R.id.shop_sprice);
           shopDprice=(TextView)view.findViewById(R.id.shop_dprice);
           shopDistance=(TextView)view.findViewById(R.id.shop_distance);
           shopTime=(TextView)view.findViewById(R.id.shop_time);
       }
   }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop,parent,false);
        mContext=parent.getContext();
        final ViewHolder holder=new ViewHolder(view);
        holder.shopView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                Shop shop=mShopList.get(position);
                //action
                Toast.makeText(mContext, shop.getName(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(mContext, FoodListActivity.class);
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Shop shop=mShopList.get(position);
//        holder.shopImage.setText(shop.getImageURL());
        holder.shopName.setText(shop.getName());
        holder.shopScore.setText(shop.getScore());
        holder.shopSale.setText("月售"+shop.getSale());
        holder.shopDprice.setText("起送¥"+shop.getDprice());
        holder.shopSprice.setText("配送费¥"+shop.getSprice());
        holder.shopDistance.setText(shop.getDistance()+"m");
        holder.shopTime.setText(shop.getTime()+"分钟");
        String url=shop.getImageURL();
//        Log.d("---------------",url);
        Glide.with(mContext)
                .load(url)
                .into(holder.shopImage);
    }

    @Override
    public int getItemCount(){
        return mShopList.size();
    }

}
