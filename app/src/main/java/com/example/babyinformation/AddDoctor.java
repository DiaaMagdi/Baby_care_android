package com.example.babyinformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddDoctor extends AppCompatActivity {

    Doctors_ViewModel doctors_viewModel;

    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    String s1[];
    int images[] = {R.drawable.logo, R.drawable.logo, R.drawable.logo, R.drawable.logo, R.drawable.logo};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);
        doctors_viewModel = ViewModelProviders.of(this).get(Doctors_ViewModel.class);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUp popUp = new PopUp();
                popUp.show(getSupportFragmentManager(), "Example bottom sheet");
            }
        });

        recyclerView = findViewById(R.id.recyclerView);

        s1 = getResources().getStringArray(R.array.Doctor);
        MyAdapter myAdapter = new MyAdapter(this, s1, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        doctors_viewModel.DoctorsMutableData.observe(this, databases -> {
            myAdapter.setList(databases);
        });

    }


}
