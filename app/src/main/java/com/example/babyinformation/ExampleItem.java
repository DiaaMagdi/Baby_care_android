package com.example.babyinformation;

public class ExampleItem {
    public int imageResource;
    public String text1, text2, text3, text4;

    public ExampleItem(int mImageResource, String mText1, String mText2, String mText3, String mText4) {
        imageResource = mImageResource;
        text1 = mText1;
        text2 = mText2;
        text3 = mText3;
        text4 = mText4;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }

    public String getText3() {
        return text3;
    }

    public String getText4() {
        return text4;
    }
}

