package com.example.babyinformation;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class detailsfood extends AppCompatActivity {

    nutsViewModel NutsViewModel;

    TextView pregnancy,afterBirth,breastFeeding,baby;
    ImageView imageView1,imageView2,imageView3,imageView9;
    int icons[] = {R.drawable.done, R.drawable.nope, R.drawable.k,R.drawable.kk};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsfood);

        pregnancy =  findViewById(R.id.pregnancy_tv);
        afterBirth = findViewById(R.id.after_birth_tv);
        breastFeeding = findViewById(R.id.breastfeeding_tv);
        baby = findViewById(R.id.baby_tv);
        imageView1= findViewById(R.id.imageView1);
        imageView2= findViewById(R.id.imageView2);
        imageView3= findViewById(R.id.imageView3);
        imageView9= findViewById(R.id.imageView9);


        int i = getIntent().getIntExtra("I", 0);

        NutsViewModel = ViewModelProviders.of(this).get(nutsViewModel.class);



        NutsViewModel.nutsMutableData.observe(this, databases -> {
            pregnancy.setText(databases.get(i).getPregnancy());
            afterBirth.setText(databases.get(i).getAfterBirth());
            breastFeeding.setText(databases.get(i).getBreastfeeding());
            baby.setText(databases.get(i).getBaby());
            imageView1.setImageResource(icons[Integer.parseInt(databases.get(i).getVal1())]);
            imageView2.setImageResource(icons[Integer.parseInt(databases.get(i).getVal2())]);
            imageView3.setImageResource(icons[Integer.parseInt(databases.get(i).getVal3())]);
            imageView9.setImageResource(icons[Integer.parseInt(databases.get(i).getVal4())]);

        });
    }
}
