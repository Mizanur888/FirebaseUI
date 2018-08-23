package com.example.rahmanm2.firebaseui.DataModel;


import com.example.rahmanm2.firebaseui.R;

import java.util.ArrayList;

public class menuModel {
    private int Imageurl;
    private String imageName;
    private String Title;
    public static ArrayList<menuModel>models;
    public menuModel(){

    }

    public menuModel(int imageurl, String imageName, String title) {
        Imageurl = imageurl;
        this.imageName = imageName;
        Title = title;
    }

    public int getImageurl() {
        return Imageurl;
    }

    public void setImageurl(int imageurl) {
        Imageurl = imageurl;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    private static int[]getIUmages(){
        int[]images = new int[]{
            R.drawable.burger,R.drawable.chickenrape,
                R.drawable.healethyfoods,R.drawable.pasta,
                  R.drawable.vegitables,R.drawable.steakegg,
                    R.drawable.my_bg
        };
        return images;
    }

    private static String[]getFoodsName(){
        String []titles = new String[]{
                "Burger","Chicken Rape","Healthy Foods",
                 "Pasta","Vegitables","Steak and Egg",
                 "Donats"
        };
        return titles;
    }
    public static ArrayList<menuModel>getAllData(){
        models = new ArrayList<menuModel>();
        int []images = getIUmages();
        String[]title = getFoodsName();
        for(int i = 0;i<images.length;i++){
            menuModel model = new menuModel();
            model.setImageurl(images[i]);
            model.setTitle(title[i]);

            models.add(model);
        }
        return models;
    }
}
