package com.example.babyinformation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class Nuts extends AppCompatActivity implements NutsAdapter.OnNoteListener {

    nutsViewModel NutsViewModel;
    TextView category;


    String s2[];
    String s3[];
    String s4[];
    String s5[];
    int images[] = {R.drawable.almond, R.drawable.apricot_kernel, R.drawable.blackbeans};
    int icons[] = {R.drawable.done, R.drawable.nope, R.drawable.k, R.drawable.kk};
//    int Line[] = {R.drawable.line};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuts);
        nutsViewModel.z = getIntent().getIntExtra("Post", 0);
        NutsViewModel = ViewModelProviders.of(this).get(nutsViewModel.class);

        category = findViewById(R.id.textView3);
        RecyclerView recyclerView = findViewById(R.id.recView);
        recyclerView.setHasFixedSize(true);
        s2 = getResources().getStringArray(R.array.period1);
        s3 = getResources().getStringArray(R.array.period2);
        s4 = getResources().getStringArray(R.array.period3);
        s5 = getResources().getStringArray(R.array.period4);
        final NutsAdapter nutsAdapter = new NutsAdapter(this, s2, s3, s4, s5, images, icons, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(nutsAdapter);

        NutsViewModel.nutsMutableData.observe(this, databases -> {
            category.setText(databases.get(0).getCategory());
            nutsAdapter.setList(databases);
        });
    }


    @Override
    public void OnNoteClick(int position) {
        Intent intent = new Intent(this, detailsfood.class);
        intent.putExtra("I", position);
        startActivity(intent);
    }
}
