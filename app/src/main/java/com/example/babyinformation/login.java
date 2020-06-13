package com.example.babyinformation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class login extends AppCompatActivity {

    private EditText Email;
    private EditText Password;
    private Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = (EditText) findViewById(R.id.etEmail);
        Password = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.loginbtn);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginValidation();
            }

        });


    }


    private void loginValidation () {
        if (Email.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Email is Required", Toast.LENGTH_SHORT).show();
            Email.setError("name is required");
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(Email.getText().toString().trim()).matches()) {
            Email.setError("email Is Invalid");
            Toast.makeText(this, "Email must be in this way 'example@example.com' ", Toast.LENGTH_LONG).show();

        }
        if (Password.getText().toString().length() < 6) {
            Password.setError("Password Is Invalid");
            Toast.makeText(this, "Password Must be more 6 character", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(this, parent_profile.class);
            startActivity(intent);
            finish();
        }


    }

}