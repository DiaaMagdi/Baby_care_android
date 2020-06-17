package com.example.babyinformation.RegisterData.api;

import com.example.babyinformation.RegisterData.model.Login;
import com.example.babyinformation.RegisterData.model.Register;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    //Login
    @POST("login")
    Call<Login>sendLogin(
            @Query("email") String email,
            @Query("password") String password
    );

    //register
    @POST("register")
    @FormUrlEncoded
    Call<Register>sendRegister(
            @Field("email") String email,
            @Field("name") String name,
            @Field("gender") String gender,
            @Field("type") String type,
            @Field("password") String password
            //   @Field("phone") int phone
    );
}
