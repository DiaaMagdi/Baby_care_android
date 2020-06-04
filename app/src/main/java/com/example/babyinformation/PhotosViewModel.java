package com.example.babyinformation;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosViewModel extends ViewModel {
    MutableLiveData<List<GetSet>> PhotosMutableData = new MutableLiveData<>();



    public PhotosViewModel() {

        Log.v("development", "callingAPI");

        Photos_Client.getINSTANCE().storeImages().enqueue(new Callback<List<GetSet>>() {
            @Override
            public void onResponse(Call<List<GetSet>> call, Response<List<GetSet>> response) {
                Log.v("development", "success");
            }

            @Override
            public void onFailure(Call<List<GetSet>> call, Throwable t) {
                Log.v("development", t.getMessage());

            }
        });
    }
}
