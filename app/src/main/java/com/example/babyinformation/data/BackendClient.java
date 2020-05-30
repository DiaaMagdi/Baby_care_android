package com.example.babyinformation.data;

import com.example.babyinformation.pojo.Vaccination_ChildrenData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BackendClient {
    public static final String BASE_URL = "https://babycaregaduation.000webhostapp.com/";
    private BackendInterface backendInterface;
    private static BackendClient instance;

    public BackendClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        backendInterface = retrofit.create(BackendInterface.class);
    }

    public static BackendClient getInstance(){
        if(instance == null){
            instance = new BackendClient();
        }
        return instance;
    }

    public Call<List<Vaccination_ChildrenData>> getChildren(int mother_id){
        return backendInterface.getChildren(mother_id);
    }
}
