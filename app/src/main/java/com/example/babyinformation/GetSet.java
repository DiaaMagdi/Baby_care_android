package com.example.babyinformation;

import android.graphics.Bitmap;


public class GetSet {

    Bitmap image;
    boolean haveImage;
    boolean status;
    int listItemPosition;
    private String uid;
    String imageSrc;


    public void setUid(String uid) {
        this.uid = uid;
    }


    public int getListItemPosition() {
        return listItemPosition;
    }

    public void setListItemPosition(int listItemPosition) {
        this.listItemPosition = listItemPosition;
    }

    public boolean isHaveImage() {
        return haveImage;
    }

    public void setHaveImage(boolean haveImage) {
        this.haveImage = haveImage;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
