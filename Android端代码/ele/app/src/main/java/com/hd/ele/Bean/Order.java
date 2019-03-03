package com.hd.ele.Bean;

public class Order {
    private String tv_name;
    private String tv_price;
    private String tv_number;
    private String tv_time;
    private String tv_Image;

//    public Order(String tv_name, String tv_Price) {
//        this.tv_name = tv_name;
//        this.tv_price = tv_Price;
//    }
//    public Order(String tv_name, String tv_time, String tv_price, String tv_number, String tv_Image) {
//        this.tv_name = tv_name;
//        this.tv_time = tv_time;
//        this.tv_price = tv_price;
//        this.tv_number = tv_number;
//        this.tv_Image = tv_Image;
//    }

    public Order(String tv_name, String tv_time, String tv_price, String tv_number) {
        this.tv_name = tv_name;
        this.tv_time = tv_time;
        this.tv_price = tv_price;
        this.tv_number = tv_number;
    }

    public void setTv_name(String tv_name) {
        this.tv_name = tv_name;
    }

    public void setTv_time(String tv_time) {
        this.tv_time = tv_time;
    }

    public void setTv_price(String tv_price) {
        this.tv_price = tv_price;
    }

    public void setTv_number(String tv_number) {
        this.tv_number = tv_number;
    }

    public void setTv_Image(String tv_Image) {
        this.tv_Image = tv_Image;
    }

    public String getTv_name() { return tv_name; }

    public String getTv_time() { return tv_time; }

    public String getTv_price() { return tv_price; }

    public String getTv_number() { return tv_number; }

    public String getTv_Image() {
        return tv_Image;
    }

}