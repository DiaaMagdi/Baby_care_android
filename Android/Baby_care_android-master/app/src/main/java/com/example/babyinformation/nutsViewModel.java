package com.example.babyinformation;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class nutsViewModel extends ViewModel {
    MutableLiveData<List<Nuts_database>> nutsMutableData = new MutableLiveData<>();

    public static int z;

    public nutsViewModel() {

        Log.v("development", "callingAPI");

        Nuts_Client.getINSTANCE().getPost(z).enqueue(new Callback<List<Nuts_database>>() {
            @Override
            public void onResponse(Call<List<Nuts_database>> call, Response<List<Nuts_database>> response) {
                nutsMutableData.setValue(response.body());
                Log.v("development", "success");
            }

            @Override
            public void onFailure(Call<List<Nuts_database>> call, Throwable t) {
                Log.v("development", t.getMessage());

            }
        });
    }


}
