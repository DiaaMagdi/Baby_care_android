package com.example.babyinformation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface Doctors_interface {
    @GET("api/json/get/EJQ7fk4jd")
    Call<List<DoctorsDatabase>> getDoctors();
}
