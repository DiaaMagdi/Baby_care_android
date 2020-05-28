package com.example.babyinformation;

import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.babyinformation.data.BackendClient;
import com.example.babyinformation.pojo.Vaccination_ChildrenData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VaccinationViewModel extends ViewModel {
    MutableLiveData<List<Vaccination_ChildrenData>> childrenMutableLiveData = new MutableLiveData<>();

    public VaccinationViewModel() {
        childrenMutableLiveData.setValue(new ArrayList<>());
    }

    public void getChildren(){
        BackendClient.getInstance().getChildren().enqueue(new Callback<List<Vaccination_ChildrenData>>() {
            @Override
            public void onResponse(Call<List<Vaccination_ChildrenData>> call, Response<List<Vaccination_ChildrenData>> response) {
                childrenMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Vaccination_ChildrenData>> call, Throwable t) {
                Log.e("Vaccination", "Error, on failure, VaccinationViewModel");
            }
        });
    }
}
