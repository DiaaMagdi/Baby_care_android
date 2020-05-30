package com.example.babyinformation.data;

import com.example.babyinformation.pojo.Vaccination_ChildrenData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BackendInterface {

    /// Vaccination ////////////////////////////////////////////////////////////////////////////////
    @GET("public/api/babyinfo/{mother_id}")
    public Call<List<Vaccination_ChildrenData>> getChildren(@Path("mother_id") int PostId);

}
