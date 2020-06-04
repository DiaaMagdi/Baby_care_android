package com.example.babyinformation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Photos_Interface {
    @POST("public/api/upload/1")
    Call<List<GetSet>> storeImage(@Body GetSet getSet);
}
