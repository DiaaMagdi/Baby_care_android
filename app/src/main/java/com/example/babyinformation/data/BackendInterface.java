package com.example.babyinformation.data;

import com.example.babyinformation.pojo.Vaccination_ChildrenData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BackendInterface {

    /// Vaccination ////////////////////////////////////////////////////////////////////////////////
    @GET("api/json/get/41x2tjVsu")
    public Call<List<Vaccination_ChildrenData>> getChildren();

}
