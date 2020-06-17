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

import com.example.babyinformation.RegisterData.api.ApiService;
import com.example.babyinformation.RegisterData.api.RetrofitClient;
import com.example.babyinformation.RegisterData.model.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
                Login();
            }

        });

    }

    private void Login () {

        String email=Email.getText().toString().trim();
        String password=Password.getText().toString().trim();
        if (email.isEmpty()) {
            Email.requestFocus();
            Toast.makeText(this, "Email is Required", Toast.LENGTH_SHORT).show();
            Email.setError("name is required");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(Email.getText().toString().trim()).matches()) {
            Email.requestFocus();
            Email.setError("email Is Invalid");
            Toast.makeText(this, "Email must be in this way 'example@example.com' ", Toast.LENGTH_LONG).show();
            return;
        }
        if (password.length() < 6) {
            Password.requestFocus();
            Password.setError("Password Is Invalid");
            Toast.makeText(this, "Password Must be more 6 character", Toast.LENGTH_LONG).show();
            return;
        }

        RetrofitClient.getClient().create(ApiService.class).sendLogin(email,password).enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.isSuccessful()) {
                  /*  if(response.body().getStatus()){
                  Intent intent = new Intent(login.this, parent_profile.class);
                    startActivity(intent);
                    finishAfterTransition();
                    Toast.makeText(login.this, "Success", Toast.LENGTH_SHORT).show();

                    }else{
                                        Toast.makeText(login.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();

                    }*/

                    Intent intent = new Intent(login.this, parent_profile.class);
                    startActivity(intent);
                    finishAfterTransition();
                    Toast.makeText(login.this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(login.this, response.message()+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(login.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}