package com.example.babyinformation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Nuts_interface {
    @GET("public/api/type/{id}")
    Call<List<Nuts_database>> getPost(@Path("id") int PostId);

}
