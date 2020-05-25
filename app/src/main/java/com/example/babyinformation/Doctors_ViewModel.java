package com.example.babyinformation;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Doctors_ViewModel extends ViewModel {
    MutableLiveData<List<DoctorsDatabase>> DoctorsMutableData = new MutableLiveData<>();



    public Doctors_ViewModel() {

        Log.v("development", "callingAPI");

        Doctors_Client.getINSTANCE().getDoctors().enqueue(new Callback<List<DoctorsDatabase>>() {
            @Override
            public void onResponse(Call<List<DoctorsDatabase>> call, Response<List<DoctorsDatabase>> response) {
                DoctorsMutableData.setValue(response.body());
                Log.v("development", "success");
            }

            @Override
            public void onFailure(Call<List<DoctorsDatabase>> call, Throwable t) {
                Log.v("development", t.getMessage());

            }
        });
    }
}
