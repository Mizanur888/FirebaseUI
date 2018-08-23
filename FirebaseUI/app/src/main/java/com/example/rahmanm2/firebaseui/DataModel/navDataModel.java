package com.example.rahmanm2.firebaseui.DataModel;

import com.example.rahmanm2.firebaseui.R;

import java.util.ArrayList;

public class navDataModel {
    private int ImageID;
    private String imageTitle;
    public static ArrayList<navDataModel> Listmodel;// = new ArrayList<navDataModel>();
    public navDataModel(){

    }
    public navDataModel(int imageID, String imageTitle) {
        ImageID = imageID;
        this.imageTitle = imageTitle;
    }

    public int getImageID() {
        return ImageID;
    }

    public void setImageID(int imageID) {
        ImageID = imageID;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    private static int[]images(){
        int []images ={
                R.drawable.ic_add_a_photo_black_24dp,
                R.drawable.ic_library_music_black_24dp,
                R.drawable.ic_mail_black_24dp
        };
        return images;
    }
    private static String[]getImageTit(){
         String []titleds ={
            "Photo","Music","Email"
        };
        return titleds;
    }


    public static ArrayList<navDataModel>getAllNavDataModel(){
        Listmodel = new ArrayList<navDataModel>();
        int image[] = images();
        String tit[] = getImageTit();

        for(int i = 0;i<image.length;i++){
            navDataModel model = new navDataModel();
            model.setImageID(image[i]);
            model.setImageTitle(tit[i]);
            Listmodel.add(model);
        }
        return Listmodel;
    }
}
