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

//initialize validation style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
//add validation for name
        awesomeValidation.addValidation(this,R.id.nameuser, RegexTemplate.NOT_EMPTY,R.string.invalide_name);
//add validation for emali
        awesomeValidation.addValidation(this,R.id.emailuser, Patterns.EMAIL_ADDRESS,R.string.invalide_email);
//add validation for password
        awesomeValidation.addValidation(this,R.id.passuser,".{6,}",R.string.invalide_password);
//confirm password

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check validation
                if(awesomeValidation.validate()& (parentradio.isChecked() || sitterradio.isChecked())){
                    Toast.makeText(getApplicationContext() , "Form Validation Succefully...",Toast.LENGTH_SHORT).show();
                    openActivity2();
                }
                else {
                    Toast.makeText(getApplicationContext() , "Please choose a baby sitter or a Parent ",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void openActivity2() {
        Intent intent = new Intent(this, Baby_information.class);
        startActivity(intent);
    }


}
