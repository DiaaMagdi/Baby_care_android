package com.example.babyinformation;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Photos_Client {
    private static final String BASE_URL="https://babycaregaduation.000webhostapp.com/";
    private Photos_Interface photos_Interface;
    private static Photos_Client INSTANCE;


    public Photos_Client(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        photos_Interface= retrofit.create(Photos_Interface.class);
    }


    public static Photos_Client getINSTANCE() {
        if (null == INSTANCE){
            INSTANCE = new Photos_Client();
        }
        return INSTANCE;
    }
    public Call<List<GetSet>> storeImages()
    {
        GetSet getSet = new GetSet();
        return photos_Interface.storeImage(getSet);
    }
}
