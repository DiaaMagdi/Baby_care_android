package com.example.babyinformation;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.babyinformation.RegisterData.api.ApiService;
import com.example.babyinformation.RegisterData.api.RetrofitClient;
import com.example.babyinformation.RegisterData.model.Register;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class userinfo extends AppCompatActivity {
    //initialize variable
    EditText nameuser, emailuser, passuser;
    RadioButton parentradio ,sitterradio;
    String[] gender = {"Male","Female"};
    Button button;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

        //assign variable
        nameuser = (EditText) findViewById(R.id.nameuser);
        emailuser = (EditText) findViewById(R.id.emailuser);
        passuser = (EditText) findViewById(R.id.passuser);
        parentradio =(RadioButton)findViewById(R.id.radioparent);
        sitterradio =(RadioButton)findViewById(R.id.radiositter);
        button = (Button) findViewById(R.id.button);
        Spinner spinner = findViewById(R.id.gender);
        SpinAdapter adapter = new SpinAdapter(this,R.layout.customspinner,gender);
        spinner.setAdapter(adapter);
/*
//initialize validation style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
//add validation for name
        awesomeValidation.addValidation(this,R.id.nameuser, RegexTemplate.NOT_EMPTY,R.string.invalide_name);
//add validation for emali
        awesomeValidation.addValidation(this,R.id.emailuser, Patterns.EMAIL_ADDRESS,R.string.invalide_email);
//add validation for password
        awesomeValidation.addValidation(this,R.id.passuser,".{6,}",R.string.invalide_password);*/
//confirm password

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check validation

                SignUp();

            }
        });

    }

    private void SignUp()
    {
        String name=nameuser.getText().toString().trim();
        String email=emailuser.getText().toString().trim();
        String password=passuser.getText().toString().trim();


        if (email.isEmpty()) {
            emailuser.requestFocus();
            Toast.makeText(this, "Email is Required", Toast.LENGTH_SHORT).show();
            emailuser.setError("email is required");
            return;
        }
        if (name.isEmpty()) {
            nameuser.requestFocus();
            Toast.makeText(this, "Email is Required", Toast.LENGTH_SHORT).show();
            nameuser.setError("name is required");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailuser.getText().toString().trim()).matches()) {
            emailuser.requestFocus();
            emailuser.setError("email Is Invalid");
            Toast.makeText(this, "Email must be in this way 'example@example.com' ", Toast.LENGTH_LONG).show();
            return;
        }

        if (password.length() < 6) {
            passuser.requestFocus();
            passuser.setError("Password Is Invalid");
            Toast.makeText(this, "Password Must be more 6 character", Toast.LENGTH_LONG).show();
            return;
        }



        RetrofitClient.getClient().create(ApiService.class).sendRegister(email,name,"male","parent",password).enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if (response.isSuccessful())
                {
                    Intent intent = new Intent(userinfo.this, Baby_information.class);
                    intent.putExtra("dara",response.body().getName());
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {

            }
        });
    }
}
