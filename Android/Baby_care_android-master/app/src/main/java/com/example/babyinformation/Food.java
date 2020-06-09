package com.example.babyinformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Food extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
    }

    public void Meat(View view) {
        Intent intent = new Intent(Food.this, Nuts.class);
        intent.putExtra("Post", 10);
        startActivity(intent);
    }

    public void Beverages(View view) {
        Intent intent = new Intent(Food.this, Nuts.class);
        intent.putExtra("Post", 11);
        startActivity(intent);
    }

    public void Seafood(View view) {
        Intent intent = new Intent(Food.this, Nuts.class);
        intent.putExtra("Post", 12);
        startActivity(intent);
    }

    public void Snacks(View view) {
        Intent intent = new Intent(Food.this, Nuts.class);
        intent.putExtra("Post", 7);
        startActivity(intent);
    }

    public void Fruits(View view) {
        Intent intent = new Intent(Food.this, Nuts.class);
        intent.putExtra("Post", 8);
        startActivity(intent);
    }

    public void Supplements(View view) {
        Intent intent = new Intent(Food.this, Nuts.class);
        intent.putExtra("Post", 9);
        startActivity(intent);
    }

    public void Seasoning(View view) {
        Intent intent = new Intent(Food.this, Nuts.class);
        intent.putExtra("Post", 4);
        startActivity(intent);
    }

    public void Proccessedfood(View view) {
        Intent intent = new Intent(Food.this, Nuts.class);
        intent.putExtra("Post", 5);
        startActivity(intent);
    }

    public void diaryBased(View view) {
        Intent intent = new Intent(Food.this, Nuts.class);
        intent.putExtra("Post", 6);
        startActivity(intent);
    }

    public void nuts(View view) {
        Intent intent = new Intent(Food.this, Nuts.class);
        intent.putExtra("Post", 1);
        startActivity(intent);
    }

    public void vegetables(View view) {
        Intent intent = new Intent(Food.this, Nuts.class);
        intent.putExtra("Post", 2);
        startActivity(intent);
    }

    public void Wholegrains(View view) {
        Intent intent = new Intent(Food.this, Nuts.class);
        intent.putExtra("Post", 3);
        startActivity(intent);
    }
}
