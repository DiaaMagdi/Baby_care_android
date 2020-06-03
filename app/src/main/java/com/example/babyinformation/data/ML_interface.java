package com.example.babyinformation.data;

import com.example.babyinformation.pojo.ML_CryReason;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ML_interface {
    @Multipart
    @POST("predict")
    public Call<ML_CryReason> getCryReason(@Part MultipartBody.Part baby_cry_record_map);
}
