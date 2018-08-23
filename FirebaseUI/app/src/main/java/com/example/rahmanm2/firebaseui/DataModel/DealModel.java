package com.example.rahmanm2.firebaseui.DataModel;

import java.io.Serializable;

public class DealModel implements Serializable{
    private String ID;
    private String Title;
    private String Desc;
    private String price;
    private String ImageUrl;

    public DealModel(){

    }

    public DealModel(String title, String desc, String price, String imageUrl) {
        this.Title = title;
        this.price = price;
        this.Desc = desc;
        ImageUrl = imageUrl;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
