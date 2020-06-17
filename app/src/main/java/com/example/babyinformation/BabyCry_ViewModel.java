package com.example.babyinformation;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.babyinformation.data.ML_Client;
import com.example.babyinformation.pojo.ML_CryReason;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BabyCry_ViewModel extends ViewModel {
    MutableLiveData<ML_CryReason> cryReasonMutableLiveData = new MutableLiveData<>();

    public BabyCry_ViewModel() {
        cryReasonMutableLiveData.setValue(new ML_CryReason("Processing audio", "Sucsess")); //TODO
    }

    public void getCryReason(MultipartBody.Part baby_cry_record_map){
        ML_Client.getInstance().getCryReason(baby_cry_record_map).enqueue(new Callback<ML_CryReason>() {
            @Override
            public void onResponse(Call<ML_CryReason> call, Response<ML_CryReason> response) {
                cryReasonMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ML_CryReason> call, Throwable t) {
                Log.e("cryAnalysis","BabyCry_ViewModel<>Onfailire");
            }
        });
    }
}
