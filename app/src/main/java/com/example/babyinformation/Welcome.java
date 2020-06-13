package com.example.babyinformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Welcome extends AppCompatActivity {

    private Button btn_Login;
    private Button btn_Signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btn_Login = (Button) findViewById(R.id.button_login);
        btn_Signup = (Button) findViewById(R.id.button_signup);

        btn_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intsign = new Intent(Welcome.this, userinfo.class);
                startActivity(intsign);
            }
        });

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intlogin = new Intent(Welcome.this, login.class);
                startActivity(intlogin);
            }
        });

    }

}
