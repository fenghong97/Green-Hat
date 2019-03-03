package com.hd.ele.Bean;

/**
 * Created by Administrator on 2018/12/19 0019.
 */

public class Shop {
    private String name;
    private String imageURL;
    private String score;
    private String sale;
    private String sprice;
    private String dprice;
    private String distance;
    private String time;

    public Shop(String name,String imageURL,String score,String sale,String sprice,String dprice,String distance,String time){
        this.name=name;
        this.imageURL=imageURL;
        this.score=score;
        this.sale=sale;
        this.sprice=sprice;
        this.dprice=dprice;
        this.distance=distance;
        this.time=time;
    }

    public Shop(){

    }


    public void setName(String name){
        this.name=name;
    }

    public void setImageURL(String imageURL) {
        this.imageURL=imageURL;
    }

    public void setScore(String score){
        this.score=score;
    }

    public void setSale(String sale){
        this.sale=sale;
    }

    public void setSprice(String sprice){
        this.sprice=sprice;
    }

    public void setDprice(String dprice){
        this.dprice=dprice;
    }

    public void setDistance(String distance){
        this.distance=distance;
    }

    public void setTime(String time){
        this.time=time;
    }

    public String getName(){
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getScore(){
        return score;
    }

    public String getSale(){
        return sale;
    }

    public String getSprice(){
        return sprice;
    }

    public String getDprice(){
        return dprice;
    }

    public String getDistance(){
        return distance;
    }

    public String getTime(){
        return time;
    }
}
