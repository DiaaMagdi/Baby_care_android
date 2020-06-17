package com.example.babyinformation;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Doctors_Client {

    private static final String BASE_URL="https://next.json-generator.com/";
    private Doctors_interface Doctors_interface;
    private static Doctors_Client INSTANCE;


    public Doctors_Client(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        Doctors_interface= retrofit.create(Doctors_interface.class);
    }


    public static Doctors_Client getINSTANCE() {
        if (null == INSTANCE){
            INSTANCE = new Doctors_Client();
        }
        return INSTANCE;
    }
    public Call<List<DoctorsDatabase>> getDoctors()
    {
        return Doctors_interface.getDoctors();
    }
}
