package com.example.babyinformation;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Nuts_Client {
    private static final String BASE_URL="https://babycaregaduation.000webhostapp.com/";
    private Nuts_interface nuts_interface;
    private static Nuts_Client INSTANCE;


    public Nuts_Client(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        nuts_interface= retrofit.create(Nuts_interface.class);
    }


    public static Nuts_Client getINSTANCE() {
        if (null == INSTANCE){
            INSTANCE = new Nuts_Client();
        }
        return INSTANCE;
    }
    public Call<List<Nuts_database>> getPost(int x)
    {
        return nuts_interface.getPost(x);
    }
}
