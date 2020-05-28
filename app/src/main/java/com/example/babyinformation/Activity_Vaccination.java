package com.example.babyinformation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.babyinformation.pojo.Vaccination_ChildrenData;

import java.util.ArrayList;
import java.util.List;

public class Activity_Vaccination extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Vaccine> vaccinationList;
    VaccinationViewModel vaccinationViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__vaccination);

        /*
        //manual insertion of vaccination list:
        vaccinationList = new ArrayList<>();
        vaccinationList.add(new Vaccine("Ahmed", "Measles", "2020-7-2"));
        vaccinationList.add(new Vaccine("Aser", "Measles", "2020-8-2"));
        vaccinationList.add(new Vaccine("Ahmed", "Measles 2nd dose", "2020-7-2"));
         */
        /*
        //automatic insertion of vaccination list without backend connection
        List<Vaccination_ChildrenData> vaccination_childrenData= new ArrayList<>();
        vaccination_childrenData.add(new Vaccination_ChildrenData(1, "Ahmed", "2019-2-2"));
        vaccination_childrenData.add(new Vaccination_ChildrenData(1, "Aser", "2020-3-1"));
        vaccinationList = Vaccine.getChildrenVaccineList(vaccination_childrenData);
        Log.v("VaccinationListSize", Integer.toString(vaccinationList.size()));
        */

        vaccinationViewModel = ViewModelProviders.of(this).get(VaccinationViewModel.class);
        vaccinationViewModel.getChildren();

        recyclerView = findViewById(R.id.recyclarView);
        VaccinationAdapter adapter = new VaccinationAdapter(vaccinationList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        vaccinationViewModel.childrenMutableLiveData.observe(this, new Observer<List<Vaccination_ChildrenData>>() {
            @Override
            public void onChanged(List<Vaccination_ChildrenData> vaccination_childrenData) {
                adapter.setList(Vaccine.getChildrenVaccineList(vaccination_childrenData));
            }
        });

    }
}
