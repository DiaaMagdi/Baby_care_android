package com.example.babyinformation.data;

import com.example.babyinformation.pojo.ML_CryReason;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ML_Client {
    public static final String BASE_URL = "http://35.184.192.172/";
    private ML_interface ml_interface;
    private static ML_Client instance;

    public ML_Client() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ml_interface = retrofit.create(ML_interface.class);
    }

    public static ML_Client getInstance(){
        if(instance == null){
            instance = new ML_Client();
        }
        return instance;
    }

    public Call<ML_CryReason> getCryReason(MultipartBody.Part baby_cry_record_map){
        return ml_interface.getCryReason(baby_cry_record_map);
    }
}
